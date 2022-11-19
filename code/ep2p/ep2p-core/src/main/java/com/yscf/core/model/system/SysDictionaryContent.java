package com.yscf.core.model.system;

import com.achievo.framework.entity.BaseEntity;
import java.util.Date;

/**
 * 
 * Description：<br> 
 * 数据字典内容bean
 * @author  Yu.Zhang
 * @date    2015年9月10日
 * @version v1.0.0
 */
public class SysDictionaryContent extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String pid;
	
	/** 字典编码
	 */
    private String dictContCode;

    /**
     * 字典名称
     */
    private String dictContName;
    
	/** 字典编码模糊查询条件 只用于查询
	 */
    private String dictContCodeLike;

    /**
     * 字典名称模糊查询条件 只用于查询
     */
    private String dictContNameLike;
    
    /**
     * 描述
     */
    private String dictContDesc;
    
    /**
     * 展示顺序
     */
    private Integer dictContOrder;
    
    /**
     * 数据字典ID
     */
    private String dictId;
    
    /**
     * 状态
     */
    private String status;
    
    /**
     * 只用于查询内容列表时,判断父类是否允许修改 数据库不存在
     */
    private String isUpdate;

    private String createUser;

    private Date createTime;

    private String lastUpdateUser;

    private Date lastUpdateTime;
    
    /**
     * 版本号
     */
    private String version;
    
    public String getDictContCodeLike() {
		return dictContCodeLike;
	}

	public void setDictContCodeLike(String dictContCodeLike) {
		this.dictContCodeLike = dictContCodeLike;
	}

	public String getDictContNameLike() {
		return dictContNameLike;
	}

	public void setDictContNameLike(String dictContNameLike) {
		this.dictContNameLike = dictContNameLike;
	}

	public String getIsUpdate() {
		return isUpdate;
	}

	public void setIsUpdate(String isUpdate) {
		this.isUpdate = isUpdate;
	}

	public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getDictContCode() {
        return dictContCode;
    }

    public void setDictContCode(String dictContCode) {
        this.dictContCode = dictContCode == null ? null : dictContCode.trim();
    }

    public String getDictContName() {
        return dictContName;
    }

    public void setDictContName(String dictContName) {
        this.dictContName = dictContName == null ? null : dictContName.trim();
    }

    public String getDictContDesc() {
        return dictContDesc;
    }

    public void setDictContDesc(String dictContDesc) {
        this.dictContDesc = dictContDesc == null ? null : dictContDesc.trim();
    }

    public Integer getDictContOrder() {
        return dictContOrder;
    }

    public void setDictContOrder(Integer dictContOrder) {
        this.dictContOrder = dictContOrder ;
    }

    public String getDictId() {
        return dictId;
    }

    public void setDictId(String dictId) {
        this.dictId = dictId == null ? null : dictId.trim();
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
        SysDictionaryContent other = (SysDictionaryContent) that;
        return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getDictContCode() == null ? other.getDictContCode() == null : this.getDictContCode().equals(other.getDictContCode()))
            && (this.getDictContName() == null ? other.getDictContName() == null : this.getDictContName().equals(other.getDictContName()))
            && (this.getDictContDesc() == null ? other.getDictContDesc() == null : this.getDictContDesc().equals(other.getDictContDesc()))
            && (this.getDictContOrder() == null ? other.getDictContOrder() == null : this.getDictContOrder().equals(other.getDictContOrder()))
            && (this.getDictId() == null ? other.getDictId() == null : this.getDictId().equals(other.getDictId()))
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
        result = prime * result + ((getDictContCode() == null) ? 0 : getDictContCode().hashCode());
        result = prime * result + ((getDictContName() == null) ? 0 : getDictContName().hashCode());
        result = prime * result + ((getDictContDesc() == null) ? 0 : getDictContDesc().hashCode());
        result = prime * result + ((getDictContOrder() == null) ? 0 : getDictContOrder().hashCode());
        result = prime * result + ((getDictId() == null) ? 0 : getDictId().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        result = prime * result + ((getVersion() == null) ? 0 : getVersion().hashCode());
        return result;
    }
}