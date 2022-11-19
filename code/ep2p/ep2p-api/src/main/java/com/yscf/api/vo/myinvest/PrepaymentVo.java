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
 * 2016年1月5日     JunJie.Liu		Initial Version.
 *************************************************************************
 */
package com.yscf.api.vo.myinvest;

import java.math.BigDecimal;


/**
 * Description：<br>
 * 提前还款接口参数VO
 * 
 * @author JunJie.Liu
 * @date 2016年1月5日
 * @version v1.0.0
 */
public class PrepaymentVo {

	/**
	 * 借款ID
	 */
	private String borrowId;
	
	/**
	 * 提前还款金额
	 */
	private  BigDecimal repaymentAmount;
	

	public BigDecimal getRepaymentAmount() {
		return repaymentAmount;
	}

	public void setRepaymentAmount(BigDecimal repaymentAmount) {
		this.repaymentAmount = repaymentAmount;
	}

	public String getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(String borrowId) {
		this.borrowId = borrowId;
	}
	
}
