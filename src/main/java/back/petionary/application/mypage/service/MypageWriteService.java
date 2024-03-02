package back.petionary.application.mypage.service;

import back.petionary.Exception.PetionaryException;
import back.petionary.application.mypage.dto.request.MypageAccountInfoRequest;
import back.petionary.application.mypage.dto.response.MypageAccountInfoResponse;
import back.petionary.domain.account.entity.Account;
import back.petionary.domain.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MypageWriteService {
    private final AccountRepository accountRepository;

    public String updateAccountInfo(Long accountId, MypageAccountInfoRequest mypageAccountInfoRequest) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new PetionaryException("회원을 찾을 수 없습니다"));
        account.updateAccountInfo(mypageAccountInfoRequest.getPhoneNumber(), mypageAccountInfoRequest.getImage());
        return "업데이트 성공";
    }

    public String accountWithdrawal(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new PetionaryException("회원을 찾을 수 없습니다"));
        accountRepository.delete(account);
        return "성공";
    }
}
