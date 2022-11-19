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
 * 2016年1月8日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.model.user;

import java.math.BigDecimal;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br> 
 * 仅用于用户登录后显示
 * @author  Yu.Zhang
 * @date    2016年1月8日
 * @version v1.0.0
 */
public class CustomerDto extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7905850452935054398L;

	/**
	 * 客户id
	 */
	private String pid;
	
	/**
	 * vip级别名称
	 */
	private String vipLevelName;
	
	/**
	 * vip级别
	 */
	private String vipLevel;
	
	/**
	 * VIP显示图片
	 */
	private String vipIco;
	
	/**
	 * 银行卡号
	 */
	private String bankcardNo;
	
	/**
	 * 可用积分
	 */
	private Integer availablePoint;
	
	/**
	 * 可用余额
	 */
	private BigDecimal availableBalance;
	
	/**
	 * 客户账号
	 */
	private String customerName;
	
	/**
	 * 客户名称
	 */
	private String sname;
	
	/**
	 * email
	 */
	private String email;
	
	/**
	 * 手机号码
	 */
	private String phoneNo;
	
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
	
	/**
	 * 未使用体验金张数
	 */
	private String expActCount;
	
	/**
	 * 是否实名认证
	 */
	private String isAttestation;
	
	/**
	 * 是否绑定手机
	 */
	private String isBingPhone;
	
	/**
	 * 是否设置交易密码
	 */
	private String isSetTradePwd;
	
	/**
	 * 头像路径
	 */
	private String imageUrl;
	
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
	 * VIP时长
	 */
	private Integer vipTime;
	
	/**
	 * VIP经验值
	 */
	private Integer empiricalValue;
	
	/**
	 * 管理费率
	 */
	private BigDecimal discount;
	
	/**
	 * 移动端登录设备ID
	 */
	private String mobileDeviceMachineCode;
	
	public String getMobileDeviceMachineCode() {
		return mobileDeviceMachineCode;
	}

	public void setMobileDeviceMachineCode(String mobileDeviceMachineCode) {
		this.mobileDeviceMachineCode = mobileDeviceMachineCode;
	}

	public String getBankcardNo() {
		return bankcardNo;
	}

	public void setBankcardNo(String bankcardNo) {
		this.bankcardNo = bankcardNo;
	}

	public BigDecimal getDiscount() {
		return discount;
	}

	public void setDiscount(BigDecimal discount) {
		this.discount = discount;
	}

	public String getExpActCount() {
		return expActCount;
	}

	public void setExpActCount(String expActCount) {
		this.expActCount = expActCount;
	}

	public Integer getExperienceStart() {
		return experienceStart;
	}

	public void setExperienceStart(Integer experienceStart) {
		this.experienceStart = experienceStart;
	}

	public String getVipLevel() {
		return vipLevel;
	}

	public void setVipLevel(String vipLevel) {
		this.vipLevel = vipLevel;
	}

	public Integer getEmpiricalValue() {
		return empiricalValue;
	}

	public void setEmpiricalValue(Integer empiricalValue) {
		this.empiricalValue = empiricalValue;
	}

	public Integer getExperienceEnd() {
		return experienceEnd;
	}

	public void setExperienceEnd(Integer experienceEnd) {
		this.experienceEnd = experienceEnd;
	}

	public String getIsVip() {
		return isVip;
	}

	public void setIsVip(String isVip) {
		this.isVip = isVip;
	}

	public Integer getVipTime() {
		return vipTime;
	}

	public void setVipTime(Integer vipTime) {
		this.vipTime = vipTime;
	}

	public Integer getIsFirstPay() {
		return isFirstPay;
	}

	public void setIsFirstPay(Integer isFirstPay) {
		this.isFirstPay = isFirstPay;
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getIsAttestation() {
		return isAttestation;
	}

	public void setIsAttestation(String isAttestation) {
		this.isAttestation = isAttestation;
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

	public String getSname() {
		return sname;
	}

	public void setSname(String sname) {
		this.sname = sname;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getVipLevelName() {
		return vipLevelName;
	}

	public void setVipLevelName(String vipLevelName) {
		this.vipLevelName = vipLevelName;
	}

	public String getVipIco() {
		return vipIco;
	}

	public void setVipIco(String vipIco) {
		this.vipIco = vipIco;
	}

	public Integer getAvailablePoint() {
		return availablePoint;
	}

	public void setAvailablePoint(Integer availablePoint) {
		this.availablePoint = availablePoint;
	}

	public BigDecimal getAvailableBalance() {
		return availableBalance;
	}

	public void setAvailableBalance(BigDecimal availableBalance) {
		this.availableBalance = availableBalance;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public Integer getSignday() {
		return signday;
	}

	public void setSignday(Integer signday) {
		this.signday = signday;
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
}


