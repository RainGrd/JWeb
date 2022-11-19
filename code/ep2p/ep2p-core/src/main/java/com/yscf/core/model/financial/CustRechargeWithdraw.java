/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年12月21日     JingYu.Dai		Initial Version.
 *************************************************************************
 */
package com.yscf.core.model.financial;

import java.math.BigDecimal;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br> 
 * 个人中心	充值提现	流水类  
 * 注意：这个类对应线 上充值表和线下充值表 
 * 作用：用于客户个人中心充值查询
 * @author  JingYu.Dai
 * @date    2015年12月21日
 * @version v1.0.0
 */
public class CustRechargeWithdraw extends BaseEntity{

	private static final long serialVersionUID = 7141220319377467507L;
	
	private String pid;	//主键
	private String recOrderNo;	//充值流水号
	private BigDecimal amount;	//金额
	
	/***************个人中心充值字段开始*********************/
	private String customerId;	//客户ID
	private String payName;	//充值平台名称
	private String recStatus;	//充值状态
	private String recTime;	//充值时间
	private String status;	//状态
	/***************个人中心充值字段结束*********************/
	
	/***************个人中心提现字段开始*********************/
	private String applyTime;	//申请时间
	private String fee;	//提现手续费
	private String bankId;	//银行卡ID
	private String bankcardNo;	//银行卡 卡号
	private String auditStatus;	//审核状态（1：提现审核、2：同意（转账确认）、3：提现拒绝、4：转账成功、5：转账失败）
	private String witDesc;	//备注
	/***************个人中心提现字段结束*********************/
	
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
	public String getPayName() {
		return payName;
	}
	public void setPayName(String payName) {
		this.payName = payName;
	}
	public BigDecimal getAmount() {
		return amount;
	}
	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}
	public String getRecTime() {
		return recTime;
	}
	public void setRecTime(String recTime) {
		this.recTime = recTime;
	}
	public String getRecOrderNo() {
		return recOrderNo;
	}
	public void setRecOrderNo(String recOrderNo) {
		this.recOrderNo = recOrderNo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getRecStatus() {
		return recStatus;
	}
	public void setRecStatus(String recStatus) {
		this.recStatus = recStatus;
	}
	public String getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}
	public String getFee() {
		return fee;
	}
	public void setFee(String fee) {
		this.fee = fee;
	}
	public String getBankId() {
		return bankId;
	}
	public void setBankId(String bankId) {
		this.bankId = bankId;
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
	public String getWitDesc() {
		return witDesc;
	}
	public void setWitDesc(String witDesc) {
		this.witDesc = witDesc;
	}

}


