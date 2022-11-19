/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 用户管理服务接口
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月6日     Simon.Hoo		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.activity;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.activity.ActActivity;
import com.yscf.core.model.activity.ActBirPriItemActivityRel;
import com.yscf.core.model.activity.ActParConRel;

/**
 * 
 * @ClassName : ActActivityService
 * @Description : 活动管理业务接口
 * @Author : Qing.Cai
 * @Date : 2015年9月22日 下午3:26:07
 */
public interface IActActivityService {

	/**
	 * 
	 * @Description : 查询系统活动列表,带分页
	 * @param actActivity
	 *            系统活动对象
	 * @param info
	 *            分页对象
	 * @return 系统活动列表
	 * @Author : Qing.Cai
	 * @Date : 2015年10月28日 下午3:41:31
	 */
	public PageList<ActActivity> selectAllPage(ActActivity actActivity, PageInfo info);

	/**
	 * 
	 * @Description : 根据活动ID查询活动已有条件信息
	 * @param objectId
	 *            某个类型活动ID
	 * @param activityType
	 *            活动类型
	 * @return 已选条件列表
	 * @Author : Qing.Cai
	 * @Date : 2015年9月23日 上午10:52:39
	 */
	public List<ActParConRel> selectActParConRelByObjectId(String objectId, String activityType);

	/**
	 * 
	 * Description：<br>
	 * 根据条件查询vip生日特权信息
	 * 
	 * @author fengshiliang
	 * @date 2015年10月14日
	 * @version v1.0.0
	 * @param actActivity
	 * @return
	 */
	public PageList<ActActivity> selectActActivityByParam(ActActivity actActivity, PageInfo info);

	/**
	 * 
	 * Description：<br>
	 * 保存vip生日特权信息
	 * 
	 * @author fengshiliang
	 * @date 2015年10月14日
	 * @version v1.0.0
	 * @param actActivity
	 */
	public void saveOrUpdatePir(ActActivity actActivity) throws FrameworkException;

	/**
	 * 
	 * Description：<br>
	 * 
	 * 
	 * @author fengshiliang
	 * @date 2015年10月14日
	 * @version v1.0.0
	 * @param pids
	 */
	public void deleteBatch(String pids) throws FrameworkException;

	public ActActivity selectByPrimaryKey(String pid);

	/**
	 * 
	 * Description：<br>
	 * 根据活动ID查询活动已有特权信息
	 * 
	 * @author fengshiliang
	 * @date 2015年10月15日
	 * @version v1.0.0
	 * @param objectId
	 * @param activityType
	 * @return
	 */
	public List<ActBirPriItemActivityRel> selectBirPriByObjectId(String objectId);

	/**
	 * 
	 * @Description : 根据状态查询当前活动的进行数
	 * @param status
	 *            活动状态
	 * @return 当前状态下的活动数量
	 * @Author : Qing.Cai
	 * @Date : 2015年11月12日 下午4:33:48
	 */
	public int selectCountByStatus(@Param("status") String status);

	/**
	 * 
	 * @Description : 活动开始定时任务,修改活动状态
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2015年12月24日 下午3:13:12
	 */
	public void activityBeginTask() throws FrameworkException;

	/**
	 * 
	 * @Description : 活动结束定时任务,修改活动状态
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2015年12月26日 下午4:03:43
	 */
	public void activityEndTask() throws FrameworkException;

	/**
	 * 
	 * @Description : 活动赠送
	 * @param borrowId
	 *            借款ID
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2015年12月28日 下午3:50:05
	 */
	public void activityGive(String borrowId) throws FrameworkException;

	/**
	 * Description：<br>
	 * VIP生日特权活动定时任务  
	 * 
	 * @author shiliang.feng
	 * @date 2016年3月8日
	 * @version v1.0.0
	 * @param ActivityId
	 * @throws FrameworkException 
	 */
	public void birPriRelByActivityId() throws FrameworkException;
	/**
	 * 
	 * @Description : 注册or认证送红包
	 * @param customerId
	 *            客户ID
	 * @param redType
	 *           类型（注册：Constant.REGISTER_GIVERED,认证：Constant.AUTHENTICA_GIVERED）
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2016年1月26日 下午2:30:08
	 */
	public void giveRedByType(String customerId, String redType) throws FrameworkException;
}
