/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 累计已发放收益
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年11月23日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.model.task;

import com.achievo.framework.entity.BaseEntity;
import java.math.BigDecimal;

/**
 * Description：<br> 
 * 累计已发放收益
 * @author  Lin Xu
 * @date    2015年12月23日
 * @version v1.0.0
 */
public class GrantProfitReport extends BaseEntity {
	
	private static final long serialVersionUID = 8776559763174119958L;

	private String pid;

    private BigDecimal amount;

    private String usernum;

    private String ayear;

    private String amonth;

    private String crateTime;

    private String lastUpdateTime;

    private String status;

    private String version;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getUsernum() {
        return usernum;
    }

    public void setUsernum(String usernum) {
        this.usernum = usernum == null ? null : usernum.trim();
    }

    public String getAyear() {
        return ayear;
    }

    public void setAyear(String ayear) {
        this.ayear = ayear == null ? null : ayear.trim();
    }

    public String getAmonth() {
        return amonth;
    }

    public void setAmonth(String amonth) {
        this.amonth = amonth == null ? null : amonth.trim();
    }

    public String getCrateTime() {
        return crateTime;
    }

    public void setCrateTime(String crateTime) {
        this.crateTime = crateTime == null ? null : crateTime.trim();
    }

    public String getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(String lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime == null ? null : lastUpdateTime.trim();
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
        GrantProfitReport other = (GrantProfitReport) that;
        return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getAmount() == null ? other.getAmount() == null : this.getAmount().equals(other.getAmount()))
            && (this.getUsernum() == null ? other.getUsernum() == null : this.getUsernum().equals(other.getUsernum()))
            && (this.getAyear() == null ? other.getAyear() == null : this.getAyear().equals(other.getAyear()))
            && (this.getAmonth() == null ? other.getAmonth() == null : this.getAmonth().equals(other.getAmonth()))
            && (this.getCrateTime() == null ? other.getCrateTime() == null : this.getCrateTime().equals(other.getCrateTime()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getAmount() == null) ? 0 : getAmount().hashCode());
        result = prime * result + ((getUsernum() == null) ? 0 : getUsernum().hashCode());
        result = prime * result + ((getAyear() == null) ? 0 : getAyear().hashCode());
        result = prime * result + ((getAmonth() == null) ? 0 : getAmonth().hashCode());
        result = prime * result + ((getCrateTime() == null) ? 0 : getCrateTime().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        return result;
    }
}