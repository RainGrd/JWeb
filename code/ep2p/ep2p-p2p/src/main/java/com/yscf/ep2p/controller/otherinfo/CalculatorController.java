/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 帮助中心控制层
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年11月12日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.ep2p.controller.otherinfo;

import java.math.BigDecimal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.util.DateUtil;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.core.model.business.BizReceiptPlan;
import com.yscf.core.model.business.CalculationDto;
import com.yscf.core.model.content.ColumnContent;
import com.yscf.core.service.calculation.CalculationFactory;
import com.yscf.core.service.calculation.ICalculation;
import com.yscf.core.service.content.impl.ColumnContentServiceImpl;
import com.yscf.ep2p.controller.base.EscfBaseWebController;

/**
 * 
 * Description：<br> 
 * 投资计算器控制层
 * @author  Yu.Zhang
 * @date    2015年11月13日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/help/calculatorController")
public class CalculatorController extends EscfBaseWebController {
	
	private Logger logger = LoggerFactory.getLogger(CalculatorController.class);

	@Autowired
	public CalculatorController(ColumnContentServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return ColumnContent.class;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 跳转到投资计算器页面
	 * @author  Yu.Zhang
	 * @date    2015年11月13日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toCalculatorPage")
	@ResponseBody
	public ModelAndView toCalculatorPage(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		return new ModelAndView("helpcenter/calculator");
	}
	
	
	/**
	 * 
	 * Description：<br> 
	 * 执行投资计算
	 * @author  Yu.Zhang
	 * @date    2015年11月13日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/execCalculator")
	@ResponseBody
	public ModelAndView execCalculator(CalculationDto dto,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			dto.setInterestDate(DateUtil.format(DateUtil.getToday()));
			dto.setManagementRate(new BigDecimal(1.6));
			ICalculation  calculation = CalculationFactory.getCalculation(dto);
			List <BizReceiptPlan> list = calculation.execReceivablesCalc();
			modelView.addObject("data", list);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			e.printStackTrace();
			if(logger.isDebugEnabled()){
				logger.debug("执行投资计算失败"+e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
}


