package back.petionary.security.oauth.provider;

import back.petionary.security.auth.PrincipalDetails;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TokenProvider {

    public String create(PrincipalDetails principalDetails) {
        PrincipalDetails principal = principalDetails;
        int sec = 1800000;
        String token = JWT.create()
                .withSubject("petionary")
                .withExpiresAt(new Date(System.currentTimeMillis() + (sec)))
                .withClaim("id", principal.getAccount().getId())
                .withClaim("loginId", principal.getAccount().getEmail())
                .sign(Algorithm.HMAC512("petionary"));
        return token;
    }
}
