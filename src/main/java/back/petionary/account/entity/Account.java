package back.petionary.account.entity;

import back.petionary.common.BaseEntity;
import back.petionary.common.util.validation.Validation;
import java.time.LocalDate;
import javax.annotation.PostConstruct;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Entity
public class Account extends BaseEntity{

    @Column(nullable = false)
    private String email;

    @Column(nullable = false, length = 11)
    private String phone;

    @Column(nullable = false, length = 30)
    private String name;

    @Column(nullable = false)
    private LocalDate birth;

    @Column(nullable = false)
    private Address address;

    public Account(String email, String phone, String name, LocalDate birth, Address address) {
        this.email = Validation.validateNull(email);
        this.phone = Validation.validateSize(Validation.validateNull(phone), 11);
        this.name = Validation.validateSize(Validation.validateNull(name), 30);
        this.birth = Validation.validateNull(birth);
        this.address = Validation.validateNull(address);
    }
}