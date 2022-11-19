package com.yscf.db.model.escf2.financial;

import com.achievo.framework.entity.BaseEntity;
import java.math.BigDecimal;

/**
 * Description：<br> 
 * 资金组成详情表(CaffP2pAmountDetail) 映射基础类（JavaBean）
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffP2pAmountDetail extends BaseEntity {
    
	private static final long serialVersionUID = 6564604833988032287L;

	private Long caffp2pamountdetailid;

    private Long createddatelong;

    private Long version;

    private Long userid;

    private Integer accountamounttypeint;

    private BigDecimal amount;

    private Long fkey;

    private Integer fundtallytypeint;

    public Long getCaffp2pamountdetailid() {
        return caffp2pamountdetailid;
    }

    public void setCaffp2pamountdetailid(Long caffp2pamountdetailid) {
        this.caffp2pamountdetailid = caffp2pamountdetailid;
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
        CaffP2pAmountDetail other = (CaffP2pAmountDetail) that;
        return (this.getCaffp2pamountdetailid() == null ? other.getCaffp2pamountdetailid() == null : this.getCaffp2pamountdetailid().equals(other.getCaffp2pamountdetailid()))
            && (this.getCreateddatelong() == null ? other.getCreateddatelong() == null : this.getCreateddatelong().equals(other.getCreateddatelong()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getAccountamounttypeint() == null ? other.getAccountamounttypeint() == null : this.getAccountamounttypeint().equals(other.getAccountamounttypeint()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getFkey() == null ? other.getFkey() == null : this.getFkey().equals(other.getFkey()))
            && (this.getFundtallytypeint() == null ? other.getFundtallytypeint() == null : this.getFundtallytypeint().equals(other.getFundtallytypeint()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCaffp2pamountdetailid() == null) ? 0 : getCaffp2pamountdetailid().hashCode());
        result = prime * result + ((getCreateddatelong() == null) ? 0 : getCreateddatelong().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getAccountamounttypeint() == null) ? 0 : getAccountamounttypeint().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getFkey() == null) ? 0 : getFkey().hashCode());
        result = prime * result + ((getFundtallytypeint() == null) ? 0 : getFundtallytypeint().hashCode());
        return result;
    }
}