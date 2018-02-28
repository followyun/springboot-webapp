package com.my.webapp.redis.impl;

import com.my.webapp.redis.RedisManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Component;

/**
 */
@Component
public class DiyRedisManagerImpl implements RedisManager {

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public void put(String key, String value) {
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer redisSerializer = new StringRedisSerializer();
                connection.set(key.getBytes(), redisSerializer.serialize(value));
                return null;
            }
        });
    }

    @Override
    public void put(String key, String value, Long second) {

    }

    @Override
    public String get(String key) {
        return (String) redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer redisSerializer = new StringRedisSerializer();
                byte[] value = connection.get(key.getBytes());
                return redisSerializer.deserialize(value);
            }
        });

    }

    @Override
    public void delete(String key) {
        redisTemplate.execute(new RedisCallback() {
            @Override
            public Object doInRedis(RedisConnection connection) throws DataAccessException {
                connection.del(key.getBytes());
                return null;
            }
        });
    }
}
