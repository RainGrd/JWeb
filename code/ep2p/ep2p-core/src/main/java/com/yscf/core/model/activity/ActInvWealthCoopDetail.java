package com.yscf.core.model.activity;

import java.math.BigDecimal;
import java.util.Date;

import com.achievo.framework.dto.BaseDto;
import com.achievo.framework.entity.BaseEntity;

/**
 * 
 * Description：<br>
 * 财富合伙人 参与明细 实体
 * 
 * @author fengshiliang
 * @date 2015年10月22日
 * @version v1.0.0
 */
public class ActInvWealthCoopDetail extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8247762254966000922L;

	private String pid;

	private String activityId;

	private String wealthCoopId;

	private String smsId;

	private String customerId;
	
	private BigDecimal bonusAmount;

	private String getTime;

	private String useTime;

	private String useStatus;

	private String status;

	private int getAmount;

	private String createUser;

	private String createTime;

	private String lastUpdateUser;

	private Date lastUpdateTime;

	private String version;
	private String wealthCoopValue;// 奖励值
	private String invAwaDesc;
	private String validityPeriod;// 有效期
	private String invalidTime;// 过期时间
	private String actCode;// 奖励编号

	private String actName;// 奖励名称

	private String actTag;// 奖励标签

	private String actStatus;// 奖励类型

	private String participantsNumber;// 产于人数
	private String customerName;// 用户名
	private String custName;// 姓名
	private String phoneNo;// 电话号码
	private String investAwardType;// 备注
	
	private String investAwardTypeName;
	private String wealthCoopValueName;// 奖励值

	private String startTime;
	private String endTime;
	
	

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

	public String getWealthCoopId() {
		return wealthCoopId;
	}

	public void setWealthCoopId(String wealthCoopId) {
		this.wealthCoopId = wealthCoopId == null ? null : wealthCoopId.trim();
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

	public BigDecimal getBonusAmount() {
		return bonusAmount;
	}

	public void setBonusAmount(BigDecimal bonusAmount) {
		this.bonusAmount = bonusAmount;
	}

	public String getGetTime() {
		return getTime;
	}

	public void setGetTime(String getTime) {
		this.getTime = getTime;
	}

	public String getUseTime() {
		return useTime;
	}

	public void setUseTime(String useTime) {
		this.useTime = useTime;
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
		this.lastUpdateUser = lastUpdateUser == null ? null : lastUpdateUser
				.trim();
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version == null ? null : version.trim();
	}

	public String getWealthCoopValue() {
		return wealthCoopValue;
	}

	public void setWealthCoopValue(String wealthCoopValue) {
		this.wealthCoopValue = wealthCoopValue;
	}

	public String getInvAwaDesc() {
		return invAwaDesc;
	}

	public void setInvAwaDesc(String invAwaDesc) {
		this.invAwaDesc = invAwaDesc;
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

	public int getGetAmount() {
		return getAmount;
	}

	public void setGetAmount(int getAmount) {
		this.getAmount = getAmount;
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
		ActInvWealthCoopDetail other = (ActInvWealthCoopDetail) that;
		return (this.getPid() == null ? other.getPid() == null : this.getPid()
				.equals(other.getPid()))
				&& (this.getActivityId() == null ? other.getActivityId() == null
						: this.getActivityId().equals(other.getActivityId()))
				&& (this.getWealthCoopId() == null ? other.getWealthCoopId() == null
						: this.getWealthCoopId()
								.equals(other.getWealthCoopId()))
				&& (this.getSmsId() == null ? other.getSmsId() == null : this
						.getSmsId().equals(other.getSmsId()))
				&& (this.getCustomerId() == null ? other.getCustomerId() == null
						: this.getCustomerId().equals(other.getCustomerId()))
				&& (this.getGetTime() == null ? other.getGetTime() == null
						: this.getGetTime().equals(other.getGetTime()))
				&& (this.getUseTime() == null ? other.getUseTime() == null
						: this.getUseTime().equals(other.getUseTime()))
				&& (this.getUseStatus() == null ? other.getUseStatus() == null
						: this.getUseStatus().equals(other.getUseStatus()))
				&& (this.getStatus() == null ? other.getStatus() == null : this
						.getStatus().equals(other.getStatus()))
				&& (this.getCreateUser() == null ? other.getCreateUser() == null
						: this.getCreateUser().equals(other.getCreateUser()))
				&& (this.getCreateTime() == null ? other.getCreateTime() == null
						: this.getCreateTime().equals(other.getCreateTime()))
				&& (this.getLastUpdateUser() == null ? other
						.getLastUpdateUser() == null : this.getLastUpdateUser()
						.equals(other.getLastUpdateUser()))
				&& (this.getLastUpdateTime() == null ? other
						.getLastUpdateTime() == null : this.getLastUpdateTime()
						.equals(other.getLastUpdateTime()))
				&& (this.getVersion() == null ? other.getVersion() == null
						: this.getVersion().equals(other.getVersion()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getPid() == null) ? 0 : getPid().hashCode());
		result = prime * result
				+ ((getActivityId() == null) ? 0 : getActivityId().hashCode());
		result = prime
				* result
				+ ((getWealthCoopId() == null) ? 0 : getWealthCoopId()
						.hashCode());
		result = prime * result
				+ ((getSmsId() == null) ? 0 : getSmsId().hashCode());
		result = prime * result
				+ ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
		result = prime * result
				+ ((getGetTime() == null) ? 0 : getGetTime().hashCode());
		result = prime * result
				+ ((getUseTime() == null) ? 0 : getUseTime().hashCode());
		result = prime * result
				+ ((getUseStatus() == null) ? 0 : getUseStatus().hashCode());
		result = prime * result
				+ ((getStatus() == null) ? 0 : getStatus().hashCode());
		result = prime * result
				+ ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
		result = prime * result
				+ ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		result = prime
				* result
				+ ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser()
						.hashCode());
		result = prime
				* result
				+ ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime()
						.hashCode());
		result = prime * result
				+ ((getVersion() == null) ? 0 : getVersion().hashCode());
		return result;
	}

	@Override
	public String toString() {
		return BaseDto.object2Json(this);
	}
	
	/**
	 * 格式化奖励值
	 * @param value
	 * @param row
	 * @param index
	 * @returns {String}
	 */
	public String formatValue(String value){
		if("1".equals(value)){
			return this.wealthCoopValue;
		}else if("2".equals(value)){
			return this.wealthCoopValue+"%";
		} 
		return null;
	}
	/**
	 * 格式化奖励方式
	 * @param value
	 * @param row
	 * @param index
	 * @returns {String}
	 */
	public String formatType(String type){
		if("1".equals(type)){
			return "固定金额奖励";
		}else if("2".equals(type)){
			return "百分比奖励";
		} 
		return null;
	}

	public String getInvestAwardTypeName() {
		return formatType(this.investAwardType);
	}

	public void setInvestAwardTypeName(String investAwardTypeName) {
		this.investAwardTypeName = investAwardTypeName;
	}

	public String getWealthCoopValueName() {
		return formatValue(this.investAwardType);
	}

	public void setWealthCoopValueName(String wealthCoopValueName) {
		this.wealthCoopValueName = wealthCoopValueName;
	}
	
	

}