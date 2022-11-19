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
import com.yscf.common.Constant.Constant;
import com.yscf.core.dao.financial.BizAccountRechargeMapper;
import com.yscf.core.dao.financial.BizRechargeOfflineMapper;
import com.yscf.core.model.comm.ExportObjectModel;
import com.yscf.core.model.financial.BizRechargeOffline;
import com.yscf.core.model.ptp.financial.BizRechargeOfflineModel;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.service.financial.IBizAccountRechargeService;
import com.yscf.core.service.financial.IBizRechargeOfflineService;

/**
 * Description：线下充值业务处理实现类
 * @author  JingYu.Dai
 * @date    2015年9月25日
 * @version v1.0.0
 */
@Service("bizRechargeOfflineServiceImpl")
public class BizRechargeOfflineServiceImpl extends BaseService<BaseEntity, String> implements IBizRechargeOfflineService {

	@Autowired
	public BizRechargeOfflineServiceImpl(BizRechargeOfflineMapper dao) {
		super(dao);
	}
	
	@Resource(name="bizAccountRechargeMapper")
	BizAccountRechargeMapper bizAccountRechargeDao;
	
	@Resource(name = "bizAccountRechargeServiceImpl")
	private IBizAccountRechargeService bizAccountRechargeService;
	 
	@Override
	public PageList<BizRechargeOffline> selectOfflineRechargePage(
			BizRechargeOffline bo, PageInfo info)
			throws FrameworkException {
		BizRechargeOfflineMapper dao = (BizRechargeOfflineMapper) getDao();
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
		map.put("recTimeBegin", "".equals(recTimeBegin)?null:recTimeBegin);
		map.put("recTimeEnd", "".equals(recTimeEnd)?null:recTimeEnd);
		map.put("recStatus", "".equals(recStatus)?null:recStatus);
		//查询线下充值列表数据 分页
		PageList<BizRechargeOffline> list = dao.selectRechargeOfflinePage(map,info);
		//创建 线下充值类 对象
		BizRechargeOffline ro = new BizRechargeOffline();
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
	public int insert(BizRechargeOffline bro) throws FrameworkException{
		//获得线下充值数据层接口 对象
		BizRechargeOfflineMapper dao = (BizRechargeOfflineMapper) getDao();
		bro.setPid(bro.getPK());
		//新增线下充值数据
		return dao.insert(bro);
	}
	
	@Override
	public int updateOfflineRechargeCheck(BizRechargeOffline bro)
			throws FrameworkException {
		int count = 0;
		String recStatus = bro.getRecStatus();
		//判断审核状态是否为同意
		if(Constant.REC_STATUS_CONSENT.equals(recStatus)){
			/*String clientId = bro.getCustomerId();
			BigDecimal availableAmount = new BigDecimal(0);*/
			//查询客户可用余额资金
			/*String availableAmountStr = bizAccountRechargeDao.getAvailableAmountByClientId(clientId);
			if(null == availableAmountStr){
				//创建充值资金对象
				BizAccountRecharge ar = new BizAccountRecharge();
				ar.setCustomerId(bro.getCustomerId());
				ar.setAvailableAmount(availableAmount.add(bro.getAmount()));
				ar.setCreateUser(bro.getLastUpdateUser());
				ar.setCreateTime(bro.getLastUpdateTime());
				ar.setLastUpdateUser(bro.getLastUpdateUser());
				ar.setLastUpdateTime(bro.getLastUpdateTime());
				ar.setStatus(Constant.ACTIVATE);
				ar.setAccRechDesc("线下充值"+bro.getAmount()+"元！");
				ar.setPid(ar.getPK());
				//新增充值资金数据
				count += bizAccountRechargeDao.insert(ar);
			}else{
				availableAmount = new BigDecimal(availableAmountStr);
				//创建充值资金对象
				BizAccountRecharge ar = new BizAccountRecharge();
				ar.setCustomerId(bro.getCustomerId());
				ar.setAvailableAmount(availableAmount.add(bro.getAmount()));
				ar.setLastUpdateUser(bro.getLastUpdateUser());
				ar.setLastUpdateTime(bro.getLastUpdateTime());
				//新增充值资金数据
				count += bizAccountRechargeDao.updateByCustomerId(ar);
			}*/
			BizRechargeOfflineMapper dao = (BizRechargeOfflineMapper) getDao();
			count += dao.updateByPrimaryKeySelective(bro);
			bizAccountRechargeService.doRechargeOfflineNoTran(bro, "客户线下充值", DateUtil.getSystemDate());
		}else{
			BizRechargeOfflineMapper dao = (BizRechargeOfflineMapper) getDao();
			count += dao.updateByPrimaryKeySelective(bro);
		}
		return count;
	}

	@Override
	public int getTotalCountByRecStatus(String recStatus)
			throws FrameworkException {
		BizRechargeOfflineMapper dao = (BizRechargeOfflineMapper) getDao();
		return dao.getTotalCountByRecStatus(recStatus);
	}

	@Override
	public List<BizRechargeOfflineModel> selectBizRechargeOfflineEom(
			BizRechargeOffline bizRechargeOffline, ExportObjectModel eom) {
		BizRechargeOfflineMapper dao = (BizRechargeOfflineMapper) getDao();
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("map", bizRechargeOffline);
		map.put("expm", eom);
		List<BizRechargeOffline> elist = dao.selectBizRechargeOfflineEom(map); 
		List<BizRechargeOfflineModel> listVo = new  ArrayList<BizRechargeOfflineModel>(0);
		if(null == elist){
			elist = new ArrayList<BizRechargeOffline>(0);
		}else{
			//统计总数
			BigDecimal amount = new BigDecimal(0);
			
			for(BizRechargeOffline vo : elist){
				BizRechargeOfflineModel broVo= new BizRechargeOfflineModel();
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
				broVo.setRemark(vo.getRecOffDesc());
				broVo.setStatusName(broVo.getStatusNameByCode(vo.getRecStatus()));
				
				listVo.add(broVo);
			}
			BizRechargeOfflineModel bbal = new BizRechargeOfflineModel();
			bbal.setCustomerName(Constant.AGGREGATE_STRING);
			bbal.setRechargeAmount(amount);
	//		添加到集合中
			listVo.add(bbal);
		}
		return listVo;
	
	}

}


