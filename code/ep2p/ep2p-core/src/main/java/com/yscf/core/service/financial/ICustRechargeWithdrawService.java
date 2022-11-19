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
import java.util.List;
import java.util.Map;

import com.achievo.framework.exception.FrameworkException;
import com.yscf.core.model.financial.CustFundWater;
import com.yscf.core.model.financial.CustRechargeWithdraw;

/**
 * Description：<br> 
 * 客户个人中心 充值提现 业务层接口
 * @author  JingYu.Dai
 * @date    2015年12月21日
 * @version v1.0.0
 */
public interface ICustRechargeWithdrawService {

	/**
	 * Description：<br> 
	 * escf3.0 ep2p 个人中心 充值 流水查询  分页
	 * @author  JingYu.Dai
	 * @date    2015年12月17日
	 * @version v1.0.0
	 * @param custFundWater 资金流水对象
	 * @param pageIndex 分页 条数
	 * @param pageSize 分页 页码数
	 * @return List<CustFundWater>
	 */
	public List<CustRechargeWithdraw> selectRechargePage(CustFundWater custFundWater,Integer pageIndex, Integer pageSize) throws FrameworkException;
	
	/**
	 * 
	 * Description：<br> 
	 * escf3.0 ep2p 个人中心 充值 流水查询  总数
	 * @author  JingYu.Dai
	 * @date    2015年12月18日
	 * @version v1.0.0
	 * @param custFundWater 资金流水对象
	 * @param pageIndex 分页 条数
	 * @param pageSize 分页 页码数
	 * @return
	 */
	public Integer selectRechargeTotal(CustFundWater custFundWater) throws FrameworkException;
		
	
	/**
	 * Description：<br> 
	 * escf3.0 ep2p 个人中心 提现 流水查询  分页
	 * @author  JingYu.Dai
	 * @date    2015年12月17日
	 * @version v1.0.0
	 * @param custFundWater 资金流水对象
	 * @param pageIndex 分页 条数
	 * @param pageSize 分页 页码数
	 * @return List<CustFundWater>
	 */
	public List<CustRechargeWithdraw> selectWithdrawPage(CustFundWater custFundWater,Integer pageIndex, Integer pageSize) throws FrameworkException;
	
	/**
	 * Description：<br> 
	 * escf3.0 ep2p 个人中心 提现 流水查询  总数
	 * @author  JingYu.Dai
	 * @date    2015年12月18日
	 * @version v1.0.0
	 * @param custFundWater 资金流水对象
	 * @return Integer 总记录条数
	 */
	public Integer selectWithdrawTotal(CustFundWater custFundWater) throws FrameworkException;
	
	/**
	 * Description：<br> 
	 * 根据客户统计 提现总额
	 * @author  JingYu.Dai
	 * @date    2015年12月24日
	 * @version v1.0.0
	 * @param cusId
	 * @return
	 * @throws FrameworkException
	 */
	public Map<String,BigDecimal> getAmountSumByCustId(String cusId) throws FrameworkException;
	
}


