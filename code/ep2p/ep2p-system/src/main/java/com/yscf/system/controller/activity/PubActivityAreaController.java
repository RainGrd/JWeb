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
package com.yscf.system.controller.activity;

import java.util.HashMap;
import java.util.List;
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

import com.achievo.framework.exception.FrameworkException;
import com.achievo.framework.exception.HttpRequestException;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;
import com.achievo.framework.mybatis.paginator.domain.PageList;
import com.achievo.framework.web.controller.MessageBuilder;
import com.achievo.framework.web.message.HttpMessage;
import com.yscf.core.exception.CodeIsExistenceException;
import com.yscf.core.model.activity.PubActivityArea;
import com.yscf.core.model.pub.PubFile;
import com.yscf.core.service.activity.impl.PubActivityAreaServiceImpl;
import com.yscf.core.service.pub.impl.PubFileServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;

/**
 * 
 * @ClassName : PubActivityAreaController
 * @Description : 活动专区控制器
 * @Author : Qing.Cai
 * @Date : 2015年12月16日 上午11:22:44
 */
@Controller
@RequestMapping("/pubActivityAreaController")
public class PubActivityAreaController extends EscfBaseController {
	private Logger logger = LoggerFactory.getLogger(PubActivityAreaController.class);

	/** 活动专区图片保存路径 */
	public static final String FILEUPLOAD_IMAGE_ACTIVITYAREA = "activityArea";

	// 文件操作服务类
	@Resource(name = "pubFileServiceImpl")
	private PubFileServiceImpl pubFileServiceImpl;

	@Autowired
	public PubActivityAreaController(PubActivityAreaServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return PubActivityArea.class;
	}

	/**
	 * 
	 * @Description : 跳转到活动专区管理页面
	 * @return pubActivityArea_index.jsp
	 * @Author : Qing.Cai
	 * @Date : 2015年12月16日 下午2:30:28
	 */
	@RequestMapping("/openPubActivityArea")
	public ModelAndView openPubActivityArea() {
		return new ModelAndView("/activity/pubActivityArea_index");
	}

	/**
	 * 
	 * @Description : 后台_分页查询活动专区列表
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param pubActivityArea
	 *            活动专区对象
	 * @return 活动专区列表
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2015年12月16日 下午2:31:51
	 */
	@RequestMapping(value = "/selectAllPage")
	@ResponseBody
	public ModelAndView selectAllPage(HttpServletRequest request, HttpServletResponse response, PubActivityArea pubActivityArea) throws HttpRequestException {
		PubActivityAreaServiceImpl service = (PubActivityAreaServiceImpl) getService();
		ModelAndView modelView = new ModelAndView();
		try {
			PageInfo info = getPageInfo(request);
			if (null == pubActivityArea) {
				pubActivityArea = new PubActivityArea();
			}
			PageList<PubActivityArea> list = service.selectAllPage(pubActivityArea, info);
			modelView.addObject("rows", list);
			modelView.addObject("total", list.getPaginator().getTotalCount());
			MessageBuilder.processSuccess(modelView, null, HttpMessage.SUCCESS_MSG, request);
		} catch (Exception e) {
			if (logger.isDebugEnabled()) {
				logger.info("后台_分页查询活动专区列表", e.getMessage());
			}
			e.printStackTrace();
			MessageBuilder.processError(modelView, e, request);
		}
		return modelView;
	}

	/**
	 * 
	 * @Description : 编辑是否展示
	 * @param pid
	 *            活动专区ID
	 * @param isShows
	 *            是否展示
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2015年12月17日 上午11:46:50
	 */
	@RequestMapping(value = "/updateIsShows")
	@ResponseBody
	public void updateIsShows(String pid, Integer isShows, HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		PubActivityAreaServiceImpl service = (PubActivityAreaServiceImpl) getService();
		Map<String, Object> tojson = new HashMap<String, Object>();
		try {
			// 业务放到servicce层处理
			PubActivityArea pubActivityArea = new PubActivityArea();
			pubActivityArea.setPid(pid);// 设置ID
			pubActivityArea.setIsShows(isShows);// 设置是否展示
			pubActivityArea.setLastUpdateUser(getUserId());// 设置最后修改人
			service.updateIsShows(pubActivityArea);
			tojson.put("message", HttpMessage.SUCCESS_CODE);
		} catch (FrameworkException e) {
			if (logger.isDebugEnabled()) {
				logger.info("编辑是否展示失败", e.getMessage());
			}
			e.printStackTrace();
			tojson.put("message", "操作失败！");
		}
		outputJson(tojson, response);
	}

