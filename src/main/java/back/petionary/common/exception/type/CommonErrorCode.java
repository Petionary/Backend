package back.petionary.common.exception.type;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CommonErrorCode {
    NOT_VALUE(HttpStatus.BAD_REQUEST, "값이 존재하지 않습니다."),
    EXCEED_SIZE(HttpStatus.BAD_REQUEST, "범위를 벗어났습니다.");

    private HttpStatus status;
    private String message;
}
