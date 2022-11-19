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
package com.yscf.system.controller.customer;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.achievo.framework.constant.ComExcelConstants;
import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.excel.domain.CheckResult;
import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.security.domain.ContextUser;
import com.achievo.framework.util.DateUtil;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.common.Constant.Constant;
import com.yscf.core.model.activity.ActExpActDetail;
import com.yscf.core.model.activity.ActInvAwaActDetail;
import com.yscf.core.model.business.BizReceiptPlan;
import com.yscf.core.model.comm.ExportObjectModel;
import com.yscf.core.model.user.Bank;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.model.user.CusUpdateServiceWater;
import com.yscf.core.model.user.CustBidStatusWater;
import com.yscf.core.model.user.CustBlacklistStatusWater;
import com.yscf.core.model.user.CustDueinWater;
import com.yscf.core.model.user.CustExperienceWater;
import com.yscf.core.model.user.CustFreezeWater;
import com.yscf.core.model.user.CustLargessVipWater;
import com.yscf.core.model.user.CustPoinWater;
import com.yscf.core.model.user.CustStatusWater;
import com.yscf.core.model.user.CustTransferStatusWater;
import com.yscf.core.model.user.CustWitStatusWater;
import com.yscf.core.service.activity.impl.ActExpActDetailServiceImpl;
import com.yscf.core.service.activity.impl.ActInvAwaActDetailServiceImpl;
import com.yscf.core.service.business.IBizReceiptPlanService;
import com.yscf.core.service.user.ICusUpdateServiceWaterService;
import com.yscf.core.service.user.impl.BankServiceImpl;
import com.yscf.core.service.user.impl.CusTomerServiceImpl;
import com.yscf.core.service.user.impl.CustBidStatusWaterServiceImpl;
import com.yscf.core.service.user.impl.CustBlacklistStatusWaterServiceImpl;
import com.yscf.core.service.user.impl.CustDueinWaterServiceImpl;
import com.yscf.core.service.user.impl.CustExperienceServiceImpl;
import com.yscf.core.service.user.impl.CustFreezeAmountWaterServiceImpl;
import com.yscf.core.service.user.impl.CustLargessVipWaterServiceImpl;
import com.yscf.core.service.user.impl.CustPoinWaterServiceImpl;
import com.yscf.core.service.user.impl.CustStatusWaterServiceImpl;
import com.yscf.core.service.user.impl.CustTransferStatusWaterServiceImpl;
import com.yscf.core.service.user.impl.CustWitStatusWaterServiceImpl;
import com.yscf.core.service.userinfo.impl.UserCenterServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;
import com.yscf.system.dto.ExportVoToModelDto;
import com.yscf.system.util.FileDownload;
import com.yscf.system.vo.ExportObjectVo;

