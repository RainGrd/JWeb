/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 首页特殊查询服务层接口
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年11月13日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.homepage;

import java.math.BigDecimal;

/**
 * Description：<br> 
 * 首页特殊查询服务层接口
 * @author  Lin Xu
 * @date    2015年11月13日
 * @version v1.0.0
 */
public interface IHomePageService {
	
	/**
	 * Description：<br> 
	 * 获取最高年利率
	 * @author  Lin Xu
	 * @date    2015年11月23日
	 * @version v1.0.0
	 * @return
	 */
	public BigDecimal getMaximumAPR();

}


