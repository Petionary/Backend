package back.petionary.application.mypet.dto.request;

import back.petionary.application.mypet.enums.PetGender;
import back.petionary.application.mypet.enums.PetSpecies;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MypetCreateRequest {
    private String imgUrl;
    private String petName;
    private String petBirth;
    private PetGender petGender;
    private PetSpecies petSpecies;
    private String speciesDetail;
    private String content;
}
