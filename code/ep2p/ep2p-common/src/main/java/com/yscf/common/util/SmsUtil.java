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
 * 2015年12月14日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.common.util;

import java.util.Random;

/**
 * Description：<br> 
 * 短信发送工具类
 * @author  Yu.Zhang
 * @date    2015年12月14日
 * @version v1.0.0
 */
public class SmsUtil {
	
	
	/**
	 * 
	 * Description：<br> 
	 * 获取4位随机数验证码
	 * @author  Yu.Zhang
	 * @date    2015年12月14日
	 * @version v1.0.0
	 * @return
	 */
    public static String RandomNumber(){
        StringBuffer sb = new StringBuffer();
        String str = "0123456789";
        Random r = new Random();
        for(int i=0;i<4;i++){
            int num = r.nextInt(str.length());
            sb.append(str.charAt(num));
            str = str.replace((str.charAt(num)+""), "");
        }
        return sb.toString();
    }
}


