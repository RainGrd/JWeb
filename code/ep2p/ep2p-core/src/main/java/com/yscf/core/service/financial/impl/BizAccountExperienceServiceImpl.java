/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 体验金接口实现
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年12月23日     Jie.Zou		Initial Version.
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
import com.sun.star.uno.RuntimeException;
import com.yscf.common.Constant.Constant;
import com.yscf.common.Constant.TradeTypeConstant;
import com.yscf.core.dao.financial.BizAccountAmountDetailMapper;
import com.yscf.core.dao.financial.BizAccountExperienceMapper;
import com.yscf.core.dao.financial.BizFrozenWaterMapper;
import com.yscf.core.exception.UpdateChangeVersionException;
import com.yscf.core.model.business.BizBorrowDetail;
import com.yscf.core.model.financial.BizAccountAmountDetail;
import com.yscf.core.model.financial.BizAccountExperience;
import com.yscf.core.model.financial.BizFrozenWater;
import com.yscf.core.model.financial.CustFundWater;
import com.yscf.core.service.financial.IBizAccountAmountDetailService;
import com.yscf.core.service.financial.IBizAccountExperienceService;
import com.yscf.core.service.financial.ICustFundWaterService;
import com.yscf.core.service.financial.ICustomerAccountService;
import com.yscf.core.util.MiscUtil;

/**
 * Description：<br> 
 * 体验金接口实现
 * @author  Jie.Zou
 * @date    2015年12月23日
 * @version v1.0.0
 */
@Service("bizAccountExperienceServiceImpl")
public class BizAccountExperienceServiceImpl extends BaseService<BaseEntity, String> implements IBizAccountExperienceService  {

	@Resource
	private BizAccountAmountDetailMapper amountDetailMapper;
	
	@Resource
	private ICustFundWaterService custFundWaterService;
	
	@Resource
	private BizFrozenWaterMapper bizFrozenWaterMapper;
	
	@Resource
	private ICustomerAccountService accountService;
	
	@Resource
	private IBizAccountAmountDetailService bizAccountAmountDetailService;
	
	@Autowired
	public BizAccountExperienceServiceImpl(BizAccountExperienceMapper dao) {
		super(dao);
	}

	@Override
	public BizAccountExperience selectExperienceAccountByCustomerId(
			String customerId) {
		BizAccountExperienceMapper mapper = (BizAccountExperienceMapper)getDao();
		return mapper.selectAccountExperienceByCustomerId(customerId);
	}

