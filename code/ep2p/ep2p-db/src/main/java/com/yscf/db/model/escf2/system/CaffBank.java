package com.yscf.db.model.escf2.system;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br> 
 * 银行表(CaffBank)  映射基础类（JavaBean）
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffBank extends BaseEntity {

	private static final long serialVersionUID = -2375400170634524161L;

	private Long caffbankid;

    private Long createddatelong;

    private Long version;

    private String accountnumber;

    private Integer banktypeint;

    private Long bitstate;

    private Integer cityint;

    private String openingbank;

    private Integer provinceint;

    private Long userid;

    public Long getCaffbankid() {
        return caffbankid;
    }

    public void setCaffbankid(Long caffbankid) {
        this.caffbankid = caffbankid;
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

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber == null ? null : accountnumber.trim();
    }

    public Integer getBanktypeint() {
        return banktypeint;
    }

    public void setBanktypeint(Integer banktypeint) {
        this.banktypeint = banktypeint;
    }

    public Long getBitstate() {
        return bitstate;
    }

    public void setBitstate(Long bitstate) {
        this.bitstate = bitstate;
    }

    public Integer getCityint() {
        return cityint;
    }

    public void setCityint(Integer cityint) {
        this.cityint = cityint;
    }

    public String getOpeningbank() {
        return openingbank;
    }

    public void setOpeningbank(String openingbank) {
        this.openingbank = openingbank == null ? null : openingbank.trim();
    }

    public Integer getProvinceint() {
        return provinceint;
    }

    public void setProvinceint(Integer provinceint) {
        this.provinceint = provinceint;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
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
        CaffBank other = (CaffBank) that;
        return (this.getCaffbankid() == null ? other.getCaffbankid() == null : this.getCaffbankid().equals(other.getCaffbankid()))
            && (this.getCreateddatelong() == null ? other.getCreateddatelong() == null : this.getCreateddatelong().equals(other.getCreateddatelong()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getAccountnumber() == null ? other.getAccountnumber() == null : this.getAccountnumber().equals(other.getAccountnumber()))
            && (this.getBanktypeint() == null ? other.getBanktypeint() == null : this.getBanktypeint().equals(other.getBanktypeint()))
            && (this.getBitstate() == null ? other.getBitstate() == null : this.getBitstate().equals(other.getBitstate()))
            && (this.getCityint() == null ? other.getCityint() == null : this.getCityint().equals(other.getCityint()))
            && (this.getOpeningbank() == null ? other.getOpeningbank() == null : this.getOpeningbank().equals(other.getOpeningbank()))
            && (this.getProvinceint() == null ? other.getProvinceint() == null : this.getProvinceint().equals(other.getProvinceint()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCaffbankid() == null) ? 0 : getCaffbankid().hashCode());
        result = prime * result + ((getCreateddatelong() == null) ? 0 : getCreateddatelong().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getAccountnumber() == null) ? 0 : getAccountnumber().hashCode());
        result = prime * result + ((getBanktypeint() == null) ? 0 : getBanktypeint().hashCode());
        result = prime * result + ((getBitstate() == null) ? 0 : getBitstate().hashCode());
        result = prime * result + ((getCityint() == null) ? 0 : getCityint().hashCode());
        result = prime * result + ((getOpeningbank() == null) ? 0 : getOpeningbank().hashCode());
        result = prime * result + ((getProvinceint() == null) ? 0 : getProvinceint().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        return result;
    }
}