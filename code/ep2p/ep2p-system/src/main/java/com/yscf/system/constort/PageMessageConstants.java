/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 页面消息提示语接口
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月24日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.system.constort;

/**
 * Description：<br> 
 * 页面消息提示语接口
 * @author  Lin Xu
 * @date    2015年9月24日
 * @version v1.0.0
 */
public interface PageMessageConstants {
	/**
	 * 公共提示语
	 */
	public final static String COMMON_MESSAGE_EXCPTION = "操作出现异常，请联系系统管理员";
	public final static String COMMON_MESSAGE_FAIL = "操作失败";
	public final static String COMMON_MESSAGE_SUCCESS = "操作成功";
	
	/**
	 * 协议内容模板管理
	 */
	public final static String AGREEMENT_TEMP_ADD_SUCCESS = "协议模板添加成功";
	public final static String AGREEMENT_TEMP_EIDT_SUCCESS = "协议模板修改成功";
	public final static String AGREEMENT_TEMP_ADD_FAIL = "协议模板为空";
	public final static String AGREEMENT_TEMP_ACTIVE = "协议模板已启用";
	public final static String AGREEMENT_TEMP_DISABLE = "协议模板已禁用";
	
	/**
	 * 协议内容提示语
	 */
	public final static String AGREEMENT_CONTEXT_NOTNULL = "协议模板内容不能为空";
	public final static String AGREEMENT_CONTEXT_ADD_SUCCESS = "协议模板内容添加成功";
	public final static String AGREEMENT_CONTEXT_EIDT_SUCCESS = "协议模板内容编辑成功";
	public final static String AGREEMENT_CONTEXT_EIDT_FAIL = "协议模板内容编辑失败";
	public final static String AGREEMENT_CONTEXT_ACTIVE = "协议模板内容已启用";
	public final static String AGREEMENT_CONTEXT_DISABLE = "协议模板内容已禁用";
	
	/**
	 * 合同信息数据
	 */
	public final static String CONTRACT_MANAGE_SUCCESS = "协议信息保存成功";
	public final static String CONTRACT_CONTEXT_ACTIVE = "协议模板已启用";
	public final static String CONTRACT_CONTEXT_DISABLE = "协议模板已禁用";
	public final static String CONTRACT_UPDATE_SUCCESS = "协议信息修改成功";
	public final static String CONTRACT_NOTFOUND_INFO = "协议信息不存在";
	public final static String CONTRACT_UPFILE_SUCCESS = "协议上传成功";
	public final static String CONTRACT_UPFILE_SAVE_FAIL = "协议中的文件保存失败";
	
}


