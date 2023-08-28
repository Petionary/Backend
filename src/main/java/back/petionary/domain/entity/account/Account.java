package back.petionary.domain.entity.account;

import back.petionary.common.BaseEntity;
import back.petionary.common.util.validation.Validation;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
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

    @Column(nullable = false, length = PHONE_SIZE)
    private String phone;

    @Column(nullable = false, length = NAME_SIZE)
    private String name;

    @Column(nullable = false)
    private LocalDate birth;

    @Column(nullable = false)
    private Address address;

    public Account(String email, String phone, String name, LocalDate birth, Address address) {
        Validation.validateNull(email, phone, name, birth, address);
        Validation.validateSize(phone, PHONE_SIZE);
        Validation.validateSize(name, NAME_SIZE);
        this.email = email;
        this.phone = phone;
        this.name = name;
        this.birth = birth;
        this.address = address;
    }
}