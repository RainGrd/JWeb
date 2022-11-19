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
package com.yscf.core.service.financial;

import com.yscf.common.Constant.Constant;
import com.yscf.core.service.financial.impl.BaseRechargeOnlinePlatform;
import com.yscf.core.service.financial.impl.LlPayAppServiceImpl;
import com.yscf.core.service.financial.impl.TenpayServiceImpl;

/**
 * Description：<br> 
 * TODO
 * @author  Jie.Zou
 * @date    2016年3月10日
 * @version v1.0.0
 */
public class IRechargeOnlineFactory {
	private volatile static BaseRechargeOnlinePlatform platform = null; 
	
	private IRechargeOnlineFactory(){};
	
	public static BaseRechargeOnlinePlatform getPlatform(String payConfigId){
        if(platform == null){
            synchronized(IRechargeOnlineFactory.class){
                if(payConfigId.equals(Constant.TEN_PAY)){
                	platform = new TenpayServiceImpl();
                }else if(payConfigId.equals(Constant.LL_PAY)){
                	platform = new LlPayAppServiceImpl();
                }
            }
        }
        return platform;
    }
}


