package back.petionary.account;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AccountWriteService {

    private final AccountRepository accountRepository;
}
