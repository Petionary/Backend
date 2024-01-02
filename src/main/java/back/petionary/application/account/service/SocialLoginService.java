package back.petionary.application.account.service;

import back.petionary.configuration.Config;
import back.petionary.domain.account.entity.Account;
import back.petionary.application.account.dto.AccountRequest;
import back.petionary.domain.account.repository.AccountRepository;
import back.petionary.security.auth.PrincipalDetails;
import back.petionary.security.oauth.provider.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.*;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.DefaultResponseErrorHandler;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;


@Service
@RequiredArgsConstructor
public class SocialLoginService {

    private final TokenProvider tokenProvider;
    private final AccountRepository accountRepository;
    private final Config config;



    public MultiValueMap<String, String> accessTokenParams(String grantType, String clientId,String code,String redirect_uri) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", grantType);
        params.add("client_id", clientId);
        params.add("code", code);
        params.add("redirect_uri", redirect_uri);
        params.add("client_secret", config.getKAKAO_SECRET_ID());
        return params;
    }



    @Transactional
    public void getKakaoAccessToken(String code, HttpServletResponse res, HttpSession session){
    RestTemplate rt = new RestTemplate();
    rt.setRequestFactory(new HttpComponentsClientHttpRequestFactory());
    rt.setErrorHandler(new DefaultResponseErrorHandler() {
        public boolean hasError(ClientHttpResponse response) throws IOException {
            HttpStatus statusCode = response.getStatusCode();
            return statusCode.series() == HttpStatus.Series.SERVER_ERROR;
        }
    });

    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

    MultiValueMap<String, String> params = accessTokenParams("authorization_code", config.getKAKAO_CLIENT_ID(), code, config.getKAKAO_REDIRECT_URI());
    HttpEntity<MultiValueMap<String, String>> accessTokenRequest = new HttpEntity<>(params, httpHeaders);
    ResponseEntity<String> accessTokenResponse = rt.exchange(config.getKAKAO_TOKEN_URI(), HttpMethod.POST, accessTokenRequest, String.class);
    try {
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(accessTokenResponse.getBody());

        session.setAttribute("Authorization", jsonObject.get("access_token"));
        String header = "Bearer " + jsonObject.get("access_token");

        Map<String, String> requestHeaders = new HashMap<>();
        requestHeaders.put("Authorization", header);
        String responseBody = get(config.getKAKAO_USER_INFO_URI(), requestHeaders);
        JSONObject profile = (JSONObject) jsonParser.parse(responseBody);
        JSONObject properties = (JSONObject) profile.get("properties");
        JSONObject kakao_account = (JSONObject) profile.get("kakao_account");

        String email = (String) kakao_account.get("email");
        String userName = (String) properties.get("nickname");

        session.setAttribute("email" , email);

        Account kakaoUser = new AccountRequest(email,userName).createKakaoAccount();

        if (!accountRepository.existsByEmail(kakaoUser.getEmail())) {
            accountRepository.save(kakaoUser);
        }
        String access_token = tokenProvider.create(new PrincipalDetails(kakaoUser));
        res.setHeader("Authorization", "Bearer "+access_token);

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
            if (responseCode == HttpURLConnection.HTTP_OK) {
                return readBody(con.getInputStream());
            } else {
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

    public String logout( HttpSession session) {
        String email = (String) session.getAttribute("email");
        String role = accountRepository.findByEmail(email).get().getRole();
        if (role.equals("ROLE_KAKAO")) {
            String access_token = (String) session.getAttribute("Authorization");
            if (access_token != null && !"".equals(access_token)) {
                HttpURLConnection connect = connect("https://kapi.kakao.com/v1/user/logout");
                try(InputStream inputStream = connect.getInputStream()) {
                    connect.setRequestMethod("POST");
                    connect.setRequestProperty("Authorization", "Bearer " + access_token);
                    readBody(inputStream);
                }catch (IOException pe) {
                    pe.printStackTrace();
                }
                session.removeAttribute("access_token");
                session.removeAttribute("email");
            }
        }
        return "로그아웃 성공";
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

