package com.system.entity;

import java.io.Serializable;

/**
 * @BelongsProject: JWeb
 * @BelongsPackage: com.items.entity
 * @Author: RainGrd
 * @CreateTime: 2022-05-15  20:29
 * @Description: TODO
 * @Version: 1.0
 */
public class Teacher implements Serializable {

    private static final long serialVersionUID = 4181858688275957411L;

    /*教师编号*/
    private String teacherId;
    /*教师姓名*/
    private String teacherName;
    /*账号密码*/
    private String password;
    /*教师年龄*/
    private Integer teacherAge;
    /*教师性别*/
    private String teacherSex;
    /*教师部门*/
    private String teacherDept;
    /*教师联系方式*/
    private String teacherPhone;
    /*教师QQ*/
    private String qq;
    /*教师邮件*/
    private String email;

    /*教师职称*/
    private String professional;
    /*就职状态*/
    private Integer offerStatus;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }


    public String getTeacherSex() {
        return teacherSex;
    }

    public void setTeacherSex(String teacherSex) {
        this.teacherSex = teacherSex;
    }

    public String getTeacherDept() {
        return teacherDept;
    }

    public void setTeacherDept(String teacherDept) {
        this.teacherDept = teacherDept;
    }

    public String getTeacherPhone() {
        return teacherPhone;
    }

    public void setTeacherPhone(String teacherPhone) {
        this.teacherPhone = teacherPhone;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getProfessional() {
        return professional;
    }

    public void setProfessional(String professional) {
        this.professional = professional;
    }

    public Integer getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(Integer offerStatus) {
        this.offerStatus = offerStatus;
    }

    public Integer getTeacherAge() {
        return teacherAge;
    }

    public void setTeacherAge(Integer teacherAge) {
        this.teacherAge = teacherAge;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "teacherId='" + teacherId + '\'' +
                ", teacherName='" + teacherName + '\'' +
                ", password='" + password + '\'' +
                ", teacherAge=" + teacherAge +
                ", teacherSex='" + teacherSex + '\'' +
                ", teacherDept='" + teacherDept + '\'' +
                ", teacherPhone='" + teacherPhone + '\'' +
                ", qq='" + qq + '\'' +
                ", email='" + email + '\'' +
                ", professional='" + professional + '\'' +
                ", offerStatus=" + offerStatus +
                '}';
    }
}
