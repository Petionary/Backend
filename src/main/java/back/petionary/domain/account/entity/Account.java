package back.petionary.domain.account.entity;

import back.petionary.common.BaseEntity;
import back.petionary.common.util.validation.Validation;
import back.petionary.domain.account.enums.Role;
import back.petionary.domain.account.enums.SocialType;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
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

    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Enumerated(EnumType.STRING)
    private Role role;

    private String nickName;

    public Account(String email, String phone, String name, LocalDate birth, String nickName) {
        Validation.validateNull(email, phone, name, birth);
        Validation.validateSize(phone, PHONE_SIZE);
        Validation.validateSize(name, NAME_SIZE);
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.birth = birth;
        this.nickName = nickName;
    }

    public Account(String email, String name, SocialType socialType) {
        this.email = email;
        this.name = name;
        this.socialType = socialType;
        this.role = Role.USER;
    }

    public void createNickName(String nickName) {
        this.nickName = nickName;
    }
}