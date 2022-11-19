package com.yscf.core.dao.business;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.business.BizBorrow;
import com.yscf.core.model.business.BizBorrowTimeVO;
import com.yscf.core.model.ptp.financial.BizBorrowStatModel;

/**
 * 
 * Description：<br>
 * 借款数据交互层
 * 
 * @author Yu.Zhang
 * @date 2015年9月18日
 * @version v1.0.0
 */
@MapperScan("bizBorrowMapper")
public interface BizBorrowMapper extends IBaseDao<BaseEntity, String> {

	int deleteByPrimaryKey(String pid);

	int insert(BizBorrow record);

	int insertSelective(BizBorrow record);

	BizBorrow selectByPrimaryKey(String pid);

	int updateByPrimaryKeySelective(BizBorrow record);

	int updateByPrimaryKey(BizBorrow record);

	int updateStatusBatch(Map<String, Object> map);

	List<BizBorrow> selectAll(BizBorrow bizBorrow);

	PageList<BizBorrow> selectAllPage(@Param("map") BizBorrow bizBorrow, PageInfo info);

	/**
	 * 
	 * Description：<br>
	 * 申请时间为空的借款信息
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月24日
	 * @version v1.0.0
	 * @param borrow
	 * @return
	 */
	List<BizBorrow> getBorrowApplyTimeIsNull(String customerId);

	/**
	 * 
	 * Description：<br>
	 * 获取借款金额合计
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月29日
	 * @version v1.0.0
	 * @return
	 */
	BigDecimal getSumRepaymentAmt();

	/**
	 * 
	 * Description：<br>
	 * 获取标的数量，通过其类型，状态，时间段
	 * 
	 * @author JunJie.Liu
	 * @date 2015年11月5日
	 * @version v1.0.0
	 * @param start
	 *            开始时间
	 * @param end
	 *            结束时间
	 * @param borrowType
	 *            借款类型
	 * @param borrowStatus
	 *            标的状态
	 * @return
	 */
	public Integer getBorrowCountByStandard(@Param("start") String start, @Param("end") String end, @Param("borrowType") String borrowType, @Param("borrowStatus") String borrowStatus);

	/**
	 * 
	 * Description：<br>
	 * 查询借款项目类型统计占比
	 * 
	 * @author JunJie.Liu
	 * @date 2015年11月6日
	 * @version v1.0.0
	 * @param borrowStatus4
	 * @return
	 */
	List<BizBorrowStatModel> selectStatByBorrowType(@Param("borrowStatus") String borrowStatus);

	/**
	 * 
	 * Description：<br>
	 * 统计借款数量占比
	 * 
	 * @author JunJie.Liu
	 * @date 2015年11月9日
	 * @version v1.0.0
	 * @param borrowStatus4
	 *            满标
	 * @param borrowStatus0
	 *            撤销
	 * @param borrowStatus3
	 *            流标
	 * @return
	 */
	List<BizBorrowStatModel> findByBorrowCountData(@Param("borrowStatus4") String borrowStatus4, @Param("borrowStatus0") String borrowStatus0, @Param("borrowStatus3") String borrowStatus3);

	/**
	 * 
	 * Description：<br>
	 * 通过借款期限获得借款期限占比信息
	 * 
	 * @author Jie.Zou
	 * @date 2015年11月11日
	 * @version v1.0.0
	 * @param vo
	 * @return
	 */
	BizBorrowTimeVO selectBorrowTime(BizBorrowTimeVO vo);

	/**
	 * 
	 * Description：<br>
	 * 根据借款类型获取最新的标
	 * 
	 * @author JunJie.Liu
	 * @date 2015年12月2日
	 * @version v1.0.0
	 * @return
	 */
	BizBorrow getLastBorrowByBorrowType(String borrowType);

	/**
	 * 
	 * Description：<br>
	 * 获取当前时间前十分钟 待招标并且发布时间不为空的标的 并且修改发布状态
	 * 
	 * @author shiliang.feng
	 * @date 2015年12月24日
	 * @version v1.0.0
	 */
	public void updateBorrowPublish() throws RuntimeException;

	/**
	 * 
	 * Description：<br>
	 * 获得投标已完成（满标）的借款信息
	 * 
	 * @author Jie.Zou
	 * @date 2015年12月26日
	 * @version v1.0.0
	 * @return
	 */
	List<BizBorrow> selectBidOkBorrows();

	/**
	 * 
	 * Description：<br>
	 * 得到过期的借款项目
	 * 
	 * @author Jie.Zou
	 * @date 2015年12月30日
	 * @version v1.0.0
	 * @param time
	 *            当前时间
	 * @return
	 */
	List<BizBorrow> selectExpireBorrowIds(Date time);

	/**
	 * 
	 * @Description : API>>已还款列表
	 * @param map
	 *            条件Map
	 * @return 已还款列表
	 * @Author : Qing.Cai
	 * @Date : 2016年1月27日 下午9:49:15
	 */
	public List<BizBorrow> selectAlreadyRepaymentAPI(Map<String, Object> map);

	/**
	 * 
	 * Description：<br> 
	 * 根据客户ID查询未结清的借款数据条数
	 * @author  Yu.Zhang
	 * @date    2016年2月18日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	int getNoSettleCount(@Param("customerId")String userId);
	
	/**
	 * 
	 * Description：<br> 
	 * 得到需要进入招标中状态的分标
	 * @author  Jie.Zou
	 * @date    2016年2月18日
	 * @version v1.0.0
	 * @param map 需要保护父级Id和当前分标序号
	 * @return
	 */
	List<BizBorrow> getToBorrowBidding(@Param("map")Map<String, Object> map);
	
	/**
	 * 
	 * Description：<br> 
	 * 通过借款状态得到借款列表
	 * @author  Jie.Zou
	 * @date    2016年2月18日
	 * @version v1.0.0
	 * @param borStatus借款状态
	 * @return
	 */
	List<BizBorrow> getBorrowByBorStauts(@Param("borStatus")String borStatus);
	

}