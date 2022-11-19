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
 * 2015年9月24日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.model.business;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.util.StringUtils;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br>
 * 借款信息VO
 * 
 * @author Yu.Zhang
 * @date 2015年9月24日
 * @version v1.0.0
 */
public class BizBorrowInfoVO extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 借款ID
	 */
	private String pid;

	/**
	 * 借款历史ID
	 */
	private String borrowHisId;

	/**
	 * 借款编号
	 */
	private String borrowCode;

	/**
	 * 客户ID
	 */
	private String customerId;

	/**
	 * 客户账号
	 */
	private String customerName;

	/**
	 * 客户姓名
	 */
	private String sname;

	/**
	 * 客户手机号码
	 */
	private String phoneNo;

	/**
	 * 客户状态
	 */
	private String customerStatus;

	/**
	 * 抵押楼盘信息ID
	 */
	private String homeId;

	/**
	 * 房产描述
	 */
	private String homeDesc;

	/**
	 * 房产总价
	 */
	private BigDecimal homeTotal;

	private String homeTotalStr;

	/**
	 * 借款金额
	 */
	private BigDecimal borrowMoney;

	private String borrowMoneyStr;

	/**
	 * 借款时间
	 */
	private String borrowTime;

	/**
	 * 借款用途
	 */
	private String borrowUse;

	/**
	 * 还款来源
	 */
	private String payment;

	/**
	 * 年利率
	 */
	private BigDecimal borrowApr;

	/**
	 * 还款方式
	 */
	private String repaymentType;

	/**
	 * 计息类型
	 */
	private String accrualType;

	/**
	 * 其他
	 */
	private String other;

	/**
	 * 借款类型
	 */
	private String borrowType;

	/**
	 * 起投金额
	 */
	private BigDecimal startMoney;

	/**
	 * 起投金额千位符格式化
	 */
	private String startMoneyStr;

	/**
	 * 投资上限
	 */
	private BigDecimal endMoney;

	/**
	 * 投资上限千位符格式化
	 */
	private String endMoneyStr;

	/**
	 * 是否倍数投资
	 */
	private String isTimesInvest;

	/**
	 * 是否允许使用加息券
	 */
	private String isJiaxiTicket;

	/**
	 * 投资奖励比例
	 */
	private BigDecimal investRewardScale;

	/**
	 * 一个标投资总金额
	 */
	private BigDecimal investmentAmountTotal;
	/**
	 * 是否允许债券转让
	 */
	private String isEquitableAssignment;

	/**
	 * 债券转让利率开始值
	 */
	private BigDecimal startValue;

	/**
	 * 债券转让利率结束值
	 */
	private BigDecimal endValue;

	/**
	 * 已投金额
	 */
	private BigDecimal alreadyMoney;

	/**
	 * 完成进度
	 */
	private BigDecimal borrowProgress;

	/**
	 * 担保机构ID
	 */
	private String guaOrgId;

	/**
	 * 操作时间
	 */
	private String operTime;

	/**
	 * 是否分标
	 */
	private String isSplit;

	/**
	 * 招标期限
	 */
	private String deadline;

	/**
	 * 未投金额
	 */
	private BigDecimal surplusMoney;

	/**
	 * 招标密码
	 */
	private String borrowPassword;

	/**
	 * 借款合同ID
	 */
	private String borAgrId;

	/**
	 * 借款数据状态
	 */
	private String borrowStatus;

	/**
	 * 备注
	 */
	private String borrowDesc;

	/**
	 * 借款期限
	 */
	private String borDeadline;

	/**
	 * 申请时间
	 */
	private String applyTime;

	/**
	 * 申请时间查询条件
	 */
	private String startApproveTime;

	/**
	 * 申请时间查询条件
	 */
	private String endApproveTime;

	/**
	 * 审批表ID
	 */
	private String approveId;

	/**
	 * 0 提交审核 1 担保初审 2 借前审核
	 */
	private String approveNode;

	/**
	 * 审批状态 1 申请中 2 担保初审 3 担保拒绝 4 借前审核 5 借前拒绝 6 借前同意
	 */
	private String approveStatus;

	/**
	 * 审批人
	 */
	private String approveUser;

	/**
	 * 审批时间
	 */
	private String approveTime;

	/**
	 * 担保审批意见
	 */
	private String vouchOpinion;

	/**
	 * 借前审批意见
	 */
	private String lendOpinion;

	/**
	 * 审批备注
	 */
	private String borCondDesc;

	private String createTime;

	private String createUser;

	private String lastUpdateUser;

	private String lastUpdateTime;

	/**
	 * 借款审核状态数据字典名称
	 */
	private String approveStatusName;

	/**
	 * 借款类型名称
	 */
	private String borrowTypeName;

	/**
	 * 计息方式名称
	 */
	private String accrualTypeName;

	/**
	 * 还款方式名称
	 */
	private String repaymentTypeName;

	/**
	 * 审批人名字
	 */
	private String approveUserName;

	/**
	 * 借款名称
	 */
	private String borrowName;

	/**
	 * 产品标签
	 */
	private String borrowTag;

	/**
	 * 借款利率
	 */
	private BigDecimal borrowRate;

	/**
	 * 抵押物信息
	 */
	private String hostageInfo;

	/**
	 * 抵押物价值
	 */
	private String hostageValuable;

	/**
	 * 房产信息
	 */
	private String houseInfo;

	/**
	 * 担保承诺
	 */
	private String guaAcc;

	/**
	 * 风控综述
	 */
	private String riskDesc;

	/**
	 * 是否是审批列表请求数据
	 */
	private String isApproveList;

	private String startBorrowRate;

	private String endBorrowRate;

	private String startBorrowMoney;

	private String endBorrowMoney;

	private String startBorDeadline;

	private String endBorDeadline;

	/**
	 * 是否是总计数据
	 */
	private String isTotal;

	/**
	 * 查询最小金额
	 */
	private String borrowMoneyMin;

	/**
	 * 查询最大金额
	 */
	private String borrowMoneyMax;

	/**
	 * 查询最小金额
	 */
	private String borrowRateMin;

	/**
	 * 查询最大利率
	 */
	private String borrowRateMax;

	/**
	 * 查询最小利率
	 */
	private String borDeadlineMin;

	/**
	 * 查询最大期限
	 */
	private String borDeadlineMax;

	/**
	 * 发标时间
	 */
	private String publishTime;

	/**
	 * 开始发标时间
	 */
	private String startPublishTime;

	/**
	 * 结束发标时间
	 */
	private String endPublishTime;

	/**
	 * 分标个数
	 */
	private String splitBorCount;

	/**
	 * 分标序号
	 */
	private String borOrder;

	/**
	 * 父级Id
	 */
	private String parentId;

	/**
	 * 投标总数
	 */
	private String tenderCount;

	/**
	 * 借款状态
	 */
	private String borStatus;

	/**
	 * 撤销原因
	 */
	private String restReason;

	/**
	 * 借款状态名称
	 */
	private String borStatusName;

	/**
	 * 借款是否为发布或发布管理
	 */
	private String borrowReleaseStatu;

	/**
	 * 状态
	 */
	private String status;
	/**
	 * 理财产品关联的ids
	 */
	private String condIds;

	/**
	 * 新手标体验标状态
	 */
	private String newStandardStatus;

	/**
	 * 新手标体验标类型
	 */
	private String newStandardType;
	/**
	 * 新手标体验标类型全部
	 */
	private String[] newStandardTypeArray;

	/**
	 * 管理费率
	 */
	private BigDecimal manageExpenseRate;

	/**
	 * 总申请数
	 */
	private Integer approveCount;

	/**
	 * 申请通过数
	 */
	private Integer approveViaCount;

	/**
	 * 申请通过率
	 */
	private BigDecimal approveViaRate;

	/**
	 * 目前所在省份
	 */
	private String province;

	/**
	 * 目前所在市区
	 */
	private String city;

	private String version;
	
	/**
	 * 满标时间
	 */
	private Date borFullTime;
	
	public Date getBorFullTime() {
		return borFullTime;
	}

	public void setBorFullTime(Date borFullTime) {
		this.borFullTime = borFullTime;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public String getHomeTotalStr() {
		return homeTotalStr;
	}

	public void setHomeTotalStr(String homeTotalStr) {
		this.homeTotalStr = homeTotalStr;
	}

	public String getBorrowMoneyStr() {
		return borrowMoneyStr;
	}

	public void setBorrowMoneyStr(String borrowMoneyStr) {
		this.borrowMoneyStr = borrowMoneyStr;
	}

	public String getProvince() {
		return province;
	}

	public void setProvince(String province) {
		this.province = province;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public BigDecimal getApproveViaRate() {
		return approveViaRate;
	}

	public void setApproveViaRate(BigDecimal approveViaRate) {
		this.approveViaRate = approveViaRate;
	}

	public Integer getApproveCount() {
		return approveCount;
	}

	public void setApproveCount(Integer approveCount) {
		this.approveCount = approveCount;
	}

	public Integer getApproveViaCount() {
		return approveViaCount;
	}

	public void setApproveViaCount(Integer approveViaCount) {
		this.approveViaCount = approveViaCount;
	}

	public BigDecimal getInvestmentAmountTotal() {
		return investmentAmountTotal;
	}

	public void setInvestmentAmountTotal(BigDecimal investmentAmountTotal) {
		this.investmentAmountTotal = investmentAmountTotal;
	}

	public BigDecimal getManageExpenseRate() {
		return manageExpenseRate;
	}

	public void setManageExpenseRate(BigDecimal manageExpenseRate) {
		this.manageExpenseRate = manageExpenseRate;
	}

	public String getCondIds() {
		return condIds;
	}

	public void setCondIds(String condIds) {
		this.condIds = condIds;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getBorrowReleaseStatu() {
		return borrowReleaseStatu;
	}

	public void setBorrowReleaseStatu(String borrowReleaseStatu) {
		this.borrowReleaseStatu = borrowReleaseStatu;
	}

	public String getStartPublishTime() {
		return startPublishTime;
	}

	public void setStartPublishTime(String startPublishTime) {
		this.startPublishTime = startPublishTime;
	}

	public String getEndPublishTime() {
		return endPublishTime;
	}

	public void setEndPublishTime(String endPublishTime) {
		this.endPublishTime = endPublishTime;
	}

	public String getBorStatusName() {
		return borStatusName;
	}

	public void setBorStatusName(String borStatusName) {
		this.borStatusName = borStatusName;
	}

	public String getRestReason() {
		return restReason;
	}

	public void setRestReason(String restReason) {
		this.restReason = restReason;
	}

	public String getStartBorDeadline() {
		return startBorDeadline;
	}

	public void setStartBorDeadline(String startBorDeadline) {
		this.startBorDeadline = startBorDeadline;
	}

	public String getEndBorDeadline() {
		return endBorDeadline;
	}

	public void setEndBorDeadline(String endBorDeadline) {
		this.endBorDeadline = endBorDeadline;
	}

	public String getStartBorrowRate() {
		return startBorrowRate;
	}

	public void setStartBorrowRate(String startBorrowRate) {
		this.startBorrowRate = startBorrowRate;
	}

	public String getEndBorrowRate() {
		return endBorrowRate;
	}

	public void setEndBorrowRate(String endBorrowRate) {
		this.endBorrowRate = endBorrowRate;
	}

	public String getStartBorrowMoney() {
		return startBorrowMoney;
	}

	public void setStartBorrowMoney(String startBorrowMoney) {
		this.startBorrowMoney = startBorrowMoney;
	}

	public String getEndBorrowMoney() {
		return endBorrowMoney;
	}

	public void setEndBorrowMoney(String endBorrowMoney) {
		this.endBorrowMoney = endBorrowMoney;
	}

	public String getIsApproveList() {
		return isApproveList;
	}

	public void setIsApproveList(String isApproveList) {
		this.isApproveList = isApproveList;
	}

	public String getHouseInfo() {
		return houseInfo;
	}

	public void setHouseInfo(String houseInfo) {
		this.houseInfo = houseInfo;
	}

	public String getGuaAcc() {
		return guaAcc;
	}

	public void setGuaAcc(String guaAcc) {
		this.guaAcc = guaAcc;
	}

	public String getRiskDesc() {
		return riskDesc;
	}

	public void setRiskDesc(String riskDesc) {
		this.riskDesc = riskDesc;
	}

	public String getBorrowName() {
		return borrowName;
	}

	public void setBorrowName(String borrowName) {
		this.borrowName = borrowName;
	}

	public BigDecimal getBorrowRate() {
		return borrowRate;
	}

	public void setBorrowRate(BigDecimal borrowRate) {
		this.borrowRate = borrowRate;
	}

	public String getHostageInfo() {
		return hostageInfo;
	}

	public void setHostageInfo(String hostageInfo) {
		this.hostageInfo = hostageInfo;
	}

	public String getHostageValuable() {
		return hostageValuable;
	}

	public void setHostageValuable(String hostageValuable) {
		this.hostageValuable = hostageValuable;
	}

	public String getApproveUserName() {
		return approveUserName;
	}

	public void setApproveUserName(String approveUserName) {
		this.approveUserName = approveUserName;
	}

	public String getBorrowTypeName() {
		return borrowTypeName;
	}

	public void setBorrowTypeName(String borrowTypeName) {
		this.borrowTypeName = borrowTypeName;
	}

	public String getAccrualTypeName() {
		return accrualTypeName;
	}

	public void setAccrualTypeName(String accrualTypeName) {
		this.accrualTypeName = accrualTypeName;
	}

	public String getRepaymentTypeName() {
		return repaymentTypeName;
	}

	public void setRepaymentTypeName(String repaymentTypeName) {
		this.repaymentTypeName = repaymentTypeName;
	}

	public String getApproveStatusName() {
		return approveStatusName;
	}

	public void setApproveStatusName(String approveStatusName) {
		this.approveStatusName = approveStatusName;
	}

	public String getBorDeadline() {
		return borDeadline;
	}

	public void setBorDeadline(String borDeadline) {
		this.borDeadline = borDeadline;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getLastUpdateUser() {
		return lastUpdateUser;
	}

	public void setLastUpdateUser(String lastUpdateUser) {
		this.lastUpdateUser = lastUpdateUser;
	}

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getBorCondDesc() {
		return borCondDesc;
	}

	public void setBorCondDesc(String borCondDesc) {
		this.borCondDesc = borCondDesc;
	}

	public String getApproveId() {
		return approveId;
	}

	public void setApproveId(String approveId) {
		this.approveId = approveId;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public String getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(String approveTime) {
		this.approveTime = approveTime;
	}

	public String getApproveStatus() {
		return approveStatus;
	}

	public void setApproveStatus(String approveStatus) {
		this.approveStatus = approveStatus;
	}

	public String getApproveUser() {
		return approveUser;
	}

	public void setApproveUser(String approveUser) {
		this.approveUser = approveUser;
	}

	public String getVouchOpinion() {
		return vouchOpinion;
	}

	public void setVouchOpinion(String vouchOpinion) {
		this.vouchOpinion = vouchOpinion;
	}

	public String getLendOpinion() {
		return lendOpinion;
	}

	public void setLendOpinion(String lendOpinion) {
		this.lendOpinion = lendOpinion;
	}

	public String getCustomerStatus() {
		return customerStatus;
	}

	public void setCustomerStatus(String customerStatus) {
		this.customerStatus = customerStatus;
	}

	public String getStartApproveTime() {
		return startApproveTime;
	}

	public void setStartApproveTime(String startApproveTime) {
		this.startApproveTime = startApproveTime;
	}

	public String getEndApproveTime() {
		return endApproveTime;
	}

	public void setEndApproveTime(String endApproveTime) {
		this.endApproveTime = endApproveTime;
	}

	public String getBorrowHisId() {
		return borrowHisId;
	}

	public void setBorrowHisId(String borrowHisId) {
		this.borrowHisId = borrowHisId;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getBorrowCode() {
		return borrowCode;
	}

	public void setBorrowCode(String borrowCode) {
		this.borrowCode = borrowCode;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
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

	public String getHomeId() {
		return homeId;
	}

	public void setHomeId(String homeId) {
		this.homeId = homeId;
	}

	public String getHomeDesc() {
		return homeDesc;
	}

	public void setHomeDesc(String homeDesc) {
		this.homeDesc = homeDesc;
	}

	public BigDecimal getHomeTotal() {
		return homeTotal;
	}

	public void setHomeTotal(BigDecimal homeTotal) {
		this.homeTotal = homeTotal;
	}

	public BigDecimal getBorrowMoney() {
		return borrowMoney;
	}

	public void setBorrowMoney(BigDecimal borrowMoney) {
		this.borrowMoney = borrowMoney;
	}

	public String getBorrowTime() {
		return borrowTime;
	}

	public void setBorrowTime(String borrowTime) {
		this.borrowTime = borrowTime;
	}

	public String getBorrowUse() {
		return borrowUse;
	}

	public void setBorrowUse(String borrowUse) {
		this.borrowUse = borrowUse;
	}

	public String getPayment() {
		return payment;
	}

	public void setPayment(String payment) {
		this.payment = payment;
	}

	public BigDecimal getBorrowApr() {
		return borrowApr;
	}

	public void setBorrowApr(BigDecimal borrowApr) {
		this.borrowApr = borrowApr;
	}

	public String getRepaymentType() {
		return repaymentType;
	}

	public void setRepaymentType(String repaymentType) {
		this.repaymentType = repaymentType;
	}

	public String getAccrualType() {
		return accrualType;
	}

	public void setAccrualType(String accrualType) {
		this.accrualType = accrualType;
	}

	public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}

	public String getBorrowType() {
		return borrowType;
	}

	public void setBorrowType(String borrowType) {
		this.borrowType = borrowType;
	}

	public BigDecimal getStartMoney() {
		return startMoney;
	}

	public void setStartMoney(BigDecimal startMoney) {
		this.startMoney = startMoney;
	}

	public BigDecimal getEndMoney() {
		return endMoney;
	}

	public void setEndMoney(BigDecimal endMoney) {
		this.endMoney = endMoney;
	}

	public String getIsTimesInvest() {
		return isTimesInvest;
	}

	public void setIsTimesInvest(String isTimesInvest) {
		this.isTimesInvest = isTimesInvest;
	}

	public String getIsJiaxiTicket() {
		return isJiaxiTicket;
	}

	public void setIsJiaxiTicket(String isJiaxiTicket) {
		this.isJiaxiTicket = isJiaxiTicket;
	}

	public BigDecimal getInvestRewardScale() {
		return investRewardScale;
	}

	public void setInvestRewardScale(BigDecimal investRewardScale) {
		this.investRewardScale = investRewardScale;
	}

	public String getIsEquitableAssignment() {
		return isEquitableAssignment;
	}

	public void setIsEquitableAssignment(String isEquitableAssignment) {
		this.isEquitableAssignment = isEquitableAssignment;
	}

	public BigDecimal getStartValue() {
		return startValue;
	}

	public void setStartValue(BigDecimal startValue) {
		this.startValue = startValue;
	}

	public BigDecimal getEndValue() {
		return endValue;
	}

	public void setEndValue(BigDecimal endValue) {
		this.endValue = endValue;
	}

	public BigDecimal getAlreadyMoney() {
		return alreadyMoney;
	}

	public void setAlreadyMoney(BigDecimal alreadyMoney) {
		this.alreadyMoney = alreadyMoney;
	}

	public BigDecimal getBorrowProgress() {
		return borrowProgress;
	}

	public void setBorrowProgress(BigDecimal borrowProgress) {
		this.borrowProgress = borrowProgress;
	}

	public String getGuaOrgId() {
		return guaOrgId;
	}

	public void setGuaOrgId(String guaOrgId) {
		this.guaOrgId = guaOrgId;
	}

	public String getOperTime() {
		return operTime;
	}

	public void setOperTime(String operTime) {
		this.operTime = operTime;
	}

	public String getIsSplit() {
		return isSplit;
	}

	public void setIsSplit(String isSplit) {
		this.isSplit = isSplit;
	}

	public String getDeadline() {
		return deadline;
	}

	public void setDeadline(String deadline) {
		this.deadline = deadline;
	}

	public BigDecimal getSurplusMoney() {
		return surplusMoney;
	}

	public void setSurplusMoney(BigDecimal surplusMoney) {
		this.surplusMoney = surplusMoney;
	}

	public String getBorrowPassword() {
		return borrowPassword;
	}

	public void setBorrowPassword(String borrowPassword) {
		this.borrowPassword = borrowPassword;
	}

	public String getBorAgrId() {
		return borAgrId;
	}

	public void setBorAgrId(String borAgrId) {
		this.borAgrId = borAgrId;
	}

	public String getBorrowStatus() {
		return borrowStatus;
	}

	public void setBorrowStatus(String borrowStatus) {
		this.borrowStatus = borrowStatus;
	}

	public String getBorrowDesc() {
		return borrowDesc;
	}

	public void setBorrowDesc(String borrowDesc) {
		this.borrowDesc = borrowDesc;
	}

	public String getBorrowMoneyMin() {
		return borrowMoneyMin;
	}

	public void setBorrowMoneyMin(String borrowMoneyMin) {
		this.borrowMoneyMin = borrowMoneyMin;
	}

	public String getBorrowMoneyMax() {
		return borrowMoneyMax;
	}

	public void setBorrowMoneyMax(String borrowMoneyMax) {
		this.borrowMoneyMax = borrowMoneyMax;
	}

	public String getBorrowRateMin() {
		return borrowRateMin;
	}

	public void setBorrowRateMin(String borrowRateMin) {
		if (StringUtils.hasText(borrowRateMin)) {
			borrowRateMin = new BigDecimal(borrowRateMin).divide(
					new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)
					.toString();
		}
		this.borrowRateMin = borrowRateMin;
	}

	public String getBorrowRateMax() {
		return borrowRateMax;
	}

	public void setBorrowRateMax(String borrowRateMax) {
		if (StringUtils.hasText(borrowRateMax)) {
			borrowRateMax = new BigDecimal(borrowRateMax).divide(
					new BigDecimal(100), 2, BigDecimal.ROUND_HALF_UP)
					.toString();
		}
		this.borrowRateMax = borrowRateMax;
	}

	public String getBorDeadlineMin() {
		return borDeadlineMin;
	}

	public void setBorDeadlineMin(String borDeadlineMin) {
		this.borDeadlineMin = borDeadlineMin;
	}

	public String getBorDeadlineMax() {
		return borDeadlineMax;
	}

	public void setBorDeadlineMax(String borDeadlineMax) {
		this.borDeadlineMax = borDeadlineMax;
	}

	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public String getSplitBorCount() {
		return splitBorCount;
	}

	public void setSplitBorCount(String splitBorCount) {
		this.splitBorCount = splitBorCount;
	}

	public String getBorOrder() {
		return borOrder;
	}

	public void setBorOrder(String borOrder) {
		this.borOrder = borOrder;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getTenderCount() {
		return tenderCount;
	}

	public void setTenderCount(String tenderCount) {
		this.tenderCount = tenderCount;
	}

	public String getBorStatus() {
		return borStatus;
	}

	public void setBorStatus(String borStatus) {
		this.borStatus = borStatus;
	}

	public String getApproveNode() {
		return approveNode;
	}

	public void setApproveNode(String approveNode) {
		this.approveNode = approveNode;
	}

	public String getBorrowTag() {
		return borrowTag;
	}

	public void setBorrowTag(String borrowTag) {
		this.borrowTag = borrowTag;
	}

	public String getNewStandardStatus() {
		return newStandardStatus;
	}

	public void setNewStandardStatus(String newStandardStatus) {
		this.newStandardStatus = newStandardStatus;
	}

	public String getNewStandardType() {
		return newStandardType;
	}

	public void setNewStandardType(String newStandardType) {
		this.newStandardType = newStandardType;
	}

	public String[] getNewStandardTypeArray() {
		return newStandardTypeArray;
	}

	public void setNewStandardTypeArray(String[] newStandardTypeArray) {
		this.newStandardTypeArray = newStandardTypeArray;
	}

	public String getIsTotal() {
		return isTotal;
	}

	public void setIsTotal(String isTotal) {
		this.isTotal = isTotal;
	}

	public String getStartMoneyStr() {
		return startMoneyStr;
	}

	public void setStartMoneyStr(String startMoneyStr) {
		this.startMoneyStr = startMoneyStr;
	}

	public String getEndMoneyStr() {
		return endMoneyStr;
	}

	public void setEndMoneyStr(String endMoneyStr) {
		this.endMoneyStr = endMoneyStr;
	}

}
