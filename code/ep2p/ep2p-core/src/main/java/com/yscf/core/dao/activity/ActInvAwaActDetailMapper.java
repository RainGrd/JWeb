package com.yscf.core.dao.activity;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.activity.ActInvAwaActDetail;

public interface ActInvAwaActDetailMapper extends IBaseDao<BaseEntity, String> {
	int deleteByPrimaryKey(String pid);

	int insert(ActInvAwaActDetail record);

	int insertSelective(ActInvAwaActDetail record);

	ActInvAwaActDetail selectByPrimaryKey(String pid);

	int updateByPrimaryKeySelective(ActInvAwaActDetail record);

	int updateByPrimaryKey(ActInvAwaActDetail record);

	/**
	 * Description：根据pid查询客户积分明细,带分页功能的
	 * 
	 * @author heng.wang
	 * @date 2015年10月9日
	 * @version v1.0.0
	 * @param userName
	 *            系统客户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public PageList<ActInvAwaActDetail> selectCusPointDetailsById(@Param("actInvAwaActDetail") ActInvAwaActDetail actInvAwaActDetail, PageInfo info);

	/**
	 * Description：积分求和,带分页功能的
	 * 
	 * @author heng.wang
	 * @date 2015年10月24日
	 * @version v1.0.0
	 * @param userName
	 *            系统客户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public Map<String, BigDecimal> selectCusPointDetailsByIdSum(@Param("actInvAwaActDetail") ActInvAwaActDetail actInvAwaActDetail);

	/**
	 * Description：根据pid查询客户投资奖励经验明细,带分页功能的
	 * 
	 * @author heng.wang
	 * @date 2015年10月9日
	 * @version v1.0.0
	 * @param userName
	 *            系统客户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public Map<String, BigDecimal> selectJingyanDetailsByIdSum(@Param("actInvAwaActDetail") ActInvAwaActDetail actInvAwaActDetail);

	/**
	 * Description：统计经验明细
	 * 
	 * @author heng.wang
	 * @date 2015年10月24日
	 * @version v1.0.0
	 * @param userName
	 *            系统客户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public PageList<ActInvAwaActDetail> selectJingyanDetailsById(@Param("actInvAwaActDetail") ActInvAwaActDetail actInvAwaActDetail, PageInfo info);

	/**
	 * Description：根据pid查询客户投资奖励加息明细,带分页功能的
	 * 
	 * @author heng.wang
	 * @date 2015年10月9日
	 * @version v1.0.0
	 * @param userName
	 *            系统客户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public PageList<ActInvAwaActDetail> selectJiaXiDetailsById(@Param("actInvAwaActDetail") ActInvAwaActDetail actInvAwaActDetail, PageInfo info);

	/**
	 * 
	 * Description：<br>
	 * 查看 获得投资奖励活动的用户
	 * 
	 * @author fengshiliang
	 * @date 2015年10月21日
	 * @version v1.0.0
	 * @param actInvAwaActDetail
	 * @param info
	 * @return
	 */
	public PageList<ActInvAwaActDetail> selectAllPageByCondition(@Param("map") ActInvAwaActDetail actInvAwaActDetail, PageInfo info);

	/**
	 * 
	 * @Description : 查询参与人数的综合
	 * @param actInvAwaActDetail
	 *            投资奖励对象
	 * @return 参与的总人数
	 * @Author : Qing.Cai
	 * @Date : 2016年2月25日 下午3:46:13
	 */
	public Integer selectParticipantsNumberByCondition(@Param("map") ActInvAwaActDetail actInvAwaActDetail);

	/**
	 * 
	 * Description：<br>
	 * 查看 获得投资奖励活动的用户 明细
	 * 
	 * @author fengshiliang
	 * @date 2015年10月21日
	 * @version v1.0.0
	 * @param actInvAwaActDetail
	 * @param info
	 * @return
	 */
	public PageList<ActInvAwaActDetail> selectAllPageDetail(@Param("map") ActInvAwaActDetail actExpActDetail, PageInfo info);

	/**
	 * 
	 * Description：<br>
	 * 导出excel
	 * 
	 * @author JunJie.Liu
	 * @date 2015年11月4日
	 * @version v1.0.0
	 * @param actInvAwaActDetail
	 * @param eom
	 * @return
	 */
	public List<ActInvAwaActDetail> selectAllPageDetailEom(HashMap<String, Object> maps);

	/**
	 * 
	 * Description：<br>
	 * 查询客户拥有的 投资奖励 根据奖励类型
	 * 
	 * @author shiliang.feng
	 * @date 2015年12月10日
	 * @version v1.0.0
	 * @param customerId
	 *            客户id
	 * @param investAwardType
	 *            活动类型 1 积分 2经验 3加息劵
	 * @return
	 */
	public List<ActInvAwaActDetail> selectAllPageByTypeId(@Param("customerId") String customerId, @Param("investAwardType") String investAwardType);

	/**
	 * 
	 * @Description : 批量新增
	 * @param list
	 *            需要插入的list集合
	 * @Author : Qing.Cai
	 * @Date : 2015年12月28日 下午8:59:45
	 */
	public void insertBatch(List<ActInvAwaActDetail> list);

	/**
	 * 
	 * @Description : 获取活动参与人数
	 * @param investAwardId
	 *            投资奖励活动ID
	 * @return 领取人数
	 * @Author : Qing.Cai
	 * @Date : 2016年3月7日 下午8:09:36
	 */
	public Integer selectInterestCount(String investAwardId);

	/**
	 * 
	 * @Description : 根据加息劵活动ID查询加息劵已领取明细
	 * @param investAwardId
	 *            加息劵活动ID
	 * @return 已领取的集合
	 * @Author : Qing.Cai
	 * @Date : 2016年3月10日 下午2:55:16
	 */
	public List<ActInvAwaActDetail> selectInterestListByInvestAwardId(@Param("investAwardId") String investAwardId);

	/**
	 * 
	 * @Description : 检查用户是否参与当前加息劵活动
	 * @param investAwardId
	 *            加息劵活动ID
	 * @param customerId
	 *            用户ID
	 * @return 0 未参与,>0 表示参与
	 * @Author : Qing.Cai
	 * @Date : 2016年3月10日 下午3:39:55
	 */
	public Integer checkUserIsAttend(@Param("investAwardId") String investAwardId, @Param("customerId") String customerId);

}