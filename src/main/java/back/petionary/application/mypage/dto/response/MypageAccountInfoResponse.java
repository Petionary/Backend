package back.petionary.application.mypage.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MypageAccountInfoResponse {
    private Long accountId;
    private String nickName;
    private String phoneNumber;
    private String image;
}
