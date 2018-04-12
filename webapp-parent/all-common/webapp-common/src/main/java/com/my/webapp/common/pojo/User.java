package com.my.webapp.common.pojo;

/**
 */
public class User {
    private String userName;
    private int year;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public User(String userName, int year) {
        this.userName = userName;
        this.year = year;
    }

    public User() {
    }
}
