/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 资金类别变化明细业务逻辑接口
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年12月14日     Jie.Zou		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.financial;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.yscf.core.model.financial.BizAccountRecharge;
import com.yscf.core.model.financial.CustFundWater;


/**
 * Description：<br> 
 * 资金类别变化明细业务逻辑接口
 * @author  Jie.Zou
 * @date    2015年12月14日
 * @version v1.0.0
 */
public interface IBizAccountAmountDetailService {
	
	  /**
	 * 
	 * Description：<br> 
	 * 新增资金类别变化明细
	 * @author  Jie.Zou
	 * @date    2015年12月15日
	 * @version v1.0.0
	 * @param accountCommon 普通资金实体
	 * @param amount  发生额
	 * @param fundWater 资金流水
	 * @param balance	 余额
	 * @param fundType  资金类型
	 * @param time   时间
	 */
	void addAccountAmountDetail(BizAccountRecharge accountRecharge,BigDecimal amount,CustFundWater fundWater,BigDecimal balance,String fundType,Date time);
	
	/**
	 * 
	 * Description：<br> 
	 * 批量新增资金类别变化明细
	 * @author  Jie.Zou
	 * @date    2016年1月12日
	 * @version v1.0.0
	 * @param custFWs 资金流水
	 * @param fundType 资金类别
	 * @param balance 资金类别余额
	 */
	void addAccountAmountDetails(List<CustFundWater> custFWs,String fundType);
}


