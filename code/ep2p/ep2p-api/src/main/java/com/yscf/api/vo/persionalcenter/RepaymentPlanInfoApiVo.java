/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 待还款详情
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2016年1月25日     Jie.Zou		Initial Version.
 *************************************************************************
 */
package com.yscf.api.vo.persionalcenter;

import java.math.BigDecimal;

/**
 * Description：<br> 
 * 待还款详情
 * @author  Jie.Zou
 * @date    2016年1月25日
 * @version v1.0.0
 */
public class RepaymentPlanInfoApiVo {
	
	/**
	 * 借款Id
	 */
	private String borrowId;
	
	/**
	 * 借款编码
	 */
	private String borrowCode;
	
	/**
	 * 借款名称
	 */
	private String borrowName;
	
	/**
	 * 借款金额
	 */
	private BigDecimal borrowMoney;
	
	/**
	 * 借款利率
	 */
	private BigDecimal borrowRate;
	
	/**
	 * 管理费率
	 */
	private BigDecimal manageExpenseRate;
	
	/**
	 * 期限
	 */
	private String deadline;
	
	/**
	 * 还款方式
	 */
	private String repaymentType;
	
	/**
	 * 待还金额
	 */
	private BigDecimal waitRepayMoney;
	
	/**
	 * 本金
	 */
	private BigDecimal capital;
	
	/**
	 * 利息
	 */
	private BigDecimal interest;
	
	/**
	 * 管理费
	 */
	private BigDecimal managementCost;
	
	/**
	 * 罚息
	 */
	private BigDecimal latefee;
	
	/**
	 * 待还时间
	 */
	private String waitRepayTime;
	
	/**
	 * 当前期次
	 */
	private String currentPlanindex;
	
	/**
	 * 最大期次
	 */
	private String maxPlanindex;
	
	private String customerId;
	
	private String repayId;
	
	public String getRepayId() {
		return repayId;
	}

	public void setRepayId(String repayId) {
		this.repayId = repayId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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

	public BigDecimal getBorrowMoney() {
		return borrowMoney;
	}

	public void setBorrowMoney(BigDecimal borrowMoney) {
		this.borrowMoney = borrowMoney;
	}

	public BigDecimal getBorrowRate() {
		return borrowRate;
	}

	public void setBorrowRate(BigDecimal borrowRate) {
		this.borrowRate = borrowRate;
	}

	public BigDecimal getManageExpenseRate() {
		return manageExpenseRate;
	}

	public void setManageExpenseRate(BigDecimal manageExpenseRate) {
		this.manageExpenseRate = manageExpenseRate;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public String getRepaymentType() {
		return repaymentType;
	}

	public void setRepaymentType(String repaymentType) {
		this.repaymentType = repaymentType;
	}

	public BigDecimal getWaitRepayMoney() {
		return waitRepayMoney;
	}

	public void setWaitRepayMoney(BigDecimal waitRepayMoney) {
		this.waitRepayMoney = waitRepayMoney;
	}

	public BigDecimal getCapital() {
		return capital;
	}

	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public BigDecimal getManagementCost() {
		return managementCost;
	}

	public void setManagementCost(BigDecimal managementCost) {
		this.managementCost = managementCost;
	}

	public BigDecimal getLatefee() {
		return latefee;
	}

	public void setLatefee(BigDecimal latefee) {
		this.latefee = latefee;
	}

	public String getWaitRepayTime() {
		return waitRepayTime;
	}

	public void setWaitRepayTime(String waitRepayTime) {
		this.waitRepayTime = waitRepayTime;
	}

	public String getCurrentPlanindex() {
		return currentPlanindex;
	}

	public void setCurrentPlanindex(String currentPlanindex) {
		this.currentPlanindex = currentPlanindex;
	}

	public String getMaxPlanindex() {
		return maxPlanindex;
	}

	public void setMaxPlanindex(String maxPlanindex) {
		this.maxPlanindex = maxPlanindex;
	}

	public String getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(String borrowId) {
		this.borrowId = borrowId;
	}
	
}


