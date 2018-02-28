package com.my.webapp.redis.constants;

/**
 * Redis中key值前缀
 */
public interface RedisKeyPrefix {
       String SEPERATOR = ":";
       String CUSTOMER_TOKEN = "customer_token" + SEPERATOR;
}
