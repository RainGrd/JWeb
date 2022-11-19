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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.achievo.framework.util.DateUtil;
import com.google.common.collect.Lists;
import com.yscf.common.Constant.Constant;
import com.yscf.common.Constant.TradeTypeConstant;
import com.yscf.core.dao.business.BizFundtallyMapper;
import com.yscf.core.dao.financial.BizAccountAmountDetailMapper;
import com.yscf.core.dao.financial.BizAccountCommonMapper;
import com.yscf.core.dao.financial.BizAccountRechargeMapper;
import com.yscf.core.dao.financial.BizFrozenWaterMapper;
import com.yscf.core.dao.financial.BizWithdrawMapper;
import com.yscf.core.dao.financial.CustFundWaterMapper;
import com.yscf.core.dao.financial.CustomerAccountMapper;
import com.yscf.core.dao.system.SysAccountMapper;
import com.yscf.core.dao.system.SysParamsMapper;
import com.yscf.core.dao.user.CusTomerMapper;
import com.yscf.core.model.business.BizFundtally;
import com.yscf.core.model.comm.ExportObjectModel;
import com.yscf.core.model.financial.BizAccountAmountDetail;
import com.yscf.core.model.financial.BizAccountCommon;
import com.yscf.core.model.financial.BizAccountRecharge;
import com.yscf.core.model.financial.BizFrozenWater;
import com.yscf.core.model.financial.BizWithdraw;
import com.yscf.core.model.financial.CustFundWater;
import com.yscf.core.model.financial.CustomerAccount;
import com.yscf.core.model.ptp.financial.BizWithdrawModel;
import com.yscf.core.model.system.SysAccount;
import com.yscf.core.model.system.SysParams;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.service.financial.IBizWithdrawService;

/**
 * Description：<br> 
 * 系统体现服务实现
 * @author  Allen
 * @date    2015年9月6日
 * @version v1.0.0
 */
