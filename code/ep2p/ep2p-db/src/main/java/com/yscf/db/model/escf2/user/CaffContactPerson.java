package com.yscf.db.model.escf2.user;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br> 
 * 联系人表（CaffContactPerson） 映射基础类（JavaBean）
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffContactPerson extends BaseEntity {
    
	private static final long serialVersionUID = 743290836991954855L;

	private Long caffcontactpersonid;

    private Long createddatelong;

    private Long version;

    private Integer contactpersonrelationint;

    private String name;

    private String phone;

    private Long userid;

    public Long getCaffcontactpersonid() {
        return caffcontactpersonid;
    }

    public void setCaffcontactpersonid(Long caffcontactpersonid) {
        this.caffcontactpersonid = caffcontactpersonid;
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

    public Integer getContactpersonrelationint() {
        return contactpersonrelationint;
    }

    public void setContactpersonrelationint(Integer contactpersonrelationint) {
        this.contactpersonrelationint = contactpersonrelationint;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone == null ? null : phone.trim();
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
        CaffContactPerson other = (CaffContactPerson) that;
        return (this.getCaffcontactpersonid() == null ? other.getCaffcontactpersonid() == null : this.getCaffcontactpersonid().equals(other.getCaffcontactpersonid()))
            && (this.getCreateddatelong() == null ? other.getCreateddatelong() == null : this.getCreateddatelong().equals(other.getCreateddatelong()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getContactpersonrelationint() == null ? other.getContactpersonrelationint() == null : this.getContactpersonrelationint().equals(other.getContactpersonrelationint()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getPhone() == null ? other.getPhone() == null : this.getPhone().equals(other.getPhone()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCaffcontactpersonid() == null) ? 0 : getCaffcontactpersonid().hashCode());
        result = prime * result + ((getCreateddatelong() == null) ? 0 : getCreateddatelong().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getContactpersonrelationint() == null) ? 0 : getContactpersonrelationint().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getPhone() == null) ? 0 : getPhone().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        return result;
    }
}