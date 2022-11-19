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
 * 2015年9月8日     Simon.Hoo		Initial Version.
 *************************************************************************
 */
package com.yscf.system.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Description：MD5摘要
 * @author  JingYu.Dai
 * @date    2015年9月8日
 * @version v1.0.0
 */
public class MD5 {
	
	/**
	 * Description：对字符串进行MD5进行加密
	 * @author  JingYu.Dai
	 * @date    2015年9月8日
	 * @version v1.0.0
	 * @param str
	 * @return strMD5
	 */
	public static String encryptionMD5(String str){
		return MD5.getHash(str , "MD5");
	}
	
	/**
	 * Description：对字符串进行摘要
	 * @author  JingYu.Dai
	 * @date    2015年9月8日
	 * @version v1.0.0
	 * @param source 需要加密的字符串
	 * @param hashType 加密类型 （MD5 和 SHA）
	 * @return
	 */
	public static String getHash(String source, String hashType) {
	    // 用来将字节转换成 16 进制表示的字符
	    char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f'};

	    try {
	      MessageDigest md = MessageDigest.getInstance(hashType);
	      md.update(source.getBytes()); // 通过使用 update 方法处理数据,使指定的 byte数组更新摘要
	      byte[] encryptStr = md.digest(); // 获得密文完成哈希计算,产生128 位的长整数
	      char str[] = new char[16 * 2]; // 每个字节用 16 进制表示的话，使用两个字符
	      int k = 0; // 表示转换结果中对应的字符位置
	      for (int i = 0; i < 16; i++) { // 从第一个字节开始，对每一个字节,转换成 16 进制字符的转换
	        byte byte0 = encryptStr[i]; // 取第 i 个字节
	        str[k++] = hexDigits[byte0 >>> 4 & 0xf]; // 取字节中高 4 位的数字转换, >>> 为逻辑右移，将符号位一起右移
	        str[k++] = hexDigits[byte0 & 0xf]; // 取字节中低 4 位的数字转换
	      }
	      return new String(str); // 换后的结果转换为字符串
	    } catch (NoSuchAlgorithmException e) {
	      e.printStackTrace();
	    }
	    return null;
	  }
	
}


