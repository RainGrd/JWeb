package com.yscf.core.model.business;

import java.util.Date;

import com.achievo.framework.dto.BaseDto;
import com.achievo.framework.entity.BaseEntity;

/**
 * 
 * Description：<br>
 * 借款信息与条件关系表 实体
 * 
 * @author fengshiliang
 * @date 2015年10月20日
 * @version v1.0.0
 */
public class BizBorrowCondRel extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7289406236425538643L;

	private String pid;

	private String condId;

	private String borrowId;

	private String relType;

	private String status;

	private String createUser;

	private Date createTime;

	private String lastUpdateUser;

	private Date lastUpdateTime;

	private String borCondDesc;

	private String condName;

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid == null ? null : pid.trim();
	}

	public String getCondId() {
		return condId;
	}

	public void setCondId(String condId) {
		this.condId = condId == null ? null : condId.trim();
	}

	public String getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(String borrowId) {
		this.borrowId = borrowId == null ? null : borrowId.trim();
	}

	public String getRelType() {
		return relType;
	}

	public void setRelType(String relType) {
		this.relType = relType == null ? null : relType.trim();
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
		this.lastUpdateUser = lastUpdateUser == null ? null : lastUpdateUser
				.trim();
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getBorCondDesc() {
		return borCondDesc;
	}

	public void setBorCondDesc(String borCondDesc) {
		this.borCondDesc = borCondDesc == null ? null : borCondDesc.trim();
	}

	public String getCondName() {
		return condName;
	}

	public void setCondName(String condName) {
		this.condName = condName;
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
		BizBorrowCondRel other = (BizBorrowCondRel) that;
		return (this.getPid() == null ? other.getPid() == null : this.getPid()
				.equals(other.getPid()))
				&& (this.getCondId() == null ? other.getCondId() == null : this
						.getCondId().equals(other.getCondId()))
				&& (this.getBorrowId() == null ? other.getBorrowId() == null
						: this.getBorrowId().equals(other.getBorrowId()))
				&& (this.getRelType() == null ? other.getRelType() == null
						: this.getRelType().equals(other.getRelType()))
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
				&& (this.getBorCondDesc() == null ? other.getBorCondDesc() == null
						: this.getBorCondDesc().equals(other.getBorCondDesc()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getPid() == null) ? 0 : getPid().hashCode());
		result = prime * result
				+ ((getCondId() == null) ? 0 : getCondId().hashCode());
		result = prime * result
				+ ((getBorrowId() == null) ? 0 : getBorrowId().hashCode());
		result = prime * result
				+ ((getRelType() == null) ? 0 : getRelType().hashCode());
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
		result = prime
				* result
				+ ((getBorCondDesc() == null) ? 0 : getBorCondDesc().hashCode());
		return result;
	}

	@Override
	public String toString() {
		return BaseDto.object2Json(this);
	}

}