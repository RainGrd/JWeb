/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 *  
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年10月21日     fengshiliang		Initial Version.
 *************************************************************************
 */
package com.yscf.system.controller.activity;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
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

import com.achievo.framework.constant.ComExcelConstants;
import com.achievo.framework.excel.domain.CheckResult;
import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.common.Constant.Constant;
import com.yscf.core.model.activity.ActActivity;
import com.yscf.core.model.activity.ActInvWealthCoopDetail;
import com.yscf.core.model.comm.ExportObjectModel;
import com.yscf.core.service.activity.IActActivityService;
import com.yscf.core.service.activity.IActInvWealthCoopDetailService;
import com.yscf.core.service.activity.impl.ActInvWealthCoopDetailServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;
import com.yscf.system.dto.ExportVoToModelDto;
import com.yscf.system.util.FileDownload;
import com.yscf.system.vo.ExportObjectVo;

/**
 * Description：<br>
 * 财富合伙人明细 控制器
 * 
 * @author fengshiliang
 * @date 2015年10月21日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/actInvWealthCoopDetailController")
public class ActInvWealthCoopDetailController extends EscfBaseController {

	private Logger logger = LoggerFactory
			.getLogger(ActInvWealthCoopDetailController.class);

	/**
	 * 获得 投资奖励明细活动 service
	 */
	@Resource(name = "actInvWealthCoopDetailServiceImpl")
	private IActInvWealthCoopDetailService coopDetailService;
	
	@Resource(name = "actActivityServiceImpl")
	private IActActivityService actActivityServiceImpl;

	@Autowired
	public ActInvWealthCoopDetailController(
			ActInvWealthCoopDetailServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return ActInvWealthCoopDetail.class;
	}

	/**
	 * 
	 * Description：<br>
	 * 跳转到 财富合伙人 查询页面
	 * 
	 * @author fengshiliang
	 * @date 2015年10月21日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @param objectId
	 * @param activityType
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/openWealthCoopDetail")
	@ResponseBody
	public ModelAndView openWealthCoopDetail(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		return new ModelAndView("/activity/actInvWealthCoopDetail_index");
	}

	/**
	 * 
	 * Description：<br>
	 * 获得财富合伙人 明细
	 * 
	 * @author fengshiliang
	 * @date 2015年10月21日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @param ActInvAwaActDetail
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectAllPage")
	@ResponseBody
	public ModelAndView selectAllPage(HttpServletRequest request,
			HttpServletResponse response, ActInvWealthCoopDetail coopDetail)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			PageInfo info = getPageInfo(request);
			if (null == coopDetail) {
				coopDetail = new ActInvWealthCoopDetail();
			}
			PageList<ActInvWealthCoopDetail> list = coopDetailService
					.selectAllPageByCondition(coopDetail, info);
			modelView.addObject("rows", list);
			modelView.addObject("total", list.getPaginator().getTotalCount());
			MessageBuilder.processSuccess(modelView, null,
					HttpMessage.SUCCESS_MSG, request);
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
	 * 财富合伙人明细
	 * 
	 * @author fengshiliang
	 * @date 2015年10月22日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @param coopDetail
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectAllPageDetail")
	@ResponseBody
	public ModelAndView selectAllPageDetail(HttpServletRequest request,
			HttpServletResponse response, ActInvWealthCoopDetail coopDetail)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			PageInfo info = getPageInfo(request);
			if (null == coopDetail) {
				coopDetail = new ActInvWealthCoopDetail();
			}
			PageList<ActInvWealthCoopDetail> list = coopDetailService
					.selectAllPageDetail(coopDetail, info);
			modelView.addObject("rows", list);
			modelView.addObject("total", list.getPaginator().getTotalCount());
			MessageBuilder.processSuccess(modelView, null,
					HttpMessage.SUCCESS_MSG, request);
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
	 * @Description : 财富合伙人明细
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return actInvWealthCoopDetail_list.jsp
	 * @Author :
	 * @Date : 2015年10月28日 上午11:33:14
	 */
	@RequestMapping("/openWealthCoopDetailList")
	public ModelAndView openWealthCoopDetailList(HttpServletRequest request,
			HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView(
				"/activity/detail/actInvWealthCoopDetail_list");
		try {
			// 赋值
			String actName = java.net.URLDecoder.decode(
					request.getParameter("actName"), "UTF-8");
			String actCode = java.net.URLDecoder.decode(
					request.getParameter("actCode"), "UTF-8");
			modelAndView.addObject(
					"activityId",
					request.getParameter("activityId") != null ? request
							.getParameter("activityId") : 0);
			modelAndView.addObject(
					"expNumber",
					request.getParameter("expNumber") != null ? request
							.getParameter("expNumber") : 0);
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
	 * Description：<br> 
	 * 导出赠送明细
	 * @author  JunJie.Liu
	 * @date    2015年10月29日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @param bizReceiptTransferVO
	 * @param expvo
	 * @return
	 */
	@RequestMapping("/exportDownLoadFile")
	@ResponseBody
	public HashMap<String, Object> exportDownLoadFile(HttpServletRequest request, HttpServletResponse response,
			ActInvWealthCoopDetail actInvWealthCoopDetail,ExportObjectVo expvo){
		HashMap<String, Object> remap = new HashMap<String, Object>();
		try {
			if(null == actInvWealthCoopDetail){
				actInvWealthCoopDetail = new ActInvWealthCoopDetail();
			}
			//进行导出数据封装
			if(null != expvo){
				//通过VO转Model
				ExportObjectModel eom = ExportVoToModelDto.voToModel(expvo);
				
				//获取结果集
				List<ActInvWealthCoopDetail> reList = coopDetailService.selectActInvWealthCoopDetailVOEom(actInvWealthCoopDetail, eom);
				//通过信息进行配置文件 
				ActActivity ac = actActivityServiceImpl.selectByPrimaryKey(actInvWealthCoopDetail.getActivityId());
				String title="";
				if(ac!=null){
					title = ac.getActName();
				}
				//通过信息进行配置文件
				String xmlpath = getTempBasePath(request).get(Constant.COM_EXPORTBASEPATH) + "export_act_inv_wealth_coop_detail.xml";
				CheckResult checkrsl = exportExcel(title+"-赠送明细.xls", xmlpath, "export_act_inv_wealth_coop_detail", reList);
				//获取生成的文件信息
				if(checkrsl.isPass()){
					File file =  (File) checkrsl.getStatusMessage().get(ComExcelConstants.EXCELRESLUT);
					FileDownload.downloadAbsoluteFile(response, request, file, true);
					logger.info("POI 2007 导出的结果文件为名：" + file.getName());
					processSuccess(remap, file.getName());
				}else{
					List<String> errorMsg = checkrsl.getErrorMessage();
					for(String str : errorMsg){
						processError(remap , str);
					}
				}
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
}
