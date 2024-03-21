package back.petionary.domain.loss.entity;

import back.petionary.common.BaseEntity;
import back.petionary.domain.account.entity.Account;
import back.petionary.domain.pet.entity.Pet;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@Entity
public class ReportLoss extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    @ManyToOne(fetch = FetchType.LAZY)
    private Pet pet;

    private String feature;

    @Column(nullable = false)
    private String lossLocation;

    @Column(nullable = false)
    private LocalDateTime lossDateTime;

    private String content;

    @OneToMany(mappedBy = "reportLoss", cascade = CascadeType.ALL)
    private List<ReportLossImages> lossImages = new ArrayList<>();

    public ReportLoss(Account account, Pet pet, String feature, String lossLocation, LocalDateTime lossDateTime, String content) {
        this.account = account;
        this.pet = pet;
        this.feature = feature;
        this.lossLocation = lossLocation;
        this.lossDateTime = lossDateTime;
        this.content = content;
    }

    public void create(String feature, Account account, String lossLocation){
        this.feature = feature;
        this.account = account;
        this.lossLocation = lossLocation;
    }
}
