/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 客户账户管理服务接口
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.yscf.core.model.comm.ExportObjectModel;
import com.yscf.core.model.financial.CustomerAccount;
import com.yscf.core.model.ptp.financial.CustomerAccountModel;

/**
 * Description：<br> 
 * 客户账户管理服务接口
 * @author  Allen
 * @date    2015年9月6日
 * @version v1.0.0
 */
public interface ICustomerAccountService {
	/**
	 * Description：新增系统客户账户
	 * @author  Allen
	 * @date    2015年9月8日
	 * @version v1.0.0
	 * @param record
	 * @return int 受影响行数
	 * @throws FrameworkException
	 */
	public int insert(CustomerAccount record) throws FrameworkException;
	
	/**
	 * 
	 * Description：<br> 
	 * 更新账户
	 * @author  JunJie.Liu
	 * @date    2015年11月13日
	 * @version v1.0.0
	 * @param customerAccount
	 * @return
	 */
	public int updateByPrimaryKey(CustomerAccount customerAccount);
	
	/**
	 * Description：查询客户账户列表
	 * @author  Allen
	 * @date    2015年9月9日
	 * @version v1.0.0
	 * @param customerAccount 系统客户账户
	 * @return List<CustomerAccount>
	 * @throws FrameworkException
	 */
	List<CustomerAccount> selectAll(CustomerAccount customerAccount, PageInfo pageInfo);
	/**
	 * Description：批量删除客户账户列表
	 * @author  heng.wang
	 * @date    2015年9月11日
	 * @version v1.0.0
	 * @param String pids 系统客户账户
	 * @return int
	 * @throws FrameworkException
	 */
	int deleteBtach(String pids);
	
	/**
	 * Description：查询客户账户列表，带分页功能
	 * @author  Allen
	 * @date    2015年9月14日
	 * @version v1.0.0
	 * @param CustomerAccount customerAccount 系统客户账户
	 * @return PageList<CustomerAccount>
	 * @throws FrameworkException
	 */
	PageList<CustomerAccount> selectAllPage(HashMap<String,Object> parasMap, PageInfo info) ;
	
	/**
	 * 
	 * Description：<br> 
	 * 导出客户账户列表
	 * @author  JunJie.Liu
	 * @date    2015年11月2日
	 * @version v1.0.0
	 * @param customerAccountVO
	 * @param eom
	 * @return
	 */
	public List<CustomerAccountModel> selectCustomerAccountVOEom(
			CustomerAccountModel customerAccountVO, ExportObjectModel eom);
	
	/**
	 * 
	 * Description：<br> 
	 * 根据用户id，查询其账户
	 * @author  JunJie.Liu
	 * @date    2015年11月13日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	public CustomerAccount getByCusterId(String userId);
	
	/**
	 * 
	 * Description：<br> 
	 * 可用余额转成冻结金额
	 * @author  JunJie.Liu
	 * @date    2015年12月17日
	 * @version v1.0.0
	 * @param amount
	 * 			金额
	 * @param userId
	 * 			用户id
	 * @param borrowId
	 * 			借款id
	 * @param tradeType
	 * 			TradeTypeConstant  交易类型
	 * @param note
	 * 			备注
	 * 
	 * @throws FrameworkException
	 */
	public void updateAvailableAmountToFreezeAmount(BigDecimal amount,
			String userId, String borrowId)throws FrameworkException;
	
	/**
	 * 
	 * Description：<br> 
	 * 流标，冻结金额转成可用余额
	 * @author  JunJie.Liu
	 * @date    2015年12月17日
	 * @version v1.0.0
	 * @param amount
	 * 			金额
	 * @param userId
	 * 			用户id
	 * @param borrowId
	 * 			借款id
	 * @param tradeType
	 * 			交易类型
	 * @param note
	 * 			备注
	 */
	public void updateFreezeAmountToAvailableAmount(BigDecimal amount,String userId,String borrowId)throws FrameworkException;
	
	/**
	 * 
	 * Description：<br> 
	 * 借款人还款后的本金或利息直接进入投资人帐户余额的普通资金。增加可用余额
	 * @author  JunJie.Liu
	 * @date    2015年12月17日
	 * @version v1.0.0
	 * @param amount
	 * 			金额
	 * @param userId
	 * 			客户id
	 * @param fk
	 * 			外键，交易类型是否需要跳转,无需跳转传Null
	 * @param tradeType
	 * 			交易类型
	 * @param note
	 * 			描述
	 */
	public void updateAddAvailableAmount(BigDecimal amount,String userId,String fk,String tradeType,String note)throws FrameworkException;
	
	/**
	 * 
	 * Description：<br> 
	 * 购买债权，减少可用余额
	 * @author  JunJie.Liu
	 * @date    2015年12月17日
	 * @version v1.0.0
	 * @param amount
	 * 			金额
	 * @param userId
	 * 			客户id
	 * @param fk
	 * 			外键，交易类型是否需要跳转,无需跳转传Null
	 * @param tradeType
	 * 			交易类型
	 * @param note
	 *			 描述
	 */
	public void updateDeductedAvailableAmount(BigDecimal amount,String userId,String fk,String tradeType,String note)throws FrameworkException;
	
	/**
	 * 
	 * Description：<br> 
	 * 满标，扣除冻结金额
	 * @author  JunJie.Liu
	 * @date    2015年12月17日
	 * @version v1.0.0
	 * @param amount
	 * 			金额
	 * @param userId
	 * 			客户id
	 * @param borrowId
	 * 			借款id
	 * @param tradeType
	 * 			交易类型
	 * @param note
	 * 			描述
	 */
	public void updateDeductedFreezeAmount(BigDecimal amount,String userId,String borrowId,String tradeType,String note)throws FrameworkException;
	
