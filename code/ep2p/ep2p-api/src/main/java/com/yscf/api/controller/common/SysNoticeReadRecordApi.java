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
 * 2016年1月19日     shiliang.feng		Initial Version.
 *************************************************************************
 */
package com.yscf.api.controller.common;

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

import com.achievo.framework.util.DateUtil;
import com.achievo.framework.vo.JsonObject;
import com.yscf.api.common.ApiCode;
import com.yscf.api.controller.base.EscfBaseApi;
import com.yscf.api.util.ApiUtil;
import com.yscf.api.vo.user.CustomerVo;
import com.yscf.core.model.content.SysNoticeReadRecord;
import com.yscf.core.service.content.impl.SysNoticeReadRecordServiceImpl;

/**
 * Description：<br>
 * 系统消息 阅读记录
 * 
 * @author shiliang.feng
 * @date 2016年1月19日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/content/sysNoticeReadRecordApi")
public class SysNoticeReadRecordApi extends EscfBaseApi {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	public SysNoticeReadRecordApi(SysNoticeReadRecordServiceImpl service) {
		super(service);
	}

	@Override
	public Class<?> getModel() {
		return SysNoticeReadRecord.class;
	}

	/**
	 * 栏目内容 服务
	 */
	@Resource(name = "noticeReadRecordServiceImpl")
	private SysNoticeReadRecordServiceImpl readRecordServiceImpl;

	/**
	 * Description：<br>
	 * 读单条消息
	 * 
	 * @author shiliang.feng
	 * @date 2016年1月19日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/readMessage", method = RequestMethod.POST)
	@ResponseBody
	public String readMessage(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
		CustomerVo tcustomer = getCustomer(xtoken);
		String userid = tcustomer.getPid();
		try {
			SysNoticeReadRecord content = (SysNoticeReadRecord) ApiUtil
					.convertObjectByBody(request, SysNoticeReadRecord.class);
			content.setPid(content.getPK());
			content.setCustomerId(userid);
			content.setCreateUser(userid);
			content.setCreateTime(DateUtil.getToday());
			content.setReadTime(DateUtil.getToday());
			int isRead = readRecordServiceImpl.insertSelective(content);
			map.put("result", isRead > 0 ? true : false);
			jsonObject.setResult(map);
			jsonObject.setCode("200");
			jsonObject.setMessage("成功");
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
	 * 全部已读
	 * 
	 * @author shiliang.feng
	 * @date 2016年1月19日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "/readAllMessage", method = RequestMethod.POST)
	@ResponseBody
	public String readAllMessage(HttpServletRequest request,
			HttpServletResponse response) {
		HashMap<String, Object> map = new HashMap<String, Object>();
		JsonObject jsonObject = new JsonObject();
		String xtoken = ApiUtil.getHeadXtokenDecryptStr(request);
		CustomerVo tcustomer = getCustomer(xtoken);
		if (tcustomer == null) {
			map.put("result", false);
			jsonObject.setResult(map);
			jsonObject.setCode("900");
			jsonObject.setMessage("无用户信息");
			jsonObject.setStatus(false);
			return ApiUtil.getEncryptStr(jsonObject);
		}
		String userid = tcustomer.getPid();
		try {
			Boolean isSuccess = readRecordServiceImpl.readAllMessage(userid);

			map.put("result", isSuccess);
			jsonObject.setResult(map);
			jsonObject.setCode("200");
			jsonObject.setMessage("成功");
			jsonObject.setStatus(true);
		} catch (Exception e) {
			jsonObject.setMessage(e.getMessage());
			logger.error(e.getMessage());
			processResultStatus(jsonObject, ApiCode.HTTP_CODE_900,
					e.getMessage(), false);
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}

}