	@Override
	public void unfreezeAmountWhenBiddingSuccess(BizBorrowDetail bid,
			Date time,String note) {
		this.subtractFrozenAmountWithTallyCreateNoTran(bid.getCustomerId(),bid.getBorrowId(), TradeTypeConstant.INVEST_TYPE_302, bid.getInvestmentAmount(), bid.getPid(), note, time);
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 某个操作之前进行了冻结金额，当确认操作成功时，将这部分的金额从冻结金额中减去，而这部分金额不会返回可用金额部分
	 * 1.从冻结金额减去金额2.添加流水3.添加资金类别变化明细
	 * @author  Jie.Zou
	 * @date    2015年12月28日
	 * @version v1.0.0
	 * @param userId
	 * @param fundTallyType
	 * @param amount
	 * @param key
	 * @param note
	 * @param time
	 * @return
	 */
	public CustFundWater subtractFrozenAmountWithTallyCreateNoTran(String userId,String borrowId,String fundTallyType,BigDecimal amount,String key,String note,Date time){
		try {
			//获取用户该借款冻结明细
			BigDecimal eAmount = BigDecimal.ZERO;
			List<BizFrozenWater> bizFrozenWaterList = bizFrozenWaterMapper.findList(userId, borrowId,TradeTypeConstant.INVEST_TYPE_302);
			for (BizFrozenWater bfw : bizFrozenWaterList) {
				eAmount = eAmount.add(bfw.getExperienceAmount() == null ? BigDecimal.ZERO	: bfw.getExperienceAmount());
			}
			if(eAmount.compareTo(amount)!=0){
				throw new RuntimeException("用户冻结金额不匹配，用户id为："+userId+",标的id为："+borrowId);
			}
			BizAccountExperience experience = this.subtractFrozenAmount(userId,borrowId, amount, time);
			return addFundAndAmountDetail(userId,Constant.CUST_FUND_BUSINESS_TYPE_2 ,fundTallyType, amount, note, time, experience);
		} catch (FrameworkException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 从冻结资金中减去金额，不添加流水，不添加资金变化使用明细
	 * @author  Jie.Zou
	 * @date    2015年12月28日
	 * @version v1.0.0
	 * @param userId
	 * @param amount
	 * @param time
	 * @return
	 * @throws FrameworkException 
	 */
	public BizAccountExperience subtractFrozenAmount(String userId,String borrowId,BigDecimal amount,Date time) throws FrameworkException{
		BizAccountExperienceMapper mapper = (BizAccountExperienceMapper)getDao();
		//获取用户体验金资金类别信息
		BizAccountExperience accountExperience = this.getAccountAmountDataIfCreate(userId);
		if(accountExperience.getFrozenAmount().compareTo(amount)>0){
			//扣除用户该体验标投资的体验金
			BigDecimal frozenAmount = accountExperience.getFrozenAmount().subtract(amount);
			//批量删除用户冻结明细
			bizFrozenWaterMapper.updateByDelete(userId, borrowId, TradeTypeConstant.INVEST_TYPE_302);
			//设置更新后的冻结体验金金额
			accountExperience.setFrozenAmount(MiscUtil.getBigDecimalMoney(frozenAmount));
			mapper.updateByPrimaryKeySelective(accountExperience);
			accountExperience = mapper.selectByPrimaryKey(accountExperience.getPid());
		}else{
			throw new FrameworkException("冻结金额不足，无法完成扣除冻结金额操作");
		}
		
		return accountExperience;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 通过客户ID得到客户的体验金资金类别记录信息（没有则新建一条记录）
	 * @author  Jie.Zou
	 * @date    2015年12月28日
	 * @version v1.0.0
	 * @param userId
	 * @return
	 * @throws FrameworkException
	 */
	public BizAccountExperience  getAccountAmountDataIfCreate(String userId) throws FrameworkException{
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("customerId", userId);
		BizAccountExperienceMapper mapper =  (BizAccountExperienceMapper)getDao();
		BizAccountExperience accountExperience = (BizAccountExperience) mapper.selectAccountExperienceByCustomerId(userId);
		if(accountExperience == null){
			accountExperience = new BizAccountExperience();
			accountExperience.setPid(accountExperience.getPK());
			accountExperience.setCustomerId(userId);
			accountExperience.setAvailableAmount(BigDecimal.ZERO);
			accountExperience.setFrozenAmount(BigDecimal.ZERO);
			accountExperience.setCreateTime(DateUtil.getSystemDate());
			accountExperience.setCreateUser(userId);
			accountExperience.setStatus(Constant.PUBLIC_STATUS);
			accountExperience.setVersion(Constant.PUBLIC_VERSION);
			mapper.insert(accountExperience);
		}
		return accountExperience;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 添加资金流水和体验金资金类别变化明细
	 * @author  Jie.Zou
	 * @date    2015年12月28日
	 * @version v1.0.0
	 * @param userId
	 * @param fundWateType
	 * @param amount
	 * @param note
	 * @param time
	 * @param accountExperience
	 * @return
	 * @throws FrameworkException
	 */
	private CustFundWater addFundAndAmountDetail(String userId,String fundWateType,String fundTallyType,BigDecimal amount,String note, Date time,BizAccountExperience accountExperience) throws FrameworkException{
		
		//添加流水
		BigDecimal custav = accountService.getUserBalanceAmountExculdExperience(userId);//客户账户总额（可用余额+冻结金额）不包含体验金
		CustFundWater fundWater = custFundWaterService.addFundWater(userId,fundWateType,fundTallyType, amount, custav, note, time,accountExperience.getPid());
		
		//添加资金类别的变化明细。此处的余额非账户总余额，而是当前资金类别的余额
		BigDecimal balance = this.getCustBalance(userId);
		this.addAccountAmountDetail(accountExperience, amount, fundWater, balance,Constant.ACCOUNTAMOUNT_TYPE_EXPERIENCE, time);
		return fundWater;
	}
	
	@Override
	public BigDecimal getCustBalance(String userId) throws FrameworkException {
		BizAccountExperienceMapper mapper =  (BizAccountExperienceMapper)getDao();
		BizAccountExperience accountExperience = (BizAccountExperience) mapper.selectAccountExperienceByCustomerId(userId);
		if(accountExperience!=null){
			if(accountExperience.getAvailableAmount()==null){
				accountExperience.setAvailableAmount(BigDecimal.ZERO);
			}
			if(accountExperience.getFrozenAmount()==null){
				accountExperience.setFrozenAmount(BigDecimal.ZERO);
			}
			return accountExperience.getAvailableAmount().add(accountExperience.getFrozenAmount());
		}
		return BigDecimal.ZERO;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 添加体验资金的变化明细
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
	private void addAccountAmountDetail(BizAccountExperience accountExperience,
			BigDecimal amount, CustFundWater fundWater, BigDecimal balance,
			String fundType, Date time) {
		BizAccountAmountDetail detail = new BizAccountAmountDetail();
		detail.setPid(detail.getPK());
		detail.setCustomerId(accountExperience.getCustomerId());
		detail.setFundType(fundType);
		detail.setPayConfigId(fundWater.getPid());
		detail.setFundtallyType(fundWater.getBusinessType());
		detail.setHappenValue(amount);
		detail.setBalance(balance);
		detail.setCreateTime(DateUtil.format(DateUtil.getSystemDate()));
		detail.setCreateUser(accountExperience.getCustomerId());
		detail.setVersion(Constant.PUBLIC_VERSION);
		amountDetailMapper.insert(detail);
	}

	@Override
	public BizAccountExperience selectAccountExperience(String customerId) {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("customerId", customerId);
		BizAccountExperienceMapper mapper =  (BizAccountExperienceMapper)getDao();
		BizAccountExperience accountExperience = (BizAccountExperience) mapper.selectAccountExperienceByCustomerId(customerId);
		return accountExperience;
	}

	@Override
	public List<BizAccountExperience> selectExperienceByPids(String[] pids) {
		BizAccountExperienceMapper mapper =  (BizAccountExperienceMapper)getDao();
		return mapper.selectExperienceByPids(pids);
	}

	@Override
	public int updateAmountByCustIds(List<String> userIds, BigDecimal amount,
			String fundWaterType, String tradeType, String note, String fk) {
		BizAccountExperienceMapper mapper =  (BizAccountExperienceMapper)getDao();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userIds", userIds);
		map.put("amount", amount);
		//批量更新客户体验金的可用余额
		int result = mapper.updateAmountByCustIds(map);
		if(result<1){
			throw new UpdateChangeVersionException("批量更新客户的普通资金可用余额失败");
		}
		//批量更新客户账户的体验金可用余额
		result = accountService.updateCustExperienceAmountByUsersId(userIds, amount);
		if(result<1){
			throw new UpdateChangeVersionException("批量更新客户的账户可用余额失败");
		}
		//批量增加资金流水
		List<CustFundWater> custFWs = custFundWaterService.addFundWaters(userIds, fundWaterType, tradeType, amount, note, DateUtil.getSystemDate(), fk);
		//批量增加资金类别变化明细
		bizAccountAmountDetailService.addAccountAmountDetails(custFWs, Constant.ACCOUNTAMOUNT_TYPE_EXPERIENCE);
		return result;
	}

}


