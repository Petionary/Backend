package back.petionary.application.address.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class AddressRequest {
    private Long accountId;
    private String nickName;
    private List<AddressDetails> addressDetails;
}
