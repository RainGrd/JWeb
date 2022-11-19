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

import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.activity.PubRedpacket;

/**
 * 
 * @ClassName : IPubRedpacketService
 * @Description : 红包信息业务接口
 * @Author : Qing.Cai
 * @Date : 2015年10月8日 下午4:44:13
 */
public interface IPubRedpacketService {

	/**
	 * 
	 * @Description : 查询红包信息列表,带分页
	 * @param pubRedpacket
	 *            红包信息对象
	 * @param info
	 *            分页对象
	 * @return 红包信息列表
	 * @Author : Qing.Cai
	 * @Date : 2015年10月8日 下午4:44:31
	 */
	public PageList<PubRedpacket> selectAllPage(PubRedpacket pubRedpacket, PageInfo info);

	/**
	 * 
	 * @Description : 编辑红包活动
	 * @param pubRedpacket
	 *            红包信息对象
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2015年10月8日 下午4:45:43
	 */
	public void pubRedpacketEdit(PubRedpacket pubRedpacket) throws FrameworkException;

	/**
	 * 
	 * @Description : 根据PID查询红包信息对象
	 * @param pid
	 *            主键ID
	 * @return 红包信息对象
	 * @Author : Qing.Cai
	 * @Date : 2015年10月8日 下午4:46:47
	 */
	public PubRedpacket getPubRedpacketByPid(String pid);

	/**
	 * 
	 * @Description : 批量删除红包信息对象
	 * @param pids
	 *            PID(1,2,3)
	 * @Author : Qing.Cai
	 * @Date : 2015年10月8日 下午4:47:16
	 */
	public void deleteBatch(String pids);

}
