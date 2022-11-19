package com.yscf.db.model.escf2.business;

import java.math.BigDecimal;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br> 
 * 债权转让实体（CaffP2pReceiptTransfer） 映射基础类（JavaBean）
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffP2pReceiptTransfer extends BaseEntity {
   
	private static final long serialVersionUID = 9222147323412017510L;

	private Long caffp2preceipttransferid;

    private Long createddatelong;

    private Long version;

    private Long userid;

    private Long bitstate;

    private Long caffp2preceiptplanid;

    private Long expiredatelong;

    private Long newuserid;

    private BigDecimal profitrate;

    private Long receiptdatelong;

    private Long roottransferid;

    private BigDecimal servicefeerate;

    private Integer stateenum;

    private Integer stateint;

    private BigDecimal successamount;

    private Long successdatelong;

    private BigDecimal transferamount;

    public Long getCaffp2preceipttransferid() {
        return caffp2preceipttransferid;
    }

    public void setCaffp2preceipttransferid(Long caffp2preceipttransferid) {
        this.caffp2preceipttransferid = caffp2preceipttransferid;
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

    public Long getBitstate() {
        return bitstate;
    }

    public void setBitstate(Long bitstate) {
        this.bitstate = bitstate;
    }

    public Long getCaffp2preceiptplanid() {
        return caffp2preceiptplanid;
    }

    public void setCaffp2preceiptplanid(Long caffp2preceiptplanid) {
        this.caffp2preceiptplanid = caffp2preceiptplanid;
    }

    public Long getExpiredatelong() {
        return expiredatelong;
    }

    public void setExpiredatelong(Long expiredatelong) {
        this.expiredatelong = expiredatelong;
    }

    public Long getNewuserid() {
        return newuserid;
    }

    public void setNewuserid(Long newuserid) {
        this.newuserid = newuserid;
    }

    public BigDecimal getProfitrate() {
        return profitrate;
    }

    public void setProfitrate(BigDecimal profitrate) {
        this.profitrate = profitrate;
    }

    public Long getReceiptdatelong() {
        return receiptdatelong;
    }

    public void setReceiptdatelong(Long receiptdatelong) {
        this.receiptdatelong = receiptdatelong;
    }

    public Long getRoottransferid() {
        return roottransferid;
    }

    public void setRoottransferid(Long roottransferid) {
        this.roottransferid = roottransferid;
    }

    public BigDecimal getServicefeerate() {
        return servicefeerate;
    }

    public void setServicefeerate(BigDecimal servicefeerate) {
        this.servicefeerate = servicefeerate;
    }

    public Integer getStateenum() {
        return stateenum;
    }

    public void setStateenum(Integer stateenum) {
        this.stateenum = stateenum;
    }

    public Integer getStateint() {
        return stateint;
    }

    public void setStateint(Integer stateint) {
        this.stateint = stateint;
    }

    public BigDecimal getSuccessamount() {
        return successamount;
    }

    public void setSuccessamount(BigDecimal successamount) {
        this.successamount = successamount;
    }

    public Long getSuccessdatelong() {
        return successdatelong;
    }

    public void setSuccessdatelong(Long successdatelong) {
        this.successdatelong = successdatelong;
    }

    public BigDecimal getTransferamount() {
        return transferamount;
    }

    public void setTransferamount(BigDecimal transferamount) {
        this.transferamount = transferamount;
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
        CaffP2pReceiptTransfer other = (CaffP2pReceiptTransfer) that;
        return (this.getCaffp2preceipttransferid() == null ? other.getCaffp2preceipttransferid() == null : this.getCaffp2preceipttransferid().equals(other.getCaffp2preceipttransferid()))
            && (this.getCreateddatelong() == null ? other.getCreateddatelong() == null : this.getCreateddatelong().equals(other.getCreateddatelong()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getBitstate() == null ? other.getBitstate() == null : this.getBitstate().equals(other.getBitstate()))
            && (this.getCaffp2preceiptplanid() == null ? other.getCaffp2preceiptplanid() == null : this.getCaffp2preceiptplanid().equals(other.getCaffp2preceiptplanid()))
            && (this.getExpiredatelong() == null ? other.getExpiredatelong() == null : this.getExpiredatelong().equals(other.getExpiredatelong()))
            && (this.getNewuserid() == null ? other.getNewuserid() == null : this.getNewuserid().equals(other.getNewuserid()))
            && (this.getProfitrate() == null ? other.getProfitrate() == null : this.getProfitrate().equals(other.getProfitrate()))
            && (this.getReceiptdatelong() == null ? other.getReceiptdatelong() == null : this.getReceiptdatelong().equals(other.getReceiptdatelong()))
            && (this.getRoottransferid() == null ? other.getRoottransferid() == null : this.getRoottransferid().equals(other.getRoottransferid()))
            && (this.getServicefeerate() == null ? other.getServicefeerate() == null : this.getServicefeerate().equals(other.getServicefeerate()))
            && (this.getStateenum() == null ? other.getStateenum() == null : this.getStateenum().equals(other.getStateenum()))
            && (this.getStateint() == null ? other.getStateint() == null : this.getStateint().equals(other.getStateint()))
            && (this.getSuccessamount() == null ? other.getSuccessamount() == null : this.getSuccessamount().equals(other.getSuccessamount()))
            && (this.getSuccessdatelong() == null ? other.getSuccessdatelong() == null : this.getSuccessdatelong().equals(other.getSuccessdatelong()))
            && (this.getTransferamount() == null ? other.getTransferamount() == null : this.getTransferamount().equals(other.getTransferamount()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCaffp2preceipttransferid() == null) ? 0 : getCaffp2preceipttransferid().hashCode());
        result = prime * result + ((getCreateddatelong() == null) ? 0 : getCreateddatelong().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getBitstate() == null) ? 0 : getBitstate().hashCode());
        result = prime * result + ((getCaffp2preceiptplanid() == null) ? 0 : getCaffp2preceiptplanid().hashCode());
        result = prime * result + ((getExpiredatelong() == null) ? 0 : getExpiredatelong().hashCode());
        result = prime * result + ((getNewuserid() == null) ? 0 : getNewuserid().hashCode());
        result = prime * result + ((getProfitrate() == null) ? 0 : getProfitrate().hashCode());
        result = prime * result + ((getReceiptdatelong() == null) ? 0 : getReceiptdatelong().hashCode());
        result = prime * result + ((getRoottransferid() == null) ? 0 : getRoottransferid().hashCode());
        result = prime * result + ((getServicefeerate() == null) ? 0 : getServicefeerate().hashCode());
        result = prime * result + ((getStateenum() == null) ? 0 : getStateenum().hashCode());
        result = prime * result + ((getStateint() == null) ? 0 : getStateint().hashCode());
        result = prime * result + ((getSuccessamount() == null) ? 0 : getSuccessamount().hashCode());
        result = prime * result + ((getSuccessdatelong() == null) ? 0 : getSuccessdatelong().hashCode());
        result = prime * result + ((getTransferamount() == null) ? 0 : getTransferamount().hashCode());
        return result;
    }
}