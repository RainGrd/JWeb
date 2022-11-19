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
 * 2016年3月10日     Jie.Zou		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.financial.impl;

import java.util.Map;

import com.yscf.core.model.financial.BizRechargeOnline;
import com.yscf.core.model.financial.recharge.PayConfig;

/**
 * Description：<br> 
 * TODO
 * @author  Jie.Zou
 * @date    2016年3月10日
 * @version v1.0.0
 */
public abstract class BaseRechargeOnlinePlatform {
	
	/**
	 * 
	 * Description：<br> 
	 * 获取提交充值表单数据的支付平台网址
	 * @author  Jie.Zou
	 * @date    2015年11月25日
	 * @version v1.0.0
	 * @param paySystemKey 支付平台关键字
	 * @return
	 */
	public abstract String getRechargeURL(String paySystemKey);
	
	/**
	 * 
	 * Description：<br> 
	 * 包装出充值订单号
	 * @author  Jie.Zou
	 * @date    2015年11月24日
	 * @version v1.0.0
	 * @param recharge
	 * @param mid
	 * @return
	 */
	public abstract String wrapOrderId(BizRechargeOnline recharge,String mid);
	
	/**
	 * 
	 * Description：<br> 
	 * 从通知数据中的充值订单号分解出rechargeOnlineId（流水号）
	 * @author  Jie.Zou
	 * @date    2015年11月18日
	 * @version v1.0.0
	 * @param feedbackData
	 * @param paySystemType
	 * @return
	 */
	public abstract String resolveRechargeOnlineId(Map<String,String> feedbackData,String paySystemType);
	
	/**
	 * 
	 * Description：<br> 
	 * 充值通知时检测签名
	 * @author  Jie.Zou
	 * @date    2015年11月23日
	 * @version v1.0.0
	 * @param feedbackData
	 * @param payConfig
	 * @return
	 */
	public abstract boolean checkFeedbackSign(Map<String, String> feedbackData,PayConfig payConfig);
	
	/**
	 * 
	 * Description：<br> 
	 * 判断支付是否成功，此方法是支付平台返回相应的支付结果编码来判断支付是否成功
	 * @author  Jie.Zou
	 * @date    2015年11月23日
	 * @version v1.0.0
	 * @param feedbackData
	 * @return
	 */
	public abstract boolean isRechargeStateSuccess(Map<String, String> feedbackData);
	

	/**
	 * 
	 * Description：<br> 
	 * 获取进行充值时提交给支付平台的表单数据
	 * 此方法在实际的支付平台处理逻辑实现
	 * @author  Jie.Zou
	 * @date    2015年11月24日
	 * @version v1.0.0
	 * @param recharge 线上充值实体
	 * @param payConfig 支付账户配置信息实体
	 * @param params 传递的额外参数
	 * @return
	 */
	public abstract Map<String, String> getFormParams(BizRechargeOnline recharge,
			PayConfig payConfig, Map<String, String> params);

	
	/**
	 * 
	 * Description：<br> 
	 * 提交表单到支付平台，对数据进行签名的方法
	 * @author  Jie.Zou
	 * @date    2015年11月24日
	 * @version v1.0.0
	 * @param params 表单数据
	 * @param payConfig 支付账户配置信息实体
	 * @return
	 */
	public abstract String sign4recharge(Map<String, String> params, PayConfig payConfig);
	
	/**
	 * 
	 * Description：<br> 
	 * 支付平台通知系统入账，对数据进行签名的方法
	 * @author  Jie.Zou
	 * @date    2015年11月24日
	 * @version v1.0.0
	 * @param params 表单数据
	 * @param payConfig 支付账户配置信息实体
	 * @return
	 */
	public abstract String sign4notify(Map<String, String> params, PayConfig payConfig);
}


