package back.petionary.domain.loss.repository;

import back.petionary.domain.loss.entity.ReportLoss;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReportLossRepository extends JpaRepository<ReportLoss, Long> {
    Optional<ReportLoss> findByIdAndAccountId(Long reportLossId, Long accountId);
    Page<ReportLoss> findAll(Pageable pageable);
}
