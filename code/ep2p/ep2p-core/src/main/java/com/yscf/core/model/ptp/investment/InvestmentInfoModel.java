/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 招标中、待收中、已结清的投资信息数据
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年11月30日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.model.ptp.investment;


import java.math.BigDecimal;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br> 
 * 已结清的投资信息数据
 * @author  Lin Xu
 * @date    2015年11月30日
 * @version v1.0.0
 */
public class InvestmentInfoModel extends BaseEntity {

	private static final long serialVersionUID = -2624125758352762353L;
	
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
	//投资金额
	private BigDecimal investmentAmount;
    //借款期限
    private String borDeadline;
    //年利率
    private BigDecimal borrowApr;
	//加息
	private BigDecimal hike;
	//投资奖励
	private BigDecimal investmentReward;
	//利息
	private BigDecimal interest;
	//投标状态
	private String tenderStatus;
	//协议模板id
	private String protocolId;
	//投资时间
	private String investmentTime;
	//投资开始时间
	private String startInvestmentTime;
	//投资结束时间
	private String endInvestmentTime;
	//转让id
	private String transferId;
	
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

	public BigDecimal getInvestmentAmount() {
		return investmentAmount;
	}

	public void setInvestmentAmount(BigDecimal investmentAmount) {
		this.investmentAmount = investmentAmount;
	}

	public String getBorDeadline() {
		return borDeadline;
	}

	public void setBorDeadline(String borDeadline) {
		this.borDeadline = borDeadline;
	}

	public BigDecimal getBorrowApr() {
		return borrowApr;
	}

	public void setBorrowApr(BigDecimal borrowApr) {
		this.borrowApr = borrowApr;
	}

	public BigDecimal getHike() {
		return hike;
	}

	public void setHike(BigDecimal hike) {
		this.hike = hike;
	}

	public BigDecimal getInvestmentReward() {
		return investmentReward;
	}

	public void setInvestmentReward(BigDecimal investmentReward) {
		this.investmentReward = investmentReward;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public String getTenderStatus() {
		return tenderStatus;
	}

	public void setTenderStatus(String tenderStatus) {
		this.tenderStatus = tenderStatus;
	}

	public String getProtocolId() {
		return protocolId;
	}

	public void setProtocolId(String protocolId) {
		this.protocolId = protocolId;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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

	public String getInvestmentTime() {
		return investmentTime;
	}

	public void setInvestmentTime(String investmentTime) {
		this.investmentTime = investmentTime;
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

	public String getTransferId() {
		return transferId;
	}

	public void setTransferId(String transferId) {
		this.transferId = transferId;
	}
    
    
}


