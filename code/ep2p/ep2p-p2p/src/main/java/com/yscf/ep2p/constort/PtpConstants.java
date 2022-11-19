/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 静态常量值
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年11月12日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.ep2p.constort;

/**
 * Description：<br> 
 * 静态常量值
 * @author  Lin Xu
 * @date    2015年11月12日
 * @version v1.0.0
 */
public class PtpConstants {

	public static final String CONSUMER = "consumer";
	
	/**********************************登录错误信息begin*******************************************/
	
	/**
	 * 登录名不存在
	 */
	public static final Integer LOGIN_ERROR_1 = 1;
	
	/**
	 * 密码错误
	 */
	public static final Integer LOGIN_ERROR_2 = 2;
	
	/**
	 * 用户已被锁定
	 */
	public static final Integer LOGIN_ERROR_3 = 3;
	
	/**
	 * 登录成功
	 */
	public static final Integer LOGIN_ERROR_4 = 4;
	
	/**
	 * 客户未被锁定
	 */
	public static final String IS_LOCKED_1 = "1";
	
	/**
	 * 客户被锁定
	 */
	public static final String IS_LOCKED_2 = "2";
	
	
	
	/**********************************登录错误信息end*******************************************/
	
	
	/**********************************个人中心左侧菜单信息begin************************************/
	public static final String CHECKMENU = "checkmenu";
	//账户总览
	public static final String USERCENTER_MENU_HOME = "USERMENU_HOME"; 
	//我的投资 
	public static final String USERCENTER_MENU_INVEST = "USERMENU_INVEST";
	//我的借款 
	public static final String USERCENTER_MENU_BORROW = "USERMENU_BORROW";
	//我的福利 
	public static final String USERCENTER_MENU_WELFARE = "USERMENU_WELFARE"; 
	//债权管理 
	public static final String USERCENTER_MENU_BONDS = "USERMENU_BONDS";
	//资金流水
	public static final String USERCENTER_MENU_FUNDDTL = "USERMENU_FUNDDTL";
	//充值提现
	public static final String USERCENTER_MENU_CHARGE = "USERMENU_CHARGE"; 
	//安全中心
	public static final String USERCENTER_MENU_SECURITY = "USERMENU_SECURITY";
	//消息中心
	public static final String USERCENTER_MENU_INFODTL = "USERMENU_INFODTL";
	//邀请有奖
	public static final String USERCENTER_MENU_INVITE = "USERMENU_INVITE"; 
	
	
	
	/**********************************个人中心左侧菜单信息end**************************************/
	
	/********************************** 短信发送start **************************************/
	
	/**
	 * 未注册
	 */
	public static final String NO_REGISTER = "NO_REGISTER";
	
	/**
	 * 已注册
	 */
	public static final String REGISTERED = "REGISTERED";
	
	/**
	 * 数据字典，短信信息key
	 *
	 */
	public static final String SMS_INFO = "SMS_INFO";
	
	/**
	 * 短信验证码存放session key值
	 */
	public static final String SMSCODE_SESSION = "SMSCODE_SESSION";
	
	/**
	 * 过期时长
	 */
	public static final String INVALID_TIME = "INVALID_TIME";
	
	/**
	 * 验证码模板tempCode
	 */
	public static final String IDENTIFYING_CODE = "IDENTIFYING_CODE";
	
	
	/********************************** 短信发送end **************************************/
}


