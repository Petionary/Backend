package back.petionary.domain.loss.entity;

import back.petionary.common.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;

@Getter
@NoArgsConstructor
@Entity
public class ReportLossImages extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    private ReportLoss reportLoss;

    private String imgUrl;
}
