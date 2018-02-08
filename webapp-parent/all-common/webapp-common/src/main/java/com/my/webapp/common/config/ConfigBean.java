package com.my.webapp.common.config;

import org.hibernate.validator.constraints.NotBlank;

/**
 * 配置文件中的Bean管理
 */
//@Component
//@ConfigurationProperties(prefix = "test")
public class ConfigBean {
    @NotBlank
    private String value;
    @NotBlank
    private String value1;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getValue1() {
        return value1;
    }

    public void setValue1(String value1) {
        this.value1 = value1;
    }
}
