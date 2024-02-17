package back.petionary.application.mypage.controller;

import back.petionary.application.mypage.dto.request.MypageAccountInfoRequest;
import back.petionary.application.mypage.dto.response.MypageAccountInfoResponse;
import back.petionary.application.mypage.service.MypageReadService;
import back.petionary.application.mypage.service.MypageWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypageController {
    private final MypageWriteService mypageWriteService;
    private final MypageReadService mypageReadService;

    @GetMapping("/{id}")
    public MypageAccountInfoResponse findAccountInfo(@PathVariable Long id){
        return mypageReadService.findAccountInfo(id);
    }

    @PutMapping("/{id}")
    public String updateAccountInfo(@PathVariable Long id, @RequestBody MypageAccountInfoRequest mypageAccountInfoRequest) {
        return mypageWriteService.updateAccountInfo(id, mypageAccountInfoRequest);
    }

    @DeleteMapping("/{id}")
    public String accountWithdrawal(@PathVariable Long id) {
        return mypageWriteService.accountWithdrawal(id);
    }
}
