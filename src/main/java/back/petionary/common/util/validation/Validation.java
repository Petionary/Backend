package back.petionary.common.util.validation;

import back.petionary.common.exception.type.CommonErrorCode;
import back.petionary.common.exception.CommonException;

public class Validation {
    public static <T> T validateNull(T element) {
        if(element == null) {
            throw new CommonException(CommonErrorCode.NOT_VALUE);
        }
        return element;
    }

    public static <T> T validateSize(T element, int size) {
        if(element instanceof String) {
            if(element.toString().length() > size) {
                throw new CommonException(CommonErrorCode.EXCEED_SIZE);
            }
            return element;
        }
        return null;
    }
}