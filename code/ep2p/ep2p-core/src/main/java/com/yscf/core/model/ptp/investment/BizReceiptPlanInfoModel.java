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
 * 2015年12月22日     JunJie.Liu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.model.ptp.investment;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.util.StringUtils;

import com.yscf.common.Constant.Constant;

/**
 * Description：<br>
 * 投资管理明细
 * 
 * @author JunJie.Liu
 * @date 2015年12月22日
 * @version v1.0.0
 */
public class BizReceiptPlanInfoModel {

	private String pid;

	private String borrowId;

	private String customerId;

	private String borrowCode;

	private String borrowName;

	private String customerName;

	private String phoneNo;

	private String planIndex;

	private String borDeadline;

	private Date expireTime;

	private String repaymentType;
	
	private String repaymentTypeName;

	private BigDecimal benXi;

	private BigDecimal capital;

	private BigDecimal liXi;
	
	private BigDecimal jiaXi;

	private BigDecimal lateFee;

	private String lateDay;

	private String transferCode;

	private String transferId;
	
	private String recPlaDesc;
	
	private String receiptStatus;
	
	private String receiptStatusName;
	
	private String startExpireTime;
	
	private String endExpireTime;
	
	private String startExpireTime2;
	
	private String endExpireTime2;
	
	private String des;
	
	private String des2;
	
	private String issue;
	
	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(String borrowId) {
		this.borrowId = borrowId;
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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPlanIndex() {
		return planIndex;
	}

	public void setPlanIndex(String planIndex) {
		this.planIndex = planIndex;
	}

	public String getBorDeadline() {
		return borDeadline;
	}

	public void setBorDeadline(String borDeadline) {
		this.borDeadline = borDeadline;
	}

	public Date getExpireTime() {
		return expireTime;
	}

	public void setExpireTime(Date expireTime) {
		this.expireTime = expireTime;
	}

	public String getRepaymentType() {
		return repaymentType;
	}

	public void setRepaymentType(String repaymentType) {
		this.repaymentType = repaymentType;
	}

	public BigDecimal getBenXi() {
		return benXi;
	}

	public void setBenXi(BigDecimal benXi) {
		this.benXi = benXi;
	}

	public BigDecimal getCapital() {
		return capital;
	}

	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}

	public BigDecimal getLiXi() {
		return liXi;
	}

	public void setLiXi(BigDecimal liXi) {
		this.liXi = liXi;
	}

	public BigDecimal getLateFee() {
		return lateFee;
	}

	public void setLateFee(BigDecimal lateFee) {
		this.lateFee = lateFee;
	}

	public String getLateDay() {
		return lateDay;
	}

	public void setLateDay(String lateDay) {
		this.lateDay = lateDay;
	}

	public String getTransferCode() {
		return transferCode;
	}

	public void setTransferCode(String transferCode) {
		this.transferCode = transferCode;
	}

	public String getTransferId() {
		return transferId;
	}

	public void setTransferId(String transferId) {
		this.transferId = transferId;
	}

	public String getRepaymentTypeName() {
		return repaymentTypeName;
	}

	public void setRepaymentTypeName(String repaymentTypeName) {
		this.repaymentTypeName = repaymentTypeName;
	}

	public String getRecPlaDesc() {
		return recPlaDesc;
	}

	public void setRecPlaDesc(String recPlaDesc) {
		this.recPlaDesc = recPlaDesc;
	}

	public String getReceiptStatus() {
		return receiptStatus;
	}

	public void setReceiptStatus(String receiptStatus) {
		this.receiptStatus = receiptStatus;
	}

	public String getReceiptStatusName() {
		return receiptStatusName;
	}

	public void setReceiptStatusName(String receiptStatusName) {
		this.receiptStatusName = receiptStatusName;
	}

	public String getStartExpireTime() {
		return startExpireTime;
	}

	public void setStartExpireTime(String startExpireTime) {
		this.startExpireTime = startExpireTime;
	}

	public String getEndExpireTime() {
		return endExpireTime;
	}

	public void setEndExpireTime(String endExpireTime) {
		this.endExpireTime = endExpireTime;
	}

	public String getStartExpireTime2() {
		return startExpireTime2;
	}

