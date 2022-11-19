package com.yscf.core.dao.activity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.activity.ActRedpActDetail;

/**
 * 
 * @ClassName : ActRedpActDetailMapper
 * @Description : 红包活动明细数据层接口
 * @Author : Qing.Cai
 * @Date : 2015年10月20日 上午10:57:38
 */
public interface ActRedpActDetailMapper extends IBaseDao<BaseEntity, String> {
	int deleteByPrimaryKey(String pid);

	int insert(ActRedpActDetail record);

	int insertSelective(ActRedpActDetail record);

	ActRedpActDetail selectByPrimaryKey(String pid);

	int updateByPrimaryKeySelective(ActRedpActDetail record);

	int updateByPrimaryKey(ActRedpActDetail record);

	/**
	 * 
	 * @Description : 红包活动查询
	 * @param actRedpActDetail
	 *            红包活动明细对象
	 * @param info
	 *            分页对象
	 * @return 红包活动列表
	 * @Author : Qing.Cai
	 * @Date : 2015年10月20日 上午10:57:59
	 */
	public PageList<ActRedpActDetail> selectAllPage(@Param("actRedpActDetail") ActRedpActDetail actRedpActDetail, PageInfo info);

	/**
	 * 
	 * @Description : 查询红包活动明细列表,带分页
	 * @param actRedpActDetail
	 *            红包活动明细对象
	 * @param info
	 *            分页对象
	 * @return 红包活动明细列表
	 * @Author : Qing.Cai
	 * @Date : 2015年10月20日 上午10:58:57
	 */
	public PageList<ActRedpActDetail> selectAllPageDetail(@Param("actRedpActDetail") ActRedpActDetail actRedpActDetail, PageInfo info);

	/**
	 * 
	 * @Description : 查询红包总金额的总计
	 * @param actRedpActDetail
	 *            红包活动明细对象
	 * @return 总金额的总计
	 * @Author : Qing.Cai
	 * @Date : 2015年10月20日 上午11:29:09
	 */
	public BigDecimal selectBonusTotalByCondition(@Param("actRedpActDetail") ActRedpActDetail actRedpActDetail);

	/**
	 * 
	 * @Description : 查询已领取总额的总计
	 * @param actRedpActDetail
	 *            红包活动明细对象
	 * @return 已领取总额的总计
	 * @Author : Qing.Cai
	 * @Date : 2015年10月20日 上午11:29:29
	 */
	public BigDecimal selectHasReceiveTotalByCondition(@Param("actRedpActDetail") ActRedpActDetail actRedpActDetail);

	/**
	 * 
	 * @Description : 查询未领取金额的总计
	 * @param actRedpActDetail
	 *            红包活动明细对象
	 * @return 未领取金额的总计
	 * @Author : Qing.Cai
	 * @Date : 2015年10月20日 上午11:29:45
	 */
	public BigDecimal selectNotReceiveTotalByCondition(@Param("actRedpActDetail") ActRedpActDetail actRedpActDetail);

	/**
	 * 
	 * @Description : 查询明细领取金额的总计
	 * @param actRedpActDetail
	 *            红包活动明细对象
	 * @return 领取金额的总计
	 * @Author : Qing.Cai
	 * @Date : 2015年10月20日 上午11:45:33
	 */
	public BigDecimal selectReceiveTotalDetail(@Param("actRedpActDetail") ActRedpActDetail actRedpActDetail);

	/**
	 * 
	 * @Description : 导出查询
	 * @param map
	 *            条件
	 * @return 红包活动明细
	 * @Author : Qing.Cai
	 * @Date : 2015年11月4日 上午11:42:47
	 */
	List<ActRedpActDetail> selectAllPageDetailExport(HashMap<String, Object> map);

	/**
	 * 
	 * @Description : 查询红包参与人数的总计
	 * @param actRedpActDetail
	 *            红包活动明细对象
	 * @return 参与人的总计
	 * @Author : Qing.Cai
	 * @Date : 2015年11月5日 下午3:42:07
	 */
	public Integer selectParticipantsNumberByCondition(@Param("actRedpActDetail") ActRedpActDetail actRedpActDetail);

	/**
	 * 
	 * @Description : 批量新增
	 * @param list
	 *            需要插入的list集合
	 * @Author : Qing.Cai
	 * @Date : 2015年11月26日 上午11:07:24
	 */
	public void insertBatch(List<ActRedpActDetail> list);

	/**
	 * 
	 * @Description : 批量新增送红包
	 * @param list
	 *            需要插入的list集合
	 * @Author : Qing.Cai
	 * @Date : 2015年12月28日 下午8:52:41
	 */
	public void insertBatchSong(List<ActRedpActDetail> list);

	/**
	 * 
	 * @Description : 删除当前红包的所有明细数据
	 * @param activityId
	 *            活动ID
	 * @param redpacketId
	 *            红包ID
	 * @Author : Qing.Cai
	 * @Date : 2015年11月26日 上午11:16:15
	 */
	public void deleteRedpacketDetail(@Param("activityId") String activityId, @Param("redpacketId") String redpacketId);

	/**
	 * 
	 * @Description : 检查客户是否参加此次活动(参加=已领取红包,未参加=未领取红包)
	 * @param redpacketId
	 *            红包活动ID
	 * @param customerId
	 *            客户ID
	 * @return 0 没有参加 >0 参加
	 * @Author : Qing.Cai
	 * @Date : 2016年1月27日 上午10:58:17
	 */
	public int checkUserIsAttend(@Param("redpacketId") String redpacketId, @Param("customerId") String customerId);

	/**
	 * 
	 * @Description : 根据活动ID查询红包未领取的集合
	 * @param redpacketId
	 *            红包活动ID
	 * @return 未领取的红包活动明细集合
	 * @Author : Qing.Cai
	 * @Date : 2016年3月7日 下午5:10:12
	 */
	public List<ActRedpActDetail> selectRedListByRedpacketId(@Param("redpacketId") String redpacketId);

	/**
	 * 
	 * @Description : 修改红包领取人信息
	 * @param actRedpActDetail
	 *            红包信息对象
	 * @Author : Qing.Cai
	 * @Date : 2016年3月7日 下午7:36:25
	 */
	public void updateRedReceiveUser(@Param("actRedpActDetail") ActRedpActDetail actRedpActDetail);

	/**
	 * 
	 * @Description : 根据红包活动ID查询红包已领取情况
	 * @param redpacketId
	 *            红包活动ID
	 * @return 已领取的红包活动明细集合
	 * @Author : Qing.Cai
	 * @Date : 2016年3月8日 下午7:25:40
	 */
	public List<ActRedpActDetail> selectReceiveRedDetail(@Param("redpacketId") String redpacketId);

	/**
	 * 
	 * @Description : 查询在当前抢红包活动下用户领取金额
	 * @param redpacketId
	 *            抢红包活动ID
	 * @param userId
	 *            用户ID
	 * @return 领取金额
	 * @Author : Qing.Cai
	 * @Date : 2016年3月9日 下午3:22:20
	 */
	public Double selectUserReceiveAmount(@Param("redpacketId") String redpacketId, @Param("customerId") String customerId);
}