package back.petionary.common.util.validation;

import back.petionary.common.exception.type.CommonErrorCode;
import back.petionary.common.exception.CommonException;
import java.util.Arrays;
import java.util.Objects;

public class Validation {

    public static <T> void validateNull(T... elements) {
        for (T element : elements) {
            if(element == null) {
                throw new CommonException(CommonErrorCode.NOT_VALUE);
            }
        }
    }

    public static <T> void validateSize(T element, int size) {
        if (element instanceof String) {
            if (element.toString().length() > size) {
                throw new CommonException(CommonErrorCode.EXCEED_SIZE);
            }
        }
    }
}