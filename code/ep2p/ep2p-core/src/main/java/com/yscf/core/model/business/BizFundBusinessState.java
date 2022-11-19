package com.yscf.core.model.business;

import java.math.BigDecimal;
import java.util.Date;

import com.achievo.framework.entity.BaseEntity;

/**
 * 
 * Description：<br> 
 * 资金交易状况表
 * @author  Yu.Zhang
 * @date    2015年10月13日
 * @version v1.0.0
 */
public class BizFundBusinessState extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 贷款金额
	 */
	private BigDecimal borAmount;

	/**
	 * 投资金额
	 */
    private BigDecimal investAmount;
    
    /**
     * 待收金额
     */
    private BigDecimal dueAmount;

    /**
     * 已收金额
     */
    private BigDecimal receivedAmount;

    /**
     * 用户收益
     */
    private BigDecimal customerIncome;

    /**
     * 发放收益
     */
    private BigDecimal provideIncome;

    private String status;

    private String createUser;

    private String createTime;

    private String lastUpdateUser;

    private Date lastUpdateTime;

    private String accComDesc;
    
    private String startTime;
    
    private String endTime;
    
    
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

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public BigDecimal getBorAmount() {
        return borAmount;
    }

    public void setBorAmount(BigDecimal borAmount) {
        this.borAmount = borAmount;
    }

    public BigDecimal getInvestAmount() {
        return investAmount;
    }

    public void setInvestAmount(BigDecimal investAmount) {
        this.investAmount = investAmount;
    }

    public BigDecimal getDueAmount() {
        return dueAmount;
    }

    public void setDueAmount(BigDecimal dueAmount) {
        this.dueAmount = dueAmount;
    }

    public BigDecimal getReceivedAmount() {
        return receivedAmount;
    }

    public void setReceivedAmount(BigDecimal receivedAmount) {
        this.receivedAmount = receivedAmount;
    }

    public BigDecimal getCustomerIncome() {
        return customerIncome;
    }

    public void setCustomerIncome(BigDecimal customerIncome) {
        this.customerIncome = customerIncome;
    }

    public BigDecimal getProvideIncome() {
        return provideIncome;
    }

    public void setProvideIncome(BigDecimal provideIncome) {
        this.provideIncome = provideIncome;
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
        BizFundBusinessState other = (BizFundBusinessState) that;
        return (this.getBorAmount() == null ? other.getBorAmount() == null : this.getBorAmount().equals(other.getBorAmount()))
            && (this.getInvestAmount() == null ? other.getInvestAmount() == null : this.getInvestAmount().equals(other.getInvestAmount()))
            && (this.getDueAmount() == null ? other.getDueAmount() == null : this.getDueAmount().equals(other.getDueAmount()))
            && (this.getReceivedAmount() == null ? other.getReceivedAmount() == null : this.getReceivedAmount().equals(other.getReceivedAmount()))
            && (this.getCustomerIncome() == null ? other.getCustomerIncome() == null : this.getCustomerIncome().equals(other.getCustomerIncome()))
            && (this.getProvideIncome() == null ? other.getProvideIncome() == null : this.getProvideIncome().equals(other.getProvideIncome()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getLastUpdateUser() == null ? other.getLastUpdateUser() == null : this.getLastUpdateUser().equals(other.getLastUpdateUser()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()))
            && (this.getAccComDesc() == null ? other.getAccComDesc() == null : this.getAccComDesc().equals(other.getAccComDesc()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getBorAmount() == null) ? 0 : getBorAmount().hashCode());
        result = prime * result + ((getInvestAmount() == null) ? 0 : getInvestAmount().hashCode());
        result = prime * result + ((getDueAmount() == null) ? 0 : getDueAmount().hashCode());
        result = prime * result + ((getReceivedAmount() == null) ? 0 : getReceivedAmount().hashCode());
        result = prime * result + ((getCustomerIncome() == null) ? 0 : getCustomerIncome().hashCode());
        result = prime * result + ((getProvideIncome() == null) ? 0 : getProvideIncome().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        result = prime * result + ((getAccComDesc() == null) ? 0 : getAccComDesc().hashCode());
        return result;
    }
}