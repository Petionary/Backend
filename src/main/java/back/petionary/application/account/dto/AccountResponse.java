package back.petionary.application.account.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AccountResponse {

    private long id;
    private Kakaoproperties properties;
    @JsonProperty("naver_account")
    private NaverAccount naverAccount;

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class Kakaoproperties {
        private String nickname;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class NaverAccount {
        private NaverProfile profile;
        private String email;
    }


    @AllArgsConstructor
    @NoArgsConstructor
    @Getter
    public static class NaverProfile {
        private String nickname;
    }
}
