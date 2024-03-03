package back.petionary.application.account.service;

import back.petionary.application.account.dto.AccountRequest;
import back.petionary.domain.account.entity.Account;
import back.petionary.domain.account.enums.SocialType;
import back.petionary.domain.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
public class SocialLoginService {

    private final AccountRepository accountRepository;

    @Value("${spring.security.oauth2.client.registration.client-id}")
    private String NAVER_CLIENT_ID;

    @Value("${spring.security.oauth2.client.registration.redirect-uri}")
    private String NAVER_REDIRECT_URI;

    @Value("${spring.security.oauth2.client.registration.client-secret}")
    private String NAVER_SECRET_ID;

    @Value("${spring.security.oauth2.client.provider.kakao.token-uri}")
    private String NAVER_TOKEN_URI;

    @Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
    private String NAVER_USER_INFO_URI;

    public void naverToken(String code, HttpServletResponse response) throws IOException {
        try {
            JSONParser jsonParser = new JSONParser();
            String header = "Bearer " + code;
            Map<String, String> requestHeaders = new HashMap<>();
            requestHeaders.put("Authorization", header);
            String responseBody = get(NAVER_USER_INFO_URI, requestHeaders);
            JSONObject parse = (JSONObject) jsonParser.parse(responseBody);

            JSONObject responseParse = (JSONObject) parse.get("response");
            String encodeUserName = (String) responseParse.get("name");
            String loginId = (String) responseParse.get("id");
            String email = (String) responseParse.get("email");
            String phoneNumber = (String) responseParse.get("mobile_e164");
            String userName = new String(encodeUserName.getBytes(StandardCharsets.UTF_8));

//            Account NaverUser = new Account(userName, email, phoneNumber, );\
            User user = new AccountRequest("social_" + loginId, userName, encode.encode("네이버"), email, phoneNumber).naverOAuthToEntity();
//            User user = new UserRequest("social_" + loginId, userName, encode.encode("네이버"), email, phoneNumber).naverOAuthToEntity();

            Account naverUser = new Account(email, userName, SocialType.NAVER);

            if (!accountRepository.existsByEmail(naverUser.getEmail())) {
                accountRepository.save(naverUser);
            }
            String access_token = tokenProvider.create(new PrincipalDetails(user));
            response.addHeader("Authorization","Bearer " + access_token);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    private static String get(String apiUrl, Map<String, String> requestHeaders) {
        HttpURLConnection con = connect(apiUrl);
        try {
            con.setRequestMethod("GET");
            for (Map.Entry<String, String> header : requestHeaders.entrySet()) {
                con.setRequestProperty(header.getKey(), header.getValue());
            }


            int responseCode = con.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) { // 정상 호출
                return readBody(con.getInputStream());
            } else { // 에러 발생
                return readBody(con.getErrorStream());
            }
        } catch (IOException e) {
            throw new RuntimeException("API 요청과 응답 실패", e);
        } finally {
            con.disconnect();
        }
    }


    private static HttpURLConnection connect(String apiUrl) {
        try {
            URL url = new URL(apiUrl);
            return (HttpURLConnection) url.openConnection();
        } catch (MalformedURLException e) {
            throw new RuntimeException("API URL이 잘못되었습니다. : " + apiUrl, e);
        } catch (IOException e) {
            throw new RuntimeException("연결이 실패했습니다. : " + apiUrl, e);
        }
    }



    private static String readBody(InputStream body) {
        InputStreamReader streamReader = new InputStreamReader(body);


        try (BufferedReader lineReader = new BufferedReader(streamReader)) {
            StringBuilder responseBody = new StringBuilder();


            String line;
            while ((line = lineReader.readLine()) != null) {
                responseBody.append(line);
            }


            return responseBody.toString();
        } catch (IOException e) {
            throw new RuntimeException("API 응답을 읽는데 실패했습니다.", e);
        }
    }
}
