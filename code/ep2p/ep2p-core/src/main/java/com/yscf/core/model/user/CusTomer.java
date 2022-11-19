package com.yscf.core.model.user;

import java.math.BigDecimal;
import java.util.Date;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：客户对象
 * @author  JingYu.Dai
 * @date    2015年10月20日
 * @version v1.0.0
 */
public class CusTomer extends BaseEntity {

	private static final long serialVersionUID = -8101380688477874269L;

	private String pid;

	private String customerName;//用户名

	private String sname;//姓名

	private Date birthday;//生日
	
	private Integer index; // 旧数据ID，密码加密因子

	private String password;//登录密码

	private String phoneNo;//手机号码

	private String email;//邮箱

	private String sex;//性别
	private String sexName;//性别名称

	private String age;//年纪

	private String resReg;//户籍

	private String status;//状态

	private String registrationTime;// 注册时间

	private String refereeUser;//推荐人ID

	private String customerServiceUser;//客服ID

	private BigDecimal totalStay;//总共待收

	private BigDecimal availableBalance;//可用余额
	private BigDecimal availableBeginBalance;//可用余额开始
	private BigDecimal availableEndBalance;//可用余额结束

	private Integer availablePoint;//可用积分

	private Integer empiricalValue;//经验值
	
	private Integer maxEmpiricalValueEnd;//最大经验值结束

	private String isBlacklist;//是否黑名单

	private String isForbidWithdraw;//是否禁止体现

	private String isForbidTransfer;//是否债券转让

	private String isFreeze;//是否冻结

	private Integer vipTime;//vip时长
	private Date vipTimeTwo;

	private String isVip;//是否vip

	private String vipLevel;//vip等级

	private String isAttestation;//是否认证：

	private String identificationNo;//身份证号码

	private String isMarriage;//婚姻状况(名称)
	
	private String isMarriageName; //婚姻状况(值)

	private String homeAddress;//家庭住址

	private String nowAddress;//现居住地址

	private String wechatNo;//微信号

	private String immediateFamily;//直系亲属

	private String immediateFamilyNo;//直系亲属联系方式

	private String createUser;//创建人

	private String createTime;//创建时间
    private String custDesc;//备注
    
    private String newCustServiceId;//新客服ID
    
    private String updSerWatDesc;
    
    private String cameSzTime;
    
    private String larVipWatDesc;//赠送vip描述
    
    private String vipName;//vip名称
    
    private String vipId;//vipID
    private String largessValue;//vip时长  
    private String vipInfoPid;//vip信息表id
    
    private String cusStatus;//客户状态
    private Date forceDate;//生效日期
    private Date invalidDate;//失效日期
    private String cusStatusRemark;//客户状态变更说明
    private String investmentIncentives;//投资奖励
    private String frozenAmount;//冻结金额
    private String receivedRedEnvelope;//收到红包
    private String experienceGold;//体验金
    private String totalAccount;//账户总额
    private String investmentAccount;//投资总额
    private String investmentReward;//投标奖励
    private String recommendReward;//推荐奖励
    private String netInterest;//净赚利息
    private String rechargeTotal;//充值总额
    private String receivedInterest;//待收利息
    private String withdrawalsTotal;//提现总额
    private String certType;//证件类型
    private String busLicCertUrl;//证件地址
    private String happenBeginTime;//发生开始时间
    private String happenEndTime;//发生结束时间
    private String pointType;//投资类型
    private BigDecimal getAmount;//红包金额
    private BigDecimal paychecksAmount;//红包金额
    private String useStatus;//红包使用情况
    private String getTime;//获得时间
    private String redDesc;//获得时间
    private String cusServiceName;//客服名称
    private String cusServicePid;//客服pid
    private String oldcusServiceId;//旧客服pid
    
    private String vipPID;//vip等级Pid
    private String vipLevelName ;//vip级别

	private String lastUpdateUser;//最后更新人

	private String lastUpdateTime;//最后更新时间
	
	private Date expirationTime;//vip到期时间
	
	private BigDecimal serviceCharge;//服务费
	
	private String distributionTime;//分配时间
	
	private String mobileDeviceMachineCode;//移动设备的机器代码
	
	private Integer experienceStart;//当前经验的开始值
	
	
	
	public Integer getExperienceStart() {
		return experienceStart;
	}

	public void setExperienceStart(Integer experienceStart) {
		this.experienceStart = experienceStart;
	}

