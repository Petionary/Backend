package back.petionary.application.address.service;

import back.petionary.Exception.PetionaryException;
import back.petionary.application.address.dto.AddressDetails;
import back.petionary.application.address.dto.AddressRequest;
import back.petionary.domain.account.entity.Account;
import back.petionary.domain.account.entity.Address;
import back.petionary.domain.account.repository.AccountRepository;
import back.petionary.domain.account.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AddressService {
    private final AccountRepository accountRepository;
    private final AddressRepository addressRepository;

    public String setAccountInfo(AddressRequest addressRequest) {
        Account account = accountRepository.findById(addressRequest.getAccountId()).orElseThrow(() -> new PetionaryException("Account not found with id: " + addressRequest.getAccountId()));
        List<AddressDetails> addressDetails = addressRequest.getAddressDetails();
        for (AddressDetails addressDetail : addressDetails) {
            Address address =  new Address(account, addressDetail.getArea(), addressDetail.getCity(), addressDetail.getLocalAddress());
            addressRepository.save(address);
        }
        account.createNickName(addressRequest.getNickName());
        accountRepository.save(account);
        return "성공";
    }
}
