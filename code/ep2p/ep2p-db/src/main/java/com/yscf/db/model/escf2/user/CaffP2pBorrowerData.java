package com.yscf.db.model.escf2.user;

import com.achievo.framework.entity.BaseEntity;
import java.math.BigDecimal;

/**
 * Description：<br> 
 * 借款者表（CaffP2pBorrowerData）  映射基础类（JavaBean）
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffP2pBorrowerData extends BaseEntity {
   
	private static final long serialVersionUID = -3505665734039829726L;

	private Long caffp2pborrowerdataid;

    private Long createddatelong;

    private Long version;

    private Long userid;

    private BigDecimal availablecreditamount;

    private Integer borrowertypeint;

    private BigDecimal maxcreditamount;

    public Long getCaffp2pborrowerdataid() {
        return caffp2pborrowerdataid;
    }

    public void setCaffp2pborrowerdataid(Long caffp2pborrowerdataid) {
        this.caffp2pborrowerdataid = caffp2pborrowerdataid;
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

    public BigDecimal getAvailablecreditamount() {
        return availablecreditamount;
    }

    public void setAvailablecreditamount(BigDecimal availablecreditamount) {
        this.availablecreditamount = availablecreditamount;
    }

    public Integer getBorrowertypeint() {
        return borrowertypeint;
    }

    public void setBorrowertypeint(Integer borrowertypeint) {
        this.borrowertypeint = borrowertypeint;
    }

    public BigDecimal getMaxcreditamount() {
        return maxcreditamount;
    }

    public void setMaxcreditamount(BigDecimal maxcreditamount) {
        this.maxcreditamount = maxcreditamount;
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
        CaffP2pBorrowerData other = (CaffP2pBorrowerData) that;
        return (this.getCaffp2pborrowerdataid() == null ? other.getCaffp2pborrowerdataid() == null : this.getCaffp2pborrowerdataid().equals(other.getCaffp2pborrowerdataid()))
            && (this.getCreateddatelong() == null ? other.getCreateddatelong() == null : this.getCreateddatelong().equals(other.getCreateddatelong()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getAvailablecreditamount() == null ? other.getAvailablecreditamount() == null : this.getAvailablecreditamount().equals(other.getAvailablecreditamount()))
            && (this.getBorrowertypeint() == null ? other.getBorrowertypeint() == null : this.getBorrowertypeint().equals(other.getBorrowertypeint()))
            && (this.getMaxcreditamount() == null ? other.getMaxcreditamount() == null : this.getMaxcreditamount().equals(other.getMaxcreditamount()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCaffp2pborrowerdataid() == null) ? 0 : getCaffp2pborrowerdataid().hashCode());
        result = prime * result + ((getCreateddatelong() == null) ? 0 : getCreateddatelong().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getAvailablecreditamount() == null) ? 0 : getAvailablecreditamount().hashCode());
        result = prime * result + ((getBorrowertypeint() == null) ? 0 : getBorrowertypeint().hashCode());
        result = prime * result + ((getMaxcreditamount() == null) ? 0 : getMaxcreditamount().hashCode());
        return result;
    }
}