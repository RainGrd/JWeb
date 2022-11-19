package com.yscf.core.dao.financial;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;

import com.achievo.framework.dao.IBaseDao;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.yscf.core.model.financial.CustFundWater;
import com.yscf.core.model.financial.CustRechargeWithdraw;

/**
 * Description：<br> 
 * 客户个人中心 充值提现 数据访问接口
 * @author  JingYu.Dai
 * @date    2015年12月21日
 * @version v1.0.0
 */
@MapperScan("custRechargeWithdrawMapper")
public interface CustRechargeWithdrawMapper extends IBaseDao<BaseEntity, String> {
   
	/**
	 * Description：<br> 
	 * escf3.0 ep2p 个人中心 充值提现 流水查询  分页
	 * @author  JingYu.Dai
	 * @date    2015年12月17日
	 * @version v1.0.0
	 * @param custFundWater 资金流水对象
	 * @param pageIndex 分页 条数
	 * @param pageSize 分页 页码数
	 * @return List<CustRechargeWithdraw> 
	 */
	public List<CustRechargeWithdraw> selectRechargePage(
			@Param("map")CustFundWater businessTypes, @Param("pageIndex")Integer pageIndex, @Param("pageSize")Integer pageSize);
	
	/**
	 * Description：<br> 
	  * escf3.0 ep2p 个人中心 充值提现 流水查询  总数
	 * @author  JingYu.Dai
	 * @date    2015年12月17日
	 * @version v1.0.0
	 * @param custFundWater 资金流水对象
	 * @return Integer 总记录条数
	 */
	public Integer selectRechargeTotal(
			@Param("map")CustFundWater businessTypes);
	/**
	 * Description：<br> 
	 * escf3.0 ep2p 个人中心 充值提现 流水查询  分页
	 * @author  JingYu.Dai
	 * @date    2015年12月17日
	 * @version v1.0.0
	 * @param custFundWater 资金流水对象
	 * @param pageIndex 分页 条数
	 * @param pageSize 分页 页码数
	 * @return List<CustRechargeWithdraw> 
	 */
	public List<CustRechargeWithdraw> selectWithdrawPage(
			@Param("map")CustFundWater businessTypes, @Param("pageIndex")Integer pageIndex, @Param("pageSize")Integer pageSize);
	
	/**
	 * Description：<br> 
	  * escf3.0 ep2p 个人中心 充值提现 流水查询  总数
	 * @author  JingYu.Dai
	 * @date    2015年12月17日
	 * @version v1.0.0
	 * @param custFundWater 资金流水对象
	 * @return Integer 总记录条数
	 */
	public Integer selectWithdrawTotal(
			@Param("map")CustFundWater businessTypes);
	
	/**
	 * Description：<br> 
	 * 根据客户统计提现总额
	 * @author  JingYu.Dai
	 * @date    2015年12月24日
	 * @version v1.0.0
	 * @param cusId
	 * @return
	 * @throws FrameworkException
	 */
	public Map<String,BigDecimal> getAmountSumByCustId(String cusId);
}