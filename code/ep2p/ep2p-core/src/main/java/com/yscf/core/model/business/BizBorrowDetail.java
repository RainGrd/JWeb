package com.yscf.core.model.business;

import java.math.BigDecimal;

import org.springframework.util.StringUtils;

import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.activity.PubInvestAward;
import com.yscf.core.model.activity.PubVipinfo;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.util.RepalceUtil;

/**
 * Description：投资详情Bean 对应表：t_biz_borrow_detail
 * 
 * @author JingYu.Dai
 * @date 2015年10月13日
 * @version v1.0.0
 */
public class BizBorrowDetail extends BaseEntity {

	private static final long serialVersionUID = 4951966148530726852L;

	private String pid;

	/**
	 * 利息 （根据、期数、总金额、年利率、加息卷） 计算得出
	 */
	private BigDecimal interest;

	private int page;

	private int limit;

	/**
	 * 客户对象
	 */
	private CusTomer customer;

	/**
	 * 借款对象
	 */
	private BizBorrow bizBorrow;

	/**
	 * 投资奖励信息表对象
	 */
	private PubInvestAward pubInvestAward;

	/**
	 * VIP信息对象
	 */
	private PubVipinfo pubVipinfo;

	/**
	 * VIP ID 对应t_pub_vipinfo.pid
	 */
	private String vipinfoId;

	/**
	 * 管理费汇率
	 */
	private BigDecimal managerRate;

	/**
	 * 投资奖励金额
	 */
	private BigDecimal awardAmount;

	/**
	 * 借款项目Id
	 */
	private String borrowId;

	/**
	 * 客户Id
	 */
	private String customerId;

	/**
	 * 投资金额
	 */
	private BigDecimal investmentAmount;

	/**
	 * 标的投资总额
	 */
	private BigDecimal investmentAmountTotal;

	/**
	 * 最小投资金额 用于区间查询 不对应表字段
	 */
	private BigDecimal minInvestmentAmount;

	/**
	 * 最大投资金额 用于区间查询 不对应表字段
	 */
	private BigDecimal maxInvestmentAmount;
	/**
	 * 投资奖励ID
	 */
	private String investAwardId;

	/**
	 * 投资奖励ID
	 */
	private String investmentTime;

	/**
	 * 投资开始时间 用于区间查询 不对应表字段
	 */
	private String beginInvestmentTime;

	/**
	 * 投资结束时间 用于区间查询 不对应表字段
	 */
	private String endInvestmentTime;

	/**
	 * 计息时间
	 */
	private String interestTime;

	/**
	 * 计息开始时间 用于区间查询 不对应表字段
	 */
	private String beginInterestTime;

	/**
	 * 计息结束时间 用于区间查询 不对应表字段
	 */
	private String endInterestTime;

	/**
	 * 投标方式
	 */
	private String investmentType;
	/**
	 * 投标方式 对应中文名
	 */
	private String investmentTypeName;

	/**
	 * 体验金卷ID（对应表t_act_exp_act_detail的id）
	 */
	private String experienceTicketId;

	/**
	 * 状态
	 */
	private String status;

	/**
	 * 创建人
	 */
	private String createUser;

	/**
	 * 创建时间
	 */
	private String createTime;

	/**
	 * 最后修改人
	 */
	private String lastUpdateUser;

	/**
	 * 最后修改时间
	 */
	private String lastUpdateTime;

	/**
	 * 备注
	 */
	private String borDetDesc;

	/**
	 * 客户账号
	 */
	private String customerName;

	/**
	 * 客户姓名
	 */
	private String sname;
	/**
	 * 加密后的用户名
	 */
	@SuppressWarnings("unused")
	private String privateName;

	/**
	 * 客户手机号码
	 */
	private String phoneNo;

	/**
	 * 客户状态
	 */
	private String customerStatus;

	/**
	 * 投资收益
	 */
	private BigDecimal investmentPayoffs;

	private String imageUrl;// 头像图片信息
	/**
	 * 当前页
	 */
	private Integer pageIndex;
	/**
	 * 页面大小
	 */
	private Integer pageSize;

	/**
	 * 债权转让id
	 */
	private String transferId;

	/**
	 * 投资记录来源 1原标投资 2债权购买
	 */
	private String investType;
	
