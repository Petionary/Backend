package back.petionary.common.exception;

import back.petionary.common.exception.response.ExceptionResponse;
import back.petionary.common.exception.type.CommonErrorCode;
import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
@Builder
public class CommonException extends RuntimeException {
    private final CommonErrorCode errorCode;

    public ExceptionResponse toExceptionResponse() {
        return new ExceptionResponse(errorCode.getStatus(),errorCode.getMessage(), LocalDateTime.now());
    }
}
