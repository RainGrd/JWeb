/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 功能：待收明细信息模型
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2016年3月2日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.model.ptp.investment;

import java.math.BigDecimal;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br> 
 * 功能：待收明细信息模型
 * @author  Lin Xu
 * @date    2016年3月2日
 * @version v1.0.0
 */
public class DueInDetailModel extends BaseEntity {

	private static final long serialVersionUID = -5300165475822511289L;
	//主键借款信息主键
	private String pid;
	//用户ID
	private String customerId;
	//借款编码
	private String borrowCode;
	//借款编码转让的就是转让编码
	private String borrowOtherCode;
	//借款名称
	private String borrowName;
	//借款金额
	private BigDecimal borrowMoney;
	//借款利率
	private BigDecimal borrowRate;
	//借款期限
	private String borDeadline;
	//还款类型
	private String repaymentType;
	private String repaymentTypeName;
	//转让id
	private String transferId;
	//投资金额
	private BigDecimal intementMoney;
	//投资利息
	private BigDecimal receivableInterest;
	//投资奖励
	private BigDecimal awardAmount;
	//加息收益
	private BigDecimal receivableHike;
	//投资时间
	private String investmentTime;
	//已收金额
	private BigDecimal receivedMoney;
	//待收金额
	private BigDecimal dueinMoney;
	//已收期数
	private Integer receivedWek;
	/*债券转让状态 ：（>0 表示多少天后可转让，=0表示债券可以转让 ，<0表示不可装让（
	  -1 ==>a、原项目的没有处于逾期状态 
	  -2 ==>b、原项目成功还款一期及以上 
    -3 ==>c、原项目剩余还款期次2期及以上
    -4 ==>d、当前处于VIP有效期内
    -5 ==>e、不可是待收日当天及前两日（小于最近待收日前三天的可转）
    -6 ==>f、原标必须设定为允许债权转让。
	））
	*/
	private Integer tranferStatus;
	//合同id
	private String borAgrId;
	
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
	public String getBorrowOtherCode() {
		return borrowOtherCode;
	}
	public void setBorrowOtherCode(String borrowOtherCode) {
		this.borrowOtherCode = borrowOtherCode;
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
	public String getBorDeadline() {
		return borDeadline;
	}
	public void setBorDeadline(String borDeadline) {
		this.borDeadline = borDeadline;
	}
	public String getRepaymentType() {
		return repaymentType;
	}
	public void setRepaymentType(String repaymentType) {
		this.repaymentType = repaymentType;
	}
	public String getTransferId() {
		return transferId;
	}
	public void setTransferId(String transferId) {
		this.transferId = transferId;
	}
	public BigDecimal getIntementMoney() {
		return intementMoney;
	}
	public void setIntementMoney(BigDecimal intementMoney) {
		this.intementMoney = intementMoney;
	}
	public BigDecimal getReceivableInterest() {
		return receivableInterest;
	}
	public void setReceivableInterest(BigDecimal receivableInterest) {
		this.receivableInterest = receivableInterest;
	}
	public BigDecimal getAwardAmount() {
		return awardAmount;
	}
	public void setAwardAmount(BigDecimal awardAmount) {
		this.awardAmount = awardAmount;
	}
	public BigDecimal getReceivableHike() {
		return receivableHike;
	}
	public void setReceivableHike(BigDecimal receivableHike) {
		this.receivableHike = receivableHike;
	}
	public String getInvestmentTime() {
		return investmentTime;
	}
	public void setInvestmentTime(String investmentTime) {
		this.investmentTime = investmentTime;
	}
	public BigDecimal getReceivedMoney() {
		return receivedMoney;
	}
	public void setReceivedMoney(BigDecimal receivedMoney) {
		this.receivedMoney = receivedMoney;
	}
	public BigDecimal getDueinMoney() {
		return dueinMoney;
	}
	public void setDueinMoney(BigDecimal dueinMoney) {
		this.dueinMoney = dueinMoney;
	}
	public Integer getReceivedWek() {
		return receivedWek;
	}
	public void setReceivedWek(Integer receivedWek) {
		this.receivedWek = receivedWek;
	}
	public Integer getTranferStatus() {
		return tranferStatus;
	}
	public void setTranferStatus(Integer tranferStatus) {
		this.tranferStatus = tranferStatus;
	}
	public String getBorAgrId() {
		return borAgrId;
	}
	public void setBorAgrId(String borAgrId) {
		this.borAgrId = borAgrId;
	}
	public String getCustomerId() {
		return customerId;
	}
	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}
	public String getRepaymentTypeName() {
		return repaymentTypeName;
	}
	public void setRepaymentTypeName(String repaymentTypeName) {
		this.repaymentTypeName = repaymentTypeName;
	}
	
}


