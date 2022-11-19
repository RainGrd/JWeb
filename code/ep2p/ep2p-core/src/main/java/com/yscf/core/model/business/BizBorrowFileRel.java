package com.yscf.core.model.business;

import com.achievo.framework.entity.BaseEntity;

public class BizBorrowFileRel extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String pid;
	
	/**
	 * 文件ID
	 */
    private String fileId;

    /**
     * 借款id
     */
    private String borrowId;

    /**
     * 状态
     */
    private String status;

    private String createUser;

    private String createTime;

    private String lastUpdateUser;

    private String lastUpdateTime;

    /**
     * 文件描述
     */
    private String borFilRelDesc;

    /**
     * 文件类型 1 身份证+婚姻登记证明 2 个人征信报告 3 收入证明 4 近半年银行流水 5 社保查询单 6 房产证材料
     */
    private String borFileRelType;
    
    
    /**
	 * 文件名称	引用文件表中字段，只用于查询
	 */
    private String fileName;

    /**
     * 文件大小	引用文件表中字段，只用于查询
     */
    private String fileSize;

    /**
     * 文件类型	引用文件表中字段，只用于查询
     */
    private String fileType;

    /**
     * url　	引用文件表中字段，只用于查询
     */
    private String fileUrl;
    
    /**
     * 是否显示　引用文件表中字段，只用于查询
     */
    private String isShow;
    

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileSize() {
		return fileSize;
	}

	public void setFileSize(String fileSize) {
		this.fileSize = fileSize;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}

	public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId == null ? null : fileId.trim();
    }

    public String getBorrowId() {
        return borrowId;
    }

    public void setBorrowId(String borrowId) {
        this.borrowId = borrowId == null ? null : borrowId.trim();
    }

    public String getBorFileRelType() {
        return borFileRelType;
    }

    public void setBorFileRelType(String borFileRelType) {
        this.borFileRelType = borFileRelType == null ? null : borFileRelType.trim();
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

    public String getBorFilRelDesc() {
        return borFilRelDesc;
    }

    public void setBorFilRelDesc(String borFilRelDesc) {
        this.borFilRelDesc = borFilRelDesc == null ? null : borFilRelDesc.trim();
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
        BizBorrowFileRel other = (BizBorrowFileRel) that;
        return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getFileId() == null ? other.getFileId() == null : this.getFileId().equals(other.getFileId()))
            && (this.getBorrowId() == null ? other.getBorrowId() == null : this.getBorrowId().equals(other.getBorrowId()))
            && (this.getBorFileRelType() == null ? other.getBorFileRelType() == null : this.getBorFileRelType().equals(other.getBorFileRelType()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastUpdateUser() == null ? other.getLastUpdateUser() == null : this.getLastUpdateUser().equals(other.getLastUpdateUser()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()))
            && (this.getBorFilRelDesc() == null ? other.getBorFilRelDesc() == null : this.getBorFilRelDesc().equals(other.getBorFilRelDesc()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getFileId() == null) ? 0 : getFileId().hashCode());
        result = prime * result + ((getBorrowId() == null) ? 0 : getBorrowId().hashCode());
        result = prime * result + ((getBorFileRelType() == null) ? 0 : getBorFileRelType().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        result = prime * result + ((getBorFilRelDesc() == null) ? 0 : getBorFilRelDesc().hashCode());
        return result;
    }
}