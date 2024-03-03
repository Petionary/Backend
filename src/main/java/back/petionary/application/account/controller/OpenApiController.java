package back.petionary.application.account.controller;

import back.petionary.application.account.service.SocialLoginService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OpenApiController {

    private final SocialLoginService socialLoginService;

//    @GetMapping("/login/oauth2/naver-login")
//    public String authNaver(@RequestParam String code, @RequestParam String state) {
////        HttpHeaders headers = new HttpHeaders();
////        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
////
////        HttpEntity<MultiValueMap<String, String>> naverTokenRequest =
////                new HttpEntity<>(generateParam(code), headers);
////
////        return requestAuth(naverTokenRequest);
////        String accessToken = extractAccessToken(requestAccessToken(generateAuthCodeRequest(code, state)).getBody());
////        return requestProfile(generateProfileRequest(accessToken)).getBody();
//        return code;
//    }



}
