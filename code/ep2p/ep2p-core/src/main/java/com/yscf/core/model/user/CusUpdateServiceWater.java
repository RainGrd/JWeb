package com.yscf.core.model.user;

import com.achievo.framework.entity.BaseEntity;
import java.util.Date;

public class CusUpdateServiceWater extends BaseEntity {
	
	private static final long serialVersionUID = 5491114147746990995L;

	private String pid;

    private String oldCustServiceId;

    private String newCustServiceId;

    private String customerId;

    private String status;

    private Date distributionTime;

    private String createUser;

    private String createTime;

    private String lastUpdateUser;

    private Date lastUpdateTime;

    private String updSerWatDesc;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getOldCustServiceId() {
        return oldCustServiceId;
    }

    public void setOldCustServiceId(String oldCustServiceId) {
        this.oldCustServiceId = oldCustServiceId == null ? null : oldCustServiceId.trim();
    }

    public String getNewCustServiceId() {
        return newCustServiceId;
    }

    public void setNewCustServiceId(String newCustServiceId) {
        this.newCustServiceId = newCustServiceId == null ? null : newCustServiceId.trim();
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

    public Date getDistributionTime() {
        return distributionTime;
    }

    public void setDistributionTime(Date distributionTime) {
        this.distributionTime = distributionTime;
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

    public String getUpdSerWatDesc() {
        return updSerWatDesc;
    }

    public void setUpdSerWatDesc(String updSerWatDesc) {
        this.updSerWatDesc = updSerWatDesc == null ? null : updSerWatDesc.trim();
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
        CusUpdateServiceWater other = (CusUpdateServiceWater) that;
        return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getOldCustServiceId() == null ? other.getOldCustServiceId() == null : this.getOldCustServiceId().equals(other.getOldCustServiceId()))
            && (this.getNewCustServiceId() == null ? other.getNewCustServiceId() == null : this.getNewCustServiceId().equals(other.getNewCustServiceId()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getDistributionTime() == null ? other.getDistributionTime() == null : this.getDistributionTime().equals(other.getDistributionTime()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastUpdateUser() == null ? other.getLastUpdateUser() == null : this.getLastUpdateUser().equals(other.getLastUpdateUser()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()))
            && (this.getUpdSerWatDesc() == null ? other.getUpdSerWatDesc() == null : this.getUpdSerWatDesc().equals(other.getUpdSerWatDesc()));
    }

    public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getOldCustServiceId() == null) ? 0 : getOldCustServiceId().hashCode());
        result = prime * result + ((getNewCustServiceId() == null) ? 0 : getNewCustServiceId().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getDistributionTime() == null) ? 0 : getDistributionTime().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        result = prime * result + ((getUpdSerWatDesc() == null) ? 0 : getUpdSerWatDesc().hashCode());
        return result;
    }
}