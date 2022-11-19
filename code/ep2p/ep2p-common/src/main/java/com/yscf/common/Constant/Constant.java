/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * e生财富 项目常量类
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月9日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.common.Constant;

import java.util.HashMap;
import java.util.Map;

/**
 * Description：<br>
 * e生财富 项目 常量类
 * 
 * @author Yu.Zhang
 * @date 2015年9月9日
 * @version v1.0.0
 */
public class Constant {
	
	public static Integer LOGIN_CACHE_TIME = 1800;
	
	/**
	 * 分配客服队列  存储在队列中的key
	 */
	public static String CUS_DISTRIBUTION_QUEUE = "CUS_DISTRIBUTION_QUEUE";
	
	/**
	 * 客服角色编码
	 */
	public static Integer SYS_USER_ROLE_CODE = 10025;
	
	/**
	 * 客服的客户数
	 */
	public static Integer SERVICE_USER_COUNT = 1000;

	// 公共路径值配置
	public static final String COM_BASEPATH = "basePath";
	public static final String COM_CLASSBASEPATH = "classbasePath";
	public static final String COM_EXPORTBASEPATH = "exportbasePath";
	public static final String COM_IMPORTBASEPATH = "importbasePath";

	// 公共静态参数
	public static final String CONTEXT_USER = "contextUser";

	/************************ 系统参数配置 *****************************/
	/**
	 * 密码长度 对用系统参数表（t_sys_params.param_key）
	 */
	public static final String PASSWORD_LENGTH = "PASSWORD_LENGTH";

	/**
	 * 默认的版本号为 1 
	 */
	public static final String PUBLIC_VERSION = "1";
	
	/**默认状态*/
	public static final String PUBLIC_STATUS = "1";

	// ---------------------------------------------------------

	/**
	 * 充值状态 未定义 状态标识
	 */
	public static final String REC_STATUS_UNDEFINED = "0";

	/**
	 * 充值资金待审核 状态标识
	 */
	public static final String REC_STATUS_REFERENDUM = "1";

	/**
	 * 充值资金同意 状态标识
	 */
	public static final String REC_STATUS_CONSENT = "2";

	/**
	 * 充值资金拒绝 状态标识
	 */
	public static final String REC_STATUS_REFUSE = "3";

	public static final String SERVLET_POSTFIX = "doPost.shtml";

	/**
	 * 提现管理审核状态 1：提现审核 提现管理审核状态 （1：提现审核、2：同意（转账确认）、3：提现拒绝、4：转账成功、5：转账失败）
	 */
	public static final String WITHDRAWAL_AUDIT = "1";
	/**
	 * 提现管理审核状态 2：同意（转账确认） 提现管理审核状态 （1：提现审核、2：同意（转账确认）、3：提现拒绝、4：转账成功、5：转账失败）
	 */
	public static final String WITHDRAWAL_TRANSFER_CONFIRMATION = "2";
	/**
	 * 提现管理审核状态 3：提现拒绝 提现管理审核状态 （1：提现审核、2：同意（转账确认）、3：提现拒绝、4：转账成功、5：转账失败）
	 */
	public static final String WITHDRAWAL_REFUSE = "3";
	/**
	 * 提现管理审核状态 4：转账成功 提现管理审核状态 （1：提现审核、2：同意（转账确认）、3：提现拒绝、4：转账成功、5：转账失败）
	 */
	public static final String WITHDRAWAL_TRANSFER_SUCCESS = "4";
	/**
	 * 提现管理审核状态 5：转账失败 提现管理审核状态 （1：提现审核、2：同意（转账确认）、3：提现拒绝、4：转账成功、5：转账失败）
	 */
	public static final String WITHDRAWAL_TRANSFER_FAILURE = "5";

	/**
	 * 数据禁用
	 */
	public static final String DISABLE = "2";

	/**
	 * 数据启用
	 */
	public static final String ACTIVATE = "1";

	/**
	 * 数据删除
	 */
	public static final String DELETE = "0";

