package com.yscf.core.dao.business;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.business.BizBorrowDetail;
import com.yscf.core.model.ptp.financial.BizDetailModel;

/**
 * Description：充值详情数据访问层接口
 * 
 * @author JingYu.Dai
 * @date 2015年10月13日
 * @version v1.0.0
 */
@MapperScan("bizBorrowDetailMapper")
public interface BizBorrowDetailMapper extends IBaseDao<BaseEntity, String> {
	int deleteByPrimaryKey(String pid);

	int insert(BizBorrowDetail record);

	int insertSelective(BizBorrowDetail record);

	BizBorrowDetail selectByPrimaryKey(String pid);

	int updateByPrimaryKeySelective(BizBorrowDetail record);

	int updateByPrimaryKey(BizBorrowDetail record);

	PageList<BizBorrowDetail> selectAllPage(
			@Param("map") BizBorrowDetail bizBorrowDetail, PageInfo info);

	/**
	 * Description：充值详情 关联所有子表 条件查询 分页
	 * 
	 * @author JingYu.Dai
	 * @date 2015年10月13日
	 * @version v1.0.0
	 * @param bizBorrowDetail
	 *            充值详情Bean
	 * @param info
	 *            分页对象
	 * @return PageList<BizBorrowDetail>
	 */
	List<BizBorrowDetail> selectAllRelSelectivePage(
			BizBorrowDetail bizBorrowDetai);

	/**
	 * 
	 * Description：充值详情 关联所有子表 条件查询 查询总记录条数
	 * 
	 * @author JingYu.Dai
	 * @date 2015年10月14日
	 * @version v1.0.0
	 * @param bizBorrowDetai
	 *            值详情Bean
	 * @return Integer 总计录条数
	 */
	Integer selectAllRelSelectiveTotalCount(BizBorrowDetail bizBorrowDetai);

	PageList<BizBorrowDetail> selectListByBorrowId(
			@Param("map") BizBorrowDetail bizBorrowDetail, PageInfo info);

	/**
	 * 
	 * Description：根据客户id查投标记录详细
	 * 
	 * @author heng.wang
	 * @date 2015年10月21日
	 * @version v1.0.0
	 * @param bizBorrowDetai
	 *            值详情Bean
	 * @return Integer 总计录条数
	 */
	public PageList<BizBorrowDetail> selectBidRecordDetailsById(
			@Param("bizBorrowDetail") BizBorrowDetail bizBorrowDetail,
			PageInfo info);

	/**
	 * 
	 * Description：统计投标记录详细
	 * 
	 * @author heng.wang
	 * @date 2015年10月27日
	 * @version v1.0.0
	 * @param bizBorrowDetai
	 *            值详情Bean
	 * @return Integer 总计录条数
	 */
	Map<String, BigDecimal> selectBidRecordDetailsByIdSum(
			@Param("bizBorrowDetail") BizBorrowDetail bizBorrowDetail);

	/**
	 * 
	 * Description：<br>
	 * 表的投资总额
	 * 
	 * @author shiliang.feng
	 * @date 2015年11月17日
	 * @version v1.0.0
	 * @param borrowId
	 *            标id
	 * @return
	 */
	public BigDecimal selectInvestmentAmountTotalByBorrowId(String borrowId);

	/**
	 * 
	 * Description：<br>
	 * 根据用户id借款id查询用户的投标记录
	 * 
	 * @author JunJie.Liu
	 * @date 2015年11月17日
	 * @version v1.0.0
	 * @param userId
	 * @param borrowId
	 * @return
	 */
	BizBorrowDetail findBizBorrowDetail(@Param("userId") String userId,
			@Param("borrowId") String borrowId);

	/**
	 * 
	 * Description：<br>
	 * 根据借款id 查询借款详情
	 * 
	 * @author shiliang.feng
	 * @date 2015年11月26日
	 * @version v1.0.0
	 * @param borrowId
	 *            借款id
	 * @param pageIndex
	 *            当前页
	 * @param pageSize
	 *            页面大小
	 * @return
	 */
	public List<BizBorrowDetail> selectBizBorrowDetailByBorrowId(
			@Param("borrowId") String borrowId,
			@Param("pageIndex") Integer pageIndex,
			@Param("pageSize") Integer pageSize);

