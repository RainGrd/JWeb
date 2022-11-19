/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 公共跳转页面控制层
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年10月23日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.system.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Description：<br> 
 * 公共跳转页面控制层
 * @author  Lin Xu
 * @date    2015年10月23日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/commonController")
public class CommonController{

	
	/**
	 * Description：<br> 
	 * 跳转至数据下载页面
	 * @author  Lin Xu
	 * @date    2015年10月19日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("/toDownloadPage")
	public ModelAndView toDownloadPage(HttpServletRequest request, HttpServletResponse response){
		ModelAndView modelv = new ModelAndView("/common/common_download");
		modelv.addObject("dgid", request.getParameter("dgid"));
		return modelv;	
	}
	
	

}


