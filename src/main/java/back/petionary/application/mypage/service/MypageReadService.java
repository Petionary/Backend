package back.petionary.application.mypage.service;

import back.petionary.exception.PetionaryException;
import back.petionary.application.mypage.dto.response.MypageAccountInfoResponse;
import back.petionary.domain.account.entity.Account;
import back.petionary.domain.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MypageReadService {
    private final AccountRepository accountRepository;

    public MypageAccountInfoResponse findAccountInfo(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new PetionaryException("Account not found with id: " + accountId));
        return new MypageAccountInfoResponse(account.getId(), account.getNickName(), account.getPhone(), account.getImage());
    }
}
