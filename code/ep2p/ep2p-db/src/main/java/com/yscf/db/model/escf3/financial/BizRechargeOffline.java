package com.yscf.db.model.escf3.financial;

import java.math.BigDecimal;

import com.achievo.framework.entity.BaseEntity;
import com.yscf.db.model.escf3.user.CusTomer;

/**
 * Description：线下充值Bean 对应（t_biz_recharge_offline表）
 * @author  JingYu.Dai
 * @date    2015年9月28日
 * @version v1.0.0
 */
public class BizRechargeOffline extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = -365485368605365689L;

	private String pid;

    private String customerId; //客户ID

    private BigDecimal amount; 	//充值金额

    private String recStatus; //充值状态
 
    private String recTime;  //充值时间

    private String userId;	//充值用户ID
    
    private String recOffDesc; //充值描述
    
    private CusTomer customer; //客户对象
    
    private String recTimeBegin; //充值开始时间  用于条件充值时间（recTime）的区间查询  （当做VO用）
    
    private String recTimeEnd; 	 //充值结束始时间  用于条件充值时间（recTime）的区间查询  （当做VO用）

    private String status;

    private String createUser;

    private String createTime;

    private String lastUpdateUser;

    private String lastUpdateTime;

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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getRecStatus() {
        return recStatus;
    }

    public void setRecStatus(String recStatus) {
        this.recStatus = recStatus;
    }

    public String getRecTime() {
        return recTime;
    }

    public void setRecTime(String recTime) {
        this.recTime = recTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
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

    public String getRecOffDesc() {
        return recOffDesc;
    }

    public void setRecOffDesc(String recOffDesc) {
        this.recOffDesc = recOffDesc == null ? null : recOffDesc.trim();
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
        BizRechargeOffline other = (BizRechargeOffline) that;
        return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getRecStatus() == null ? other.getRecStatus() == null : this.getRecStatus().equals(other.getRecStatus()))
            && (this.getRecTime() == null ? other.getRecTime() == null : this.getRecTime().equals(other.getRecTime()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastUpdateUser() == null ? other.getLastUpdateUser() == null : this.getLastUpdateUser().equals(other.getLastUpdateUser()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()))
            && (this.getRecOffDesc() == null ? other.getRecOffDesc() == null : this.getRecOffDesc().equals(other.getRecOffDesc()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getRecStatus() == null) ? 0 : getRecStatus().hashCode());
        result = prime * result + ((getRecTime() == null) ? 0 : getRecTime().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        result = prime * result + ((getRecOffDesc() == null) ? 0 : getRecOffDesc().hashCode());
        return result;
    }

	public CusTomer getCustomer() {
		return customer;
	}

	public void setCustomer(CusTomer customer) {
		this.customer = customer;
	}

	public String getRecTimeBegin() {
		return recTimeBegin;
	}

	public void setRecTimeBegin(String recTimeBegin) {
		this.recTimeBegin = recTimeBegin;
	}

	public String getRecTimeEnd() {
		return recTimeEnd;
	}

	public void setRecTimeEnd(String recTimeEnd) {
		this.recTimeEnd = recTimeEnd;
	}

	
}