package com.yscf.db.model.escf2.business;

import com.achievo.framework.entity.BaseEntity;
import java.math.BigDecimal;

/**
 * Description：<br> 
 * 投标信息表（CaffP2pBorrowBiddingInfo）
 * @author  JingYu.Dai
 * @date    2016年3月4日
 * @version v1.0.0
 */
public class CaffP2pBorrowBiddingInfo extends BaseEntity {
   
	private static final long serialVersionUID = -7026314399724098604L;

	private Long caffp2pborrowbiddinginfoid;

    private Long createddatelong;

    private Long version;

    private Integer bidcount;

    private BigDecimal borrowamount;

    private Long borrowid;

    private BigDecimal currentamount;

    public Long getCaffp2pborrowbiddinginfoid() {
        return caffp2pborrowbiddinginfoid;
    }

    public void setCaffp2pborrowbiddinginfoid(Long caffp2pborrowbiddinginfoid) {
        this.caffp2pborrowbiddinginfoid = caffp2pborrowbiddinginfoid;
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

    public Integer getBidcount() {
        return bidcount;
    }

    public void setBidcount(Integer bidcount) {
        this.bidcount = bidcount;
    }

    public BigDecimal getBorrowamount() {
        return borrowamount;
    }

    public void setBorrowamount(BigDecimal borrowamount) {
        this.borrowamount = borrowamount;
    }

    public Long getBorrowid() {
        return borrowid;
    }

    public void setBorrowid(Long borrowid) {
        this.borrowid = borrowid;
    }

    public BigDecimal getCurrentamount() {
        return currentamount;
    }

    public void setCurrentamount(BigDecimal currentamount) {
        this.currentamount = currentamount;
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
        CaffP2pBorrowBiddingInfo other = (CaffP2pBorrowBiddingInfo) that;
        return (this.getCaffp2pborrowbiddinginfoid() == null ? other.getCaffp2pborrowbiddinginfoid() == null : this.getCaffp2pborrowbiddinginfoid().equals(other.getCaffp2pborrowbiddinginfoid()))
            && (this.getCreateddatelong() == null ? other.getCreateddatelong() == null : this.getCreateddatelong().equals(other.getCreateddatelong()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getBidcount() == null ? other.getBidcount() == null : this.getBidcount().equals(other.getBidcount()))
            && (this.getBorrowamount() == null ? other.getBorrowamount() == null : this.getBorrowamount().equals(other.getBorrowamount()))
            && (this.getBorrowid() == null ? other.getBorrowid() == null : this.getBorrowid().equals(other.getBorrowid()))
            && (this.getCurrentamount() == null ? other.getCurrentamount() == null : this.getCurrentamount().equals(other.getCurrentamount()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCaffp2pborrowbiddinginfoid() == null) ? 0 : getCaffp2pborrowbiddinginfoid().hashCode());
        result = prime * result + ((getCreateddatelong() == null) ? 0 : getCreateddatelong().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getBidcount() == null) ? 0 : getBidcount().hashCode());
        result = prime * result + ((getBorrowamount() == null) ? 0 : getBorrowamount().hashCode());
        result = prime * result + ((getBorrowid() == null) ? 0 : getBorrowid().hashCode());
        result = prime * result + ((getCurrentamount() == null) ? 0 : getCurrentamount().hashCode());
        return result;
    }
}