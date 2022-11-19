package com.yscf.core.model.activity;

import java.math.BigDecimal;


/**
 * 
 * @ClassName	:  PubRedpacket
 * @Description	:  红包信息javaBean
 * @Author 		:  Qing.Cai 
 * @String		:  2015年10月8日 下午4:14:34
 */
public class PubRedpacket extends ActivityDto {
	
	private static final long serialVersionUID = 1L;
	
    private String pid;

    private String repName;

    private BigDecimal repAmount;

    private Integer repNumber;

    private BigDecimal onlyMaxAmount;

    private String isBirPrivilege;

    private String redpacketType;

    private String allotType;

    private String redDesc;

    private String effTime;

    private String expTime;

    private String status;

    private String createUser;

    private String createTime;

    private String lastCreateUser;

    private String lastCreateTime;

    private String version;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getRepName() {
        return repName;
    }

    public void setRepName(String repName) {
        this.repName = repName == null ? null : repName.trim();
    }

    public BigDecimal getRepAmount() {
        return repAmount;
    }

    public void setRepAmount(BigDecimal repAmount) {
        this.repAmount = repAmount;
    }

    public Integer getRepNumber() {
        return repNumber;
    }

    public void setRepNumber(Integer repNumber) {
        this.repNumber = repNumber;
    }

    public BigDecimal getOnlyMaxAmount() {
        return onlyMaxAmount;
    }

    public void setOnlyMaxAmount(BigDecimal onlyMaxAmount) {
        this.onlyMaxAmount = onlyMaxAmount;
    }

    public String getIsBirPrivilege() {
        return isBirPrivilege;
    }

    public void setIsBirPrivilege(String isBirPrivilege) {
        this.isBirPrivilege = isBirPrivilege == null ? null : isBirPrivilege.trim();
    }

    public String getRedpacketType() {
        return redpacketType;
    }

    public void setRedpacketType(String redpacketType) {
        this.redpacketType = redpacketType == null ? null : redpacketType.trim();
    }

    public String getAllotType() {
        return allotType;
    }

    public void setAllotType(String allotType) {
        this.allotType = allotType == null ? null : allotType.trim();
    }

    public String getRedDesc() {
        return redDesc;
    }

    public void setRedDesc(String redDesc) {
        this.redDesc = redDesc == null ? null : redDesc.trim();
    }

    public String getEffTime() {
        return effTime;
    }

    public void setEffTime(String effTime) {
        this.effTime = effTime;
    }

    public String getExpTime() {
        return expTime;
    }

    public void setExpTime(String expTime) {
        this.expTime = expTime;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastCreateUser() {
        return lastCreateUser;
    }

    public void setLastCreateUser(String lastCreateUser) {
        this.lastCreateUser = lastCreateUser == null ? null : lastCreateUser.trim();
    }

    public String getLastCreateTime() {
        return lastCreateTime;
    }

    public void setLastCreateTime(String lastCreateTime) {
        this.lastCreateTime = lastCreateTime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
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
        PubRedpacket other = (PubRedpacket) that;
        return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getRepName() == null ? other.getRepName() == null : this.getRepName().equals(other.getRepName()))
            && (this.getRepAmount() == null ? other.getRepAmount() == null : this.getRepAmount().equals(other.getRepAmount()))
            && (this.getRepNumber() == null ? other.getRepNumber() == null : this.getRepNumber().equals(other.getRepNumber()))
            && (this.getOnlyMaxAmount() == null ? other.getOnlyMaxAmount() == null : this.getOnlyMaxAmount().equals(other.getOnlyMaxAmount()))
            && (this.getIsBirPrivilege() == null ? other.getIsBirPrivilege() == null : this.getIsBirPrivilege().equals(other.getIsBirPrivilege()))
            && (this.getRedpacketType() == null ? other.getRedpacketType() == null : this.getRedpacketType().equals(other.getRedpacketType()))
            && (this.getAllotType() == null ? other.getAllotType() == null : this.getAllotType().equals(other.getAllotType()))
            && (this.getRedDesc() == null ? other.getRedDesc() == null : this.getRedDesc().equals(other.getRedDesc()))
            && (this.getEffTime() == null ? other.getEffTime() == null : this.getEffTime().equals(other.getEffTime()))
            && (this.getExpTime() == null ? other.getExpTime() == null : this.getExpTime().equals(other.getExpTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastCreateUser() == null ? other.getLastCreateUser() == null : this.getLastCreateUser().equals(other.getLastCreateUser()))
            && (this.getLastCreateTime() == null ? other.getLastCreateTime() == null : this.getLastCreateTime().equals(other.getLastCreateTime()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getRepName() == null) ? 0 : getRepName().hashCode());
        result = prime * result + ((getRepAmount() == null) ? 0 : getRepAmount().hashCode());
        result = prime * result + ((getRepNumber() == null) ? 0 : getRepNumber().hashCode());
        result = prime * result + ((getOnlyMaxAmount() == null) ? 0 : getOnlyMaxAmount().hashCode());
        result = prime * result + ((getIsBirPrivilege() == null) ? 0 : getIsBirPrivilege().hashCode());
        result = prime * result + ((getRedpacketType() == null) ? 0 : getRedpacketType().hashCode());
        result = prime * result + ((getAllotType() == null) ? 0 : getAllotType().hashCode());
        result = prime * result + ((getRedDesc() == null) ? 0 : getRedDesc().hashCode());
        result = prime * result + ((getEffTime() == null) ? 0 : getEffTime().hashCode());
        result = prime * result + ((getExpTime() == null) ? 0 : getExpTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastCreateUser() == null) ? 0 : getLastCreateUser().hashCode());
        result = prime * result + ((getLastCreateTime() == null) ? 0 : getLastCreateTime().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        return result;
    }
}