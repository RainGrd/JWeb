package com.yscf.core.model.ptp.financial;

import java.math.BigDecimal;

import com.achievo.framework.entity.BaseEntity;

/**
 * 
 * Description：<br>
 * 客户账户VO
 * 
 * @author JunJie.Liu
 * @date 2015年11月2日
 * @version v1.0.0
 */
public class CustomerAccountModel extends BaseEntity {

	private static final long serialVersionUID = 8534592184964537370L;

	private String customerName;

	private String sname;

	private String phoneNo;

	private String rechargeStartValue;

	private String rechargeEndValue;

	private BigDecimal recharge;

	private String withdrawStartValue;

	private String withdrawEndValue;

	private BigDecimal withdraw;

	private String dueStartValue;

	private String dueEndValue;

	private BigDecimal due;

	private String availableStartValue;

	private String availableEndValue;

	private BigDecimal available;

	private String freezeStartValue;

	private String freezeEndValue;

	private BigDecimal freeze;

	private String tenderStartValue;

	private String tenderEndValue;

	private BigDecimal tender;

	private String registrationStartValue;

	private String registrationEndValue;

	private BigDecimal experienceGold;
	
	private String registerTime;

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

	public String getRechargeStartValue() {
		return rechargeStartValue;
	}

	public void setRechargeStartValue(String rechargeStartValue) {
		this.rechargeStartValue = rechargeStartValue;
	}

	public String getRechargeEndValue() {
		return rechargeEndValue;
	}

	public void setRechargeEndValue(String rechargeEndValue) {
		this.rechargeEndValue = rechargeEndValue;
	}

	public String getWithdrawStartValue() {
		return withdrawStartValue;
	}

	public void setWithdrawStartValue(String withdrawStartValue) {
		this.withdrawStartValue = withdrawStartValue;
	}

	public String getWithdrawEndValue() {
		return withdrawEndValue;
	}

	public void setWithdrawEndValue(String withdrawEndValue) {
		this.withdrawEndValue = withdrawEndValue;
	}

	public String getDueStartValue() {
		return dueStartValue;
	}

	public void setDueStartValue(String dueStartValue) {
		this.dueStartValue = dueStartValue;
	}

	public String getDueEndValue() {
		return dueEndValue;
	}

	public void setDueEndValue(String dueEndValue) {
		this.dueEndValue = dueEndValue;
	}

	public String getAvailableStartValue() {
		return availableStartValue;
	}

	public void setAvailableStartValue(String availableStartValue) {
		this.availableStartValue = availableStartValue;
	}

	public String getAvailableEndValue() {
		return availableEndValue;
	}

	public void setAvailableEndValue(String availableEndValue) {
		this.availableEndValue = availableEndValue;
	}

	public String getFreezeStartValue() {
		return freezeStartValue;
	}

	public void setFreezeStartValue(String freezeStartValue) {
		this.freezeStartValue = freezeStartValue;
	}

	public String getFreezeEndValue() {
		return freezeEndValue;
	}

	public void setFreezeEndValue(String freezeEndValue) {
		this.freezeEndValue = freezeEndValue;
	}

	public String getTenderStartValue() {
		return tenderStartValue;
	}

	public void setTenderStartValue(String tenderStartValue) {
		this.tenderStartValue = tenderStartValue;
	}

	public String getTenderEndValue() {
		return tenderEndValue;
	}

	public void setTenderEndValue(String tenderEndValue) {
		this.tenderEndValue = tenderEndValue;
	}

	public String getRegistrationStartValue() {
		return registrationStartValue;
	}

	public void setRegistrationStartValue(String registrationStartValue) {
		this.registrationStartValue = registrationStartValue;
	}

	public String getRegistrationEndValue() {
		return registrationEndValue;
	}

	public void setRegistrationEndValue(String registrationEndValue) {
		this.registrationEndValue = registrationEndValue;
	}

	public BigDecimal getRecharge() {
		return recharge;
	}

	public void setRecharge(BigDecimal recharge) {
		this.recharge = recharge;
	}

	public BigDecimal getWithdraw() {
		return withdraw;
	}

	public void setWithdraw(BigDecimal withdraw) {
		this.withdraw = withdraw;
	}

	public BigDecimal getDue() {
		return due;
	}

	public void setDue(BigDecimal due) {
		this.due = due;
	}

	public BigDecimal getAvailable() {
		return available;
	}

	public void setAvailable(BigDecimal available) {
		this.available = available;
	}

	public BigDecimal getFreeze() {
		return freeze;
	}

	public void setFreeze(BigDecimal freeze) {
		this.freeze = freeze;
	}

	public BigDecimal getTender() {
		return tender;
	}

	public void setTender(BigDecimal tender) {
		this.tender = tender;
	}

	public BigDecimal getExperienceGold() {
		return experienceGold;
	}

	public void setExperienceGold(BigDecimal experienceGold) {
		this.experienceGold = experienceGold;
	}

	public String getRegisterTime() {
		return registerTime;
	}

	public void setRegisterTime(String registerTime) {
		this.registerTime = registerTime;
	}

	
}