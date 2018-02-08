package com.my.webapp.app.controller.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.hibernate.validator.constraints.NotBlank;

/**
 */
@ApiModel("加密参数")
public class EncryptParam {

    @ApiModelProperty(value = "明文", required = true)//参数说明
    @NotBlank
    private String text;

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
