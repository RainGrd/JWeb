package com.yscf.api.vo.exchange;

/**
 * 
 * @ClassName : ActivityVo
 * @Description : 活动VO
 * @Author : Qing.Cai
 * @Date : 2016年3月8日 下午4:08:46
 */
public class ActivityVo {

	private String pid;// 主键
	/** 客户ID */
	private String customerId;
	/** 当前页码 */
	private Integer pageIndex;
	/** 每页显示数 */
	private Integer pageSize;
	/** 红包活动ID */
	private String redpacketId;
	/** 加息劵活动ID */
	private String investAwardId;

	public String getInvestAwardId() {
		return investAwardId;
	}

	public void setInvestAwardId(String investAwardId) {
		this.investAwardId = investAwardId;
	}

	public String getRedpacketId() {
		return redpacketId;
	}

	public void setRedpacketId(String redpacketId) {
		this.redpacketId = redpacketId;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getCustomerId() {
		return customerId;
	}

	public void setCustomerId(String customerId) {
		this.customerId = customerId;
	}

	public Integer getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(Integer pageIndex) {
		this.pageIndex = pageIndex;
	}

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

}