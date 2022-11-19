package com.yscf.db.model.escf3.user;

import com.achievo.framework.entity.BaseEntity;
import java.util.Date;

public class Bank extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7431738007904667539L;

	private String pid;

    private String bankcardNo;//银行卡卡号

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
    
    private String loginPassWord;//登录密码
    
    private int index;//加密因子
     
    private String newPwd;//交易密码

    private int isWithdrawals; //是否可提现  0：否 1：是
    
    private int quickPayment; //是否快捷支付 0：否 1：是 
    
    
    public int getQuickPayment() {
		return quickPayment;
	}

	public void setQuickPayment(int quickPayment) {
		this.quickPayment = quickPayment;
	}

	public int getIsWithdrawals() {
		return isWithdrawals;
	}

	public void setIsWithdrawals(int isWithdrawals) {
		this.isWithdrawals = isWithdrawals;
	}

	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public String getLoginPassWord() {
		return loginPassWord;
	}

	public void setLoginPassWord(String loginPassWord) {
		this.loginPassWord = loginPassWord;
	}

	public int getIndex() {
		return index;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public String getPassWord() {
		return passWord;
	}

	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}

	public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getBankcardNo() {
        return bankcardNo;
    }

    public void setBankcardNo(String bankcardNo) {
        this.bankcardNo = bankcardNo == null ? null : bankcardNo.trim();
    }

    public String getBankcardName() {
        return bankcardName;
    }

    public void setBankcardName(String bankcardName) {
        this.bankcardName = bankcardName == null ? null : bankcardName.trim();
    }

    public String getBankcardIdentification() {
        return bankcardIdentification;
    }

    public void setBankcardIdentification(String bankcardIdentification) {
        this.bankcardIdentification = bankcardIdentification == null ? null : bankcardIdentification.trim();
    }

    public String getBelongingBank() {
        return belongingBank;
    }

    public void setBelongingBank(String belongingBank) {
        this.belongingBank = belongingBank == null ? null : belongingBank.trim();
    }

    public String getBelongingProvince() {
        return belongingProvince;
    }

    public void setBelongingProvince(String belongingProvince) {
        this.belongingProvince = belongingProvince == null ? null : belongingProvince.trim();
    }

    public String getBelongingCity() {
        return belongingCity;
    }

    public void setBelongingCity(String belongingCity) {
        this.belongingCity = belongingCity == null ? null : belongingCity.trim();
    }

    public String getOpenAddress() {
        return openAddress;
    }

    public void setOpenAddress(String openAddress) {
        this.openAddress = openAddress == null ? null : openAddress.trim();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
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
        this.lastUpdateUser = lastUpdateUser == null ? null : lastUpdateUser.trim();
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
        this.bankDesc = bankDesc == null ? null : bankDesc.trim();
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
        Bank other = (Bank) that;
        return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getBankcardNo() == null ? other.getBankcardNo() == null : this.getBankcardNo().equals(other.getBankcardNo()))
            && (this.getBankcardName() == null ? other.getBankcardName() == null : this.getBankcardName().equals(other.getBankcardName()))
            && (this.getBankcardIdentification() == null ? other.getBankcardIdentification() == null : this.getBankcardIdentification().equals(other.getBankcardIdentification()))
            && (this.getBelongingBank() == null ? other.getBelongingBank() == null : this.getBelongingBank().equals(other.getBelongingBank()))
            && (this.getBelongingProvince() == null ? other.getBelongingProvince() == null : this.getBelongingProvince().equals(other.getBelongingProvince()))
            && (this.getBelongingCity() == null ? other.getBelongingCity() == null : this.getBelongingCity().equals(other.getBelongingCity()))
            && (this.getOpenAddress() == null ? other.getOpenAddress() == null : this.getOpenAddress().equals(other.getOpenAddress()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastUpdateUser() == null ? other.getLastUpdateUser() == null : this.getLastUpdateUser().equals(other.getLastUpdateUser()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()))
            && (this.getBankDesc() == null ? other.getBankDesc() == null : this.getBankDesc().equals(other.getBankDesc()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getBankcardNo() == null) ? 0 : getBankcardNo().hashCode());
        result = prime * result + ((getBankcardName() == null) ? 0 : getBankcardName().hashCode());
        result = prime * result + ((getBankcardIdentification() == null) ? 0 : getBankcardIdentification().hashCode());
        result = prime * result + ((getBelongingBank() == null) ? 0 : getBelongingBank().hashCode());
        result = prime * result + ((getBelongingProvince() == null) ? 0 : getBelongingProvince().hashCode());
        result = prime * result + ((getBelongingCity() == null) ? 0 : getBelongingCity().hashCode());
        result = prime * result + ((getOpenAddress() == null) ? 0 : getOpenAddress().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        result = prime * result + ((getBankDesc() == null) ? 0 : getBankDesc().hashCode());
        return result;
    }
}