package com.yscf.api.vo.exchange;

/**
 * 
 * @ClassName : BorrowVo
 * @Description : 借款VO
 * @Author : Qing.Cai
 * @Date : 2016年1月27日 下午8:28:14
 */
public class BorrowVo {

	private String pid;// 主键
	/** 客户ID */
	private String customerId;
	/** 当前页码 */
	private Integer pageIndex;
	/** 每页显示数 */
	private Integer pageSize;

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