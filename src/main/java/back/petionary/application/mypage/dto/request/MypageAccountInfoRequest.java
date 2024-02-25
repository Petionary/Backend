package back.petionary.application.mypage.dto.request;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MypageAccountInfoRequest {
    @ApiModelProperty(value = "핸드폰 번호", example = "010-1234-5678")
    private String phoneNumber;
    @ApiModelProperty(value = "이미지 url")
    private String image;
}
