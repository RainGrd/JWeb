package com.yscf.core.model.content;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br> 
 * 协议内容信息
 * @author  Lin Xu
 * @date    2015年9月22日
 * @version v1.0.0
 */
public class AgreementContent extends BaseEntity {

	private static final long serialVersionUID = -1099427779569985147L;
	//协议内容ID
	private String pid;
	//协议内容名称
    private String agrContName;
	//协议内容模板信息ID
    private String agrConTemId;
	//关键字
    private String protocol;
	//状态
    private String status;
	//创建人
    private String createUser;
	//创建时间
    private String createTime;
    //最后更新人
    private String lastUpdateUser;
	//最后修改时间
    private String lastUpdateTime;
	//版本号
    private String version;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getAgrContName() {
        return agrContName;
    }

    public void setAgrContName(String agrContName) {
        this.agrContName = agrContName == null ? null : agrContName.trim();
    }

    public String getAgrConTemId() {
        return agrConTemId;
    }

    public void setAgrConTemId(String agrConTemId) {
        this.agrConTemId = agrConTemId == null ? null : agrConTemId.trim();
    }

    public String getProtocol() {
        return protocol;
    }

    public void setProtocol(String protocol) {
        this.protocol = protocol == null ? null : protocol.trim();
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser == null ? null : lastUpdateUser.trim();
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
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
        AgreementContent other = (AgreementContent) that;
        return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getAgrContName() == null ? other.getAgrContName() == null : this.getAgrContName().equals(other.getAgrContName()))
            && (this.getAgrConTemId() == null ? other.getAgrConTemId() == null : this.getAgrConTemId().equals(other.getAgrConTemId()))
            && (this.getProtocol() == null ? other.getProtocol() == null : this.getProtocol().equals(other.getProtocol()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastUpdateUser() == null ? other.getLastUpdateUser() == null : this.getLastUpdateUser().equals(other.getLastUpdateUser()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getAgrContName() == null) ? 0 : getAgrContName().hashCode());
        result = prime * result + ((getAgrConTemId() == null) ? 0 : getAgrConTemId().hashCode());
        result = prime * result + ((getProtocol() == null) ? 0 : getProtocol().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        return result;
    }
}