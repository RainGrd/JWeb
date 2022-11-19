package com.yscf.db.model.escf2.system;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br> 
 * 用户上传文件表(CaffFile)  映射基础类（JavaBean）
 * @author  JingYu.Dai
 * @date    2016年2月26日
 * @version v1.0.0
 */
public class CaffFile extends BaseEntity {
  
	private static final long serialVersionUID = -6882916809318131062L;

	private Long cafffileid;

    private Long createddatelong;

    private Long version;

    private Long albumid;

    private Long bitstate;

    private Integer filetypeint;

    private String ioname;

    private String name;

    private Long userid;

    public Long getCafffileid() {
        return cafffileid;
    }

    public void setCafffileid(Long cafffileid) {
        this.cafffileid = cafffileid;
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

    public Long getAlbumid() {
        return albumid;
    }

    public void setAlbumid(Long albumid) {
        this.albumid = albumid;
    }

    public Long getBitstate() {
        return bitstate;
    }

    public void setBitstate(Long bitstate) {
        this.bitstate = bitstate;
    }

    public Integer getFiletypeint() {
        return filetypeint;
    }

    public void setFiletypeint(Integer filetypeint) {
        this.filetypeint = filetypeint;
    }

    public String getIoname() {
        return ioname;
    }

    public void setIoname(String ioname) {
        this.ioname = ioname == null ? null : ioname.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
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
        CaffFile other = (CaffFile) that;
        return (this.getCafffileid() == null ? other.getCafffileid() == null : this.getCafffileid().equals(other.getCafffileid()))
            && (this.getCreateddatelong() == null ? other.getCreateddatelong() == null : this.getCreateddatelong().equals(other.getCreateddatelong()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getAlbumid() == null ? other.getAlbumid() == null : this.getAlbumid().equals(other.getAlbumid()))
            && (this.getBitstate() == null ? other.getBitstate() == null : this.getBitstate().equals(other.getBitstate()))
            && (this.getFiletypeint() == null ? other.getFiletypeint() == null : this.getFiletypeint().equals(other.getFiletypeint()))
            && (this.getIoname() == null ? other.getIoname() == null : this.getIoname().equals(other.getIoname()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getUserid() == null ? other.getUserid() == null : this.getUserid().equals(other.getUserid()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getCafffileid() == null) ? 0 : getCafffileid().hashCode());
        result = prime * result + ((getCreateddatelong() == null) ? 0 : getCreateddatelong().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getAlbumid() == null) ? 0 : getAlbumid().hashCode());
        result = prime * result + ((getBitstate() == null) ? 0 : getBitstate().hashCode());
        result = prime * result + ((getFiletypeint() == null) ? 0 : getFiletypeint().hashCode());
        result = prime * result + ((getIoname() == null) ? 0 : getIoname().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getUserid() == null) ? 0 : getUserid().hashCode());
        return result;
    }
}