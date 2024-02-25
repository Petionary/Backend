package back.petionary.domain.token.repository;


import back.petionary.domain.token.entity.Token;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<Token,Long> {

    Optional<Token> findByAccountId(Long accountId);
}
