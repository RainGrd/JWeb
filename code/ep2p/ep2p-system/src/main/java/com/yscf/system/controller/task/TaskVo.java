/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * TODO
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年10月8日     JingYu.Dai		Initial Version.
 *************************************************************************
 */
package com.yscf.system.controller.task;


/**
 * Description：任务Vo
 * @author  JingYu.Dai
 * @date    2015年10月8日
 * @version v1.0.0
 */
public class TaskVo { 
	
	private String taskName;//任务名称
	
	private Integer taskCount;//任务记录条数
	
	/*
	 * 对应任务常量类（TaskConstant） 对应数据字典表t_sys_dictionary_content.dict_cont_code列值
	 * 用于前端判断是哪种任务类型
	 */
	private String taskType;
	
	/*
	 * 对应任务常量类（TaskConstant） 对应数据字典表t_sys_dictionary.dict_code列值
	 * 用于前端判断是哪个任务模块
	 */
	private String taskModule;

	public String getTaskName() {
		return taskName;
	}

	public void setTaskName(String taskName) {
		this.taskName = taskName;
	}

	public Integer getTaskCount() {
		return taskCount;
	}

	public void setTaskCount(Integer taskCount) {
		this.taskCount = taskCount;
	}

	public String getTaskType() {
		return taskType;
	}

	public void setTaskType(String taskType) {
		this.taskType = taskType;
	}

	public String getTaskModule() {
		return taskModule;
	}

	public void setTaskModule(String taskModule) {
		this.taskModule = taskModule;
	}
	
	
}


