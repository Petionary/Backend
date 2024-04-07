package back.petionary.application.account.service;

import back.petionary.domain.account.entity.Account;
import back.petionary.domain.account.enums.SocialType;
import back.petionary.domain.account.repository.AccountRepository;
import back.petionary.exception.PetionaryException;
import back.petionary.security.oauth.provider.LoginToken;
import back.petionary.security.oauth.provider.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.SecureRandom;
import java.util.HashMap;
import java.util.Map;


@Service
@RequiredArgsConstructor
@Transactional
public class SocialLoginService {

    private final TokenProvider tokenProvider;
    private final AccountRepository accountRepository;
    @Value("${spring.security.oauth2.client.registration.kakao.client-id}")
    private String KAKAO_CLIENT_ID;

    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
    private String KAKAO_CLIENT_SECRET;

    @Value("${spring.security.oauth2.client.registration.kakao.redirect-uri}")
    private String KAKAO_REDIRECT_URI;

    @Value("${spring.security.oauth2.client.registration.kakao.client-secret}")
    private String KAKAO_SECRET_ID;

    @Value("${spring.security.oauth2.client.provider.kakao.token-uri}")
    private String KAKAO_TOKEN_URI;

    @Value("${spring.security.oauth2.client.provider.kakao.user-info-uri}")
    private String KAKAO_USER_INFO_URI;

    //naver
    @Value("${spring.security.oauth2.client.registration.naver.client-id}")
    private String NAVER_CLIENT_ID;

    @Value("${spring.security.oauth2.client.registration.naver.client-secret}")
    private String NAVER_CLIENT_SECRET;

    @Value("${spring.security.oauth2.client.registration.naver.redirect-uri}")
    private String NAVER_REDIRECT_URI;

    @Value("${spring.security.oauth2.client.provider.naver.token-uri}")
    private String NAVER_TOKEN_URI;

    @Value("${spring.security.oauth2.client.provider.naver.user-info-uri}")
    private String NAVER_USER_INFO_URI;


    public MultiValueMap<String, String> accessTokenParams(String grantType, String clientId,
        String code, String redirect_uri, String clientSecret) {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", grantType);
        params.add("client_id", clientId);
        params.add("code", code);
        params.add("redirect_uri", redirect_uri);
        params.add("client_secret", clientSecret);
        return params;
    }

    public LoginToken getKakaoAccessToken(String code) {
        RestTemplate rt = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = accessTokenParams("authorization_code",
            KAKAO_CLIENT_ID, code, KAKAO_REDIRECT_URI, KAKAO_CLIENT_SECRET);
        HttpEntity<MultiValueMap<String, String>> accessTokenRequest = new HttpEntity<>(params,
            httpHeaders);
        ResponseEntity<String> accessTokenResponse = rt.exchange(KAKAO_TOKEN_URI, HttpMethod.POST,
            accessTokenRequest, String.class);
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(accessTokenResponse.getBody());

            String header = "Bearer " + jsonObject.get("access_token");

            Map<String, String> requestHeaders = new HashMap<>();
            requestHeaders.put("Authorization", header);
            String responseBody = get(KAKAO_USER_INFO_URI, requestHeaders);
            JSONObject profile = (JSONObject) jsonParser.parse(responseBody);
            JSONObject properties = (JSONObject) profile.get("properties");
            JSONObject kakao_account = (JSONObject) profile.get("kakao_account");

            String email = (String) kakao_account.get("email");
            String userName = (String) properties.get("nickname");
            Account account = null;
            if (!accountRepository.existsByEmail(email)) {
                Account kakaoUser = new Account(email, userName, SocialType.KAKAO);
                account = accountRepository.save(kakaoUser);
            }else {
                account = accountRepository.findByEmail(email).orElseThrow(() -> new PetionaryException("회원을 찾을 수 없습니다."));
            }
            return tokenProvider.getToken(account.getId(),account.getRole());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String generateState() {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }

    public LoginToken getNaverAccessToken(String code) {
        RestTemplate rt = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

        MultiValueMap<String, String> params = accessTokenParams("authorization_code",
                NAVER_CLIENT_ID, code, NAVER_REDIRECT_URI, NAVER_CLIENT_SECRET);
        String state = generateState();
        params.add("state", state);

        HttpEntity<MultiValueMap<String, String>> accessTokenRequest = new HttpEntity<>(params,
                httpHeaders);
        ResponseEntity<String> accessTokenResponse = rt.exchange(NAVER_TOKEN_URI, HttpMethod.POST,
                accessTokenRequest, String.class);
        try {
            JSONParser jsonParser = new JSONParser();
            JSONObject jsonObject = (JSONObject) jsonParser.parse(accessTokenResponse.getBody());

            String header = "Bearer " + jsonObject.get("access_token");

            Map<String, String> requestHeaders = new HashMap<>();
            requestHeaders.put("Authorization", header);
            String responseBody = get(NAVER_USER_INFO_URI, requestHeaders);
            JSONObject parse = (JSONObject) jsonParser.parse(responseBody);
            JSONObject responseParse = (JSONObject) parse.get("response");

            String email = (String) responseParse.get("email");
            String userName = (String) responseParse.get("name");

            Account account = null;
            if (!accountRepository.existsByEmail(email)) {
                Account naverUser = new Account(email, userName, SocialType.NAVER);
                account = accountRepository.save(naverUser);
            }else {
                account = accountRepository.findByEmail(email).orElseThrow(() -> new PetionaryException("회원을 찾을 수 없습니다."));
            }
            return tokenProvider.getToken(account.getId(),account.getRole());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
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
