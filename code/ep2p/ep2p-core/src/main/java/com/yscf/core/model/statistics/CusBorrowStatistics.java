package com.yscf.core.model.statistics;

import java.math.BigDecimal;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br> 
 * 客户借款统计
 * @author  JingYu.Dai
 * @date    2015年11月5日
 * @version v1.0.0
 */
public class CusBorrowStatistics extends BaseEntity {

	private static final long serialVersionUID = 5627042607248025431L;
	
	/**
	 * 借款统计总额
	 */
	private BigDecimal borrowMoneySum;
	
	/**
	 * 客户统计人数
	 */
	private Integer customerCount;
	
	/**
	 * 省份
	 */
	private String province;
	
	/**
	 * 市
	 */
	private String city;

	public BigDecimal getBorrowMoneySum() {
		return borrowMoneySum;
	}

	public void setBorrowMoneySum(BigDecimal borrowMoneySum) {
		this.borrowMoneySum = borrowMoneySum;
	}

	public Integer getCustomerCount() {
		return customerCount;
	}

	public void setCustomerCount(Integer customerCount) {
		this.customerCount = customerCount;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}
   
	
	
}