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

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.core.model.activity.PubRedpacket;
import com.yscf.core.service.activity.impl.PubRedpacketServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;

/**
 * 
 * @ClassName	:  PubRedpacketController
 * @Description	:  红包信息控制器
 * @Author 		:  Qing.Cai 
 * @Date		:  2015年10月8日 下午4:54:24
 */
@Controller
@RequestMapping("/pubRedpacketController")
public class PubRedpacketController extends EscfBaseController {
	private Logger logger = LoggerFactory.getLogger(PubRedpacketController.class);

	@Autowired
	public PubRedpacketController(PubRedpacketServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return PubRedpacket.class;
	}

	/**
	 * 
	 * @Description : 跳转到抢红包设置列表
	 * @return pubRedpacket_index.jsp
	 * @Author : Qing.Cai
	 * @Date : 2015年9月17日 下午4:58:36
	 */
	@RequestMapping("/openPubRedpacket")
	public ModelAndView openPubRedpacket() {
		return new ModelAndView("/activity/pubRedpacket_index");
	}
	
	/**
	 * 
	 * @Description : 跳转到抢红包设置列表
	 * @return pubRedpacket_song_index.jsp
	 * @Author : Qing.Cai
	 * @Date : 2015年10月9日 下午2:58:36
	 */
	@RequestMapping("/openPubRedpacketSong")
	public ModelAndView openPubRedpacketSong() {
		return new ModelAndView("/activity/pubRedpacket_song_index");
	}

	/**
	 * 
	 * @Description : 跳转到新增/编辑红包页面
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return pubRedpacket_add.jsp
	 * @Author : Qing.Cai
	 * @Date : 2015年10月8日 下午3:33:54
	 */
	@RequestMapping("/openPubRedpacketAdd")
	public ModelAndView openPubRedpacketAdd(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("/activity/pubRedpacket_add");
		// 赋值
		modelAndView.addObject("pid", request.getParameter("pid") != null ? request.getParameter("pid") : 0);
		return modelAndView;
	}
	
	/**
	 * 
	 * @Description : 跳转到新增/编辑送红包页面
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return pubRedpacket_song_add.jsp
	 * @Author : Qing.Cai
	 * @Date : 2015年10月8日 下午3:33:54
	 */
	@RequestMapping("/openPubRedpacketAddSong")
	public ModelAndView openPubRedpacketAddSong(HttpServletRequest request, HttpServletResponse response) {
		ModelAndView modelAndView = new ModelAndView("/activity/pubRedpacket_song_add");
		// 赋值
		modelAndView.addObject("pid", request.getParameter("pid") != null ? request.getParameter("pid") : 0);
		return modelAndView;
	}

	/**
	 * 
	 * @Description : 查询抢红包信息列表,带分页
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param pubRedpacket
	 *            红包对象
	 * @return
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2015年10月8日 下午4:43:31
	 */
	@RequestMapping(value = "/queryPubRedpacketList")
	@ResponseBody
	public ModelAndView queryPubRedpacketList(HttpServletRequest request, HttpServletResponse response, PubRedpacket pubRedpacket) throws HttpRequestException {
		PubRedpacketServiceImpl service = (PubRedpacketServiceImpl) getService();
		ModelAndView modelView = new ModelAndView();
		try {
			PageInfo info = getPageInfo(request);
			if (null == pubRedpacket) {
				pubRedpacket = new PubRedpacket();
			}
			// 查询所有抢红包类型的数据
			pubRedpacket.setRedpacketType("2");
			PageList<PubRedpacket> list = service.selectAllPage(pubRedpacket, info);
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
	 * @Description : 查询送红包信息列表,带分页
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param pubRedpacket
	 *            红包对象
	 * @return
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2015年10月8日 下午4:43:31
	 */
	@RequestMapping(value = "/queryPubRedpacketListSong")
	@ResponseBody
	public ModelAndView queryPubRedpacketListSong(HttpServletRequest request, HttpServletResponse response, PubRedpacket pubRedpacket) throws HttpRequestException {
		PubRedpacketServiceImpl service = (PubRedpacketServiceImpl) getService();
		ModelAndView modelView = new ModelAndView();
		try {
			PageInfo info = getPageInfo(request);
			if (null == pubRedpacket) {
				pubRedpacket = new PubRedpacket();
			}
			// 查询所有送红包类型的数据
			pubRedpacket.setRedpacketType("1");
			PageList<PubRedpacket> list = service.selectAllPage(pubRedpacket, info);
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
	 * @Description : 根据主键获取对象
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param pid
	 *            主键ID
	 * @return
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2015年10月8日 下午2:19:08
	 */
	@RequestMapping(value = "/getPubRedpacketByPid")
	@ResponseBody
	public ModelAndView getPubRedpacketByPid(HttpServletRequest request, HttpServletResponse response, String pid) throws HttpRequestException {
		PubRedpacketServiceImpl service = (PubRedpacketServiceImpl) getService();
		ModelAndView modelView = new ModelAndView();
		try {
			// 创建需要获取的对象名称
			PubRedpacket pubRedpacket = new PubRedpacket();
			pubRedpacket = (PubRedpacket) service.selectByPrimaryKey(pid);
			modelView.addObject("pubRedpacket", pubRedpacket);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("查询红包活动详细信息失败", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * @Description : 编辑红包活动
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param pubRedpacket
	 *            红包对象
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2015年10月8日 上午11:21:39
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public void save(HttpServletRequest request, HttpServletResponse response, PubRedpacket pubRedpacket) throws HttpRequestException {
		PubRedpacketServiceImpl service = (PubRedpacketServiceImpl) getService();
		Map<String, Object> tojson = new HashMap<String, Object>();
		try {// 赋值创建人信息
			pubRedpacket.setCreateUser(getContextUser().getUserId());
			// 业务放到servicce层处理
			service.pubRedpacketEdit(pubRedpacket);
			tojson.put("message", HttpMessage.SUCCESS_CODE);
		} catch (FrameworkException e) {
			if (logger.isDebugEnabled()) {
				logger.info("红包活动新增or编辑失败", e.getMessage());
				e.printStackTrace();
			}
			tojson.put("message", "操作失败！");
		}
		outputJson(tojson, response);
	}

	/**
	 * 
	 * @Description : 批量删除红包活动
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param pids
	 *            红包ID(1,2,3)
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2015年10月8日 下午6:59:14
	 */
	@RequestMapping(value = "/deleteBatch")
	@ResponseBody
	public void deleteBatch(HttpServletRequest request, HttpServletResponse response, String pids) throws HttpRequestException {
		PubRedpacketServiceImpl service = (PubRedpacketServiceImpl) getService();
		Map<String, Object> tojson = new HashMap<String, Object>();
		try {
			// 业务放到servicce层处理
			service.deleteBatch(pids);
			tojson.put("message", HttpMessage.SUCCESS_CODE);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("除红包失败", e.getMessage());
				e.printStackTrace();
			}
			tojson.put("message", "操作失败！");
		}
		outputJson(tojson, response);
	}

}
