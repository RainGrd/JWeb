package com.yscf.db.model.escf3.user;

import java.math.BigDecimal;
import java.util.List;

import com.achievo.framework.entity.BaseEntity;

public class CustFundWater extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 212445823658706071L;

	private String pid;

	private CusTomer customer;
	
    private BigDecimal fundValue;

    private String fundType;

    private String businessType; //业务类型名称
    private String businessTypeValue;//业务类型的值

    private BigDecimal accountBalance;

    private String customerId;

    private String status;

    private String happenTime;

    private String createUser;

    private String createTime;

    private String lastUpdateUser;

    private String lastUpdateTime;
    
    /**
     * 流水类型集合
     */
    private List<String> businessTypes;
    
    /**
     * 流水类型字符串  多个已英文逗号隔开
     */
    private String businessTypeStr;
    
    private String funWatDesc;
    
    private String version;
    
    // 外键
    private String fkey;
    private String happenBeginTime;//发生开始时间
    private String happenEndTime;//发生结束时间
    private String flag;//标识
    private String typeId;//类型iD
    private String typeName;//类型名称
    private String searchCondition;//资金流水类型查询条件
    private String waterType;//资金流水类型
    private String waterTypeId;//资金流水Id
    private BigDecimal expenditure;//支出
    
    private String shouRu;
    private String zhiChu;
    
    private BigDecimal totalIncome;
    
    private BigDecimal totalPay;
    private String other;//资金流水其它
    
    
    public String getShouRu() {
		return shouRu;
	}
	public void setShouRu(String shouRu) {
		this.shouRu = shouRu;
	}
	public String getZhiChu() {
		return zhiChu;
	}
	public void setZhiChu(String zhiChu) {
		this.zhiChu = zhiChu;
	}
	public String getBusinessTypeValue() {
		return businessTypeValue;
	}
	public void setBusinessTypeValue(String businessTypeValue) {
		this.businessTypeValue = businessTypeValue;
	}
	public BigDecimal getExpenditure() {
		return expenditure;
	}
    public String getOther() {
		return other;
	}

	public void setOther(String other) {
		this.other = other;
	}


	public void setExpenditure(BigDecimal expenditure) {
		this.expenditure = expenditure;
	}

	public String getWaterTypeId() {
		return waterTypeId;
	}

	public void setWaterTypeId(String waterTypeId) {
		this.waterTypeId = waterTypeId;
	}

	public String getWaterType() {
		return waterType;
	}

	public void setWaterType(String waterType) {
		this.waterType = waterType;
	}

	public String getSearchCondition() {
		return searchCondition;
	}

	public void setSearchCondition(String searchCondition) {
		this.searchCondition = searchCondition;
	}

	public String getTypeId() {
		return typeId;
	}

	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
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

	public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public BigDecimal getFundValue() {
        return fundValue;
    }

    public void setFundValue(BigDecimal fundValue) {
        this.fundValue = fundValue;
    }

    public String getFundType() {
        return fundType;
    }

    public void setFundType(String fundType) {
        this.fundType = fundType == null ? null : fundType.trim();
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType == null ? null : businessType.trim();
    }

    public BigDecimal getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(BigDecimal accountBalance) {
        this.accountBalance = accountBalance;
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

    public String getHappenTime() {
        return happenTime;
    }

    public void setHappenTime(String happenTime) {
        this.happenTime = happenTime;
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

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getFunWatDesc() {
        return funWatDesc;
    }

    public void setFunWatDesc(String funWatDesc) {
        this.funWatDesc = funWatDesc == null ? null : funWatDesc.trim();
    }
    
    public CusTomer getCustomer() {
		return customer;
	}

	public void setCustomer(CusTomer customer) {
		this.customer = customer;
	}
	
	public BigDecimal getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(BigDecimal totalIncome) {
		this.totalIncome = totalIncome;
	}

	public BigDecimal getTotalPay() {
		return totalPay;
	}

	public void setTotalPay(BigDecimal totalPay) {
		this.totalPay = totalPay;
	}
	
	public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
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
        CustFundWater other = (CustFundWater) that;
        return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getFundValue() == null ? other.getFundValue() == null : this.getFundValue().equals(other.getFundValue()))
            && (this.getFundType() == null ? other.getFundType() == null : this.getFundType().equals(other.getFundType()))
            && (this.getBusinessType() == null ? other.getBusinessType() == null : this.getBusinessType().equals(other.getBusinessType()))
            && (this.getAccountBalance() == null ? other.getAccountBalance() == null : this.getAccountBalance().equals(other.getAccountBalance()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getHappenTime() == null ? other.getHappenTime() == null : this.getHappenTime().equals(other.getHappenTime()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastUpdateUser() == null ? other.getLastUpdateUser() == null : this.getLastUpdateUser().equals(other.getLastUpdateUser()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()))
            && (this.getCustomer() == null ? other.getCustomer() == null : this.getCustomer().equals(other.getCustomer()))
            && (this.getTotalIncome() == null ? other.getTotalIncome() == null : this.getTotalIncome().equals(other.getTotalIncome()))
            && (this.getTotalPay() == null ? other.getTotalPay() == null : this.getTotalPay().equals(other.getTotalPay()))
            && (this.getFunWatDesc() == null ? other.getFunWatDesc() == null : this.getFunWatDesc().equals(other.getFunWatDesc()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getFundValue() == null) ? 0 : getFundValue().hashCode());
        result = prime * result + ((getFundType() == null) ? 0 : getFundType().hashCode());
        result = prime * result + ((getBusinessType() == null) ? 0 : getBusinessType().hashCode());
        result = prime * result + ((getAccountBalance() == null) ? 0 : getAccountBalance().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getHappenTime() == null) ? 0 : getHappenTime().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        result = prime * result + ((getCustomer() == null) ? 0 : getCustomer().hashCode());
        result = prime * result + ((getFunWatDesc() == null) ? 0 : getFunWatDesc().hashCode());
        result = prime * result + ((getTotalIncome() == null) ? 0 : getTotalIncome().hashCode());
        result = prime * result + ((getTotalPay() == null) ? 0 : getTotalPay().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        return result;
    }
	public List<String> getBusinessTypes() {
		return businessTypes;
	}
	public void setBusinessTypes(List<String> businessTypes) {
		this.businessTypes = businessTypes;
	}
	public String getBusinessTypeStr() {
		return businessTypeStr;
	}
	public void setBusinessTypeStr(String businessTypeStr) {
		this.businessTypeStr = businessTypeStr;
	}
	public String getFkey() {
		return fkey;
	}
	public void setFkey(String fkey) {
		this.fkey = fkey;
	}
}