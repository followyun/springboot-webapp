package com.my.webapp.service.impl;

import com.my.webapp.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 */
@Service
public class LoginServiceImpl implements LoginService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());

    @Override
    public String login() {
        logger.debug("[debug]使用slf4j进行日志记录！");
        logger.info("[info]使用slf4j进行日志记录！");
        return "welcome to spring boot world！";
    }
}
