/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 首页信息展示接口
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2016年1月28日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.api.controller.homepage;

import java.util.HashMap;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
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
import com.yscf.core.model.content.adv.ContAdvContent;
import com.yscf.core.service.content.impl.ContAdvContentServiceImpl;
import com.yscf.core.service.homepage.impl.HomePageServiceImpl;

/**
 * Description：<br> 
 * 首页信息展示接口
 * @author  Lin Xu
 * @date    2016年1月28日
 * @version v1.0.0
 */
@RequestMapping("/homepage/homePageApi")
@Controller
public class HomePageApi extends EscfBaseApi {
	//日志处理对象
	private Logger logger = Logger.getLogger(HomePageApi.class);
	
	// 广告服务
	@Resource(name = "contAdvContentServiceImpl")
	private ContAdvContentServiceImpl contAdvContentService;

	
	@Autowired
	public HomePageApi(HomePageServiceImpl service) {
		super(service);
	}
	
	/**
	 * Description：<br> 
	 * 获取广告和banner信息
	 * @author  Lin Xu
	 * @date    2016年1月28日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/getAlimama",method=RequestMethod.POST)
	@ResponseBody
	public String getAlimama(HttpServletRequest request, HttpServletResponse response){
		JsonObject jsonObject = new JsonObject();
		HashMap<String, Object> remap = new HashMap<String, Object>();
		try {
			// 广告位定义的前台标示
			//String webTag = request.getParameter("webTag");
			HashMap<String, Object> inDuModel = (HashMap<String, Object>) ApiUtil.convertObjectByBody(request, HashMap.class);
			String webTag = inDuModel.get("webTag").toString();
			//---------------------广告内容---------------------------
			if(StringUtil.isNotEmpty(webTag)){
				List<ContAdvContent> advContent = contAdvContentService.selectAdvContentByWebTag(webTag);
				remap.put("relist", advContent);
				jsonObject.setResult(remap);
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_200,"查询信息成功",false);
			}else{
				processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,"查询失败，缺少标识符",false);
			}
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("获取广告信息失败："+e.getMessage());
			}
			processResultStatus(jsonObject,ApiCode.HTTP_CODE_900,e.getMessage(),false);
			e.printStackTrace();
		}
		return ApiUtil.getEncryptStr(jsonObject);
	}
	
	

}


