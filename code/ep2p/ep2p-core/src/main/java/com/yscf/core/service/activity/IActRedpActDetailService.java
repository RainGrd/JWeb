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
import com.yscf.core.exception.RedNotEnoughException;
import com.yscf.core.model.activity.ActRedpActDetail;
import com.yscf.core.model.comm.ExportObjectModel;

/**
 * 
 * @ClassName : IActRedpActDetailService
 * @Description : 红包活动业务层接口
 * @Author : Qing.Cai
 * @Date : 2015年10月20日 上午11:01:03
 */
public interface IActRedpActDetailService {

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
	public PageList<ActRedpActDetail> selectAllPage(ActRedpActDetail actRedpActDetail, PageInfo info);

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
	public PageList<ActRedpActDetail> selectAllPageDetail(ActRedpActDetail actRedpActDetail, PageInfo info);

	/**
	 * 
	 * @Description : 导出查询
	 * @param actVipActDetail
	 *            红包活动明细对象
	 * @param eom
	 *            导出对象
	 * @return 红包活动明细
	 * @Author : Qing.Cai
	 * @Date : 2015年11月4日 上午10:46:25
	 */
	public List<ActRedpActDetail> selectAllPageDetailExport(ActRedpActDetail actRedpActDetail, ExportObjectModel eom);

	/**
	 * 
	 * @Description : 判断用户是否领取
	 * @param redpacketId
	 *            红包活动ID
	 * @param userId
	 *            用户ID
	 * @return 0.未领取 1.已领取
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2016年3月7日 下午3:04:51
	 */
	public Integer checkUserIsAttend(String redpacketId, String userId) throws FrameworkException;

	/**
	 * 
	 * @Description : 领取红包
	 * @param redpacketId
	 *            红包活动ID
	 * @param userId
	 *            用户ID
	 * @return 领取金额
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2016年3月7日 下午3:02:45
	 */
	public Double receiveRed(String redpacketId, String userId) throws FrameworkException, RedNotEnoughException;

	/**
	 * 
	 * @Description : 查询红包领取明细
	 * @param redpacketId
	 *            红包ID
	 * @return 已领取明细
	 * @Author : Qing.Cai
	 * @Date : 2016年3月8日 下午7:09:25
	 */
	public List<ActRedpActDetail> selectReceiveRedDetail(String redpacketId);

	/**
	 * 
	 * @Description : 检查当前用户在当前抢红包活动下的状态
	 * @param redpacketId
	 *            抢红包活动ID
	 * @param userId
	 *            用户ID
	 * @return 活动状态(1.未领取 2.已领取 3.抢光了)
	 * @Author : Qing.Cai
	 * @Date : 2016年3月9日 上午11:53:27
	 */
	public String checkUserReceiveRedStatus(String redpacketId, String userId);

	/**
	 * 
	 * @Description : 查询在当前抢红包活动下用户领取金额
	 * @param redpacketId
	 *            抢红包活动ID
	 * @param userId
	 *            用户ID
	 * @return 领取金额
	 * @Author : Qing.Cai
	 * @Date : 2016年3月9日 下午3:19:42
	 */
	public Double selectUserReceiveAmount(String redpacketId, String userId);
}
