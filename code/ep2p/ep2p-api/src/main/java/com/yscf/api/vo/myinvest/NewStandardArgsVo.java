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
 * 新手标参数VO
 * 
 * @author JunJie.Liu
 * @date 2016年1月5日
 * @version v1.0.0
 */
public class NewStandardArgsVo {

	private String userId; // 用户id

	private String borrowId; // 标的Id

	private Integer pageNum; // 页码

	private Integer pageSize; // 条数

	private String tradePwd; // 交易密码

	private String amount; // 金额

	private String investAwardId;// 加息券id

	private String tranPKey;

	public String getBorrowId() {
		return borrowId;
	}

	public String getInvestAwardId() {
		return investAwardId;
	}

	public void setInvestAwardId(String investAwardId) {
		this.investAwardId = investAwardId;
	}

	public void setBorrowId(String borrowId) {
		this.borrowId = borrowId;
	}

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

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getTranPKey() {
		return tranPKey;
	}

	public void setTranPKey(String tranPKey) {
		this.tranPKey = tranPKey;
	}

}
