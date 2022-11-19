package com.yscf.api.vo.user;

import java.util.Date;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br> 
 * 安全中心Vo
 * @author  heng.wang
 * @date    2016年1月12日
 * @version v1.0.0
 */
public class BankVo  extends BaseEntity{
	/**
	 * 
	 */
	private static final long serialVersionUID = -6866855681666036875L;

	private String pid;
	
	private String userId;//客户id

    private String bankCardNo;//银行卡卡号

    private String bankcardName;//银行卡登记名称

    private String bankcardIdentification;//银行卡登记身份证号码

    private String belongingBank;//银行卡归属银行

    private String belongingProvince;//银行卡归属省份

    private String belongingCity;//银行卡归属城市

    private String openAddress;//银行卡开户行 	

    private String customerId;//客户id

    private String status;//状态

    private String createUser;//创建人

    private String createTime;//创建时间

    private String lastUpdateUser;//最后修改人

    private Date lastUpdateTime;//最后修改时间

    private String bankDesc;//备注
    
    private String passWord;//密码

    
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getBankcardNo() {
		return bankCardNo;
	}

	public void setBankcardNo(String bankcardNo) {
		this.bankCardNo = bankcardNo;
	}

	public String getBankcardName() {
		return bankcardName;
	}

	public void setBankcardName(String bankcardName) {
		this.bankcardName = bankcardName;
	}

	public String getBankcardIdentification() {
		return bankcardIdentification;
	}

	public void setBankcardIdentification(String bankcardIdentification) {
		this.bankcardIdentification = bankcardIdentification;
	}

	public String getBelongingBank() {
		return belongingBank;
	}

	public void setBelongingBank(String belongingBank) {
		this.belongingBank = belongingBank;
	}

	public String getBelongingProvince() {
		return belongingProvince;
	}

	public void setBelongingProvince(String belongingProvince) {
		this.belongingProvince = belongingProvince;
	}

	public String getBelongingCity() {
		return belongingCity;
	}

	public void setBelongingCity(String belongingCity) {
		this.belongingCity = belongingCity;
	}

	public String getOpenAddress() {
		return openAddress;
	}

	public void setOpenAddress(String openAddress) {
		this.openAddress = openAddress;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreateUser() {
		return createUser;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
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
		this.lastUpdateUser = lastUpdateUser;
	}

	public Date getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Date lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getBankDesc() {
		return bankDesc;
	}

	public void setBankDesc(String bankDesc) {
		this.bankDesc = bankDesc;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
    
}
