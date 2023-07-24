package back.petionary.common.exception;

import back.petionary.common.exception.response.ExceptionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler({CommonException.class})
    private ResponseEntity<ExceptionResponse> customException(CommonException commonException) {
        return commonException.toExceptionResponse().toExceptionResponseEntity();
    }
}
