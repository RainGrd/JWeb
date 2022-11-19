package com.yscf.db.model.escf2.financial;

import java.math.BigDecimal;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br> 
 * 资金类别变化明细表（CaffP2pAccountAmountDetail） 映射基础类（JavaBean）
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffP2pAccountAmountDetail extends BaseEntity {
  
	private static final long serialVersionUID = -5543160853309384989L;

	private Long caffp2paccountamountdetailid;

    private Long createddatelong;

    private Long version;

    private Long userid;

    private Integer accountamounttypeint;

    private BigDecimal amount;

    private BigDecimal balance;

    private Long fundtallyid;

    private Integer fundtallytypeint;

    public Long getCaffp2paccountamountdetailid() {
        return caffp2paccountamountdetailid;
    }

    public void setCaffp2paccountamountdetailid(Long caffp2paccountamountdetailid) {
        this.caffp2paccountamountdetailid = caffp2paccountamountdetailid;
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

    public Integer getAccountamounttypeint() {
        return accountamounttypeint;
    }

    public void setAccountamounttypeint(Integer accountamounttypeint) {
        this.accountamounttypeint = accountamounttypeint;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Long getFundtallyid() {
        return fundtallyid;
    }

    public void setFundtallyid(Long fundtallyid) {
        this.fundtallyid = fundtallyid;
    }

    public Integer getFundtallytypeint() {
        return fundtallytypeint;
    }

    public void setFundtallytypeint(Integer fundtallytypeint) {
        this.fundtallytypeint = fundtallytypeint;
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
        CaffP2pAccountAmountDetail other = (CaffP2pAccountAmountDetail) that;
        return (this.getCaffp2paccountamountdetailid() == null ? other.getCaffp2paccountamountdetailid() == null : this.getCaffp2paccountamountdetailid().equals(other.getCaffp2paccountamountdetailid()))
            && (this.getCreateddatelong() == null ? other.getCreateddatelong() == null : this.getCreateddatelong().equals(other.getCreateddatelong()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getAccountamounttypeint() == null ? other.getAccountamounttypeint() == null : this.getAccountamounttypeint().equals(other.getAccountamounttypeint()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getBalance() == null ? other.getBalance() == null : this.getBalance().equals(other.getBalance()))
            && (this.getFundtallyid() == null ? other.getFundtallyid() == null : this.getFundtallyid().equals(other.getFundtallyid()))
            && (this.getFundtallytypeint() == null ? other.getFundtallytypeint() == null : this.getFundtallytypeint().equals(other.getFundtallytypeint()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCaffp2paccountamountdetailid() == null) ? 0 : getCaffp2paccountamountdetailid().hashCode());
        result = prime * result + ((getCreateddatelong() == null) ? 0 : getCreateddatelong().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getAccountamounttypeint() == null) ? 0 : getAccountamounttypeint().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getBalance() == null) ? 0 : getBalance().hashCode());
        result = prime * result + ((getFundtallyid() == null) ? 0 : getFundtallyid().hashCode());
        result = prime * result + ((getFundtallytypeint() == null) ? 0 : getFundtallytypeint().hashCode());
        return result;
    }
}