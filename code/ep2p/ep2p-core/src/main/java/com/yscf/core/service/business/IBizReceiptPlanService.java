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
 * 2015年10月20日     fengshiliang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.business;

import java.math.BigDecimal;
import java.util.List;

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.business.BizReceiptPlan;
import com.yscf.core.model.business.BizReceiptTransfer;
import com.yscf.core.model.comm.ExportObjectModel;
import com.yscf.core.model.ptp.financial.BizReceiptPlanModel;
import com.yscf.core.model.ptp.investment.BizReceiptPlanInfoModel;
import com.yscf.core.model.reflect.InvestUser;



/**
 * 
 * Description：<br> 
 * 收款计划列表
 * @author  JunJie.Liu
 * @date    2015年10月23日
 * @version v1.0.0
 */
public interface IBizReceiptPlanService {

	public BizReceiptPlan getBizReceiptPlanById(String pid);
	
	public int updateByPrimaryKeySelective(BizReceiptPlan receiptTransf);
	
	public int insert(BizReceiptPlan bizReceiptPlan);
	
	/**
	 * 
	 * Description：<br> 
	 * 查询用户项目明细
	 * @author  JunJie.Liu
	 * @date    2015年11月11日
	 * @version v1.0.0
	 * @param pageSize 
	 * @param pageIndex 
	 * @param vos
	 * 			查询条件
	 * @return
	 */
	public List<BizReceiptPlanModel> findList(BizReceiptPlanModel condition,Integer pageIndex,Integer pageSize );
	
	/**
	 * 
	 * Description：<br> 
	 * 查询用户项目明细总条数
	 * @author  JunJie.Liu
	 * @date    2015年11月11日
	 * @version v1.0.0
	 * @param vos
	 * 			查询条件
	 * @return
	 */
	public Integer findTotalCount(BizReceiptPlanModel condition);
	
	/**
	 * 
	 * Description：<br> 
	 * 查询转让详情
	 * @author  JunJie.Liu
	 * @date    2015年11月12日
	 * @version v1.0.0
	 * @param condition
	 * @return
	 */
	public BizReceiptPlanModel findReceiptPlanById(BizReceiptPlanModel condition);
	
	/**
	 * 
	 * Description：<br> 
	 * 转让确认
	 * @author  JunJie.Liu
	 * @date    2015年11月12日
	 * @version v1.0.0
	 * @param condition
	 * @return
	 */
	public String updateReceiptPlanById(BizReceiptPlanModel condition);

	public PageList<BizReceiptPlanInfoModel> searchAllPage(
			BizReceiptPlanInfoModel brpm, PageInfo info);

	public BizReceiptPlanInfoModel sumAllPage(BizReceiptPlanInfoModel brpm);

	public List<BizReceiptPlanInfoModel> selectBizReceiptPlanInfoModelVOEom(
			BizReceiptPlanInfoModel bizReceiptPlanInfoModel,
			ExportObjectModel eom);
	
	/**
	 * 
	 * Description：<br> 
	 * 根据收款计划，获取到本息
	 * @author  JunJie.Liu
	 * @date    2015年12月24日
	 * @version v1.0.0
	 * @param recPlaId
	 * @return
	 */
	public BigDecimal sumBenXi(String recPlaId);
	
	/**
	 * 
	 * Description：<br> 
	 * 根据还款计划，获取所有的收款计划
	 * @author  JunJie.Liu
	 * @date    2015年12月24日
	 * @version v1.0.0
	 * @param pid
	 * @return
	 */
	public List<BizReceiptPlan> findListByRepaymentId(String pid);
	
	/**
	 * 
	 * Description：<br> 
	 * 获取用户标的待收本息
	 * @author  JunJie.Liu
	 * @date    2016年1月6日
	 * @version v1.0.0
	 * @param borrowId
	 * @param userId
	 * @return
	 */
	public BigDecimal getSurpAmount(String borrowId, String userId);
	
	/**
	 * 
	 * Description：<br> 
	 * 获取用户标的已收本息
	 * @author  JunJie.Liu
	 * @date    2016年1月6日
	 * @version v1.0.0
	 * @param borrowId
	 * @param userId
	 * @return
	 */
	public BigDecimal getAlreadyAmount(String borrowId, String userId);

	/**
	 * 
	 * Description：<br> 
	 * 获取用户标的本息，不含加息
	 * @author  JunJie.Liu
	 * @date    2016年1月6日
	 * @version v1.0.0
	 * @param borrowId
	 * @param userId
	 * @return
	 */
	public BigDecimal sumCapitalAndInterest(String borrowId, String userId);

