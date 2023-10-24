package back.petionary.common.error.custom;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PetionaryExceptionResponseTest {

    @Test
    @DisplayName("NOT_FOUND 예외가 발생하면 '찾을 수 없습니다'를 반환한다.")
    void responsePetionaryException() {
        //given
        ErrorCode errorCode = ErrorCode.NOT_FOUND;

        //when && then
        assertThatThrownBy(() -> {throw new PetionaryException(errorCode);})
            .isInstanceOf(PetionaryException.class)

            .message().isEqualTo("찾을 수 없습니다.");
    }

}