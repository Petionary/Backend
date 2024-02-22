package back.petionary.application.address.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;

@Getter
public class AddressDetails {

    //지역 - 시도
    @ApiModelProperty(example = "서울")
    private String area;

    //시군구
    @ApiModelProperty(example = "강남구")
    private String city;

    //동읍면 - 리
    @ApiModelProperty(example = "논현동")
    private String localAddress;
}
