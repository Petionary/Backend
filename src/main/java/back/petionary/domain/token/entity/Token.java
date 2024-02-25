package back.petionary.domain.token.entity;

import back.petionary.common.BaseEntity;
import back.petionary.domain.account.entity.Account;
import java.time.LocalDateTime;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * packageName    : back.petionary.domain.token
 * fileName       : Token
 * author         : hoewoonjeong
 * date           : 2/22/24
 * description    :
 * ===========================================================
 * DATE              AUTHOR             NOTE
 * -----------------------------------------------------------
 * 2/22/24        hoewoonjeong               최초 생성
 */
@Entity
@NoArgsConstructor
@Getter
public class Token extends BaseEntity {
    private String refresh;
    private String access;
    private LocalDateTime refreshExpiredAt;
    private LocalDateTime accessExpiredAt;
    @OneToOne(fetch = FetchType.LAZY)
    private Account account;
}
