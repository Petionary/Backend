package back.petionary.account.entity;

import back.petionary.common.BaseEntity;
import java.time.LocalDate;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@NoArgsConstructor
@Entity
public class Account extends BaseEntity {

    @Column(nullable = false)
    @NotNull
    private String email;

    @Column(nullable = false)
    @NotNull
    private String phone;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private LocalDate birth;

    @Column(nullable = false)
    private Address address;

    public Account(String email, String phone, String name, LocalDate birth, Address address) {
        this.email = nullValidate(email);
        this.phone = nullValidate(phone);
        this.name = nullValidate(name);
        this.birth = nullValidate(birth);
        this.address = nullValidate(address);
    }
}
