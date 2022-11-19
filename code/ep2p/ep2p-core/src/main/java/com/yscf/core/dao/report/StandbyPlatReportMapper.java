/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 平台数据和备用金报表
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年11月4日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.dao.report;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.financial.BizEnsureMoney;
import com.yscf.core.model.report.PlatFormDateModel;

/**
 * Description：<br> 
 * 平台数据和备用金报表
 * @author  Lin Xu
 * @date    2015年11月4日
 * @version v1.0.0
 */
@MapperScan("standbyPlatReportMapper")
public interface StandbyPlatReportMapper extends IBaseDao<BaseEntity, String> {

	/**
	 * Description：<br> 
	 * 统计累计投资 
	 * @author  Lin Xu
	 * @date    2015年11月5日
	 * @version v1.0.0
	 * @param pfdm
	 * @return
	 */
	public BigDecimal selectCumulativeInvest(PlatFormDateModel pfdm);
	
	/**
	 * Description：<br> 
	 * 统计按年累计投资列表
	 * @author  Lin Xu
	 * @date    2015年11月5日
	 * @version v1.0.0
	 * @param pfdm
	 * @return
	 */
	public List<PlatFormDateModel> selectCumulativeInvList(PlatFormDateModel pfdm);
	
	/**
	 * Description：<br> 
	 * 统计已还本息
	 * @author  Lin Xu
	 * @date    2015年11月5日
	 * @version v1.0.0
	 * @param pfdm
	 * @return
	 */
	public BigDecimal selectAlsoPrincipal(PlatFormDateModel pfdm);
	
	/**
	 * Description：<br> 
	 * 统计已还本息列表
	 * @author  Lin Xu
	 * @date    2015年11月5日
	 * @version v1.0.0
	 * @param pfdm
	 * @return
	 */
	public List<PlatFormDateModel> selectAlsoPrincipalList(PlatFormDateModel pfdm);
	
	/**
	 * Description：<br> 
	 * 统计累计用户受益
	 * @author  Lin Xu
	 * @date    2015年11月5日
	 * @version v1.0.0
	 * @param pfdm
	 * @return
	 */
	public BigDecimal selectUserBenefit(PlatFormDateModel pfdm);
	
	/**
	 * Description：<br> 
	 * 统计累计用户受益列表 
	 * @author  Lin Xu
	 * @date    2015年11月5日
	 * @version v1.0.0
	 * @param pfdm
	 * @return
	 */
	public List<PlatFormDateModel> selectUserBenefitList(PlatFormDateModel pfdm);
	
	/**
	 * Description：<br> 
	 * 统计已发放收益 
	 * @author  Lin Xu
	 * @date    2015年11月5日
	 * @version v1.0.0
	 * @param pfdm
	 * @return
	 */
	public BigDecimal selectGrantProfit(PlatFormDateModel pfdm);
	
	/**
	 * Description：<br> 
	 * 统计已发放收益列表
	 * @author  Lin Xu
	 * @date    2015年11月5日
	 * @version v1.0.0
	 * @param pfdm
	 * @return
	 */
	public List<PlatFormDateModel> selectGrantProfitList(PlatFormDateModel pfdm);
	
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
	 * 查询备用金额
	 * @author  Lin Xu
	 * @date    2015年11月6日
	 * @version v1.0.0
	 * @return
	 */
	public BigDecimal selectGoldDepositReport(@Param("yearmonth")String yearmonth);
	
}


