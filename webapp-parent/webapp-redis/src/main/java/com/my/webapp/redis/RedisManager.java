package com.my.webapp.redis;

/**
 */
public interface RedisManager {

    /**
     * 设置值
     * @param key
     * @param value
     */
    void put(String key, String value);

    /**
     * 设置值，并设置有效期
     * @param key
     * @param value
     * @param second 有效期，单位秒
     */
    void put(String key, String value, Long second);

    /**
     * 获取值
     * @param key
     * @return
     */
    String get(String key);

    /**
     * 删除值
     * @param key
     */
    void delete(String key);
    }
