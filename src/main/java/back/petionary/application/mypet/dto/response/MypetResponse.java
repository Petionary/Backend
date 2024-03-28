package back.petionary.application.mypet.dto.response;

import back.petionary.application.mypet.enums.PetGender;
import back.petionary.application.mypet.enums.PetSpecies;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class MypetResponse {
    @ApiModelProperty(value = "이미지 url")
    private String imgUrl;

    @ApiModelProperty(value = "펫 이름", example = "밍밍")
    private String petName;

    @ApiModelProperty(value = "펫 생년월일", example = "2019-10-10")
    private LocalDate petBirth;

    @ApiModelProperty(value = "펫 성별 - MALE(수컷), FEMALE(암컷)", example = "MALE")
    private PetGender petGender;

    @ApiModelProperty(value = "펫 종류 - DOG(개), CAT(고양이), OTHER(기타)", example = "DOG")
    private PetSpecies petSpecies;

    @ApiModelProperty(value = "펫 상세 종", example = "말티즈")
    private String speciesDetail;

    @ApiModelProperty(value = "펫 상세 정보", example = "몸에 검은색 반점이 있어요.")
    private String content;

}
