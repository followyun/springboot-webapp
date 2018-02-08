package com.my.common.util;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * DES加密解密工具
 */
public class DESUtil {

    /**
     * 加密
     *
     * @param plainText  明文
     * @param privateKey 文本类型
     * @return
     */
    public static String encrypt(String plainText, String privateKey) {

        try {
            KeyGenerator keygen = KeyGenerator.getInstance("DES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(privateKey.getBytes());

            keygen.init(56, secureRandom);
            SecretKey secretKey = keygen.generateKey();

            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);

            byte[] cipherBytes = cipher.doFinal(plainText.getBytes("utf-8"));
            byte[] plainTextBytes = Base64.getEncoder().encode(cipherBytes);

            return new String(plainTextBytes, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 解密
     *
     * @param cipherText 密文
     * @param privateKey 文本类型
     * @return
     */
    public static String decrypt(String cipherText, String privateKey) {
        try {
            KeyGenerator keygen = KeyGenerator.getInstance("DES");
            SecureRandom secureRandom = SecureRandom.getInstance("SHA1PRNG");
            secureRandom.setSeed(privateKey.getBytes());

            keygen.init(56, secureRandom);
            SecretKey secretKey = keygen.generateKey();

            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey);

            byte[] cipherTextBytes = Base64.getDecoder().decode(cipherText.getBytes("utf-8"));
            byte[] cipherBytes = cipher.doFinal(cipherTextBytes);

            return new String(cipherBytes, "utf-8");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
