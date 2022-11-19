package com.yscf.db.model.escf2.user;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br> 
 * VIP表(CaffP2pVip)		映射基础类（JavaBean）
 * @author  JingYu.Dai
 * @date    2016年3月2日
 * @version v1.0.0
 */
public class CaffP2pVip extends BaseEntity {

	private static final long serialVersionUID = -7710799547001081976L;

	private Long caffp2pvipid;

    private Long createddatelong;

    private Long version;

    private Long userid;

    private Long begindatelong;

    private Long enddatelong;

    private Long fee;

    private Long serviceid;

    private Integer stateint;

    private Integer vipachievetypeint;

    private Long bitstate;

    private Integer flagint;

    public Long getCaffp2pvipid() {
        return caffp2pvipid;
    }

    public void setCaffp2pvipid(Long caffp2pvipid) {
        this.caffp2pvipid = caffp2pvipid;
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

    public Long getBegindatelong() {
        return begindatelong;
    }

    public void setBegindatelong(Long begindatelong) {
        this.begindatelong = begindatelong;
    }

    public Long getEnddatelong() {
        return enddatelong;
    }

    public void setEnddatelong(Long enddatelong) {
        this.enddatelong = enddatelong;
    }

    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }

    public Long getServiceid() {
        return serviceid;
    }

    public void setServiceid(Long serviceid) {
        this.serviceid = serviceid;
    }

    public Integer getStateint() {
        return stateint;
    }

    public void setStateint(Integer stateint) {
        this.stateint = stateint;
    }

    public Integer getVipachievetypeint() {
        return vipachievetypeint;
    }

    public void setVipachievetypeint(Integer vipachievetypeint) {
        this.vipachievetypeint = vipachievetypeint;
    }

    public Long getBitstate() {
        return bitstate;
    }

    public void setBitstate(Long bitstate) {
        this.bitstate = bitstate;
    }

    public Integer getFlagint() {
        return flagint;
    }

    public void setFlagint(Integer flagint) {
        this.flagint = flagint;
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
        CaffP2pVip other = (CaffP2pVip) that;
        return (this.getCaffp2pvipid() == null ? other.getCaffp2pvipid() == null : this.getCaffp2pvipid().equals(other.getCaffp2pvipid()))
            && (this.getCreateddatelong() == null ? other.getCreateddatelong() == null : this.getCreateddatelong().equals(other.getCreateddatelong()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getBegindatelong() == null ? other.getBegindatelong() == null : this.getBegindatelong().equals(other.getBegindatelong()))
            && (this.getEnddatelong() == null ? other.getEnddatelong() == null : this.getEnddatelong().equals(other.getEnddatelong()))
            && (this.getFee() == null ? other.getFee() == null : this.getFee().equals(other.getFee()))
            && (this.getServiceid() == null ? other.getServiceid() == null : this.getServiceid().equals(other.getServiceid()))
            && (this.getStateint() == null ? other.getStateint() == null : this.getStateint().equals(other.getStateint()))
            && (this.getVipachievetypeint() == null ? other.getVipachievetypeint() == null : this.getVipachievetypeint().equals(other.getVipachievetypeint()))
            && (this.getBitstate() == null ? other.getBitstate() == null : this.getBitstate().equals(other.getBitstate()))
            && (this.getFlagint() == null ? other.getFlagint() == null : this.getFlagint().equals(other.getFlagint()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCaffp2pvipid() == null) ? 0 : getCaffp2pvipid().hashCode());
        result = prime * result + ((getCreateddatelong() == null) ? 0 : getCreateddatelong().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getBegindatelong() == null) ? 0 : getBegindatelong().hashCode());
        result = prime * result + ((getEnddatelong() == null) ? 0 : getEnddatelong().hashCode());
        result = prime * result + ((getFee() == null) ? 0 : getFee().hashCode());
        result = prime * result + ((getServiceid() == null) ? 0 : getServiceid().hashCode());
        result = prime * result + ((getStateint() == null) ? 0 : getStateint().hashCode());
        result = prime * result + ((getVipachievetypeint() == null) ? 0 : getVipachievetypeint().hashCode());
        result = prime * result + ((getBitstate() == null) ? 0 : getBitstate().hashCode());
        result = prime * result + ((getFlagint() == null) ? 0 : getFlagint().hashCode());
        return result;
    }
}