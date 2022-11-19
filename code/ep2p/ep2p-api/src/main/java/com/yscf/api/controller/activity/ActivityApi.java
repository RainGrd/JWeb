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
import com.achievo.framework.util.DateUtil;
import com.achievo.framework.util.StringUtil;
import com.achievo.framework.vo.JsonObject;
import com.yscf.api.common.ApiCode;
import com.yscf.api.controller.base.EscfBaseApi;
import com.yscf.api.util.ApiUpDownFileUtil;
import com.yscf.api.util.ApiUtil;
import com.yscf.api.util.IdcardApiUtil;
import com.yscf.api.vo.exchange.BorrowVo;
import com.yscf.api.vo.system.ApiFileObject;
import com.yscf.api.vo.system.FileConfig;
import com.yscf.api.vo.user.AttestationVo;
import com.yscf.common.Constant.Constant;
import com.yscf.core.model.activity.ActExpActDetail;
import com.yscf.core.model.business.BizBorrow;
import com.yscf.core.model.business.BizRepaymentPlan;
import com.yscf.core.model.pub.PubFile;
import com.yscf.core.model.user.CustInterestTicket;
import com.yscf.core.service.activity.impl.ActActivityServiceImpl;
import com.yscf.core.service.business.impl.BizBorrowServiceImpl;
import com.yscf.core.service.business.impl.BizRepaymentPlanServiceImpl;
import com.yscf.core.service.user.impl.CusTomerServiceImpl;
import com.yscf.core.service.user.impl.CustAuthenticationHisServiceImpl;
import com.yscf.core.service.user.impl.LoginEptpServiceImpl;

/**
 * 
 * @ClassName : ActivityApi
 * @Description : 活动API控制
 * @Author : Qing.Cai
 * @Date : 2016年1月21日 下午2:29:50
 */
@Controller
@RequestMapping("/activity/activityApi")
public class ActivityApi extends EscfBaseApi {

	private Logger logger = LoggerFactory.getLogger(ActivityApi.class);

	@Autowired
	public ActivityApi(LoginEptpServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return AttestationVo.class;
	}

	// 存放有效证件图片路径
	private static String AUTHENTICATION_PATH = "upload/authentication/";

	/** 客户信息 */
	@Resource(name = "cusTomerService")
	private CusTomerServiceImpl cusTomerService;
	/** 实名认证 */
	@Resource(name = "custAuthenticationHisServiceImpl")
	private CustAuthenticationHisServiceImpl custAuthenticationHisServiceImpl;
	/** 活动 */
	@Resource(name = "actActivityServiceImpl")
	private ActActivityServiceImpl actActivityServiceImpl;
	/** 还款计划 */
	@Resource(name = "bizRepaymentPlanService")
	private BizRepaymentPlanServiceImpl bizRepaymentPlanService;
	/** 借款 */
	@Resource(name = "bizBorrowService")
	private BizBorrowServiceImpl bizBorrowService;

	/** 文档配置对象 */
	@Resource(name = "fileConfig")
	private FileConfig fileconfig;

