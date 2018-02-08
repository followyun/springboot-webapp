package com.my.webapp.common.config;

import org.jasypt.encryption.StringEncryptor;

/**
 */
public class PropertiesEncryptor implements StringEncryptor {

    public PropertiesEncryptor() {
        super();
    }

    @Override
    public String encrypt(String s) {
        return new StringBuilder(s).reverse().toString();//简单加密，将数据反序
    }

    @Override
    public String decrypt(String s) {
        return new StringBuilder(s).reverse().toString();//解密
    }

}
