/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 用于前台或者API以及微信接口的页面PageInfo对象
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2016年1月26日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.common.util;

import org.apache.commons.lang.StringUtils;
import com.achievo.framework.mybatis.paginator.domain.PageInfo;

/**
 * Description：<br> 
 * 用于前台或者API以及微信接口的页面PageInfo对象
 * @author  Lin Xu
 * @date    2016年1月26日
 * @version v1.0.0
 */
public class PageInfoUtil {

	/**
	 * Description：<br> 
	 * 获取初始化分页对象
	 * @author  Lin Xu
	 * @date    2016年1月26日
	 * @version v1.0.0
	 * @param pagesize 每页条数
	 * @param page 当前页
	 * @return
	 */
	public static PageInfo getIncePageInfo(Integer pagesize,Integer page){
		PageInfo pageinfo = new PageInfo();
		pageinfo.setLimit(StringUtils.isEmpty(pagesize+"") ? 10 : pagesize);
		pageinfo.setPage(StringUtils.isEmpty(page + "") ? 0 : page);
		return pageinfo;
	}
	
}


