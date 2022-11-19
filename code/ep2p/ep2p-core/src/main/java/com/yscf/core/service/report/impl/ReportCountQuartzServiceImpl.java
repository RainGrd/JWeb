/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 报表统计信息服务
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年12月23日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.report.impl;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.service.impl.BaseService;
import com.yscf.core.dao.task.ReportCountQuartzMapper;
import com.yscf.core.model.task.AlsoPrincipalReport;
import com.yscf.core.model.task.CumulativeIinvestReport;
import com.yscf.core.model.task.GrantProfitReport;
import com.yscf.core.model.task.UserBenefitReport;
import com.yscf.core.service.report.IReportCountQuartzService;

/**
 * Description：<br> 
 * 报表统计信息服务
 * @author  Lin Xu
 * @date    2015年12月23日
 * @version v1.0.0
 */
@Service("reportCountQuartzServiceImpl")
public class ReportCountQuartzServiceImpl extends BaseService<BaseEntity, String> implements IReportCountQuartzService {

	Logger logger = Logger.getLogger(ReportCountQuartzServiceImpl.class);
	
	@Autowired
	public ReportCountQuartzServiceImpl(ReportCountQuartzMapper dao) {
		super(dao);
	}

	/**
	 * 累计投资报表信息服务
	 */
	@Override
	public void cumulativeInvestReportService() {
		ReportCountQuartzMapper reportMapper = (ReportCountQuartzMapper) getDao();
		try {
			//需要插入的信息数据
			List<HashMap<String, Object>> thisList = reportMapper.selectThisAccumulReport();
			if(null != thisList && thisList.size() > 0){
				for(HashMap<String, Object> tjmap : thisList){
					//1.查看信息是否存在,若存在则删除
					String ayears = (String) tjmap.get("ayear");
					String amonths = (String)tjmap.get("amonth");
					List<HashMap<String, Object>> qumaplist = reportMapper.selectAccumulatedInvestReport(ayears, amonths);
					if(null != qumaplist && qumaplist.size() > 0){
						reportMapper.deleteAccumulatedInvestReport(ayears, amonths);
					}
					//2.插入操作数据信息
					CumulativeIinvestReport cumireportModel = new CumulativeIinvestReport();
					cumireportModel.setPid(cumireportModel.getPK());
					cumireportModel.setAmount((BigDecimal) tjmap.get("amount"));
					cumireportModel.setAyear(ayears);
					cumireportModel.setAmonth(amonths);
					cumireportModel.setCreateUser("admin");
					reportMapper.insertAccumulatedInvestReport(cumireportModel);
				}
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("==>cumulativeInvestReportService:"+ e.getMessage());
			}
		}
	}

	/**
	 * 已还本息报表统计服务
	 */
	@Override
	public void alsoPrincipalReportService() {
		ReportCountQuartzMapper reportMapper = (ReportCountQuartzMapper) getDao();
		try {
			//需要插入的信息数据
			List<HashMap<String, Object>> thisList = reportMapper.selectThisAlsoPrincipalReport();
			if(null != thisList && thisList.size() > 0){
				for(HashMap<String, Object> tjmap : thisList){
					//1.查看信息是否存在,若存在则删除
					String ayears = (String) tjmap.get("ayear");
					String amonths = (String)tjmap.get("amonth");
					List<HashMap<String, Object>> qumaplist = reportMapper.selectAlsoPrincipalReport(ayears, amonths);
					if(null != qumaplist && qumaplist.size() > 0){
						reportMapper.deleteAlsoPrincipalReport(ayears, amonths);
					}
					//2.插入操作数据信息
					AlsoPrincipalReport alsoreportModel = new AlsoPrincipalReport();
					alsoreportModel.setPid(alsoreportModel.getPK());
					alsoreportModel.setAmount((BigDecimal) tjmap.get("amount"));
					alsoreportModel.setAyear(ayears);
					alsoreportModel.setAmonth(amonths);
					reportMapper.insertAlsoPrincipalReport(alsoreportModel);
				}
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("==>cumulativeInvestReportService:"+ e.getMessage());
			}
		}
	}
	
	/**
	 * 累计用户收益统计服务
	 */
	@Override
	public void userBenefitReportService(){
		ReportCountQuartzMapper reportMapper = (ReportCountQuartzMapper) getDao();
		try {
			//需要插入的信息数据
			List<HashMap<String, Object>> thisList = reportMapper.selectThisUserBenefitReport();
			if(null != thisList && thisList.size() > 0){
				for(HashMap<String, Object> tjmap : thisList){
					//1.查看信息是否存在,若存在则删除
					String ayears = (String) tjmap.get("ayear");
					String amonths = (String)tjmap.get("amonth");
					List<HashMap<String, Object>> qumaplist = reportMapper.selectUserBenefitReport(ayears, amonths);
					if(null != qumaplist && qumaplist.size() > 0){
						reportMapper.deleteUserBenefitReport(ayears, amonths);
					}
					//2.插入操作数据信息
					UserBenefitReport userreportModel = new UserBenefitReport();
					userreportModel.setPid(userreportModel.getPK());
					userreportModel.setAmount((BigDecimal)tjmap.get("amount"));
					userreportModel.setUseravg((BigDecimal)tjmap.get("useravg"));
					userreportModel.setAyear(ayears);
					userreportModel.setAmonth(amonths);
					reportMapper.insertUserBenefitReport(userreportModel);
				}
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("==>cumulativeInvestReportService:"+ e.getMessage());
			}
		}
	}

	/**
	 * 累计已发送收益统计服务
	 */
	@Override
	public void grantProfitReportService() {
		ReportCountQuartzMapper reportMapper = (ReportCountQuartzMapper) getDao();
		try {
			//需要插入的信息数据
			List<HashMap<String, Object>> thisList = reportMapper.selectThisGrantProfitReport();
			if(null != thisList && thisList.size() > 0){
				for(HashMap<String, Object> tjmap : thisList){
					//1.查看信息是否存在,若存在则删除
					String ayears = (String) tjmap.get("ayear");
					String amonths = (String)tjmap.get("amonth");
					List<HashMap<String, Object>> qumaplist = reportMapper.selectGrantProfitReport(ayears, amonths);
					if(null != qumaplist && qumaplist.size() > 0){
						reportMapper.deleteGrantProfitReport(ayears, amonths);
					}
					//2.插入操作数据信息
					GrantProfitReport grantreportModel = new GrantProfitReport();
					grantreportModel.setPid(grantreportModel.getPK());
					grantreportModel.setAmount((BigDecimal)tjmap.get("amount"));
					grantreportModel.setAyear(ayears);
					grantreportModel.setAmonth(amonths);
					reportMapper.insertGrantProfitReport(grantreportModel);
				}
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("==>cumulativeInvestReportService:"+ e.getMessage());
			}
		}
	}
	
	

}


