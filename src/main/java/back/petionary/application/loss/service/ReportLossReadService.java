package back.petionary.application.loss.service;

import back.petionary.application.loss.dto.response.ReportLossListResponse;
import back.petionary.application.loss.dto.response.ReportLossResponse;
import back.petionary.domain.loss.entity.ReportLoss;
import back.petionary.domain.loss.repository.ReportLossRepository;
import back.petionary.exception.PetionaryException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReportLossReadService {
    private final ReportLossRepository reportLossRepository;

    public ReportLossResponse findReportLoss(Long accountId, Long reportLossId) {
        ReportLoss reportLoss = reportLossRepository.findByIdAndAccountId(reportLossId, accountId).orElseThrow(() -> new PetionaryException("회원 id에 해당하는 게시물을 찾지 못했습니다."));
        return new ReportLossResponse(reportLoss.getAccount().getNickName(), reportLoss.getPet().getPetName(), reportLoss.getPet().getPetBirth(), reportLoss.getFeature(), reportLoss.getLossLocation(),
                reportLoss.getLossDateTime(), reportLoss.getContent());
    }

    public List<ReportLossListResponse> findReportLossList(Pageable pageable) {
        Page<ReportLoss> reportLossPage = reportLossRepository.findAll(pageable);
        return reportLossPage.getContent().stream()
                .map(page -> new ReportLossListResponse(page.getFeature(), page.getAccount().getNickName(), page.getLossLocation()))
                .collect(Collectors.toList());
    }
}
