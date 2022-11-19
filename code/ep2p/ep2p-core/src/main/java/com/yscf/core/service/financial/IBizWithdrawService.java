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

import java.util.HashMap;
import java.util.List;

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.comm.ExportObjectModel;
import com.yscf.core.model.financial.BizWithdraw;
import com.yscf.core.model.ptp.financial.BizWithdrawModel;

/**
 * Description：<br> 
 * 体现管理服务接口
 * @author  Allen
 * @date    2015年9月6日
 * @version v1.0.0
 */
public interface IBizWithdrawService {
	/**
	 * Description：新增系统体现
	 * @author  Allen
	 * @date    2015年9月8日
	 * @version v1.0.0
	 * @param record
	 * @return int 受影响行数
	 * @throws FrameworkException
	 */
	public int insert(BizWithdraw record) throws FrameworkException;
	
	/**
	 * Description：查询体现列表
	 * @author  Allen
	 * @date    2015年9月9日
	 * @version v1.0.0
	 * @param bizWithdraw 系统体现
	 * @return List<BizWithdraw>
	 * @throws FrameworkException
	 */
	List<BizWithdraw> selectAll(BizWithdraw bizWithdraw, PageInfo pageInfo);
	/**
	 * Description：批量删除体现列表
	 * @author  heng.wang
	 * @date    2015年9月11日
	 * @version v1.0.0
	 * @param String pids 系统体现
	 * @return int
	 * @throws FrameworkException
	 */
	int deleteBtach(String pids);
	/**
	 * 批量审核体现申请
	 * Description：<br> 
	 * @author  Allen
	 * @date    2015年9月21日
	 * @version v1.0.0
	 * @param bizWithdraw
	 * @return
	 */
	int updateBatchAudit(BizWithdraw bizWithdraw);
	
	int batchAuditTransferService(BizWithdraw bizWithdraw);
	/**
	 * Description：查询提现管理（通用）,条件查询,带分页功能的
	 * @author  Allen
	 * @date    2015年9月14日
	 * @version v1.0.0
	 * @param bizWithdraw bizWithdraw 系统体现
	 * @return PageList<bizWithdraw>
	 * @throws FrameworkException
	 */
	PageList<BizWithdraw> selectSelectivePage(HashMap<String,Object> parasMap, PageInfo info) ;
	
	/**
	 * Description：查询总记录条数  根据审核状态audit_status
	 * @author  JingYu.Dai
	 * @date    2015年10月9日
	 * @version v1.0.0
	 * @param auditStatus 审核状态
	 * @return int
	 * @throws FrameworkException
	 */
	int getTotalCountByAuditStatus(String auditStatus) throws FrameworkException;
	
	/**
	 * 
	 * Description：<br> 
	 * 导出提现管理excel
	 * @author  JunJie.Liu
	 * @date    2015年11月3日
	 * @version v1.0.0
	 * @param bizWithdrawVO
	 * @param eom
	 * @return
	 */
	public List<BizWithdrawModel> selectBizWithdrawVOEom(
			BizWithdrawModel bizWithdrawVO, ExportObjectModel eom);
	
	
}


