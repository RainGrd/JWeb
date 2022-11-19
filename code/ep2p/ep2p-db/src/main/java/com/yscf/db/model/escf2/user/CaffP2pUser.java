package com.yscf.db.model.escf2.user;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br> 
 * 系统用户表（CaffP2pUser）  映射基础类（JavaBean）
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffP2pUser extends BaseEntity {
 
	private static final long serialVersionUID = -4803935113971069290L;

	private Long caffp2puserid;

    private Long bitstate;

    private Long bitstate2;

    private Long bitstate3;

    private Long exkey;

    private Integer sourceint;

    private Integer loginerrorcount;

    private Long createddatelong;

    private Long version;

    private String email;

    private String phone;

    private String name;

    private String password;

    private String tradepassword;

    public Long getCaffp2puserid() {
        return caffp2puserid;
    }

    public void setCaffp2puserid(Long caffp2puserid) {
        this.caffp2puserid = caffp2puserid;
    }

    public Long getBitstate() {
        return bitstate;
    }

    public void setBitstate(Long bitstate) {
        this.bitstate = bitstate;
    }

    public Long getBitstate2() {
        return bitstate2;
    }

    public void setBitstate2(Long bitstate2) {
        this.bitstate2 = bitstate2;
    }

    public Long getBitstate3() {
        return bitstate3;
    }

    public void setBitstate3(Long bitstate3) {
        this.bitstate3 = bitstate3;
    }

    public Long getExkey() {
        return exkey;
    }

    public void setExkey(Long exkey) {
        this.exkey = exkey;
    }

    public Integer getSourceint() {
        return sourceint;
    }

    public void setSourceint(Integer sourceint) {
        this.sourceint = sourceint;
    }

    public Integer getLoginerrorcount() {
        return loginerrorcount;
    }

    public void setLoginerrorcount(Integer loginerrorcount) {
        this.loginerrorcount = loginerrorcount;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getTradepassword() {
        return tradepassword;
    }

    public void setTradepassword(String tradepassword) {
        this.tradepassword = tradepassword == null ? null : tradepassword.trim();
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
        CaffP2pUser other = (CaffP2pUser) that;
        return (this.getCaffp2puserid() == null ? other.getCaffp2puserid() == null : this.getCaffp2puserid().equals(other.getCaffp2puserid()))
            && (this.getBitstate() == null ? other.getBitstate() == null : this.getBitstate().equals(other.getBitstate()))
            && (this.getBitstate2() == null ? other.getBitstate2() == null : this.getBitstate2().equals(other.getBitstate2()))
            && (this.getBitstate3() == null ? other.getBitstate3() == null : this.getBitstate3().equals(other.getBitstate3()))
            && (this.getExkey() == null ? other.getExkey() == null : this.getExkey().equals(other.getExkey()))
            && (this.getSourceint() == null ? other.getSourceint() == null : this.getSourceint().equals(other.getSourceint()))
            && (this.getLoginerrorcount() == null ? other.getLoginerrorcount() == null : this.getLoginerrorcount().equals(other.getLoginerrorcount()))
            && (this.getCreateddatelong() == null ? other.getCreateddatelong() == null : this.getCreateddatelong().equals(other.getCreateddatelong()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword()))
            && (this.getTradepassword() == null ? other.getTradepassword() == null : this.getTradepassword().equals(other.getTradepassword()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCaffp2puserid() == null) ? 0 : getCaffp2puserid().hashCode());
        result = prime * result + ((getBitstate() == null) ? 0 : getBitstate().hashCode());
        result = prime * result + ((getBitstate2() == null) ? 0 : getBitstate2().hashCode());
        result = prime * result + ((getBitstate3() == null) ? 0 : getBitstate3().hashCode());
        result = prime * result + ((getExkey() == null) ? 0 : getExkey().hashCode());
        result = prime * result + ((getSourceint() == null) ? 0 : getSourceint().hashCode());
        result = prime * result + ((getLoginerrorcount() == null) ? 0 : getLoginerrorcount().hashCode());
        result = prime * result + ((getCreateddatelong() == null) ? 0 : getCreateddatelong().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
        result = prime * result + ((getTradepassword() == null) ? 0 : getTradepassword().hashCode());
        return result;
    }
}