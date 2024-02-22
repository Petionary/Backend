package back.petionary.application.address.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class AddressRequest {
    @ApiModelProperty(example = "회원id")
    private Long accountId;

    @ApiModelProperty(example = "닉네임")
    private String nickName;

    @ApiModelProperty(dataType = "List<AddressDetails>")
    private List<AddressDetails> addressDetails;
}
