/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 用户在线充值Controller	
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年11月26日     Jie.Zou		Initial Version.
 *************************************************************************
 */
package com.yscf.ep2p.controller.financial.recharge;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.util.DateUtil;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.common.Constant.Constant;
import com.yscf.common.util.GetMacAddress;
import com.yscf.core.model.financial.BizAccountRecharge;
import com.yscf.core.model.ptp.base.BeCurrentModel;
import com.yscf.core.service.financial.IBizRechargeOnlineService;
import com.yscf.core.service.financial.IPayConfigService;
import com.yscf.core.service.financial.IRechargeOnlineFactory;
import com.yscf.core.service.financial.impl.BizRechargeOnlineServiceImpl;
import com.yscf.core.service.financial.impl.CustRechargeServiceImpl;
import com.yscf.core.service.user.ICusTomerService;
import com.yscf.core.util.MiscUtil;
import com.yscf.ep2p.constort.PtpConstants;
import com.yscf.ep2p.controller.base.EscfBaseWebController;

/**
 * Description：<br> 
 * 用户在线充值Controller	
 * @author  Jie.Zou
 * @date    2015年11月26日
 * @version v1.0.0
 */
@Controller
@RequestMapping("recharge/userRechargeController")
public class UserRechargeController extends EscfBaseWebController  {

	private Logger logger = LoggerFactory.getLogger(UserRechargeController.class);
	
	@Resource(name = "bizRechargeOnlineServiceImpl")
	private IBizRechargeOnlineService BizRechargeOnlineService;
	
	@Resource(name = "payConfigServiceImpl")
	private IPayConfigService payConfigService;
	
	@Resource
	private ICusTomerService cusTomerService;
	
	@Resource(name = "custRechargeServiceImpl")
	private CustRechargeServiceImpl custRechargeServiceImpl;
	
	@Autowired
	public UserRechargeController(BizRechargeOnlineServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return BeCurrentModel.class;
	}
	
	@RequestMapping("/toUserRecharge")
	public String toUserRecharge(){
		HttpServletRequest req = getRequest();
		//添加选中的菜单
		req.setAttribute(PtpConstants.CHECKMENU, PtpConstants.USERCENTER_MENU_CHARGE);
		req.setAttribute("availableAmount", req.getParameter("recharegeAmount"));
		return "temp.usercenter.recharge";		
	}
	
	@RequestMapping("/toRecharge")
	public ModelAndView toRecharge(){
		return new ModelAndView("/personcenter/fundsflow/recharge/rechargeOnline");
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 判断是否实名认证
	 * @author  Jie.Zou
	 * @date    2016年2月2日
	 * @version v1.0.0
	 * @return
	 */
	@RequestMapping(value = "/checkIsAttestation")
	@ResponseBody
	public ModelAndView checkIsAttestation(HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelView = new ModelAndView();
		try {
			modelView.addObject("isAttestation",cusTomerService.selectIsAttestationById(this.getUserId()));
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 充值操作中，点击“下一步”，调用此方法生成充值的表单数据
	 * @author  Jie.Zou
	 * @date    2015年12月3日
	 * @version v1.0.0
	 * @return
	 */
	@RequestMapping(value = "/confirmRecharge")
	@ResponseBody
	public ModelAndView confirmRecharge(BizAccountRecharge recharge ,HttpServletRequest request, HttpServletResponse response)
			throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("temp.usercenter.rechargedo");
		try {
			if(checkRechargeAmount(recharge.getAvailableAmount())
					&&checkIsAttestation()){
				Map<String, String> formParams = this.getFormParams(recharge.getAvailableAmount(), recharge.getRechargePlatform(),request);
				String rechargeUrl = this.getRechargeURL(recharge.getRechargePlatform());
				modelView.addObject("formParams",formParams);
				modelView.addObject("rechargeUrl",rechargeUrl);
				modelView.addObject("recharegeAmount",MiscUtil.getBigDecimalMoney(recharge.getAvailableAmount()));
			}
			//MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("用户提交充值失败", e.getMessage());
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 获取提交充值数据的充值平台网址
	 * @author  Jie.Zou
	 * @date    2015年12月10日
	 * @version v1.0.0
	 * @param payConfigId
	 * @return
	 */
	public String getRechargeURL(String payConfigId){
		if(payConfigId != null){
			return IRechargeOnlineFactory.getPlatform(payConfigId).getRechargeURL(payConfigId);
		}
		return "";
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 验证充值金额
	 * @author  Jie.Zou
	 * @date    2015年12月10日
	 * @version v1.0.0
	 * @param amount
	 * @return
	 */
	private boolean checkRechargeAmount(BigDecimal amount){
		if(amount == null || amount.compareTo(BigDecimal.ZERO)<=0){
			return false;
		}
		return true;
	}
	
	/**]
	 * 
	 * Description：<br> 
	 * 后台验证是否实名认证
	 * @author  Jie.Zou
	 * @date    2016年2月22日
	 * @version v1.0.0
	 * @return
	 */
	private boolean checkIsAttestation(){
		if(null!=this.getUserId()){
			if("2".equals(cusTomerService.selectIsAttestationById(this.getUserId()))){
				return true;
			}
		}
		return false;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 得到表单数据
	 * @author  Jie.Zou
	 * @date    2015年12月10日
	 * @version v1.0.0
	 * @param amount
	 * @param payConfigId
	 * @param request
	 * @return
	 */
	public Map<String, String> getFormParams(BigDecimal amount,String payConfigId,HttpServletRequest request){
		Map<String, String> ipMap = new HashMap<String, String>();
		ipMap.put("tranIP", GetMacAddress.getIpAddr(request));
		ipMap.put(Constant.COM_BASEPATH, request.getServletContext().getRealPath("/"));
		Map<String, String> formParams = custRechargeServiceImpl.getFormParams(getUserId(), amount, payConfigId, DateUtil.getSystemDate(), ipMap);
		return formParams  ;
	}
	
}