	/**
	 * 
	 * @Description : 实名认证（大陆）
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return JsonObject
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2016年1月23日 下午5:15:53
	 */
	@RequestMapping(value = "/realNameAuthenticationMainland", method = RequestMethod.POST)
	@ResponseBody
	public String realNameAuthenticationMainland(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		JsonObject jsonObject = new JsonObject();
		try {
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			// 用户ID
			String userId = getCustomerId(xtoken);
			// String userId = xtoken;
			// 获取传过来的参数
			AttestationVo attestation = (AttestationVo) ApiUtil.convertObjectByBody(request, AttestationVo.class);
			if (!StringUtil.isNotEmpty(xtoken)) {
				processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "客户ID不能为null", false);
			} else if (!StringUtils.hasLength(attestation.getSname())) {
				processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "真实姓名不能为空", false);
			} else if (!StringUtils.hasLength(attestation.getIdentificationNo())) {
				processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "身份证号不能为空", false);
			} else {
				// 调用检查身份证号码是否正确的接口
				String identival = attestation.getIdentificationNo();
//				String resultStr = ApixUtil.checkIdcardinfo(identival);
//				String resultStr = "";
				//ApixUtil.checkIdcardinfo(identival);
				String sname = attestation.getSname();
				String resultStr = IdcardApiUtil.checkIdcardinfo(identival,sname);
				if (StringUtil.isNotBlank(resultStr)) {
					JSONObject a = new JSONObject(resultStr);
					if (a.get("success").equals("true")) {
						if(a.get("result").equals("1")){
							processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "姓名和身份证不一致", false);
						}else if(a.get("result").equals("2")){
							processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "无法找到此身份证信息", false);
						}
						// 修改客户信息
						custAuthenticationHisServiceImpl.realNameAuthenticationMainland(userId, attestation.getSname(), attestation.getIdentificationNo());
						// 返回处理结果
						processResultStatus(jsonObject, ApiCode.HTTP_CODE_200, "实名认证成功", true);
						// 判断是否赠送红包
						actActivityServiceImpl.giveRedByType(userId, Constant.AUTHENTICA_GIVERED);
					} else {
						processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "实名认证失败", false);
					}
				} else {
					processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "实名认证失败", false);
				}
			}

		} catch (Exception e) {
			processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "实名认证失败", false);
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}

	/**
	 * 
	 * @Description : 实名认证（非大陆）
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return JsonObject
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2016年1月23日 下午5:21:43
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/realNameAuthenticationNonMainland", method = RequestMethod.POST)
	@ResponseBody
	public String realNameAuthenticationNonMainland(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		JsonObject jsonObject = new JsonObject();
		try {
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			// 用户ID
			String userId = getCustomerId(xtoken);

			// 获取传过来的参数
			if (!StringUtil.isNotEmpty(xtoken)) {
				processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "客户ID不能为null", false);
			} else{
				// 读取上传中的文件信息并保存
				String basePath = fileconfig.getFileRoot() + "/";
				String savePath = AUTHENTICATION_PATH;
				String rflname = getCustomer(xtoken).getSname() + "_authentication_" + System.currentTimeMillis() + ".jpg";
				// 读取文件
				HashMap<String, Object> map = ApiUpDownFileUtil.readUPloadFile(request, response, basePath, savePath, rflname, null);
				// 判断是否成功
				if (!(Boolean) map.get("status")) {
					processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "文件读取失败", false);
				} else if (!StringUtils.hasLength((String)map.get("sname"))){
					processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "真实姓名不能为空", false);
				} else if (!StringUtils.hasLength((String)map.get("identificationNo"))){
					processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "身份证号不能为空", false);
				} else {
					// 解析文件List
					List<ApiFileObject> list = (List<ApiFileObject>) map.get(ApiUpDownFileUtil.FILE_UPOBJECT);
					// 文件List
					List<PubFile> listPubFile = new ArrayList<PubFile>();
					// 循环解析
					for (int i = 0; i < list.size(); i++) {
						ApiFileObject afo = list.get(i);
						// 获取创建文件对象
						PubFile pubFile = new PubFile();
						pubFile.setFileName(afo.getFilename());// 文件名称
						pubFile.setFileType("jpg");// 文件类型
						pubFile.setFileUrl(afo.getRelativepath());// 文件路径
						pubFile.setIsShow("1");// 是否显示(1.显示)
						pubFile.setStatus(Constant.PUBLIC_STATUS);// 默认状态
						pubFile.setCreateUser(getCustomerId(xtoken));// 创建人ID
						pubFile.setCreateDate(DateUtil.getToday().toString());// 创建时间
						// 添加到list
						listPubFile.add(pubFile);
					}
					custAuthenticationHisServiceImpl.realNameAuthenticationNonMainland(userId, (String)map.get("sname"), (String)map.get("identificationNo"), listPubFile);

					processResultStatus(jsonObject, ApiCode.HTTP_CODE_200, "实名认证成功", true);
				}
			}
		} catch (Exception e) {
			processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "实名认证失败", false);
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}

	/**
	 * 
	 * @Description : 查询用户的卡卷
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return JsonObject
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2016年1月26日 上午9:42:16
	 */
	@RequestMapping(value = "/selectUserCardVolume", method = RequestMethod.POST)
	@ResponseBody
	public String selectUserCardVolume(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		JsonObject jsonObject = new JsonObject();
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			// 用户ID
			String userId = getCustomerId(xtoken);
			// String userId = xtoken;
			if (StringUtils.hasLength(userId)) {
				// 获取客户的卡劵
				List<CustInterestTicket> list = cusTomerService.selectUserCardVolume(userId);
				if (null == list || list.size() == 0) {
					// 如果为空就创建一个空对象
					list = new ArrayList<CustInterestTicket>();
				}
				map.put("list", list);
				jsonObject.setResult(map);

				processResultStatus(jsonObject, ApiCode.HTTP_CODE_200, "查询卡卷成功", false);
			} else {
				processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "参数不能为空", false);
			}

		} catch (Exception e) {
			processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "查询卡卷失败", false);
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}

	/**
	 * 
	 * @Description : 我的卡劵-已失效or已过期
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return JsonObject
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2016年1月26日 下午4:00:49
	 */
	@RequestMapping(value = "/selectUserCardVolumeHasExpired", method = RequestMethod.POST)
	@ResponseBody
	public String selectUserCardVolumeHasExpired(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		JsonObject jsonObject = new JsonObject();
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			// 用户ID
			String userId = getCustomerId(xtoken);
			// String userId = xtoken;
			if (StringUtils.hasLength(userId)) {
				// 获取客户的卡劵（已失效or已过期）
				List<CustInterestTicket> list = cusTomerService.selectUserCardVolumeHasExpired(userId);
				if (null == list || list.size() == 0) {
					// 如果为空就创建一个空对象
					list = new ArrayList<CustInterestTicket>();
				}
				map.put("list", list);
				jsonObject.setResult(map);

				processResultStatus(jsonObject, ApiCode.HTTP_CODE_200, "查询卡卷成功", false);
			} else {
				processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "参数不能为空", false);
			}
		} catch (Exception e) {
			processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "查询卡卷失败", false);
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}

	/**
	 * 
	 * @Description : 查询客户的体验金列表-可用
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return JsonObject
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2016年1月27日 下午6:50:09
	 */
	@RequestMapping(value = "/selectCustTomerExperienceGold", method = RequestMethod.POST)
	@ResponseBody
	public String selectCustTomerExperienceGold(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		JsonObject jsonObject = new JsonObject();
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			// 用户ID
			String userId = getCustomerId(xtoken);
			// String userId = xtoken;
			if (StringUtils.hasLength(userId)) {
				// 获取客户的体验金列表
				List<ActExpActDetail> list = cusTomerService.selectCustTomerExperienceGold(userId);
				if (null == list || list.size() == 0) {
					// 如果为空就创建一个空对象
					list = new ArrayList<ActExpActDetail>();
				}
				map.put("list", list);
				jsonObject.setResult(map);

				processResultStatus(jsonObject, ApiCode.HTTP_CODE_200, "查询客户的体验金列表成功", false);
			} else {
				processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "参数不能为空", false);
			}

		} catch (Exception e) {
			processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "查询客户的体验金列表失败", false);
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}

	/**
	 * 
	 * @Description : 待还款列表
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return JsonObject
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2016年1月27日 下午8:48:49
	 */
	@RequestMapping(value = "/selectPendingRepayment", method = RequestMethod.POST)
	@ResponseBody
	public String selectPendingRepayment(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		JsonObject jsonObject = new JsonObject();
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			// 用户ID
			String userId = getCustomerId(xtoken);
			// 获取传过来的参数
			BorrowVo borrowVo = (BorrowVo) ApiUtil.convertObjectByBody(request, BorrowVo.class);
			if (StringUtils.hasLength(userId)) {
				// 初始化分页对象
				Integer pageIndex = 1;
				Integer pageSize = 10;
				if (null != borrowVo) {
					pageIndex = borrowVo.getPageIndex() == null ? 1 : borrowVo.getPageIndex();
					pageSize = borrowVo.getPageSize() == null ? 10 : borrowVo.getPageSize();
				}
				pageIndex = (pageIndex - 1) * pageSize;

				// 获取客户的待还款列表
				List<BizRepaymentPlan> list = bizRepaymentPlanService.selectPendingRepaymentAPI(userId, pageIndex, pageSize);
				if (null == list || list.size() == 0) {
					// 如果为空就创建一个空对象
					list = new ArrayList<BizRepaymentPlan>();
				}
				map.put("list", list);
				jsonObject.setResult(map);

				processResultStatus(jsonObject, ApiCode.HTTP_CODE_200, "查询客户的待还款列表成功", false);
			} else {
				processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "参数不能为空", false);
			}

		} catch (Exception e) {
			processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "查询客户的待还款列表失败", false);
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}

	/**
	 * 
	 * @Description : 已还款列表
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return JsonObject
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2016年1月27日 下午9:50:10
	 */
	@RequestMapping(value = "/selectAlreadyRepayment", method = RequestMethod.POST)
	@ResponseBody
	public String selectAlreadyRepayment(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		JsonObject jsonObject = new JsonObject();
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			// 用户ID
			String userId = getCustomerId(xtoken);
			// 获取传过来的参数
			BorrowVo borrowVo = (BorrowVo) ApiUtil.convertObjectByBody(request, BorrowVo.class);
			if (StringUtils.hasLength(userId)) {
				// 初始化分页对象
				Integer pageIndex = 1;
				Integer pageSize = 10;
				if (null != borrowVo) {
					pageIndex = borrowVo.getPageIndex() == null ? 1 : borrowVo.getPageIndex();
					pageSize = borrowVo.getPageSize() == null ? 10 : borrowVo.getPageSize();
				}
				pageIndex = (pageIndex - 1) * pageSize;

				// 获取客户的待还款列表
				List<BizBorrow> list = bizBorrowService.selectAlreadyRepaymentAPI(userId, pageIndex, pageSize);
				if (null == list || list.size() == 0) {
					// 如果为空就创建一个空对象
					list = new ArrayList<BizBorrow>();
				}
				map.put("list", list);
				jsonObject.setResult(map);

				processResultStatus(jsonObject, ApiCode.HTTP_CODE_200, "查询客户的待还款列表成功", false);
			} else {
				processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "参数不能为空", false);
			}

		} catch (Exception e) {
			processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "查询客户的待还款列表失败", false);
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}

	/**
	 * 
	 * @Description : 我的加息劵列表
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return JsonObject
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2016年1月28日 上午11:27:02
	 */
	@RequestMapping(value = "/selectUserInterestTicket", method = RequestMethod.POST)
	@ResponseBody
	public String selectUserInterestTicket(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		JsonObject jsonObject = new JsonObject();
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			// 用户ID
			String userId = getCustomerId(xtoken);
			// 获取传过来的参数
			BorrowVo borrowVo = (BorrowVo) ApiUtil.convertObjectByBody(request, BorrowVo.class);
			if (StringUtils.hasLength(userId)) {
				// 初始化分页对象
				Integer pageIndex = 1;
				Integer pageSize = 10;
				if (null != borrowVo) {
					pageIndex = borrowVo.getPageIndex() == null ? 1 : borrowVo.getPageIndex();
					pageSize = borrowVo.getPageSize() == null ? 10 : borrowVo.getPageSize();
				}
				pageIndex = (pageIndex - 1) * pageSize;

				// 获取我的加息劵列表
				List<CustInterestTicket> list = cusTomerService.selectUserInterestTicketAPI(userId, pageIndex, pageSize);
				if (null == list || list.size() == 0) {
					// 如果为空就创建一个空对象
					list = new ArrayList<CustInterestTicket>();
				}
				map.put("list", list);
				jsonObject.setResult(map);

				processResultStatus(jsonObject, ApiCode.HTTP_CODE_200, "查询我的加息劵列表成功", false);
			} else {
				processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "参数不能为空", false);
			}

		} catch (Exception e) {
			processResultStatus(jsonObject, ApiCode.HTTP_CODE_900, "查询我的加息劵列表失败", false);
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}

}
