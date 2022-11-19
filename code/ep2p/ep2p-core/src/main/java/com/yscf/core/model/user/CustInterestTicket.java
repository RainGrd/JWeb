package com.yscf.core.model.user;

import java.math.BigDecimal;

import com.achievo.framework.entity.BaseEntity;

/**
 * 
 * @ClassName : CustInterestTicket
 * @Description : 客户加息劵javaBean
 * @Author : Qing.Cai
 * @String : 2016年1月8日 上午11:27:09
 */
public class CustInterestTicket extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String pid;

	private String customerId;

	private String name;

	private Double scale;

	private String startTime;

	private String endTime;

	private Integer validity;

	private String getType;

	private String useStatus;

	private String interestDesc;

	private String status;

	private String createUser;

	private String createTime;

	private String lastCreateUser;

	private String lastCreateTime;

	private String version;

	private Integer cardType;// 类型:1是加息劵 2是体验金

	private BigDecimal cardQuota;// 金额(体验金) 百分比 (加息劵)

	private Integer cardValidity;// 有效期

	private String cardDesc;// 描述

	private String actInvAwaActDetailId;// 活动明细ID

	public String getActInvAwaActDetailId() {
		return actInvAwaActDetailId;
	}

	public void setActInvAwaActDetailId(String actInvAwaActDetailId) {
		this.actInvAwaActDetailId = actInvAwaActDetailId;
	}

	public Integer getCardType() {
		return cardType;
	}

	public void setCardType(Integer cardType) {
		this.cardType = cardType;
	}

	public BigDecimal getCardQuota() {
		return cardQuota;
	}

	public void setCardQuota(BigDecimal cardQuota) {
		this.cardQuota = cardQuota;
	}

	public Integer getCardValidity() {
		return cardValidity;
	}

	public void setCardValidity(Integer cardValidity) {
		this.cardValidity = cardValidity;
	}

	public String getCardDesc() {
		return cardDesc;
	}

	public void setCardDesc(String cardDesc) {
		this.cardDesc = cardDesc;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid == null ? null : pid.trim();
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId == null ? null : customerId.trim();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name == null ? null : name.trim();
	}

	public Double getScale() {
		return scale;
	}

	public void setScale(Double scale) {
		this.scale = scale;
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

	public Integer getValidity() {
		return validity;
	}

	public void setValidity(Integer validity) {
		this.validity = validity;
	}

	public String getGetType() {
		return getType;
	}

	public void setGetType(String getType) {
		this.getType = getType == null ? null : getType.trim();
	}

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus == null ? null : useStatus.trim();
	}

	public String getInterestDesc() {
		return interestDesc;
	}

	public void setInterestDesc(String interestDesc) {
		this.interestDesc = interestDesc == null ? null : interestDesc.trim();
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
		CustInterestTicket other = (CustInterestTicket) that;
		return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid())) && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId())) && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName())) && (this.getScale() == null ? other.getScale() == null : this.getScale().equals(other.getScale())) && (this.getStartTime() == null ? other.getStartTime() == null : this.getStartTime().equals(other.getStartTime())) && (this.getEndTime() == null ? other.getEndTime() == null : this.getEndTime().equals(other.getEndTime())) && (this.getValidity() == null ? other.getValidity() == null : this.getValidity().equals(other.getValidity()))
				&& (this.getGetType() == null ? other.getGetType() == null : this.getGetType().equals(other.getGetType())) && (this.getUseStatus() == null ? other.getUseStatus() == null : this.getUseStatus().equals(other.getUseStatus())) && (this.getInterestDesc() == null ? other.getInterestDesc() == null : this.getInterestDesc().equals(other.getInterestDesc())) && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus())) && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser())) && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
				&& (this.getLastCreateUser() == null ? other.getLastCreateUser() == null : this.getLastCreateUser().equals(other.getLastCreateUser())) && (this.getLastCreateTime() == null ? other.getLastCreateTime() == null : this.getLastCreateTime().equals(other.getLastCreateTime())) && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
		result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
		result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
		result = prime * result + ((getScale() == null) ? 0 : getScale().hashCode());
		result = prime * result + ((getStartTime() == null) ? 0 : getStartTime().hashCode());
		result = prime * result + ((getEndTime() == null) ? 0 : getEndTime().hashCode());
		result = prime * result + ((getValidity() == null) ? 0 : getValidity().hashCode());
		result = prime * result + ((getGetType() == null) ? 0 : getGetType().hashCode());
		result = prime * result + ((getUseStatus() == null) ? 0 : getUseStatus().hashCode());
		result = prime * result + ((getInterestDesc() == null) ? 0 : getInterestDesc().hashCode());
		result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
		result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
		result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		result = prime * result + ((getLastCreateUser() == null) ? 0 : getLastCreateUser().hashCode());
		result = prime * result + ((getLastCreateTime() == null) ? 0 : getLastCreateTime().hashCode());
		result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
		return result;
	}
}