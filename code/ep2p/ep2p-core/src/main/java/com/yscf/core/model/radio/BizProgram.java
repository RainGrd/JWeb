package com.yscf.core.model.radio;

import com.achievo.framework.entity.BaseEntity;

import java.util.Date;

public class BizProgram extends BaseEntity {
    /**
	 * 
	 */
	private static final long serialVersionUID = -5566227059614547804L;

	private String pid;

    private String programPlateId;//板块选择id

    private String programTitle;//节目标题

    private String pictureFileId;//配图ID,上传节目的路径

    private String appendixFileId;//节目配图ID

    private String programText;//节目配文

    private String programOrder;

    private String programLevel;

    private String programType;//节目类型
    
    private String programId;//节目id

    private Integer forwardNum;

    private Integer programDuration;

    private Integer listenNum;//收听数
    private Integer listenBeginNum;//收听开始数
    private Integer listenEndNum;//收听结束数

    private Integer praiseNum;//获赞数
    private Integer praiseBeginNum;//获赞开始数
    private Integer praiseEndNum;//获赞结束数

    private String publishTime;//发布时间
    private String publishBeginTime;//发布开始时间
    private String publishEndTime;//发布结束时间
    

    private String eliminateTime;//下架时间

    private String guest;

    private String predictTime;//预发布时间

    private String status;//状态选择

    private String createUser;

    private String createTime;

    private String lastUpdateUser;

    private Date lastUpdateTime;

    private String proDesc;
    private String uploadTime;//上传时间
    private String uploadBeginTime;//上传开始时间
    private String uploadEndTime;//上传结束时间
    private Integer pageNum; // 页码

	private Integer pageSize; // 条数
	
    //===========================扩展字段
    //排序的字段信息
    private String desc;
    //时间类型
    private String timetype;
    //对应后台天或者月的参数值进行的值
    private String ymonthDay;
    
    private String imageUrl;
    //客户id
    private String customerId;
    //是否点赞
    private Integer isPraise;

	public Integer getIsPraise() {
		return isPraise;
	}

