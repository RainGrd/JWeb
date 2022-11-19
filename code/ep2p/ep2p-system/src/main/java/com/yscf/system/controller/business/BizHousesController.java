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
 * 2015年9月22日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.system.controller.business;

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
import com.achievo.framework.util.DateUtil;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.common.Constant.Constant;
import com.yscf.core.model.business.BizHouses;
import com.yscf.core.service.business.IBizHousesService;
import com.yscf.core.service.business.impl.BizHousesServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;

/**
 * Description：<br> 
 * 楼盘信息 Controller
 * @author  Yu.Zhang
 * @date    2015年9月22日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/bizHousesController")
public class BizHousesController extends EscfBaseController {

	private Logger logger = LoggerFactory.getLogger(BizHousesController.class);
	
	@Autowired
	public BizHousesController(BizHousesServiceImpl service) {
		super(service);
	}
	
	@Override
	public Class<?> getModel() {
		return BizHouses.class;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 跳转到楼盘管理页面
	 * @author  Yu.Zhang
	 * @date    2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toList")
	@ResponseBody
	public ModelAndView toList(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("/business/house/house_manager_list");
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 跳转到楼盘管理新增页面
	 * @author  Yu.Zhang
	 * @date    2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toAdd")
	@ResponseBody
	public ModelAndView toAdd(String pid ,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("/business/house/house_manager_add");
		modelView.addObject("pid", pid);
		return modelView;
	}
	
	
	/**
	 * 
	 * Description：<br> 
	 * 楼盘管理保存
	 * @author  Yu.Zhang
	 * @date    2015年10月15日
	 * @version v1.0.0
	 * @param bizHouses
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		BizHouses bizHouses = null;
		try {
			IBizHousesService service  = (IBizHousesService) getService();
			bizHouses = (BizHouses) getEntity(request);
			// 不为空修改，为空新增
			if(null!=bizHouses && null!=bizHouses.getPid() && !"".equals(bizHouses.getPid().trim())){
				bizHouses.setLastUpdateTime(DateUtil.getToday());
				bizHouses.setLastUpdateUser(getUserId());
				service.updateByPrimaryKeySelective(bizHouses);
			}else{
				bizHouses.setCreateTime(DateUtil.getToday());
				bizHouses.setCreateUser(getUserId());
				bizHouses.setStatus(Constant.ACTIVATE);
				getService().insert(bizHouses);
			}
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (FrameworkException e) {
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
	 * 数据字典批量删除
	 * @author  Yu.Zhang
	 * @date    2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/delete")
	@ResponseBody
	public ModelAndView delete(String pid,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		
		ModelAndView modelView = new ModelAndView();
		try {
			BizHouses bizHouses = new BizHouses() ;
			bizHouses.setPid(pid);
			bizHouses.setStatus(Constant.DISABLE);
			bizHouses.setLastUpdateTime(DateUtil.getToday());
			bizHouses.setLastUpdateUser(getUserId());
			IBizHousesService service  = (IBizHousesService) getService();
			service.updateByPrimaryKeySelective(bizHouses);
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
	 * 数据字典查询,带分页
	 * @author  Yu.Zhang
	 * @date    2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectAllPage")
	@ResponseBody
	public ModelAndView selectAllPage(BizHouses bizHouses,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		
		ModelAndView modelView = new ModelAndView();
		try {
			PageInfo info = getPageInfo(request);
			// 只查询有效数据
			bizHouses.setStatus(Constant.ACTIVATE);
			IBizHousesService service  = (IBizHousesService) getService();
			PageList<BizHouses> pageList = service.selectAllPage(bizHouses,info);
		    modelView.addObject("rows",pageList);
		    modelView.addObject("total",pageList.getPaginator().getTotalCount());
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
	 * 查询过滤重复的省
	 * @author  Yu.Zhang
	 * @date    2015年9月22日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectDistinctProvince")
	@ResponseBody
	public ModelAndView selectDistinctProvince(BizHouses bizHouses,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			BizHousesServiceImpl service = (BizHousesServiceImpl) getService();
			List<BizHouses> list = service.selectDistinctProvince(bizHouses);
			modelView.addObject("data", list);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("新增借款,获取楼盘省信息失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 根据省查询过滤重复的市
	 * @author  Yu.Zhang
	 * @date    2015年9月22日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectDistinctCityByProvince", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView selectDistinctCityByProvince(BizHouses bizHouses,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			BizHousesServiceImpl service = (BizHousesServiceImpl) getService();
			bizHouses.setHomesProvince(new String(bizHouses.getHomesProvince().getBytes("8859_1"),"UTF-8"));
			List<BizHouses> list = service.selectDistinctCityByProvince(bizHouses);
			modelView.addObject("data", list);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("新增借款,获取楼盘市信息失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 根据省,市 查询过滤重复的区
	 * @author  Yu.Zhang
	 * @date    2015年9月22日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectDistinctAreaByCity")
	@ResponseBody
	public ModelAndView selectDistinctAreaByCity(BizHouses bizHouses,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			BizHousesServiceImpl service = (BizHousesServiceImpl) getService();
			bizHouses.setHomesProvince(new String(bizHouses.getHomesProvince().getBytes("8859_1"),"UTF-8"));
			bizHouses.setHomesCity(new String(bizHouses.getHomesCity().getBytes("8859_1"),"UTF-8"));
			List<BizHouses> list = service.selectDistinctAreaByCity(bizHouses);
			modelView.addObject("data", list);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("新增借款,获取楼盘区信息失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 根据省,市,区 查询过滤重复的楼盘名称
	 * @author  Yu.Zhang
	 * @date    2015年9月22日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectDistinctHomesNameByAddress")
	@ResponseBody
	public ModelAndView selectDistinctHomesNameByAddress(BizHouses bizHouses,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			bizHouses.setHomesProvince(new String(bizHouses.getHomesProvince().getBytes("8859_1"),"UTF-8"));
			bizHouses.setHomesCity(new String(bizHouses.getHomesCity().getBytes("8859_1"),"UTF-8"));
			bizHouses.setHomesArea(new String(bizHouses.getHomesArea().getBytes("8859_1"),"UTF-8"));
			BizHousesServiceImpl service = (BizHousesServiceImpl) getService();
			List<BizHouses> list = service.selectDistinctHomesNameByAddress(bizHouses);
			modelView.addObject("data", list);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("新增借款,获取楼盘名称信息失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 根据省,市,区,楼盘名称 查询户型
	 * @author  Yu.Zhang
	 * @date    2015年9月22日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectDistinctHomesTypeByHomesName")
	@ResponseBody
	public ModelAndView selectDistinctHomesTypeByHomesName(BizHouses bizHouses,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			bizHouses.setHomesProvince(new String(bizHouses.getHomesProvince().getBytes("8859_1"),"UTF-8"));
			bizHouses.setHomesCity(new String(bizHouses.getHomesCity().getBytes("8859_1"),"UTF-8"));
			bizHouses.setHomesArea(new String(bizHouses.getHomesArea().getBytes("8859_1"),"UTF-8"));
			bizHouses.setHomesName(new String(bizHouses.getHomesName().getBytes("8859_1"),"UTF-8"));
			BizHousesServiceImpl service = (BizHousesServiceImpl) getService();
			List<BizHouses> list = service.selectDistinctHomesTypeByHomesName(bizHouses);
			modelView.addObject("data", list);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("新增借款,获取楼盘户型信息失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 根据ID查询楼盘信息
	 * @author  heng.wang
	 * @date    2015年9月16日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/getByPid")
	@ResponseBody
	public ModelAndView getByPid(String pid,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			BizHouses borrow = (BizHouses) getService().selectByPrimaryKey(pid);
			modelView.addObject("result", borrow);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (FrameworkException e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据PID获取楼盘信息失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

}


