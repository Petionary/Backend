package back.petionary.application.address.controller;


import back.petionary.application.address.dto.AddressRequest;
import back.petionary.application.address.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AddressController {

    private final AddressService addressService;

    @PostMapping("/setUserInfo")
    public String setUserInfo(@RequestBody AddressRequest addressRequest) {
        return addressService.setUserInfo(addressRequest);
    }

}
