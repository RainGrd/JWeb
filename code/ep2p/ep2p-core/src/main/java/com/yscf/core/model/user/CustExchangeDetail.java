package com.yscf.core.model.user;

import com.achievo.framework.entity.BaseEntity;

/**
 * 
 * @ClassName : CustExchangeDetail
 * @Description : 客户兑换明细javaBean
 * @Author : Qing.Cai
 * @String : 2016年1月13日 下午2:56:39
 */
public class CustExchangeDetail extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String pid;

	private String customerId;

	private String exchangeType;

	private String exchangeTime;

	private String exchangeStatus;

	private String exchangeRemark;

	private Integer exchangePoint;

	private String status;

	private String createUser;

	private String createTime;

	private String lastUpdateUser;

	private String lastUpdateTime;

	// 笔数
	private Integer pensNumber;
	// 笔数占比
	private String pensNumberProportion;
	// 总额
	private Double total;

	// 用户名
	private String customerName;
	// 姓名
	private String sname;
	// 手机号码
	private String phoneNo;

	// 发生开始时间
	private String exchangeBeginTime;
	// 发生结束时间
	private String exchangeEndTime;

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Integer getPensNumber() {
		return pensNumber;
	}

	public void setPensNumber(Integer pensNumber) {
		this.pensNumber = pensNumber;
	}

	public String getPensNumberProportion() {
		return pensNumberProportion;
	}

	public void setPensNumberProportion(String pensNumberProportion) {
		this.pensNumberProportion = pensNumberProportion;
	}

	public String getExchangeBeginTime() {
		return exchangeBeginTime;
	}

	public void setExchangeBeginTime(String exchangeBeginTime) {
		this.exchangeBeginTime = exchangeBeginTime;
	}

	public String getExchangeEndTime() {
		return exchangeEndTime;
	}

	public void setExchangeEndTime(String exchangeEndTime) {
		this.exchangeEndTime = exchangeEndTime;
	}

	public String getPid() {
		return pid;
	}

	public Integer getExchangePoint() {
		return exchangePoint;
	}

	public void setExchangePoint(Integer exchangePoint) {
		this.exchangePoint = exchangePoint;
	}

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

	public void setPid(String pid) {
		this.pid = pid == null ? null : pid.trim();
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId == null ? null : customerId.trim();
	}

	public String getExchangeType() {
		return exchangeType;
	}

	public void setExchangeType(String exchangeType) {
		this.exchangeType = exchangeType == null ? null : exchangeType.trim();
	}

	public String getExchangeTime() {
		return exchangeTime;
	}

	public void setExchangeTime(String exchangeTime) {
		this.exchangeTime = exchangeTime;
	}

	public String getExchangeStatus() {
		return exchangeStatus;
	}

	public void setExchangeStatus(String exchangeStatus) {
		this.exchangeStatus = exchangeStatus == null ? null : exchangeStatus.trim();
	}

	public String getExchangeRemark() {
		return exchangeRemark;
	}

	public void setExchangeRemark(String exchangeRemark) {
		this.exchangeRemark = exchangeRemark == null ? null : exchangeRemark.trim();
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
		CustExchangeDetail other = (CustExchangeDetail) that;
		return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid())) && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId())) && (this.getExchangeType() == null ? other.getExchangeType() == null : this.getExchangeType().equals(other.getExchangeType())) && (this.getExchangeTime() == null ? other.getExchangeTime() == null : this.getExchangeTime().equals(other.getExchangeTime())) && (this.getExchangeStatus() == null ? other.getExchangeStatus() == null : this.getExchangeStatus().equals(other.getExchangeStatus())) && (this.getExchangeRemark() == null ? other.getExchangeRemark() == null : this.getExchangeRemark().equals(other.getExchangeRemark()))
				&& (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus())) && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser())) && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime())) && (this.getLastUpdateUser() == null ? other.getLastUpdateUser() == null : this.getLastUpdateUser().equals(other.getLastUpdateUser())) && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
		result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
		result = prime * result + ((getExchangeType() == null) ? 0 : getExchangeType().hashCode());
		result = prime * result + ((getExchangeTime() == null) ? 0 : getExchangeTime().hashCode());
		result = prime * result + ((getExchangeStatus() == null) ? 0 : getExchangeStatus().hashCode());
		result = prime * result + ((getExchangeRemark() == null) ? 0 : getExchangeRemark().hashCode());
		result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
		result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
		result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
		result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
		return result;
	}
}