/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * highchart数据转换工具
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年10月28日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Description：<br> 
 * highchart数据转换工具
 * @author  Lin Xu
 * @date    2015年10月28日
 * @version v1.0.0
 */
public class HReportUtil {
	
	/**
	 * Description：<br> 
	 * 公共月
	 * @author  Lin Xu
	 * @date    2015年10月28日
	 * @version v1.0.0
	 * @param contchar
	 * @return
	 */
	public static String[] comMonth() {
		String [] month = new String[12];
		for(int i = 1 ; i <= 12 ; i++){
			if(i<10){
				month[i-1] = "0" + i + "";
			}else{
				month[i-1] = i + "";
			}
		}
		return month;
	};
	
	/**
	 * Description：<br> 
	 * 获取公共月
	 * @author  JingYu.Dai
	 * @date    2015年11月4日
	 * @version v1.0.0
	 * @return
	 */
	public static int[] comMonth2() {
		int [] month = new int[12];
		for(int i = 1 ; i <= 12 ; i++){
			month[i-1] = i ;
		}
		return month;
	};
	
	/**
	 * Description：<br> 
	 * 公共月
	 * @author  Lin Xu
	 * @date    2015年10月28日
	 * @version v1.0.0
	 * @param contchar
	 * @return
	 */
	public static String[] comMonth(String contchar) {
		String [] month = new String[12];
		for(int i = 1 ; i <= 12 ; i++){
			month[i-1] = i + contchar;
		}
		return month;
	};
	
	
	/**
	 * Description：<br> 
	 * 获取指定月数的天数的数组
	 * @author  Lin Xu
	 * @date    2015年10月28日
	 * @version v1.0.0
	 * @param yearm
	 * @return
	 */
	public static String[] comDay(String yearm){
		try {
			SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");  
	        Date myDate = myFormatter.parse(yearm+"-01");
			//获取当前时间  
	        Calendar cal = Calendar.getInstance();  
	        cal.setTime(myDate);
	        //下面可以设置月份，注：月份设置要减1，所以设置1月就是1-1，设置2月就是2-1，如此类推  
	        cal.set(Calendar.YEAR, Integer.parseInt(yearm.substring(0, 4)));
	        cal.set(Calendar.MONTH, Integer.parseInt(yearm.substring(5, 7)) -1);
	        //得到一个月最最后一天日期(31/30/29/28)  
	        int MaxDay=cal.getActualMaximum(Calendar.DAY_OF_MONTH);  
	        String [] reday = new String[MaxDay];
	        for(int i = 1 ; i <= MaxDay ; i++){
	        	if(i< 10){
	        		reday[i-1] = "0" + i;
	        	}else{
	        		reday[i-1] = i + "";
	        	}
	        }
	        return reday;
		} catch (ParseException e) {
			e.printStackTrace();
		}  
        return null;
	}
	
	/**
	 * Description：<br> 
	 * 获取指定月数的天数的数组
	 * @author  JingYu.Dai
	 * @date    2015年11月4日
	 * @version v1.0.0
	 * @param yearm
	 * @return
	 */
	public static int[] comDay2(String yearm){
		try {
			SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");  
	        Date myDate = myFormatter.parse(yearm+"-01");
			//获取当前时间  
	        Calendar cal = Calendar.getInstance();  
	        cal.setTime(myDate);
	        //下面可以设置月份，注：月份设置要减1，所以设置1月就是1-1，设置2月就是2-1，如此类推  
	        cal.set(Calendar.YEAR, Integer.parseInt(yearm.substring(0, 4)));
	        cal.set(Calendar.MONTH, Integer.parseInt(yearm.substring(5, 7)) -1);
	        //得到一个月最最后一天日期(31/30/29/28)  
	        int MaxDay=cal.getActualMaximum(Calendar.DAY_OF_MONTH);  
	        int [] reday = new int[MaxDay];
	        for(int i = 1 ; i <= MaxDay ; i++){
	        	reday[i-1] = i;
	        }
	        return reday;
		} catch (ParseException e) {
			e.printStackTrace();
		}  
        return null;
	}
	
	/**
	 * Description：<br> 
	 * 获取指定月数的天数的数组
	 * @author  Lin Xu
	 * @date    2015年10月28日
	 * @version v1.0.0
	 * @param yearm
	 * @return
	 */
	public static String[] comDay(String yearm,String contchar){
		try {
			SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd");  
	        Date myDate = myFormatter.parse(yearm+"-01");
			//获取当前时间  
	        Calendar cal = Calendar.getInstance();
	        cal.setTime(myDate);
	        //下面可以设置月份，注：月份设置要减1，所以设置1月就是1-1，设置2月就是2-1，如此类推  
	        cal.set(Calendar.YEAR, Integer.parseInt(yearm.substring(0, 4)));
	        cal.set(Calendar.MONTH, Integer.parseInt(yearm.substring(5, 7)) -1);
	        //得到一个月最最后一天日期(31/30/29/28)  
	        int MaxDay=cal.getActualMaximum(Calendar.DAY_OF_MONTH);  
	        String [] reday = new String[MaxDay];
	        for(int i = 1 ; i <= MaxDay ; i++){
	        	reday[i-1] = i + contchar;
	        }
	        return reday;
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	/**
	 * Description：<br> 
	 * 出现小时的数据信息
	 * @author  Lin Xu
	 * @date    2015年10月29日
	 * @version v1.0.0
	 * @param days
	 * @param bool
	 * @return
	 */
	public static String[] comYMDH(boolean bool,String chars){
		String[] ymdh = new String[24];
		for(int i = 0 ; i < 24 ; i++){
			if(bool){
				if(i<10){
					ymdh[i] = "0" + i + chars; 
				}else{
					ymdh[i] = i + chars;
				}
			}else{
				if(i<10){
					ymdh[i] = "0" + i; 
				}else{
					ymdh[i] = "" + i;
				}
			}
		}
		return ymdh;
	}
	
	
	
}


