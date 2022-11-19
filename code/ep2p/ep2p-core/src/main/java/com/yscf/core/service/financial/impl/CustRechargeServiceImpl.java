/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 充值逻辑层
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2016年3月10日     Jie.Zou		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.financial.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import net.sf.ehcache.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Service;

import com.achievo.framework.util.DateUtil;
import com.yscf.common.Constant.Constant;
import com.yscf.core.dao.financial.BizRechargeOnlineMapper;
import com.yscf.core.dao.financial.recharge.PayConfigMapper;
import com.yscf.core.dao.user.BankMapper;
import com.yscf.core.model.financial.BizRechargeOnline;
import com.yscf.core.model.financial.recharge.PayConfig;
import com.yscf.core.model.system.SysParams;
import com.yscf.core.model.user.Bank;
import com.yscf.core.service.financial.ICustRechargeService;
import com.yscf.core.service.financial.IRechargeOnlineFactory;
import com.yscf.core.service.system.impl.SysParamsServiceImpl;
import com.yscf.core.service.user.impl.BankServiceImpl;

/**
 * Description：<br> 
 * 充值逻辑层
 * @author  Jie.Zou
 * @date    2016年3月10日
 * @version v1.0.0
 */
@Service("custRechargeServiceImpl")
public class CustRechargeServiceImpl implements ICustRechargeService {
	
	@Resource(name = "tenpayServiceImpl")
	private TenpayServiceImpl tenpayService;
	
	@Resource(name = "llPayAppServiceImpl")
	private LlPayAppServiceImpl llPayAppServiceImpl;
	
	@Resource(name ="payConfigMapper")
	private PayConfigMapper payMapper;
	
	@Resource(name = "sysParamsService")
	public SysParamsServiceImpl sysParamService;
	
	@Resource(name = "bizAccountRechargeServiceImpl")
	private BizAccountRechargeServiceImpl bizAccountRechargeService;
	
	@Resource
	private BizRechargeOnlineMapper bizRechargeOnlineMapper;
	
	@Resource
	private BankMapper bankMapper;
	
	@Resource(name = "bankService")
	private BankServiceImpl bankService;
	
	@Override
	public Map<String, String> getFormParams(String userId, BigDecimal amount,
			String payConfigId, Date time, Map<String, String> params) {
		try {
			SysParams webRoot = sysParamService.getParamsByParamKey(Constant.WEB_ROOT);
			params.put(Constant.WEB_ROOT, webRoot.getParamValue());
			PayConfig payConfig = payMapper.selectByPrimaryKey(payConfigId);
			BizRechargeOnline recharge = new BizRechargeOnline();
			recharge.setPid(recharge.getPK());
			recharge.setCustomerId(userId);
			recharge.setRecStatus(Constant.PAY_PENDING);
			recharge.setStatus(Constant.PUBLIC_STATUS);
			recharge.setAmount(amount);
			recharge.setPayConfigId(payConfigId);
			recharge.setRecTime(DateUtil.format(DateUtil.getSystemDate()));
			recharge.setCreateTime(DateUtil.getToday().toString());
			recharge.setCreateUser(userId);
			bizRechargeOnlineMapper.insert(recharge);
			recharge = bizRechargeOnlineMapper.selectByPrimaryKey(recharge.getPid());
			return IRechargeOnlineFactory.getPlatform(payConfigId).getFormParams(recharge, payConfig, params);
		} catch (Exception e) {
			e.printStackTrace();
		}  	
		return null;
	}
	
	@Override
	public String doRechargeFeedback(Map<String, String> feedbackData,
			String paySystemType, Date time) {
		String result = Constant.PAY_UNDEFINED;
		BaseRechargeOnlinePlatform service = IRechargeOnlineFactory.getPlatform(paySystemType);
		Integer rechargeOnlineId = Integer.parseInt(service.resolveRechargeOnlineId(feedbackData, paySystemType));
		BizRechargeOnline recharge = bizRechargeOnlineMapper.getRechargeByRecOrderNo(rechargeOnlineId);
		if(recharge != null){
			final Object rechargeLock = getRechargeLocks(recharge.getPid());
			synchronized (rechargeLock) {
				recharge = bizRechargeOnlineMapper.getRechargeByRecOrderNo(rechargeOnlineId);
				result = this.doRechargeFeedbackInner(feedbackData, paySystemType, time, recharge);
			}
		}
		return result;
	}
	
	private static Map<String, Object> rechargeLocks = new ConcurrentHashMap<String, Object>();

