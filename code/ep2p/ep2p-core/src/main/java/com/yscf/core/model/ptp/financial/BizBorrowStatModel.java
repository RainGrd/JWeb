package com.yscf.core.model.ptp.financial;

import java.math.BigDecimal;


/**
 * 
 * Description：<br>
 * 借款统计VO
 * 
 * @author JunJie.Liu
 * @date 2015年11月6日
 * @version v1.0.0
 */
public class BizBorrowStatModel {

	// 借款类型
	private String borrowType;

	// 借款总金额（满标）
	private BigDecimal totalAmount;

	// 借款总笔数（满标）
	private Integer totalCount;
	
	// 金额占比
	private BigDecimal amountPercent;
	
	// 笔数
	private BigDecimal countPercent;

	public String getBorrowType() {
		return borrowType;
	}

	public void setBorrowType(String borrowType) {
		this.borrowType = borrowType;
	}

	public BigDecimal getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(BigDecimal totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}

	public BigDecimal getAmountPercent() {
		return amountPercent;
	}

	public void setAmountPercent(BigDecimal amountPercent) {
		this.amountPercent = amountPercent;
	}

	public BigDecimal getCountPercent() {
		return countPercent;
	}

	public void setCountPercent(BigDecimal countPercent) {
		this.countPercent = countPercent;
	}
	

}