/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 用户管理服务实现
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
import java.util.Map;

import javax.annotation.Resource;

import net.sf.ehcache.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.service.impl.BaseService;
import com.achievo.framework.util.DateUtil;
import com.yscf.common.Constant.Constant;
import com.yscf.common.Constant.TradeTypeConstant;
import com.yscf.core.dao.financial.BizAccountAmountDetailMapper;
import com.yscf.core.dao.financial.BizAccountRechargeMapper;
import com.yscf.core.model.financial.BizAccountAmountDetail;
import com.yscf.core.model.financial.BizAccountRecharge;
import com.yscf.core.model.financial.BizRechargeOffline;
import com.yscf.core.model.financial.BizRechargeOnline;
import com.yscf.core.model.financial.CustFundWater;
import com.yscf.core.service.financial.IBizAccountAmountDetailService;
import com.yscf.core.service.financial.IBizAccountRechargeService;
import com.yscf.core.service.financial.ICustFundWaterService;
import com.yscf.core.service.financial.ICustomerAccountService;
import com.yscf.core.util.MiscUtil;

/**
 * Description：充值资金业务处理实现类
 * @author  JingYu.Dai
 * @date    2015年9月25日
 * @version v1.0.0
 */
@Service("bizAccountRechargeServiceImpl")
public class BizAccountRechargeServiceImpl extends BaseService<BaseEntity, String> implements IBizAccountRechargeService {

	@Autowired
	public BizAccountRechargeServiceImpl(BizAccountRechargeMapper dao) {
		super(dao);
	}

	@Resource
	private BizAccountAmountDetailMapper amountDetailMapper;
	
	@Resource(name = "custFundWaterService")
	private ICustFundWaterService custFundWaterService;
	
	@Resource(name = "bizAccountAmountDetailServiceImpl")
	private IBizAccountAmountDetailService bizAccountAmountDetailService;
	
	@Resource
	private ICustomerAccountService accountService;
	
	private static Map<String, Object> rechargeLocks = new ConcurrentHashMap<String, Object>();

	public static Object getRechargeLocks(String rechargeOnlineId) {
		if(!rechargeLocks.containsKey(rechargeOnlineId)){
			rechargeLocks.put(rechargeOnlineId, new Object());
		}
		return rechargeLocks.get(rechargeOnlineId);
	}