	public static Object getRechargeLocks(String rechargeOnlineId) {
		if(!rechargeLocks.containsKey(rechargeOnlineId)){
			rechargeLocks.put(rechargeOnlineId, new Object());
		}
		return rechargeLocks.get(rechargeOnlineId);
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 支付通知方法
	 * @author  Jie.Zou
	 * @date    2015年11月23日
	 * @version v1.0.0
	 * @param feedbackData
	 * @param paySystemType
	 * @param time
	 * @param recharge
	 * @return
	 */
	private String doRechargeFeedbackInner(Map<String,String> feedbackData,String paySystemType,Date time,BizRechargeOnline recharge){
		//支付平台存在多次通知的情况，对支付成功的情况，只处理状态还处于“待付款”
		//的线上充值尸体，对于支付失败的情况，系统不做任何数据变动，保持尸体的状态
		//因为部分支付平台对某个充值操作肯呢个先通知一个支付失败的消息，后续
		//又通知一个支付成功的消息，因此，系统始终保持等待一个支付成功的消息，
		//除非已经处理过，所以返回值只要不是UNDFFINED既可以认为已经成功处理过
		BaseRechargeOnlinePlatform service = IRechargeOnlineFactory.getPlatform(paySystemType);
		if(recharge.getRecStatus().equals(Constant.PAY_PENDING)){
			PayConfig payConfig = payMapper.selectByPrimaryKey(recharge.getPayConfigId());
			if(service.checkFeedbackSign(feedbackData, payConfig)){
				if(service.isRechargeStateSuccess(feedbackData)){
					this.rechargeUser(recharge, time);
					//充值成功判断是不是App的连连支付充值，如果是则拿出之前保存的银行卡ID修改银行卡状态
					if(Constant.LL_PAY.equals(paySystemType)){
						if(null!=recharge.getBankId()){
							Bank bank = bankMapper.selectByPrimaryKey(recharge.getBankId());
							try {
								Bank newBank = bankService.getByCardBank(bank.getBankcardNo());
								if(null!=newBank){
									bank.setBelongingBank(newBank.getBelongingBank());
								}
								bank.setStatus(Constant.ACTIVATE);
								bankMapper.updateByPrimaryKey(bank);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					}
					return Constant.PAY_OK;
				}else{
					return Constant.PAY_FAILED;
				}
			}
		}else{
			//当后台通知先于前台通知时，则出现前台页面跳转时，
			//金额已经入账成功的情况，此时不能再执行入账操作  
			//但是前台页面要返回充值状态的消息给充值用户
			return recharge.getRecStatus(); 
		}
		return Constant.PAY_UNDEFINED;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 真正执行系统入账操作
	 * @author  Jie.Zou
	 * @date    2015年12月16日
	 * @version v1.0.0
	 * @param recharge
	 * @param time
	 * @return
	 */
	private boolean rechargeUser(BizRechargeOnline recharge,Date time){
		try {
			this.doRechargeUser(recharge, time);
		} catch (Exception e) {
			modifyPayPendingRechargePending(recharge.getPid());
		}
		return true;
	}
	
	protected void doRechargeUser(BizRechargeOnline recharge,Date time){
		PayConfig payConfig = payMapper.selectByPrimaryKey(recharge.getPayConfigId());
		this.doRechargeUserNoTran(recharge, Constant.PAY_OK, "通过"+payConfig.getPayName()+"充值", time);
	}
	
	protected void doRechargeUserNoTran(BizRechargeOnline recharge,String stateAfter,String note,Date time){
		bizAccountRechargeService.doRechargeOnlineNoTran(recharge, note, time);
		recharge.setRecStatus(stateAfter);
		bizRechargeOnlineMapper.updateByPrimaryKey(recharge);
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 待付款->待充值
	 * @author  Jie.Zou
	 * @date    2015年12月25日
	 * @version v1.0.0
	 * @param rechargeOnlineId
	 */
	private void modifyPayPendingRechargePending(String rechargeOnlineId){
		BizRechargeOnline rechargeOnline = bizRechargeOnlineMapper.selectByPrimaryKey(rechargeOnlineId);
		if(rechargeOnline.getRecStatus() == Constant.PAY_PENDING){
			rechargeOnline.setRecStatus(Constant.PAY_RECHARGE_PENDING);
			bizRechargeOnlineMapper.updateByPrimaryKey(rechargeOnline);
		}
	}
	
}


