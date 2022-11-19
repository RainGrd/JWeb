/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 连连支付充值通知Controller
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年12月10日     Jie.Zou		Initial Version.
 *************************************************************************
 */
package com.yscf.ep2p.controller.financial.recharge.online.llpay;

import java.io.IOException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.yscf.common.Constant.Constant;
import com.yscf.core.service.financial.impl.BizRechargeOnlineServiceImpl;
import com.yscf.ep2p.controller.financial.recharge.online.PayServletController;


/**
 * Description：<br> 
 * 连连支付充值通知Controller
 * @author  Jie.Zou
 * @date    2015年12月10日
 * @version v1.0.0
 */
@Controller
@RequestMapping("recharge/llpayReturn")
public class LlpayControllerReturn extends PayServletController {
	
	private final Logger logger = LoggerFactory.getLogger("ll");
	
	@Autowired
	public LlpayControllerReturn(BizRechargeOnlineServiceImpl service) {
		super(service);
	}

	@Override
	protected String getPaySystemType() {
		return Constant.LL_PAY;
	}
	
	@Override
	protected Logger getLogger() {
		return logger;
	}

	@Override
	protected void postHandle(HttpServletRequest req, HttpServletResponse resp,
			String result, Map<String, String> feedbackData) throws IOException {
		if(result != Constant.PAY_UNDEFINED){
			resp.sendRedirect(this.getRechargeResultURL(feedbackData));
		}
	}

}


