/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 待还款详情VO
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2016年1月25日     Jie.Zou		Initial Version.
 *************************************************************************
 */
package com.yscf.core.model.business;

import java.math.BigDecimal;

import org.springframework.util.StringUtils;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br> 
 * 待还款详情VO
 * @author  Jie.Zou
 * @date    2016年1月25日
 * @version v1.0.0
 */
public class BizRepaymentPlanInfoVo extends BaseEntity {
	private static final long serialVersionUID = -7810119617717865213L;

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
	
	/**
	 * 已还本金
	 */
	private BigDecimal repaidCapital;
	
	/**
	 * 已还利息
	 */
	private BigDecimal repaidInterest;
	
	/**
	 * 提前还款少支付利息
	 */
	private BigDecimal advanceRepayInterest;
	
	/**
	 * 已还管理费
	 */
	private BigDecimal repaidManagementCost;
	
	/**
	 * 提前还款少支付管理费
	 */
	private BigDecimal advanceRepayManagementCost;
	
	/**
	 * 已还罚息
	 */
	private BigDecimal repaidPenalty;
	
	/**
	 * 提前还款罚息
	 */
	private BigDecimal prepaymentFee;
	
	/**
	 * 实际还款时间
	 */
	private String repaidTime;
	
	public String getBorrowCode() {
		if(StringUtils.hasText(borrowCode)){
			return borrowCode;
		}
		return "";
	}

	public void setBorrowCode(String borrowCode) {
		if(StringUtils.hasText(borrowCode)){
			this.borrowCode = borrowCode;
		}else{
			this.borrowCode = "";
		}
	}

	public String getBorrowName() {
		if(StringUtils.hasText(borrowName)){
			return borrowName; 
		}
		return "";
	}

	public void setBorrowName(String borrowName) {
		if(StringUtils.hasText(borrowName)){
			this.borrowName = borrowName;
		}else{
			this.borrowName = "";
		}
	}

	public BigDecimal getBorrowMoney() {
		if( null != borrowMoney){
			return borrowMoney;
		}
		return BigDecimal.ZERO;
	}

	public void setBorrowMoney(BigDecimal borrowMoney) {
		if(null != borrowMoney){
			this.borrowMoney = borrowMoney;
		}else{
			this.borrowMoney = BigDecimal.ZERO;
		}
	}

	public BigDecimal getBorrowRate() {
		if(null != borrowRate){
			return borrowRate;
		}
		return BigDecimal.ZERO;
	}

	public void setBorrowRate(BigDecimal borrowRate) {
		if(null != borrowRate){
			this.borrowRate = borrowRate;
		}else{
			this.borrowRate = BigDecimal.ZERO;
		}
	}

	public BigDecimal getManageExpenseRate() {
		if( null != manageExpenseRate){
			return manageExpenseRate;
		}
		return BigDecimal.ZERO;
	}

	public void setManageExpenseRate(BigDecimal manageExpenseRate) {
		if(null != manageExpenseRate){
			this.manageExpenseRate = manageExpenseRate;
		}else{
			this.manageExpenseRate = BigDecimal.ZERO;
		}
	}

	public String getDeadline() {
		if(StringUtils.hasText(deadline)){
			return deadline;
		}
		return "";
	}

	public void setDeadline(String deadline) {
		if(StringUtils.hasText(deadline)){
			this.deadline = deadline;
		}else{
			this.deadline = "";
		}
	}

	public String getRepaymentType() {
		if(StringUtils.hasText(repaymentType)){
			return repaymentType;
		}
		return "";
	}

	public void setRepaymentType(String repaymentType) {
		if(StringUtils.hasText(repaymentType)){
			this.repaymentType = repaymentType;
		}else{
			this.repaymentType = "";
		}
	}

	public BigDecimal getWaitRepayMoney() {
		if(null != waitRepayMoney){
			return waitRepayMoney;
		}
		return BigDecimal.ZERO;
	}

	public void setWaitRepayMoney(BigDecimal waitRepayMoney) {
		if(null != waitRepayMoney){
			this.waitRepayMoney = waitRepayMoney;
		}else{
			this.waitRepayMoney = BigDecimal.ZERO;
		}
	}

	public BigDecimal getCapital() {
		if(null != capital){
			return capital;
		}
		return BigDecimal.ZERO;
	}

	public void setCapital(BigDecimal capital) {
		if(null != capital){
			this.capital = capital;
		}else{
			this.capital = BigDecimal.ZERO;
		}
	}

	public BigDecimal getInterest() {
		if(null != interest){
			return interest;
		}
		return BigDecimal.ZERO;
	}

	public void setInterest(BigDecimal interest) {
		if(null != interest){
			this.interest = interest;
		}else{
			this.interest = BigDecimal.ZERO;
		}
	}

	public BigDecimal getManagementCost() {
		if(null != managementCost){
			return managementCost;
		}
		return BigDecimal.ZERO;
	}

