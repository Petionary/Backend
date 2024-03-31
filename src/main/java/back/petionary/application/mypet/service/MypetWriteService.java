package back.petionary.application.mypet.service;

import back.petionary.application.mypet.dto.request.MypetCreateRequest;
import back.petionary.application.mypet.dto.request.MypetUpdateRequest;
import back.petionary.domain.account.entity.Account;
import back.petionary.domain.account.repository.AccountRepository;
import back.petionary.domain.pet.PetRepository;
import back.petionary.domain.pet.entity.Pet;
import back.petionary.exception.PetionaryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MypetWriteService {
    private final AccountRepository accountRepository;
    private final PetRepository petRepository;

    public String create(Long accountId, MypetCreateRequest mypetCreateRequest) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new PetionaryException("회원을 찾을 수 없습니다"));
        Pet pet = new Pet(account, mypetCreateRequest.getImgUrl(), mypetCreateRequest.getName(),
                mypetCreateRequest.getBirth(), mypetCreateRequest.getGender(), mypetCreateRequest.getSpecies(),
                mypetCreateRequest.getSpeciesDetail(), mypetCreateRequest.getContent());
        petRepository.save(pet);
        return "성공";
    }

    public void update(Long accountId, Long petId, MypetUpdateRequest mypetUpdateRequest) {
        Pet pet = petRepository.findByIdAndAccountId(petId, accountId).orElseThrow(() -> new PetionaryException("회원id에 해당하는 펫을 찾을 수 없습니다"));
        pet.update(mypetUpdateRequest.getImgUrl(), mypetUpdateRequest.getName(), mypetUpdateRequest.getBirth(),
                mypetUpdateRequest.getGender(), mypetUpdateRequest.getSpecies(), mypetUpdateRequest.getSpeciesDetail(), mypetUpdateRequest.getContent());
    }

}
