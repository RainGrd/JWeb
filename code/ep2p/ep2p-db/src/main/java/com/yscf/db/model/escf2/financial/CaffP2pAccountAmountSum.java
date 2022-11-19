package com.yscf.db.model.escf2.financial;

import com.achievo.framework.entity.BaseEntity;
import java.math.BigDecimal;

/**
 * Description：<br> 
 * 汇总用户的金额数据（CaffP2pAccountAmountSum） 映射基础类（JavaBean）
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffP2pAccountAmountSum extends BaseEntity {
 
	private static final long serialVersionUID = -3047382877065352618L;

	private Long caffp2paccountamountsumid;

    private Long createddatelong;

    private Long version;

    private Long userid;

    private BigDecimal availableamount;

    private BigDecimal frozenamount;

    private BigDecimal bidreward;

    private BigDecimal experienceavailabeamount;

    private BigDecimal experiencefrozenamount;

    private BigDecimal hongbaoamount;

    private BigDecimal interestdone;

    private BigDecimal interestfee;

    private BigDecimal interestpending;

    private BigDecimal latestreceipt;

    private Long latestreceiptdatelong;

    private BigDecimal receiptpendingamount;

    private BigDecimal repaypendingamount;

    public Long getCaffp2paccountamountsumid() {
        return caffp2paccountamountsumid;
    }

    public void setCaffp2paccountamountsumid(Long caffp2paccountamountsumid) {
        this.caffp2paccountamountsumid = caffp2paccountamountsumid;
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

    public BigDecimal getAvailableamount() {
        return availableamount;
    }

    public void setAvailableamount(BigDecimal availableamount) {
        this.availableamount = availableamount;
    }

    public BigDecimal getFrozenamount() {
        return frozenamount;
    }

    public void setFrozenamount(BigDecimal frozenamount) {
        this.frozenamount = frozenamount;
    }

    public BigDecimal getBidreward() {
        return bidreward;
    }

    public void setBidreward(BigDecimal bidreward) {
        this.bidreward = bidreward;
    }

    public BigDecimal getExperienceavailabeamount() {
        return experienceavailabeamount;
    }

    public void setExperienceavailabeamount(BigDecimal experienceavailabeamount) {
        this.experienceavailabeamount = experienceavailabeamount;
    }

    public BigDecimal getExperiencefrozenamount() {
        return experiencefrozenamount;
    }

    public void setExperiencefrozenamount(BigDecimal experiencefrozenamount) {
        this.experiencefrozenamount = experiencefrozenamount;
    }

    public BigDecimal getHongbaoamount() {
        return hongbaoamount;
    }

    public void setHongbaoamount(BigDecimal hongbaoamount) {
        this.hongbaoamount = hongbaoamount;
    }

    public BigDecimal getInterestdone() {
        return interestdone;
    }

    public void setInterestdone(BigDecimal interestdone) {
        this.interestdone = interestdone;
    }

    public BigDecimal getInterestfee() {
        return interestfee;
    }

    public void setInterestfee(BigDecimal interestfee) {
        this.interestfee = interestfee;
    }

    public BigDecimal getInterestpending() {
        return interestpending;
    }

    public void setInterestpending(BigDecimal interestpending) {
        this.interestpending = interestpending;
    }

    public BigDecimal getLatestreceipt() {
        return latestreceipt;
    }

    public void setLatestreceipt(BigDecimal latestreceipt) {
        this.latestreceipt = latestreceipt;
    }

    public Long getLatestreceiptdatelong() {
        return latestreceiptdatelong;
    }

    public void setLatestreceiptdatelong(Long latestreceiptdatelong) {
        this.latestreceiptdatelong = latestreceiptdatelong;
    }

    public BigDecimal getReceiptpendingamount() {
        return receiptpendingamount;
    }

    public void setReceiptpendingamount(BigDecimal receiptpendingamount) {
        this.receiptpendingamount = receiptpendingamount;
    }

    public BigDecimal getRepaypendingamount() {
        return repaypendingamount;
    }

    public void setRepaypendingamount(BigDecimal repaypendingamount) {
        this.repaypendingamount = repaypendingamount;
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
        CaffP2pAccountAmountSum other = (CaffP2pAccountAmountSum) that;
        return (this.getCaffp2paccountamountsumid() == null ? other.getCaffp2paccountamountsumid() == null : this.getCaffp2paccountamountsumid().equals(other.getCaffp2paccountamountsumid()))
            && (this.getCreateddatelong() == null ? other.getCreateddatelong() == null : this.getCreateddatelong().equals(other.getCreateddatelong()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getAvailableamount() == null ? other.getAvailableamount() == null : this.getAvailableamount().equals(other.getAvailableamount()))
            && (this.getFrozenamount() == null ? other.getFrozenamount() == null : this.getFrozenamount().equals(other.getFrozenamount()))
            && (this.getBidreward() == null ? other.getBidreward() == null : this.getBidreward().equals(other.getBidreward()))
            && (this.getExperienceavailabeamount() == null ? other.getExperienceavailabeamount() == null : this.getExperienceavailabeamount().equals(other.getExperienceavailabeamount()))
            && (this.getExperiencefrozenamount() == null ? other.getExperiencefrozenamount() == null : this.getExperiencefrozenamount().equals(other.getExperiencefrozenamount()))
            && (this.getHongbaoamount() == null ? other.getHongbaoamount() == null : this.getHongbaoamount().equals(other.getHongbaoamount()))
            && (this.getInterestdone() == null ? other.getInterestdone() == null : this.getInterestdone().equals(other.getInterestdone()))
            && (this.getInterestfee() == null ? other.getInterestfee() == null : this.getInterestfee().equals(other.getInterestfee()))
            && (this.getInterestpending() == null ? other.getInterestpending() == null : this.getInterestpending().equals(other.getInterestpending()))
            && (this.getLatestreceipt() == null ? other.getLatestreceipt() == null : this.getLatestreceipt().equals(other.getLatestreceipt()))
            && (this.getLatestreceiptdatelong() == null ? other.getLatestreceiptdatelong() == null : this.getLatestreceiptdatelong().equals(other.getLatestreceiptdatelong()))
            && (this.getReceiptpendingamount() == null ? other.getReceiptpendingamount() == null : this.getReceiptpendingamount().equals(other.getReceiptpendingamount()))
            && (this.getRepaypendingamount() == null ? other.getRepaypendingamount() == null : this.getRepaypendingamount().equals(other.getRepaypendingamount()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCaffp2paccountamountsumid() == null) ? 0 : getCaffp2paccountamountsumid().hashCode());
        result = prime * result + ((getCreateddatelong() == null) ? 0 : getCreateddatelong().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getAvailableamount() == null) ? 0 : getAvailableamount().hashCode());
        result = prime * result + ((getFrozenamount() == null) ? 0 : getFrozenamount().hashCode());
        result = prime * result + ((getBidreward() == null) ? 0 : getBidreward().hashCode());
        result = prime * result + ((getExperienceavailabeamount() == null) ? 0 : getExperienceavailabeamount().hashCode());
        result = prime * result + ((getExperiencefrozenamount() == null) ? 0 : getExperiencefrozenamount().hashCode());
        result = prime * result + ((getHongbaoamount() == null) ? 0 : getHongbaoamount().hashCode());
        result = prime * result + ((getInterestdone() == null) ? 0 : getInterestdone().hashCode());
        result = prime * result + ((getInterestfee() == null) ? 0 : getInterestfee().hashCode());
        result = prime * result + ((getInterestpending() == null) ? 0 : getInterestpending().hashCode());
        result = prime * result + ((getLatestreceipt() == null) ? 0 : getLatestreceipt().hashCode());
        result = prime * result + ((getLatestreceiptdatelong() == null) ? 0 : getLatestreceiptdatelong().hashCode());
        result = prime * result + ((getReceiptpendingamount() == null) ? 0 : getReceiptpendingamount().hashCode());
        result = prime * result + ((getRepaypendingamount() == null) ? 0 : getRepaypendingamount().hashCode());
        return result;
    }
}