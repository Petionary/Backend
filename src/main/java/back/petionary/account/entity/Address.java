package back.petionary.account.entity;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Embeddable
@NoArgsConstructor
@Getter
public class Address {

    @Column(nullable = false)
    private String zipCode; //우편번호

    @Column(nullable = false)
    private String detail; //상세주소

    public Address(String zipCode, String detail) {
        this.zipCode = Objects.requireNonNull(zipCode, "값이 존재하지 않습니다.");
        this.detail = Objects.requireNonNull(detail, "값이 존재하지 않습니다.");
    }
}
