package back.petionary.security.oauth.provider;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * packageName    : back.petionary.security.oauth.provider
 * fileName       : LoginInfo
 * author         : hoewoonjeong
 * date           : 3/2/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 3/2/24        hoewoonjeong               최초 생성
 */
@RequiredArgsConstructor
@Getter
public class LoginInfo {
    private final Long accountId;
}
