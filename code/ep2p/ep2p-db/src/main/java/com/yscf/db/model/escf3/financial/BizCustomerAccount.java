package com.yscf.db.model.escf3.financial;

import java.math.BigDecimal;
import java.util.Date;

import com.achievo.framework.entity.BaseEntity;

/**
 * 
 * Description：<br> 
 * 客户账户信息
 * @author  Yu.Zhang
 * @date    2015年12月5日
 * @version v1.0.0
 */
public class BizCustomerAccount extends BaseEntity {
   

	/**
	 * 
	 */
	private static final long serialVersionUID = -2856458847930074404L;

	private String pid;

    private String customerId;

    private BigDecimal rechargeAmount;

    private BigDecimal withdrawAmount;

    private BigDecimal dueAmount;

    private BigDecimal availableAmount;

    private BigDecimal freezeAmount;

    private BigDecimal tenderAmount;

    private BigDecimal experienceAmount;

    private String status;

    private String createUser;

    private String createTime;

    private String lastUpdateUser;

    private Date lastUpdateTime;

    private String accComDesc;

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

    public BigDecimal getRechargeAmount() {
        return rechargeAmount;
    }

    public void setRechargeAmount(BigDecimal rechargeAmount) {
        this.rechargeAmount = rechargeAmount;
    }

    public BigDecimal getWithdrawAmount() {
        return withdrawAmount;
    }

    public void setWithdrawAmount(BigDecimal withdrawAmount) {
        this.withdrawAmount = withdrawAmount;
    }

    public BigDecimal getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(BigDecimal dueAmount) {
        this.dueAmount = dueAmount;
    }

    public BigDecimal getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(BigDecimal availableAmount) {
        this.availableAmount = availableAmount;
    }

    public BigDecimal getFreezeAmount() {
        return freezeAmount;
    }

    public void setFreezeAmount(BigDecimal freezeAmount) {
        this.freezeAmount = freezeAmount;
    }

    public BigDecimal getTenderAmount() {
        return tenderAmount;
    }

    public void setTenderAmount(BigDecimal tenderAmount) {
        this.tenderAmount = tenderAmount;
    }

    public BigDecimal getExperienceAmount() {
        return experienceAmount;
    }

    public void setExperienceAmount(BigDecimal experienceAmount) {
        this.experienceAmount = experienceAmount;
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

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getAccComDesc() {
        return accComDesc;
    }

    public void setAccComDesc(String accComDesc) {
        this.accComDesc = accComDesc == null ? null : accComDesc.trim();
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
        BizCustomerAccount other = (BizCustomerAccount) that;
        return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getRechargeAmount() == null ? other.getRechargeAmount() == null : this.getRechargeAmount().equals(other.getRechargeAmount()))
            && (this.getWithdrawAmount() == null ? other.getWithdrawAmount() == null : this.getWithdrawAmount().equals(other.getWithdrawAmount()))
            && (this.getDueAmount() == null ? other.getDueAmount() == null : this.getDueAmount().equals(other.getDueAmount()))
            && (this.getAvailableAmount() == null ? other.getAvailableAmount() == null : this.getAvailableAmount().equals(other.getAvailableAmount()))
            && (this.getFreezeAmount() == null ? other.getFreezeAmount() == null : this.getFreezeAmount().equals(other.getFreezeAmount()))
            && (this.getTenderAmount() == null ? other.getTenderAmount() == null : this.getTenderAmount().equals(other.getTenderAmount()))
            && (this.getExperienceAmount() == null ? other.getExperienceAmount() == null : this.getExperienceAmount().equals(other.getExperienceAmount()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastUpdateUser() == null ? other.getLastUpdateUser() == null : this.getLastUpdateUser().equals(other.getLastUpdateUser()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()))
            && (this.getAccComDesc() == null ? other.getAccComDesc() == null : this.getAccComDesc().equals(other.getAccComDesc()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getRechargeAmount() == null) ? 0 : getRechargeAmount().hashCode());
        result = prime * result + ((getWithdrawAmount() == null) ? 0 : getWithdrawAmount().hashCode());
        result = prime * result + ((getDueAmount() == null) ? 0 : getDueAmount().hashCode());
        result = prime * result + ((getAvailableAmount() == null) ? 0 : getAvailableAmount().hashCode());
        result = prime * result + ((getFreezeAmount() == null) ? 0 : getFreezeAmount().hashCode());
        result = prime * result + ((getTenderAmount() == null) ? 0 : getTenderAmount().hashCode());
        result = prime * result + ((getExperienceAmount() == null) ? 0 : getExperienceAmount().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        result = prime * result + ((getAccComDesc() == null) ? 0 : getAccComDesc().hashCode());
        return result;
    }
}