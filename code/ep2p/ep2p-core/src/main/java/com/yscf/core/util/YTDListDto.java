package com.yscf.core.util;

import java.io.Serializable;

/**
 * Description：<br> 
 * 年、月、日 DTO  用做 按年 按月 按日 统计数据时  数量的点击曲线图
 * @author  JingYu.Dai
 * @date    2015年11月4日
 * @version v1.0.0
 */
public class YTDListDto  implements Serializable  {

	private static final long serialVersionUID = -6309193682958714723L;
	/**
	 * 列名称
	 */
	private String name;
	/**
	 * 数据集
	 */
	private int[] data;
	
	public YTDListDto( String name,int[] data){
		this.name = name;
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int[] getData() {
		return data;
	}

	public void setData(int[] data) {
		this.data = data;
	}

}