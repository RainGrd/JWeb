package com.yscf.core.model.pub;

import com.achievo.framework.entity.BaseEntity;

/**
 * 
 * Description：<br>
 * 业务合同类
 * 
 * @author JunJie.Liu
 * @date 2016年2月22日
 * @version v1.0.0
 */
public class Contract extends BaseEntity {

	private static final long serialVersionUID = 5845880547020058939L;

	private String pid; // 　主键

	private String customerId;// 客户id

	private String businessId; // 业务Id

	private String agreementModelId; // 协议模板id

	private String agreementFileUrl; // 合同文件路径

	private String agreementNumber; // 合同编号

	private String type; // 合同类型

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid == null ? null : pid.trim();
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId == null ? null : businessId.trim();
	}

	public String getAgreementModelId() {
		return agreementModelId;
	}

	public void setAgreementModelId(String agreementModelId) {
		this.agreementModelId = agreementModelId == null ? null
				: agreementModelId.trim();
	}

	public String getAgreementFileUrl() {
		return agreementFileUrl;
	}

	public void setAgreementFileUrl(String agreementFileUrl) {
		this.agreementFileUrl = agreementFileUrl == null ? null
				: agreementFileUrl.trim();
	}

	public String getAgreementNumber() {
		return agreementNumber;
	}

	public void setAgreementNumber(String agreementNumber) {
		this.agreementNumber = agreementNumber == null ? null : agreementNumber
				.trim();
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type == null ? null : type.trim();
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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
		Contract other = (Contract) that;
		return (this.getPid() == null ? other.getPid() == null : this.getPid()
				.equals(other.getPid()))
				&& (this.getBusinessId() == null ? other.getBusinessId() == null
						: this.getBusinessId().equals(other.getBusinessId()))
				&& (this.getAgreementModelId() == null ? other
						.getAgreementModelId() == null : this
						.getAgreementModelId().equals(
								other.getAgreementModelId()))
				&& (this.getAgreementFileUrl() == null ? other
						.getAgreementFileUrl() == null : this
						.getAgreementFileUrl().equals(
								other.getAgreementFileUrl()))
				&& (this.getAgreementNumber() == null ? other
						.getAgreementNumber() == null : this
						.getAgreementNumber()
						.equals(other.getAgreementNumber()))
				&& (this.getType() == null ? other.getType() == null : this
						.getType().equals(other.getType()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getPid() == null) ? 0 : getPid().hashCode());
		result = prime * result
				+ ((getBusinessId() == null) ? 0 : getBusinessId().hashCode());
		result = prime
				* result
				+ ((getAgreementModelId() == null) ? 0 : getAgreementModelId()
						.hashCode());
		result = prime
				* result
				+ ((getAgreementFileUrl() == null) ? 0 : getAgreementFileUrl()
						.hashCode());
		result = prime
				* result
				+ ((getAgreementNumber() == null) ? 0 : getAgreementNumber()
						.hashCode());
		result = prime * result
				+ ((getType() == null) ? 0 : getType().hashCode());
		return result;
	}
}