	/**
	 * 分页显示条数
	 */
	public static final String ROWS = "rows";

	/**
	 * 分页当前页
	 */
	public static final String PAGE = "page";

	/**
	 * 借款审批状态 申请中
	 */
	public static final String BORROW_APPROVE_STATUS_1 = "1";

	/**
	 * 借款审批状态 担保初审
	 */
	public static final String BORROW_APPROVE_STATUS_2 = "2";

	/**
	 * 借款审批状态 担保拒绝
	 */
	public static final String BORROW_APPROVE_STATUS_3 = "3";

	/**
	 * 借款审批状态 借前审核
	 */
	public static final String BORROW_APPROVE_STATUS_4 = "4";

	/**
	 * 借款审批状态 借前拒绝
	 */
	public static final String BORROW_APPROVE_STATUS_5 = "5";

	/**
	 * 借款审批状态 借前同意
	 */
	public static final String BORROW_APPROVE_STATUS_6 = "6";

	/**
	 * 客户提交申请
	 */
	public static final String BORROW_NODE_STATUS_0 = "0";

	/**
	 * 担保初审
	 */
	public static final String BORROW_NODE_STATUS_1 = "1";

	/**
	 * 借前审核
	 */
	public static final String BORROW_NODE_STATUS_2 = "2";

	/**************************** 标的类型 开始 ******************************/
	/**
	 * 借款类型 e抵押
	 */
	public static final String BORROW_TYPE_1 = "1";

	/**
	 * 借款类型 e首房
	 */
	public static final String BORROW_TYPE_2 = "2";
	/**
	 * 借款类型 e计划
	 */
	public static final String BORROW_TYPE_3 = "3";
	/**
	 * 新手标
	 */
	public static final String BORROW_TYPE_4 = "4";
	/**
	 * 体验标
	 */
	public static final String BORROW_TYPE_5 = "5";

	/**************************** 标的类型 结束 ******************************/

	/**************************** 借款状态常量 开始 *********************************/

	/**
	 * 已撤销
	 */
	public static final String BORROW_STATUS_0 = "0";

	/**
	 * 待招标
	 */
	public static final String BORROW_STATUS_1 = "1";

	/**
	 * 招标中
	 */
	public static final String BORROW_STATUS_2 = "2";

	/**
	 * 已流标
	 */
	public static final String BORROW_STATUS_3 = "3";

	/**
	 * 已满标
	 */
	public static final String BORROW_STATUS_4 = "4";

	/**
	 * 还款中
	 */
	public static final String BORROW_STATUS_5 = "5";

	/**
	 * 逾期还款
	 */
	public static final String BORROW_STATUS_6 = "6";

	/**
	 * 逾期垫付
	 */
	public static final String BORROW_STATUS_7 = "7";

	/**
	 * 已结清
	 */
	public static final String BORROW_STATUS_8 = "8";

	/**************************** 借款状态常量 结束 *********************************/

	/**************************** 还款状态常量 开始 *********************************/
	/**
	 * 还款中
	 */
	public static final String BIZ_REPLAN_STATUS_1 = "1";
	/**
	 * 逾期还款
	 */
	public static final String BIZ_REPLAN_STATUS_2 = "2";
	/**
	 * 逾期垫付
	 */
	public static final String BIZ_REPLAN_STATUS_3 = "3";
	/**
	 * 已结清
	 */
	public static final String BIZ_REPLAN_STATUS_4 = "4";
	
	/**
	 * 提前还款
	 */
	public static final String BIZ_REPLAN_STATUS_7 = "7";
	

	/**************************** 还款状态常量 结束 *********************************/
	/**************************** 收款状态常量 开始 *********************************/
	/**
	 * 1 待收中、2 已逾期、3 转让中、4 已转让、5 已垫付、6 已结清 7 提前还款
	 */
	public static final String BIZ_RECEIPTPLAN_STATUS_1 = "1";
	/**
	 * 已逾期
	 */
	public static final String BIZ_RECEIPTPLAN_STATUS_2 = "2";
	/**
	 * 转让中
	 */
	public static final String BIZ_RECEIPTPLAN_STATUS_3 = "3";
	/**
	 * 已转让
	 */
	public static final String BIZ_RECEIPTPLAN_STATUS_4 = "4";
	/**
	 * 已垫付
	 */
	public static final String BIZ_RECEIPTPLAN_STATUS_5 = "5";
	/**
	 * 已结清
	 */
	public static final String BIZ_RECEIPTPLAN_STATUS_6 = "6";
	
