package com.my.webapp.redis;

import java.util.List;

/**
 */
public interface RedisService {

    /**
     * 保存Object
     * @param key
     * @param object
     */
    void setObject(String key, Object object);
    /**
     * 保存Object，并指定有效期
     * @param key
     * @param object
     * @param seconds 有效时间 单位为秒
     */
    void setObject(String key, Object object, Long seconds);

    /**
     * 获取List对象
     * @param key
     * @return
     */
     <T> List<T> getList(String key, Class<T> tClass);
    /**
     * 获取Object
     */
    <T> T getObject(String key, Class<T> clazz);
}
