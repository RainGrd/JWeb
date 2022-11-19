package com.yscf.common.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 类名称：ConvertObjectUtil   
 * 类描述：   对象转对象的工具类，主要通过反射把相同属性的名字进行赋值
 * 创建人：Lin Xu
 * 创建时间：2015年11月15日 下午3:24:53   
 * 修改人：Lin Xu
 * 修改时间：2015年11月15日 下午3:24:53   
 * 修改备注：   
 * @version: 1.0.0
 */
public class ConvertObjectUtil {

	/**
	 * @Title: convertClass  
	 * @Description: 
	 * 通过属性名相同的方法进行赋值转换（对特殊八种类型有特别处理）
	 * @param obj 参数对象
	 * @param cla 转换后对象
	 * @throws NoSuchMethodException
	 * @throws InvocationTargetException
	 * @throws IllegalAccessException
	 * @throws InstantiationException 
	 * @return T 
	 * @throws
	 */
	public static <T> T convertClass(Object obj,Class<T> cla) throws NoSuchMethodException, 
	InvocationTargetException, IllegalAccessException, InstantiationException {  
        Map<String,Object> maps = new HashMap<String,Object>();  
        //返回对象bean
        T  dataBean = null;  
        if(null==obj) {  
            return null;  
        } 
        //得到出入对象想的类对
        Class<?> cls = obj.getClass();  
        dataBean = cla.newInstance();  
        Field[] fields = cls.getDeclaredFields();  
        Field[] beanFields = cla.getDeclaredFields();  
        //取得传入对象的各个属性的值放入map中
        for(Field field:fields){  
            String fieldName = field.getName();  
            //过滤信息UUId
            if(null != fieldName && !"serialVersionUID".equals(fieldName)){
	            String strGet = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());  
	            Method methodGet = cls.getDeclaredMethod(strGet);  
	            Object object = methodGet.invoke(obj);  
	            if(Date.class.equals(field.getType())){
	            	SimpleDateFormat osdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	            	if(null == object){
	            		maps.put(fieldName,null);
	            	}else{
	            		String datev = osdf.format(object==null?"":object);
		            	maps.put(fieldName,datev==null?"":datev); 
	            	}
	            }else{
	            	maps.put(fieldName,object==null?"":object); 
	            }
            }
        }  
        //进行赋值处理
        for(Field field:beanFields){  
            field.setAccessible(true);  
            String fieldName = field.getName();  
            Class<?> fieldType = field.getType();  
            String fieldValue = maps.get(fieldName)==null?null:maps.get(fieldName).toString();  
            if(fieldValue!=null && !"".equals(fieldValue)){  
                try {  
                    if(String.class.equals(fieldType)){  
                        field.set(dataBean, fieldValue);  
                    }else if(byte.class.equals(fieldType)){  
                        field.setByte(dataBean, Byte.parseByte(fieldValue));  
                    }else if(Byte.class.equals(fieldType)){  
                        field.set(dataBean, Byte.valueOf(fieldValue));  
                    }else if(boolean.class.equals(fieldType)){  
                        field.setBoolean(dataBean, Boolean.parseBoolean(fieldValue));  
                    }else if(Boolean.class.equals(fieldType)){  
                        field.set(dataBean, Boolean.valueOf(fieldValue));  
                    }else if(short.class.equals(fieldType)){  
                        field.setShort(dataBean, Short.parseShort(fieldValue));  
                    }else if(Short.class.equals(fieldType)){  
                        field.set(dataBean, Short.valueOf(fieldValue));  
                    }else if(int.class.equals(fieldType)){  
                        field.setInt(dataBean, Integer.parseInt(fieldValue));  
                    }else if(Integer.class.equals(fieldType)){  
                        field.set(dataBean, Integer.valueOf(fieldValue));  
                    }else if(long.class.equals(fieldType)){  
                        field.setLong(dataBean, Long.parseLong(fieldValue));  
                    }else if(Long.class.equals(fieldType)){  
                        field.set(dataBean, Long.valueOf(fieldValue));  
                    }else if(float.class.equals(fieldType)){  
                        field.setFloat(dataBean, Float.parseFloat(fieldValue));  
                    }else if(Float.class.equals(fieldType)){  
                        field.set(dataBean, Float.valueOf(fieldValue));  
                    }else if(double.class.equals(fieldType)){  
                        field.setDouble(dataBean, Double.parseDouble(fieldValue));  
                    }else if(Double.class.equals(fieldType)){  
                        field.set(dataBean, Double.valueOf(fieldValue));  
                    }else if(Date.class.equals(fieldType)){  
                    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                        field.set(dataBean, sdf.parseObject(fieldValue));
                    }else{
                    	field.set(dataBean, maps.get(fieldName)==null?null:maps.get(fieldName));
                    }
                } catch (IllegalArgumentException e) {  
                    e.printStackTrace();  
                } catch (ParseException e) {
					e.printStackTrace();
				}  
            }  
        }  
        return dataBean;  
    } 
	
	/**
	 * @Title: convertObject  
	 * @Description: 
	 * 通过属性名相同的方法进行赋值转换（类型自动转换）
	 * @param obj
	 * @param cla
	 * @throws InstantiationException
	 * @throws IllegalAccessException
	 * @throws NoSuchMethodException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws InvocationTargetException 
	 * @return T 
	 * @throws
	 */
	public static <T> T convertObject(Object obj,Class<T> cla) throws InstantiationException, 
	IllegalAccessException, NoSuchMethodException, SecurityException, IllegalArgumentException,
	InvocationTargetException{
		Map<String,Object> maps = new HashMap<String,Object>();  
        //返回对象bean
        T  dataBean = null;  
        if(null==obj) {  
            return null;  
        } 
        //得到出入对象想的类
        Class<?> cls = obj.getClass();  
        dataBean = cla.newInstance();  
        Field[] fields = cls.getDeclaredFields();  
        Field[] beanFields = cla.getDeclaredFields();  
        //取得传入对象的各个属性的值放入map中
        for(Field field:fields){  
            String fieldName = field.getName();  
            //过滤信息UUId
            if(null != fieldName && !"serialVersionUID".equals(fieldName)){
	            String strGet = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1, fieldName.length());  
	            Method methodGet = cls.getDeclaredMethod(strGet);  
	            Object object = methodGet.invoke(obj);  
	            maps.put(fieldName,object); 
            }
        }  
        //进行赋值处理
        for(Field field:beanFields){  
            field.setAccessible(true);  
            String fieldName = field.getName();  
            Object fieldObj = maps.get(fieldName)==null?null:maps.get(fieldName);  
            if(fieldObj!=null){  
                 try {
					field.set(dataBean, fieldObj);
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
            }  
        }  
        return dataBean;
	}
	
	/**
	 * @Title: batchConvertObject  
	 * @Description: 
	 * 批量进行转换对象方法 
	 * @param olist
	 * @param cal
	 * @return 
	 * @return List<?> 
	 * @throws
	 */
	public static <T> List<?> batchConvertObject(List<?> olist,Class<T> cal){
		List<T> relist = null;
		if(null != olist){
			//判断类是否为空
			relist =  new ArrayList<T>(olist.size());
			for(Object ob : olist){
				try {
					relist.add((T)convertObject(ob, cal));
				} catch (InstantiationException e) {
					e.printStackTrace();
				}catch(IllegalAccessException e ){
					e.printStackTrace();
				}catch(NoSuchMethodException e){
					e.printStackTrace();
				}catch(SecurityException e){
					e.printStackTrace();
				}catch(IllegalArgumentException e){
					e.printStackTrace();
				}catch(InvocationTargetException e){
					e.printStackTrace();
				}
			}
		}		
		return relist;
	}
	
}
