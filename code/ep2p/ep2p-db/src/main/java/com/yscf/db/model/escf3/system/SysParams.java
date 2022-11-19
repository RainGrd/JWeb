package com.yscf.db.model.escf3.system;

import java.util.Date;

import com.achievo.framework.dto.BaseDto;
import com.achievo.framework.entity.BaseEntity;

/**
 * 系统参数实体
 * 
 * @author fengshiliang
 * @date 2015年9月10日
 * @version v1.0.0
 *
 */
public class SysParams extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6270317647315618397L;

	private String paramKey;

	private String paramValue;
	/**
	 * 描述
	 */
	private String paramDesc;
	/**
	 * 排序
	 */
	private String paramOrder;
	/**
	 * 状态 0 禁用 1 启用
	 */
	private String status;

	private String createUser;

	private Date createTime;

	private String lastUpdateUser;

	private Date lastUpdateTime;
	/**
	 * 版本
	 */
	private String version;

	public String getParamKey() {
		return paramKey;
	}

	public void setParamKey(String paramKey) {
		this.paramKey = paramKey;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public String getParamDesc() {
		return paramDesc;
	}

	public void setParamDesc(String paramDesc) {
		this.paramDesc = paramDesc;
	}

	public String getParamOrder() {
		return paramOrder;
	}

	public void setParamOrder(String paramOrder) {
		this.paramOrder = paramOrder;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
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
		this.lastUpdateUser = lastUpdateUser;
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
		this.version = version;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((createTime == null) ? 0 : createTime.hashCode());
		result = prime * result
				+ ((createUser == null) ? 0 : createUser.hashCode());
		result = prime * result
				+ ((lastUpdateTime == null) ? 0 : lastUpdateTime.hashCode());
		result = prime * result
				+ ((lastUpdateUser == null) ? 0 : lastUpdateUser.hashCode());
		result = prime * result
				+ ((paramDesc == null) ? 0 : paramDesc.hashCode());
		result = prime * result
				+ ((paramKey == null) ? 0 : paramKey.hashCode());
		result = prime * result
				+ ((paramOrder == null) ? 0 : paramOrder.hashCode());
		result = prime * result
				+ ((paramValue == null) ? 0 : paramValue.hashCode());
		result = prime * result + ((status == null) ? 0 : status.hashCode());
		result = prime * result + ((version == null) ? 0 : version.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SysParams other = (SysParams) obj;
		if (createTime == null) {
			if (other.createTime != null)
				return false;
		} else if (!createTime.equals(other.createTime))
			return false;
		if (createUser == null) {
			if (other.createUser != null)
				return false;
		} else if (!createUser.equals(other.createUser))
			return false;
		if (lastUpdateTime == null) {
			if (other.lastUpdateTime != null)
				return false;
		} else if (!lastUpdateTime.equals(other.lastUpdateTime))
			return false;
		if (lastUpdateUser == null) {
			if (other.lastUpdateUser != null)
				return false;
		} else if (!lastUpdateUser.equals(other.lastUpdateUser))
			return false;
		if (paramDesc == null) {
			if (other.paramDesc != null)
				return false;
		} else if (!paramDesc.equals(other.paramDesc))
			return false;
		if (paramKey == null) {
			if (other.paramKey != null)
				return false;
		} else if (!paramKey.equals(other.paramKey))
			return false;
		if (paramOrder == null) {
			if (other.paramOrder != null)
				return false;
		} else if (!paramOrder.equals(other.paramOrder))
			return false;
		if (paramValue == null) {
			if (other.paramValue != null)
				return false;
		} else if (!paramValue.equals(other.paramValue))
			return false;
		if (status == null) {
			if (other.status != null)
				return false;
		} else if (!status.equals(other.status))
			return false;
		if (version == null) {
			if (other.version != null)
				return false;
		} else if (!version.equals(other.version))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return BaseDto.object2Json(this);
	}

}
