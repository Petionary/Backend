package back.petionary.config.oauth;

import back.petionary.account.entity.Account;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
//    private String picture;

    public SessionUser(Account account) {
        this.name = account.getName();
        this.email = account.getEmail();
//        this.picture = account.getPicture();
    }
}