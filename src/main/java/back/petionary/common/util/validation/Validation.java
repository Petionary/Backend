package back.petionary.common.util.validation;

public class Validation {

    public static <T> void validateNull(T... elements) {
        for (T element : elements) {
            if(element == null) {
                throw new IllegalArgumentException();
            }
        }
    }

    public static <T> void validateSize(T element, int size) {
        if (element instanceof String) {
            if (element.toString().length() > size) {
                throw new IllegalArgumentException();
            }
        }
    }
}