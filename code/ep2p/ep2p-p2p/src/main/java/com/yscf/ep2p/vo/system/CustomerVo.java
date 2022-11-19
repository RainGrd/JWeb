/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 用户信息视图vo
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年11月12日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.ep2p.vo.system;

import java.math.BigDecimal;
import java.util.Date;

import com.achievo.framework.vo.BaseVo;

/**
 * Description：<br> 
 * 用户信息视图vo
 * @author  Lin Xu
 * @date    2015年11月12日
 * @version v1.0.0
 */
public class CustomerVo extends BaseVo{

	/**
	 * 参数序列
	 */
	private static final long serialVersionUID = 7347351329729751550L;
	
	private String pid;
	//用户名
	private String customerName;
	//姓名
	private String sname;
	//生日
	private Date birthday;
	//手机号码
	private String phoneNo;
	//邮箱
	private String email;
	//性别
	private String sex;
	//年纪
	private String age;
	//总共待收
	private BigDecimal totalStay;
	//可用积分
	private Integer availablePoint;
	//经验值
	private Integer empiricalValue;
	//vip时长
	private int vipTime;
	//vip等级
	private String vipLevel;
	//头像图片信息
	private String imageUrl;
	
	
	/**
	 * VIP显示图片
	 */
	private String vipIco;
	
	/**
	 * 可用余额
	 */
	private BigDecimal availableBalance;
	
	/**
	 * 签到天数
	 */
	private Integer signday;
	
	/**
	 * 未读消息
	 */
	private String messRecordCount;
	
	/**
	 * 未使用加息卷张数
	 */
	private String interestTicketCount;
	
	/************************以上客户表数据*****************************/
	
	//是否认证：
	private String isAttestation;
	//投资奖励
	private String investmentIncentives;
	//冻结金额
    private String frozenAmount;
    //收到红包
    private String receivedRedEnvelope;
    //体验金
    private String experienceGold;
    //账户总额
    private String totalAccount;
    //投资总额
    private String investmentAccount;
    //投标奖励
    private String investmentReward;
    //推荐奖励
    private String recommendReward;
    //净赚利息
    private String netInterest;
    //充值总额
    private String rechargeTotal;
    //待收利息
    private String receivedInterest;
    //提现总额
    private String withdrawalsTotal;
    //红包金额
    private BigDecimal paychecksAmount;
    // 发生类型
    private String expGetType;
    // 积分值
	private String investAwardValue;
	// 类型
	private String investAwardType;
	// 总数
	private String totalNum;
	// 积分
	private String point;
	// 加息
	private String experience;
	
	private String increaseInterest;
	//vip级别
	private String vipLevelName ;
	
	//Lin.Xu 2016年3月7日18:51:58 添加页面显示数据
	/**
	 * 未使用体验金张数
	 */
	private String expActCount;
	
	/**
	 * 是否绑定手机
	 */
	private String isBingPhone;
	
	/**
	 * 是否设置交易密码
	 */
	private String isSetTradePwd;
	
	/**
	 * 充值总金额
	 */
	private BigDecimal rechargeAmount;

	/**
	 * 提现总金额
	 */
    private BigDecimal withdrawAmount;

    /**
     * 总待收金额
     */
    private BigDecimal dueAmount;

    /**
     * 冻结金额
     */
    private BigDecimal freezeAmount;

    /**
     * 投标总额
     */
    private BigDecimal tenderAmount;

    /**
     * 体验金
     */
    private BigDecimal experienceAmount;
    
	/**
	 * 银行卡张数
	 */
	private Integer bankCount;
	
	/**
	 * 卡券张数
	 */
	private Integer cardVoucherCount;
	
	/**
	 * 身份证号码
	 */
	private String identificationNo;
	
	/**
	 * 注册时间
	 */
	private String registrationTime;
	
	/**
	 * 是否首次支付 0 是 不等于0 不是
	 */
	private Integer isFirstPay;
	
	/**
	 * VIP当前级别最高积分值
	 */
	private Integer experienceEnd;
	
	/**
	 * VIP当前级别最低积分值
	 */
	private Integer experienceStart;
	
	/**
	 * 是否VIP
	 */
	private String isVip;
	
