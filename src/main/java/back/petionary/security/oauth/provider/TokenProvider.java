package back.petionary.security.oauth.provider;

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
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

@Component
class TokenProvider {

    private AccountRepository accountRepository;
    private TokenRepository tokenRepository;
    @Value("${jwt.secret-key}")
    private String secretKey;
    @Value("${jwt.access-token-expire-time}")
    private String ACCESS_TOKEN_EXPIRE_TIME;
    @Value("{jwt.refresh-token-expire-time}")
    private String REFRESH_TOKEN_EXPIRE_TIME;
    private Key key;

    public TokenProvider(AccountRepository accountRepository, TokenRepository tokenRepository) {
        this.accountRepository = accountRepository;
        this.tokenRepository = tokenRepository;
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        this.key = Keys.hmacShaKeyFor(keyBytes);
    }

    /**
     * accessToken, refreshToken 생성
     */
    public void getToken(Long accountId, Role role, Date dateNow) {
        LocalDateTime now = LocalDateTime.now();
        long nowTokenExpire = now.toInstant(ZoneOffset.UTC).toEpochMilli();
        LocalDateTime accessTokenTime = now.plus(Long.parseLong(ACCESS_TOKEN_EXPIRE_TIME),
            ChronoUnit.SECONDS);
        LocalDateTime refreshTokenTime = now.plus(Long.parseLong(REFRESH_TOKEN_EXPIRE_TIME),
            ChronoUnit.SECONDS);

        long accessTokenExpire = accessTokenTime.toInstant(ZoneOffset.UTC).toEpochMilli();
        long refreshTokenExpire = refreshTokenTime.toInstant(ZoneOffset.UTC).toEpochMilli();
        String accessToken = createToken(
            accountId, accessTokenExpire, role
        );
        Role loginUserRole = role;
        Token token = tokenRepository.findByAccountId(accountId).orElse(null);
        if (token != null) {
            if (nowTokenExpire > token.getRefreshExpiredAt().getLong()) {

            }
        }
            ?.let {
            if (nowTokenExpire > it.expireAt) {
                throw KepaException(ExceptionCode.REFRESH_TOKEN_EXPIRE)
            }
            val account = accountRepository.findByIdOrNull(it.id)
                ?:throw KepaException(ExceptionCode.NOT_EXSISTS_INFO)
            LoginToken(accessToken = accessToken, refreshToken = it.token,
                accessTokenExpiredAt = Date(accessTokenExpire),
                refreshTokenExpiredAt = Date(it.expireAt), id = account.id)
        }
        if (loginToken == null) {
            val refreshToken = createToken(
                id = id,
                tokenExpireTime = refreshTokenExpire,
                role = role
            )
            val account = accountRepository.findByIdOrNull(id)
                ?:throw KepaException(ExceptionCode.NOT_EXSISTS_INFO)
            val savedRefreshToken = refreshTokenRepository.save(
                RefreshToken(account = account, token = refreshToken, expireAt = refreshTokenExpire,
                    role = loginUserRole))
            loginToken = LoginToken(accessToken = accessToken, refreshToken = refreshToken,
                accessTokenExpiredAt = Date(accessTokenExpire),
                refreshTokenExpiredAt = Date(refreshTokenExpire), id = savedRefreshToken.id)
        }
        //return loginToken
    }

    public Authentication getAuthentication(String token) {
        Account account = accountRepository.findById(getAccount(token)).orElse(null);
        if (account == null) {
            throw new RuntimeException();
        }
        val loginUserDetail = new PrincipalDetails(account);
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