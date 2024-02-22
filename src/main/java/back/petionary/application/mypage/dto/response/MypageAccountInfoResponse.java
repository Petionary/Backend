package back.petionary.application.mypage.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MypageAccountInfoResponse {
    @ApiModelProperty(example = "회원id")
    private Long accountId;

    @ApiModelProperty(example = "닉네임")
    private String nickName;

    @ApiModelProperty(example = "010-1234-5678")
    private String phoneNumber;

    @ApiModelProperty(example = "이미지 url")
    private String image;
}
