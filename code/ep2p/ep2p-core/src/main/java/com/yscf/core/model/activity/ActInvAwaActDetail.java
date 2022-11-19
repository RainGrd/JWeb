package com.yscf.core.model.activity;

import java.math.BigDecimal;

import com.achievo.framework.dto.BaseDto;
import com.achievo.framework.entity.BaseEntity;

public class ActInvAwaActDetail extends BaseEntity {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2831880980763508818L;

	private String pid;

	private String activityId;

	private String investAwardId;

	private String smsId;

	private String customerId;

	private String getTime;

	private String useTime;

	private String useStatus;

	private String status;

	private String createUser;

	private String createTime;

	private String lastUpdateUser;

	private String lastUpdateTime;

	private String version;
	private BigDecimal investAwardValue;// 奖励值
	private String invAwaDesc;
	private String validityPeriod;// 有效期
	private String invalidTime;// 过期时间
	private String actCode;// 奖励编号

	private String actName;// 奖励名称

	private String actTag;// 奖励标签

	private String actStatus;// 奖励类型

	private String participantsNumber;// 参与人数
	private String customerName;// 用户名
	private String custName;// 姓名
	private String phoneNo;// 电话号码
	private String investAwardType;// 备注
	private String startTime;
	private String endTime;

	private String formatAwardTypeName;
	
	private Integer interest;// 加息劵总数
	// 头像URL
	private String imageUrl;

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	
	public Integer getInterest() {
		return interest;
	}

	public void setInterest(Integer interest) {
		this.interest = interest;
	}

	public String getUseTime() {
		return useTime;
	}

	public void setUseTime(String useTime) {
		this.useTime = useTime;
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

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getInvestAwardType() {
		return investAwardType;
	}

	public void setInvestAwardType(String investAwardType) {
		this.investAwardType = investAwardType;
	}

	public String getValidityPeriod() {
		return validityPeriod;
	}

	public void setValidityPeriod(String validityPeriod) {
		this.validityPeriod = validityPeriod;
	}

	public String getInvalidTime() {
		return invalidTime;
	}

	public void setInvalidTime(String invalidTime) {
		this.invalidTime = invalidTime;
	}

	public BigDecimal getInvestAwardValue() {
		return investAwardValue;
	}

	public void setInvestAwardValue(BigDecimal investAwardValue) {
		this.investAwardValue = investAwardValue;
	}

	public String getInvAwaDesc() {
		return invAwaDesc;
	}

	public void setInvAwaDesc(String invAwaDesc) {
		this.invAwaDesc = invAwaDesc;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid == null ? null : pid.trim();
	}

	public String getActivityId() {
		return activityId;
	}

	public void setActivityId(String activityId) {
		this.activityId = activityId == null ? null : activityId.trim();
	}

	public String getInvestAwardId() {
		return investAwardId;
	}

	public void setInvestAwardId(String investAwardId) {
		this.investAwardId = investAwardId == null ? null : investAwardId.trim();
	}

	public String getSmsId() {
		return smsId;
	}

	public void setSmsId(String smsId) {
		this.smsId = smsId == null ? null : smsId.trim();
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId == null ? null : customerId.trim();
	}

	public String getGetTime() {
		return getTime;
	}

	public void setGetTime(String getTime) {
		this.getTime = getTime;
	}

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus == null ? null : useStatus.trim();
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

	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser == null ? null : lastUpdateUser.trim();
	}

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version == null ? null : version.trim();
	}

	public String getActCode() {
		return actCode;
	}

	public void setActCode(String actCode) {
		this.actCode = actCode;
	}

	public String getActName() {
		return actName;
	}

	public void setActName(String actName) {
		this.actName = actName;
	}

	public String getActTag() {
		return actTag;
	}

	public void setActTag(String actTag) {
		this.actTag = actTag;
	}

	public String getActStatus() {
		return actStatus;
	}

	public void setActStatus(String actStatus) {
		this.actStatus = actStatus;
	}

	public String getParticipantsNumber() {
		return participantsNumber;
	}

	public void setParticipantsNumber(String participantsNumber) {
		this.participantsNumber = participantsNumber;
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
		ActInvAwaActDetail other = (ActInvAwaActDetail) that;
		return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid())) && (this.getActivityId() == null ? other.getActivityId() == null : this.getActivityId().equals(other.getActivityId())) && (this.getInvestAwardId() == null ? other.getInvestAwardId() == null : this.getInvestAwardId().equals(other.getInvestAwardId())) && (this.getSmsId() == null ? other.getSmsId() == null : this.getSmsId().equals(other.getSmsId())) && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId())) && (this.getGetTime() == null ? other.getGetTime() == null : this.getGetTime().equals(other.getGetTime())) && (this.getUseStatus() == null ? other.getUseStatus() == null : this.getUseStatus().equals(other.getUseStatus()))
				&& (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus())) && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser())) && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime())) && (this.getLastUpdateUser() == null ? other.getLastUpdateUser() == null : this.getLastUpdateUser().equals(other.getLastUpdateUser())) && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime())) && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
		result = prime * result + ((getActivityId() == null) ? 0 : getActivityId().hashCode());
		result = prime * result + ((getInvestAwardId() == null) ? 0 : getInvestAwardId().hashCode());
		result = prime * result + ((getSmsId() == null) ? 0 : getSmsId().hashCode());
		result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
		result = prime * result + ((getGetTime() == null) ? 0 : getGetTime().hashCode());
		result = prime * result + ((getUseStatus() == null) ? 0 : getUseStatus().hashCode());
		result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
		result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
		result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
		result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
		result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
		return result;
	}

	@Override
	public String toString() {
		return BaseDto.object2Json(this);
	}

	public String getFormatAwardTypeName() {
		this.formatAwardTypeName = getFormatAwardTypeName(this.investAwardType);
		return formatAwardTypeName;
	}

	private String getFormatAwardTypeName(String investAwardType) {
		if ("1".equals(investAwardType)) {
			return "积分";
		} else if ("2".equals(investAwardType)) {
			return "经验";
		} else if ("3".equals(investAwardType)) {
			return "加息";
		}
		return null;
	}

	public void setFormatAwardTypeName(String formatAwardTypeName) {
		this.formatAwardTypeName = formatAwardTypeName;
	}

}