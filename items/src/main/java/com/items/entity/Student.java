package com.items.entity;

/**
 * @BelongsProject: JWeb
 * @BelongsPackage: com.items.entity
 * @Author: RainGrd
 * @CreateTime: 2022-05-15  09:57
 * @Description: TODO
 * @Version: 1.0
 */
public class Student {
    /*学生编号*/
    private Integer id;
    /*学生账号名*/
    private String name;
    /*学生账号密码*/
    private String password;
    /*性别*/
    private String sex;
    /*年龄*/
    private int age;
    /*指导老师编号*/
    private Integer techerId;
    /*班级编号*/
    private Integer grage;
    /*班级名称*/
    private String className;
    /*课程编号*/
    private int tieleId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Integer getTecherId() {
        return techerId;
    }

    public void setTecherId(Integer techerId) {
        this.techerId = techerId;
    }

    public Integer getGrage() {
        return grage;
    }

    public void setGrage(Integer grage) {
        this.grage = grage;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public int getTieleId() {
        return tieleId;
    }

    public void setTieleId(int tieleId) {
        this.tieleId = tieleId;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", sex='" + sex + '\'' +
                ", age=" + age +
                ", techerId=" + techerId +
                ", grage=" + grage +
                ", className='" + className + '\'' +
                ", tieleId=" + tieleId +
                '}';
    }
}
