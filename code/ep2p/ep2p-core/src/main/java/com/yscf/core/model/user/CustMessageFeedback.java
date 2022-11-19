package com.yscf.core.model.user;

import com.achievo.framework.entity.BaseEntity;
import java.util.Date;

public class CustMessageFeedback extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 5284091919577471190L;

	private String pid;

    private String customerId; //提交人

    private String submitTime; //提交时间

    private String submitContent; //提交内容

    private String userId; //处理人 用户ID：t_sys_user.pid

    private Date disposeTime; //处理时间

    private String disposeDesc; //意见备注

    private String extfield1;

    private String extfield2;

    private String extfield3;

    private String status;//数据状态(默认为1，0 删除)

    private String version;

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

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public String getSubmitContent() {
        return submitContent;
    }

    public void setSubmitContent(String submitContent) {
        this.submitContent = submitContent == null ? null : submitContent.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
    }

    public Date getDisposeTime() {
        return disposeTime;
    }

    public void setDisposeTime(Date disposeTime) {
        this.disposeTime = disposeTime;
    }

    public String getDisposeDesc() {
        return disposeDesc;
    }

    public void setDisposeDesc(String disposeDesc) {
        this.disposeDesc = disposeDesc == null ? null : disposeDesc.trim();
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
        CustMessageFeedback other = (CustMessageFeedback) that;
        return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getSubmitTime() == null ? other.getSubmitTime() == null : this.getSubmitTime().equals(other.getSubmitTime()))
            && (this.getSubmitContent() == null ? other.getSubmitContent() == null : this.getSubmitContent().equals(other.getSubmitContent()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getDisposeTime() == null ? other.getDisposeTime() == null : this.getDisposeTime().equals(other.getDisposeTime()))
            && (this.getDisposeDesc() == null ? other.getDisposeDesc() == null : this.getDisposeDesc().equals(other.getDisposeDesc()))
            && (this.getExtfield1() == null ? other.getExtfield1() == null : this.getExtfield1().equals(other.getExtfield1()))
            && (this.getExtfield2() == null ? other.getExtfield2() == null : this.getExtfield2().equals(other.getExtfield2()))
            && (this.getExtfield3() == null ? other.getExtfield3() == null : this.getExtfield3().equals(other.getExtfield3()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getSubmitTime() == null) ? 0 : getSubmitTime().hashCode());
        result = prime * result + ((getSubmitContent() == null) ? 0 : getSubmitContent().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getDisposeTime() == null) ? 0 : getDisposeTime().hashCode());
        result = prime * result + ((getDisposeDesc() == null) ? 0 : getDisposeDesc().hashCode());
        result = prime * result + ((getExtfield1() == null) ? 0 : getExtfield1().hashCode());
        result = prime * result + ((getExtfield2() == null) ? 0 : getExtfield2().hashCode());
        result = prime * result + ((getExtfield3() == null) ? 0 : getExtfield3().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        return result;
    }
}