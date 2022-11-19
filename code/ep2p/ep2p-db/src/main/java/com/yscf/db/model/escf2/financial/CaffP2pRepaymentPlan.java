package com.yscf.db.model.escf2.financial;

import com.achievo.framework.entity.BaseEntity;
import java.math.BigDecimal;

/**
 * Description：<br> 
 * 还款计划表（CaffP2pRepaymentPlan） 映射基础类（JavaBean）
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffP2pRepaymentPlan extends BaseEntity {
    
	private static final long serialVersionUID = -7664860375634157959L;

	private Long caffp2prepaymentplanid;

    private Long createddatelong;

    private Long version;

    private Long userid;

    private Long borrowid;

    private Integer borrowtypeint;

    private BigDecimal capital;

    private Long expiredateactuallong;

    private Long expiredatelong;

    private BigDecimal interest;

    private Integer interestcalculationtypeint;

    private BigDecimal latefee;

    private Long latefeecalculatedatelong;

    private BigDecimal payamount;

    private Long paydatelong;

    private Integer planindex;

    private Long reference1;

    private Long reference2;

    private Long reference3;

    private BigDecimal repaidamount;

    private Long repaiddatelong;

    private Integer stateint;

    private Long bitstate;

    public Long getCaffp2prepaymentplanid() {
        return caffp2prepaymentplanid;
    }

    public void setCaffp2prepaymentplanid(Long caffp2prepaymentplanid) {
        this.caffp2prepaymentplanid = caffp2prepaymentplanid;
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

    public Integer getBorrowtypeint() {
        return borrowtypeint;
    }

    public void setBorrowtypeint(Integer borrowtypeint) {
        this.borrowtypeint = borrowtypeint;
    }

    public BigDecimal getCapital() {
        return capital;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

    public Long getExpiredateactuallong() {
        return expiredateactuallong;
    }

    public void setExpiredateactuallong(Long expiredateactuallong) {
        this.expiredateactuallong = expiredateactuallong;
    }

    public Long getExpiredatelong() {
        return expiredatelong;
    }

    public void setExpiredatelong(Long expiredatelong) {
        this.expiredatelong = expiredatelong;
    }

    public BigDecimal getInterest() {
        return interest;
    }

    public void setInterest(BigDecimal interest) {
        this.interest = interest;
    }

    public Integer getInterestcalculationtypeint() {
        return interestcalculationtypeint;
    }

    public void setInterestcalculationtypeint(Integer interestcalculationtypeint) {
        this.interestcalculationtypeint = interestcalculationtypeint;
    }

    public BigDecimal getLatefee() {
        return latefee;
    }

    public void setLatefee(BigDecimal latefee) {
        this.latefee = latefee;
    }

    public Long getLatefeecalculatedatelong() {
        return latefeecalculatedatelong;
    }

    public void setLatefeecalculatedatelong(Long latefeecalculatedatelong) {
        this.latefeecalculatedatelong = latefeecalculatedatelong;
    }

    public BigDecimal getPayamount() {
        return payamount;
    }

    public void setPayamount(BigDecimal payamount) {
        this.payamount = payamount;
    }

    public Long getPaydatelong() {
        return paydatelong;
    }

    public void setPaydatelong(Long paydatelong) {
        this.paydatelong = paydatelong;
    }

    public Integer getPlanindex() {
        return planindex;
    }

    public void setPlanindex(Integer planindex) {
        this.planindex = planindex;
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

    public BigDecimal getRepaidamount() {
        return repaidamount;
    }

    public void setRepaidamount(BigDecimal repaidamount) {
        this.repaidamount = repaidamount;
    }

    public Long getRepaiddatelong() {
        return repaiddatelong;
    }

    public void setRepaiddatelong(Long repaiddatelong) {
        this.repaiddatelong = repaiddatelong;
    }

    public Integer getStateint() {
        return stateint;
    }

    public void setStateint(Integer stateint) {
        this.stateint = stateint;
    }

    public Long getBitstate() {
        return bitstate;
    }

    public void setBitstate(Long bitstate) {
        this.bitstate = bitstate;
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
        CaffP2pRepaymentPlan other = (CaffP2pRepaymentPlan) that;
        return (this.getCaffp2prepaymentplanid() == null ? other.getCaffp2prepaymentplanid() == null : this.getCaffp2prepaymentplanid().equals(other.getCaffp2prepaymentplanid()))
            && (this.getCreateddatelong() == null ? other.getCreateddatelong() == null : this.getCreateddatelong().equals(other.getCreateddatelong()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getBorrowid() == null ? other.getBorrowid() == null : this.getBorrowid().equals(other.getBorrowid()))
            && (this.getBorrowtypeint() == null ? other.getBorrowtypeint() == null : this.getBorrowtypeint().equals(other.getBorrowtypeint()))
            && (this.getCapital() == null ? other.getCapital() == null : this.getCapital().equals(other.getCapital()))
            && (this.getExpiredateactuallong() == null ? other.getExpiredateactuallong() == null : this.getExpiredateactuallong().equals(other.getExpiredateactuallong()))
            && (this.getExpiredatelong() == null ? other.getExpiredatelong() == null : this.getExpiredatelong().equals(other.getExpiredatelong()))
            && (this.getInterest() == null ? other.getInterest() == null : this.getInterest().equals(other.getInterest()))
            && (this.getInterestcalculationtypeint() == null ? other.getInterestcalculationtypeint() == null : this.getInterestcalculationtypeint().equals(other.getInterestcalculationtypeint()))
            && (this.getLatefee() == null ? other.getLatefee() == null : this.getLatefee().equals(other.getLatefee()))
            && (this.getLatefeecalculatedatelong() == null ? other.getLatefeecalculatedatelong() == null : this.getLatefeecalculatedatelong().equals(other.getLatefeecalculatedatelong()))
            && (this.getPayamount() == null ? other.getPayamount() == null : this.getPayamount().equals(other.getPayamount()))
            && (this.getPaydatelong() == null ? other.getPaydatelong() == null : this.getPaydatelong().equals(other.getPaydatelong()))
            && (this.getPlanindex() == null ? other.getPlanindex() == null : this.getPlanindex().equals(other.getPlanindex()))
            && (this.getReference1() == null ? other.getReference1() == null : this.getReference1().equals(other.getReference1()))
            && (this.getReference2() == null ? other.getReference2() == null : this.getReference2().equals(other.getReference2()))
            && (this.getReference3() == null ? other.getReference3() == null : this.getReference3().equals(other.getReference3()))
            && (this.getRepaidamount() == null ? other.getRepaidamount() == null : this.getRepaidamount().equals(other.getRepaidamount()))
            && (this.getRepaiddatelong() == null ? other.getRepaiddatelong() == null : this.getRepaiddatelong().equals(other.getRepaiddatelong()))
            && (this.getStateint() == null ? other.getStateint() == null : this.getStateint().equals(other.getStateint()))
            && (this.getBitstate() == null ? other.getBitstate() == null : this.getBitstate().equals(other.getBitstate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCaffp2prepaymentplanid() == null) ? 0 : getCaffp2prepaymentplanid().hashCode());
        result = prime * result + ((getCreateddatelong() == null) ? 0 : getCreateddatelong().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getBorrowid() == null) ? 0 : getBorrowid().hashCode());
        result = prime * result + ((getBorrowtypeint() == null) ? 0 : getBorrowtypeint().hashCode());
        result = prime * result + ((getCapital() == null) ? 0 : getCapital().hashCode());
        result = prime * result + ((getExpiredateactuallong() == null) ? 0 : getExpiredateactuallong().hashCode());
        result = prime * result + ((getExpiredatelong() == null) ? 0 : getExpiredatelong().hashCode());
        result = prime * result + ((getInterest() == null) ? 0 : getInterest().hashCode());
        result = prime * result + ((getInterestcalculationtypeint() == null) ? 0 : getInterestcalculationtypeint().hashCode());
        result = prime * result + ((getLatefee() == null) ? 0 : getLatefee().hashCode());
        result = prime * result + ((getLatefeecalculatedatelong() == null) ? 0 : getLatefeecalculatedatelong().hashCode());
        result = prime * result + ((getPayamount() == null) ? 0 : getPayamount().hashCode());
        result = prime * result + ((getPaydatelong() == null) ? 0 : getPaydatelong().hashCode());
        result = prime * result + ((getPlanindex() == null) ? 0 : getPlanindex().hashCode());
        result = prime * result + ((getReference1() == null) ? 0 : getReference1().hashCode());
        result = prime * result + ((getReference2() == null) ? 0 : getReference2().hashCode());
        result = prime * result + ((getReference3() == null) ? 0 : getReference3().hashCode());
        result = prime * result + ((getRepaidamount() == null) ? 0 : getRepaidamount().hashCode());
        result = prime * result + ((getRepaiddatelong() == null) ? 0 : getRepaiddatelong().hashCode());
        result = prime * result + ((getStateint() == null) ? 0 : getStateint().hashCode());
        result = prime * result + ((getBitstate() == null) ? 0 : getBitstate().hashCode());
        return result;
    }
}