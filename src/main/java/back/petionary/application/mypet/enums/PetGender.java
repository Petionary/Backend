package back.petionary.application.mypet.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
@AllArgsConstructor
@Getter
public enum PetGender {
    MALE("수컷"), FEMALE("암컷");

    private String type;
}
