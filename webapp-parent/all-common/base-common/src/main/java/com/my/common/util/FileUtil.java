package com.my.common.util;

import java.io.*;

/**
 * 读取指定目录下文件内容
 */
public class FileUtil {
    public static String readToString(String pathName) {
        StringBuilder result = new StringBuilder();
        try {
            InputStreamReader file = new InputStreamReader(new FileInputStream(pathName),"UTF-8");
            BufferedReader br = new BufferedReader(file);
            String s = null;
            while ((s = br.readLine()) != null) {
                result.append(s);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        return result.toString();
    }
}

