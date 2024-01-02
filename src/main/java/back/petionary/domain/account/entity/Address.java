package back.petionary.domain.account.entity;
import javax.persistence.*;

import back.petionary.common.BaseEntity;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Setter
public class Address extends BaseEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    private String area; // 지역 : 서울특별시

    private String address; //주소 : 송파구



    public Address(Account account, String area, String address) {
        this.account = account;
        this.area = area;
        this.address = address;
    }

}