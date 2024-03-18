package back.petionary.application.account.dto;

import back.petionary.domain.account.entity.Account;
import back.petionary.domain.account.enums.SocialType;
import lombok.Getter;

@Getter
public class AccountRequest {

    private String email;
    private String userName;


    public AccountRequest(String email, String userName) {
        this.email = email;
        this.userName = userName;
    }

    public Account createKakaoAccount(SocialType socialType) {
        return new Account(this.email, this.userName, socialType);
    }

    public Account createNaverAccount(SocialType socialType) {
        return new Account(this.email, this.userName, socialType);
    }
}
