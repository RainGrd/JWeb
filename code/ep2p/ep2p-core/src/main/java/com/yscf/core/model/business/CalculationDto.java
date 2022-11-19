/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * TODO
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年11月11日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.model.business;

import java.math.BigDecimal;

/**
 * Description：<br>
 * 借款试算DTO
 * 
 * @author Yu.Zhang
 * @date 2015年11月11日
 * @version v1.0.0
 */
public class CalculationDto {

	/**
	 * 借款金额
	 */
	private BigDecimal repaymentAmt;

	/**
	 * 借款期限
	 */
	private Integer repaymentDeadline;

	/**
	 * 借款利率
	 */
	private BigDecimal repaymentRate;

	/**
	 * 管理费利率
	 */
	private BigDecimal managementRate;

	/**
	 * 利息手续费率
	 */
	private BigDecimal interestRate;

	/**
	 * 加息劵
	 */
	private BigDecimal addInterest;

	/**
	 * 加息劵手续费率
	 */
	private BigDecimal addInterestRate;

	/**
	 * 还款方式  1 按月等额本息 2 按月付息，到期还本 3 到期一次性还本息
	 */
	private Integer repaymentType;

	/**
	 * 计息日期
	 */
	private String interestDate;
	/**
	 * 投资奖励百分比
	 */
	private BigDecimal rewardRate;
	
	/**
	 * 创建人
	 */
	private String createUser;
	
	/**
	 * 借款ID
	 */
	private String borrowId;
	
	/**
	 * 计息方式
	 */
	private String interestType;
	
	
	/**
	 * 客户ID
	 */
	private String customerId;
	
	/**
	 * 总加息收益。 如果有传总加息利息，则不用计算加息收益，直接平均分配到每期加息收益中
	 */
	private BigDecimal sumAddInterest;
	
	public BigDecimal getSumAddInterest() {
		return sumAddInterest;
	}

	public void setSumAddInterest(BigDecimal sumAddInterest) {
		this.sumAddInterest = sumAddInterest;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(String borrowId) {
		this.borrowId = borrowId;
	}

	public String getInterestType() {
		return interestType;
	}

	public void setInterestType(String interestType) {
		this.interestType = interestType;
	}

	public BigDecimal getAddInterest() {
		return addInterest;
	}

	public void setAddInterest(BigDecimal addInterest) {
		this.addInterest = addInterest;
	}

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}

	public BigDecimal getAddInterestRate() {
		return addInterestRate;
	}

	public void setAddInterestRate(BigDecimal addInterestRate) {
		this.addInterestRate = addInterestRate;
	}

	public BigDecimal getRewardRate() {
		return rewardRate;
	}

	public void setRewardRate(BigDecimal rewardRate) {
		this.rewardRate = rewardRate;
	}

	public String getInterestDate() {
		return interestDate;
	}

	public void setInterestDate(String interestDate) {
		this.interestDate = interestDate;
	}

	public Integer getRepaymentDeadline() {
		return repaymentDeadline;
	}

	public void setRepaymentDeadline(Integer repaymentDeadline) {
		this.repaymentDeadline = repaymentDeadline;
	}

	public BigDecimal getRepaymentRate() {
		return repaymentRate;
	}

	public void setRepaymentRate(BigDecimal repaymentRate) {
		this.repaymentRate = repaymentRate;
	}

	public BigDecimal getManagementRate() {
		return managementRate;
	}

	public void setManagementRate(BigDecimal managementRate) {
		this.managementRate = managementRate;
	}

	public Integer getRepaymentType() {
		return repaymentType;
	}

	public void setRepaymentType(Integer repaymentType) {
		this.repaymentType = repaymentType;
	}

	public BigDecimal getRepaymentAmt() {
		return repaymentAmt;
	}

	public void setRepaymentAmt(BigDecimal repaymentAmt) {
		this.repaymentAmt = repaymentAmt;
	}

}
