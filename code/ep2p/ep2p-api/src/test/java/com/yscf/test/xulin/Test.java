package com.yscf.test.xulin;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import com.achievo.framework.util.DateUtil;

public class Test {

	public static void main(String[] args) {
//		Date d=new Date();   
//	   SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");   
//	   System.out.println("今天的日期："+df.format(d));   
//	   System.out.println("两天前的日期：" + df.format(new Date(d.getTime() - 2 * 24 * 60 * 60 * 1000)));  
//	   System.out.println("三天后的日期：" + df.format(new Date(d.getTime() + 3 * 24 * 60 * 60 * 1000)));
//	   
//	   GregorianCalendar cal =  (GregorianCalendar) GregorianCalendar.getInstance();
//	   cal.add((GregorianCalendar.YEAR), 1);
////	   System.out.println(cal);
//	      System.out.println("f" + cal.getTime());
		
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		//给定的时间
		Date d;
		try {
			d = format.parse("2016-2-23 15:10:55");
			//当前时间处理
			Calendar cal = Calendar.getInstance();
			cal.set(Calendar.HOUR_OF_DAY, 0);
			cal.set(Calendar.MINUTE, 0);
			cal.set(Calendar.SECOND, 0);
			cal.set(Calendar.MILLISECOND, 0);

			//给定时间处理
			Calendar setCal = Calendar.getInstance();
			setCal.setTime(d);
			setCal.set(Calendar.HOUR_OF_DAY, 0);
			setCal.set(Calendar.MINUTE, 0);
			setCal.set(Calendar.SECOND, 0);
			setCal.set(Calendar.MILLISECOND, 0);

			long dayDiff =(setCal.getTimeInMillis()-cal.getTimeInMillis())/(1000*60*60*24);
			System.out.println(dayDiff);
			// 创建计算时间的对象
			GregorianCalendar gc=new GregorianCalendar();
			gc.setTime(new Date());
			gc.add(5, (int)dayDiff);
			String vipEndTime = DateUtil.format(gc.getTime());
			System.out.println(vipEndTime); 
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		

	   

	}

}
