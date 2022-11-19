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

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.activity.PubExpGold;

/**
 * 
 * @ClassName : PubExpGoldService
 * @Description : 体验金信息业务接口
 * @Author : Qing.Cai
 * @Date : 2015年9月17日 下午4:18:38
 */
public interface IPubExpGoldService {

	/**
	 * 
	 * @Description : 查询体验金信息列表,带分页
	 * @param pubExpGold
	 *            体验金信息对象
	 * @param info
	 *            分页对象
	 * @return 体验金信息列表
	 * @Author : Qing.Cai
	 * @Date : 2015年9月17日 下午4:19:00
	 */
	public PageList<PubExpGold> selectAllPage(PubExpGold pubExpGold,
			PageInfo info);

	/**
	 * 
	 * @Description : 编辑体验金活动
	 * @param pubExpGold
	 *            体验金信息对象
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2015年9月23日 下午2:02:49
	 */
	public void pubExpGoldEdit(PubExpGold pubExpGold) throws FrameworkException;

	/**
	 * 
	 * @Description : 根据主键获取对象
	 * @param pid
	 *            主键ID
	 * @return 体验金活动对象
	 * @Author : Qing.Cai
	 * @Date : 2015年9月23日 下午3:11:20
	 */
	public PubExpGold getPubExpGoldByPid(String pid);

	/**
	 * 
	 * @Description : 批量删除体验金活动
	 * @param pids
	 *            体验金ID(1,2,3)
	 * @return 受影响的行数
	 * @Author : Qing.Cai
	 * @Date : 2015年9月24日 下午6:53:20
	 */
	public void deleteBatch(String pids);

	/**
	 * 
	 * Description：<br>
	 * 根据活动id 查询体验金信息
	 * 
	 * @author fengshiliang
	 * @date 2015年11月10日
	 * @version v1.0.0
	 * @param activityId
	 * @return
	 */
	public PubExpGold selectByActivityId(String activityId);

}
