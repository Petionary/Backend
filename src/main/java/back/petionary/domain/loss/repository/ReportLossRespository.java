package back.petionary.domain.loss.repository;

import back.petionary.domain.loss.entity.ReportLoss;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportLossRespository  extends JpaRepository<ReportLoss, Long> {
}
