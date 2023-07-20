package back.petionary.account.entity;

import back.petionary.common.BaseEntity;
import back.petionary.pet.entity.Pet;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import lombok.Getter;

@Entity
@Getter
public class Account extends BaseEntity {

    @Column(nullable = false)
    private String email;
    @Column(nullable = false)
    private String address;
    @Column(length = 15, nullable = false)
    private String phone;
    @Column(nullable = false)
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "owner_id")
    private List<Pet> pet = new ArrayList<>();
}
