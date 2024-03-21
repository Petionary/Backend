package back.petionary.application.loss.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReportLossListResponse {
    @ApiModelProperty(value = "특징")
    private String feature;

    @ApiModelProperty(value = "닉네임")
    private String nickName;

    @ApiModelProperty(value = "실종 위치")
    private String lossLocation;
}
