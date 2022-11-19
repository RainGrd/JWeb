/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 体验金接口
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年12月23日     Jie.Zou		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.financial;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.achievo.framework.exception.FrameworkException;
import com.yscf.core.model.business.BizBorrowDetail;
import com.yscf.core.model.financial.BizAccountExperience;

/**
 * Description：<br> 
 * 体验金接口
 * @author  Jie.Zou
 * @date    2015年12月23日
 * @version v1.0.0
 */
public interface IBizAccountExperienceService {
	
	/**
	 * 
	 * Description：<br> 
	 * 获取客户的资金类别对象
	 * @author  Jie.Zou
	 * @date    2015年12月24日
	 * @version v1.0.0
	 * @param customerId
	 * @return
	 */
	public BizAccountExperience selectExperienceAccountByCustomerId(String customerId);
	
	/**
	 * 
	 * Description：<br> 
	 * 体验标投标成功时，冻结金额冻结的金额解冻，解冻的资金不会到体验金的可用余额
	 * @author  Jie.Zou
	 * @date    2015年12月28日
	 * @version v1.0.0
	 * @param map
	 * @param time
	 */
	void unfreezeAmountWhenBiddingSuccess(BizBorrowDetail bid,Date time,String note);
	
	/**
	 * 
	 * Description：<br> 
	 * 得到账户总额，可用余额+冻结金额
	 * @author  Jie.Zou
	 * @date    2015年12月28日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 * @throws FrameworkException
	 */
	BigDecimal getCustBalance(String userId) throws FrameworkException;
	
	/**
	 * 
	 * Description：<br> 
	 * 通过客户ID得到体验金资金类别对象，不存在就返回null
	 * @author  Jie.Zou
	 * @date    2015年12月29日
	 * @version v1.0.0
	 * @param customerId
	 * @return
	 */
	BizAccountExperience selectAccountExperience(String customerId);
	
	/**
	 * 
	 * Description：<br> 
	 * 通过体验金卷集合Id得到体验金集合
	 * @author  Jie.Zou
	 * @date    2015年12月31日
	 * @version v1.0.0
	 * @param pids
	 * @return
	 */
	List<BizAccountExperience> selectExperienceByPids(String[] pids);
	
	/**
	 * 
	 * Description：<br> 
	 * 批量更新用户体验金金额可用余额
	 * @author  Jie.Zou
	 * @date    2016年1月12日
	 * @version v1.0.0
	 * @param userIds 用户集合
	 * @param amount  发生金额
	 * @param fundWaterType 支出/收入
	 * @param tradeType     资金流水类型
	 * @param note          资金流水备注
	 * @param fk   外键PID
	 * @return
	 */
	public int updateAmountByCustIds(List<String> userIds ,BigDecimal amount,String fundWaterType,String tradeType,String note,String fk);
}


