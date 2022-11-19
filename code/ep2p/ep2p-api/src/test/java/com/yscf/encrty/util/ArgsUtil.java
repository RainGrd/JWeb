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
 * 2016年1月5日     JunJie.Liu		Initial Version.
 *************************************************************************
 */
package com.yscf.encrty.util;

import com.achievo.framework.json.gson.GsonUtil;
import com.achievo.framework.util.SecurityUtil;
import com.google.gson.Gson;

/**
 * Description：<br>
 * TODO
 * 
 * @author JunJie.Liu
 * @date 2016年1月5日
 * @version v1.0.0
 */
public class ArgsUtil {
	
	public static String getEncryStr(Object obj) {
		Gson gson = GsonUtil.create();
		String json = gson.toJson(obj);
		System.out.println("【参数】=====>" + json);
		String m = SecurityUtil.encrypt(ComContents.ENKEY, json);
		System.out.println("【密文】====>"+m);
		return m;
	}
	
	
	// 测试
	public static void main(String[] args) {
		User user = new User();
		user.setId("1");
		user.setAge("20");
		user.setName("小杰");
		
		String s = ArgsUtil.getEncryStr(user);
		
		// 输出密文
		User u = (User) convertObjectByEncryptStr(s,User.class);
		System.out.println("========解析成明文并转成对象后===========");
		System.out.println("id:"+u.getId());
		System.out.println("age:"+u.getAge());
		System.out.println("name:"+u.getName());
	}
	
	
	/**
	 * 
	 * Description：<br> 
	 * 将密文解析后，转成对象
	 * @author  JunJie.Liu
	 * @date    2016年1月5日
	 * @version v1.0.0
	 * @param encryptStr
	 * @param clazz
	 * @return
	 */
	public static Object convertObjectByEncryptStr(String encryptStr,Class<?> clazz){
		Gson gson = GsonUtil.create();
		return gson.fromJson(SecurityUtil.decrypt(ComContents.ENKEY,encryptStr), clazz);
	}
}


class User {

	private String id;
	private String name;
	private String age;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = age;
	}

}
