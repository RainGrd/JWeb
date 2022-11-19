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
package com.yscf.system.controller.activity;

import java.io.File;
import java.io.UnsupportedEncodingException;
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

import com.achievo.framework.constant.ComExcelConstants;
import com.achievo.framework.excel.domain.CheckResult;
import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.common.Constant.Constant;
import com.yscf.core.model.activity.ActVipActDetail;
import com.yscf.core.model.comm.ExportObjectModel;
import com.yscf.core.service.activity.impl.ActVipActDetailServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;
import com.yscf.system.dto.ExportVoToModelDto;
import com.yscf.system.util.FileDownload;
import com.yscf.system.vo.ExportObjectVo;

/**
 * 
 * @ClassName : ActVipActDetailController
 * @Description : 赠送VIP活动明细Controller
 * @Author : Qing.Cai
 * @Date : 2015年10月10日 下午3:13:15
 */
@Controller
@RequestMapping("/actVipActDetailController")
public class ActVipActDetailController extends EscfBaseController {
	private Logger logger = LoggerFactory.getLogger(ActVipActDetailController.class);

	@Autowired
	public ActVipActDetailController(ActVipActDetailServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return ActVipActDetail.class;
	}

	/**
	 * 
	 * @Description : 跳转到赠送VIP列表页面
	 * @return actVipActDetail_index.jsp
	 * @Author : Qing.Cai
	 * @Date : 2015年10月10日 下午3:23:03
	 */
	@RequestMapping("/openActVipActDetail")
	public ModelAndView openActVipActDetail() {
		return new ModelAndView("/activity/actVipActDetail_index");
	}

	/**
	 * 
	 * @Description : 跳转到赠送VIP明细列表页面
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return actVipActDetail_list.jsp
	 * @Author : Qing.Cai
	 * @Date : 2015年10月28日 上午11:06:12
	 */
	@RequestMapping("/openActVipActDetailList")
	public ModelAndView openActVipActDetailList(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("/activity/detail/actVipActDetail_list");
		try {
			// 赋值
			String actName = java.net.URLDecoder.decode(request.getParameter("actName"), "UTF-8");
			String actCode = java.net.URLDecoder.decode(request.getParameter("actCode"), "UTF-8");
			modelAndView.addObject("activityId", request.getParameter("activityId") != null ? request.getParameter("activityId") : 0);
			modelAndView.addObject("actCode", actCode != null ? actCode : "");
			modelAndView.addObject("actName", actName != null ? actName : "");
		} catch (UnsupportedEncodingException e) {
			if (logger.isInfoEnabled()) {
				e.printStackTrace();
			}
		}
		return modelAndView;
	}

	/**
	 * 
	 * @Description : 查询赠送VIP活动列表,带分页
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param actVipActDetail
	 *            赠送VIP活动明细对象
	 * @return 赠送VIP活动列表
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2015年10月10日 下午3:19:49
	 */
	@RequestMapping(value = "/selectAllPage")
	@ResponseBody
	public ModelAndView selectAllPage(HttpServletRequest request, HttpServletResponse response, ActVipActDetail actVipActDetail) throws HttpRequestException {
		ActVipActDetailServiceImpl service = (ActVipActDetailServiceImpl) getService();
		ModelAndView modelView = new ModelAndView();
		try {
			PageInfo info = getPageInfo(request);
			if (null == actVipActDetail) {
				actVipActDetail = new ActVipActDetail();
			}
			PageList<ActVipActDetail> list = service.selectAllPage(actVipActDetail, info);
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
	 * @Description : 查询赠送VIP活动明细列表,带分页
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param actVipActDetail
	 *            赠送VIP活动明细对象
	 * @return 赠送VIP活动明细列表
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2015年10月12日 下午3:18:04
	 */
	@RequestMapping(value = "/selectAllPageDetail")
	@ResponseBody
	public ModelAndView selectAllPageDetail(HttpServletRequest request, HttpServletResponse response, ActVipActDetail actVipActDetail) throws HttpRequestException {
		ActVipActDetailServiceImpl service = (ActVipActDetailServiceImpl) getService();
		ModelAndView modelView = new ModelAndView();
		try {
			PageInfo info = getPageInfo(request);
			if (null == actVipActDetail) {
				actVipActDetail = new ActVipActDetail();
			}
			PageList<ActVipActDetail> list = service.selectAllPageDetail(actVipActDetail, info);
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
	 * @Description : 导出
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param actVipActDetail
	 *            赠送VIP明细对象
	 * @param expvo
	 *            导出对象
	 * @return
	 * @Author : Qing.Cai
	 * @Date : 2015年11月4日 上午10:59:03
	 */
	@RequestMapping("/exportDownLoadFile")
	@ResponseBody
	public HashMap<String, Object> exportDownLoadFile(HttpServletRequest request, HttpServletResponse response, ActVipActDetail actVipActDetail, ExportObjectVo expvo) {
		HashMap<String, Object> remap = new HashMap<String, Object>();
		ActVipActDetailServiceImpl service = (ActVipActDetailServiceImpl) getService();
		try {
			if (null == actVipActDetail) {
				actVipActDetail = new ActVipActDetail();
			}
			// 进行导出数据封装
			if (null != expvo) {
				// 通过VO转Model
				ExportObjectModel eom = ExportVoToModelDto.voToModel(expvo);
				// 获取结果集
				List<ActVipActDetail> reList = service.selectAllPageDetailExport(actVipActDetail, eom);
				// 通过信息进行配置文件
				String xmlpath = getTempBasePath(request).get(Constant.COM_EXPORTBASEPATH) + "export_activity_vip_detail.xml";
				CheckResult checkrsl = exportExcel(actVipActDetail.getActName()+"_赠送VIP明细.xls", xmlpath, "export_activity_manage", reList);
				// 获取生成的文件信息
				if (checkrsl.isPass()) {
					File file = (File) checkrsl.getStatusMessage().get(ComExcelConstants.EXCELRESLUT);
					FileDownload.downloadAbsoluteFile(response, request, file, true);
					logger.info("POI 2007 导出的结果文件为名：" + file.getName());
					processSuccess(remap, file.getName());
				} else {
					List<String> errorMsg = checkrsl.getErrorMessage();
					for (String str : errorMsg) {
						processError(remap, str);
					}
				}
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
			processError(remap, e.getMessage());
		}
		return remap;
	}

}
