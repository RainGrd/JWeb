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
 * 2015年10月13日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.system.controller.business;

import java.util.Date;
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
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.common.util.EscfDateUtil;
import com.yscf.core.model.business.BizFundBusinessState;
import com.yscf.core.service.business.impl.BizFundBusinessStateServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;

/**
 * Description：<br> 
 * 资金交易状况表
 * @author  Yu.Zhang
 * @date    2015年10月13日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/bizFundBusinessStateController")
public class BizFundBusinessStateController  extends EscfBaseController{
	
	private Logger logger = LoggerFactory.getLogger(BizFundBusinessStateController.class);

	@Autowired
	public BizFundBusinessStateController(BizFundBusinessStateServiceImpl service) {
		super(service);
	}
	
	@Override
	public Class<?> getModel() {
		return BizFundBusinessState.class;
	}
	
	@RequestMapping(value = "/selectAll")
	@ResponseBody
	public ModelAndView selectAll(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			BizFundBusinessStateServiceImpl service = (BizFundBusinessStateServiceImpl)getService();
			BizFundBusinessState info = new BizFundBusinessState();
			
			Date date = new Date();//获取当前时间 

			info.setEndTime(EscfDateUtil.formatterDate(date, 2));
			// 获取向后推6天的日期
			date=EscfDateUtil.increasedDays(new Date(), -6);
			info.setStartTime(EscfDateUtil.formatterDate(date, 1));
			List<BizFundBusinessState> pageList = service.selectAll(info);
		    modelView.addObject("rows",pageList);
		    modelView.addObject("total",pageList.size());
		    MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
 			if(logger.isDebugEnabled()){
				e.printStackTrace();
				logger.info("资金交易状况表查询异常",e.getMessage());
			}
			MessageBuilder.processError(modelView,e,request);
		}
		return modelView;
	}

}


