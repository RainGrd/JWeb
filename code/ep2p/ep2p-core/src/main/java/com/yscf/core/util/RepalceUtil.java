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
 * 2015年11月27日     JunJie.Liu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.util;

/**
 * Description：<br>
 * 替换字符工具类
 * 
 * @author JunJie.Liu
 * @date 2015年11月27日
 * @version v1.0.0
 */
public class RepalceUtil {

	/**
	 * 
	 * Description：<br>
	 * 用*替换用户名，大于四位的字符替换*，最多显示6个*，小于四位替换后两位为*,两位替换后一位，一位不替换
	 * 手机格式的返回130****1111
	 * @author JunJie.Liu
	 * @date 2015年11月27日
	 * @version v1.0.0
	 * @param customerName
	 * @return
	 */
	public static String replaceCustomerNameToStar(String customerName) {
		if (customerName == null || customerName.trim() == "") {
			return customerName;
		}
		String reg = "^[1][358][0-9]{9}$";
		if(customerName.matches(reg) && customerName.length() == 11){
			// 手机号码
			String first = customerName.substring(0,3);
			String last = customerName.substring(7,11);
			
			return first + "****" + last;
		}else{
		
			int len = customerName.length();
	
			if (len > 4) {
				int n = len - 4;
				String cn = customerName.substring(0,4);
				n = n < 7 ? n : 6;
				String s = "";
				for (int i = 0; i < n; i++) {
					s += "*";
				}
				return cn + s;
			} else if (len > 2) {
				String cn = customerName.substring(0, len - 2);
				return cn + "**";
			} else if (len > 1) {
				String cn = customerName.substring(0, len - 1);
				return cn + "*";
			} else {
				return customerName;
			}
		}
	}
}
