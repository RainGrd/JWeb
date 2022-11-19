package com.yscf.db.model.escf2.system;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br> 
 * 借款者图片材料表(CaffP2pBorrowerImage) 映射基础类（JavaBean）
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffP2pBorrowerImage extends BaseEntity {
    
	private static final long serialVersionUID = 56507428467072839L;

	private Long caffp2pborrowerimageid;

    private Long createddatelong;

    private Long version;

    private Long userid;

    private Long borrowid;

    private Long fileid;

    private Integer indexorder;

    public Long getCaffp2pborrowerimageid() {
        return caffp2pborrowerimageid;
    }

    public void setCaffp2pborrowerimageid(Long caffp2pborrowerimageid) {
        this.caffp2pborrowerimageid = caffp2pborrowerimageid;
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

    public Long getBorrowid() {
        return borrowid;
    }

    public void setBorrowid(Long borrowid) {
        this.borrowid = borrowid;
    }

    public Long getFileid() {
        return fileid;
    }

    public void setFileid(Long fileid) {
        this.fileid = fileid;
    }

    public Integer getIndexorder() {
        return indexorder;
    }

    public void setIndexorder(Integer indexorder) {
        this.indexorder = indexorder;
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
        CaffP2pBorrowerImage other = (CaffP2pBorrowerImage) that;
        return (this.getCaffp2pborrowerimageid() == null ? other.getCaffp2pborrowerimageid() == null : this.getCaffp2pborrowerimageid().equals(other.getCaffp2pborrowerimageid()))
            && (this.getCreateddatelong() == null ? other.getCreateddatelong() == null : this.getCreateddatelong().equals(other.getCreateddatelong()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getBorrowid() == null ? other.getBorrowid() == null : this.getBorrowid().equals(other.getBorrowid()))
            && (this.getFileid() == null ? other.getFileid() == null : this.getFileid().equals(other.getFileid()))
            && (this.getIndexorder() == null ? other.getIndexorder() == null : this.getIndexorder().equals(other.getIndexorder()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCaffp2pborrowerimageid() == null) ? 0 : getCaffp2pborrowerimageid().hashCode());
        result = prime * result + ((getCreateddatelong() == null) ? 0 : getCreateddatelong().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getBorrowid() == null) ? 0 : getBorrowid().hashCode());
        result = prime * result + ((getFileid() == null) ? 0 : getFileid().hashCode());
        result = prime * result + ((getIndexorder() == null) ? 0 : getIndexorder().hashCode());
        return result;
    }
}