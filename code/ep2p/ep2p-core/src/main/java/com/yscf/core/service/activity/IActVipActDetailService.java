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

import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.activity.ActVipActDetail;
import com.yscf.core.model.comm.ExportObjectModel;

/**
 * 
 * @ClassName : IActVipActDetailService
 * @Description : 赠送VIP活动明细业务层接口
 * @Author : Qing.Cai
 * @Date : 2015年10月10日 下午3:06:34
 */
public interface IActVipActDetailService {

	/**
	 * 
	 * @Description : 查询赠送VIP活动列表,带分页
	 * @param actVipActDetail
	 *            赠送VIP活动明细对象
	 * @param info
	 *            分页对象
	 * @return 赠送VIP活动列表
	 * @Author : Qing.Cai
	 * @Date : 2015年10月10日 下午3:05:34
	 */
	PageList<ActVipActDetail> selectAllPage(ActVipActDetail actVipActDetail, PageInfo info);

	/**
	 * 
	 * @Description : 查询赠送VIP活动明细列表,带分页
	 * @param actVipActDetail
	 *            赠送VIP活动明细对象
	 * @param info
	 *            分页对象
	 * @return 赠送VIP活动明细列表
	 * @Author : Qing.Cai
	 * @Date : 2015年10月10 日 下午3:04:34
	 */
	PageList<ActVipActDetail> selectAllPageDetail(ActVipActDetail actVipActDetail, PageInfo info);

	/**
	 * 
	 * @Description : 导出查询
	 * @param actVipActDetail
	 *            赠送VIP信息对象
	 * @param eom
	 *            导出对象
	 * @return 赠送VIP活动明细
	 * @Author : Qing.Cai
	 * @Date : 2015年11月4日 上午10:45:25
	 */
	List<ActVipActDetail> selectAllPageDetailExport(ActVipActDetail actVipActDetail, ExportObjectModel eom);
}
