package com.yscf.core.model.activity;

import java.math.BigDecimal;


/**
 * 
 * @ClassName : PubInvestAward
 * @Description : 投资奖励信息表javaBean
 * @Author : Qing.Cai
 * @String : 2015年10月14日 上午11:05:55
 */
public class PubInvestAward extends ActivityDto {

	private static final long serialVersionUID = 1L;

	private String pid;

	private String investAwardName;//投资奖励名称

	private BigDecimal investAwardValue;//投资奖励值

	private String investAwardType;//奖励类型（1 积分 2经验 3加息劵）

	private String isBirPrivilege;//是否生日特权

	private String startTime;//适用开始时间

	private String endTime;//适用结束时间

	private String invAwaDesc;//描述

	private String status;//状态

	private String createUser;

	private String createTime;

	private String lastCreateUser;

	private String lastCreateTime;

	private String version;
	
	private Integer validity;
	
	private String expAmount;//体验金额
	
	private String effTime;//失效时间
	
	private String type;//类型 1：加息券 2：体验金
	
	private Integer interest;// 加息卷总数
	
	private String amount;//金额
	
	private String couponType;//赠劵类型
	
	private	String dataTime; //时长
	
	private String useStatus;//使用状态
	
	private String linkType;//链接类型  1：e计划，2：e计划/e首房
	
	
	public String getLinkType() {
		return linkType;
	}

	public void setLinkType(String linkType) {
		this.linkType = linkType;
	}

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	public Integer getInterest() {
		return interest;
	}

	public void setInterest(Integer interest) {
		this.interest = interest;
	}
	
	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getCouponType() {
		return couponType;
	}

	public void setCouponType(String couponType) {
		this.couponType = couponType;
	}

	public String getDataTime() {
		return dataTime;
	}

	public void setDataTime(String dataTime) {
		this.dataTime = dataTime;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getExpAmount() {
		return expAmount;
	}

	public void setExpAmount(String expAmount) {
		this.expAmount = expAmount;
	}

	public String getEffTime() {
		return effTime;
	}

	public void setEffTime(String effTime) {
		this.effTime = effTime;
	}

	public Integer getValidity() {
		return validity;
	}

	public void setValidity(Integer validity) {
		this.validity = validity;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid == null ? null : pid.trim();
	}

	public String getInvestAwardName() {
		return investAwardName;
	}

	public void setInvestAwardName(String investAwardName) {
		this.investAwardName = investAwardName == null ? null : investAwardName.trim();
	}

	public BigDecimal getInvestAwardValue() {
		return investAwardValue;
	}

	public void setInvestAwardValue(BigDecimal investAwardValue) {
		this.investAwardValue = investAwardValue;
	}

	public String getInvestAwardType() {
		return investAwardType;
	}

	public void setInvestAwardType(String investAwardType) {
		this.investAwardType = investAwardType == null ? null : investAwardType.trim();
	}

	public String getIsBirPrivilege() {
		return isBirPrivilege;
	}

	public void setIsBirPrivilege(String isBirPrivilege) {
		this.isBirPrivilege = isBirPrivilege == null ? null : isBirPrivilege.trim();
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getInvAwaDesc() {
		return invAwaDesc;
	}

	public void setInvAwaDesc(String invAwaDesc) {
		this.invAwaDesc = invAwaDesc == null ? null : invAwaDesc.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser == null ? null : createUser.trim();
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastCreateUser() {
		return lastCreateUser;
	}

	public void setLastCreateUser(String lastCreateUser) {
		this.lastCreateUser = lastCreateUser == null ? null : lastCreateUser.trim();
	}

	public String getLastCreateTime() {
		return lastCreateTime;
	}

	public void setLastCreateTime(String lastCreateTime) {
		this.lastCreateTime = lastCreateTime;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version == null ? null : version.trim();
	}

	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		PubInvestAward other = (PubInvestAward) that;
		return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid())) && (this.getInvestAwardName() == null ? other.getInvestAwardName() == null : this.getInvestAwardName().equals(other.getInvestAwardName())) && (this.getInvestAwardValue() == null ? other.getInvestAwardValue() == null : this.getInvestAwardValue().equals(other.getInvestAwardValue())) && (this.getInvestAwardType() == null ? other.getInvestAwardType() == null : this.getInvestAwardType().equals(other.getInvestAwardType())) && (this.getIsBirPrivilege() == null ? other.getIsBirPrivilege() == null : this.getIsBirPrivilege().equals(other.getIsBirPrivilege())) && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime()))
				&& (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime())) && (this.getInvAwaDesc() == null ? other.getInvAwaDesc() == null : this.getInvAwaDesc().equals(other.getInvAwaDesc())) && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus())) && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser())) && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime())) && (this.getLastCreateUser() == null ? other.getLastCreateUser() == null : this.getLastCreateUser().equals(other.getLastCreateUser()))
				&& (this.getLastCreateTime() == null ? other.getLastCreateTime() == null : this.getLastCreateTime().equals(other.getLastCreateTime())) && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
		result = prime * result + ((getInvestAwardName() == null) ? 0 : getInvestAwardName().hashCode());
		result = prime * result + ((getInvestAwardValue() == null) ? 0 : getInvestAwardValue().hashCode());
		result = prime * result + ((getInvestAwardType() == null) ? 0 : getInvestAwardType().hashCode());
		result = prime * result + ((getIsBirPrivilege() == null) ? 0 : getIsBirPrivilege().hashCode());
		result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
		result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
		result = prime * result + ((getInvAwaDesc() == null) ? 0 : getInvAwaDesc().hashCode());
		result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
		result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
		result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		result = prime * result + ((getLastCreateUser() == null) ? 0 : getLastCreateUser().hashCode());
		result = prime * result + ((getLastCreateTime() == null) ? 0 : getLastCreateTime().hashCode());
		result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
		return result;
	}
}