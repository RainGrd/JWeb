/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 自动投标列表信息对象
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年11月23日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.model.ptp.autobidding;

import java.math.BigDecimal;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br> 
 * 自动投标列表信息对象
 * @author  Lin Xu
 * @date    2015年12月8日
 * @version v1.0.0
 */
public class AutoBidding extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 7360371886029325832L;
	
	//主键id
	private String pid;
	//客户ID（t_cust_customer.pid）
    private String customerId;
    //投资金额-固定金额
    private BigDecimal amount;
    //投资金额-余额比率
    private Integer balanceratio;
    //投资期限-最短投资月
    private Integer minborrowmonth;
    //投资期限-最长投资月
    private Integer maxborrowmonth;
    //收益率-最小收益率
    private BigDecimal minborrowrate;
    //收益率-最大收益率
    private BigDecimal maxborrowrate;
    //借款类型（e计划、e首房、e抵押）
    private String borrowType;
    //自动投标状态( 0：未启用 、 1：启用)
    private Integer autostatus;
    //扩展一
    private String extfield1;
    //扩展二
    private String extfield2;
    //扩展3
    private String extfield3;
    //创建人
    private String createUser;
    //创建时间
    private String createTime;
    //最后更新人
    private String lastUpdateUser;
    //最后更新时间
    private String lastUpdateTime;
    //数据状态(默认为1，0 删除)
    private String status;
    //版本号
    private String version;
    //其他描述
    private String description;
    //排名
    private Integer ranking;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId == null ? null : customerId.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getBalanceratio() {
        return balanceratio;
    }

    public void setBalanceratio(Integer balanceratio) {
        this.balanceratio = balanceratio;
    }

    public Integer getMinborrowmonth() {
        return minborrowmonth;
    }

    public void setMinborrowmonth(Integer minborrowmonth) {
        this.minborrowmonth = minborrowmonth;
    }

    public Integer getMaxborrowmonth() {
        return maxborrowmonth;
    }

    public void setMaxborrowmonth(Integer maxborrowmonth) {
        this.maxborrowmonth = maxborrowmonth;
    }

    public BigDecimal getMinborrowrate() {
        return minborrowrate;
    }

    public void setMinborrowrate(BigDecimal minborrowrate) {
        this.minborrowrate = minborrowrate;
    }

    public BigDecimal getMaxborrowrate() {
        return maxborrowrate;
    }

    public void setMaxborrowrate(BigDecimal maxborrowrate) {
        this.maxborrowrate = maxborrowrate;
    }

    public String getBorrowType() {
        return borrowType;
    }

    public void setBorrowType(String borrowType) {
        this.borrowType = borrowType == null ? null : borrowType.trim();
    }

    public Integer getAutostatus() {
        return autostatus;
    }

    public void setAutostatus(Integer autostatus) {
        this.autostatus = autostatus;
    }

    public String getExtfield1() {
        return extfield1;
    }

    public void setExtfield1(String extfield1) {
        this.extfield1 = extfield1 == null ? null : extfield1.trim();
    }

    public String getExtfield2() {
        return extfield2;
    }

    public void setExtfield2(String extfield2) {
        this.extfield2 = extfield2 == null ? null : extfield2.trim();
    }

    public String getExtfield3() {
        return extfield3;
    }

    public void setExtfield3(String extfield3) {
        this.extfield3 = extfield3 == null ? null : extfield3.trim();
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status == null ? null : status.trim();
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version == null ? null : version.trim();
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
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
        AutoBidding other = (AutoBidding) that;
        return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getBalanceratio() == null ? other.getBalanceratio() == null : this.getBalanceratio().equals(other.getBalanceratio()))
            && (this.getMinborrowmonth() == null ? other.getMinborrowmonth() == null : this.getMinborrowmonth().equals(other.getMinborrowmonth()))
            && (this.getMaxborrowmonth() == null ? other.getMaxborrowmonth() == null : this.getMaxborrowmonth().equals(other.getMaxborrowmonth()))
            && (this.getMinborrowrate() == null ? other.getMinborrowrate() == null : this.getMinborrowrate().equals(other.getMinborrowrate()))
            && (this.getMaxborrowrate() == null ? other.getMaxborrowrate() == null : this.getMaxborrowrate().equals(other.getMaxborrowrate()))
            && (this.getBorrowType() == null ? other.getBorrowType() == null : this.getBorrowType().equals(other.getBorrowType()))
            && (this.getAutostatus() == null ? other.getAutostatus() == null : this.getAutostatus().equals(other.getAutostatus()))
            && (this.getExtfield1() == null ? other.getExtfield1() == null : this.getExtfield1().equals(other.getExtfield1()))
            && (this.getExtfield2() == null ? other.getExtfield2() == null : this.getExtfield2().equals(other.getExtfield2()))
            && (this.getExtfield3() == null ? other.getExtfield3() == null : this.getExtfield3().equals(other.getExtfield3()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastUpdateUser() == null ? other.getLastUpdateUser() == null : this.getLastUpdateUser().equals(other.getLastUpdateUser()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getCustomerId() == null) ? 0 : getCustomerId().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getBalanceratio() == null) ? 0 : getBalanceratio().hashCode());
        result = prime * result + ((getMinborrowmonth() == null) ? 0 : getMinborrowmonth().hashCode());
        result = prime * result + ((getMaxborrowmonth() == null) ? 0 : getMaxborrowmonth().hashCode());
        result = prime * result + ((getMinborrowrate() == null) ? 0 : getMinborrowrate().hashCode());
        result = prime * result + ((getMaxborrowrate() == null) ? 0 : getMaxborrowrate().hashCode());
        result = prime * result + ((getBorrowType() == null) ? 0 : getBorrowType().hashCode());
        result = prime * result + ((getAutostatus() == null) ? 0 : getAutostatus().hashCode());
        result = prime * result + ((getExtfield1() == null) ? 0 : getExtfield1().hashCode());
        result = prime * result + ((getExtfield2() == null) ? 0 : getExtfield2().hashCode());
        result = prime * result + ((getExtfield3() == null) ? 0 : getExtfield3().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        return result;
    }

	public Integer getRanking() {
		return ranking;
	}

	public void setRanking(Integer ranking) {
		this.ranking = ranking;
	}
}