	/**
	 * 
	 * Description：<br> 
	 * 获取用户标的加息
	 * @author  JunJie.Liu
	 * @date    2016年1月6日
	 * @version v1.0.0
	 * @param borrowId
	 * @param userId
	 * @return
	 */
	public BigDecimal sumNetHike(String borrowId, String userId);
	
	/**
	 * 
	 * Description：<br> 
	 * 根据状态，求vip的总利息
	 * @author  JunJie.Liu
	 * @date    2016年1月15日
	 * @version v1.0.0
	 * @param bizReceiptplanStatus5
	 * @param pid
	 * @return
	 */
	public BigDecimal sumVipAmount(String bizReceiptplanStatus, String pid);

	/**
	 * 
	 * Description：<br> 
	 * 根据还款计划，获取非vip的收款计划集合
	 * @author  JunJie.Liu
	 * @date    2016年1月15日
	 * @version v1.0.0
	 * @param repaymentId
	 * @param bizReceiptplanStatus5
	 * @return
	 */
	public List<BizReceiptPlan> findListByRepaymentIdNotVip(String repaymentId,
			String bizReceiptplanStatus5);

	/**
	 * 
	 * Description：<br> 
	 * 根据用户和标的id，求用户的本金
	 * @author  JunJie.Liu
	 * @date    2016年1月15日
	 * @version v1.0.0
	 * @param borrowId
	 * @param customerId
	 * @return
	 */
	public BigDecimal sumBenJin(String borrowId, String customerId);
	
	/**
	 * 
	 * Description：<br> 
	 * 根据债权id，及来源类型，获取债权收款计划
	 * @author  JunJie.Liu
	 * @date    2016年1月20日
	 * @version v1.0.0
	 * @param parentTransferId
	 * @param investSrcType
	 * @return
	 */
	public List<BizReceiptPlan> findListByTransfer(String parentTransferId,
			String investSrcType);

	/**
	 * 
	 * Description：<br> 
	 * 购买债权时，更新原有计划为已转让
	 * @author  JunJie.Liu
	 * @date    2016年1月21日
	 * @version v1.0.0
	 * @param pid
	 * @param investSrcType1
	 * @param bizReceiptplanStatus4
	 * @return
	 */
	public int updateByTransfer(String pid, String investSrcType1,
			String bizReceiptplanStatus4);
	
	/**
	 * 
	 * Description：<br> 
	 * 批量新增收款计划
	 * @author  JunJie.Liu
	 * @date    2016年1月21日
	 * @version v1.0.0
	 * @param list
	 */
	public void insertBatch(List<BizReceiptPlan> list);

	/**
	 * 
	 * Description：<br> 
	 * 获取用户的收款计划，如果是通过购买债权得到的收款计划，则需传入债权id
	 * @author  JunJie.Liu
	 * @date    2016年1月25日
	 * @version v1.0.0
	 * @param userId
	 * @param borrowId
	 * @param transferId
	 * @param investSrcType
	 * 				计划来源  1原标  2债权购买
	 * @return
	 */
	public List<BizReceiptPlan> findList(String userId, String borrowId,String investSrcType,
			 String transferId);
	
	/**
	 * 
	 * Description：<br> 
	 * 进行转让<br>
	 * 
	 * 将收款计划列表改为转让中，并且将转让id加上，如果有转让id，则无需修改<br>
	 * 如果参数transferId不为空，则表示再次转让,并且更新收款计划为转让中<br>
	 * 产生新的债权编号，保存转让记录<br>
	 * @author  JunJie.Liu
	 * @date    2016年1月26日
	 * @version v1.0.0
	 * @param borrowId
	 * @param transferId
	 * @param userId
	 * @param transferAmount
	 * @param brt
	 * @return
	 */
	public String updateTransfer(String borrowId, String transferId,
			String userId, BigDecimal transferAmount, BizReceiptTransfer brt);
	

	
	/**
	 * 
	 * Description：<br> 
	 * //客户待收明细
	 * @author  wangheng
	 * @date    2016年2月29日
	 * @version v1.0.0
	 * @param custDueinWater
	 * @param pageInfo
	 * @return
	 */
	public PageList<BizReceiptPlan> selectAllPage(BizReceiptPlan bizReceiptPlan,
			PageInfo pageInfo);
	
	/**
	 * 
	 * Description：<br> 
	 * 系统还加息
	 * @author  JunJie.Liu
	 * @date    2016年3月4日
	 * @version v1.0.0
	 * @param time
	 */
	public void repayNetHike(String time) throws FrameworkException;

	/**
	 *  将垫付的vip状态，改成已结清
	 */
	public void updateStatusByVip(String srcStatus,
			String toStatus, String brpId);

	public List<InvestUser> findInvestList(String borrowId, String userId);
	
	
}
