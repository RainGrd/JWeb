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
 * 2015年11月17日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.common.util;

import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

/**
 * Description：<br> 
 * 加密工具类
 * @author  Yu.Zhang
 * @date    2015年11月17日
 * @version v1.0.0
 */
public class EncodedUtil {

	/**
	 * 使用提供的加密种子进行加密密码，算法使用ShaPasswordEncoder。
	 * 此为单向加密，无法解密出密码明文。
	 * 
	 * @param rawPassword 密码明文
	 * @param salt 加密种子
	 * @return 密码密文
	 */
	public static String getEncodedPassword(String rawPassword, String salt) {
		ShaPasswordEncoder encoder = getPasswordEncoder();
		return encoder.encodePassword(rawPassword, salt);
	}
	
	/**
	 * Caff中所有的密码加密算法使用ShaPasswordEncoder
	 */
	public static ShaPasswordEncoder getPasswordEncoder() {
		return new ShaPasswordEncoder();
	}
	
	/**
	 * 根据加密因子对比加密后的密码与明文密码是否一致。
	 * 
	 * @param rawPassword 密码明文
	 * @param salt 加密种子
	 * @return 密码密文
	 */
	public static boolean matchPassword(String encPass,String rawPassword, Integer salt) {
		ShaPasswordEncoder encoder = getPasswordEncoder();
		return encoder.isPasswordValid(encPass, rawPassword, salt);
	}
	
	/**
	 * 使用对称性加密字符串，可使用相应算法解密出原字符串
	 * 
	 * @param rawStr 原字符串
	 * @param salt 加密种子
	 * @return
	 */
	public static String getEncodedStr(String rawStr, String salt) {
		EncryptorAES encryptorAES = new EncryptorAES(salt);
		return encryptorAES.encryt(rawStr);
	}
	
	/**
	 * 解密出通过对称性加密的字符串明文
	 * 
	 * @param encodedStr 加密密文
	 * @param salt 加密种子
	 */
	public static String getDecodedStr(String encodedStr, String salt) {
		EncryptorAES encryptorAES = new EncryptorAES(salt);
		return encryptorAES.decrypt(encodedStr);
	}
	
}


