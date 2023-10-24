package back.petionary.common.error.custom;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public class PetionaryException extends RuntimeException {
    private final ErrorCode errorCode;

    public HttpStatus getStatus() {
        return errorCode.getStatus();
    }
    public int getStatusCode() {
        return errorCode.getStatusCode();
    }
    public String getMessage() {
        return errorCode.getMessage();
    }
}
