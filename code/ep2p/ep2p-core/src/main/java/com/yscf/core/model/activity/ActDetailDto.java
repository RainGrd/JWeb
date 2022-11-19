package com.yscf.core.model.activity;

import java.math.BigDecimal;

import com.achievo.framework.entity.BaseEntity;

/**
 * 
 * @ClassName : ActDetailDto
 * @Description : 明细表DTOjavaBean
 * @Author : Qing.Cai
 * @Date : 2015年10月9日 下午5:44:20
 */
public class ActDetailDto extends BaseEntity {

	private static final long serialVersionUID = -4688711002504311252L;

	// 活动编号
	private String actCode;
	// 活动标签
	private String actTag;
	// 活动名称
	private String actName;
	// 活动状态
	private String actStatus;
	// 活动参与人数
	private Integer participantsNumber;
	// 用户名
	private String customerName;
	// 姓名
	private String custName;
	// VIP等级
	private String vipLevel;
	// 手机号码
	private String phoneNo;
	// 备注
	private String remark;
	// 获得金额
	private BigDecimal getAmount;
	// 使用金额
	private BigDecimal useAmount;
	// 有效期
	private Integer expNumber;
	// 红包总金额
	private BigDecimal bonusTotal;
	// 已领取总金额
	private BigDecimal hasReceiveTotal;
	// 未领取总金额
	private BigDecimal notReceiveTotal;

	public BigDecimal getBonusTotal() {
		return bonusTotal;
	}

	public void setBonusTotal(BigDecimal bonusTotal) {
		this.bonusTotal = bonusTotal;
	}

	public BigDecimal getHasReceiveTotal() {
		return hasReceiveTotal;
	}

	public void setHasReceiveTotal(BigDecimal hasReceiveTotal) {
		this.hasReceiveTotal = hasReceiveTotal;
	}

	public BigDecimal getNotReceiveTotal() {
		return notReceiveTotal;
	}

	public void setNotReceiveTotal(BigDecimal notReceiveTotal) {
		this.notReceiveTotal = notReceiveTotal;
	}

	public Integer getExpNumber() {
		return expNumber;
	}

	public void setExpNumber(Integer expNumber) {
		this.expNumber = expNumber;
	}

	public BigDecimal getGetAmount() {
		return getAmount;
	}

	public void setGetAmount(BigDecimal getAmount) {
		this.getAmount = getAmount;
	}

	public BigDecimal getUseAmount() {
		return useAmount;
	}

	public void setUseAmount(BigDecimal useAmount) {
		this.useAmount = useAmount;
	}

	public String getActCode() {
		return actCode;
	}

	public void setActCode(String actCode) {
		this.actCode = actCode;
	}

	public String getActTag() {
		return actTag;
	}

	public void setActTag(String actTag) {
		this.actTag = actTag;
	}

	public String getActName() {
		return actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}

	public String getActStatus() {
		return actStatus;
	}

	public void setActStatus(String actStatus) {
		this.actStatus = actStatus;
	}

	public Integer getParticipantsNumber() {
		return participantsNumber;
	}

	public void setParticipantsNumber(Integer participantsNumber) {
		this.participantsNumber = participantsNumber;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getVipLevel() {
		return vipLevel;
	}

	public void setVipLevel(String vipLevel) {
		this.vipLevel = vipLevel;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
