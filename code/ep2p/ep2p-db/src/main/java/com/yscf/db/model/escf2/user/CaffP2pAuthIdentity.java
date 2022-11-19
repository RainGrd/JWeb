package com.yscf.db.model.escf2.user;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br> 
 * 身份认证表(CaffP2pAuthIdentity)	映射基础类（JavaBean）
 * @author  JingYu.Dai
 * @date    2016年3月2日
 * @version v1.0.0
 */
public class CaffP2pAuthIdentity extends BaseEntity {

	private static final long serialVersionUID = 3195501260225565433L;

	private Long caffp2pauthidentityid;

    private Long createddatelong;

    private Long version;

    private Integer auditstateint;

    private String originalrealname;

    private String newrealname;

    private Long userid;

    private String note;

    public Long getCaffp2pauthidentityid() {
        return caffp2pauthidentityid;
    }

    public void setCaffp2pauthidentityid(Long caffp2pauthidentityid) {
        this.caffp2pauthidentityid = caffp2pauthidentityid;
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

    public Integer getAuditstateint() {
        return auditstateint;
    }

    public void setAuditstateint(Integer auditstateint) {
        this.auditstateint = auditstateint;
    }

    public String getOriginalrealname() {
        return originalrealname;
    }

    public void setOriginalrealname(String originalrealname) {
        this.originalrealname = originalrealname == null ? null : originalrealname.trim();
    }

    public String getNewrealname() {
        return newrealname;
    }

    public void setNewrealname(String newrealname) {
        this.newrealname = newrealname == null ? null : newrealname.trim();
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
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
        CaffP2pAuthIdentity other = (CaffP2pAuthIdentity) that;
        return (this.getCaffp2pauthidentityid() == null ? other.getCaffp2pauthidentityid() == null : this.getCaffp2pauthidentityid().equals(other.getCaffp2pauthidentityid()))
            && (this.getCreateddatelong() == null ? other.getCreateddatelong() == null : this.getCreateddatelong().equals(other.getCreateddatelong()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getAuditstateint() == null ? other.getAuditstateint() == null : this.getAuditstateint().equals(other.getAuditstateint()))
            && (this.getOriginalrealname() == null ? other.getOriginalrealname() == null : this.getOriginalrealname().equals(other.getOriginalrealname()))
            && (this.getNewrealname() == null ? other.getNewrealname() == null : this.getNewrealname().equals(other.getNewrealname()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getNote() == null ? other.getNote() == null : this.getNote().equals(other.getNote()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCaffp2pauthidentityid() == null) ? 0 : getCaffp2pauthidentityid().hashCode());
        result = prime * result + ((getCreateddatelong() == null) ? 0 : getCreateddatelong().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getAuditstateint() == null) ? 0 : getAuditstateint().hashCode());
        result = prime * result + ((getOriginalrealname() == null) ? 0 : getOriginalrealname().hashCode());
        result = prime * result + ((getNewrealname() == null) ? 0 : getNewrealname().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getNote() == null) ? 0 : getNote().hashCode());
        return result;
    }
}