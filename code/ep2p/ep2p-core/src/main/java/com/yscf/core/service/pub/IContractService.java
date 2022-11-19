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
package com.yscf.core.service.pub;

import com.yscf.core.model.pub.Contract;


/**
 * 
 * Description：<br> 
 * 业务合同类
 * @author  JunJie.Liu
 * @date    2016年2月22日
 * @version v1.0.0
 */
public interface IContractService {

	public Contract getByCustIdAndBusinessId(String userId, String borrowId,
			String type);

	public int insertSelective(Contract contract);

	
}


