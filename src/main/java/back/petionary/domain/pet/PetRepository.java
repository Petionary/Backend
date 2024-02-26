package back.petionary.domain.pet;

import back.petionary.domain.account.entity.Account;
import back.petionary.domain.pet.entity.Pet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PetRepository  extends JpaRepository<Pet, Long> {
    List<Pet> findByAccountId(Long accountId);
}
