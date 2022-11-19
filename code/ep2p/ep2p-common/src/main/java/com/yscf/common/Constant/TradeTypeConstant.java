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
 * 2015年12月17日     JunJie.Liu		Initial Version.
 *************************************************************************
 */
package com.yscf.common.Constant;

/**
 * Description：<br> 
 * 交易类型常量,奇数收入，偶数支出
 * 100 - 199 充值记录
 * 200 - 299 提现记录
 * 300 - 399 投资记录
 * 400 - 499 收益记录
 * 500 - 599 其他
 * 1000-1999 系统交易类型
 * 2000-2999 风险赔付交易类型
 * 注释含有3.0保留内容，指3.0版本的系统，保留2.0数据的查询
 * 修改记录：
 * 2016-3-3 13:51 增加系统交易类型，逾期赔付  1026 
 * 2016年3月5日13:38:58  增加收益记录  收取提前还款罚息   427
 * 2016年3月5日13:39:19  增加其他   支出提前还款罚息 522
 * 2016年3月5日13:42:16  增加系统交易类型 收取提前还款罚息 1035
 * @author  JunJie.Liu
 * @date    2015年12月17日
 * @version v1.0.0
 */
public class TradeTypeConstant {
	
	/*******************用户资金交易类型S********************/
	
	
	/*********************充值记录S***********************/
	
	/**
	 * 线下充值
	 */
	public static final String RECHARGE_TYPE_101 = "101";
	
	/**
	 * 线上充值
	 */
	public static final String RECHARGE_TYPE_103 = "103";
	
	/*********************充值记录E***********************/
	
	
	
	/*********************提现记录S***********************/
	/**
	 * 提现记录
	 */
	public static final String WITHDRAW_TYPE_202 = "202";
	
	/*********************提现记录E***********************/
	
	/*********************投资记录S***********************/
	
	/**
	 * 投资成功
	 */
	public static final String INVEST_TYPE_302 = "302";
	
	/*********************投资记录E***********************/
	
	
	/*********************收益记录S***********************/
	/**
	 * 加息奖励
	 */
	public static final String RETURNS_TYPE_401 = "401";
	
	/**
	 * 投资奖励
	 */
	public static final String RETURNS_TYPE_403 = "403";
	
	/**
	 * 推荐奖励
	 */
	public static final String RETURNS_TYPE_405 = "405";
	
	/**
	 * 收取本金
	 */
	public static final String RETURNS_TYPE_407 = "407";
	
	/**
	 * 收取利息
	 */
	public static final String RETURNS_TYPE_409 = "409";
	
	/**
	 * 收取罚息
	 */
	public static final String RETURNS_TYPE_411 = "411";
	
	/**
	 * 债权转让
	 */
	public static final String RETURNS_TYPE_413 = "413";
	
	/**
	 * 红包（含派红包、抢红包）
	 */
	public static final String RETURNS_TYPE_415 = "415";
	
	/**
	 * 积分兑现
	 */
	public static final String RETURNS_TYPE_417 = "419";
	
	/**
	 * 系统奖励
	 */
	public static final String RETURNS_TYPE_421 = "421";
	
	/**
	 * 利息管理费分成（3.0保留）
	 */
	public static final String RETURNS_TYPE_423 = "423";
	
	/**
	 * 收款（3.0保留）
	 */
	public static final String RETURNS_TYPE_425 = "425";
	
	/**
	 * 收取提前还款罚息  
	 */
	public static final String RETURNS_TYPE_427 = "427";
	
	/*********************收益记录E***********************/

	/*********************其他S***********************/
	
	/**
	 * 利息管理费
	 */
	public static final String OTHER_TYPE_502 = "502";
	
	/**
	 * 借款成功
	 */
	public static final String OTHER_TYPE_503 = "503";
	
	/**
	 * 借款管理费
	 */
	public static final String OTHER_TYPE_504 = "504";
	
	/**
	 * VIP付费
	 */
	public static final String OTHER_TYPE_506 = "506";
	
	/**
	 * 提现手续费
	 */
	public static final String OTHER_TYPE_508 = "508";
	
	/**
	 * 已还本金
	 */
	public static final String OTHER_TYPE_510 = "510";
	
	/**
	 * 已还利息
	 */
	public static final String OTHER_TYPE_512 = "512";
	
	/**
	 * 支付逾期罚息
	 */
	public static final String OTHER_TYPE_514 = "514";
	
	/**
	 * 债权转让服务费
	 */
	public static final String OTHER_TYPE_516 = "516";
	
	/**
	 * 购买债权
	 */
	public static final String OTHER_TYPE_518 = "518";
	
	/**
	 * 账户管理费（3.0保留）
	 */
	public static final String OTHER_TYPE_520 = "520";
	
