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
 * 2015年10月6日     Simon.Hoo		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.activity;

import java.util.List;

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.activity.PubInvestAward;

/**
 * 
 * @ClassName : IPubInvestAwardService
 * @Description : 投资奖励信息业务接口
 * @Author : Qing.Cai
 * @Date : 2015年10月13日 下午2:24:21
 */
public interface IPubInvestAwardService {

	/**
	 * 
	 * @Description : 查询投资奖励信息列表,带分页
	 * @param pubInvestAward
	 *            投资奖励信息对象
	 * @param info
	 *            分页对象
	 * @return 投资奖励信息列表
	 * @Author : Qing.Cai
	 * @Date : 2015年10月13日 下午3:19:00
	 */
	public PageList<PubInvestAward> selectAllPage(PubInvestAward pubInvestAward, PageInfo info);

	/**
	 * 
	 * @Description : 编辑投资奖励活动
	 * @param pubInvestAward
	 *            投资奖励信息对象
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2015年10月13日 下午2:02:49
	 */
	public void pubInvestAwardEdit(PubInvestAward pubInvestAward) throws FrameworkException;

	/**
	 * 
	 * @Description : 根据主键获取对象
	 * @param pid
	 *            主键ID
	 * @return 投资奖励活动对象
	 * @Author : Qing.Cai
	 * @Date : 2015年10月13日 下午3:11:20
	 */
	public PubInvestAward getPubInvestAwardByPid(String pid);

	/**
	 * 
	 * @Description : 批量删除投资奖励活动
	 * @param pids
	 *            投资奖励ID(1,2,3)
	 * @return 受影响的行数
	 * @Author : Qing.Cai
	 * @Date : 2015年10月13日 下午2:53:20
	 */
	public void deleteBatch(String pids);
	/**
	 * Description：根据客户ID查我的赠劵
	 * @author  wangheng
	 * @date    2015年10月15日
	 * @version v1.0.0
	 * @param userName 系统用户
	 * @return boolean
	 * @throws FrameworkException
	 */
	public List<PubInvestAward> selectMyCoupon(String customerId,Integer pageIndex,Integer pageSize);
	
	/**
	 * Description：根据客户ID查我的赠劵总记录数
	 * @author  wangheng
	 * @date    2016年3月1日
	 * @version v1.0.0
	 * @param userName 系统用户
	 * @return Integer
	 * @throws FrameworkException
	 */
	public Integer selectMyCouponCount(String customerId);
	
	/**
	 * Description：根据客户ID查我的赠劵
	 * @author  wangheng
	 * @date    2016年3月2日
	 * @version v1.0.0
	 * @param customerId 系统用户
	 * @return PubInvestAward
	 * @throws FrameworkException
	 */
	public List<PubInvestAward> selectMyCouponExpired(String customerId,Integer pageIndex,Integer pageSize);
	
	/**
	 * Description：根据客户ID查我已过期的赠劵总记录数
	 * @author  wangheng
	 * @date    2016年3月2日
	 * @version v1.0.0
	 * @param userName 系统用户
	 * @return Integer
	 * @throws FrameworkException
	 */
	public Integer selectMyCouponExpiredCount(String customerId);

}
