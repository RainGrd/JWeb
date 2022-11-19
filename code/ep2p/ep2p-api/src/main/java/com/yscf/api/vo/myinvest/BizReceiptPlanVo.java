package com.yscf.api.vo.myinvest;

import java.math.BigDecimal;

/**
 * 
 * Description：<br> 
 * 收款计划VO
 * @author  JunJie.Liu
 * @date    2015年11月11日
 * @version v1.0.0
 */
public class BizReceiptPlanVo {
	
    /**
     * 应收本金
     */
    private BigDecimal capital;

    /**
     * 到期时间
     */
    private String rDate;
    
    /**
     * 利息 
     */
    private BigDecimal netInterest;
    
	public BigDecimal getNetInterest() {
		return netInterest;
	}

	public void setNetInterest(BigDecimal netInterest) {
		this.netInterest = netInterest;
	}


    public BigDecimal getCapital() {
        return capital;
    }

    public void setCapital(BigDecimal capital) {
        this.capital = capital;
    }

	public String getRDate() {
		return rDate;
	}

	public void setRDate(String rDate) {
		this.rDate = rDate;
	}

	
	
}