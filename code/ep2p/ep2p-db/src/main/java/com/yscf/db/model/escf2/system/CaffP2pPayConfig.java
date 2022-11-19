package com.yscf.db.model.escf2.system;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br> 
 * 支付账户配置表(CaffP2pPayConfig) 映射基础类（JavaBean）
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffP2pPayConfig extends BaseEntity {
    
	private static final long serialVersionUID = -4912363531223554320L;

	private Long caffp2ppayconfigid;

    private Long createddatelong;

    private Long version;

    private Long userid;

    private Integer paysystemint;

    private String exinfo1;

    private String exinfo2;

    private String merchantid;

    private String signkey;

    public Long getCaffp2ppayconfigid() {
        return caffp2ppayconfigid;
    }

    public void setCaffp2ppayconfigid(Long caffp2ppayconfigid) {
        this.caffp2ppayconfigid = caffp2ppayconfigid;
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

    public Integer getPaysystemint() {
        return paysystemint;
    }

    public void setPaysystemint(Integer paysystemint) {
        this.paysystemint = paysystemint;
    }

    public String getExinfo1() {
        return exinfo1;
    }

    public void setExinfo1(String exinfo1) {
        this.exinfo1 = exinfo1 == null ? null : exinfo1.trim();
    }

    public String getExinfo2() {
        return exinfo2;
    }

    public void setExinfo2(String exinfo2) {
        this.exinfo2 = exinfo2 == null ? null : exinfo2.trim();
    }

    public String getMerchantid() {
        return merchantid;
    }

    public void setMerchantid(String merchantid) {
        this.merchantid = merchantid == null ? null : merchantid.trim();
    }

    public String getSignkey() {
        return signkey;
    }

    public void setSignkey(String signkey) {
        this.signkey = signkey == null ? null : signkey.trim();
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
        CaffP2pPayConfig other = (CaffP2pPayConfig) that;
        return (this.getCaffp2ppayconfigid() == null ? other.getCaffp2ppayconfigid() == null : this.getCaffp2ppayconfigid().equals(other.getCaffp2ppayconfigid()))
            && (this.getCreateddatelong() == null ? other.getCreateddatelong() == null : this.getCreateddatelong().equals(other.getCreateddatelong()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getPaysystemint() == null ? other.getPaysystemint() == null : this.getPaysystemint().equals(other.getPaysystemint()))
            && (this.getExinfo1() == null ? other.getExinfo1() == null : this.getExinfo1().equals(other.getExinfo1()))
            && (this.getExinfo2() == null ? other.getExinfo2() == null : this.getExinfo2().equals(other.getExinfo2()))
            && (this.getMerchantid() == null ? other.getMerchantid() == null : this.getMerchantid().equals(other.getMerchantid()))
            && (this.getSignkey() == null ? other.getSignkey() == null : this.getSignkey().equals(other.getSignkey()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCaffp2ppayconfigid() == null) ? 0 : getCaffp2ppayconfigid().hashCode());
        result = prime * result + ((getCreateddatelong() == null) ? 0 : getCreateddatelong().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getPaysystemint() == null) ? 0 : getPaysystemint().hashCode());
        result = prime * result + ((getExinfo1() == null) ? 0 : getExinfo1().hashCode());
        result = prime * result + ((getExinfo2() == null) ? 0 : getExinfo2().hashCode());
        result = prime * result + ((getMerchantid() == null) ? 0 : getMerchantid().hashCode());
        result = prime * result + ((getSignkey() == null) ? 0 : getSignkey().hashCode());
        return result;
    }
}