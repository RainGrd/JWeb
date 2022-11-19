/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 数据字典内容
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月10日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.ep2p.controller.system;

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

import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.core.model.system.SysDictionaryContent;
import com.yscf.core.model.system.SysParams;
import com.yscf.core.service.system.impl.SysDictionaryContentServiceImpl;
import com.yscf.core.service.system.impl.SysParamsServiceImpl;
import com.yscf.ep2p.controller.base.EscfBaseWebController;

/**
 * 
 * Description：<br> 
 * 系统参数controller
 * @author  Yu.Zhang
 * @date    2015年12月18日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/sysParam/SysParamContentController")
public class SysParamContentController extends EscfBaseWebController {

	private Logger logger = LoggerFactory.getLogger(SysParamContentController.class);
	
	@Autowired
	public SysParamContentController(SysParamsServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return SysParams.class;
	}

	/**
	 * 
	 * Description：<br> 
	 * 根据系统参数key，获取系统参数value
	 * @author  Yu.Zhang
	 * @date    2015年12月17日
	 * @version v1.0.0
	 * @param paramKey
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/getParamValueByKey")
	@ResponseBody
	public ModelAndView getParamValueByKey(String paramKey,HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			SysParamsServiceImpl service = (SysParamsServiceImpl) getService();
			SysParams sysParams = service.getParamsByParamKey(paramKey);
			modelView.addObject("result", sysParams.getParamValue());
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

}
