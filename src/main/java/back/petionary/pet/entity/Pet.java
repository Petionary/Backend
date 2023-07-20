package back.petionary.pet.entity;

import back.petionary.common.BaseEntity;
import back.petionary.pet.entity.type.PetGender;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import lombok.Getter;

@Getter
@Entity
public class Pet extends BaseEntity {

    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String kind;
    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private PetGender gender;
    @Column(nullable = false)
    private Integer age;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pet_id")
    private List<PetImage> images = new ArrayList<>();
}
