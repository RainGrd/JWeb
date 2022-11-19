/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * TODO
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2016年1月25日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.business;

import java.util.List;

import com.achievo.framework.exception.FrameworkException;
import com.yscf.core.model.business.BizRepaymentPlan;
import com.yscf.core.model.business.BizRepaymentPlanInfoVo;
import com.yscf.core.model.business.RepaymentDto;

/**
 * Description：<br> 
 * 还款业务处理接口
 * @author  Yu.Zhang
 * @date    2016年1月25日
 * @version v1.0.0
 */
public interface IRepaymentService {

	
	/**
	 * 
	 * Description：<br> 
	 * 根据借款ID获取提前还款信息
	 * @author  Yu.Zhang
	 * @date    2016年1月25日
	 * @version v1.0.0
	 * @param borrowId 借款ID
	 * @param repaymentDate 还款时间
	 */
	public RepaymentDto getPrepaymentInfo (String borrowId,String repaymentDate);
	
	
	/**
	 * 
	 * Description：<br> 
	 * 提前还款
	 * @author  Yu.Zhang
	 * @date    2016年1月25日
	 * @version v1.0.0
	 * @param borrowId 借款ID
	 * @throws FrameworkException 
	 */
	public void prepayment (String borrowId,String userId) throws FrameworkException;
	
	/**
	 * 
	 * Description：<br> 
	 * 通过还款计划ID得到还款信息
	 * @author  Jie.Zou
	 * @date    2016年3月1日
	 * @version v1.0.0
	 * @param repayId 还款计划Id
	 * @return
	 */
	public BizRepaymentPlanInfoVo getRepaymentInfoVo(String repayId);
	
	/**
	 * 
	 * Description：<br> 
	 * 根据借款Id得到借款的还款信息
	 * @author  Jie.Zou
	 * @date    2016年1月26日
	 * @version v1.0.0
	 * @param borrowId 借款Id
	 * @return
	 */
	public BizRepaymentPlanInfoVo getRepaidInfoVoByBid(String borrowId);
	
	/**
	 * 
	 * Description：<br> 
	 * 通过借款id得到该借款的逾期还款信息
	 * @author  Jie.Zou
	 * @date    2016年1月27日
	 * @version v1.0.0
	 * @param borrowId
	 * @return
	 */
	public BizRepaymentPlanInfoVo getOverdueRepayBorrow(String borrowId);
	
	/**
	 * 
	 * Description：<br> 
	 * 通过借款id得到逾期还款计划
	 * @author  Jie.Zou
	 * @date    2016年1月27日
	 * @version v1.0.0
	 * @param borrowId
	 * @return
	 */
	public List<BizRepaymentPlanInfoVo> getOverdueRepayments(String borrowId);
	
	/**
	 * 
	 * Description：<br> 
	 * 通过借款id得到最近的还款计划
	 * @author  Jie.Zou
	 * @date    2016年1月28日
	 * @version v1.0.0
	 * @param borrowId
	 * @return
	 */
	public BizRepaymentPlan selectRepayByTime(String borrowId);
	
	/**
	 * 
	 * Description：<br> 
	 * 通过借款id得到逾期的还款计划
	 * @author  Jie.Zou
	 * @date    2016年1月28日
	 * @version v1.0.0
	 * @param borrowId
	 * @return
	 */
	public List<BizRepaymentPlan> selectOverdueRepayment(String borrowId);
	
	/**
	 * 
	 * Description：<br> 
	 * 通过借款id得到最近的还款计划info
	 * @author  Jie.Zou
	 * @date    2016年1月28日
	 * @version v1.0.0
	 * @param borrowId
	 * @return
	 */
	public BizRepaymentPlanInfoVo getRepayInfoByTime(String borrowId);
	
}