	/**
	 * 提前还款
	 */
	public static final String BIZ_RECEIPTPLAN_STATUS_7 = "7";

	/**************************** 收款状态常量 结束 *********************************/

	/**************************** 债权转让管理常量 开始 *********************************/
	/**
	 * 转让中
	 */
	public static final String BIZ_TRANSFER_STATUS_1 = "1";
	/**
	 * 已转让
	 */
	public static final String BIZ_TRANSFER_STATUS_2 = "2";
	/**
	 * 已失败
	 */
	public static final String BIZ_TRANSFER_STATUS_3 = "3";
	/**
	 * 已撤销
	 */
	public static final String BIZ_TRANSFER_STATUS_4 = "4";

	/**************************** 债权转让管理常量 结束 *********************************/

	/**************************** 消息推送常量 开始 *********************************/
	/**
	 * 短信
	 */
	public static final String SMS_PUSH_TYPE_1 = "1";
	/**
	 * 微信
	 */
	public static final String SMS_PUSH_TYPE_2 = "2";
	/**
	 * APP
	 */
	public static final String SMS_PUSH_TYPE_3 = "3";
	/**
	 * 系统消息
	 */
	public static final String SMS_PUSH_TYPE_4 = "4";

	/**************************** 消息推送常量 结束 *********************************/

	/**
	 * 绝对路径
	 */
	public static final String ABSOLUTE = "absolute";

	/**
	 * 相对路径
	 */
	public static final String RELATIVE = "relative";

	/**************************** 业务模块常量 开始 *********************************/

	/**
	 * 业务管理
	 */
	public static final String BUSINESS = "business";
	
	/**
	 * 电台
	 */
	public static final String RADIO = "radio";

	/**
	 * 内容管理
	 */
	public static final String CONTENT = "content";
	
	/**
	 * 客户管理
	 */
	public static final String CUTOMER = "customer";

	/**************************** 业务模块常量 结束 *********************************/

	/**************************** 财务模块常量 开始 *********************************/
	/**
	 * 客户账户明细流水类型
	 */
	public static final String CUST_FUND_WATER_TYPE = "CUST_FUND_WATER_TYPE";
	/**
	 * 客户账户明细流水类型 线上充值
	 */
	public static final String CUST_FUND_WATER_TYPE_1 = "1";
	/**
	 * 客户账户明细流水类型 线下充值
	 */
	public static final String CUST_FUND_WATER_TYPE_2 = "2";
	/**
	 * 客户账户明细流水类型 提现
	 */
	public static final String CUST_FUND_WATER_TYPE_3 = "3";
	/**
	 * 客户账户明细流水类型 借款成功
	 */
	public static final String CUST_FUND_WATER_TYPE_4 = "4";
	/**
	 * 客户账户明细流水类型 投标成功
	 */
	public static final String CUST_FUND_WATER_TYPE_5 = "5";
	/**
	 * 客户账户明细流水类型 还款
	 */
	public static final String CUST_FUND_WATER_TYPE_6 = "6";
	/**
	 * 客户账户明细流水类型 收款
	 */
	public static final String CUST_FUND_WATER_TYPE_7 = "7";
	/**
	 * 客户账户明细流水类型 VIP付费
	 */
	public static final String CUST_FUND_WATER_TYPE_8 = "8";
	/**
	 * 客户账户明细流水类型 支付投标奖励
	 */
	public static final String CUST_FUND_WATER_TYPE_9 = "9";

