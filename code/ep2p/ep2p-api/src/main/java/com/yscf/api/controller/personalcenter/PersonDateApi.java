/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 个人信息页面及头像信息
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2016年1月18日     Lin Xu		Initial Version.
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.achievo.framework.util.StringUtil;
import com.achievo.framework.vo.JsonObject;
import com.yscf.api.common.ApiCode;
import com.yscf.api.controller.base.EscfBaseApi;
import com.yscf.api.util.ApiUtil;
import com.yscf.api.vo.user.AccountInfoVo;
import com.yscf.core.model.financial.CustomerAccount;
import com.yscf.core.service.financial.impl.CustomerAccountServiceImpl;
import com.yscf.core.service.userinfo.impl.UserCenterServiceImpl;

/**
 * Description：<br> 
 * 个人信息页面信息
 * @author  Lin Xu
 * @date    2016年1月18日
 * @version v1.0.0
 */
@RequestMapping("/personalCenter/personDateApi")
@Controller
public class PersonDateApi extends EscfBaseApi {
	
	Logger logger = LoggerFactory.getLogger(AutoTenderApi.class);
	
	
	@Autowired
	public PersonDateApi(UserCenterServiceImpl service) {
		super(service);
	}

	//用户中心数据信息
	@Resource(name="userCenterServiceImpl")
	private UserCenterServiceImpl ucservice;
	
	//客户账户信息数据
	@Resource(name="customerAccountService")
	private CustomerAccountServiceImpl custmoeAccountService;
	
	
	/**
	 * Description：<br> 
	 * 返回资产统计信息数据
	 * @author  Lin Xu
	 * @date    2016年1月21日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/getUserProperty",method=RequestMethod.POST)
	@ResponseBody
	public String getUserProperty(HttpServletRequest request, HttpServletResponse response){
		JsonObject jsonobject = new JsonObject();
		HashMap<String, Object> remap = new HashMap<String, Object>();
		try {
			String token = ApiUtil.getHeadXtokenDecryptStr(request);
			if(StringUtil.isNotEmpty(token)){
				String userId = token;//getCustomerId(token);
				if(StringUtil.isNotEmpty(userId)){
					//个人中心我的账户对象
					AccountInfoVo aivo = new AccountInfoVo();
					//获取账户信息
					CustomerAccount customerAccount = custmoeAccountService.getByCusterId(userId);
					if(null != customerAccount){
						aivo.setAvailableBalance(customerAccount.getAvailableAmount());
						aivo.setFreezingAmount(customerAccount.getFreezeAmount());
					}
					//获取待收金额
					HashMap<String, BigDecimal> durInMoney = ucservice.getUserDueInMoney(userId);
					if(null != durInMoney){
						BigDecimal dueinamount = durInMoney.get("tatolcapital") == null ? new BigDecimal(0) : durInMoney.get("tatolcapital");
						aivo.setDueinAmount(dueinamount);
						BigDecimal dueininterest = durInMoney.get("tatolinterest") == null ? new BigDecimal(0) : durInMoney.get("tatolinterest");
						aivo.setDueinInterest(dueininterest);
					}
					//获取利息信息   投资利息   加息利息   投资奖励   红包收益  推荐奖励
					aivo.setInvestInterest(ucservice.getUserInvestInterest(userId));
					aivo.setRateInterest(ucservice.getUserRateInterest(userId));
					aivo.setInvestmentIncentive(ucservice.getUserInIncentive(userId));
					aivo.setRedEnvelope(ucservice.getUserRedEnvelope(userId));
					aivo.setRecommendedAwards(ucservice.getUserRecommendedAwards(userId));
					//返回移动端信息
					remap.put("accountinfovo", aivo);
					jsonobject.setResult(remap);
					processResultStatus(jsonobject,ApiCode.HTTP_CODE_200,"统计资产信息成功",true);
				}else{
					processResultStatus(jsonobject,ApiCode.HTTP_CODE_900,"当前用户信息已过期或不存在",false);
				}
			}else{
				processResultStatus(jsonobject,ApiCode.HTTP_CODE_900,"获取的头部校验信息错误",false);
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("获取资产统计信息异常："+e.getMessage());
			}
			processResultStatus(jsonobject,ApiCode.HTTP_CODE_900,"获取资产统计，服务器异常",false);
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonobject);
		
	}

	

}


