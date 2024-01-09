package back.petionary.application.account.controller;

import back.petionary.application.account.service.SocialLoginService;
import back.petionary.security.auth.PrincipalDetails;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@RestController
@RequiredArgsConstructor
public class OpenApiController {

    private final SocialLoginService socialLoginService;

    @GetMapping("/login/oauth2/kakao-login")
    public void kakaoLogin(@RequestParam String code, HttpServletResponse res, HttpSession session) {
        socialLoginService.getKakaoAccessToken(code, res, session);
    }

    @GetMapping("/kakao-logout")
    public String kakaoLogout(HttpSession session){
        return socialLoginService.logout(session);
    }
}
