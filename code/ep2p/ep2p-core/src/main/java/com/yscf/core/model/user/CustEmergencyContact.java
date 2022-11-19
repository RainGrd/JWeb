package com.yscf.core.model.user;

import com.achievo.framework.entity.BaseEntity;

public class CustEmergencyContact extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 2666512657919633117L;

	private String pid;

    private String name;//紧急联系人姓名

    private String relation;//关系

    private String phoneNo;//电话号码

    private String customerId;//客户id
    
    private String address;//地址
    
    private String userName;//用户名
    
    private String originalPassWord;//原始密码
    
    private String newPassWord;//新设置的密码
    
//    private String tradePassword;//设置交易密码
    
    private String acccount;//用户账户
    
    private String loginTime;//登录时间
    
    private String tradingTime;//交易时间

    
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

//	public String getTradePassword() {
//		return tradePassword;
//	}

//	public void setTradePassword(String tradePassword) {
//		this.tradePassword = tradePassword;
//	}

	public String getAcccount() {
		return acccount;
	}

	public void setAcccount(String acccount) {
		this.acccount = acccount;
	}

//	public String gettradePassword() {
//		return tradePassword;
//	}
//
//	public void settradePassword(String jiaoYiPassWord) {
//		this.tradePassword = jiaoYiPassWord;
//	}

	public String getNewPassWord() {
		return newPassWord;
	}

	public void setNewPassWord(String newPassWord) {
		this.newPassWord = newPassWord;
	}

	public String getOriginalPassWord() {
		return originalPassWord;
	}

	public void setOriginalPassWord(String originalPassWord) {
		this.originalPassWord = originalPassWord;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getRelation() {
        return relation;
    }

    public void setRelation(String relation) {
        this.relation = relation == null ? null : relation.trim();
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo == null ? null : phoneNo.trim();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }
    

    public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
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
        CustEmergencyContact other = (CustEmergencyContact) that;
        return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getName() == null ? other.getName() == null : this.getName().equals(other.getName()))
            && (this.getRelation() == null ? other.getRelation() == null : this.getRelation().equals(other.getRelation()))
            && (this.getPhoneNo() == null ? other.getPhoneNo() == null : this.getPhoneNo().equals(other.getPhoneNo()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getName() == null) ? 0 : getName().hashCode());
        result = prime * result + ((getRelation() == null) ? 0 : getRelation().hashCode());
        result = prime * result + ((getPhoneNo() == null) ? 0 : getPhoneNo().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        return result;
    }
}