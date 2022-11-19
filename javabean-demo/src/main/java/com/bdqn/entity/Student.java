package com.bdqn.entity;

/**
 * @BelongsProject: JWeb
 * @BelongsPackage: com.bdqn.entity
 * @Author: RainGrd
 * @CreateTime: 2022-05-09  08:48
 * @Description: 学生类
 * @Version: 1.0
 */
public class Student {
    private String sid;
    private String name;
    private int age;
    private String married;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMarried() {
        return married;
    }

    public void setMarried(String married) {
        this.married = married;
    }

    public void setSid(String sid) {
        this.sid = sid;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void showinfo(){
        System.out.println("我是一个学生！");
    }
}
