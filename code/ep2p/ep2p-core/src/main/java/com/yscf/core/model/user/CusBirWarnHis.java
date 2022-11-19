package com.yscf.core.model.user;

import com.achievo.framework.entity.BaseEntity;
import java.util.Date;

/**
 * 
 * @ClassName : CusBirWarnHis
 * @Description : VIP生日提醒历史JavaBean
 * @Author : Qing.Cai
 * @Date : 2015年10月24日 下午2:37:30
 */
public class CusBirWarnHis extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String pid;

	private String customerId;

	private String vipMessage;

	private String smsTempId;

	private String custAccountId;

	private String isWran;

	private String operTime;

	private String status;

	private String createUser;

	private Date createTime;

	private String lastUpdateUser;

	private Date lastUpdateTime;

	private String birWarHisDesc;

	private String customerName;// 用户名
	private String sname;// 姓名
	private String phoneNo;// 手机号码
	private String birthday;// 生日
	private String vipLevel;// VIP等级
	private String refereeUser;// 推荐人
	private String customerServiceUser;// 客服
	private String beginOperTime;// 开始生日日期
	private String endOperTime;// 结束生日日期

	public String getBeginOperTime() {
		return beginOperTime;
	}

	public void setBeginOperTime(String beginOperTime) {
		this.beginOperTime = beginOperTime;
	}

	public String getEndOperTime() {
		return endOperTime;
	}

	public void setEndOperTime(String endOperTime) {
		this.endOperTime = endOperTime;
	}

	public String getRefereeUser() {
		return refereeUser;
	}

	public void setRefereeUser(String refereeUser) {
		this.refereeUser = refereeUser;
	}

	public String getCustomerServiceUser() {
		return customerServiceUser;
	}

	public void setCustomerServiceUser(String customerServiceUser) {
		this.customerServiceUser = customerServiceUser;
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

	public String getBirthday() {
		return birthday;
	}

	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}

	public String getVipLevel() {
		return vipLevel;
	}

	public void setVipLevel(String vipLevel) {
		this.vipLevel = vipLevel;
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

	public String getVipMessage() {
		return vipMessage;
	}

	public void setVipMessage(String vipMessage) {
		this.vipMessage = vipMessage == null ? null : vipMessage.trim();
	}

	public String getSmsTempId() {
		return smsTempId;
	}

	public void setSmsTempId(String smsTempId) {
		this.smsTempId = smsTempId == null ? null : smsTempId.trim();
	}

	public String getCustAccountId() {
		return custAccountId;
	}

	public void setCustAccountId(String custAccountId) {
		this.custAccountId = custAccountId == null ? null : custAccountId.trim();
	}

	public String getIsWran() {
		return isWran;
	}

	public void setIsWran(String isWran) {
		this.isWran = isWran == null ? null : isWran.trim();
	}

	public String getOperTime() {
		return operTime;
	}

	public void setOperTime(String operTime) {
		this.operTime = operTime;
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

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser == null ? null : lastUpdateUser.trim();
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getBirWarHisDesc() {
		return birWarHisDesc;
	}

	public void setBirWarHisDesc(String birWarHisDesc) {
		this.birWarHisDesc = birWarHisDesc == null ? null : birWarHisDesc.trim();
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
		CusBirWarnHis other = (CusBirWarnHis) that;
		return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid())) && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId())) && (this.getVipMessage() == null ? other.getVipMessage() == null : this.getVipMessage().equals(other.getVipMessage())) && (this.getSmsTempId() == null ? other.getSmsTempId() == null : this.getSmsTempId().equals(other.getSmsTempId())) && (this.getCustAccountId() == null ? other.getCustAccountId() == null : this.getCustAccountId().equals(other.getCustAccountId())) && (this.getIsWran() == null ? other.getIsWran() == null : this.getIsWran().equals(other.getIsWran()))
				&& (this.getOperTime() == null ? other.getOperTime() == null : this.getOperTime().equals(other.getOperTime())) && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus())) && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser())) && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime())) && (this.getLastUpdateUser() == null ? other.getLastUpdateUser() == null : this.getLastUpdateUser().equals(other.getLastUpdateUser())) && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()))
				&& (this.getBirWarHisDesc() == null ? other.getBirWarHisDesc() == null : this.getBirWarHisDesc().equals(other.getBirWarHisDesc()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
		result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
		result = prime * result + ((getVipMessage() == null) ? 0 : getVipMessage().hashCode());
		result = prime * result + ((getSmsTempId() == null) ? 0 : getSmsTempId().hashCode());
		result = prime * result + ((getCustAccountId() == null) ? 0 : getCustAccountId().hashCode());
		result = prime * result + ((getIsWran() == null) ? 0 : getIsWran().hashCode());
		result = prime * result + ((getOperTime() == null) ? 0 : getOperTime().hashCode());
		result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
		result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
		result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
		result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
		result = prime * result + ((getBirWarHisDesc() == null) ? 0 : getBirWarHisDesc().hashCode());
		return result;
	}
}