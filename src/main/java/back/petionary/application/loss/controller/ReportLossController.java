package back.petionary.application.loss.controller;

import back.petionary.application.loss.dto.ReportLossRequest;
import back.petionary.application.loss.service.ReportLossWriteService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Api(tags = {"분실 신고 등록 API"}, description = "ReportLossController")
@RestController
@RequiredArgsConstructor
@RequestMapping("/reportLoss")
public class ReportLossController {
    private final ReportLossWriteService reportLossWriteService;

    @ApiOperation(value = "reportLoss 분실 신고 등록", notes = "작성한 분실 신고를 등록합니다.")
    @PostMapping("{accountId}/{petId}")
    public String create(@PathVariable(value = "accountId") Long accountId, @PathVariable(value = "petId") Long petId, @RequestBody ReportLossRequest reportLossRequest){
        return reportLossWriteService.create(accountId, petId, reportLossRequest);
    }
}
