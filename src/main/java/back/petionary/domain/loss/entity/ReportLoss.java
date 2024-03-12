package back.petionary.domain.loss.entity;

import back.petionary.common.BaseEntity;
import back.petionary.domain.account.entity.Account;
import back.petionary.domain.pet.entity.Pet;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.time.LocalDateTime;

@Getter
@NoArgsConstructor
@Entity
public class ReportLoss extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    private Pet pet;

    private String special;

    private String lossLocation;

    private LocalDateTime lossDateTime;

    private String content;

    public ReportLoss(Account account, Pet pet, String special, String lossLocation, LocalDateTime lossDateTime, String content) {
        this.account = account;
        this.pet = pet;
        this.special = special;
        this.lossLocation = lossLocation;
        this.lossDateTime = lossDateTime;
        this.content = content;
    }
}
