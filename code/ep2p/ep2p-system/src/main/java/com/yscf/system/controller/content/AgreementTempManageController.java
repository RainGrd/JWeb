/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 内容管理-协议管理
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月21日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.system.controller.content;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.security.domain.ContextUser;
import com.achievo.framework.util.DateUtil;
import com.achievo.framework.util.StringUtil;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.google.common.collect.Lists;
import com.yscf.core.model.content.AgreementContTemp;
import com.yscf.core.service.content.impl.AgreementTempManageServiceImpl;
import com.yscf.system.constort.PageMessageConstants;
import com.yscf.system.controller.base.EscfBaseController;

/**
 * Description：<br> 
 * 内容管理-协议管理
 * @author  Lin Xu
 * @date    2015年9月21日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/agreementTempManageController")
public class AgreementTempManageController extends EscfBaseController {
	
	private Logger logger = LoggerFactory.getLogger(AgreementTempManageController.class);
	
	@Autowired
	public AgreementTempManageController(AgreementTempManageServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return AgreementContTemp.class;
	}
	
	/**
	 * Description：<br> 
	 * 跳转至合同管理列表页面
	 * @author  Lin Xu
	 * @date    2015年9月21日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping("/toAgreementTempM")
	public ModelAndView toAgreementTempM(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		return new ModelAndView("/content/agreementManage/agreementManageList");
	}
	
	/**
	 * Description：<br> 
	 * 获取合同管理列表信息数据
	 * @author  Lin Xu
	 * @date    2015年9月21日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping("/getAgreementTempMList")
	@ResponseBody
	public ModelAndView getAgreementTempMList(HttpServletRequest request, HttpServletResponse response,
			AgreementContTemp agctemp) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		AgreementTempManageServiceImpl atmservice = (AgreementTempManageServiceImpl) getService();
	   try {
		   //参数判断和分页设置
		   PageInfo info = getPageInfo(request);
		   if(null == agctemp){
			   agctemp = new AgreementContTemp();
		   }
		   //获取返回对象信息
		   PageList<AgreementContTemp> pagelist = atmservice.selectByPrimaryObj(agctemp, info);
		   List<AgreementContTemp> bizlist = Lists.newArrayList();
		   int tatol = 0;
		   if(null != pagelist){
			   bizlist = pagelist;
			   tatol = pagelist.getPaginator().getTotalCount();
		   }
		   //设置返回模型中的参数
		   modelView.addObject("rows", bizlist);
		   modelView.addObject("total", tatol);
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
	 * Description：<br> 
	 * 跳转协议内容模板增加页面
	 * @author  Lin Xu
	 * @date    2015年9月24日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping("/toAgreementTempMAdd")
	public ModelAndView toAgreementTempMAdd(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		return new ModelAndView("/content/agreementManage/agreementCTemp_add");
	}
	
	/**
	 * Description：<br> 
	 * 添加协议内容模板信息
	 * @author  Lin Xu
	 * @date    2015年9月24日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @param acts
	 * @return
	 */
	@RequestMapping(value="/saveAgreementTemp",method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> saveAgreementTemp(HttpServletRequest request,
			HttpServletResponse response, AgreementContTemp acts) {
		HashMap<String, Object> remap = new HashMap<String, Object>();
		AgreementTempManageServiceImpl atmservice = (AgreementTempManageServiceImpl) getService();
		try {
			if(null != acts){
				ContextUser cuser = getContextUser();
				//设置参数值
				String pid = acts.getPK();
				acts.setPid(pid);
				acts.setCreateUser(cuser.getUserName());
				acts.setCreateTime(DateUtil.format(new Date()));
				acts.setLastUpdateUser(cuser.getUserName());
				acts.setLastUpdateTime(DateUtil.format(new Date()));
				acts.setVersion("1");
				//插入对象
				atmservice.insert(acts);
				processSuccess(remap, PageMessageConstants.AGREEMENT_TEMP_ADD_SUCCESS);
			}else{
				processFail(remap, PageMessageConstants.AGREEMENT_TEMP_ADD_FAIL);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
			processError(remap,e.getMessage());
		}
		return remap;
	}
	
	/**
	 * Description：<br> 
	 * 跳转协议内容模板编辑页面
	 * @author  Lin Xu
	 * @date    2015年9月24日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping("/toAgreementTempMEidt")
	public ModelAndView toAgreementTempMEidt(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView model = new ModelAndView("/content/agreementManage/agreementCTemp_eidt");
		AgreementTempManageServiceImpl atmservice = (AgreementTempManageServiceImpl) getService();
		try {
			String pid = request.getParameter("pid");
			AgreementContTemp act = (AgreementContTemp) atmservice.selectByPrimaryKey(pid);
			model.addObject("acto", act);
		} catch (FrameworkException e) {
			e.printStackTrace();
		}
		return model;
	}
	
	/**
	 * Description：<br> 
	 * 编辑协议内容模板信息
	 * @author  Lin Xu
	 * @date    2015年9月24日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @param acts
	 * @return
	 */
	@RequestMapping(value="/eidtAgreementTemp",method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> eidtAgreementTemp(HttpServletRequest request,
			HttpServletResponse response, AgreementContTemp acts) {
		HashMap<String, Object> remap = new HashMap<String, Object>();
		AgreementTempManageServiceImpl atmservice = (AgreementTempManageServiceImpl) getService();
		try {
			if(null != acts){
				ContextUser cuser = getContextUser();
				String pid = request.getParameter("pid");
				AgreementContTemp act = (AgreementContTemp) atmservice.selectByPrimaryKey(pid);
				//设置参数值
				act.setPid(pid);
				act.setAgrContTempName(acts.getAgrContTempName());
				act.setStatus(acts.getStatus());
				act.setLastUpdateUser(cuser.getUserName());
				act.setLastUpdateTime(DateUtil.format(new Date()));
				//修改对象
				atmservice.updateByPrimaryKey(act);
				processSuccess(remap, PageMessageConstants.AGREEMENT_TEMP_EIDT_SUCCESS);
			}else{
				processFail(remap, PageMessageConstants.AGREEMENT_TEMP_ADD_FAIL);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
			processError(remap,e.getMessage());
		}
		return remap;
	}
	
	
	
	
	
	/**
	 * Description：<br> 
	 * 启用或禁用协议内容模板
	 * @author  Lin Xu
	 * @date    2015年9月24日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @param pids
	 * @param status
	 * @return
	 */
	@RequestMapping(value="/updateAgreementTemp")
	@ResponseBody
	public HashMap<String, Object> updateAgreementTemp(HttpServletRequest request,
			HttpServletResponse response,String pids,String status){
		HashMap<String, Object> remap = new HashMap<String, Object>();
		AgreementTempManageServiceImpl atmservice = (AgreementTempManageServiceImpl) getService();
		try{
			if(StringUtil.isNotEmpty(pids) && StringUtil.isNotEmpty(status)){
				ContextUser cuser = getContextUser();
				String[] pidsarray = StringUtil.split(pids, ",");
				//进行循环处理状态信息
				for(String pid : pidsarray){
					AgreementContTemp act = new AgreementContTemp();
						act.setPid(pid);
						act.setStatus(status);
						act.setLastUpdateUser(cuser.getUserName());
						act.setLastUpdateTime(DateUtil.format(new Date()));
					atmservice.updateByPrimaryKeySelective(act);
				}
				//进行返回结果信息
				if("1".equals(status)){
					processSuccess(remap, PageMessageConstants.AGREEMENT_TEMP_ACTIVE);
				}
				if("2".equals(status)){
					processSuccess(remap, PageMessageConstants.AGREEMENT_TEMP_DISABLE);
				}
			}else{
				processFail(remap, PageMessageConstants.COMMON_MESSAGE_FAIL);
			}
		}catch(Exception e){
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
			processError(remap,e.getMessage());
		}
		return remap;
	}
	
	
	/**
	 * 
	 * Description：<br> 
	 * 查询所有的模板信息
	 * @author  Lin Xu
	 * @date    2015年9月18日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectAllAgreementTemp")
	@ResponseBody
	public ModelAndView selectAllAgreementTemp(HttpServletRequest request, HttpServletResponse response,AgreementContTemp act)throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		AgreementTempManageServiceImpl atmservice = (AgreementTempManageServiceImpl) getService();
		try {
			List<AgreementContTemp> actlist = Lists.newArrayList();
			AgreementContTemp defact = new AgreementContTemp();
			defact.setPid("");
			defact.setAgrContTempName("-- 请选择 --");
			//添加第一个默认元素和查询的数据集合
			actlist.add(0, defact);
			actlist.addAll(atmservice.selectByAllObj(act));
			modelView.addObject("data", actlist);
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


