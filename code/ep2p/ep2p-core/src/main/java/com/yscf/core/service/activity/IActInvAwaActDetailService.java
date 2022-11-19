package com.yscf.core.service.activity;

import java.util.List;

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.exception.InterestNotEnoughException;
import com.yscf.core.model.activity.ActInvAwaActDetail;
import com.yscf.core.model.comm.ExportObjectModel;

/**
 * Description：<br>
 * 客户积分页签明细服务接口
 * 
 * @author heng.wang
 * @date 2015年10月9日
 * @version v1.0.0
 */
public interface IActInvAwaActDetailService {
	/**
	 * Description：根据客户id查询投资奖励积分明细
	 * 
	 * @author heng.wang
	 * @date 2015年10月9日
	 * @version v1.0.0
	 * @param userName
	 *            系统客服
	 * @return boolean
	 * @throws FrameworkException
	 */
	public PageList<ActInvAwaActDetail> selectCusPointDetailsById(String pid, PageInfo info);

	/**
	 * Description：根据客户id查询投资奖励经验明细
	 * 
	 * @author heng.wang
	 * @date 2015年10月9日
	 * @version v1.0.0
	 * @param userName
	 *            系统客服
	 * @return boolean
	 * @throws FrameworkException
	 */
	public PageList<ActInvAwaActDetail> selectJingyanDetailsById(String pid, PageInfo info);

	/**
	 * Description：根据客户id查询投资奖励加息明细
	 * 
	 * @author heng.wang
	 * @date 2015年10月10日
	 * @version v1.0.0
	 * @param userName
	 *            系统客服
	 * @return boolean
	 * @throws FrameworkException
	 */
	public PageList<ActInvAwaActDetail> selectJiaXiDetailsById(String pid, PageInfo info);

	/**
	 * 
	 * Description：<br>
	 * 获得 投资奖励活动
	 * 
	 * @author fengshiliang
	 * @date 2015年10月21日
	 * @version v1.0.0
	 * @param actInvAwaActDetail
	 * @param info
	 * @return
	 */
	public PageList<ActInvAwaActDetail> selectAllPageByCondition(ActInvAwaActDetail actInvAwaActDetail, PageInfo info);

	/**
	 * 
	 * Description：<br>
	 * 获得 投资奖励活动 明细
	 * 
	 * @author fengshiliang
	 * @date 2015年10月21日
	 * @version v1.0.0
	 * @param actExpActDetail
	 * @param info
	 * @return
	 */
	public PageList<ActInvAwaActDetail> selectAllPageDetail(ActInvAwaActDetail actExpActDetail, PageInfo info);

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
	public List<ActInvAwaActDetail> selectAllPageDetailEom(ActInvAwaActDetail actInvAwaActDetail, ExportObjectModel eom);

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
	public List<ActInvAwaActDetail> selectAllPageByTypeId(String customerId, String investAwardType);

	/**
	 * 
	 * @Description : 领取加息劵
	 * @param investAwardId
	 *            加息劵活动ID
	 * @param customerId
	 *            领取人ID
	 * @Author : Qing.Cai
	 * @Date : 2016年3月7日 下午8:03:51
	 */
	public void receiveInterest(String investAwardId, String customerId) throws FrameworkException, InterestNotEnoughException;

	/**
	 * 
	 * @Description : 查询已领取加息劵的列表
	 * @param investAwardId
	 *            加息劵活动
	 * @return 领取加息劵列表
	 * @Author : Qing.Cai
	 * @Date : 2016年3月10日 下午4:32:51
	 */
	public List<ActInvAwaActDetail> selectReceiveInvestDetail(String investAwardId);

	/**
	 * 
	 * @Description : 检查当前用户在当前领取加息劵活动下的状态
	 * @param investAwardId
	 *            加息劵活动ID
	 * @param customerId
	 *            用户ID
	 * @return (1.未领取 2.已领取 3.抢光了)
	 * @Author : Qing.Cai
	 * @Date : 2016年3月10日 下午2:48:59
	 */
	public String checkUserReceiveInterestStatus(String investAwardId, String customerId);

}
