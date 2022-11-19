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
 * 2015年12月28日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.api.controller.activity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.NullArgumentException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.vo.JsonObject;
import com.yscf.api.common.ApiCode;
import com.yscf.api.controller.base.EscfBaseApi;
import com.yscf.api.util.ApiUtil;
import com.yscf.api.vo.exchange.CustExchangeDetailVo;
import com.yscf.core.exception.PointNotEnoughException;
import com.yscf.core.model.system.SysDictionaryContent;
import com.yscf.core.model.system.SysParams;
import com.yscf.core.model.user.CustPoinWater;
import com.yscf.core.service.exchange.impl.ExchangeServiceImpl;
import com.yscf.core.service.system.impl.SysDictionaryContentServiceImpl;
import com.yscf.core.service.system.impl.SysParamsServiceImpl;
import com.yscf.core.service.user.impl.CusTomerServiceImpl;
import com.yscf.core.service.user.impl.CustPoinWaterServiceImpl;

/**
 * 
 * @ClassName : ExchangeApi
 * @Description : 兑换API控制
 * @Author : Qing.Cai
 * @Date : 2016年1月21日 下午3:23:16
 */
@Controller
@RequestMapping("/exchange/exchangeApi")
public class ExchangeApi extends EscfBaseApi {

	@Autowired
	public ExchangeApi(ExchangeServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return CustPoinWater.class;
	}

	private Logger logger = LoggerFactory.getLogger(ExchangeApi.class);

	/** 兑换业务处理接口 */
	@Resource(name = "exchangeServiceImpl")
	private ExchangeServiceImpl exchangeServiceImpl;

	/** 客户管理服务接口 */
	@Resource(name = "cusTomerService")
	private CusTomerServiceImpl customerService;

	/** 数据字典内容服务接口 */
	@Resource(name = "sysDictionaryContentService")
	private SysDictionaryContentServiceImpl sysDictionaryContentService;
	
	/** 客户资料明细 */
	@Resource(name = "custPoinWaterServiceImpl")
	private CustPoinWaterServiceImpl custPoinWaterServiceImpl;
	
	/** 系统配置服务接口 */
	@Resource(name = "sysParamsService")
	private SysParamsServiceImpl sysParamsServiceImpl;
	

