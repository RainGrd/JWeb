/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 充值逻辑层
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2016年3月10日     Jie.Zou		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.financial;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

/**
 * Description：<br> 
 * 充值逻辑层
 * @author  Jie.Zou
 * @date    2016年3月10日
 * @version v1.0.0
 */
public interface ICustRechargeService {
	
	/**
	 * 
	 * Description：<br> 
	 * 获取进行充值时提交给支付平台的表单数据
	 * 此方法在bizAccountRechargeServiceImpl中实现，实际的支付平台处理逻辑不实现该方法
	 * @author  Jie.Zou
	 * @date    2015年11月24日
	 * @version v1.0.0
	 * @param userId 用户ID
	 * @param amount 充值金额
	 * @param payConfigId 支付账户配置信息实体ID
	 * @param time 操作时间
	 * @param params 传递的额外参数
	 * @return
	 */
	public Map<String, String> getFormParams(String userId, BigDecimal amount,
			String payConfigId, Date time, Map<String, String> params);
	
	/**
	 * 
	 * Description：<br> 
	 * 支付平台支付结束后，通知到系统，系统调用此方法
	 * 对通知进行下一步的处理：如支付成功，则对用户执行
	 * 入账操作，否则，则关闭充值流程
	 * @author  Jie.Zou
	 * @date    2015年11月17日
	 * @version v1.0.0
	 * @return
	 */
	public String doRechargeFeedback(Map<String, String> feedbackData,
			String paySystemType, Date time);
}


