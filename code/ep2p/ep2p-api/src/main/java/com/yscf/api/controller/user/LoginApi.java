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
package com.yscf.api.controller.user;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jodd.util.StringUtil;
import net.rubyeye.xmemcached.MemcachedClient;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.util.DateUtil;
import com.achievo.framework.vo.JsonObject;
import com.yscf.api.common.ApiCode;
import com.yscf.api.common.ConstantApi;
import com.yscf.api.controller.base.EscfBaseApi;
import com.yscf.api.controller.otherinfo.CalculatorApi;
import com.yscf.api.util.ApiUtil;
import com.yscf.api.vo.user.CustomerVo;
import com.yscf.api.vo.user.LoginVo;
import com.yscf.common.Constant.Constant;
import com.yscf.common.util.ConvertObjectUtil;
import com.yscf.core.model.system.SysParams;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.model.user.CustomerDto;
import com.yscf.core.service.system.impl.SysParamsServiceImpl;
import com.yscf.core.service.user.impl.CusTomerDtoServiceImpl;
import com.yscf.core.service.user.impl.CusTomerServiceImpl;
import com.yscf.core.service.user.impl.LoginEptpServiceImpl;
import com.yscf.core.ucenter.BBSApi;

/**
 * Description：<br> 
 * 用户登录API控制
 * @author  Yu.Zhang
 * @date    2015年12月28日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/user/loginApi")
public class LoginApi extends EscfBaseApi {
	
	@Autowired
	public LoginApi(LoginEptpServiceImpl service) {
		super(service);
	}

	private Logger logger = LoggerFactory.getLogger(CalculatorApi.class);
	
	@Resource(name="memcachedClient")
	private MemcachedClient memcachedClient;
	
	@Resource(name = "cusTomerService")
	private CusTomerServiceImpl cusTomerService;	// 客户信息
	
	@Resource(name = "cusTomerDtoService")
	private CusTomerDtoServiceImpl cusTomerDtoService;	// 客户信息
	
	@Resource(name = "sysParamsService")
	private SysParamsServiceImpl sysParamsService;
	
	
	/**
	 * 
	 * Description：<br> 
	 * 用户登录App接口
	 * @author  Yu.Zhang
	 * @date    2015年12月28日
	 * @version v1.0.0
	 * @param loginName 登录名
	 * @param password  密码
	 * @param loginFlag 是否免用户名密码登录
	 * @param request
	 * @param response
	 * @return JsonObject
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/login",method=RequestMethod.POST)
	@ResponseBody
	public String login(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		Map<String, Object> loginResult = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		try {
			CustomerVo custvo  = null;
			LoginVo loginVo = (LoginVo) ApiUtil.convertObjectByBody(request, LoginVo.class);
			
			Boolean result = false;
			// 判断是否免用户名、密码登录
			if("yes".equalsIgnoreCase(loginVo.getLoginFlag())){
				result = true;
				
				// 清空错误时间，错误次数 返回成功
				CusTomer cusTomer = cusTomerService.selectByLoginName(loginVo.getLoginName());
				cusTomerService.clearLoginError(cusTomer);
				
				// 将客户信息查询出来设置结果集中
				CustomerDto customerVo = cusTomerDtoService.getCustomerDtoByPid(cusTomer.getPid());
				loginResult.put(Constant.CUS_KEY, customerVo);
			}else{
				LoginEptpServiceImpl service = (LoginEptpServiceImpl) getService();
				loginResult = service.login(loginVo.getLoginName(), loginVo.getPassword());
				result = (Boolean) loginResult.get(Constant.LOGIN_RESULT);
			}
			
			if(result){
				// 加载数据至session中
				CustomerDto sessionCusTomer = (CustomerDto) loginResult.get(Constant.CUS_KEY);
				if(null == sessionCusTomer.getCustomerName()){
					sessionCusTomer.setCustomerName(sessionCusTomer.getPhoneNo());
				}
				
				custvo = ConvertObjectUtil.convertObject(sessionCusTomer, CustomerVo.class);
				//　保存客户对象值
				memcachedClient.add(custvo.getPid(), ConstantApi.LOGIN_TIME, custvo);
				
				loginResult.clear();
				
				// token 信息返回
				loginResult.put(ConstantApi.TOKEN, custvo.getPid());
//				loginResult.put("customerVo", custvo);
				loginResult.put("systemTime", DateUtil.getToday().getTime());
				jsonObject.setResult(loginResult);
				
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"登录成功",true);
				
				// 更新移动设备的机器代码
				cusTomerService.updateMobileDeviceMachineCode(sessionCusTomer.getPid(),sessionCusTomer.getPid());
			}else{
				// 登陆错误信息处理
				String errorType = (String) loginResult.get(Constant.LOGIN_ERROR_KEY);
				String errorMessage = "";
				if(Constant.LOGIN_ERROR_1.equals(errorType)){
					errorMessage = "登录名不存在!";
				}else if(Constant.LOGIN_ERROR_2.equals(errorType)){
					errorMessage = "密码错误!";
				}else if(Constant.LOGIN_ERROR_3.equals(errorType)){
					errorMessage = "用户已被锁定!";
				}else if(Constant.LOGIN_ERROR_5.equals(errorType)){
					errorMessage = "该用户已被禁用，请联系客服！";
				}else if(Constant.LOGIN_ERROR_6.equals(errorType)){
					errorMessage = "该用户已被禁用，请联系客服！";
				}
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,errorMessage,true);
			}
		} catch (Exception e) {
			e.printStackTrace();
			if(logger.isDebugEnabled()){
				logger.debug("用户App登录失败"+e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	

	
	/**
	 * 
	 * Description：<br> 
	 * 获取论坛地址已经同步登陆结果
	 * @author  Yu.Zhang
	 * @date    2015年12月28日
	 * @version v1.0.0
	 * @param loginName 登录名
	 * @param password  密码
	 * @param loginFlag 是否免用户名密码登录
	 * @param request
	 * @param response
	 * @return JsonObject
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/getBbsLoginResult",method=RequestMethod.POST)
	@ResponseBody
	public String getBbsLoginResult(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		JsonObject jsonObject = new JsonObject();
		try {
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			
			// 同步登陆论坛、
			CusTomer cusTomer = cusTomerService.selectByPrimaryKey(xtoken);
			String result = BBSApi.login(cusTomer.getCustomerName(), cusTomer.getPassword());
			
			// 获取系统配置的论坛地址
			SysParams sysParams =  sysParamsService.getParamsByParamKey("FORUM_ADDRESS");
			String bbsAddress = sysParams.getParamValue();
			String bbsLoginResult = null;
			if(StringUtil.isNotEmpty(result)){
				String [] results =  result.split("src=");
				for(int i = 0 ;i < results.length ; i ++){
					if(results[i].contains(bbsAddress)){
						bbsLoginResult = results[i].split("reload")[0];
						break;
					}
				}
			}
			
			Map<String, Object> loginResult = new HashMap<String, Object>();
			loginResult.put("bbsResult", bbsLoginResult.replaceAll("\"", "").trim());
			loginResult.put("bbsAddress", bbsAddress.trim());
			jsonObject.setResult(loginResult);
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"操作成功",true);
		} catch (Exception e) {
			e.printStackTrace();
			if(logger.isDebugEnabled()){
				logger.debug("用户App登录失败"+e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	
	
	
	/**
	 * 
	 * Description：<br> 
	 * 退出
	 * @author  Yu.Zhang
	 * @date    2015年12月28日
	 * @version v1.0.0
	 * @param loginName 登录名
	 * @param password  密码
	 * @param loginFlag 是否免用户名密码登录
	 * @param request
	 * @param response
	 * @return JsonObject
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/loginout",method=RequestMethod.POST)
	@ResponseBody
	public String loginout(HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		JsonObject jsonObject = new JsonObject();
		try {
			String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
			//清空缓存数据
			if(null != xtoken && !"".equals(xtoken))
				memcachedClient.delete(xtoken);
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"退出成功",true);
		} catch (Exception e) {
			e.printStackTrace();
			if(logger.isDebugEnabled()){
				logger.debug("用户App登录失败"+e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
}


