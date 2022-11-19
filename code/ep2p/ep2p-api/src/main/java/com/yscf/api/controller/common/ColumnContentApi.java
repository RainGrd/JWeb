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
 * 2015年12月28日     shiliang.feng		Initial Version.
 *************************************************************************
 */
package com.yscf.api.controller.common;

import java.util.HashMap;
import java.util.List;

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
import com.yscf.api.vo.common.ColumnContentVo;
import com.yscf.api.vo.user.CustomerVo;
import com.yscf.common.util.ConvertObjectUtil;
import com.yscf.core.model.content.ColumnContent;
import com.yscf.core.service.content.impl.ColumnContentServiceImpl;

/**
 * Description：<br>
 * 系统公告 新手指引 广告位 公共控制器
 * 
 * @author shiliang.feng
 * @date 2015年12月28日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/content/columnContentApi")
public class ColumnContentApi extends EscfBaseApi {
	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public ColumnContentApi(ColumnContentServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return ColumnContent.class;
	}

	/**
	 * 栏目内容 服务
	 */
	@Resource(name = "columnContentService")
	private ColumnContentServiceImpl contentServiceImpl;

	/**
	 * Description：<br>
	 * 根据前台标示 查询栏目内容 可以查询 新手指引 系统公告
	 * 
	 * @author shiliang.feng
	 * @date 2015年12月28日
	 * @version v1.0.0
	 * @param webTag
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getContentByTag", method = RequestMethod.POST)
	@ResponseBody
	public String getContentByTag(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
		CustomerVo customer = null;
		if (!StringUtil.isBlank(xtoken) && null != xtoken
				&& !"null".equals(xtoken)) {
			customer = getCustomer(xtoken);
		}
		String userid = null;
		if (null != customer) {
			userid = customer.getPid();
		}
		try {
			ColumnContentVo content = (ColumnContentVo) ApiUtil
					.convertObjectByBody(request, ColumnContentVo.class);
			if (content == null || StringUtil.isBlank(content.getWebTag())) {
				jsonObject.setCode("500");
				jsonObject.setMessage("webTag is null!");
			}
			List<ColumnContent> contentList = contentServiceImpl
					.selectColContentListByWebTag(content.getWebTag(), userid,
							content.getPageIndex() * content.getPageSize(),
							content.getPageSize(), content.getIsHome());

			List<ColumnContentVo> contents = (List<ColumnContentVo>) ConvertObjectUtil
					.batchConvertObject(contentList, ColumnContentVo.class);
			List<ColumnContent> contentCount = contentServiceImpl
					.selectColContentListByWebTag(content.getWebTag(), userid,
							null, null, null);
			map.put("result", contents);
			map.put("pageCount", contentCount != null ? contentCount.size() : 0);
			jsonObject.setResult(map);
			jsonObject.setCode("200");
			jsonObject.setMessage("查询成功");
			jsonObject.setStatus(true);
		} catch (Exception e) {
			jsonObject.setMessage(e.getMessage());
			logger.error(e.getMessage());
			processResultStatus(jsonObject, ApiCode.HTTP_CODE_900,
					e.getMessage(), false);
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}

	/**
	 * Description：<br>
	 * 根据pid查询内容
	 * 
	 * @author shiliang.feng
	 * @date 2015年12月28日
	 * @version v1.0.0
	 * @param key
	 * @return
	 */
	@RequestMapping(value = "/getContentByKey", method = RequestMethod.POST)
	@ResponseBody
	public String getContentByKey(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		try {
			ColumnContent args = (ColumnContent) ApiUtil.convertObjectByBody(
					request, ColumnContent.class);
			ColumnContent columnContent = (ColumnContent) contentServiceImpl
					.selectByPrimaryKey(args.getPid());
			ColumnContentVo content = ConvertObjectUtil.convertObject(
					columnContent, ColumnContentVo.class);
			map.put("result", content);
			jsonObject.setCode("200");
			jsonObject.setMessage("查询成功");
			jsonObject.setResult(map);
			jsonObject.setStatus(true);
		} catch (Exception e) {
			jsonObject.setMessage(e.getMessage());
			if (logger.isDebugEnabled()) {
				logger.error(e.getMessage());
			}
			jsonObject.setCode("900");
			jsonObject.setMessage("操作失败");
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}

	/**
	 * 
	 * Description：<br>
	 * 批量已读（未读）
	 * 
	 * @author shiliang.feng
	 * @date 2016年1月6日
	 * @version v1.0.0
	 * @param pids
	 * @param isReading
	 * @return
	 */
	@RequestMapping(value = "/batchUpdateIsReadingByPids", method = RequestMethod.POST)
	@ResponseBody
	public String batchUpdateIsReadingByPids(HttpServletRequest request,
			HttpServletResponse response) {
		JsonObject jsonObject = new JsonObject();
		HashMap<String, Object> map = new HashMap<String, Object>();
		try {
			ColumnContent args = (ColumnContent) ApiUtil.convertObjectByBody(
					request, ColumnContent.class);
			Boolean result = contentServiceImpl.batchUpdateIsReadingByPids(
					args.getIsReading(), args.getPid());
			map.put("result", result);
			jsonObject.setResult(map);
			jsonObject.setCode("200");
			jsonObject.setMessage("查询成功");
			jsonObject.setStatus(true);
		} catch (Exception e) {
			jsonObject.setCode("500");
			jsonObject.setMessage("修改异常!");
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
}
