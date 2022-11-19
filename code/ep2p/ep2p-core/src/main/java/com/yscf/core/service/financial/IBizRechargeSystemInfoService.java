/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月30日     JingYu.Dai		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.financial;

import java.util.List;

import com.achievo.framework.exception.FrameworkException;
import com.yscf.core.model.financial.BizRechargeSystemInfo;

/**
 * Description：充值渠道业务层接口
 * @author  JingYu.Dai
 * @date    2015年9月30日
 * @version v1.0.0
 */
public interface IBizRechargeSystemInfoService {
	
	/**
	 * Description：查询所有充值渠道
	 * @author  JingYu.Dai
	 * @date    2015年9月30日
	 * @version v1.0.0
	 * @return List<BizRechargeSystemInfo>
	 * @throws FrameworkException
	 */
	List<BizRechargeSystemInfo> selectAll() throws FrameworkException;

}


