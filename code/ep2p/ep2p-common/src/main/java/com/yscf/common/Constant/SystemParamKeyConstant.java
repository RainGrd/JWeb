/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 系统参数Key接口
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年11月6日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.common.Constant;

/**
 * Description：<br> 
 * 系统参数Key接口
 * @author  Lin Xu
 * @date    2015年11月6日
 * @version v1.0.0
 */
public interface SystemParamKeyConstant {
	//A级风险预警
	public final static String BIZ_ENSURE_A_RISK_WARN = "BIZ_ENSURE_A_RISK_WARN";
	
	//B级风险预警
	public final static String BIZ_ENSURE_B_RISK_WARN = "BIZ_ENSURE_B_RISK_WARN";
	
	//债权转让手续费率
	public final static String BIZ_TRANSFER_FEE = "BIZ_TRANSFER_FEE";
	
	//前台访问地址
	public final static String EP2P_URL = "ep2pUrl";
	
	/**
	 * 交易密码错误次数
	 */
	public final static String TRADE_ERROR_COUNT = "TRADE_ERROR_COUNT";
	
	/**
	 * 交易密码冻结时长、单位（分钟）
	 */
	public final static String TRADE_FREEZE_TIME = "TRADE_FREEZE_TIME";

	/**
	 * 交易密码错误的时间间隔 单位（分钟）
	 */
	public final static String TRADE_ERROT_TIME = "TRADE_ERROT_TIME";
	
	/**
	 * 债权转让，本金最小范围百分比
	 */
	public final static String TRANSFER_CAPITAL_PERCENTER = "TRANSFER_CAPITAL_PERCENTER";
	
	/**
	 * 债权转让合同名称
	 */
	public final static String BOR_AGR_TRANSFER = "BOR_AGR_TRANSFER";
	
	/**
	 * 借款合同名称
	 */
	public final static String BOR_AGR_BORROW = "BOR_AGR_BORROW";
	
	/**
	 * 逾期赔付率，针对借款人
	 */
	public final static String OVER_FEE_BORROW = "OVER_FEE_BORROW";
	
	/**
	 * 逾期赔付率，针对投资人
	 */
	public final static String OVER_FEE_INVEST = "OVER_FEE_INVEST";
	
	/**
	 * 平台
	 */
	public final static String PLAT_FORM = "PLAT_FORM";
	/**
	 * 平台地址
	 */
	public final static String PLAT_FORM_ADDRESS = "PLAT_FORM_ADDRESS";
	
	/**
	 * 100%本息保障
	 */
	public final static String GUARANTEE_TYPE = "GUARANTEE_TYPE";
}


