package back.petionary.domain.account.entity;

import back.petionary.common.BaseEntity;
import javax.persistence.Embeddable;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@NoArgsConstructor
@Getter
@Embeddable
@Entity
public class Address extends BaseEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    private String area;
    private String city;
    private String localAddress;

    public Address(Account account, String area, String city, String localAddress) {
        this.account = account;
        this.area = area;
        this.city = city;
        this.localAddress = localAddress;
    }
}