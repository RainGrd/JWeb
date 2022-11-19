/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 平台数据和备用金报表 service
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年11月4日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.report.impl;


import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.service.impl.BaseService;
import com.achievo.framework.util.StringUtil;
import com.yscf.core.dao.report.StandbyPlatReportMapper;
import com.yscf.core.model.financial.BizEnsureMoney;
import com.yscf.core.model.report.PlatFormDateModel;
import com.yscf.core.service.report.IStandbyPlatReportService;
import com.yscf.core.util.HReportUtil;

/**
 * Description：<br> 
 * 平台数据和备用金报表 service
 * @author  Lin Xu
 * @date    2015年11月4日
 * @version v1.0.0
 */
@Service("standbyPlatReportService")
public class StandbyPlatReportServiceImpl extends BaseService<BaseEntity, String> implements IStandbyPlatReportService {

	//日志信息数据
	Logger logger = Logger.getLogger(StandbyPlatReportServiceImpl.class);
	
	@Autowired
	public StandbyPlatReportServiceImpl(StandbyPlatReportMapper dao) {
		super(dao);
	}

	@Override
	public HashMap<String, Object> selectAllTotalAmount(PlatFormDateModel pfdm) {
		
		StandbyPlatReportMapper mapper = (StandbyPlatReportMapper) getDao();
		//累计投资
		BigDecimal cumulativei = mapper.selectCumulativeInvest(pfdm) == null ? new BigDecimal(0) : mapper.selectCumulativeInvest(pfdm);
		//已还本息
		BigDecimal alsoprincipal = mapper.selectAlsoPrincipal(pfdm) == null ? new BigDecimal(0) : mapper.selectAlsoPrincipal(pfdm);
		//累计用户受益
		BigDecimal userbenefit = mapper.selectUserBenefit(pfdm) == null ? new BigDecimal(0) : mapper.selectUserBenefit(pfdm);
		//已发放收益
		BigDecimal grantprofit = mapper.selectGrantProfit(pfdm) == null ? new BigDecimal(0) : mapper.selectGrantProfit(pfdm);
		
		//返回结果集
		HashMap<String, Object> tatolreslut = new HashMap<String, Object>();
		tatolreslut.put("cumulativei", cumulativei);
		tatolreslut.put("alsoprincipal", alsoprincipal);
		tatolreslut.put("userbenefit", userbenefit);
		tatolreslut.put("grantprofit", grantprofit);
		
		return tatolreslut;
	}

	
	@Override
	public BigDecimal[] selectPlatLoadReport(PlatFormDateModel pfdm) {
		BigDecimal[] reslutD = new BigDecimal[12];
		StandbyPlatReportMapper mapper = (StandbyPlatReportMapper) getDao();
		try {
			//获取结果集
			if(null != pfdm){
				List<PlatFormDateModel> pflist = new ArrayList<PlatFormDateModel>();
				if("1".equals(pfdm.getType())){
					pflist = mapper.selectCumulativeInvList(pfdm);
				}
				if("2".equals(pfdm.getType())){
					pflist = mapper.selectAlsoPrincipalList(pfdm);
				}
				if("3".equals(pfdm.getType())){
					pflist = mapper.selectUserBenefitList(pfdm);
				}
				if("4".equals(pfdm.getType())){
					pflist = mapper.selectGrantProfitList(pfdm);
				}
				//初始化信息
				String[] key = HReportUtil.comMonth();
				BigDecimal dfdoubl = new BigDecimal(0);
				for(int i = 0 ; i < key.length ; i++){
					if(null != pflist && pflist.size() > 0){
						for(PlatFormDateModel m : pflist){
							String mh = m.getMonth();
							if(StringUtil.isNotEmpty(mh) && mh.equals(key[i]) && null != m.getAmountMoney()){
								dfdoubl = m.getAmountMoney();
								break;
							}else{
								dfdoubl = new BigDecimal(0);
							}
						}
					}else{
						dfdoubl = new BigDecimal(0);
					}
					//设置值
					reslutD[i] = dfdoubl;
				}
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
		}
		return reslutD;
	}

	@Override
	public BigDecimal selectYearAllMoney(PlatFormDateModel pfdm) {
		StandbyPlatReportMapper mapper = (StandbyPlatReportMapper) getDao();
		BigDecimal yBigDecimal = new BigDecimal(0);
		try{
			if("1".equals(pfdm.getType())){
				//累计投资
				yBigDecimal = mapper.selectCumulativeInvest(pfdm) == null ? new BigDecimal(0) : mapper.selectCumulativeInvest(pfdm);
			}
			if("2".equals(pfdm.getType())){
				//已还本息
				yBigDecimal = mapper.selectAlsoPrincipal(pfdm) == null ? new BigDecimal(0) : mapper.selectAlsoPrincipal(pfdm);
			}
			if("3".equals(pfdm.getType())){
				//累计用户受益
				yBigDecimal = mapper.selectUserBenefit(pfdm) == null ? new BigDecimal(0) : mapper.selectUserBenefit(pfdm);
			}
			if("4".equals(pfdm.getType())){
				//已发放收益
				yBigDecimal = mapper.selectGrantProfit(pfdm) == null ? new BigDecimal(0) : mapper.selectGrantProfit(pfdm);
			}
		}catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
		}
		return yBigDecimal;
	}

	/**
	 * 查询备用金额
	 */
	@Override
	public BizEnsureMoney selectAllEnsureMoney() {
		StandbyPlatReportMapper mapper = (StandbyPlatReportMapper) getDao();
		return mapper.selectAllEnsureMoney();
	}

	/**
	 * 查询备用金额数据信息
	 */
	@Override
	public BigDecimal[] selectGoldDepositReport(String year) {
		StandbyPlatReportMapper mapper = (StandbyPlatReportMapper) getDao();
		BigDecimal[] redol = new BigDecimal[12];
		try{
			String[] yeararray = HReportUtil.comMonth();
			for(int i = 0 ; i < yeararray.length ; i++){
				BigDecimal sval = mapper.selectGoldDepositReport(year + "-" + yeararray[i]);
				if(null != sval){
					redol[i] = sval;
				}else{
					redol[i] = new BigDecimal(0);
				}
			}
		}catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
		}
		return redol;
	}
	
	
	
	
}


