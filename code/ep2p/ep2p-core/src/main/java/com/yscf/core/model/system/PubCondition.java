package com.yscf.core.model.system;

import com.achievo.framework.entity.BaseEntity;
import java.util.Date;

/**
 * 
 * @ClassName : PubCondition
 * @Description : 条件设定JavaBean
 * @Author : Qing.Cai
 * @Date : 2015年10月28日 下午4:04:46
 */
public class PubCondition extends BaseEntity {
	private static final long serialVersionUID = 1L;

	private String pid;

	private String condName;

	private String condValue;

	private String condDesc;

	private Integer condOrder;

	private String status;

	private String createUser;

	private Date createTime;

	private String lastUpdateUser;

	private Date lastUpdateTime;

	private String version;

	private String condCode;

	private String condTag;

	public String getCondCode() {
		return condCode;
	}

	public void setCondCode(String condCode) {
		this.condCode = condCode;
	}

	public String getCondTag() {
		return condTag;
	}

	public void setCondTag(String condTag) {
		this.condTag = condTag;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid == null ? null : pid.trim();
	}

	public String getCondName() {
		return condName;
	}

	public void setCondName(String condName) {
		this.condName = condName == null ? null : condName.trim();
	}

	public String getCondValue() {
		return condValue;
	}

	public void setCondValue(String condValue) {
		this.condValue = condValue == null ? null : condValue.trim();
	}

	public String getCondDesc() {
		return condDesc;
	}

	public void setCondDesc(String condDesc) {
		this.condDesc = condDesc == null ? null : condDesc.trim();
	}

	public Integer getCondOrder() {
		return condOrder;
	}

	public void setCondOrder(Integer condOrder) {
		this.condOrder = condOrder ;
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
		PubCondition other = (PubCondition) that;
		return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid())) && (this.getCondName() == null ? other.getCondName() == null : this.getCondName().equals(other.getCondName())) && (this.getCondValue() == null ? other.getCondValue() == null : this.getCondValue().equals(other.getCondValue())) && (this.getCondDesc() == null ? other.getCondDesc() == null : this.getCondDesc().equals(other.getCondDesc())) && (this.getCondOrder() == null ? other.getCondOrder() == null : this.getCondOrder().equals(other.getCondOrder())) && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus())) && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
				&& (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime())) && (this.getLastUpdateUser() == null ? other.getLastUpdateUser() == null : this.getLastUpdateUser().equals(other.getLastUpdateUser())) && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime())) && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
		result = prime * result + ((getCondName() == null) ? 0 : getCondName().hashCode());
		result = prime * result + ((getCondValue() == null) ? 0 : getCondValue().hashCode());
		result = prime * result + ((getCondDesc() == null) ? 0 : getCondDesc().hashCode());
		result = prime * result + ((getCondOrder() == null) ? 0 : getCondOrder().hashCode());
		result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
		result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
		result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
		result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
		result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
		return result;
	}
}