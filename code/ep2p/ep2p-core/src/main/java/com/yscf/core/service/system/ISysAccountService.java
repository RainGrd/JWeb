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
package com.yscf.core.service.system;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.achievo.framework.exception.FrameworkException;
import com.yscf.core.model.system.SysAccount;



/**
 * 
 * Description：<br> 
 * 系统账户资金
 * @author  JunJie.Liu
 * @date    2015年12月18日
 * @version v1.0.0
 */
public interface ISysAccountService {
	
	/**
	 * 
	 * Description：<br> 
	 *  系统资金增加金额
	 * @author  JunJie.Liu
	 * @date    2015年12月18日
	 * @version v1.0.0
	 * @param amount
	 * 			金额
	 * @param userId
	 * 			客户id，没有传null
	 * @param tradeType
	 * 			交易类型，从交易类型中选取
	 * @param pkId
	 * 			外键，没有传null
	 * @param desc
	 * 			备注，没有传null
	 */
	public void updateAddSystemAmount(BigDecimal amount,String userId,String tradeType,String pkId,String desc) throws FrameworkException;
	
	/**
	 * 
	 * Description：<br> 
	 *  系统资金减少金额
	 * @author  JunJie.Liu
	 * @date    2015年12月18日
	 * @version v1.0.0
	 * @param amount
	 * 			金额
	 * @param userId
	 * 			客户id，没有传null
	 * @param tradeType
	 * 			交易类型，从交易类型中选取,必填
	 * @param pkId
	 * 			外键，没有传null
	 * @param desc
	 * 			备注，没有传null
	 */
	public void updateSubSystemAmount(BigDecimal amount,String userId,String tradeType,String pkId,String desc) throws FrameworkException;
	
	/**
	 * 
	 * Description：<br> 
	 * 得到系统账户信息
	 * @author  Jie.Zou
	 * @date    2016年1月15日
	 * @version v1.0.0
	 * @return
	 */
	public SysAccount getSysAccount();

}


