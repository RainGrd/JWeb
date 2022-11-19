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
package com.yscf.system.vo;

import java.io.Serializable;

/**
 * Description：<br> 
 * 导出窗口信息对象
 * @author  Lin Xu
 * @date    2015年10月21日
 * @version v1.0.0
 */
public class ExportObjectVo  implements Serializable{

	private static final long serialVersionUID = 1951581178406161010L;
	//导出设置类型 1 2 3
	private String exportSet;
	//勾选的记录数
	private String checkrows;
	//填写的页数
	private String checkpage;
	//填写的页数结束
	private String checkpageE;
	//选择的记录数据
	private String checkrecords;
	//选择的记录数据结束
	private String checkrecordsE;
	//每页显示条数
	private Integer rows;
	
	
	public String getExportSet() {
		return exportSet;
	}
	public void setExportSet(String exportSet) {
		this.exportSet = exportSet;
	}
	public String getCheckrows() {
		return checkrows;
	}
	public void setCheckrows(String checkrows) {
		this.checkrows = checkrows;
	}
	public String getCheckpage() {
		return checkpage;
	}
	public void setCheckpage(String checkpage) {
		this.checkpage = checkpage;
	}
	public String getCheckrecords() {
		return checkrecords;
	}
	public void setCheckrecords(String checkrecords) {
		this.checkrecords = checkrecords;
	}
	public Integer getRows() {
		return rows;
	}
	public void setRows(Integer rows) {
		this.rows = rows;
	}
	public String getCheckpageE() {
		return checkpageE;
	}
	public void setCheckpageE(String checkpageE) {
		this.checkpageE = checkpageE;
	}
	public String getCheckrecordsE() {
		return checkrecordsE;
	}
	public void setCheckrecordsE(String checkrecordsE) {
		this.checkrecordsE = checkrecordsE;
	}
	
}