	/**
	 * 客户账户明细业务类型
	 */
	public static final String CUST_FUND_BUSINESS_TYPE = "CUST_FUND_BUSINESS_TYPE";
	/**
	 * 客户账户明细业务类型 全部
	 */
	public static final String CUST_FUND_BUSINESS_TYPE_0 = "0";
	/**
	 * 客户账户明细业务类型 收入
	 */
	public static final String CUST_FUND_BUSINESS_TYPE_1 = "1";
	/**
	 * 客户账户明细业务类型 支出
	 */
	public static final String CUST_FUND_BUSINESS_TYPE_2 = "2";

	/**
	 * 系统参数：备付金本金垫付罚息利率
	 */
	public static final String BIZ_ENSURE_CAPITAL_PENALTY_RATE = "BIZ_ENSURE_CAPITAL_PENALTY_RATE";
	/**
	 * 系统参数：备付金利息垫付罚息利率
	 */
	public static final String BIZ_ENSURE_INTEREST_PENALTY_RATE = "BIZ_ENSURE_INTEREST_PENALTY_RATE";
	/**
	 * 系统参数：备付金管理费收取比例
	 */
	public static final String BIZ_ENSURE_MANAGE_RATE = "BIZ_ENSURE_MANAGE_RATE";
	
	/**
	 * 系统参数：提前还款罚金比例
	 */
	public static final String PRE_REPAYMENT_RATE = "PRE_REPAYMENT_RATE";
	
	/**
	 * 系统参数：备付金活期利率
	 */
	public static final String BIZ_ENSURE_RATE_OF_CALL = "BIZ_ENSURE_RATE_OF_CALL";
	/**
	 * 系统参数：B级风险预警
	 */
	public static final String BIZ_ENSURE_B_RISK_WARN = "BIZ_ENSURE_B_RISK_WARN";
	/**
	 * 系统参数：A级风险预警
	 */
	public static final String BIZ_ENSURE_A_RISK_WARN = "BIZ_ENSURE_A_RISK_WARN";

	/**
	 * 系统字典：备付金明细类型
	 */
	public static final String ENSURE_DETAIL_TYPE = "ENSURE_DETAIL_TYPE";

	/**
	 * Description：<br>
	 * 获取备付金参数键
	 * 
	 * @author jenkin.yu
	 * @date 2015年10月21日
	 * @version v1.0.0
	 * @return
	 */
	public static Map<String, String> getBizEnsureParamsKey() {
		Map<String, String> bizParams = new HashMap<String, String>();
		bizParams.put("bizEnsureCapitalPenaltyRate", BIZ_ENSURE_CAPITAL_PENALTY_RATE);
		bizParams.put("bizEnsureInterestPenaltyRate", BIZ_ENSURE_INTEREST_PENALTY_RATE);
		bizParams.put("bizEnsureManageRate", BIZ_ENSURE_MANAGE_RATE);
		bizParams.put("bizEnsureRateOfCall", BIZ_ENSURE_RATE_OF_CALL);
		bizParams.put("bizEnsureBRiskWarn", BIZ_ENSURE_B_RISK_WARN);
		bizParams.put("bizEnsureARiskWarn", BIZ_ENSURE_A_RISK_WARN);
		return bizParams;
	}

	/**************************** 财务模块常量 结束 *********************************/

	/**************************** 备付金管理 开始 ******************************/

	/**
	 * 备付金 费用类型 1 收入
	 */
	public static final String FEE_TYPE_1 = "1";

	/**
	 * 备付金 费用类型 2 支出
	 */
	public static final String FEE_TYPE_2 = "2";

	/**
	 * 备付金 费用类型 3 总计
	 */
	public static final String FEE_TYPE_3 = "3";

	/**
	 * 备付金 调整类型 初始化备用金
	 */
	public static final String ENSURE_TYPE_1 = "1";

	/**
	 * 备付金 调整类型 调减备付金
	 */
	public static final String ENSURE_TYPE_2 = "2";

	/**
	 * 备付金 调整类型 调增备付金
	 */
	public static final String ENSURE_TYPE_3 = "3";

	/**************************** 备付金管理 结束 ******************************/

