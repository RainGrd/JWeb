package com.bdqn.model;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * @author hengwang
 * @date 2021/11/7 18:57
 */
@Component   //在ioc容器注册bean对象
//和配置文件绑定，可以从配置文件获取数据
@ConfigurationProperties(prefix = "user")
public class User {

    private String userName;

    private String account;

    private int age;

    private java.util.List<Object> list;

    private Address address;

    private Map<String,Object> map;



    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }



    public Map<String, Object> getMap() {
        return map;
    }

    public void setMap(Map<String, Object> map) {
        this.map = map;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public List<Object> getList() {
        return list;
    }

    public void setList(List<Object> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "User{" +
                "userName='" + userName + '\'' +
                ", account='" + account + '\'' +
                ", age=" + age +
                ", address=" + address +
                ", map=" + map +
                ", list=" + list +
                '}';
    }
}
