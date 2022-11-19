package com.yscf.db.model.escf2.business;

import com.achievo.framework.entity.BaseEntity;
import java.math.BigDecimal;

/**
 * Description：<br> 
 * 投标记录表(CaffP2pBid) 映射基础类（JavaBean）
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffP2pBid extends BaseEntity {
  
	private static final long serialVersionUID = -1787176636821049720L;

	private Long caffp2pbidid;

    private Long createddatelong;

    private Long lastestbidtimelong;

    private Long successdatelong;

    private Long version;

    private Long userid;

    private BigDecimal amount;

    private BigDecimal autobidamount;

    private BigDecimal availableamount;

    private Integer bidcount;

    private Integer bidstateint;

    private Long bitstate;

    private Long borrowid;

    public Long getCaffp2pbidid() {
        return caffp2pbidid;
    }

    public void setCaffp2pbidid(Long caffp2pbidid) {
        this.caffp2pbidid = caffp2pbidid;
    }

    public Long getCreateddatelong() {
        return createddatelong;
    }

    public void setCreateddatelong(Long createddatelong) {
        this.createddatelong = createddatelong;
    }

    public Long getLastestbidtimelong() {
        return lastestbidtimelong;
    }

    public void setLastestbidtimelong(Long lastestbidtimelong) {
        this.lastestbidtimelong = lastestbidtimelong;
    }

    public Long getSuccessdatelong() {
        return successdatelong;
    }

    public void setSuccessdatelong(Long successdatelong) {
        this.successdatelong = successdatelong;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getAutobidamount() {
        return autobidamount;
    }

    public void setAutobidamount(BigDecimal autobidamount) {
        this.autobidamount = autobidamount;
    }

    public BigDecimal getAvailableamount() {
        return availableamount;
    }

    public void setAvailableamount(BigDecimal availableamount) {
        this.availableamount = availableamount;
    }

    public Integer getBidcount() {
        return bidcount;
    }

    public void setBidcount(Integer bidcount) {
        this.bidcount = bidcount;
    }

    public Integer getBidstateint() {
        return bidstateint;
    }

    public void setBidstateint(Integer bidstateint) {
        this.bidstateint = bidstateint;
    }

    public Long getBitstate() {
        return bitstate;
    }

    public void setBitstate(Long bitstate) {
        this.bitstate = bitstate;
    }

    public Long getBorrowid() {
        return borrowid;
    }

    public void setBorrowid(Long borrowid) {
        this.borrowid = borrowid;
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
        CaffP2pBid other = (CaffP2pBid) that;
        return (this.getCaffp2pbidid() == null ? other.getCaffp2pbidid() == null : this.getCaffp2pbidid().equals(other.getCaffp2pbidid()))
            && (this.getCreateddatelong() == null ? other.getCreateddatelong() == null : this.getCreateddatelong().equals(other.getCreateddatelong()))
            && (this.getLastestbidtimelong() == null ? other.getLastestbidtimelong() == null : this.getLastestbidtimelong().equals(other.getLastestbidtimelong()))
            && (this.getSuccessdatelong() == null ? other.getSuccessdatelong() == null : this.getSuccessdatelong().equals(other.getSuccessdatelong()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getAutobidamount() == null ? other.getAutobidamount() == null : this.getAutobidamount().equals(other.getAutobidamount()))
            && (this.getAvailableamount() == null ? other.getAvailableamount() == null : this.getAvailableamount().equals(other.getAvailableamount()))
            && (this.getBidcount() == null ? other.getBidcount() == null : this.getBidcount().equals(other.getBidcount()))
            && (this.getBidstateint() == null ? other.getBidstateint() == null : this.getBidstateint().equals(other.getBidstateint()))
            && (this.getBitstate() == null ? other.getBitstate() == null : this.getBitstate().equals(other.getBitstate()))
            && (this.getBorrowid() == null ? other.getBorrowid() == null : this.getBorrowid().equals(other.getBorrowid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCaffp2pbidid() == null) ? 0 : getCaffp2pbidid().hashCode());
        result = prime * result + ((getCreateddatelong() == null) ? 0 : getCreateddatelong().hashCode());
        result = prime * result + ((getLastestbidtimelong() == null) ? 0 : getLastestbidtimelong().hashCode());
        result = prime * result + ((getSuccessdatelong() == null) ? 0 : getSuccessdatelong().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getAutobidamount() == null) ? 0 : getAutobidamount().hashCode());
        result = prime * result + ((getAvailableamount() == null) ? 0 : getAvailableamount().hashCode());
        result = prime * result + ((getBidcount() == null) ? 0 : getBidcount().hashCode());
        result = prime * result + ((getBidstateint() == null) ? 0 : getBidstateint().hashCode());
        result = prime * result + ((getBitstate() == null) ? 0 : getBitstate().hashCode());
        result = prime * result + ((getBorrowid() == null) ? 0 : getBorrowid().hashCode());
        return result;
    }
}