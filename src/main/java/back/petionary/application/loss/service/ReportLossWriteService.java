package back.petionary.application.loss.service;

import back.petionary.application.loss.dto.request.ReportLossRequest;
import back.petionary.domain.account.entity.Account;
import back.petionary.domain.account.repository.AccountRepository;
import back.petionary.domain.loss.entity.ReportLoss;
import back.petionary.domain.loss.repository.ReportLossRepository;
import back.petionary.domain.pet.PetRepository;
import back.petionary.domain.pet.entity.Pet;
import back.petionary.exception.PetionaryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReportLossWriteService {
    private final AccountRepository accountRepository;
    private final PetRepository petRepository;
    private final ReportLossRepository reportLossRepository;

    public String create(Long accountId, Long petId, ReportLossRequest reportLossRequest) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new PetionaryException("회원을 찾을 수 없습니다."));
        Pet pet = petRepository.findByIdAndAccountId(petId, accountId).orElseThrow(() -> new PetionaryException("회원id에 해당하는 펫을 찾을 수 없습니다."));
        ReportLoss reportLoss = new ReportLoss(account, pet, reportLossRequest.getFeature(), reportLossRequest.getLossLocation(), reportLossRequest.getLossDateTime(), reportLossRequest.getContent());
        reportLossRepository.save(reportLoss);
        return "성공";
    }
}