	/**
	 * 会员VIP利息管理费汇率 
	 */
	private BigDecimal discount;
	
	
	public String getExpActCount() {
		return expActCount;
	}
	public void setExpActCount(String expActCount) {
		this.expActCount = expActCount;
	}
	public String getIsBingPhone() {
		return isBingPhone;
	}
	public void setIsBingPhone(String isBingPhone) {
		this.isBingPhone = isBingPhone;
	}
	public String getIsSetTradePwd() {
		return isSetTradePwd;
	}
	public void setIsSetTradePwd(String isSetTradePwd) {
		this.isSetTradePwd = isSetTradePwd;
	}
	public BigDecimal getRechargeAmount() {
		return rechargeAmount;
	}
	public void setRechargeAmount(BigDecimal rechargeAmount) {
		this.rechargeAmount = rechargeAmount;
	}
	public BigDecimal getWithdrawAmount() {
		return withdrawAmount;
	}
	public void setWithdrawAmount(BigDecimal withdrawAmount) {
		this.withdrawAmount = withdrawAmount;
	}
	public BigDecimal getDueAmount() {
		return dueAmount;
	}
	public void setDueAmount(BigDecimal dueAmount) {
		this.dueAmount = dueAmount;
	}
	public BigDecimal getFreezeAmount() {
		return freezeAmount;
	}
	public void setFreezeAmount(BigDecimal freezeAmount) {
		this.freezeAmount = freezeAmount;
	}
	public BigDecimal getTenderAmount() {
		return tenderAmount;
	}
	public void setTenderAmount(BigDecimal tenderAmount) {
		this.tenderAmount = tenderAmount;
	}
	public BigDecimal getExperienceAmount() {
		return experienceAmount;
	}
	public void setExperienceAmount(BigDecimal experienceAmount) {
		this.experienceAmount = experienceAmount;
	}
	public Integer getBankCount() {
		return bankCount;
	}
	public void setBankCount(Integer bankCount) {
		this.bankCount = bankCount;
	}
	public Integer getCardVoucherCount() {
		return cardVoucherCount;
	}
	public void setCardVoucherCount(Integer cardVoucherCount) {
		this.cardVoucherCount = cardVoucherCount;
	}
	public String getIdentificationNo() {
		return identificationNo;
	}
	public void setIdentificationNo(String identificationNo) {
		this.identificationNo = identificationNo;
	}
	public String getRegistrationTime() {
		return registrationTime;
	}
	public void setRegistrationTime(String registrationTime) {
		this.registrationTime = registrationTime;
	}
	public Integer getIsFirstPay() {
		return isFirstPay;
	}
	public void setIsFirstPay(Integer isFirstPay) {
		this.isFirstPay = isFirstPay;
	}
	public Integer getExperienceEnd() {
		return experienceEnd;
	}
	public void setExperienceEnd(Integer experienceEnd) {
		this.experienceEnd = experienceEnd;
	}
	public Integer getExperienceStart() {
		return experienceStart;
	}
	public void setExperienceStart(Integer experienceStart) {
		this.experienceStart = experienceStart;
	}
	public String getIsVip() {
		return isVip;
	}
	public void setIsVip(String isVip) {
		this.isVip = isVip;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
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
	public Date getBirthday() {
		return birthday;
	}
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}
	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}
	public int getVipTime() {
		return vipTime;
	}
	public void setVipTime(int vipTime) {
		this.vipTime = vipTime;
	}
	public String getVipLevel() {
		return vipLevel;
	}
	public void setVipLevel(String vipLevel) {
		this.vipLevel = vipLevel;
	}
	public String getIsAttestation() {
		return isAttestation;
	}
	public void setIsAttestation(String isAttestation) {
		this.isAttestation = isAttestation;
	}
	public String getInvestmentIncentives() {
		return investmentIncentives;
	}
	public void setInvestmentIncentives(String investmentIncentives) {
		this.investmentIncentives = investmentIncentives;
	}
	public String getFrozenAmount() {
		return frozenAmount;
	}
	public void setFrozenAmount(String frozenAmount) {
		this.frozenAmount = frozenAmount;
	}
	public String getReceivedRedEnvelope() {
		return receivedRedEnvelope;
	}
	public void setReceivedRedEnvelope(String receivedRedEnvelope) {
		this.receivedRedEnvelope = receivedRedEnvelope;
	}
	public String getExperienceGold() {
		return experienceGold;
	}
	public void setExperienceGold(String experienceGold) {
		this.experienceGold = experienceGold;
	}
	public String getTotalAccount() {
		return totalAccount;
	}
	public void setTotalAccount(String totalAccount) {
		this.totalAccount = totalAccount;
	}
	public String getInvestmentAccount() {
		return investmentAccount;
	}
	public void setInvestmentAccount(String investmentAccount) {
		this.investmentAccount = investmentAccount;
	}
	public String getInvestmentReward() {
		return investmentReward;
	}
	public void setInvestmentReward(String investmentReward) {
		this.investmentReward = investmentReward;
	}
	public String getRecommendReward() {
		return recommendReward;
	}
	public void setRecommendReward(String recommendReward) {
		this.recommendReward = recommendReward;
	}
	public String getNetInterest() {
		return netInterest;
	}
	public void setNetInterest(String netInterest) {
		this.netInterest = netInterest;
	}
	public String getRechargeTotal() {
		return rechargeTotal;
	}
	public void setRechargeTotal(String rechargeTotal) {
		this.rechargeTotal = rechargeTotal;
	}
	public String getReceivedInterest() {
		return receivedInterest;
	}
	public void setReceivedInterest(String receivedInterest) {
		this.receivedInterest = receivedInterest;
	}
	public String getWithdrawalsTotal() {
		return withdrawalsTotal;
	}
	public void setWithdrawalsTotal(String withdrawalsTotal) {
		this.withdrawalsTotal = withdrawalsTotal;
	}
	public String getExpGetType() {
		return expGetType;
	}
	public void setExpGetType(String expGetType) {
		this.expGetType = expGetType;
	}
	public String getInvestAwardValue() {
		return investAwardValue;
	}
	public void setInvestAwardValue(String investAwardValue) {
		this.investAwardValue = investAwardValue;
	}
	public String getInvestAwardType() {
		return investAwardType;
	}
	public void setInvestAwardType(String investAwardType) {
		this.investAwardType = investAwardType;
	}
	public String getTotalNum() {
		return totalNum;
	}
	public void setTotalNum(String totalNum) {
		this.totalNum = totalNum;
	}
	public String getPoint() {
		return point;
	}
	public void setPoint(String point) {
		this.point = point;
	}
	public String getExperience() {
		return experience;
	}
	public void setExperience(String experience) {
		this.experience = experience;
	}
	public String getIncreaseInterest() {
		return increaseInterest;
	}
	public void setIncreaseInterest(String increaseInterest) {
		this.increaseInterest = increaseInterest;
	}
	public String getVipLevelName() {
		return vipLevelName;
	}
	public void setVipLevelName(String vipLevelName) {
		this.vipLevelName = vipLevelName;
	}
	public Integer getSignday() {
		return signday;
	}
	public void setSignday(Integer signday) {
		this.signday = signday;
	}
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public String getVipIco() {
		return vipIco;
	}
	public void setVipIco(String vipIco) {
		this.vipIco = vipIco;
	}
	public String getMessRecordCount() {
		return messRecordCount;
	}
	public void setMessRecordCount(String messRecordCount) {
		this.messRecordCount = messRecordCount;
	}
	public String getInterestTicketCount() {
		return interestTicketCount;
	}
	public void setInterestTicketCount(String interestTicketCount) {
		this.interestTicketCount = interestTicketCount;
	}
	public BigDecimal getTotalStay() {
		return totalStay;
	}
	public void setTotalStay(BigDecimal totalStay) {
		this.totalStay = totalStay;
	}
	public Integer getAvailablePoint() {
		return availablePoint;
	}
	public void setAvailablePoint(Integer availablePoint) {
		this.availablePoint = availablePoint;
	}
	public Integer getEmpiricalValue() {
		return empiricalValue;
	}
	public void setEmpiricalValue(Integer empiricalValue) {
		this.empiricalValue = empiricalValue;
	}
	public BigDecimal getPaychecksAmount() {
		return paychecksAmount;
	}
	public void setPaychecksAmount(BigDecimal paychecksAmount) {
		this.paychecksAmount = paychecksAmount;
	}
	public BigDecimal getDiscount() {
		return discount;
	}
	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}
	
}


