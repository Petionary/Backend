package back.petionary.application.account.dto;

import back.petionary.domain.account.entity.Account;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AccountRequest {

    private String email;
    private String userName;


    public AccountRequest(String email, String userName) {
        this.email = email;
        this.userName = userName;
    }

    public Account kakaoOAuthToEntity() {
        return new Account(this.email, this.userName);
    }
}
