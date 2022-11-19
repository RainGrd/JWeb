package com.yscf.db.model.escf2.financial;

import com.achievo.framework.entity.BaseEntity;
import java.math.BigDecimal;

/**
 * Description：<br> 
 * 新充值资金表（CaffP2pAccountRecharge） 映射基础类（JavaBean）
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffP2pAccountRecharge extends BaseEntity {
   
	private static final long serialVersionUID = 7155917139299467774L;

	private Long caffp2paccountrechargeid;

    private Long createddatelong;

    private Long version;

    private Long userid;

    private BigDecimal availableamount;

    private BigDecimal frozenamount;

    public Long getCaffp2paccountrechargeid() {
        return caffp2paccountrechargeid;
    }

    public void setCaffp2paccountrechargeid(Long caffp2paccountrechargeid) {
        this.caffp2paccountrechargeid = caffp2paccountrechargeid;
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
        CaffP2pAccountRecharge other = (CaffP2pAccountRecharge) that;
        return (this.getCaffp2paccountrechargeid() == null ? other.getCaffp2paccountrechargeid() == null : this.getCaffp2paccountrechargeid().equals(other.getCaffp2paccountrechargeid()))
            && (this.getCreateddatelong() == null ? other.getCreateddatelong() == null : this.getCreateddatelong().equals(other.getCreateddatelong()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getAvailableamount() == null ? other.getAvailableamount() == null : this.getAvailableamount().equals(other.getAvailableamount()))
            && (this.getFrozenamount() == null ? other.getFrozenamount() == null : this.getFrozenamount().equals(other.getFrozenamount()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCaffp2paccountrechargeid() == null) ? 0 : getCaffp2paccountrechargeid().hashCode());
        result = prime * result + ((getCreateddatelong() == null) ? 0 : getCreateddatelong().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getAvailableamount() == null) ? 0 : getAvailableamount().hashCode());
        result = prime * result + ((getFrozenamount() == null) ? 0 : getFrozenamount().hashCode());
        return result;
    }
}