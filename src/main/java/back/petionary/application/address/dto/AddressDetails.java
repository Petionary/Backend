package back.petionary.application.address.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class AddressDetails {
    @ApiModelProperty(example = "서울", value = "지역-시,도")
    private String area;

    @ApiModelProperty(example = "강남구", value = "시군구")
    private String city;

    @ApiModelProperty(example = "논현동", value = "동읍면-리")
    private String localAddress;
}
