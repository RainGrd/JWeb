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
 * 2015年12月26日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.user.impl;

import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.achievo.framework.entity.BaseEntity;
import com.achievo.framework.service.impl.BaseService;
import com.achievo.framework.util.DateUtil;
import com.achievo.framework.util.StringUtil;
import com.yscf.common.Constant.Constant;
import com.yscf.common.util.EncodedUtil;
import com.yscf.common.util.EscfDateUtil;
import com.yscf.core.dao.user.CusTomerMapper;
import com.yscf.core.model.system.SysParams;
import com.yscf.core.model.user.CusTomer;
import com.yscf.core.model.user.CustomerDto;
import com.yscf.core.service.system.impl.SysParamsServiceImpl;
import com.yscf.core.service.user.ICusTomerDtoService;
import com.yscf.core.service.user.ILoginEptpService;

/**
 * Description：<br> 
 * 前台登录业务实现类
 * @author  Yu.Zhang
 * @date    2015年12月26日
 * @version v1.0.0
 */
@Service("loginEptpService")
public class LoginEptpServiceImpl  extends BaseService<BaseEntity, String> implements ILoginEptpService {
	
	@Autowired
	public LoginEptpServiceImpl(CusTomerMapper dao) {
		super(dao);
	}

	@Resource(name = "sysParamsService")
	private SysParamsServiceImpl sysParamsService;	// 系统参数

	@Resource(name = "cusTomerService")
	private CusTomerServiceImpl cusTomerService;	// 客户信息
	
	@Resource(name = "cusTomerDtoService")
	private ICusTomerDtoService cusTomerDtoService;	// 客户信息
	
	@Resource(name = "cusTomerMapper")
	private CusTomerMapper cusTomerMapper;
	
