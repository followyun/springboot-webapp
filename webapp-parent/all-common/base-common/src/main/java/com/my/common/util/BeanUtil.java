package com.my.common.util;

import org.springframework.beans.BeanUtils;
import org.springframework.cglib.beans.BeanMap;

import java.util.HashMap;
import java.util.Map;

public class BeanUtil extends BeanUtils {

    /**
     * bean转换为map
     *
     * @param o
     * @return
     */
    public static Map beanToMap(Object o) {

        if (o == null) {
            return null;
        }
        Map<String, Object> map = new HashMap<String, Object>();
        BeanMap beanMap = BeanMap.create(o);
        for (Object key : beanMap.keySet()) {
            map.put(key + "", beanMap.get(key));
        }

        return map;
    }

    /**
     * bean转换为map
     *
     * @param map
     * @param <T>
     * @return
     */
    public static <T> T mapToBean(Map map, Class<T> tClass) {

        T bean = null;
        try {
            bean = tClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
            return null;
        } catch (IllegalAccessException e) {
            e.printStackTrace();
            return null;
        }
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);

        return bean;
    }

    /**
     * 将bean转换为地址栏上的参数
     *
     * @param bean
     * @return
     */
    private static String formatRequestParam(Object bean) {
        StringBuilder strBuilder = new StringBuilder();
        if (bean != null) {
            BeanMap map = BeanMap.create(bean);
            for (Object key : map.keySet()) {
                strBuilder.append(key + "").append("=");
                if (map.get(key) != null) {
                    strBuilder.append(map.get(key).toString());
                }
                strBuilder.append("&");
            }
            strBuilder.deleteCharAt(strBuilder.length() - 1);//删除掉最后一个"&"

        }
        return strBuilder.toString();
    }

}
