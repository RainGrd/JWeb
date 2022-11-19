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
package com.yscf.api.controller.otherinfo;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.util.DateUtil;
import com.achievo.framework.vo.JsonObject;
import com.yscf.api.common.ApiCode;
import com.yscf.api.controller.base.EscfBaseApi;
import com.yscf.api.util.ApiUtil;
import com.yscf.core.model.business.CalculationDto;
import com.yscf.core.service.calculation.CalculationFactory;
import com.yscf.core.service.calculation.ICalculation;
import com.yscf.core.service.user.impl.LoginEptpServiceImpl;

/**
 * 
 * Description：<br> 
 * 投资计算器API控制
 * @author  Yu.Zhang
 * @date    2015年11月13日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/otherinfo/calculatorApi")
public class CalculatorApi extends EscfBaseApi {
	
	@Autowired
	public CalculatorApi(LoginEptpServiceImpl service) {
		super(service);
	}

	private Logger logger = LoggerFactory.getLogger(CalculatorApi.class);
	
	
	/**
	 * 
	 * Description：<br> 
	 * 收益计算器 APP接口
	 * @author  Yu.Zhang
	 * @date    2015年12月28日
	 * @version v1.0.0
	 * @param repaymentAmt 借款金额
	 * @param repaymentRate	  年化率
	 * @param repaymentDeadline  期限
	 * @param repaymentType		还款方式
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/execCalculator",method=RequestMethod.POST)
	@ResponseBody
	public String execCalculator(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		try {
			
			CalculationDto dto =(CalculationDto) ApiUtil.convertObjectByBody(request, CalculationDto.class);
			
			dto.setInterestDate(DateUtil.format(DateUtil.getToday()));
			ICalculation  calculation = CalculationFactory.getCalculation(dto);
			
			map.put("interest", calculation.getSumInterest());

			jsonObject.setResult(map);
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"操作成功",true);
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("执行投资计算失败"+e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"操作失败",true);
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
}


