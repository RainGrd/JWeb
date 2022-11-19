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
 * 2015年12月26日     shiliang.feng		Initial Version.
 *************************************************************************
 */
package com.yscf.api.controller.homepage;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.NullArgumentException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.json.gson.GsonUtil;
import com.achievo.framework.util.StringUtil;
import com.achievo.framework.vo.JsonObject;
import com.google.gson.Gson;
import com.yscf.api.common.ApiCode;
import com.yscf.api.controller.base.EscfBaseApi;
import com.yscf.api.util.ApiUtil;
import com.yscf.api.vo.myinvest.BizBorrowVo;
import com.yscf.api.vo.myinvest.BizRepaymentPlanVo;
import com.yscf.api.vo.myinvest.NewStandardArgsVo;
import com.yscf.api.vo.myinvest.NewStandardRecordVo;
import com.yscf.api.vo.user.CustomerVo;
import com.yscf.common.util.ConvertObjectUtil;
import com.yscf.core.model.business.BizBorrow;
import com.yscf.core.model.business.BizBorrowDetail;
import com.yscf.core.model.business.BizBorrowFileRel;
import com.yscf.core.model.business.BizBorrowInfoVO;
import com.yscf.core.model.business.BizFinanceProducts;
import com.yscf.core.model.business.BizRepaymentPlan;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.service.business.impl.BizBorrowDetailServiceImpl;
import com.yscf.core.service.business.impl.BizBorrowFileRelServiceImpl;
import com.yscf.core.service.business.impl.BizBorrowInfoVOServiceImpl;
import com.yscf.core.service.business.impl.BizBorrowServiceImpl;
import com.yscf.core.service.business.impl.BizFinanceProductsServiceImpl;
import com.yscf.core.service.business.impl.BizRepaymentPlanServiceImpl;
import com.yscf.core.service.finProManage.finProManageImpl.FinancialProductsManageServiceImpl;
import com.yscf.core.service.system.impl.SysParamsServiceImpl;
import com.yscf.core.service.user.impl.CusTomerServiceImpl;
import com.yscf.core.util.RepalceUtil;

