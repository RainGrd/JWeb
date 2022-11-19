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
 * 2016年1月21日     shiliang.feng		Initial Version.
 *************************************************************************
 */
package com.yscf.api.vo.myinvest;

import java.math.BigDecimal;

/**
 * Description：<br>
 * APP散标 还款计划
 * 
 * @author shiliang.feng
 * @date 2016年1月21日
 * @version v1.0.0
 */
public class BizRepaymentPlanVo {
	// 还款计划ID
	private String pid;
	// 借款ID
	private String borrowId;
	// 本金
	private BigDecimal capital;
	// 利息
	private BigDecimal interest;
	// 还款期数
	private String planindex;

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(String borrowId) {
		this.borrowId = borrowId;
	}

	public BigDecimal getCapital() {
		return capital;
	}

	public void setCapital(BigDecimal capital) {
		this.capital = capital;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public String getPlanindex() {
		return planindex;
	}

	public void setPlanindex(String planindex) {
		this.planindex = planindex;
	}

}
