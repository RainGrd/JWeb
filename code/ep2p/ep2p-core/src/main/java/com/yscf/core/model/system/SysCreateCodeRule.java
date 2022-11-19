package com.yscf.core.model.system;

import java.sql.Timestamp;
import java.util.Date;

import com.achievo.framework.entity.BaseEntity;

/**
 * 
 * @ClassName	:  SysCreateCodeRule
 * @Description	:  编号生成规则javaBean
 * @Author 		:  Qing.Cai 
 * @Date		:  2015年9月17日 上午11:06:24
 */
public class SysCreateCodeRule extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	private String pid;

    private String rulePrefix;

    private String ruleYear;

    private String ruleMonth;

    private String ruleDay;

    private String ruleType;

    private Integer ruleOrder;

    private String ruleDesc;

    private String status;

    private String createUser;

    private Timestamp createTime;

    private String lastCreateUser;

    private Timestamp lastCreateTime;

    private String version;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getRulePrefix() {
        return rulePrefix;
    }

    public void setRulePrefix(String rulePrefix) {
        this.rulePrefix = rulePrefix == null ? null : rulePrefix.trim();
    }

    public String getRuleYear() {
        return ruleYear;
    }

    public void setRuleYear(String ruleYear) {
        this.ruleYear = ruleYear == null ? null : ruleYear.trim();
    }

    public String getRuleMonth() {
        return ruleMonth;
    }

    public void setRuleMonth(String ruleMonth) {
        this.ruleMonth = ruleMonth == null ? null : ruleMonth.trim();
    }

    public String getRuleDay() {
        return ruleDay;
    }

    public void setRuleDay(String ruleDay) {
        this.ruleDay = ruleDay == null ? null : ruleDay.trim();
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType == null ? null : ruleType.trim();
    }

    public Integer getRuleOrder() {
        return ruleOrder;
    }

    public void setRuleOrder(Integer ruleOrder) {
        this.ruleOrder = ruleOrder;
    }

    public String getRuleDesc() {
        return ruleDesc;
    }

    public void setRuleDesc(String ruleDesc) {
        this.ruleDesc = ruleDesc == null ? null : ruleDesc.trim();
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

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getLastCreateUser() {
        return lastCreateUser;
    }

    public void setLastCreateUser(String lastCreateUser) {
        this.lastCreateUser = lastCreateUser == null ? null : lastCreateUser.trim();
    }

    public Date getLastCreateTime() {
        return lastCreateTime;
    }

    public void setLastCreateTime(Timestamp lastCreateTime) {
        this.lastCreateTime = lastCreateTime;
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
        SysCreateCodeRule other = (SysCreateCodeRule) that;
        return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getRulePrefix() == null ? other.getRulePrefix() == null : this.getRulePrefix().equals(other.getRulePrefix()))
            && (this.getRuleYear() == null ? other.getRuleYear() == null : this.getRuleYear().equals(other.getRuleYear()))
            && (this.getRuleMonth() == null ? other.getRuleMonth() == null : this.getRuleMonth().equals(other.getRuleMonth()))
            && (this.getRuleDay() == null ? other.getRuleDay() == null : this.getRuleDay().equals(other.getRuleDay()))
            && (this.getRuleType() == null ? other.getRuleType() == null : this.getRuleType().equals(other.getRuleType()))
            && (this.getRuleOrder() == null ? other.getRuleOrder() == null : this.getRuleOrder().equals(other.getRuleOrder()))
            && (this.getRuleDesc() == null ? other.getRuleDesc() == null : this.getRuleDesc().equals(other.getRuleDesc()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastCreateUser() == null ? other.getLastCreateUser() == null : this.getLastCreateUser().equals(other.getLastCreateUser()))
            && (this.getLastCreateTime() == null ? other.getLastCreateTime() == null : this.getLastCreateTime().equals(other.getLastCreateTime()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getRulePrefix() == null) ? 0 : getRulePrefix().hashCode());
        result = prime * result + ((getRuleYear() == null) ? 0 : getRuleYear().hashCode());
        result = prime * result + ((getRuleMonth() == null) ? 0 : getRuleMonth().hashCode());
        result = prime * result + ((getRuleDay() == null) ? 0 : getRuleDay().hashCode());
        result = prime * result + ((getRuleType() == null) ? 0 : getRuleType().hashCode());
        result = prime * result + ((getRuleOrder() == null) ? 0 : getRuleOrder().hashCode());
        result = prime * result + ((getRuleDesc() == null) ? 0 : getRuleDesc().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastCreateUser() == null) ? 0 : getLastCreateUser().hashCode());
        result = prime * result + ((getLastCreateTime() == null) ? 0 : getLastCreateTime().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        return result;
    }
}