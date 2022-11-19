package com.yscf.db.model.escf2.business;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br> 
 * 线上充值备注表（CaffP2pRechargeOnlineNote） 映射基础类（JavaBean）
 * @author  JingYu.Dai
 * @date    2016年3月4日
 * @version v1.0.0
 */
public class CaffP2pRechargeOnlineNote extends BaseEntity {
    
	private static final long serialVersionUID = 4556529538834707651L;

	private Long caffp2prechargeonlinenoteid;

    private Long createddatelong;

    private Long version;

    private Long caffp2prechargeonlineid;

    private String note;

    public Long getCaffp2prechargeonlinenoteid() {
        return caffp2prechargeonlinenoteid;
    }

    public void setCaffp2prechargeonlinenoteid(Long caffp2prechargeonlinenoteid) {
        this.caffp2prechargeonlinenoteid = caffp2prechargeonlinenoteid;
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

    public Long getCaffp2prechargeonlineid() {
        return caffp2prechargeonlineid;
    }

    public void setCaffp2prechargeonlineid(Long caffp2prechargeonlineid) {
        this.caffp2prechargeonlineid = caffp2prechargeonlineid;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
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
        CaffP2pRechargeOnlineNote other = (CaffP2pRechargeOnlineNote) that;
        return (this.getCaffp2prechargeonlinenoteid() == null ? other.getCaffp2prechargeonlinenoteid() == null : this.getCaffp2prechargeonlinenoteid().equals(other.getCaffp2prechargeonlinenoteid()))
            && (this.getCreateddatelong() == null ? other.getCreateddatelong() == null : this.getCreateddatelong().equals(other.getCreateddatelong()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getCaffp2prechargeonlineid() == null ? other.getCaffp2prechargeonlineid() == null : this.getCaffp2prechargeonlineid().equals(other.getCaffp2prechargeonlineid()))
            && (this.getNote() == null ? other.getNote() == null : this.getNote().equals(other.getNote()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCaffp2prechargeonlinenoteid() == null) ? 0 : getCaffp2prechargeonlinenoteid().hashCode());
        result = prime * result + ((getCreateddatelong() == null) ? 0 : getCreateddatelong().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getCaffp2prechargeonlineid() == null) ? 0 : getCaffp2prechargeonlineid().hashCode());
        result = prime * result + ((getNote() == null) ? 0 : getNote().hashCode());
        return result;
    }
}