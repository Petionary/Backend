package back.petionary.application.account.dto;

import lombok.Getter;

@Getter
public class AccountRequest {

    private String email;
    private String userName;


    public AccountRequest(String email, String userName) {
        this.email = email;
        this.userName = userName;
    }
}
