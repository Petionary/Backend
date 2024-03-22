package back.petionary.application.loss.service;

import back.petionary.application.loss.dto.response.ReportLossListResponse;
import back.petionary.application.loss.dto.response.ReportLossResponse;
import back.petionary.domain.account.entity.Account;
import back.petionary.domain.account.repository.AccountRepository;
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

    public ReportLossResponse findReportLoss(Long reportLossId) {
        ReportLoss reportLoss = reportLossRepository.findById(reportLossId).orElseThrow(() -> new PetionaryException("해당하는 게시물을 찾을 수 없습니다."));
        return new ReportLossResponse(reportLoss.getAccount().getNickName(), reportLoss.getPet().getPetName(), reportLoss.getPet().getPetBirth(), reportLoss.getFeature(), reportLoss.getLossLocation(),
                reportLoss.getLossDateTime(), reportLoss.getContent());
    }

    public List<ReportLossListResponse> findReportLossList(Pageable pageable) {
        Page<ReportLoss> reportLossPage = reportLossRepository.findAll(pageable);
        return reportLossPage.getContent().stream()
                .map(report -> new ReportLossListResponse(report.getFeature(), report.getAccount().getNickName(), report.getLossLocation()))
                .collect(Collectors.toList());
    }
}
