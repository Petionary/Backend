package back.petionary.security.oauth.provider;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor
@Getter
public class LoginToken {
    private String grantType = "Bearer";
    private String accessToken;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDateTime accessTokenExpiredAt;

    public LoginToken(String accessToken, LocalDateTime accessTokenExpiredAt) {
        this.accessToken = accessToken;
        this.accessTokenExpiredAt = accessTokenExpiredAt;
    }
}
