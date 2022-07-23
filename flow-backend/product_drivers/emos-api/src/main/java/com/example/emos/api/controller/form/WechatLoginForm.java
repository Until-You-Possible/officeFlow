package com.example.emos.api.controller.form;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Schema(name = "WechatLoginForm", description = "微信小程序登陆Emos系统Form类")
@Data
public class WechatLoginForm {

    @Schema(description = "uuid")
    @NotBlank(message = "uuid不能为空")
    private String uuid;

}