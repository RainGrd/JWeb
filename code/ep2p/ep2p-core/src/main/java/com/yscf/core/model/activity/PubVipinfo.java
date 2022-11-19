package com.yscf.core.model.activity;

import java.math.BigDecimal;


/**
 * 
 * @ClassName : PubVipinfo
 * @Description : VIP信息JavaBean
 * @Author : Qing.Cai
 * @String : 2015年9月25日 上午3:33:35
 */
public class PubVipinfo extends ActivityDto {

	private static final long serialVersionUID = 1L;

	private String pid;

	private String vip;
	
	private String vipName;

	private String vipIco;

	private String vipLevel;

	private Integer validTime;

	private BigDecimal discount;

	private String startTime;

	private String endTime;

	private String isBirPrivilege;

	private String status;

	private String createUser;

	private String createTime;

	private String lastUpdateUser;

	private String lastUpdateTime;

	private String version;

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid == null ? null : pid.trim();
	}

	public String getVip() {
		return vip;
	}

	public void setVip(String vip) {
		this.vip = vip == null ? null : vip.trim();
	}

	public String getVipName() {
		return vipName;
	}

	public void setVipName(String vipName) {
		this.vipName = vipName == null ? null : vipName.trim();
	}

	public String getVipIco() {
		return vipIco;
	}

	public void setVipIco(String vipIco) {
		this.vipIco = vipIco == null ? null : vipIco.trim();
	}

	public String getVipLevel() {
		return vipLevel;
	}

	public void setVipLevel(String vipLevel) {
		this.vipLevel = vipLevel == null ? null : vipLevel.trim();
	}

	public Integer getValidTime() {
		return validTime;
	}

	public void setValidTime(Integer validTime) {
		this.validTime = validTime;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getIsBirPrivilege() {
		return isBirPrivilege;
	}

	public void setIsBirPrivilege(String isBirPrivilege) {
		this.isBirPrivilege = isBirPrivilege == null ? null : isBirPrivilege.trim();
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

	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser == null ? null : lastUpdateUser.trim();
	}

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
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
		PubVipinfo other = (PubVipinfo) that;
		return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid())) && (this.getVip() == null ? other.getVip() == null : this.getVip().equals(other.getVip())) && (this.getVipName() == null ? other.getVipName() == null : this.getVipName().equals(other.getVipName())) && (this.getVipIco() == null ? other.getVipIco() == null : this.getVipIco().equals(other.getVipIco())) && (this.getVipLevel() == null ? other.getVipLevel() == null : this.getVipLevel().equals(other.getVipLevel())) && (this.getValidTime() == null ? other.getValidTime() == null : this.getValidTime().equals(other.getValidTime())) && (this.getDiscount() == null ? other.getDiscount() == null : this.getDiscount().equals(other.getDiscount()))
				&& (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime())) && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime())) && (this.getIsBirPrivilege() == null ? other.getIsBirPrivilege() == null : this.getIsBirPrivilege().equals(other.getIsBirPrivilege())) && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus())) && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser())) && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
				&& (this.getLastUpdateUser() == null ? other.getLastUpdateUser() == null : this.getLastUpdateUser().equals(other.getLastUpdateUser())) && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime())) && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
		result = prime * result + ((getVip() == null) ? 0 : getVip().hashCode());
		result = prime * result + ((getVipName() == null) ? 0 : getVipName().hashCode());
		result = prime * result + ((getVipIco() == null) ? 0 : getVipIco().hashCode());
		result = prime * result + ((getVipLevel() == null) ? 0 : getVipLevel().hashCode());
		result = prime * result + ((getValidTime() == null) ? 0 : getValidTime().hashCode());
		result = prime * result + ((getDiscount() == null) ? 0 : getDiscount().hashCode());
		result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
		result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
		result = prime * result + ((getIsBirPrivilege() == null) ? 0 : getIsBirPrivilege().hashCode());
		result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
		result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
		result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
		result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
		result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
		return result;
	}
}