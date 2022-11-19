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
 * 2015年11月2日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.achievo.framework.util.DateUtil;

/**
 * Description：<br>
 * e生财富日期工具类扩展
 * 
 * @author Yu.Zhang
 * @date 2015年11月2日
 * @version v1.0.0
 */
public class EscfDateUtil extends DateUtil {

	/**
	 * 开始时分秒
	 */
	public static final String BEGIN_MINUTE = " 00:00:00";

	/**
	 * 结束时分秒
	 */
	public static final String END_MINUTE = " 23:59:59";

	/**
	 * 日期年月日格式化
	 */
	public static final String FORMAT_YYYY_MM_DD = "yyyy-MM-dd";
	
	
	/**
	 * 日期年月日时分秒格式化
	 */
	public static final String FORMAT_YYYY_MM_DD_H_M_S = "yyyy-MM-dd HH:mm:ss";

	/**
	 * 
	 * @Description : 格式化时间
	 * @param dateTime
	 *            需要格式化的时间
	 * @param type
	 *            类型1是开始时间2是结束时间
	 * @return 格式化后的时间
	 * @Author : Qing.Cai
	 * @Date : 2015年11月2日 上午10:36:43
	 */
	public static String formatterDate(String dateTime, Integer type) {
		if(!StringUtils.hasLength(dateTime)){
			return null;
		}
		// 需要返回的日期格式
		String date = "";
		// 转换成时间格式
		Date d = DateUtil.format(dateTime, FORMAT_YYYY_MM_DD);
		// 判断是什么类型 1 是开始时间 2是结束时间
		if (type == 1) {
			// 格式化时间并加 00：00：00
			date = new StringBuffer().append(DateUtil.format(d, FORMAT_YYYY_MM_DD).trim()).append(BEGIN_MINUTE).toString();
		} else {
			// 格式化时间并加 23：59：59
			date = new StringBuffer().append(DateUtil.format(d, FORMAT_YYYY_MM_DD).trim()).append(END_MINUTE).toString();
		}
		return date;
	}
	
	/**
	 * 
	 * @Description : 格式化时间
	 * @param dateTime
	 *            需要格式化的时间
	 * @param type
	 *            类型1是开始时间2是结束时间
	 * @author  Yu.Zhang
	 * @date    2015年11月2日
	 * @version v1.0.0
	 * @param date
	 * @param type
	 * @return
	 */
	public static String formatterDate(Date date, Integer type) {
		if(null ==date){
			return "";
		}
		String result = null;
		// 判断是什么类型 1 是开始时间 2是结束时间
		if (type == 1) {
			// 格式化时间并加 00：00：00
			result = new StringBuffer().append(DateUtil.formatYmd(date).trim()).append(BEGIN_MINUTE).toString();
		} else {
			// 格式化时间并加 23：59：59
			result = new StringBuffer().append(DateUtil.formatYmd(date).trim()).append(END_MINUTE).toString();
		}
		return result;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 根据 date 日期 添加天数 ，正数为添加，负数为减去
	 * @author  Yu.Zhang
	 * @date    2015年11月2日
	 * @version v1.0.0
	 * @param day
	 * @return
	 */
	public static Date increasedDays(Date date,Integer day){
		Calendar calendar = new GregorianCalendar(); 
		calendar.setTime(date); 
		calendar.add(Calendar.DATE,day);//把日期往后增加6天.整数往后推,负数往前移动 
		return calendar.getTime();   //这个时间就是日期往后推6天的结果 
	}
	
	
	public static Calendar getDateOfLastMonth(Calendar date,int day) {  
	    Calendar lastDate = (Calendar) date.clone();  
	    lastDate.add(Calendar.MONTH, 1);  
	    lastDate.set(Calendar.DAY_OF_MONTH, lastDate.getActualMaximum(Calendar.DAY_OF_MONTH));  
	    int lastDay = lastDate.get(Calendar.DAY_OF_MONTH);
	    if(lastDay < day){
	    	return lastDate;
	    }
	    lastDate.set(Calendar.DAY_OF_MONTH, day);  
	    return lastDate;  
	}  
	
	/**
	 * 
	 * Description：<br> 
	 * 获取传入时间的下一个月日期
	 * @author  Yu.Zhang
	 * @date    2015年11月11日
	 * @version v1.0.0
	 * @param dateStr
	 * @return
	 */
	public static String getDateOfLastMonth(String dateStr,int day) {  
	    SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYY_MM_DD);  
	    try {  
	        Date date = sdf.parse(dateStr);  
	        Calendar c = Calendar.getInstance();  
	        c.setTime(date); 
	        return format(getDateOfLastMonth(c,day).getTime());  
	    } catch (ParseException e) {  
	        throw new IllegalArgumentException("Invalid date format(yyyyMMdd): " + dateStr);  
	    }  
	} 
	
