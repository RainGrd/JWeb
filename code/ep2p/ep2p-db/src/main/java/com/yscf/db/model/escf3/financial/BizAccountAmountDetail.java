package com.yscf.db.model.escf3.financial;

import java.math.BigDecimal;

import com.achievo.framework.entity.BaseEntity;

/**
 * 
 * Description：<br> 
 * 资金类别变化明细实体
 * @author  Jie.Zou
 * @date    2015年12月14日
 * @version v1.0.0
 */
public class BizAccountAmountDetail extends BaseEntity {
	
	private static final long serialVersionUID = -4753630961413614984L;

	/**
	 * id
	 */
	private String pid;

	/**
	 * 客户ID
	 */
    private String customerId;

    /**
     * 关联流水ID
     */
    private String payConfigId;

    /**
     * 资金类别
     */
    private String fundType;

    /**
     * 发生额
     */
    private BigDecimal happenValue;

    /**
     * 账户总余额
     */
    private BigDecimal balance;

    /**
     * 资金流水类别
     */
    private String fundtallyType;

    /**
     * 数据状态
     */
    private String status;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 最后修改人
     */
    private String lastUpdateUser;

    /**
     * 最后修改时间
     */
    private String lastUpdateTime;

    /**
     * 备注
     */
    private String accAmoDetDesc;
    
    /**
     * 版本号
     */
    private String version;

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

    public String getPayConfigId() {
        return payConfigId;
    }

    public void setPayConfigId(String payConfigId) {
        this.payConfigId = payConfigId == null ? null : payConfigId.trim();
    }

    public String getFundType() {
        return fundType;
    }

    public void setFundType(String fundType) {
        this.fundType = fundType == null ? null : fundType.trim();
    }

    public BigDecimal getHappenValue() {
        return happenValue;
    }

    public void setHappenValue(BigDecimal happenValue) {
        this.happenValue = happenValue;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getFundtallyType() {
        return fundtallyType;
    }

    public void setFundtallyType(String fundtallyType) {
        this.fundtallyType = fundtallyType == null ? null : fundtallyType.trim();
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

    public String getAccAmoDetDesc() {
        return accAmoDetDesc;
    }

    public void setAccAmoDetDesc(String accAmoDetDesc) {
        this.accAmoDetDesc = accAmoDetDesc == null ? null : accAmoDetDesc.trim();
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
        BizAccountAmountDetail other = (BizAccountAmountDetail) that;
        return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getPayConfigId() == null ? other.getPayConfigId() == null : this.getPayConfigId().equals(other.getPayConfigId()))
            && (this.getFundType() == null ? other.getFundType() == null : this.getFundType().equals(other.getFundType()))
            && (this.getHappenValue() == null ? other.getHappenValue() == null : this.getHappenValue().equals(other.getHappenValue()))
            && (this.getBalance() == null ? other.getBalance() == null : this.getBalance().equals(other.getBalance()))
            && (this.getFundtallyType() == null ? other.getFundtallyType() == null : this.getFundtallyType().equals(other.getFundtallyType()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastUpdateUser() == null ? other.getLastUpdateUser() == null : this.getLastUpdateUser().equals(other.getLastUpdateUser()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()))
            && (this.getAccAmoDetDesc() == null ? other.getAccAmoDetDesc() == null : this.getAccAmoDetDesc().equals(other.getAccAmoDetDesc()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getPayConfigId() == null) ? 0 : getPayConfigId().hashCode());
        result = prime * result + ((getFundType() == null) ? 0 : getFundType().hashCode());
        result = prime * result + ((getHappenValue() == null) ? 0 : getHappenValue().hashCode());
        result = prime * result + ((getBalance() == null) ? 0 : getBalance().hashCode());
        result = prime * result + ((getFundtallyType() == null) ? 0 : getFundtallyType().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        result = prime * result + ((getAccAmoDetDesc() == null) ? 0 : getAccAmoDetDesc().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        return result;
    }
}