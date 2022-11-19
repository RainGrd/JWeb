package com.yscf.db.model.escf2.business;

import com.achievo.framework.entity.BaseEntity;
import java.math.BigDecimal;

/**
 * Description：<br> 
 * 线上充值表（CaffP2pRechargeOnline） 映射基础类（JavaBean）
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffP2pRechargeOnline extends BaseEntity {
    
	private static final long serialVersionUID = -5164438269685636896L;

	private Long caffp2prechargeonlineid;

    private Long createddatelong;

    private Long version;

    private Long userid;

    private BigDecimal amount;

    private Long payconfigid;

    private Integer stateint;

    private Integer paysystemtypeint;

    public Long getCaffp2prechargeonlineid() {
        return caffp2prechargeonlineid;
    }

    public void setCaffp2prechargeonlineid(Long caffp2prechargeonlineid) {
        this.caffp2prechargeonlineid = caffp2prechargeonlineid;
    }

    public Long getCreateddatelong() {
        return createddatelong;
    }

    public void setCreateddatelong(Long createddatelong) {
        this.createddatelong = createddatelong;
    }

    public Long getVersion() {
        return version;
    }

    public void setVersion(Long version) {
        this.version = version;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getPayconfigid() {
        return payconfigid;
    }

    public void setPayconfigid(Long payconfigid) {
        this.payconfigid = payconfigid;
    }

    public Integer getStateint() {
        return stateint;
    }

    public void setStateint(Integer stateint) {
        this.stateint = stateint;
    }

    public Integer getPaysystemtypeint() {
        return paysystemtypeint;
    }

    public void setPaysystemtypeint(Integer paysystemtypeint) {
        this.paysystemtypeint = paysystemtypeint;
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
        CaffP2pRechargeOnline other = (CaffP2pRechargeOnline) that;
        return (this.getCaffp2prechargeonlineid() == null ? other.getCaffp2prechargeonlineid() == null : this.getCaffp2prechargeonlineid().equals(other.getCaffp2prechargeonlineid()))
            && (this.getCreateddatelong() == null ? other.getCreateddatelong() == null : this.getCreateddatelong().equals(other.getCreateddatelong()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getPayconfigid() == null ? other.getPayconfigid() == null : this.getPayconfigid().equals(other.getPayconfigid()))
            && (this.getStateint() == null ? other.getStateint() == null : this.getStateint().equals(other.getStateint()))
            && (this.getPaysystemtypeint() == null ? other.getPaysystemtypeint() == null : this.getPaysystemtypeint().equals(other.getPaysystemtypeint()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCaffp2prechargeonlineid() == null) ? 0 : getCaffp2prechargeonlineid().hashCode());
        result = prime * result + ((getCreateddatelong() == null) ? 0 : getCreateddatelong().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getPayconfigid() == null) ? 0 : getPayconfigid().hashCode());
        result = prime * result + ((getStateint() == null) ? 0 : getStateint().hashCode());
        result = prime * result + ((getPaysystemtypeint() == null) ? 0 : getPaysystemtypeint().hashCode());
        return result;
    }
}