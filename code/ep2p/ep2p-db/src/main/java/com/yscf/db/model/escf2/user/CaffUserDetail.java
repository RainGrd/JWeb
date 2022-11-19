package com.yscf.db.model.escf2.user;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br> 
 * 用户详细信息表（CaffUserDetail）  映射基础类（JavaBean）
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffUserDetail extends BaseEntity {

	private static final long serialVersionUID = 622949871797589147L;

	private Long caffuserdetailid;

    private Long createddatelong;

    private Long version;

    private Long birthdatelong;

    private String currentaddress;

    private Integer edubackgroundint;

    private String houseaddress;

    private Integer houseconditionint;

    private String idnumber;

    private Integer idtypeint;

    private Integer incomegradeint;

    private Integer kidcount;

    private Integer marriageint;

    private String msn;

    private Integer nationalityint;

    private Integer nativecityint;

    private Integer nativeprovinceint;

    private String phonedistrict;

    private String phonenumber;

    private String qq;

    private String realname;

    private Integer sexint;

    private String socialinssancecode;

    private Integer socialroleint;

    private Long userid;

    private String ww;

    private String zip;

    private Long reference1;

    private Long reference2;

    private Long reference3;

    public Long getCaffuserdetailid() {
        return caffuserdetailid;
    }

    public void setCaffuserdetailid(Long caffuserdetailid) {
        this.caffuserdetailid = caffuserdetailid;
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

    public Long getBirthdatelong() {
        return birthdatelong;
    }

    public void setBirthdatelong(Long birthdatelong) {
        this.birthdatelong = birthdatelong;
    }

    public String getCurrentaddress() {
        return currentaddress;
    }

    public void setCurrentaddress(String currentaddress) {
        this.currentaddress = currentaddress == null ? null : currentaddress.trim();
    }

    public Integer getEdubackgroundint() {
        return edubackgroundint;
    }

    public void setEdubackgroundint(Integer edubackgroundint) {
        this.edubackgroundint = edubackgroundint;
    }

    public String getHouseaddress() {
        return houseaddress;
    }

    public void setHouseaddress(String houseaddress) {
        this.houseaddress = houseaddress == null ? null : houseaddress.trim();
    }

    public Integer getHouseconditionint() {
        return houseconditionint;
    }

    public void setHouseconditionint(Integer houseconditionint) {
        this.houseconditionint = houseconditionint;
    }

    public String getIdnumber() {
        return idnumber;
    }

    public void setIdnumber(String idnumber) {
        this.idnumber = idnumber == null ? null : idnumber.trim();
    }

    public Integer getIdtypeint() {
        return idtypeint;
    }

    public void setIdtypeint(Integer idtypeint) {
        this.idtypeint = idtypeint;
    }

    public Integer getIncomegradeint() {
        return incomegradeint;
    }

    public void setIncomegradeint(Integer incomegradeint) {
        this.incomegradeint = incomegradeint;
    }

    public Integer getKidcount() {
        return kidcount;
    }

    public void setKidcount(Integer kidcount) {
        this.kidcount = kidcount;
    }

    public Integer getMarriageint() {
        return marriageint;
    }

    public void setMarriageint(Integer marriageint) {
        this.marriageint = marriageint;
    }

    public String getMsn() {
        return msn;
    }

    public void setMsn(String msn) {
        this.msn = msn == null ? null : msn.trim();
    }

    public Integer getNationalityint() {
        return nationalityint;
    }

    public void setNationalityint(Integer nationalityint) {
        this.nationalityint = nationalityint;
    }

    public Integer getNativecityint() {
        return nativecityint;
    }

    public void setNativecityint(Integer nativecityint) {
        this.nativecityint = nativecityint;
    }

    public Integer getNativeprovinceint() {
        return nativeprovinceint;
    }

    public void setNativeprovinceint(Integer nativeprovinceint) {
        this.nativeprovinceint = nativeprovinceint;
    }

    public String getPhonedistrict() {
        return phonedistrict;
    }

    public void setPhonedistrict(String phonedistrict) {
        this.phonedistrict = phonedistrict == null ? null : phonedistrict.trim();
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber == null ? null : phonenumber.trim();
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq == null ? null : qq.trim();
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname == null ? null : realname.trim();
    }

    public Integer getSexint() {
        return sexint;
    }

    public void setSexint(Integer sexint) {
        this.sexint = sexint;
    }

    public String getSocialinssancecode() {
        return socialinssancecode;
    }

    public void setSocialinssancecode(String socialinssancecode) {
        this.socialinssancecode = socialinssancecode == null ? null : socialinssancecode.trim();
    }

    public Integer getSocialroleint() {
        return socialroleint;
    }

    public void setSocialroleint(Integer socialroleint) {
        this.socialroleint = socialroleint;
    }

    public Long getUserid() {
        return userid;
    }

    public void setUserid(Long userid) {
        this.userid = userid;
    }

    public String getWw() {
        return ww;
    }

    public void setWw(String ww) {
        this.ww = ww == null ? null : ww.trim();
    }

    public String getZip() {
        return zip;
    }

    public void setZip(String zip) {
        this.zip = zip == null ? null : zip.trim();
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
        CaffUserDetail other = (CaffUserDetail) that;
        return (this.getCaffuserdetailid() == null ? other.getCaffuserdetailid() == null : this.getCaffuserdetailid().equals(other.getCaffuserdetailid()))
            && (this.getCreateddatelong() == null ? other.getCreateddatelong() == null : this.getCreateddatelong().equals(other.getCreateddatelong()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getBirthdatelong() == null ? other.getBirthdatelong() == null : this.getBirthdatelong().equals(other.getBirthdatelong()))
            && (this.getCurrentaddress() == null ? other.getCurrentaddress() == null : this.getCurrentaddress().equals(other.getCurrentaddress()))
            && (this.getEdubackgroundint() == null ? other.getEdubackgroundint() == null : this.getEdubackgroundint().equals(other.getEdubackgroundint()))
            && (this.getHouseaddress() == null ? other.getHouseaddress() == null : this.getHouseaddress().equals(other.getHouseaddress()))
            && (this.getHouseconditionint() == null ? other.getHouseconditionint() == null : this.getHouseconditionint().equals(other.getHouseconditionint()))
            && (this.getIdnumber() == null ? other.getIdnumber() == null : this.getIdnumber().equals(other.getIdnumber()))
            && (this.getIdtypeint() == null ? other.getIdtypeint() == null : this.getIdtypeint().equals(other.getIdtypeint()))
            && (this.getIncomegradeint() == null ? other.getIncomegradeint() == null : this.getIncomegradeint().equals(other.getIncomegradeint()))
            && (this.getKidcount() == null ? other.getKidcount() == null : this.getKidcount().equals(other.getKidcount()))
            && (this.getMarriageint() == null ? other.getMarriageint() == null : this.getMarriageint().equals(other.getMarriageint()))
            && (this.getMsn() == null ? other.getMsn() == null : this.getMsn().equals(other.getMsn()))
            && (this.getNationalityint() == null ? other.getNationalityint() == null : this.getNationalityint().equals(other.getNationalityint()))
            && (this.getNativecityint() == null ? other.getNativecityint() == null : this.getNativecityint().equals(other.getNativecityint()))
            && (this.getNativeprovinceint() == null ? other.getNativeprovinceint() == null : this.getNativeprovinceint().equals(other.getNativeprovinceint()))
            && (this.getPhonedistrict() == null ? other.getPhonedistrict() == null : this.getPhonedistrict().equals(other.getPhonedistrict()))
            && (this.getPhonenumber() == null ? other.getPhonenumber() == null : this.getPhonenumber().equals(other.getPhonenumber()))
            && (this.getQq() == null ? other.getQq() == null : this.getQq().equals(other.getQq()))
            && (this.getRealname() == null ? other.getRealname() == null : this.getRealname().equals(other.getRealname()))
            && (this.getSexint() == null ? other.getSexint() == null : this.getSexint().equals(other.getSexint()))
            && (this.getSocialinssancecode() == null ? other.getSocialinssancecode() == null : this.getSocialinssancecode().equals(other.getSocialinssancecode()))
            && (this.getSocialroleint() == null ? other.getSocialroleint() == null : this.getSocialroleint().equals(other.getSocialroleint()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getWw() == null ? other.getWw() == null : this.getWw().equals(other.getWw()))
            && (this.getZip() == null ? other.getZip() == null : this.getZip().equals(other.getZip()))
            && (this.getReference1() == null ? other.getReference1() == null : this.getReference1().equals(other.getReference1()))
            && (this.getReference2() == null ? other.getReference2() == null : this.getReference2().equals(other.getReference2()))
            && (this.getReference3() == null ? other.getReference3() == null : this.getReference3().equals(other.getReference3()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCaffuserdetailid() == null) ? 0 : getCaffuserdetailid().hashCode());
        result = prime * result + ((getCreateddatelong() == null) ? 0 : getCreateddatelong().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getBirthdatelong() == null) ? 0 : getBirthdatelong().hashCode());
        result = prime * result + ((getCurrentaddress() == null) ? 0 : getCurrentaddress().hashCode());
        result = prime * result + ((getEdubackgroundint() == null) ? 0 : getEdubackgroundint().hashCode());
        result = prime * result + ((getHouseaddress() == null) ? 0 : getHouseaddress().hashCode());
        result = prime * result + ((getHouseconditionint() == null) ? 0 : getHouseconditionint().hashCode());
        result = prime * result + ((getIdnumber() == null) ? 0 : getIdnumber().hashCode());
        result = prime * result + ((getIdtypeint() == null) ? 0 : getIdtypeint().hashCode());
        result = prime * result + ((getIncomegradeint() == null) ? 0 : getIncomegradeint().hashCode());
        result = prime * result + ((getKidcount() == null) ? 0 : getKidcount().hashCode());
        result = prime * result + ((getMarriageint() == null) ? 0 : getMarriageint().hashCode());
        result = prime * result + ((getMsn() == null) ? 0 : getMsn().hashCode());
        result = prime * result + ((getNationalityint() == null) ? 0 : getNationalityint().hashCode());
        result = prime * result + ((getNativecityint() == null) ? 0 : getNativecityint().hashCode());
        result = prime * result + ((getNativeprovinceint() == null) ? 0 : getNativeprovinceint().hashCode());
        result = prime * result + ((getPhonedistrict() == null) ? 0 : getPhonedistrict().hashCode());
        result = prime * result + ((getPhonenumber() == null) ? 0 : getPhonenumber().hashCode());
        result = prime * result + ((getQq() == null) ? 0 : getQq().hashCode());
        result = prime * result + ((getRealname() == null) ? 0 : getRealname().hashCode());
        result = prime * result + ((getSexint() == null) ? 0 : getSexint().hashCode());
        result = prime * result + ((getSocialinssancecode() == null) ? 0 : getSocialinssancecode().hashCode());
        result = prime * result + ((getSocialroleint() == null) ? 0 : getSocialroleint().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getWw() == null) ? 0 : getWw().hashCode());
        result = prime * result + ((getZip() == null) ? 0 : getZip().hashCode());
        result = prime * result + ((getReference1() == null) ? 0 : getReference1().hashCode());
        result = prime * result + ((getReference2() == null) ? 0 : getReference2().hashCode());
        result = prime * result + ((getReference3() == null) ? 0 : getReference3().hashCode());
        return result;
    }
}