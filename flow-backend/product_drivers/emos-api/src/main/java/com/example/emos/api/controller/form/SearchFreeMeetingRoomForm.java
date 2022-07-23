package com.example.emos.api.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Schema(description = "查询空闲会议室表单")
public class SearchFreeMeetingRoomForm {
    @NotBlank(message = "date不能为空")
    @Pattern(regexp = "^((((1[6-9]|[2-9]\\d)\\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\\d|3[01]))|(((1[6-9]|[2-9]\\d)\\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\\d|30))|(((1[6-9]|[2-9]\\d)\\d{2})-0?2-(0?[1-9]|1\\d|2[0-8]))|(((1[6-9]|[2-9]\\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$", message = "date内容不正确")
    @Schema(description = "日期")
    private String date;

    @NotBlank(message = "start不能为空")
    @Pattern(regexp = "^([01]?[0-9]|2[0-3]):(00|30)$", message = "start内容不正确")
    @Schema(description = "起始时间")
    private String start;

    @NotBlank(message = "end不能为空")
    @Pattern(regexp = "^([01]?[0-9]|2[0-3]):(00|30)$", message = "end内容不正确")
    @Schema(description = "结束时间")
    private String end;
}