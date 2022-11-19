/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 用户管理
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月6日     Simon.Hoo		Initial Version.
 *************************************************************************
 */
package com.yscf.system.controller.radio;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.util.DateUtil;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.common.Constant.Constant;
import com.yscf.common.util.PromptMsgConstants;
import com.yscf.core.model.pub.PubFile;
import com.yscf.core.model.radio.BizProgram;
import com.yscf.core.service.radio.impl.ProgramListServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;

/**
 * Description：<br>
 * 电台管理
 * @author heng.wang
 * @date 2015年10月19日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/radioController")
public class RadioController extends EscfBaseController {
	private Logger logger = LoggerFactory.getLogger(RadioController.class);
	
	@Autowired
	public RadioController(ProgramListServiceImpl service) {
		super(service);
	}
	@Override
	public Class<?> getModel() {
		return BizProgram.class;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 跳转到节目列表查询页面
	 * @author  heng.wang
	 * @date    2015年10月19日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toProgramSearchList")
	@ResponseBody
	public ModelAndView getProgramSearchList(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		return new ModelAndView("/radio/programList");
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 节目列表查询页面
	 * @author  heng.wang
	 * @date    2015年10月19日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/getProgramSearchList")
	@ResponseBody
	public ModelAndView getProgramSearchList(BizProgram bizProgram,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ProgramListServiceImpl service  = (ProgramListServiceImpl) getService();
		ModelAndView modelView = new ModelAndView();
	   try {
			PageInfo info = getPageInfo(request);
			if(null == bizProgram){
				bizProgram = new BizProgram();
			}
 			PageList<BizProgram> list = service.selectAllPage(bizProgram,info);
 			//给获赞数和收听数设置默认值
 			if(list.size() > 0 || list != null){
 				for(int i =0;i<list.size();i++){
 				   Integer praiseNum = list.get(i).getPraiseNum();
 				   Integer listenNum = list.get(i).getListenNum();
 				   if("".equals(praiseNum) || praiseNum==null){
 					   list.get(i).setPraiseNum(0);
 				   }
 				  if("".equals(listenNum) || listenNum==null){
 					 list.get(i).setListenNum(0);
 				   }
 				}
 			}
 		    modelView.addObject("rows", list);
 		    modelView.addObject("total", list.getPaginator().getTotalCount());
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
	 * 批量删除节目列表
	 * @author  heng.wang
	 * @date    2015年10月19日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/beatchDeleteProgramList")
	@ResponseBody
	public ModelAndView beatchDeleteProgramList(String pids,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		ProgramListServiceImpl service  = (ProgramListServiceImpl) getService();
		try {
			//根据主键批量删除
			if(pids!=""){
				service.beatchDeleteProgramList(pids);
				MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
			}
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
	 * 跳转到节目列表预览或编辑页面
	 * @author  heng.wang
	 * @date    2015年10月19日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toPreviewOrEditProgramList")
	@ResponseBody
	public ModelAndView toPreviewOrEditProgramList(String pid,String flag,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelAndView = new ModelAndView("/radio/toPreviewOrEditProgramList");
		modelAndView.addObject("pid", pid);
		modelAndView.addObject("flag", flag);
		return modelAndView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 根据pid查看节目列表详情
	 * @author  heng.wang
	 * @date    2015年10月19日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/previewOrEditProgramById")
	@ResponseBody
	public ModelAndView previewOrEditProgramById(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		ProgramListServiceImpl service  = (ProgramListServiceImpl) getService();
		BaseEntity entity = getEntity(request);
		BizProgram bizProgram = (BizProgram) entity;
		if(null == bizProgram){
			bizProgram = new BizProgram();
		}
		try {
				PageList<BizProgram> list  = service.previewOrEditProgramById(bizProgram.getPid());
				modelView.addObject("result", list.get(0));
				MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据PID获取客服信息失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 根据pid修改节目列表详情
	 * @author  heng.wang
	 * @date    2015年10月20日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/updateProgramListById")
	@ResponseBody
	public ModelAndView updateProgramListById(String pid,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		ProgramListServiceImpl service  = (ProgramListServiceImpl) getService();
		BaseEntity entity = getEntity(request);
		BizProgram bizProgram = (BizProgram) entity;
		bizProgram.setPid(pid);
		try {
				service.updateProgramListById(bizProgram);
				MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据PID修改节目列表信息失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 跳转到上传节目列表页面
	 * @author  heng.wang
	 * @date    2015年10月20日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/uploadProgramList")
	@ResponseBody
	public ModelAndView uploadProgramList(String pid,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelAndView = new ModelAndView("/radio/uploadProgramList");
		modelAndView.addObject("pid", pid);
		return modelAndView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 上传节目列表
	 * @author  heng.wang
	 * @date    2015年10月20日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/upload")
	@ResponseBody
	public ModelAndView upload(BizProgram bizProgram,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		ProgramListServiceImpl service  = (ProgramListServiceImpl) getService();
		String filejpg ="";
		String filevido ="";
		try {
			String pid = bizProgram.getPK();
			bizProgram.setPid(pid); 
			bizProgram.setStatus("3");
			//bizProgram.setPublishTime(DateUtil.getSystemDate());
			bizProgram.setCreateTime(DateUtil.format(new Date()));
			bizProgram.setUploadTime(DateUtil.format(new Date()));
			// 文件上传
			HashMap<String, Object> remap = saveUploadFileObject(request,
					Constant.RADIO);
			if ((Boolean) remap.get(PromptMsgConstants.COMMON_FLG)) {
				List<PubFile> resultPlist =  ((List<PubFile>)remap.get("resultList"));
				if(null  != resultPlist && resultPlist.size() > 1){
					PubFile pubFilea = ((List<PubFile>) remap.get("resultList")).get(0);
					filejpg=  pubFilea.getFileUrl();
					PubFile pubFileb = ((List<PubFile>) remap.get("resultList")).get(1);
					filevido=  pubFileb.getFileUrl();
				}
				bizProgram.setProgramType(filejpg);
				bizProgram.setPictureFileId(filevido);
				service.uploadProgram(bizProgram);
				MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
			} else{
				MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("上传节目列表信息失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 根据pid修改发布时间
	 * @author  heng.wang
	 * @date    2015年10月20日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/updatepublishTime")
	@ResponseBody
	public ModelAndView updatepublishTime(String pid,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		ProgramListServiceImpl service  = (ProgramListServiceImpl) getService();
		BizProgram bizProgram = new BizProgram();
		bizProgram.setPid(pid);
		bizProgram.setPublishTime(DateUtil.format(new Date()));
		try {
				service.updatepublishTime(bizProgram);
				MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据PID修改节目列表信息失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 根据pid下架节目时间
	 * @author  heng.wang
	 * @date    2015年10月20日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/offlineProgram")
	@ResponseBody
	public ModelAndView offlineProgram(String pid,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		ProgramListServiceImpl service  = (ProgramListServiceImpl) getService();
		BizProgram bizProgram = new BizProgram();
		bizProgram.setPid(pid);
		bizProgram.setEliminateTime(DateUtil.format(new Date()));
		try {
			service.offlineProgram(bizProgram);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据PID下架节目列表失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 跳转到预发布节目列表页面
	 * @author  heng.wang
	 * @date    2015年10月20日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toPredictProgram")
	@ResponseBody
	public ModelAndView toPredictProgram(String pid,String publishTime,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelAndView = new ModelAndView("/radio/toPredictProgram");
		modelAndView.addObject("pid", pid);
		modelAndView.addObject("publishTime", publishTime);
		return modelAndView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 保存预发布时间
	 * @author  heng.wang
	 * @date    2015年10月20日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/savePredict")
	@ResponseBody
	public ModelAndView savePredict(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		ProgramListServiceImpl service  = (ProgramListServiceImpl) getService();
		BaseEntity entity = getEntity(request);
		BizProgram bizProgram = (BizProgram) entity;
		try {
			service.savePredict(bizProgram);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据PID下架节目列表失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	
}
