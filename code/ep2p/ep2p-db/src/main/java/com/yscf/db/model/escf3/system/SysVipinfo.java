package com.yscf.db.model.escf3.system;

import java.math.BigDecimal;

import com.achievo.framework.entity.BaseEntity;

/**
 * 
 * @ClassName : SysVipinfo
 * @Description : VIP信息表
 * @Author : Qing.Cai
 * @Date : 2015年10月29日 下午3:40:07
 */
public class SysVipinfo extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String pid;

	private String vipCode;

	private String vipName;

	private String vipIco;

	private String vipLevel;

	private BigDecimal validTime;

	private BigDecimal discount;

	private String startTime;

	private String endTime;

	private String remark;

	private String status;

	private String createUser;

	private String createTime;

	private String lastUpdateUser;

	private String lastUpdateTime;

	private String version;

	private String createUserName;

	// 适用开始时间-begin
	private String beginApplyStartData;
	// 适用开始时间-end
	private String endApplyStartData;
	// 适用结束时间-begin
	private String beginApplyFinishData;
	// 适用结束时间-end
	private String endApplyFinishData;
	// 条件主键字符串(1,2,3)
	private String condIds;

	public String getCondIds() {
		return condIds;
	}

	public void setCondIds(String condIds) {
		this.condIds = condIds;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getBeginApplyStartData() {
		return beginApplyStartData;
	}

	public void setBeginApplyStartData(String beginApplyStartData) {
		this.beginApplyStartData = beginApplyStartData;
	}

	public String getEndApplyStartData() {
		return endApplyStartData;
	}

	public void setEndApplyStartData(String endApplyStartData) {
		this.endApplyStartData = endApplyStartData;
	}

	public String getBeginApplyFinishData() {
		return beginApplyFinishData;
	}

	public void setBeginApplyFinishData(String beginApplyFinishData) {
		this.beginApplyFinishData = beginApplyFinishData;
	}

	public String getEndApplyFinishData() {
		return endApplyFinishData;
	}

	public void setEndApplyFinishData(String endApplyFinishData) {
		this.endApplyFinishData = endApplyFinishData;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid == null ? null : pid.trim();
	}

	public String getVipCode() {
		return vipCode;
	}

	public void setVipCode(String vipCode) {
		this.vipCode = vipCode == null ? null : vipCode.trim();
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

	public BigDecimal getValidTime() {
		return validTime;
	}

	public void setValidTime(BigDecimal validTime) {
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark == null ? null : remark.trim();
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
		SysVipinfo other = (SysVipinfo) that;
		return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid())) && (this.getVipCode() == null ? other.getVipCode() == null : this.getVipCode().equals(other.getVipCode())) && (this.getVipName() == null ? other.getVipName() == null : this.getVipName().equals(other.getVipName())) && (this.getVipIco() == null ? other.getVipIco() == null : this.getVipIco().equals(other.getVipIco())) && (this.getVipLevel() == null ? other.getVipLevel() == null : this.getVipLevel().equals(other.getVipLevel())) && (this.getValidTime() == null ? other.getValidTime() == null : this.getValidTime().equals(other.getValidTime())) && (this.getDiscount() == null ? other.getDiscount() == null : this.getDiscount().equals(other.getDiscount()))
				&& (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime())) && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime())) && (this.getRemark() == null ? other.getRemark() == null : this.getRemark().equals(other.getRemark())) && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus())) && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser())) && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
				&& (this.getLastUpdateUser() == null ? other.getLastUpdateUser() == null : this.getLastUpdateUser().equals(other.getLastUpdateUser())) && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime())) && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
		result = prime * result + ((getVipCode() == null) ? 0 : getVipCode().hashCode());
		result = prime * result + ((getVipName() == null) ? 0 : getVipName().hashCode());
		result = prime * result + ((getVipIco() == null) ? 0 : getVipIco().hashCode());
		result = prime * result + ((getVipLevel() == null) ? 0 : getVipLevel().hashCode());
		result = prime * result + ((getValidTime() == null) ? 0 : getValidTime().hashCode());
		result = prime * result + ((getDiscount() == null) ? 0 : getDiscount().hashCode());
		result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
		result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
		result = prime * result + ((getRemark() == null) ? 0 : getRemark().hashCode());
		result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
		result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
		result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
		result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
		result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
		return result;
	}
}