package com.yscf.core.model.system;

import java.util.Date;
import java.util.List;

import com.achievo.framework.entity.BaseEntity;

public class SysMenu extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 3749717765680927697L;

	private String pid;
	
	private Integer menuCode; //菜单编码

    private String parentAuthId; //父级ID自关联
    
    private String parentMenuName; //父级菜单名称

    private String menuName; //菜单名称

    private String menuVisible; //菜单可见性

    private String menuUrl; //菜单URL

    private String menuDesc; //菜单描述

    private Integer menuOrder; //菜单顺序号
    
    private Integer menuLevel; //菜单层级

    private String menuNameEn; //菜单英文名

    private String isLeaf;	//是否是叶子节点

    private String menuIco;	//菜单图标

    private String status;

    private String createUser;

    private Date createTime;

    private String lastUpdateUser;

    private Date lastUpdateTime;

    private String version;
    
    private List<SysButton> buttons; //按钮列表

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getParentAuthId() {
        return parentAuthId;
    }

    public void setParentAuthId(String parentAuthId) {
        this.parentAuthId = parentAuthId == null ? null : parentAuthId.trim();
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName == null ? null : menuName.trim();
    }

    public String getMenuVisible() {
        return menuVisible;
    }

    public void setMenuVisible(String menuVisible) {
        this.menuVisible = menuVisible == null ? null : menuVisible.trim();
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl == null ? null : menuUrl.trim();
    }

    public String getMenuDesc() {
        return menuDesc;
    }

    public void setMenuDesc(String menuDesc) {
        this.menuDesc = menuDesc == null ? null : menuDesc.trim();
    }

    public Integer getMenuOrder() {
		return menuOrder;
	}

	public void setMenuOrder(Integer menuOrder) {
		this.menuOrder = menuOrder;
	}

	public Integer getMenuLevel() {
		return menuLevel;
	}

	public void setMenuLevel(Integer menuLevel) {
		this.menuLevel = menuLevel;
	}

	public String getMenuNameEn() {
        return menuNameEn;
    }

    public void setMenuNameEn(String menuNameEn) {
        this.menuNameEn = menuNameEn == null ? null : menuNameEn.trim();
    }

    public String getIsLeaf() {
        return isLeaf;
    }

    public void setIsLeaf(String isLeaf) {
        this.isLeaf = isLeaf == null ? null : isLeaf.trim();
    }

    public String getMenuIco() {
        return menuIco;
    }

    public void setMenuIco(String menuIco) {
        this.menuIco = menuIco == null ? null : menuIco.trim();
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
        SysMenu other = (SysMenu) that;
        return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getParentAuthId() == null ? other.getParentAuthId() == null : this.getParentAuthId().equals(other.getParentAuthId()))
            && (this.getMenuName() == null ? other.getMenuName() == null : this.getMenuName().equals(other.getMenuName()))
            && (this.getMenuVisible() == null ? other.getMenuVisible() == null : this.getMenuVisible().equals(other.getMenuVisible()))
            && (this.getMenuUrl() == null ? other.getMenuUrl() == null : this.getMenuUrl().equals(other.getMenuUrl()))
            && (this.getMenuDesc() == null ? other.getMenuDesc() == null : this.getMenuDesc().equals(other.getMenuDesc()))
            && (this.getMenuOrder() == null ? other.getMenuOrder() == null : this.getMenuOrder().equals(other.getMenuOrder()))
            && (this.getMenuNameEn() == null ? other.getMenuNameEn() == null : this.getMenuNameEn().equals(other.getMenuNameEn()))
            && (this.getIsLeaf() == null ? other.getIsLeaf() == null : this.getIsLeaf().equals(other.getIsLeaf()))
            && (this.getMenuIco() == null ? other.getMenuIco() == null : this.getMenuIco().equals(other.getMenuIco()))
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
        result = prime * result + ((getParentAuthId() == null) ? 0 : getParentAuthId().hashCode());
        result = prime * result + ((getMenuName() == null) ? 0 : getMenuName().hashCode());
        result = prime * result + ((getMenuVisible() == null) ? 0 : getMenuVisible().hashCode());
        result = prime * result + ((getMenuUrl() == null) ? 0 : getMenuUrl().hashCode());
        result = prime * result + ((getMenuDesc() == null) ? 0 : getMenuDesc().hashCode());
        result = prime * result + ((getMenuOrder() == null) ? 0 : getMenuOrder().hashCode());
        result = prime * result + ((getMenuNameEn() == null) ? 0 : getMenuNameEn().hashCode());
        result = prime * result + ((getIsLeaf() == null) ? 0 : getIsLeaf().hashCode());
        result = prime * result + ((getMenuIco() == null) ? 0 : getMenuIco().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        return result;
    }

	public String getParentMenuName() {
		return parentMenuName;
	}

	public void setParentMenuName(String parentMenuName) {
		this.parentMenuName = parentMenuName;
	}

	public List<SysButton> getButtons() {
		return buttons;
	}

	public void setButtons(List<SysButton> buttons) {
		this.buttons = buttons;
	}

	public Integer getMenuCode() {
		return menuCode;
	}

	public void setMenuCode(Integer menuCode) {
		this.menuCode = menuCode;
	}

}