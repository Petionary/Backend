package back.petionary.application.mypage.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MypageAccountInfoResponse {
    @ApiModelProperty(value = "회원id", example = "1")
    private Long accountId;

    @ApiModelProperty(value = "닉네임", example = "밍밍")
    private String nickName;

    @ApiModelProperty(value = "핸드폰 번호", example = "010-1234-5678")
    private String phoneNumber;

    @ApiModelProperty(value = "이미지 url")
    private String image;
}
