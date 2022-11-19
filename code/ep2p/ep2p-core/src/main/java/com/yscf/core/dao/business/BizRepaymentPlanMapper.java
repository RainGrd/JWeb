package com.yscf.core.dao.business;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.business.BizOverdueRateVO;
import com.yscf.core.model.business.BizRepaymentPlan;

/**
 * Description：<br>
 * 还款计划的mapper层
 * 
 * @author Lin Xu
 * @date 2015年10月22日
 * @version v1.0.0
 */
@MapperScan("bizRepaymentPlanMapper")
public interface BizRepaymentPlanMapper extends IBaseDao<BaseEntity, String> {

	int deleteByPrimaryKey(String pid);

	int insert(BizRepaymentPlan record);

	int insertSelective(BizRepaymentPlan record);

	BizRepaymentPlan selectByPrimaryKey(String pid);

	int updateByPrimaryKeySelective(BizRepaymentPlan record);

	int updateByPrimaryKey(BizRepaymentPlan record);

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
	public BizOverdueRateVO selectOverdueRateVO(BizOverdueRateVO vo);

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
	public Integer getSumOverdueNum(BizOverdueRateVO vo);

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
	public BigDecimal getOverdueNoRepaymentAmt(BizOverdueRateVO vo);

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
	public BigDecimal getPendingRepaymentAmt(BizOverdueRateVO vo);

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
	public BigDecimal getHasRepaymentAmt(BizOverdueRateVO vo);

	/**
	 * 
	 * @Description :前台_我的借款_待还款
	 * @param map
	 *            条件map
	 * @return 我的待还款列表
	 * @Author : Qing.Cai
	 * @Date : 2015年11月19日 上午11:26:25
	 */
	public List<BizRepaymentPlan> selectPendingRepayment(Map<String, Object> map);

	/**
	 * 
	 * @Description : 前台_我的借款_待还款的总数
	 * @param map
	 *            条件map
	 * @return 总数
	 * @Author : Qing.Cai
	 * @Date : 2015年12月10日 上午11:25:03
	 */
	public int selectPendingRepaymentCount(Map<String, Object> map);

	/**
	 * 
	 * @Description : 前台_我的借款_已还款
	 * @param map
	 *            条件map
	 * @return 我的已还款列表
	 * @Author : Qing.Cai
	 * @Date : 2015年11月19日 上午11:26:51
	 */
	public List<BizRepaymentPlan> selectRepayment(Map<String, Object> map);

	/**
	 * 
	 * @Description : 前台_我的借款_已还款的总数
	 * @param map
	 *            条件map
	 * @return 总数
	 * @Author : Qing.Cai
	 * @Date : 2015年12月10日 上午11:27:10
	 */
	public int selectRepaymentCount(Map<String, Object> map);

	/**
	 * 
	 * Description：<br>
	 * 批量保存
	 * 
	 * @author JunJie.Liu
	 * @date 2015年12月3日
	 * @version v1.0.0
	 * @param rpList
	 */
	public void saveBatch(List<BizRepaymentPlan> rpList);

	/**
	 * 
	 * Description：<br>
	 * 根据标的id,批量删除用户的还款计划
	 * 
	 * @author JunJie.Liu
	 * @date 2015年12月3日
	 * @version v1.0.0
	 * @param pid
	 * @param customerId
	 */
	public void deleteBatch(@Param("borrowId") String borrowId, @Param("customerId") String customerId);

	/**
	 * 
	 * Description：<br>
	 * 获取所有还款中的还款计划,包含时间
	 * 
	 * @author JunJie.Liu
	 * @date 2015年12月24日
	 * @version v1.0.0
	 * @param bizReplanStatus
	 * @param time
	 * @return
	 */
	public List<BizRepaymentPlan> findRepaymentPlanByStatus(@Param("bizReplanStatus") String bizReplanStatus, @Param("time") String time);

	/**
	 * 
	 * Description：<br>
	 * 获取所有还款中的还款计划，小于时间
	 * 
	 * @author JunJie.Liu
	 * @date 2015年12月24日
	 * @version v1.0.0
	 * @param bizReplanStatus
	 * @param time
	 * @return
	 */
	public List<BizRepaymentPlan> findRepaymentPlanByTime(@Param("bizReplanStatus") String bizReplanStatus, @Param("time") String time);

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
	 * 根据日期获取还款期次数据
	 * 
	 * @author Yu.Zhang
	 * @date 2016年1月25日
	 * @version v1.0.0
	 * @param string
	 * @param string2
	 * @return
	 */
	public BizRepaymentPlan getRepaymentPlanIndexByDate(@Param("borrowId") String borrowId, @Param("sysDate") String sysDate);

	/**
	 * 
	 * @Description : API--待还款列表
	 * @param map
	 *            条件Map
	 * @return 客户待还款列表
	 * @Author : Qing.Cai
	 * @Date : 2016年1月27日 下午8:38:59
	 */
	public List<BizRepaymentPlan> selectPendingRepaymentAPI(Map<String, Object> map);
	
	/**
	 * 
	 * Description：<br> 
	 * 根据借款Id和日期获得下一期还款计划数据
	 * @author  Jie.Zou
	 * @date    2016年1月28日
	 * @version v1.0.0
	 * @param borrowId
	 * @return
	 */
	public BizRepaymentPlan selectRepayByTime(@Param("borrowId") String borrowId);
	
	/**
	 * 
	 * Description：<br> 
	 * 根据借款Id得到逾期的还款计划
	 * @author  Jie.Zou
	 * @date    2016年1月28日
	 * @version v1.0.0
	 * @param borrowId
	 * @return
	 */
	public List<BizRepaymentPlan> selectOverdueRepayment(@Param("borrowId") String borrowId);

	/**
	 * 
	 * Description：<br> 
	 * 提前还款批量更新
	 * @author  Yu.Zhang
	 * @date    2016年1月28日
	 * @version v1.0.0
	 * @param updateRepaymentList
	 */
	public void batchUpdateToPrepayment(List<BizRepaymentPlan> list);
	
	/**
	 * 根据借款id,获取还款计划
	 * @param borrowId
	 * @return
	 */
	public List<BizRepaymentPlan> findListByBorrowId(@Param("borrowId") String borrowId);

}