	public void setManagementCost(BigDecimal managementCost) {
		if(null != managementCost){
			this.managementCost = managementCost;
		}else{
			this.managementCost = BigDecimal.ZERO;
		}
	}

	public BigDecimal getLatefee() {
		if(null != latefee){
			return latefee;
		}
		return BigDecimal.ZERO;
	}

	public void setLatefee(BigDecimal latefee) {
		if(null != latefee){
			this.latefee = latefee;
		}else{
			this.latefee = BigDecimal.ZERO;
		}
	}

	public String getWaitRepayTime() {
		if(StringUtils.hasText(waitRepayTime)){
			return waitRepayTime;
		}
		return "";
	}

	public void setWaitRepayTime(String waitRepayTime) {
		if(StringUtils.hasText(waitRepayTime)){
			this.waitRepayTime = waitRepayTime;
		}else{
			this.waitRepayTime = "";
		}
	}

	public String getCurrentPlanindex() {
		if(StringUtils.hasText(currentPlanindex)){
			return currentPlanindex;
		}
		return "";
	}

	public void setCurrentPlanindex(String currentPlanindex) {
		if(StringUtils.hasText(currentPlanindex)){
			this.currentPlanindex = currentPlanindex;
		}else{
			this.currentPlanindex = "";
		}
	}

	public String getMaxPlanindex() {
		if(StringUtils.hasText(maxPlanindex)){
			return maxPlanindex;
		}
		return "";
	}

	public void setMaxPlanindex(String maxPlanindex) {
		if(StringUtils.hasText(maxPlanindex)){
			this.maxPlanindex = maxPlanindex;
		}else{
			this.maxPlanindex = "";
		}
	}

	public BigDecimal getRepaidCapital() {
		if(null != repaidCapital){
			return repaidCapital;
		}
		return BigDecimal.ZERO;
	}

	public void setRepaidCapital(BigDecimal repaidCapital) {
		if(null != repaidCapital){
			this.repaidCapital = repaidCapital;
		}else{
			this.repaidCapital = BigDecimal.ZERO;
		}
	}

	public BigDecimal getRepaidInterest() {
		if(null != repaidInterest){
			return repaidInterest;
		}
		return BigDecimal.ZERO;
	}

	public void setRepaidInterest(BigDecimal repaidInterest) {
		if(null != repaidInterest){
			this.repaidInterest = repaidInterest;
		}else{
			this.repaidInterest = BigDecimal.ZERO;
		}
	}

	public BigDecimal getAdvanceRepayInterest() {
		if(null != advanceRepayInterest){
			return advanceRepayInterest;
		}
		return BigDecimal.ZERO;
	}

	public void setAdvanceRepayInterest(BigDecimal advanceRepayInterest) {
		if(null != advanceRepayInterest){
			this.advanceRepayInterest = advanceRepayInterest;
		}else{
			this.advanceRepayInterest = BigDecimal.ZERO;
		}
	}

	public BigDecimal getRepaidManagementCost() {
		if(null != repaidManagementCost){
			return repaidManagementCost;
		}
		return BigDecimal.ZERO;
	}

	public void setRepaidManagementCost(BigDecimal repaidManagementCost) {
		if(null != repaidManagementCost){
			this.repaidManagementCost = repaidManagementCost;
		}else{
			this.repaidManagementCost = BigDecimal.ZERO;
		}
	}

	public BigDecimal getAdvanceRepayManagementCost() {
		if(null != advanceRepayManagementCost){
			return advanceRepayManagementCost;
		}
		return BigDecimal.ZERO;
	}

	public void setAdvanceRepayManagementCost(BigDecimal advanceRepayManagementCost) {
		if(null != advanceRepayManagementCost){
			this.advanceRepayManagementCost = advanceRepayManagementCost;
		}else{
			this.advanceRepayManagementCost = BigDecimal.ZERO;
		}
	}

	public BigDecimal getRepaidPenalty() {
		if(null != repaidPenalty){
			return repaidPenalty;
		}
		return BigDecimal.ZERO;
	}

	public void setRepaidPenalty(BigDecimal repaidPenalty) {
		if(null != repaidPenalty){
			this.repaidPenalty = repaidPenalty;
		}else{
			this.repaidPenalty = BigDecimal.ZERO;
		}
	}

	public BigDecimal getPrepaymentFee() {
		if(null != prepaymentFee){
			return prepaymentFee;
		}
		return BigDecimal.ZERO;
	}

	public void setPrepaymentFee(BigDecimal prepaymentFee) {
		if(null != prepaymentFee){
			this.prepaymentFee = prepaymentFee;
		}else{
			this.prepaymentFee = BigDecimal.ZERO;
		}
	}

	public String getRepaidTime() {
		return repaidTime;
	}

	public void setRepaidTime(String repaidTime) {
		this.repaidTime = repaidTime;
	}
	
}


