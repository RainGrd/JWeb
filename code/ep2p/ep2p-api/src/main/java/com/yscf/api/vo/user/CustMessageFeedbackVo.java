package com.yscf.api.vo.user;

import java.util.Date;

import com.achievo.framework.entity.BaseEntity;
/**
 * Description：<br> 
 * 意见反馈
 * @author  heng.wang
 * @date    2015年1月15日
 * @version v1.0.0
 */
public class CustMessageFeedbackVo extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7291937260903243202L;

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
		this.pid = pid;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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
		this.submitContent = submitContent;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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
		this.disposeDesc = disposeDesc;
	}

	public String getExtfield1() {
		return extfield1;
	}

	public void setExtfield1(String extfield1) {
		this.extfield1 = extfield1;
	}

	public String getExtfield2() {
		return extfield2;
	}

	public void setExtfield2(String extfield2) {
		this.extfield2 = extfield2;
	}

	public String getExtfield3() {
		return extfield3;
	}

	public void setExtfield3(String extfield3) {
		this.extfield3 = extfield3;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
    
    
}
