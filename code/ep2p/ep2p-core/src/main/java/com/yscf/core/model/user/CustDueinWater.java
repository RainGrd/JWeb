package com.yscf.core.model.user;

import java.math.BigDecimal;
import java.util.Date;

import com.achievo.framework.entity.BaseEntity;

public class CustDueinWater extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3000763971699042624L;

	private String pid;

    private BigDecimal dueinTotalValue;

    private BigDecimal corpus;

    private BigDecimal accrual;

    private String customerId;

    private String borrowId;

    private String status;

    private String backTime;

    private String createUser;

    private Date createTime;

    private String lastUpdateUser;

    private Date lastUpdateTime;

    private String dueWatDesc;
    private String happenBeginTime;//发生开始时间
    private String happenEndTime;//发生开始时间
    

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

    public BigDecimal getDueinTotalValue() {
        return dueinTotalValue;
    }

    public void setDueinTotalValue(BigDecimal dueinTotalValue) {
        this.dueinTotalValue = dueinTotalValue;
    }

    public BigDecimal getCorpus() {
        return corpus;
    }

    public void setCorpus(BigDecimal corpus) {
        this.corpus = corpus;
    }

    public BigDecimal getAccrual() {
        return accrual;
    }

    public void setAccrual(BigDecimal accrual) {
        this.accrual = accrual;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public String getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(String borrowId) {
        this.borrowId = borrowId == null ? null : borrowId.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getBackTime() {
        return backTime;
    }

    public void setBackTime(String backTime) {
        this.backTime = backTime;
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

    public String getDueWatDesc() {
        return dueWatDesc;
    }

    public void setDueWatDesc(String dueWatDesc) {
        this.dueWatDesc = dueWatDesc == null ? null : dueWatDesc.trim();
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
        CustDueinWater other = (CustDueinWater) that;
        return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getDueinTotalValue() == null ? other.getDueinTotalValue() == null : this.getDueinTotalValue().equals(other.getDueinTotalValue()))
            && (this.getCorpus() == null ? other.getCorpus() == null : this.getCorpus().equals(other.getCorpus()))
            && (this.getAccrual() == null ? other.getAccrual() == null : this.getAccrual().equals(other.getAccrual()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getBorrowId() == null ? other.getBorrowId() == null : this.getBorrowId().equals(other.getBorrowId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getBackTime() == null ? other.getBackTime() == null : this.getBackTime().equals(other.getBackTime()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastUpdateUser() == null ? other.getLastUpdateUser() == null : this.getLastUpdateUser().equals(other.getLastUpdateUser()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()))
            && (this.getDueWatDesc() == null ? other.getDueWatDesc() == null : this.getDueWatDesc().equals(other.getDueWatDesc()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getDueinTotalValue() == null) ? 0 : getDueinTotalValue().hashCode());
        result = prime * result + ((getCorpus() == null) ? 0 : getCorpus().hashCode());
        result = prime * result + ((getAccrual() == null) ? 0 : getAccrual().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getBorrowId() == null) ? 0 : getBorrowId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getBackTime() == null) ? 0 : getBackTime().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        result = prime * result + ((getDueWatDesc() == null) ? 0 : getDueWatDesc().hashCode());
        return result;
    }
}