	public String getMobileDeviceMachineCode() {
		return mobileDeviceMachineCode;
	}

	public void setMobileDeviceMachineCode(String mobileDeviceMachineCode) {
		this.mobileDeviceMachineCode = mobileDeviceMachineCode;
	}

	public Integer getMaxEmpiricalValueEnd() {
		return maxEmpiricalValueEnd;
	}

	public void setMaxEmpiricalValueEnd(Integer maxEmpiricalValueEnd) {
		this.maxEmpiricalValueEnd = maxEmpiricalValueEnd;
	}

	public String getDistributionTime() {
		return distributionTime;
	}

	public void setDistributionTime(String distributionTime) {
		this.distributionTime = distributionTime;
	}

	public BigDecimal getServiceCharge() {
		return serviceCharge;
	}

	public void setServiceCharge(BigDecimal serviceCharge) {
		this.serviceCharge = serviceCharge;
	}

	public Date getExpirationTime() {
		return expirationTime;
	}

	public void setExpirationTime(Date expirationTime) {
		this.expirationTime = expirationTime;
	}

	public String getTradePassword() {
		return tradePassword;
	}

	private String disableUser;//禁止用户
	private String total;//总计
	private Integer count; // 近七日新增客户数据统计
	
	private String tradePassword; // 交易密码
	
	
	
	/**
	 * 是否锁定 1 未锁定 2 锁定
	 */
	private String isLocked;
	
	/**
	 * 用户登录输入账号 支持手机号码、用户名、邮箱
	 */
	private String loginName;
	
	/**
	 * 密码错误次数
	 */
	private Integer errorCount;
	
	/**
	 * 错误时间
	 */
	private String errorTime;
	
	/**
	 * 锁定时间
	 */
	private String lockedTime;

	private String expGetType;// 发生类型
	private String investAwardValue;// 积分值
	private String investAwardType;// 类型
	private String totalNum;// 总数
	private String point;// 积分
	private String experience;// 经验
	private String increaseInterest;// 加息
	// 认证状态
	private String attestationStatus;
	// 是否实名认证
	private String isAuthentication;
	private String cusBor;//借款客户
	private String cusInv;//投资客户
	
	private String emergerncyName;//紧急联系人姓名
	private String emergerncyRelation;//紧急联系人关系
	private String emergerncyPhoneNo;//紧急联系人电话
	private String loginTime;//登录时间
	private String tradingTime;//交易时间
	private String tradePassWord;//交易密码
	private String cusBorAndCusInv;//借款和投资
	private BigDecimal jxInterest;//加息收益
	private String referralCode;//推荐码
	private String recommendedAwards;//分享获得奖励
	private String imageUrl;//头像图片信息
	private String emergencyContactPid;//紧急联系人pid
	private String bid; //客户查询列表加的投标字段
	
	private Integer tradeErrorCount; // 交易密码错误次数
	
	private Date tradeFreezeTime; // 交易密码解冻时间
	
	private Date tradeErrotTime; // 交易密码错误累计截止时间
	
	public String getBid() {
		return bid;
	}

	public void setBid(String bid) {
		this.bid = bid;
	}

	public String getEmergencyContactPid() {
		return emergencyContactPid;
	}

	public void setEmergencyContactPid(String emergencyContactPid) {
		this.emergencyContactPid = emergencyContactPid;
	}

	public String getSexName() {
		return sexName;
	}

	public void setSexName(String sexName) {
		this.sexName = sexName;
	}

	public String getIsMarriageName() {
		return isMarriageName;
	}

	public void setIsMarriageName(String isMarriageName) {
		this.isMarriageName = isMarriageName;
	}

	public String getRecommendedAwards() {
		return recommendedAwards;
	}

	public void setRecommendedAwards(String recommendedAwards) {
		this.recommendedAwards = recommendedAwards;
	}

	public String getReferralCode() {
		return referralCode;
	}

	public void setReferralCode(String referralCode) {
		this.referralCode = referralCode;
	}

	public BigDecimal getJxInterest() {
		return jxInterest;
	}

	public void setJxInterest(BigDecimal jxInterest) {
		this.jxInterest = jxInterest;
	}

	public String getCusBorAndCusInv() {
		return cusBorAndCusInv;
	}

	public void setCusBorAndCusInv(String cusBorAndCusInv) {
		this.cusBorAndCusInv = cusBorAndCusInv;
	}

