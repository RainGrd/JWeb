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
 * 2015年11月4日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.common.util;

import java.math.BigDecimal;

/**
 * Description：<br> 
 * 算法工具类
 * @author  Yu.Zhang
 * @date    2015年11月4日
 * @version v1.0.0
 */
public class ArithmeticUtil {
		  
	    /*  
	     * 小数精确的位数  
	     */   
	    private static final int DEF_DIV_SCALE = 10;   
	  
	    /**  
	     * 提供精确的加法运算。  
	     *  
	     * @param v1  
	     *            被加数  
	     * @param v2  
	     *            加数  
	     * @return 两个参数的和  
	     */   
	    public static BigDecimal add(BigDecimal v1, BigDecimal v2) {   
	        return v1.add(v2);   
	    }   
	     
	    /**  
	     * 提供精确的加法运算。  
	     *  
	     * @param v1  
	     *            被加数  
	     * @param v2  
	     *            加数  
	     * @return 两个参数的和  
	     */   
	    public static BigDecimal add(String v1, String v2) {   
	        BigDecimal b1 = new BigDecimal(v1);   
	        BigDecimal b2 = new BigDecimal(v2);   
	        return b1.add(b2);   
	    }   
	     
	    /**  
	     * 提供精确的加法运算。 String  
	     *  
	     * @param v1  
	     *            被加数  
	     * @param v2  
	     *            加数  
	     * @return 两个参数的和  
	     */   
	    public static String strAdd(String v1, String v2,int scale) {   
	        if (scale < 0) {   
	            throw new IllegalArgumentException(   
	                    "The scale must be a positive integer or zero");   
	        }   
	        BigDecimal b1 = new BigDecimal(v1);   
	        BigDecimal b2 = new BigDecimal(v2);   
	        return b1.add(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).toString();   
	    }   
	    /**  
	     * 提供精确的减法运算。  
	     *  
	     * @param v1  
	     *            被减数  
	     * @param v2  
	     *            减数  
	     * @return 两个参数的差  
	     */   
	    public static BigDecimal sub(BigDecimal v1, BigDecimal v2) {   
	        return v1.subtract(v2);   
	    }   
	     
	    /**  
	     * 提供精确的减法运算。  
	     *  
	     * @param v1  
	     *            被减数  
	     * @param v2  
	     *            减数  
	     * @return 两个参数的差  
	     */   
	    public static BigDecimal sub(String v1, String v2) {   
	        BigDecimal b1 = new BigDecimal(v1);   
	        BigDecimal b2 = new BigDecimal(v2);   
	        return b1.subtract(b2);   
	    }   
	     
	    /**  
	     * 对一个数字取精度  
	     * @param v  
	     * @param scale  
	     * @return  
	     */   
	    public static BigDecimal round(String v, int scale) {   
	        if (scale < 0) {   
	            throw new IllegalArgumentException(   
	                    "The scale must be a positive integer or zero");   
	        }   
	        BigDecimal b = new BigDecimal(v);   
	        BigDecimal one = new BigDecimal("1");   
	        return b.divide(one, scale, BigDecimal.ROUND_HALF_UP);   
	    }   
	     
	    /**  
	     * 提供精确的减法运算。String  
	     *  
	     * @param v1  
	     *            被减数  
	     * @param v2  
	     *            减数  
	     * @return 两个参数的差  
	     */   
	    public static String strSub(String v1, String v2,int scale) {   
	        if (scale < 0) {   
	            throw new IllegalArgumentException( "The scale must be a positive integer or zero");   
	        }   
	        BigDecimal b1 = new BigDecimal(v1);   
	        BigDecimal b2 = new BigDecimal(v2);   
	        return b1.subtract(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).toString();   
	    }   
	    /**  
	     * 提供精确的乘法运算。  
	     *  
	     * @param v1  
	     *            被乘数  
	     * @param v2  
	     *            乘数  
	     * @return 两个参数的积  
	     */   
	    public static BigDecimal mul(BigDecimal v1, BigDecimal v2) {   
	        return v1.multiply(v2);   
	    }   
	     
	    /**  
	     * 提供精确的乘法运算。  
	     *  
	     * @param v1  
	     *            被乘数  
	     * @param v2  
	     *            乘数  
	     * @return 两个参数的积  
	     */   
	    public static BigDecimal mul(String v1, String v2) {   
	        BigDecimal b1 = new BigDecimal(v1);   
	        BigDecimal b2 = new BigDecimal(v2);   
	        return b1.multiply(b2);   
	    }   
	     
