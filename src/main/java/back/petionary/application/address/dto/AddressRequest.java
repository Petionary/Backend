package back.petionary.application.address.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class AddressRequest {

    private Long accountId; //회원 이메일

    private String nickName; //닉네임

    private List<AddressDetails> addressDetails;

    //private String area; //지역

    //private String address; //주소
}
