package back.petionary.application.mypet.service;

import back.petionary.application.mypet.dto.response.MypetListResponse;
import back.petionary.application.mypet.dto.response.MypetResponse;
import back.petionary.domain.account.entity.Account;
import back.petionary.domain.account.repository.AccountRepository;
import back.petionary.domain.pet.PetRepository;
import back.petionary.domain.pet.entity.Pet;
import back.petionary.exception.PetionaryException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MypetReadService {
    private final AccountRepository accountRepository;
    private final PetRepository petRepository;

    public List<MypetListResponse> getMypets(Long accountId) {
        Account account = accountRepository.findById(accountId).orElseThrow(() -> new PetionaryException("회원을 찾을 수 없습니다"));
        List<Pet> petList = petRepository.findByAccountId(account.getId());
        return petList.stream()
                .map(pet -> new MypetListResponse(pet.getImgUrl(), pet.getPetName(), pet.getPetSpecies(), pet.getCreatedAt(), pet.getUpdatedAt()))
                .collect(Collectors.toList());
    }

    public MypetResponse findMypet(Long accountId, Long petId) {
        Pet pet = petRepository.findByIdAndAccountId(petId, accountId).orElseThrow(() -> new PetionaryException("회원id에 해당하는 펫을 찾을 수 없습니다"));
        return new MypetResponse(pet.getImgUrl(), pet.getPetName(), pet.getPetBirth(), pet.getPetGender(), pet.getPetSpecies(), pet.getSpeciesDetail(), pet.getContent());
    }
}
