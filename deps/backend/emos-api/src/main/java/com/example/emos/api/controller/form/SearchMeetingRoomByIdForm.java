package com.example.emos.api.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@Schema(description = "根据ID查询会议室表单")
public class SearchMeetingRoomByIdForm {

    @NotNull(message = "id不能为空")
    @Min(value = 1,message = "id不能小于1")
    @Schema(description = "会议室ID")
    private Integer id;
}
