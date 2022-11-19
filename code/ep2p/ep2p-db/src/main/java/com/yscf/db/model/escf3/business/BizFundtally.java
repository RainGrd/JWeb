package com.yscf.db.model.escf3.business;

import java.math.BigDecimal;

import com.achievo.framework.entity.BaseEntity;
import com.yscf.db.model.escf3.user.CusTomer;

/**
 * Description：<br>
 * 资金流水表(t_biz_fundtally)
 * @author JingYu.Dai
 * @date 2015年10月28日
 * @version v1.0.0
 */
public class BizFundtally extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5718463163026936158L;
	
	private int page;

	private int limit;

	private String pid;

	/**
	 * 客户ID
	 */
	private String customerId;

	/**
	 * 处理时间
	 */
	private String actorTime;

	/**
	 * 开始时间 没有相对应的表字段
	 */
	private String beginActorTime;

	/**
	 * 结束时间 没有相对应的表字段
	 */
	private String endActorTime;

	/**
	 * 客户对象 没有相对应的表字段
	 */
	private CusTomer customer;

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
	 * 28-收取垫付本金，29-收取垫付利息，30-垫付本金，31-垫付利息，32-续投奖励，
	 * 33-债权转让，34-债权转让服务费，35-购买债权）
	 */
	private String detailType;

	/**
	 * 交易类型（1：收入、2：支出）
	 */
	private String dealType;

	/**
	 * 备注
	 */
	private String funDesc;

	private String status;

	private String createUser;

	private String createTime;

	private String lastUpdateUser;

	private String lastUpdateTime;

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid == null ? null : pid.trim();
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId == null ? null : customerId.trim();
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
		this.detailType = detailType == null ? null : detailType.trim();
	}

	public String getDealType() {
		return dealType;
	}

	public void setDealType(String dealType) {
		this.dealType = dealType == null ? null : dealType.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser == null ? null : createUser.trim();
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser == null ? null : lastUpdateUser
				.trim();
	}

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getFunDesc() {
		return funDesc;
	}

	public void setFunDesc(String funDesc) {
		this.funDesc = funDesc == null ? null : funDesc.trim();
	}

	@Override
	public boolean equals(Object that) {
		if (this == that) {
			return true;
		}
		if (that == null) {
			return false;
		}
		if (getClass() != that.getClass()) {
			return false;
		}
		BizFundtally other = (BizFundtally) that;
		return (this.getPid() == null ? other.getPid() == null : this.getPid()
				.equals(other.getPid()))
				&& (this.getCustomerId() == null ? other.getCustomerId() == null
						: this.getCustomerId().equals(other.getCustomerId()))
				&& (this.getAmount() == null ? other.getAmount() == null : this
						.getAmount().equals(other.getAmount()))
				&& (this.getBalance() == null ? other.getBalance() == null
						: this.getBalance().equals(other.getBalance()))
				&& (this.getDetailType() == null ? other.getDetailType() == null
						: this.getDetailType().equals(other.getDetailType()))
				&& (this.getDealType() == null ? other.getDealType() == null
						: this.getDealType().equals(other.getDealType()))
				&& (this.getStatus() == null ? other.getStatus() == null : this
						.getStatus().equals(other.getStatus()))
				&& (this.getCreateUser() == null ? other.getCreateUser() == null
						: this.getCreateUser().equals(other.getCreateUser()))
				&& (this.getCreateTime() == null ? other.getCreateTime() == null
						: this.getCreateTime().equals(other.getCreateTime()))
				&& (this.getLastUpdateUser() == null ? other
						.getLastUpdateUser() == null : this.getLastUpdateUser()
						.equals(other.getLastUpdateUser()))
				&& (this.getLastUpdateTime() == null ? other
						.getLastUpdateTime() == null : this.getLastUpdateTime()
						.equals(other.getLastUpdateTime()))
				&& (this.getFunDesc() == null ? other.getFunDesc() == null
						: this.getFunDesc().equals(other.getFunDesc()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getPid() == null) ? 0 : getPid().hashCode());
		result = prime * result
				+ ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
		result = prime * result
				+ ((getAmount() == null) ? 0 : getAmount().hashCode());
		result = prime * result
				+ ((getBalance() == null) ? 0 : getBalance().hashCode());
		result = prime * result
				+ ((getDetailType() == null) ? 0 : getDetailType().hashCode());
		result = prime * result
				+ ((getDealType() == null) ? 0 : getDealType().hashCode());
		result = prime * result
				+ ((getStatus() == null) ? 0 : getStatus().hashCode());
		result = prime * result
				+ ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
		result = prime * result
				+ ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		result = prime
				* result
				+ ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser()
						.hashCode());
		result = prime
				* result
				+ ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime()
						.hashCode());
		result = prime * result
				+ ((getFunDesc() == null) ? 0 : getFunDesc().hashCode());
		return result;
	}

	public CusTomer getCustomer() {
		return customer;
	}

	public void setCustomer(CusTomer customer) {
		this.customer = customer;
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

	public String getActorTime() {
		return actorTime;
	}

	public void setActorTime(String actorTime) {
		this.actorTime = actorTime;
	}

	public String getBeginActorTime() {
		return beginActorTime;
	}

	public void setBeginActorTime(String beginActorTime) {
		this.beginActorTime = beginActorTime;
	}

	public String getEndActorTime() {
		return endActorTime;
	}

	public void setEndActorTime(String endActorTime) {
		this.endActorTime = endActorTime;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getLimit() {
		return limit;
	}

	public void setLimit(int limit) {
		this.limit = limit;
	}

}