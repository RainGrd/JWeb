package com.yscf.core.model.activity;

import com.achievo.framework.entity.BaseEntity;

/**
 * 
 * @ClassName : ActivityDto
 * @Description : 活动管理公共的属性的JavaBean
 * @Author : Qing.Cai
 * @Date : 2015年9月17日 下午2:30:22
 */
public class ActivityDto extends BaseEntity {

	private static final long serialVersionUID = 2858294820960256675L;

	// 活动编号
	private String actCode;
	// 活动标签
	private String actTag;
	// 活动类型
	private String activityType;
	// 短信模版ID
	private String smsId;
	// 是否短信提醒
	private String isSmsWarn;
	// 活动描述
	private String actDesc;
	// 条件PIDS（1，2，3）
	private String condIds;
	// 短信编号
	private String tempCode;
	// 短信内容
	private String smsContent;
	// 适用开始时间-begin
	private String beginApplyStartData;
	// 适用开始时间-end
	private String endApplyStartData;
	// 适用结束时间-begin
	private String beginApplyFinishData;
	// 适用结束时间-end
	private String endApplyFinishData;
	// 用户名称
	private String createUserName;
	// 活动状态
	private String actStatus;

	public String getActStatus() {
		return actStatus;
	}

	public void setActStatus(String actStatus) {
		this.actStatus = actStatus;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getBeginApplyStartData() {
		return beginApplyStartData;
	}

	public void setBeginApplyStartData(String beginApplyStartData) {
		this.beginApplyStartData = beginApplyStartData;
	}

	public String getEndApplyStartData() {
		return endApplyStartData;
	}

	public void setEndApplyStartData(String endApplyStartData) {
		this.endApplyStartData = endApplyStartData;
	}

	public String getBeginApplyFinishData() {
		return beginApplyFinishData;
	}

	public void setBeginApplyFinishData(String beginApplyFinishData) {
		this.beginApplyFinishData = beginApplyFinishData;
	}

	public String getEndApplyFinishData() {
		return endApplyFinishData;
	}

	public void setEndApplyFinishData(String endApplyFinishData) {
		this.endApplyFinishData = endApplyFinishData;
	}

	public String getSmsContent() {
		return smsContent;
	}

	public void setSmsContent(String smsContent) {
		this.smsContent = smsContent;
	}

	public String getTempCode() {
		return tempCode;
	}

	public void setTempCode(String tempCode) {
		this.tempCode = tempCode;
	}

	public String getCondIds() {
		return condIds;
	}

	public void setCondIds(String condIds) {
		this.condIds = condIds;
	}

	public String getActCode() {
		return actCode;
	}

	public void setActCode(String actCode) {
		this.actCode = actCode == null ? null : actCode.trim();
	}

	public String getActTag() {
		return actTag;
	}

	public void setActTag(String actTag) {
		this.actTag = actTag == null ? null : actTag.trim();
	}

	public String getActivityType() {
		return activityType;
	}

	public void setActivityType(String activityType) {
		this.activityType = activityType == null ? null : activityType.trim();
	}

	public String getSmsId() {
		return smsId;
	}

	public void setSmsId(String smsId) {
		this.smsId = smsId == null ? null : smsId.trim();
	}

	public String getIsSmsWarn() {
		return isSmsWarn;
	}

	public void setIsSmsWarn(String isSmsWarn) {
		this.isSmsWarn = isSmsWarn == null ? null : isSmsWarn.trim();
	}

	public String getActDesc() {
		return actDesc;
	}

	public void setActDesc(String actDesc) {
		this.actDesc = actDesc == null ? null : actDesc.trim();
	}

}
