package com.yscf.core.model.sms;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br> 
 * 消息发送记录信息
 * @author  Lin Xu
 * @date    2015年10月22日
 * @version v1.0.0
 */
public class SmsRecordsModel extends BaseEntity {
   
	private static final long serialVersionUID = 867831327906896187L;
	//主键
	private String pid;
	//消息编码
    private String smsCode;
    //手机号码
    private String phoneNo;
    //消息内容
    private String smsContext;
    //发送的类型  1 短信  2.微信   3.APP 4.系统消息
    private String pushType;
    //发送模块
    private String pushModel;
    //创建人即发送人
    private String createUser;
    //创建时间
    private String createTime;
    
    public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getSmsCode() {
        return smsCode;
    }

    public void setSmsCode(String smsCode) {
        this.smsCode = smsCode == null ? null : smsCode.trim();
    }

    public String getSmsContext() {
        return smsContext;
    }

    public void setSmsContext(String smsContext) {
        this.smsContext = smsContext == null ? null : smsContext.trim();
    }

    public String getPushType() {
        return pushType;
    }

    public void setPushType(String pushType) {
        this.pushType = pushType == null ? null : pushType.trim();
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
        SmsRecordsModel other = (SmsRecordsModel) that;
        return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getSmsCode() == null ? other.getSmsCode() == null : this.getSmsCode().equals(other.getSmsCode()))
            && (this.getSmsContext() == null ? other.getSmsContext() == null : this.getSmsContext().equals(other.getSmsContext()))
            && (this.getPushType() == null ? other.getPushType() == null : this.getPushType().equals(other.getPushType()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getSmsCode() == null) ? 0 : getSmsCode().hashCode());
        result = prime * result + ((getSmsContext() == null) ? 0 : getSmsContext().hashCode());
        result = prime * result + ((getPushType() == null) ? 0 : getPushType().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        return result;
    }

	public String getPushModel() {
		return pushModel;
	}

	public void setPushModel(String pushModel) {
		this.pushModel = pushModel;
	}
}