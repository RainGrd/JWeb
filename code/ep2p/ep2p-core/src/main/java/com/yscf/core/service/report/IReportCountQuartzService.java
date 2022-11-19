/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 统计报表接口服务层
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年12月23日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.report;

/**
 * Description：<br> 
 * 统计报表接口服务层
 * @author  Lin Xu
 * @date    2015年12月23日
 * @version v1.0.0
 */
public interface IReportCountQuartzService {
	
	/**
	 * Description：<br> 
	 * 累计投资报表信息统计服务
	 * @author  Lin Xu
	 * @date    2015年12月23日
	 * @version v1.0.0
	 */
	public void cumulativeInvestReportService();
	

	/**
	 * Description：<br> 
	 * 已还本息报表统计服务
	 * @author  Lin Xu
	 * @date    2015年12月23日
	 * @version v1.0.0
	 */
	public void alsoPrincipalReportService();
	
	/**
	 * Description：<br> 
	 * 累计用户收益统计服务
	 * @author  Lin Xu
	 * @date    2015年12月24日
	 * @version v1.0.0
	 */
	public void userBenefitReportService();
	
	/**
	 * Description：<br> 
	 * 累计已发送收益统计服务
	 * @author  Lin Xu
	 * @date    2015年12月24日
	 * @version v1.0.0
	 */
	public void grantProfitReportService();
	
}


