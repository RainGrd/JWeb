/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 支付接口业务逻辑接口
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年11月18日     Jie.Zou		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.financial;

import java.util.List;

import com.yscf.core.model.financial.recharge.PayConfig;

/**
 * Description：<br> 
 * 支付接口业务逻辑接口
 * @author  Jie.Zou
 * @date    2015年11月18日
 * @version v1.0.0
 */
public interface IPayConfigService {
	
	/**
	 * 
	 * Description：<br> 
	 * 查询所有有效的支付接口
	 * @author  Jie.Zou
	 * @date    2015年11月18日
	 * @version v1.0.0
	 * @param config
	 * @return
	 */
	public List<PayConfig> selectAll();
	
	/**
	 * 
	 * Description：<br> 
	 * 通过ID得到对应的支付接口
	 * @author  Jie.Zou
	 * @date    2015年12月10日
	 * @version v1.0.0
	 * @param payConfigId
	 * @return
	 */
	public PayConfig selectPay(String payConfigId);
}


