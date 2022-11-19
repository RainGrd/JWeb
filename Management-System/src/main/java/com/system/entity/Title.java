package com.system.entity;

import java.io.Serializable;

/**
 * @BelongsProject: JWeb
 * @BelongsPackage: com.items.entity
 * @Author: RainGrd
 * @CreateTime: 2022-05-15  20:35
 * @Description: TODO
 * @Version: 1.0
 */
public class Title implements Serializable {

    private static final long serialVersionUID = -126617489857269238L;
    private Integer titleID;
    /*类型*/
    private String titleType;
    /*名称*/
    private String tieleName;
    /*教师编号*/
    private String teacherId;

    public Integer getTitleID() {
        return titleID;
    }

    public void setTitleID(Integer titleID) {
        this.titleID = titleID;
    }

    public String getTitleType() {
        return titleType;
    }

    public void setTitleType(String titleType) {
        this.titleType = titleType;
    }

    public String getTieleName() {
        return tieleName;
    }

    public void setTieleName(String tieleName) {
        this.tieleName = tieleName;
    }

    public String getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(String teacherId) {
        this.teacherId = teacherId;
    }

    @Override
    public String toString() {
        return "Title{" +
                "titleID=" + titleID +
                ", titleType='" + titleType + '\'' +
                ", tieleName='" + tieleName + '\'' +
                ", teacherId='" + teacherId + '\'' +
                '}';
    }
}
