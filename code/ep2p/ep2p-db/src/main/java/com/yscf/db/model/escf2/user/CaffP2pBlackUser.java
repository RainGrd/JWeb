package com.yscf.db.model.escf2.user;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br> 
 * 黑名单用户表（CaffP2pBlackUser）
 * @author  JingYu.Dai
 * @date    2016年3月2日
 * @version v1.0.0
 */
public class CaffP2pBlackUser extends BaseEntity {
    
	private static final long serialVersionUID = 1796204723421796395L;

	private Long caffp2pblackuserid;

    private Long createddatelong;

    private Long version;

    private Long userid;

    public Long getCaffp2pblackuserid() {
        return caffp2pblackuserid;
    }

    public void setCaffp2pblackuserid(Long caffp2pblackuserid) {
        this.caffp2pblackuserid = caffp2pblackuserid;
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
        CaffP2pBlackUser other = (CaffP2pBlackUser) that;
        return (this.getCaffp2pblackuserid() == null ? other.getCaffp2pblackuserid() == null : this.getCaffp2pblackuserid().equals(other.getCaffp2pblackuserid()))
            && (this.getCreateddatelong() == null ? other.getCreateddatelong() == null : this.getCreateddatelong().equals(other.getCreateddatelong()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCaffp2pblackuserid() == null) ? 0 : getCaffp2pblackuserid().hashCode());
        result = prime * result + ((getCreateddatelong() == null) ? 0 : getCreateddatelong().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        return result;
    }
}