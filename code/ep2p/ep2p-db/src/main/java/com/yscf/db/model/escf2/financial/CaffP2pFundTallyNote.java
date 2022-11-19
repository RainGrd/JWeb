package com.yscf.db.model.escf2.financial;

import com.achievo.framework.entity.BaseEntity;

public class CaffP2pFundTallyNote extends BaseEntity {
    private Long caffp2pfundtallynoteid;

    private Long createddatelong;

    private Long version;

    private Long fundtallyid;

    private String note;

    public Long getCaffp2pfundtallynoteid() {
        return caffp2pfundtallynoteid;
    }

    public void setCaffp2pfundtallynoteid(Long caffp2pfundtallynoteid) {
        this.caffp2pfundtallynoteid = caffp2pfundtallynoteid;
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

    public Long getFundtallyid() {
        return fundtallyid;
    }

    public void setFundtallyid(Long fundtallyid) {
        this.fundtallyid = fundtallyid;
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
        CaffP2pFundTallyNote other = (CaffP2pFundTallyNote) that;
        return (this.getCaffp2pfundtallynoteid() == null ? other.getCaffp2pfundtallynoteid() == null : this.getCaffp2pfundtallynoteid().equals(other.getCaffp2pfundtallynoteid()))
            && (this.getCreateddatelong() == null ? other.getCreateddatelong() == null : this.getCreateddatelong().equals(other.getCreateddatelong()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getFundtallyid() == null ? other.getFundtallyid() == null : this.getFundtallyid().equals(other.getFundtallyid()))
            && (this.getNote() == null ? other.getNote() == null : this.getNote().equals(other.getNote()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCaffp2pfundtallynoteid() == null) ? 0 : getCaffp2pfundtallynoteid().hashCode());
        result = prime * result + ((getCreateddatelong() == null) ? 0 : getCreateddatelong().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getFundtallyid() == null) ? 0 : getFundtallyid().hashCode());
        result = prime * result + ((getNote() == null) ? 0 : getNote().hashCode());
        return result;
    }
}