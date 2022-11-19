package com.yscf.db.model.escf2.user;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br> 
 * 积分明细表（CaffP2pUserIntegralDetail  映射基础类（JavaBean）
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffP2pUserIntegralDetail extends BaseEntity {
 
	private static final long serialVersionUID = -269048417687586883L;

	private Long caffp2puserintegraldetailid;

    private Long createddatelong;

    private Long version;

    private Long userid;

    private Long fkey;

    private String note;

    private Integer point;

    private Integer userintegraltypeint;

    public Long getCaffp2puserintegraldetailid() {
        return caffp2puserintegraldetailid;
    }

    public void setCaffp2puserintegraldetailid(Long caffp2puserintegraldetailid) {
        this.caffp2puserintegraldetailid = caffp2puserintegraldetailid;
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

    public Long getFkey() {
        return fkey;
    }

    public void setFkey(Long fkey) {
        this.fkey = fkey;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note == null ? null : note.trim();
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getUserintegraltypeint() {
        return userintegraltypeint;
    }

    public void setUserintegraltypeint(Integer userintegraltypeint) {
        this.userintegraltypeint = userintegraltypeint;
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
        CaffP2pUserIntegralDetail other = (CaffP2pUserIntegralDetail) that;
        return (this.getCaffp2puserintegraldetailid() == null ? other.getCaffp2puserintegraldetailid() == null : this.getCaffp2puserintegraldetailid().equals(other.getCaffp2puserintegraldetailid()))
            && (this.getCreateddatelong() == null ? other.getCreateddatelong() == null : this.getCreateddatelong().equals(other.getCreateddatelong()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getFkey() == null ? other.getFkey() == null : this.getFkey().equals(other.getFkey()))
            && (this.getNote() == null ? other.getNote() == null : this.getNote().equals(other.getNote()))
            && (this.getPoint() == null ? other.getPoint() == null : this.getPoint().equals(other.getPoint()))
            && (this.getUserintegraltypeint() == null ? other.getUserintegraltypeint() == null : this.getUserintegraltypeint().equals(other.getUserintegraltypeint()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCaffp2puserintegraldetailid() == null) ? 0 : getCaffp2puserintegraldetailid().hashCode());
        result = prime * result + ((getCreateddatelong() == null) ? 0 : getCreateddatelong().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getFkey() == null) ? 0 : getFkey().hashCode());
        result = prime * result + ((getNote() == null) ? 0 : getNote().hashCode());
        result = prime * result + ((getPoint() == null) ? 0 : getPoint().hashCode());
        result = prime * result + ((getUserintegraltypeint() == null) ? 0 : getUserintegraltypeint().hashCode());
        return result;
    }
}