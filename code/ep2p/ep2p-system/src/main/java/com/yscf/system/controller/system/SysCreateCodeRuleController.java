/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 用户管理
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月6日     Simon.Hoo		Initial Version.
 *************************************************************************
 */
package com.yscf.system.controller.system;

import java.util.HashMap;
import java.util.Map;

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
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.core.model.system.SysCreateCodeRule;
import com.yscf.core.service.system.impl.SysCreateCodeRuleServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;

/**
 * 
 * @ClassName : SysCreateCodeRuleController
 * @Description : 编号生成规则控制器
 * @Author : Qing.Cai
 * @Date : 2015年9月15日 下午2:40:53
 */
@Controller
@RequestMapping("/sysCreateCodeRuleController")
public class SysCreateCodeRuleController extends EscfBaseController {
	private Logger logger = LoggerFactory.getLogger(SysCreateCodeRuleController.class);

	@Autowired
	public SysCreateCodeRuleController(SysCreateCodeRuleServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return SysCreateCodeRule.class;
	}

	/**
	 * 
	 * @Description : 跳转编号规则的列表页面
	 * @return
	 * @Author : Qing.Cai
	 * @Date : 2015年9月15日 下午4:07:26
	 */
	@RequestMapping("/openCreateCodeRuleIndex")
	public ModelAndView openCreateCodeRuleIndex() {
		return new ModelAndView("/system/createCodeRule_index");
	}

	/**
	 * 
	 * @Description : 查询编号生成规则信息,带分页
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param sysCreateCodeRule
	 *            编号生成规则对象
	 * @return
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2015年9月15日 下午2:39:14
	 */
	@RequestMapping(value = "/queryCreateCodeList")
	@ResponseBody
	public ModelAndView queryUserList(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		SysCreateCodeRuleServiceImpl service = (SysCreateCodeRuleServiceImpl) getService();
		ModelAndView modelView = new ModelAndView();
		try {
			PageInfo info = getPageInfo(request);
			// 查询条件设置
			SysCreateCodeRule sysCreateCodeRule = (SysCreateCodeRule) getEntity(request);
			if (null == sysCreateCodeRule) {
				sysCreateCodeRule = new SysCreateCodeRule();
			}
			PageList<SysCreateCodeRule> list = service.selectAllPage(sysCreateCodeRule, info);
			modelView.addObject("rows", list);
			modelView.addObject("total", list.getPaginator().getTotalCount());
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * @Description : 临时编号生成规则
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param rulePrefix
	 *            前缀
	 * @param ruleType
	 *            编号类型(1:系统管理 2:财务管理 3:统计管理 4:论坛管理 5:内容管理 6:电台管理 7:活动管理 8:业务管理
	 *            9:客户管理 )
	 * @param seqLength
	 *            序号长度(0001 4位 or 00001 5位)
	 * @Author : Qing.Cai
	 * @Date : 2015年9月24日 下午6:00:50
	 */
	@RequestMapping(value = "/generatNoRuleTem")
	@ResponseBody
	public void generatNoRuleTem(HttpServletRequest request, HttpServletResponse response, String rulePrefix, String ruleType, Integer seqLength) {
		SysCreateCodeRuleServiceImpl service = (SysCreateCodeRuleServiceImpl) getService();
		Map<String, Object> tojson = new HashMap<String, Object>();
		try {
			// 业务放到servicce层处理
			String actCode = service.generatNoRuleTem(rulePrefix, ruleType, seqLength);
			tojson.put("message", actCode);
		} catch (FrameworkException e) {
			if (logger.isDebugEnabled()) {
				logger.info("临时编号生成规则新增or编辑失败", e.getMessage());
				e.printStackTrace();
			}
			tojson.put("message", "操作失败！");
		}
		outputJson(tojson, response);
	}

	/**
	 * 
	 * @Description : 临时编号生成规则
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param rulePrefix
	 *            前缀
	 * @param ruleType
	 *            编号类型(1:系统管理 2:财务管理 3:统计管理 4:论坛管理 5:内容管理 6:电台管理 7:活动管理 8:业务管理
	 *            9:客户管理 )
	 * @param seqLength
	 *            序号长度(0001 4位 or 00001 5位)
	 * @Author : Qing.Cai
	 * @Date : 2015年11月3日 下午4:18:01
	 */
	@RequestMapping(value = "/generatNoRuleTemNoDateTime")
	@ResponseBody
	public void generatNoRuleTemNoDateTime(HttpServletRequest request, HttpServletResponse response, String rulePrefix, String ruleType, Integer seqLength) {
		SysCreateCodeRuleServiceImpl service = (SysCreateCodeRuleServiceImpl) getService();
		Map<String, Object> tojson = new HashMap<String, Object>();
		try {
			// 业务放到servicce层处理
			String actCode = service.generatNoRuleTemNoDateTime(rulePrefix, ruleType, seqLength);
			tojson.put("message", actCode);
		} catch (FrameworkException e) {
			if (logger.isDebugEnabled()) {
				logger.info("临时编号生成规则新增or编辑失败", e.getMessage());
				e.printStackTrace();
			}
			tojson.put("message", "操作失败！");
		}
		outputJson(tojson, response);
	}
	
	/**
	 * 
	 * @Description : 临时编号生成规则
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param rulePrefix
	 *            前缀
	 * @param ruleType
	 *            编号类型(1:系统管理 2:财务管理 3:统计管理 4:论坛管理 5:内容管理 6:电台管理 7:活动管理 8:业务管理
	 *            9:客户管理 )
	 * @param seqLength
	 *            序号长度(0001 4位 or 00001 5位)
	 * @Author : Qing.Cai
	 * @Date : 2015年11月3日 下午4:18:01
	 */
	@RequestMapping(value = "/generatLabelNoRuleTem")
	@ResponseBody
	public void generatLabelNoRuleTem(HttpServletRequest request, HttpServletResponse response, String rulePrefix, String ruleType) {
		SysCreateCodeRuleServiceImpl service = (SysCreateCodeRuleServiceImpl) getService();
		Map<String, Object> tojson = new HashMap<String, Object>();
		try {
			// 业务放到servicce层处理
			String actCode = service.generatLabelNoRuleTem(rulePrefix, ruleType);
			tojson.put("message", actCode);
		} catch (FrameworkException e) {
			if (logger.isDebugEnabled()) {
				logger.info("临时编号生成规则新增or编辑失败", e.getMessage());
				e.printStackTrace();
			}
			tojson.put("message", "操作失败！");
		}
		outputJson(tojson, response);
	}

}
