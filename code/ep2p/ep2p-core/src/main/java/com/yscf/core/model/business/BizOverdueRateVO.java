/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 逾期率报表VO类
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年11月5日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.model.business;

import java.math.BigDecimal;

/**
 * Description：<br> 
 * 逾期率报表VO类
 * @author  Yu.Zhang
 * @date    2015年11月5日
 * @version v1.0.0
 */
public class BizOverdueRateVO {
	
	/**
	 * 类别
	 */
	private String type;
	
	/**
	 * 期数
	 */
	private Integer overdueNum;
	
	/**
	 * 逾期金额
	 */
	private BigDecimal overdueAmt;
	
	/**
	 * 金额占比
	 */
	private BigDecimal amtProportion;
	
	/**
	 * 期数占比
	 */
	private BigDecimal numProportion;
	
	/**
	 * 开始逾期天数
	 */
	private Integer startOverdueDay;
	
	/**
	 * 结束逾期天数
	 */
	private Integer endOverdueDay;
	
	/**
	 * 系统当前时间 用于计算逾期天数
	 */
	private String toDay;


	public String getToDay() {
		return toDay;
	}


	public void setToDay(String toDay) {
		this.toDay = toDay;
	}


	public Integer getStartOverdueDay() {
		return startOverdueDay;
	}


	public void setStartOverdueDay(Integer startOverdueDay) {
		this.startOverdueDay = startOverdueDay;
	}


	public Integer getEndOverdueDay() {
		return endOverdueDay;
	}


	public void setEndOverdueDay(Integer endOverdueDay) {
		this.endOverdueDay = endOverdueDay;
	}


	public String getType() {
		return type;
	}


	public void setType(String type) {
		this.type = type;
	}


	public Integer getOverdueNum() {
		return overdueNum;
	}


	public void setOverdueNum(Integer overdueNum) {
		this.overdueNum = overdueNum;
	}


	public BigDecimal getOverdueAmt() {
		return overdueAmt;
	}


	public void setOverdueAmt(BigDecimal overdueAmt) {
		this.overdueAmt = overdueAmt;
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
	
}


