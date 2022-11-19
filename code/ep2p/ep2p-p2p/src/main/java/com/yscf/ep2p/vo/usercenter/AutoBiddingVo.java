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
package com.yscf.ep2p.vo.usercenter;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Description：<br> 
 * 自动投标列表信息Vo对象
 * @author  Lin Xu
 * @date    2015年12月8日
 * @version v1.0.0
 */
public class AutoBiddingVo implements Serializable {
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
    //创建人
    private String createUser;
    //最后更新人
    private String lastUpdateUser;
    

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

    

    public String getCreateUser() {
        return createUser;
    }

    public void setCreateUser(String createUser) {
        this.createUser = createUser == null ? null : createUser.trim();
    }


    public String getLastUpdateUser() {
        return lastUpdateUser;
    }

    public void setLastUpdateUser(String lastUpdateUser) {
        this.lastUpdateUser = lastUpdateUser == null ? null : lastUpdateUser.trim();
    }
}