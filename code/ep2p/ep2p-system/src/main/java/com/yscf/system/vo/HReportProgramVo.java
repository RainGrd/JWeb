/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 节目单的各个数量的点击曲线图
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年10月29日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.system.vo;

import java.io.Serializable;

/**
 * Description：<br> 
 * 节目单的各个数量的点击曲线图
 * @author  Lin Xu
 * @date    2015年10月29日
 * @version v1.0.0
 */
public class HReportProgramVo implements Serializable {
	private static final long serialVersionUID = 6450692688094907042L;
	
	//列名称
	private String name;
	//数据集
	private Integer[] data;
	
	public HReportProgramVo(){
		
	}
	
	public HReportProgramVo( String name,Integer[] data){
		this.name = name;
		this.data = data;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer[] getData() {
		return data;
	}

	public void setData(Integer[] data) {
		this.data = data;
	}

}


