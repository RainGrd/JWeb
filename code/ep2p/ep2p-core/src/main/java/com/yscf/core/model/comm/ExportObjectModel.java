/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 导出窗口信息对象
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年10月21日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.model.comm;

import com.achievo.framework.entity.BaseEntity;

/**
 * Description：<br> 
 * 导出窗口信息对象
 * @author  Lin Xu
 * @date    2015年10月21日
 * @version v1.0.0
 */
public class ExportObjectModel extends BaseEntity{

	private static final long serialVersionUID = 1951581178406161010L;
	//导出设置类型 1 2 3
	private Integer exportSet;
	//勾选的记录数
	private String[] checkrows;
	//填写的页数
	private Integer startCheckpage;
	private Integer endCheckpage;
	//选择的记录数据
	private Integer startCheckrecords;
	private Integer endCheckrecords;
	
	
	public Integer getExportSet() {
		return exportSet;
	}
	public void setExportSet(Integer exportSet) {
		this.exportSet = exportSet;
	}
	public String[] getCheckrows() {
		return checkrows;
	}
	public void setCheckrows(String[] checkrows) {
		this.checkrows = checkrows;
	}
	public Integer getStartCheckpage() {
		return startCheckpage;
	}
	public void setStartCheckpage(Integer startCheckpage) {
		this.startCheckpage = startCheckpage;
	}
	public Integer getStartCheckrecords() {
		return startCheckrecords;
	}
	public void setStartCheckrecords(Integer startCheckrecords) {
		this.startCheckrecords = startCheckrecords;
	}
	public Integer getEndCheckpage() {
		return endCheckpage;
	}
	public void setEndCheckpage(Integer endCheckpage) {
		this.endCheckpage = endCheckpage;
	}
	public Integer getEndCheckrecords() {
		return endCheckrecords;
	}
	public void setEndCheckrecords(Integer endCheckrecords) {
		this.endCheckrecords = endCheckrecords;
	}
	
}


