package com.my.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;

/**
 * json工具
 */
public class JsonUtil {

    private static final ObjectMapper objectMapper;

    static {
        objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);//忽略未知属性
        //-- 配置Jackson返回日期类型格式（默认是日期的毫秒数）
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        objectMapper.setDateFormat(format);

    }

    /**
     * 将对象转换为json字符串
     *
     * @param object
     * @return
     */
    public static String objToJson(Object object) {

        try {
            String jsonStr = objectMapper.writeValueAsString(object);
            return jsonStr;
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将json字符串转换为对象
     *
     * @param jsonStr
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> T jsonToObj(String jsonStr, Class<T> cls) {

        try {
            return objectMapper.readValue(jsonStr, cls);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将json转换为List
     *
     * @param jsonStr
     * @param cls
     * @param <T>
     * @return
     */
    public static <T> List<T> jsonToList(String jsonStr, Class<T> cls) {

        try {
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(List.class, cls);
            return (List<T>) objectMapper.readValue(jsonStr, javaType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 将json转换为Map，
     *
     * @param jsonStr
     * @param valueCls
     * @param <V>
     * @return
     */
    public static <V> Map<String, V> jsonToMap(String jsonStr, Class<V> valueCls) {

        try {
            JavaType javaType = objectMapper.getTypeFactory().constructParametricType(Map.class, String.class, valueCls);
            return (Map<String, V>) objectMapper.readValue(jsonStr, javaType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
