package com.yscf.core.dao.business;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.business.BizReceiptPlan;
import com.yscf.core.model.ptp.financial.BizReceiptPlanModel;
import com.yscf.core.model.ptp.investment.BizReceiptPlanInfoModel;
import com.yscf.core.model.reflect.InvestUser;
@MapperScan("bizReceiptPlanMapper")
public interface BizReceiptPlanMapper extends IBaseDao<BaseEntity, String> {
    int deleteByPrimaryKey(String pid);

    int insert(BizReceiptPlan record);

    int insertSelective(BizReceiptPlan record);

    BizReceiptPlan selectByPrimaryKey(String pid);

    int updateByPrimaryKeySelective(BizReceiptPlan record);

    int updateByPrimaryKey(BizReceiptPlan record);
    
    /**
	 * 
	 * Description：<br> 
	 * 查询用户项目明细
	 * @author  JunJie.Liu
	 * @date    2015年11月11日
	 * @version v1.0.0
	 * @param vos
	 * 			查询条件
     * @param pageSize 
     * @param pageIndex 
	 * @return
	 */
	List<BizReceiptPlanModel> findList(@Param("vos") BizReceiptPlanModel vos,
			@Param("pageIndex") Integer pageIndex,@Param("pageSize") Integer pageSize);

	/**
	 * 
	 * Description：<br> 
	 * 查询用户项目明细
	 * @author  JunJie.Liu
	 * @date    2015年11月11日
	 * @version v1.0.0
	 * @param vos
	 * 			查询条件
	 * @return
	 */
	Integer findTotalCount(@Param("vos") BizReceiptPlanModel condition);

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
	BizReceiptPlanModel findReceiptPlanById(@Param("vos") BizReceiptPlanModel condition);

	PageList<BizReceiptPlanInfoModel> searchAllPage(
			@Param("map") BizReceiptPlanInfoModel brpm, PageInfo info);
	
	BigDecimal sumAllPage(@Param("map") BizReceiptPlanInfoModel brpm);

	List<BizReceiptPlanInfoModel> selectBizReceiptPlanInfoVOEom(
			HashMap<String, Object> maps);

	BigDecimal sumBenXi(@Param("pid") String recPlaId);

	List<BizReceiptPlan> findListByRepaymentId(@Param("rpmId") String rpmId);

	BigDecimal getSurpAmount(@Param("borrowId") String borrowId, @Param("userId") String userId);

	BigDecimal getAlreadyAmount(@Param("borrowId") String borrowId,@Param("userId") String userId);

	BigDecimal sumCapitalAndInterest(@Param("borrowId") String borrowId,@Param("userId") String userId);

	BigDecimal sumNetHike(@Param("borrowId") String borrowId,@Param("userId") String userId);
	
	/**
	 * 
	 * Description：<br> 
	 * 根据状态，求vip的总利息
	 * @author  JunJie.Liu
	 * @date    2016年1月15日
	 * @version v1.0.0
	 * @param bizReceiptplanStatus
	 * @param pid
	 * @return
	 */
	BigDecimal sumVipAmount(@Param("bizReceiptplanStatus")String bizReceiptplanStatus,@Param("repaymentPid") String repaymentPid);

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
	List<BizReceiptPlan> findListByRepaymentNotVip(@Param("repaymentId") String repaymentId,
			@Param("bizReceiptplanStatus") String bizReceiptplanStatus);

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
	BigDecimal sumBenJin(@Param("borrowId") String borrowId,@Param("customerId") String customerId);

	/**
	 * 
	 * Description：<br> 
	 * 根据债权转让Id，及来源类型，获取到收款计划
	 * @author  JunJie.Liu
	 * @date    2016年1月20日
	 * @version v1.0.0
	 * @param transferId
	 * @param investSrcType
	 * @return
	 */
	List<BizReceiptPlan> findListByTransfer(@Param("transferId") String transferId,
			@Param("playType") String playType);

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
	public int updateByTransfer(@Param("transferId") String transferId, @Param("investSrcType") String investSrcType,
			@Param("bizReceiptplanStatus") String bizReceiptplanStatus);

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
	public List<BizReceiptPlan> findListByUser(@Param("userId") String userId, @Param("borrowId") String borrowId,
			@Param("playType") String playType,@Param("transferId") String transferId );

	void updateTransfer(@Param("borrowId") String borrowId,@Param("userId")  String userId,@Param("transferId")  String transferId,
			@Param("bizReceiptplanStatus") String bizReceiptplanStatus,@Param("transferId2")  String transferId2);

	/**
	 * 
	 * Description：<br> 
	 * 根据借款ID查询需要还款的收款计划列表
	 * @author  Yu.Zhang
	 * @date    2016年1月28日
	 * @version v1.0.0
	 * @param pid
	 * @return
	 */
	List<BizReceiptPlan> findValidByRepaymentId(@Param("rpmId")String rpmId);

	/**
	 * 
	 * Description：<br> 
	 * 提前还款批量更新
	 * @author  Yu.Zhang
	 * @date    2016年1月28日
	 * @version v1.0.0
	 * @param updateReceiptPlanList
	 */
	void batchUpdateToPrepayment(List<BizReceiptPlan> updateReceiptPlanList);
	
	/**
	 * 
	 * Description：<br> 
	 * 待收本金金额明细
	 * @author  wangheng
	 * @date    2016年2月29日
	 * @version v1.0.0
	 * @param BizReceiptPlan
	 */
	public PageList<BizReceiptPlan> selectAllPage(@Param("bizReceiptPlan")BizReceiptPlan bizReceiptPlan,  PageInfo pageInfo);
	
	/**
	 * 
	 * Description：<br> 
	 * 根据加息待收状态，及时间，获取收款计划
	 * @author  JunJie.Liu
	 * @date    2016年3月4日
	 * @version v1.0.0
	 * @param netHikeStatus0
	 * @param time
	 * @return
	 */
	public List<BizReceiptPlan> findListByNetHikeStatus(@Param("netHikeStatus")String netHikeStatus,
			@Param("time")String time);
	
	/**
	 * 
	 * Description：<br> 
	 * 将垫付的vip，状态改为已结清
	 * @author  JunJie.Liu
	 * @date    2016年3月5日
	 * @version v1.0.0
	 * @param srcStatus
	 * @param toStatus
	 * @param brpId
	 */
	public void updateStatusByVip(@Param("srcStatus")String srcStatus, @Param("toStatus")String toStatus, @Param("brpId")String brpId);
	


	/**
	 * 
	 * Description：<br> 
	 * 根据借款ID，回款人ID，借款期限获取最后一期数据
	 * @author  Yu.Zhang
	 * @date    2016年3月10日
	 * @version v1.0.0
	 * @param borrowId
	 * @param customerId
	 * @param borDeadline
	 * @return 
	 */
	public BizReceiptPlan getLastDeline(@Param("borrowId")String borrowId, @Param("customerId")String customerId, @Param("borDeadline")String borDeadline);


	/**
	 * 
	 * Description：<br> 
	 * 获取投资人列表信息
	 * @author  JunJie.Liu
	 * @date    2016年3月5日
	 * @version v1.0.0
	 * @param srcStatus
	 * @param toStatus
	 * @param brpId
	 */
	public List<InvestUser> findInvestList(@Param("borrowId") String borrowId,@Param("userId") String userId);
	
}