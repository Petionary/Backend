package back.petionary.account;

import back.petionary.common.util.validation.Validation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@RequiredArgsConstructor
@Getter
public enum MyRole {
    GUEST("ROLE_GUEST","비회원"),
    USER("ROLE_USER", "회원");

    private final String key;
    private final String string;
}