	    /**  
	     * 提供精确的乘法运算。 保留scale 位小数  
	     *  
	     * @param v1  
	     *            被乘数  
	     * @param v2  
	     *            乘数  
	     * @return 两个参数的积  
	     */   
	    public static BigDecimal mul2(BigDecimal v1, BigDecimal v2,int scale) {   
	        return  round(v1.multiply(v2),scale);   
	    }   
	     
	    /**  
	     * 提供精确的乘法运算。 保留scale 位小数 String  
	     *  
	     * @param v1  
	     *            被乘数  
	     * @param v2  
	     *            乘数  
	     * @return 两个参数的积  
	     */   
	    public static String strMul2(String v1, String v2,int scale) {   
	        if (scale < 0) {   
	            throw new IllegalArgumentException(   
	                    "The scale must be a positive integer or zero");   
	        }   
	        BigDecimal b1 = new BigDecimal(v1);   
	        BigDecimal b2 = new BigDecimal(v2);   
	        return b1.multiply(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).toString();   
	    }   
	    /**  
	     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。  
	     *  
	     * @param v1  
	     *            被除数  
	     * @param v2  
	     *            除数  
	     * @return 两个参数的商  
	     */   
	    public static BigDecimal div(String v1, String v2) {   
	        return div(v1, v2, DEF_DIV_SCALE);   
	    }   
	     
	    /**  
	     * 提供（相对）精确的除法运算，当发生除不尽的情况时，精确到 小数点以后10位，以后的数字四舍五入。  
	     *  
	     * @param v1  
	     *            被除数  
	     * @param v2  
	     *            除数  
	     * @return 两个参数的商  
	     */   
	    public static BigDecimal div(BigDecimal v1, BigDecimal v2) {   
	        return div(v1, v2, DEF_DIV_SCALE);   
	    }   
	  
	    /**  
	     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。  
	     *  
	     * @param v1  
	     *            被除数  
	     * @param v2  
	     *            除数  
	     * @param scale  
	     *            表示需要精确到小数点以后几位。  
	     * @return 两个参数的商  
	     */   
	    public static BigDecimal div(BigDecimal v1, BigDecimal v2, int scale) {   
	        if (scale < 0) {   
	            throw new IllegalArgumentException(   
	                    "The scale must be a positive integer or zero");   
	        }   
	        return v1.divide(v2, scale, BigDecimal.ROUND_HALF_UP);   
	    }   
	     
