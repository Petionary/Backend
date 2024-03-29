package back.petionary.application.loss.controller;

import back.petionary.application.loss.dto.request.ReportLossRequest;
import back.petionary.application.loss.dto.response.ReportLossListResponse;
import back.petionary.application.loss.dto.response.ReportLossMainListResponse;
import back.petionary.application.loss.dto.response.ReportLossResponse;
import back.petionary.application.loss.service.ReportLossReadService;
import back.petionary.application.loss.service.ReportLossWriteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(tags = {"분실 신고 API"}, description = "ReportLossController")
@RestController
@RequiredArgsConstructor
@RequestMapping("/report-loss")
public class ReportLossController {
    private final ReportLossWriteService reportLossWriteService;
    private final ReportLossReadService reportLossReadService;

    @ApiOperation(value = "reportLoss 분실 신고 등록", notes = "작성한 분실 신고를 등록합니다.")
    @PostMapping("{accountId}/{petId}")
    public String create(@PathVariable(value = "accountId") Long accountId, @PathVariable(value = "petId") Long petId, @RequestBody ReportLossRequest reportLossRequest){
        return reportLossWriteService.create(accountId, petId, reportLossRequest);
    }
    @ApiOperation(value = "reportLoss 분실 신고 상세보기 조회", notes = "분실 신고 상세보기를 조회합니다.")
    @GetMapping("{reportLossId}")
    public ReportLossResponse findReportLoss(@PathVariable(value = "reportLossId") Long reportLossId) {
        return reportLossReadService.findReportLoss(reportLossId);
    }

    @ApiOperation(value = "reportLoss 분실 신고 게시판 전체 조회", notes = "분실 신고 전체 게시판을 조회합니다.")
    @GetMapping
    public List<ReportLossListResponse> findReportLossList(@PageableDefault(size = 6) Pageable pageable) {
        return reportLossReadService.findReportLossList(pageable);
    }

    @ApiOperation(value = "main reportLoss 분실 신고 조회", notes = "등록된 최신 데이터 5개의 분실 신고 게시물을 조회합니다.")
    @GetMapping("/main")
    public List<ReportLossMainListResponse> findReportLossMainList() {
        return reportLossReadService.findReportLossMainList();
    }
}
