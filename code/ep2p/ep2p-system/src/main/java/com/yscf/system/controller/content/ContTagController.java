/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 *  
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年9月18日     fengshiliang		Initial Version.
 *************************************************************************
 */
package com.yscf.system.controller.content;

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
import com.achievo.framework.util.DateUtil;
import com.achievo.framework.util.StringUtil;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.core.model.content.ContTag;
import com.yscf.core.service.content.IContTagService;
import com.yscf.core.service.content.impl.ContTagServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;

/**
 * Description：<br>
 * 栏目所属标签
 * 
 * @author fengshiliang
 * @date 2015年9月18日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/contTagController")
public class ContTagController extends EscfBaseController {

	private Logger logger = LoggerFactory.getLogger(ContTagController.class);

	@Autowired
	public ContTagController(ContTagServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return ContTag.class;
	}

	/**
	 * 
	 * Description：<br>
	 * 得到栏目所属标签
	 * 
	 * @author fengshiliang
	 * @date 2015年9月21日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/selectedTag")
	@ResponseBody
	public ModelAndView selectedTag(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView view = new ModelAndView();
		try {
			IContTagService service = (ContTagServiceImpl) getService();// new
																		// SysDictionaryContentServiceImpl(null);//
			String contentId = request.getParameter("contentId");
			List<ContTag> tagList = service.selectedTag(contentId);
			view.addObject("rows", tagList);
			MessageBuilder.processSuccess(view, null, HttpMessage.SUCCESS_MSG,
					request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(view, e, request);
		}
		return view;
	}

	/**
	 * 
	 * Description：<br>
	 * 保存或者修改 栏目内容关联的标签信息
	 * 
	 * @author fengshiliang
	 * @date 2015年9月21日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 * @throws HttpRequestException
	 */
	@RequestMapping(value = "/saveOrUpdateTag")
	@ResponseBody
	public void saveOrUpdateTag(HttpServletRequest request,
			HttpServletResponse response) throws HttpRequestException {
		ModelAndView view = new ModelAndView();
		try {
			String contentId = request.getParameter("contentId"); // 选择的内容id
			String selectedTagIds = request.getParameter("tagIds");// 选择的ids
			String pid = request.getParameter("pid");
			String[] selectedTagId = selectedTagIds.split(",");
			if (!StringUtil.isBlank(contentId)) {
				IContTagService service = (ContTagServiceImpl) getService();
				service.deleteByColuContId(contentId);
			}
			for (int i = 0; i < selectedTagId.length; i++) {
				ContTag tag = new ContTag();

				tag.setCreateTime(DateUtil.getToday());
				tag.setLastUpdateTime(DateUtil.getToday());
				tag.setCreateUser(getContextUser().getUserName());
				tag.setLastUpdateUser(getContextUser().getUserName());
				tag.setColuContId(contentId);
				tag.setDictContId(selectedTagId[i]);
				if (!StringUtil.isBlank(pid)) {
					tag.setStatus("1");
					tag.setPid(null);
					getService().insert(tag);
				} else {
					tag.setStatus("0");
					tag.setPid(pid);
					getService().updateByPrimaryKey(tag);
				}

			}
			MessageBuilder.processSuccess(view, null, HttpMessage.SUCCESS_MSG,
					request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info(e.getMessage());
				e.printStackTrace();
			}
			MessageBuilder.processError(view, e, request);
		}
	}

}