	/**
	 * 
	 * Description：<br> 
	 * 获取传入时间的下一天
	 * @author  Yu.Zhang
	 * @date    2015年11月11日
	 * @version v1.0.0
	 * @param dateStr
	 * @return
	 */
	public static String getDateOfLastDay(String dateStr,int day) {  
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYY_MM_DD);  
		try {  
			Date date = sdf.parse(dateStr);  
			Calendar c = Calendar.getInstance();  
			c.setTime(date);  
			c.add(Calendar.DATE, day);
			return format(c.getTime());  
		} catch (ParseException e) {  
			throw new IllegalArgumentException("Invalid date format(yyyyMMdd): " + dateStr);  
		}  
	} 
	
	
	/**
	 * 
	 * Description：<br> 
	 *  获取传入时间的下一个月日期
	 * @author  Yu.Zhang
	 * @date    2015年11月11日
	 * @version v1.0.0
	 * @param dateStr
	 * @return
	 */
	public static String getDateOfLastMonth(Date date,int day) {  
	    SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYY_MM_DD);  
        Calendar c = Calendar.getInstance();  
        c.setTime(date);  
        return sdf.format(getDateOfLastMonth(c,day).getTime());  
	} 
	
	/**
	 * Description：<br> 
	 * 获取传入时间的下一个月日期第一天 和最后一天
	 * @author  JingYu.Dai
	 * @date    2015年11月11日
	 * @version v1.0.0
	 * @param date 时间
	 * @return Map<String,String>(firstDay：第一天、lastDay：最后一天)
	 */
	public static Map<String,String> getNextMonthFirstAndLastDay(Date date) {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Map<String,String> map = new HashMap<String, String>();
		// 获取前月的第一天
		Calendar c = Calendar.getInstance();// 获取当前日期
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 1);// 设置为1号,当前日期既为本月第一天
		String firstDay = format.format(c.getTime());
		// 获取前月的最后一天
		c.add(Calendar.MONTH, 1);
		c.set(Calendar.DAY_OF_MONTH, 0);// 设置为1号,当前日期既为本月第一天
		String lastDay = format.format(c.getTime());
		map.put("firstDay", firstDay);
		map.put("lastDay", lastDay);
		return map;
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 获取传入时间的所在天
	 * @author  Yu.Zhang
	 * @date    2015年11月11日
	 * @version v1.0.0
	 * @param dateStr
	 * @return
	 */
	public static int getDay(String dateStr) {  
	    SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYY_MM_DD);  
	    try {  
	        Date date = sdf.parse(dateStr);  
	        Calendar c = Calendar.getInstance();  
	        c.setTime(date);  
	        return c.get(Calendar.DATE);  
	    } catch (ParseException e) {  
	        throw new IllegalArgumentException("Invalid date format(yyyyMMdd): " + dateStr);  
	    }  
	} 
	
	/**
	 * 
	 * Description：<br> 
	 * 添加分钟
	 * @author  Yu.Zhang
	 * @date    2015年11月19日
	 * @version v1.0.0
	 * @param date
	 * @param minute
	 * @return
	 */
	public static Date addMinute(Date date,int minute ){
		 Calendar c = Calendar.getInstance();  
	     c.setTime(date);
	     c.add(Calendar.MINUTE, minute);
	     return c.getTime();
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 添加分钟
	 * @author  Yu.Zhang
	 * @date    2015年11月19日
	 * @version v1.0.0
	 * @param date
	 * @param minute
	 * @return
	 */
	public static Date addMinute(String date,int minute ){
		SimpleDateFormat sdf = new SimpleDateFormat(FORMAT_YYYY_MM_DD_H_M_S);  
		 Calendar c = Calendar.getInstance();  
	     try {
			c.setTime(sdf.parse(date));
			c.add(Calendar.MINUTE, minute);
		} catch (ParseException e) {
			e.printStackTrace();
		}
	     return c.getTime();
	}
	
	/**
	 * 
	 * Description：<br> 
	 * 比较日期大小
	 * @author  Yu.Zhang
	 * @date    2015年11月19日
	 * @version v1.0.0
	 * @param date
	 * @param date1
	 * @return
	 * @throws ParseException 
	 */
	public static boolean dateCompare(String date,String date1) {
		try{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			Date dt1 = df.parse(date);
			Date dt2 = df.parse(date1);
	        if (dt1.getTime() > dt2.getTime()) {
	            return true;
	        }
		}catch(Exception e){
        	e.printStackTrace();
        }
        return false;
	} 
	
	/**
	 * 
	 * Description：<br> 
	 * 比较日期大小
	 * @author  Yu.Zhang
	 * @date    2015年11月19日
	 * @version v1.0.0
	 * @param date
	 * @param date1
	 * @return
	 */
	public static boolean dateCompare(Date dt1,Date dt2){
        if (dt1.getTime() > dt2.getTime()) {
            return true;
        }
        return false;
	} 
	
	
	/**  
     * 计算两个日期之间相差的天数  
     * @param smdate 较小的时间 
     * @param bdate  较大的时间 
     * @return 相差天数 
     * @throws ParseException  
     */    
    public static int daysBetween(Date smdate,Date bdate) throws ParseException    
    {    
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        smdate=sdf.parse(sdf.format(smdate));  
        bdate=sdf.parse(sdf.format(bdate));  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(smdate);    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(bdate);    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));           
    }    
	      
	/** 
	*字符串的日期格式的计算 
	*/  
    public static int daysBetween(String smdate,String bdate) throws ParseException{  
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(sdf.parse(smdate));    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(sdf.parse(bdate));    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return Integer.parseInt(String.valueOf(between_days));     
    } 
    
    /**
     * 获取当前月份天数
     * Description：<br> 
     * TODO
     * @author  Yu.Zhang
     * @date    2016年1月27日
     * @version v1.0.0
     * @return
     */
    public static int getDayOfMonth(){
		Calendar aCalendar = Calendar.getInstance(Locale.CHINA);
		int day=aCalendar.getActualMaximum(Calendar.DATE);
		return day;
	}

	
	public static void main(String[] args) throws ParseException {
//		System.out.println(EscfDateUtil.daysBetween("2015-12-22 11:40:20","2015-12-26 12:40:20" ));
		System.out.println(getDayOfMonth());
	}
	
	
	
	
}
