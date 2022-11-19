package com.yscf.core.model.financial;

import com.achievo.framework.entity.BaseEntity;
import java.util.Date;

/**
 * Description：充值渠道类
 * @author  JingYu.Dai
 * @date    2015年9月30日
 * @version v1.0.0
 */
public class BizRechargeSystemInfo extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 4502107302308491884L;

	private String pid;

    private String shopNo;  //商户凭证号
    
    private String recSysName;  //商户名称

    private String recSysKeys;	//秘钥

    private String recSysOrder;	//序号

    private String obligate1;	//预留字段1

    private String obligate2;	//预留字段2
    
    private String recPlaDesc;	//备注

    private String status;

    private String createUser;

    private Date createTime;

    private String lastUpdateUser;

    private Date lastUpdateTime;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getShopNo() {
        return shopNo;
    }

    public void setShopNo(String shopNo) {
        this.shopNo = shopNo == null ? null : shopNo.trim();
    }

    public String getRecSysKeys() {
        return recSysKeys;
    }

    public void setRecSysKeys(String recSysKeys) {
        this.recSysKeys = recSysKeys == null ? null : recSysKeys.trim();
    }

    public String getRecSysOrder() {
        return recSysOrder;
    }

    public void setRecSysOrder(String recSysOrder) {
        this.recSysOrder = recSysOrder == null ? null : recSysOrder.trim();
    }

    public String getObligate1() {
        return obligate1;
    }

    public void setObligate1(String obligate1) {
        this.obligate1 = obligate1 == null ? null : obligate1.trim();
    }

    public String getObligate2() {
        return obligate2;
    }

    public void setObligate2(String obligate2) {
        this.obligate2 = obligate2 == null ? null : obligate2.trim();
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
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

    public String getRecPlaDesc() {
        return recPlaDesc;
    }

    public void setRecPlaDesc(String recPlaDesc) {
        this.recPlaDesc = recPlaDesc == null ? null : recPlaDesc.trim();
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
        BizRechargeSystemInfo other = (BizRechargeSystemInfo) that;
        return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getShopNo() == null ? other.getShopNo() == null : this.getShopNo().equals(other.getShopNo()))
            && (this.getRecSysKeys() == null ? other.getRecSysKeys() == null : this.getRecSysKeys().equals(other.getRecSysKeys()))
            && (this.getRecSysOrder() == null ? other.getRecSysOrder() == null : this.getRecSysOrder().equals(other.getRecSysOrder()))
            && (this.getObligate1() == null ? other.getObligate1() == null : this.getObligate1().equals(other.getObligate1()))
            && (this.getObligate2() == null ? other.getObligate2() == null : this.getObligate2().equals(other.getObligate2()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastUpdateUser() == null ? other.getLastUpdateUser() == null : this.getLastUpdateUser().equals(other.getLastUpdateUser()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()))
            && (this.getRecPlaDesc() == null ? other.getRecPlaDesc() == null : this.getRecPlaDesc().equals(other.getRecPlaDesc()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getShopNo() == null) ? 0 : getShopNo().hashCode());
        result = prime * result + ((getRecSysKeys() == null) ? 0 : getRecSysKeys().hashCode());
        result = prime * result + ((getRecSysOrder() == null) ? 0 : getRecSysOrder().hashCode());
        result = prime * result + ((getObligate1() == null) ? 0 : getObligate1().hashCode());
        result = prime * result + ((getObligate2() == null) ? 0 : getObligate2().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        result = prime * result + ((getRecPlaDesc() == null) ? 0 : getRecPlaDesc().hashCode());
        return result;
    }

	public String getRecSysName() {
		return recSysName;
	}

	public void setRecSysName(String recSysName) {
		this.recSysName = recSysName;
	}
}