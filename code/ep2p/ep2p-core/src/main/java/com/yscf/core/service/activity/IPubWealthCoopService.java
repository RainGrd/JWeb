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
import com.yscf.core.model.activity.PubWealthCooperation;

/**
 * 
 * @ClassName : PubWealthCoopService
 * @Description : 财富合伙人信息业务接口
 * @Author : Qing.Cai
 * @Date : 2015年9月17日 下午4:18:38
 */
public interface IPubWealthCoopService {

	/**
	 * 
	 * @Description : 查询财富合伙人信息列表,带分页
	 * @param PubWealthCoop
	 *            财富合伙人信息对象
	 * @param info
	 *            分页对象
	 * @return 财富合伙人信息列表
	 * @Author : Qing.Cai
	 * @Date : 2015年9月17日 下午4:19:00
	 */
	public PageList<PubWealthCooperation> selectAllPage(
			PubWealthCooperation pubWealthCoop, PageInfo info);

	/**
	 * 
	 * @Description : 编辑财富合伙人活动
	 * @param PubWealthCoop
	 *            财富合伙人信息对象
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2015年9月23日 下午2:02:49
	 */
	public void PubWealthCoopEdit(PubWealthCooperation PubWealthCoop)
			throws FrameworkException;

	/**
	 * 
	 * @Description : 根据主键获取对象
	 * @param pid
	 *            主键ID
	 * @return 财富合伙人活动对象
	 * @Author : Qing.Cai
	 * @Date : 2015年9月23日 下午3:11:20
	 */
	public PubWealthCooperation getPubWealthCoopByPid(String pid);

	/**
	 * 
	 * @Description : 批量删除财富合伙人活动
	 * @param pids
	 *            财富合伙人ID(1,2,3)
	 * @return 受影响的行数
	 * @Author : Qing.Cai
	 * @Date : 2015年9月24日 下午6:53:20
	 */
	public void deleteBatch(String pids) throws FrameworkException;

	/**
	 * Description：<br> 
	 * 参与财富合伙人  满标时计算
	 * 	 财富合伙人明细加一
	 * 	 根据财富合伙人奖励值  给邀请人奖励
	 * @author  shiliang.feng
	 * @param borrowId 标的id
	 * @date    2016年3月9日
	 * @version v1.0.0
	 * @throws FrameworkException
	 */
	public void pubWealthCoopDetail(String borrowId) throws FrameworkException;
}
