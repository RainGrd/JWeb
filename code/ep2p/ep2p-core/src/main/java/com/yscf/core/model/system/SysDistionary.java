package com.yscf.core.model.system;

import com.achievo.framework.entity.BaseEntity;
import java.util.Date;

/**
 * 
 * Description：数据字典bean  
 * TODO
 * @author  Yu.Zhang
 * @date    2015年9月7日
 * @version v1.0.0
 */
public class SysDistionary extends BaseEntity {
 
	
	private static final long serialVersionUID = -3110857708854082682L;

	private String pid;
	
	/**
	 * 数据字典名称
	 */
    private String dictName;

    /**
     * 描述
     */
    private String dictDesc;

    /**
     * 是否允许修改
     */
    private String isUpdate;

    /**
     * 数据字典code
     */
    private String dictCode;

    /**
     * 状态 0 禁用 1 启用
     */
    private String status;

    private String createUser;

    private Date createTime;

    private String lastUpdateUser;

    private Date lastUpdateTime;

    /**
     * 版本号
     */
    private String version;
    
    /**
     * 数据字典 name or code 数据查询条件 只用于查询
     */
    private String dictNameOrCode;
    
    /**
     * 数据字典名称模糊查询条件
     */
    private String dictNameLike;
    
    /**
     * 数据字典code模糊查询条件
     */
    private String dictCodeLike;
    
    
    public String getDictNameLike() {
		return dictNameLike;
	}

	public void setDictNameLike(String dictNameLike) {
		this.dictNameLike = dictNameLike;
	}

	public String getDictCodeLike() {
		return dictCodeLike;
	}

	public void setDictCodeLike(String dictCodeLike) {
		this.dictCodeLike = dictCodeLike;
	}

	public String getDictNameOrCode() {
		return dictNameOrCode;
	}

	public void setDictNameOrCode(String dictNameOrCode) {
		this.dictNameOrCode = dictNameOrCode;
	}

	public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getDictName() {
        return dictName;
    }

    public void setDictName(String dictName) {
        this.dictName = dictName == null ? null : dictName.trim();
    }

    public String getDictDesc() {
        return dictDesc;
    }

    public void setDictDesc(String dictDesc) {
        this.dictDesc = dictDesc == null ? null : dictDesc.trim();
    }

    public String getIsUpdate() {
        return isUpdate;
    }

    public void setIsUpdate(String isUpdate) {
        this.isUpdate = isUpdate == null ? null : isUpdate.trim();
    }

    public String getDictCode() {
        return dictCode;
    }

    public void setDictCode(String dictCode) {
        this.dictCode = dictCode == null ? null : dictCode.trim();
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
        SysDistionary other = (SysDistionary) that;
        return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getDictName() == null ? other.getDictName() == null : this.getDictName().equals(other.getDictName()))
            && (this.getDictDesc() == null ? other.getDictDesc() == null : this.getDictDesc().equals(other.getDictDesc()))
            && (this.getIsUpdate() == null ? other.getIsUpdate() == null : this.getIsUpdate().equals(other.getIsUpdate()))
            && (this.getDictCode() == null ? other.getDictCode() == null : this.getDictCode().equals(other.getDictCode()))
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
        result = prime * result + ((getDictName() == null) ? 0 : getDictName().hashCode());
        result = prime * result + ((getDictDesc() == null) ? 0 : getDictDesc().hashCode());
        result = prime * result + ((getIsUpdate() == null) ? 0 : getIsUpdate().hashCode());
        result = prime * result + ((getDictCode() == null) ? 0 : getDictCode().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        return result;
    }
}