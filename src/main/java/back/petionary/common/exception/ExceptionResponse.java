package back.petionary.common.exception;


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

    public static ResponseEntity<ExceptionResponse> customExceptionResponse(CustomErrorCode errorCode) {
        return ResponseEntity
            .status(errorCode.getStatus())
            .body(ExceptionResponse.builder()
                .status(errorCode.getStatus())
                .message(errorCode.getMessage())
                .time(LocalDateTime.now())
                .build()
            );
    }
}