	/**
	 * 统计列表:总计
	 */
	public static final String AGGREGATE_STRING = "合计";

	/**
	 * 新增用户数 标题
	 */
	public static final String ADD_USER_COUNT_TITLE = "新增用户数";
	/**
	 * 新增用户数 标题
	 */
	public static final String USER_REVOKE_MSG = "用户撤销";

	/**
	 * 用户实名认证数 标题
	 */
	public static final String USER_CERTIFICATION_COUNT_TITLE = "实名认证数";

	/**************************** 积分类型 开始 ******************************/
	/** 投资积分 */
	public static final String POINT_INVEST_TYPE = "pointGetType_tzjf";

	/**************************** 积分类型 结束 ******************************/

	/** 交易密码错误CODE */
	public static final String USER_TRADE_PWD_ERROR = "tradePwdError";
	public static final String USER_TRADE_PWD_BANK = "tradePwdBank";
	public static final String USER_TRADE_FREEZE = "tradeFreeze";
	public static final String USER_TRADE_PWD_ISNULL = "tradePwdIsNull";

	/**************** 投资方式S **************/
	/** PC端 */
	public static final String INVEST_TYPE_1 = "1";
	/** 移动端*/
	public static final String INVEST_TYPE_2 = "2";
	/**************** 投资方式E **************/
	/** 可债权转让 */
	public static final String IS_EQUITABLE_ASSIGNMENT_YES = "1";
	/** 不可债权转让 */
	public static final String IS_EQUITABLE_ASSIGNMENT_NO = "2";

	/**************************** 资金类别 开始 ******************************/
	/** 未定义*/
	public static final String ACCOUNTAMOUNT_TYPE_UNDEFINED = "0";
	/** 充值资金 */
	public static final String ACCOUNTAMOUNT_TYPE_RECHARGE = "1";
	/** 普通资金 */
	public static final String ACCOUNTAMOUNT_TYPE_COMMON = "2";
	/** 体验资金 */
	public static final String ACCOUNTAMOUNT_TYPE_EXPERIENCE = "3";
	
	/**************************** 资金类别 结束 ******************************/

	/**************************** 系统请求路径 开始 ******************************/
	public static final String WEB_ROOT = "web_root";
	/**************************** 系统请求路径 结束 ******************************/
	
	/**
	 * 15天以内
	 */
	public static final String TIME_TYPE_15 = "15";
	
	/**
	 * 30天以内
	 */
	public static final String TIME_TYPE_30 = "30";
	
	/**
	 * 根据邮箱找回密码参数加密因子
	 */
	public static final String RESET_PWD_SALT = "13467";
	
	
	/**************************** 定时任务执行状态 开始 ******************************/
	
	/**
	 * 待执行
	 */
	public static final String JOB_STATUS_1 = "1";
	
	/**
	 * 执行中
	 */
	public static final String JOB_STATUS_2 = "2";
	
	/**
	 * 暂停
	 */
	public static final String JOB_STATUS_3 = "3";
	
	/**
	 * 完成
	 */
	public static final String JOB_STATUS_4 = "4";
	
	/**
	 * 异常
	 */
	public static final String JOB_STATUS_5 = "5";
	
	/**************************** 定时任务执行状态 结束 ******************************/
	
	/**************************** 线上充值平台 开始 ******************************/
	/**
	 * 财付通
	 */
	public static final String TEN_PAY = "1";
	
	/**
	 * 连连支付App
	 */
	public static final String LL_PAY = "2";
	/**************************** 线上充值平台 结束 ******************************/
	
