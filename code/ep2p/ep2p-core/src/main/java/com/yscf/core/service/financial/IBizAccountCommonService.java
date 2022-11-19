/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 普通资金类别处理接口
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月6日     Allen		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.financial;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.achievo.framework.exception.FrameworkException;
import com.yscf.core.model.business.BizBorrow;
import com.yscf.core.model.financial.BizAccountCommon;
import com.yscf.core.model.financial.CustFundWater;


/**
 * Description:普通资金类别处理接口
 * @author  JingYu.Dai
 * @date    2015年9月25日
 * @version v1.0.0
 */
public interface IBizAccountCommonService {
	
	/**
	 * 
	 * Description：<br> 
	 * 将借款成功的金额加到借款者账户的普通金额类别中
	 * @author  Jie.Zou
	 * @date    2015年12月28日
	 * @version v1.0.0
	 * @param bizBorrow
	 * @throws FrameworkException 
	 */
	void addSuccessBorrowAmount(BizBorrow bizBorrow,Date time) throws FrameworkException;
	
	/**
	 * 
	 * Description：<br> 
	 * 普通资金更新可用余额
	 * 添加一个资金流水 1.更新可用余额 2.添加流水 3.添加资金类别的变化明细
	 * @author  Jie.Zou
	 * @date    2015年12月28日
	 * @version v1.0.0
	 * @param userId 用户Id
	 * @param fundWateType 资金流水类型
	 * @param amount 发生额
	 * @param note 资金流水备注
	 * @param time 时间
	 * @return
	 * @throws FrameworkException
	 */
	CustFundWater modifyAvailableAmountWithTallyCreateNoTran(
			String userId,String fundtallyType, String fundWateType, BigDecimal amount, String note,
			Date time) throws FrameworkException;
	
	/**
	 * 
	 * Description：<br> 
	 * 获取普通资金总额（可用余额+冻结金额）
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
	 * 通过客户ID得到普通资金类别对象，不存在就返回null
	 * @author  Jie.Zou
	 * @date    2015年12月29日
	 * @version v1.0.0
	 * @param customerId
	 * @return
	 */
	BizAccountCommon selectAccountCommon(String customerId);
	
	/**
	 * 
	 * Description：<br> 
	 * 更新客户普通资金类别表
	 * @author  Jie.Zou
	 * @date    2015年12月28日
	 * @version v1.0.0
	 * @param common
	 * @param amount
	 * @return
	 */
	BizAccountCommon modifyAvailableAmountNoTran(BizAccountCommon common,BigDecimal amount);
	
	/**
	 * 
	 * Description：<br> 
	 * 批量更新用户普通金额可用余额
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
	int updateAmountByCustIds(List<String> userIds ,BigDecimal amount,String fundWaterType,String tradeType,String note,String fk);
	
	/**
	 * 
	 * Description：<br> 
	 * 批量更新用户普通金额可用余额MAP
	 * @author  Jie.Zou
	 * @date    2016年3月3日
	 * @version v1.0.0
	 * @param maps 
	 * @param fundWaterType 支出/收入
	 * @param tradeType 资金流水类型
	 * @param note  资金流水备注
	 * @param fk 外键PID
	 * @return
	 */
	int updateAmountByCustIds(Map<String, Object> maps,String fundWaterType,String tradeType,String note,String fk);
}


