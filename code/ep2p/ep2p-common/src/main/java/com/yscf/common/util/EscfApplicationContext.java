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
 * 2015年12月24日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.common.util;

import org.springframework.beans.BeansException;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

/**
 * Description：<br> 
 * 获取spring bean工具类
 * @author  Yu.Zhang
 * @date    2015年12月24日
 * @version v1.0.0
 */
public class EscfApplicationContext {
	
	private static WebApplicationContext applicationContext;

	
	  /** 
	  * 获取对象    
	  * @param name 
	  * @return Object 一个以所给名字注册的bean的实例 
	  * @throws BeansException 
	  */  
	  public static Object getBean(String name) throws BeansException {  
		   if(null == applicationContext ){
			   applicationContext = ContextLoader.getCurrentWebApplicationContext(); 
		   } 
	       return applicationContext.getBean(name);  
	  }  
}


