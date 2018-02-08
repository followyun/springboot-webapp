package com.my.webapp.app.logback.converter;

import ch.qos.logback.classic.pattern.ClassicConverter;
import ch.qos.logback.classic.spi.ILoggingEvent;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**
 */
public class HttpRequestConverter extends ClassicConverter{
    @Override
    public String convert(ILoggingEvent iLoggingEvent) {

        ServletRequestAttributes requestAttributes = (ServletRequestAttributes)RequestContextHolder.getRequestAttributes();
        if(requestAttributes == null){
            return "";
        }

        HttpServletRequest request = requestAttributes.getRequest();
        if(request == null){
            return "";
        }
        return request.getRequestURI();
    }
}
