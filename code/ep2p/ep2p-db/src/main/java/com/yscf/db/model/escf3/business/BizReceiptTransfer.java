package com.yscf.db.model.escf3.business;

import java.math.BigDecimal;
import java.util.Date;

import com.achievo.framework.entity.BaseEntity;

/**
 * 
 * Description：<br> 
 * 债权转让
 * @author  JunJie.Liu
 * @date    2015年10月23日
 * @version v1.0.0
 */
public class BizReceiptTransfer extends BaseEntity {

	private static final long serialVersionUID = 6550224424200069932L;

	private String pid;

    private String borrowId;

    private String customerId;

    private Date interestData;
    
    private String parentTransferId;
    
    private BigDecimal profitRate;

    private Date expireTime;

    private BigDecimal successAmount;

    private Date successTime;

    private String status;

    private String createUser;

    private Date createTime;

    private String lastUpdateUser;

    private Date lastUpdateTime;

    private String recTraDesc;

    private String transferCode;

    private BigDecimal residualPrincipal;

    private Integer timeRemaining;

    private BigDecimal projectValue;

    private BigDecimal fee;

    private BigDecimal interestToday;

    private String version;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(String borrowId) {
        this.borrowId = borrowId == null ? null : borrowId.trim();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public BigDecimal getProfitRate() {
        return profitRate;
    }

    public void setProfitRate(BigDecimal profitRate) {
        this.profitRate = profitRate;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
    }

    public BigDecimal getSuccessAmount() {
        return successAmount;
    }

    public void setSuccessAmount(BigDecimal successAmount) {
        this.successAmount = successAmount;
    }

    public Date getSuccessTime() {
        return successTime;
    }

    public void setSuccessTime(Date successTime) {
        this.successTime = successTime;
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

    public String getRecTraDesc() {
        return recTraDesc;
    }

    public void setRecTraDesc(String recTraDesc) {
        this.recTraDesc = recTraDesc == null ? null : recTraDesc.trim();
    }

    public String getTransferCode() {
        return transferCode;
    }

    public void setTransferCode(String transferCode) {
        this.transferCode = transferCode == null ? null : transferCode.trim();
    }

    public BigDecimal getResidualPrincipal() {
        return residualPrincipal;
    }

    public void setResidualPrincipal(BigDecimal residualPrincipal) {
        this.residualPrincipal = residualPrincipal;
    }

    public Integer getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(Integer timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    public BigDecimal getProjectValue() {
        return projectValue;
    }

    public void setProjectValue(BigDecimal projectValue) {
        this.projectValue = projectValue;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public BigDecimal getInterestToday() {
        return interestToday;
    }

    public void setInterestToday(BigDecimal interestToday) {
        this.interestToday = interestToday;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }
    
    public Date getInterestData() {
		return interestData;
	}

	public void setInterestData(Date interestData) {
		this.interestData = interestData;
	}

	public String getParentTransferId() {
		return parentTransferId;
	}

	public void setParentTransferId(String parentTransferId) {
		this.parentTransferId = parentTransferId;
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
        BizReceiptTransfer other = (BizReceiptTransfer) that;
        return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getBorrowId() == null ? other.getBorrowId() == null : this.getBorrowId().equals(other.getBorrowId()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getProfitRate() == null ? other.getProfitRate() == null : this.getProfitRate().equals(other.getProfitRate()))
            && (this.getExpireTime() == null ? other.getExpireTime() == null : this.getExpireTime().equals(other.getExpireTime()))
            && (this.getSuccessAmount() == null ? other.getSuccessAmount() == null : this.getSuccessAmount().equals(other.getSuccessAmount()))
            && (this.getSuccessTime() == null ? other.getSuccessTime() == null : this.getSuccessTime().equals(other.getSuccessTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastUpdateUser() == null ? other.getLastUpdateUser() == null : this.getLastUpdateUser().equals(other.getLastUpdateUser()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()))
            && (this.getRecTraDesc() == null ? other.getRecTraDesc() == null : this.getRecTraDesc().equals(other.getRecTraDesc()))
            && (this.getTransferCode() == null ? other.getTransferCode() == null : this.getTransferCode().equals(other.getTransferCode()))
            && (this.getResidualPrincipal() == null ? other.getResidualPrincipal() == null : this.getResidualPrincipal().equals(other.getResidualPrincipal()))
            && (this.getTimeRemaining() == null ? other.getTimeRemaining() == null : this.getTimeRemaining().equals(other.getTimeRemaining()))
            && (this.getProjectValue() == null ? other.getProjectValue() == null : this.getProjectValue().equals(other.getProjectValue()))
            && (this.getFee() == null ? other.getFee() == null : this.getFee().equals(other.getFee()))
            && (this.getInterestToday() == null ? other.getInterestToday() == null : this.getInterestToday().equals(other.getInterestToday()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getBorrowId() == null) ? 0 : getBorrowId().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getProfitRate() == null) ? 0 : getProfitRate().hashCode());
        result = prime * result + ((getExpireTime() == null) ? 0 : getExpireTime().hashCode());
        result = prime * result + ((getSuccessAmount() == null) ? 0 : getSuccessAmount().hashCode());
        result = prime * result + ((getSuccessTime() == null) ? 0 : getSuccessTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        result = prime * result + ((getRecTraDesc() == null) ? 0 : getRecTraDesc().hashCode());
        result = prime * result + ((getTransferCode() == null) ? 0 : getTransferCode().hashCode());
        result = prime * result + ((getResidualPrincipal() == null) ? 0 : getResidualPrincipal().hashCode());
        result = prime * result + ((getTimeRemaining() == null) ? 0 : getTimeRemaining().hashCode());
        result = prime * result + ((getProjectValue() == null) ? 0 : getProjectValue().hashCode());
        result = prime * result + ((getFee() == null) ? 0 : getFee().hashCode());
        result = prime * result + ((getInterestToday() == null) ? 0 : getInterestToday().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        return result;
    }

    /**
     * 
     * Description：<br> 
     * 债权是否失效
     * @author  JunJie.Liu
     * @date    2016年2月1日
     * @version v1.0.0
     * @return
     */
	public boolean isExipre() {
		try{
			if(this.expireTime.getTime()<=System.currentTimeMillis()){
				return true;
			}
		}catch(Exception e){
			
		}
		return false;
	}

}