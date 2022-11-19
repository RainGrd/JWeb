package com.yscf.core.model.ptp.financial;

import java.math.BigDecimal;

/**
 * Description：<br>
 * 资金流水表(t_biz_fundtally)
 * 
 * @author JingYu.Dai
 * @date 2015年10月28日
 * @version v1.0.0
 */
public class BizFundtallyModel {

	/**
	 * 处理时间
	 */
	private String actorTime;

	/**
	 * 客户名
	 */
	private String customerName;

	/**
	 * 姓名
	 */
	private String sname;

	/**
	 * 手机号码
	 */
	private String phoneNo;

	/**
	 * 收入金额 没有相对应的表字段
	 */
	private BigDecimal incomeAmount;

	/**
	 * 支出支出 没有相对应的表字段
	 */
	private BigDecimal outlayAmount;

	/**
	 * 金额
	 */
	private BigDecimal amount;

	/**
	 * 余额
	 */
	private BigDecimal balance;

	/**
	 * 流水类型（0-未定义，1-线下充值，2-线上充值，3-提现，4-借款成功，
	 * 5-投标成功，6-还款，7-收款，8-VIP付费，9-利息管理费，10-支付投标奖励，
	 * 11-投标奖励，12-收取担保费用，13-支付担保费用，14-推荐借款费用，
	 * 15-VIP推荐奖金，16-借款管理费，17-提现手续费，18-充值提现费，
	 * 19-线下充值返利，20-赠送体验金，21-系统回收体验金，22-收取本金，
	 * 23-收取利息，24-收取罚息，25-归还本金，26-归还利息，27-罚息，
	 * 28-收取垫付本金，29-收取垫付利息，30-垫付本金，31-垫付利息，32-续投奖励， 33-债权转让，34-债权转让服务费，35-购买债权）
	 */
	private String detailType;

	private String detailTypeName;

	/**
	 * 交易类型（1：收入、2：支出）
	 */
	private String dealType;

	/**
	 * 备注
	 */
	private String funDesc;

	public String getActorTime() {
		return actorTime;
	}

	public void setActorTime(String actorTime) {
		this.actorTime = actorTime;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public BigDecimal getIncomeAmount() {
		return incomeAmount;
	}

	public void setIncomeAmount(BigDecimal incomeAmount) {
		this.incomeAmount = incomeAmount;
	}

	public BigDecimal getOutlayAmount() {
		return outlayAmount;
	}

	public void setOutlayAmount(BigDecimal outlayAmount) {
		this.outlayAmount = outlayAmount;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}

	public String getDetailType() {
		return detailType;
	}

	public void setDetailType(String detailType) {
		this.detailType = detailType;
	}

	public String getDealType() {
		return dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType;
	}

	public String getFunDesc() {
		return funDesc;
	}

	public void setFunDesc(String funDesc) {
		this.funDesc = funDesc;
	}

	public String getDetailTypeName() {
		return detailTypeName;
	}

	public void setDetailTypeName(String detailTypeName) {
		this.detailTypeName = detailTypeName;
	}

	public String getTypeName(String type) {

		if ("1".equals(type)) {
			return "线下充值";
		} else if ("2".equals(type)) {
			return "线上充值";
		} else if ("3".equals(type)) {
			return "提现";
		} else if ("4".equals(type)) {
			return "借款成功";
		} else if ("5".equals(type)) {
			return "投标成功";
		} else if ("6".equals(type)) {
			return "还款";
		} else if ("7".equals(type)) {
			return "收款";
		} else if ("8".equals(type)) {
			return "VIP付费";
		} else if ("9".equals(type)) {
			return "利息管理费";
		} else if ("10".equals(type)) {
			return "支付投标奖励";
		} else if ("11".equals(type)) {
			return "投标奖励";
		} else if ("12".equals(type)) {
			return "收取担保费用";
		} else if ("13".equals(type)) {
			return "支付担保费用";
		} else if ("14".equals(type)) {
			return "推荐借款费用";
		} else if ("15".equals(type)) {
			return "VIP推荐奖金";
		} else if ("16".equals(type)) {
			return "借款管理费";
		} else if ("17".equals(type)) {
			return "提现手续费";
		} else if ("18".equals(type)) {
			return "充值提现费";
		} else if ("19".equals(type)) {
			return "线下充值返利";
		} else if ("20".equals(type)) {
			return "赠送体验金";
		} else if ("21".equals(type)) {
			return "系统回收体验金";
		} else if ("22".equals(type)) {
			return "收取本金";
		} else if ("23".equals(type)) {
			return "收取利息";
		} else if ("24".equals(type)) {
			return "收取罚息";
		} else if ("25".equals(type)) {
			return "归还本金";
		} else if ("26".equals(type)) {
			return "归还利息";
		} else if ("27".equals(type)) {
			return "罚息";
		} else if ("28".equals(type)) {
			return "收取垫付本金";
		} else if ("29".equals(type)) {
			return "收取垫付利息";
		} else if ("30".equals(type)) {
			return "垫付本金";
		} else if ("31".equals(type)) {
			return "垫付利息";
		} else if ("32".equals(type)) {
			return "续投奖励";
		} else if ("33".equals(type)) {
			return "债权转让";
		} else if ("34".equals(type)) {
			return "债权转让服务费";
		} else if ("35".equals(type)) {
			return "购买债权";
		}
		return "";
	}

}