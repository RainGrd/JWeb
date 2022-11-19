package com.yscf.core.util;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br> 
 * 年、月、日 DTO  用做 按年 按月 按日 统计数据时  作为接收对象
 * @author  JingYu.Dai
 * @date    2015年11月4日
 * @version v1.0.0
 */
public class YTDDto extends BaseEntity {

	private static final long serialVersionUID = -6309193682958714723L;

	/**
	 * 年份
	 */
	private String yearD;

	/**
	 * 月份
	 */
	private Integer monthD;

	/**
	 * 天
	 */
	private Integer dayD;
	
	/**
	 * 时间
	 */
	private String date;

	/**
	 * 统计数
	 */
	private Integer countD;

	public String getYearD() {
		return yearD;
	}

	public void setYearD(String yearD) {
		this.yearD = yearD;
	}

	public Integer getMonthD() {
		return monthD;
	}

	public void setMonthD(Integer monthD) {
		this.monthD = monthD;
	}

	public Integer getDayD() {
		return dayD;
	}

	public void setDayD(Integer dayD) {
		this.dayD = dayD;
	}

	public String getDateT() {
		return date;
	}

	public void setDateT(String dateT) {
		this.date = dateT;
	}

	public Integer getCountD() {
		return countD;
	}

	public void setCountD(Integer countD) {
		this.countD = countD;
	}
	
	
}