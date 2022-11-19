/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 线上充值API
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年12月31日     Jie.Zou		Initial Version.
 *************************************************************************
 */
package com.yscf.api.controller.personalcenter;

import java.math.BigDecimal;
import java.util.HashMap;

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

import com.achievo.framework.vo.JsonObject;
import com.yscf.api.common.ApiCode;
import com.yscf.api.controller.base.EscfBaseApi;
import com.yscf.api.util.ApiUtil;
import com.yscf.api.vo.persionalcenter.RechargeOnlineVo;
import com.yscf.api.vo.user.CustomerVo;
import com.yscf.core.model.financial.BizRechargeOnline;
import com.yscf.core.service.financial.IRechargeOnlineFactory;
import com.yscf.core.service.financial.impl.BizRechargeOnlineServiceImpl;

/**
 * Description：<br> 
 * 线上充值API
 * @author  Jie.Zou
 * @date    2015年12月31日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/rechargeOnlineApi")
public class RechargeOnlineApi extends EscfBaseApi {

	@Resource(name = "bizRechargeOnlineServiceImpl")
	private BizRechargeOnlineServiceImpl bizRechargeOnlineService;
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Autowired
	public RechargeOnlineApi(BizRechargeOnlineServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return BizRechargeOnline.class;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 新增充值记录
	 * @author  Jie.Zou
	 * @date    2016年1月25日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/addRechargeOnline", method = RequestMethod.POST)
	@ResponseBody
	public String addRechargeOnline(
			HttpServletRequest request, HttpServletResponse response){
		String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
		CustomerVo tcustomer = getCustomer(xtoken);
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		try {
			RechargeOnlineVo recharge = (RechargeOnlineVo)ApiUtil.convertObjectByBody(request, RechargeOnlineVo.class);
			if(null == recharge){
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"查询失败，参数对象为NULL",false);
				return ApiUtil.getEncryptStr(jsonObject);
			}else{
				recharge.setCustomerId(tcustomer.getPid());
			}
			if(!StringUtils.hasText(recharge.getCustomerId())){
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"充值用户为空",false);
			}else if(recharge.getAmount().compareTo(BigDecimal.ZERO)<=0){
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"充值金额输入错误",false);
			}else if(!StringUtils.hasText(recharge.getPayconfigId())){
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"充值支付平台为空",false);
			}else if(!StringUtils.hasText(recharge.getBankCard())){
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"银行卡卡号为空",false);
			}else{
				BizRechargeOnline rechargeOnline = bizRechargeOnlineService.addRechargeOnline(recharge.getCustomerId(), recharge.getAmount(), recharge.getPayconfigId(),recharge.getBankCard());
				if(null==rechargeOnline){
					map.put("addRechargeResult", "false");
					processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"新增充值记录失败",false);
				}else{
					String noOrder = IRechargeOnlineFactory.getPlatform(rechargeOnline.getPayConfigId()).wrapOrderId(rechargeOnline, rechargeOnline.getPayConfigId());
					map.put("addRechargeResult","true");
					map.put("noOrder",noOrder);
					processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"新增充值记录成功",true);
				}
				jsonObject.setResult(map);
			}
		} catch (Exception e) {
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
}


