package com.bdqn.entity;

import java.util.Arrays;

/**
 * @author: RainGrd
 * @date: 2022/5/8
 * @description:
 * @version: 1.0
 * 类描述：
 */
public class User {
    private String username;
    private String password;
    private String sex;
    private String[] hobby;
    private String city;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String[] getHobby() {
        return hobby;
    }

    public void setHobby(String[] hobby) {
        this.hobby = hobby;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", hobby=" + Arrays.toString(hobby) +
                ", city='" + city + '\'' +
                '}';
    }
}
