package back.petionary.application.address.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

import java.util.List;

@Getter
public class AddressRequest {
    @ApiModelProperty(value = "회원id", example = "1")
    private Long accountId;

    @ApiModelProperty(value = "닉네임", example = "밍밍")
    private String nickName;

    @ApiModelProperty(dataType = "List<AddressDetails>")
    private List<AddressDetails> addressDetails;
}
