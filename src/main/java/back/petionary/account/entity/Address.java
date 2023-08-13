package back.petionary.account.entity;

import back.petionary.common.util.validation.Validation;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
@Embeddable
public class Address {

    @Column(nullable = false)
    private String zipCode; //우편번호

    @Column(nullable = false)
    private String detail; //상세주소

    public Address(String zipCode, String detail) {
        Validation.validateNull(zipCode,detail);
        this.zipCode = zipCode;
        this.detail = detail;
    }
}