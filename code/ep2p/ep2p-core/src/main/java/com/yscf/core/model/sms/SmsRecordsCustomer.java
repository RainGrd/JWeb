package com.yscf.core.model.sms;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br> 
 * 消息和人员信息关系表
 * @author  Lin Xu
 * @date    2015年10月22日
 * @version v1.0.0
 */
public class SmsRecordsCustomer extends BaseEntity {
	
	private static final long serialVersionUID = -506577711202870409L;
	//id
	private String pid;
	//消息id
    private String smsRecordId;
    //人员id
    private String customerId;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getSmsRecordId() {
        return smsRecordId;
    }

    public void setSmsRecordId(String smsRecordId) {
        this.smsRecordId = smsRecordId == null ? null : smsRecordId.trim();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
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
        SmsRecordsCustomer other = (SmsRecordsCustomer) that;
        return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getSmsRecordId() == null ? other.getSmsRecordId() == null : this.getSmsRecordId().equals(other.getSmsRecordId()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getSmsRecordId() == null) ? 0 : getSmsRecordId().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        return result;
    }
}