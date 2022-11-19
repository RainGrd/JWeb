package com.yscf.api.vo.myinvest;

import java.math.BigDecimal;

/**
 * 
 * Description：<br>
 * 标的VO
 * 
 * @author JunJie.Liu
 * @date 2016年1月4日
 * @version v1.0.0
 */
public class BizBorrowVo {

	private String pid;

	/**
	 * 借款编号
	 */
	private String borrowCode;

	/**
	 * 借款名称
	 */
	private String borrowName;

	/**
	 * 借款利率
	 */
	private BigDecimal borrowRate;

	/**
	 * 借款金额
	 */
	private BigDecimal borrowMoney;

	/**
	 * 借款时间
	 */
	// private String borrowTime;

	/**
	 * 还款方式
	 */
	private String repaymentType;

	/**
	 * 计息类型
	 */
	private String accrualType;

	/**
	 * 借款类型
	 */
	private String borrowType;

	/**
	 * 起投金额
	 */
	private BigDecimal startMoney;

	/**
	 * 投资上限
	 */
	private BigDecimal endMoney;

	/**
	 * 是否倍数投资
	 */
	private String isTimesInvest;

	/**
	 * 是否允许使用加息券
	 */
	private String isJiaxiTicket;

	/**
	 * 投资奖励比例
	 */
	private BigDecimal investRewardScale;

	/**
	 * 是否允许债券转让
	 */
	private String isEquitableAssignment;

	/**
	 * 债券转让利率开始值
	 */
	private BigDecimal startValue;

	/**
	 * 债券转让利率结束值
	 */
	private BigDecimal endValue;

	/**
	 * 已投金额
	 */
	private BigDecimal alreadyMoney;

	/**
	 * 完成进度
	 */
	private BigDecimal borrowProgress;

	/**
	 * 招标期限
	 */
	private String deadline;

	/**
	 * 未投金额
	 */
	private BigDecimal surplusMoney;

	/**
	 * 借款合同ID
	 */
	private String borAgrId;

	/**
	 * 状态
	 */
	private String status;

	private String createUser;

	/**
	 * 项目描述
	 */
	private String borrowDesc;

	/**
	 * 申请时间
	 */
	// private String applyTime;

	/**
	 * 借款期限
	 */
	private String borDeadline;

	/**
	 * 发标时间
	 */
	// private String publishTime;

	/**
	 * 投标总数
	 */
	private String tenderCount;

	/**
	 * 借款状态
	 */
	private String borStatus;

	/**
	 * 管理费率
	 */
	private BigDecimal manageExpenseRate;

	/**
	 * 满标时间
	 */
	// private Date borFullTime;

	// 扩展字段
	private String repaymentTypeName;

	// 计息类型名称
	private String accrualTypeName;

	// 借款类型名称
	private String borrowTypeName;

	private String joinCondition;

	/**
	 * 当前页
	 */
	private Integer pageIndex;
	/**
	 * 页面大小
	 */
	private Integer pageSize;
	/**
	 * 排序方式
	 */
	private String sortType;
	/**
	 * 排序类型
	 */
	private String sortModel;

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getSortType() {
		return sortType;
	}

	public void setSortType(String sortType) {
		this.sortType = sortType;
	}

	public String getSortModel() {
		return sortModel;
	}

	public void setSortModel(String sortModel) {
		this.sortModel = sortModel;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getBorrowCode() {
		return borrowCode;
	}

	public void setBorrowCode(String borrowCode) {
		this.borrowCode = borrowCode;
	}

	public String getBorrowName() {
		return borrowName;
	}

	public void setBorrowName(String borrowName) {
		this.borrowName = borrowName;
	}

	public BigDecimal getBorrowRate() {
		return borrowRate;
	}

	public void setBorrowRate(BigDecimal borrowRate) {
		this.borrowRate = borrowRate;
	}

	public BigDecimal getBorrowMoney() {
		return borrowMoney;
	}

	public void setBorrowMoney(BigDecimal borrowMoney) {
		this.borrowMoney = borrowMoney;
	}

	// public String getBorrowTime() {
	// return borrowTime;
	// }
	//
	// public void setBorrowTime(String borrowTime) {
	// this.borrowTime = borrowTime;
	// }

	public String getRepaymentType() {
		return repaymentType;
	}

	public void setRepaymentType(String repaymentType) {
		this.repaymentType = repaymentType;
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
		this.isTimesInvest = isTimesInvest;
	}

	public String getIsJiaxiTicket() {
		return isJiaxiTicket;
	}

	public void setIsJiaxiTicket(String isJiaxiTicket) {
		this.isJiaxiTicket = isJiaxiTicket;
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
		this.isEquitableAssignment = isEquitableAssignment;
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

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public BigDecimal getSurplusMoney() {
		return surplusMoney;
	}

	public void setSurplusMoney(BigDecimal surplusMoney) {
		this.surplusMoney = surplusMoney;
	}

	public String getBorAgrId() {
		return borAgrId;
	}

	public void setBorAgrId(String borAgrId) {
		this.borAgrId = borAgrId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getBorrowDesc() {
		return borrowDesc;
	}

	public void setBorrowDesc(String borrowDesc) {
		this.borrowDesc = borrowDesc;
	}

	// public String getApplyTime() {
	// return applyTime;
	// }
	//
	// public void setApplyTime(String applyTime) {
	// this.applyTime = applyTime;
	// }

	public String getBorDeadline() {
		return borDeadline;
	}

	public void setBorDeadline(String borDeadline) {
		this.borDeadline = borDeadline;
	}

	// public String getPublishTime() {
	// return publishTime;
	// }
	//
	// public void setPublishTime(String publishTime) {
	// this.publishTime = publishTime;
	// }

	public String getTenderCount() {
		return tenderCount;
	}

	public void setTenderCount(String tenderCount) {
		this.tenderCount = tenderCount;
	}

	public String getBorStatus() {
		return borStatus;
	}

	public void setBorStatus(String borStatus) {
		this.borStatus = borStatus;
	}

	public BigDecimal getManageExpenseRate() {
		return manageExpenseRate;
	}

	public void setManageExpenseRate(BigDecimal manageExpenseRate) {
		this.manageExpenseRate = manageExpenseRate;
	}

	// public Date getBorFullTime() {
	// return borFullTime;
	// }
	//
	// public void setBorFullTime(Date borFullTime) {
	// this.borFullTime = borFullTime;
	// }

	public String getRepaymentTypeName() {
		return repaymentTypeName;
	}

	public void setRepaymentTypeName(String repaymentTypeName) {
		this.repaymentTypeName = repaymentTypeName;
	}

	public String getJoinCondition() {
		return joinCondition;
	}

	public void setJoinCondition(String joinCondition) {
		this.joinCondition = joinCondition;
	}

	public String getAccrualTypeName() {
		return accrualTypeName;
	}

	public void setAccrualTypeName(String accrualTypeName) {
		this.accrualTypeName = accrualTypeName;
	}

	public String getBorrowTypeName() {
		return borrowTypeName;
	}

	public void setBorrowTypeName(String borrowTypeName) {
		this.borrowTypeName = borrowTypeName;
	}

}