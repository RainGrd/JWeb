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
 * 2015年10月22日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.business;

import java.math.BigDecimal;
import java.util.List;

import com.achievo.framework.exception.FrameworkException;
import com.yscf.core.model.business.BizBorrow;
import com.yscf.core.model.business.BizOverdueRateVO;
import com.yscf.core.model.business.BizRepaymentPlan;
import com.yscf.core.model.business.BizRepaymentPlanInfoVo;

/**
 * Description：<br>
 * 还款计划服务层接口
 * 
 * @author Lin Xu
 * @date 2015年10月22日
 * @version v1.0.0
 */
public interface IBizRepaymentPlanService {

	/**
	 * Description：<br>
	 * 更具条件进行修改数据
	 * 
	 * @author Lin Xu
	 * @date 2015年10月22日
	 * @version v1.0.0
	 * @param record
	 * @return
	 */
	public int updateByPrimaryKeySelective(BizRepaymentPlan record);

	/**
	 * 
	 * Description：<br>
	 * 根据逾期天数获取逾期金额、逾期期数
	 * 
	 * @author Yu.Zhang
	 * @date 2015年11月5日
	 * @version v1.0.0
	 * @param vo
	 * @return
	 */
	public List<BizOverdueRateVO> selectOverdueRateVOList();

	/**
	 * 
	 * Description：<br>
	 * 获取还款计划表总还款数
	 * 
	 * @author Yu.Zhang
	 * @date 2015年11月5日
	 * @version v1.0.0
	 * @return
	 */
	public Integer getSumReapymentPlanNum();

	/**
	 * 
	 * Description：<br>
	 * 获取还款计划表总逾期数
	 * 
	 * @author Yu.Zhang
	 * @date 2015年11月5日
	 * @version v1.0.0
	 * @return
	 */
	public Integer getSumOverdueNum();

	/**
	 * 
	 * Description：<br>
	 * 获取已还款金额
	 * 
	 * @author Yu.Zhang
	 * @date 2015年11月6日
	 * @version v1.0.0
	 * @return
	 */
	public BigDecimal getHasRepaymentAmt();

	/**
	 * 
	 * Description：<br>
	 * 获取正常待还金额
	 * 
	 * @author Yu.Zhang
	 * @date 2015年11月6日
	 * @version v1.0.0
	 * @return
	 */
	public BigDecimal getPendingRepaymentAmt();

	/**
	 * 
	 * Description：<br>
	 * 获取逾期未还金额
	 * 
	 * @author Yu.Zhang
	 * @date 2015年11月6日
	 * @version v1.0.0
	 * @return
	 */
	public BigDecimal getOverdueNoRepaymentAmt();

	/**
	 * 
	 * @Description :前台_我的借款_待还款
	 * @param bizRepaymentPlan
	 *            还款计划
	 * @return 我的待还款列表
	 * @Author : Qing.Cai
	 * @Date : 2015年11月19日 上午11:26:25
	 */
	List<BizRepaymentPlan> selectPendingRepayment(BizRepaymentPlan bizRepaymentPlan, Integer pageIndex, Integer pageSize);

	/**
	 * 
	 * @Description : 前台_我的借款_待还款的总数
	 * @param bizRepaymentPlan
	 *            还款计划
	 * @return 总数
	 * @Author : Qing.Cai
	 * @Date : 2015年11月20日 上午11:18:02
	 */
	Integer selectPendingRepaymentCount(BizRepaymentPlan bizRepaymentPlan);

	/**
	 * 
	 * @Description : 前台_我的借款_已还款
	 * @param bizRepaymentPlan
	 *            还款计划
	 * @return 我的已还款列表
	 * @Author : Qing.Cai
	 * @Date : 2015年11月19日 上午11:26:51
	 */
	List<BizRepaymentPlan> selectRepayment(BizRepaymentPlan bizRepaymentPlan, Integer pageIndex, Integer pageSize);

	/**
	 * 
	 * @Description : 前台_我的借款_已还款的总数
	 * @param bizRepaymentPlan
	 *            还款计划
	 * @return 总数
	 * @Author : Qing.Cai
	 * @Date : 2015年11月20日 上午11:18:24
	 */
	Integer selectRepaymentCount(BizRepaymentPlan bizRepaymentPlan);

