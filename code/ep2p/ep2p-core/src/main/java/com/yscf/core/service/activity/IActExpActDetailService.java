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
import com.yscf.core.model.activity.ActExpActDetail;
import com.yscf.core.model.comm.ExportObjectModel;

/**
 * 
 * @ClassName : IActExpActDetailService
 * @Description : 体验金明细业务接口
 * @Author : Qing.Cai
 * @Date : 2015年10月19日 下午3:27:47
 */
public interface IActExpActDetailService {

	/**
	 * 
	 * @Description : 体验金赠送查询
	 * @param actExpActDetail
	 *            体验金明细对象
	 * @param info
	 *            分页对象
	 * @return 体验金赠送列表
	 * @Author : Qing.Cai
	 * @Date : 2015年10月19日 下午3:28:37
	 */
	PageList<ActExpActDetail> selectAllPageByCondition(ActExpActDetail actExpActDetail, PageInfo info);

	/**
	 * 
	 * @Description : 查询赠送体验金活动明细列表,带分页
	 * @param actExpActDetail
	 *            体验金明细对象
	 * @param info
	 *            分页对象
	 * @return 体验金明细列表
	 * @Author : Qing.Cai
	 * @Date : 2015年10月19日 下午3:28:40
	 */
	PageList<ActExpActDetail> selectAllPageDetail(ActExpActDetail actExpActDetail, PageInfo info);

	/**
	 * Description：根据客户id查询体验金资金明细
	 * 
	 * @author heng.wang
	 * @date 2015年10月12日
	 * @version v1.0.0
	 * @param userName
	 *            系统客服
	 * @return boolean
	 * @throws FrameworkException
	 */
	public PageList<ActExpActDetail> selectTiYanJinDetailsById(String pid, PageInfo info);

	/**
	 * Description：查询客户体验金资金明细列表
	 * 
	 * @author heng.wang
	 * @date 2015年10月9日
	 * @version v1.0.0
	 * @param userName
	 *            系统用户
	 * @return boolean
	 * @throws FrameworkException
	 */
	List<ActExpActDetail> selectAllPage(ActExpActDetail actExpActDetail, PageInfo pageInfo);

	/**
	 * 
	 * @Description : 导出查询
	 * @param actExpActDetail
	 *            赠送体验金对象
	 * @param eom
	 *            导出对象
	 * @return 体验金赠送明细列表
	 * @Author : Qing.Cai
	 * @Date : 2015年11月4日 下午3:35:54
	 */
	List<ActExpActDetail> selectAllPageDetailExport(ActExpActDetail actExpActDetail, ExportObjectModel eom);

	/**
	 * 
	 * @Description : 查询客户当前可用体验金劵
	 * @param customerId
	 *            客户ID
	 * @return 体验金劵列表
	 * @Author : Qing.Cai
	 * @Date : 2016年1月5日 下午5:16:33
	 */
	public List<ActExpActDetail> selectCustTomerExperienceGold(String customerId);
	
	/**
	 * 
	 * Description：<br> 
	 * 根据PID数组查询体验金对象
	 * @author  Jie.Zou
	 * @date    2016年3月9日
	 * @version v1.0.0
	 * @param pids PID主键数组(1,2,3)
	 * @return 体验金劵列表
	 */
	public List<ActExpActDetail> selectExperienceGoldByPidArr(String[] pids);
}
