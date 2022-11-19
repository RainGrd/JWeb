package com.yscf.db.model.escf2.financial;

import com.achievo.framework.entity.BaseEntity;
import java.math.BigDecimal;

/**
 * Description：<br> 
 * 资金流水表（CaffP2pFundTally） 映射基础类（JavaBean）
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffP2pFundTally extends BaseEntity {
    
	private static final long serialVersionUID = 839106385061011058L;

	private Long caffp2pfundtallyid;

    private Long createddatelong;

    private Long version;

    private Long userid;

    private BigDecimal amount;

    private BigDecimal balance;

    private Long fkey;

    private Integer fundtallytypeint;

    public Long getCaffp2pfundtallyid() {
        return caffp2pfundtallyid;
    }

    public void setCaffp2pfundtallyid(Long caffp2pfundtallyid) {
        this.caffp2pfundtallyid = caffp2pfundtallyid;
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

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public Long getFkey() {
        return fkey;
    }

    public void setFkey(Long fkey) {
        this.fkey = fkey;
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
        CaffP2pFundTally other = (CaffP2pFundTally) that;
        return (this.getCaffp2pfundtallyid() == null ? other.getCaffp2pfundtallyid() == null : this.getCaffp2pfundtallyid().equals(other.getCaffp2pfundtallyid()))
            && (this.getCreateddatelong() == null ? other.getCreateddatelong() == null : this.getCreateddatelong().equals(other.getCreateddatelong()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getBalance() == null ? other.getBalance() == null : this.getBalance().equals(other.getBalance()))
            && (this.getFkey() == null ? other.getFkey() == null : this.getFkey().equals(other.getFkey()))
            && (this.getFundtallytypeint() == null ? other.getFundtallytypeint() == null : this.getFundtallytypeint().equals(other.getFundtallytypeint()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCaffp2pfundtallyid() == null) ? 0 : getCaffp2pfundtallyid().hashCode());
        result = prime * result + ((getCreateddatelong() == null) ? 0 : getCreateddatelong().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getBalance() == null) ? 0 : getBalance().hashCode());
        result = prime * result + ((getFkey() == null) ? 0 : getFkey().hashCode());
        result = prime * result + ((getFundtallytypeint() == null) ? 0 : getFundtallytypeint().hashCode());
        return result;
    }
}