package back.petionary.application.account.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDto {
    @JsonProperty("token_type")
    private String tokenType;
    @JsonProperty("access_token")
    private String accessToken;
    @JsonProperty("id_token")
    private String idToken;
    @JsonProperty("refresh_token")
    private String refreshToken;
    @JsonProperty("expores_in")
    private Integer expiresIn;
    @JsonProperty("refresh_token_expires_in")
    private Integer refreshTokenExpiresIn;
    private String scope;
}
