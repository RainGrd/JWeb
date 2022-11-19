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
package com.yscf.core.service.user;

import java.util.List;

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.comm.ExportObjectModel;
import com.yscf.core.model.user.CusBirWarnHis;

/**
 * 
 * @ClassName : ICusBirWarnHisService
 * @Description : VIP生日提醒历史业务接口
 * @Author : Qing.Cai
 * @Date : 2015年10月24日 下午6:46:33
 */
public interface ICusBirWarnHisService {

	/**
	 * 
	 * @Description : VIP生日提醒列表,带分页
	 * @param cusBirWarnHis
	 *            VIP生日提醒对象
	 * @param pageInfo
	 *            分页对象
	 * @return 当年VIP生日提醒列表
	 * @Author : Qing.Cai
	 * @Date : 2015年10月24日 下午6:47:04
	 */
	PageList<CusBirWarnHis> selectAllPage(CusBirWarnHis cusBirWarnHis, PageInfo pageInfo);

	/**
	 * 
	 * @Description : 新增VIP生日短信提醒
	 * @param cusBirWarnHis
	 *            VIP生日对象
	 * @Author : Qing.Cai
	 * @Date : 2015年10月26日 上午2:28:11
	 */
	public void saveCusBirWarnHis(CusBirWarnHis cusBirWarnHis) throws FrameworkException;

	/**
	 * 
	 * @Description : 导出
	 * @param cusBirWarnHis
	 *            VIP生日提醒对象
	 * @param eom
	 *            导出对象
	 * @return 今年VIP生日提醒列表
	 * @Author : Qing.Cai
	 * @Date : 2015年11月5日 下午6:56:11
	 */
	List<CusBirWarnHis> selectAllPageDetailExport(CusBirWarnHis cusBirWarnHis, ExportObjectModel eom);

	/**
	 * 
	 * @Description : 查询今天VIP生日提醒数
	 * @return 总数
	 * @Author : Qing.Cai
	 * @Date : 2015年11月12日 下午5:09:52
	 */
	int selectVipBirCount();
}
