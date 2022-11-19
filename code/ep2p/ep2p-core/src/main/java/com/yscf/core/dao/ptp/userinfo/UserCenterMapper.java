/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 用户中心账户总览和我的投资mapper
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年11月24日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.dao.ptp.userinfo;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.ptp.investment.DueInDetailModel;
import com.yscf.core.model.ptp.investment.InvestDueInModel;
import com.yscf.core.model.ptp.investment.InvestmentInfoModel;
import com.yscf.core.model.ptp.investment.InviteTendersModel;
import com.yscf.core.model.ptp.investment.TransferTendersMode;

/**
 * Description：<br> 
 * 用户中心账户总览和我的投资mapper
 * @author  Lin Xu
 * @date    2015年11月24日
 * @version v1.0.0
 */
@MapperScan("userCenterMapper")
public interface UserCenterMapper extends IBaseDao<BaseEntity, String> {
	
	/**
	 * Description：<br> 
	 * 获取用户的待收金额信息
	 * @author  Lin Xu
	 * @date    2015年11月25日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	public HashMap<String, BigDecimal> getUserDueInMoney(String userId);
	
	/**
	 * Description：<br> 
	 * 获取用户投资利息
	 * @author  Lin Xu
	 * @date    2015年11月25日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	public BigDecimal getUserInvestInterest(String userId);

	
	/**
	 * Description：<br> 
	 * 加息利息
	 * @author  Lin Xu
	 * @date    2015年11月25日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	public BigDecimal getUserRateInterest(String userId);
	
	
	/**
	 * Description：<br> 
	 * 投资奖励
	 * @author  Lin Xu
	 * @date    2015年11月25日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	public BigDecimal getUserInIncentive(String userId);
	
	
	/**
	 * Description：<br> 
	 * 红包收益
	 * @author  Lin Xu
	 * @date    2015年11月25日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	public BigDecimal getUserRedEnvelope(String userId);
	
	
	
	/**
	 * Description：<br> 
	 * 推荐奖励
	 * @author  Lin Xu
	 * @date    2015年11月25日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	public BigDecimal getUserRecommendedAwards(String userId);
	
	
	/**
	 * Description：<br> 
	 * 获取用户的投资总额
	 * @author  Lin Xu
	 * @date    2015年11月28日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	public BigDecimal getUserInvestMoney(String userId);
	
	
	/**
	 * Description：<br> 
	 * 获取待收金额
	 * @author  Lin Xu
	 * @date    2015年11月28日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	public BigDecimal getUserIDueinMoney(String userId);
	
	
	/**
	 * Description：<br> 
	 * 净利息
	 * @author  Lin Xu
	 * @date    2015年11月28日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	public BigDecimal getUserNetmargin(String userId);
	
	/**
	 * Description：<br> 
	 * 获取我的投资-待收中的投标信息
	 * @author  Lin Xu
	 * @date    2016年1月23日
	 * @version v1.0.0
	 * @param map
	 * @return
	 */
	public PageList<InvestDueInModel> selectDueinBorrow(@Param("qmap")HashMap<String, Object> map,PageInfo pageInfo);
	
	/**
	 * Description：<br> 
	 * 查询我的投资-招标中投标信息
	 * @author  Lin Xu
	 * @date    2016年1月26日
	 * @version v1.0.0
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public PageList<InviteTendersModel> selectInviteTendersInfo(@Param("qmap")HashMap<String, Object> map,PageInfo pageInfo);
	
	/**
	 * Description：<br> 
	 * 查询我的投资-转让投标信息
	 * @author  Lin Xu
	 * @date    2016年1月26日
	 * @version v1.0.0
	 * @param map
	 * @param pageInfo
	 * @return
	 */
	public PageList<TransferTendersMode> selectTransferInfo(@Param("qmap")HashMap<String, Object> map,PageInfo pageInfo);
	
	/**
	 * Description：<br> 
	 * 查询我的投资-已结清信息数据
	 * @author  Lin Xu
	 * @date    2015年11月30日
	 * @version v1.0.0
	 * @param map
	 * @return
	 */
	public PageList<InvestmentInfoModel> selectInvestmentInfo(@Param("qmap")HashMap<String, Object> map,PageInfo pageInfo);
	
	
	/**
	 * Description：<br> 
	 * 查询投资排名
	 * @author  Lin Xu
	 * @date    2015年12月1日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	public HashMap<String, Object> selectInvestmentRanking(String userId);
	
	/**
	 * 
	 * Description：<br> 
	 * 根据借款id，获取已结清项目的投资信息
	 * @author  JunJie.Liu
	 * @date    2016年1月29日
	 * @version v1.0.0
	 * @param borrowId
	 * @return
	 */
	public InvestmentInfoModel selectInvestmentInfoByBorrowId(@Param("userId") String userId,
			@Param("borrowId") String borrowId);
	
	/**
	 * Description：<br> 
	 * 根据借款的id和用户查询对应投资的详细信息
	 * @author  Lin Xu
	 * @date    2016年3月2日
	 * @version v1.0.0
	 * @param userId
	 * @param borrowId
	 * @return
	 */
	public List<DueInDetailModel> selectDueInDetailList(@Param("qmap")HashMap<String, Object> map);
	
}