/**
 * Description：<br>
 * 客户管理
 * @author heng.wang
 * @date 2015年9月14日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/customerController")
public class CustomerController extends EscfBaseController {
	private Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Resource(name = "cusUpdateServiceWaterServiceImpl")
	private ICusUpdateServiceWaterService cusUpdateServiceWaterServiceImpl;
	//赠送vip
	@Resource(name = "custLargessVipWaterServiceImpl")
	private CustLargessVipWaterServiceImpl custLargessVipWaterServiceImpl;
	
	//客户状态流水
	@Resource(name = "custStatusWaterServiceImpl")
	private CustStatusWaterServiceImpl custStatusWaterServiceImpl;
	//客户提现状态流水
	@Resource(name = "custWitStatusWaterServiceImpl")
	private CustWitStatusWaterServiceImpl custWitStatusWaterServiceImpl;
	//黑名单客户状态流水
	@Resource(name = "custBlacklistStatusWaterServiceImpl")
	private CustBlacklistStatusWaterServiceImpl custBlacklistStatusWaterServiceImpl;
	//投标客户状态流水
	@Resource(name = "custBidStatusWaterServiceImpl")
	private CustBidStatusWaterServiceImpl custBidStatusWaterServiceImpl;
	//债权转让客户状态流水
	@Resource(name = "custTransferStatusWaterServiceImpl")
	private CustTransferStatusWaterServiceImpl custTransferStatusWaterServiceImpl;
	//客户积分明细
	@Resource(name = "custPoinWaterServiceImpl")
	private CustPoinWaterServiceImpl custPoinWaterServiceImpl;
	//客户经验明细
	@Resource(name = "custExperienceServiceImpl")
	private CustExperienceServiceImpl custExperienceServiceImpl;
	//客户冻结金额明细
	@Resource(name = "custFreezeAmountWaterServiceImpl")
	private CustFreezeAmountWaterServiceImpl custFreezeAmountWaterServiceImpl;
	//客户积分明细
	@Resource(name = "actInvAwaActDetailServiceImpl")
	private ActInvAwaActDetailServiceImpl actInvAwaActDetailServiceImpl;
	//客户体验金明细
	@Resource(name = "actExpActDetailServiceImpl")
	private ActExpActDetailServiceImpl actExpActDetailServiceImpl;
	
	@Resource(name = "custDueinWaterServiceImpl")
	private CustDueinWaterServiceImpl custDueinWaterServiceImpl;
	
	//客户银行卡明细
	@Resource(name = "bankService")
	private BankServiceImpl bankService;
	
	//加息
	@Resource(name = "userCenterServiceImpl")
	private UserCenterServiceImpl userCenterServiceImpl;
	
	//客户待收本金明细
	@Resource
	public IBizReceiptPlanService bizReceiptPlanService;
	
	@Autowired
	public CustomerController(CusTomerServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return CusTomer.class;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 跳转到客户查询页面
	 * @author  heng.wang
	 * @date    2015年9月10日
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
		return new ModelAndView("/customer/customerSearchList");
	}
	/**
	 * 
	 * Description：<br> 
	 * 客户查询列表页面
	 * @author  heng.wang
	 * @date    2015年9月15日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/getCustomerSearchList")
	@ResponseBody
	public ModelAndView getCustomerSearchList(CusTomer cusTomer,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		CusTomerServiceImpl service  = (CusTomerServiceImpl) getService();
		ModelAndView modelView = new ModelAndView();
	   try {
			PageInfo info = getPageInfo(request);
			if(null == cusTomer){
				cusTomer = new CusTomer();
			}
			cusTomer.setPid(super.getUserId());
 			PageList<CusTomer> list = service.selectAllPage(cusTomer,info);
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
	 * 跳转到批量更换客服
	 * @author  heng.wang
	 * @date    2015年9月16日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toBatchReplaceCus")
	@ResponseBody
	public ModelAndView toBatchReplaceCus(String oldcusServiceId,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("/customer/batchReplaceCustomer");
		modelView.addObject("pid", request.getParameter("pid"));
		modelView.addObject("oldcusServiceId", oldcusServiceId);
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 客服查询列表
	 * @author  heng.wang
	 * @date    2015年9月16日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/getBatchReplaceCusList")
	@ResponseBody
	public ModelAndView getBatchReplaceCusList(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		return new ModelAndView("/customer/batchReplaceCustomer");
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 根据根据客户ID查询客服
	 * @author  heng.wang
	 * @date    2015年10月16日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectCustomer")
	@ResponseBody
	public ModelAndView selectCustomer(HttpServletRequest request, HttpServletResponse response)throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			List<CusTomer> list = new ArrayList<CusTomer>();
			CusTomerServiceImpl service  = (CusTomerServiceImpl) getService();
			String userId =getContextUser().getUserId();
			CusTomer cusTomer = new CusTomer();
			cusTomer.setCusServicePid("");
			cusTomer.setCusServiceName("-- 请选择 --");
			list.add(cusTomer);
			list.addAll(service.selectCustomerServiceById(userId));
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
	
	/**
	 * 
	 * Description：<br> 
	 * 查客户vip等级
	 * @author  heng.wang
	 * @date    2015年10月28日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectVipLevel")
	@ResponseBody
	public ModelAndView selectVipLevel(HttpServletRequest request, HttpServletResponse response)throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			List<CusTomer> list = new ArrayList<CusTomer>();
			CusTomerServiceImpl service  = (CusTomerServiceImpl) getService();
			CusTomer cusTomer = new CusTomer();
			cusTomer.setVipPID("");
			//cusTomer.setVipLevelName("-- 请选择 --");
			list.add(cusTomer);
			list.addAll(service.selectVipLevel());
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
	
	/**
	 * 
	 * Description：<br> 
	 * 根据ID查询批量客服
	 * @author  heng.wang
	 * @date    2015年9月16日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/getCusByPid")
	@ResponseBody
	public ModelAndView getCusByPid(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			String pid = request.getParameter("pid");
			PageList<CusTomer> list  = ((CusTomerServiceImpl) getService()).selectCusByPid(pid);
			modelView.addObject("rows", list);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据PID获取客服失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 根据ID批量更新客服
	 * @author  heng.wang
	 * @date    2015年9月16日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/batchUpdateCus")
	@ResponseBody
	public ModelAndView batchUpdateCus(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		CusTomerServiceImpl service  = (CusTomerServiceImpl) getService();
		try {
			ContextUser cu = getContextUser();
			CusTomer cusTomer = (CusTomer) getEntity(request);
			//设置客服id
			cusTomer.setCustomerServiceUser(cusTomer.getCusServicePid());
			//设置旧客服iD
			cusTomer.setOldcusServiceId(cusTomer.getOldcusServiceId());
			//设置客户id
			cusTomer.setPid(cusTomer.getPid());
			//根据客户pid批量更新客服id
			service.updatCusIdByPrimaryKey(cusTomer);
			//更新 客户更换客服流水表
			//cusUpdateServiceWaterServiceImpl.updatCusIdByPrimaryKey(cusTomer);
			cusTomer.setCreateTime(DateUtil.format(new Date()));
			cusTomer.setCreateUser(cu.getUserName());
			cusUpdateServiceWaterServiceImpl.insertCustWater(cusTomer);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据PID更新客服失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 根据ID查询客户
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
		if("".equals(pid) && pid==null){
			pid=super.getUserId();
		}
		try {
			CusTomer cus = (CusTomer) getService().selectByPrimaryKey(pid);
			modelView.addObject("result", cus);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据PID获取客户失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	
	/**
	 * 
	 * Description：<br> 
	 * 保存客户信息
	 * @author  heng.wang
	 * @date    2015年9月16日
	 * @version v1.0.0
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
		try {
			CusTomerServiceImpl service  = (CusTomerServiceImpl) getService();
			CusTomer cusTomer = (CusTomer) getEntity(request);
			service.updateByPrimaryKeySelective(cusTomer);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("保存客户信息失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 跳到客户操作页面
	 * @author  heng.wang
	 * @date    2015年9月18日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toCustomerOperationPage")
	@ResponseBody
	public ModelAndView toCustomerOperationPage(String oldcusServiceId,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelAndView = new ModelAndView("/customer/toCustomerOperationPage");
		modelAndView.addObject("pid", request.getParameter("pid"));
		modelAndView.addObject("oldcusServiceId", oldcusServiceId);
		return modelAndView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 客户历史查看页面
	 * @author  heng.wang
	 * @date    2015年9月18日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/customerHistoryView")
	@ResponseBody
	public ModelAndView customerHistoryView(String pid,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelAndView = new ModelAndView("/customer/customerHistoryView");
		modelAndView.addObject("pid", pid);
		return modelAndView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 根据pid查看客户详情
	 * @author  heng.wang
	 * @date    2015年9月18日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectCusHistoryDetailedById")
	@ResponseBody
	public ModelAndView selectCusHistoryDetailedById(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			BaseEntity entity = getEntity(request);
			CusTomer cusTomer = (CusTomer) entity;
			if(null == cusTomer){
				cusTomer = new CusTomer();
			}
			PageList<CusTomer> list  = ((CusTomerServiceImpl) getService()).selectCusHistoryDetailedById(cusTomer.getPid());
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
	 * 根据pid查看客户历史详情
	 * @author  heng.wang
	 * @date    2015年9月21日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/cusHistoryDetailedById")
	@ResponseBody
	public ModelAndView cusHistoryDetailedById(String pid,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		PageInfo info = getPageInfo(request);
		try {
			PageList<CusUpdateServiceWater> list  = cusUpdateServiceWaterServiceImpl.cusHistoryDetailedById(pid,info);
			modelView.addObject("rows", list);
			modelView.addObject("total", list.getPaginator().getTotalCount());
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据PID获取客服历史信息失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 跳转到赠送VIP页面
	 * @author  heng.wang
	 * @date    2015年9月122日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toGiveVipList")
	@ResponseBody
	public ModelAndView toGiveVipList(HttpServletRequest request, HttpServletResponse response,String pid)
			throws HttpRequestException {
		ModelAndView modelAndView = new ModelAndView("/customer/toGiveVipList");
		modelAndView.addObject("pid", pid);
		return modelAndView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 根据pid查看赠送vip历史详情
	 * @author  heng.wang
	 * @date    2015年9月22日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/giveVipHistoryDetailedById")
	@ResponseBody
	public ModelAndView giveVipHistoryDetailedById(String pid,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		PageInfo info = getPageInfo(request);
		try {
			PageList<CusUpdateServiceWater> list  = cusUpdateServiceWaterServiceImpl.cusHistoryDetailedById(pid,info);
			modelView.addObject("rows", list);
			 modelView.addObject("total", list.getPaginator().getTotalCount());
			 MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据PID获取客服历史信息失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 根据客户ID保存赠送时长
	 * @author  heng.wang
	 * @date    2015年9月22日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/saveGiveVip")
	@ResponseBody
	public ModelAndView saveGiveVip(String largessValue,String vipInfoId,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			CusTomer cusTomer = (CusTomer) getEntity(request);
			cusTomer.setLargessValue(largessValue);
			CustLargessVipWater custLargessVipWater = new CustLargessVipWater();
			PageList<CusTomer> list  = ((CusTomerServiceImpl) getService()).selectCusHistoryDetailedById(cusTomer.getPid());
			String isVip = list.get(0).getIsVip();
			
			//1:判断是否是vip
			if(isVip.equals("是")){
				//设置客户id
				custLargessVipWater.setPid(custLargessVipWater.getPK());         
				custLargessVipWater.setCustomerId(cusTomer.getPid());
				//设置vip信息表的pid
				//custLargessVipWater.setVipNo(cusTomer.getVipInfoPid());
				custLargessVipWater.setVipNo(vipInfoId);
				custLargessVipWater.setStatus("1");
				custLargessVipWater.setLargessValue(cusTomer.getLargessValue());
				custLargessVipWater.setLarVipWatDesc(cusTomer.getLarVipWatDesc());
				//设置创建时间
				custLargessVipWater.setCreateTime(DateUtil.format(DateUtil.getToday()));
				//设置分配时间
				custLargessVipWater.setDistributionTime(DateUtil.format(DateUtil.getToday()));
				//获取vip时长
				int vipTime =list.get(0).getVipTime();//之前是data数据类型
				//获取赠送天数
				int day = Integer.parseInt(cusTomer.getLargessValue());
				Date newDay=new DateTime(vipTime).plusDays(day).toDate();
				cusTomer.setVipTimeTwo(newDay);
				//2:插入赠送客户vip流水表
				custLargessVipWaterServiceImpl.insert(custLargessVipWater);
				//3:更新客户表的vip到期时间
				((CusTomerServiceImpl) getService()).updateByPrimaryKeySelective(cusTomer);
			}
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("新增赠送vip客服失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 根据pid查看赠送vip历史详情
	 * @author  heng.wang
	 * @date    2015年9月22日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/getVipHistoryDetailedById")
	@ResponseBody
	public ModelAndView getVipHistoryDetailedById(String pid,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		PageInfo info = getPageInfo(request);
		try {
			PageList<CustLargessVipWater> list  = custLargessVipWaterServiceImpl.getVipHistoryDetailedById(pid,info);
			modelView.addObject("rows", list);
			 modelView.addObject("total", list.getPaginator().getTotalCount());
			 MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据PID获取VIP历史详细信息失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 跳转到禁止/启用该用户页面
	 * @author  heng.wang
	 * @date    2015年9月122日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toDisableOrEnableCusStatusList")
	@ResponseBody
	public ModelAndView toDisableOrEnableCusStatusList(HttpServletRequest request, HttpServletResponse response,String pid)
			throws HttpRequestException {
		ModelAndView modelAndView = new ModelAndView("/customer/disOrEnableCusStatus");
		modelAndView.addObject("pid", pid);
		return modelAndView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 根据客户ID保存客户状态
	 * @author  heng.wang
	 * @date    2015年9月23日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/saveCustomerStatus")
	@ResponseBody
	public ModelAndView saveCustomerStatus(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			CusTomer cusTomer = (CusTomer) getEntity(request);
			CustStatusWater custStatusWater = new CustStatusWater();
			//设置客户id
			custStatusWater.setPid(custStatusWater.getPK());
			custStatusWater.setCustomerId(cusTomer.getPid());
			//设置变更状态
			custStatusWater.setUpStatus(cusTomer.getCusStatus());
			//设置有效日期
			custStatusWater.setEffTime(DateUtil.getSystemDate());
			//设置失效日期
			custStatusWater.setExpTime(cusTomer.getInvalidDate());
			//设置分配时间
			custStatusWater.setDistributionTime(DateUtil.format(DateUtil.getToday()));
			custStatusWater.setUpDesc(cusTomer.getCusStatusRemark());
			//设置创建时间
			custStatusWater.setCreateTime(DateUtil.format(DateUtil.getToday()));
			//插入客户状态流水表
			custStatusWaterServiceImpl.insert(custStatusWater);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("禁止或启用客户状态失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 根据pid查看客户状态历史详情
	 * @author  heng.wang
	 * @date    2015年9月22日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/cusStatusChangeHistoryById")
	@ResponseBody
	public ModelAndView cusStatusChangeHistoryById(String pid,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		PageInfo info = getPageInfo(request);
		try {
			PageList<CustStatusWater> list  = custStatusWaterServiceImpl.cusStatusChangeHistoryById(pid,info);
			modelView.addObject("rows", list);
			 modelView.addObject("total", list.getPaginator().getTotalCount());
			 MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据PID获取客服状态信息失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 跳转到禁止/允许提现
	 * @author  heng.wang
	 * @date    2015年9月24日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toDisableOrAllowWithdrawalList")
	@ResponseBody
	public ModelAndView toDisableOrAllowWithdrawalList(HttpServletRequest request, HttpServletResponse response,String pid)
			throws HttpRequestException {
		ModelAndView modelAndView = new ModelAndView("/customer/disableOrAllowWithdrawalList");
		modelAndView.addObject("pid", pid);
		return modelAndView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 根据客户ID保存客户提现状态
	 * @author  heng.wang
	 * @date    2015年9月23日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/saveCustomerAllowStatus")
	@ResponseBody
	public ModelAndView saveCustomerAllowStatus(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			CusTomer cusTomer = (CusTomer) getEntity(request);
			CustWitStatusWater custWitStatusWater = new CustWitStatusWater();
			//设置客户id
			custWitStatusWater.setPid(custWitStatusWater.getPK());
			custWitStatusWater.setCustomerId(cusTomer.getPid());
			//设置变更状态
			custWitStatusWater.setWitStatus(cusTomer.getCusStatus());
			//设置有效日期
			custWitStatusWater.setEffTime(DateUtil.getSystemDate());
			//设置失效日期
			custWitStatusWater.setExpTime(cusTomer.getInvalidDate());
			//设置分配时间
			custWitStatusWater.setDistributionTime(DateUtil.format(DateUtil.getToday()));
			custWitStatusWater.setUpDesc(cusTomer.getCusStatusRemark());
			//设置创建时间
			custWitStatusWater.setCreateTime(DateUtil.format(DateUtil.getToday()));
			//插入客户提现状态流水表
			custWitStatusWaterServiceImpl.insert(custWitStatusWater);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("禁止或启用客户提现状态失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 根据pid查看客户提现状态历史详情
	 * @author  heng.wang
	 * @date    2015年9月24日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectCusStatusChangeHistoryById")
	@ResponseBody
	public ModelAndView selectCusStatusChangeHistoryById(String pid,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		PageInfo info = getPageInfo(request);
		try {
			PageList<CustWitStatusWater> list  = custWitStatusWaterServiceImpl.selectCusStatusChangeHistoryById(pid,info);
			modelView.addObject("rows", list);
			 modelView.addObject("total", list.getPaginator().getTotalCount());
			 MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据PID获取投标客服状态信息失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 跳转到禁止/启用黑名单
	 * @author  heng.wang
	 * @date    2015年9月24日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toDisableOrAllowBlacklistList")
	@ResponseBody
	public ModelAndView toDisableOrAllowBlacklistList(HttpServletRequest request, HttpServletResponse response,String pid)
			throws HttpRequestException {
		ModelAndView modelAndView = new ModelAndView("/customer/toDisableOrAllowBlacklistList");
		modelAndView.addObject("pid", pid);
		return modelAndView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 根据客户ID保存黑名单客户状态
	 * @author  heng.wang
	 * @date    2015年9月24日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/saveBlacklistCustomerAllowStatus")
	@ResponseBody
	public ModelAndView saveBlacklistCustomerAllowStatus(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			CusTomer cusTomer = (CusTomer) getEntity(request);
			CustBlacklistStatusWater custBlacklistStatusWater = new CustBlacklistStatusWater();
			//设置客户id
			custBlacklistStatusWater.setPid(custBlacklistStatusWater.getPK());
			custBlacklistStatusWater.setCustomerId(cusTomer.getPid());
			//设置变更状态
			custBlacklistStatusWater.setUpStatus(cusTomer.getCusStatus());
			//设置有效日期
			custBlacklistStatusWater.setEffTime(DateUtil.getSystemDate());
			//设置失效日期
			custBlacklistStatusWater.setExpTime(cusTomer.getInvalidDate());
			//设置分配时间
			custBlacklistStatusWater.setDistributionTime(DateUtil.format(DateUtil.getToday()));
			custBlacklistStatusWater.setUpDesc(cusTomer.getCusStatusRemark());
			//设置创建时间
			custBlacklistStatusWater.setCreateTime(DateUtil.format(DateUtil.getToday()));
			//插入黑名单客户状态流水表
			custBlacklistStatusWaterServiceImpl.insert(custBlacklistStatusWater);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("禁止或启用黑名单客户状态失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	/**
	 * 
	 * Description：<br> 
	 * 根据pid查看黑名单客户状态历史详情
	 * @author  heng.wang
	 * @date    2015年9月24日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectBlicklistCusStatusHistoryById")
	@ResponseBody
	public ModelAndView selectBlicklistCusStatusHistoryById(String pid,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		PageInfo info = getPageInfo(request);
		try {
			PageList<CustBlacklistStatusWater> list  = custBlacklistStatusWaterServiceImpl.selectBlicklistCusStatusHistoryById(pid,info);
			modelView.addObject("rows", list);
			 modelView.addObject("total", list.getPaginator().getTotalCount());
			 MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据PID获取黑名单客服状态信息失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 跳转到客户投标状态页面
	 * @author  heng.wang
	 * @date    2015年9月24日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toDisableOrAllowBidList")
	@ResponseBody
	public ModelAndView toDisableOrAllowBidList(HttpServletRequest request, HttpServletResponse response,String pid)
			throws HttpRequestException {
		ModelAndView modelAndView = new ModelAndView("/customer/toDisableOrAllowBidList");
		modelAndView.addObject("pid", pid);
		return modelAndView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 根据客户ID保存投标客户状态
	 * @author  heng.wang
	 * @date    2015年9月25日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/saveBidCustomerStatus")
	@ResponseBody
	public ModelAndView saveBidCustomerStatus(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			CusTomer cusTomer = (CusTomer) getEntity(request);
			CustBidStatusWater custBidStatusWater = new CustBidStatusWater();
			//设置客户id
			custBidStatusWater.setPid(custBidStatusWater.getPK());
			custBidStatusWater.setCustomerId(cusTomer.getPid());
			//设置变更状态
			custBidStatusWater.setUpStatus(cusTomer.getCusStatus());
			//设置有效日期
			custBidStatusWater.setEffTime(DateUtil.getSystemDate());
			//设置失效日期
			custBidStatusWater.setExpTime(cusTomer.getInvalidDate());
			//设置分配时间
			custBidStatusWater.setDistributionTime(DateUtil.format(DateUtil.getToday()));
			custBidStatusWater.setUpDesc(cusTomer.getCusStatusRemark());
			//设置创建时间
			custBidStatusWater.setCreateTime(DateUtil.format(DateUtil.getToday()));
			//插入投标客户状态流水表
			custBidStatusWaterServiceImpl.insert(custBidStatusWater);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("禁止或启用投标客户状态失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	/**
	 * 
	 * Description：<br> 
	 * 根据pid查看投标客户状态历史详情
	 * @author  heng.wang
	 * @date    2015年9月25日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectBidCusStatusHistoryById")
	@ResponseBody
	public ModelAndView selectBidCusStatusHistoryById(String pid,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		PageInfo info = getPageInfo(request);
		try {
			PageList<CustBidStatusWater> list  = custBidStatusWaterServiceImpl.selectBidCusStatusHistoryById(pid,info);
			modelView.addObject("rows", list);
			 modelView.addObject("total", list.getPaginator().getTotalCount());
			 MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据PID获取投标客服状态信息失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 跳转到客户债权状态页面
	 * @author  heng.wang
	 * @date    2015年9月24日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toDisableOrAllowCredRightList")
	@ResponseBody
	public ModelAndView toDisableOrAllowCredRightList(HttpServletRequest request, HttpServletResponse response,String pid)
			throws HttpRequestException {
		ModelAndView modelAndView = new ModelAndView("/customer/toDisableOrAllowCredRightList");
		modelAndView.addObject("pid", pid);
		return modelAndView;
	}
	/**
	 * 
	 * Description：<br> 
	 * 根据客户ID保存债权转让客户状态
	 * @author  heng.wang
	 * @date    2015年9月25日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/saveCredRightCustomerStatus")
	@ResponseBody
	public ModelAndView saveCredRightCustomerStatus(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			CusTomer cusTomer = (CusTomer) getEntity(request);
			CustTransferStatusWater custTransferStatusWater = new CustTransferStatusWater();
			//设置客户id
			custTransferStatusWater.setPid(custTransferStatusWater.getPK());
			custTransferStatusWater.setCustomerId(cusTomer.getPid());
			//设置变更状态
			custTransferStatusWater.setUpStatus(cusTomer.getCusStatus());
			//设置有效日期
			custTransferStatusWater.setEffTime(DateUtil.getSystemDate());
			//设置失效日期
			custTransferStatusWater.setExpTime(cusTomer.getInvalidDate());
			//设置分配时间
			custTransferStatusWater.setDistributionTime(DateUtil.format(DateUtil.getToday()));
			custTransferStatusWater.setUpDesc(cusTomer.getCusStatusRemark());
			//设置创建时间
			custTransferStatusWater.setCreateTime(DateUtil.format(DateUtil.getToday()));
			//插入投标客户状态流水表
			custTransferStatusWaterServiceImpl.insert(custTransferStatusWater);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("禁止或启用 债权转让客户状态失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	/**
	 * 
	 * Description：<br> 
	 * 根据pid查看投标客户状态历史详情
	 * @author  heng.wang
	 * @date    2015年9月25日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectCusCredRightStatusHistoryById")
	@ResponseBody
	public ModelAndView selectCusCredRightStatusHistoryById(String pid,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		PageInfo info = getPageInfo(request);
		try {
			PageList<CustTransferStatusWater> list  = custTransferStatusWaterServiceImpl.selectCusCredRightStatusHistoryById(pid,info);
			modelView.addObject("rows", list);
			 modelView.addObject("total", list.getPaginator().getTotalCount());
			 MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据PID获取债权转让客服状态信息失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 跳转到查看客户资料页面
	 * @author  heng.wang
	 * @date    2015年9月28日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/viewCustomerDataList")
	@ResponseBody
	public ModelAndView viewCustomerDataList(HttpServletRequest request, HttpServletResponse response,String pid)
			throws HttpRequestException {
 		ModelAndView modelAndView = new ModelAndView("customer/toViewCustomerDataList");
		modelAndView.addObject("pid", pid);
		return modelAndView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 根据pid查看客户详细(15个超链接页面)
	 * @author  heng.wang
	 * @date    2015年9月28日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectCusHistoryById")
	@ResponseBody
	public ModelAndView selectCusHistoryById(String pid,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("customer/selectCusDetailsById");
		modelView.addObject("pid", pid);
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 根据pid异步请求客户详细
	 * @author  heng.wang
	 * @date    2015年10月13日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/loadCustomerInfo")
	@ResponseBody
	public ModelAndView loadCustomerInfo(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		CusTomer cusTomer = (CusTomer) getEntity(request);
		try {
			if(null != cusTomer)
			{
				PageList<CusTomer> list  = ((CusTomerServiceImpl) getService()).selectCusHistoryDetailedById(cusTomer.getPid());
				Integer vipTime = list.get(0).getVipTime();
				if(vipTime !=null){
				// 计算出当前时间加上几个月后的天数是多少天
				GregorianCalendar gcd = new GregorianCalendar();
				// 获取当前时间
				gcd.setTime(new Date());
				// 添加原来的日期
				gcd.add(Calendar.DATE,  vipTime);
				// 获取购买后的日期
				Date dt = gcd.getTime();
				list.get(0).setVipTimeTwo(dt);
				}
				//加息收益
				BigDecimal jxsy = userCenterServiceImpl.getUserRateInterest(cusTomer.getPid());
				list.get(0).setJxInterest(jxsy);
				
				// 投资奖励数据的处理 
				String point = "";
				String experience = "";
				String increaseInterest = "";
				for(CusTomer ct:list){
					String investAwardType = ct.getInvestAwardType();
					String investAwardValue = ct.getInvestAwardValue();
					String totalNum = ct.getTotalNum();
					if(null != investAwardType && !"".equals(investAwardType)){
					if(investAwardType.equals("积分")){
						point += investAwardValue + "点积分 ";
					}else if(investAwardType.equals("经验值")){
						experience += investAwardValue + "点经验 ";
					}else if(investAwardType.equals("加息券")){
						increaseInterest += "加息 " + totalNum + " 次 ";
					}
					list.get(0).setPoint(point);
					list.get(0).setExperience(experience);
					list.get(0).setIncreaseInterest(increaseInterest);
					
					}
				}
				modelView.addObject("result", list.get(0));
				MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据PID获取客服详细信息失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 跳转到查看客户积分详细页面
	 * @author  heng.wang
	 * @date    2015年9月28日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toAvailablePointDetailsList")
	@ResponseBody
	public ModelAndView toAvailablePointDetailsList(String pid,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("customer/selectAvailablePointDetails");
		modelView.addObject("pid", pid);
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 根据pid查看客户积分详细页面
	 * @author  heng.wang
	 * @date    2015年9月29日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectAvailablePointDetailsById")
	@ResponseBody
	public ModelAndView selectAvailablePointDetailsById(String pid,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		PageInfo info = getPageInfo(request);
		try {
			PageList<CustPoinWater> list  = custPoinWaterServiceImpl.selectAvailablePointDetailsById(pid,info);
			modelView.addObject("rows", list);
			 modelView.addObject("total", list.getPaginator().getTotalCount());
			 MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据PID获取客户积分信息失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	/**
	 * 
	 * Description：<br> 
	 * 客户积分查询列表页面
	 * @author  heng.wang
	 * @date    2015年9月15日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectAvailablePointDetailList")
	@ResponseBody
	public ModelAndView selectAvailablePointDetailList(CusTomer cusTomer,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		CustPoinWater custPoinWater = new CustPoinWater();
	   try {
			PageInfo info = getPageInfo(request);
			if(null == cusTomer){
				cusTomer = new CusTomer();
			}
			custPoinWater.setHappenBeginTime(cusTomer.getHappenBeginTime());
			custPoinWater.setHappenEndTime(cusTomer.getHappenEndTime());
			custPoinWater.setPointType(cusTomer.getPointType());
 			PageList<CustPoinWater> list =  custPoinWaterServiceImpl.selectAllPage(custPoinWater,info);
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
	 * 跳转到查看客户经验详细页面
	 * @author  heng.wang
	 * @date    2015年10月9日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toExperienceDetailsList")
	@ResponseBody
	public ModelAndView toExperienceDetailsList(String pid,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("customer/toExperienceDetailsList");
		modelView.addObject("pid", pid);
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 根据pid查看客户经验详细页面
	 * @author  heng.wang
	 * @date    2015年10月9日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectExperienceDetailsById")
	@ResponseBody
	public ModelAndView selectExperienceDetailsById(String pid,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		PageInfo info = getPageInfo(request);
		try {
			PageList<CustExperienceWater> list  = custExperienceServiceImpl.selectExperienceDetailsById(pid,info);
			modelView.addObject("rows", list);
			 modelView.addObject("total", list.getPaginator().getTotalCount());
			 MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据PID获取客服经验信息失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 客户积分查询列表页面
	 * @author  heng.wang
	 * @date    2015年10月9日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectExperienceDetailList")
	@ResponseBody
	public ModelAndView selectExperienceDetailList(CusTomer cusTomer,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		CustExperienceWater custExperienceWater = new CustExperienceWater();
	   try {
			PageInfo info = getPageInfo(request);
			if(null == cusTomer){
				cusTomer = new CusTomer();
			}
			custExperienceWater.setHappenBeginTime(cusTomer.getHappenBeginTime());
			custExperienceWater.setHappenEndTime(cusTomer.getHappenEndTime());
			custExperienceWater.setExpGetType(cusTomer.getExpGetType());
 			PageList<CustExperienceWater> list = custExperienceServiceImpl.selectAllPage(custExperienceWater,info);
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
	 * 跳转到查看客户资料页面
	 * @author  heng.wang
	 * @date    2015年10月9日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/viewPoint")
	@ResponseBody
	public ModelAndView viewPoint(String flag,String pid,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("customer/viewCustData");
		modelView.addObject("flag", flag);
		modelView.addObject("pid", pid);
		return modelView;
	}
	/**
	 * 
	 * Description：<br> 
	 * 近七日新增客户数据
	 * @author  Yu.Zhang
	 * @date    2015年9月22日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/selectNearlySevenNewCustomers")
	@ResponseBody
	public void selectNearlySevenNewCustomers(HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> tojson = new HashMap<String, Object>();
		try {
			CusTomerServiceImpl service  = (CusTomerServiceImpl) getService();
			tojson = service.selectNearlySevenNewCustomers();
			tojson.put("message",HttpMessage.SUCCESS_CODE);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("近七日新增客户数据查询出错" + e.getMessage());
			}
			tojson.put("message","近七日新增客户数据查询异常,请联系管理员!");
		} 
		outputJson(tojson, response);
	}
	
	
	/**
	 * 
	 * Description：<br> 
	 * 跳转到客户积分页签的内容页面
	 * @author  heng.wang
	 * @date    2015年10月9日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toCusPointDetailsList")
	@ResponseBody
	public ModelAndView toCusPointDetailsList(String pid,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("customer/toCusPointDetailsList");
		modelView.addObject("pid", pid);
		return modelView;
	}
	/**
	 * 
	 * Description：<br> 
	 * 根据pid查看客户投资奖励积分明细页面
	 * @author  heng.wang
	 * @date    2015年10月9日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectCusPointDetailsById")
	@ResponseBody
	public ModelAndView selectCusPointDetailsById(String pid,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		PageInfo info = getPageInfo(request);
		try {
			PageList<ActInvAwaActDetail> list  = actInvAwaActDetailServiceImpl.selectCusPointDetailsById(pid,info);
			modelView.addObject("rows", list);
			 modelView.addObject("total", list.getPaginator().getTotalCount());
			 MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据PID获取客服投资奖励积分信息失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 跳转到客户投资奖励经验页签的内容页面
	 * @author  heng.wang
	 * @date    2015年10月9日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toJingyanDetailsList")
	@ResponseBody
	public ModelAndView toJingyanDetailsList(String pid,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("customer/toJingyanDetailsList");
		modelView.addObject("pid", pid);
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 根据pid查看客户投资奖励经验明细页面
	 * @author  heng.wang
	 * @date    2015年10月9日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectJingyanDetailsById")
	@ResponseBody
	public ModelAndView selectJingyanDetailsById(String pid,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		PageInfo info = getPageInfo(request);
		try {
			PageList<ActInvAwaActDetail> list  = actInvAwaActDetailServiceImpl.selectJingyanDetailsById(pid,info);
			modelView.addObject("rows", list);
			 modelView.addObject("total", list.getPaginator().getTotalCount());
			 MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据PID获取客服经验信息失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	/**
	 * 
	 * Description：<br> 
	 * 跳转到查看客户投资奖励加息券详细页面
	 * @author  heng.wang
	 * @date    2015年10月9日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toCusjiaxiDetailsList")
	@ResponseBody
	public ModelAndView toCusjiaxiDetailsList(String pid,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("customer/toCusjiaxiDetailsList");
		modelView.addObject("pid", pid);
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 根据pid查看客户投资奖励明细页面
	 * @author  heng.wang
	 * @date    2015年10月10日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectJiaXiDetailsById")
	@ResponseBody
	public ModelAndView selectJiaXiDetailsById(String pid,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		PageInfo info = getPageInfo(request);
		try {
			PageList<ActInvAwaActDetail> list  = actInvAwaActDetailServiceImpl.selectJiaXiDetailsById(pid,info);
			modelView.addObject("rows", list);
			 modelView.addObject("total", list.getPaginator().getTotalCount());
			 MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据PID获取客户 投资奖励加息信息失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 跳转到查看客户冻结余额详细页面
	 * @author  heng.wang
	 * @date    2015年10月9日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toFrozenAmountDetailsList")
	@ResponseBody
	public ModelAndView toFrozenAmountDetailsList(String pid,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("customer/toFrozenAmountDetailsList");
		modelView.addObject("pid", pid);
		return modelView;
	}
	/**
	 * 
	 * Description：<br> 
	 * 根据pid查看客户冻结金额详细页面
	 * @author  heng.wang
	 * @date    2015年10月9日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectFrozenAmountDetailsById")
	@ResponseBody
	public ModelAndView selectFrozenAmountDetailsById(String pid,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		PageInfo info = getPageInfo(request);
		CustFreezeWater custFreezeWater = new CustFreezeWater();
		try {
			custFreezeWater.setCustomerId(pid);
			PageList<CustFreezeWater> list  = custFreezeAmountWaterServiceImpl.selectAllPage(custFreezeWater,info);
			modelView.addObject("rows", list);
			 modelView.addObject("total", list.getPaginator().getTotalCount());
			 MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据PID获取客服冻结金额失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 客户冻结金额查询列表页面
	 * @author  heng.wang
	 * @date    2015年10月10日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectFrozenAmountDetailList")
	@ResponseBody
	public ModelAndView selectFrozenAmountDetailList(CusTomer cusTomer,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		CustFreezeWater custFreezeWater = new CustFreezeWater();
	   try {
			PageInfo info = getPageInfo(request);
			if(null == cusTomer){
				cusTomer = new CusTomer();
			}
			custFreezeWater.setHappenBeginTime(cusTomer.getHappenBeginTime());
			custFreezeWater.setHappenEndTime(cusTomer.getHappenEndTime());
 			PageList<CustFreezeWater> list = custFreezeAmountWaterServiceImpl.selectAllPage(custFreezeWater,info);
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
	 * 跳转到查看客户投资奖励红包详细页面
	 * @author  heng.wang
	 * @date    2015年10月9日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toHongbaoDetailsList")
	@ResponseBody
	public ModelAndView toHongbaoDetailsList(String pid,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("customer/toHongbaoDetailsList");
		modelView.addObject("pid", pid);
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 根据pid查看客户投资奖励红包详细页面
	 * @author  heng.wang
	 * @date    2015年10月13日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectHongbaoDetailsById")
	@ResponseBody
	public ModelAndView selectHongbaoDetailsById(String pid,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		CusTomerServiceImpl service  = (CusTomerServiceImpl) getService();
		PageInfo info = getPageInfo(request);
		try {
			PageList<CusTomer> list  = service.selectHongbaoDetailsById(pid,info);
			modelView.addObject("rows", list);
			 modelView.addObject("total", list.getPaginator().getTotalCount());
			 MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据PID获取客服投资奖励红包详细失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 跳转到查看客户体验金资金流水详细页面
	 * @author  heng.wang
	 * @date    2015年10月9日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toTiYanGoldDetailsList")
	@ResponseBody
	public ModelAndView toTiYanGoldDetailsList(String pid,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("customer/toTiYanGoldDetailsList");
		modelView.addObject("pid", pid);
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 根据pid查看客户体验金详细页面
	 * @author  heng.wang
	 * @date    2015年10月12日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectTiYanJinDetailsById")
	@ResponseBody
	public ModelAndView selectTiYanJinDetailsById(String pid,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		PageInfo info = getPageInfo(request);
		try {
			PageList<ActExpActDetail> list  = actExpActDetailServiceImpl.selectTiYanJinDetailsById(pid,info);
			if(list!=null && list.size()>0){
				for(int i=0;i<list.size()-1;i++){
					String userStatus = list.get(i).getUseStatus();
					if(userStatus.equals("已使用") && userStatus!=null){
						BigDecimal userAmount = list.get(i).getExpAmount();
						list.get(i).setUserExpAmount(userAmount);
					}
				}
			}
			
			modelView.addObject("rows", list);
			 modelView.addObject("total", list.getPaginator().getTotalCount());
			 MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据PID获取体验金资金流水详细失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 客户体验金查询列表页面
	 * @author  heng.wang
	 * @date    2015年10月10日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectTiYanJinDetailList")
	@ResponseBody
	public ModelAndView selectTiYanJinDetailList(CusTomer cusTomer,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		ActExpActDetail actExpActDetail = new ActExpActDetail();
	   try {
			PageInfo info = getPageInfo(request);
			if(null == cusTomer){
				cusTomer = new CusTomer();
			}
			actExpActDetail.setHappenBeginTime(cusTomer.getHappenBeginTime());
			actExpActDetail.setHappenEndTime(cusTomer.getHappenEndTime());
			actExpActDetail.setCustomerId(cusTomer.getPid());
 			PageList<ActExpActDetail> list = actExpActDetailServiceImpl.selectAllPage(actExpActDetail,info);
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
	 * 跳转到查看客户总共待收资金流水详细页面
	 * @author  heng.wang
	 * @date    2015年10月14日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toTotalStayDetailsList")
	@ResponseBody
	public ModelAndView toTotalStayDetailsList(String pid,String flag,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("customer/toTotalStayDetailsList");
		modelView.addObject("pid", pid);
		modelView.addObject("flag", flag);
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 根据pid查看客户总共待收详细页面
	 * @author  heng.wang
	 * @date    2015年10月14日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 * 此地方待修改
	 */
	@RequestMapping(value = "/selectTotalStayDetailsById")
	@ResponseBody
	public ModelAndView selectTotalStayDetailsById(String pid,String flag,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		PageInfo info = getPageInfo(request);
		PageList<BizReceiptPlan> list = new PageList<BizReceiptPlan>();
		BizReceiptPlan bizReceiptPlan = new BizReceiptPlan();
		try {
			//flag = 1：总共待收  2：待收利息
			if(flag.equals("1")){
				bizReceiptPlan.setCustomerId(pid);
				 list  = bizReceiptPlanService.selectAllPage(bizReceiptPlan,info);
			}
			if(flag.equals("2")){
				bizReceiptPlan.setCustomerId(pid);
				list = bizReceiptPlanService.selectAllPage(bizReceiptPlan,info);
			}
			 modelView.addObject("rows", list);
			 modelView.addObject("total", list.getPaginator().getTotalCount());
			 MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据PID获取总共待收资金流水详细失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 客户总共待收查询列表页面
	 * @author  heng.wang
	 * @date    2015年10月14日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectTotalStayDetailList")
	@ResponseBody
	public ModelAndView selectTotalStayDetailList(CusTomer cusTomer,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		CustDueinWater custDueinWater = new CustDueinWater();
	   try {
			PageInfo info = getPageInfo(request);
			if(null == cusTomer){
				cusTomer = new CusTomer();
			}
			custDueinWater.setHappenBeginTime(cusTomer.getHappenBeginTime());
			custDueinWater.setHappenEndTime(cusTomer.getHappenEndTime());
			custDueinWater.setCustomerId(cusTomer.getPid());
 			PageList<CustDueinWater> list = custDueinWaterServiceImpl.selectAllPage(custDueinWater,info);
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
	 * 跳转到查看客户待收利息资金流水详细页面
	 * @author  heng.wang
	 * @date    2015年10月15日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toCollectInterestDetailsList")
	@ResponseBody
	public ModelAndView toCollectInterestDetailsList(String pid,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("customer/toCollectInterestDetailsList");
		modelView.addObject("pid", pid);
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 根据pid查看客户详情
	 * @author  heng.wang
	 * @date    2015年9月18日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectBankInfoById")
	@ResponseBody
	public ModelAndView selectBankInfoById(String pid,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			PageList<Bank> list  = bankService.selectBankInfoById(pid);
			String openAddreeName="";
			String bp="";
			String bc="";
			String oppenAddress="";
			for(int i=0;i<list.size();i++){
				//添加省+市+支行名称
				oppenAddress= list.get(i).getOpenAddress();
				bp=list.get(i).getBelongingProvince();
				bc=list.get(i).getBelongingCity();
				openAddreeName=bp+bc+oppenAddress;
				list.get(i).setOpenAddress(openAddreeName);
			}
			modelView.addObject("rows", list);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据PID获取客服银行卡账户信息失败",e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	/**
	 * Description：<br> 
	 * 导出客户信息
	 * @author  heng.wang
	 * @date    2015年10月29日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @param bba
	 */
	@RequestMapping("/exportDownLoadFile")
	@ResponseBody
	public HashMap<String, Object> exportDownLoadFile(HttpServletRequest request, HttpServletResponse response,
			CusTomer cusTomer,ExportObjectVo expvo){
		HashMap<String, Object> remap = new HashMap<String, Object>();
		CusTomerServiceImpl service  = (CusTomerServiceImpl) getService();
		try {
			if(null == cusTomer){
				cusTomer = new CusTomer();
			}
			//进行导出数据封装
			if(null != expvo){
				//通过VO转Model
				ExportObjectModel eom = ExportVoToModelDto.voToModel(expvo);
				//获取结果集
				List<CusTomer> reList = service.selectCustomer(cusTomer, eom);
				//通过信息进行配置文件
				String xmlpath = getTempBasePath(request).get(Constant.COM_EXPORTBASEPATH) + "export_customer.xml";
				CheckResult checkrsl = exportExcel("客户信息数据.xls", xmlpath, "export_customer", reList);
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
	
	/**
	 * 
	 * Description：<br> 
	 * 根据条件查询
	 * @author  heng.wang
	 * @date    2015年9月15日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "selectPage")
	@ResponseBody
	public ModelAndView selectPage(CusTomer cusTomer,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		CusTomerServiceImpl service  = (CusTomerServiceImpl) getService();
		ModelAndView modelView = new ModelAndView();
	   try {
			PageInfo info = getPageInfo(request);
			if(null == cusTomer){
				cusTomer = new CusTomer();
			}
 			PageList<CusTomer> list = service.selectPage(cusTomer,info);
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

}
