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
 * 2015年10月22日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.system.controller.business;

import java.util.Date;
import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.yscf.core.model.business.BizRepaymentPlan;
import com.yscf.core.service.business.impl.BizRepaymentPlanServiceImpl;
import com.yscf.system.constort.PageMessageConstants;
import com.yscf.system.controller.base.EscfBaseController;

/**
 * Description：<br> 
 * TODO
 * @author  Lin Xu
 * @date    2015年10月22日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/bizRepaymentPlanController")
public class BizRepaymentPlanController extends EscfBaseController {
	
	Logger logger = Logger.getLogger(BizRepaymentPlanController.class);

	@Autowired
	public BizRepaymentPlanController(BizRepaymentPlanServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return BizRepaymentPlan.class;
	}

	/**
	 * Description：<br> 
	 * 修改备注信息
	 * @author  Lin Xu
	 * @date    2015年10月22日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @param brp
	 * @return
	 */
	@RequestMapping("/updateBizRemPlan")
	@ResponseBody
	public HashMap<String, Object> updateBizRemPlan(HttpServletRequest request, 
			HttpServletResponse response,BizRepaymentPlan brp){
		HashMap<String, Object> remap = new HashMap<String, Object>();
		BizRepaymentPlanServiceImpl brps = (BizRepaymentPlanServiceImpl) getService();
		try{
			if(null != brp){
				brp.setLastUpdateUser(getContextUser().getUserId());
				brp.setLastUpdateTime(new Date());
				brps.updateByPrimaryKeySelective(brp);
				processSuccess(remap, PageMessageConstants.COMMON_MESSAGE_SUCCESS);
			}else{
				processFail(remap, PageMessageConstants.COMMON_MESSAGE_FAIL);
			}
		}catch(Exception e){
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
			processError(remap,e.getMessage());
		}
		return remap;
	}
	
	
}


