/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 体现管理服务接口
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月6日     Allen		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.financial;

import java.util.List;

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.comm.ExportObjectModel;
import com.yscf.core.model.financial.BizRechargeOffline;
import com.yscf.core.model.ptp.financial.BizRechargeOfflineModel;


/**
 * Description：线下充值业务处理接口
 * @author  JingYu.Dai
 * @date    2015年9月25日
 * @version v1.0.0
 */
public interface IBizRechargeOfflineService {

	/**
	 * Description：新增线下充值
	 * @author  JingYu.Dai
	 * @date    2015年9月28日
	 * @version v1.0.0
	 * @param bizRechargeOffline
	 * @return int
	 */
	int insert(BizRechargeOffline bizRechargeOffline) throws FrameworkException;
	
	/**
     * Description：查询线下充值列表数据  分页
     * @author  JingYu.Dai
     * @date    2015年9月28日
     * @version v1.0.0
     * @param record  线下充值类 对象（BizRechargeOffline）
     * @param info 分页 对象 （PageInfo）
     * @return PageList<BizRechargeOffline> 
     */
	PageList<BizRechargeOffline> selectOfflineRechargePage(BizRechargeOffline bizRechargeOffline,PageInfo info) throws FrameworkException;
	
	/**
	 * Description：线下充值审核
	 * @author  JingYu.Dai
	 * @date    2015年9月29日
	 * @version v1.0.0
	 * @param rb BizRechargeOffline 类对象
	 * @return int 受影响行数
	 * @throws FrameworkException
	 */
	int updateOfflineRechargeCheck(BizRechargeOffline rb) throws FrameworkException;
	
	/**
	 * Description：查询总记录条数  条件充值状态
	 * @author  JingYu.Dai
	 * @date    2015年10月9日
	 * @version v1.0.0
	 * @param recStatus 充值状态
	 * @return int 
	 * @throws FrameworkException
	 */
	int getTotalCountByRecStatus(String recStatus) throws FrameworkException;
	
	/**
	 * 
	 * Description：<br> 
	 * 导出线下充值客户列表
	 * @author  JunJie.Liu
	 * @date    2015年10月30日
	 * @version v1.0.0
	 * @param bizRechargeOffline
	 * @param eom
	 * @return
	 */
	List<BizRechargeOfflineModel> selectBizRechargeOfflineEom(
			BizRechargeOffline bizRechargeOffline, ExportObjectModel eom);

}