	    /**  
	     * 提供（相对）精确的除法运算。当发生除不尽的情况时，由scale参数指 定精度，以后的数字四舍五入。  
	     *  
	     * @param v1  
	     *            被除数  
	     * @param v2  
	     *            除数  
	     * @param scale  
	     *            表示需要精确到小数点以后几位。  
	     * @return 两个参数的商  
	     */   
	    public static BigDecimal div(String v1, String v2, int scale) {   
	        if (scale < 0) {   
	            throw new IllegalArgumentException(   
	                    "The scale must be a positive integer or zero");   
	        }   
	        BigDecimal b1 = new BigDecimal(v1);   
	        BigDecimal b2 = new BigDecimal(v2);   
	        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);   
	    }   
	  
	    /**  
	     * 精确的除法运算。除不尽时，由scale参数指 定精度 四舍五入。string  
	     *  
	     * @param v1  
	     *            被除数  
	     * @param v2  
	     *            除数  
	     * @param scale  
	     *            表示需要精确到小数点以后几位。  
	     * @return 两个参数的商  
	     */   
	    public static String strDiv(String v1, String v2, int scale) {   
	        if (scale < 0) {   
	            throw new IllegalArgumentException(   
	                    "The scale must be a positive integer or zero");   
	        }   
	        BigDecimal b1 = new BigDecimal(v1);   
	        BigDecimal b2 = new BigDecimal(v2);   
	        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP).toString();   
	    }   
	     
	    /**  
	     * 精确的除法运算。除不尽时，由scale参数指 定精度 四舍五入。string  
	     *  
	     * @param v1  
	     *            被除数  
	     * @param v2  
	     *            除数  
	     * @param scale  
	     *            表示需要精确到小数点以后几位。  
	     * @return 两个参数的商  
	     */   
	    public static BigDecimal bigDiv(String v1, String v2, int scale) {   
	        if (scale < 0) {   
	            throw new IllegalArgumentException(   
	                    "The scale must be a positive integer or zero");   
	        }   
	        BigDecimal b1 = new BigDecimal(v1);   
	        BigDecimal b2 = new BigDecimal(v2);   
	        return b1.divide(b2, scale, BigDecimal.ROUND_HALF_UP);   
	    }   
	    /**  
	     * 取余数  string  
	     * @param v1  
	     * @param v2  
	     * @param scale  
	     * @return  
	     */   
	    public static BigDecimal strRemainder(String v1,String v2, int scale){   
	        if (scale < 0) {   
	            throw new IllegalArgumentException(   
	                    "The scale must be a positive integer or zero");   
	        }   
	        BigDecimal b1 = new BigDecimal(v1);   
	        BigDecimal b2 = new BigDecimal(v2);   
	        return b1.remainder(b2).setScale(scale, BigDecimal.ROUND_HALF_UP);   
	    }   
	    /**  
	     * 取余数  string  
	     * @param v1  
	     * @param v2  
	     * @param scale  
	     * @return  string  
	     */   
	    public static String strRemainder2Str(String v1,String v2, int scale){   
	        if (scale < 0) {   
	            throw new IllegalArgumentException(   
	                    "The scale must be a positive integer or zero");   
	        }   
	        BigDecimal b1 = new BigDecimal(v1);   
	        BigDecimal b2 = new BigDecimal(v2);   
	        return b1.remainder(b2).setScale(scale, BigDecimal.ROUND_HALF_UP).toString();   
	    }   
	     
	    /**  
	     * 比较大小 如果v1 大于v2 则 返回true 否则false  
	     * @param v1  
	     * @param v2  
	     * @return  
	     */   
	    public static boolean strcompareTo(String v1,String v2){   
	        BigDecimal b1 = new BigDecimal(v1);   
	        BigDecimal b2 = new BigDecimal(v2);   
	        int bj = b1.compareTo(b2);   
	        boolean res ;   
	        if(bj>0)   
	            res = true;   
	        else   
	            res = false;   
	        return res;   
	    }   
	     
	    /**  
	     * 比较大小 如果v1 大于等于v2 则 返回true 否则false  
	     * @param v1  
	     * @param v2  
	     * @return  
	     */   
	    public static boolean strcompareTo2(String v1,String v2){   
	        BigDecimal b1 = new BigDecimal(v1);   
	        BigDecimal b2 = new BigDecimal(v2);   
	        int bj = b1.compareTo(b2);   
	        boolean res ;   
	        if(bj>=0)   
	            res = true;   
	        else   
	            res = false;   
	        return res;   
	    }   
	     
	    /**  
	     * 比较大小 如果v1 等于v2 则 返回true 否则false  
	     * @param v1  
	     * @param v2  
	     * @return  
	     */   
	    public static boolean strcompareTo3(String v1,String v2){   
	        BigDecimal b1 = new BigDecimal(v1);   
	        BigDecimal b2 = new BigDecimal(v2);   
	        int bj = b1.compareTo(b2);   
	        boolean res ;   
	        if(bj==0)   
	            res = true;   
	        else   
	            res = false;   
	        return res;   
	    }   
	      
	    /**  
	     * 取余数  BigDecimal  
	     * @param v1  
	     * @param v2  
	     * @param scale  
	     * @return  
	     */   
	    public static BigDecimal bigRemainder(BigDecimal v1,BigDecimal v2, int scale){   
	        if (scale < 0) {   
	            throw new IllegalArgumentException(   
	                    "The scale must be a positive integer or zero");   
	        }   
	        return v1.remainder(v2).setScale(scale, BigDecimal.ROUND_HALF_UP);   
	    }   
	      
	    /**  
	     * 提供精确的小数位四舍五入处理。  
	     *  
	     * @param v  
	     *            需要四舍五入的数字  
	     * @param scale  
	     *            小数点后保留几位  
	     * @return 四舍五入后的结果  
	     */   
	    public static BigDecimal round(BigDecimal v, int scale) {   
	        if (scale < 0) {   
	            throw new IllegalArgumentException(   
	                    "The scale must be a positive integer or zero");   
	        }   
	        BigDecimal one = new BigDecimal("1");   
	        return v.divide(one, scale, BigDecimal.ROUND_HALF_UP);   
	    }   
	     
	    /**  
	     * 提供精确的小数位四舍五入处理。string  
	     *  
	     * @param v  
	     *            需要四舍五入的数字  
	     * @param scale  
	     *            小数点后保留几位  
	     * @return 四舍五入后的结果  
	     */   
	    public static String strRound(String v, int scale) {   
	        if (scale < 0) {   
	            throw new IllegalArgumentException(   
	                    "The scale must be a positive integer or zero");   
	        }   
	        BigDecimal b = new BigDecimal(v);   
	        return b.setScale(scale, BigDecimal.ROUND_HALF_UP).toString();   
	    }   
}


