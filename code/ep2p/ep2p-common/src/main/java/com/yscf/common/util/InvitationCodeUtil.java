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
 * 2015年12月4日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.common.util;

/**
 * Description：<br> 
 * 客户邀请码生成工具类
 * @author  Yu.Zhang
 * @date    2015年12月4日
 * @version v1.0.0
 */
public class InvitationCodeUtil {

	/**
	 * 生成状态码
	 * Description：<br> 
	 * @author  JingYu.Dai
	 * @date    2015年12月3日
	 * @version v1.0.0
	 * @return
	 */
	public static String createStatusCode(String code){
		String[] str = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K",
				"L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W",
				"X", "Y", "A" };
		String a = code.substring(0, 1);
		String b = code.substring(1, 2);
		String c = code.substring(2);
		Integer e = new Integer(c);
		if(999==e){
			if(b.equals("z")){
				int aIndex = 0;
				for (int i = 0; i < str.length; i++) {
					if(a.equals(str[i])){
						aIndex = i;
						break;
					}
				}
				a = str[aIndex+1];
				b = "0";
				c = "000";
			}else{
				int bIndex = 0;
				for (int i = 0; i < str.length; i++) {
					if(b.equals(str[i])){
						bIndex = i;
						break;
					}
				}
				b = str[bIndex+1];
				c = "000";
			}
		}else if(e>=100){
			e++;
			c = ""+e;
		}else if(e<100 && e>=10){
			e++;
			c = "0"+e;
		}else{
			e++;
			c = "00" + e;
		}
		
		return a+b+c;
	}
}


