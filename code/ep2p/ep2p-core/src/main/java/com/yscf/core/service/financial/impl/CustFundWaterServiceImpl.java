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
import java.util.Map.Entry;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.service.impl.BaseService;
import com.achievo.framework.util.DateUtil;
import com.sun.star.uno.RuntimeException;
import com.yscf.common.Constant.Constant;
import com.yscf.common.util.EscfDateUtil;
import com.yscf.core.dao.financial.CustFundWaterMapper;
import com.yscf.core.model.comm.ExportObjectModel;
import com.yscf.core.model.financial.CustFundWater;
import com.yscf.core.model.ptp.financial.CustFundWaterModel;
import com.yscf.core.service.financial.ICustFundWaterService;
import com.yscf.core.service.financial.ICustomerAccountService;

/**
 * Description：<br>
 * 客户资金流水服务实现
 * 
 * @author Allen
 * @date 2015年9月6日
 * @version v1.0.0
 */
@Service("custFundWaterService")
public class CustFundWaterServiceImpl extends BaseService<BaseEntity, String>
		implements ICustFundWaterService {


	@Resource
	private ICustomerAccountService customerAccountService;
	
	@Autowired
	public CustFundWaterServiceImpl(CustFundWaterMapper dao) {
		super(dao);
	}

	@Override
	public List<CustFundWater> selectAll(CustFundWater custFundWater,
			PageInfo pageInfo) {
		CustFundWaterMapper mapper = (CustFundWaterMapper) getDao();
		return mapper.selectAll(custFundWater);
	}

	@Override
	public PageList<CustFundWater> selectAllPage(
			HashMap<String, Object> parasMap, PageInfo info) {
		CustFundWaterMapper mapper = (CustFundWaterMapper) getDao();
		return mapper.selectAllPage(parasMap, info);
	}

	@Override
	public List<CustFundWater> getTotalData(HashMap<String, Object> parasMap) {
		CustFundWaterMapper mapper = (CustFundWaterMapper) getDao();
		return mapper.getTotalData(parasMap);
	}

	@Override
	public PageList<CustFundWater> selectAccountTotalDetailsById(String pid,
			String flag, PageInfo info) {
		CustFundWaterMapper mapper = (CustFundWaterMapper) getDao();
		CustFundWater custFundWater = new CustFundWater();
		custFundWater.setCustomerId(pid);
		if (flag != null) {
			custFundWater.setFlag(flag);
		}
		return mapper.selectAccountTotalDetailsById(custFundWater, info);
	}

	@Override
	public PageList<CustFundWater> selectAllPages(CustFundWater custFundWater,
			PageInfo pageInfo) {
		CustFundWaterMapper mapper = (CustFundWaterMapper) getDao();
		PageList<CustFundWater> tbjl = mapper.selectAllPages(custFundWater,
				pageInfo);
		if(tbjl !=null && tbjl.size()>0){
			String expenditure=null;
			for(int i=0;i<tbjl.size();i++){
				String fundValue = tbjl.get(i).getExpenditure().toString();
				expenditure="-"+ fundValue;
				tbjl.get(i).setExpenditure(new BigDecimal(expenditure));
			}
		}
		//统计资金流水总额
		Map<String, BigDecimal> map = mapper.selectAllPagesSum(custFundWater);
		CustFundWater custFundWater2 = new CustFundWater();
		if (map != null) {
			custFundWater2.setFundValue(map.get("fundValue"));
			//获取支出余额
			String expenditures = map.get("exPenditure").toString();
			String expenditure = "-"+expenditures;
			custFundWater2.setExpenditure(new BigDecimal(expenditure));
			tbjl.add(custFundWater2);
		}
		return tbjl;
	}

	@Override
	public PageList<CustFundWater> selectTouziDetailsById(String pid,
			PageInfo info) {
		CustFundWaterMapper mapper = (CustFundWaterMapper) getDao();
		CustFundWater custFundWater = new CustFundWater();
		custFundWater.setCustomerId(pid);
		return mapper.selectTouziDetailsById(custFundWater, info);
	}

	@Override
	public PageList<CustFundWater> selectWaterType(CustFundWater custFundWater,
			PageInfo info) {
		CustFundWaterMapper mapper = (CustFundWaterMapper) getDao();
		return mapper.selectWaterType(custFundWater, info);
	}

	@Override
	public PageList<CustFundWater> selectZiJinWater(String pid, PageInfo info) {
		CustFundWaterMapper mapper = (CustFundWaterMapper) getDao();
		CustFundWater custFundWater = new CustFundWater();
		custFundWater.setCustomerId(pid);
		return mapper.selectZiJinWater(custFundWater, info);
	}

	@Override
	public PageList<CustFundWater> selectZiJinWaterAllPage(
			CustFundWater custFundWater, PageInfo info) {
		CustFundWaterMapper mapper = (CustFundWaterMapper) getDao();
		// 修改适用开始时间格式-begin
		if (null != custFundWater.getHappenBeginTime()
				&& !"".equals(custFundWater.getHappenBeginTime())) {
			custFundWater.setHappenBeginTime(EscfDateUtil.formatterDate(
					custFundWater.getHappenBeginTime(), 1));
		}
		// 修改适用开始时间格式-end
		if (null != custFundWater.getHappenEndTime()
				&& !"".equals(custFundWater.getHappenEndTime())) {
			custFundWater.setHappenEndTime(EscfDateUtil.formatterDate(
					custFundWater.getHappenEndTime(), 2));
		}
		PageList<CustFundWater> pls = null;
			pls = mapper.selectZiJinWater(custFundWater, info);
			String expenditure="0";//支出
			String fundValues="0";//收入
			if(pls!=null && pls.size()>0){
				for(int i=0;i<pls.size();i++){
					Integer  businessType =Integer.parseInt(pls.get(i).getBusinessTypeValue());
					BigDecimal fundValue =pls.get(i).getFundValue();//收入
					if(businessType %2==0){//支出
						expenditure = "-" + fundValue;
						pls.get(i).setExpenditure(new BigDecimal(expenditure));
						pls.get(i).setFundValue(new BigDecimal(fundValues));
					}else{
						pls.get(i).setFundValue(fundValue);//收入
						pls.get(i).setExpenditure(new BigDecimal(fundValues));//支出
					}
				}
			}
			//统计总额
			Map<String, BigDecimal> map = mapper.selectZiJinWaterSum(custFundWater);
			CustFundWater cfw = new CustFundWater();
			if(!"".equals(map) && null !=map){
				 cfw.setFundValue(map.get("fundValue"));
				 expenditure = map.get("exPenditure").toString();
				 String totalExpenditure = "-" + expenditure;
				 cfw.setExpenditure(new BigDecimal(totalExpenditure));
				 pls.add(cfw);
			}
		return pls;
	}

	@Override
	public List<CustFundWaterModel> selectCustFundWaterVOEom(
			CustFundWaterModel custFundWaterVO, ExportObjectModel eom) {
		CustFundWaterMapper mapper = (CustFundWaterMapper) getDao();
		HashMap<String, Object> map = new HashMap<String, Object>();
		map.put("parasMap", custFundWaterVO);
		map.put("expm", eom);
		List<CustFundWater> elist = mapper.selectCustFundWaterEom(map);
		List<CustFundWaterModel> elistVo = new ArrayList<CustFundWaterModel>();

		BigDecimal available = new BigDecimal(0.0);
		BigDecimal income = new BigDecimal(0.0);
		BigDecimal expend =  new BigDecimal(0.0);

		for (CustFundWater cfw : elist) {
			if (cfw == null) {
				continue;
			}
			CustFundWaterModel vo = new CustFundWaterModel();
			if (cfw.getCustomer() != null) {
				vo.setCustomerName(cfw.getCustomer().getCustomerName());
				vo.setSname(cfw.getCustomer().getSname());
				vo.setPhoneNo(cfw.getCustomer().getPhoneNo());
			}
			vo.setAccountBalance(cfw.getAccountBalance());
			vo.setBusinessType(cfw.getBusinessType());
			vo.setExpend(vo.getExpend(cfw.getBusinessType(),
					cfw.getFundValue(), cfw.getTotalPay()));
			vo.setFundType(cfw.getFundType());
			vo.setFundTypeName(vo.getFundNameByType(cfw.getFundType()));
			vo.setFunWatDesc(cfw.getFunWatDesc());
			vo.setHappenTime(cfw.getHappenTime());
			vo.setIncome(vo.getIncome(cfw.getBusinessType(),
					cfw.getFundValue(), cfw.getTotalIncome()));

			available = available.add(vo.getAccountBalance() == null ? new BigDecimal(0) : vo.getAccountBalance());
			income = income.add(vo.getIncome() == null ? new BigDecimal(0) : vo.getIncome());
			expend = expend.add(vo.getExpend() == null ? new BigDecimal(0) : vo.getExpend());

			elistVo.add(vo);
		}

		CustFundWaterModel vo = new CustFundWaterModel();
		vo.setCustomerName(Constant.AGGREGATE_STRING);
		vo.setIncome(income);
		vo.setExpend(expend);
		vo.setAccountBalance(available);
		elistVo.add(vo);
		
		return elistVo;
	}
	@Override
	public List<CustFundWater> selectZiJinWater(HashMap<String, Object> map) {
		CustFundWaterMapper mapper = (CustFundWaterMapper) getDao();
		String beginTime = map.get("beginTime").toString();
		String endTime = map.get("endTime").toString();
		if(!"".equals(beginTime) && beginTime!=null){
			map.put("beginTime", EscfDateUtil.formatterDate(beginTime, 1));
		}
		if(!"".equals(endTime) && endTime!=null){
			map.put("endTime", EscfDateUtil.formatterDate(endTime, 2));
		}
		return mapper.selectWater(map);
	}



	@Override
	public CustFundWater addFundWater(String userId, String fundWateType,String fundtallyType,
		BigDecimal amount, BigDecimal balance, String note, Date time,String fk) throws FrameworkException {

		if(!StringUtils.hasText(userId)){
			throw new RuntimeException("流水对应的用户ID错误，userId="+userId);
		}
		
		if(!StringUtils.hasText(fundtallyType)){
			throw new RuntimeException("流水类型错误，fundWaterType="+fundtallyType);
		}
		
		if(amount ==null || amount.compareTo(BigDecimal.ZERO)<=0){
			throw new RuntimeException("流水发生金额错误,amount="+amount);
		}
		
		if(balance == null){
			throw new RuntimeException("账户余额不能为null");
		}
		
		CustFundWater fundWater = new CustFundWater();
		fundWater.setPid(fundWater.getPK());
		fundWater.setHappenTime(DateUtil.format(time));
		fundWater.setCreateTime(DateUtil.format(time));
		fundWater.setCustomerId(userId);
		fundWater.setFundType(fundWateType);
		fundWater.setFundValue(amount);
		fundWater.setAccountBalance(balance);
		fundWater.setBusinessType(fundtallyType);
		fundWater.setFkey(fk);
		fundWater.setCreateUser(userId);
		fundWater.setVersion(Constant.PUBLIC_VERSION);
		if(StringUtils.hasText(note)){
			fundWater.setFunWatDesc(note);
		}
		CustFundWaterMapper mapper = (CustFundWaterMapper)this.getDao();
		mapper.insertSelective(fundWater);
		return fundWater;
	}

	@Override
	public List<CustFundWater> selectCapitalFlow(String userId) {
		CustFundWaterMapper mapper = (CustFundWaterMapper)this.getDao();
		List<CustFundWater> pls=null;
		pls = mapper.selectCapitalFlow(userId);
		String expenditure="0";//支出
		String fundValues="0";//收入
		if(pls!=null && pls.size()>0){
			for(int i=0;i<pls.size();i++){
				Integer  businessType =Integer.parseInt(pls.get(i).getBusinessTypeValue());
				BigDecimal fundValue =pls.get(i).getFundValue();//收入
				if(businessType %2==0){//支出
					expenditure = "-" + fundValue;
					pls.get(i).setExpenditure(new BigDecimal(expenditure));
					pls.get(i).setFundValue(new BigDecimal(fundValues));
				}else{
					pls.get(i).setFundValue(fundValue);//收入
					pls.get(i).setExpenditure(new BigDecimal(fundValues));//支出
				}
			}
		}
		return pls;                                                                                                        
	}

	@Override
	public List<CustFundWater> addFundWaters(List<String> userIds, String fundWateType,
			String fundtallyType, BigDecimal amount,
			String note, Date time, String fk) {
		if(!(userIds.size()>0)){
			throw new RuntimeException("流水对应的用户ID错误，userIds="+userIds);
		}
		
		if(!StringUtils.hasText(fundtallyType)){
			throw new RuntimeException("流水类型错误，fundtallyType="+fundtallyType);
		}
		
		if(amount ==null || amount.compareTo(BigDecimal.ZERO)<=0){
			throw new RuntimeException("流水发生金额错误,amount="+amount);
		}
		CustFundWaterMapper mapper = (CustFundWaterMapper)this.getDao();
		List<CustFundWater> custFWs = new ArrayList<CustFundWater>(); 
		for (String userId : userIds) {
			CustFundWater custFW = new CustFundWater();
			custFW.setPid(custFW.getPK());
			custFW.setCustomerId(userId);
			custFW.setFundValue(amount);
			custFW.setFundType(fundWateType);
			custFW.setBusinessType(fundtallyType);
			custFW.setAccountBalance(customerAccountService.getUserBalanceAmountExculdExperience(userId));
			custFW.setHappenTime(DateUtil.format(time));
			custFW.setFkey(fk);
			custFW.setCreateTime(DateUtil.format(time));
			custFW.setStatus(Constant.PUBLIC_STATUS);
			if(StringUtils.hasText(note)){
				custFW.setFunWatDesc(note);
			}
			custFWs.add(custFW);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("custFWs", custFWs);
		mapper.addFundWaters(map);
		return custFWs;
	}

	@Override
	public List<CustFundWater> addFundWaters(Map<String, Object> maps,
			String fundWateType, String fundtallyType, String note, Date time,
			String fk) {
		if(!StringUtils.hasText(fundtallyType)){
			throw new RuntimeException("流水类型错误，fundtallyType="+fundtallyType);
		}
		CustFundWaterMapper mapper = (CustFundWaterMapper)this.getDao();
		List<CustFundWater> custFWs = new ArrayList<CustFundWater>();
		for (Entry<String, Object> en : maps.entrySet()) {
			CustFundWater custFW = new CustFundWater();
			custFW.setPid(custFW.getPK());
			custFW.setCustomerId(en.getKey());
			custFW.setFundValue((BigDecimal)(en.getValue()));
			custFW.setFundType(fundWateType);
			custFW.setBusinessType(fundtallyType);
			custFW.setAccountBalance(customerAccountService.getUserBalanceAmountExculdExperience(en.getKey()));
			custFW.setHappenTime(DateUtil.format(time));
			custFW.setFkey(fk);
			custFW.setCreateTime(DateUtil.format(time));
			custFW.setStatus(Constant.PUBLIC_STATUS);
			if(StringUtils.hasText(note)){
				custFW.setFunWatDesc(note);
			}
			custFWs.add(custFW);
		}
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("custFWs", custFWs);
		mapper.addFundWaters(map);
		return custFWs;
	}
	
}