	/**************************** 线上充值状态 开始 ******************************/
	/**
	 * 未定义
	 */
	public static final String PAY_UNDEFINED = "0";
	/**
	 * 待付款
	 */
	public static final String PAY_PENDING = "1";
	/**
	 * 付款失败
	 * 状态变化过程：待付款->付款失败
	 */
	public static final String PAY_FAILED = "2";
	/**
	 * 充值成功
	 * 状态变化过程：待付款->充值成功
	 */
	public static final String PAY_OK = "3";
	/**
	 * 当支付平台通知付款成功，但是系统执行充值时发生异常（如由于账户更新并发等）,
	 * 此时充值数据变为"待充值"，后续有系统任务来继续完成充值的过程
	 * 状态变化过程：待付款->待充值
	 */
	public static final String PAY_RECHARGE_PENDING = "4";
	/**
	 * 系统充值
	 * 状态变化过程：待付款->待充值->系统充值
	 */
	public static final String PAY_RECHARGE_TASK = "5";
	/**
	 * 手动补单
	 * 由于各种原因，充值可能出现掉单，此时充值状态为“待付款”由财务人员已经确认该款是否到账
	 * 状态变化过程：待付款->手动补单
	 */
	public static final String PAY_RECHARGE_MADEUP = "6";
	/**
	 * 由于系统自动补单功能曾经存在错误，导致了一些充值成功名单是状态却被设置为“待充值”
	 * 这些错误的状态统一设置为PAY_ERROR_ADJUSTED加以区分
	 */
	public static final String PAY_ERROR_ADJUSTED = "7";
	
	/**************************** 线上充值状态 结束 ******************************/
	
	
	
	/**********************************前台、app 登录错误信息begin*******************************************/
	
	/**
	 * 登录验证错误信息返回
	 */
	public static final String LOGIN_ERROR_KEY = "errorType";
	
	/**
	 * 登录验证结果key
	 */
	public static final String LOGIN_RESULT = "result";
	
	/**
	 * 登录名不存在
	 */
	public static final String LOGIN_ERROR_1 = "1";
	
	/**
	 * 密码错误
	 */
	public static final String LOGIN_ERROR_2 = "2";
	
	/**
	 * 用户已被锁定
	 */
	public static final String LOGIN_ERROR_3 = "3";
	
	/**
	 * 用户已被拉入黑名单
	 */
	public static final String LOGIN_ERROR_5 = "5";
	
	/**
	 * 用户已被禁用
	 */
	public static final String LOGIN_ERROR_6 = "6";
	
	/**
	 * 登录成功
	 */
	public static final String LOGIN_ERROR_4 = "4";
	
	/**
	 * 客户未被锁定
	 */
	public static final String IS_LOCKED_1 = "1";
	
	/**
	 * 客户被锁定
	 */
	public static final String IS_LOCKED_2 = "2";
	
	
	public static final String CUS_KEY = "customer";
	
	
	
	/**********************************前台、app 登录错误信息end*******************************************/
	
	
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
	
	
	/*********************活动的常量****************************/
	/** 活动类型：赠送VIP */
	public static final String ACTIVITY_TYPE_1 = "1";
	/** 活动类型：红包 */
	public static final String ACTIVITY_TYPE_2 = "2";
	/** 活动类型：体验金 */
	public static final String ACTIVITY_TYPE_3 = "3";
	/** 活动类型：投资奖励 */
	public static final String ACTIVITY_TYPE_4 = "4";
	/** 活动类型：现金 */
	public static final String ACTIVITY_TYPE_5 = "5";
	/** 活动类型：生日特权 */
	public static final String ACTIVITY_TYPE_7 = "7";
	/** 活动类型：财富合伙人 */
	public static final String ACTIVITY_TYPE_8 = "8";
	
	/** 活动状态：已删除 */
	public static final String ACTIVITY_STATUS_1 = "1";
	/** 活动状态：未开始 */
	public static final String ACTIVITY_STATUS_2 = "2";
	/** 活动状态：进行中 */
	public static final String ACTIVITY_STATUS_3 = "3";
	/** 活动状态：过期 */
	public static final String ACTIVITY_STATUS_4 = "4";
	
	/** 使用状态：已领取 */
	public static final String USE_STATUS_1 = "1";
	/** 使用状态：未领取 */
	public static final String USE_STATUS_2 = "-1";
	
	/** 红包类型：送红包 */
	public static final String REDPACKET_TYPE_1 = "1";
	/** 红包类型：抢红包 */
	public static final String REDPACKET_TYPE_2 = "2";
	
