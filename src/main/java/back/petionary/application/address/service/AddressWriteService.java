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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class AddressWriteService {
    private final AccountRepository accountRepository;
    private final AddressRepository addressRepository;

    public String updateAccountInfo(AddressRequest addressRequest) {
        Account account = accountRepository.findById(addressRequest.getAccountId()).orElseThrow(() -> new PetionaryException("Account not found with id: " + addressRequest.getAccountId()));
        List<AddressDetails> addressDetails = addressRequest.getAddressDetails();
        addressDetails.stream()
                .map(addressDetail -> new Address(account, addressDetail.getArea(), addressDetail.getCity(), addressDetail.getLocalAddress()))
                .forEach(addressRepository::save);
        account.createNickName(addressRequest.getNickName());
        return "성공";
    }
}
