package back.petionary.application.account.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AccountResponse {

    private long id;
    private Kakaoproperties properties;
    @JsonProperty("kakao_account")
    private KakaoAccount kakaoAccount;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Kakaoproperties {
        private String nickname;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class KakaoAccount {
        private KakaoProfile profile;
        private String email;
    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class KakaoProfile {
        private String nickname;
    }
}