	/**
	 * 
	 * @Description : 编辑是否禁用
	 * @param pid
	 *            主键
	 * @param isDisable
	 *            是否禁用 1.启用 2.禁用
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2016年2月26日 上午10:51:28
	 */
	@RequestMapping(value = "/updateIsDisable")
	@ResponseBody
	public void updateIsDisable(String pid, Integer isDisable, HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		PubActivityAreaServiceImpl service = (PubActivityAreaServiceImpl) getService();
		Map<String, Object> tojson = new HashMap<String, Object>();
		try {
			// 业务放到servicce层处理
			PubActivityArea pubActivityArea = new PubActivityArea();
			pubActivityArea.setPid(pid);// 设置ID
			pubActivityArea.setIsDisable(isDisable);// 设置是否禁用
			pubActivityArea.setLastUpdateUser(getUserId());// 设置最后修改人
			service.updateIsDisable(pubActivityArea);
			tojson.put("message", HttpMessage.SUCCESS_CODE);
		} catch (FrameworkException e) {
			if (logger.isDebugEnabled()) {
				logger.info("编辑是否禁用失败", e.getMessage());
			}
			e.printStackTrace();
			tojson.put("message", "操作失败！");
		}
		outputJson(tojson, response);
	}

	/**
	 * 
	 * @Description : 删除
	 * @param pid
	 *            活动专区主键
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2015年12月17日 下午3:06:36
	 */
	@RequestMapping(value = "/deletePubActivityArea")
	@ResponseBody
	public void deletePubActivityArea(String pid, HttpServletRequest request, HttpServletResponse response) throws HttpRequestException {
		PubActivityAreaServiceImpl service = (PubActivityAreaServiceImpl) getService();
		Map<String, Object> tojson = new HashMap<String, Object>();
		try {
			// 业务放到servicce层处理
			PubActivityArea pubActivityArea = new PubActivityArea();
			pubActivityArea.setPid(pid);// 设置ID
			pubActivityArea.setLastUpdateUser(getUserId());// 设置最后修改人
			service.deletePubActivityArea(pubActivityArea);
			tojson.put("message", HttpMessage.SUCCESS_CODE);
		} catch (FrameworkException e) {
			if (logger.isDebugEnabled()) {
				logger.info("删除:", e.getMessage());
			}
			e.printStackTrace();
			tojson.put("message", "操作失败！");
		}
		outputJson(tojson, response);
	}

	/**
	 * 
	 * @Description : 新增活动专区对象(需要跳转到一个制定的HTML页面)
	 * @param request
	 *            HttpServletRequest 对象
	 * @param response
	 *            HttpServletResponse 对象
	 * @param pubActivityArea
	 *            活动专区对象
	 * @throws HttpRequestException
	 * @Author : Qing.Cai
	 * @Date : 2015年12月18日 下午2:50:19
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/save")
	@ResponseBody
	public void save(HttpServletRequest request, HttpServletResponse response, PubActivityArea pubActivityArea) throws HttpRequestException {
		PubActivityAreaServiceImpl service = (PubActivityAreaServiceImpl) getService();
		Map<String, Object> tojson = new HashMap<String, Object>();
		try {
			// 判断当前的URL和数据存的URL是否相同,如果相同就不需要重新上传
			if (!pubActivityArea.getPid().equals("-1")) {
				PubActivityArea paa = (PubActivityArea) service.selectByPrimaryKey(pubActivityArea.getPid());
				if (paa.getActivityImage() == null || !paa.getActivityImage().equals(pubActivityArea.getActivityImage())) {
					// 判断是否传图
					if (null != pubActivityArea.getActivityImage() && !pubActivityArea.getActivityImage().equals("")) {
						// 调用文件上传的方法
						HashMap<String, Object> map = saveUploadFileObject(request, PubActivityAreaController.FILEUPLOAD_IMAGE_ACTIVITYAREA);
						// 获取文件类
						PubFile pubFile = ((List<PubFile>) map.get("resultList")).get(0);
						// 保存文件类
						pubFileServiceImpl.insert(pubFile);
						pubActivityArea.setFileId(pubFile.getPid());// 设置文件ID
						pubActivityArea.setActivityImage(pubFile.getFileUrl());// 设置文件路径
					}
				}
			} else {
				// 判断是否传图
				if (null != pubActivityArea.getActivityImage() && !pubActivityArea.getActivityImage().equals("")) {
					// 调用文件上传的方法
					HashMap<String, Object> map = saveUploadFileObject(request, PubActivityAreaController.FILEUPLOAD_IMAGE_ACTIVITYAREA);
					// 获取文件类
					PubFile pubFile = ((List<PubFile>) map.get("resultList")).get(0);
					// 保存文件类
					pubFileServiceImpl.insert(pubFile);
					pubActivityArea.setFileId(pubFile.getPid());// 设置文件ID
					pubActivityArea.setActivityImage(pubFile.getFileUrl());// 设置文件路径
				}
			}
			pubActivityArea.setCreateUser(getUserId());// 赋值创建人信息
			// 业务放到servicce层处理
			service.pubAreaEditOther(pubActivityArea);
			tojson.put("message", HttpMessage.SUCCESS_CODE);
		} catch (CodeIsExistenceException e){
			if (logger.isDebugEnabled()) {
				logger.info("新增活动专区对象失败：", e.getMessage());
			}
			e.printStackTrace();
			tojson.put("message", "编号已经存在,请重新填写.");
		} catch (FrameworkException e) {
			if (logger.isDebugEnabled()) {
				logger.info("新增活动专区对象失败：", e.getMessage());
			}
			e.printStackTrace();
			tojson.put("message", "操作失败！");
		} 
		outputJson(tojson, response);
	}

}
