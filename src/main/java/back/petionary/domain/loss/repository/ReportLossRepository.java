package back.petionary.domain.loss.repository;

import back.petionary.domain.loss.entity.ReportLoss;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ReportLossRepository extends JpaRepository<ReportLoss, Long> {

    @Query("select r from ReportLoss r join fetch r.account join fetch r.pet where r.id = :reportLossId")
    Optional<ReportLoss> findByIdWithFetchJoin(Long reportLossId);

    @Query(value = "select r from ReportLoss r join fetch r.account",
            countQuery = "select count(r) from ReportLoss r")
    Page<ReportLoss> findAllWithFetchJoin(Pageable pageable);

    @Query(value = "select * from report_loss order by id desc limit 5", nativeQuery = true)
    List<ReportLoss> findAll();
}
