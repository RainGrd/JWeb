/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月10日     JingYu.Dai		Initial Version.
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

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.core.model.financial.BizRechargeSystemInfo;
import com.yscf.core.service.financial.impl.BizRechargeSystemInfoServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;


/**
 * Descriptio：充值渠道
 * @author  JingYu.Dai
 * @date    2015年9月25日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/bizRechargeSystemInfoController")
public class BizRechargeSystemInfoController extends EscfBaseController {

	private Logger logger = LoggerFactory.getLogger(BizRechargeSystemInfoController.class);

	@Autowired
	public BizRechargeSystemInfoController(BizRechargeSystemInfoServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return BizRechargeSystemInfo.class;
	}
	
	/**
	 * Description：查询充值渠道  列表
	 * @author  JingYu.Dai
	 * @date    2015年9月28日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException
	 */
	@RequestMapping("/selectComboboxAll")
	@ResponseBody
	public ModelAndView selectComboboxAll(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView view = new ModelAndView();
		BizRechargeSystemInfoServiceImpl serviceImpl = (BizRechargeSystemInfoServiceImpl) getService();
		try {
			List<BizRechargeSystemInfo> list = serviceImpl.selectAll();
			BizRechargeSystemInfo systemInfo = new BizRechargeSystemInfo();
			systemInfo.setRecSysName("全部");
			List<BizRechargeSystemInfo> systemInfos = new ArrayList<BizRechargeSystemInfo>();
			systemInfos.add(systemInfo);
			systemInfos.addAll(list);
			MessageBuilder.processSuccess(view, systemInfos, HttpMessage.SUCCESS_CODE,
					request);
		} catch (FrameworkException e) {
			if (logger.isInfoEnabled()) {
				e.printStackTrace();
			}
			MessageBuilder.processError(view, e, request);
		}
		return view;
	}
}
