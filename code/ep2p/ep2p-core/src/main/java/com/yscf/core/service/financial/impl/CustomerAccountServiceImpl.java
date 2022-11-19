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
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.apache.commons.lang.NullArgumentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.achievo.framework.util.DateUtil;
import com.google.common.collect.Lists;
import com.sun.star.uno.RuntimeException;
import com.yscf.common.Constant.Constant;
import com.yscf.common.Constant.TradeTypeConstant;
import com.yscf.core.dao.activity.ActExpActDetailMapper;
import com.yscf.core.dao.financial.BizAccountAmountDetailMapper;
import com.yscf.core.dao.financial.BizAccountCommonMapper;
import com.yscf.core.dao.financial.BizAccountExperienceMapper;
import com.yscf.core.dao.financial.BizAccountRechargeMapper;
import com.yscf.core.dao.financial.BizFrozenWaterMapper;
import com.yscf.core.dao.financial.CustomerAccountMapper;
import com.yscf.core.dao.system.SysParamsMapper;
import com.yscf.core.exception.UpdateChangeVersionException;
import com.yscf.core.model.activity.ActExpActDetail;
import com.yscf.core.model.comm.ExportObjectModel;
import com.yscf.core.model.financial.BizAccountAmountDetail;
import com.yscf.core.model.financial.BizAccountCommon;
import com.yscf.core.model.financial.BizAccountExperience;
import com.yscf.core.model.financial.BizAccountRecharge;
import com.yscf.core.model.financial.BizFrozenWater;
import com.yscf.core.model.financial.CustFundWater;
import com.yscf.core.model.financial.CustomerAccount;
import com.yscf.core.model.ptp.financial.CustomerAccountModel;
import com.yscf.core.model.system.SysParams;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.service.financial.IBizAccountCommonService;
import com.yscf.core.service.financial.IBizAccountExperienceService;
import com.yscf.core.service.financial.IBizAccountRechargeService;
import com.yscf.core.service.financial.IBizEnsureMoneyService;
import com.yscf.core.service.financial.ICustFundWaterService;
import com.yscf.core.service.financial.ICustomerAccountService;
import com.yscf.core.service.system.ISysAccountService;

/**
 * Description：<br>
 * 客户账户服务实现
 * 
 * @author Allen
 * @date 2015年9月6日
 * @version v1.0.0
 */
