package com.yscf.db.model.escf2.platform;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br> 
 * 邮件表（CaffMail） 映射基础类（JavaBean）
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffMail extends BaseEntity {
    
	private static final long serialVersionUID = 8835560116888632099L;

	private Long caffmailid;

    private Long createddatelong;

    private Long version;

    private Long bitstate;

    private String description;

    private String fromemail;

    private String host;

    private String pass;

    private String templatefolder;

    private String username;

    public Long getCaffmailid() {
        return caffmailid;
    }

    public void setCaffmailid(Long caffmailid) {
        this.caffmailid = caffmailid;
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

    public Long getBitstate() {
        return bitstate;
    }

    public void setBitstate(Long bitstate) {
        this.bitstate = bitstate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    public String getFromemail() {
        return fromemail;
    }

    public void setFromemail(String fromemail) {
        this.fromemail = fromemail == null ? null : fromemail.trim();
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host == null ? null : host.trim();
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass == null ? null : pass.trim();
    }

    public String getTemplatefolder() {
        return templatefolder;
    }

    public void setTemplatefolder(String templatefolder) {
        this.templatefolder = templatefolder == null ? null : templatefolder.trim();
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username == null ? null : username.trim();
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
        CaffMail other = (CaffMail) that;
        return (this.getCaffmailid() == null ? other.getCaffmailid() == null : this.getCaffmailid().equals(other.getCaffmailid()))
            && (this.getCreateddatelong() == null ? other.getCreateddatelong() == null : this.getCreateddatelong().equals(other.getCreateddatelong()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getBitstate() == null ? other.getBitstate() == null : this.getBitstate().equals(other.getBitstate()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getFromemail() == null ? other.getFromemail() == null : this.getFromemail().equals(other.getFromemail()))
            && (this.getHost() == null ? other.getHost() == null : this.getHost().equals(other.getHost()))
            && (this.getPass() == null ? other.getPass() == null : this.getPass().equals(other.getPass()))
            && (this.getTemplatefolder() == null ? other.getTemplatefolder() == null : this.getTemplatefolder().equals(other.getTemplatefolder()))
            && (this.getUsername() == null ? other.getUsername() == null : this.getUsername().equals(other.getUsername()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCaffmailid() == null) ? 0 : getCaffmailid().hashCode());
        result = prime * result + ((getCreateddatelong() == null) ? 0 : getCreateddatelong().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getBitstate() == null) ? 0 : getBitstate().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getFromemail() == null) ? 0 : getFromemail().hashCode());
        result = prime * result + ((getHost() == null) ? 0 : getHost().hashCode());
        result = prime * result + ((getPass() == null) ? 0 : getPass().hashCode());
        result = prime * result + ((getTemplatefolder() == null) ? 0 : getTemplatefolder().hashCode());
        result = prime * result + ((getUsername() == null) ? 0 : getUsername().hashCode());
        return result;
    }
}