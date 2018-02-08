package com.my.webapp.app;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 */
@SpringBootApplication
//配置web服务器启动加载指定包下的组件
@ComponentScan(basePackages = {
        "com.my.webapp.app",
        "com.my.webapp.common.config",
        "com.my.webapp.service",
        "com.my.webapp.dao"
})
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);

    }
}
