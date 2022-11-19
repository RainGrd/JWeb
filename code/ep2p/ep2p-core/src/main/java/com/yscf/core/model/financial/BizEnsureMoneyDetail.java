package com.yscf.core.model.financial;

import java.math.BigDecimal;
import java.util.Date;

import com.achievo.framework.entity.BaseEntity;

public class BizEnsureMoneyDetail extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = -3166253918545692670L;

	private String pid;

    /**
     * 明细类型
     */
    private String ensMonDetType;
    
    private String ensMonDetTypeName;

    /**
     * 客户ID
     */
    private String customerId;

    /**
     * 借款ID
     */
    private String borrowId;

    /**
     * 发生金额
     */
    private BigDecimal happenValue;
    
    /**
     * 1 收入 2 支出
     */
    private String feeType;

    /**
     * 保障金余额
     */
    private BigDecimal balance;
    
    /**
     * 处理人ID
     */
    private String actorUser;
    
    private String actorUserName;
    
    /**
     * 处理时间
     */
    private String catorTime;
    
    private String status;

    private String createUser;

    private Date createTime;

    private String lastUpdateUser;

    private Date lastUpdateTime;

    /**
     * 备注
     */
    private String ensMonDetDesc;
    
    /**
     * 查询开始时间
     */
    private String startDate;
    
    /**
     * 查询结束时间
     */
    private String endDate;
    
    private String customerName;
    
    private String sname;
    
    private String phoneNo;
    
    /**
     * 收入总计
     */
    private BigDecimal sumIncome;
    
    /**
     * 支出总计
     */
    private BigDecimal sumExpenditures;
    
    /**
     * 是否详情页面 只用于查询
     */
    private String isDetail;
    
    /**
     * 收入
     */
    private BigDecimal income;
    
    /**
     * 支出
     */
    private BigDecimal expenditures;
    
    public BigDecimal getIncome() {
		return income;
	}

	public void setIncome(BigDecimal income) {
		this.income = income;
	}

	public BigDecimal getExpenditures() {
		return expenditures;
	}

	public void setExpenditures(BigDecimal expenditures) {
		this.expenditures = expenditures;
	}

	public String getIsDetail() {
		return isDetail;
	}

	public void setIsDetail(String isDetail) {
		this.isDetail = isDetail;
	}

	public BigDecimal getSumIncome() {
		return sumIncome;
	}

	public void setSumIncome(BigDecimal sumIncome) {
		this.sumIncome = sumIncome;
	}

	public BigDecimal getSumExpenditures() {
		return sumExpenditures;
	}

	public void setSumExpenditures(BigDecimal sumExpenditures) {
		this.sumExpenditures = sumExpenditures;
	}

	public String getEnsMonDetTypeName() {
		return ensMonDetTypeName;
	}

	public void setEnsMonDetTypeName(String ensMonDetTypeName) {
		this.ensMonDetTypeName = ensMonDetTypeName;
	}

	public String getActorUserName() {
		return actorUserName;
	}

	public void setActorUserName(String actorUserName) {
		this.actorUserName = actorUserName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getFeeType() {
		return feeType;
	}

	public void setFeeType(String feeType) {
		this.feeType = feeType;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getActorUser() {
		return actorUser;
	}

	public void setActorUser(String actorUser) {
		this.actorUser = actorUser;
	}

	public String getCatorTime() {
		return catorTime;
	}

	public void setCatorTime(String catorTime) {
		this.catorTime = catorTime;
	}

	public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getEnsMonDetType() {
        return ensMonDetType;
    }

    public void setEnsMonDetType(String ensMonDetType) {
        this.ensMonDetType = ensMonDetType == null ? null : ensMonDetType.trim();
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

    public BigDecimal getHappenValue() {
        return happenValue;
    }

    public void setHappenValue(BigDecimal happenValue) {
        this.happenValue = happenValue;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
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

    public String getEnsMonDetDesc() {
        return ensMonDetDesc;
    }

    public void setEnsMonDetDesc(String ensMonDetDesc) {
        this.ensMonDetDesc = ensMonDetDesc == null ? null : ensMonDetDesc.trim();
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
        BizEnsureMoneyDetail other = (BizEnsureMoneyDetail) that;
        return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getEnsMonDetType() == null ? other.getEnsMonDetType() == null : this.getEnsMonDetType().equals(other.getEnsMonDetType()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getBorrowId() == null ? other.getBorrowId() == null : this.getBorrowId().equals(other.getBorrowId()))
            && (this.getHappenValue() == null ? other.getHappenValue() == null : this.getHappenValue().equals(other.getHappenValue()))
            && (this.getBalance() == null ? other.getBalance() == null : this.getBalance().equals(other.getBalance()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastUpdateUser() == null ? other.getLastUpdateUser() == null : this.getLastUpdateUser().equals(other.getLastUpdateUser()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()))
            && (this.getEnsMonDetDesc() == null ? other.getEnsMonDetDesc() == null : this.getEnsMonDetDesc().equals(other.getEnsMonDetDesc()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getEnsMonDetType() == null) ? 0 : getEnsMonDetType().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getBorrowId() == null) ? 0 : getBorrowId().hashCode());
        result = prime * result + ((getHappenValue() == null) ? 0 : getHappenValue().hashCode());
        result = prime * result + ((getBalance() == null) ? 0 : getBalance().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        result = prime * result + ((getEnsMonDetDesc() == null) ? 0 : getEnsMonDetDesc().hashCode());
        return result;
    }
}