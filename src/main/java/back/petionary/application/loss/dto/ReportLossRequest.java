package back.petionary.application.loss.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class ReportLossRequest {
    @ApiModelProperty(value = "이미지 url")
    private String imgUrl;

    @ApiModelProperty(value = "펫 특징", example = "양털 후리스 옷을 입고 있어요.")
    private String special;

    @ApiModelProperty(value = "실종 위치", example = "부천시 원미구 00아파트 근처")
    private String lossLocation;

    @ApiModelProperty(value = "실종 날짜 및 시간", example = "2024-10-02T10:03")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm", timezone = "Asia/Seoul")
    private LocalDateTime lossDateTime;

    @ApiModelProperty(value = "내용", example = "강아지를 찾습니다. 도와주세요.")
    private String content;
}
