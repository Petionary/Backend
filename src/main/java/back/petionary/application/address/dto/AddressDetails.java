package back.petionary.application.address.dto;

import lombok.Getter;

@Getter
public class AddressDetails {

    //지역 - 시도
    private String area;
    //시군구
    private String city;
    //동읍면 - 리
    private String localAddress;
}