	public String getTradePassWord() {
		return tradePassWord;
	}

	public void setTradePassWord(String tradePassWord) {
		this.tradePassWord = tradePassWord;
	}

	public String getLoginTime() {
		return loginTime;
	}

	public void setLoginTime(String loginTime) {
		this.loginTime = loginTime;
	}

	public String getTradingTime() {
		return tradingTime;
	}

	public void setTradingTime(String tradingTime) {
		this.tradingTime = tradingTime;
	}

	public String getEmergerncyName() {
		return emergerncyName;
	}

	public void setEmergerncyName(String emergerncyName) {
		this.emergerncyName = emergerncyName;
	}

	public String getEmergerncyRelation() {
		return emergerncyRelation;
	}

	public void setEmergerncyRelation(String emergerncyRelation) {
		this.emergerncyRelation = emergerncyRelation;
	}

	public String getEmergerncyPhoneNo() {
		return emergerncyPhoneNo;
	}

	public void setEmergerncyPhoneNo(String emergerncyPhoneNo) {
		this.emergerncyPhoneNo = emergerncyPhoneNo;
	}

	public String getErrorTime() {
		return errorTime;
	}

	public void setErrorTime(String errorTime) {
		this.errorTime = errorTime;
	}

	public String getLockedTime() {
		return lockedTime;
	}

	public void setLockedTime(String lockedTime) {
		this.lockedTime = lockedTime;
	}

	public String getIsLocked() {
		return isLocked;
	}

	public void setIsLocked(String isLocked) {
		this.isLocked = isLocked;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public Integer getErrorCount() {
		return errorCount;
	}

	public void setErrorCount(Integer errorCount) {
		this.errorCount = errorCount;
	}

	public Integer getCount() {
		return count;
	}
	
	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getCusBor() {
		return cusBor;
	}

	public void setCusBor(String cusBor) {
		this.cusBor = cusBor;
	}

	public String getCusInv() {
		return cusInv;
	}

	public void setCusInv(String cusInv) {
		this.cusInv = cusInv;
	}

	public String getTotal() {
		return total;
	}

	public void setTotal(String total) {
		this.total = total;
	}

	public String getDisableUser() {
		return disableUser;
	}

	public void setDisableUser(String disableUser) {
		this.disableUser = disableUser;
	}

	public BigDecimal getAvailableBeginBalance() {
		return availableBeginBalance;
	}

	public void setAvailableBeginBalance(BigDecimal availableBeginBalance) {
		this.availableBeginBalance = availableBeginBalance;
	}

	public BigDecimal getAvailableEndBalance() {
		return availableEndBalance;
	}

	public void setAvailableEndBalance(BigDecimal availableEndBalance) {
		this.availableEndBalance = availableEndBalance;
	}

	public String getVipPID() {
		return vipPID;
	}

	public void setVipPID(String vipPID) {
		this.vipPID = vipPID;
	}

	public String getVipLevelName() {
		return vipLevelName;
	}

	public void setVipLevelName(String vipLevelName) {
		this.vipLevelName = vipLevelName;
	}

	public String getAttestationStatus() {
		return attestationStatus;
	}

	public void setAttestationStatus(String attestationStatus) {
		this.attestationStatus = attestationStatus;
	}

	public String getIsAuthentication() {
		return isAuthentication;
	}

	public void setIsAuthentication(String isAuthentication) {
		this.isAuthentication = isAuthentication;
	}

	public String getOldcusServiceId() {
		return oldcusServiceId;
	}
	public void setOldcusServiceId(String oldcusServiceId) {
		this.oldcusServiceId = oldcusServiceId;
	}
	public String getCusServiceName() {
		return cusServiceName;
	}

	public void setCusServiceName(String cusServiceName) {
		this.cusServiceName = cusServiceName;
	}

	public String getCusServicePid() {
		return cusServicePid;
	}

	public void setCusServicePid(String cusServicePid) {
		this.cusServicePid = cusServicePid;
	}

	public BigDecimal getGetAmount() {
		return getAmount;
	}

	public void setGetAmount(BigDecimal getAmount) {
		this.getAmount = getAmount;
	}

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}

	public String getGetTime() {
		return getTime;
	}

	public void setGetTime(String getTime) {
		this.getTime = getTime;
	}

	public String getRedDesc() {
		return redDesc;
	}

