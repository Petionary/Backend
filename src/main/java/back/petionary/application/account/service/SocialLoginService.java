package back.petionary.application.account.service;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@Data
@RequiredArgsConstructor
public class SocialLoginService {
    private  RestTemplate restTemplate;
   // @Value("${spring.kakao.clinet-id}")
    private  String kakaoClientId;

}
