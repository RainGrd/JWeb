/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 定义调度中的报表统计映射
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年12月23日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.dao.task;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.task.AlsoPrincipalReport;
import com.yscf.core.model.task.CumulativeIinvestReport;
import com.yscf.core.model.task.GrantProfitReport;
import com.yscf.core.model.task.UserBenefitReport;

/**
 * Description：<br> 
 * 定义调度中的报表统计映射
 * @author  Lin Xu
 * @date    2015年12月23日
 * @version v1.0.0
 */
@MapperScan("reportCountQuartzMapper")
public interface ReportCountQuartzMapper extends IBaseDao<BaseEntity, String>{

	/**
	 * Description：<br> 
	 * 查询需要插入的数据信息
	 * @author  Lin Xu
	 * @date    2015年12月23日
	 * @version v1.0.0
	 * @return
	 */
	public List<HashMap<String, Object>> selectThisAccumulReport();
	
	/**
	 * Description：<br> 
	 * 根据年月查询累计投资的数据信息
	 * @author  Lin Xu
	 * @date    2015年12月23日
	 * @version v1.0.0
	 * @param ayear
	 * @param amonth
	 * @return
	 */
	public List<HashMap<String,Object>> selectAccumulatedInvestReport(@Param("ayear")String ayear, @Param("amonth")String amonth);
	
	/**
	 * Description：<br> 
	 * 根据年月删除对应的累计投资的报表信息
	 * @author  Lin Xu
	 * @date    2015年12月23日
	 * @version v1.0.0
	 * @param ayear
	 * @param amonth
	 */
	public void deleteAccumulatedInvestReport(@Param("ayear")String ayear, @Param("amonth")String amonth);
	
	/**
	 * Description：<br> 
	 * 插入新的累计投资信息
	 * @author  Lin Xu
	 * @date    2015年12月23日
	 * @version v1.0.0
	 * @param param
	 */
	public void insertAccumulatedInvestReport(CumulativeIinvestReport param);
	
	
	//---------------------------------已还本息------------------------------------------------
	/**
	 * Description：<br> 
	 * 查询需要插入的数据信息
	 * @author  Lin Xu
	 * @date    2015年12月23日
	 * @version v1.0.0
	 * @return
	 */
	public List<HashMap<String, Object>> selectThisAlsoPrincipalReport();
	
	/**
	 * Description：<br> 
	 * 根据年月查询累计投资的数据信息
	 * @author  Lin Xu
	 * @date    2015年12月23日
	 * @version v1.0.0
	 * @param ayear
	 * @param amonth
	 * @return
	 */
	public List<HashMap<String,Object>> selectAlsoPrincipalReport(@Param("ayear")String ayear, @Param("amonth")String amonth);
	
	/**
	 * Description：<br> 
	 * 根据年月删除对应的累计投资的报表信息
	 * @author  Lin Xu
	 * @date    2015年12月23日
	 * @version v1.0.0
	 * @param ayear
	 * @param amonth
	 */
	public void deleteAlsoPrincipalReport(@Param("ayear")String ayear, @Param("amonth")String amonth);
	
	/**
	 * Description：<br> 
	 * 插入新的累计投资信息
	 * @author  Lin Xu
	 * @date    2015年12月23日
	 * @version v1.0.0
	 * @param param
	 */
	public void insertAlsoPrincipalReport(AlsoPrincipalReport param);
	
	
	//---------------------------------用户收益------------------------------------------------
	/**
	 * Description：<br> 
	 * 查询需要插入的数据信息
	 * @author  Lin Xu
	 * @date    2015年12月23日
	 * @version v1.0.0
	 * @return
	 */
	public List<HashMap<String, Object>> selectThisUserBenefitReport();
	
	/**
	 * Description：<br> 
	 * 根据年月查询累计投资的数据信息
	 * @author  Lin Xu
	 * @date    2015年12月23日
	 * @version v1.0.0
	 * @param ayear
	 * @param amonth
	 * @return
	 */
	public List<HashMap<String,Object>> selectUserBenefitReport(@Param("ayear")String ayear, @Param("amonth")String amonth);
	
	/**
	 * Description：<br> 
	 * 根据年月删除对应的累计投资的报表信息
	 * @author  Lin Xu
	 * @date    2015年12月23日
	 * @version v1.0.0
	 * @param ayear
	 * @param amonth
	 */
	public void deleteUserBenefitReport(@Param("ayear")String ayear, @Param("amonth")String amonth);
	
	/**
	 * Description：<br> 
	 * 插入新的累计投资信息
	 * @author  Lin Xu
	 * @date    2015年12月23日
	 * @version v1.0.0
	 * @param param
	 */
	public void insertUserBenefitReport(UserBenefitReport param);
	
	
	
	//---------------------------------累计已发放收益------------------------------------------------
	/**
	 * Description：<br> 
	 * 查询需要插入的数据信息
	 * @author  Lin Xu
	 * @date    2015年12月23日
	 * @version v1.0.0
	 * @return
	 */
	public List<HashMap<String, Object>> selectThisGrantProfitReport();
	
	/**
	 * Description：<br> 
	 * 根据年月查询累计投资的数据信息
	 * @author  Lin Xu
	 * @date    2015年12月23日
	 * @version v1.0.0
	 * @param ayear
	 * @param amonth
	 * @return
	 */
	public List<HashMap<String,Object>> selectGrantProfitReport(@Param("ayear")String ayear, @Param("amonth")String amonth);
	
	/**
	 * Description：<br> 
	 * 根据年月删除对应的累计投资的报表信息
	 * @author  Lin Xu
	 * @date    2015年12月23日
	 * @version v1.0.0
	 * @param ayear
	 * @param amonth
	 */
	public void deleteGrantProfitReport(@Param("ayear")String ayear, @Param("amonth")String amonth);
	
	/**
	 * Description：<br> 
	 * 插入新的累计投资信息
	 * @author  Lin Xu
	 * @date    2015年12月23日
	 * @version v1.0.0
	 * @param param
	 */
	public void insertGrantProfitReport(GrantProfitReport param);
	

}


