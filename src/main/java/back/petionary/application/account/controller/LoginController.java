package back.petionary.application.account.controller;

import back.petionary.application.account.service.SocialLoginService;
import back.petionary.security.oauth.provider.LoginToken;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class LoginController {

    private final SocialLoginService socialLoginService;

    @GetMapping("/login/oauth2/kakao-login")
    public LoginToken kakaoLogin(@RequestParam String code) {
        return socialLoginService.getKakaoAccessToken(code);
    }

    @GetMapping("/login/oauth2/naver-login")
    public LoginToken naverLogin(@RequestParam String code) {ㅁㅁaaa
        return socialLoginService.getNaverAccessToken(code);
    }
}
