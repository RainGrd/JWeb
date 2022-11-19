package com.yscf.core.model.sms;

import com.achievo.framework.entity.BaseEntity;
import java.util.Date;

public class CustMessRecord extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4745331212350920058L;

	private String pid;

    private String customerId;//客户id

    private Integer sendType;//发送类型
    
    private Integer msgType;//消息类型

    private String sendModel;//发送模块

    private String sendContent;//发送内容

    private Integer isRead;//是否阅读

    private String extfield1;//扩展字段1

    private String extfield2;//扩展字段2

    private String extfield3;//扩展字段3

    private String createUser;//创建人

    private String createTime;//创建时间

    private String lastUpdateUser;//最后修改人

    private Date lastUpdateTime;//最后修改时间

    private String status;//状态

    private String version;//版本号
    
    private String beginTime;//开始时间
    
    private String endTime; //结束时间 
    private String msgConten; //消息内容
    
    private String msgTitle; //消息主题
    
    private Integer pageSize;//显示数据的条数
    
    private Integer pageNum;//页数
    
    
    public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public String getMsgTitle() {
		return msgTitle;
	}

	public void setMsgTitle(String msgTitle) {
		this.msgTitle = msgTitle;
	}

	public Integer getMsgType() {
		return msgType;
	}

	public void setMsgType(Integer msgType) {
		this.msgType = msgType;
	}

	public String getMsgConten() {
		return msgConten;
	}

	public void setMsgConten(String msgConten) {
		this.msgConten = msgConten;
	}

	public String getBeginTime() {
		return beginTime;
	}

	public void setBeginTime(String beginTime) {
		this.beginTime = beginTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public Integer getSendType() {
        return sendType;
    }

    public void setSendType(Integer sendType) {
        this.sendType = sendType;
    }

    public String getSendModel() {
        return sendModel;
    }

    public void setSendModel(String sendModel) {
        this.sendModel = sendModel == null ? null : sendModel.trim();
    }

    public String getSendContent() {
        return sendContent;
    }

    public void setSendContent(String sendContent) {
        this.sendContent = sendContent == null ? null : sendContent.trim();
    }

    public Integer getIsRead() {
        return isRead;
    }

    public void setIsRead(Integer isRead) {
        this.isRead = isRead;
    }

    public String getExtfield1() {
        return extfield1;
    }

    public void setExtfield1(String extfield1) {
        this.extfield1 = extfield1 == null ? null : extfield1.trim();
    }

    public String getExtfield2() {
        return extfield2;
    }

    public void setExtfield2(String extfield2) {
        this.extfield2 = extfield2 == null ? null : extfield2.trim();
    }

    public String getExtfield3() {
        return extfield3;
    }

    public void setExtfield3(String extfield3) {
        this.extfield3 = extfield3 == null ? null : extfield3.trim();
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

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
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
        CustMessRecord other = (CustMessRecord) that;
        return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getSendType() == null ? other.getSendType() == null : this.getSendType().equals(other.getSendType()))
            && (this.getSendModel() == null ? other.getSendModel() == null : this.getSendModel().equals(other.getSendModel()))
            && (this.getSendContent() == null ? other.getSendContent() == null : this.getSendContent().equals(other.getSendContent()))
            && (this.getIsRead() == null ? other.getIsRead() == null : this.getIsRead().equals(other.getIsRead()))
            && (this.getExtfield1() == null ? other.getExtfield1() == null : this.getExtfield1().equals(other.getExtfield1()))
            && (this.getExtfield2() == null ? other.getExtfield2() == null : this.getExtfield2().equals(other.getExtfield2()))
            && (this.getExtfield3() == null ? other.getExtfield3() == null : this.getExtfield3().equals(other.getExtfield3()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastUpdateUser() == null ? other.getLastUpdateUser() == null : this.getLastUpdateUser().equals(other.getLastUpdateUser()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getSendType() == null) ? 0 : getSendType().hashCode());
        result = prime * result + ((getSendModel() == null) ? 0 : getSendModel().hashCode());
        result = prime * result + ((getSendContent() == null) ? 0 : getSendContent().hashCode());
        result = prime * result + ((getIsRead() == null) ? 0 : getIsRead().hashCode());
        result = prime * result + ((getExtfield1() == null) ? 0 : getExtfield1().hashCode());
        result = prime * result + ((getExtfield2() == null) ? 0 : getExtfield2().hashCode());
        result = prime * result + ((getExtfield3() == null) ? 0 : getExtfield3().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        return result;
    }
}