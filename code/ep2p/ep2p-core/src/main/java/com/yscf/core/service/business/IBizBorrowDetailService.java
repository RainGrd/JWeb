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
 * 2015年10月10日     Jie.Zou		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.business;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.business.BizBorrow;
import com.yscf.core.model.business.BizBorrowDetail;
import com.yscf.core.model.ptp.financial.BizDetailModel;

/**
 * Description：<br>
 * 借款投标记录接口
 * 
 * @author Jie.Zou
 * @date 2015年10月10日
 * @version v1.0.0
 */
public interface IBizBorrowDetailService {
	/**
	 * 
	 * Description：<br>
	 * 分页查询
	 * 
	 * @author Jie.Zou
	 * @date 2015年10月10日
	 * @version v1.0.0
	 * @param bizBorrow
	 * @param info
	 * @return
	 */
	public PageList<BizBorrowDetail> selectAllPage(
			BizBorrowDetail borrowDetail, PageInfo info);

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
	 * @throws FrameworkException
	 */
	PageList<BizBorrowDetail> selectAllRelSelectivePage(
			BizBorrowDetail borrowDetail, PageInfo info)
			throws FrameworkException;

	/**
	 * 
	 * Description：<br>
	 * 通过借款ID得到投资记录
	 * 
	 * @author Jie.Zou
	 * @date 2015年10月12日
	 * @version v1.0.0
	 * @param pid
	 * @param info
	 * @return
	 */
	public PageList<BizBorrowDetail> getListByBorrowId(
			BizBorrowDetail bizBorrowDetail, PageInfo info);

	/**
	 * Description：根据客户id查投标记录 分页
	 * 
	 * @author heng.wang
	 * @date 2015年10月21日
	 * @version v1.0.0
	 * @param bizBorrowDetail
	 * @param info
	 *            分页对象
	 * @return PageList<BizBorrowDetail>
	 * @throws FrameworkException
	 */
	public PageList<BizBorrowDetail> selectBidRecordDetailsById(
			BizBorrowDetail bizBorrowDetail, PageInfo info);

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
	public BizBorrowDetail findBizBorrowDetail(String userId, String borrowId);

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
			String borrowId, Integer pageIndex, Integer pageSize);

	public BigDecimal sumInvestAmount(String pid, String userId);

	public void insert(BizBorrowDetail bbd);

	/**
	 * 
	 * Description：<br>
	 * 查询动态投资列表
	 * 
	 * @author heng.wang
	 * @date 2015年12月10日
	 * @version v1.0.0
	 * @param userId
	 * @param borrowId
	 * @return
	 */
	public List<BizBorrowDetail> selectInverstList();

	/**
	 * 
	 * Description：<br>
	 * 求投资记录总数
	 * 
	 * @author JunJie.Liu
	 * @date 2015年12月12日
	 * @version v1.0.0
	 * @param pid
	 * @return
	 */
	public Integer selectBizBorrowDetailByBorrowIdCount(String pid);

	/**
	 * 
	 * Description：<br>
	 * 通过借款ID或者该借款的所有投标记录
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
	 * Description：<br>
	 * 修改投标记录
	 * 
	 * @author Jie.Zou
	 * @date 2015年12月28日
	 * @version v1.0.0
	 * @param pid
	 */
	void updateByPrimaryKeySelective(BizBorrowDetail bizBorrowDetail);

	/**
	 * 
	 * Description：<br>
	 * 根据投标记录得到借款的所有投资人的投标奖励
	 * 
	 * @author Jie.Zou
	 * @date 2015年12月29日
	 * @version v1.0.0
	 * @param bids
	 * @return
	 */
	Map<String, BigDecimal> getBidRewards(BizBorrow bizBorrow,
			List<BizBorrowDetail> bids);

	/**
	 * 
	 * Description：<br>
	 * 查投资排行版
	 * 
	 * @author heng.wang
	 * @date 2016年1月3日
	 * @version v1.0.0
	 * @param bids
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
	 * @param bids
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
	public BizDetailModel getBizDetailModelByUser(String borrowId, String userId);

	/**
	 * 
	 * Description：<br>
	 * 根据用户ID获取有效的投资记录数（不包括体验标）
	 * 
	 * @author JunJie.Liu
	 * @date 2016年1月6日
	 * @version v1.0.0
	 * @param borrowId
	 * @param userId
	 * @return
	 */
	public Integer getBizDetailCountByUser(String userId);

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
	public BigDecimal getInvestTotalByBorrowId(String borrowId, String userId);

	/**
	 * Description：<br>
	 * 校验招标密码
	 * 
	 * @author shiliang.feng
	 * @date 2016年2月2日
	 * @version v1.0.0
	 * @param borrowPassword
	 *            招标密码
	 * @param userId
	 * @return
	 */
	public int vailidateBorrowPassword(String borrowPassword, String userId);

	/**
	 * 
	 * Description：<br> 
	 * 判断用户是否满足投资条件
	 * @author  JunJie.Liu
	 * @date    2016年2月25日
	 * @version v1.0.0
	 * @param pid
	 * @param userId
	 * @return
	 */
	public boolean isPassCondition(String borrowId, String userId);
}
