package com.yscf.db.model.escf2.user;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br> 
 * 积分概况表（CaffP2pUserIntegral）  映射基础类（JavaBean）
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffP2pUserIntegral extends BaseEntity {

	private static final long serialVersionUID = -5625476546531920334L;

	private Long caffp2puserintegralid;

    private Long createddatelong;

    private Long version;

    private Long userid;

    private Integer borrowintegral;

    private Integer borrowintegralchecked;

    private Integer giftintegral;

    private Integer giftintegralchecked;

    private Integer investintegral;

    private Integer investintegralchecked;

    public Long getCaffp2puserintegralid() {
        return caffp2puserintegralid;
    }

    public void setCaffp2puserintegralid(Long caffp2puserintegralid) {
        this.caffp2puserintegralid = caffp2puserintegralid;
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

    public Integer getBorrowintegral() {
        return borrowintegral;
    }

    public void setBorrowintegral(Integer borrowintegral) {
        this.borrowintegral = borrowintegral;
    }

    public Integer getBorrowintegralchecked() {
        return borrowintegralchecked;
    }

    public void setBorrowintegralchecked(Integer borrowintegralchecked) {
        this.borrowintegralchecked = borrowintegralchecked;
    }

    public Integer getGiftintegral() {
        return giftintegral;
    }

    public void setGiftintegral(Integer giftintegral) {
        this.giftintegral = giftintegral;
    }

    public Integer getGiftintegralchecked() {
        return giftintegralchecked;
    }

    public void setGiftintegralchecked(Integer giftintegralchecked) {
        this.giftintegralchecked = giftintegralchecked;
    }

    public Integer getInvestintegral() {
        return investintegral;
    }

    public void setInvestintegral(Integer investintegral) {
        this.investintegral = investintegral;
    }

    public Integer getInvestintegralchecked() {
        return investintegralchecked;
    }

    public void setInvestintegralchecked(Integer investintegralchecked) {
        this.investintegralchecked = investintegralchecked;
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
        CaffP2pUserIntegral other = (CaffP2pUserIntegral) that;
        return (this.getCaffp2puserintegralid() == null ? other.getCaffp2puserintegralid() == null : this.getCaffp2puserintegralid().equals(other.getCaffp2puserintegralid()))
            && (this.getCreateddatelong() == null ? other.getCreateddatelong() == null : this.getCreateddatelong().equals(other.getCreateddatelong()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getBorrowintegral() == null ? other.getBorrowintegral() == null : this.getBorrowintegral().equals(other.getBorrowintegral()))
            && (this.getBorrowintegralchecked() == null ? other.getBorrowintegralchecked() == null : this.getBorrowintegralchecked().equals(other.getBorrowintegralchecked()))
            && (this.getGiftintegral() == null ? other.getGiftintegral() == null : this.getGiftintegral().equals(other.getGiftintegral()))
            && (this.getGiftintegralchecked() == null ? other.getGiftintegralchecked() == null : this.getGiftintegralchecked().equals(other.getGiftintegralchecked()))
            && (this.getInvestintegral() == null ? other.getInvestintegral() == null : this.getInvestintegral().equals(other.getInvestintegral()))
            && (this.getInvestintegralchecked() == null ? other.getInvestintegralchecked() == null : this.getInvestintegralchecked().equals(other.getInvestintegralchecked()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCaffp2puserintegralid() == null) ? 0 : getCaffp2puserintegralid().hashCode());
        result = prime * result + ((getCreateddatelong() == null) ? 0 : getCreateddatelong().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getBorrowintegral() == null) ? 0 : getBorrowintegral().hashCode());
        result = prime * result + ((getBorrowintegralchecked() == null) ? 0 : getBorrowintegralchecked().hashCode());
        result = prime * result + ((getGiftintegral() == null) ? 0 : getGiftintegral().hashCode());
        result = prime * result + ((getGiftintegralchecked() == null) ? 0 : getGiftintegralchecked().hashCode());
        result = prime * result + ((getInvestintegral() == null) ? 0 : getInvestintegral().hashCode());
        result = prime * result + ((getInvestintegralchecked() == null) ? 0 : getInvestintegralchecked().hashCode());
        return result;
    }
}