/**
 * Description：<br>
 * e计划 e首房/e抵押 移动端接口
 * 
 * @author shiliang.feng
 * @date 2015年12月26日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/eplan/eplanApi")
public class EplanApi extends EscfBaseApi {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public EplanApi(BizBorrowServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return BizBorrow.class;
	}

	// 借款服务
	@Resource(name = "bizBorrowService")
	private BizBorrowServiceImpl bizBorrowServiceImpl;
	// 借款详情业务类服务接口
	@Resource(name = "bizBorrowDeatailService")
	private BizBorrowDetailServiceImpl detailServiceImpl;
	// 理财产品介绍 服务实现
	@Resource(name = "bizFinanceProductsServiceImpl")
	private BizFinanceProductsServiceImpl bizFinanceProductsServiceImpl;
	// 借款VO业务类服务接口
	@Resource(name = "financialProductsManageServiceImpl")
	private FinancialProductsManageServiceImpl productsManageServiceImpl;
	// 客户服务实现
	@Resource(name = "cusTomerService")
	private CusTomerServiceImpl cusTomerService;
	// 借款和文件关联
	@Resource(name = "bizBorrowFileRelService")
	private BizBorrowFileRelServiceImpl bizBorrowFileRelService;
	// 借款服务VO
	@Resource(name = "bizBorrowInfoVOService")
	private BizBorrowInfoVOServiceImpl bizBorrowInfoVoService;
	// 系统参数
	@Resource(name = "sysParamsService")
	private SysParamsServiceImpl sysParamsServiceImpl;
	// 还款计划服务
	@Resource(name = "bizRepaymentPlanService")
	private BizRepaymentPlanServiceImpl bizRepaymentPlanServiceImpl;

	/**
	 * 
	 * Description：<br>
	 * 手机端 e计划列表
	 * 
	 * @author shiliang.feng
	 * @date 2015年12月26日
	 * @version v1.0.0
	 * @param sortType
	 *            排序方式 默认1是综合 排序 2是年华利率 3是借款期限
	 * @param sortModel
	 *            升序 还是降序 1代表降序，2代表升序
	 * @param borrowType
	 *            1：散标 3：e计划
	 * 
	 * @return
	 */
	@RequestMapping(value = "/getEplanList", method = RequestMethod.POST)
	@ResponseBody
	public String getEplanList(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		String reqStr = ApiUtil.getBodyDecryptStr(request);
		Gson gson = GsonUtil.create();
		BizBorrowVo vo = gson.fromJson(reqStr, BizBorrowVo.class);
		JsonObject jsonObject = new JsonObject();
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			BizBorrowInfoVO borrow = new BizBorrowInfoVO();
			if (StringUtil.isBlank(vo.getBorrowType())) {
				processResultStatus(jsonObject, "900", "borrowType is null!",
						false);
				return ApiUtil.getEncryptStr(jsonObject);
			} else {
				if ("1".equals(vo.getBorrowType())
						|| "3".equals(vo.getBorrowType())
						|| "-1".equals(vo.getBorrowType())) {
					borrow.setBorrowType(vo.getBorrowType());
				} else {
					processResultStatus(
							jsonObject,
							"900",
							"Parameter is not valid!borrowType Equal to 1 or 3",
							false);
					return ApiUtil.getEncryptStr(jsonObject);
				}
			}
			// 查看已发布或者是预发布 的e计划 产品
			List<BizBorrowInfoVO> borrowList = productsManageServiceImpl
					.selectPublishedFinData(borrow,
							vo.getPageIndex() * vo.getPageSize(),
							vo.getPageSize(), vo.getSortType(),
							vo.getSortModel());
			List<BizBorrowInfoVO> totlePage = productsManageServiceImpl
					.selectPublishedFinData(borrow, null, null,
							vo.getSortType(), vo.getSortModel());
			map.put("list", borrowList);
			map.put("pageCount", totlePage.size());
			map.put("systime", System.currentTimeMillis());
			jsonObject.setResult(map);
			jsonObject.setCode("200");
			jsonObject.setMessage("查询成功");
			jsonObject.setStatus(true);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			processResultStatus(jsonObject, ApiCode.HTTP_CODE_900,
					e.getMessage(), false);
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}

	/**
	 * Description：<br>
	 * app推荐项目
	 * 
	 * @author shiliang.feng
	 * @date 2016年1月22日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/appRecommendProjects", method = RequestMethod.POST)
	@ResponseBody
	public String appRecommendProjects(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		JsonObject jsonObject = new JsonObject();
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			// 查看已发布或者是预发布 的e计划 产品
			List<BizBorrowInfoVO> borrowList = bizBorrowInfoVoService
					.selectAppRecommendProjects();
			map.put("list", borrowList);
			map.put("systime", System.currentTimeMillis());
			jsonObject.setResult(map);
			jsonObject.setCode("200");
			jsonObject.setMessage("查询成功");
			jsonObject.setStatus(true);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error(e.getMessage());
			processResultStatus(jsonObject, ApiCode.HTTP_CODE_900,
					e.getMessage(), false);
		}

		return ApiUtil.getEncryptStr(jsonObject);
	}

	/**
	 * Description：<br>
	 * e计划详情
	 * 
	 * @author shiliang.feng
	 * @date 2015年12月26日
	 * @version v1.0.0
	 * @param borrowId
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping(value = "/getEplanInfo", method = RequestMethod.POST)
	@ResponseBody
	public String getEplanInfo(HttpServletRequest request,
			HttpServletResponse response) throws FrameworkException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();

		try {
			BizBorrowVo args = (BizBorrowVo) ApiUtil.convertObjectByBody(
					request, BizBorrowVo.class);
			if (args == null) {
				throw new NullArgumentException("参数对象为null");
			}
			if (!StringUtils.hasLength(args.getPid())) {
				throw new NullArgumentException("标的id为null");
			}
			// 根据borrowId 查询实体
			BizBorrow borrow =(BizBorrow) bizBorrowServiceImpl
					.selectByPrimaryKey(args.getPid());
			if (borrow == null) {
				throw new RuntimeException("查询对象为null,主键为：" + args.getPid());
			}
			map.put("customId", borrow.getCustomerId());
			map.put("borrowRate", borrow.getBorrowRate());
			map.put("isJiaxiTicket", borrow.getIsJiaxiTicket());
			map.put("borDeadline", borrow.getBorDeadline());
			map.put("accrualType", borrow.getAccrualType());
			map.put("accrualTypeName", borrow.getAccrualTypeName());
			map.put("borStatus", borrow.getBorStatus());
			map.put("securityType", "100%本息保障");
			map.put("borrowProgress", borrow.getBorrowProgress());
			map.put("pid", borrow.getPid());
			map.put("borrowCode", borrow.getBorrowCode());
			map.put("borrowMoney", borrow.getBorrowMoney());
			map.put("surplusMoney", borrow.getSurplusMoney());
			map.put("repaymentType", borrow.getRepaymentType());
			map.put("repaymentTypeName", borrow.getRepaymentTypeName());
			map.put("investRewardScale", borrow.getInvestRewardScale());
			map.put("borrowName", borrow.getBorrowName());
			map.put("borrowType", borrow.getBorrowType());
			map.put("borrowTypeName", borrow.getBorrowTypeName());
			map.put("systime", System.currentTimeMillis());
			jsonObject.setResult(map);
			jsonObject.setCode("200");
			jsonObject.setMessage("查询成功");
			jsonObject.setStatus(true);

		} catch (Exception e) {
			jsonObject.setMessage(e.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			processResultStatus(jsonObject, ApiCode.HTTP_CODE_900,
					e.getMessage(), false);
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}

	/**
	 * 
	 * Description：<br>
	 * 查询用户信息
	 * 
	 * @author shiliang.feng
	 * @date 2016年1月6日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @param pid
	 *            用户主键
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping(value = "/getCustomerByPid", method = RequestMethod.POST)
	@ResponseBody
	public String getCustomerByPid(HttpServletRequest request,
			HttpServletResponse response) throws FrameworkException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		try {
			CusTomer args = (CusTomer) ApiUtil.convertObjectByBody(request,
					CusTomer.class);
			if (args == null) {
				throw new NullArgumentException("参数对象为null");
			}
			if (!StringUtils.hasLength(args.getPid())) {
				throw new NullArgumentException("标的id为null");
			}
			// 根据borrowId 查询理财产品介绍数据
			CusTomer cusTomer = cusTomerService.selectByPrimaryKey(args
					.getPid());
			if (cusTomer == null) {
				throw new RuntimeException("查询对象为null,主键为：" + args.getPid());
			}

			String privateCustName = RepalceUtil
					.replaceCustomerNameToStar(cusTomer.getSname());
			String privatePhoneNo = RepalceUtil
					.replaceCustomerNameToStar(cusTomer.getPhoneNo());

			map.put("privatePhoneNo", privatePhoneNo);// 加密后的电话号码
			map.put("privateCustName", privateCustName);
			map.put("sex", cusTomer.getSex());
			map.put("isMarriage", cusTomer.getIsMarriage());// 婚否
			map.put("isMarriageName", cusTomer.getIsMarriageName());// 婚否名字
			map.put("age", cusTomer.getAge());
			map.put("ResReg", cusTomer.getResReg());// 户籍
			map.put("custId", cusTomer.getPid());

			jsonObject.setResult(map);
			jsonObject.setCode("200");
			jsonObject.setMessage("查询成功");
			jsonObject.setStatus(true);
		} catch (Exception e) {
			jsonObject.setMessage(e.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			processResultStatus(jsonObject, ApiCode.HTTP_CODE_900,
					e.getMessage(), false);
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}

	/**
	 * Description：<br>
	 * 获得e计划 项目描述
	 * 
	 * @author shiliang.feng
	 * @date 2015年12月26日
	 * @version v1.0.0
	 * @param borrowId
	 *            借款id
	 * @return
	 */
	@RequestMapping(value = "/getEplanProduct", method = RequestMethod.POST)
	@ResponseBody
	public String getEplanProduct(HttpServletRequest request,
			HttpServletResponse response) throws FrameworkException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		try {
			BizBorrowVo args = (BizBorrowVo) ApiUtil.convertObjectByBody(
					request, BizBorrowVo.class);
			if (args == null) {
				throw new NullArgumentException("参数对象为null");
			}
			if (!StringUtils.hasLength(args.getPid())) {
				throw new NullArgumentException("标的id为null");
			}
			// 根据borrowId 查询理财产品介绍数据
			BizFinanceProducts product = bizFinanceProductsServiceImpl
					.getBizFinanceProductsByBorrowId(args.getPid());
			if (product == null) {
				throw new RuntimeException("查询对象为null,主键为：" + args.getPid());
			}
			map.put("borrowId", product.getBorrowId());
			map.put("projectDescription", product.getProjectDescription());

			jsonObject.setResult(map);
			jsonObject.setCode("200");
			jsonObject.setMessage("查询成功");
			jsonObject.setStatus(true);
		} catch (Exception e) {
			jsonObject.setMessage(e.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			processResultStatus(jsonObject, ApiCode.HTTP_CODE_900,
					e.getMessage(), false);
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}

	/**
	 * Description：<br>
	 * 投资记录
	 * 
	 * @author shiliang.feng
	 * @date 2015年12月26日
	 * @version v1.0.0
	 * @param borrowId
	 *            借款id
	 * @param pageIndex
	 *            当前页
	 * @param pageSize
	 *            页面大小
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getBorrowDetailList", method = RequestMethod.POST)
	@ResponseBody
	public String getBorrowDetailList(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();

		try {
			BizBorrowDetail args = (BizBorrowDetail) ApiUtil
					.convertObjectByBody(request, BizBorrowDetail.class);
			// 根据borrowId 查询理财产品介绍数据
			List<BizBorrowDetail> detailList = detailServiceImpl
					.selectBizBorrowDetailByBorrowId(args.getBorrowId(),
							args.getPageIndex(), args.getPageSize());
			List<NewStandardRecordVo> list = (List<NewStandardRecordVo>) ConvertObjectUtil
					.batchConvertObject(detailList, NewStandardRecordVo.class);
			map.put("result", list);
			jsonObject.setResult(map);
			jsonObject.setCode("200");
			jsonObject.setMessage("查询成功");
			jsonObject.setStatus(true);
		} catch (Exception e) {
			jsonObject.setMessage(e.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			processResultStatus(jsonObject, ApiCode.HTTP_CODE_900,
					e.getMessage(), false);
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}

	/**
	 * Description：<br>
	 * 标的其他信息 借款用途 还款来源 抵押信息/房产信息
	 * 
	 * @author shiliang.feng
	 * @date 2015年12月26日
	 * @version v1.0.0
	 * @param borrowId
	 *            借款id
	 * @param pageIndex
	 *            当前页
	 * @param pageSize
	 *            页面大小
	 * @return
	 */
	@RequestMapping(value = "/getBorrowOtherInfo", method = RequestMethod.POST)
	@ResponseBody
	public String getBorrowOtherInfo(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();

		try {
			BizBorrowVo args = (BizBorrowVo) ApiUtil.convertObjectByBody(
					request, BizBorrowVo.class);
			BizBorrow borrow = (BizBorrow) bizBorrowServiceImpl
					.selectByPrimaryKey(args.getPid());

			map.put("borrowUse", borrow.getBorrowUse());// 借款用途
			map.put("payment", borrow.getPayment());// 还款来源
			map.put("hostageInfo", borrow.getHostageInfo());// 抵押信息
			map.put("homeDesc", borrow.getHomeDesc());// 房产信息
			map.put("pid", borrow.getPid());
			jsonObject.setResult(map);
			jsonObject.setCode("200");
			jsonObject.setMessage("查询成功");
			jsonObject.setStatus(true);
		} catch (Exception e) {
			jsonObject.setMessage(e.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			processResultStatus(jsonObject, ApiCode.HTTP_CODE_900,
					e.getMessage(), false);
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}

	/**
	 * Description：<br>
	 * 风控信息
	 * 
	 * @author shiliang.feng
	 * @date 2015年12月26日
	 * @version v1.0.0
	 * @param borrowId
	 *            借款id
	 * @return
	 */
	@RequestMapping(value = "/getRiskControl", method = RequestMethod.POST)
	@ResponseBody
	public String getRiskControl(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();

		try {
			BizBorrowVo args = (BizBorrowVo) ApiUtil.convertObjectByBody(
					request, BizBorrowVo.class);
			// 风控信息
			BizBorrow borrow = (BizBorrow) bizBorrowServiceImpl
					.selectByPrimaryKey(args.getPid());
			BizBorrowFileRel bizBorrowFileRel = new BizBorrowFileRel();
			bizBorrowFileRel.setBorrowId(args.getPid());
			// 用户上传材料
			List<BizBorrowFileRel> list = bizBorrowFileRelService
					.selectAll(bizBorrowFileRel);
			map.put("guaOrgName", borrow.getGuaOrgName());// 担保机构
			map.put("guaAcc", borrow.getGuaAcc());// 担保承诺
			map.put("riskDesc", borrow.getRiskDesc());// 担保承诺
			map.put("homeDesc", borrow.getHomeDesc());// 房产信息
			map.put("pid", borrow.getPid());// 标的id
			map.put("list", list);// 用户上传的材料

			jsonObject.setResult(map);
			jsonObject.setCode("200");
			jsonObject.setMessage("查询成功");
			jsonObject.setStatus(true);
		} catch (Exception e) {
			jsonObject.setMessage(e.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			processResultStatus(jsonObject, ApiCode.HTTP_CODE_900,
					e.getMessage(), false);
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}

	/**
	 * 
	 * Description：<br>
	 * 点击立即加入方法
	 * 
	 * @author shiliang.feng
	 * @date 2016年1月7日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/nowBorrow", method = RequestMethod.POST)
	@ResponseBody
	public String nowBorrow(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		try {
			BizBorrowVo args = (BizBorrowVo) ApiUtil.convertObjectByBody(
					request, BizBorrowVo.class);
			// 风控信息
			BizBorrow borrow = (BizBorrow) bizBorrowServiceImpl
					.selectByPrimaryKey(args.getPid());
			if (borrow == null) {
				throw new RuntimeException("查询对象为null,主键为：" + args.getPid());
			}
			map.put("pid", borrow.getPid());// 标的id
			map.put("alreadyMoney", borrow.getAlreadyMoney());// 已投金额
			map.put("startMoney", borrow.getStartMoney());// 起投金额
			map.put("surplusMoney", borrow.getSurplusMoney());// 未投金额
			map.put("endMoney", borrow.getEndMoney());// 投资上限
			map.put("isTimesInvest", borrow.getIsTimesInvest());// 是否倍数投资
			map.put("isJiaxiTicket", borrow.getIsJiaxiTicket());// 是否可以使用加息
			map.put("borrowRate", borrow.getBorrowRate());// 年华利率
			map.put("borDeadline", borrow.getBorDeadline());// 借款期限
			map.put("accrualType", borrow.getAccrualType());// 计息类型
			map.put("borrowCode", borrow.getBorrowCode());// 借款编号
			map.put("investRewardScale", borrow.getInvestRewardScale());// 投资奖励比例
			map.put("systime", System.currentTimeMillis());
			jsonObject.setResult(map);
			jsonObject.setCode("200");
			jsonObject.setMessage("查询成功");
			jsonObject.setStatus(true);
		} catch (Exception e) {
			jsonObject.setMessage(e.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			processResultStatus(jsonObject, ApiCode.HTTP_CODE_900,
					e.getMessage(), false);
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}

	/**
	 * Description：<br>
	 * 立即投资
	 * 
	 * @author shiliang.feng
	 * @date 2015年12月26日
	 * @version v1.0.0
	 * @param borrowDetail
	 *            投资记录
	 * @param pwd
	 *            交易密码
	 * 
	 * @return
	 * @throws FrameworkException
	 */
	@RequestMapping(value = "/saveBorrow", method = RequestMethod.POST)
	@ResponseBody
	public String saveBorrow(HttpServletRequest request,
			HttpServletResponse response) throws FrameworkException {
		HashMap<String, Object> result = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
		CustomerVo tcustomer = getCustomer(xtoken);
		String userid = tcustomer.getPid();
		try {
			NewStandardArgsVo args = (NewStandardArgsVo) ApiUtil
					.convertObjectByBody(request, NewStandardArgsVo.class);

			BigDecimal amount = new BigDecimal(args.getAmount());
			String userId = args.getUserId();
			String investAwardId = args.getInvestAwardId();
			String borrowId = args.getBorrowId();
			BizBorrowDetail detail = new BizBorrowDetail();

			detail.setBorrowId(borrowId);
			detail.setInvestmentAmount(amount);
			detail.setCustomerId(userId);
			detail.setInvestAwardId(investAwardId);
			result = detailServiceImpl.investmentAuto(detail, userid, "3");
			result.put("errorCode", "");
			result.put("num", -1);
			result.put("t", -1);

			jsonObject.setResult(result);
			jsonObject.setCode("200");
			jsonObject.setMessage("查询成功");
			jsonObject.setStatus(true);
		} catch (Exception e) {
			jsonObject.setMessage(e.getMessage());
			logger.error(e.getMessage());
			processResultStatus(jsonObject, ApiCode.HTTP_CODE_900,
					e.getMessage(), false);
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}

	/**
	 * Description：<br>
	 * 根据标的id 查询 标的返回计划
	 * 
	 * @author shiliang.feng
	 * @date 2016年1月21日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws FrameworkException
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/selectRepaymentsByBorrowId", method = RequestMethod.POST)
	@ResponseBody
	public String selectRepaymentsByBorrowId(HttpServletRequest request,
			HttpServletResponse response) throws FrameworkException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		try {
			BizRepaymentPlanVo args = (BizRepaymentPlanVo) ApiUtil
					.convertObjectByBody(request, BizRepaymentPlanVo.class);
			// 还款计划
			List<BizRepaymentPlan> planList = bizRepaymentPlanServiceImpl
					.selectRepaymentsByBorrowId(args.getBorrowId());
			List<BizRepaymentPlanVo> planVoList = (List<BizRepaymentPlanVo>) ConvertObjectUtil
					.batchConvertObject(planList, BizRepaymentPlanVo.class);
			map.put("result", planVoList);
			jsonObject.setResult(map);
			jsonObject.setCode("200");
			jsonObject.setMessage("查询成功");
			jsonObject.setStatus(true);
		} catch (Exception e) {
			jsonObject.setMessage(e.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			processResultStatus(jsonObject, ApiCode.HTTP_CODE_900,
					e.getMessage(), false);
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
}
