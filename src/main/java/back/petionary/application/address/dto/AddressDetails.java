package back.petionary.application.address.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class AddressDetails {
    @ApiModelProperty(value = "지역-시,도", example = "서울")
    private String area;

    @ApiModelProperty(value = "시군구", example = "강남구")
    private String city;

    @ApiModelProperty(value = "동읍면-리", example = "논현동")
    private String localAddress;
}
