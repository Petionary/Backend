package back.petionary.application.mypet.service;

import back.petionary.Exception.PetionaryException;
import back.petionary.application.mypet.dto.request.MypetCreateRequest;
import back.petionary.domain.account.entity.Account;
import back.petionary.domain.account.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MypetWriteService {
    private final AccountRepository accountRepository;

    public String create(Long accountId, MypetCreateRequest mypetCreateRequest) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new PetionaryException("Account not found with id: " + accountId));
        return "성공";
    }

}
