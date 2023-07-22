package back.petionary.common.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {

    @ExceptionHandler({CustomException.class})
    private ResponseEntity<ExceptionResponse> customException(CustomException customException) {
        return ExceptionResponse.customExceptionResponse(customException.getErrorCode());
    }
}
