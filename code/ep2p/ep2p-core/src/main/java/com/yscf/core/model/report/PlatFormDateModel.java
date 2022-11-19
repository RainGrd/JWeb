/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 平台数据模型
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年11月4日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.model.report;

import java.math.BigDecimal;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br> 
 * 平台数据和备用金报表
 * @author  Lin Xu
 * @date    2015年11月4日
 * @version v1.0.0
 */
public class PlatFormDateModel extends BaseEntity {
	
	private static final long serialVersionUID = 6995461279941034689L;
	//主键id
	private String pid;
	//年份
	private String year;
	//月份
	private String month;
	//年月
	private String yearmonth;
	//金额
	private BigDecimal amountMoney;
	//类型
	private String type;
	
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public String getMonth() {
		return month;
	}
	public void setMonth(String month) {
		this.month = month;
	}
	public BigDecimal getAmountMoney() {
		return amountMoney;
	}
	public void setAmountMoney(BigDecimal amountMoney) {
		this.amountMoney = amountMoney;
	}
	public String getYearmonth() {
		return yearmonth;
	}
	public void setYearmonth(String yearmonth) {
		this.yearmonth = yearmonth;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}


