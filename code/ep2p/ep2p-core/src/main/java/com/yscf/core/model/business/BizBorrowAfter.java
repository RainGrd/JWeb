package com.yscf.core.model.business;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br> 
 * 借后管理bean 
 * @author  Lin.Xu
 * @date    2015年10月18日
 * @version v1.0.0
 */
public class BizBorrowAfter extends BaseEntity implements Cloneable  {
	
	private static final long serialVersionUID = 1L;
	
	//还款id
	private String pid;
	//借款名称
    private String borrowName;
    //借款编码
    private String borrowCode;
    //借款人id
    private String cpid;
    //借款人账户名
    private String customerName;
    //借款人姓名
    private String sname;
    //借款人可用余额
    private String availableBalance;
    //手机号码
    private String phoneNo;
    //借款金额
    private String borrowMoney;
    //借款类型
    private String borrowType;
    //年利率
    private String borrowApr;
    //房产总价
    private String homeTotal;
    //期限
    private String periods;
    //还款计划id
    private String rplanid;
    //还款时间
    private String repaidTime;
    //还款方式
    private String repaymentType;
    //还款方式
    private String repaymentTypeVal;
    //计息方式
    private String accrualType;
    //本期还款金额
    private String capital;
    //还款计划状态
    private String receiptPalnStatus;
    //还款计划状态
    private String receiptPalnStatusVal;
    //是否   黑名单
    private String isBlackList;
    //备注
    private String repPlaDesc;
    
    
    /*扩展字段信息*/
    //还款时间的结束时间值
    private String repaidEndTime;
    //借款金额结束
    private String borrowEndMoney;
    //年利率结束
    private String borrowEndApr;
    //还款期数
    private String planIndex;
    //还款结束期数
    private String planEndIndex;
    //逾期待还款天数
    private Integer overdueDays;
    
    
    
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getBorrowName() {
		return borrowName;
	}
	public void setBorrowName(String borrowName) {
		this.borrowName = borrowName;
	}
	public String getCpid() {
		return cpid;
	}
	public void setCpid(String cpid) {
		this.cpid = cpid;
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
	public String getAvailableBalance() {
		return availableBalance;
	}
	public void setAvailableBalance(String availableBalance) {
		this.availableBalance = availableBalance;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getBorrowMoney() {
		return borrowMoney;
	}
	public void setBorrowMoney(String borrowMoney) {
		this.borrowMoney = borrowMoney;
	}
	public String getBorrowApr() {
		return borrowApr;
	}
	public void setBorrowApr(String borrowApr) {
		this.borrowApr = borrowApr;
	}
	public String getPeriods() {
		return periods;
	}
	public void setPeriods(String periods) {
		this.periods = periods;
	}
	public String getRplanid() {
		return rplanid;
	}
	public void setRplanid(String rplanid) {
		this.rplanid = rplanid;
	}
	public String getRepaidTime() {
		return repaidTime;
	}
	public void setRepaidTime(String repaidTime) {
		this.repaidTime = repaidTime;
	}
	public String getRepaymentType() {
		return repaymentType;
	}
	public void setRepaymentType(String repaymentType) {
		this.repaymentType = repaymentType;
	}
	public String getCapital() {
		return capital;
	}
	public void setCapital(String capital) {
		this.capital = capital;
	}
	public String getReceiptPalnStatus() {
		return receiptPalnStatus;
	}
	public void setReceiptPalnStatus(String receiptPalnStatus) {
		this.receiptPalnStatus = receiptPalnStatus;
	}
	public String getIsBlackList() {
		return isBlackList;
	}
	public void setIsBlackList(String isBlackList) {
		this.isBlackList = isBlackList;
	}
	public String getRepPlaDesc() {
		return repPlaDesc;
	}
	public void setRepPlaDesc(String repPlaDesc) {
		this.repPlaDesc = repPlaDesc;
	}
	public String getHomeTotal() {
		return homeTotal;
	}
	public void setHomeTotal(String homeTotal) {
		this.homeTotal = homeTotal;
	}
	public String getBorrowCode() {
		return borrowCode;
	}
	public void setBorrowCode(String borrowCode) {
		this.borrowCode = borrowCode;
	}
	public String getRepaidEndTime() {
		return repaidEndTime;
	}
	public void setRepaidEndTime(String repaidEndTime) {
		this.repaidEndTime = repaidEndTime;
	}
	public String getBorrowEndMoney() {
		return borrowEndMoney;
	}
	public void setBorrowEndMoney(String borrowEndMoney) {
		this.borrowEndMoney = borrowEndMoney;
	}
	public String getBorrowEndApr() {
		return borrowEndApr;
	}
	public void setBorrowEndApr(String borrowEndApr) {
		this.borrowEndApr = borrowEndApr;
	}
	public String getPlanIndex() {
		return planIndex;
	}
	public void setPlanIndex(String planIndex) {
		this.planIndex = planIndex;
	}
	public String getPlanEndIndex() {
		return planEndIndex;
	}
	public void setPlanEndIndex(String planEndIndex) {
		this.planEndIndex = planEndIndex;
	}
	public String getAccrualType() {
		return accrualType;
	}
	public void setAccrualType(String accrualType) {
		this.accrualType = accrualType;
	}
	public String getBorrowType() {
		return borrowType;
	}
	public void setBorrowType(String borrowType) {
		this.borrowType = borrowType;
	}
	public String getReceiptPalnStatusVal() {
		return receiptPalnStatusVal;
	}
	public void setReceiptPalnStatusVal(String receiptPalnStatusVal) {
		this.receiptPalnStatusVal = receiptPalnStatusVal;
	}
	public String getRepaymentTypeVal() {
		return repaymentTypeVal;
	}
	public void setRepaymentTypeVal(String repaymentTypeVal) {
		this.repaymentTypeVal = repaymentTypeVal;
	}
	public Integer getOverdueDays() {
		return overdueDays;
	}
	public void setOverdueDays(Integer overdueDays) {
		this.overdueDays = overdueDays;
	}
    
}