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
 * 2015年11月16日     JunJie.Liu		Initial Version.
 *************************************************************************
 */
package com.yscf.ep2p.controller.business;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.util.DateUtil;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.common.Constant.Constant;
import com.yscf.common.util.PromptMsgConstants;
import com.yscf.core.model.business.BizBorrow;
import com.yscf.core.model.business.BizBorrowFileRel;
import com.yscf.core.model.business.BizBorrowInfoVO;
import com.yscf.core.model.business.BizHouses;
import com.yscf.core.model.pub.PubFile;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.service.business.IBizBorrowDetailService;
import com.yscf.core.service.business.IBizBorrowFileRelService;
import com.yscf.core.service.business.IBizBorrowInfoVOService;
import com.yscf.core.service.business.impl.BizBorrowApproveServiceImpl;
import com.yscf.core.service.business.impl.BizBorrowServiceImpl;
import com.yscf.core.service.business.impl.BizHousesServiceImpl;
import com.yscf.core.service.user.impl.CusTomerServiceImpl;
import com.yscf.ep2p.controller.base.EscfBaseWebController;

/**
 * Description：<br>
 * 借款控制层类
 * 
 * @author JunJie.Liu
 * @date 2015年11月16日
 * @version v1.0.0
 */
@Controller
@RequestMapping("business/borrowController")
public class BorrowController extends EscfBaseWebController {

	@Autowired
	public BorrowController(BizBorrowServiceImpl service) {
		super(service);
	}

	private Logger logger = LoggerFactory.getLogger(BorrowController.class);

	@Override
	public Class<?> getModel() {

		return BizBorrow.class;
	}

	@Resource
	private IBizBorrowDetailService bizBorrowDetailService;

	@Resource(name = "bizBorrowInfoVOService")
	private IBizBorrowInfoVOService bizBorrowInfoVOService;

	@Resource(name = "bizBorrowFileRelService")
	private IBizBorrowFileRelService bizBorrowFileRelService;

	// 客户积分明细
	@Resource(name = "cusTomerService")
	private CusTomerServiceImpl cusTomerService;
	
	@Resource(name = "bizHousesService")
	private BizHousesServiceImpl housesServiceImpl;
	

	@Resource(name ="bizBorrowApproveService")
	private BizBorrowApproveServiceImpl bizBorrowApproveService;
	
	
	/**
	 * 
	 * Description：<br>
	 * 跳转到我要借款页面
	 * 
	 * @author Yu.Zhang
	 * @date 2015年11月23日
	 * @version v1.0.0
	 * @return
	 */
	@RequestMapping("/toBorrowPage")
	public String toBorrowPage() {
		return "temp.borrow.page";
	}

