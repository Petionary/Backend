package back.petionary.pet.entity;


import back.petionary.common.BaseEntity;
import java.util.UUID;
import javax.persistence.Entity;
import lombok.Getter;

@Getter
@Entity
public class PetImage extends BaseEntity {
    private UUID imageId;
}
