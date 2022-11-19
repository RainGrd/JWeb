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
package com.yscf.api.vo.persionalcenter;

/**
 * Description：<br>
 * 待收详情参数VO
 * 
 * @author JunJie.Liu
 * @date 2016年1月5日
 * @version v1.0.0
 */
public class ReceiptPlanArgsVo {

	private String userId; // 用户id

	private String receiptPlanId; // 收款计划id

	private String borrowId; // 项目详情

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getReceiptPlanId() {
		return receiptPlanId;
	}

	public void setReceiptPlanId(String receiptPlanId) {
		this.receiptPlanId = receiptPlanId;
	}

	public String getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(String borrowId) {
		this.borrowId = borrowId;
	}

}
