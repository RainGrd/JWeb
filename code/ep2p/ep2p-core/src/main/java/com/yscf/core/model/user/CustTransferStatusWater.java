package com.yscf.core.model.user;

import com.achievo.framework.entity.BaseEntity;
import java.util.Date;

public class CustTransferStatusWater extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 329187862430985921L;

	private String pid;

    private String customerId;

    private String upStatus;

    private Date effTime;

    private Date expTime;

    private String upDesc;

    private String distributionTime;

    private String createUser;

    private String createTime;

    private String lastUpdateUser;

    private Date lastUpdateTime;

    private String cusTraStaWatDesc;
    
    

    public Date getEffTime() {
		return effTime;
	}

	public void setEffTime(Date effTime) {
		this.effTime = effTime;
	}

	public Date getExpTime() {
		return expTime;
	}

	public void setExpTime(Date expTime) {
		this.expTime = expTime;
	}

	public String getDistributionTime() {
		return distributionTime;
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

	public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public String getUpStatus() {
        return upStatus;
    }

    public void setUpStatus(String upStatus) {
        this.upStatus = upStatus == null ? null : upStatus.trim();
    }

    public String getUpDesc() {
        return upDesc;
    }

    public void setUpDesc(String upDesc) {
        this.upDesc = upDesc == null ? null : upDesc.trim();
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

    public String getCusTraStaWatDesc() {
        return cusTraStaWatDesc;
    }

    public void setCusTraStaWatDesc(String cusTraStaWatDesc) {
        this.cusTraStaWatDesc = cusTraStaWatDesc == null ? null : cusTraStaWatDesc.trim();
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
        CustTransferStatusWater other = (CustTransferStatusWater) that;
        return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getUpStatus() == null ? other.getUpStatus() == null : this.getUpStatus().equals(other.getUpStatus()))
            && (this.getEffTime() == null ? other.getEffTime() == null : this.getEffTime().equals(other.getEffTime()))
            && (this.getExpTime() == null ? other.getExpTime() == null : this.getExpTime().equals(other.getExpTime()))
            && (this.getUpDesc() == null ? other.getUpDesc() == null : this.getUpDesc().equals(other.getUpDesc()))
            && (this.getDistributionTime() == null ? other.getDistributionTime() == null : this.getDistributionTime().equals(other.getDistributionTime()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastUpdateUser() == null ? other.getLastUpdateUser() == null : this.getLastUpdateUser().equals(other.getLastUpdateUser()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()))
            && (this.getCusTraStaWatDesc() == null ? other.getCusTraStaWatDesc() == null : this.getCusTraStaWatDesc().equals(other.getCusTraStaWatDesc()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getUpStatus() == null) ? 0 : getUpStatus().hashCode());
        result = prime * result + ((getEffTime() == null) ? 0 : getEffTime().hashCode());
        result = prime * result + ((getExpTime() == null) ? 0 : getExpTime().hashCode());
        result = prime * result + ((getUpDesc() == null) ? 0 : getUpDesc().hashCode());
        result = prime * result + ((getDistributionTime() == null) ? 0 : getDistributionTime().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        result = prime * result + ((getCusTraStaWatDesc() == null) ? 0 : getCusTraStaWatDesc().hashCode());
        return result;
    }
}