	/**
	 * 
	 * Description：<br>
	 * 产生还款计划
	 * 
	 * @author JunJie.Liu
	 * @date 2015年12月3日
	 * @version v1.0.0
	 * @param borrow
	 */
	public void createRepaymentPlan(BizBorrow borrow) throws Exception;

	/**
	 * 
	 * Description：<br>
	 * 获取所有的还款中还款计划，包含时间
	 * 
	 * @author JunJie.Liu
	 * @date 2015年12月24日
	 * @version v1.0.0
	 * @param time
	 * @param bizReplanStatus1
	 * @return
	 */
	public List<BizRepaymentPlan> findRepaymentPlanByStatus(String bizReplanStatus, String time);

	/**
	 * 
	 * Description：<br>
	 * 获取所有的还款中还款计划，小于时间
	 * 
	 * @author JunJie.Liu
	 * @date 2015年12月24日
	 * @version v1.0.0
	 * @param time
	 * @param bizReplanStatus1
	 * @return
	 */
	public List<BizRepaymentPlan> findRepaymentPlanByTime(String bizReplanStatus, String time);

	/**
	 * 
	 * Description：<br>
	 * 还款
	 * 
	 * @author JunJie.Liu
	 * @date 2015年12月24日
	 * @version v1.0.0
	 * @param brp
	 * @throws FrameworkException
	 */
	public void executeRepayment(BizRepaymentPlan brp,String des) throws FrameworkException;

	/**
	 * 
	 * Description：<br>
	 * 通过借款ID得到还款计划集合
	 * 
	 * @author Jie.Zou
	 * @date 2016年1月4日
	 * @version v1.0.0
	 * @param borrowId
	 * @return
	 */
	public List<BizRepaymentPlan> selectRepaymentsByBorrowId(String borrowId);

	/**
	 * 
	 * Description：<br>
	 * （已被系统逾期垫付的借款人）将其金额还给系统，如果没有钱，更新其罚息
	 * 
	 * @author JunJie.Liu
	 * @date 2016年1月14日
	 * @version v1.0.0
	 * @param brp
	 */
	public void executeRepaymentToSystem(BizRepaymentPlan brp,String des) throws FrameworkException;

	/**
	 * 
	 * Description：<br>
	 * 更新借款人的罚息，以及收款计划的罚息
	 * 
	 * @author JunJie.Liu
	 * @date 2016年1月15日
	 * @version v1.0.0
	 * @param brp
	 * @param flag  控制是否更改投资人的罚息<br>
	 *  未垫付，则需要更新投资人的罚息，所以传true,<br>
	 *  已垫付，则只需更新借款人的罚息，所以传false<br>
	 */
	public int updateOverdue (BizRepaymentPlan brp,boolean flag) throws FrameworkException;

	/**
	 * 
	 * Description：<br>
	 * 进行垫付，如果系统备付金没有钱，更新其罚息
	 * 
	 * @author JunJie.Liu
	 * @date 2016年1月15日
	 * @version v1.0.0
	 * @param brp
	
	 */
	public void executePayment(BizRepaymentPlan brp) throws FrameworkException;

	/**
	 * 
	 * Description：<br>
	 * 创建预期还款计划
	 * 
	 * @author Jie.Zou
	 * @date 2016年1月21日
	 * @version v1.0.0
	 * @param borrow
	 */
	public void createForecastRepaymentPlan(BizBorrow borrow);

	/**
	 * 
	 * Description：<br>
	 * 得到待还款详情VO
	 * 
	 * @author Jie.Zou
	 * @date 2016年1月25日
	 * @version v1.0.0
	 * @param repayId
	 *            还款计划ID
	 * @return
	 */
	public BizRepaymentPlanInfoVo getRepaymentInfo(String repayId);

	/**
	 * 
	 * @Description : API--待还款列表
	 * @param userId
	 *            用户ID
	 * @param pageIndex
	 *            页数
	 * @param pageSize
	 *            页码
	 * @return 客户的待还款列表
	 * @Author : Qing.Cai
	 * @Date : 2016年1月27日 下午8:42:40
	 */
	public List<BizRepaymentPlan> selectPendingRepaymentAPI(String userId, Integer pageIndex, Integer pageSize);
	
	/**
	 * 根据借款id，获取还款计划
	 * @param pid
	 * @return
	 */
	public List<BizRepaymentPlan> findListByBorrowId(String pid);
}
