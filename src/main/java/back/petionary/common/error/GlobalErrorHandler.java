package back.petionary.common.error;

import back.petionary.common.error.custom.PetionaryException;
import back.petionary.common.error.custom.PetionaryExceptionResponse;
import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalErrorHandler {

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Map<String, String> getValidationResponse(MethodArgumentNotValidException e) {
        Map<String, String> error = new HashMap<>();
        e.getAllErrors().forEach(errorElement ->
            error.put(((FieldError)errorElement).getField(),errorElement.getDefaultMessage())
            );
        return error;
    }

    @ExceptionHandler({PetionaryException.class})
    public ResponseEntity<PetionaryExceptionResponse> getPetionaryExceptionResponse(PetionaryException exception) {
        return ResponseEntity.status(exception.getStatus())
            .body(PetionaryExceptionResponse.responsePetionaryException(exception));
    }
}
