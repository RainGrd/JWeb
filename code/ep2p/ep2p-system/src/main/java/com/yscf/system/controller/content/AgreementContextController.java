/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 协议内容控制层
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月22日     Lin Xu		Initial Version.
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
import com.yscf.core.model.content.AgreementContent;
import com.yscf.core.service.content.impl.AgreementContextServiceImpl;
import com.yscf.system.constort.PageMessageConstants;
import com.yscf.system.controller.base.EscfBaseController;

/**
 * Description：<br> 
 * 协议内容控制层
 * @author  Lin Xu
 * @date    2015年9月22日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/agreementContextController")
public class AgreementContextController extends EscfBaseController {

	private Logger logger = LoggerFactory.getLogger(AgreementContextController.class);
	
	@Autowired
	public AgreementContextController(AgreementContextServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return AgreementContent.class;
	}
	
	/**
	 * Description：<br> 
	 * 跳转至协议内容信息列表页
	 * @author  Lin Xu
	 * @date    2015年9月25日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping("/toAgreeContPage")
	public ModelAndView toAgreeContPage(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelv = new ModelAndView("/content/agreementManage/agreementMContextList");
		//设置参数
		modelv.addObject("ppid", request.getParameter("ppid"));
		return modelv;
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
	@RequestMapping("/getAgreementContextMList")
	@ResponseBody
	public ModelAndView getAgreementContextMList(HttpServletRequest request, HttpServletResponse response,
			AgreementContent agcc) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		AgreementContextServiceImpl acontservice = (AgreementContextServiceImpl) getService();
	   try {
		   //参数判断和分页设置
		   PageInfo info = getPageInfo(request);
		   if(null == agcc){
			   agcc = new AgreementContent();
		   }
		   //获取返回对象信息
		   PageList<AgreementContent> pagelist = acontservice.selectByPrimaryObj(agcc, info);
		   List<AgreementContent> bizlist = Lists.newArrayList();
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
	@RequestMapping("/toAgreementMContextAdd")
	public ModelAndView toAgreementMContextAdd(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelv = new ModelAndView("/content/agreementManage/agreementMContext_add");
		//设置参数
		modelv.addObject("agrConTemId", request.getParameter("fid"));
		return modelv;
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
	@RequestMapping("/toAgreementMContextEidt")
	public ModelAndView toAgreementMContextEidt(HttpServletRequest request, 
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelv = new ModelAndView("/content/agreementManage/agreementMContext_eidt");
		AgreementContextServiceImpl atcservice = (AgreementContextServiceImpl) getService();
		try {
			String pid = request.getParameter("pid");
			AgreementContent agc = (AgreementContent) atcservice.selectByPrimaryKey(pid);
			modelv.addObject("actext", agc);
		} catch (FrameworkException e) {
			e.printStackTrace();
			logger.debug("==>toAgreementMContextEidt: 查询数据信息出错");
		}
		return modelv;
	}
	
	
	/**
	 * Description：<br> 
	 * 添加协议内容信息
	 * @author  Lin Xu
	 * @date    2015年9月24日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @param acts
	 * @return
	 */
	@RequestMapping(value="/saveAgreementContext",method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> saveAgreementContext(HttpServletRequest request,
			HttpServletResponse response, AgreementContent ac) {
		HashMap<String, Object> remap = new HashMap<String, Object>();
		AgreementContextServiceImpl atmservice = (AgreementContextServiceImpl) getService();
		try {
			if(null != ac){
				ContextUser cuser = getContextUser();
				//设置参数值
				String pid = ac.getPK();
				ac.setPid(pid);
				ac.setCreateUser(cuser.getUserName());
				ac.setCreateTime(DateUtil.format(new Date()));
				ac.setLastUpdateUser(cuser.getUserName());
				ac.setLastUpdateTime(DateUtil.format(new Date()));
				ac.setStatus("1");  // 1. 表示启用，2.表示禁用
				ac.setVersion("1");
				//插入对象
				atmservice.insert(ac);
				processSuccess(remap, PageMessageConstants.AGREEMENT_CONTEXT_ADD_SUCCESS);
			}else{
				processFail(remap, PageMessageConstants.AGREEMENT_CONTEXT_NOTNULL);
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
	 * 修改协议内容信息
	 * @author  Lin Xu
	 * @date    2015年9月24日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @param acts
	 * @return
	 */
	@RequestMapping(value="/eidtAgreementContext",method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> eidtAgreementContext(HttpServletRequest request,
			HttpServletResponse response, AgreementContent ac) {
		HashMap<String, Object> remap = new HashMap<String, Object>();
		AgreementContextServiceImpl atcservice = (AgreementContextServiceImpl) getService();
		try {
			if(null != ac){
				String qpid = ac.getPid();
				ContextUser cuser = getContextUser();
				AgreementContent agc = (AgreementContent) atcservice.selectByPrimaryKey(qpid);
				//设置参数值
				agc.setLastUpdateUser(cuser.getUserName());
				agc.setLastUpdateTime(DateUtil.format(new Date()));
				agc.setAgrContName(ac.getAgrContName());
				agc.setProtocol(ac.getProtocol());
				//插入对象
				atcservice.updateByPrimaryKey(agc);
				processSuccess(remap, PageMessageConstants.AGREEMENT_CONTEXT_EIDT_SUCCESS);
			}else{
				processFail(remap, PageMessageConstants.AGREEMENT_CONTEXT_NOTNULL);
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
	 * 批量启用或者禁用协议内容信息
	 * @author  Lin Xu
	 * @date    2015年9月28日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @param upids 选中的id
	 * @param status 状态信息
	 * @param fpid 父级id
	 * @return
	 */
	@RequestMapping(value="/updateBatchAgreementContext",method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> updateBatchAgreementContext(HttpServletRequest request,
			HttpServletResponse response,String upids,String status,String fpid){
		HashMap<String, Object> remap = new HashMap<String, Object>();
		AgreementContextServiceImpl atcservice = (AgreementContextServiceImpl) getService();
		try{
			//判断信息是否完整
			if(StringUtil.isNotEmpty(upids) && StringUtil.isNotEmpty(status) && StringUtil.isNotEmpty(fpid)){
				ContextUser cuser = getContextUser();
				String[] pidslist = StringUtil.split(upids, ",");
				List<AgreementContent> aslist = Lists.newArrayList();
				for(String pid : pidslist){
					AgreementContent aco = new AgreementContent();
					aco.setPid(pid);
					aco.setAgrConTemId(fpid);
					aco.setStatus(status);
					aco.setLastUpdateUser(cuser.getUserName());
					aco.setLastUpdateTime(DateUtil.format(new Date()));
					//添加集合中
					aslist.add(aco);
				}
				//进行批量修改
				atcservice.updateBatchObj(aslist);
				//进行结果集返回
				if("1".equals(status)){
					processSuccess(remap, PageMessageConstants.AGREEMENT_CONTEXT_ACTIVE);
				}
				if("2".equals(status)){
					processSuccess(remap,PageMessageConstants.AGREEMENT_CONTEXT_DISABLE);
				}
			}else{
				processFail(remap, PageMessageConstants.AGREEMENT_CONTEXT_NOTNULL);
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
	 * 选择协议
	 * @author  JunJie.Liu
	 * @date    2015年10月23日
	 * @version v1.0.0
	 * @return
	 */
	@RequestMapping(value="/chooseAgreement")
	@ResponseBody
	public ModelAndView chooseAgreement(HttpServletRequest request,
			HttpServletResponse response){
		return new ModelAndView("content/agreementManage/agreementChose");
	}
}


