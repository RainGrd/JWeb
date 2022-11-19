/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 自动投标信息的反馈对象
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年11月23日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.model.ptp.autobidding;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br> 
 * 自动投标信息的反馈对象
 * @author  Lin Xu
 * @date    2015年12月8日
 * @version v1.0.0
 */
public class AutoBiddingQueueRemarks extends BaseEntity {

	private static final long serialVersionUID = -7848899371823039293L;
	
	//主键id
	private String pid;
	//客户ID（t_cust_customer.pid）
    private String customerId;
    //自动投标id(t_auto_bidding.pid)
    private String autoBiddingId;
    //借款ID(t_biz_customer.pid)
    private String borrowId;
    //自动投标结果状态（1.成功投标  2.未能匹配  3.数据异常）
    private Integer autoResultStatus;
    //扩展1
    private String extfield1;
   // 扩展2
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
    //数据状态
    private String status;
    //乐观锁信息
    private String version;
    //投标原因说明
    private String description;

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

    public String getAutoBiddingId() {
        return autoBiddingId;
    }

    public void setAutoBiddingId(String autoBiddingId) {
        this.autoBiddingId = autoBiddingId == null ? null : autoBiddingId.trim();
    }

    public String getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(String borrowId) {
        this.borrowId = borrowId == null ? null : borrowId.trim();
    }

    public Integer getAutoResultStatus() {
        return autoResultStatus;
    }

    public void setAutoResultStatus(Integer autoResultStatus) {
        this.autoResultStatus = autoResultStatus;
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
        AutoBiddingQueueRemarks other = (AutoBiddingQueueRemarks) that;
        return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getCustomerId() == null ? other.getCustomerId() == null : this.getCustomerId().equals(other.getCustomerId()))
            && (this.getAutoBiddingId() == null ? other.getAutoBiddingId() == null : this.getAutoBiddingId().equals(other.getAutoBiddingId()))
            && (this.getBorrowId() == null ? other.getBorrowId() == null : this.getBorrowId().equals(other.getBorrowId()))
            && (this.getAutoResultStatus() == null ? other.getAutoResultStatus() == null : this.getAutoResultStatus().equals(other.getAutoResultStatus()))
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
        result = prime * result + ((getAutoBiddingId() == null) ? 0 : getAutoBiddingId().hashCode());
        result = prime * result + ((getBorrowId() == null) ? 0 : getBorrowId().hashCode());
        result = prime * result + ((getAutoResultStatus() == null) ? 0 : getAutoResultStatus().hashCode());
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
}