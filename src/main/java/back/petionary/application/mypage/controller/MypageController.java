package back.petionary.application.mypage.controller;

import back.petionary.application.mypage.dto.request.MypageAccountInfoRequest;
import back.petionary.application.mypage.dto.response.MypageAccountInfoResponse;
import back.petionary.application.mypage.service.MypageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MypageController {
    private final MypageService mypageService;

    @GetMapping("/mypage-account-info-response/{id}")
    public MypageAccountInfoResponse mypageAccountInfoResponse(@PathVariable Long id){
        return mypageService.mypageAccountInfoResponse(id);
    }

    @PutMapping("/mypage-update-account-info/{id}")
    public String mypageUpdateUserInfo(@PathVariable Long id, @RequestBody MypageAccountInfoRequest mypageAccountInfoRequest) {
        return mypageService.mypageUpdateAccountInfo(id, mypageAccountInfoRequest);
    }

    @DeleteMapping("/mypage-delete/{id}")
    public String accountWithdrawal(@PathVariable Long id) {
        return mypageService.accountWithdrawal(id);
    }
}