	/**
	 * 
	 * @Description : 积分兑换话费
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return JsonObject
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2016年1月21日 下午3:26:57
	 */
	@RequestMapping(value = "/exchangeTelephoneFare", method = RequestMethod.POST)
	@ResponseBody
	public String exchangeTelephoneFare(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		JsonObject jsonObject = new JsonObject();
		try {
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			// 用户ID
			String userId = getCustomerId(xtoken);
			// 获取传过来的参数
			CustExchangeDetailVo exchange = (CustExchangeDetailVo) ApiUtil.convertObjectByBody(request, CustExchangeDetailVo.class);

			if (!StringUtils.hasLength(exchange.getPhoneNo())) {
				processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "手机号码不能为空", false);
			}else if (!StringUtils.hasLength(exchange.getDictionaryContentId())) {
				processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "兑换对应物品不能为空", false);
			}else if (!StringUtils.hasLength(userId)) {
				processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "客户id为null", false);
			}else{
				// 兑换
				String resultStr = exchangeServiceImpl.exchangePhoneFees(userId, exchange.getDictionaryContentId(), exchange.getPhoneNo());
				JSONObject a = new JSONObject(resultStr);
				if (a.get("Code").equals("0") && a.get("Msg").equals("success")) {
					processResultStatus(jsonObject, ApiCode.HTTP_CODE_200, "兑换话费成功", true);
				}else{
					processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "兑换话费失败", false);
				}
				
			}
		} catch (PointNotEnoughException e) {
			// 积分不够
			processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "积分不够", false);
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
		} catch (Exception e) {
			processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "兑换话费失败", false);
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}

	/**
	 * 
	 * @Description : 兑换加息劵
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return JsonObject
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2016年1月21日 下午7:20:41
	 */
	@RequestMapping(value = "/exchangeInterestTicket", method = RequestMethod.POST)
	@ResponseBody
	public String exchangeInterestTicket(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		JsonObject jsonObject = new JsonObject();
		try {
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			// 用户ID
			String userId = getCustomerId(xtoken);
			// 获取传过来的参数
			CustExchangeDetailVo exchange = (CustExchangeDetailVo) ApiUtil.convertObjectByBody(request, CustExchangeDetailVo.class);

			if (!StringUtils.hasLength(exchange.getDictionaryContentId())) {
				processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "兑换对应物品不能为空", false);
			}else if (!StringUtils.hasLength(userId)) {
				processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "客户id为null", false);
			}else{
				// 兑换
				exchangeServiceImpl.exchangeInterestTicket(userId, exchange.getDictionaryContentId());
	
				processResultStatus(jsonObject, ApiCode.HTTP_CODE_200, "兑换加息劵成功", true);
			}
		} catch (PointNotEnoughException e) {
			// 积分不够
			processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "积分不够", false);
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
		} catch (Exception e) {
			processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "兑换加息劵失败", false);
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}

	/**
	 * 
	 * @Description : 兑换VIP
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return JsonObject
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2016年1月21日 下午7:21:58
	 */
	@RequestMapping(value = "/exchangeVIP", method = RequestMethod.POST)
	@ResponseBody
	public String exchangeVIP(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		try {
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			// 用户ID
			String userId = getCustomerId(xtoken);
//			String userId = xtoken;
			// 获取传过来的参数
			CustExchangeDetailVo exchange = (CustExchangeDetailVo) ApiUtil.convertObjectByBody(request, CustExchangeDetailVo.class);

			if (!StringUtils.hasLength(exchange.getDictionaryContentId())) {
				processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "兑换对应物品不能为空", false);
			}else if (!StringUtils.hasLength(userId)) {
				processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "客户id为null", false);
			}else{
				// 兑换
				String vipEndTime = exchangeServiceImpl.exchangeVIP(userId, exchange.getDictionaryContentId());
				// 返回VIP到期时间
				map.put("vipEndTime", vipEndTime);
				
				jsonObject.setResult(map);
				
				processResultStatus(jsonObject, ApiCode.HTTP_CODE_200, "兑换VIP成功", true);
			}
		} catch (PointNotEnoughException e) {
			// 积分不够
			processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "积分不够", false);
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
		} catch (Exception e) {
			processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "兑换VIP失败", false);
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}

	/**
	 * 
	 * @Description : 兑换现金
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return JsonObject
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2016年1月21日 下午7:21:45
	 */
	@RequestMapping(value = "/exchangeCash", method = RequestMethod.POST)
	@ResponseBody
	public String exchangeCash(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		JsonObject jsonObject = new JsonObject();
		try {
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			// 用户ID
			String userId = getCustomerId(xtoken);
			// 获取传过来的参数
			CustExchangeDetailVo exchange = (CustExchangeDetailVo) ApiUtil.convertObjectByBody(request, CustExchangeDetailVo.class);

			if (!StringUtils.hasLength(exchange.getDictionaryContentId())) {
				processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "兑换对应物品不能为空", false);
			}else if (!StringUtils.hasLength(userId)) {
				processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "客户id为null", false);
			}else{
				// 兑换
				exchangeServiceImpl.exchangeCash(userId, exchange.getDictionaryContentId());
	
				processResultStatus(jsonObject, ApiCode.HTTP_CODE_200, "兑换现金成功", true);
			}
		} catch (PointNotEnoughException e) {
			// 积分不够
			processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "积分不够", false);
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
		} catch (Exception e) {
			processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "兑换现金失败", false);
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}

	/**
	 * 
	 * @Description : 根据类型查询兑换所需的积分and值
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return JsonObject
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2016年1月21日 下午7:33:44
	 */
	@RequestMapping(value = "/selectExchangeIntegral", method = RequestMethod.POST)
	@ResponseBody
	public String selectExchangeIntegral(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		try {
			// 获取传过来的参数
			CustExchangeDetailVo exchange = (CustExchangeDetailVo) ApiUtil.convertObjectByBody(request, CustExchangeDetailVo.class);

			if (!StringUtils.hasLength(exchange.getDictCode())) {
				throw new NullArgumentException("数据字典编码不能为空");
			}
			// 查询
			List<SysDictionaryContent> list = sysDictionaryContentService.selectByDisctCode(exchange.getDictCode());
			if (null == list || list.size() == 0) {
				// 如果为空，就创建一个空对象
				list = new ArrayList<SysDictionaryContent>();
			}
			
			// 如果是加息劵就查询出有效期出来
			SysParams sysParams = sysParamsServiceImpl.getParamsByParamKey("INTEREST_TICKET_VALIDITY");
			
			map.put("list", list);
			map.put("interestTicketValidity", sysParams.getParamValue());
			jsonObject.setResult(map);
			
			processResultStatus(jsonObject, ApiCode.HTTP_CODE_200, "查询兑换所需积分成功", true);

		} catch (Exception e) {
			processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "查询兑换所需积分失败", false);
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}

	/**
	 * 
	 * @Description : 查询个人积分明细
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2016年1月21日 下午9:41:26
	 */
	@RequestMapping(value = "/selectUserPointDetail", method = RequestMethod.POST)
	@ResponseBody
	public String selectUserPointDetail(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		try {
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			// 用户ID
			String userId = getCustomerId(xtoken);
//			String userId = xtoken;
			// 获取传过来的参数
			CustExchangeDetailVo exchange = (CustExchangeDetailVo) ApiUtil.convertObjectByBody(request, CustExchangeDetailVo.class);

			if (!StringUtils.hasLength(userId)) {
				throw new NullArgumentException("客户id为null");
			}
			
			// 初始化分页对象
			Integer pageIndex = 1;
			Integer pageSize = 10;
			if(null != exchange){
				pageIndex = exchange.getPageIndex() == null ? 1 : exchange.getPageIndex();
				pageSize = exchange.getPageSize() == null ? 10 : exchange.getPageSize();
			}
			pageIndex = (pageIndex - 1) * pageSize;
			
			// 查询
			List<CustPoinWater> list = custPoinWaterServiceImpl.selectUserPointDetailAPI(userId, pageIndex, pageSize);
			if (null == list || list.size() == 0) {
				// 如果为空,就创建一个空对象
				list = new ArrayList<CustPoinWater>();
			}
			map.put("list", list);

			jsonObject.setResult(map);
			
			processResultStatus(jsonObject, ApiCode.HTTP_CODE_200, "查询个人积分明细成功", true);

		} catch (Exception e) {
			processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "查询个人积分明细失败", false);
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}

}