	public void setRedDesc(String redDesc) {
		this.redDesc = redDesc;
	}

	public String getPoint() {
		return point;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

	public void setPoint(String point) {
		this.point = point;
	}

	public String getResReg() {
		return resReg;
	}

	public String getExperience() {
		return experience;
	}

	public void setResReg(String resReg) {
		this.resReg = resReg;
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

	public String getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(String totalNum) {
		this.totalNum = totalNum;
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

	public void setCount(Integer count) {
		this.count = count;
	}

	public String getExpGetType() {
		return expGetType;
	}

	public void setExpGetType(String expGetType) {
		this.expGetType = expGetType;
	}

	public String getHappenBeginTime() {
		return happenBeginTime;
	}

	public void setHappenBeginTime(String happenBeginTime) {
		this.happenBeginTime = happenBeginTime;
	}

	public String getHappenEndTime() {
		return happenEndTime;
	}

	public void setHappenEndTime(String happenEndTime) {
		this.happenEndTime = happenEndTime;
	}

	public String getPointType() {
		return pointType;
	}

	public void setPointType(String pointType) {
		this.pointType = pointType;
	}

	public Date getVipTimeTwo() {
		return vipTimeTwo;
	}

	public void setVipTimeTwo(Date vipTimeTwo) {
		this.vipTimeTwo = vipTimeTwo;
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

	public String getCertType() {
		return certType;
	}

	public void setCertType(String certType) {
		this.certType = certType;
	}

	public String getBusLicCertUrl() {
		return busLicCertUrl;
	}

	public void setBusLicCertUrl(String busLicCertUrl) {
		this.busLicCertUrl = busLicCertUrl;
	}

	public String getExperienceGold() {
		return experienceGold;
	}

	public void setExperienceGold(String experienceGold) {
		this.experienceGold = experienceGold;
	}

	public String getReceivedRedEnvelope() {
		return receivedRedEnvelope;
	}

	public void setReceivedRedEnvelope(String receivedRedEnvelope) {
		this.receivedRedEnvelope = receivedRedEnvelope;
	}

	public String getFrozenAmount() {
		return frozenAmount;
	}

	public void setFrozenAmount(String frozenAmount) {
		this.frozenAmount = frozenAmount;
	}

	public String getInvestmentIncentives() {
		return investmentIncentives;
	}

	public void setInvestmentIncentives(String investmentIncentives) {
		this.investmentIncentives = investmentIncentives;
	}

	public String getCusStatus() {
		return cusStatus;
	}

	public void setCusStatus(String cusStatus) {
		this.cusStatus = cusStatus;
	}

	public Date getForceDate() {
		return forceDate;
	}

	public void setForceDate(Date forceDate) {
		this.forceDate = forceDate;
	}

	public Date getInvalidDate() {
		return invalidDate;
	}

	public void setInvalidDate(Date invalidDate) {
		this.invalidDate = invalidDate;
	}

	public String getCusStatusRemark() {
		return cusStatusRemark;
	}

	public void setCusStatusRemark(String cusStatusRemark) {
		this.cusStatusRemark = cusStatusRemark;
	}

	public String getVipInfoPid() {
		return vipInfoPid;
	}

	public void setVipInfoPid(String vipInfoPid) {
		this.vipInfoPid = vipInfoPid;
	}

	public String getVipId() {
		return vipId;
	}

	public void setVipId(String vipId) {
		this.vipId = vipId;
	}

	public String getVipName() {
		return vipName;
	}

	public void setVipName(String vipName) {
		this.vipName = vipName;
	}

	public String getLarVipWatDesc() {
		return larVipWatDesc;
	}

	public void setLarVipWatDesc(String larVipWatDesc) {
		this.larVipWatDesc = larVipWatDesc;
	}

	public String getLargessValue() {
		return largessValue;
	}

	public void setLargessValue(String largessValue) {
		this.largessValue = largessValue;
	}

	public String getNewCustServiceId() {
		return newCustServiceId;
	}

	public void setNewCustServiceId(String newCustServiceId) {
		this.newCustServiceId = newCustServiceId;
	}

	public String getUpdSerWatDesc() {
		return updSerWatDesc;
	}

	public void setUpdSerWatDesc(String updSerWatDesc) {
		this.updSerWatDesc = updSerWatDesc;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid == null ? null : pid.trim();
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName == null ? null : customerName.trim();
	}

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname == null ? null : sname.trim();
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password == null ? null : password.trim();
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo == null ? null : phoneNo.trim();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email == null ? null : email.trim();
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex == null ? null : sex.trim();
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public String getRefereeUser() {
		return refereeUser;
	}

	public void setRefereeUser(String refereeUser) {
		this.refereeUser = refereeUser == null ? null : refereeUser.trim();
	}

	public String getCustomerServiceUser() {
		return customerServiceUser;
	}

	public void setCustomerServiceUser(String customerServiceUser) {
		this.customerServiceUser = customerServiceUser == null ? null : customerServiceUser.trim();
	}

	public BigDecimal getTotalStay() {
		return totalStay;
	}

	public void setTotalStay(BigDecimal totalStay) {
		this.totalStay = totalStay;
	}

	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
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

	public String getIsBlacklist() {
		return isBlacklist;
	}

	public void setIsBlacklist(String isBlacklist) {
		this.isBlacklist = isBlacklist == null ? null : isBlacklist.trim();
	}

	public String getIsForbidWithdraw() {
		return isForbidWithdraw;
	}

	public void setIsForbidWithdraw(String isForbidWithdraw) {
		this.isForbidWithdraw = isForbidWithdraw == null ? null : isForbidWithdraw.trim();
	}

	public String getIsForbidTransfer() {
		return isForbidTransfer;
	}

	public void setIsForbidTransfer(String isForbidTransfer) {
		this.isForbidTransfer = isForbidTransfer == null ? null : isForbidTransfer.trim();
	}

	public String getIsFreeze() {
		return isFreeze;
	}

	public void setIsFreeze(String isFreeze) {
		this.isFreeze = isFreeze == null ? null : isFreeze.trim();
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public String getRegistrationTime() {
		return registrationTime;
	}

	public void setRegistrationTime(String registrationTime) {
		this.registrationTime = registrationTime;
	}

	
	public Integer getVipTime() {
		return vipTime;
	}

	public void setVipTime(Integer vipTime) {
		this.vipTime = vipTime;
	}

	public String getIsVip() {
		return isVip;
	}

	public void setIsVip(String isVip) {
		this.isVip = isVip == null ? null : isVip.trim();
	}

	public String getVipLevel() {
		return vipLevel;
	}

	public void setVipLevel(String vipLevel) {
		this.vipLevel = vipLevel == null ? null : vipLevel.trim();
	}

	public String getIsAttestation() {
		return isAttestation;
	}

	public void setIsAttestation(String isAttestation) {
		this.isAttestation = isAttestation == null ? null : isAttestation.trim();
	}

	public String getIdentificationNo() {
		return identificationNo;
	}

	public void setIdentificationNo(String identificationNo) {
		this.identificationNo = identificationNo == null ? null : identificationNo.trim();
	}

	public String getIsMarriage() {
		return isMarriage;
	}

	public void setIsMarriage(String isMarriage) {
		this.isMarriage = isMarriage == null ? null : isMarriage.trim();
	}

	public String getHomeAddress() {
		return homeAddress;
	}

	public void setHomeAddress(String homeAddress) {
		this.homeAddress = homeAddress == null ? null : homeAddress.trim();
	}

	public String getNowAddress() {
		return nowAddress;
	}

	public void setNowAddress(String nowAddress) {
		this.nowAddress = nowAddress == null ? null : nowAddress.trim();
	}

	public String getWechatNo() {
		return wechatNo;
	}

	public void setWechatNo(String wechatNo) {
		this.wechatNo = wechatNo == null ? null : wechatNo.trim();
	}

	public String getImmediateFamily() {
		return immediateFamily;
	}

	public void setImmediateFamily(String immediateFamily) {
		this.immediateFamily = immediateFamily == null ? null : immediateFamily.trim();
	}

	public String getImmediateFamilyNo() {
		return immediateFamilyNo;
	}

	public void setImmediateFamilyNo(String immediateFamilyNo) {
		this.immediateFamilyNo = immediateFamilyNo == null ? null : immediateFamilyNo.trim();
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
		this.lastUpdateUser = lastUpdateUser == null ? null : lastUpdateUser.trim();
	}

	public String getCustDesc() {
		return custDesc;
	}

	public void setCustDesc(String custDesc) {
		this.custDesc = custDesc == null ? null : custDesc.trim();
	}

	public String getCameSzTime() {
		return cameSzTime;
	}

	public void setCameSzTime(String cameSzTime) {
		this.cameSzTime = cameSzTime;
	}

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
	
	public void setTradePassword(String tradePassword) {
		this.tradePassword = tradePassword;
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
		CusTomer other = (CusTomer) that;
		return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid())) && (this.getCustomerName() == null ? other.getCustomerName() == null : this.getCustomerName().equals(other.getCustomerName())) && (this.getSname() == null ? other.getSname() == null : this.getSname().equals(other.getSname())) && (this.getBirthday() == null ? other.getBirthday() == null : this.getBirthday().equals(other.getBirthday())) && (this.getPassword() == null ? other.getPassword() == null : this.getPassword().equals(other.getPassword())) && (this.getPhoneNo() == null ? other.getPhoneNo() == null : this.getPhoneNo().equals(other.getPhoneNo())) && (this.getEmail() == null ? other.getEmail() == null : this.getEmail().equals(other.getEmail()))
				&& (this.getSex() == null ? other.getSex() == null : this.getSex().equals(other.getSex())) && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus())) && (this.getRegistrationTime() == null ? other.getRegistrationTime() == null : this.getRegistrationTime().equals(other.getRegistrationTime())) && (this.getRefereeUser() == null ? other.getRefereeUser() == null : this.getRefereeUser().equals(other.getRefereeUser())) && (this.getCustomerServiceUser() == null ? other.getCustomerServiceUser() == null : this.getCustomerServiceUser().equals(other.getCustomerServiceUser())) && (this.getTotalStay() == null ? other.getTotalStay() == null : this.getTotalStay().equals(other.getTotalStay()))
				&& (this.getAvailableBalance() == null ? other.getAvailableBalance() == null : this.getAvailableBalance().equals(other.getAvailableBalance())) && (this.getAvailablePoint() == null ? other.getAvailablePoint() == null : this.getAvailablePoint().equals(other.getAvailablePoint())) && (this.getEmpiricalValue() == null ? other.getEmpiricalValue() == null : this.getEmpiricalValue().equals(other.getEmpiricalValue())) && (this.getIsBlacklist() == null ? other.getIsBlacklist() == null : this.getIsBlacklist().equals(other.getIsBlacklist())) && (this.getIsForbidWithdraw() == null ? other.getIsForbidWithdraw() == null : this.getIsForbidWithdraw().equals(other.getIsForbidWithdraw()))
				&& (this.getIsForbidTransfer() == null ? other.getIsForbidTransfer() == null : this.getIsForbidTransfer().equals(other.getIsForbidTransfer())) && (this.getIsFreeze() == null ? other.getIsFreeze() == null : this.getIsFreeze().equals(other.getIsFreeze())) && (this.getIsVip() == null ? other.getIsVip() == null : this.getIsVip().equals(other.getIsVip())) && (this.getVipLevel() == null ? other.getVipLevel() == null : this.getVipLevel().equals(other.getVipLevel())) && (this.getIsAttestation() == null ? other.getIsAttestation() == null : this.getIsAttestation().equals(other.getIsAttestation()))
				&& (this.getIdentificationNo() == null ? other.getIdentificationNo() == null : this.getIdentificationNo().equals(other.getIdentificationNo())) && (this.getIsMarriage() == null ? other.getIsMarriage() == null : this.getIsMarriage().equals(other.getIsMarriage())) && (this.getHomeAddress() == null ? other.getHomeAddress() == null : this.getHomeAddress().equals(other.getHomeAddress())) && (this.getNowAddress() == null ? other.getNowAddress() == null : this.getNowAddress().equals(other.getNowAddress())) && (this.getCameSzTime() == null ? other.getCameSzTime() == null : this.getCameSzTime().equals(other.getCameSzTime())) && (this.getWechatNo() == null ? other.getWechatNo() == null : this.getWechatNo().equals(other.getWechatNo()))
				&& (this.getImmediateFamily() == null ? other.getImmediateFamily() == null : this.getImmediateFamily().equals(other.getImmediateFamily())) && (this.getImmediateFamilyNo() == null ? other.getImmediateFamilyNo() == null : this.getImmediateFamilyNo().equals(other.getImmediateFamilyNo())) && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser())) && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime())) && (this.getLastUpdateUser() == null ? other.getLastUpdateUser() == null : this.getLastUpdateUser().equals(other.getLastUpdateUser()))
				&& (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime())) && (this.getCustDesc() == null ? other.getCustDesc() == null : this.getCustDesc().equals(other.getCustDesc()));
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
		result = prime * result + ((getCustomerName() == null) ? 0 : getCustomerName().hashCode());
		result = prime * result + ((getSname() == null) ? 0 : getSname().hashCode());
		result = prime * result + ((getBirthday() == null) ? 0 : getBirthday().hashCode());
		result = prime * result + ((getPassword() == null) ? 0 : getPassword().hashCode());
		result = prime * result + ((getPhoneNo() == null) ? 0 : getPhoneNo().hashCode());
		result = prime * result + ((getEmail() == null) ? 0 : getEmail().hashCode());
		result = prime * result + ((getSex() == null) ? 0 : getSex().hashCode());
		result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
		result = prime * result + ((getRegistrationTime() == null) ? 0 : getRegistrationTime().hashCode());
		result = prime * result + ((getRefereeUser() == null) ? 0 : getRefereeUser().hashCode());
		result = prime * result + ((getCustomerServiceUser() == null) ? 0 : getCustomerServiceUser().hashCode());
		result = prime * result + ((getTotalStay() == null) ? 0 : getTotalStay().hashCode());
		result = prime * result + ((getAvailableBalance() == null) ? 0 : getAvailableBalance().hashCode());
		result = prime * result + ((getAvailablePoint() == null) ? 0 : getAvailablePoint().hashCode());
		result = prime * result + ((getEmpiricalValue() == null) ? 0 : getEmpiricalValue().hashCode());
		result = prime * result + ((getIsBlacklist() == null) ? 0 : getIsBlacklist().hashCode());
		result = prime * result + ((getIsForbidWithdraw() == null) ? 0 : getIsForbidWithdraw().hashCode());
		result = prime * result + ((getIsForbidTransfer() == null) ? 0 : getIsForbidTransfer().hashCode());
		result = prime * result + ((getIsFreeze() == null) ? 0 : getIsFreeze().hashCode());
		result = prime * result + ((getIsVip() == null) ? 0 : getIsVip().hashCode());
		result = prime * result + ((getVipLevel() == null) ? 0 : getVipLevel().hashCode());
		result = prime * result + ((getIsAttestation() == null) ? 0 : getIsAttestation().hashCode());
		result = prime * result + ((getIdentificationNo() == null) ? 0 : getIdentificationNo().hashCode());
		result = prime * result + ((getIsMarriage() == null) ? 0 : getIsMarriage().hashCode());
		result = prime * result + ((getHomeAddress() == null) ? 0 : getHomeAddress().hashCode());
		result = prime * result + ((getNowAddress() == null) ? 0 : getNowAddress().hashCode());
		result = prime * result + ((getCameSzTime() == null) ? 0 : getCameSzTime().hashCode());
		result = prime * result + ((getWechatNo() == null) ? 0 : getWechatNo().hashCode());
		result = prime * result + ((getImmediateFamily() == null) ? 0 : getImmediateFamily().hashCode());
		result = prime * result + ((getImmediateFamilyNo() == null) ? 0 : getImmediateFamilyNo().hashCode());
		result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
		result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
		result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
		result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
		result = prime * result + ((getCustDesc() == null) ? 0 : getCustDesc().hashCode());
		return result;
	}
	
	public boolean validateTradePwd(String pwd){
		if(pwd!=null && pwd.trim() != ""){
			if(pwd.equals(this.tradePassword)){
				return true;
			}
		}
		return false;
	}

	public BigDecimal getPaychecksAmount() {
		return paychecksAmount;
	}

	public void setPaychecksAmount(BigDecimal paychecksAmount) {
		this.paychecksAmount = paychecksAmount;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Integer getTradeErrorCount() {
		return tradeErrorCount;
	}

	public void setTradeErrorCount(Integer tradeErrorCount) {
		this.tradeErrorCount = tradeErrorCount;
	}

	public Date getTradeFreezeTime() {
		return tradeFreezeTime;
	}

	public void setTradeFreezeTime(Date tradeFreezeTime) {
		this.tradeFreezeTime = tradeFreezeTime;
	}

	public Date getTradeErrotTime() {
		return tradeErrotTime;
	}

	public void setTradeErrotTime(Date tradeErrotTime) {
		this.tradeErrotTime = tradeErrotTime;
	}
	
	
}