package com.yscf.core.model.system;

import com.achievo.framework.entity.BaseEntity;

public class SysButton extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2197269891914656121L;

	private String pid;

    private String buttonName; //按钮名称

    private String buttonCode; //按钮编码

    private String buttonIco; //按钮图标

    private String status;	//状态
    
    private String menuButtonId; //菜单按钮中间表Id

    private String createUser;

    private String createTime;

    private String lastUpdateUser;

    private String lastUpdateTime;

    private String version;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getButtonName() {
        return buttonName;
    }

    public void setButtonName(String buttonName) {
        this.buttonName = buttonName == null ? null : buttonName.trim();
    }

    public String getButtonCode() {
        return buttonCode;
    }

    public void setButtonCode(String buttonCode) {
        this.buttonCode = buttonCode == null ? null : buttonCode.trim();
    }

    public String getButtonIco() {
        return buttonIco;
    }

    public void setButtonIco(String buttonIco) {
        this.buttonIco = buttonIco == null ? null : buttonIco.trim();
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
        SysButton other = (SysButton) that;
        return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getButtonName() == null ? other.getButtonName() == null : this.getButtonName().equals(other.getButtonName()))
            && (this.getButtonCode() == null ? other.getButtonCode() == null : this.getButtonCode().equals(other.getButtonCode()))
            && (this.getButtonIco() == null ? other.getButtonIco() == null : this.getButtonIco().equals(other.getButtonIco()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastUpdateUser() == null ? other.getLastUpdateUser() == null : this.getLastUpdateUser().equals(other.getLastUpdateUser()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()))
            && (this.getVersion() == null ? other.getVersion() == null : this.getVersion().equals(other.getVersion()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getButtonName() == null) ? 0 : getButtonName().hashCode());
        result = prime * result + ((getButtonCode() == null) ? 0 : getButtonCode().hashCode());
        result = prime * result + ((getButtonIco() == null) ? 0 : getButtonIco().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        return result;
    }

	public String getMenuButtonId() {
		return menuButtonId;
	}

	public void setMenuButtonId(String menuButtonId) {
		this.menuButtonId = menuButtonId;
	}
}