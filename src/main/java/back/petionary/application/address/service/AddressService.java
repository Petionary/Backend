package back.petionary.application.address.service;

import back.petionary.application.address.dto.AddressDetails;
import back.petionary.application.address.dto.AddressRequest;
import back.petionary.domain.account.entity.Account;
import back.petionary.domain.account.entity.Address;
import back.petionary.domain.account.repository.AccountRepository;
import back.petionary.domain.account.repository.AddressRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressService {

    private final AccountRepository accountRepository;
    private final AddressRepository addressRepository;

    @Transactional
    public String setUserInfo(AddressRequest addressRequest) {
        Account account = accountRepository.findById(addressRequest.getAccountId()).get();
        List<AddressDetails> addressDetails = addressRequest.getAddressDetails();

        for (AddressDetails addressDetail : addressDetails) {
            Address address = new Address();
            address.setArea(addressDetail.getArea());
            address.setAddress(addressDetail.getAddress());
            address.setAccount(account);
            addressRepository.save(address);
        }
        account.setNickName(addressRequest.getNickName());
        accountRepository.save(account);


//        Address address = new Address();
//        address.setArea(addressRequest.getArea());
//        address.setAddress(addressRequest.getAddress());
//        account.setNickName(addressRequest.getNickName());
//        address.setAccount(account);
//        addressRepository.save(address);
//        accountRepository.save(account);
//        return "성공";

        return "성공";

    }

}
