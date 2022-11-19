/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 普通资金类别处理实现类
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月6日     Allen		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.financial.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.service.impl.BaseService;
import com.achievo.framework.util.DateUtil;
import com.yscf.common.Constant.Constant;
import com.yscf.common.Constant.TradeTypeConstant;
import com.yscf.core.dao.financial.BizAccountAmountDetailMapper;
import com.yscf.core.dao.financial.BizAccountCommonMapper;
import com.yscf.core.exception.UpdateChangeVersionException;
import com.yscf.core.model.business.BizBorrow;
import com.yscf.core.model.financial.BizAccountAmountDetail;
import com.yscf.core.model.financial.BizAccountCommon;
import com.yscf.core.model.financial.CustFundWater;
import com.yscf.core.service.financial.IBizAccountAmountDetailService;
import com.yscf.core.service.financial.IBizAccountCommonService;
import com.yscf.core.service.financial.ICustFundWaterService;
import com.yscf.core.service.financial.ICustomerAccountService;
import com.yscf.core.util.MiscUtil;

/**
 * Description：普通资金处理实现类
 * @author  JingYu.Dai
 * @date    2015年9月25日
 * @version v1.0.0
 */
@Service("bizAccountCommonService")
public class BizAccountCommonServiceImpl extends BaseService<BaseEntity, String> implements IBizAccountCommonService {

	@Resource
	private ICustomerAccountService accountService;
	
	@Resource
	private BizAccountAmountDetailMapper amountDetailMapper;
	
	@Resource
	private IBizAccountAmountDetailService bizAccountAmountDetailService;
	
	@Resource
	private ICustFundWaterService custFundWaterService;
	
	@Autowired
	public BizAccountCommonServiceImpl(BizAccountCommonMapper dao) {
		super(dao);
	}

	@Override
	public void addSuccessBorrowAmount(BizBorrow bizBorrow,Date time) throws FrameworkException {
		modifyAvailableAmountWithTallyCreateNoTran(bizBorrow.getCustomerId(), Constant.CUST_FUND_BUSINESS_TYPE_1,TradeTypeConstant.OTHER_TYPE_503, bizBorrow.getBorrowMoney(), bizBorrow.getBorrowCode()+bizBorrow.getBorrowName()+"借款成功", time);
	}

	@Override
	public CustFundWater modifyAvailableAmountWithTallyCreateNoTran(
			String userId,String fundWateType,String fundtallyType,BigDecimal amount, String note,
			Date time) throws FrameworkException {
		BizAccountCommon common = this.getAccountAmountDataIfCreate(userId);
		//更新充值资金类别表
		common = this.modifyAvailableAmountNoTran(common, amount);
		//更新客户账户表
		modifyAvailableAmountCustomer(amount, userId, common);
		//添加资金流水和资金流水明细
		return this.addFundAndAmountDetail(userId, fundtallyType,fundWateType, amount, note, time, common);
	}
	
