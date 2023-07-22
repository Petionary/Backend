package back.petionary.account.entity;

import back.petionary.common.exception.CustomErrorCode;
import back.petionary.common.exception.CustomException;
import java.util.Objects;
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
        this.zipCode = validate(zipCode);
        this.detail = validate(detail);
    }
    private String validate(String element) {
        if(element == null) {
            throw new CustomException(CustomErrorCode.NOT_VALUE);
        }
        return element;
    }
}


