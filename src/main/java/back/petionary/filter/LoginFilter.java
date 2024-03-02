package back.petionary.filter;

import back.petionary.domain.token.repository.TokenRepository;
import back.petionary.security.oauth.provider.TokenProvider;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

/**
 * packageName    : back.petionary.filter
 * fileName       : LoginFilter
 * author         : hoewoonjeong
 * date           : 3/2/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/2/24        hoewoonjeong               최초 생성
 */
@RequiredArgsConstructor
public class LoginFilter extends OncePerRequestFilter {

    private final TokenProvider tokenProvider;
    private final TokenRepository tokenRepository;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
        FilterChain filterChain) throws ServletException, IOException {
        String token = tokenProvider.resolveToken(request);
        if (token != null) {
            String[] loginTokens = token.split(" ");
            if (loginTokens.length != 2 || token.toLowerCase().startsWith("bearer ") ||
                tokenProvider.validateToken(loginTokens[1]) ||
                tokenRepository.existsByAccess(loginTokens[1])
            ) {
                return;
            }
            Authentication authentication = tokenProvider.getAuthentication(loginTokens[1]);
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }
    }
}