	@Override
	public CustFundWater modifyAvailableAmountWithTallyCreateNoTran(
			String userId, String fundtallyType, String fundWateType, BigDecimal amount, String note,
			Date time,String fk) throws FrameworkException {
		
		BizAccountRecharge recharge =  this.getAccountAmountDataIfCreate(userId);
		//更新充值资金类别表
		recharge = this.modifyAvailableAmountNoTran(recharge, amount);
		//更新客户账户表
		modifyAvailableAmountCustomer(amount, userId, recharge,fundtallyType);
		//添加资金流水和资金流水明细
		return this.addFundAndAmountDetail(userId, fundtallyType,fundWateType, amount, note, time, recharge,fk);
	}
	
	
	/**
	 * 
	 * Description：<br> 
	 * 获取用户的充值资金类别的对象，如果不存在这样的对象，则创建一个新的对象保存起来，在返回
	 * @author  Jie.Zou
	 * @date    2015年12月15日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 * @throws FrameworkException 
	 */
	public BizAccountRecharge  getAccountAmountDataIfCreate(String userId) throws FrameworkException{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("customerId", userId);
		BizAccountRechargeMapper mapper =  (BizAccountRechargeMapper)getDao();
		BizAccountRecharge accountRecharge = (BizAccountRecharge) mapper.selectAccountRechargeByCustomerId(userId);
		if(accountRecharge == null){
			accountRecharge = new BizAccountRecharge();
			accountRecharge.setPid(accountRecharge.getPK());
			accountRecharge.setCustomerId(userId);
			accountRecharge.setAvailableAmount(BigDecimal.ZERO);
			accountRecharge.setFrozenAmount(BigDecimal.ZERO);
			accountRecharge.setCreateTime(DateUtil.format(DateUtil.getSystemDate()));
			accountRecharge.setCreateUser(userId);
			accountRecharge.setStatus(Constant.PUBLIC_STATUS);
			accountRecharge.setVersion(Constant.PUBLIC_VERSION);
			mapper.insert(accountRecharge);
		}
		return accountRecharge;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 添加流水和类别资金变化明细
	 * @author  Jie.Zou
	 * @date    2015年12月14日
	 * @version v1.0.0
	 * @param userId 客户Id
	 * @param fundtallyType 资金流水类型
	 * @param fundWateType  收入/支出
	 * @param amount	发生额
	 * @param note   资金流水备注
	 * @param time   时间
	 * @return
	 * @throws FrameworkException
	 */
	private CustFundWater addFundAndAmountDetail(String userId,String fundtallyType ,String fundWateType,BigDecimal amount,String note, Date time,BizAccountRecharge accountRecharge,String fk) throws FrameworkException{
		
		//添加流水
		BigDecimal balance = accountService.getUserBalanceAmountExculdExperience(userId);
		CustFundWater fundWater = custFundWaterService.addFundWater(userId, fundWateType,fundtallyType, amount, balance, note, time,fk);
		
		//添加资金类别的变化明细。此处的余额非账户总余额，而是当前资金类别的余额
		BigDecimal rechargeBalance = this.getCustBalance(userId);
		this.addAccountAmountDetail(accountRecharge, amount, fundWater, rechargeBalance,Constant.ACCOUNTAMOUNT_TYPE_RECHARGE, time);
		return fundWater;
	}

	@Override
	public BizAccountRecharge modifyAvailableAmountNoTran(
			BizAccountRecharge accountRecharge, BigDecimal amount) {
		BigDecimal availableAmount = MiscUtil.getBigDecimalMoney(accountRecharge.getAvailableAmount()).add(amount);
		accountRecharge.setAvailableAmount(MiscUtil.getBigDecimalMoney(availableAmount));
		BizAccountRechargeMapper mapper = (BizAccountRechargeMapper)this.getDao();
		mapper.updateByPrimaryKeySelective(accountRecharge);
		return accountRecharge;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 更新客户账户明细（可用余额更新加入新充值金额）
	 * @author  Jie.Zou
	 * @date    2015年12月26日
	 * @version v1.0.0
	 * @param amount 充值金额
	 * @param userId 充值客户
	 * @param recharge 资金类别标对象
	 * @throws FrameworkException
	 */
	private void modifyAvailableAmountCustomer(BigDecimal amount,String userId,BizAccountRecharge recharge,String fundtallyType) throws FrameworkException{
		accountService.updateAddAvailableAmount(amount, userId, recharge.getPid());
		accountService.updateCustRechargeAmount(userId, amount);
	}

	@Override
	public void addAccountAmountDetail(BizAccountRecharge accountRecharge,
			BigDecimal amount, CustFundWater fundWater, BigDecimal balance,
			String fundType, Date time) {
		BizAccountAmountDetail detail = new BizAccountAmountDetail();
		detail.setPid(detail.getPK());
		detail.setCustomerId(accountRecharge.getCustomerId());
		detail.setFundType(fundType);
		detail.setPayConfigId(fundWater.getPid());
		detail.setFundtallyType(fundWater.getBusinessType());
		detail.setHappenValue(amount);
		detail.setBalance(balance);
		detail.setCreateTime(DateUtil.format(DateUtil.getSystemDate()));
		detail.setCreateUser(accountRecharge.getCustomerId());
		detail.setVersion(Constant.PUBLIC_VERSION);
		
		amountDetailMapper.insert(detail);
	}
	
	@Override
	public void doRechargeOnlineNoTran(BizRechargeOnline recharge,
			String note, Date time) {
		this.doRechargeNoTran(recharge.getCustomerId(), recharge.getAmount(),TradeTypeConstant.RECHARGE_TYPE_103, Constant.CUST_FUND_BUSINESS_TYPE_1, note, time,recharge.getPid());
	}
	
	@Override
	public void doRechargeOfflineNoTran(BizRechargeOffline recharge,
			String note, Date time) {
		this.doRechargeNoTran(recharge.getCustomerId(), recharge.getAmount(),TradeTypeConstant.RECHARGE_TYPE_101, Constant.CUST_FUND_BUSINESS_TYPE_1, note, time,recharge.getPid());
	}
	
	private void doRechargeNoTran(String userId,BigDecimal amount,String fundtallyType,String fundWateType,String note,Date time,String fk){
		try {
			this.modifyAvailableAmountWithTallyCreateNoTran(userId, fundtallyType, fundWateType, amount, note, time,fk);
		} catch (FrameworkException e) {
			e.printStackTrace();
		}
	}

	@Override
	public BigDecimal getCustBalance(String userId) throws FrameworkException {
		BizAccountRechargeMapper mapper =  (BizAccountRechargeMapper)getDao();
		BizAccountRecharge accountRecharge = (BizAccountRecharge) mapper.selectAccountRechargeByCustomerId(userId);
		if(accountRecharge!=null){
			if(accountRecharge.getAvailableAmount()==null){
				accountRecharge.setAvailableAmount(BigDecimal.ZERO);
			}
			if(accountRecharge.getFrozenAmount()==null){
				accountRecharge.setFrozenAmount(BigDecimal.ZERO);
			}
			return accountRecharge.getAvailableAmount().add(accountRecharge.getFrozenAmount());
		}
		return BigDecimal.ZERO;
	}


	@Override
	public BizAccountRecharge selectAccountRecharge(String customerId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("customerId", customerId);
		BizAccountRechargeMapper mapper =  (BizAccountRechargeMapper)getDao();
		BizAccountRecharge accountRecharge = (BizAccountRecharge) mapper.selectAccountRechargeByCustomerId(customerId);
		return accountRecharge;
	}
	

}


