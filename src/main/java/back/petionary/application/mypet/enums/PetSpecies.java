package back.petionary.application.mypet.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum PetSpecies {
    DOG("개"), CAT("고양이"), OTHER("기타");

    private String type;
}
