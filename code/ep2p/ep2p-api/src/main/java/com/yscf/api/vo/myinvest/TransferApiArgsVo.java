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


/**
 * Description：<br>
 * 债权转让API接口参数VO
 * 
 * @author JunJie.Liu
 * @date 2016年1月5日
 * @version v1.0.0
 */
public class TransferApiArgsVo {

	private String transferId; // 债权id

	private String userId; // 用户id

	private Integer pageNum; // 页码

	private Integer pageSize; // 条数

	private String type; // 请求类型 1:年化率 2:转让价格

	private boolean desc; // 排序 true:降序 false:升序

	private String tradePwd; // 交易密码

	private String borrowId; // 借款id

	private String receiptPlanId; // 收款计划id

	private String yearRate; // 年利率

	private String netAmount; // 净收益

	private String returnedDate; // 回款天数

	private String amount; // 转让金额
	
	private String transferType; // 转让类型 1.持有用 2.转让中 3.已转让
	
	private String tranPKey;

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public boolean isDesc() {
		return desc;
	}

	public void setDesc(boolean desc) {
		this.desc = desc;
	}

	public String getTransferId() {
		return transferId;
	}

	public void setTransferId(String transferId) {
		this.transferId = transferId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getTradePwd() {
		return tradePwd;
	}

	public void setTradePwd(String tradePwd) {
		this.tradePwd = tradePwd;
	}

	public String getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(String borrowId) {
		this.borrowId = borrowId;
	}

	public String getReceiptPlanId() {
		return receiptPlanId;
	}

	public void setReceiptPlanId(String receiptPlanId) {
		this.receiptPlanId = receiptPlanId;
	}

	public String getYearRate() {
		return yearRate;
	}

	public void setYearRate(String yearRate) {
		this.yearRate = yearRate;
	}

	public String getNetAmount() {
		return netAmount;
	}

	public void setNetAmount(String netAmount) {
		this.netAmount = netAmount;
	}

	public String getReturnedDate() {
		return returnedDate;
	}

	public void setReturnedDate(String returnedDate) {
		this.returnedDate = returnedDate;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTransferType() {
		return transferType;
	}

	public void setTransferType(String transferType) {
		this.transferType = transferType;
	}

	public String getTranPKey() {
		return tranPKey;
	}

	public void setTranPKey(String tranPKey) {
		this.tranPKey = tranPKey;
	}

	
	
}
