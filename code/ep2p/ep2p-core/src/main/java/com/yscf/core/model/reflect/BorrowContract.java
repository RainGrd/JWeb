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
 * 2016年3月8日     JunJie.Liu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.model.reflect;

/**
 * Description：<br>
 * 借款合同，模板替换数据对象
 * 
 * @author JunJie.Liu
 * @date 2016年3月8日
 * @version v1.0.0
 */
public class BorrowContract {

	private String code;
	// 借款人
	private String borrowUserName;
	// 投资人
	private String investUserName;
	// 担保人
	private String bondsman;
	// 平台：后台配置
	private String platform;
	// 平台地址：后台配置
	private String platfromAddress;
	// 借款编号
	private String borrowCode;
	// 借款名称
	private String borrowName;
	// 借款期限
	private String bd;
	// 借款开始时间 格式： 年/月/日
	private String startDate;
	// 借款结束时间 格式： 年/月/日
	private String endDate;
	// 还款方式名称
	private String repaymentTypeName;
	// 保障方式:后台配置
	private String guaranteeType;
	// 借款金额(大写)（大写）伍佰叁拾玖元陆角壹分
	private String amountInWords;
	// 借款金额 小写￥539.61
	private String amount;
	
	public String getBorrowUserName() {
		return borrowUserName;
	}
	public void setBorrowUserName(String borrowUserName) {
		this.borrowUserName = borrowUserName;
	}
	public String getInvestUserName() {
		return investUserName;
	}
	public void setInvestUserName(String investUserName) {
		this.investUserName = investUserName;
	}
	public String getBondsman() {
		return bondsman;
	}
	public void setBondsman(String bondsman) {
		this.bondsman = bondsman;
	}
	public String getPlatform() {
		return platform;
	}
	public void setPlatform(String platform) {
		this.platform = platform;
	}
	public String getPlatfromAddress() {
		return platfromAddress;
	}
	public void setPlatfromAddress(String platfromAddress) {
		this.platfromAddress = platfromAddress;
	}
	public String getBorrowCode() {
		return borrowCode;
	}
	public void setBorrowCode(String borrowCode) {
		this.borrowCode = borrowCode;
	}
	public String getBorrowName() {
		return borrowName;
	}
	public void setBorrowName(String borrowName) {
		this.borrowName = borrowName;
	}
	public String getBd() {
		return bd;
	}
	public void setBd(String bd) {
		this.bd = bd;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	public String getEndDate() {
		return endDate;
	}
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getRepaymentTypeName() {
		return repaymentTypeName;
	}
	public void setRepaymentTypeName(String repaymentTypeName) {
		this.repaymentTypeName = repaymentTypeName;
	}
	public String getGuaranteeType() {
		return guaranteeType;
	}
	public void setGuaranteeType(String guaranteeType) {
		this.guaranteeType = guaranteeType;
	}
	public String getAmountInWords() {
		return amountInWords;
	}
	public void setAmountInWords(String amountInWords) {
		this.amountInWords = amountInWords;
	}
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
}