@Service("bizWithdrawService")
public class BizWithdrawServiceImpl extends BaseService<BaseEntity, String> implements
	IBizWithdrawService {

	@Autowired
	public BizWithdrawServiceImpl(BizWithdrawMapper dao) {
		super(dao);
	}
	
	//资金类别变化明细数据访问层
	@Resource(name = "bizAccountAmountDetailMapper")
	private BizAccountAmountDetailMapper bizAccountAmountDetailMapper;
	
	//普通资金 数据访问层
	@Resource(name = "bizAccountCommonMapper")
	private BizAccountCommonMapper bizAccountCommonMapper;

	//资金充值数据访问层接口
	@Resource(name = "bizAccountRechargeMapper")
	private BizAccountRechargeMapper bizAccountRechargeMapper;
	
	//客户账户 数据访问层
	@Resource(name = "customerAccountMapper")
	private CustomerAccountMapper customerAccountMapper;
	
	//客户数据访问层接口
	@Resource(name = "cusTomerMapper")
	private CusTomerMapper cusTomerMapper;
	
	//冻结资金流水表 数据访问层接口
	@Resource(name = "bizFrozenWaterMapper")
	private BizFrozenWaterMapper bizFrozenWaterMapper;
	
	//客户资金流水 数据访问层接口
	@Resource(name = "custFundWaterMapper")
	private CustFundWaterMapper custFundWaterMapper;
	
	//系统资金流水 数据访问层接口
	@Resource(name = "bizFundtallyMapper")
	private BizFundtallyMapper bizFundtallyMapper;
	
	//系统账户记录系统总金额 数据访问层接口
	@Resource(name = "sysAccountMapper")
	private SysAccountMapper sysAccountMapper;
	
	//系统参数 数据持久层服务接口
	@Resource(name = "sysParamsMapper")
	private SysParamsMapper sysParamsMapper;
	
	@Override
	public int insert(BizWithdraw entity) throws FrameworkException {
		//提现描述
		StringBuffer custWitDesc = new StringBuffer();
		//资金冻结流水对象
		BizFrozenWater bizFrozenWater = new BizFrozenWater();
		//客户Id
		String customerId = entity.getCustomer().getPid();
		/*修改 普通资金表(t_biz_account_common)	减除 普通资金available_amount普通可用金额  （累加冻结金额frozen_amount）*/
		//普通资金对象
		BizAccountCommon accountCommon = bizAccountCommonMapper.selectAccountCommonByCustomerId(customerId);
		//普通可用金额
		BigDecimal comAvailableAmount = accountCommon.getAvailableAmount();
		//普通资金冻结金额
		BigDecimal comFrozenAmount = accountCommon.getFrozenAmount();
		custWitDesc.append("提现 ").append(entity.getAmount());
		//判断提现金额是否大于普通资金 进行逻辑处理
		if(entity.getAmount().compareTo(comAvailableAmount) > 0){
			//如果提现金额大于普通可用金额	 那普通可用金额 等于0
			accountCommon.setAvailableAmount(new BigDecimal(0));
			//提现金额小于 普通可用资金	冻结金额 = 冻结金额 + 提现金额
			accountCommon.setFrozenAmount(comAvailableAmount.add(comFrozenAmount));
			//更新普通资金表
			bizAccountCommonMapper.updateByPrimaryKeySelective(accountCommon);
			//描述
			custWitDesc.append("元 ，其中普通资金：").append(comAvailableAmount);
			//冻结资金流水赋值  普通资金
			bizFrozenWater.setCommonAmount(comAvailableAmount);
			
			/* 修改 充值资金表(t_biz_account_recharge) 充值资金表available_amount可用新充值额  （累加冻结金额frozen_amount）*/
			//获得充值资金对象
			BizAccountRecharge accountRecharge =bizAccountRechargeMapper.selectAccountRechargeByCustomerId(customerId);
			//可用新充值额
			BigDecimal recAvailableAmount = accountRecharge.getAvailableAmount();
			//充值金冻结金额
			BigDecimal recFrozenAmount = accountRecharge.getFrozenAmount();
			//充值金额应该减去的金额 = 提现金额 - 普通可用金额
			BigDecimal witRecAmount = entity.getAmount().subtract(comAvailableAmount);
			//充值可用余额 = 充值可用余额-充值金额应该减去的金额
			accountRecharge.setAvailableAmount(recAvailableAmount.subtract(witRecAmount));
			//充值冻结金额 = 充值冻结金额 + 充值金额应该减去的金额
			accountRecharge.setFrozenAmount(recFrozenAmount.add(witRecAmount));
			bizAccountRechargeMapper.updateByPrimaryKeySelective(accountRecharge);
			//描述
			custWitDesc.append("、充值资金：").append(witRecAmount);
			
			//冻结资金流水赋值  充值资金
			bizFrozenWater.setRechargeAmount(witRecAmount);
			
			//获取提现费用和提现费率 map{"fee":提现费用,"wf":提现费率}
			Map<String,BigDecimal> map = getWithdrawalFee(witRecAmount);
			BigDecimal fee = map.get("fee");
			BigDecimal wf = map.get("wf");
			//描述 提现 150元 ，其中普通资金 50元、充值资金100元、手续费 0.4 元（100 * 0.4%）
			custWitDesc.append("、手续费：").append(fee).append("元、").append(witRecAmount).append(" * ")
			.append(new java.text.DecimalFormat("0.00").format(wf.multiply(new BigDecimal(100)))).append("%");
			entity.setFee(fee);
		}else if(entity.getAmount().compareTo(comAvailableAmount) <= 0){
			//提现金额小于 普通可用资金	普通可用金额 = 普通资金 - 提现金额
			accountCommon.setAvailableAmount(comAvailableAmount.subtract(entity.getAmount()));
			//提现金额小于 普通可用资金	冻结金额 = 冻结金额 + 提现金额
			accountCommon.setFrozenAmount(comFrozenAmount.add(entity.getAmount()));
			//更新普通资金表
			bizAccountCommonMapper.updateByPrimaryKeySelective(accountCommon);
			//描述
			custWitDesc.append("普通资金：").append(entity.getAmount());
			//冻结资金流水赋值  普通资金
			bizFrozenWater.setCommonAmount(entity.getAmount());
		}
		
		/* 修改 客户账户表(t_biz_customer_account) 提现总金额withdraw_amount（累加） available_amount可用金额（减） 
		 * 冻结金额freeze_amount（累加当前提现总额）*/
		//客户账户对象
		CustomerAccount customerAccount = customerAccountMapper.getByCusterId(customerId);
		//可用金额
		customerAccount.setAvailableAmount(customerAccount.getAvailableAmount().subtract(entity.getAmount()));
		//冻结金额
		customerAccount.setFreezeAmount(entity.getAmount().add(customerAccount.getFreezeAmount()));
		customerAccountMapper.updateByPrimaryKeySelective(customerAccount);
		
		/* 新增	提现表(t_biz_withdraw)*/
		//提现申请时间
		entity.setPid(entity.getPK());
		entity.setAccountId(customerAccount.getPid());
		entity.setApplyTime(DateUtil.format(new Date()));
		entity.setCreateTime(DateUtil.format(new Date()));
		entity.setCreateUser(entity.getCustomer().getPid());
		entity.setAuditStatus("1");
		entity.setCustWitDesc(custWitDesc.toString());
		BizWithdrawMapper dao = (BizWithdrawMapper) getDao();
		dao.insertSelective(entity);
		
		/* t_biz_frozen_water（冻结资金流水表）冻结流水*/
		bizFrozenWater.setCustomerId(customerId);
		bizFrozenWater.setFkey(entity.getPid());
		bizFrozenWater.setFrozenType(TradeTypeConstant.WITHDRAW_TYPE_202);
		bizFrozenWater.setPid(bizFrozenWater.getPK());
		//冻结资金流水赋值  普通资金
		bizFrozenWater.setCreateUser(customerId);
		bizFrozenWater.setCreateTime(DateUtil.format(new Date()));
		bizFrozenWaterMapper.insertSelective(bizFrozenWater);
		
		//更新交易时间
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("createTime", DateUtil.format(new Date()));
		map.put("userId", customerId);
		cusTomerMapper.updateLastTransacTime(map);
		
		return 1;

	}
	
	@Override
	public int updateBatchAudit(BizWithdraw bw) {
		int result = 0;
		BizWithdrawMapper mapper = (BizWithdrawMapper) getDao();
		if(null != bw && null!=bw.getPid()){
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("auditStatus", bw.getAuditStatus());
			map.put("witDesc", bw.getWitDesc());
			map.put("witSureDesc", bw.getWitSureDesc());
			if(null != bw.getWitSureDesc()){
				map.put("transferedTime", DateUtil.format(new Date()));
			}
			map.put("transferedUserId", bw.getTransferedUserId());
			map.put("descPromptId", bw.getDescPromptId());
			map.put("userId", bw.getUserId());
			map.put("idItem", bw.getPid().split(","));
			result = mapper.batchAudit(map);
			//auditStatus'审核状态（1：提现审核、2：同意（转账确认）、3：提现拒绝、4：转账成功、5：转账失败）',
			String auditStatus = bw.getAuditStatus();
			String[] wids = bw.getPid().split(",");
			if(null != wids && null != wids[0])
			if("2".equals(auditStatus)){
				auditStatus2();
			}else if("3".equals(auditStatus)){
				auditStatus3(wids);
			}else if("4".equals(auditStatus)){
				auditStatus4(wids);
			}else if("5".equals(auditStatus)){
				auditStatus5(wids);
			}
		}
		return result;
	}
	
	/**
	 * Description：<br> 
	 * 审核状态 2：同意（转账确认）
	 * @author  JingYu.Dai
	 * @date    2016年1月3日
	 * @version v1.0.0
	 */
	private void auditStatus2(){
	}
	
	/**
	 * Description：<br> 
	 * 审核状态 3：提现拒绝
	 * @author  JingYu.Dai
	 * @date    2016年1月3日
	 * @version v1.0.0
	 * @param wids 提现ID集合
	 */
	private void auditStatus3(String[] wids){
		BizWithdrawMapper dao = (BizWithdrawMapper) getDao();
		for (String wid : wids) {
			BizWithdraw bw = dao.selectByPrimaryKey(wid);
			String userId = bw.getCustomerId();
			//冻结流水记录
			List<BizFrozenWater> bfs = bizFrozenWaterMapper.findList(userId,wid,TradeTypeConstant.WITHDRAW_TYPE_202);
			BigDecimal isCom = new BigDecimal(0);
			for (BizFrozenWater bfw : bfs) {
				//冻结的普通可用金额
				BigDecimal ca =bfw.getCommonAmount();
				//冻结的充值金额
				BigDecimal ra =bfw.getRechargeAmount();
				
				//客户账户对象
				CustomerAccount customerAccount = customerAccountMapper.getByCusterId(userId);
				//可用金额 = 可用金额 + 冻结的普通可用金额 + 冻结的充值金额
				customerAccount.setAvailableAmount(customerAccount.getAvailableAmount().add(ca).add(ra));
				//冻结金额 = 冻结金额 - 冻结的普通可用金额 - 冻结的充值金额
				customerAccount.setFreezeAmount(customerAccount.getFreezeAmount().subtract(ca).subtract(ra));
				customerAccountMapper.updateByPrimaryKeySelective(customerAccount);
				
				if(ca.compareTo(isCom) > 0){
					//普通资金对象
					BizAccountCommon accountCommon = bizAccountCommonMapper.selectAccountCommonByCustomerId(userId);
					//普通可用余额 = 普通可用余额 + 冻结资金
					accountCommon.setAvailableAmount(ca.add(accountCommon.getAvailableAmount()));
					//冻结金额 = 冻结金额 - 冻结资金
					accountCommon.setFrozenAmount(accountCommon.getFrozenAmount().subtract(ca));
					bizAccountCommonMapper.updateByPrimaryKeySelective(accountCommon);
				}
				if(ra.compareTo(isCom) > 0 ){
					//获得充值资金对象
					BizAccountRecharge accountRecharge = bizAccountRechargeMapper.selectAccountRechargeByCustomerId(userId);
					//充值可用金额  = 充值可用金额 + 冻结资金
					accountRecharge.setAvailableAmount(accountRecharge.getAvailableAmount().add(ra));
					//充值冻结金额 = 充值冻结金额  - 冻结资金
					accountRecharge.setFrozenAmount(accountRecharge.getFrozenAmount().subtract(ra));
					bizAccountRechargeMapper.updateByPrimaryKeySelective(accountRecharge);
				}
				
			}
			//删除冻结流水表
			bizFrozenWaterMapper.updateByDelete(userId,wid,TradeTypeConstant.WITHDRAW_TYPE_202);
		}
	}
	
	/**
	 * Description：<br> 
	 * 审核状态 4：转账成功
	 * @author  JingYu.Dai
	 * @date    2016年1月3日
	 * @version v1.0.0
	 * @param wids 提现ID集合
	 */
	private void auditStatus4(String[] wids){
		BizWithdrawMapper dao = (BizWithdrawMapper) getDao();
		for (String wid : wids) {
			BizWithdraw bw = dao.selectByPrimaryKey(wid);
			String userId = bw.getCustomerId();
			//冻结流水记录
			List<BizFrozenWater> bfs = bizFrozenWaterMapper.findList(userId,wid,TradeTypeConstant.WITHDRAW_TYPE_202);
			BigDecimal isCom = new BigDecimal(0);
			
			for (BizFrozenWater bfw : bfs) {
				//冻结的普通可用金额
				BigDecimal ca =bfw.getCommonAmount();
				//冻结的充值金额
				BigDecimal ra =bfw.getRechargeAmount();
				//获取提现费用和提现费率 map{"fee":提现费用,"wf":提现费率}
				Map<String,BigDecimal> map = getWithdrawalFee(ra);
				BigDecimal fee = map.get("fee");
				BigDecimal wf = map.get("wf");
				//客户资金流水备注\系统资金流水备注 (这两个被备注通用)
				StringBuffer funWatDesc = new StringBuffer();
				funWatDesc.append("提现 ").append(ca.add(ra));
				funWatDesc.append("元 ，其中普通资金：").append(ca);
				funWatDesc.append("、充值资金：").append(ra);
				funWatDesc.append("、手续费：").append(fee).append("元、").append(ra).append(" * ")
				.append(new java.text.DecimalFormat("0.00").format(wf.multiply(new BigDecimal(100)))).append("%");
				//客户账户对象
				CustomerAccount customerAccount = customerAccountMapper.getByCusterId(userId);
				//账户总可用金额 
				BigDecimal availableAmount = customerAccount.getAvailableAmount();
				//账户总冻结金额 
				BigDecimal freezeAmount = customerAccount.getFreezeAmount();
				
				//账户总余额 = 账户总可用金额  +  账户总冻结金额 
				BigDecimal accountBalance = availableAmount.add(freezeAmount.subtract(ca).subtract(ra));
				
				//提现总金额 = 提现总金额 + 冻结的普通可用金额 + 冻结的充值金额
				customerAccount.setWithdrawAmount(customerAccount.getWithdrawAmount().add(ca).add(ra));
				//冻结金额 = 冻结金额 - 冻结的普通可用金额 - 冻结的充值金额
				customerAccount.setFreezeAmount(freezeAmount.subtract(ca).subtract(ra));
				customerAccountMapper.updateByPrimaryKeySelective(customerAccount);
				
				if(ca.compareTo(isCom) > 0){
					//普通资金对象
					BizAccountCommon accountCommon = bizAccountCommonMapper.selectAccountCommonByCustomerId(userId);
					//冻结金额 = 冻结金额 - 冻结资金
					accountCommon.setFrozenAmount(accountCommon.getFrozenAmount().subtract(ca));
					bizAccountCommonMapper.updateByPrimaryKeySelective(accountCommon);
				}
				if(ra.compareTo(isCom) > 0 ){
					//获得充值资金对象
					BizAccountRecharge accountRecharge = bizAccountRechargeMapper.selectAccountRechargeByCustomerId(userId);
					//充值冻结金额 = 充值冻结金额  - 冻结资金
					accountRecharge.setFrozenAmount(accountRecharge.getFrozenAmount().subtract(ra));
					bizAccountRechargeMapper.updateByPrimaryKeySelective(accountRecharge);
					
					//增加 系统账户表记录系统总金额
					sysAccountMapper.updateToAddBlance(bw.getFee());
					
					List<SysAccount> sas = sysAccountMapper.selectAll();
					BigDecimal balance = sas.get(0).getBalance();
					//String funDesc = "提现费用!";
					//添加系统资金流水
					addBizFundtally(userId,bw.getFee(),balance,TradeTypeConstant.SYSTEM_TRADE_TYPE_1007,Constant.CUST_FUND_BUSINESS_TYPE_1,funWatDesc.toString());
					
				}
				//添加 客户资金流水 
				addCustFundWater(userId,ca,ra,Constant.CUST_FUND_BUSINESS_TYPE_2,TradeTypeConstant.WITHDRAW_TYPE_202,accountBalance,wid,funWatDesc.toString());
				
			}
			//删除冻结流水表
			bizFrozenWaterMapper.updateByDelete(userId,wid,TradeTypeConstant.WITHDRAW_TYPE_202);
		}
	}
	
	/**
	 * Description：<br> 
	 * 添加 客户资金流水 
	 * @author  JingYu.Dai
	 * @date    2016年1月4日
	 * @version v1.0.0
	 * @param customerId 客户ID
	 * @param ca 冻结的普通可用金额
	 * @param ra 冻结的充值金额
	 * @param fundType 交易类型（1：收入、2：支出）
	 * @param businessType 交易类型  例如(101：线下充值、103：线上充值、202：提现记录） 详情
	 * @param accountBalance 账户余额
	 * @param fkey
	 * @param funWatDesc
	 */
	private void addCustFundWater(String customerId,BigDecimal ca, BigDecimal ra, String fundType,
		String businessType, BigDecimal accountBalance,String fkey,String funWatDesc){
		
		//用于默认值比较
		BigDecimal isCompare = new BigDecimal(0);
		//创建 客户资金流水 
		CustFundWater fw = new CustFundWater();
		String date = DateUtil.format(new Date());
		fw.setPid(fw.getPK());
		fw.setCustomerId(customerId);
		fw.setFundValue(ca.add(ra));
		fw.setFundType(fundType);
		fw.setBusinessType(businessType);
		fw.setAccountBalance(accountBalance);
		fw.setFkey(fkey);
		fw.setFunWatDesc(funWatDesc);
		fw.setHappenTime(date);
		fw.setCreateTime(date);
		fw.setCreateUser(customerId);
		//添加 客户资金流水 
		custFundWaterMapper.insertSelective(fw);
		
		if(ra.compareTo(isCompare)>0){
			String accAmoDetDesc = "提现  充值资金  资金类别变化明细";
			//获得充值资金对象
			BizAccountRecharge accountRecharge =bizAccountRechargeMapper.selectAccountRechargeByCustomerId(customerId);
			//可用新充值额
			BigDecimal recAvailableAmount = accountRecharge.getAvailableAmount();
			//充值金冻结金额
			BigDecimal recFrozenAmount = accountRecharge.getFrozenAmount();
			
			//添加 	资金类别变化明细 Constant.ACCOUNTAMOUNT_TYPE_RECHARGE
			addBizAccountAmountDetail(customerId,fw.getPid(),Constant.ACCOUNTAMOUNT_TYPE_RECHARGE,ra,recAvailableAmount.add(recFrozenAmount),TradeTypeConstant.WITHDRAW_TYPE_202,accAmoDetDesc);
		}
		if(ca.compareTo(isCompare)>0){
			//添加 	资金类别变化明细
			String accAmoDetDesc = "提现  普通资金  资金类别变化明细";
			//普通资金对象
			BizAccountCommon accountCommon = bizAccountCommonMapper.selectAccountCommonByCustomerId(customerId);
			//普通可用金额
			BigDecimal comAvailableAmount = accountCommon.getAvailableAmount();
			//普通资金冻结金额
			BigDecimal comFrozenAmount = accountCommon.getFrozenAmount();
			
			addBizAccountAmountDetail(customerId,fw.getPid(),Constant.ACCOUNTAMOUNT_TYPE_COMMON,ca,comAvailableAmount.add(comFrozenAmount),TradeTypeConstant.WITHDRAW_TYPE_202,accAmoDetDesc);
		}
	}
	
	/**
	 * Description：<br> 
	 * 添加系统资金流水
	 * @author  JingYu.Dai
	 * @date    2016年1月4日
	 * @version v1.0.0
	 * @param customerId 客户ID
	 * @param amount 金额
	 * @param balance 余额
	 * @param detailType 流水类型   例如：流水类型（1001:VIP付费,1003:利息管理费,1004: 推荐奖励分成,1006:推荐奖励,1007:提现手续费)
	 * @param dealType 交易类型（1：收入、2：支出）
	 * @param funDesc 备注
	 */
	private void addBizFundtally(String customerId,BigDecimal amount,
		BigDecimal balance,String detailType,String dealType ,String funDesc){
		String date = DateUtil.format(new Date());
		BizFundtally bf = new BizFundtally();
		bf.setPid(bf.getPK());
		bf.setCustomerId(customerId);
		bf.setAmount(amount);
		bf.setBalance(balance);
		bf.setDetailType(detailType);
		bf.setDealType(dealType);
		bf.setFunDesc(funDesc);
		bf.setActorTime(date);
		bf.setCreateTime(date);
		bf.setCreateUser(customerId);
		
		//添加系统资金流水
		bizFundtallyMapper.insertSelective(bf);
	}
	
	/**
	 * Description：<br> 
	 * 计算提现费用
	 * @author  JingYu.Dai
	 * @date    2016年1月4日
	 * @version v1.0.0
	 * @param ra
	 * @return map{"fee":提现费用,"wf":提现费率}
	 */
	private Map<String,BigDecimal> getWithdrawalFee(BigDecimal ra){
		Map<String,BigDecimal> map = new HashMap<String, BigDecimal>();
		//获取系统参数
		SysParams params= sysParamsMapper.getParamsByParamKey("WITHDRAWAL_FEE");
		String withdrawalFee = params.getParamValue();
		//提现费率
		BigDecimal wf = new BigDecimal(withdrawalFee);
		//提现费用
		String fee = new java.text.DecimalFormat("0.00").format(ra.multiply(wf));
		map.put("fee", new BigDecimal(fee));
		map.put("wf", wf);
		return map;
	}
	
	
	/**
	 * Description：<br> 
	 * 添加 	资金类别变化明细
	 * @author  JingYu.Dai
	 * @date    2016年1月4日
	 * @version v1.0.0
	 * @param customerId 客户ID
	 * @param payConfigId 关联流水ID
	 * @param fundType 资金类别（0-未定义，1-充值资金，2-回款资金，3-体验资金，4-普通资金）
	 * @param happenValue 发生额
	 * @param balance 账户总余额
	 * @param fundtallyType 资金流水类别
	 * @param accAmoDetDesc 备注
	 */
	private void addBizAccountAmountDetail(String customerId,String payConfigId,String fundType,
		BigDecimal happenValue,BigDecimal balance,String fundtallyType,String accAmoDetDesc){
		//创建资金类别变化明细实体
		BizAccountAmountDetail ad = new BizAccountAmountDetail();
		String date = DateUtil.format(new Date());
		
		ad.setPid(ad.getPK());
		ad.setCustomerId(customerId);
		ad.setPayConfigId(payConfigId);
		ad.setFundType(fundType);
		ad.setHappenValue(happenValue);
		ad.setBalance(balance);
		ad.setFundtallyType(fundtallyType);
		ad.setAccAmoDetDesc(accAmoDetDesc);
		ad.setCreateTime(date);
		ad.setCreateUser(customerId);
		bizAccountAmountDetailMapper.insertSelective(ad);
	}
	
	/**
	 * Description：<br> 
	 * 审核状态 5：转账失败
	 * @author  JingYu.Dai
	 * @date    2016年1月3日
	 * @version v1.0.0
	 * @param wids 提现ID集合
	 */
	private void auditStatus5(String[] wids){
		auditStatus3(wids);
	}

	@Override
	public List<BizWithdraw> selectAll(BizWithdraw bizWithdraw, PageInfo pageInfo) {
		BizWithdrawMapper mapper = (BizWithdrawMapper) getDao();
		return mapper.selectAll(bizWithdraw);
	}
	
	@Override
	public PageList<BizWithdraw> selectSelectivePage(HashMap<String,Object> parasMap,PageInfo info) {
		BizWithdrawMapper mapper = (BizWithdrawMapper) getDao();
		PageList<BizWithdraw> bizWithdraws = mapper.selectSelectivePage(parasMap,info);
		//创建 线下充值类 对象
		BizWithdraw bw = new BizWithdraw();
		//创建 客户对象
		CusTomer customer = new CusTomer();
		customer.setCustomerName("合计");
		bw.setCustomer(customer);
		//根据查询条件查询 统计（提现金额、提现手续费）  总金额   并且赋值
		Map<String,BigDecimal> map = mapper.selectSumAmountSelective(parasMap);
		if(null != map && null != bw){
			bw.setAmount(map.get("amount"));
			bw.setCost(map.get("cost"));
			bw.setFee(map.get("fee"));
			bizWithdraws.add(bw);
		}
		return bizWithdraws;
	}
	
	@Override
	public int deleteByPrimaryKey(String pids){
		BizWithdrawMapper mapper = (BizWithdrawMapper) getDao();
		String [] arr = pids.split(",");
		List<String> list = Lists.newArrayList();
		for(int i=0;i<arr.length;i++){
			list.add(arr[i]);
		}
		return mapper.deleteByPrimaryKey(pids);
		
	}

	@Override
	public int deleteBtach(String pids) {
		BizWithdrawMapper mapper = (BizWithdrawMapper) getDao();
		String [] arr = pids.split(",");
		List<String> list = Lists.newArrayList();
		for(int i=0;i<arr.length;i++){
			list.add(arr[i]);
		}
		return mapper.deleteBtach(list);
	}
	@Override
	public int batchAuditTransferService(BizWithdraw bw) {
		int result = 0;
		if(null != bw && null!=bw.getPid()){
			BizWithdrawMapper mapper = (BizWithdrawMapper) getDao();
			Map<String,Object> map = new HashMap<String,Object>();
			map.put("auditStatus", bw.getAuditStatus());
			map.put("witSureDesc", bw.getWitSureDesc());
			map.put("descPromptId", bw.getDescPromptId());
			map.put("idItem", bw.getPid().split(","));
			result = mapper.batchAuditTransferService(map);
		}
		return result;
	}

	@Override
	public int getTotalCountByAuditStatus(String auditStatus)
			throws FrameworkException {
		BizWithdrawMapper dao = (BizWithdrawMapper) getDao();
		return dao.getTotalCountByAuditStatus(auditStatus);
	}

	@Override
	public List<BizWithdrawModel> selectBizWithdrawVOEom(
			BizWithdrawModel bizWithdrawVO, ExportObjectModel eom) {
		
		BizWithdrawMapper dao = (BizWithdrawMapper) getDao();
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("parasMap", bizWithdrawVO);
		map.put("expm", eom);
		List<BizWithdraw> elist = dao.selectBizWithdrawVOEom(map); 
		
		List<BizWithdrawModel> elistVo = new ArrayList<BizWithdrawModel>();
		
		BigDecimal amount = new BigDecimal(0);
		BigDecimal fee = new BigDecimal(0);
		
		for(BizWithdraw bw : elist){
			if(bw==null){
				continue;
			}
			
			BizWithdrawModel vo = new BizWithdrawModel();
			
			if(bw.getCustomer()!=null){
				vo.setCustomerName(bw.getCustomer().getCustomerName());
				vo.setPhoneNo(bw.getCustomer().getPhoneNo());
				vo.setSname(bw.getCustomer().getSname());
				
			}
			if(bw.getBank()!=null){
				vo.setBankcardNo(bw.getBank().getBankcardNo());
				vo.setBelongingBank(bw.getBank().getBelongingBank());
				vo.setOpenAddress(bw.getBank().getOpenAddress());
			}
			vo.setFee(bw.getFee());
			vo.setAmount(bw.getAmount());
			vo.setApplyTime(bw.getApplyTime());
			vo.setAuditStatusName(vo.formatAuditStatus(bw.getAuditStatus()));
			vo.setWitDesc(bw.getWitDesc());
			vo.setWitSureDesc(bw.getWitSureDesc());
			
			amount =amount.add(bw.getAmount() == null ? new BigDecimal(0) : bw.getAmount());
			fee = fee.add(bw.getFee() == null ? new BigDecimal(0) : bw.getFee());
			
			elistVo.add(vo);
		}
		BizWithdrawModel vo = new BizWithdrawModel();
		vo.setCustomerName(Constant.AGGREGATE_STRING);
		vo.setAmount(amount);
		vo.setFee(fee);
		elistVo.add(vo);
		return elistVo;
	}

}


