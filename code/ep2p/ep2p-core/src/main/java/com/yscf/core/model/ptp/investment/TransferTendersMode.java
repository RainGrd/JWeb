/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 转让中和已转的信息数据模型
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2016年1月25日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.model.ptp.investment;

import java.math.BigDecimal;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br> 
 * 转让中和已转的信息数据模型
 * @author  Lin Xu
 * @date    2016年1月25日
 * @version v1.0.0
 */
public class TransferTendersMode extends BaseEntity {

	private static final long serialVersionUID = 1077703800259976927L;
	//pid
	private String pid;
	//用户id
	private String customerId;
	//借款类型
	private String borrowType;
	//借款编号
	private String borrowCode;
	//借款名称
	private String borrowName;
	//转让编号
	private String tranderCode;
	//转让期数
	private Integer timeRemaining;
	//项目价值
	private BigDecimal projectValue;
	//项目转让价格
	private BigDecimal successAmount;
	//状态信息
	private String trandStatus;
	//合同编号
	private String protocolId;
	//转让时间
	private String investmentTime;
	//转让开始时间
	private String startInvestmentTime;
	//转让结束时间
	private String endInvestmentTime;
	
	/* 扩展字段信息 */
	//每页显示的条数
	private Integer pagesize;
	//当前页
	private Integer page;
	
	
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
	public String getTranderCode() {
		return tranderCode;
	}
	public void setTranderCode(String tranderCode) {
		this.tranderCode = tranderCode;
	}
	public Integer getTimeRemaining() {
		return timeRemaining;
	}
	public void setTimeRemaining(Integer timeRemaining) {
		this.timeRemaining = timeRemaining;
	}
	public BigDecimal getProjectValue() {
		return projectValue;
	}
	public void setProjectValue(BigDecimal projectValue) {
		this.projectValue = projectValue;
	}
	public BigDecimal getSuccessAmount() {
		return successAmount;
	}
	public void setSuccessAmount(BigDecimal successAmount) {
		this.successAmount = successAmount;
	}
	public String getInvestmentTime() {
		return investmentTime;
	}
	public void setInvestmentTime(String investmentTime) {
		this.investmentTime = investmentTime;
	}
	public String getStartInvestmentTime() {
		return startInvestmentTime;
	}
	public void setStartInvestmentTime(String startInvestmentTime) {
		this.startInvestmentTime = startInvestmentTime;
	}
	public String getEndInvestmentTime() {
		return endInvestmentTime;
	}
	public void setEndInvestmentTime(String endInvestmentTime) {
		this.endInvestmentTime = endInvestmentTime;
	}
	public String getTrandStatus() {
		return trandStatus;
	}
	public void setTrandStatus(String trandStatus) {
		this.trandStatus = trandStatus;
	}
	public String getProtocolId() {
		return protocolId;
	}
	public void setProtocolId(String protocolId) {
		this.protocolId = protocolId;
	}
	public Integer getPagesize() {
		return pagesize;
	}
	public void setPagesize(Integer pagesize) {
		this.pagesize = pagesize;
	}
	public Integer getPage() {
		return page;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public String getBorrowType() {
		return borrowType;
	}
	public void setBorrowType(String borrowType) {
		this.borrowType = borrowType;
	}
	
}


