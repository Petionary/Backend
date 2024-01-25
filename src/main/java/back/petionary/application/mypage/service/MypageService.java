package back.petionary.application.mypage.service;

import back.petionary.application.mypage.dto.request.MypageAccountInfoRequest;
import back.petionary.application.mypage.dto.response.MypageAccountInfoResponse;
import back.petionary.domain.account.entity.Account;
import back.petionary.domain.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MypageService {
    private final AccountRepository accountRepository;

    @Transactional
    public MypageAccountInfoResponse mypageAccountInfoResponse(Long accountId) {
        Account account = accountRepository.findById(accountId).get();
        return new MypageAccountInfoResponse(account.getId(), account.getNickName(), account.getPhone(), account.getImage());
    }

    @Transactional
    public String mypageUpdateAccountInfo(Long accountId, MypageAccountInfoRequest mypageAccountInfoRequest) {
        Account account = accountRepository.findById(accountId).get();
        account.updateAccountInfo(mypageAccountInfoRequest.getPhoneNumber(), mypageAccountInfoRequest.getImage());
        return "업데이트 성공";
    }

    @Transactional
    public String accountWithdrawal(Long accountId) {
        Account account = accountRepository.findById(accountId).get();
        accountRepository.delete(account);
        return "성공";
    }
}
