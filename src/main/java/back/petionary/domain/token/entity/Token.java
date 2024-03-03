package back.petionary.domain.token.entity;

import back.petionary.domain.account.entity.Account;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class Token {
    private String refresh;
    private String access;
    private LocalDateTime refreshExpiredAt;
    private LocalDateTime accessExpiredAt;
    @OneToOne(fetch = FetchType.LAZY)
    private Account account;
}
