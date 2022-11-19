package com.yscf.core.model.system;

import com.achievo.framework.entity.BaseEntity;

/**
 * 
 * @ClassName : SysIpPvHis
 * @Description : IP访问历史查看记录JavaBean
 * @Author : Qing.Cai
 * @Date : 2015年11月11日 下午3:11:46
 */
public class SysIpPvHis extends BaseEntity {

	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer type;

	private String macAddress;

	private String path;

	private String createTime;

	private Integer year;// 年份

	private Integer month;// 月份

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMonth() {
		return month;
	}

	public void setMonth(Integer month) {
		this.month = month;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getMacAddress() {
		return macAddress;
	}

	public void setMacAddress(String macAddress) {
		this.macAddress = macAddress == null ? null : macAddress.trim();
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path == null ? null : path.trim();
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
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
		SysIpPvHis other = (SysIpPvHis) that;
		return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId())) && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType())) && (this.getMacAddress() == null ? other.getMacAddress() == null : this.getMacAddress().equals(other.getMacAddress())) && (this.getPath() == null ? other.getPath() == null : this.getPath().equals(other.getPath())) && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
		result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
		result = prime * result + ((getMacAddress() == null) ? 0 : getMacAddress().hashCode());
		result = prime * result + ((getPath() == null) ? 0 : getPath().hashCode());
		result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		return result;
	}
}