package com.yscf.core.model.activity;

import java.math.BigDecimal;


/**
 * 
 * @ClassName : PubExpGold
 * @Description : 体验金信息表javaBean
 * @Author : Qing.Cai
 * @String : 2015年9月17日 下午2:26:01
 */
public class PubExpGold extends ActivityDto {

	private static final long serialVersionUID = -1025146586422780594L;

	private String pid;

	private String expName;

	private BigDecimal expAmount;

	private Integer expNumber;

	private String isBirPrivilege;

	private String expGolDesc;

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

	public String getExpName() {
		return expName;
	}

	public void setExpName(String expName) {
		this.expName = expName == null ? null : expName.trim();
	}

	public BigDecimal getExpAmount() {
		return expAmount;
	}

	public void setExpAmount(BigDecimal expAmount) {
		this.expAmount = expAmount;
	}

	public Integer getExpNumber() {
		return expNumber;
	}

	public void setExpNumber(Integer expNumber) {
		this.expNumber = expNumber;
	}

	public String getIsBirPrivilege() {
		return isBirPrivilege;
	}

	public void setIsBirPrivilege(String isBirPrivilege) {
		this.isBirPrivilege = isBirPrivilege == null ? null : isBirPrivilege.trim();
	}

	public String getExpGolDesc() {
		return expGolDesc;
	}

	public void setExpGolDesc(String expGolDesc) {
		this.expGolDesc = expGolDesc == null ? null : expGolDesc.trim();
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
		PubExpGold other = (PubExpGold) that;
		return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid())) && (this.getExpName() == null ? other.getExpName() == null : this.getExpName().equals(other.getExpName())) && (this.getExpAmount() == null ? other.getExpAmount() == null : this.getExpAmount().equals(other.getExpAmount())) && (this.getExpNumber() == null ? other.getExpNumber() == null : this.getExpNumber().equals(other.getExpNumber())) && (this.getIsBirPrivilege() == null ? other.getIsBirPrivilege() == null : this.getIsBirPrivilege().equals(other.getIsBirPrivilege())) && (this.getExpGolDesc() == null ? other.getExpGolDesc() == null : this.getExpGolDesc().equals(other.getExpGolDesc()))
				&& (this.getEffTime() == null ? other.getEffTime() == null : this.getEffTime().equals(other.getEffTime())) && (this.getExpTime() == null ? other.getExpTime() == null : this.getExpTime().equals(other.getExpTime())) && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus())) && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser())) && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime())) && (this.getLastCreateUser() == null ? other.getLastCreateUser() == null : this.getLastCreateUser().equals(other.getLastCreateUser()))
				&& (this.getLastCreateTime() == null ? other.getLastCreateTime() == null : this.getLastCreateTime().equals(other.getLastCreateTime())) && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
		result = prime * result + ((getExpName() == null) ? 0 : getExpName().hashCode());
		result = prime * result + ((getExpAmount() == null) ? 0 : getExpAmount().hashCode());
		result = prime * result + ((getExpNumber() == null) ? 0 : getExpNumber().hashCode());
		result = prime * result + ((getIsBirPrivilege() == null) ? 0 : getIsBirPrivilege().hashCode());
		result = prime * result + ((getExpGolDesc() == null) ? 0 : getExpGolDesc().hashCode());
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