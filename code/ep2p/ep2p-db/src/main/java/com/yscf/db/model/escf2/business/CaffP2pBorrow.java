package com.yscf.db.model.escf2.business;

import com.achievo.framework.entity.BaseEntity;
import java.math.BigDecimal;

/**
 * Description：<br> 
 * 借款信息表(CaffP2pBorrow) 映射基础类（JavaBean）
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffP2pBorrow extends BaseEntity {
    
	private static final long serialVersionUID = -7306283311309852307L;

	private Long caffp2pborrowid;

    private Long createddatelong;

    private Long version;

    private Long userid;

    private BigDecimal amount;

    private BigDecimal successamount;

    private Integer bidcount;

    private Integer biddays;

    private Integer borrowcount;

    private Long bidexpiredatelong;

    private Long bidtimelong;

    private Long successdatelong;

    private Long bondingcompanyid;

    private Integer borrowstateint;

    private Integer borrowtypeint;

    private Integer repayguaranteetypeint;

    private Integer interestcalculationtypeint;

    private BigDecimal maxbidamount;

    private BigDecimal minbidamount;

    private String password;

    private Integer purposetypeint;

    private BigDecimal rate;

    private BigDecimal rewardrate;

    private Long rootborrowid;

    private Long createuserid;

    private Integer timecount;

    private String title;

    private Long reference1;

    private Long reference2;

    private Long reference3;

    private Long bitstate;

    private String cdregistertime;

    private String cdregistertimeend;

    private String cdbidamount;

    public Long getCaffp2pborrowid() {
        return caffp2pborrowid;
    }

    public void setCaffp2pborrowid(Long caffp2pborrowid) {
        this.caffp2pborrowid = caffp2pborrowid;
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

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getSuccessamount() {
        return successamount;
    }

    public void setSuccessamount(BigDecimal successamount) {
        this.successamount = successamount;
    }

    public Integer getBidcount() {
        return bidcount;
    }

    public void setBidcount(Integer bidcount) {
        this.bidcount = bidcount;
    }

    public Integer getBiddays() {
        return biddays;
    }

    public void setBiddays(Integer biddays) {
        this.biddays = biddays;
    }

    public Integer getBorrowcount() {
        return borrowcount;
    }

    public void setBorrowcount(Integer borrowcount) {
        this.borrowcount = borrowcount;
    }

    public Long getBidexpiredatelong() {
        return bidexpiredatelong;
    }

    public void setBidexpiredatelong(Long bidexpiredatelong) {
        this.bidexpiredatelong = bidexpiredatelong;
    }

    public Long getBidtimelong() {
        return bidtimelong;
    }

    public void setBidtimelong(Long bidtimelong) {
        this.bidtimelong = bidtimelong;
    }

    public Long getSuccessdatelong() {
        return successdatelong;
    }

    public void setSuccessdatelong(Long successdatelong) {
        this.successdatelong = successdatelong;
    }

    public Long getBondingcompanyid() {
        return bondingcompanyid;
    }

    public void setBondingcompanyid(Long bondingcompanyid) {
        this.bondingcompanyid = bondingcompanyid;
    }

    public Integer getBorrowstateint() {
        return borrowstateint;
    }

    public void setBorrowstateint(Integer borrowstateint) {
        this.borrowstateint = borrowstateint;
    }

    public Integer getBorrowtypeint() {
        return borrowtypeint;
    }

    public void setBorrowtypeint(Integer borrowtypeint) {
        this.borrowtypeint = borrowtypeint;
    }

    public Integer getRepayguaranteetypeint() {
        return repayguaranteetypeint;
    }

    public void setRepayguaranteetypeint(Integer repayguaranteetypeint) {
        this.repayguaranteetypeint = repayguaranteetypeint;
    }

    public Integer getInterestcalculationtypeint() {
        return interestcalculationtypeint;
    }

    public void setInterestcalculationtypeint(Integer interestcalculationtypeint) {
        this.interestcalculationtypeint = interestcalculationtypeint;
    }

    public BigDecimal getMaxbidamount() {
        return maxbidamount;
    }

    public void setMaxbidamount(BigDecimal maxbidamount) {
        this.maxbidamount = maxbidamount;
    }

    public BigDecimal getMinbidamount() {
        return minbidamount;
    }

    public void setMinbidamount(BigDecimal minbidamount) {
        this.minbidamount = minbidamount;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getPurposetypeint() {
        return purposetypeint;
    }

    public void setPurposetypeint(Integer purposetypeint) {
        this.purposetypeint = purposetypeint;
    }

    public BigDecimal getRate() {
        return rate;
    }

    public void setRate(BigDecimal rate) {
        this.rate = rate;
    }

    public BigDecimal getRewardrate() {
        return rewardrate;
    }

    public void setRewardrate(BigDecimal rewardrate) {
        this.rewardrate = rewardrate;
    }

    public Long getRootborrowid() {
        return rootborrowid;
    }

    public void setRootborrowid(Long rootborrowid) {
        this.rootborrowid = rootborrowid;
    }

    public Long getCreateuserid() {
        return createuserid;
    }

    public void setCreateuserid(Long createuserid) {
        this.createuserid = createuserid;
    }

    public Integer getTimecount() {
        return timecount;
    }

    public void setTimecount(Integer timecount) {
        this.timecount = timecount;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title == null ? null : title.trim();
    }

    public Long getReference1() {
        return reference1;
    }

    public void setReference1(Long reference1) {
        this.reference1 = reference1;
    }

    public Long getReference2() {
        return reference2;
    }

    public void setReference2(Long reference2) {
        this.reference2 = reference2;
    }

    public Long getReference3() {
        return reference3;
    }

    public void setReference3(Long reference3) {
        this.reference3 = reference3;
    }

    public Long getBitstate() {
        return bitstate;
    }

    public void setBitstate(Long bitstate) {
        this.bitstate = bitstate;
    }

    public String getCdregistertime() {
        return cdregistertime;
    }

    public void setCdregistertime(String cdregistertime) {
        this.cdregistertime = cdregistertime == null ? null : cdregistertime.trim();
    }

    public String getCdregistertimeend() {
        return cdregistertimeend;
    }

    public void setCdregistertimeend(String cdregistertimeend) {
        this.cdregistertimeend = cdregistertimeend == null ? null : cdregistertimeend.trim();
    }

    public String getCdbidamount() {
        return cdbidamount;
    }

    public void setCdbidamount(String cdbidamount) {
        this.cdbidamount = cdbidamount == null ? null : cdbidamount.trim();
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
        CaffP2pBorrow other = (CaffP2pBorrow) that;
        return (this.getCaffp2pborrowid() == null ? other.getCaffp2pborrowid() == null : this.getCaffp2pborrowid().equals(other.getCaffp2pborrowid()))
            && (this.getCreateddatelong() == null ? other.getCreateddatelong() == null : this.getCreateddatelong().equals(other.getCreateddatelong()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getSuccessamount() == null ? other.getSuccessamount() == null : this.getSuccessamount().equals(other.getSuccessamount()))
            && (this.getBidcount() == null ? other.getBidcount() == null : this.getBidcount().equals(other.getBidcount()))
            && (this.getBiddays() == null ? other.getBiddays() == null : this.getBiddays().equals(other.getBiddays()))
            && (this.getBorrowcount() == null ? other.getBorrowcount() == null : this.getBorrowcount().equals(other.getBorrowcount()))
            && (this.getBidexpiredatelong() == null ? other.getBidexpiredatelong() == null : this.getBidexpiredatelong().equals(other.getBidexpiredatelong()))
            && (this.getBidtimelong() == null ? other.getBidtimelong() == null : this.getBidtimelong().equals(other.getBidtimelong()))
            && (this.getSuccessdatelong() == null ? other.getSuccessdatelong() == null : this.getSuccessdatelong().equals(other.getSuccessdatelong()))
            && (this.getBondingcompanyid() == null ? other.getBondingcompanyid() == null : this.getBondingcompanyid().equals(other.getBondingcompanyid()))
            && (this.getBorrowstateint() == null ? other.getBorrowstateint() == null : this.getBorrowstateint().equals(other.getBorrowstateint()))
            && (this.getBorrowtypeint() == null ? other.getBorrowtypeint() == null : this.getBorrowtypeint().equals(other.getBorrowtypeint()))
            && (this.getRepayguaranteetypeint() == null ? other.getRepayguaranteetypeint() == null : this.getRepayguaranteetypeint().equals(other.getRepayguaranteetypeint()))
            && (this.getInterestcalculationtypeint() == null ? other.getInterestcalculationtypeint() == null : this.getInterestcalculationtypeint().equals(other.getInterestcalculationtypeint()))
            && (this.getMaxbidamount() == null ? other.getMaxbidamount() == null : this.getMaxbidamount().equals(other.getMaxbidamount()))
            && (this.getMinbidamount() == null ? other.getMinbidamount() == null : this.getMinbidamount().equals(other.getMinbidamount()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getPurposetypeint() == null ? other.getPurposetypeint() == null : this.getPurposetypeint().equals(other.getPurposetypeint()))
            && (this.getRate() == null ? other.getRate() == null : this.getRate().equals(other.getRate()))
            && (this.getRewardrate() == null ? other.getRewardrate() == null : this.getRewardrate().equals(other.getRewardrate()))
            && (this.getRootborrowid() == null ? other.getRootborrowid() == null : this.getRootborrowid().equals(other.getRootborrowid()))
            && (this.getCreateuserid() == null ? other.getCreateuserid() == null : this.getCreateuserid().equals(other.getCreateuserid()))
            && (this.getTimecount() == null ? other.getTimecount() == null : this.getTimecount().equals(other.getTimecount()))
            && (this.getTitle() == null ? other.getTitle() == null : this.getTitle().equals(other.getTitle()))
            && (this.getReference1() == null ? other.getReference1() == null : this.getReference1().equals(other.getReference1()))
            && (this.getReference2() == null ? other.getReference2() == null : this.getReference2().equals(other.getReference2()))
            && (this.getReference3() == null ? other.getReference3() == null : this.getReference3().equals(other.getReference3()))
            && (this.getBitstate() == null ? other.getBitstate() == null : this.getBitstate().equals(other.getBitstate()))
            && (this.getCdregistertime() == null ? other.getCdregistertime() == null : this.getCdregistertime().equals(other.getCdregistertime()))
            && (this.getCdregistertimeend() == null ? other.getCdregistertimeend() == null : this.getCdregistertimeend().equals(other.getCdregistertimeend()))
            && (this.getCdbidamount() == null ? other.getCdbidamount() == null : this.getCdbidamount().equals(other.getCdbidamount()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCaffp2pborrowid() == null) ? 0 : getCaffp2pborrowid().hashCode());
        result = prime * result + ((getCreateddatelong() == null) ? 0 : getCreateddatelong().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getSuccessamount() == null) ? 0 : getSuccessamount().hashCode());
        result = prime * result + ((getBidcount() == null) ? 0 : getBidcount().hashCode());
        result = prime * result + ((getBiddays() == null) ? 0 : getBiddays().hashCode());
        result = prime * result + ((getBorrowcount() == null) ? 0 : getBorrowcount().hashCode());
        result = prime * result + ((getBidexpiredatelong() == null) ? 0 : getBidexpiredatelong().hashCode());
        result = prime * result + ((getBidtimelong() == null) ? 0 : getBidtimelong().hashCode());
        result = prime * result + ((getSuccessdatelong() == null) ? 0 : getSuccessdatelong().hashCode());
        result = prime * result + ((getBondingcompanyid() == null) ? 0 : getBondingcompanyid().hashCode());
        result = prime * result + ((getBorrowstateint() == null) ? 0 : getBorrowstateint().hashCode());
        result = prime * result + ((getBorrowtypeint() == null) ? 0 : getBorrowtypeint().hashCode());
        result = prime * result + ((getRepayguaranteetypeint() == null) ? 0 : getRepayguaranteetypeint().hashCode());
        result = prime * result + ((getInterestcalculationtypeint() == null) ? 0 : getInterestcalculationtypeint().hashCode());
        result = prime * result + ((getMaxbidamount() == null) ? 0 : getMaxbidamount().hashCode());
        result = prime * result + ((getMinbidamount() == null) ? 0 : getMinbidamount().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getPurposetypeint() == null) ? 0 : getPurposetypeint().hashCode());
        result = prime * result + ((getRate() == null) ? 0 : getRate().hashCode());
        result = prime * result + ((getRewardrate() == null) ? 0 : getRewardrate().hashCode());
        result = prime * result + ((getRootborrowid() == null) ? 0 : getRootborrowid().hashCode());
        result = prime * result + ((getCreateuserid() == null) ? 0 : getCreateuserid().hashCode());
        result = prime * result + ((getTimecount() == null) ? 0 : getTimecount().hashCode());
        result = prime * result + ((getTitle() == null) ? 0 : getTitle().hashCode());
        result = prime * result + ((getReference1() == null) ? 0 : getReference1().hashCode());
        result = prime * result + ((getReference2() == null) ? 0 : getReference2().hashCode());
        result = prime * result + ((getReference3() == null) ? 0 : getReference3().hashCode());
        result = prime * result + ((getBitstate() == null) ? 0 : getBitstate().hashCode());
        result = prime * result + ((getCdregistertime() == null) ? 0 : getCdregistertime().hashCode());
        result = prime * result + ((getCdregistertimeend() == null) ? 0 : getCdregistertimeend().hashCode());
        result = prime * result + ((getCdbidamount() == null) ? 0 : getCdbidamount().hashCode());
        return result;
    }
}