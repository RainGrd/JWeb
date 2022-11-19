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

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.achievo.framework.util.DateUtil;
import com.yscf.common.Constant.Constant;
import com.yscf.core.dao.financial.BizRechargeOnlineMapper;
import com.yscf.core.dao.user.BankMapper;
import com.yscf.core.model.comm.ExportObjectModel;
import com.yscf.core.model.financial.BizRechargeOnline;
import com.yscf.core.model.ptp.financial.BizRechargeOnlineModel;
import com.yscf.core.model.user.Bank;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.service.financial.IBizRechargeOnlineService;
import com.yscf.core.service.user.impl.BankServiceImpl;

/**
 * Description：线上充值业务实现类
 * @author  JingYu.Dai
 * @date    2015年9月25日
 * @version v1.0.0
 */
@Service("bizRechargeOnlineServiceImpl")
public class BizRechargeOnlineServiceImpl extends BaseService<BaseEntity, String> implements IBizRechargeOnlineService {
	
	Logger logger = Logger.getLogger(BizRechargeOnlineServiceImpl.class);

	@Resource(name = "bizAccountRechargeServiceImpl")
	private BizAccountRechargeServiceImpl bizAccountRechargeService;
	
	@Resource
	private PayConfigServiceImpl payConfigService;
	
	@Resource
	private BankMapper bankMapper;
	
	@Resource(name = "bankService")
	private BankServiceImpl bankService;
	
	@Autowired
	public BizRechargeOnlineServiceImpl(BizRechargeOnlineMapper dao) {
		super(dao);
	}

	@Override
	public PageList<BizRechargeOnline> selectOnlineRechargePage(
			BizRechargeOnline bo, PageInfo info)
			throws FrameworkException {
		BizRechargeOnlineMapper dao = (BizRechargeOnlineMapper) getDao();
		Map<String, String> map = new HashMap<String,String>();
		CusTomer c = bo.getCustomer();
		if(null != c){
			String customerName = c.getCustomerName();
			String sname = c.getSname();
			String phoneNo =  c.getPhoneNo();
			map.put("customerName", "".equals(customerName)?null:customerName);
			map.put("sname", "".equals(sname)?null:sname);
			map.put("phoneNo", "".equals(phoneNo)?null:phoneNo);
		}
		String recTimeBegin = bo.getRecTimeBegin();
		String recTimeEnd = bo.getRecTimeEnd();
		String recStatus = bo.getRecStatus();
		Integer recOrderNo = bo.getRecOrderNo();
		String payConfigId = bo.getPayConfigId();
		map.put("recTimeBegin", "".equals(recTimeBegin)?null:recTimeBegin);
		map.put("recTimeEnd", "".equals(recTimeEnd)?null:recTimeEnd);
		map.put("recStatus", "".equals(recStatus)?null:recStatus);
		map.put("recOrderNo", null==recOrderNo?null:recOrderNo.toString());
		map.put("paySystemId", "".equals(payConfigId)?null:payConfigId);
		
		//查询线下充值列表数据 分页
		PageList<BizRechargeOnline> list = dao.selectOnlineRechargePage(map,info);
		//创建 线下充值类 对象
		BizRechargeOnline ro = new BizRechargeOnline();
		//创建 客户对象
		CusTomer customer = new CusTomer();
		customer.setCustomerName("合计");
		ro.setCustomer(customer);
		//根据查询条件查询 总金额  并且赋值
		ro.setAmount(dao.selectSumAmountSelective(map));
		list.add(ro);
		return list;
	}

	@Override
	public int replacementOrder(BizRechargeOnline bizRechargeOnline) {
		BizRechargeOnlineMapper dao = (BizRechargeOnlineMapper) getDao();
		//充值状态充值状态(1 待付款 2 付款失败 3 充值成功 4 待充值 5 系统充值 6 手动补单 7 错误修正 )
		bizRechargeOnline.setRecStatus("6");
		bizAccountRechargeService.doRechargeOnlineNoTran(bizRechargeOnline, "手动补单", DateUtil.getSystemDate());
		return dao.updateByPrimaryKeySelective(bizRechargeOnline);
	}

