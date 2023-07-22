package back.petionary.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum CustomErrorCode {
    NOT_VALUE(HttpStatus.BAD_REQUEST, "값이 존재하지 않습니다.");

    private HttpStatus status;
    private String message;
}
