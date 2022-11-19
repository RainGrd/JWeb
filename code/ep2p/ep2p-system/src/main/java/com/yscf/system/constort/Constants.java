/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 数据字典页面控制数据交互类
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月7日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.system.constort;

/**
 * Description：常量类型
 * @author  JingYu.Dai
 * @date    2015年9月8日
 * @version v1.0.0
 */
public class Constants {
	
	public static final int DEFAULT_PAGE_SIZE = 5;
	public static final String DEFAULT_PAGE_NAME = "page";
	public static final String CONTEXT_PATH = "ctx";
	public static final String SERVER_IMG_PATH = "sip";

	public static final String LOGIN_SESSION_DATANAME = "users";
	public static final String LOGIN_URL = "login";
	public static final String LOGIN_SUCCESS_URL = "index";
	public static final String LOGIN_LOGIN_OUT_URL = "loginout";
	public static final String LOGIN_MSG = "loginMsg";
	public static final String USERNAME_IS_NULL = "用户名为空!";
	public static final String LOGIN_IS_EXIST = "该用户已登录!";
	public static final String UNKNOWN_SESSION_EXCEPTION = "异常会话!";
	public static final String UNKNOWN_ACCOUNT_EXCEPTION = "账号错误!";
	public static final String INCORRECT_CREDENTIALS_EXCEPTION = "密码错误!";
	public static final String LOCKED_ACCOUNT_EXCEPTION = "账号已被锁定，请与系统管理员联系!";
	public static final String INCORRECT_CAPTCHA_EXCEPTION = "验证码错误!";
	public static final String AUTHENTICATION_EXCEPTION = "您没有授权!";
	public static final String UNKNOWN_EXCEPTION = "出现未知异常,请与系统管理员联系!";
	public static final String TREE_GRID_ADD_STATUS = "add";
	public static final String POST_DATA_SUCCESS = "数据更新成功!";
	public static final String POST_DATA_FAIL = "提交失败了!";
	public static final String GET_SQL_LIKE = "%";
	public static final String IS_FUNCTION = "F";
	public static final String PERSISTENCE_STATUS = "A";
	public static final String PERSISTENCE_DELETE_STATUS = "I";
	public static final String SYSTEM_ADMINISTRATOR = "admin";
	public static final String NULL_STRING = "";
	public static final String IS_DOT = ".";
	public static final String HQL_LIKE = "like";
	public static final String TEXT_TYPE_PLAIN = "text/plain";
	public static final String TEXT_TYPE_HTML = "text/html";
	public static final String FUNCTION_TYPE_O = "O";
	public static final String TREE_STATUS_OPEN = "open";
	public static final String TREE_STATUS_CLOSED = "closed";
	public static final String IS_EXT_SUBMENU = " 或可能包含菜单!";
	public static final String SHIRO_USER = "shiroUser";
	public static final String CONTEXT_USER = "contextUser";
	public static final String CONTEXT_USER_ROLE_LIST = "contextUserRoleList";
	public static final String CONTEXT_USER_PERMISSION_LIST = "contextUserPermissionList";		
	public static final String LOGS_INSERT = "insert:";
	public static final String LOGS_INSERT_TEXT = "插入:";
	public static final String LOGS_INSERT_NAME = "insertLogs";
	public static final String LOGS_UPDATE = "update:";
	public static final String LOGS_UPDATE_TEXT = "更新:";
	public static final String LOGS_UPDATE_NAME = "updateLogs";
	public static final String LOGS_DELETE = "delete:";
	public static final String LOGS_DELETE_TEXT = "删除:";
	public static final String LOGS_DELETE_NAME = "deleteLogs";
	public static final String LOGS_TB_NAME = "Log";
	public static final String FILE_SUFFIX_SQL = ".sql";
	public static final String FILE_SUFFIX_ZIP = ".zip";

	// 合同类别

	public static final String CONTRACT_CATELOG_DK = "DK";// 贷款合同
	public static final String CONTRACT_CATELOG_BZ = "BZ";// 保证合同
	public static final String CONTRACT_CATELOG_DY = "DY";// 抵押合同
	public static final String CONTRACT_CATELOG_ZY = "ZY";// 质押合同
	public static final String CONTRACT_CATELOG_SX = "SX";// 授信合同
	public static final String CONTRACT_CATELOG_JJ = "JJ";// 借款借据
	public static final String CONTRACT_CATELOG_ZK = "ZK";// 展期合同
	public static final String CONTRACT_CATELOG_ZKBZ = "ZKBZ";// 展期保证
	public static final String CONTRACT_CATELOG_LLBG = "LLBG";// 利率变更
	
	//报表信息
	public static final String REPORT_TITLE_001 = "播放数";
	public static final String REPORT_TITLE_002 = "用户数";
	public static final String REPORT_TITLE_003 = "获赞数";
	public static final String REPORT_TITLE_004 = "转发数";
	public static final String REPORT_TITLE_005 = "用户量";

}
