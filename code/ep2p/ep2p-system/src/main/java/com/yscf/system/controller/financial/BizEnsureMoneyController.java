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
 * 2015年10月20日     jenkin.yu		Initial Version.
 *************************************************************************
 */
package com.yscf.system.controller.financial;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.util.DateUtil;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.common.Constant.Constant;
import com.yscf.common.Constant.TradeTypeConstant;
import com.yscf.core.model.financial.BizEnsureMoney;
import com.yscf.core.model.financial.BizEnsureMoneyParams;
import com.yscf.core.model.system.SysParams;
import com.yscf.core.service.financial.IBizEnsureMoneyService;
import com.yscf.core.service.financial.impl.BizEnsureMoneyServiceImpl;
import com.yscf.core.service.system.impl.SysParamsServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;

/**
 * Description：<br> 
 * 备付金
 * @author  jenkin.yu
 * @date    2015年10月20日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/bizEnsureMoneyController")
public class BizEnsureMoneyController extends EscfBaseController{
	
	private Logger logger = LoggerFactory.getLogger(BizEnsureMoneyController.class);

	@Autowired
	public BizEnsureMoneyController(BizEnsureMoneyServiceImpl service) {
		super(service);
	}

	@Resource(name = "sysParamsService")
	private SysParamsServiceImpl sysParamsServiceImpl;
	
	@Override
	public Class<?> getModel() {
		return BizEnsureMoney.class;
	}
	/**
	 * Description：<br> 
	 * 备付金列表
	 * @author  jenkin.yu
	 * @date    2015年10月20日
	 * @version v1.0.0
	 * @return
	 */
	@RequestMapping("/bizEnsureMoneyIndex")
	public ModelAndView bizEnsureMoneyIndex() {
		return new ModelAndView("/financial/bizEnsureMoney/toList");
	}
	
	/**
	 * Description：<br> 
	 * 获取备付金基本参数
	 * @author  jenkin.yu
	 * @date    2015年10月21日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @throws HttpRequestException
	 */
	@RequestMapping("/bizEnsureRateParam")
	@ResponseBody
	public ModelAndView bizEnsureRateParam(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException{
		Map<String, Object> tojson = new HashMap<String, Object>();
		ModelAndView modelView = new ModelAndView();
		try {
			PageInfo page = new PageInfo();
			page.setLimit(Integer.MAX_VALUE);
			PageList<SysParams> params = sysParamsServiceImpl.searchParamsByKeyOrVal(new SysParams(),page);
			if(params.size() > 0){
				for(SysParams param : params){
					if(param.getParamKey().equals(Constant.BIZ_ENSURE_CAPITAL_PENALTY_RATE)){
						tojson.put(Constant.BIZ_ENSURE_CAPITAL_PENALTY_RATE, param.getParamValue());
					}
					if(param.getParamKey().equals(Constant.BIZ_ENSURE_INTEREST_PENALTY_RATE)){
						tojson.put(Constant.BIZ_ENSURE_INTEREST_PENALTY_RATE, param.getParamValue());
					}
					if(param.getParamKey().equals(Constant.BIZ_ENSURE_MANAGE_RATE)){
						tojson.put(Constant.BIZ_ENSURE_MANAGE_RATE, param.getParamValue());
					}
					if(param.getParamKey().equals(Constant.BIZ_ENSURE_RATE_OF_CALL)){
						tojson.put(Constant.BIZ_ENSURE_RATE_OF_CALL, param.getParamValue());
					}
					if(param.getParamKey().equals(Constant.BIZ_ENSURE_A_RISK_WARN)){
						tojson.put(Constant.BIZ_ENSURE_A_RISK_WARN, param.getParamValue());
					}
					if(param.getParamKey().equals(Constant.BIZ_ENSURE_B_RISK_WARN)){
						tojson.put(Constant.BIZ_ENSURE_B_RISK_WARN, param.getParamValue());
					}
				}
			}
			List<Map<String, Object>> lists = new ArrayList<Map<String,Object>>();
			lists.add(tojson);
			modelView.addObject("rows", lists);
			modelView.addObject("total", lists.size());
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (FrameworkException e) {
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
		}
		return modelView;
	}
	
	/**
	 * Description：<br> 
	 * 保存备付金基本参数
	 * @author  jenkin.yu
	 * @date    2015年10月22日
	 * @version v1.0.0
	 * @param params
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping("/updateBizEnsureRateParam")
	@ResponseBody
	public ModelAndView saveBizEnsureParams(BizEnsureMoneyParams params, HttpServletRequest request, HttpServletResponse response) throws HttpRequestException{
		ModelAndView modelView = new ModelAndView();
		try {
			Map<String, String> bizParams = Constant.getBizEnsureParamsKey();
			SysParams params1 = sysParamsServiceImpl.getParamsByParamKey(bizParams.get("bizEnsureCapitalPenaltyRate"));
//			SysParams params2 = sysParamsServiceImpl.getParamsByParamKey(bizParams.get("bizEnsureInterestPenaltyRate"));
			SysParams params3 = sysParamsServiceImpl.getParamsByParamKey(bizParams.get("bizEnsureManageRate"));
			SysParams params4 = sysParamsServiceImpl.getParamsByParamKey(bizParams.get("bizEnsureRateOfCall"));
			SysParams params5 = sysParamsServiceImpl.getParamsByParamKey(bizParams.get("bizEnsureBRiskWarn"));
			SysParams params6 = sysParamsServiceImpl.getParamsByParamKey(bizParams.get("bizEnsureARiskWarn"));
			if (!params1.getParamValue().equals(params.getBizEnsureCapitalPenaltyRate())) {
				params1.setParamValue(params.getBizEnsureCapitalPenaltyRate());
				sysParamsServiceImpl.updateParamByPid(params1);
			}
//			if (!params2.getParamValue().equals(params.getBizEnsureInterestPenaltyRate())) {
//				params2.setParamValue(params.getBizEnsureInterestPenaltyRate());
//				sysParamsServiceImpl.updateParamByPid(params2);
//			}
			if (!params3.getParamValue().equals(params.getBizEnsureManageRate())) {
				params3.setParamValue(params.getBizEnsureManageRate());
				sysParamsServiceImpl.updateParamByPid(params3);
			}
			if (!params4.getParamValue().equals(params.getBizEnsureRateOfCall())) {
				params4.setParamValue(params.getBizEnsureRateOfCall());
				sysParamsServiceImpl.updateParamByPid(params4);
			}
			if (!params5.getParamValue().equals(params.getBizEnsureBRiskWarn())) {
				params5.setParamValue(params.getBizEnsureBRiskWarn());
				sysParamsServiceImpl.updateParamByPid(params5);
			}
			if (!params6.getParamValue().equals(params.getBizEnsureARiskWarn())) {
				params6.setParamValue(params.getBizEnsureARiskWarn());
				sysParamsServiceImpl.updateParamByPid(params6);
			}
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		}catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
		
	}
	
	
	/**
	 * 
	 * Description：<br> 
	 * 跳转至备付金调整页面
	 * @author  Yu.Zhang
	 * @date    2015年10月28日
	 * @version v1.0.0
	 * @param type
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping("/toEnsureAdjustment")
	@ResponseBody
	public ModelAndView toEnsureAdjustment(String type,HttpServletRequest request, HttpServletResponse response) throws HttpRequestException{
		ModelAndView modelView = new ModelAndView("financial/bizEnsureMoney/ensure_adjustment");
		modelView.addObject("type", type);
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 保存备付金调整
	 * @author  Yu.Zhang
	 * @date    2015年10月28日
	 * @version v1.0.0
	 * @param type
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping("/saveEnsureAdjustment")
	@ResponseBody
	public ModelAndView saveEnsureAdjustment(BizEnsureMoney bizEnsureMoney,HttpServletRequest request, HttpServletResponse response) throws HttpRequestException{
		IBizEnsureMoneyService service = (BizEnsureMoneyServiceImpl) getService();
		ModelAndView modelView = new ModelAndView();
		try {
			bizEnsureMoney.setCreateTime(DateUtil.getToday());
			bizEnsureMoney.setCreateUser(getUserId());
			bizEnsureMoney.setLastUpdateTime(DateUtil.getToday());
			bizEnsureMoney.setLastUpdateUser(getUserId());
			if(TradeTypeConstant.RISK_TRADE_TYPE_2007.equals(bizEnsureMoney.getEnsureType())){	// 初始化备付金
				service.saveEnsureAdjustment(bizEnsureMoney);
			}else if(TradeTypeConstant.RISK_TRADE_TYPE_2009.equals(bizEnsureMoney.getEnsureType())){// 调增备付金
				service.updateAddProvisions(bizEnsureMoney.getAmount(), getUserId(), TradeTypeConstant.RISK_TRADE_TYPE_2009, null,bizEnsureMoney.getEnsMonDetDesc());
			}else if(TradeTypeConstant.RISK_TRADE_TYPE_2010.equals(bizEnsureMoney.getEnsureType())){	// 调减备付金
				service.updateSubProvisions(bizEnsureMoney.getAmount(), getUserId(), TradeTypeConstant.RISK_TRADE_TYPE_2010, null,bizEnsureMoney.getEnsMonDetDesc());
			}
			MessageBuilder.processSuccess(modelView, null,HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			MessageBuilder.processError(modelView, e, request);
			if (logger.isDebugEnabled()) {
				logger.info("编辑保存  理财产品介绍  失败", e.getMessage());
				e.printStackTrace();
			}
		}
		return modelView;
	}
}


