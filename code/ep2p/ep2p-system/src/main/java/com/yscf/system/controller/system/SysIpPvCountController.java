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
package com.yscf.system.controller.system;

import java.util.HashMap;
import java.util.Map;

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
import com.yscf.common.util.GetMacAddress;
import com.yscf.core.model.system.SysIpPvCount;
import com.yscf.core.service.system.impl.SysIpPvCountServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;

/**
 * Description：<br> 
 * 资金交易状况表
 * @author  Yu.Zhang
 * @date    2015年10月13日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/sysIpPvCountController")
public class SysIpPvCountController  extends EscfBaseController{
	
	private Logger logger = LoggerFactory.getLogger(SysIpPvCountController.class);

	@Autowired
	public SysIpPvCountController(SysIpPvCountServiceImpl service) {
		super(service);
	}
	
	@Override
	public Class<?> getModel() {
		return SysIpPvCount.class;
	}
	
	@RequestMapping(value = "/selectNearlySevenData")
	@ResponseBody
	public void selectNearlySevenData(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		Map<String, Object> tojson = new HashMap<String, Object>();
		try {
			SysIpPvCountServiceImpl service = (SysIpPvCountServiceImpl)getService();
			tojson = service.selectNearlySevenData();
			tojson.put("message",HttpMessage.SUCCESS_CODE);
		} catch (Exception e) {
 			if(logger.isDebugEnabled()){
				e.printStackTrace();
				logger.info("ip pv 报表查询异常",e.getMessage());
			}
		}
		outputJson(tojson, response);
	}
	
	
	@RequestMapping(value = "/executeIpPvCount")
	@ResponseBody
	public ModelAndView executeIpPvCount(SysIpPvCount info ,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			SysIpPvCountServiceImpl service = (SysIpPvCountServiceImpl)getService();
			GetMacAddress mac = new GetMacAddress(GetMacAddress.getIpAddr(request));
//			info.setMacAddress(GetMacAddress.getIpAddr(request));
			info.setMacAddress(mac.getRemoteMacAddr());
			info.setCreateTime(DateUtil.format(DateUtil.getToday()));
			System.out.println("------------------ MacAddress="+info.getMacAddress()+",path="+info.getPath());
			service.executeIpPvCount(info);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				e.printStackTrace();
				logger.info("ip pv 统计异常",e.getMessage());
			}
			MessageBuilder.processError(modelView,e,request);
		}
		return modelView;
	}
	
	
	

}


