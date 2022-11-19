package com.yscf.db.model.escf3.business;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.util.StringUtils;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.util.DateUtil;

/**
 * Description：<br> 
 * 收款计划表（t_biz_receipt_plan）
 * @author  JunJie.Liu
 * @date    2015年11月11日
 * @version v1.0.0
 */
public class BizReceiptPlan extends BaseEntity {
	
	private static final long serialVersionUID = -747178989751744798L;
	// 收款计划id
	private String pid;
	// 还款计划id
    private String repPlaPid;
    // 借款id
    private String borrowId;
    // 客户id
    private String customerId;
    
    /**
     * 应收本金
     */
    private BigDecimal capital;

    /**
     * 剩余本金
     */
    private BigDecimal remainingCapital;

    /**
     * 到期时间
     */
    private String expireTime;
    
    /**
     * 管理费
     */
    private BigDecimal managementCost;
    
	// 提前还款罚息
	private BigDecimal prepaymentFee;
    
    // 实际逾期时间
    private Date expireactualTime;
    // 罚息
    private BigDecimal lateFee;
    // 罚息更新时间
    private Date lateFeeCalculateTime;
    // 垫付比例
    private BigDecimal payRate;
    // 垫付金额
    private BigDecimal payAmount;
    // 垫付类型
    private String payType;
    // 垫付时间
    private Date payAmountTime;
    // 还款期数
    private String planIndex;
    // 已还金额
    private BigDecimal repaidCapital;
    // 已还利息
    private BigDecimal repaidInterest;
    // 实际还款时间
    private Date repaidTime;
    // 利息
    private BigDecimal interest;
    // 计息方式
    private String interestType;
    // 状态
    private String status;
    // 收款状态
    private String receiptStatus;
    // 收款状态名称
    private String receiptStatusName;
    // 创建人
    private String createUser;
    // 创建时间
    private Date createTime;
    // 最后修改人
    private String lastUpdateUser;
    // 最后修改时间
    private Date lastUpdateTime;
    // 备注
    private String recPlaDesc;
    
    private String version;
    
    /**
     * 每期投资净利息 
     */
    private BigDecimal netInterest;
    
    /**
     * 每期投资应的的利息（扣除手续费）
     */
    private BigDecimal  receivableInterest;
    
    /**
     * 每期投资净加息卷获取利息
     */
    private BigDecimal netHike;
    
    /**
     * 每期投资应的的净加息卷获取利息（扣除手续费）
     */
    
    private BigDecimal receivableHike;
    
    /**
     * 加息利息管理费
     */
    private BigDecimal increaseInterest;
    
    /**
     * 是否是vip   0不是  1是
     */
    private String isVip;
    
    private String transferId;  // 债权转让id
     
    private String playType;  // 收款计划来源  1原标  2债权转让
    
    private String rDate;  // 收款时间，去掉时分秒的时间
    
    // 本金 + 利息 + 加息 + 逾期罚息 + 投资奖励
    @SuppressWarnings("unused")
	private BigDecimal receiptAmount;
    
	public BigDecimal getPrepaymentFee() {
		return prepaymentFee;
	}

	public void setPrepaymentFee(BigDecimal prepaymentFee) {
		this.prepaymentFee = prepaymentFee;
	}

	public BigDecimal getIncreaseInterest() {
		return increaseInterest;
	}

	public void setIncreaseInterest(BigDecimal increaseInterest) {
		this.increaseInterest = increaseInterest;
	}

	public BigDecimal getNetInterest() {
		return netInterest;
	}

	public void setNetInterest(BigDecimal netInterest) {
		this.netInterest = netInterest;
	}

	public BigDecimal getReceivableInterest() {
		return receivableInterest;
	}

	public void setReceivableInterest(BigDecimal receivableInterest) {
		this.receivableInterest = receivableInterest;
	}

	public BigDecimal getNetHike() {
		return netHike;
	}

	public void setNetHike(BigDecimal netHike) {
		this.netHike = netHike;
	}

	public BigDecimal getReceivableHike() {
		return receivableHike;
	}

	public void setReceivableHike(BigDecimal receivableHike) {
		this.receivableHike = receivableHike;
	}

	public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getRepPlaPid() {
        return repPlaPid;
    }

    public void setRepPlaPid(String repPlaPid) {
        this.repPlaPid = repPlaPid == null ? null : repPlaPid.trim();
    }

