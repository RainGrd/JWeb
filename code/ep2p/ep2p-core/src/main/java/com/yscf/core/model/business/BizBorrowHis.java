package com.yscf.core.model.business;

import java.math.BigDecimal;

import com.achievo.framework.entity.BaseEntity;

/**
 * 
 * Description：<br> 
 * 借款历史bean
 * @author  Yu.Zhang
 * @date    2015年9月21日
 * @version v1.0.0
 */
public class BizBorrowHis extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String pid;

    private String parentId;

    private String borrowCode;

    private String customerId;

    private String homeId;

    private String homeDesc;

    private BigDecimal homeTotal;

    private BigDecimal borrowMoney;

    private String borrowTime;

    private String borrowUse;

    private String payment;

    private BigDecimal borrowApr;

    private String repaymentType;

    private String accrualType;

    private String other;

    private String borrowType;

    private BigDecimal startMoney;

    private BigDecimal endMoney;

    private String isTimesInvest;

    private String isJiaxiTicket;

    private BigDecimal investRewardScale;

    private String isEquitableAssignment;

    private BigDecimal startValue;

    private BigDecimal endValue;

    private BigDecimal alreadyMoney;

    private BigDecimal borrowProgress;

    private String guaOrgId;

    private String operTime;

    private String isSplit;

    private String deadline;

    private BigDecimal surplusMoney;

    private String borrowPassword;

    private String borDeadline;

    private String publishTime;

    private String borAgrId;

    private String examineStatus;

    private String status;

    private String createUser;

    private String createTime;

    private String lastUpdateUser;

    private String lastUpdateTime;

    private String borrowDesc;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    public String getBorrowCode() {
        return borrowCode;
    }

    public void setBorrowCode(String borrowCode) {
        this.borrowCode = borrowCode == null ? null : borrowCode.trim();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public String getHomeId() {
        return homeId;
    }

    public void setHomeId(String homeId) {
        this.homeId = homeId == null ? null : homeId.trim();
    }

    public String getHomeDesc() {
        return homeDesc;
    }

    public void setHomeDesc(String homeDesc) {
        this.homeDesc = homeDesc == null ? null : homeDesc.trim();
    }

    public BigDecimal getHomeTotal() {
        return homeTotal;
    }

    public void setHomeTotal(BigDecimal homeTotal) {
        this.homeTotal = homeTotal;
    }

    public BigDecimal getBorrowMoney() {
        return borrowMoney;
    }

    public void setBorrowMoney(BigDecimal borrowMoney) {
        this.borrowMoney = borrowMoney;
    }

    public String getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(String borrowTime) {
        this.borrowTime = borrowTime;
    }

    public String getBorrowUse() {
        return borrowUse;
    }

    public void setBorrowUse(String borrowUse) {
        this.borrowUse = borrowUse == null ? null : borrowUse.trim();
    }

    public String getPayment() {
        return payment;
    }

    public void setPayment(String payment) {
        this.payment = payment == null ? null : payment.trim();
    }

    public BigDecimal getBorrowApr() {
        return borrowApr;
    }

    public void setBorrowApr(BigDecimal borrowApr) {
        this.borrowApr = borrowApr;
    }

    public String getRepaymentType() {
        return repaymentType;
    }

    public void setRepaymentType(String repaymentType) {
        this.repaymentType = repaymentType == null ? null : repaymentType.trim();
    }

    public String getAccrualType() {
        return accrualType;
    }

    public void setAccrualType(String accrualType) {
        this.accrualType = accrualType == null ? null : accrualType.trim();
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other == null ? null : other.trim();
    }

    public String getBorrowType() {
        return borrowType;
    }

    public void setBorrowType(String borrowType) {
        this.borrowType = borrowType == null ? null : borrowType.trim();
    }

    public BigDecimal getStartMoney() {
        return startMoney;
    }

    public void setStartMoney(BigDecimal startMoney) {
        this.startMoney = startMoney;
    }

    public BigDecimal getEndMoney() {
        return endMoney;
    }

    public void setEndMoney(BigDecimal endMoney) {
        this.endMoney = endMoney;
    }

    public String getIsTimesInvest() {
        return isTimesInvest;
    }

    public void setIsTimesInvest(String isTimesInvest) {
        this.isTimesInvest = isTimesInvest == null ? null : isTimesInvest.trim();
    }

    public String getIsJiaxiTicket() {
        return isJiaxiTicket;
    }

    public void setIsJiaxiTicket(String isJiaxiTicket) {
        this.isJiaxiTicket = isJiaxiTicket == null ? null : isJiaxiTicket.trim();
    }

    public BigDecimal getInvestRewardScale() {
        return investRewardScale;
    }

    public void setInvestRewardScale(BigDecimal investRewardScale) {
        this.investRewardScale = investRewardScale;
    }

    public String getIsEquitableAssignment() {
        return isEquitableAssignment;
    }

    public void setIsEquitableAssignment(String isEquitableAssignment) {
        this.isEquitableAssignment = isEquitableAssignment == null ? null : isEquitableAssignment.trim();
    }

    public BigDecimal getStartValue() {
        return startValue;
    }

    public void setStartValue(BigDecimal startValue) {
        this.startValue = startValue;
    }

    public BigDecimal getEndValue() {
        return endValue;
    }

    public void setEndValue(BigDecimal endValue) {
        this.endValue = endValue;
    }

    public BigDecimal getAlreadyMoney() {
        return alreadyMoney;
    }

    public void setAlreadyMoney(BigDecimal alreadyMoney) {
        this.alreadyMoney = alreadyMoney;
    }

    public BigDecimal getBorrowProgress() {
        return borrowProgress;
    }

    public void setBorrowProgress(BigDecimal borrowProgress) {
        this.borrowProgress = borrowProgress;
    }

    public String getGuaOrgId() {
        return guaOrgId;
    }

    public void setGuaOrgId(String guaOrgId) {
        this.guaOrgId = guaOrgId == null ? null : guaOrgId.trim();
    }

    public String getOperTime() {
        return operTime;
    }

    public void setOperTime(String operTime) {
        this.operTime = operTime;
    }

    public String getIsSplit() {
        return isSplit;
    }

    public void setIsSplit(String isSplit) {
        this.isSplit = isSplit == null ? null : isSplit.trim();
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline == null ? null : deadline.trim();
    }

    public BigDecimal getSurplusMoney() {
        return surplusMoney;
    }

    public void setSurplusMoney(BigDecimal surplusMoney) {
        this.surplusMoney = surplusMoney;
    }

    public String getBorrowPassword() {
        return borrowPassword;
    }

    public void setBorrowPassword(String borrowPassword) {
        this.borrowPassword = borrowPassword == null ? null : borrowPassword.trim();
    }

    public String getBorDeadline() {
        return borDeadline;
    }

    public void setBorDeadline(String borDeadline) {
        this.borDeadline = borDeadline == null ? null : borDeadline.trim();
    }

    public String getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(String publishTime) {
        this.publishTime = publishTime;
    }

    public String getBorAgrId() {
        return borAgrId;
    }

    public void setBorAgrId(String borAgrId) {
        this.borAgrId = borAgrId == null ? null : borAgrId.trim();
    }

    public String getExamineStatus() {
        return examineStatus;
    }

    public void setExamineStatus(String examineStatus) {
        this.examineStatus = examineStatus == null ? null : examineStatus.trim();
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

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getBorrowDesc() {
        return borrowDesc;
    }

    public void setBorrowDesc(String borrowDesc) {
        this.borrowDesc = borrowDesc == null ? null : borrowDesc.trim();
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
        BizBorrowHis other = (BizBorrowHis) that;
        return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()))
            && (this.getBorrowCode() == null ? other.getBorrowCode() == null : this.getBorrowCode().equals(other.getBorrowCode()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getHomeId() == null ? other.getHomeId() == null : this.getHomeId().equals(other.getHomeId()))
            && (this.getHomeDesc() == null ? other.getHomeDesc() == null : this.getHomeDesc().equals(other.getHomeDesc()))
            && (this.getHomeTotal() == null ? other.getHomeTotal() == null : this.getHomeTotal().equals(other.getHomeTotal()))
            && (this.getBorrowMoney() == null ? other.getBorrowMoney() == null : this.getBorrowMoney().equals(other.getBorrowMoney()))
            && (this.getBorrowTime() == null ? other.getBorrowTime() == null : this.getBorrowTime().equals(other.getBorrowTime()))
            && (this.getBorrowUse() == null ? other.getBorrowUse() == null : this.getBorrowUse().equals(other.getBorrowUse()))
            && (this.getPayment() == null ? other.getPayment() == null : this.getPayment().equals(other.getPayment()))
            && (this.getBorrowApr() == null ? other.getBorrowApr() == null : this.getBorrowApr().equals(other.getBorrowApr()))
            && (this.getRepaymentType() == null ? other.getRepaymentType() == null : this.getRepaymentType().equals(other.getRepaymentType()))
            && (this.getAccrualType() == null ? other.getAccrualType() == null : this.getAccrualType().equals(other.getAccrualType()))
            && (this.getOther() == null ? other.getOther() == null : this.getOther().equals(other.getOther()))
            && (this.getBorrowType() == null ? other.getBorrowType() == null : this.getBorrowType().equals(other.getBorrowType()))
            && (this.getStartMoney() == null ? other.getStartMoney() == null : this.getStartMoney().equals(other.getStartMoney()))
            && (this.getEndMoney() == null ? other.getEndMoney() == null : this.getEndMoney().equals(other.getEndMoney()))
            && (this.getIsTimesInvest() == null ? other.getIsTimesInvest() == null : this.getIsTimesInvest().equals(other.getIsTimesInvest()))
            && (this.getIsJiaxiTicket() == null ? other.getIsJiaxiTicket() == null : this.getIsJiaxiTicket().equals(other.getIsJiaxiTicket()))
            && (this.getInvestRewardScale() == null ? other.getInvestRewardScale() == null : this.getInvestRewardScale().equals(other.getInvestRewardScale()))
            && (this.getIsEquitableAssignment() == null ? other.getIsEquitableAssignment() == null : this.getIsEquitableAssignment().equals(other.getIsEquitableAssignment()))
            && (this.getStartValue() == null ? other.getStartValue() == null : this.getStartValue().equals(other.getStartValue()))
            && (this.getEndValue() == null ? other.getEndValue() == null : this.getEndValue().equals(other.getEndValue()))
            && (this.getAlreadyMoney() == null ? other.getAlreadyMoney() == null : this.getAlreadyMoney().equals(other.getAlreadyMoney()))
            && (this.getBorrowProgress() == null ? other.getBorrowProgress() == null : this.getBorrowProgress().equals(other.getBorrowProgress()))
            && (this.getGuaOrgId() == null ? other.getGuaOrgId() == null : this.getGuaOrgId().equals(other.getGuaOrgId()))
            && (this.getOperTime() == null ? other.getOperTime() == null : this.getOperTime().equals(other.getOperTime()))
            && (this.getIsSplit() == null ? other.getIsSplit() == null : this.getIsSplit().equals(other.getIsSplit()))
            && (this.getDeadline() == null ? other.getDeadline() == null : this.getDeadline().equals(other.getDeadline()))
            && (this.getSurplusMoney() == null ? other.getSurplusMoney() == null : this.getSurplusMoney().equals(other.getSurplusMoney()))
            && (this.getBorrowPassword() == null ? other.getBorrowPassword() == null : this.getBorrowPassword().equals(other.getBorrowPassword()))
            && (this.getBorDeadline() == null ? other.getBorDeadline() == null : this.getBorDeadline().equals(other.getBorDeadline()))
            && (this.getPublishTime() == null ? other.getPublishTime() == null : this.getPublishTime().equals(other.getPublishTime()))
            && (this.getBorAgrId() == null ? other.getBorAgrId() == null : this.getBorAgrId().equals(other.getBorAgrId()))
            && (this.getExamineStatus() == null ? other.getExamineStatus() == null : this.getExamineStatus().equals(other.getExamineStatus()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastUpdateUser() == null ? other.getLastUpdateUser() == null : this.getLastUpdateUser().equals(other.getLastUpdateUser()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()))
            && (this.getBorrowDesc() == null ? other.getBorrowDesc() == null : this.getBorrowDesc().equals(other.getBorrowDesc()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getBorrowCode() == null) ? 0 : getBorrowCode().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getHomeId() == null) ? 0 : getHomeId().hashCode());
        result = prime * result + ((getHomeDesc() == null) ? 0 : getHomeDesc().hashCode());
        result = prime * result + ((getHomeTotal() == null) ? 0 : getHomeTotal().hashCode());
        result = prime * result + ((getBorrowMoney() == null) ? 0 : getBorrowMoney().hashCode());
        result = prime * result + ((getBorrowTime() == null) ? 0 : getBorrowTime().hashCode());
        result = prime * result + ((getBorrowUse() == null) ? 0 : getBorrowUse().hashCode());
        result = prime * result + ((getPayment() == null) ? 0 : getPayment().hashCode());
        result = prime * result + ((getBorrowApr() == null) ? 0 : getBorrowApr().hashCode());
        result = prime * result + ((getRepaymentType() == null) ? 0 : getRepaymentType().hashCode());
        result = prime * result + ((getAccrualType() == null) ? 0 : getAccrualType().hashCode());
        result = prime * result + ((getOther() == null) ? 0 : getOther().hashCode());
        result = prime * result + ((getBorrowType() == null) ? 0 : getBorrowType().hashCode());
        result = prime * result + ((getStartMoney() == null) ? 0 : getStartMoney().hashCode());
        result = prime * result + ((getEndMoney() == null) ? 0 : getEndMoney().hashCode());
        result = prime * result + ((getIsTimesInvest() == null) ? 0 : getIsTimesInvest().hashCode());
        result = prime * result + ((getIsJiaxiTicket() == null) ? 0 : getIsJiaxiTicket().hashCode());
        result = prime * result + ((getInvestRewardScale() == null) ? 0 : getInvestRewardScale().hashCode());
        result = prime * result + ((getIsEquitableAssignment() == null) ? 0 : getIsEquitableAssignment().hashCode());
        result = prime * result + ((getStartValue() == null) ? 0 : getStartValue().hashCode());
        result = prime * result + ((getEndValue() == null) ? 0 : getEndValue().hashCode());
        result = prime * result + ((getAlreadyMoney() == null) ? 0 : getAlreadyMoney().hashCode());
        result = prime * result + ((getBorrowProgress() == null) ? 0 : getBorrowProgress().hashCode());
        result = prime * result + ((getGuaOrgId() == null) ? 0 : getGuaOrgId().hashCode());
        result = prime * result + ((getOperTime() == null) ? 0 : getOperTime().hashCode());
        result = prime * result + ((getIsSplit() == null) ? 0 : getIsSplit().hashCode());
        result = prime * result + ((getDeadline() == null) ? 0 : getDeadline().hashCode());
        result = prime * result + ((getSurplusMoney() == null) ? 0 : getSurplusMoney().hashCode());
        result = prime * result + ((getBorrowPassword() == null) ? 0 : getBorrowPassword().hashCode());
        result = prime * result + ((getBorDeadline() == null) ? 0 : getBorDeadline().hashCode());
        result = prime * result + ((getPublishTime() == null) ? 0 : getPublishTime().hashCode());
        result = prime * result + ((getBorAgrId() == null) ? 0 : getBorAgrId().hashCode());
        result = prime * result + ((getExamineStatus() == null) ? 0 : getExamineStatus().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        result = prime * result + ((getBorrowDesc() == null) ? 0 : getBorrowDesc().hashCode());
        return result;
    }
}