package com.yscf.db.model.escf2.business;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br> 
 * 线下充值备注表（CaffP2pRechargeOfflineNote) 映射基础类（JavaBean）
 * @author  JingYu.Dai
 * @date    2016年3月4日
 * @version v1.0.0
 */
public class CaffP2pRechargeOfflineNote extends BaseEntity {
    
	private static final long serialVersionUID = -6347240177922204814L;

	private Long caffp2prechargeofflinenoteid;

    private Long createddatelong;

    private Long version;

    private Long caffp2prechargeofflineid;

    private String note;

    private String audinote;

    public Long getCaffp2prechargeofflinenoteid() {
        return caffp2prechargeofflinenoteid;
    }

    public void setCaffp2prechargeofflinenoteid(Long caffp2prechargeofflinenoteid) {
        this.caffp2prechargeofflinenoteid = caffp2prechargeofflinenoteid;
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

    public Long getCaffp2prechargeofflineid() {
        return caffp2prechargeofflineid;
    }

    public void setCaffp2prechargeofflineid(Long caffp2prechargeofflineid) {
        this.caffp2prechargeofflineid = caffp2prechargeofflineid;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public String getAudinote() {
        return audinote;
    }

    public void setAudinote(String audinote) {
        this.audinote = audinote == null ? null : audinote.trim();
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
        CaffP2pRechargeOfflineNote other = (CaffP2pRechargeOfflineNote) that;
        return (this.getCaffp2prechargeofflinenoteid() == null ? other.getCaffp2prechargeofflinenoteid() == null : this.getCaffp2prechargeofflinenoteid().equals(other.getCaffp2prechargeofflinenoteid()))
            && (this.getCreateddatelong() == null ? other.getCreateddatelong() == null : this.getCreateddatelong().equals(other.getCreateddatelong()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getCaffp2prechargeofflineid() == null ? other.getCaffp2prechargeofflineid() == null : this.getCaffp2prechargeofflineid().equals(other.getCaffp2prechargeofflineid()))
            && (this.getNote() == null ? other.getNote() == null : this.getNote().equals(other.getNote()))
            && (this.getAudinote() == null ? other.getAudinote() == null : this.getAudinote().equals(other.getAudinote()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCaffp2prechargeofflinenoteid() == null) ? 0 : getCaffp2prechargeofflinenoteid().hashCode());
        result = prime * result + ((getCreateddatelong() == null) ? 0 : getCreateddatelong().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getCaffp2prechargeofflineid() == null) ? 0 : getCaffp2prechargeofflineid().hashCode());
        result = prime * result + ((getNote() == null) ? 0 : getNote().hashCode());
        result = prime * result + ((getAudinote() == null) ? 0 : getAudinote().hashCode());
        return result;
    }
}