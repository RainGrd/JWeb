package com.yscf.core.model.business;

import com.achievo.framework.entity.BaseEntity;
import java.util.Date;
/**
 * 
 * Description：<br> 
 * 楼盘管理bean
 * @author  Yu.Zhang
 * @date    2015年9月22日
 * @version v1.0.0
 */
public class BizHouses extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String pid;

    private String homesName;

    private String homesProvince;

    private String homesCity;

    private String homesArea;

    private String homesType;

    private String developer;

    private Date launchTime;

    private String propertyRight;

    private String buildingType;

    private String status;

    private String createUser;

    private Date createTime;

    private String lastUpdateUser;

    private Date lastUpdateTime;

    private String houDesc;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getHomesName() {
        return homesName;
    }

    public void setHomesName(String homesName) {
        this.homesName = homesName == null ? null : homesName.trim();
    }

    public String getHomesProvince() {
        return homesProvince;
    }

    public void setHomesProvince(String homesProvince) {
        this.homesProvince = homesProvince == null ? null : homesProvince.trim();
    }

    public String getHomesCity() {
        return homesCity;
    }

    public void setHomesCity(String homesCity) {
        this.homesCity = homesCity == null ? null : homesCity.trim();
    }

    public String getHomesArea() {
        return homesArea;
    }

    public void setHomesArea(String homesArea) {
        this.homesArea = homesArea == null ? null : homesArea.trim();
    }

    public String getHomesType() {
        return homesType;
    }

    public void setHomesType(String homesType) {
        this.homesType = homesType == null ? null : homesType.trim();
    }

    public String getDeveloper() {
        return developer;
    }

    public void setDeveloper(String developer) {
        this.developer = developer == null ? null : developer.trim();
    }

    public Date getLaunchTime() {
        return launchTime;
    }

    public void setLaunchTime(Date launchTime) {
        this.launchTime = launchTime;
    }

    public String getPropertyRight() {
        return propertyRight;
    }

    public void setPropertyRight(String propertyRight) {
        this.propertyRight = propertyRight == null ? null : propertyRight.trim();
    }

    public String getBuildingType() {
        return buildingType;
    }

    public void setBuildingType(String buildingType) {
        this.buildingType = buildingType == null ? null : buildingType.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser == null ? null : lastUpdateUser.trim();
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getHouDesc() {
        return houDesc;
    }

    public void setHouDesc(String houDesc) {
        this.houDesc = houDesc == null ? null : houDesc.trim();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        BizHouses other = (BizHouses) that;
        return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getHomesName() == null ? other.getHomesName() == null : this.getHomesName().equals(other.getHomesName()))
            && (this.getHomesProvince() == null ? other.getHomesProvince() == null : this.getHomesProvince().equals(other.getHomesProvince()))
            && (this.getHomesCity() == null ? other.getHomesCity() == null : this.getHomesCity().equals(other.getHomesCity()))
            && (this.getHomesArea() == null ? other.getHomesArea() == null : this.getHomesArea().equals(other.getHomesArea()))
            && (this.getHomesType() == null ? other.getHomesType() == null : this.getHomesType().equals(other.getHomesType()))
            && (this.getDeveloper() == null ? other.getDeveloper() == null : this.getDeveloper().equals(other.getDeveloper()))
            && (this.getLaunchTime() == null ? other.getLaunchTime() == null : this.getLaunchTime().equals(other.getLaunchTime()))
            && (this.getPropertyRight() == null ? other.getPropertyRight() == null : this.getPropertyRight().equals(other.getPropertyRight()))
            && (this.getBuildingType() == null ? other.getBuildingType() == null : this.getBuildingType().equals(other.getBuildingType()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastUpdateUser() == null ? other.getLastUpdateUser() == null : this.getLastUpdateUser().equals(other.getLastUpdateUser()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()))
            && (this.getHouDesc() == null ? other.getHouDesc() == null : this.getHouDesc().equals(other.getHouDesc()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getHomesName() == null) ? 0 : getHomesName().hashCode());
        result = prime * result + ((getHomesProvince() == null) ? 0 : getHomesProvince().hashCode());
        result = prime * result + ((getHomesCity() == null) ? 0 : getHomesCity().hashCode());
        result = prime * result + ((getHomesArea() == null) ? 0 : getHomesArea().hashCode());
        result = prime * result + ((getHomesType() == null) ? 0 : getHomesType().hashCode());
        result = prime * result + ((getDeveloper() == null) ? 0 : getDeveloper().hashCode());
        result = prime * result + ((getLaunchTime() == null) ? 0 : getLaunchTime().hashCode());
        result = prime * result + ((getPropertyRight() == null) ? 0 : getPropertyRight().hashCode());
        result = prime * result + ((getBuildingType() == null) ? 0 : getBuildingType().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        result = prime * result + ((getHouDesc() == null) ? 0 : getHouDesc().hashCode());
        return result;
    }
}