	public void setIsPraise(Integer isPraise) {
		this.isPraise = isPraise;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public String getProgramId() {
		return programId;
	}

	public void setProgramId(String programId) {
		this.programId = programId;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public Integer getPageNum() {
		return pageNum;
	}

	public void setPageNum(Integer pageNum) {
		this.pageNum = pageNum;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public String getUploadBeginTime() {
		return uploadBeginTime;
	}

	public void setUploadBeginTime(String uploadBeginTime) {
		this.uploadBeginTime = uploadBeginTime;
	}

	public String getUploadEndTime() {
		return uploadEndTime;
	}

	public void setUploadEndTime(String uploadEndTime) {
		this.uploadEndTime = uploadEndTime;
	}

	public String getUploadTime() {
		return uploadTime;
	}

	public void setUploadTime(String uploadTime) {
		this.uploadTime = uploadTime;
	}

	public Integer getPraiseBeginNum() {
		return praiseBeginNum;
	}

	public void setPraiseBeginNum(Integer praiseBeginNum) {
		this.praiseBeginNum = praiseBeginNum;
	}

	public Integer getPraiseEndNum() {
		return praiseEndNum;
	}

	public void setPraiseEndNum(Integer praiseEndNum) {
		this.praiseEndNum = praiseEndNum;
	}

	public Integer getListenBeginNum() {
		return listenBeginNum;
	}

	public void setListenBeginNum(Integer listenBeginNum) {
		this.listenBeginNum = listenBeginNum;
	}

	public Integer getListenEndNum() {
		return listenEndNum;
	}

	public void setListenEndNum(Integer listenEndNum) {
		this.listenEndNum = listenEndNum;
	}

	public String getPublishBeginTime() {
		return publishBeginTime;
	}

	public void setPublishBeginTime(String publishBeginTime) {
		this.publishBeginTime = publishBeginTime;
	}

	public String getPublishEndTime() {
		return publishEndTime;
	}

	public void setPublishEndTime(String publishEndTime) {
		this.publishEndTime = publishEndTime;
	}
	public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid == null ? null : pid.trim();
    }

    public String getProgramPlateId() {
        return programPlateId;
    }

    public void setProgramPlateId(String programPlateId) {
        this.programPlateId = programPlateId == null ? null : programPlateId.trim();
    }

    public String getProgramTitle() {
        return programTitle;
    }

    public void setProgramTitle(String programTitle) {
        this.programTitle = programTitle == null ? null : programTitle.trim();
    }

    public String getPictureFileId() {
        return pictureFileId;
    }

    public void setPictureFileId(String pictureFileId) {
        this.pictureFileId = pictureFileId == null ? null : pictureFileId.trim();
    }

    public String getAppendixFileId() {
        return appendixFileId;
    }

    public void setAppendixFileId(String appendixFileId) {
        this.appendixFileId = appendixFileId == null ? null : appendixFileId.trim();
    }

    public String getProgramText() {
        return programText;
    }

    public void setProgramText(String programText) {
        this.programText = programText == null ? null : programText.trim();
    }

    public String getProgramOrder() {
        return programOrder;
    }

    public void setProgramOrder(String programOrder) {
        this.programOrder = programOrder == null ? null : programOrder.trim();
    }

    public String getProgramLevel() {
        return programLevel;
    }

    public void setProgramLevel(String programLevel) {
        this.programLevel = programLevel == null ? null : programLevel.trim();
    }

    public String getProgramType() {
        return programType;
    }

    public void setProgramType(String programType) {
        this.programType = programType == null ? null : programType.trim();
    }

    public Integer getForwardNum() {
        return forwardNum;
    }

    public void setForwardNum(Integer forwardNum) {
        this.forwardNum = forwardNum;
    }

    public Integer getProgramDuration() {
        return programDuration;
    }

    public void setProgramDuration(Integer programDuration) {
        this.programDuration = programDuration;
    }

    public Integer getListenNum() {
        return listenNum;
    }

    public void setListenNum(Integer listenNum) {
        this.listenNum = listenNum;
    }

    public Integer getPraiseNum() {
        return praiseNum;
    }

    public void setPraiseNum(Integer praiseNum) {
        this.praiseNum = praiseNum;
    }


	public String getPublishTime() {
		return publishTime;
	}

	public void setPublishTime(String publishTime) {
		this.publishTime = publishTime;
	}

	public String getGuest() {
        return guest;
    }

    public void setGuest(String guest) {
        this.guest = guest == null ? null : guest.trim();
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


    public String getPredictTime() {
		return predictTime;
	}

	public void setPredictTime(String predictTime) {
		this.predictTime = predictTime;
	}

	public String getEliminateTime() {
		return eliminateTime;
	}

	public void setEliminateTime(String eliminateTime) {
		this.eliminateTime = eliminateTime;
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

    public Date getLastUpdateTime() {
        return lastUpdateTime;
    }

    public void setLastUpdateTime(Date lastUpdateTime) {
        this.lastUpdateTime = lastUpdateTime;
    }

    public String getProDesc() {
        return proDesc;
    }

    public void setProDesc(String proDesc) {
        this.proDesc = proDesc == null ? null : proDesc.trim();
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
        BizProgram other = (BizProgram) that;
        return (this.getPid() == null ? other.getPid() == null : this.getPid().equals(other.getPid()))
            && (this.getProgramPlateId() == null ? other.getProgramPlateId() == null : this.getProgramPlateId().equals(other.getProgramPlateId()))
            && (this.getProgramTitle() == null ? other.getProgramTitle() == null : this.getProgramTitle().equals(other.getProgramTitle()))
            && (this.getPictureFileId() == null ? other.getPictureFileId() == null : this.getPictureFileId().equals(other.getPictureFileId()))
            && (this.getAppendixFileId() == null ? other.getAppendixFileId() == null : this.getAppendixFileId().equals(other.getAppendixFileId()))
            && (this.getProgramText() == null ? other.getProgramText() == null : this.getProgramText().equals(other.getProgramText()))
            && (this.getProgramOrder() == null ? other.getProgramOrder() == null : this.getProgramOrder().equals(other.getProgramOrder()))
            && (this.getProgramLevel() == null ? other.getProgramLevel() == null : this.getProgramLevel().equals(other.getProgramLevel()))
            && (this.getProgramType() == null ? other.getProgramType() == null : this.getProgramType().equals(other.getProgramType()))
            && (this.getForwardNum() == null ? other.getForwardNum() == null : this.getForwardNum().equals(other.getForwardNum()))
            && (this.getProgramDuration() == null ? other.getProgramDuration() == null : this.getProgramDuration().equals(other.getProgramDuration()))
            && (this.getListenNum() == null ? other.getListenNum() == null : this.getListenNum().equals(other.getListenNum()))
            && (this.getPraiseNum() == null ? other.getPraiseNum() == null : this.getPraiseNum().equals(other.getPraiseNum()))
            && (this.getPublishTime() == null ? other.getPublishTime() == null : this.getPublishTime().equals(other.getPublishTime()))
            && (this.getEliminateTime() == null ? other.getEliminateTime() == null : this.getEliminateTime().equals(other.getEliminateTime()))
            && (this.getGuest() == null ? other.getGuest() == null : this.getGuest().equals(other.getGuest()))
            && (this.getPredictTime() == null ? other.getPredictTime() == null : this.getPredictTime().equals(other.getPredictTime()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()))
            && (this.getCreateUser() == null ? other.getCreateUser() == null : this.getCreateUser().equals(other.getCreateUser()))
            && (this.getCreateTime() == null ? other.getCreateTime() == null : this.getCreateTime().equals(other.getCreateTime()))
            && (this.getLastUpdateUser() == null ? other.getLastUpdateUser() == null : this.getLastUpdateUser().equals(other.getLastUpdateUser()))
            && (this.getLastUpdateTime() == null ? other.getLastUpdateTime() == null : this.getLastUpdateTime().equals(other.getLastUpdateTime()))
            && (this.getProDesc() == null ? other.getProDesc() == null : this.getProDesc().equals(other.getProDesc()));
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getPid() == null) ? 0 : getPid().hashCode());
        result = prime * result + ((getProgramPlateId() == null) ? 0 : getProgramPlateId().hashCode());
        result = prime * result + ((getProgramTitle() == null) ? 0 : getProgramTitle().hashCode());
        result = prime * result + ((getPictureFileId() == null) ? 0 : getPictureFileId().hashCode());
        result = prime * result + ((getAppendixFileId() == null) ? 0 : getAppendixFileId().hashCode());
        result = prime * result + ((getProgramText() == null) ? 0 : getProgramText().hashCode());
        result = prime * result + ((getProgramOrder() == null) ? 0 : getProgramOrder().hashCode());
        result = prime * result + ((getProgramLevel() == null) ? 0 : getProgramLevel().hashCode());
        result = prime * result + ((getProgramType() == null) ? 0 : getProgramType().hashCode());
        result = prime * result + ((getForwardNum() == null) ? 0 : getForwardNum().hashCode());
        result = prime * result + ((getProgramDuration() == null) ? 0 : getProgramDuration().hashCode());
        result = prime * result + ((getListenNum() == null) ? 0 : getListenNum().hashCode());
        result = prime * result + ((getPraiseNum() == null) ? 0 : getPraiseNum().hashCode());
        result = prime * result + ((getPublishTime() == null) ? 0 : getPublishTime().hashCode());
        result = prime * result + ((getEliminateTime() == null) ? 0 : getEliminateTime().hashCode());
        result = prime * result + ((getGuest() == null) ? 0 : getGuest().hashCode());
        result = prime * result + ((getPredictTime() == null) ? 0 : getPredictTime().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        result = prime * result + ((getCreateUser() == null) ? 0 : getCreateUser().hashCode());
        result = prime * result + ((getCreateTime() == null) ? 0 : getCreateTime().hashCode());
        result = prime * result + ((getLastUpdateUser() == null) ? 0 : getLastUpdateUser().hashCode());
        result = prime * result + ((getLastUpdateTime() == null) ? 0 : getLastUpdateTime().hashCode());
        result = prime * result + ((getProDesc() == null) ? 0 : getProDesc().hashCode());
        return result;
    }

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getYmonthDay() {
		return ymonthDay;
	}

	public void setYmonthDay(String ymonthDay) {
		this.ymonthDay = ymonthDay;
	}

	public String getTimetype() {
		return timetype;
	}

	public void setTimetype(String timetype) {
		this.timetype = timetype;
	}

}