    public String getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(String borrowId) {
        this.borrowId = borrowId == null ? null : borrowId.trim();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public BigDecimal getCapital() {
        return capital;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    public Date getExpireactualTime() {
        return expireactualTime;
    }

    public void setExpireactualTime(Date expireactualTime) {
        this.expireactualTime = expireactualTime;
    }

    public BigDecimal getRemainingCapital() {
		return remainingCapital;
	}

	public void setRemainingCapital(BigDecimal remainingCapital) {
		this.remainingCapital = remainingCapital;
	}

	public String getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(String expireTime) {
		this.expireTime = expireTime;
	}

	public BigDecimal getLateFee() {
        return lateFee;
    }

    public void setLateFee(BigDecimal lateFee) {
        this.lateFee = lateFee;
    }

    public Date getLateFeeCalculateTime() {
        return lateFeeCalculateTime;
    }

    public void setLateFeeCalculateTime(Date lateFeeCalculateTime) {
        this.lateFeeCalculateTime = lateFeeCalculateTime;
    }

    public BigDecimal getPayRate() {
        return payRate;
    }

    public void setPayRate(BigDecimal payRate) {
        this.payRate = payRate;
    }

    public BigDecimal getPayAmount() {
        return payAmount;
    }

    public void setPayAmount(BigDecimal payAmount) {
        this.payAmount = payAmount;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType == null ? null : payType.trim();
    }

    public Date getPayAmountTime() {
        return payAmountTime;
    }

    public void setPayAmountTime(Date payAmountTime) {
        this.payAmountTime = payAmountTime;
    }

    public String getPlanIndex() {
        return planIndex;
    }

    public void setPlanIndex(String planIndex) {
        this.planIndex = planIndex == null ? null : planIndex.trim();
    }

    public BigDecimal getRepaidCapital() {
        return repaidCapital;
    }

    public void setRepaidCapital(BigDecimal repaidCapital) {
        this.repaidCapital = repaidCapital;
    }

    public BigDecimal getRepaidInterest() {
        return repaidInterest;
    }

    public void setRepaidInterest(BigDecimal repaidInterest) {
        this.repaidInterest = repaidInterest;
    }

    public Date getRepaidTime() {
        return repaidTime;
    }

    public void setRepaidTime(Date repaidTime) {
        this.repaidTime = repaidTime;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public String getInterestType() {
        return interestType;
    }

    public BigDecimal getManagementCost() {
		return managementCost;
	}

	public void setManagementCost(BigDecimal managementCost) {
		this.managementCost = managementCost;
	}

	public void setInterestType(String interestType) {
        this.interestType = interestType == null ? null : interestType.trim();
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

    public String getRecPlaDesc() {
        return recPlaDesc;
    }

    public void setRecPlaDesc(String recPlaDesc) {
        this.recPlaDesc = recPlaDesc == null ? null : recPlaDesc.trim();
    }
    
    public String getReceiptStatus() {
		return receiptStatus;
	}

	public void setReceiptStatus(String receiptStatus) {
		this.receiptStatus = receiptStatus;
	}

	public String getTransferId() {
		return transferId;
	}

	public void setTransferId(String transferId) {
		this.transferId = transferId;
	}

	public String getPlayType() {
		return playType;
	}

	public void setPlayType(String playType) {
		this.playType = playType;
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
        BizReceiptPlan other = (BizReceiptPlan) that;
        return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getRepPlaPid() == null ? other.getRepPlaPid() == null : this.getRepPlaPid().equals(other.getRepPlaPid()))
            && (this.getBorrowId() == null ? other.getBorrowId() == null : this.getBorrowId().equals(other.getBorrowId()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getCapital() == null ? other.getCapital() == null : this.getCapital().equals(other.getCapital()))
            && (this.getExpireactualTime() == null ? other.getExpireactualTime() == null : this.getExpireactualTime().equals(other.getExpireactualTime()))
            && (this.getExpireTime() == null ? other.getExpireTime() == null : this.getExpireTime().equals(other.getExpireTime()))
            && (this.getLateFee() == null ? other.getLateFee() == null : this.getLateFee().equals(other.getLateFee()))
            && (this.getLateFeeCalculateTime() == null ? other.getLateFeeCalculateTime() == null : this.getLateFeeCalculateTime().equals(other.getLateFeeCalculateTime()))
            && (this.getPayRate() == null ? other.getPayRate() == null : this.getPayRate().equals(other.getPayRate()))
            && (this.getPayAmount() == null ? other.getPayAmount() == null : this.getPayAmount().equals(other.getPayAmount()))
            && (this.getPayType() == null ? other.getPayType() == null : this.getPayType().equals(other.getPayType()))
            && (this.getPayAmountTime() == null ? other.getPayAmountTime() == null : this.getPayAmountTime().equals(other.getPayAmountTime()))
            && (this.getPlanIndex() == null ? other.getPlanIndex() == null : this.getPlanIndex().equals(other.getPlanIndex()))
            && (this.getRepaidCapital() == null ? other.getRepaidCapital() == null : this.getRepaidCapital().equals(other.getRepaidCapital()))
            && (this.getRepaidInterest() == null ? other.getRepaidInterest() == null : this.getRepaidInterest().equals(other.getRepaidInterest()))
            && (this.getRepaidTime() == null ? other.getRepaidTime() == null : this.getRepaidTime().equals(other.getRepaidTime()))
            && (this.getInterest() == null ? other.getInterest() == null : this.getInterest().equals(other.getInterest()))
            && (this.getInterestType() == null ? other.getInterestType() == null : this.getInterestType().equals(other.getInterestType()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getReceiptStatus() == null ? other.getReceiptStatus() == null : this.getReceiptStatus().equals(other.getReceiptStatus()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastUpdateUser() == null ? other.getLastUpdateUser() == null : this.getLastUpdateUser().equals(other.getLastUpdateUser()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()))
            && (this.getRecPlaDesc() == null ? other.getRecPlaDesc() == null : this.getRecPlaDesc().equals(other.getRecPlaDesc()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getRepPlaPid() == null) ? 0 : getRepPlaPid().hashCode());
        result = prime * result + ((getBorrowId() == null) ? 0 : getBorrowId().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getCapital() == null) ? 0 : getCapital().hashCode());
        result = prime * result + ((getExpireactualTime() == null) ? 0 : getExpireactualTime().hashCode());
        result = prime * result + ((getExpireTime() == null) ? 0 : getExpireTime().hashCode());
        result = prime * result + ((getLateFee() == null) ? 0 : getLateFee().hashCode());
        result = prime * result + ((getLateFeeCalculateTime() == null) ? 0 : getLateFeeCalculateTime().hashCode());
        result = prime * result + ((getPayRate() == null) ? 0 : getPayRate().hashCode());
        result = prime * result + ((getPayAmount() == null) ? 0 : getPayAmount().hashCode());
        result = prime * result + ((getPayType() == null) ? 0 : getPayType().hashCode());
        result = prime * result + ((getPayAmountTime() == null) ? 0 : getPayAmountTime().hashCode());
        result = prime * result + ((getPlanIndex() == null) ? 0 : getPlanIndex().hashCode());
        result = prime * result + ((getRepaidCapital() == null) ? 0 : getRepaidCapital().hashCode());
        result = prime * result + ((getRepaidInterest() == null) ? 0 : getRepaidInterest().hashCode());
        result = prime * result + ((getRepaidTime() == null) ? 0 : getRepaidTime().hashCode());
        result = prime * result + ((getInterest() == null) ? 0 : getInterest().hashCode());
        result = prime * result + ((getInterestType() == null) ? 0 : getInterestType().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getReceiptStatus() == null) ? 0 : getReceiptStatus().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        result = prime * result + ((getRecPlaDesc() == null) ? 0 : getRecPlaDesc().hashCode());
        return result;
    }

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
	public String getIsVip() {
		return isVip;
	}

	public void setIsVip(String isVip) {
		this.isVip = isVip;
	}

	public String getRDate() {
		if(StringUtils.hasLength(this.expireTime)){
			this.rDate = new SimpleDateFormat("yyyy-MM-dd").format(DateUtil.format(this.expireTime));
		}else{
			this.rDate = "";
		}
		return rDate;
	}

	public void setRDate(String rDate) {
		this.rDate = rDate;
	}

	public String getReceiptStatusName() {
		return receiptStatusName;
	}

	public void setReceiptStatusName(String receiptStatusName) {
		this.receiptStatusName = receiptStatusName;
	}

	public BigDecimal getReceiptAmount() {
		this.capital = this.capital == null ? BigDecimal.ZERO : this.capital;
		this.netInterest = this.netInterest == null ? BigDecimal.ZERO : this.netInterest;
		this.netHike = this.netHike == null ? BigDecimal.ZERO : this.netHike;
		this.lateFee = this.lateFee == null ? BigDecimal.ZERO : this.lateFee;
		
		
		return this.capital.add(this.netInterest).add(this.netHike).add(this.lateFee);
	}

	public void setReceiptAmount(BigDecimal receiptAmount) {
		this.receiptAmount = receiptAmount;
	}

	
	
	
	
}