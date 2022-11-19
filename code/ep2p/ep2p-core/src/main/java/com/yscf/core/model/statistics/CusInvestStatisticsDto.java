package com.yscf.core.model.statistics;

import com.achievo.framework.entity.BaseEntity;

/**
 * 
 * @ClassName : CusInvestStatisticsDto
 * @Description : 报表数据信息点DTO
 * @Author : Qing.Cai
 * @Date : 2015年11月6日 下午4:07:33
 */
public class CusInvestStatisticsDto extends BaseEntity {

	private static final long serialVersionUID = 789464623815691127L;

	private String name;// 名称

	private Integer y; // 人数

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getY() {
		return y;
	}

	public void setY(Integer y) {
		this.y = y;
	}

}