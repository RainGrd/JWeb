package com.yscf.db.model.escf2.business;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br> 
 * 提现表(CaffP2pWithdraw) 映射基础类（JavaBean）
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffP2pWithdraw extends BaseEntity {
	
	private static final long serialVersionUID = 6736306592174281715L;

	private Long caffp2pwithdrawid;

    private Long createddatelong;

    private Long version;

    private Long userid;

    private String accountnumber;

    private Long amount;

    private Long cost;

    private Long fee;

    private Integer banktypeint;

    private Integer cityint;

    private String openingbank;

    private Integer provinceint;

    private Integer stateint;

    private Long transfereddatelong;

    private Long auditdatelong;

    private Long bitstate;

    public Long getCaffp2pwithdrawid() {
        return caffp2pwithdrawid;
    }

    public void setCaffp2pwithdrawid(Long caffp2pwithdrawid) {
        this.caffp2pwithdrawid = caffp2pwithdrawid;
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

    public String getAccountnumber() {
        return accountnumber;
    }

    public void setAccountnumber(String accountnumber) {
        this.accountnumber = accountnumber == null ? null : accountnumber.trim();
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public Long getCost() {
        return cost;
    }

    public void setCost(Long cost) {
        this.cost = cost;
    }

    public Long getFee() {
        return fee;
    }

    public void setFee(Long fee) {
        this.fee = fee;
    }

    public Integer getBanktypeint() {
        return banktypeint;
    }

    public void setBanktypeint(Integer banktypeint) {
        this.banktypeint = banktypeint;
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

    public Integer getStateint() {
        return stateint;
    }

    public void setStateint(Integer stateint) {
        this.stateint = stateint;
    }

    public Long getTransfereddatelong() {
        return transfereddatelong;
    }

    public void setTransfereddatelong(Long transfereddatelong) {
        this.transfereddatelong = transfereddatelong;
    }

    public Long getAuditdatelong() {
        return auditdatelong;
    }

    public void setAuditdatelong(Long auditdatelong) {
        this.auditdatelong = auditdatelong;
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
        CaffP2pWithdraw other = (CaffP2pWithdraw) that;
        return (this.getCaffp2pwithdrawid() == null ? other.getCaffp2pwithdrawid() == null : this.getCaffp2pwithdrawid().equals(other.getCaffp2pwithdrawid()))
            && (this.getCreateddatelong() == null ? other.getCreateddatelong() == null : this.getCreateddatelong().equals(other.getCreateddatelong()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getAccountnumber() == null ? other.getAccountnumber() == null : this.getAccountnumber().equals(other.getAccountnumber()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getCost() == null ? other.getCost() == null : this.getCost().equals(other.getCost()))
            && (this.getFee() == null ? other.getFee() == null : this.getFee().equals(other.getFee()))
            && (this.getBanktypeint() == null ? other.getBanktypeint() == null : this.getBanktypeint().equals(other.getBanktypeint()))
            && (this.getCityint() == null ? other.getCityint() == null : this.getCityint().equals(other.getCityint()))
            && (this.getOpeningbank() == null ? other.getOpeningbank() == null : this.getOpeningbank().equals(other.getOpeningbank()))
            && (this.getProvinceint() == null ? other.getProvinceint() == null : this.getProvinceint().equals(other.getProvinceint()))
            && (this.getStateint() == null ? other.getStateint() == null : this.getStateint().equals(other.getStateint()))
            && (this.getTransfereddatelong() == null ? other.getTransfereddatelong() == null : this.getTransfereddatelong().equals(other.getTransfereddatelong()))
            && (this.getAuditdatelong() == null ? other.getAuditdatelong() == null : this.getAuditdatelong().equals(other.getAuditdatelong()))
            && (this.getBitstate() == null ? other.getBitstate() == null : this.getBitstate().equals(other.getBitstate()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCaffp2pwithdrawid() == null) ? 0 : getCaffp2pwithdrawid().hashCode());
        result = prime * result + ((getCreateddatelong() == null) ? 0 : getCreateddatelong().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getAccountnumber() == null) ? 0 : getAccountnumber().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getCost() == null) ? 0 : getCost().hashCode());
        result = prime * result + ((getFee() == null) ? 0 : getFee().hashCode());
        result = prime * result + ((getBanktypeint() == null) ? 0 : getBanktypeint().hashCode());
        result = prime * result + ((getCityint() == null) ? 0 : getCityint().hashCode());
        result = prime * result + ((getOpeningbank() == null) ? 0 : getOpeningbank().hashCode());
        result = prime * result + ((getProvinceint() == null) ? 0 : getProvinceint().hashCode());
        result = prime * result + ((getStateint() == null) ? 0 : getStateint().hashCode());
        result = prime * result + ((getTransfereddatelong() == null) ? 0 : getTransfereddatelong().hashCode());
        result = prime * result + ((getAuditdatelong() == null) ? 0 : getAuditdatelong().hashCode());
        result = prime * result + ((getBitstate() == null) ? 0 : getBitstate().hashCode());
        return result;
    }
}