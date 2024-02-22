package back.petionary.application.mypage.controller;

import back.petionary.application.mypage.dto.request.MypageAccountInfoRequest;
import back.petionary.application.mypage.dto.response.MypageAccountInfoResponse;
import back.petionary.application.mypage.service.MypageReadService;
import back.petionary.application.mypage.service.MypageWriteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"마이페이지 API"}, description = "mypageController")
@RestController
@RequiredArgsConstructor
@RequestMapping("/mypage")
public class MypageController {
    private final MypageWriteService mypageWriteService;
    private final MypageReadService mypageReadService;

    @GetMapping("/{id}")
    @ApiOperation(value = "mypage 회원 정보 조회", notes = "해당 id를 가지는 회원의 정보를 보여줍니다.")
    public MypageAccountInfoResponse findAccountInfo(@PathVariable Long id){
        return mypageReadService.findAccountInfo(id);
    }

    @PutMapping("/{id}")
    @ApiOperation(value = "mypage 이미지, 전화번호 수정", notes = "해당 id를 가지는 회원의 이미지와 전화번호를 수정합니다.")
    public String updateAccountInfo(@PathVariable Long id, @RequestBody MypageAccountInfoRequest mypageAccountInfoRequest) {
        return mypageWriteService.updateAccountInfo(id, mypageAccountInfoRequest);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "회원 탈퇴", notes = "해당 id를 가지는 회원을 탈퇴합니다.")
    public String accountWithdrawal(@PathVariable Long id) {
        return mypageWriteService.accountWithdrawal(id);
    }
}
