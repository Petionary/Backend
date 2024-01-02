package back.petionary.domain.account.entity;
import javax.persistence.*;

import back.petionary.common.BaseEntity;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Entity
public class Address extends BaseEntity {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    private String area; // 지역 : 서울특별시

    private String address; //주소 : 송파구

    public Address(Long id, Account account, String area, String address) {
        this.id = id;
        this.account = account;
        this.area = area;
        this.address = address;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}