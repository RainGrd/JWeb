/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
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
 * Description：任务常量
 * @author  JingYu.Dai
 * @date    2015年10月8日
 * @version v1.0.0
 */
public class TaskConstant {

	/**
	 * 活动审核任务	对应数据字典表t_sys_dictionary.dict_code
	 */
	public static String ACTIVITY_CHECK_TASK = "ACTIVITY_CHECK_TASK";
	
	/**
	 * 财务审核任务 	对应数据字典表t_sys_dictionary.dict_code
	 */
	public static String FINANCE_CHECK_TASK = "FINANCE_CHECK_TASK"; 
	
	/**
	 * 业务审核任务	对应数据字典表t_sys_dictionary.dict_code
	 */
	public static String BUSINESS_CHECK_TASK = "BUSINESS_CHECK_TASK";
	
	/**
	 * 客户审核任务	对应数据字典表t_sys_dictionary.dict_code
	 */
	public static String CLIENT_CHECK_TASK = "CLIENT_CHECK_TASK";
	
	/*****************************活动审核任务->子任务**********************************/

	
	/**
	 * 活动审核任务->待发布活动		对应数据字典表t_sys_dictionary_content.dict_cont_code
	 */
	public static String ACTIVITY_TO_LAUNCH = "1";
	
	/**
	 * 活动审核任务->进行中活动	对应数据字典表t_sys_dictionary_content.dict_cont_code
	 */
	public static String ACTIVITY_IN_THE_ACTIVITY = "2";
	
	/**
	 * 活动审核任务->今日新帖		对应数据字典表t_sys_dictionary_content.dict_cont_code
	 */
	public static String ACTIVITY_TODAY_UPDATES = "3";
	
	/**
	 * 活动审核任务->最新评论		对应数据字典表t_sys_dictionary_content.dict_cont_code
	 */
	public static String ACTIVITY_COMMENTS = "4";
	
	
	/****************************财务审核任务->子任务**********************************/
	
	/**
	 * 财务审核任务->转账确认		对应数据字典表t_sys_dictionary_content.dict_cont_code
	 */
	public static String FINANCE_TRANSFER_CONFIRMATION = "3";
	
	/**
	 * 财务审核任务->充值审核		对应数据字典表t_sys_dictionary_content.dict_cont_code
	 */
	public static String FINANCE_RECHARGE = "2";
	
	/**
	 * 财务审核任务->提现申请审核		对应数据字典表t_sys_dictionary_content.dict_cont_code
	 */
	public static String FINANCE_WITHDRAW = "1";
	
	/****************************业务审核任务->子任务**********************************/
	
	/**
	 * 业务审核任务->借款担保初审		对应数据字典表t_sys_dictionary_content.dict_cont_code
	 */
	public static String BUSINESS_LOAN_GUARANTEE = "1";
	
	/**
	 * 业务审核任务->借款借前审核		对应数据字典表t_sys_dictionary_content.dict_cont_code
	 */
	public static String BUSINESS_BEFORE_BORROWING = "2";
	
	/**
	 * 业务审核任务->项目发布		对应数据字典表t_sys_dictionary_content.dict_cont_code
	 */
	public static String BUSINESS_PROJECT_RELEASE = "3";
	
	/**
	 * 业务审核任务->下个月待还款		对应数据字典表t_sys_dictionary_content.dict_cont_code
	 */
	public static String BUSINESS_NEXT_MONTH_REPAYMENT = "4";
	
	/**
	 * 业务审核任务->近15天转让债权		对应数据字典表t_sys_dictionary_content.dict_cont_code
	 */
	public static String BUSINESS_ASSIGNED_CLAIM = "5";
	
	/*******************************客户审核任务->子任务************************************/
	
	/**
	 * 客户审核任务->实名认证		对应数据字典表t_sys_dictionary_content.dict_cont_code
	 */
	public static String CLIENT_CHECK_CERTIFICATION = "1";
	
	/**
	 * 客户审核任务->VIP生日		对应数据字典表t_sys_dictionary_content.dict_cont_code
	 */
	public static String CLIENT_CHECK_VIP_BIRTHDAY = "2";
}


