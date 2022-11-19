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
 * 2015年11月3日     JunJie.Liu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.model.ptp.financial;

import java.math.BigDecimal;


/**
 * Description：<br>
 * 提现VO
 * 
 * @author JunJie.Liu
 * @date 2015年11月3日
 * @version v1.0.0
 */
public class BizWithdrawModel {

	private String customerName;

	private String sname;

	private String phoneNo;

	private String applyTime;// 申请时间

	private BigDecimal amount;// 提现金额

	private BigDecimal fee;// 手续费

	private String belongingBank;// 银行

	private String openAddress;// 银行支行

	private String bankcardNo; // 卡号

	private String auditStatus; // 状态

	private String auditStatusName;// 状态名称

	private String witDesc;// 备注

	private String witSureDesc;// 用户名

	private String queryStatus; // 类型

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getFee() {
		return fee;
	}

	public void setFee(BigDecimal fee) {
		this.fee = fee;
	}

	public String getBelongingBank() {
		return belongingBank;
	}

	public void setBelongingBank(String belongingBank) {
		this.belongingBank = belongingBank;
	}

	public String getOpenAddress() {
		return openAddress;
	}

	public void setOpenAddress(String openAddress) {
		this.openAddress = openAddress;
	}

	public String getBankcardNo() {
		return bankcardNo;
	}

	public void setBankcardNo(String bankcardNo) {
		this.bankcardNo = bankcardNo;
	}

	public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	public String getAuditStatusName() {
		return auditStatusName;
	}

	public void setAuditStatusName(String auditStatusName) {
		this.auditStatusName = auditStatusName;
	}

	public String getWitDesc() {
		return witDesc;
	}

	public void setWitDesc(String witDesc) {
		this.witDesc = witDesc;
	}

	public String getWitSureDesc() {
		return witSureDesc;
	}

	public void setWitSureDesc(String witSureDesc) {
		this.witSureDesc = witSureDesc;
	}

	/**
	 * 获取状态
	 * 
	 * @param value
	 * @param row
	 * @param index
	 * @returns {String}
	 */
	public String formatAuditStatus(String auditStatus) {
		// 审核状态（1：申请中、2：同意（确认中）、3：拒绝、4：转账成功、5：转账失败）
		if ("1".equals(auditStatus)) {
			return "提现申请";
		} else if ("2".equals(auditStatus)) {
			return "转账确认";
		} else if ("3".equals(auditStatus)) {
			return "提现拒绝";
		} else if ("4".equals(auditStatus)) {
			return "转账成功";
		} else if ("5".equals(auditStatus)) {
			return "转账失败";
		}
		return "";
	}

	public String getQueryStatus() {
		return queryStatus;
	}

	public void setQueryStatus(String queryStatus) {
		this.queryStatus = queryStatus;
	}
	
	
}
