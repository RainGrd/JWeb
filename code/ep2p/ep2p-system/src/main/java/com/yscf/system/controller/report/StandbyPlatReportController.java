/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 平台数据和备用金报表controller
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年11月4日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.system.controller.report;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashMap;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.util.StringUtil;
import com.yscf.common.Constant.SystemParamKeyConstant;
import com.yscf.core.model.financial.BizEnsureMoney;
import com.yscf.core.model.report.PlatFormDateModel;
import com.yscf.core.model.system.SysParams;
import com.yscf.core.service.report.impl.StandbyPlatReportServiceImpl;
import com.yscf.core.service.system.impl.SysParamsServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;

/**
 * Description：<br> 
 * 平台数据和备用金报表controller
 * @author  Lin Xu
 * @date    2015年11月4日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/report/splatReportController")
public class StandbyPlatReportController extends EscfBaseController {
	
	Logger logger = Logger.getLogger(StandbyPlatReportController.class);
	
	@Resource(name="sysParamsService")
	private SysParamsServiceImpl sysParamsService;

	@Autowired
	public StandbyPlatReportController(StandbyPlatReportServiceImpl service) {
		super(service);
	}

	
	@Override
	public Class<?> getModel() {
		return PlatFormDateModel.class;
	}
	
	
	/**
	 * Description：<br> 
	 * 跳转到平台数据信息
	 * @author  Lin Xu
	 * @date    2015年11月4日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws FrameworkException 
	 */
	@RequestMapping("/toPlatformPage")
	public ModelAndView toPlatformPage(HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelv = new ModelAndView("/report/platform_data");
		try {
			StandbyPlatReportServiceImpl service = (StandbyPlatReportServiceImpl) getService();
			HashMap<String, Object> reslut = service.selectAllTotalAmount(null);
			modelv.addObject("reslut", reslut);
			//统计时间
			Date beginDate = new Date();
			modelv.addObject("yesterday", beginDate);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
		}
		return modelv;
	}
	
	/**
	 * Description：<br> 
	 * 加载平台统计数据信息
	 * @author  Lin Xu
	 * @date    2015年11月5日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @param pfdm
	 * @return
	 */
	@RequestMapping(value="/getPlatLoadReport",method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> getPlatLoadReport(HttpServletRequest request, HttpServletResponse response,PlatFormDateModel pfdm){
		HashMap<String, Object> remap = new HashMap<String, Object>();
		StandbyPlatReportServiceImpl service  = (StandbyPlatReportServiceImpl) getService();
		try {
			if(null != pfdm){
				//按月进行统计数据信息
				BigDecimal[] reData = service.selectPlatLoadReport(pfdm);
				remap.put("datas", reData);
				//统计年度总额
				remap.put("amoney", service.selectYearAllMoney(pfdm));
				processSuccess(remap, "查询成功");
			}else{
				processFail(remap, "查询失败");
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
			processError(remap,e.getMessage());
		}
		return remap;
	}
	
	
	/**
	 * Description：<br> 
	 * 备用金报表
	 * @author  Lin Xu
	 * @date    2015年11月6日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toGoldDeposit")
	public ModelAndView toGoldDeposit(HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelv = new ModelAndView("/report/golddeposit_preview");
		StandbyPlatReportServiceImpl service  = (StandbyPlatReportServiceImpl) getService();
		BizEnsureMoney bem = service.selectAllEnsureMoney();
		if(null != bem){
			modelv.addObject("bem", bem);
			//a风险预警
			SysParams av = sysParamsService.getParamsByParamKey(SystemParamKeyConstant.BIZ_ENSURE_A_RISK_WARN);
			String ayj = av.getParamValue() == null || av.getParamValue().replace(",", "").equals("") ? "0" : av.getParamValue().replace(",", "");
			BigDecimal avd  = new BigDecimal(ayj);
			SysParams bv = sysParamsService.getParamsByParamKey(SystemParamKeyConstant.BIZ_ENSURE_B_RISK_WARN);
			String byj = bv.getParamValue() == null || bv.getParamValue().replace(",", "").equals("") ? "0" : bv.getParamValue().replace(",", "");
			BigDecimal bvd = new BigDecimal(byj);
			String statusInfo = "正常";
			String status = "Z";
			if(bem.getBalance().compareTo(avd) <= 0){
				statusInfo = "A级预警";
				status = "A";
			}else if(bem.getBalance().compareTo(bvd) == 1){
				statusInfo = "正常";
				status = "Z";
			}else{
				statusInfo = "B级预警";
				status = "B";
			}
			modelv.addObject("statusInfo", statusInfo);
			modelv.addObject("satatus", status);
		}
		return modelv;
	}
	
	/**
	 * Description：<br> 
	 * 备用金报表数据
	 * @author  Lin Xu
	 * @date    2015年11月6日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @param year
	 * @return
	 */
	@RequestMapping(value="/getGoldDepositReport",method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> getGoldDepositReport(HttpServletRequest request, HttpServletResponse response,String	year){
		HashMap<String, Object> remap = new HashMap<String, Object>();
		StandbyPlatReportServiceImpl service  = (StandbyPlatReportServiceImpl) getService();
		try {
			if(StringUtil.isNotEmpty(year)){
				//按月进行统计数据信息
				BigDecimal[] reData = service.selectGoldDepositReport(year);
				remap.put("datas", reData);
				//统计年度总额
				processSuccess(remap, "查询成功");
			}else{
				processFail(remap, "查询失败");
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
			processError(remap,e.getMessage());
		}
		return remap;
	}
	
	
}


