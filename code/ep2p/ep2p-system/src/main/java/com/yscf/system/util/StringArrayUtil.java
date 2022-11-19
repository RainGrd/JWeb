/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 数组专函工具类
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年10月24日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.system.util;

import com.achievo.framework.util.StringUtil;

/**
 * Description：<br> 
 * 数组专函工具类
 * @author  Lin Xu
 * @date    2015年10月24日
 * @version v1.0.0
 */
public class StringArrayUtil {
	
	/**
	 * Description：<br> 
	 * 字符串型数组转换成按照特殊字符连接的字符串
	 * @author  Lin Xu
	 * @date    2015年10月24日
	 * @version v1.0.0
	 * @param s
	 * @param spchart
	 * @return
	 */
	public static String strAtoString(String[] sary, String spchart) {
		String restr = "";
		if(null != sary && sary.length > 0 && StringUtil.isNotEmpty(spchart)){
			StringBuffer sb = new StringBuffer();
			for (int i = 0; i < sary.length; i++) {
				sb.append(sary[i].trim() + spchart);
			}
			String newStr = sb.toString();
			restr = newStr.substring(0, newStr.length() - 1);
		}
		return restr;
	}

}


