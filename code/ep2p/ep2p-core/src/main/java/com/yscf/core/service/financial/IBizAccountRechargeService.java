/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 体现管理服务接口
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

import com.achievo.framework.exception.FrameworkException;
import com.yscf.core.model.financial.BizAccountRecharge;
import com.yscf.core.model.financial.BizRechargeOffline;
import com.yscf.core.model.financial.BizRechargeOnline;
import com.yscf.core.model.financial.CustFundWater;


/**
 * Description:充值业务处理接口
 * @author  JingYu.Dai
 * @date    2015年9月25日
 * @version v1.0.0
 */
public interface IBizAccountRechargeService {
	   
	   /**
	    * 
	    * Description：<br> 
	    * 更新可用金额的同时，添加一个资金流水
	    * 1.更新可用余额
	    * 2.添加流水
	    * 3.添加资金类别的变化明细
	    * @author  Jie.Zou
	    * @date    2015年12月12日
	    * @version v1.0.0
	    * @param userId 用户ID
	    * @param fundtallyType 资金流水类别
	    * @param fundWateType 流水类别
	    * @param amount 发生额度
	    * @param note 流水备注
	    * @param time 操作时间
	    * @return
	    */
	   CustFundWater modifyAvailableAmountWithTallyCreateNoTran(String userId,String fundtallyType,String fundWateType,BigDecimal amount,String note,Date time ,String fk) throws FrameworkException;
	   
	   /**
	    * 
	    * Description：<br> 
	    * 更新可用金额，不进行其他额外的操作（如添加流水）
	    * @author  Jie.Zou
	    * @date    2015年12月15日
	    * @version v1.0.0
	    * @param accountRecharge
	    * @param amount
	    * @return
	    */
	   BizAccountRecharge modifyAvailableAmountNoTran(BizAccountRecharge accountRecharge,BigDecimal amount);
	   
	   /**
		* 
		* Description：<br> 
		* 新增资金类别变化明细
		* @author  Jie.Zou
		* @date    2015年12月15日
		* @version v1.0.0
		* @param accountRecharge 充值资金实体
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
		 * 执行线上充值操作。主要完成添加金额到可用金额，以及添加一条充值流水
		 * @author  Jie.Zou
		 * @date    2015年12月15日
		 * @version v1.0.0
		 * @param recharge
		 * @param note
		 * @param time
		 */
	    void doRechargeOnlineNoTran(BizRechargeOnline recharge,String note,Date time);
	    
	    /**
	     * Description：<br> 
	     * 执行线下充值操作。主要完成添加金额到可用金额，以及添加一条充值流水
	     * @author  JingYu.Dai
	     * @date    2016年1月5日
	     * @version v1.0.0
	     * @param recharge
	     * @param note
	     * @param time
	     */
	    void doRechargeOfflineNoTran(BizRechargeOffline recharge,
				String note, Date time);
	    
	    /**
		 * Description：<br> 
		 * 获取用户账户总余额，既总可用余额和总冻结金额之和
		 * @author  Jie.Zou
		 * @date    2015年12月12日
		 * @version v1.0.0
		 * @param userId
		 * @return
		 */
		BigDecimal getCustBalance(String userId) throws FrameworkException;
		
		/**
		 * 
		 * Description：<br> 
		 * 通过客户ID得到充值资金类别对象，不存在就返回null
		 * @author  Jie.Zou
		 * @date    2015年12月29日
		 * @version v1.0.0
		 * @param customerId
		 * @return
		 */
		BizAccountRecharge selectAccountRecharge(String customerId);
}


