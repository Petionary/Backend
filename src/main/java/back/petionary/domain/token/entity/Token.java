package back.petionary.domain.token.entity;

import back.petionary.common.BaseEntity;
import back.petionary.domain.account.entity.Account;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@NoArgsConstructor
@Getter
@AllArgsConstructor
public class Token extends BaseEntity {
    private String refresh;
    private String access;
    private LocalDateTime refreshExpiredAt;
    private LocalDateTime accessExpiredAt;
    @OneToOne(fetch = FetchType.LAZY)
    private Account account;
}