	/** 红包分配类型：平均分配 */
	public static final String ALLOT_TYPE_1 = "1";
	/** 红包分配类型：随机分配 */
	public static final String ALLOT_TYPE_2 = "2";
	
	/** 投资奖励类型：积分 */
	public static final String INVEST_AWARD_TYPE_1 = "1";
	/** 投资奖励类型：经验 */
	public static final String INVEST_AWARD_TYPE_2 = "2";
	/** 投资奖励类型：加息劵 */
	public static final String INVEST_AWARD_TYPE_3 = "3";
	
	/** 积分类型：活动赠送 */
	public static final String POINT_TYPE_1 = "1";
	/** 积分类型：积分兑换话费 */
	public static final String POINT_TYPE_2 = "2";
	/** 积分类型：积分兑换加息劵 */
	public static final String POINT_TYPE_3 = "3";
	/** 积分类型：积分兑换VIP */
	public static final String POINT_TYPE_4 = "4";
	/** 积分类型：积分兑换现金 */
	public static final String POINT_TYPE_5 = "5";
	/** 积分类型：生日积分 */
	public static final String POINT_TYPE_6 = "pointGetType_srjf";
	/** 积分类型：邀请注册 */
	public static final String POINT_TYPE_7 = "pointGetType_yqzc";
	/** 积分类型：投资积分 */
	public static final String POINT_TYPE_8 = "pointGetType_tzjf";
	/** 积分类型：电台收听 */
	public static final String POINT_TYPE_9 = "pointGetType_dtst";
	/** 积分类型：论坛发帖 */
	public static final String POINT_TYPE_10 = "pointGetType_rtft";
	/** 积分类型：每日签到 */
	public static final String POINT_TYPE_11 = "pointGetType_mrqd";
	/** 积分类型：首次投资 */
	public static final String POINT_TYPE_12 = "pointGetType_sctz";
	/** 积分类型：绑定银行卡 */
	public static final String POINT_TYPE_13 = "pointGetType_bdyhk";
	/** 积分类型：绑定邮箱 */
	public static final String POINT_TYPE_14 = "pointGetType_bdyx";
	/** 积分类型：论坛回帖 */
	public static final String POINT_TYPE_15 = "pointGetType_trht";
	/** 积分类型：实名认证 */
	public static final String POINT_TYPE_16 = "pointGetType_smrz";
	/** 积分类型：成功注册 */
	public static final String POINT_TYPE_17 = "pointGetType_cgzc";
	
	/** 加息劵获得类型：活动 */
	public static final String INTEREST_GET_TYPE_1 = "1";
	/** 加息劵获得类型：兑换 */
	public static final String INTEREST_GET_TYPE_2 = "2";
	
	/** 加息劵状态：正常 */
	public static final String INTEREST_STATUS_1 = "1";
	/** 加息劵状态：过期 */
	public static final String INTEREST_STATUS_2 = "2";
	/** 加息劵状态：已删除 */
	public static final String INTEREST_STATUS_3 = "-1";
	
	/** 经验值获得类型：VIP每日经验 */
	public static final String EXP_GET_TYPE_1 = "1";
	/** 经验值获得类型：投资经验 */
	public static final String EXP_GET_TYPE_2 = "2";
	/** 经验值获得类型：活动获得 */
	public static final String EXP_GET_TYPE_3 = "3";
	
	/*********************活动的常量end****************************/
	
	/**************客户相关联的常量**************************/
	/** 获取VIP类型：1.购买 */
	public static final String LARGESS_VIP_TYPE_1 = "1";
	/** 获取VIP类型：2.兑换 */
	public static final String LARGESS_VIP_TYPE_2 = "2";
	/** 获取VIP类型：3.赠送 */
	public static final String LARGESS_VIP_TYPE_3 = "3";
	
	/***************客户相关联的常量end********************/
	
	/***************手机验证码关联的常量*******************/
	
	/**
	 * 即使短信验证码
	 */
	public static final Integer SMS_TYPE_1 = 1;
	
