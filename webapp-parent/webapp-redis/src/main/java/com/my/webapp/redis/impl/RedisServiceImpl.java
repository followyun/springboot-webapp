package com.my.webapp.redis.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.my.webapp.redis.RedisManager;
import com.my.webapp.redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 */
@Service
public class RedisServiceImpl implements RedisService {

    private Logger logger = LoggerFactory.getLogger(this.getClass().getSimpleName());
    @Autowired
    RedisManager redisManager;

    @Autowired
    private ObjectMapper objectMapper;

    public RedisServiceImpl(){
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);//忽略未知属性
    }

    @Override
    public void setObject(String key, Object object) {

        try {
           String value = objectMapper.writeValueAsString(object);
            redisManager.put(key, value);
        }catch (JsonProcessingException e){
            logger.error("redis：将object转换为String异常，", e);
            return;
        }
    }

    @Override
    public <T> T getObject(String key, Class<T> clazz) {

        String value = redisManager.get(key);
        if(value == null || "".equals(value)){
            return null;
        }
        try {
            return objectMapper.readValue(value, clazz);
        } catch (Exception e) {
            logger.error("redis：将string转换为object异常，",e);
            return null;
        }
    }

    @Override
    public void setObject(String key, Object object, Long seconds) {

        try {
            String value = objectMapper.writeValueAsString(object);
            redisManager.put(key, value, seconds);
        }catch (JsonProcessingException e){
            logger.error("redis：将object转换为String异常，", e);
            return;
        }
    }

    @Override
    public <T> List<T> getList(String key, Class<T> tClass) {

        String value = redisManager.get(key);
        try {
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, tClass);
            return (List<T>) objectMapper.readValue(value, javaType);
        } catch (IOException e) {
            logger.error("redis：将string转换为List异常，", e);
            return null;
        }
    }

}
