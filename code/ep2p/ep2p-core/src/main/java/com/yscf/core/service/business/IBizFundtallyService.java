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
 * 2015年10月28日     JingYu.Dai		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.business;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.business.BizFundtally;
import com.yscf.core.model.comm.ExportObjectModel;
import com.yscf.core.model.ptp.financial.BizFundtallyModel;

/**
 * Description：业务--资金流水	 业务逻辑层接口
 * @author  JingYu.Dai
 * @date    2015年10月28日
 * @version v1.0.0
 */
public interface IBizFundtallyService {
	

	 /**
     * Description：<br>
     * 资金流水,条件查询,带分页功能的
     * @author  JingYu.Dai
     * @date    2015年9月28日
     * @version v1.0.0
     * @param record  线下充值类 对象（BizFundtally）
     * @param info 分页 对象 （PageInfo）
     * @return PageList<BizFundtally> 
     */
    PageList<BizFundtally> selectBizFundtallyPage(BizFundtally bizFundtally , PageInfo info) throws FrameworkException;
    
    /**
     * 
     * Description：<br> 
     * 导出系统明细
     * @author  JunJie.Liu
     * @date    2015年11月3日
     * @version v1.0.0
     * @param fundtally
     * @param eom
     * @return
     */
	List<BizFundtallyModel> selectBizFundtallyEom(BizFundtally fundtally,
			ExportObjectModel eom);
	
	/**
	 * 
	 * Description：<br> 
	 * 批量新增系统资金流水
	 * @author  Jie.Zou
	 * @date    2016年1月14日
	 * @version v1.0.0
	 * @param userIds 客户集合 
	 * @param amount 发生额
	 * @param dealType 支出/收入
	 * @param detailType 系统资金流水类型
	 * @param time 发生时间
	 * @param note 备注
	 */
	void insertBizFundtallyByUserIds(List<String> userIds,BigDecimal amount,String dealType,String detailType,Date time,String note); 
    
}


