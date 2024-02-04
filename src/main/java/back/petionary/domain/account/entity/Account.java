package back.petionary.domain.account.entity;

import back.petionary.common.BaseEntity;
import back.petionary.common.util.validation.Validation;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Entity
public class Account extends BaseEntity {

    private final static int PHONE_SIZE = 11;
    private final static int NAME_SIZE = 30;

    @Column(nullable = false)
    private String email;

    private String phone;

    @Column(nullable = false, length = NAME_SIZE)
    private String name;

    private LocalDate birth;

    private String nickName;

    private String image;


    public Account(String email, String phone, String name, LocalDate birth) {
        Validation.validateNull(email, phone, name, birth);
        Validation.validateSize(phone, PHONE_SIZE);
        Validation.validateSize(name, NAME_SIZE);
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.birth = birth;
    }

    public void createNickName(String nickName) {
        this.nickName = nickName;
    }

    public void updateAccountInfo(String phone, String image){
        this.phone = phone;
        this.image = image;
    }
}