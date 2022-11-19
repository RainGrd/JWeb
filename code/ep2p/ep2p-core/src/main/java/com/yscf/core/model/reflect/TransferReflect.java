/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * TODO
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2016年2月19日     JunJie.Liu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.model.reflect;

/**
 * Description：<br>
 * 债权转让协议通配符替换类
 * 
 * @author JunJie.Liu
 * @date 2016年2月19日
 * @version v1.0.0
 */
public class TransferReflect {
	
	private String code;

	// 甲方,平台注册账号
	private String userA;

	// 乙方,平台注册账号
	private String userB;
	// 担保人
	private String bondsman;
	// 平台：后台配置
	private String platform;

	// 生效时间:成交时间
	private String time;
	// 借款人
	private String borrowUserName;
	// 借款编号
	private String borrowCode;
	// 借款名称
	private String borrowName;
	// 借款结束时间 格式： 年/月/日
	private String endDate;
	// 还款方式名称
	private String repaymentTypeName;
	// 保障方式:后台配置
	private String guaranteeType;

	// 债权剩余期限
	private String deadline;

	// 剩余本金
	private String transferAmount;

	// 年化率
	private String yearRate;

	// 成交金额
	private String successAmount;

	// 利息
	private String interest;

	// 项目本金
	private String totalAmount;

	public String getUserA() {
		return userA;
	}

	public void setUserA(String userA) {
		this.userA = userA;
	}

	public String getUserB() {
		return userB;
	}

	public void setUserB(String userB) {
		this.userB = userB;
	}

	public String getBondsman() {
		return bondsman;
	}

	public void setBondsman(String bondsman) {
		this.bondsman = bondsman;
	}

	public String getPlatform() {
		return platform;
	}

	public void setPlatform(String platform) {
		this.platform = platform;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getBorrowUserName() {
		return borrowUserName;
	}

	public void setBorrowUserName(String borrowUserName) {
		this.borrowUserName = borrowUserName;
	}

	public String getBorrowCode() {
		return borrowCode;
	}

	public void setBorrowCode(String borrowCode) {
		this.borrowCode = borrowCode;
	}

	public String getBorrowName() {
		return borrowName;
	}

	public void setBorrowName(String borrowName) {
		this.borrowName = borrowName;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getRepaymentTypeName() {
		return repaymentTypeName;
	}

	public void setRepaymentTypeName(String repaymentTypeName) {
		this.repaymentTypeName = repaymentTypeName;
	}

	public String getGuaranteeType() {
		return guaranteeType;
	}

	public void setGuaranteeType(String guaranteeType) {
		this.guaranteeType = guaranteeType;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public String getTransferAmount() {
		return transferAmount;
	}

	public void setTransferAmount(String transferAmount) {
		this.transferAmount = transferAmount;
	}

	public String getYearRate() {
		return yearRate;
	}

	public void setYearRate(String yearRate) {
		this.yearRate = yearRate;
	}

	public String getSuccessAmount() {
		return successAmount;
	}

	public void setSuccessAmount(String successAmount) {
		this.successAmount = successAmount;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	

}
