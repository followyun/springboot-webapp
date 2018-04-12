package com.my.webapp.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 */
@SpringBootApplication
//配置web服务器启动加载指定包下的组件
@ComponentScan(basePackages = {
        "com.my.webapp.app",
        "com.my.webapp.common.config",
        "com.my.webapp.service",
        "com.my.webapp.dao",
        "com.my.webapp.redis"
})
@EnableTransactionManagement
public class Application {
    public static void main(String[] args) {
        SpringApplication.run(Application.class);

    }
}
