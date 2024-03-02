package back.petionary.security.oauth.provider;

import back.petionary.exception.PetionaryException;
import back.petionary.domain.account.entity.Account;
import back.petionary.domain.account.enums.Role;
import back.petionary.domain.account.repository.AccountRepository;
import back.petionary.domain.token.entity.Token;
import back.petionary.domain.token.repository.TokenRepository;
import back.petionary.security.auth.PrincipalDetails;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.UnsupportedJwtException;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;
import lombok.val;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
public class TokenProvider {

    private final AccountRepository accountRepository;
    private final TokenRepository tokenRepository;
    @Value("${jwt.secret-key}")
    private String secretKey;
    @Value("${jwt.access-token-expire}")
    private String ACCESS_TOKEN_EXPIRE_TIME;
    @Value("{jwt.refresh-token-expire}")
    private String REFRESH_TOKEN_EXPIRE_TIME;
    private  Key key;

    public TokenProvider(AccountRepository accountRepository, TokenRepository tokenRepository) {
        this.accountRepository = accountRepository;
        this.tokenRepository = tokenRepository;
    }

    /**
     * accessToken, refreshToken 생성
     */
    public LoginToken getToken(Long accountId, Role role) {
        LocalDateTime now = LocalDateTime.now();

        LocalDateTime accessTokenTime =
            now.plus(Long.parseLong(ACCESS_TOKEN_EXPIRE_TIME), ChronoUnit.SECONDS);
        LocalDateTime refreshTokenTime =
            now.plus(Long.parseLong(REFRESH_TOKEN_EXPIRE_TIME), ChronoUnit.SECONDS);
        String accessToken = createToken(
            accountId, accessTokenTime.toInstant(ZoneOffset.UTC).toEpochMilli(), role
        );
        LoginToken loginToken = null;
        Token token = tokenRepository.findByAccountId(accountId).orElse(null);
        if (token != null) {
            if (now.isAfter(token.getRefreshExpiredAt())) {
                throw new PetionaryException("리프레쉬 토큰 만료됨");
            }
            loginToken = new LoginToken(accessToken, accessTokenTime);
        }
        if (token == null) {
            String refreshToken = createToken(
                accountId, refreshTokenTime.toInstant(ZoneOffset.UTC).toEpochMilli(), role
            );
            Account account = accountRepository.findById(accountId)
                .orElseThrow(() -> new PetionaryException("회원이 존재하지 않습니다."));
            tokenRepository.save(
                new Token(refreshToken, accessToken, refreshTokenTime, accessTokenTime, account));
            loginToken = new LoginToken(
                accessToken,
                accessTokenTime
            );
        }
        return loginToken;
    }

    public Authentication getAuthentication(String token) {
        Account account = accountRepository.findById(getAccount(token)).orElse(null);
        if (account == null) {
            throw new RuntimeException();
        }
        PrincipalDetails loginUserDetail = new PrincipalDetails(account);
        return new UsernamePasswordAuthenticationToken(loginUserDetail, "",
            loginUserDetail.getAuthorities());
    }

    public Long getAccount(String token) {
        return Long.parseLong(
            Jwts.parserBuilder().setSigningKey(secretKey).build().parseClaimsJws(token).getBody()
                .getSubject());
    }


    public Boolean validateToken(String token) {
        try {
            byte[] keyBytes = Decoders.BASE64.decode(secretKey);
            key = Keys.hmacShaKeyFor(keyBytes);
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(token).getBody();
            return true;
        } catch (SecurityException | UnsupportedJwtException | IllegalArgumentException e) {
            e.printStackTrace();
        } catch (ExpiredJwtException e) {
            return false;
        }
        return false;
    }

    public String resolveToken(HttpServletRequest request) {
        return request.getHeader("Authorization");
    }

    //토큰 생성
    private String createToken(Long accountId, Long tokenExpireTime, Role role) {
        return Jwts.builder()
            .setSubject(Long.toBinaryString(accountId))
            .claim("auth", role)
            .setExpiration(new Date(tokenExpireTime))
            .signWith(key, SignatureAlgorithm.HS512)
            .compact();
    }
}