	@Override
	public BizAccountCommon modifyAvailableAmountNoTran(BizAccountCommon common,BigDecimal amount){
		BigDecimal availableAmount = MiscUtil.getBigDecimalMoney(common.getAvailableAmount()).add(amount);
		common.setAvailableAmount(MiscUtil.getBigDecimalMoney(availableAmount));
		BizAccountCommonMapper mapper =  (BizAccountCommonMapper)getDao();
		mapper.updateByPrimaryKeySelective(common);
		return common;
	}

	
	/**
	 * 
	 * Description：<br> 
	 * 通过客户ID得到客户的普通资金类别记录信息（没有则新建一条记录）
	 * @author  Jie.Zou
	 * @date    2015年12月28日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 * @throws FrameworkException
	 */
	public BizAccountCommon  getAccountAmountDataIfCreate(String userId) throws FrameworkException{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("customerId", userId);
		BizAccountCommonMapper mapper =  (BizAccountCommonMapper)getDao();
		BizAccountCommon accountCommon = (BizAccountCommon) mapper.selectAccountCommonByCustomerId(userId);
		if(accountCommon == null){
			accountCommon = new BizAccountCommon();
			accountCommon.setPid(accountCommon.getPK());
			accountCommon.setCustomerId(userId);
			accountCommon.setAvailableAmount(BigDecimal.ZERO);
			accountCommon.setFrozenAmount(BigDecimal.ZERO);
			accountCommon.setCreateTime(DateUtil.getSystemDate());
			accountCommon.setCreateUser(userId);
			accountCommon.setStatus(Constant.PUBLIC_STATUS);
			accountCommon.setVersion(Constant.PUBLIC_VERSION);
			mapper.insert(accountCommon);
		}
		return accountCommon;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 更新客户账户表
	 * @author  Jie.Zou
	 * @date    2015年12月28日
	 * @version v1.0.0
	 * @param amount 发生额
	 * @param userId 客户ID
	 * @param common 客户普通资金类别记录
	 * @throws FrameworkException
	 */
	private void modifyAvailableAmountCustomer(BigDecimal amount,String userId,BizAccountCommon common) throws FrameworkException{
		accountService.updateAddAvailableAmount(amount, userId, common.getPid());
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 添加资金流水和普通资金类别变化明细
	 * @author  Jie.Zou
	 * @date    2015年12月28日
	 * @version v1.0.0
	 * @param userId
	 * @param fundWateType
	 * @param amount
	 * @param note
	 * @param time
	 * @param accountCommon
	 * @return
	 * @throws FrameworkException
	 */
	private CustFundWater addFundAndAmountDetail(String userId,String fundtallyType,String fundWateType,BigDecimal amount,String note, Date time,BizAccountCommon accountCommon) throws FrameworkException{
		
		//添加流水
		BigDecimal balance = accountService.getUserBalanceAmountExculdExperience(userId);
		CustFundWater fundWater = custFundWaterService.addFundWater(userId, fundWateType,fundtallyType, amount, balance, note, time,accountCommon.getPid());
		
		//添加资金类别的变化明细。此处的余额非账户总余额，而是当前资金类别的余额
		BigDecimal commonBalance = this.getCustBalance(userId);
		this.addAccountAmountDetail(accountCommon, amount, fundWater, commonBalance,Constant.ACCOUNTAMOUNT_TYPE_COMMON, time);
		return fundWater;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 添加普通资金的资金类别变化明细
	 * @author  Jie.Zou
	 * @date    2015年12月28日
	 * @version v1.0.0
	 * @param accountCommon
	 * @param amount
	 * @param fundWater
	 * @param balance
	 * @param fundType
	 * @param time
	 */
	private void addAccountAmountDetail(BizAccountCommon accountCommon,
			BigDecimal amount, CustFundWater fundWater, BigDecimal balance,
			String fundType, Date time) {
		BizAccountAmountDetail detail = new BizAccountAmountDetail();
		detail.setPid(detail.getPK());
		detail.setCustomerId(accountCommon.getCustomerId());
		detail.setFundType(fundType);
		detail.setPayConfigId(fundWater.getPid());
		detail.setFundtallyType(fundWater.getBusinessType());
		detail.setHappenValue(amount);
		detail.setBalance(balance);
		detail.setCreateTime(DateUtil.format(DateUtil.getSystemDate()));
		detail.setCreateUser(accountCommon.getCustomerId());
		detail.setVersion(Constant.PUBLIC_VERSION);
		
		amountDetailMapper.insert(detail);
	}
	
	@Override
	public BigDecimal getCustBalance(String userId) throws FrameworkException {
		BizAccountCommonMapper mapper =  (BizAccountCommonMapper)getDao();
		BizAccountCommon accountCommon = (BizAccountCommon) mapper.selectAccountCommonByCustomerId(userId);
		if(accountCommon!=null){
			if(accountCommon.getAvailableAmount()==null){
				accountCommon.setAvailableAmount(BigDecimal.ZERO);
			}
			if(accountCommon.getFrozenAmount()==null){
				accountCommon.setFrozenAmount(BigDecimal.ZERO);
			}
			return accountCommon.getAvailableAmount().add(accountCommon.getFrozenAmount());
		}
		return BigDecimal.ZERO;
	}

	@Override
	public BizAccountCommon selectAccountCommon(String customerId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("customerId", customerId);
		BizAccountCommonMapper mapper =  (BizAccountCommonMapper)getDao();
		BizAccountCommon accountCommon = (BizAccountCommon) mapper.selectAccountCommonByCustomerId(customerId);
		return accountCommon;
	}

	@Override
	public int updateAmountByCustIds(List<String> userIds ,BigDecimal amount,String fundWaterType,String tradeType,String note,String fk) {
		BizAccountCommonMapper mapper =  (BizAccountCommonMapper)getDao();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userIds", userIds);
		map.put("amount", amount);
		//批量更新客户普通资金的可用余额
		int result = mapper.updateAmountByCustIds(map);
		if(result<1){
			throw new UpdateChangeVersionException("批量更新客户的普通资金可用余额失败");
		}
		//批量更新客户账户的可用余额
		result = accountService.updateCustAccountByUsersId(userIds, amount);
		if(result<1){
			throw new UpdateChangeVersionException("批量更新客户的账户可用余额失败");
		}
		//批量增加资金流水
		List<CustFundWater> custFWs = custFundWaterService.addFundWaters(userIds, fundWaterType, tradeType, amount, note, DateUtil.getSystemDate(), fk);
		//批量增加资金类别变化明细
		bizAccountAmountDetailService.addAccountAmountDetails(custFWs, Constant.ACCOUNTAMOUNT_TYPE_COMMON);
		return result;
	}

	@Override
	public int updateAmountByCustIds(Map<String, Object> maps,
			String fundWaterType, String tradeType,
			String note, String fk) {
		BizAccountCommonMapper mapper =  (BizAccountCommonMapper)getDao();
		//批量更新客户普通资金的可用余额
		int result = mapper.updateAmountByMap(maps);
		if(result<1){
			throw new UpdateChangeVersionException("批量更新客户的普通资金可用余额失败");
		}
		
		//批量更新客户账户的可用余额
		result = accountService.updateCustAccountByMaps(maps);
		if(result<1){
			throw new UpdateChangeVersionException("批量更新客户的账户可用余额失败");
		}
		//批量增加资金流水
		List<CustFundWater> custFWs = custFundWaterService.addFundWaters(maps, fundWaterType, tradeType, note, DateUtil.getSystemDate(), fk);
		//批量增加资金类别变化明细
		bizAccountAmountDetailService.addAccountAmountDetails(custFWs, Constant.ACCOUNTAMOUNT_TYPE_COMMON);
		return result;
	}
	
	
}