	/**
	 * 
	 * Description：<br> 
	 * 更新待收金额
	 * @author  JunJie.Liu
	 * @date    2015年12月17日
	 * @version v1.0.0
	 * @param dueAmount
	 * @param userId
	 */
	public void updateDueAmount(BigDecimal dueAmount,String userId);
	
	/**
	 * 
	 * Description：<br> 
	 * 扣除用户利息管理费，利息管理费按比例进入系统资金帐户和备付金帐户
	 * @author  JunJie.Liu
	 * @date    2015年12月17日
	 * @version v1.0.0
	 * @param imFee
	 * @param userId
	 * @param des5 
	 */
	public void updateDeductedInterestManagementFee(BigDecimal imFee,String userId,String pk, String des5)throws FrameworkException;
	
	/**
	 * 
	 * Description：<br> 
	 * 体验金转换为冻结体验金
	 * @author  Jie.Zou
	 * @date    2015年12月22日
	 * @version v1.0.0
	 * @param amount 体验金
	 * @param userId 需要转换的客户
	 * @param borrowId 借款ID
	 * @throws FrameworkException 
	 */
	public void updateExperienceAmountToFreezeAmount(BigDecimal amount,
			String userId, String borrowId,String[] pids)throws FrameworkException;
	
	/**
	 * 
	 * Description：<br> 
	 * 增加客户账户表可用余额，但是不进行其他操作（例如：不增加资金流水信息，不修改资金类别标信息）
	 * @author  Jie.Zou
	 * @date    2015年12月26日
	 * @version v1.0.0
	 * @param amount
	 * @param userId
	 * @param fk
	 * @throws FrameworkException
	 */
	public void updateAddAvailableAmount(BigDecimal amount,String userId,String fk)throws FrameworkException;
	
	/**
	 * 
	 * Description：<br> 
	 * 执行涉及多种账户资金支出的操作
	 * 1.在每种涉及的资金类别中减去金额2.增加相应的流水3.增加资金类别变化明细
	 * @author  Jie.Zou
	 * @date    2015年12月29日
	 * @version v1.0.0
	 * @param customerId 客户Id
	 * @param amount  发生金额
	 * @param tradeType 资金流水类型
	 * @param fkey 外键Id
	 * @param tallyNote 流水备注
	 * @param time 发生时间
	 */
	void updateAvailableAmount(String customerId,BigDecimal amount,String tradeType,String fkey,String tallyNote,Date time) throws FrameworkException ;
	
	/**
	 * 
	 * Description：<br> 
	 * 得到客户的账户总金额（包括体验金）
	 * @author  Jie.Zou
	 * @date    2015年12月29日
	 * @version v1.0.0
	 * @param custmorId
	 * @return
	 * @throws FrameworkException 
	 */
	BigDecimal getUserBalanceAmount(String customerId) throws FrameworkException;
	
	/**
	 * 
	 * Description：<br> 
	 * 得到用户可用余额（不包括体验金）
	 * @author  Jie.Zou
	 * @date    2015年12月29日
	 * @version v1.0.0
	 * @return
	 */
	BigDecimal getUserAvailableAmountExculdExperience(String customerId) throws FrameworkException;
	
	/**
	 * 
	 * Description：<br> 
	 * 得到客户的账户总金额（不包括体验金）
	 * @author  Jie.Zou
	 * @date    2016年1月12日
	 * @version v1.0.0
	 * @param customerId
	 * @return
	 * @throws FrameworkException
	 */
	BigDecimal getUserBalanceAmountExculdExperience(String customerId);
	
	/**
	 * 
	 * Description：<br> 
	 * 体验标流标，冻结体验金改为可用体验金
	 * @author  Jie.Zou
	 * @date    2015年12月30日
	 * @version v1.0.0
	 * @param amount
	 * @param userId
	 * @param borrowId
	 */
	void updateExperienceAmountToAvailableAmount(BigDecimal amount,String userId,String borrowId)  throws FrameworkException;
	
	/**
	 * 
	 * Description：<br> 
	 * 批量更新客户的账户余额
	 * @author  Jie.Zou
	 * @date    2016年1月11日
	 * @version v1.0.0
	 * @param userIds
	 * @param amount
	 * @return
	 */
	int updateCustAccountByUsersId(List<String> userIds,BigDecimal amount);
	
	/**
	 * 
	 * Description：<br> 
	 * 批量更新客户的账户可用体验金余额
	 * @author  Jie.Zou
	 * @date    2016年1月12日
	 * @version v1.0.0
	 * @param userIds
	 * @param amount
	 * @return
	 */
	int updateCustExperienceAmountByUsersId(List<String> userIds,BigDecimal amount);
	
	/**
	 * 
	 * Description：<br> 
	 * 更新客户的充值总额
	 * @author  Jie.Zou
	 * @date    2016年1月12日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 */
	void updateCustRechargeAmount(String userId,BigDecimal amount);
	
	/**
	 * 
	 * Description：<br> 
	 * 批量更新客户的账户余额
	 * @author  Jie.Zou
	 * @date    2016年3月3日
	 * @version v1.0.0
	 * @param maps
	 * @return
	 */
	int updateCustAccountByUsersId(Map<String, Object> maps);
	
	/**
	 * 
	 * Description：<br> 
	 * 批量修改账户可用余额根据（map）
	 * @author  Jie.Zou
	 * @date    2016年3月4日
	 * @version v1.0.0
	 * @param maps
	 * @return
	 */
	int updateCustAccountByMaps(Map<String, Object> maps);
}


