package back.petionary.application.mypage.controller;

import back.petionary.application.mypage.dto.request.MypageAccountInfoRequest;
import back.petionary.application.mypage.dto.response.MypageAccountInfoResponse;
import back.petionary.application.mypage.service.MypageService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypageController {
    private final MypageService mypageService;

    @GetMapping("/{id}")
    public MypageAccountInfoResponse findAccountInfo(@PathVariable Long id){
        return mypageService.findAccountInfo(id);
    }

    @PutMapping("/{id}")
    public String updateAccountInfo(@PathVariable Long id, @RequestBody MypageAccountInfoRequest mypageAccountInfoRequest) {
        return mypageService.updateAccountInfo(id, mypageAccountInfoRequest);
    }

    @DeleteMapping("/{id}")
    public String accountWithdrawal(@PathVariable Long id) {
        return mypageService.accountWithdrawal(id);
    }
}
