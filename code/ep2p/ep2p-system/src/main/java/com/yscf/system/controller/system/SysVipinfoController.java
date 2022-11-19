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
package com.yscf.system.controller.system;

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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.core.model.system.SysVipCondRel;
import com.yscf.core.model.system.SysVipinfo;
import com.yscf.core.service.system.impl.SysVipinfoServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;

/**
 * 
 * @ClassName : SysVipinfoController
 * @Description : VIP信息Controller
 * @Author : Qing.Cai
 * @Date : 2015年11月3日 上午10:18:44
 */
@Controller
@RequestMapping("/sysVipinfoController")
public class SysVipinfoController extends EscfBaseController {
	private Logger logger = LoggerFactory.getLogger(SysVipinfoController.class);

	@Autowired
	public SysVipinfoController(SysVipinfoServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return SysVipinfo.class;
	}

	/**
	 * 
	 * @Description : 跳转到VIP信息设定首页
	 * @return sysVipinfo_index.jsp
	 * @Author : Qing.Cai
	 * @Date : 2015年11月3日 上午10:23:51
	 */
	@RequestMapping("/openSysVipinfo")
	public ModelAndView openSysVipinfo() {
		return new ModelAndView("/system/sysVipinfo_index");
	}

	/**
	 * 
	 * @Description : 打开新增or编辑页面
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return sysVipinfo_add.jsp
	 * @Author : Qing.Cai
	 * @Date : 2015年11月3日 上午10:23:14
	 */
	@RequestMapping("/openSysVipinfoAdd")
	public ModelAndView openSysVipinfoAdd(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("/system/sysVipinfo_add");
		// 赋值
		modelAndView.addObject("pid", request.getParameter("pid") != null ? request.getParameter("pid") : 0);
		return modelAndView;
	}

	/**
	 * 
	 * @Description : 查询VIP信息列表,带分页
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param sysVipinfo
	 *            VIP信息对象
	 * @return VIP信息列表
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2015年11月3日 上午10:22:26
	 */
	@RequestMapping(value = "/querySysVipinfoList")
	@ResponseBody
	public ModelAndView querySysVipinfoList(HttpServletRequest request, HttpServletResponse response, SysVipinfo sysVipinfo) throws HttpRequestException {
		SysVipinfoServiceImpl service = (SysVipinfoServiceImpl) getService();
		ModelAndView modelView = new ModelAndView();
		try {
			PageInfo info = getPageInfo(request);
			if (null == sysVipinfo) {
				sysVipinfo = new SysVipinfo();
			}
			PageList<SysVipinfo> list = service.selectAllPage(sysVipinfo, info);
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
	 * @Description : 根据主键查询VIP详细信息
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param pid
	 *            VIP信息主键
	 * @return VIP信息对象
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2015年11月3日 上午10:21:16
	 */
	@RequestMapping(value = "/getSysVipinfoByPid")
	@ResponseBody
	public ModelAndView getSysVipinfoByPid(HttpServletRequest request, HttpServletResponse response, String pid) throws HttpRequestException {
		SysVipinfoServiceImpl service = (SysVipinfoServiceImpl) getService();
		ModelAndView modelView = new ModelAndView();
		try {
			// 创建需要获取的对象名称
			SysVipinfo sysVipinfo = new SysVipinfo();
			sysVipinfo = (SysVipinfo) service.selectByPrimaryKey(pid);
			modelView.addObject("sysVipinfo", sysVipinfo);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("查询VIP信息详细信息失败", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * @Description : VIP信息编辑操作
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param sysVipinfo
	 *            VIP信息对象
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2015年11月3日 上午10:19:14
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public void save(HttpServletRequest request, HttpServletResponse response, SysVipinfo sysVipinfo) throws HttpRequestException {
		SysVipinfoServiceImpl service = (SysVipinfoServiceImpl) getService();
		Map<String, Object> tojson = new HashMap<String, Object>();
		try {
			// 赋值创建人信息
			sysVipinfo.setCreateUser(getContextUser().getUserId());
			// 业务放到servicce层处理
			service.sysVipinfoEdit(sysVipinfo);
			tojson.put("message", HttpMessage.SUCCESS_CODE);
		} catch (FrameworkException e) {
			if (logger.isDebugEnabled()) {
				logger.info("VIP信息新增or编辑失败", e.getMessage());
				e.printStackTrace();
			}
			tojson.put("message", "操作失败！");
		}
		outputJson(tojson, response);
	}

	/**
	 * 
	 * @Description : 批量删除
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param pids
	 *            VIP信息对象主键字符串(1,2,3)
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2015年11月3日 上午10:19:46
	 */
	@RequestMapping(value = "/deleteBatch")
	@ResponseBody
	public void deleteBatch(HttpServletRequest request, HttpServletResponse response, String pids) throws HttpRequestException {
		SysVipinfoServiceImpl service = (SysVipinfoServiceImpl) getService();
		Map<String, Object> tojson = new HashMap<String, Object>();
		try {
			// 业务放到servicce层处理
			service.deleteBatch(pids);
			tojson.put("message", HttpMessage.SUCCESS_CODE);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("批量删除VIP信息失败", e.getMessage());
				e.printStackTrace();
			}
			tojson.put("message", "操作失败！");
		}
		outputJson(tojson, response);
	}

	/**
	 * 
	 * @Description : 根据vipId查询当前VIP已选条件信息
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param vipId
	 *            VIP主键
	 * @return 已选条件信息列表
	 * @Author : Qing.Cai
	 * @Date : 2015年11月3日 下午5:08:00
	 */
	@RequestMapping(value = "/seleCondRelByVipId")
	@ResponseBody
	public ModelAndView seleCondRelByVipId(HttpServletRequest request, HttpServletResponse response, String vipId) {
		SysVipinfoServiceImpl service = (SysVipinfoServiceImpl) getService();
		ModelAndView modelView = new ModelAndView();
		try {
			List<SysVipCondRel> list = service.seleCondRelByVipId(vipId);
			modelView.addObject("data", list);
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