	/**
	 * 续投奖励（3.0保留）
	 */
	public static final String OTHER_TYPE_521 = "521";
	
	/**
	 * 支出提前还款罚息
	 */
	public static final String OTHER_TYPE_522 = "522";
	
	
	/*********************其他E***********************/
	
	
	
	/*******************用户资金交易类型E********************/
	
	
	/*******************系统交易类型E********************/
	
	/**
	 * VIP付费
	 */
	public static final String SYSTEM_TRADE_TYPE_1001 = "1001";
	
	/**
	 * 利息管理费
	 */
	public static final String SYSTEM_TRADE_TYPE_1003 = "1003";
	
	
	/**
	 * 推荐奖励分成（原利息管理费分成，3.0保留）
	 */
	public static final String SYSTEM_TRADE_TYPE_1004 = "1004";
	
	
	/**
	 * 推荐奖励（原推荐活动奖金）
	 */
	public static final String SYSTEM_TRADE_TYPE_1006 = "1006";
	
	
	/**
	 * 提现手续费（原新充值提现费）
	 */
	public static final String SYSTEM_TRADE_TYPE_1007 = "1007";
	
	
	/**
	 * 债权转让服务费
	 */
	public static final String SYSTEM_TRADE_TYPE_1009 = "1009";
	
	
	/**
	 * 管理费率
	 */
	public static final String SYSTEM_TRADE_TYPE_1011 = "1011";
	
	
	/**
	 * 加息奖励
	 */
	public static final String SYSTEM_TRADE_TYPE_1012 = "1012";
	
	
	/**
	 * 投资奖励
	 */
	public static final String SYSTEM_TRADE_TYPE_1014 = "1014";
	
	
	/**
	 * 红包（含派红包、抢红包）
	 */
	public static final String SYSTEM_TRADE_TYPE_1016 = "1016";
	
	
	/**
	 * 积分兑现（原 兑换投资积分）
	 */
	public static final String SYSTEM_TRADE_TYPE_1018 = "1018";
	
	
	/**
	 * 系统奖励
	 */
	public static final String SYSTEM_TRADE_TYPE_1020 = "1020";
	
	
	/**
	 *体验标,体验标利息
	 */
	public static final String SYSTEM_TRADE_TYPE_1022 = "1022";
	
	
	/**
	 *赠送体验金（3.0保留）
	 */
	public static final String SYSTEM_TRADE_TYPE_1024 = "1024";
	
	/**
	 *回收体验金（3.0保留）
	 */
	public static final String SYSTEM_TRADE_TYPE_1025 = "1025";
	
	/**
	 *逾期赔付
	 */
	public static final String SYSTEM_TRADE_TYPE_1026 = "1026";
	
	/**
	 *收取罚息（3.0保留）
	 */
	public static final String SYSTEM_TRADE_TYPE_1027 = "1027";
	
	
	/**
	 *续投奖励（3.0保留）
	 */
	public static final String SYSTEM_TRADE_TYPE_1028 = "1028";
	
	/**
	 *抢到红包（3.0保留）
	 */
	public static final String SYSTEM_TRADE_TYPE_1030 = "1030";
	
	/**
	 *派发红包（3.0保留）
	 */
	public static final String SYSTEM_TRADE_TYPE_1032 = "1032";
	
	/**
	 *系统奖励（3.0保留）
	 */
	public static final String SYSTEM_TRADE_TYPE_1034 = "1034";
	
	/**
	 *收取提前还款罚息
	 */
	public static final String SYSTEM_TRADE_TYPE_1035 = "1035";
	
	
	
	
	/*******************系统交易类型E********************/
	
	/*******************风险赔付金交易类型S********************/
	
	/**
	 * 垫付本息
	 */
	public static final String RISK_TRADE_TYPE_2002 = "2002";
	
	/**
	 * 收取罚息
	 */
	public static final String RISK_TRADE_TYPE_2003 = "2003";
	
	/**
	 * 收取垫付本息
	 */
	public static final String RISK_TRADE_TYPE_2005 = "2005";
	
	/**
	 * 支付逾期罚息
	 */
	public static final String RISK_TRADE_TYPE_2006 = "2006";
	
	/**
	 * 初始化备付金
	 */
	public static final String RISK_TRADE_TYPE_2007 = "2007";
	
	/**
	 * 调增备付金
	 */
	public static final String RISK_TRADE_TYPE_2009 = "2009";
	
	/**
	 * 调减备付金
	 */
	public static final String RISK_TRADE_TYPE_2010 = "2010";
	
	/**
	 * 利息管理费分成
	 */
	public static final String RISK_TRADE_TYPE_2011 = "2011";
	

	/*******************风险赔付金交易类型E********************/
	
	
	
	
	
}


