package back.petionary.common.error.custom;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {
    NOT_FOUND(HttpStatus.NOT_FOUND,"찾을 수 없습니다.");
    private final HttpStatus status;
    private final String message;

    public int getStatusCode() {
        return status.value();
    }
}
