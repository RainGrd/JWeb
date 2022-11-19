package com.yscf.db.model.escf2.platform;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br> 
 * 京东支付返回的充值Token值记录表(CaffP2pWapToken) 映射基础类（JavaBean）
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffP2pWapToken extends BaseEntity {
   
	private static final long serialVersionUID = -9106488502023929865L;

	private Long caffp2pwaptokenid;

    private Long createddatelong;

    private Long version;

    private Long userid;

    private String token;

    public Long getCaffp2pwaptokenid() {
        return caffp2pwaptokenid;
    }

    public void setCaffp2pwaptokenid(Long caffp2pwaptokenid) {
        this.caffp2pwaptokenid = caffp2pwaptokenid;
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

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token == null ? null : token.trim();
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
        CaffP2pWapToken other = (CaffP2pWapToken) that;
        return (this.getCaffp2pwaptokenid() == null ? other.getCaffp2pwaptokenid() == null : this.getCaffp2pwaptokenid().equals(other.getCaffp2pwaptokenid()))
            && (this.getCreateddatelong() == null ? other.getCreateddatelong() == null : this.getCreateddatelong().equals(other.getCreateddatelong()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()))
            && (this.getToken() == null ? other.getToken() == null : this.getToken().equals(other.getToken()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCaffp2pwaptokenid() == null) ? 0 : getCaffp2pwaptokenid().hashCode());
        result = prime * result + ((getCreateddatelong() == null) ? 0 : getCreateddatelong().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        result = prime * result + ((getToken() == null) ? 0 : getToken().hashCode());
        return result;
    }
}