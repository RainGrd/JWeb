package com.yscf.core.model.financial.recharge;

import com.achievo.framework.entity.BaseEntity;
import java.util.Date;

/**
 * 
 * Description：<br> 
 * 支付接口实体类
 * @author  Jie.Zou
 * @date    2015年11月18日
 * @version v1.0.0
 */
public class PayConfig extends BaseEntity {
	private static final long serialVersionUID = -2938965163602542746L;

	/**
	 * ID
	 */
	private String pid;

	/**
	 * 支付接口编号
	 */
    private Integer paySystemInt;

    /**
     * 状态
     */
    private String status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建人
     */
    private String createUser;

    /**
     * 最后修改人
     */
    private Date lastUpdateTime;

    /**
     * 最后修改时间
     */
    private String lastUpdateUser;

    /**
     * 预留字段1
     */
    private String exInfo1;

    /**
     * 预留字段2
     */
    private String exInfo2;

    /**
     * 商户号
     */
    private String merchantId;

    /**
     * 密钥
     */
    private String signKdy;
    
    private String payName;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public Integer getPaySystemInt() {
        return paySystemInt;
    }

    public void setPaySystemInt(Integer paySystemInt) {
        this.paySystemInt = paySystemInt;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser == null ? null : lastUpdateUser.trim();
    }

    public String getExInfo1() {
        return exInfo1;
    }

    public void setExInfo1(String exInfo1) {
        this.exInfo1 = exInfo1 == null ? null : exInfo1.trim();
    }

    public String getExInfo2() {
        return exInfo2;
    }

    public void setExInfo2(String exInfo2) {
        this.exInfo2 = exInfo2 == null ? null : exInfo2.trim();
    }

    public String getMerchantId() {
        return merchantId;
    }

    public void setMerchantId(String merchantId) {
        this.merchantId = merchantId == null ? null : merchantId.trim();
    }

    public String getSignKdy() {
        return signKdy;
    }

    public void setSignKdy(String signKdy) {
        this.signKdy = signKdy == null ? null : signKdy.trim();
    }
    
    public String getPayName() {
		return payName;
	}

	public void setPayName(String payName) {
		this.payName = payName;
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
        PayConfig other = (PayConfig) that;
        return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getPaySystemInt() == null ? other.getPaySystemInt() == null : this.getPaySystemInt().equals(other.getPaySystemInt()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()))
            && (this.getLastUpdateUser() == null ? other.getLastUpdateUser() == null : this.getLastUpdateUser().equals(other.getLastUpdateUser()))
            && (this.getExInfo1() == null ? other.getExInfo1() == null : this.getExInfo1().equals(other.getExInfo1()))
            && (this.getExInfo2() == null ? other.getExInfo2() == null : this.getExInfo2().equals(other.getExInfo2()))
            && (this.getMerchantId() == null ? other.getMerchantId() == null : this.getMerchantId().equals(other.getMerchantId()))
            && (this.getSignKdy() == null ? other.getSignKdy() == null : this.getSignKdy().equals(other.getSignKdy()))
        	&& (this.getPayName() == null ? other.getPayName() == null : this.getPayName().equals(other.getPayName()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getPaySystemInt() == null) ? 0 : getPaySystemInt().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
        result = prime * result + ((getExInfo1() == null) ? 0 : getExInfo1().hashCode());
        result = prime * result + ((getExInfo2() == null) ? 0 : getExInfo2().hashCode());
        result = prime * result + ((getMerchantId() == null) ? 0 : getMerchantId().hashCode());
        result = prime * result + ((getSignKdy() == null) ? 0 : getSignKdy().hashCode());
        result = prime * result + ((getPayName() == null) ? 0 : getPayName().hashCode());
        return result;
    }
}