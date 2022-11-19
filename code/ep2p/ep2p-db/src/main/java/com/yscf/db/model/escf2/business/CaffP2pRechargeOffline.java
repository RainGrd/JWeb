package com.yscf.db.model.escf2.business;

import java.math.BigDecimal;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br> 
 * 线下充值表（CaffP2pRechargeOffline） 映射基础类（JavaBean）
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffP2pRechargeOffline extends BaseEntity {
    
	private static final long serialVersionUID = -2358032806397409274L;

	private Long caffp2prechargeofflineid;

    private Long createddatelong;

    private Long version;

    private Long userid;

    private BigDecimal amount;

    private Long bankid;

    private Integer stateint;

    private String tradecode;

    private Long tradedatelong;

    private Long bitstate;

    public Long getCaffp2prechargeofflineid() {
        return caffp2prechargeofflineid;
    }

    public void setCaffp2prechargeofflineid(Long caffp2prechargeofflineid) {
        this.caffp2prechargeofflineid = caffp2prechargeofflineid;
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

    public Long getBankid() {
        return bankid;
    }

    public void setBankid(Long bankid) {
        this.bankid = bankid;
    }

    public Integer getStateint() {
        return stateint;
    }

    public void setStateint(Integer stateint) {
        this.stateint = stateint;
    }

    public String getTradecode() {
        return tradecode;
    }

    public void setTradecode(String tradecode) {
        this.tradecode = tradecode == null ? null : tradecode.trim();
    }

    public Long getTradedatelong() {
        return tradedatelong;
    }

    public void setTradedatelong(Long tradedatelong) {
        this.tradedatelong = tradedatelong;
    }

    public Long getBitstate() {
        return bitstate;
    }

    public void setBitstate(Long bitstate) {
        this.bitstate = bitstate;
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
        CaffP2pRechargeOffline other = (CaffP2pRechargeOffline) that;
        return (this.getCaffp2prechargeofflineid() == null ? other.getCaffp2prechargeofflineid() == null : this.getCaffp2prechargeofflineid().equals(other.getCaffp2prechargeofflineid()))
            && (this.getCreateddatelong() == null ? other.getCreateddatelong() == null : this.getCreateddatelong().equals(other.getCreateddatelong()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getBankid() == null ? other.getBankid() == null : this.getBankid().equals(other.getBankid()))
            && (this.getStateint() == null ? other.getStateint() == null : this.getStateint().equals(other.getStateint()))
            && (this.getTradecode() == null ? other.getTradecode() == null : this.getTradecode().equals(other.getTradecode()))
            && (this.getTradedatelong() == null ? other.getTradedatelong() == null : this.getTradedatelong().equals(other.getTradedatelong()))
            && (this.getBitstate() == null ? other.getBitstate() == null : this.getBitstate().equals(other.getBitstate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCaffp2prechargeofflineid() == null) ? 0 : getCaffp2prechargeofflineid().hashCode());
        result = prime * result + ((getCreateddatelong() == null) ? 0 : getCreateddatelong().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getBankid() == null) ? 0 : getBankid().hashCode());
        result = prime * result + ((getStateint() == null) ? 0 : getStateint().hashCode());
        result = prime * result + ((getTradecode() == null) ? 0 : getTradecode().hashCode());
        result = prime * result + ((getTradedatelong() == null) ? 0 : getTradedatelong().hashCode());
        result = prime * result + ((getBitstate() == null) ? 0 : getBitstate().hashCode());
        return result;
    }
}