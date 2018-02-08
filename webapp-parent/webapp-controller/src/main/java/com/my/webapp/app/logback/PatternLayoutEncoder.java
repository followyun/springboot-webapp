package com.my.webapp.app.logback;

import ch.qos.logback.classic.PatternLayout;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.pattern.PatternLayoutEncoderBase;
import com.my.webapp.app.logback.converter.HttpRequestConverter;

import java.util.HashMap;
import java.util.Map;

/**
 */
public class PatternLayoutEncoder extends PatternLayoutEncoderBase<ILoggingEvent> {
    @Override
    public void start() {
        PatternLayout patternLayout = new PatternLayout();
        patternLayout.getInstanceConverterMap().putAll(setPatternType());
        patternLayout.setContext(context);
        patternLayout.setPattern(getPattern());
        patternLayout.setOutputPatternAsHeader(outputPatternAsHeader);
        patternLayout.start();
        this.layout = patternLayout;
        super.start();
        super.start();
    }

    /**
     * 自定义转义类型
     * @return
     */
    private Map<String, String> setPatternType() {

        Map<String, String> map = new HashMap<String, String>();
        map.put("request", HttpRequestConverter.class.getName());

        return map;
    }
}
