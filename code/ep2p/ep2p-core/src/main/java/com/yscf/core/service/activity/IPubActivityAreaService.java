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

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.exception.CodeIsExistenceException;
import com.yscf.core.model.activity.ActActivity;
import com.yscf.core.model.activity.PubActivityArea;
import com.yscf.core.model.business.BizBorrow;

/**
 * 
 * @ClassName : IPubActivityAreaService
 * @Description : 活动专区业务接口
 * @Author : Qing.Cai
 * @Date : 2015年12月14日 下午3:55:32
 */
public interface IPubActivityAreaService {

	/**
	 * 
	 * @Description : 前台_查询初始化的活动专区显示
	 * @param pageSize
	 *            初始显示数量
	 * @return 活动专区初始显示列表
	 * @Author : Qing.Cai
	 * @Date : 2015年12月19日 下午5:46:08
	 */
	List<PubActivityArea> selectShowActicityArea(Integer pageSize);

	/**
	 * 
	 * @Description : 前台_查询加载的活动专区信息
	 * @param pageIndex
	 *            第几次加载
	 * @param pageSize
	 *            每次加载的数量
	 * @return 活动专区加载列表
	 * @Author : Qing.Cai
	 * @Date : 2015年12月19日 下午5:58:57
	 */
	List<PubActivityArea> selectLoadActicityArea(Integer pageIndex, Integer pageSize);

	/**
	 * 
	 * @Description : 后台_分页查询活动专区列表
	 * @param pubActivityArea
	 *            活动专区对象
	 * @param info
	 *            分页对象
	 * @return 活动专区列表
	 * @Author : Qing.Cai
	 * @Date : 2015年12月16日 上午11:16:39
	 */
	PageList<PubActivityArea> selectAllPage(PubActivityArea pubActivityArea, PageInfo info);

	/**
	 * 
	 * @Description : 修改是否展示
	 * @param pubActivityArea
	 *            活动专区对象
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2015年12月17日 下午2:31:50
	 */
	public void updateIsShows(PubActivityArea pubActivityArea) throws FrameworkException;

	/**
	 * 
	 * @Description : 删除活动专区数据
	 * @param pubActivityArea
	 *            活动专区对象
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2015年12月17日 下午3:01:24
	 */
	void deletePubActivityArea(PubActivityArea pubActivityArea) throws FrameworkException;

	/**
	 * 
	 * @Description : 新增新手标or体验标信息到活动专区表里面
	 * @param bizBorrow
	 *            借款对象
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2015年12月14日 下午4:06:17
	 */
	public void pubAreaEditBorrow(BizBorrow bizBorrow) throws FrameworkException;

	/**
	 * 
	 * @Description : 新增活动信息到活动专区表里面
	 * @param actActivity
	 *            活动对象
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2015年12月14日 下午4:07:06
	 */
	public void pubAreaEditActivity(ActActivity actActivity) throws FrameworkException;

	/**
	 * 
	 * @Description : 新增活动专区对象(需要跳转到一个制定的HTML页面)
	 * @param pubActivityArea
	 *            活动专区对象
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2015年12月18日 下午2:44:21
	 */
	public void pubAreaEditOther(PubActivityArea pubActivityArea) throws FrameworkException, CodeIsExistenceException;

	/**
	 * 
	 * @Description : 根据活动类型and活动ID修改活动状态
	 * @param activityId
	 *            活动ID
	 * @param activityType
	 *            活动类型(1 抢红包 2 加息券 99 其他)
	 * @param activityStatus
	 *            活动状态（1.进行中 2.过期 3.满标 4.流标）
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2015年12月14日 下午8:47:40
	 */
	public void updateActivityStatus(String activityId, Integer activityType, String activityStatus) throws FrameworkException;

	/**
	 * 
	 * @Description : 修改是否启用
	 * @param pubActivityArea
	 *            活动专区对象
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2016年2月26日 上午10:40:43
	 */
	public void updateIsDisable(PubActivityArea pubActivityArea) throws FrameworkException;

	/**
	 * 
	 * @Description : 查询当前最新的活动(根据类型)
	 * @param activityType
	 *            活动类型(1.抢红包 2.加息劵 99.其他)
	 * @return 最新的活动对象
	 * @Author : Qing.Cai
	 * @Date : 2016年3月9日 上午10:15:04
	 */
	public PubActivityArea selectPubActivityByType(Integer activityType);

}
