package back.petionary.common.exception.response;


import java.time.LocalDateTime;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@AllArgsConstructor
@Getter
@Builder
public class ExceptionResponse {

    private final HttpStatus status;
    private final String message;
    private final LocalDateTime time;

    public ResponseEntity<ExceptionResponse> toExceptionResponseEntity() {
        return ResponseEntity
            .status(this.status.value())
            .body(this);
    }
}
