package com.yscf.db.model.escf2.user;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br> 
 * 推荐人表（CaffP2pUserReferrer）  映射基础类（JavaBean）
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffP2pUserReferrer extends BaseEntity {
   
	private static final long serialVersionUID = 974536392959029159L;

	private Long caffp2puserreferrerid;

    private Long createddatelong;

    private Long version;

    private Long userid;

    private Long bitstate;

    private Long referrerid;

    public Long getCaffp2puserreferrerid() {
        return caffp2puserreferrerid;
    }

    public void setCaffp2puserreferrerid(Long caffp2puserreferrerid) {
        this.caffp2puserreferrerid = caffp2puserreferrerid;
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

    public Long getReferrerid() {
        return referrerid;
    }

    public void setReferrerid(Long referrerid) {
        this.referrerid = referrerid;
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
        CaffP2pUserReferrer other = (CaffP2pUserReferrer) that;
        return (this.getCaffp2puserreferrerid() == null ? other.getCaffp2puserreferrerid() == null : this.getCaffp2puserreferrerid().equals(other.getCaffp2puserreferrerid()))
            && (this.getCreateddatelong() == null ? other.getCreateddatelong() == null : this.getCreateddatelong().equals(other.getCreateddatelong()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getBitstate() == null ? other.getBitstate() == null : this.getBitstate().equals(other.getBitstate()))
            && (this.getReferrerid() == null ? other.getReferrerid() == null : this.getReferrerid().equals(other.getReferrerid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCaffp2puserreferrerid() == null) ? 0 : getCaffp2puserreferrerid().hashCode());
        result = prime * result + ((getCreateddatelong() == null) ? 0 : getCreateddatelong().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getBitstate() == null) ? 0 : getBitstate().hashCode());
        result = prime * result + ((getReferrerid() == null) ? 0 : getReferrerid().hashCode());
        return result;
    }
}