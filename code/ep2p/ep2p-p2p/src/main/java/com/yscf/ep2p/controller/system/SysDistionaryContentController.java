/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 数据字典内容
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月10日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.ep2p.controller.system;

import java.util.ArrayList;
import java.util.List;

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
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.core.model.system.SysDictionaryContent;
import com.yscf.core.service.system.impl.SysDictionaryContentServiceImpl;
import com.yscf.ep2p.controller.base.EscfBaseWebController;

/**
 * 
 * @ClassName : SysDistionaryContentController
 * @Description : 数据字典前台Controller
 * @Author : Qing.Cai
 * @Date : 2015年12月10日 下午3:49:47
 */
@Controller
@RequestMapping("/personcenter/sysDistionaryContentController")
public class SysDistionaryContentController extends EscfBaseWebController {

	private Logger logger = LoggerFactory.getLogger(SysDistionaryContentController.class);

	@Autowired
	public SysDistionaryContentController(SysDictionaryContentServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return SysDictionaryContent.class;
	}

	/**
	 * 
	 * @Description : 根据数据字典code和数据字典内容code查询数据字典内容名称
	 * @param dictCode
	 *            数据字典code
	 * @param dictContCode
	 *            数据字典内容code
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return 数据字典内容名称
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2015年12月10日 下午4:35:02
	 */
	@RequestMapping(value = "/selectDictionaryContentName")
	@ResponseBody
	public ModelAndView selectDictionaryContentName(String dictCode, String dictContCode, HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			SysDictionaryContentServiceImpl service = (SysDictionaryContentServiceImpl) getService();
			String dictContName = service.selectDictionaryContentName(dictCode, dictContCode);
			modelView.addObject("dictContName", dictContName);
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
	 * @Description : 展示兑换的基础数据
	 * @param dictCode
	 *            兑换类型
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @return 兑换数据列表
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2016年2月24日 上午11:07:52
	 */
	@RequestMapping(value = "/selectByDisctCode")
	@ResponseBody
	public ModelAndView selectByDisctCode(String dictCode, HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		List<SysDictionaryContent> list = null;
		try {
			SysDictionaryContentServiceImpl service = (SysDictionaryContentServiceImpl) getService();
			list = service.selectByDisctCode(dictCode);
			if (null == list) {
				list = new ArrayList<SysDictionaryContent>();
			}
			modelView.addObject("data", list);
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

}
