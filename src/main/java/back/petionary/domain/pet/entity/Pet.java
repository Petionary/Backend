package back.petionary.domain.pet.entity;

import back.petionary.application.mypet.enums.PetGender;
import back.petionary.application.mypet.enums.PetSpecies;
import back.petionary.common.BaseEntity;
import back.petionary.domain.account.entity.Account;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@NoArgsConstructor
@Entity
public class Pet extends BaseEntity {
    @ManyToOne(fetch = FetchType.LAZY)
    private Account account;

    private String imgUrl;

    private String petName;

    private LocalDate petBirth;

    @Enumerated(value = EnumType.STRING)
    private PetGender petGender;

    @Enumerated(value = EnumType.STRING)
    private PetSpecies petSpecies;

    private String speciesDetail;

    private String content;

    public Pet(Account account, String imgUrl, String petName, LocalDate petBirth, PetGender petGender, PetSpecies petSpecies, String speciesDetail, String content) {
        this.account = account;
        this.imgUrl = imgUrl;
        this.petName = petName;
        this.petBirth = petBirth;
        this.petGender = petGender;
        this.petSpecies = petSpecies;
        this.speciesDetail = speciesDetail;
        this.content = content;
    }

    public void update(Account account, String imgUrl, String petName, LocalDate petBirth, PetGender petGender, PetSpecies petSpecies, String speciesDetail, String content) {
        this.account = account;
        this.imgUrl = imgUrl;
        this.petName = petName;
        this.petBirth = petBirth;
        this.petGender = petGender;
        this.petSpecies = petSpecies;
        this.speciesDetail = speciesDetail;
        this.content = content;
    }



}
