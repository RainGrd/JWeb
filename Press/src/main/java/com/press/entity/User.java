package com.press.entity;

import org.apache.ibatis.type.Alias;

import java.io.Serializable;

/**
 * @author: duan.rong.gui
 * @date: 2022/3/31 11:21:21
 * @description: 主方法
 * @version: 1.0
 * 类描述：用户类
 */
@Alias("pressUser")
public class User implements Serializable {

    private static final long serialVersionUID = 2316537804381326980L;
    /*用户ID*/
    private long id;
    /*用户名*/
    private String username;
    /*密码*/
    private String password;
    /*用户状态 0:超级管理员，1：普通管理员 2：普通用户*/
    private Integer userStatus;
    /*邮箱*/
    private String email;
    /*手机*/
    private String phone;

    public Integer getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(Integer userStatus) {
        this.userStatus = userStatus;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

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


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userStatus=" + userStatus +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

