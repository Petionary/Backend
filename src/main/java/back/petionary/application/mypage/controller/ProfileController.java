package back.petionary.application.mypage.controller;

import back.petionary.application.mypage.dto.dto.request.MypageAccountInfoRequest;
import back.petionary.application.mypage.dto.dto.response.MypageAccountInfoResponse;
import back.petionary.application.mypage.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;

    @GetMapping("/mypage-account-info-response/{id}")
    public MypageAccountInfoResponse mypageAccountInfoResponse(@PathVariable Long id){
        return profileService.mypageAccountInfoResponse(id);
    }

    @PutMapping("/mypage-update-account-info/{id}")
    public String mypageUpdateUserInfo(@PathVariable Long id, @RequestBody MypageAccountInfoRequest mypageAccountInfoRequest) {
        return profileService.mypageUpdateAccountInfo(id, mypageAccountInfoRequest);
    }

    @DeleteMapping("/mypage-delete/{id}")
    public String accountWithdrawal(@PathVariable Long id) {
        return profileService.accountWithdrawal(id);
    }
}

