package com.yscf.db.model.escf3.financial;

import java.math.BigDecimal;

import com.achievo.framework.entity.BaseEntity;
import com.yscf.db.model.escf3.user.Bank;
import com.yscf.db.model.escf3.user.CusTomer;

/**
 * Description：提现类Bean（表：t_biz_withdraw）
 * @author  JingYu.Dai
 * @date    2015年10月20日
 * @version v1.0.0
 */
public class BizWithdraw extends BaseEntity {

	private static final long serialVersionUID = 8117160284754053799L;
	
	private String pid;
	
	private String bankId;
	
	private String code;//验证码
	
	private CusTomer customer;//客户对象
	
	private String customerId;

	private Bank bank;//银行对象

    private String accountId;//账户ID

    private String transferedTime;//转账时间

    private String auditTime;//审核时间
    
    private String applyTime;//申请时间

    private BigDecimal amount;//提现金额

    private BigDecimal cost;//提现费

    private BigDecimal fee;//手续费

    //private String transferedStatus;//转账状态

    private String bankType;//银行类型

    private String userId;//审核人
    
    private String userName;//审核人姓名
    
    private String transferedUserId;//转账确认人
    
    private String transferedUserName;//转账确认人姓名

    private String witDesc;//备注
    
    private String custWitDesc;//客户提现描述用于客户前台提现流水描述
    
    private String auditStatus;//审核状态
    
    private String descPromptId;//备注提示ID
    
    private String recOrderNo;//提现流水号
    
    private String witSureDesc;//转账确认备注信息
    
    private String createUser;	

    private String createTime;	

    private String lastUpdateUser;	

    private String lastUpdateTime;
    
    private String status;	

    public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}
    
    public Bank getBank() {
		return bank;
	}


	public void setBank(Bank bank) {
		this.bank = bank;
	}


	public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId == null ? null : accountId.trim();
    }


    public String getAuditTime() {
        return auditTime;
    }

    public void setAuditTime(String auditTime) {
        this.auditTime = auditTime;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType == null ? null : bankType.trim();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null ? null : userId.trim();
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

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getWitDesc() {
        return witDesc;
    }

    public void setWitDesc(String witDesc) {
        this.witDesc = witDesc == null ? null : witDesc.trim();
    }

    public String getAuditStatus() {
		return auditStatus;
	}

	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}
	
	 public CusTomer getCustomer() {
		return customer;
	}

	public void setCustomer(CusTomer customer) {
		this.customer = customer;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public String getDescPromptId() {
		return descPromptId;
	}

	public void setDescPromptId(String descPromptId) {
		this.descPromptId = descPromptId;
	}

	public String getWitSureDesc() {
		return witSureDesc;
	}

	public void setWitSureDesc(String witSureDesc) {
		this.witSureDesc = witSureDesc;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getTransferedUserName() {
		return transferedUserName;
	}

	public void setTransferedUserName(String transferedUserName) {
		this.transferedUserName = transferedUserName;
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
        BizWithdraw other = (BizWithdraw) that;
        return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getCustomer() == null ? other.getCustomer() == null : this.getCustomer().equals(other.getCustomer()))
            && (this.getBank() == null ? other.getBank() == null : this.getBank().equals(other.getBank()))
            && (this.getAccountId() == null ? other.getAccountId() == null : this.getAccountId().equals(other.getAccountId()))
            && (this.getTransferedTime() == null ? other.getTransferedTime() == null : this.getTransferedTime().equals(other.getTransferedTime()))
            && (this.getAuditTime() == null ? other.getAuditTime() == null : this.getAuditTime().equals(other.getAuditTime()))
            && (this.getApplyTime() == null ? other.getApplyTime() == null : this.getApplyTime().equals(other.getApplyTime()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getCost() == null ? other.getCost() == null : this.getCost().equals(other.getCost()))
            && (this.getFee() == null ? other.getFee() == null : this.getFee().equals(other.getFee()))
            && (this.getBankType() == null ? other.getBankType() == null : this.getBankType().equals(other.getBankType()))
            && (this.getUserId() == null ? other.getUserId() == null : this.getUserId().equals(other.getUserId()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getAuditStatus() == null ? other.getAuditStatus() == null : this.getAuditStatus().equals(other.getAuditStatus()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastUpdateUser() == null ? other.getLastUpdateUser() == null : this.getLastUpdateUser().equals(other.getLastUpdateUser()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()))
            && (this.getDescPromptId() == null ? other.getDescPromptId() == null : this.getDescPromptId().equals(other.getDescPromptId()))
        	&& (this.getWitSureDesc() == null ? other.getWitSureDesc() == null : this.getWitSureDesc().equals(other.getWitSureDesc()))
            && (this.getWitDesc() == null ? other.getWitDesc() == null : this.getWitDesc().equals(other.getWitDesc()));
        	
        
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getCustomer() == null) ? 0 : getCustomer().hashCode());
        result = prime * result + ((getBank() == null) ? 0 : getBank().hashCode());
        result = prime * result + ((getAccountId() == null) ? 0 : getAccountId().hashCode());
        result = prime * result + ((getTransferedTime() == null) ? 0 : getTransferedTime().hashCode());
        result = prime * result + ((getAuditTime() == null) ? 0 : getAuditTime().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getCost() == null) ? 0 : getCost().hashCode());
        result = prime * result + ((getFee() == null) ? 0 : getFee().hashCode());
        result = prime * result + ((getBankType() == null) ? 0 : getBankType().hashCode());
        result = prime * result + ((getUserId() == null) ? 0 : getUserId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getAuditStatus() == null) ? 0 : getAuditStatus().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        result = prime * result + ((getApplyTime() == null) ? 0 : getApplyTime().hashCode());
        result = prime * result + ((getWitDesc() == null) ? 0 : getWitDesc().hashCode());
        result = prime * result + ((getDescPromptId() == null) ? 0 : getDescPromptId().hashCode());
        result = prime * result + ((getWitSureDesc() == null) ? 0 : getWitSureDesc().hashCode());
        return result;
    }

	public String getTransferedTime() {
		return transferedTime;
	}

	public void setTransferedTime(String transferedTime) {
		this.transferedTime = transferedTime;
	}

	public String getTransferedUserId() {
		return transferedUserId;
	}

	public void setTransferedUserId(String transferedUserId) {
		this.transferedUserId = transferedUserId;
	}

	public String getRecOrderNo() {
		return recOrderNo;
	}

	public void setRecOrderNo(String recOrderNo) {
		this.recOrderNo = recOrderNo;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCustWitDesc() {
		return custWitDesc;
	}

	public void setCustWitDesc(String custWitDesc) {
		this.custWitDesc = custWitDesc;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getBankId() {
		return bankId;
	}

	public void setBankId(String bankId) {
		this.bankId = bankId;
	}

}