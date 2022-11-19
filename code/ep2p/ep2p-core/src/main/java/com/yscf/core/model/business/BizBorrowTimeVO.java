/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 借款项目期限数据占比VO类
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年11月11日     Jie.Zou		Initial Version.
 *************************************************************************
 */
package com.yscf.core.model.business;

import java.math.BigDecimal;

/**
 * Description：<br> 
 * 借款项目期限数据占比VO类
 * @author  Jie.Zou
 * @date    2015年11月11日
 * @version v1.0.0
 */
public class BizBorrowTimeVO {
	/**
	 * 借款期限类型
	 */
	private String timeType;
	
	/**
	 * 借款总金额
	 */
	private BigDecimal borrowAmt;
	
	/**
	 * 笔数
	 */
	private Integer borrowNum;
	
	/**
	 * 金额占比
	 */
	private BigDecimal amtProportion;
	
	/**
	 * 笔数占比
	 */
	private BigDecimal numProportion;
	
	/**
	 * 开始借款期限
	 */
	private Integer startBorrowTime;
	
	/**
	 * 结束借款期限
	 */
	private Integer endBorrowTime;
	

	public String getTimeType() {
		return timeType;
	}

	public void setTimeType(String timeType) {
		this.timeType = timeType;
	}

	public BigDecimal getBorrowAmt() {
		if(borrowAmt!=null){
			return borrowAmt;
		}
		return borrowAmt;
	}

	public void setBorrowAmt(BigDecimal borrowAmt) {
		this.borrowAmt = borrowAmt;
	}

	public Integer getBorrowNum() {
		return borrowNum;
	}

	public void setBorrowNum(Integer borrowNum) {
		this.borrowNum = borrowNum;
	}

	public BigDecimal getAmtProportion() {
		return amtProportion;
	}

	public void setAmtProportion(BigDecimal amtProportion) {
		this.amtProportion = amtProportion;
	}

	public BigDecimal getNumProportion() {
		return numProportion;
	}

	public void setNumProportion(BigDecimal numProportion) {
		this.numProportion = numProportion;
	}

	public Integer getStartBorrowTime() {
		return startBorrowTime;
	}

	public void setStartBorrowTime(Integer startBorrowTime) {
		this.startBorrowTime = startBorrowTime;
	}

	public Integer getEndBorrowTime() {
		return endBorrowTime;
	}

	public void setEndBorrowTime(Integer endBorrowTime) {
		this.endBorrowTime = endBorrowTime;
	}
	
	
}


