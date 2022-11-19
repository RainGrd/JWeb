package com.yscf.db.model.escf2.business;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br> 
 * 借款详情表(CaffP2pBorrowDescription)  映射基础类（JavaBean）
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffP2pBorrowDescription extends BaseEntity {
   
	private static final long serialVersionUID = 5529814445600223998L;

	private Long caffp2pborrowdescriptionid;

    private Long createddatelong;

    private Long version;

    private Long borrowid;

    private String counterguarantee;

    private String description;

    private String risknote;

    public Long getCaffp2pborrowdescriptionid() {
        return caffp2pborrowdescriptionid;
    }

    public void setCaffp2pborrowdescriptionid(Long caffp2pborrowdescriptionid) {
        this.caffp2pborrowdescriptionid = caffp2pborrowdescriptionid;
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

    public Long getBorrowid() {
        return borrowid;
    }

    public void setBorrowid(Long borrowid) {
        this.borrowid = borrowid;
    }

    public String getCounterguarantee() {
        return counterguarantee;
    }

    public void setCounterguarantee(String counterguarantee) {
        this.counterguarantee = counterguarantee == null ? null : counterguarantee.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getRisknote() {
        return risknote;
    }

    public void setRisknote(String risknote) {
        this.risknote = risknote == null ? null : risknote.trim();
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
        CaffP2pBorrowDescription other = (CaffP2pBorrowDescription) that;
        return (this.getCaffp2pborrowdescriptionid() == null ? other.getCaffp2pborrowdescriptionid() == null : this.getCaffp2pborrowdescriptionid().equals(other.getCaffp2pborrowdescriptionid()))
            && (this.getCreateddatelong() == null ? other.getCreateddatelong() == null : this.getCreateddatelong().equals(other.getCreateddatelong()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getBorrowid() == null ? other.getBorrowid() == null : this.getBorrowid().equals(other.getBorrowid()))
            && (this.getCounterguarantee() == null ? other.getCounterguarantee() == null : this.getCounterguarantee().equals(other.getCounterguarantee()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getRisknote() == null ? other.getRisknote() == null : this.getRisknote().equals(other.getRisknote()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCaffp2pborrowdescriptionid() == null) ? 0 : getCaffp2pborrowdescriptionid().hashCode());
        result = prime * result + ((getCreateddatelong() == null) ? 0 : getCreateddatelong().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getBorrowid() == null) ? 0 : getBorrowid().hashCode());
        result = prime * result + ((getCounterguarantee() == null) ? 0 : getCounterguarantee().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getRisknote() == null) ? 0 : getRisknote().hashCode());
        return result;
    }
}