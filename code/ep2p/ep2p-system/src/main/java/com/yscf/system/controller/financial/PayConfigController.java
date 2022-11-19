/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 充值渠道
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2016年1月13日     Jie.Zou		Initial Version.
 *************************************************************************
 */
package com.yscf.system.controller.financial;

import java.util.ArrayList;
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
import com.yscf.core.model.financial.recharge.PayConfig;
import com.yscf.core.service.financial.impl.PayConfigServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;

/**
 * Description：<br> 
 * 充值渠道
 * @author  Jie.Zou
 * @date    2016年1月13日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/payConfigController")
public class PayConfigController extends EscfBaseController {

	private Logger logger = LoggerFactory.getLogger(PayConfigController.class);
	
	@Autowired
	public PayConfigController(PayConfigServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return PayConfig.class;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 查询所有有效的充值渠道（支付平台）
	 * @author  Jie.Zou
	 * @date    2016年1月13日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/selectComboboxAll")
	@ResponseBody
	public ModelAndView selectComboboxAll(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView view = new ModelAndView();
		PayConfigServiceImpl payConfigServiceImpl = (PayConfigServiceImpl)getService();
		List<PayConfig> list = payConfigServiceImpl.selectAll();
		List<PayConfig> payConfigs = new ArrayList<PayConfig>();
		PayConfig payConfig = new PayConfig();
		payConfig.setPayName("全部");
		payConfigs.add(payConfig);
		payConfigs.addAll(list);
		MessageBuilder.processSuccess(view, payConfigs, HttpMessage.SUCCESS_CODE,
				request);
		return view;
	}
}


