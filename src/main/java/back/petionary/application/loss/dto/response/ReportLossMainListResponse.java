package back.petionary.application.loss.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReportLossMainListResponse {

    @ApiModelProperty(value = "펫 이름", example = "둥이")
    private String petName;

    @ApiModelProperty(value = "실종 위치", example = "부천시 원미구 00아파트 근처")
    private String lossLocation;

    @ApiModelProperty(value = "실종 날짜 및 시간")
    private LocalDateTime lossDateTime;
}
