package com.yscf.core.model.user;

import com.achievo.framework.entity.BaseEntity;

/**
 * 
 * @ClassName : CustSignIn
 * @Description : 签到信息javaBean
 * @Author : Qing.Cai
 * @Date : 2015年12月7日 下午3:28:45
 */
public class CustSignIn extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private String pid;

	private String customerId;

	private String signInTime;

	private Integer signInPlatform;

	private String createTime;

	private String createUser;

	private String version;

	private Integer signInCount;

	private String sname;

	private String imageUrl;

	public Integer getSignInCount() {
		return signInCount;
	}

	public void setSignInCount(Integer signInCount) {
		this.signInCount = signInCount;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
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

	public String getSignInTime() {
		return signInTime;
	}

	public void setSignInTime(String signInTime) {
		this.signInTime = signInTime;
	}

	public Integer getSignInPlatform() {
		return signInPlatform;
	}

	public void setSignInPlatform(Integer signInPlatform) {
		this.signInPlatform = signInPlatform;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser == null ? null : createUser.trim();
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
		CustSignIn other = (CustSignIn) that;
		return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid())) && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId())) && (this.getSignInTime() == null ? other.getSignInTime() == null : this.getSignInTime().equals(other.getSignInTime())) && (this.getSignInPlatform() == null ? other.getSignInPlatform() == null : this.getSignInPlatform().equals(other.getSignInPlatform())) && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime())) && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
				&& (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
		result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
		result = prime * result + ((getSignInTime() == null) ? 0 : getSignInTime().hashCode());
		result = prime * result + ((getSignInPlatform() == null) ? 0 : getSignInPlatform().hashCode());
		result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
		result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
		return result;
	}
}