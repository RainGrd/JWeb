/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 下表中列出了一些URL特殊符号及编码 十六进制值 
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年10月24日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.common.util;

/**
 * Description：<br> 
 * 下表中列出了一些URL特殊符号及编码 十六进制值 
 * @author  Lin Xu
 * @date    2015年10月24日
 * @version v1.0.0
 */
public interface SpecialChartConstant {
	
	//1.+ URL 中+号表示空格 %2B 
	public final String CHART_URL = "%2B";
	//2.空格 URL中的空格可以用+号或者编码 %20
	public final String CHART_NULL = "%20";
	//3./ 分隔目录和子目录 %2F 
	public final String CHART_SLANT = "%2F";
	//4.? 分隔实际的 URL 和参数 %3F 
	public final String CHART_QUESTION = "%3F";
	//5.% 指定特殊字符 %25 
	public final String CHART_BAIFH = "%25";
	//6.# 表示书签 %23 
	public final String CHART_JINH =" %23";
	//7.& URL 中指定的参数间的分隔符 %26 
	public final String CHART_AND = "%26";
	//8.= URL 中指定参数的值 %3D
	public final String CHART_POINT ="%3D";
	//9.+ URL 中指定参数是%2C
	public final String CHART_ADD = "%2C";
	
}


