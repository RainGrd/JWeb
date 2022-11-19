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
 * 2016年1月6日     JunJie.Liu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.model.ptp.financial;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Description：<br>
 * 投资记录 Vo
 * 
 * @author JunJie.Liu
 * @date 2016年1月6日
 * @version v1.0.0
 */
public class BizDetailModel {

	private BigDecimal investAmount; // 投资金额

	private Date investTime; // 　投资时间

	private BigDecimal awardAmount; // 投资奖励

	public BigDecimal getInvestAmount() {
		return investAmount;
	}

	public void setInvestAmount(BigDecimal investAmount) {
		this.investAmount = investAmount;
	}

	public Date getInvestTime() {
		return investTime;
	}

	public void setInvestTime(Date investTime) {
		this.investTime = investTime;
	}

	public BigDecimal getAwardAmount() {
		return awardAmount;
	}

	public void setAwardAmount(BigDecimal awardAmount) {
		this.awardAmount = awardAmount;
	}

}
