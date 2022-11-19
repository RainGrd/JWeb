package com.yscf.core.model.activity;

import java.math.BigDecimal;

import com.achievo.framework.dto.BaseDto;

/**
 *
 * Description：<br>
 * 财富合伙人活动信息表
 * 
 * @author fengshiliang
 * @date 2015年10月15日
 * @version v1.0.0
 */
public class PubWealthCooperation extends ActivityDto {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2196114433936582014L;

	private String pid;

	private String wealthCoopName;

	private BigDecimal wealthCoopValue;

	private String investAwardType;

	private String startTime;

	private String endTime;

	private String invAwaDesc;

	private String status;

	private String createUser;

	private String createTime;

	private String lastCreateUser;

	private String lastCreateTime;

	private String version;

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid == null ? null : pid.trim();
	}

	public String getWealthCoopName() {
		return wealthCoopName;
	}

	public void setWealthCoopName(String wealthCoopName) {
		this.wealthCoopName = wealthCoopName == null ? null : wealthCoopName
				.trim();
	}

	public BigDecimal getWealthCoopValue() {
		return wealthCoopValue;
	}

	public void setWealthCoopValue(BigDecimal wealthCoopValue) {
		this.wealthCoopValue = wealthCoopValue;
	}

	public String getInvestAwardType() {
		return investAwardType;
	}

	public void setInvestAwardType(String investAwardType) {
		this.investAwardType = investAwardType == null ? null : investAwardType
				.trim();
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
		this.lastCreateUser = lastCreateUser == null ? null : lastCreateUser
				.trim();
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
		PubWealthCooperation other = (PubWealthCooperation) that;
		return (this.getPid() == null ? other.getPid() == null : this.getPid()
				.equals(other.getPid()))
				&& (this.getWealthCoopName() == null ? other
						.getWealthCoopName() == null : this.getWealthCoopName()
						.equals(other.getWealthCoopName()))
				&& (this.getWealthCoopValue() == null ? other
						.getWealthCoopValue() == null : this
						.getWealthCoopValue()
						.equals(other.getWealthCoopValue()))
				&& (this.getInvestAwardType() == null ? other
						.getInvestAwardType() == null : this
						.getInvestAwardType()
						.equals(other.getInvestAwardType()))
				&& (this.getStartTime() == null ? other.getStartTime() == null
						: this.getStartTime().equals(other.getStartTime()))
				&& (this.getEndTime() == null ? other.getEndTime() == null
						: this.getEndTime().equals(other.getEndTime()))
				&& (this.getInvAwaDesc() == null ? other.getInvAwaDesc() == null
						: this.getInvAwaDesc().equals(other.getInvAwaDesc()))
				&& (this.getStatus() == null ? other.getStatus() == null : this
						.getStatus().equals(other.getStatus()))
				&& (this.getCreateUser() == null ? other.getCreateUser() == null
						: this.getCreateUser().equals(other.getCreateUser()))
				&& (this.getCreateTime() == null ? other.getCreateTime() == null
						: this.getCreateTime().equals(other.getCreateTime()))
				&& (this.getLastCreateUser() == null ? other
						.getLastCreateUser() == null : this.getLastCreateUser()
						.equals(other.getLastCreateUser()))
				&& (this.getLastCreateTime() == null ? other
						.getLastCreateTime() == null : this.getLastCreateTime()
						.equals(other.getLastCreateTime()))
				&& (this.getActCode() == null ? other.getActCode() == null
						: this.getActCode().equals(other.getActCode()))
				&& (this.getActTag() == null ? other.getActTag() == null : this
						.getActTag().equals(other.getActTag()))
				&& (this.getVersion() == null ? other.getVersion() == null
						: this.getVersion().equals(other.getVersion()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getPid() == null) ? 0 : getPid().hashCode());
		result = prime
				* result
				+ ((getWealthCoopName() == null) ? 0 : getWealthCoopName()
						.hashCode());
		result = prime
				* result
				+ ((getWealthCoopValue() == null) ? 0 : getWealthCoopValue()
						.hashCode());
		result = prime
				* result
				+ ((getInvestAwardType() == null) ? 0 : getInvestAwardType()
						.hashCode());
		result = prime * result
				+ ((getStartTime() == null) ? 0 : getStartTime().hashCode());
		result = prime * result
				+ ((getEndTime() == null) ? 0 : getEndTime().hashCode());
		result = prime * result
				+ ((getInvAwaDesc() == null) ? 0 : getInvAwaDesc().hashCode());
		result = prime * result
				+ ((getStatus() == null) ? 0 : getStatus().hashCode());
		result = prime * result
				+ ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
		result = prime * result
				+ ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		result = prime
				* result
				+ ((getLastCreateUser() == null) ? 0 : getLastCreateUser()
						.hashCode());
		result = prime
				* result
				+ ((getLastCreateTime() == null) ? 0 : getLastCreateTime()
						.hashCode());
		result = prime * result
				+ ((getVersion() == null) ? 0 : getVersion().hashCode());
		result = prime * result
				+ ((getActTag() == null) ? 0 : getActTag().hashCode());
		result = prime * result
				+ ((getActCode() == null) ? 0 : getActCode().hashCode());
		return result;
	}

	@Override
	public String toString() {
		return BaseDto.object2Json(this);
	}

}