@Service("customerAccountService")
public class CustomerAccountServiceImpl extends BaseService<BaseEntity, String>
		implements ICustomerAccountService {

	@Autowired
	public CustomerAccountServiceImpl(CustomerAccountMapper dao) {
		super(dao);
	}

	@Resource
	private BizAccountCommonMapper bizAccountCommonMapper;

	@Resource
	private BizAccountRechargeMapper bizAccountRechargeMapper;
	
	@Resource
	private BizAccountExperienceMapper bizAccountExperienceMapper;

	@Resource
	private BizFrozenWaterMapper bizFrozenWaterMapper;
	
	@Resource
	private ISysAccountService sysAccountService;
	
	@Resource
	private SysParamsMapper sysParamsMapper;
	
	@Resource
	private IBizAccountRechargeService bizAccountRechargeService;
	
	@Resource
	private BizAccountAmountDetailMapper amountDetailMapper;
	
	@Resource
	private ICustFundWaterService custFundWaterService;
	
	@Resource
	private IBizEnsureMoneyService bizEnsureMoneyService;
	
	@Resource
	private IBizAccountCommonService bizAccountCommonService;
	
	@Resource
	private IBizAccountExperienceService bizAccountExperienceService;
	
	@Resource
	private ActExpActDetailMapper actExpActDetailMapper; 
	
	@Override
	public int insert(CustomerAccount entity) throws FrameworkException {
		return super.insert(entity);
	}

	@Override
	public List<CustomerAccount> selectAll(CustomerAccount customerAccount,
			PageInfo pageInfo) {
		CustomerAccountMapper mapper = (CustomerAccountMapper) getDao();
		return mapper.selectAll(customerAccount);
	}

	@Override
	public PageList<CustomerAccount> selectAllPage(
			HashMap<String, Object> parasMap, PageInfo info) {
		CustomerAccountMapper mapper = (CustomerAccountMapper) getDao();
		PageList<CustomerAccount> bizWithdraws = mapper.selectAllPage(parasMap,
				info);
		// 创建 线下充值类 对象
		CustomerAccount ca = new CustomerAccount();
		// 创建 客户对象
		CusTomer customer = new CusTomer();
		customer.setCustomerName("合计");
		ca.setCustomer(customer);
		// 根据查询条件查询 统计（提现金额、提现手续费） 总金额 并且赋值
		Map<String, BigDecimal> map = mapper.selectSumAmountSelective(parasMap);
		ca.setRechargeAmount(map.get("rechargeAmount"));
		ca.setWithdrawAmount(map.get("withdrawAmount"));
		ca.setDueAmount(map.get("dueAmount"));
		ca.setAvailableAmount(map.get("availableAmount"));
		ca.setFreezeAmount(map.get("freezeAmount"));
		ca.setTenderAmount(map.get("tenderAmount"));
		ca.setExperienceAmount(map.get("experienceAmount"));
		bizWithdraws.add(ca);
		return bizWithdraws;
	}

	@Override
	public int deleteByPrimaryKey(String pids) {
		CustomerAccountMapper mapper = (CustomerAccountMapper) getDao();
		String[] arr = pids.split(",");
		List<String> list = Lists.newArrayList();
		for (int i = 0; i < arr.length; i++) {
			list.add(arr[i]);
		}
		return mapper.deleteByPrimaryKey(pids);

	}

	@Override
	public int deleteBtach(String pids) {
		CustomerAccountMapper mapper = (CustomerAccountMapper) getDao();
		String[] arr = pids.split(",");
		List<String> list = Lists.newArrayList();
		for (int i = 0; i < arr.length; i++) {
			list.add(arr[i]);
		}
		return mapper.deleteBtach(list);
	}

	public PageList<CustomerAccount> selectAllPage(
			CustomerAccountModel customerAccountVO, PageInfo info) {
		CustomerAccountMapper mapper = (CustomerAccountMapper) getDao();
		PageList<CustomerAccount> bizWithdraws = mapper.selectAllPage(
				customerAccountVO, info);
		// 创建 线下充值类 对象
		CustomerAccount ca = new CustomerAccount();
		// 创建 客户对象
		CusTomer customer = new CusTomer();
		customer.setCustomerName(Constant.AGGREGATE_STRING);
		ca.setCustomer(customer);
		// 根据查询条件查询 统计（提现金额、提现手续费） 总金额 并且赋值
		Map<String, BigDecimal> map = mapper
				.selectSumAmountSelectiveByVo(customerAccountVO);
		ca.setRechargeAmount(map.get("rechargeAmount"));
		ca.setWithdrawAmount(map.get("withdrawAmount"));
		ca.setDueAmount(map.get("dueAmount"));
		ca.setAvailableAmount(map.get("availableAmount"));
		ca.setFreezeAmount(map.get("freezeAmount"));
		ca.setTenderAmount(map.get("tenderAmount"));
		ca.setExperienceAmount(map.get("experienceAmount"));
		bizWithdraws.add(ca);
		return bizWithdraws;
	}

	@Override
	public List<CustomerAccountModel> selectCustomerAccountVOEom(
			CustomerAccountModel customerAccountVO, ExportObjectModel eom) {
		CustomerAccountMapper mapper = (CustomerAccountMapper) getDao();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("parasMap", customerAccountVO);
		map.put("expm", eom);
		List<CustomerAccount> elist = mapper.selectCustomerAccountEom(map);
		List<CustomerAccountModel> elistVo = new ArrayList<CustomerAccountModel>();

		BigDecimal available = new BigDecimal(0);
		BigDecimal due = new BigDecimal(0);
		BigDecimal experienceGold = new BigDecimal(0);
		BigDecimal freeze = new BigDecimal(0);
		BigDecimal recharge = new BigDecimal(0);
		BigDecimal tender = new BigDecimal(0);
		BigDecimal withdraw = new BigDecimal(0);

		if (null != elist) {
			for (CustomerAccount ca : elist) {
				CustomerAccountModel vo = new CustomerAccountModel();
				CusTomer cs = ca.getCustomer();
				if (cs == null) {

				} else {
					vo.setCustomerName(cs.getCustomerName());
					vo.setPhoneNo(cs.getPhoneNo());
					vo.setSname(cs.getSname());
				}
				vo.setAvailable(ca.getAvailableAmount());
				vo.setDue(ca.getDueAmount());
				vo.setExperienceGold(ca.getExperienceAmount());
				vo.setFreeze(ca.getFreezeAmount());
				vo.setRecharge(ca.getRechargeAmount());
				vo.setRegisterTime(ca.getCreateTime());
				vo.setTender(ca.getTenderAmount());
				vo.setWithdraw(ca.getWithdrawAmount());
				elistVo.add(vo);

				// 统计值
				available = available
						.add(ca.getAvailableAmount() == null ? new BigDecimal(0)
								: ca.getAvailableAmount());
				due = due.add(ca.getDueAmount() == null ? new BigDecimal(0)
						: ca.getDueAmount());
				experienceGold = experienceGold
						.add(ca.getExperienceAmount() == null ? new BigDecimal(
								0) : ca.getExperienceAmount());
				freeze = freeze
						.add(ca.getFreezeAmount() == null ? new BigDecimal(0)
								: ca.getFreezeAmount());
				recharge = recharge
						.add(ca.getRechargeAmount() == null ? new BigDecimal(0)
								: ca.getRechargeAmount());
				tender = tender
						.add(ca.getTenderAmount() == null ? new BigDecimal(0)
								: ca.getTenderAmount());
				withdraw = withdraw
						.add(ca.getWithdrawAmount() == null ? new BigDecimal(0)
								: ca.getWithdrawAmount());
			}

			CustomerAccountModel vo = new CustomerAccountModel();
			vo.setCustomerName(Constant.AGGREGATE_STRING);
			vo.setAvailable(available);
			vo.setDue(due);
			vo.setExperienceGold(experienceGold);
			vo.setFreeze(freeze);
			vo.setRecharge(recharge);
			vo.setTender(tender);
			vo.setWithdraw(withdraw);

			elistVo.add(vo);

		}
		return elistVo;
	}

	@Override
	public CustomerAccount getByCusterId(String userId) {
		CustomerAccountMapper mapper = (CustomerAccountMapper) getDao();

		return mapper.getByCusterId(userId);
	}

	@Override
	public int updateByPrimaryKey(CustomerAccount customerAccount) {
		CustomerAccountMapper mapper = (CustomerAccountMapper) getDao();
		return mapper.updateByPrimaryKey(customerAccount);
	}

	@Override
	public void updateAvailableAmountToFreezeAmount(BigDecimal amount,
			String userId, String borrowId) throws FrameworkException {
		if(amount == null || amount.compareTo(BigDecimal.ZERO)<=0 || !StringUtils.hasLength(userId)){
			throw new NullArgumentException("amount or userId is null or amount less 0 ");
		}
		// 投资，可用余额转成冻结金额
		// 获取普通资金对象
		BizAccountCommon bac = bizAccountCommonMapper
				.selectAccountCommonByCustomerId(userId);
		// 获取充值资金对象
		BizAccountRecharge bar = bizAccountRechargeMapper
				.selectAccountRechargeByCustomerId(userId);
		// 1.先判断充值资金表的金额是否满足
		BigDecimal rAmount = bar == null ? BigDecimal.ZERO : bar
				.getAvailableAmount();
		BigDecimal cAmount = bac == null ? BigDecimal.ZERO : bac
				.getAvailableAmount();

		// 充值表冻结金额
		BigDecimal rMoney = BigDecimal.ZERO;
		// 普通表冻结金额
		BigDecimal cMoney = BigDecimal.ZERO;

		if (rAmount.compareTo(amount) >= 0) {
			// 充值金额满足
			rMoney = amount;
		} else {
			// 2.不满足，求剩余的金额，由普通金额进行冻结
			rMoney = rAmount;
			cMoney = amount.subtract(rAmount);
		}
		// 3.都不满足，抛出余额不足
		if (cMoney.compareTo(BigDecimal.ZERO) < 0) {
			throw new FrameworkException("余额不足");
		}
		

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		// 6.更新充值资金表
		if(rMoney.compareTo(BigDecimal.ZERO) > 0){
			BigDecimal bfMoney = bar.getFrozenAmount() == null ? BigDecimal.ZERO
					: bar.getFrozenAmount();
	
			bar.setAvailableAmount(rAmount.subtract(rMoney));
			bar.setFrozenAmount(bfMoney.add(rMoney));
			bar.setLastUpdateTime(sdf.format(new Date()));
			bar.setLastUpdateUser(userId);
	
			int a = bizAccountRechargeMapper.updateByPrimaryKey(bar);
			if (a < 1) {
				throw new UpdateChangeVersionException("投资可用余额转成冻结金额时,更新充值资金表失败");
			}
		}

		// 5.更新普通资金表
		if(cMoney.compareTo(BigDecimal.ZERO) > 0){
			BigDecimal baMoney = bac.getFrozenAmount() == null ? BigDecimal.ZERO
					: bac.getFrozenAmount();
	
			bac.setAvailableAmount(cAmount.subtract(cMoney));
			bac.setFrozenAmount(baMoney.add(cMoney));
			bac.setLastUpdateTime(new Date());
			bac.setLastUpdateUser(userId);
	
			int b = bizAccountCommonMapper.updateByPrimaryKey(bac);
			if (b < 1) {
				throw new UpdateChangeVersionException("投资可用余额转成冻结金额时,更新普通资金表失败");
			}
		}

		// 7.更新账户总表金额
		CustomerAccountMapper mp = (CustomerAccountMapper) getDao();
		CustomerAccount ca = mp.getByCusterId(userId);
		//
		BigDecimal cabAmount = ca.getAvailableAmount() == null ? BigDecimal.ZERO
				: ca.getAvailableAmount();
		BigDecimal cfaAmount = ca.getFreezeAmount() == null ? BigDecimal.ZERO
				: ca.getFreezeAmount();
		ca.setAvailableAmount(cabAmount.subtract(amount));
		ca.setFreezeAmount(cfaAmount.add(amount));
		ca.setLastUpdateTime(sdf.format(new Date()));
		ca.setLastUpdateUser(userId);

		int c = mp.updateByPrimaryKey(ca);
		if (c < 1) {
			throw new UpdateChangeVersionException("投资可用余额转成冻结金额时,更新账户总表失败");
		}
		
		// 4.满足后，产生冻结明细表
		BizFrozenWater bfw = new BizFrozenWater();
		bfw.setFkey(borrowId);
		bfw.setFrozenType(TradeTypeConstant.INVEST_TYPE_302);
		bfw.setPid(bfw.getPK());
		bfw.setCustomerId(userId);
		bfw.setCreateTime(DateUtil.format(new Date()));
		bfw.setCreateUser(userId);
		bfw.setVersion(Constant.ACTIVATE);
		bfw.setStatus(Constant.ACTIVATE);
		bfw.setCommonAmount(cMoney);
		bfw.setRechargeAmount(rMoney);

		bizFrozenWaterMapper.insert(bfw);
		
	}

	@Override
	public void updateFreezeAmountToAvailableAmount(BigDecimal amount,
			String userId, String borrowId) throws FrameworkException {
		
		if(amount == null || amount.compareTo(BigDecimal.ZERO)<=0 || !StringUtils.hasLength(userId)){
			throw new NullArgumentException("amount or userId is null or amount less 0 ");
		}
		// 流标，冻结金额转成可用余额
		// 获取充值资金对象
		BizAccountCommon bac = bizAccountCommonMapper
				.selectAccountCommonByCustomerId(userId);
		// 获取普通资金对象
		BizAccountRecharge bar = bizAccountRechargeMapper
				.selectAccountRechargeByCustomerId(userId);

		CustomerAccountMapper mp = (CustomerAccountMapper) getDao();
		CustomerAccount ca = mp.getByCusterId(userId);
		
		BigDecimal cabAmount = ca.getAvailableAmount() == null ? BigDecimal.ZERO
				: ca.getAvailableAmount();
		BigDecimal ctaAmount = ca.getTenderAmount() == null ? BigDecimal.ZERO
				: ca.getTenderAmount();
		BigDecimal cfaAmount = ca.getFreezeAmount() == null ? BigDecimal.ZERO
				: ca.getFreezeAmount();
		
		// 1.获取用户冻结金额
		BigDecimal cAmount = bac == null ? BigDecimal.ZERO : bac
				.getFrozenAmount();
		BigDecimal rAmount = bar == null ? BigDecimal.ZERO : bar
				.getFrozenAmount();

		// 2.获取用户冻结明细
		List<BizFrozenWater> bizFrozenWaterList = bizFrozenWaterMapper
				.findList(userId, borrowId,TradeTypeConstant.INVEST_TYPE_302);

		BigDecimal rmoney = BigDecimal.ZERO;
		BigDecimal cmoney = BigDecimal.ZERO;
		
		for (BizFrozenWater bfw : bizFrozenWaterList) {
			
			rmoney = rmoney.add(bfw.getRechargeAmount() == null ? BigDecimal.ZERO
							: bfw.getRechargeAmount());
			cmoney = cmoney.add(bfw.getCommonAmount() == null ? BigDecimal.ZERO
							: bfw.getCommonAmount());
			
		}
		
		if(rmoney.add(cmoney).compareTo(amount)!=0){
			// 用户金额不匹配
			throw new RuntimeException("用户冻结金额不匹配，用户id为："+userId+",标的id为："+borrowId);
		}

		if (cfaAmount.compareTo(amount) < 0) {
			throw new FrameworkException("冻结金额不足，无法完成冻结金额转成可用金额");
		}
		
		//3.将用户冻结的金额分别转到相应的类别账户中

		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		// 充值金额账户
		if(rmoney.compareTo(BigDecimal.ZERO) > 0){
			BigDecimal arAmount = bar.getAvailableAmount() == null ? BigDecimal.ZERO : bar.getAvailableAmount();
			bar.setAvailableAmount(arAmount.add(rmoney));
			bar.setFrozenAmount(rAmount.subtract(rmoney));
			bar.setLastUpdateTime(sdf.format(new Date()));
			bar.setLastUpdateUser(userId);
			
			int a = bizAccountRechargeMapper.updateByPrimaryKey(bar);
			if (a < 1) {
				throw new UpdateChangeVersionException("流标，冻结金额转成可用余额时,更新充值资金表失败");
			}
		}
		// 普通资金账户
		
		if(cmoney.compareTo(BigDecimal.ZERO) > 0){
			BigDecimal acAmount = bac.getAvailableAmount() == null ? BigDecimal.ZERO : bac.getAvailableAmount();
	
			bac.setAvailableAmount(acAmount.add(cmoney));
			bac.setFrozenAmount(cAmount.subtract(cmoney));
			bac.setLastUpdateTime(new Date());
			bac.setLastUpdateUser(userId);
			
			int b = bizAccountCommonMapper.updateByPrimaryKey(bac);
			if (b < 1) {
				throw new UpdateChangeVersionException("流标，冻结金额转成可用余额时,更新普通资金表失败");
			}
		}
		// 客户账户表
	
		ca.setAvailableAmount(cabAmount.add(amount));
		//ca.setTenderAmount(ctaAmount.subtract(amount));投标总额
		ca.setFreezeAmount(cfaAmount.subtract(amount));
		ca.setLastUpdateTime(sdf.format(new Date()));
		ca.setLastUpdateUser(userId);

		int c = mp.updateByPrimaryKey(ca);
		if (c < 1) {
			throw new UpdateChangeVersionException("投资可用余额转成冻结金额时,更新账户总表失败");
		}
		

		bizFrozenWaterMapper.updateByDelete(userId, borrowId,TradeTypeConstant.INVEST_TYPE_302);
		
		// 普通资金流水
//		this.addFundAndAmountDetail( userId, Constant.ACCOUNTAMOUNT_TYPE_UNDEFINED, tradeType, cmoney,bac.getAvailableAmount(), note, new Date(),borrowId);
		// 充值资金流水
//		this.addFundAndAmountDetail( userId, Constant.ACCOUNTAMOUNT_TYPE_RECHARGE, tradeType, rmoney,bar.getAvailableAmount(), note, new Date(),borrowId);

		

	}

	@Override
	public void updateAddAvailableAmount(BigDecimal amount, String userId,String fk,String tradeType,String note) throws FrameworkException{
			if(amount == null || amount.compareTo(BigDecimal.ZERO)<=0 || !StringUtils.hasLength(userId)){
				throw new NullArgumentException("amount or userId is null or amount less 0 ");
			}
			// 回款，获得借款，利息等等，往普通资金中增加可用余额
			// 获取普通资金对象
			BizAccountCommon bac = bizAccountCommonMapper
					.selectAccountCommonByCustomerId(userId);

			CustomerAccountMapper mp = (CustomerAccountMapper) getDao();
			CustomerAccount ca = mp.getByCusterId(userId);
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
			
			BigDecimal acAmount = bac.getAvailableAmount() == null ? BigDecimal.ZERO : bac.getAvailableAmount();
			BigDecimal caAmount = ca.getAvailableAmount() == null ? BigDecimal.ZERO : ca.getAvailableAmount();
			
			bac.setAvailableAmount(acAmount.add(amount));
			bac.setLastUpdateTime(new Date());
			bac.setLastUpdateUser(userId);
			
			ca.setAvailableAmount(caAmount.add(amount));
			ca.setLastUpdateTime(sdf.format(new Date()));
			ca.setLastUpdateUser(userId);
			
			int b = bizAccountCommonMapper.updateByPrimaryKey(bac);
			if (b < 1) {
				throw new UpdateChangeVersionException("增加可用金额时,更新普通资金表失败");
			}
			
			int c = mp.updateByPrimaryKey(ca);
			if (c < 1) {
				throw new UpdateChangeVersionException("增加可用金额时,更新账户总表失败");
			}
			// 普通资金流水
			if(amount.compareTo(BigDecimal.ZERO) > 0){
				// 普通资金流水
				Map<String, BigDecimal> map = new HashMap<String, BigDecimal>();
				map.put(Constant.ACCOUNTAMOUNT_TYPE_COMMON, amount);
				this.addFundAndAmountDetail( userId, Constant.CUST_FUND_BUSINESS_TYPE_1, tradeType, amount,ca.getAvailableAmount(), note, new Date(),fk,map);
			}
		
	}

	@Override
	public void updateDeductedAvailableAmount(BigDecimal amount, String userId,String fk,String tradeType,String note) throws FrameworkException {
		if(amount == null || amount.compareTo(BigDecimal.ZERO)<=0 || !StringUtils.hasLength(userId)){
			throw new NullArgumentException("amount or userId is null or amount less 0 ");
		}
		//　直接减少用户可用金额时，先减少用户充值表金额，不够部分再由普通资金补上，都不够时，直接抛出余额不足异常
		// 流标，冻结金额转成可用余额
		// 获取充值资金对象
		BizAccountCommon bac = bizAccountCommonMapper
				.selectAccountCommonByCustomerId(userId);
		// 获取普通资金对象
		BizAccountRecharge bar = bizAccountRechargeMapper
				.selectAccountRechargeByCustomerId(userId);

		CustomerAccountMapper mp = (CustomerAccountMapper) getDao();
		CustomerAccount ca = mp.getByCusterId(userId);
		
		
		BigDecimal arAmount = bar.getAvailableAmount() ==  null ? BigDecimal.ZERO : bar.getAvailableAmount();
		BigDecimal acAmount = bac.getAvailableAmount() ==  null ? BigDecimal.ZERO : bac.getAvailableAmount();
		BigDecimal caAmount = ca.getAvailableAmount() ==  null ? BigDecimal.ZERO : ca.getAvailableAmount();
		
		if (caAmount.compareTo(amount) < 0) {
			throw new FrameworkException("可用金额不足，无法完成操作");
		}
		BigDecimal rMoney = BigDecimal.ZERO;
		BigDecimal cMoney = BigDecimal.ZERO;
		
		if(arAmount.compareTo(amount) < 0){
			// 不足
			rMoney = arAmount;
			cMoney = amount.subtract(rMoney);
		}else{
			// 足够
			rMoney = amount;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		
		if(rMoney.compareTo(BigDecimal.ZERO)>0){
			bar.setAvailableAmount(arAmount.subtract(rMoney));
			bar.setLastUpdateTime(sdf.format(new Date()));
			bar.setLastUpdateUser(userId);
			
			int a = bizAccountRechargeMapper.updateByPrimaryKey(bar);
			if (a < 1) {
				throw new UpdateChangeVersionException("扣除可用余额时,更新充值资金表失败");
			}
		}
		if(cMoney.compareTo(BigDecimal.ZERO)>0){
			bac.setAvailableAmount(acAmount.subtract(cMoney));
			bac.setLastUpdateTime(new Date());
			bac.setLastUpdateUser(userId);
			
			int b = bizAccountCommonMapper.updateByPrimaryKey(bac);
			if (b < 1) {
				throw new UpdateChangeVersionException("扣除可用金额时,更新普通资金表失败");
			}
		}
		ca.setAvailableAmount(caAmount.subtract(amount));
		ca.setLastUpdateTime(sdf.format(new Date()));
		ca.setLastUpdateUser(userId);
		
		int c = mp.updateByPrimaryKey(ca);
		
		if (c < 1) {
			throw new UpdateChangeVersionException("扣除可用金额时,更新账户总表失败");
		}
		
		// 普通资金流水
		boolean flag = false;
		Map<String,BigDecimal> map = new HashMap<String, BigDecimal>();
		if(cMoney.compareTo(BigDecimal.ZERO) > 0){
			flag = true;
			map.put(Constant.ACCOUNTAMOUNT_TYPE_COMMON, cMoney);
		}
		// 充值资金流水
		if(rMoney.compareTo(BigDecimal.ZERO) > 0){
			flag = true;
			map.put(Constant.ACCOUNTAMOUNT_TYPE_RECHARGE, rMoney);
		}
		if(flag){
			this.addFundAndAmountDetail( userId, Constant.CUST_FUND_BUSINESS_TYPE_2, tradeType, cMoney.add(rMoney),ca.getAvailableAmount(), note, new Date(),fk,map);
		}
	}

	@Override
	public void updateDeductedFreezeAmount(BigDecimal amount, String userId,String borrowId,String tradeType,String note) throws FrameworkException{
		if(amount == null || amount.compareTo(BigDecimal.ZERO)<=0 || !StringUtils.hasLength(userId)){
			throw new NullArgumentException("amount or userId is null or amount less 0 ");
		}
		//　直接减少用户可用金额时，先减少用户充值表金额，不够部分再由普通资金补上，都不够时，直接抛出余额不足异常
		// 流标，冻结金额转成可用余额
		// 获取充值资金对象
		BizAccountCommon bac = bizAccountCommonMapper
				.selectAccountCommonByCustomerId(userId);
		// 获取普通资金对象
		BizAccountRecharge bar = bizAccountRechargeMapper
				.selectAccountRechargeByCustomerId(userId);

		CustomerAccountMapper mp = (CustomerAccountMapper) getDao();
		CustomerAccount ca = mp.getByCusterId(userId);
		
		
		BigDecimal arAmount = bar.getFrozenAmount() ==  null ? BigDecimal.ZERO : bar.getFrozenAmount();
		BigDecimal acAmount = bac.getFrozenAmount() ==  null ? BigDecimal.ZERO : bac.getFrozenAmount();
		BigDecimal caAmount = ca.getFreezeAmount() ==  null ? BigDecimal.ZERO : ca.getFreezeAmount();
		
		if (caAmount.compareTo(amount) < 0) {
			throw new FrameworkException("冻结金额不足，无法完成扣除冻结金额操作");
		}
		
		BigDecimal rMoney = BigDecimal.ZERO;
		BigDecimal cMoney = BigDecimal.ZERO;
		
		// 2.获取用户冻结明细
		List<BizFrozenWater> bizFrozenWaterList = bizFrozenWaterMapper
				.findList(userId, borrowId,TradeTypeConstant.INVEST_TYPE_302);
		for (BizFrozenWater bfw : bizFrozenWaterList) {
			
			rMoney = rMoney.add(bfw.getRechargeAmount() == null ? BigDecimal.ZERO
							: bfw.getRechargeAmount());
			cMoney = cMoney.add(bfw.getCommonAmount() == null ? BigDecimal.ZERO
							: bfw.getCommonAmount());
			
		}
		
		if(rMoney.add(cMoney).compareTo(amount)!=0){
			// 用户金额不匹配
			throw new RuntimeException("用户冻结金额不匹配，用户id为："+userId+",标的id为："+borrowId);
		}
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		if(rMoney.compareTo(BigDecimal.ZERO) > 0){
			bar.setFrozenAmount(arAmount.subtract(rMoney));
			bar.setLastUpdateTime(sdf.format(new Date()));
			bar.setLastUpdateUser(userId);
			
			int a = bizAccountRechargeMapper.updateByPrimaryKey(bar);
			if (a < 1) {
				throw new UpdateChangeVersionException("满标,扣除冻结金额时,更新充值资金表失败");
			}
		}
		if(cMoney.compareTo(BigDecimal.ZERO) > 0){
			bac.setFrozenAmount(acAmount.subtract(cMoney));
			bac.setLastUpdateTime(new Date());
			bac.setLastUpdateUser(userId);
			
			int b = bizAccountCommonMapper.updateByPrimaryKey(bac);
			if (b < 1) {
				throw new UpdateChangeVersionException("满标,扣除冻结金额时,更新普通资金表失败");
			}
		}
		
		ca.setFreezeAmount(caAmount.subtract(amount));//减去冻结金额
		ca.setTenderAmount((ca.getTenderAmount() ==  null ? BigDecimal.ZERO : ca.getTenderAmount()).add(amount));//满标投资总额加上投标金额
		ca.setLastUpdateTime(sdf.format(new Date()));//加入最后修改时间
		ca.setLastUpdateUser(userId);
		
		int c = mp.updateByPrimaryKey(ca);
		
		if (c < 1) {
			throw new UpdateChangeVersionException("满标,扣除冻结金额时,更新账户总表失败");
		}
		//保存不同的资金类别，然后逐一扣除
		Map<String, BigDecimal> amountData = new HashMap<String, BigDecimal>();
		if(rMoney.compareTo(BigDecimal.ZERO)>0){
			amountData.put(Constant.ACCOUNTAMOUNT_TYPE_RECHARGE, rMoney);
		}
		if(cMoney.compareTo(BigDecimal.ZERO)>0){
			amountData.put(Constant.ACCOUNTAMOUNT_TYPE_COMMON, cMoney);
		}
		this.addFundAndAmountDetail(userId, Constant.CUST_FUND_BUSINESS_TYPE_2, tradeType, cMoney.add(rMoney), this.getUserBalanceAmountExculdExperience(userId), note, DateUtil.getSystemDate(), borrowId,amountData);
		bizFrozenWaterMapper.updateByDelete(userId, borrowId,TradeTypeConstant.INVEST_TYPE_302);

	}

	@Override
	public void updateDueAmount(BigDecimal dueAmount, String userId) {
		if(dueAmount == null || !StringUtils.hasLength(userId)){
			throw new NullArgumentException("amount or userId is null");
		}
		CustomerAccountMapper mp = (CustomerAccountMapper) getDao();
		CustomerAccount ca = mp.getByCusterId(userId);
		ca.setDueAmount(dueAmount);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		ca.setLastUpdateTime(sdf.format(new Date()));
		ca.setLastUpdateUser(userId);
		
		int c = mp.updateByPrimaryKey(ca);
		
		if (c < 1) {
			throw new UpdateChangeVersionException("更新待收金额时,更新账户总表失败");
		}
		
	}

	@Override
	public void updateDeductedInterestManagementFee(BigDecimal imFee,
			String userId,String pk,String desc) throws FrameworkException {
		
		if(imFee == null || imFee.compareTo(BigDecimal.ZERO)<=0 || !StringUtils.hasLength(userId)){
			throw new NullArgumentException("imFee or userId is null or amount less 0 ");
		}
		if(desc == null ){
			desc = "";
		}
		// 扣除用户利息管理费，利息管理费按比例进入系统资金帐户和备付金帐户
		BigDecimal sysImFee = BigDecimal.ZERO;
		BigDecimal beiImFee = BigDecimal.ZERO;
		
		SysParams sp = sysParamsMapper.getParamsByParamKey(Constant.BIZ_ENSURE_MANAGE_RATE);
		if(sp==null){
			throw new NullArgumentException("Constant.BIZ_ENSURE_MANAGE_RATE getParamsByParamKey(): sysparams is null");
		}
		
		BigDecimal fee = new BigDecimal(sp.getParamValue());
		
		beiImFee = imFee.multiply(fee).divide(new BigDecimal(100.0));
		sysImFee = imFee.subtract(beiImFee);
		
		if(beiImFee.compareTo(BigDecimal.ZERO) > 0){
			String desc1 = desc +  imFee.setScale(2, BigDecimal.ROUND_DOWN) + " * " + fee.setScale(2, BigDecimal.ROUND_DOWN) + "% = " + beiImFee.setScale(2, BigDecimal.ROUND_DOWN);
			bizEnsureMoneyService.updateAddProvisions(beiImFee, userId, TradeTypeConstant.RISK_TRADE_TYPE_2011, pk, desc1);
		}
		if(imFee.compareTo(BigDecimal.ZERO) > 0){
			String desc2 = desc+imFee.setScale(2, BigDecimal.ROUND_DOWN) + " * " + new BigDecimal(100).subtract(fee).setScale(2, BigDecimal.ROUND_DOWN) + "% = " + sysImFee.setScale(2, BigDecimal.ROUND_DOWN);
			sysAccountService.updateAddSystemAmount(sysImFee, userId, TradeTypeConstant.SYSTEM_TRADE_TYPE_1003, pk, desc2);
		}
	}

	
	/**
	 * 
	 * Description：<br> 
	 * 添加流水和类别资金变化明细
	 * @author  Jie.Zou
	 * @date    2015年12月14日
	 * @version v1.0.0
	 * @param userId
	 * @param fundWaterType
	 * @param amount
	 * @param note
	 * @param time
	 * @return
	 * @throws FrameworkException
	 */
	private CustFundWater addFundAndAmountDetail(String userId,String fundWateType,String tradeType,BigDecimal amount,BigDecimal balance,String note, Date time,String fkey,Map<String, BigDecimal> amountData) throws FrameworkException{
		//添加资金流水
		CustFundWater fundWater = custFundWaterService.addFundWater(userId, fundWateType,tradeType, amount, balance, note, time,fkey);
		
		//添加资金类别的变化明细。此处的余额非账户总余额，而是当前资金类别的余额
		List<BizAccountAmountDetail> accountAmountDetails = new ArrayList<BizAccountAmountDetail>();
		for(Entry<String, BigDecimal> en : amountData.entrySet()){
			if(en.getKey().equals(Constant.ACCOUNTAMOUNT_TYPE_RECHARGE)){
				//更新可用余额
				accountAmountDetails.add(this.getNewAccountAmountDetail(userId, en.getValue(), bizAccountRechargeService.getCustBalance(userId), Constant.ACCOUNTAMOUNT_TYPE_RECHARGE, tradeType, time));
			}else if(en.getKey().equals(Constant.ACCOUNTAMOUNT_TYPE_COMMON)){
				accountAmountDetails.add(this.getNewAccountAmountDetail(userId, en.getValue(), bizAccountCommonService.getCustBalance(userId), Constant.ACCOUNTAMOUNT_TYPE_COMMON, tradeType, time));
			}
		}
		
		for (BizAccountAmountDetail detail : accountAmountDetails) {
			detail.setPayConfigId(fundWater.getPid());
			amountDetailMapper.insert(detail);
		}
		return fundWater;
	}

	@Override
	public void updateExperienceAmountToFreezeAmount(BigDecimal amount,
			String userId, String borrowId,String[] pids) throws FrameworkException {
		if(amount == null || amount.compareTo(BigDecimal.ZERO)<=0 || !StringUtils.hasText(userId)){
			throw new NullArgumentException("amount or userId is null or amount less 0 ");
		}
		
		//批量修改使用的体验金状态
		actExpActDetailMapper.batchUpdateExpDetailUseStatus(pids);
		
		//投体验标，可用余额转成冻结金额
		//获取体验金资金对象
		BizAccountExperience bae = bizAccountExperienceMapper.selectAccountExperienceByCustomerId(userId);
		
		BigDecimal eAmount = bae==null ? BigDecimal.ZERO : bae.getAvailableAmount();//可用体验金 
		
		//体验金需要冻结金额
		BigDecimal frozenAmount = BigDecimal.ZERO;
		//判断体验金余额是否充足
		if(eAmount.compareTo(amount) >= 0 ){
			//体验金余额充足
			frozenAmount = amount;
		}
		
		BigDecimal bfAmount = bae.getFrozenAmount() == null ? BigDecimal.ZERO : bae.getFrozenAmount();
		bae.setAvailableAmount(eAmount.subtract(frozenAmount));
		bae.setFrozenAmount(bfAmount.add(frozenAmount));
		bae.setLastUpdateTime(DateUtil.getSystemDate());
		bae.setLastUpdateUser(userId);
		int result = bizAccountExperienceMapper.updateByPrimaryKey(bae);
		if(result<1){
			throw new UpdateChangeVersionException("投资体验金可用余额转成冻结金额时,更新体验金资金表失败");
		}
		
		//更新账户表
		CustomerAccountMapper mp = (CustomerAccountMapper)getDao();
		CustomerAccount ca = mp.getByCusterId(userId);
		BigDecimal caEAmount = ca.getExperienceAmount() == null ? BigDecimal.ZERO : ca.getExperienceAmount();//获得账户表的体验金额
		//更新账户表的体验金可用余额
		ca.setExperienceAmount(caEAmount.subtract(amount));
		ca.setLastUpdateTime(DateUtil.format(DateUtil.getSystemDate()));
		int caResult = mp.updateByPrimaryKey(ca);
		if(caResult < 1){
			throw new UpdateChangeVersionException("投资体验金可用余额转成冻结金额时,更新账户总表失败");
		}
		
		// 满足后，产生冻结明细表(因为有可能会选择多张卷,需要有循环记录)
		List<ActExpActDetail> aeads = actExpActDetailMapper.selectExperienceGoldByPidArr(pids);
		for (ActExpActDetail aead : aeads) {
			BizFrozenWater bfw = new BizFrozenWater();
			bfw.setFkey(borrowId);
			bfw.setFrozenType(TradeTypeConstant.INVEST_TYPE_302);
			bfw.setPid(bfw.getPK());
			bfw.setCustomerId(userId);
			bfw.setCreateTime(DateUtil.format(new Date()));
			bfw.setCreateUser(userId);
			bfw.setVersion(Constant.ACTIVATE);
			bfw.setStatus(Constant.ACTIVATE);
			bfw.setExperienceAmount(aead.getExpAmount());
			bizFrozenWaterMapper.insert(bfw);
		}
		
	}

	@Override
	public void updateAddAvailableAmount(BigDecimal amount, String userId,
			String fk) throws FrameworkException {
		CustomerAccountMapper mp = (CustomerAccountMapper) getDao();
		//得到客户账户表对象
		CustomerAccount ca = mp.getByCusterId(userId);
		//得到账户可用余额
		BigDecimal caAmount = ca.getAvailableAmount() == null ? BigDecimal.ZERO : ca.getAvailableAmount();
		//更新客户账户余额，更新最新修改人和修改时间
		ca.setAvailableAmount(caAmount.add(amount));
		ca.setLastUpdateTime(DateUtil.format(DateUtil.getSystemDate()));
		ca.setLastUpdateUser(userId);
		int c = mp.updateByPrimaryKey(ca);
		if (c < 1) {
			throw new UpdateChangeVersionException("增加可用金额时,更新账户总表失败");
		}
	}

	@Override
	public void updateAvailableAmount(String customerId, BigDecimal amount,
			 String tradeType, String fkey,String tallyNote, Date time) throws FrameworkException {
		//得到账户总可用余额
		BigDecimal availableAmount = this.getUserAvailableAmountExculdExperience(customerId);
		//获取资金使用分配明细
		Map<String, BigDecimal> amountData = this.getMixOutputAmountData(customerId, amount, availableAmount, tradeType);
		
		List<BizAccountAmountDetail> accountAmountDetails = new ArrayList<BizAccountAmountDetail>();
		for(Entry<String, BigDecimal> en : amountData.entrySet()){
			if(en.getKey().equals(Constant.ACCOUNTAMOUNT_TYPE_RECHARGE)){
				//更新可用余额
				BizAccountRecharge accountRecharge = bizAccountRechargeService.selectAccountRecharge(customerId);
				bizAccountRechargeService.modifyAvailableAmountNoTran(accountRecharge, en.getValue().negate());//进行资金支出，不保存流水，后面统一保存
				accountAmountDetails.add(this.getNewAccountAmountDetail(customerId, amount, bizAccountRechargeService.getCustBalance(customerId), Constant.ACCOUNTAMOUNT_TYPE_RECHARGE, tradeType, time));
			}else if(en.getKey().equals(Constant.ACCOUNTAMOUNT_TYPE_COMMON)){
				BizAccountCommon accountCommon = bizAccountCommonService.selectAccountCommon(customerId);
				bizAccountCommonService.modifyAvailableAmountNoTran(accountCommon, en.getValue().negate());//进行资金支出，不保存流水，后面统一保存
				accountAmountDetails.add(this.getNewAccountAmountDetail(customerId, amount, bizAccountCommonService.getCustBalance(customerId), Constant.ACCOUNTAMOUNT_TYPE_COMMON, tradeType, time));
			}
		}
		
		//各类资金类别已经分别更新，现在可得到账户总额，即可添加流水
		BigDecimal balance = this.getUserBalanceAmountExculdExperience(customerId);
		CustFundWater custfundWater = custFundWaterService.addFundWater(customerId,Constant.CUST_FUND_BUSINESS_TYPE_2, tradeType, amount, balance, tallyNote, time, fkey);
		
		//更新账户可用余额
		availableAmount = this.getUserAvailableAmountExculdExperience(customerId);
		CustomerAccountMapper mapper = (CustomerAccountMapper) getDao();
		CustomerAccount account =  mapper.getByCusterId(customerId);
		account.setAvailableAmount(availableAmount);
		mapper.updateByPrimaryKey(account);
		
		//保存资金类别变化明细
		for (BizAccountAmountDetail bizAccountAmountDetail : accountAmountDetails) {
			bizAccountAmountDetail.setPayConfigId(custfundWater.getPid());
			amountDetailMapper.insert(bizAccountAmountDetail);
		}
	}
	
	public Map<String, BigDecimal> getMixOutputAmountData(String customerId,BigDecimal amount,BigDecimal availableAmountTotal,String tradeType){
		//如果客户可用余额不足，返回null
		if(amount.compareTo(availableAmountTotal) > 0){
			return null;
		}
		Map<String, BigDecimal> amountData = new HashMap<String, BigDecimal>();
		//资金使用顺序为1.充值资金2.普通资金
		//充值资金
		BizAccountRecharge recharge = bizAccountRechargeService.selectAccountRecharge(customerId);
		BigDecimal amountNextLeft = amount;
		//有可能客户当前生成充值资金对象
		if(null!=recharge){
			BigDecimal availableAmount = recharge.getAvailableAmount();
			//充值资金类别可用资金足够是，全部使用当前类别资金作为目前需要的所有金额
			if(availableAmount.compareTo(amountNextLeft) >=0 ){
				amountData.put(Constant.ACCOUNTAMOUNT_TYPE_RECHARGE, amountNextLeft);
				amountNextLeft = BigDecimal.ZERO;
				return amountData;
			}else if(availableAmount.compareTo(BigDecimal.ZERO) >0){
				amountData.put(Constant.ACCOUNTAMOUNT_TYPE_RECHARGE, amountNextLeft);
				amountNextLeft = amountNextLeft.subtract(availableAmount);
			}
		}
		//普通资金
		BizAccountCommon common = bizAccountCommonService.selectAccountCommon(customerId);
		if(null!=common){
			BigDecimal availableAmount = common.getAvailableAmount();
			//充值资金类别可用资金足够是，全部使用当前类别资金作为目前需要的所有金额
			if(availableAmount.compareTo(amountNextLeft) >=0 ){
				amountData.put(Constant.ACCOUNTAMOUNT_TYPE_COMMON, amountNextLeft);
				amountNextLeft = BigDecimal.ZERO;
				return amountData;
			}else if(availableAmount.compareTo(BigDecimal.ZERO) >0){
				amountData.put(Constant.ACCOUNTAMOUNT_TYPE_COMMON, amountNextLeft);
				amountNextLeft = amountNextLeft.subtract(availableAmount);
			}
		}
		return amountData;
	}
	
	@Override
	public BigDecimal getUserBalanceAmount(String customerId) throws FrameworkException{
		return bizAccountCommonService.getCustBalance(customerId)
							   .add(bizAccountRechargeService.getCustBalance(customerId)
							   .add(bizAccountExperienceService.getCustBalance(customerId)));
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 创建新的资金类别变化明细
	 * @author  Jie.Zou
	 * @date    2015年12月29日
	 * @version v1.0.0
	 * @param customerId
	 * @param amount
	 * @param balance
	 * @param accountAmountType
	 * @param tradeType
	 * @param time
	 * @return
	 */
	private BizAccountAmountDetail getNewAccountAmountDetail(String customerId,BigDecimal amount,BigDecimal balance,String accountAmountType,String tradeType,Date time){
		BizAccountAmountDetail accountAmountDetail = new BizAccountAmountDetail();
		accountAmountDetail.setPid(accountAmountDetail.getPK());
		accountAmountDetail.setCustomerId(customerId);
		accountAmountDetail.setFundType(accountAmountType);
		accountAmountDetail.setFundtallyType(tradeType);
		accountAmountDetail.setCreateTime(DateUtil.format(DateUtil.getSystemDate()));
		accountAmountDetail.setHappenValue(amount);
		accountAmountDetail.setBalance(balance);
		accountAmountDetail.setCreateUser(customerId);
		accountAmountDetail.setVersion(Constant.PUBLIC_VERSION);
		return accountAmountDetail;
	}

	@Override
	public BigDecimal getUserAvailableAmountExculdExperience(String customerId)
			throws FrameworkException {
		BigDecimal availableAmount = BigDecimal.ZERO;
		BizAccountCommon accountCommon = bizAccountCommonService.selectAccountCommon(customerId);
		BizAccountRecharge accountRecharge = bizAccountRechargeService.selectAccountRecharge(customerId);
		if(null!=accountCommon){
			availableAmount = availableAmount.add(accountCommon.getAvailableAmount());
			if(null!=accountRecharge)
				availableAmount = availableAmount.add(accountRecharge.getAvailableAmount());
		}else if(null!=accountRecharge)
			availableAmount = availableAmount.add(accountRecharge.getAvailableAmount());
		return availableAmount;
	}

	@Override
	public void updateExperienceAmountToAvailableAmount(BigDecimal amount,
			String userId, String borrowId) throws FrameworkException {
		
	}

	@Override
	public int updateCustAccountByUsersId(List<String> userIds,
			BigDecimal amount) {
		CustomerAccountMapper mapper = (CustomerAccountMapper) getDao();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userIds", userIds);
		map.put("amount", amount);
		return mapper.updateCustAccountByUsersId(map);
	}

	@Override
	public BigDecimal getUserBalanceAmountExculdExperience(String customerId) {
		try {
			return bizAccountCommonService.getCustBalance(customerId)
					   .add(bizAccountRechargeService.getCustBalance(customerId));
		} catch (FrameworkException e) {
			e.printStackTrace();
		}
		return BigDecimal.ZERO;
	}

	@Override
	public int updateCustExperienceAmountByUsersId(List<String> userIds,
			BigDecimal amount) {
		CustomerAccountMapper mapper = (CustomerAccountMapper) getDao();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("userIds", userIds);
		map.put("amount", amount);
		return mapper.updateCustExperienceAmountByUsersId(map);
	}

	@Override
	public void updateCustRechargeAmount(String userId, BigDecimal amount) {
		CustomerAccountMapper mp = (CustomerAccountMapper) getDao();
		//得到客户账户表对象
		CustomerAccount ca = mp.getByCusterId(userId);
		//得到账户充值总额
		BigDecimal caAmount = ca.getRechargeAmount() == null ? BigDecimal.ZERO : ca.getRechargeAmount();
		//更新客户充值总额，更新最新修改人和修改时间
		ca.setRechargeAmount(caAmount.add(amount));
		ca.setLastUpdateTime(DateUtil.format(DateUtil.getSystemDate()));
		ca.setLastUpdateUser(userId);
		int c = mp.updateByPrimaryKey(ca);
		if (c < 1) {
			throw new UpdateChangeVersionException("更新充值总额时,更新账户总表失败");
		}
	}

	@Override
	public int updateCustAccountByUsersId(Map<String, Object> maps) {
		CustomerAccountMapper mapper = (CustomerAccountMapper) getDao();
		return mapper.updateCustAccountByUsersId(maps);
	}
	
	@Override
	public int updateCustAccountByMaps(Map<String, Object> maps) {
		CustomerAccountMapper mapper = (CustomerAccountMapper) getDao();
		return mapper.updateCustAccountByMaps(maps);
	}
	
}