	public void setStartExpireTime2(String startExpireTime2) {
		this.startExpireTime2 = startExpireTime2;
	}

	public String getEndExpireTime2() {
		return endExpireTime2;
	}

	public void setEndExpireTime2(String endExpireTime2) {
		this.endExpireTime2 = endExpireTime2;
	}

	public String getDes() {
		// 1 待收中、2 已逾期、3 转让中、4 已转让、5 已垫付、6 已结清
		this.des = this.recPlaDesc;
		if(this.des!=null && !"".equals(this.des)){
			this.des += ",";
		}else{
			this.des = "";
		}
		
		this.capital = this.capital == null ? BigDecimal.ZERO : this.capital;
		this.liXi = this.liXi == null ? BigDecimal.ZERO : this.liXi;
		this.jiaXi = this.jiaXi == null ? BigDecimal.ZERO : this.jiaXi;
		
		this.capital = this.capital.setScale(2, BigDecimal.ROUND_DOWN);
		this.liXi = this.liXi.setScale(2, BigDecimal.ROUND_DOWN);
		this.jiaXi = this.jiaXi.setScale(2, BigDecimal.ROUND_DOWN);
		
		String des1 = "本金："+this.capital+" ，利息：" + this.liXi;
		if(jiaXi!=null && jiaXi.compareTo(BigDecimal.ZERO)>0){
			des1 = des1 + "，加息收益："+this.jiaXi;
		}
		
		
		if(Constant.BIZ_RECEIPTPLAN_STATUS_1.equals(this.receiptStatus)){
			
			this.des += des1;
			
		}else if(Constant.BIZ_RECEIPTPLAN_STATUS_2.equals(this.receiptStatus)){
			
			this.lateFee = this.lateFee == null ? BigDecimal.ZERO : this.lateFee;
			this.lateFee = this.lateFee.setScale(2, BigDecimal.ROUND_DOWN);
			
			this.des += des1;
			
			this.des += "<br>逾期赔付：" + this.lateFee + " ， 逾期："+this.lateDay + "天";
			
		}else if(Constant.BIZ_RECEIPTPLAN_STATUS_3.equals(this.receiptStatus)){
			
			this.des += des1;
			
		}else if(Constant.BIZ_RECEIPTPLAN_STATUS_4.equals(this.receiptStatus)){
			
			this.des += des1;
			
		}else if(Constant.BIZ_RECEIPTPLAN_STATUS_5.equals(this.receiptStatus)){
			
			this.lateFee = this.lateFee == null ? BigDecimal.ZERO : this.lateFee;
			this.lateFee = this.lateFee.setScale(2, BigDecimal.ROUND_DOWN);
			
			this.des += des1;
			
			this.des += "<br>逾期赔付：" + this.lateFee + " ， 逾期："+this.lateDay + "天";
			
		}else if(Constant.BIZ_RECEIPTPLAN_STATUS_6.equals(this.receiptStatus)){
			
			this.des += des1;
			
		}
		return des;
	}

	public void setDes(String des) {
		this.des = des;
	}

	public String getDes2() {
		this.des2 = "";
		if(Constant.BIZ_RECEIPTPLAN_STATUS_3.equals(this.receiptStatus)){
			this.des2 = "债权编号："+this.transferCode+"，";
			this.des2 += getDes();			
		}else if(Constant.BIZ_RECEIPTPLAN_STATUS_4.equals(this.receiptStatus)){
			this.des2 = "债权编号："+this.transferCode+"，";
			this.des2 += getDes();	
		}
		return des2;
	}

	public void setDes2(String des2) {
		this.des2 = des2;
	}

	public String getIssue() {
		if(!StringUtils.hasLength(this.getPlanIndex()) && !StringUtils.hasLength(this.getBorDeadline())){
			return "";
		}
		this.issue = this.getPlanIndex() + "/" + this.borDeadline;
		return issue;
	}

	public void setIssue(String issue) {
		this.issue = issue;
	}

	public BigDecimal getJiaXi() {
		return jiaXi;
	}

	public void setJiaXi(BigDecimal jiaXi) {
		this.jiaXi = jiaXi;
	}
	
	
	
	
	

	
}
