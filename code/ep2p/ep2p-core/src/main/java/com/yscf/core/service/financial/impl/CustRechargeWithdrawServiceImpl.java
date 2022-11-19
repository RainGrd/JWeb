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
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.financial.CustRechargeWithdrawMapper;
import com.yscf.core.model.financial.CustFundWater;
import com.yscf.core.model.financial.CustRechargeWithdraw;
import com.yscf.core.service.financial.ICustRechargeWithdrawService;

/**
 * Description：<br> 
 * 客户个人中心 充值提现 业务层实现类
 * @author  JingYu.Dai
 * @date    2015年12月22日
 * @version v1.0.0
 */
@Service("custRechargeWithdrawServiceImpl")
public class CustRechargeWithdrawServiceImpl extends BaseService<BaseEntity, String>
		implements ICustRechargeWithdrawService{

	@Autowired
	public CustRechargeWithdrawServiceImpl(CustRechargeWithdrawMapper dao) {
		super(dao);
	}

	@Override
	public List<CustRechargeWithdraw> selectRechargePage(
			CustFundWater custFundWater, Integer pageIndex, Integer pageSize) throws FrameworkException{
		String businessTypeStr = custFundWater.getBusinessTypeStr();
		if(null != businessTypeStr){
			String[] businessTypes = businessTypeStr.split(",");
			List<String> userList = Arrays.asList(businessTypes);
			custFundWater.setBusinessTypes(userList);
		}
		CustRechargeWithdrawMapper dao = (CustRechargeWithdrawMapper)this.getDao();
		return dao.selectRechargePage(custFundWater, pageIndex*pageSize, pageSize);
	}

	@Override
	public Integer selectRechargeTotal(CustFundWater custFundWater) throws FrameworkException {
		String businessTypeStr = custFundWater.getBusinessTypeStr();
		if(null != businessTypeStr){
			String[] businessTypes = businessTypeStr.split(",");
			List<String> userList = Arrays.asList(businessTypes);
			custFundWater.setBusinessTypes(userList);
		}
		CustRechargeWithdrawMapper dao = (CustRechargeWithdrawMapper)this.getDao();
		return dao.selectRechargeTotal(custFundWater);
	}
	
	@Override
	public List<CustRechargeWithdraw> selectWithdrawPage(
			CustFundWater custFundWater, Integer pageIndex, Integer pageSize) throws FrameworkException{
		String businessTypeStr = custFundWater.getBusinessTypeStr();
		if(null != businessTypeStr){
			String[] businessTypes = businessTypeStr.split(",");
			List<String> userList = Arrays.asList(businessTypes);
			custFundWater.setBusinessTypes(userList);
		}
		CustRechargeWithdrawMapper dao = (CustRechargeWithdrawMapper)this.getDao();
		return dao.selectWithdrawPage(custFundWater, pageIndex*pageSize, pageSize);
	}

	@Override
	public Integer selectWithdrawTotal(CustFundWater custFundWater) throws FrameworkException {
		String businessTypeStr = custFundWater.getBusinessTypeStr();
		if(null != businessTypeStr){
			String[] businessTypes = businessTypeStr.split(",");
			List<String> userList = Arrays.asList(businessTypes);
			custFundWater.setBusinessTypes(userList);
		}
		CustRechargeWithdrawMapper dao = (CustRechargeWithdrawMapper)this.getDao();
		return dao.selectWithdrawTotal(custFundWater);
	}
	
	@Override
	public Map<String,BigDecimal> getAmountSumByCustId(String cusId) throws FrameworkException {
		CustRechargeWithdrawMapper dao = (CustRechargeWithdrawMapper)this.getDao();
		return dao.getAmountSumByCustId(cusId);
	}
}