	@Override
	public List<BizRechargeOnlineModel> selectBizRechargeOnlineEom(
			BizRechargeOnline bizRechargeOnline, ExportObjectModel eom) {
		
		BizRechargeOnlineMapper dao = (BizRechargeOnlineMapper) getDao();
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("map", bizRechargeOnline);
		map.put("expm", eom);
		List<BizRechargeOnline> elist = dao.selectBizRechargeOnlineEom(map); 
		List<BizRechargeOnlineModel> listVo = new  ArrayList<BizRechargeOnlineModel>(0);
		if(null == elist){
			elist = new ArrayList<BizRechargeOnline>(0);
		}else{
			//统计总数
			BigDecimal amount = new BigDecimal(0);
			
			for(BizRechargeOnline vo : elist){
				BizRechargeOnlineModel broVo= new BizRechargeOnlineModel();
				amount = amount.add(vo.getAmount() == null ? new BigDecimal(0) : vo.getAmount());
				
				if(vo.getCustomer()==null){
					broVo.setCustomerName(null);
					broVo.setPhoneNo(null);
					broVo.setRealName(null);
				}else{
					broVo.setCustomerName(vo.getCustomer().getCustomerName());
					broVo.setPhoneNo(vo.getCustomer().getPhoneNo());
					broVo.setRealName(vo.getCustomer().getSname());
				}
				
				broVo.setCreateUser(vo.getLastUpdateUser());
				
				broVo.setRechargeAmount(vo.getAmount());
				broVo.setRechargeTime(vo.getRecTime());
				broVo.setRemark(vo.getRecOnlDesc());
				broVo.setOrderCode(vo.getRecOrderNo());
				broVo.setRechargeType(vo.getPayName());
				broVo.setStatusName(broVo.getStatusNameByCode(vo.getRecStatus()));
				
				listVo.add(broVo);
			}
			BizRechargeOnlineModel bbal = new BizRechargeOnlineModel();
			bbal.setCustomerName(Constant.AGGREGATE_STRING);
			bbal.setRechargeAmount(amount);
	//		添加到集合中
			listVo.add(bbal);
		}
		return listVo;
		
	}
	

	@Override
	public void doRechargeByTask() {
		int successCount = 0;//定时任务处理成功个数
		int failCount = 0;//定时任务处理失败个数
		List<BizRechargeOnline> rechargeOnline =  this.getRechargeOnlineNeddeddoRecharge20();
		for (BizRechargeOnline bizRechargeOnline : rechargeOnline) {
			try {
				this.doRechargeByTaskTX(bizRechargeOnline);
				successCount++;
			} catch (Exception e) {
				if (logger.isDebugEnabled()) {
					//logger.debug("==>doRechargeByTask:"+ e.getMessage());
					failCount++;
					logger.error("系统任务补充入账时错误,rechargeOnlineId=" + bizRechargeOnline.getPid(),e);
				}
			}
		}
		logger.info("系统任务补充入账："+rechargeOnline.size()+"个，成功补充："+successCount+"个，失败补充："+failCount+"个");
		
	}
	
	private void doRechargeByTaskTX(BizRechargeOnline rechargeOnline){
		BizRechargeOnlineMapper mapper =  (BizRechargeOnlineMapper)getDao();
		rechargeOnline = mapper.selectByPrimaryKey(rechargeOnline.getPid());
		this.doRechargeUserNoTran(rechargeOnline, Constant.PAY_RECHARGE_TASK, "", DateUtil.getSystemDate());
		
	}
	
	protected void doRechargeUserNoTran(BizRechargeOnline recharge,String stateAfter,String note,Date time){
		bizAccountRechargeService.doRechargeOnlineNoTran(recharge, note, time);
		
		recharge.setRecStatus(stateAfter);
		try {
			this.updateByPrimaryKey(recharge);
		} catch (FrameworkException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 按充值时间顺序取钱20个执行补充入账操作
	 * @author  Jie.Zou
	 * @date    2015年12月25日
	 * @version v1.0.0
	 * @return
	 */
	private List<BizRechargeOnline> getRechargeOnlineNeddeddoRecharge20(){
		BizRechargeOnlineMapper mapper =  (BizRechargeOnlineMapper)getDao();
		return mapper.selectBizRechargesByStatus(Constant.PAY_RECHARGE_PENDING);
	}

	@Override
	public BizRechargeOnline addRechargeOnline(String userId, BigDecimal amount,
			String payConfigId,String bankCard) {
		BizRechargeOnlineMapper mapper =  (BizRechargeOnlineMapper)getDao();
		Bank bank = bankMapper.selectByBankCard(bankCard);
		if(bank==null){
			bank = new Bank();
			bank.setPid(bank.getPK());
			bank.setBankcardNo(bankCard);
			bank.setCustomerId(userId);
			bank.setCreateTime(DateUtil.format(DateUtil.getSystemDate()));
			bank.setStatus(Constant.DELETE);
			bankMapper.insert(bank);
		}
		BizRechargeOnline recharge = new BizRechargeOnline();
		recharge.setPid(recharge.getPK());
		recharge.setCustomerId(userId);
		recharge.setRecStatus(Constant.PAY_PENDING);
		recharge.setStatus(Constant.PUBLIC_STATUS);
		recharge.setAmount(amount);
		recharge.setBankId(bank.getPid());
		recharge.setPayConfigId(payConfigId);
		recharge.setRecTime(DateUtil.format(DateUtil.getSystemDate()));
		recharge.setCreateTime(DateUtil.getToday().toString());
		recharge.setCreateUser(userId);
		int result = mapper.insert(recharge);
		if(result<1){
			return null;
		}else{
			//再次获取充值记录保证数据完整
			recharge = mapper.selectByPrimaryKey(recharge.getPid());
			return recharge;
		}
	}
}

