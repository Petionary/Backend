package back.petionary.security.oauth.provider;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Getter;

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
