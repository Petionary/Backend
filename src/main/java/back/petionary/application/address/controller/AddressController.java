package back.petionary.application.address.controller;

import back.petionary.application.address.dto.AddressRequest;
import back.petionary.application.address.service.AddressWriteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = {"자주 가는 지역 및 닉네임 API"}, description = "addressController")
@RestController
@RequiredArgsConstructor
@RequestMapping("/account-info")
public class AddressController {
    private final AddressWriteService addressWriteService;
    @PostMapping()
    @ApiOperation(value = "자주 가는 지역 및 닉네임 입력", notes = "자주 가는 지역과 닉네임을 저장합니다.")
    public String updateAccountInfo(@RequestBody AddressRequest addressRequest) {
        return addressWriteService.updateAccountInfo(addressRequest);
    }
}

