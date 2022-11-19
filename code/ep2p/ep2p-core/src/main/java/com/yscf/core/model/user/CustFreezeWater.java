package com.yscf.core.model.user;

import java.math.BigDecimal;
import java.util.Date;

import com.achievo.framework.entity.BaseEntity;

public class CustFreezeWater extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7947276613833293451L;

	private String pid;

    private BigDecimal freezeValue;

    private String freezeGetType;

    private String freezeType;

    private String status;

    private String customerId;

    private String freezeTime;

    private String createUser;

    private Date createTime;

    private String lastUpdateUser;

    private Date lastUpdateTime;

    private String freWatDesc;
    private String happenBeginTime;
    private String happenEndTime;
    

    public String getHappenBeginTime() {
		return happenBeginTime;
	}

	public void setHappenBeginTime(String happenBeginTime) {
		this.happenBeginTime = happenBeginTime;
	}

	public String getHappenEndTime() {
		return happenEndTime;
	}

	public void setHappenEndTime(String happenEndTime) {
		this.happenEndTime = happenEndTime;
	}

	public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public BigDecimal getFreezeValue() {
        return freezeValue;
    }

    public void setFreezeValue(BigDecimal freezeValue) {
        this.freezeValue = freezeValue;
    }

    public String getFreezeGetType() {
        return freezeGetType;
    }

    public void setFreezeGetType(String freezeGetType) {
        this.freezeGetType = freezeGetType == null ? null : freezeGetType.trim();
    }

    public String getFreezeType() {
        return freezeType;
    }

    public void setFreezeType(String freezeType) {
        this.freezeType = freezeType == null ? null : freezeType.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public String getFreezeTime() {
        return freezeTime;
    }

    public void setFreezeTime(String freezeTime) {
        this.freezeTime = freezeTime;
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

    public String getFreWatDesc() {
        return freWatDesc;
    }

    public void setFreWatDesc(String freWatDesc) {
        this.freWatDesc = freWatDesc == null ? null : freWatDesc.trim();
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
        CustFreezeWater other = (CustFreezeWater) that;
        return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getFreezeValue() == null ? other.getFreezeValue() == null : this.getFreezeValue().equals(other.getFreezeValue()))
            && (this.getFreezeGetType() == null ? other.getFreezeGetType() == null : this.getFreezeGetType().equals(other.getFreezeGetType()))
            && (this.getFreezeType() == null ? other.getFreezeType() == null : this.getFreezeType().equals(other.getFreezeType()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getFreezeTime() == null ? other.getFreezeTime() == null : this.getFreezeTime().equals(other.getFreezeTime()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastUpdateUser() == null ? other.getLastUpdateUser() == null : this.getLastUpdateUser().equals(other.getLastUpdateUser()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()))
            && (this.getFreWatDesc() == null ? other.getFreWatDesc() == null : this.getFreWatDesc().equals(other.getFreWatDesc()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getFreezeValue() == null) ? 0 : getFreezeValue().hashCode());
        result = prime * result + ((getFreezeGetType() == null) ? 0 : getFreezeGetType().hashCode());
        result = prime * result + ((getFreezeType() == null) ? 0 : getFreezeType().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getFreezeTime() == null) ? 0 : getFreezeTime().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        result = prime * result + ((getFreWatDesc() == null) ? 0 : getFreWatDesc().hashCode());
        return result;
    }
}