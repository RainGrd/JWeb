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
import com.yscf.core.model.activity.PubVipinfo;

/**
 * 
 * @ClassName : IPubVipinfoService
 * @Description : VIP信息业务接口
 * @Author : Qing.Cai
 * @Date : 2015年9月23日 下午4:18:38
 */
public interface IPubVipinfoService {

	/**
	 * 
	 * @Description : 查询VIP信息列表,带分页
	 * @param pubVipinfo
	 *            VIP信息对象
	 * @param info
	 *            分页对象
	 * @return VIP信息列表
	 * @Author : Qing.Cai
	 * @Date : 2015年9月23日 下午4:19:00
	 */
	public PageList<PubVipinfo> selectAllPage(PubVipinfo pubVipinfo, PageInfo info);

	/**
	 * 
	 * @Description : 编辑VIP信息
	 * @param pubVipinfo
	 *            VIP信息对象
	 * @throws FrameworkException
	 * @Author : Qing.Cai
	 * @Date : 2015年9月24日 下午2:02:49
	 */
	public void pubVipinfoEdit(PubVipinfo pubVipinfo) throws FrameworkException;

	/**
	 * 
	 * @Description : 根据主键获取对象
	 * @param pid
	 *            主键ID
	 * @return VIP信息对象
	 * @Author : Qing.Cai
	 * @Date : 2015年9月24日 下午3:11:20
	 */
	public PubVipinfo getPubVipinfoByPid(String pid);

	/**
	 * 
	 * @Description : 批量删除VIP信息
	 * @param pids
	 *            VIP信息ID(1,2,3)
	 * @return 受影响的行数
	 * @Author : Qing.Cai
	 * @Date : 2015年9月24日 下午6:53:20
	 */
	public void deleteBatch(String pids);

	/**
	 * 
	 * Description：<br> 
	 * 根据pid获取vip信息
	 * @author  JunJie.Liu
	 * @date    2015年11月24日
	 * @version v1.0.0
	 * @param pid
	 * @return
	 */
	public 	PubVipinfo getById(String pid);
}
