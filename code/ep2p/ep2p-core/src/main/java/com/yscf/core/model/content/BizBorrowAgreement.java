package com.yscf.core.model.content;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br> 
 * 借款合同信息
 * @author  Lin Xu
 * @date    2015年9月22日
 * @version v1.0.0
 */
public class BizBorrowAgreement extends BaseEntity {
	private static final long serialVersionUID = -3682203549303159301L;
	//合同ID
	private String pid;
	//合同编码
    private String borAgrCode;
	//合同名称
    private String borAgrAnme;
	//文件ID
    private String fileId;
	//协议内容模板ID
    private String agrConTemId;
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
	//备注
    private String borAgrDesc;
    
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

    public String getBorAgrCode() {
        return borAgrCode;
    }

    public void setBorAgrCode(String borAgrCode) {
        this.borAgrCode = borAgrCode == null ? null : borAgrCode.trim();
    }

    public String getBorAgrAnme() {
        return borAgrAnme;
    }

    public void setBorAgrAnme(String borAgrAnme) {
        this.borAgrAnme = borAgrAnme == null ? null : borAgrAnme.trim();
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId == null ? null : fileId.trim();
    }

    public String getAgrConTemId() {
        return agrConTemId;
    }

    public void setAgrConTemId(String agrConTemId) {
        this.agrConTemId = agrConTemId == null ? null : agrConTemId.trim();
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

    public String getBorAgrDesc() {
        return borAgrDesc;
    }

    public void setBorAgrDesc(String borAgrDesc) {
        this.borAgrDesc = borAgrDesc == null ? null : borAgrDesc.trim();
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
        BizBorrowAgreement other = (BizBorrowAgreement) that;
        return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getBorAgrCode() == null ? other.getBorAgrCode() == null : this.getBorAgrCode().equals(other.getBorAgrCode()))
            && (this.getBorAgrAnme() == null ? other.getBorAgrAnme() == null : this.getBorAgrAnme().equals(other.getBorAgrAnme()))
            && (this.getFileId() == null ? other.getFileId() == null : this.getFileId().equals(other.getFileId()))
            && (this.getAgrConTemId() == null ? other.getAgrConTemId() == null : this.getAgrConTemId().equals(other.getAgrConTemId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastUpdateUser() == null ? other.getLastUpdateUser() == null : this.getLastUpdateUser().equals(other.getLastUpdateUser()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()))
            && (this.getBorAgrDesc() == null ? other.getBorAgrDesc() == null : this.getBorAgrDesc().equals(other.getBorAgrDesc()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getBorAgrCode() == null) ? 0 : getBorAgrCode().hashCode());
        result = prime * result + ((getBorAgrAnme() == null) ? 0 : getBorAgrAnme().hashCode());
        result = prime * result + ((getFileId() == null) ? 0 : getFileId().hashCode());
        result = prime * result + ((getAgrConTemId() == null) ? 0 : getAgrConTemId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        result = prime * result + ((getBorAgrDesc() == null) ? 0 : getBorAgrDesc().hashCode());
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