	/**
	 * 
	 * Description：<br>
	 * 求用户对标的已投总金额
	 * 
	 * @author JunJie.Liu
	 * @date 2015年12月8日
	 * @version v1.0.0
	 * @param borrowId
	 * @param userId
	 * @return
	 */
	public BigDecimal sunInvestAmount(@Param("borrowId") String borrowId,
			@Param("userId") String userId);

	/**
	 * 
	 * Description：<br>
	 * 查询动态投资列表
	 * 
	 * @author heng.wang
	 * @date 2015年12月8日
	 * @version v1.0.0
	 * @param borrowId
	 * @param userId
	 * @return
	 */
	public List<BizBorrowDetail> selectInverstList();

	/**
	 * 
	 * Description：<br>
	 * 根据 标的id 查询投标记录总条数
	 * 
	 * @author shiliang.feng
	 * @date 2015年12月23日
	 * @version v1.0.0
	 * @param borrowId
	 * @return
	 */
	public Integer selectBizBorrowDetailByBorrowIdCount(
			@Param("borrowId") String borrowId);

	/**
	 * 
	 * Description：<br>
	 * 通过借款ID得到该借款的所有投标记录
	 * 
	 * @author Jie.Zou
	 * @date 2015年12月28日
	 * @version v1.0.0
	 * @param borrowId
	 * @return
	 */
	public List<BizBorrowDetail> selectBorrowDetailsByBorrowId(String borrowId);

	/**
	 * 
	 * @Description : 根据借款ID和条件,查询满足此条件的投资人信息列表
	 * @param borrowId
	 *            借款ID
	 * @param conditionValue
	 *            条件ID
	 * @return 投资人信息列表
	 * @Author : Qing.Cai
	 * @Date : 2015年12月28日 下午4:54:32
	 */
	public List<BizBorrowDetail> selectBorrowDetailByConditionAndBorrowId(
			@Param("borrowId") String borrowId,
			@Param("conditionValue") String conditionValue);

	/**
	 * 
	 * Description：<br>
	 * 查投资排行版
	 * 
	 * @author heng.wang
	 * @date 2016年1月3日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	public List<BizBorrowDetail> selectMyRankingList(String userId);

	/**
	 * 
	 * Description：<br>
	 * 查我的投资排行位置
	 * 
	 * @author heng.wang
	 * @date 2016年1月3日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	public List<BizBorrowDetail> selectMyRankingNumber(String userId);

	/**
	 * 
	 * Description：<br>
	 * 根据借款id和用户获取用户投资信息
	 * 
	 * @author JunJie.Liu
	 * @date 2016年1月6日
	 * @version v1.0.0
	 * @param borrowId
	 * @param userId
	 * @return
	 */
	public BizDetailModel getBizDetailModelByUser(
			@Param("borrowId") String borrowId, @Param("userId") String userId);

	/**
	 * 
	 * Description：<br>
	 * 根据用户ID获取有效的投资记录数
	 * 
	 * @author Yu.Zhang
	 * @date 2016年1月25日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	Integer getBizDetailCountByUser(@Param("userId") String userId);

	/**
	 * Description：<br>
	 * 查询用户对标的的投资总额
	 * 
	 * @author shiliang.feng
	 * @date 2016年2月2日
	 * @version v1.0.0
	 * @param borrowId
	 * @param userId
	 * @return
	 */
	public BigDecimal getInvestTotalByBorrowId(
			@Param("borrowId") String borrowId, @Param("userId") String userId);

	/**
	 * 
	 * Description：<br> 
	 * 判断用户是否满足条件
	 * @author  JunJie.Liu
	 * @date    2016年2月25日
	 * @version v1.0.0
	 * @param sql
	 * @param userId
	 * @return
	 */
	public int isPassCondition(@Param("sql") String sql,@Param("customerId") String userId);

	/**
	 * Description：<br> 
	 * 本标的 所有投资用户
	 * @author  shiliang.feng
	 * @date    2016年3月9日
	 * @version v1.0.0
	 * @param borrowId
	 * @return
	 */
	public List<BizBorrowDetail> selectBorrowDetailsCustByBorrowId(String borrowId);
	 

}