	/**
	 * 加息卷利率
	 */
	private BigDecimal scale; 

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getPrivateName() {
		if (!StringUtils.hasLength(this.customerName)) {
			return RepalceUtil.replaceCustomerNameToStar(this.phoneNo);
		}
		return RepalceUtil.replaceCustomerNameToStar(customerName);
	}

	public void setPrivateName(String privateName) {
		this.privateName = privateName;
	}

	public String getInvestmentTypeName() {
		return investmentTypeName;
	}

	public void setInvestmentTypeName(String investmentTypeName) {
		this.investmentTypeName = investmentTypeName;
	}

	public BigDecimal getInvestmentPayoffs() {
		return investmentPayoffs;
	}

	public void setInvestmentPayoffs(BigDecimal investmentPayoffs) {
		this.investmentPayoffs = investmentPayoffs;
	}

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

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId == null ? null : customerId.trim();
	}

	public BigDecimal getInvestmentAmount() {
		return investmentAmount;
	}

	public void setInvestmentAmount(BigDecimal investmentAmount) {
		this.investmentAmount = investmentAmount;
	}

	public String getInvestmentTime() {
		return investmentTime;
	}

	public void setInvestmentTime(String investmentTime) {
		this.investmentTime = investmentTime;
	}

	public String getInterestTime() {
		return interestTime;
	}

	public void setInterestTime(String interestTime) {
		this.interestTime = interestTime;
	}

	public String getInvestmentType() {
		return investmentType;
	}

	public void setInvestmentType(String investmentType) {
		this.investmentType = investmentType == null ? null : investmentType
				.trim();
	}

	public String getVipinfoId() {
		return vipinfoId;
	}

	public void setVipinfoId(String vipinfoId) {
		this.vipinfoId = vipinfoId == null ? null : vipinfoId.trim();
	}

	public BigDecimal getManagerRate() {
		return managerRate;
	}

	public void setManagerRate(BigDecimal managerRate) {
		this.managerRate = managerRate;
	}

	public BigDecimal getAwardAmount() {
		return awardAmount;
	}

	public void setAwardAmount(BigDecimal awardAmount) {
		this.awardAmount = awardAmount;
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

	public BigDecimal getInvestmentAmountTotal() {
		return investmentAmountTotal;
	}

	public void setInvestmentAmountTotal(BigDecimal investmentAmountTotal) {
		this.investmentAmountTotal = investmentAmountTotal;
	}

	public String getBorDetDesc() {
		return borDetDesc;
	}

	public void setBorDetDesc(String borDetDesc) {
		this.borDetDesc = borDetDesc == null ? null : borDetDesc.trim();
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
		BizBorrowDetail other = (BizBorrowDetail) that;
		return (this.getPid() == null ? other.getPid() == null : this.getPid()
				.equals(other.getPid()))
				&& (this.getBorrowId() == null ? other.getBorrowId() == null
						: this.getBorrowId().equals(other.getBorrowId()))
				&& (this.getCustomerId() == null ? other.getCustomerId() == null
						: this.getCustomerId().equals(other.getCustomerId()))
				&& (this.getInvestmentAmount() == null ? other
						.getInvestmentAmount() == null : this
						.getInvestmentAmount().equals(
								other.getInvestmentAmount()))
				&& (this.getInvestAwardId() == null ? other.getInvestAwardId() == null
						: this.getInvestAwardId().equals(
								other.getInvestAwardId()))
				&& (this.getInvestmentTime() == null ? other
						.getInvestmentTime() == null : this.getInvestmentTime()
						.equals(other.getInvestmentTime()))
				&& (this.getInterestTime() == null ? other.getInterestTime() == null
						: this.getInterestTime()
								.equals(other.getInterestTime()))
				&& (this.getInvestmentType() == null ? other
						.getInvestmentType() == null : this.getInvestmentType()
						.equals(other.getInvestmentType()))
				&& (this.getVipinfoId() == null ? other.getVipinfoId() == null
						: this.getVipinfoId().equals(other.getVipinfoId()))
				&& (this.getManagerRate() == null ? other.getManagerRate() == null
						: this.getManagerRate().equals(other.getManagerRate()))
				&& (this.getAwardAmount() == null ? other.getAwardAmount() == null
						: this.getAwardAmount().equals(other.getAwardAmount()))
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
				&& (this.getBorDetDesc() == null ? other.getBorDetDesc() == null
						: this.getBorDetDesc().equals(other.getBorDetDesc()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((getPid() == null) ? 0 : getPid().hashCode());
		result = prime * result
				+ ((getBorrowId() == null) ? 0 : getBorrowId().hashCode());
		result = prime * result
				+ ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
		result = prime
				* result
				+ ((getInvestmentAmount() == null) ? 0 : getInvestmentAmount()
						.hashCode());
		result = prime
				* result
				+ ((getInvestAwardId() == null) ? 0 : getInvestAwardId()
						.hashCode());
		result = prime
				* result
				+ ((getInvestmentTime() == null) ? 0 : getInvestmentTime()
						.hashCode());
		result = prime
				* result
				+ ((getInterestTime() == null) ? 0 : getInterestTime()
						.hashCode());
		result = prime
				* result
				+ ((getInvestmentType() == null) ? 0 : getInvestmentType()
						.hashCode());
		result = prime * result
				+ ((getVipinfoId() == null) ? 0 : getVipinfoId().hashCode());
		result = prime
				* result
				+ ((getManagerRate() == null) ? 0 : getManagerRate().hashCode());
		result = prime
				* result
				+ ((getAwardAmount() == null) ? 0 : getAwardAmount().hashCode());
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
				+ ((getBorDetDesc() == null) ? 0 : getBorDetDesc().hashCode());
		return result;
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

	public String getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}

	public CusTomer getCustomer() {
		return customer;
	}

	public void setCustomer(CusTomer customer) {
		this.customer = customer;
	}

	public BizBorrow getBizBorrow() {
		return bizBorrow;
	}

	public void setBizBorrow(BizBorrow bizBorrow) {
		this.bizBorrow = bizBorrow;
	}

	public PubVipinfo getPubVipinfo() {
		return pubVipinfo;
	}

	public void setPubVipinfo(PubVipinfo pubVipinfo) {
		this.pubVipinfo = pubVipinfo;
	}

	public BigDecimal getInterest() {
		return interest;
	}

	public void setInterest(BigDecimal interest) {
		this.interest = interest;
	}

	public String getBeginInvestmentTime() {
		return beginInvestmentTime;
	}

	public void setBeginInvestmentTime(String beginInvestmentTime) {
		this.beginInvestmentTime = beginInvestmentTime;
	}

	public String getEndInvestmentTime() {
		return endInvestmentTime;
	}

	public void setEndInvestmentTime(String endInvestmentTime) {
		this.endInvestmentTime = endInvestmentTime;
	}

	public String getBeginInterestTime() {
		return beginInterestTime;
	}

	public void setBeginInterestTime(String beginInterestTime) {
		this.beginInterestTime = beginInterestTime;
	}

	public String getEndInterestTime() {
		return endInterestTime;
	}

	public void setEndInterestTime(String endInterestTime) {
		this.endInterestTime = endInterestTime;
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

	public BigDecimal getMinInvestmentAmount() {
		return minInvestmentAmount;
	}

	public void setMinInvestmentAmount(BigDecimal minInvestmentAmount) {
		this.minInvestmentAmount = minInvestmentAmount;
	}

	public BigDecimal getMaxInvestmentAmount() {
		return maxInvestmentAmount;
	}

	public void setMaxInvestmentAmount(BigDecimal maxInvestmentAmount) {
		this.maxInvestmentAmount = maxInvestmentAmount;
	}

	public String getInvestAwardId() {
		return investAwardId;
	}

	public void setInvestAwardId(String investAwardId) {
		this.investAwardId = investAwardId;
	}

	public PubInvestAward getPubInvestAward() {
		return pubInvestAward;
	}

	public void setPubInvestAward(PubInvestAward pubInvestAward) {
		this.pubInvestAward = pubInvestAward;
	}

	public String getExperienceTicketId() {
		return experienceTicketId;
	}

	public void setExperienceTicketId(String experienceTicketId) {
		this.experienceTicketId = experienceTicketId;
	}

	public String getTransferId() {
		return transferId;
	}

	public void setTransferId(String transferId) {
		this.transferId = transferId;
	}

	public String getInvestType() {
		return investType;
	}

	public void setInvestType(String investType) {
		this.investType = investType;
	}

	public BigDecimal getScale() {
		return scale;
	}

	public void setScale(BigDecimal scale) {
		this.scale = scale;
	}
	

}