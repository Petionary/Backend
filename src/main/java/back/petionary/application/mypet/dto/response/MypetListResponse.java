package back.petionary.application.mypet.dto.response;

import back.petionary.application.mypet.enums.PetSpecies;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class MypetListResponse {
    @ApiModelProperty(value = "이미지 url")
    private String imgUrl;

    @ApiModelProperty(value = "펫 이름", example = "밍밍")
    private String name;

    @ApiModelProperty(value = "펫 종류 - DOG(개), CAT(고양이), OTHER(기타)", example = "DOG")
    private PetSpecies species;

    @ApiModelProperty(value = "생성 날짜")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createAt;

    @ApiModelProperty(value = "수정 날짜")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime updateAt;
}
