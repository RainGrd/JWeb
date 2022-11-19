package com.yscf.core.model.content;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br> 
 * 协议内容管理模板
 * @author  Lin Xu
 * @date    2015年9月22日
 * @version v1.0.0
 */
public class AgreementContTemp extends BaseEntity {

	private static final long serialVersionUID = 1445970429590545702L;
	//协议内容模版ID
	private String pid;
	//协议内容模版名称
    private String agrContTempName;
	//文件ID
    private String fileId;
	//状态
    private String status;
	//创建人
    private String createUser;
	//创建时间
    private String createTime;
	//最后修改人
    private String lastUpdateUser;
	//最后修改时间
    private String lastUpdateTime;
	//版本号
    private String version;
	//关联关键字ID
    private String protocolId;
    
    
    //-----------------扩展字段--------------------------
    //开始创建时间
    private String startCTime;
    //结束创建时间
    private String endCtime;
    //开始更新时间
    private String startLupTime;
    //结束更新时间
    private String endLuptime;
    

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getAgrContTempName() {
        return agrContTempName;
    }

    public void setAgrContTempName(String agrContTempName) {
        this.agrContTempName = agrContTempName == null ? null : agrContTempName.trim();
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId == null ? null : fileId.trim();
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

    public String getProtocolId() {
        return protocolId;
    }

    public void setProtocolId(String protocolId) {
        this.protocolId = protocolId == null ? null : protocolId.trim();
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
        AgreementContTemp other = (AgreementContTemp) that;
        return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getAgrContTempName() == null ? other.getAgrContTempName() == null : this.getAgrContTempName().equals(other.getAgrContTempName()))
            && (this.getFileId() == null ? other.getFileId() == null : this.getFileId().equals(other.getFileId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastUpdateUser() == null ? other.getLastUpdateUser() == null : this.getLastUpdateUser().equals(other.getLastUpdateUser()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getProtocolId() == null ? other.getProtocolId() == null : this.getProtocolId().equals(other.getProtocolId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getAgrContTempName() == null) ? 0 : getAgrContTempName().hashCode());
        result = prime * result + ((getFileId() == null) ? 0 : getFileId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getProtocolId() == null) ? 0 : getProtocolId().hashCode());
        return result;
    }

	public String getStartCTime() {
		return startCTime;
	}

	public void setStartCTime(String startCTime) {
		this.startCTime = startCTime;
	}

	public String getEndCtime() {
		return endCtime;
	}

	public void setEndCtime(String endCtime) {
		this.endCtime = endCtime;
	}

	public String getStartLupTime() {
		return startLupTime;
	}

	public void setStartLupTime(String startLupTime) {
		this.startLupTime = startLupTime;
	}

	public String getEndLuptime() {
		return endLuptime;
	}

	public void setEndLuptime(String endLuptime) {
		this.endLuptime = endLuptime;
	}
    
}