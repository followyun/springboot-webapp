package com.my.common.util;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.*;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URLEncodedUtils;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.net.ssl.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.ConnectException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 */
public class HttpClientUtil {

    private static Logger logger = LoggerFactory.getLogger(HttpClientUtil.class);
    private static ObjectMapper objectMapper = new ObjectMapper();
    private static final String httpPrefix = "https";

    public static String doPost(String url, Object content) {

        CloseableHttpClient client = null;
        if (url.startsWith(httpPrefix)) {
            client = createSSLInsecureClient();
        } else {
            client = HttpClients.createDefault();
        }
        HttpPost post = new HttpPost(url);
        String contentJson = null;
        try {
            contentJson = objectMapper.writeValueAsString(content);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        HttpEntity entity = new StringEntity(contentJson, ContentType.APPLICATION_JSON);
        post.setEntity(entity);
        CloseableHttpResponse response = null;
        String responseStr = null;
        try {
            response = client.execute(post);
            Header contentTypeHeader = response.getFirstHeader("Content-Type");
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                responseStr = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
                logger.info("doPost url: " + url + "，Content-Type: " + contentTypeHeader.getValue() + "； Request Data: " + content + "； Response Data: " + responseStr);
            } else {
                logger.warn("doGet url: " + url + "， Content-Type: " + contentTypeHeader.getValue() + "；Request Data: " + content + "；StatusCode: " + response.getStatusLine().getStatusCode());
                return null;
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }

        try {
            response.close();
            client.close();
        } catch (IOException e) {

        }
        return responseStr;
    }

    public static String doPost(String url, Object content, HashMap<String, String> headersMap) {

        CloseableHttpClient client = null;
        if (url.startsWith(httpPrefix)) {
            client = createSSLInsecureClient();
        } else {
            client = HttpClients.createDefault();
        }

        HttpPost post = new HttpPost(url);

        String contentJson = null;
        try {
            contentJson = objectMapper.writeValueAsString(content);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        HttpEntity entity = new StringEntity(contentJson, ContentType.APPLICATION_JSON);
        if (headersMap != null && headersMap.size() > 0) {
            for (Map.Entry<String, String> header : headersMap.entrySet()) {
                post.setHeader(header.getKey(), header.getValue());
            }
        }
        post.setEntity(entity);
        CloseableHttpResponse response = null;
        String responseStr = null;
        try {
            response = client.execute(post);
            Header contentTypeHeader = response.getFirstHeader("Content-Type");
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                responseStr = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
                logger.info("doPost url: " + url + "，Content-Type: " + contentTypeHeader.getValue() + "； Request Data: " + content + "； Response Data: " + responseStr);
            } else {
                logger.warn("doGet url: " + url + "， Content-Type: " + contentTypeHeader.getValue() + "；Request Data: " + content + "；StatusCode: " + response.getStatusLine().getStatusCode());
                return null;
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
            return null;
        }

        try {
            response.close();
            client.close();
        } catch (IOException e) {

        }
        return responseStr;
    }


    public static String doGet(String url) {

        if (url == null || "".equals(url.trim())) {
            throw new IllegalArgumentException("url is null！");
        }
        CloseableHttpClient client = null;
        if (url.startsWith(httpPrefix)) {
            client = createSSLInsecureClient();
        } else {
            client = HttpClients.createDefault();
        }

        HttpGet get = new HttpGet(url);
        CloseableHttpResponse response = null;
        try {
            response = client.execute(get);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        String responseStr = null;
        Header serverHeader = response.getFirstHeader("Server");
        Header contentTypeHeader = response.getFirstHeader("Content-Type");

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            try {
                responseStr = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
                logger.info(" doGet url: " + url + "； Server: " + serverHeader.getValue() + "，Content-Type: " + contentTypeHeader.getValue() + "；Response Data:" + responseStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            logger.warn("doGet url: " + url + "；Server: " + serverHeader.getValue() + "，Content-Type: " + contentTypeHeader.getValue() + "； StatusCode: " + response.getStatusLine().getStatusCode());
            return null;
        }

        try {
            response.close();
            client.close();
        } catch (IOException e) {

        }

        return responseStr;
    }

    /**
     * get请求
     *
     * @param url          请求url
     * @param pathValueMap 装有地址栏参数的集合
     * @param charset      地址栏参数编码
     * @return
     */
    public static String doGet(String url, Map<String, String> pathValueMap, String charset) {
        if (url == null || "".equals(url.trim())) {
            throw new IllegalArgumentException("url is null！");
        }
        List<BasicNameValuePair> nameValuePairs = new ArrayList<BasicNameValuePair>();

        for (Map.Entry<String, String> entry :
                pathValueMap.entrySet()) {
            BasicNameValuePair nameValuePair = new BasicNameValuePair(entry.getKey(), entry.getValue());
            nameValuePairs.add(nameValuePair);
        }

        String finalUrl = url + "?" + URLEncodedUtils.format(nameValuePairs, charset);
        return doGet(finalUrl);
    }

    /**
     * get请求，参数默认编码UTF-8
     *
     * @param url
     * @param pathValueMap
     * @return
     */
    public static String doGet(String url, Map<String, String> pathValueMap) {
        return doGet(url, pathValueMap, "UTF-8");
    }

    public static String doGet(String url, HashMap<String, String> headersMap) {

        CloseableHttpClient client = null;
        if (url.startsWith(httpPrefix)) {
            client = createSSLInsecureClient();
        } else {
            client = HttpClients.createDefault();
        }

        HttpGet get = new HttpGet(url);
        if (headersMap != null && headersMap.size() > 0) {
            for (Map.Entry<String, String> header : headersMap.entrySet()) {
                get.setHeader(header.getKey(), header.getValue());
            }
        }
        CloseableHttpResponse response = null;
        try {
            response = client.execute(get);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        String responseStr = null;
        Header serverHeader = response.getFirstHeader("Server");
        Header contentTypeHeader = response.getFirstHeader("Content-Type");

        if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
            try {
                responseStr = EntityUtils.toString(response.getEntity(), Consts.UTF_8);
                logger.info(" doGet url: " + url + "； Server: " + serverHeader.getValue() + "，Content-Type: " + contentTypeHeader.getValue() + "；Response Data:" + responseStr);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            logger.warn("doGet url: " + url + "；Server: " + serverHeader.getValue() + "，Content-Type: " + contentTypeHeader.getValue() + "； StatusCode: " + response.getStatusLine().getStatusCode());
            return null;
        }

        try {
            response.close();
            client.close();
        } catch (IOException e) {

        }

        return responseStr;
    }

    /**
     * 地址栏上带有指定编码格式的键值对的post请求
     *
     * @param url
     * @param paramsMap
     * @param charset
     * @return
     */
    public static String postWithEncode(String url, Map<String, String> paramsMap, String charset) {

        CloseableHttpClient client = null;
        if (url.startsWith(httpPrefix)) {
            client = createSSLInsecureClient();
        } else {
            client = HttpClients.createDefault();
        }

        HttpPost post = new HttpPost(url);
        List<NameValuePair> pairs = new ArrayList<NameValuePair>();
        for (Map.Entry<String, String> entry : paramsMap.entrySet()) {
            NameValuePair pair = new BasicNameValuePair(entry.getKey(), entry.getValue());
            pairs.add(pair);
        }

        HttpEntity entity = null;
        try {
            /**
             * 该类将pairs中的数据变成charset编码格式的键值对
             * 如n0=v0&n1=v1的形式
             */
            entity = new UrlEncodedFormEntity(pairs, charset);
        } catch (UnsupportedEncodingException e) {
            logger.error("unsupported encoding : " + charset);
            e.printStackTrace();
            return null;
        }
        post.setEntity(entity);
        CloseableHttpResponse response = null;
        try {
            response = client.execute(post);
        } catch (ConnectTimeoutException e) {
            logger.warn("connect timeout ...");
            e.printStackTrace();
        } catch (ConnectException e) {
            logger.error("connect error ...");
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        int statusCode = response.getStatusLine().getStatusCode();
        if (statusCode != HttpStatus.SC_OK) {
            logger.warn(" POST Url: " + url + "param: " + post.getEntity().toString() + "响应error statusCode = " + statusCode);
            return null;
        } else {
            HttpEntity responseEntity = response.getEntity();
            try {
                byte[] bytes = EntityUtils.toByteArray(responseEntity);
                logger.warn(" POST Url: " + url + "param: " + EntityUtils.toString(entity) + "响应error statusCode = " + statusCode);
                String res = new String(bytes, charset);
                return res;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            } finally {
                try {
                    response.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    client.close();
                } catch (IOException e) {

                }
            }

        }
    }

    /**
     * 创建httpsClient
     *
     * @return
     */
    private static CloseableHttpClient createSSLInsecureClient() {
        SSLContext sslcontext = createSSLContext();
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslcontext, new HostnameVerifier() {

            @Override
            public boolean verify(String paramString, SSLSession paramSSLSession) {
                return true;
            }
        });

        return HttpClients.custom().setSSLSocketFactory(sslsf).build();
    }

    /**
     * 获取初始化sslContext
     *
     * @return
     */
    private static SSLContext createSSLContext() {
        SSLContext sslcontext = null;
        try {
            sslcontext = SSLContext.getInstance("TLS");
            sslcontext.init(null, new TrustManager[]{new TrustAnyTrustManager()}, new SecureRandom());
        } catch (NoSuchAlgorithmException e) {
            logger.error("createSSLContext: " + e.getMessage());
        } catch (KeyManagementException e) {
            logger.error("createSSLContext: " + e.getMessage());
        }
        return sslcontext;
    }

    /**
     * 自定义静态私有类
     */
    private static class TrustAnyTrustManager implements X509TrustManager {

        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {
        }

        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[]{};
        }
    }
}
