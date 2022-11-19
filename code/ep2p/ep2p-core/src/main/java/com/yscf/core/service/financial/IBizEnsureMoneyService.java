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
 * 2015年10月21日     jenkin.yu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.financial;

import java.math.BigDecimal;

import com.achievo.framework.exception.FrameworkException;
import com.yscf.core.model.financial.BizEnsureMoney;

/**
 * Description：<br> 
 * TODO
 * @author  jenkin.yu
 * @date    2015年10月21日
 * @version v1.0.0
 */
public interface IBizEnsureMoneyService {

	/**
	 * 
	 * Description：<br> 
	 * 保存备付金调增
	 * @author  Yu.Zhang
	 * @date    2015年10月28日
	 * @version v1.0.0
	 * @param bizEnsureMoney
	 * @return
	 */
	int saveEnsureAdjustment(BizEnsureMoney bizEnsureMoney);
	
	
	/**
	 * 
	 * Description：<br> 
	 *  备付金增加金额
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
	public void updateAddProvisions(BigDecimal amount,String userId,String tradeType,String pkId,String desc) throws FrameworkException;
	
	/**
	 * 
	 * Description：<br> 
	 *  备付金减少金额
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
	public void updateSubProvisions(BigDecimal amount,String userId,String tradeType,String pkId,String desc) throws FrameworkException;

	
	/**
	 * 
	 * Description：<br> 
	 * 获取风险备用金
	 * @author  JunJie.Liu
	 * @date    2016年1月15日
	 * @version v1.0.0
	 * @return
	 */
	public BigDecimal getMoney();
	
}