	/**
	 * 即使语音验证码
	 */
	public static final Integer SMS_TYPE_2 = 2;
	
	/**
	 * 定时短信验证码
	 */
	public static final Integer SMS_TYPE_3 = 3;
	
	/***************手机验证码关联的常量*******************/
	
	/***************投资来源类型*******************/
	
	/**
	 * 原标
	 */
	public static final String INVEST_SRC_TYPE_1 = "1";
	
	/**
	 * 债权购买
	 */
	public static final String INVEST_SRC_TYPE_2 = "2";
	
	
	/***************投资来源类型*******************/
	
	/***************** 活动--系统参数  *****************/
	
	/** 活动送红包类型：注册送红包 */
	public static final String REGISTER_GIVERED = "REGISTER_GIVERED";
	/** 活动送红包类型：认证送红包 */
	public static final String AUTHENTICA_GIVERED = "AUTHENTICA_GIVERED";
	/** 投资经验值基数 */
	public static final String INVESTMENT_EXPERIENCE_BASE = "INVESTMENT_EXPERIENCE_BASE";
	
	/***************** 活动--系统参数 end  *****************/
	
	/******************** 充值结果跳转路径 star *******************************/
	public static final String RECHARGE_RESULTURL = "/userinfo/centerController/toUserCashflow.shtml";
	/******************** 充值结果跳转路径 end *******************************/
	
	/********************加息是否收款S*******************************/
	/**
	 * 加息待收
	 */
	public static final String NET_HIKE_STATUS_0 = "0";
	/**
	 * 加息已收
	 */
	public static final String NET_HIKE_STATUS_1 = "1";
	
	/********************加息是否收款E*******************************/
	/********************合同类型S*******************************/
	
	/**
	 * 借款合同类型，投资人
	 */
	public static final String CONTRACT_TYPE_1 = "1";
	
	/**
	 * 借款合同类型，借款人
	 */
	public static final String CONTRACT_TYPE_2 = "2";
	
	/**
	 * 债权合同类型
	 */
	public static final String CONTRACT_TYPE_3 = "3";
	
	/********************合同类型E*******************************/
	

	/********************合同文件存储路径******************************/
	
	public static final String CONTRACT_BORROW_FILE_URL = "upload/contract/pdf/borrow/";
	
	public static final String CONTRACT_TRANSFER_FILE_URL = "upload/contract/pdf/transfer/";
	
	
	
	/********************系统消息记录表******************************/

	/**
	 * 1：系统消息
	 */
	public static final Integer CUST_MESS_RECORD_1 = 1;
	
	/**
	 * 2：体验金消息
	 */
	public static final Integer CUST_MESS_RECORD_2 = 2;
	
	/**
	 * 3：投资消息
	 */
	public static final Integer CUST_MESS_RECORD_3 = 3;
	
	/**
	 * 4：投标流标
	 */
	public static final Integer CUST_MESS_RECORD_4 = 4;
	
	/**
	 * 5：投资撤销
	 */
	public static final Integer CUST_MESS_RECORD_5 = 5;
	
	/**
	 * 6：债权消息
	 */
	public static final Integer CUST_MESS_RECORD_6 = 6;
	
	/**
	 * 7：劵类消息
	 */
	public static final Integer CUST_MESS_RECORD_7 = 7;
	
	/**
	 * 8：还款消息
	 */
	public static final Integer CUST_MESS_RECORD_8 = 8;
	
	/**
	 * 9：逾期消息
	 */
	public static final Integer CUST_MESS_RECORD_9 = 9;
	
	/**
	 * 10：回款消息
	 */
	public static final Integer CUST_MESS_RECORD_10 = 10;
	
	/**
	 * 11：申请借款通过消息
	 */
	public static final Integer CUST_MESS_RECORD_11 = 11;
	
	/**
	 * 12:申请借款拒绝消息
	 */
	public static final Integer CUST_MESS_RECORD_12 = 12;
	
	/********************系统消息记录表******************************/
	
	
}
