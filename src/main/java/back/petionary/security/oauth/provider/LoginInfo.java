package back.petionary.security.oauth.provider;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class LoginInfo {
    private final Long accountId;
}
