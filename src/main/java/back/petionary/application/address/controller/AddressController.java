package back.petionary.application.address.controller;

import back.petionary.application.address.dto.AddressRequest;
import back.petionary.application.address.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("address")
public class AddressController {
    private final AddressService addressService;

    @PostMapping("/update-account-info")
    public String setAccountInfo(@RequestBody AddressRequest addressRequest) {
        return addressService.setAccountInfo(addressRequest);
    }
}
