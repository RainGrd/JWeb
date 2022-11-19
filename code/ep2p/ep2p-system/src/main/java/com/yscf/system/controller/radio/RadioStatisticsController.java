/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 电台管理-统计
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月6日     Simon.Hoo		Initial Version.
 *************************************************************************
 */
package com.yscf.system.controller.radio;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.core.model.radio.BizProgram;
import com.yscf.core.model.radio.SocalAppModel;
import com.yscf.core.service.radio.impl.ProgramListServiceImpl;
import com.yscf.core.util.HReportUtil;
import com.yscf.system.constort.Constants;
import com.yscf.system.controller.base.EscfBaseController;
import com.yscf.system.vo.HReportProgramVo;

/**
 * Description：<br>
 * 电台管理-统计
 * @author heng.wang
 * @date 2015年10月19日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/radioStatisticsController")
public class RadioStatisticsController extends EscfBaseController {
	
	private Logger logger = LoggerFactory.getLogger(RadioStatisticsController.class);
	
	@Autowired
	public RadioStatisticsController(ProgramListServiceImpl service) {
		super(service);
	}
	
	@Override
	public Class<?> getModel() {
		return BizProgram.class;
	}
	
	
	/**
	 * Description：<br> 
	 * 跳转至显示列表页
	 * @author  Lin Xu
	 * @date    2015年10月26日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toDisplayLists")
	public ModelAndView toDisplayLists(HttpServletRequest request, HttpServletResponse response){
		ModelAndView model = new ModelAndView("/radio/statistics/display_lists");
		return model;
	}
	
	/**
	 * Description：<br> 
	 * 跳转至节目详细页面（节目折现图）
	 * @author  Lin Xu
	 * @date    2015年10月27日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toPorgramePage")
	public ModelAndView toPorgramePage(HttpServletRequest request, HttpServletResponse response){
		ModelAndView model = new ModelAndView("/radio/statistics/programe_info");
		model.addObject("prgpid", request.getParameter("prgpid"));
		model.addObject("programTitle", request.getParameter("programTitle"));
		return model;
	}
	
	/**
	 * Description：<br> 
	 * 分(时间)段用户量统计
	 * @author  Lin Xu
	 * @date    2015年10月26日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toUserAmount")
	public ModelAndView toUserAmount(HttpServletRequest request, HttpServletResponse response,
			BizProgram bizProgram){
		ModelAndView model = new ModelAndView("/radio/statistics/user_amount");
		return model;
	}
	
	/**
	 * Description：<br> 
	 * 统计系统总数
	 * @author  Lin Xu
	 * @date    2015年10月30日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @param bizProgram
	 * @return
	 */
	@RequestMapping("/toSumAmount")
	public ModelAndView toSumAmount(HttpServletRequest request, HttpServletResponse response,
			BizProgram bizProgram){
		ModelAndView model = new ModelAndView("/radio/statistics/system_amount");
		return model;
	}
	
	
	/**
	 * Description：<br> 
	 * 获取列表查询信息
	 * @author  Lin Xu
	 * @date    2015年10月27日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @param bizProgram
	 * @return
	 */
	@RequestMapping("/getDisPlayLists")
	@ResponseBody
	public ModelAndView getDisPlayLists(HttpServletRequest request, HttpServletResponse response,
			BizProgram bizProgram){
		ModelAndView model = new ModelAndView();
		ProgramListServiceImpl service  = (ProgramListServiceImpl) getService();
		try {
			PageInfo info = getPageInfo(request);
			if(null == bizProgram){
				bizProgram = new BizProgram();
			}
 			PageList<BizProgram> list = service.selectStatisticsList(bizProgram,info);
 			model.addObject("rows", list);
 			model.addObject("total", list.getPaginator().getTotalCount());
 		    MessageBuilder.processSuccess(model, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(model, e, request);
		}
		return model;
	}
	
	/**
	 * Description：<br> 
	 * 获取单个表单数据信息
	 * @author  Lin Xu
	 * @date    2015年10月28日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/getOneProgrameData",method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> getOneProgrameData(HttpServletRequest request, HttpServletResponse response){
		HashMap<String, Object> remap = new HashMap<String, Object>();
		ProgramListServiceImpl service  = (ProgramListServiceImpl) getService();
		try {
			String type = request.getParameter("timeType");
			String param = request.getParameter("param");
			String ppid = request.getParameter("ppid");
			HashMap<String, Integer[]> reslutMap = service.selectOneProgrameData(type, param,ppid);
			if(null != reslutMap){
				List<HReportProgramVo> list = new ArrayList<HReportProgramVo>();
				HReportProgramVo playnump = new HReportProgramVo(Constants.REPORT_TITLE_001,reslutMap.get("playnum"));
				HReportProgramVo listennump = new HReportProgramVo(Constants.REPORT_TITLE_002,reslutMap.get("listennum"));
				HReportProgramVo praisenump = new HReportProgramVo(Constants.REPORT_TITLE_003,reslutMap.get("praisenum"));
				HReportProgramVo forwardnump = new HReportProgramVo(Constants.REPORT_TITLE_004,reslutMap.get("forwardnum"));
				list.add(playnump);
				list.add(listennump);
				list.add(praisenump);
				list.add(forwardnump);
				//设置横坐标
				if("1".equals(type)){
					remap.put("categories", HReportUtil.comMonth());
				}else{
					remap.put("categories", HReportUtil.comDay(param));
				}
				remap.put("datas", list);
				processSuccess(remap, "查询成功");
			}else{
				processFail(remap, "查询失败");
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
	 * 获取用户信息统计信息
	 * @author  Lin Xu
	 * @date    2015年10月29日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/getUserCountData",method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> getUserCountData(HttpServletRequest request, HttpServletResponse response){
		HashMap<String, Object> remap = new HashMap<String, Object>();
		ProgramListServiceImpl service  = (ProgramListServiceImpl) getService();
		try {
			String timedata = request.getParameter("timedata");
			HashMap<String, Integer[]> reslutMap = service.getUserCountData(timedata);
			if(null != reslutMap){
				List<HReportProgramVo> list = new ArrayList<HReportProgramVo>();
				HReportProgramVo playnump = new HReportProgramVo(Constants.REPORT_TITLE_005,reslutMap.get("USERCOUNT"));
				list.add(playnump);
				remap.put("categories", HReportUtil.comYMDH(false, ""));
				remap.put("datas", list);
				processSuccess(remap, "查询成功");
			}else{
				processFail(remap, "查询失败");
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
	 * 用户转发数量信息数据
	 * @author  heng.wang
	 * @date    2015年10月28日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toSocialAppAmount")
	public ModelAndView toSocialAppAmount(HttpServletRequest request, HttpServletResponse response,
			BizProgram bizProgram){
		ModelAndView model = new ModelAndView("/radio/statistics/socialapp_amount");
		return model;
	}
	
	/**
	 * Description：<br> 
	 * 获取所有的信息
	 * @author  Lin Xu
	 * @date    2015年11月2日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/getSocialAppAmount",method=RequestMethod.POST)
	@ResponseBody
	public HashMap<String, Object> getSocialAppAmount(HttpServletRequest request, HttpServletResponse response){
		HashMap<String, Object> remap = new HashMap<String, Object>();
		ProgramListServiceImpl service  = (ProgramListServiceImpl) getService();
		try {
			List<SocalAppModel> list = service.selectSocalAppInfo(null);
			if(null != list && list.size() > 0){
				remap.put("datas", list);
				processSuccess(remap, "查询成功");
			}else{
				processFail(remap, "查询失败");
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
	 * 区域用户量统计
	 * @author  heng.wang
	 * @date    2015年10月28日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toAreaStatistics")
	public ModelAndView toAreaStatistics(HttpServletRequest request, HttpServletResponse response,
			BizProgram bizProgram){
		ModelAndView model = new ModelAndView("/radio/statistics/area_statistics");
		ProgramListServiceImpl service  = (ProgramListServiceImpl) getService();
		List<SocalAppModel> arealist = service.selectAreaStatistics(null);
		if(null == arealist){
			arealist = new ArrayList<SocalAppModel>();
		}
		model.addObject("areaList", arealist);
		return model;
	}
	
	
	/**
	 * Description：<br> 
	 * 用户性别类别统计
	 * @author  heng.wang
	 * @date    2015年10月28日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toUserSexCategoryTotal")
	public ModelAndView toUserSexCategoryTotal(HttpServletRequest request, HttpServletResponse response,
			BizProgram bizProgram){
		ModelAndView model = new ModelAndView("/radio/statistics/user_sex_category");
		return model;
	}
	
	
	/**
	 * 
	 * Description：<br> 
	 * 用户性别统计
	 * @author  heng.wang
	 * @date    2015年10月28日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/selecUserSexTotal")
	@ResponseBody
	public void selecUserSexTotal(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> tojson = new HashMap<String, Object>();
		try {
			ProgramListServiceImpl service  = (ProgramListServiceImpl) getService();
			tojson = service.selecUserSexTotal();
			tojson.put("message",HttpMessage.SUCCESS_CODE);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("用户性别统计出错" + e.getMessage());
			}
			tojson.put("message","用户性别统计出错数据异常,请联系管理员!");
		} 
		outputJson(tojson, response);
	}
	
	
	
	
}
