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
package com.yscf.system.controller.system;

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

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.util.DateUtil;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.core.model.system.SysDictionaryContent;
import com.yscf.core.service.system.impl.SysDictionaryContentServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;

/**
 * Description：<br>
 * 数据字典内容
 * 
 * @author Yu.Zhang
 * @date 2015年9月10日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/sysDistionaryContentController")
public class SysDistionaryContentController extends EscfBaseController {

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
	 * Description：<br>
	 * 跳转到数据字典内容新增页面
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toAdd")
	@ResponseBody
	public ModelAndView toAdd(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("/system/dictionary/distionary_content_add");
		modelView.addObject("dictId", request.getParameter("dictId").trim());
		modelView.addObject("isUpdate", request.getParameter("isUpdate"));
		modelView.addObject("pid", request.getParameter("pid"));
		return modelView;
	}

	@RequestMapping(value = "/save")
	@ResponseBody
	@Override
	public ModelAndView save(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			SysDictionaryContentServiceImpl service = (SysDictionaryContentServiceImpl) getService();
			SysDictionaryContent sysDictionaryContent = (SysDictionaryContent) getEntity(request);
			// 不为空修改，为空新增
			if (null != sysDictionaryContent && null != sysDictionaryContent.getPid() && !"".equals(sysDictionaryContent.getPid().trim())) {
				sysDictionaryContent.setLastUpdateTime(DateUtil.getToday());
				sysDictionaryContent.setLastUpdateUser("1");
				service.updateByPrimaryKeySelective(sysDictionaryContent);
			} else {
				sysDictionaryContent.setCreateTime(DateUtil.getToday());
				sysDictionaryContent.setCreateUser("1");
				service.insert(sysDictionaryContent);
			}
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (FrameworkException e) {
			if (logger.isDebugEnabled()) {
				logger.info("数据字典内容新增or修改失败", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 跳转到数据字典内容页面
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/toList")
	@ResponseBody
	public ModelAndView toList(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView("/system/dictionary/distionary_content_list");
		modelView.addObject("dictId", request.getParameter("dictId").trim());
		modelView.addObject("isUpdate", request.getParameter("isUpdate"));
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 数据字典内容查询
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectAllPage")
	@ResponseBody
	public ModelAndView selectAllPage(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {

		ModelAndView modelView = new ModelAndView();
		try {
			PageInfo info = getPageInfo(request);
			SysDictionaryContent sysDictionaryContent = (SysDictionaryContent) getEntity(request);
			if (null == sysDictionaryContent) {
				sysDictionaryContent = new SysDictionaryContent();
				sysDictionaryContent.setDictId(request.getParameter("dictId"));
			}
			SysDictionaryContentServiceImpl service = (SysDictionaryContentServiceImpl) getService();
			PageList<SysDictionaryContent> pageList = service.selectAllPage(sysDictionaryContent, info);
			modelView.addObject("rows", pageList);
			modelView.addObject("total", pageList.getPaginator().getTotalCount());
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("数据字典内容查询失败", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 数据字典批量修改状态
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/udpateStatus")
	@ResponseBody
	public ModelAndView udpateStatus(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {

		ModelAndView modelView = new ModelAndView();
		try {
			SysDictionaryContent sysDictionaryContent = new SysDictionaryContent();
			sysDictionaryContent.setPid(request.getParameter("pid"));
			sysDictionaryContent.setStatus(request.getParameter("status"));
			SysDictionaryContentServiceImpl service = (SysDictionaryContentServiceImpl) getService();
			service.updateStatusBatch(sysDictionaryContent);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("数据字典内容启用or禁用失败", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 根据PID获取数据
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/getByPid")
	@ResponseBody
	public ModelAndView getByPid(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {

		ModelAndView modelView = new ModelAndView();
		try {
			SysDictionaryContent sysDictionaryContent = (SysDictionaryContent) getEntity(request);
			sysDictionaryContent = (SysDictionaryContent) getService().selectByPrimaryKey(sysDictionaryContent.getPid());
			modelView.addObject("result", sysDictionaryContent);
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("根据PID获取数据字典内容失败", e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * Description：<br>
	 * 数据字典内容编码验证
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月8日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/validateContentCode")
	@ResponseBody
	public ModelAndView validateContentCode(HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {

		ModelAndView modelView = new ModelAndView();
		try {
			SysDictionaryContent sysDictionaryContent = (SysDictionaryContent) getEntity(request);
			SysDictionaryContentServiceImpl service = (SysDictionaryContentServiceImpl) getService();
			if (!service.validateCode(sysDictionaryContent)) {
				modelView.addObject("result", "fail");
			} else {
				modelView.addObject("result", "suc");
			}
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
	 * Description：<br>
	 * 根据数据字典code 查询内容
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月18日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectByDisctCode")
	@ResponseBody
	public ModelAndView selectByDisctCode(String dictCode, HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			List<SysDictionaryContent> list = new ArrayList<SysDictionaryContent>();
			SysDictionaryContentServiceImpl service = (SysDictionaryContentServiceImpl) getService();
			SysDictionaryContent content = new SysDictionaryContent();
			content.setDictContCode("");
			content.setPid("-1");
			content.setDictContName("-- 请选择 --");
			list.add(content);
			list.addAll(service.selectByDisctCode(dictCode));
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

	/**
	 * 
	 * Description：<br>
	 * 根据数据字典code 查询内容，没有请选择
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月18日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectByDisctCodeNoPlease")
	@ResponseBody
	public ModelAndView selectByDisctCodeNoPlease(String dictCode, HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			List<SysDictionaryContent> list = new ArrayList<SysDictionaryContent>();
			SysDictionaryContentServiceImpl service = (SysDictionaryContentServiceImpl) getService();
			list = service.selectByDisctCode(dictCode);
			MessageBuilder.processSuccess(modelView, list, HttpMessage.SUCCESS_MSG, request);
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
	 * Description：<br>
	 * 根据数据字典code 查询内容
	 * 
	 * @author Yu.Zhang
	 * @date 2015年9月18日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectByDisctCodeToDataList")
	@ResponseBody
	public ModelAndView selectByDisctCodeToDataList(String dictCode, HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		ModelAndView modelView = new ModelAndView();
		try {
			List<SysDictionaryContent> list = new ArrayList<SysDictionaryContent>();
			SysDictionaryContentServiceImpl service = (SysDictionaryContentServiceImpl) getService();
			list.addAll(service.selectByDisctCode(dictCode));
			modelView.addObject("rows", list);
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
	 * @Date : 2015年12月10日 下午3:35:02
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

}
