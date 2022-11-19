package com.yscf.core.model.business;

import com.achievo.framework.entity.BaseEntity;

/**
 * 
 * Description：<br> 
 * 借款审批表
 * @author  Yu.Zhang
 * @date    2015年9月24日
 * @version v1.0.0
 */
public class BizBorrowApprove extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String pid;

	/**
	 * 借款历史表ID
	 */
    private String borHisId;

    /**
     * 借款表ID
     */
    private String borId;

    /**
     * 审批节点  1  担保初审 2 贷前审核 只用于查询
     */
    private String approveNode;

    /**
     * 审批状态  1 申请中 2 担保初审 3 担保拒绝 4 借前审核 5 借前拒绝 6 审批结束
     */
    private String approveStatus;

    /**
     * 审批人
     */
    private String approveUser;

    /**
     * 审批时间
     */
    private String approveTime;

    /**
     * 担保审批意见
     */
    private String vouchOpinion;
    
    /**
     * 借前审批意见
     */
    private String lendOpinion;
    
    public String getVouchOpinion() {
		return vouchOpinion;
	}

	public void setVouchOpinion(String vouchOpinion) {
		this.vouchOpinion = vouchOpinion;
	}

	public String getLendOpinion() {
		return lendOpinion;
	}

	public void setLendOpinion(String lendOpinion) {
		this.lendOpinion = lendOpinion;
	}

	private String status;

    private String createUser;

    private String createTime;

    private String lastUpdateUser;

    private String lastUpdateTime;

    /**
     * 备注
     */
    private String borCondDesc;

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getBorHisId() {
        return borHisId;
    }

    public void setBorHisId(String borHisId) {
        this.borHisId = borHisId == null ? null : borHisId.trim();
    }

    public String getBorId() {
        return borId;
    }

    public void setBorId(String borId) {
        this.borId = borId == null ? null : borId.trim();
    }

    public String getApproveNode() {
        return approveNode;
    }

    public void setApproveNode(String approveNode) {
        this.approveNode = approveNode == null ? null : approveNode.trim();
    }

    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus == null ? null : approveStatus.trim();
    }

    public String getApproveUser() {
        return approveUser;
    }

    public void setApproveUser(String approveUser) {
        this.approveUser = approveUser == null ? null : approveUser.trim();
    }

    public String getApproveTime() {
        return approveTime;
    }

    public void setApproveTime(String approveTime) {
        this.approveTime = approveTime;
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

    public String getBorCondDesc() {
		return borCondDesc;
	}

	public void setBorCondDesc(String borCondDesc) {
		this.borCondDesc = borCondDesc;
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
        BizBorrowApprove other = (BizBorrowApprove) that;
        return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getBorHisId() == null ? other.getBorHisId() == null : this.getBorHisId().equals(other.getBorHisId()))
            && (this.getBorId() == null ? other.getBorId() == null : this.getBorId().equals(other.getBorId()))
            && (this.getApproveNode() == null ? other.getApproveNode() == null : this.getApproveNode().equals(other.getApproveNode()))
            && (this.getApproveStatus() == null ? other.getApproveStatus() == null : this.getApproveStatus().equals(other.getApproveStatus()))
            && (this.getApproveUser() == null ? other.getApproveUser() == null : this.getApproveUser().equals(other.getApproveUser()))
            && (this.getApproveTime() == null ? other.getApproveTime() == null : this.getApproveTime().equals(other.getApproveTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastUpdateUser() == null ? other.getLastUpdateUser() == null : this.getLastUpdateUser().equals(other.getLastUpdateUser()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()))
            && (this.getBorCondDesc() == null ? other.getBorCondDesc() == null : this.getBorCondDesc().equals(other.getBorCondDesc()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getBorHisId() == null) ? 0 : getBorHisId().hashCode());
        result = prime * result + ((getBorId() == null) ? 0 : getBorId().hashCode());
        result = prime * result + ((getApproveNode() == null) ? 0 : getApproveNode().hashCode());
        result = prime * result + ((getApproveStatus() == null) ? 0 : getApproveStatus().hashCode());
        result = prime * result + ((getApproveUser() == null) ? 0 : getApproveUser().hashCode());
        result = prime * result + ((getApproveTime() == null) ? 0 : getApproveTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        result = prime * result + ((getBorCondDesc() == null) ? 0 : getBorCondDesc().hashCode());
        return result;
    }
}