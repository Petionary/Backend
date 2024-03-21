package back.petionary.application.loss.dto.response;

import back.petionary.domain.loss.entity.ReportLossImages;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReportLossResponse {
    @ApiModelProperty(value = "닉네임", example = "밍밍")
    private String nickName;

    @ApiModelProperty(value = "펫 이름", example = "둥이")
    private String petName;

    @ApiModelProperty(value = "펫 나이")
    private LocalDate petAge;

    @ApiModelProperty(value = "펫 특징", example = "양털 후리스 옷을 입고 있어요.")
    private String feature;

    @ApiModelProperty(value = "실종 위치", example = "부천시 원미구 00아파트 근처")
    private String lossLocation;

    @ApiModelProperty(value = "실종 날짜 및 시간")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime lossDateTime;

    @ApiModelProperty(value = "내용", example = "강아지를 찾습니다. 도와주세요.")
    private String content;
}