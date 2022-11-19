/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * TODO
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年12月14日     Jie.Zou		Initial Version.
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
import com.achievo.framework.service.impl.BaseService;
import com.achievo.framework.util.DateUtil;
import com.yscf.common.Constant.Constant;
import com.yscf.core.dao.financial.BizAccountAmountDetailMapper;
import com.yscf.core.model.financial.BizAccountAmountDetail;
import com.yscf.core.model.financial.BizAccountRecharge;
import com.yscf.core.model.financial.CustFundWater;
import com.yscf.core.service.financial.IBizAccountAmountDetailService;
import com.yscf.core.service.financial.IBizAccountCommonService;
import com.yscf.core.service.financial.IBizAccountExperienceService;
import com.yscf.core.service.financial.IBizAccountRechargeService;

/**
 * Description：<br> 
 * 资金类别变化明细业务逻辑接口实现
 * @author  Jie.Zou
 * @date    2015年12月14日
 * @version v1.0.0
 */
@Service("bizAccountAmountDetailServiceImpl")
public class BizAccountAmountDetailServiceImpl extends BaseService<BaseEntity, String> implements IBizAccountAmountDetailService {

	@Resource
	private IBizAccountCommonService bizAccountCommonService;
	@Resource
	private IBizAccountRechargeService bizAccountRechargeService;
	@Resource
	private IBizAccountExperienceService bizAccountExperienceService;
	
	@Autowired
	public BizAccountAmountDetailServiceImpl(BizAccountAmountDetailMapper dao) {
		super(dao);
	}

	@Override
	public void addAccountAmountDetail(BizAccountRecharge accountRecharge,
			BigDecimal amount, CustFundWater fundWater, BigDecimal balance,
			String fundType, Date time) {
		BizAccountAmountDetailMapper mapper = (BizAccountAmountDetailMapper)getDao();
		BizAccountAmountDetail detail = new BizAccountAmountDetail();
		detail.setPid(detail.getPK());
		detail.setCustomerId(accountRecharge.getCustomerId());
		detail.setFundType(fundType);
		detail.setPayConfigId(fundWater.getPid());
		detail.setFundtallyType(fundWater.getFundType());
		detail.setHappenValue(amount);
		detail.setBalance(balance);
		mapper.insert(detail);
	}

	@Override
	public void addAccountAmountDetails(
			List<CustFundWater> custFWs,String fundType) {
		BizAccountAmountDetailMapper mapper = (BizAccountAmountDetailMapper)getDao();
		List<BizAccountAmountDetail> details = new ArrayList<BizAccountAmountDetail>();
		for (CustFundWater custFundWater : custFWs) {
			BizAccountAmountDetail detail = new BizAccountAmountDetail();
			detail.setPid(detail.getPK());
			detail.setCustomerId(custFundWater.getCustomerId());
			detail.setFundType(fundType);
			detail.setPayConfigId(custFundWater.getPid());
			detail.setFundtallyType(custFundWater.getBusinessType());
			detail.setHappenValue(custFundWater.getFundValue());
			detail.setBalance(this.getAccountBalance(custFundWater.getCustomerId(), fundType));
			detail.setCreateTime(DateUtil.format(DateUtil.getSystemDate()));
			detail.setStatus(Constant.PUBLIC_STATUS);
			detail.setVersion(Constant.PUBLIC_VERSION);
			details.add(detail);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("details", details);
		mapper.addDetails(map);
	}

	
	private BigDecimal getAccountBalance(String userId,String fundType){
		try {
			if(fundType.equals(Constant.ACCOUNTAMOUNT_TYPE_COMMON)){
				return bizAccountCommonService.getCustBalance(userId);
			}else if(fundType.equals(Constant.ACCOUNTAMOUNT_TYPE_RECHARGE)){
				return bizAccountRechargeService.getCustBalance(userId);
			}else if(fundType.equals(Constant.ACCOUNTAMOUNT_TYPE_EXPERIENCE)){
				return bizAccountExperienceService.getCustBalance(userId);
			}
		} catch (FrameworkException e) {
			e.printStackTrace();
		}
		return BigDecimal.ZERO;
	}
	
}


