package back.petionary.account.entity;

import back.petionary.common.BaseEntity;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Account extends BaseEntity {
    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String phone;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private LocalDate birth;
    @Column(nullable = false)
    private Address address;

    public Account(String email, String phone, String name, LocalDate birth, Address address) {
        this.email = Objects.requireNonNull(email,"값이 존재하지 않습니다."); // 커스텀 예외로 변경
        this.phone = Objects.requireNonNull(phone,"값이 존재하지 않습니다.");
        this.name = Objects.requireNonNull(name,"값이 존재하지 않습니다.");
        this.birth = Objects.requireNonNull(birth,"값이 존재하지 않습니다.");
        this.address = address;
    }
}
