package com.my.webapp.redis.impl;

import com.my.webapp.redis.RedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;

import java.util.concurrent.TimeUnit;

/**
 */
public class StringRedisManagerImpl implements RedisManager {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Override
    public void put(String key, String value) {
        stringRedisTemplate.opsForValue().set(key, value);
    }

    @Override
    public void put(String key, String value, Long second) {
        stringRedisTemplate.opsForValue().set(key, value, second, TimeUnit.SECONDS);
    }

    @Override
    public String get(String key) {
        return stringRedisTemplate.opsForValue().get(key);
    }

    @Override
    public void delete(String key) {
        stringRedisTemplate.delete(key);
    }


}
