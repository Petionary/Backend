package back.petionary.application.mypage.dto.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class MypageAccountInfoRequest {
    //private Long accountId;
    private String phoneNumber;
    private String image;

}
