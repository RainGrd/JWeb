/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 待还款详情VO数据层
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2016年1月25日     Jie.Zou		Initial Version.
 *************************************************************************
 */
package com.yscf.core.dao.business;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.yscf.core.model.business.BizRepaymentPlanInfoVo;

/**
 * Description：<br> 
 * 待还款详情VO数据层
 * @author  Jie.Zou
 * @date    2016年1月25日
 * @version v1.0.0
 */
@MapperScan("bizRepaymentPlanInfoVoMapper")
public interface BizRepaymentPlanInfoVoMapper extends IBaseDao<BaseEntity, String> {
	
	/**
	 * 
	 * Description：<br> 
	 * 通过还款计划Id得到还款计划Vo
	 * @author  Jie.Zou
	 * @date    2016年1月25日
	 * @version v1.0.0
	 * @param repayId
	 * @return
	 */
	BizRepaymentPlanInfoVo getRepaymentInfoVo(@Param("repayId")String repayId);
	
	/**
	 * 
	 * Description：<br> 
	 * 通过借款Id得到借款的还款情况
	 * @author  Jie.Zou
	 * @date    2016年1月26日
	 * @version v1.0.0
	 * @param borrowId
	 * @return
	 */
	BizRepaymentPlanInfoVo getRepaidInfoVo(@Param("borrowId")String borrowId);
	
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
	BizRepaymentPlanInfoVo getOverdueRepayBorrow(@Param("borrowId")String borrowId);
	
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
	List<BizRepaymentPlanInfoVo> getOverdueRepayment(@Param("borrowId")String borrowId);
	
	/**
	 * 
	 * Description：<br> 
	 * 通过借款Id得到最近的还款计划INFO
	 * @author  Jie.Zou
	 * @date    2016年3月1日
	 * @version v1.0.0
	 * @param borrowId
	 * @return
	 */
	BizRepaymentPlanInfoVo getRepayInfoByTime(@Param("borrowId")String borrowId);
}


