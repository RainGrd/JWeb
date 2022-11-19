/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月10日     JingYu.Dai		Initial Version.
 *************************************************************************
 */
package com.yscf.system.controller.financial;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.security.domain.ContextUser;
import com.achievo.framework.util.DateUtil;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.core.model.financial.BizAccountRecharge;
import com.yscf.core.service.financial.impl.BizAccountRechargeServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;


/**
 * Description：线下充值控制器
 * @author  JingYu.Dai
 * @date    2015年9月25日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/bizAccountRechargeController")
public class BizAccountRechargeController extends EscfBaseController {

	private Logger logger = LoggerFactory.getLogger(BizAccountRechargeController.class);

	@Autowired
	public BizAccountRechargeController(BizAccountRechargeServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return BizAccountRecharge.class;
	}

	/**
	 * Description：打开充值首页
	 * @author  JingYu.Dai
	 * @date    2015年9月28日
	 * @version v1.0.0
	 * @return ModelAndView
	 */
	@RequestMapping("/openOfflineIndex")
	public ModelAndView openRoleIndex() {
		return new ModelAndView("/financial/recharge/index");
	}
	
	/**
	 * Description：打开充值页面
	 * @author  JingYu.Dai
	 * @date    2015年9月28日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return ModelAndView
	 */
	@RequestMapping("/openClientRecharge")
	public ModelAndView openClientRecharge(HttpServletRequest request , HttpServletResponse response){
		request.setAttribute("clientPid",request.getParameter("clientPid"));
		return new ModelAndView("/financial/recharge/client_recharge");
	}
	
	/**
	 * Description：保存充值数据 
	 * @author  JingYu.Dai
	 * @date    2015年9月28日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return ModelAndView
	 * @throws HttpRequestException 
	 */
	@RequestMapping("/addRccountRecharge")
	@ResponseBody
	public ModelAndView addRccountRecharge(BizAccountRecharge accountRecharge,HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView view = new ModelAndView();
		if(null == accountRecharge.getAvailableAmount()){
			MessageBuilder.processSuccess(view, "充值金额不能为空！", HttpMessage.ERROR_CODE, request);
			return view;
		}/*else{
			new BigDecimal(accountRecharge.getAvailableAmount());
			try {
				new BigDecimal(accountRecharge.getAvailableAmount());
			} catch (Exception e) {
				if (logger.isInfoEnabled()) {
					e.printStackTrace();
				}
				processSuccess(view, "充值金额类型不对 ， 请输入有效数字！", HttpMessage.ERROR_CODE, request);
				return view;
			}
		}*/
			
		if(null == accountRecharge.getCustomerId()){
			MessageBuilder.processSuccess(view, "关联的客户出现问题请联系系统管理员！", HttpMessage.ERROR_CODE, request);
			return view;
		}else{
			BizAccountRechargeServiceImpl serviceImpl = (BizAccountRechargeServiceImpl) getService();
			String pid = accountRecharge.getPid();
			ContextUser user = getContextUser();
			if(null == pid){
				accountRecharge.setCreateUser(user.getUserName());
				accountRecharge.setStatus("1");
				accountRecharge.setCreateTime(DateUtil.format(new Date()));
			}
			try {
				serviceImpl.insert(accountRecharge);
				//新增与修改的提示信息
				//addOrUpdateHintMsg(count, pid, view, request);
			} catch (FrameworkException e) {
				if (logger.isInfoEnabled()) {
					e.printStackTrace();
				}
				MessageBuilder.processError(view, e, request);
			}
		}
		return view;
	}

}