	/**
	 * ep2p 前台登录业务处理
	 * @throws ParseException 
	 * @throws NumberFormatException 
	 */
	public Map<String, Object> login(String loginName, String password) {
		Map<String, Object> resultMap = new HashMap<String, Object>();
		// 验证登录名，密码是否为空
		Boolean result = false;
		CusTomer cusTomer = null;
		if(StringUtil.isNotEmpty(loginName) && StringUtil.isNotEmpty(password)){
			cusTomer = cusTomerService.selectByLoginName(loginName);
			// 根据登录名查询 手机号码、用户名、邮箱中是否存在，不存在直接返回
			if(null == cusTomer){
				resultMap.put(Constant.LOGIN_ERROR_KEY, Constant.LOGIN_ERROR_1);
			}else{
				
				// 判断用户是否是黑名单用户
				CusTomer temp = cusTomerService.getIsBlacklist(loginName);
				
				if(null!=temp){
					// 黑名单用户，不允许登陆
					resultMap.put(Constant.LOGIN_ERROR_KEY, Constant.LOGIN_ERROR_5);
				}else{
					
					// 判断用户是否被禁用
					temp = cusTomerService.getIsFreeze(loginName);
					if(null!= temp){
						// 已经被禁用用户，不允许登陆
						resultMap.put(Constant.LOGIN_ERROR_KEY, Constant.LOGIN_ERROR_6);
					}else{
						// 验证登录密码是否一致，不一致记录错误时间（只记录第一次错误时间），错误次数。错误次数超过系统参数配置项时，锁定账户
						if(null!= cusTomer.getPassword()){
							// 用户未被锁定，验证密码
							if(!Constant.IS_LOCKED_2.equals(cusTomer.getIsLocked())){
								// 密码验证
								result = validatePwd(password, resultMap, cusTomer);
							}else{
								// 根据当前时间与锁定时间判断，锁定是否超过配置时间
								String configTime = sysParamsService.getParamsByParamKey("LOGIN_LOCKED_TIME").getParamValue();
								String lockedTime = cusTomer.getLockedTime();
								String curTime = DateUtil.format(DateUtil.getToday());
								// 超过锁定时间
								if(EscfDateUtil.dateCompare(curTime, DateUtil.format(EscfDateUtil.addMinute(lockedTime, Integer.parseInt(configTime))))){
									// 清空错误信息
									cusTomerService.clearLoginError(cusTomer);
									// 重新查询
									cusTomer = cusTomerService.selectByLoginName(loginName);
									// 密码验证
									result = validatePwd(password, resultMap, cusTomer);
								}else{
									// 未超过锁定时间
									resultMap.put(Constant.LOGIN_ERROR_KEY,Constant. LOGIN_ERROR_3);
								}
							}
						}
					}
				}
			}
		}
		resultMap.put(Constant.LOGIN_RESULT, result);
		
		// 更新登陆时间
		if(result){
			Map<String,Object> param = new HashMap<String,Object>();
			param.put("userId", cusTomer.getPid());
			param.put("createTime", DateUtil.format(DateUtil.getToday()));
			cusTomerMapper.updateLastLoginTime(param);
		}
		
		return resultMap;
	}
	
	
	/**
	 * 
	 * Description：<br> 
	 * 验证密码 
	 * @author  Yu.Zhang
	 * @date    2015年11月19日
	 * @version v1.0.0
	 * @param loginVO
	 * @param resultMap
	 * @param service
	 * @param cusTomer
	 * @return boolean
	 */
	private boolean validatePwd(String password,  Map<String, Object> resultMap , CusTomer cusTomer) {
		
		boolean result = EncodedUtil.matchPassword(cusTomer.getPassword(),password, cusTomer.getIndex());
		// 密码一致
		if(result){
			// 密码一致，清空错误时间，错误次数 返回成功
			cusTomerService.clearLoginError(cusTomer);
			resultMap.put(Constant.LOGIN_ERROR_KEY, Constant.LOGIN_ERROR_4);
			
			// 将客户信息查询出来设置结果集中
			CustomerDto customerVo = cusTomerDtoService.getCustomerDtoByPid(cusTomer.getPid());
			resultMap.put(Constant.CUS_KEY, customerVo);
			return true;
		}else{
			// 密码错误 记录错误时间（只记录第一次错误时间），错误次数
			if(null == cusTomer.getErrorCount() || 0 == cusTomer.getErrorCount()){
				cusTomer.setErrorTime(DateUtil.format(DateUtil.getToday()));
				cusTomer.setErrorCount(1);
			}else{
				// 存在错误次数，
				// 获取系统参数中的错误时间
				SysParams configTime = sysParamsService.getParamsByParamKey("LOGIN_ERROR_TIME");
				
				String errorTime = cusTomer.getErrorTime();
				String curTime = DateUtil.format(DateUtil.getToday());
				// 判断错误时间是否超过配置时间。 不超过叠加，超过清空后添加
				if(EscfDateUtil.dateCompare(DateUtil.format(EscfDateUtil.addMinute(errorTime, Integer.parseInt(configTime.getParamValue()))), curTime)){
					cusTomer.setErrorTime(DateUtil.format(DateUtil.getToday()));
					cusTomer.setErrorCount(cusTomer.getErrorCount()==null?0:cusTomer.getErrorCount()+1);
				}else{
					cusTomer.setErrorCount(1);
				}
				
				// 获取系统参数中显示验证码次数
				SysParams captchaConfig = sysParamsService.getParamsByParamKey("LOGIN_CAPTCHA_COUNT");
				
				// 是否显示验证码
				if(null == captchaConfig.getParamValue() || "".equals(captchaConfig.getParamValue()) || Integer.parseInt(captchaConfig.getParamValue()) <=cusTomer.getErrorCount()){
					resultMap.put("isViewKaptcha","true");
				}
				
				SysParams configCount = sysParamsService.getParamsByParamKey("LOGIN_ERROR_COUNT");
				// 错误次数等于配置的次数，用户锁定
				if(cusTomer.getErrorCount().toString().equals(configCount.getParamValue())){
					cusTomer.setIsLocked(Constant.IS_LOCKED_2);
					cusTomer.setLockedTime(DateUtil.format(DateUtil.getToday()));
				}
			}
			cusTomerService.updateLoingError(cusTomer);
			resultMap.put(Constant.LOGIN_ERROR_KEY, Constant.LOGIN_ERROR_2);
		}
		return false;
	}

}


