package back.petionary.application.mypage.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MypageAccountInfoRequest {
    @ApiModelProperty(example = "010-1234-5678")
    private String phoneNumber;
    @ApiModelProperty(example = "이미지 url")
    private String image;
}
