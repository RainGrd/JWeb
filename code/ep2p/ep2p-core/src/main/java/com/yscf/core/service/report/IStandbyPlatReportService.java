/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 平台数据和备用金报表 service
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年11月4日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.report;

import java.math.BigDecimal;
import java.util.HashMap;

import com.yscf.core.model.financial.BizEnsureMoney;
import com.yscf.core.model.report.PlatFormDateModel;

/**
 * Description：<br> 
 * 平台数据和备用金报表 service
 * @author  Lin Xu
 * @date    2015年11月4日
 * @version v1.0.0
 */
public interface IStandbyPlatReportService {

	/**
	 * Description：<br> 
	 * 查询累计投资、已还本息、年度累计用户收益总计、年度已发放收益总计
	 * @author  Lin Xu
	 * @date    2015年11月4日
	 * @version v1.0.0
	 * @param pfdm
	 * @return
	 */
	public HashMap<String, Object> selectAllTotalAmount(PlatFormDateModel pfdm);
	
	/**
	 * Description：<br> 
	 * 统计年度总额
	 * @author  Lin Xu
	 * @date    2015年11月5日
	 * @version v1.0.0
	 * @param pfdm
	 * @return
	 */
	public BigDecimal selectYearAllMoney(PlatFormDateModel pfdm);
	
	/**
	 * Description：<br> 
	 * 查询平台统计数据信息
	 * @author  Lin Xu
	 * @date    2015年11月5日
	 * @version v1.0.0
	 * @param pfdm
	 * @return
	 */
	public BigDecimal[] selectPlatLoadReport(PlatFormDateModel pfdm);
	
	/**
	 * Description：<br> 
	 * 查询备用金额
	 * @author  Lin Xu
	 * @date    2015年11月6日
	 * @version v1.0.0
	 * @return
	 */
	public BizEnsureMoney selectAllEnsureMoney();
	
	/**
	 * Description：<br> 
	 * 查询备用金额数据信息
	 * @author  Lin Xu
	 * @date    2015年11月6日
	 * @version v1.0.0
	 * @param year
	 * @return
	 */
	public BigDecimal[] selectGoldDepositReport(String year);
	
}


