package com.yscf.core.model.ptp.financial;

import java.math.BigDecimal;


public class CustFundWaterModel {

	private String customerName;

	private String sname;

	private String phoneNo;

	private String happenTime;

	private String fundType;

	private String fundTypeName;

	private BigDecimal income;

	private BigDecimal expend;

	private String businessType;

	private String businessTypeName;

	private BigDecimal accountBalance;

	private String funWatDesc;

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

	public String getHappenTime() {
		return happenTime;
	}

	public void setHappenTime(String happenTime) {
		this.happenTime = happenTime;
	}

	public String getFundType() {
		return fundType;
	}

	public void setFundType(String fundType) {
		this.fundType = fundType;
	}

	public String getFundTypeName() {
		return fundTypeName;
	}

	public void setFundTypeName(String fundTypeName) {
		this.fundTypeName = fundTypeName;
	}

	public BigDecimal getIncome() {
		return income;
	}

	public void setIncome(BigDecimal income) {
		this.income = income;
	}

	public BigDecimal getExpend() {
		return expend;
	}

	public void setExpend(BigDecimal expend) {
		this.expend = expend;
	}

	public String getBusinessType() {
		return businessType;
	}

	public void setBusinessType(String businessType) {
		this.businessType = businessType;
	}

	public String getBusinessTypeName() {
		return businessTypeName;
	}

	public void setBusinessTypeName(String businessTypeName) {
		this.businessTypeName = businessTypeName;
	}

	public BigDecimal getAccountBalance() {
		return accountBalance;
	}

	public void setAccountBalance(BigDecimal accountBalance) {
		this.accountBalance = accountBalance;
	}

	public String getFunWatDesc() {
		return funWatDesc;
	}

	public void setFunWatDesc(String funWatDesc) {
		this.funWatDesc = funWatDesc;
	}

	
	public String getFundNameByType(String fundType){
		
		if("1".equals(fundType)){
			return "线上充值";
		}
		else if("2".equals(fundType)){
			return "线下充值";
		}
		else if("3".equals(fundType)){
			return "提现";
		}
		else if("4".equals(fundType)){
			return "借款成功";
		}
		else if("5".equals(fundType)){
			return "投标成功";
		}
		else if("6".equals(fundType)){
			return "还款";
		}
		else if("7".equals(fundType)){
			return "收款";
		}
		else if("8".equals(fundType)){
			return "VIP付费";
		}
		else if("9".equals(fundType)){
			return "支付投标奖励";
		}else{
			return null;
		}
	}

	public BigDecimal  getIncome(String businessType,BigDecimal fundValue,BigDecimal totalIncome){
		if("1".equals(businessType)){
			return fundValue;
		}else if("999".equals(businessType)){
			return totalIncome;
		}else{
			return null;
		}
	}
	
	public BigDecimal  getExpend(String businessType,BigDecimal fundValue,BigDecimal totalPay){
		if("2".equals(businessType)){
			return fundValue;
		}else if("999".equals(businessType)){
			return totalPay;
		}else{
			return null;
		}
	}

}