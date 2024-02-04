package back.petionary.domain.account.repository;

import back.petionary.domain.account.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findById(Long Id);
    Optional<Account> findByEmail(String Email);
}
