package back.petionary.common.error.custom;


import java.time.LocalDateTime;
import lombok.Builder;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
public class PetionaryExceptionResponse {

    private final LocalDateTime time;
    private final int statusCode;
    private final String message;

    public static PetionaryExceptionResponse responsePetionaryException(
        PetionaryException petionaryException) {
        return PetionaryExceptionResponse.builder()
            .statusCode(petionaryException.getStatusCode())
            .time(LocalDateTime.now())
            .message(petionaryException.getMessage())
            .build();
    }
}
