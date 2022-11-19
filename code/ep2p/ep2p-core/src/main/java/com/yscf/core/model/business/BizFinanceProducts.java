package com.yscf.core.model.business;

import java.math.BigDecimal;

import com.achievo.framework.dto.BaseDto;
import com.achievo.framework.entity.BaseEntity;

/**
 * 
 * Description：<br>
 * 理财产品管理 实体
 * 
 * @author fengshiliang
 * @date 2015年10月19日
 * @version v1.0.0
 */
public class BizFinanceProducts extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3096221948340774767L;

	private String pid;

	private String borrowId;

	private String borrowAgreementId;

	private String financeName;

	private Integer deadline;

	private BigDecimal financeArp;

	private String joinCondition;

	private String interestTime;

	private String earningMode;

	private String expirationDate;

	private String guaranteeMode;

	private String ruleIntroduction;

	private String projectDescription;

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

	public String getBorrowId() {
		return borrowId;
	}

	public void setBorrowId(String borrowId) {
		this.borrowId = borrowId == null ? null : borrowId.trim();
	}

	public String getBorrowAgreementId() {
		return borrowAgreementId;
	}

	public void setBorrowAgreementId(String borrowAgreementId) {
		this.borrowAgreementId = borrowAgreementId == null ? null
				: borrowAgreementId.trim();
	}

	public String getFinanceName() {
		return financeName;
	}

	public void setFinanceName(String financeName) {
		this.financeName = financeName == null ? null : financeName.trim();
	}

	public Integer getDeadline() {
		return deadline;
	}

	public void setDeadline(Integer deadline) {
		this.deadline = deadline;
	}

	public BigDecimal getFinanceArp() {
		return financeArp;
	}

	public void setFinanceArp(BigDecimal financeArp) {
		this.financeArp = financeArp;
	}

	public String getJoinCondition() {
		return joinCondition;
	}

	public void setJoinCondition(String joinCondition) {
		this.joinCondition = joinCondition == null ? null : joinCondition
				.trim();
	}

	public String getInterestTime() {
		return interestTime;
	}

	public void setInterestTime(String interestTime) {
		this.interestTime = interestTime;
	}

	public String getEarningMode() {
		return earningMode;
	}

	public void setEarningMode(String earningMode) {
		this.earningMode = earningMode == null ? null : earningMode.trim();
	}

	public String getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(String expirationDate) {
		this.expirationDate = expirationDate;
	}

	public String getGuaranteeMode() {
		return guaranteeMode;
	}

	public void setGuaranteeMode(String guaranteeMode) {
		this.guaranteeMode = guaranteeMode == null ? null : guaranteeMode
				.trim();
	}

	public String getRuleIntroduction() {
		return ruleIntroduction;
	}

	public void setRuleIntroduction(String ruleIntroduction) {
		this.ruleIntroduction = ruleIntroduction == null ? null
				: ruleIntroduction.trim();
	}

	public String getProjectDescription() {
		return projectDescription;
	}

	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription == null ? null
				: projectDescription.trim();
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
		BizFinanceProducts other = (BizFinanceProducts) that;
		return (this.getPid() == null ? other.getPid() == null : this.getPid()
				.equals(other.getPid()))
				&& (this.getBorrowId() == null ? other.getBorrowId() == null
						: this.getBorrowId().equals(other.getBorrowId()))
				&& (this.getBorrowAgreementId() == null ? other
						.getBorrowAgreementId() == null : this
						.getBorrowAgreementId().equals(
								other.getBorrowAgreementId()))
				&& (this.getFinanceName() == null ? other.getFinanceName() == null
						: this.getFinanceName().equals(other.getFinanceName()))
				&& (this.getDeadline() == null ? other.getDeadline() == null
						: this.getDeadline().equals(other.getDeadline()))
				&& (this.getFinanceArp() == null ? other.getFinanceArp() == null
						: this.getFinanceArp().equals(other.getFinanceArp()))
				&& (this.getJoinCondition() == null ? other.getJoinCondition() == null
						: this.getJoinCondition().equals(
								other.getJoinCondition()))
				&& (this.getInterestTime() == null ? other.getInterestTime() == null
						: this.getInterestTime()
								.equals(other.getInterestTime()))
				&& (this.getEarningMode() == null ? other.getEarningMode() == null
						: this.getEarningMode().equals(other.getEarningMode()))
				&& (this.getExpirationDate() == null ? other
						.getExpirationDate() == null : this.getExpirationDate()
						.equals(other.getExpirationDate()))
				&& (this.getGuaranteeMode() == null ? other.getGuaranteeMode() == null
						: this.getGuaranteeMode().equals(
								other.getGuaranteeMode()))
				&& (this.getRuleIntroduction() == null ? other
						.getRuleIntroduction() == null : this
						.getRuleIntroduction().equals(
								other.getRuleIntroduction()))
				&& (this.getProjectDescription() == null ? other
						.getProjectDescription() == null : this
						.getProjectDescription().equals(
								other.getProjectDescription()))
				&& (this.getCreateUser() == null ? other.getCreateUser() == null
						: this.getCreateUser().equals(other.getCreateUser()))
				&& (this.getCreateTime() == null ? other.getCreateTime() == null
						: this.getCreateTime().equals(other.getCreateTime()))
				&& (this.getLastUpdateUser() == null ? other
						.getLastUpdateUser() == null : this.getLastUpdateUser()
						.equals(other.getLastUpdateUser()))
				&& (this.getLastUpdateTime() == null ? other
						.getLastUpdateTime() == null : this.getLastUpdateTime()
						.equals(other.getLastUpdateTime()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getPid() == null) ? 0 : getPid().hashCode());
		result = prime * result
				+ ((getBorrowId() == null) ? 0 : getBorrowId().hashCode());
		result = prime
				* result
				+ ((getBorrowAgreementId() == null) ? 0
						: getBorrowAgreementId().hashCode());
		result = prime
				* result
				+ ((getFinanceName() == null) ? 0 : getFinanceName().hashCode());
		result = prime * result
				+ ((getDeadline() == null) ? 0 : getDeadline().hashCode());
		result = prime * result
				+ ((getFinanceArp() == null) ? 0 : getFinanceArp().hashCode());
		result = prime
				* result
				+ ((getJoinCondition() == null) ? 0 : getJoinCondition()
						.hashCode());
		result = prime
				* result
				+ ((getInterestTime() == null) ? 0 : getInterestTime()
						.hashCode());
		result = prime
				* result
				+ ((getEarningMode() == null) ? 0 : getEarningMode().hashCode());
		result = prime
				* result
				+ ((getExpirationDate() == null) ? 0 : getExpirationDate()
						.hashCode());
		result = prime
				* result
				+ ((getGuaranteeMode() == null) ? 0 : getGuaranteeMode()
						.hashCode());
		result = prime
				* result
				+ ((getRuleIntroduction() == null) ? 0 : getRuleIntroduction()
						.hashCode());
		result = prime
				* result
				+ ((getProjectDescription() == null) ? 0
						: getProjectDescription().hashCode());
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
		return result;
	}

	@Override
	public String toString() {
		return BaseDto.object2Json(this);
	}

}