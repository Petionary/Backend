package back.petionary.common;

import back.petionary.common.exception.CustomErrorCode;
import back.petionary.common.exception.CustomException;
import java.time.LocalDateTime;
import java.util.Optional;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@EntityListeners({AuditingEntityListener.class})
@Getter
@MappedSuperclass
public abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;

    @CreatedDate
    @Column(updatable = false, nullable = false)
    protected LocalDateTime createdAt;

    @LastModifiedBy
    @Column(nullable = true)
    protected LocalDateTime updatedAt;

    protected  <T> T nullValidate(T element) {
        return Optional.ofNullable(element).orElseThrow(()-> new CustomException(CustomErrorCode.NOT_VALUE));
    }


}
