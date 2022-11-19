/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 短信模板
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月14日     Jie.Zou		Initial Version.
 *************************************************************************
 */
package com.yscf.system.controller.system;

import java.util.List;

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
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.util.DateUtil;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.common.Constant.Constant;
import com.yscf.common.util.SmsUtil;
import com.yscf.core.emay.service.SmsSDKClient;
import com.yscf.core.model.system.SysSmsTemplates;
import com.yscf.core.service.system.ISysParamsService;
import com.yscf.core.service.system.impl.SysSmsTemplatesServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;

/**
 * Description：<br> 
 * 短信模板
 * @author  Jie.Zou
 * @date    2015年9月14日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/sysSmsTemplatesController")
public class SysSmsTemplatesController extends EscfBaseController {
	
	private Logger logger = LoggerFactory.getLogger(SysDistionaryContentController.class);
	
	@Resource(name = "sysParamsService")
	private ISysParamsService sysParamsService;
	
	@Autowired
	public SysSmsTemplatesController(SysSmsTemplatesServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return SysSmsTemplates.class;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 跳转到短信模板列表
	 * @author  Jie.Zou
	 * @date    2015年9月14日
	 * @version v1.0.0
	 * @return
	 */
	@RequestMapping("/toList")
	public ModelAndView toList() {
		return new ModelAndView("/system/smsTemplates/smsTemplatesList");
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 跳转到短信新增页面
	 * @author  Jie.Zou
	 * @date    2015年9月14日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toAdd")
	@ResponseBody
	public ModelAndView toAdd(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("/system/smsTemplates/smsTemplatesAdd");
		SysSmsTemplatesServiceImpl service = (SysSmsTemplatesServiceImpl)getService();
		SysSmsTemplates smsTemplates = (SysSmsTemplates) getEntity(request);
		if(smsTemplates==null){
			smsTemplates = service.getSmsTemplatesById(request.getParameter("pid"));
		}
		modelView.addObject("smsTemplates", smsTemplates);
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 短信模板列表查询（带分页）
	 * @author  Jie.Zou
	 * @date    2015年9月14日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/smsTemplatesList")
	@ResponseBody
	public ModelAndView smsTemplatesList(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		
		ModelAndView modelView = new ModelAndView();
		try {
			PageInfo info = getPageInfo(request); 
			SysSmsTemplates smsTemplates = (SysSmsTemplates)getEntity(request);
			if(null == smsTemplates){
				smsTemplates = new SysSmsTemplates();
			}
			//只查询有效数据
			smsTemplates.setStatus(Constant.ACTIVATE);
			SysSmsTemplatesServiceImpl service = (SysSmsTemplatesServiceImpl)getService();
			PageList<SysSmsTemplates> pageList = service.selectAllPage(smsTemplates,info);
			modelView.addObject("rows",pageList);
			modelView.addObject("total",pageList.getPaginator().getTotalCount());
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.info(e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView,e,request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 短信编码验证
	 * @author  Jie.Zou
	 * @date    2015年9月14日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/validateCode")
	@ResponseBody
	public ModelAndView validateCode(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			SysSmsTemplates smsTemplates = (SysSmsTemplates)getEntity(request);
			smsTemplates.setStatus(Constant.ACTIVATE);
			SysSmsTemplatesServiceImpl serviceImpl = (SysSmsTemplatesServiceImpl)getService();
			List<SysSmsTemplates> list = serviceImpl.selectAll(smsTemplates);
			if(null!= list && list.size() > 0){
				modelView.addObject("result", "fail");
			}else{
				modelView.addObject("result", "suc");
			}
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
	
	
	/**
	 * 
	 * Description：<br> 
	 * 短信编码验证
	 * @author  Jie.Zou
	 * @date    2015年9月14日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/testSendSms")
	@ResponseBody
	public ModelAndView testSendSms(String pid,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			SysSmsTemplatesServiceImpl serviceImpl = (SysSmsTemplatesServiceImpl)getService();
			SysSmsTemplates temp = (SysSmsTemplates) serviceImpl.selectByPrimaryKey(pid);
			// 获取系统参数配置的接收手机号码
			String testMobile = sysParamsService.getParamsByParamKey("TEST_SMS_MOBILE").getParamValue();
			// 调用发送短信接口
			SmsSDKClient.sendEmay(1, testMobile, temp.getSmsContent().replace("{}", SmsUtil.RandomNumber()), null, null, 5);
			
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
	
	@RequestMapping(value = "/save")
	@ResponseBody
	@Override
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("/system/smsTemplates/smsTemplatesList");
		try {
			SysSmsTemplates smsTemplates = (SysSmsTemplates)getEntity(request);
			System.out.println(smsTemplates.getPid());
			System.out.println(smsTemplates.getPid().trim());
			//为空为新增，不为空为修改
			if(null!=smsTemplates && null!=smsTemplates.getPid() && !"".equals(smsTemplates.getPid().trim())){
				smsTemplates.setLastUpdateTime(DateUtil.getToday());
				smsTemplates.setLastUpdateUser("1");
				SysSmsTemplatesServiceImpl serviceImpl = (SysSmsTemplatesServiceImpl)getService();
				serviceImpl.updateSmsTemplates(smsTemplates);
			}else{
				smsTemplates.setCreateTime(DateUtil.getToday());
				smsTemplates.setCreateUser("1");
				smsTemplates.setStatus(Constant.ACTIVATE);
				getService().insert(smsTemplates);
			}
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 短信模板批量删除
	 * @author  Jie.Zou
	 * @date    2015年9月16日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/deleteBtach")
	@ResponseBody
	public ModelAndView deleteBtach(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			 SysSmsTemplates smsTemplates = new SysSmsTemplates();
			 smsTemplates.setPid(request.getParameter("pid"));
			 smsTemplates.setLastUpdateTime(DateUtil.getToday());
			 smsTemplates.setLastUpdateUser("1");
			 smsTemplates.setStatus(Constant.DISABLE);
			 SysSmsTemplatesServiceImpl serviceImpl = (SysSmsTemplatesServiceImpl)getService();
			 serviceImpl.updateStatusBatch(smsTemplates);
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


