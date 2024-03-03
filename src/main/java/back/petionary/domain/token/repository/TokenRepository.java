package back.petionary.domain.token.repository;

import antlr.Token;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TokenRepository extends JpaRepository<Token,Long> {
    Optional<Token> findByAccountId(Long accountId);

    Boolean existsByAccess(String access);
}
