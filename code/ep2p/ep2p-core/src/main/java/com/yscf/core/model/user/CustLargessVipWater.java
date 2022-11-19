package com.yscf.core.model.user;

import com.achievo.framework.entity.BaseEntity;
import java.util.Date;

public class CustLargessVipWater extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1919515137486591859L;

	private String pid;

	private String largessValue;

	private String getType;

	private String customerId;

	private String vipinfoId;

	private String status;

	private String distributionTime;

	private String createUser;

	private String createTime;

	private String lastUpdateUser;

	private Date lastUpdateTime;

	private String larVipWatDesc;

	private String vipNo;

	private String vipName;// vip名称

	private String tpvId;// vip编号

	public String getVipinfoId() {
		return vipinfoId;
	}

	public void setVipinfoId(String vipinfoId) {
		this.vipinfoId = vipinfoId;
	}

	public String getDistributionTime() {
		return distributionTime;
	}

	public String getLargessValue() {
		return largessValue;
	}

	public void setLargessValue(String largessValue) {
		this.largessValue = largessValue;
	}

	public void setDistributionTime(String distributionTime) {
		this.distributionTime = distributionTime;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getTpvId() {
		return tpvId;
	}

	public void setTpvId(String tpvId) {
		this.tpvId = tpvId;
	}

	public String getVipNo() {
		return vipNo;
	}

	public void setVipNo(String vipNo) {
		this.vipNo = vipNo;
	}

	public String getVipName() {
		return vipName;
	}

	public void setVipName(String vipName) {
		this.vipName = vipName;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid == null ? null : pid.trim();
	}

	public String getGetType() {
		return getType;
	}

	public void setGetType(String getType) {
		this.getType = getType == null ? null : getType.trim();
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId == null ? null : customerId.trim();
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

	public String getLarVipWatDesc() {
		return larVipWatDesc;
	}

	public void setLarVipWatDesc(String larVipWatDesc) {
		this.larVipWatDesc = larVipWatDesc == null ? null : larVipWatDesc.trim();
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
		CustLargessVipWater other = (CustLargessVipWater) that;
		return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid())) && (this.getLargessValue() == null ? other.getLargessValue() == null : this.getLargessValue().equals(other.getLargessValue())) && (this.getGetType() == null ? other.getGetType() == null : this.getGetType().equals(other.getGetType())) && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId())) && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus())) && (this.getDistributionTime() == null ? other.getDistributionTime() == null : this.getDistributionTime().equals(other.getDistributionTime()))
				&& (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser())) && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime())) && (this.getLastUpdateUser() == null ? other.getLastUpdateUser() == null : this.getLastUpdateUser().equals(other.getLastUpdateUser())) && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime())) && (this.getLarVipWatDesc() == null ? other.getLarVipWatDesc() == null : this.getLarVipWatDesc().equals(other.getLarVipWatDesc()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
		result = prime * result + ((getLargessValue() == null) ? 0 : getLargessValue().hashCode());
		result = prime * result + ((getGetType() == null) ? 0 : getGetType().hashCode());
		result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
		result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
		result = prime * result + ((getDistributionTime() == null) ? 0 : getDistributionTime().hashCode());
		result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
		result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
		result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
		result = prime * result + ((getLarVipWatDesc() == null) ? 0 : getLarVipWatDesc().hashCode());
		return result;
	}
}