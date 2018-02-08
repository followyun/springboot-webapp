package com.my.webapp.dao.generator;

/**
 */
public class GeneratorParam {
    /**
     * 存放文件的路径
     */
    private String path;
    /**
     * 表名
     */
    private String tableName;
    private String userName;
    private String password;
    private String url;
    private String author;

    public String getAuthor() {
        return author;
    }

    public GeneratorParam setAuthor(String author) {
        this.author = author;
        return this;
    }

    public String getUserName() {
        return userName;
    }

    public GeneratorParam setUserName(String userName) {
        this.userName = userName;
        return this;

    }

    public String getPassword() {
        return password;
    }

    public GeneratorParam setPassword(String password) {
        this.password = password;
        return this;

    }

    public String getUrl() {
        return url;
    }

    public GeneratorParam setUrl(String url) {
        this.url = url;
        return this;

    }

    public String getPath() {
        return path;
    }

    public GeneratorParam setPath(String path) {
        this.path = path;
        return this;
    }

    public String getTableName() {
        return tableName;
    }

    public GeneratorParam setTableName(String tableName) {
        this.tableName = tableName;
        return this;

    }

}
