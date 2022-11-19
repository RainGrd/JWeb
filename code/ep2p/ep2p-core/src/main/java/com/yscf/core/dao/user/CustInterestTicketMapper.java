package com.yscf.core.dao.user;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.user.CustInterestTicket;

/**
 * 
 * @ClassName : CustInterestTicketMapper
 * @Description : 加息劵Mapper接口
 * @Author : Qing.Cai
 * @Date : 2016年1月8日 上午11:26:32
 */
public interface CustInterestTicketMapper extends IBaseDao<BaseEntity, String> {
	int deleteByPrimaryKey(String pid);

	int insert(CustInterestTicket record);

	int insertSelective(CustInterestTicket record);

	CustInterestTicket selectByPrimaryKey(String pid);

	int updateByPrimaryKeySelective(CustInterestTicket record);

	int updateByPrimaryKey(CustInterestTicket record);

	/**
	 * 
	 * @Description : 查询客户未使用的加息卷
	 * @param customerId
	 *            客户ID
	 * @return 客户未使用的加息劵集合
	 * @Author : Qing.Cai
	 * @Date : 2016年1月8日 下午2:14:09
	 */
	public List<CustInterestTicket> selectTomerNotUsedInterest(String customerId);

	/**
	 * 
	 * @Description : 查询客户已使用的加息卷
	 * @param customerId
	 *            客户ID
	 * @return 客户已使用的加息劵集合
	 * @Author : Qing.Cai
	 * @Date : 2016年1月8日 下午2:14:33
	 */
	public List<CustInterestTicket> selectTomerAlreadyUsedInterest(String customerId);

	/**
	 * 
	 * @Description : 修改加息劵使用状态
	 * @param useStatus
	 *            状态（1、已使用  2、未使用  3、已过期）
	 * @param pid
	 *            加息劵ID
	 * @Author : Qing.Cai
	 * @Date : 2016年1月8日 下午2:10:49
	 */
	public void updateInterestStatus(@Param("useStatus")String useStatus,@Param("pid") String pid);
	
	/**
	 * 
	 * Description：<br> 
	 * 批量修改加息卷使用状态
	 * @author  Jie.Zou
	 * @date    2016年3月4日
	 * @version v1.0.0
	 * @param useStatus
	 * @param pidArr
	 */
	public void updateUseStatusBatch(@Param("useStatus")String useStatus, @Param("pidArr") String[] pidArr);

	/**
	 * 
	 * @Description : 批量删除
	 * @param pidArr
	 *            加息劵ID数组
	 * @Author : Qing.Cai
	 * @Date : 2016年1月8日 下午2:09:20
	 */
	public void deleteBatch(String[] pidArr);

	/**
	 * 
	 * @Description : 批量修改加息卷状态
	 * @param status
	 *            需要修改的状态（1、正常 2、过期 -1、已删除）
	 * @param pidArr
	 *            加息卷ID数组
	 * @Author : Qing.Cai
	 * @Date : 2016年1月8日 下午2:07:07
	 */
	public void updateStatusBatch(@Param("status") String status, @Param("pidArr") String[] pidArr);

	/**
	 * 
	 * @Description : API--查询我的卡劵
	 * @param customerId
	 *            客户ID
	 * @return 我的卡劵
	 * @Author : Qing.Cai
	 * @Date : 2016年1月26日 上午10:35:20
	 */
	public List<CustInterestTicket> selectUserCardVolume(@Param("customerId") String customerId);

	/**
	 * 
	 * @Description : API--查询我的卡劵-已失效or已过期
	 * @param customerId
	 *            客户ID
	 * @return 我的过期or失效卡劵
	 * @Author : Qing.Cai
	 * @Date : 2016年1月26日 下午3:56:09
	 */
	public List<CustInterestTicket> selectUserCardVolumeHasExpired(@Param("customerId") String customerId);

	/**
	 * 
	 * @Description : API--查询我的加息劵列表
	 * @param map
	 *            条件Map
	 * @return 客户未使用的加息劵列表
	 * @Author : Qing.Cai
	 * @Date : 2016年1月28日 上午11:21:24
	 */
	public List<CustInterestTicket> selectUserInterestTicketAPI(Map<String, Object> map);

}