	/**
	 * 
	 * Description：<br>
	 * 新增借款
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toAdd")
	public ModelAndView toAdd(BizBorrowInfoVO borrow,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView model = new ModelAndView("temp.borrowContent.page");
		try {
			// pid 为空，查询当前用户是否存在未提交审核的申请
			if(null == borrow.getPid() || "".equals(borrow.getPid())){
				// 查询当前客户下是否存在借款申请时间为空的数据,不存在则新增一条数据
				borrow.setBorrowStatus(Constant.ACTIVATE);
				borrow.setCustomerId(getUserId());
				
				BizBorrowServiceImpl service = (BizBorrowServiceImpl) getService();
				List<BizBorrowInfoVO> list = bizBorrowInfoVOService.getBorrowApplyTimeIsNull(borrow);
				if (list == null || list.size() == 0) {
					borrow.setCreateTime(DateUtil.format(DateUtil.getToday()));
					borrow.setCreateUser(getUserId());
					service.insert(borrow);
				} else {
					borrow = list.get(0);
				}
			}else{
				borrow = bizBorrowInfoVOService.getBizBorrowById(borrow.getPid());
			}
			
			// e 首房，e抵押第一步页面不是同一个页面
			if(Constant.BORROW_TYPE_2.equals(borrow.getBorrowType())){
				model = new ModelAndView("temp.borrowContent.house");
			}
		} catch (Exception e) {
			e.printStackTrace();
			if (logger.isDebugEnabled()) {
				logger.debug("新增or编辑借款异常", e.getMessage());
			}
		}
		model.addObject("borrow", borrow);
		return model;
	}

	/**
	 * 
	 * Description：<br>
	 * 跳转到个人基本信息页面
	 * @author Yu.Zhang
	 * @date 2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toBorrowHouseInfoPage")
	@ResponseBody
	public ModelAndView toBorrowHouseInfoPage(BizBorrowInfoVO borrow, HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView model = new ModelAndView("mywtborrow/borrowHouseInfo");
		try {
			borrow = bizBorrowInfoVOService.getBizBorrowById(borrow.getPid());
		} catch (Exception e) {
			e.printStackTrace();
			if (logger.isDebugEnabled()) {
				logger.debug("新增or编辑借款异常", e.getMessage());
			}
		}
		model.addObject("borrow", borrow);
		return model;
	}
	
	/**
	 * 
	 * Description：<br>
	 * 跳转到个人基本信息页面
	 * @author Yu.Zhang
	 * @date 2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toBaseInfoPage")
	@ResponseBody
	public ModelAndView toBaseInfoPage(BizBorrowInfoVO borrow, HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView model = new ModelAndView("mywtborrow/borrowBaseInfo");
		try {
			borrow = bizBorrowInfoVOService.getBizBorrowById(borrow.getPid());
		} catch (Exception e) {
			e.printStackTrace();
			if (logger.isDebugEnabled()) {
				logger.debug("新增or编辑借款异常", e.getMessage());
			}
		}
		model.addObject("borrow", borrow);
		return model;
	}
	
	/**
	 * 
	 * Description：<br>
	 * 跳转到资质上传页面
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toFileInfoPage")
	@ResponseBody
	public ModelAndView toFileInfoPage(BizBorrowInfoVO borrow,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView model = new ModelAndView("mywtborrow/borrowFileInfo");
		model.addObject("borrow", borrow);
		return model;
	}

	/**
	 * 
	 * Description：<br>
	 * 跳转到借款信息页面
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toBorrowInfoPage")
	@ResponseBody
	public ModelAndView toBorrowInfoPage(BizBorrowInfoVO borrow,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView model = new ModelAndView("mywtborrow/borrowInfo");
		borrow = bizBorrowInfoVOService.getBizBorrowById(borrow.getPid());
		model.addObject("borrow", borrow);
		return model;
	}

	/**
	 * 
	 * Description：<br>
	 * 跳转到借款信息页面
	 * @author Yu.Zhang
	 * @date 2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toBorrowCompletePage")
	@ResponseBody
	public ModelAndView toBorrowCompletePage(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView model = new ModelAndView("mywtborrow/borrowComplete");
		return model;
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
	@RequestMapping(value = "/getCusTomerByPid")
	@ResponseBody
	public ModelAndView getCusTomerByPid(String pid,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			CusTomer cus = (CusTomer) cusTomerService.selectByPrimaryKey(pid);
			modelView.addObject("result", cus);
			MessageBuilder.processSuccess(modelView, null,
					HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据PID获取客户失败", e.getMessage());
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
	 * @author heng.wang
	 * @date 2015年9月16日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/saveCusTomer")
	@ResponseBody
	public ModelAndView saveCusTomer(CusTomer cusTomer,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			cusTomerService.updateByPrimaryKeySelective(cusTomer);
			MessageBuilder.processSuccess(modelView, null,
					HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("保存客户信息失败", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 保存借款
	 * @author Yu.Zhang
	 * @date 2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/save")
	@ResponseBody
	public ModelAndView save(BizBorrow borrow, HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			BizBorrowServiceImpl service = (BizBorrowServiceImpl) getService();
			// 1 设置最后更新人,最后更新时间
			borrow.setLastUpdateTime(DateUtil.format(DateUtil.getToday()));
			borrow.setLastUpdateUser(getUserId());
			// 2 数据保存
			service.updateByPrimaryKeySelective(borrow);
			modelView.addObject("borrow", borrow);
			// 3 结果集返回
			MessageBuilder.processSuccess(modelView, null,
					HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("编辑保存借款信息失败", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 上传文件
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月22日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/uploadFile")
	@ResponseBody
	public void uploadFile(BizBorrowFileRel fileRel,
			HttpServletRequest request, HttpServletResponse response) {
		Map<String, Object> tojson = new HashMap<String, Object>();
		try {
			// 文件上传
			HashMap<String, Object> remap = saveUploadFileObject(request,
					Constant.BUSINESS);

			if ((Boolean) remap.get(PromptMsgConstants.COMMON_FLG)) {
				PubFile pubFile = ((List<PubFile>) remap.get("resultList"))
						.get(0);
				fileRel.setCreateTime(DateUtil.format(DateUtil.getToday()));
				fileRel.setCreateUser(getUserId());
				fileRel.setStatus(Constant.ACTIVATE);
				fileRel.setFileId(pubFile.getPid());
				BizBorrowServiceImpl service = (BizBorrowServiceImpl) getService();
				// 保存文件信息
				service.saveFileInfo(fileRel, pubFile);
				tojson.put("message", HttpMessage.SUCCESS_CODE);
			} else {
				tojson.put("message", remap.get("message"));
			}
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.debug("上传资料列表出错" + e.getMessage());
			}
			tojson.put("message", "上传资料列表异常,请联系管理员!");
		}
		outputJson(tojson, response);
	}

	/**
	 * 
	 * Description：<br>
	 * 根据类型加载文件信息
	 * 
	 * @author Yu.Zhang
	 * @date 2015年11月28日
	 * @version v1.0.0
	 * @param borrow
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/getBorrowFileRelByType")
	@ResponseBody
	public ModelAndView getBorrowFileRelByType(BizBorrowFileRel borrow,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			List<BizBorrowFileRel> list = bizBorrowFileRelService
					.selectAll(borrow);
			modelView.addObject("result", list);
			MessageBuilder.processSuccess(modelView, null,
					HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据类型加载文件信息失败", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 删除文件信息
	 * 
	 * @author heng.wang
	 * @date 2015年9月16日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/deleteFile")
	@ResponseBody
	public ModelAndView deleteFile(BizBorrowFileRel borrow,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			borrow.setStatus(Constant.DELETE);
			borrow.setLastUpdateTime(DateUtil.format(DateUtil.getToday()));
			borrow.setLastUpdateUser(getUserId());
			bizBorrowFileRelService.updateByPrimaryKeySelective(borrow);
			MessageBuilder.processSuccess(modelView, null,
					HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("删除文件信息失败", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 根据ID查询借款信息
	 * 
	 * @author heng.wang
	 * @date 2015年9月16日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/getByPid")
	@ResponseBody
	public ModelAndView getByPid(String pid, HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			BizBorrow borrow = (BizBorrow) getService().selectByPrimaryKey(pid);
//			// 债权转让允许的利率 开始值
//			if(!StringUtils.isEmpty(borrow.getStartValue())){
//				borrow.setStartValue(borrow.getStartValue().multiply(BigDecimal.valueOf(100)));
//			}
//			
//			// 债权转让允许的利率 结束值
//			if(!StringUtils.isEmpty(borrow.getEndValue())){
//				borrow.setStartValue(borrow.getEndValue().multiply(BigDecimal.valueOf(100)));
//			}
			
			// 投资奖励比例
			if(!StringUtils.isEmpty(borrow.getInvestRewardScale())){
				borrow.setStartValue(borrow.getInvestRewardScale().multiply(BigDecimal.valueOf(100)));
			}
			
			// 管理费率
			if(!StringUtils.isEmpty(borrow.getManageExpenseRate())){
				borrow.setStartValue(borrow.getManageExpenseRate().multiply(BigDecimal.valueOf(100)));
			}
						
			// 年化率
			if(!StringUtils.isEmpty(borrow.getBorrowRate())){
				borrow.setStartValue(borrow.getBorrowRate().multiply(BigDecimal.valueOf(100)));
			}
			modelView.addObject("result", borrow);
			MessageBuilder.processSuccess(modelView, null,HttpMessage.SUCCESS_MSG, request);
		} catch (FrameworkException e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据PID获取借款信息失败", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 提交審核
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/submitAudit")
	@ResponseBody
	public ModelAndView submitAudit(BizBorrowInfoVO borrow,
			HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			borrow.setApproveUser(getUserId());
			bizBorrowApproveService.executeApprove(borrow);
			MessageBuilder.processSuccess(modelView, null,
					HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("借款审批失败", e.getMessage());
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
			List<BizHouses> list = housesServiceImpl.selectDistinctProvince(bizHouses);
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
			bizHouses.setHomesProvince(new String(bizHouses.getHomesProvince().getBytes("8859_1"),"UTF-8"));
			List<BizHouses> list = housesServiceImpl.selectDistinctCityByProvince(bizHouses);
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
			bizHouses.setHomesProvince(new String(bizHouses.getHomesProvince().getBytes("8859_1"),"UTF-8"));
			bizHouses.setHomesCity(new String(bizHouses.getHomesCity().getBytes("8859_1"),"UTF-8"));
			List<BizHouses> list = housesServiceImpl.selectDistinctAreaByCity(bizHouses);
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
			List<BizHouses> list = housesServiceImpl.selectDistinctHomesNameByAddress(bizHouses);
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
			List<BizHouses> list = housesServiceImpl.selectDistinctHomesTypeByHomesName(bizHouses);
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
	@RequestMapping(value = "/getHouseByPid")
	@ResponseBody
	public ModelAndView getHouseByPid(String pid,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			BizHouses borrow = (BizHouses) housesServiceImpl.selectByPrimaryKey(pid);
			modelView.addObject("data", borrow);
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
