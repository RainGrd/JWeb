package com.yscf.core.emay.service;

import java.util.Map;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.yscf.core.service.system.ISysDictionaryContentService;

/**
 * 
 * Description：<br> 
 * 短信平台单例接口
 * @author  Yu.Zhang
 * @date    2015年12月14日
 * @version v1.0.0
 */
public class SingletonClient {
	private static Client client=null;
	
	private SingletonClient(){
	}

	public synchronized static Client getClient(){
		if(client==null){
			try {
				// 在服务器启动时，Spring容器初始化时，不能通过以下方法获取Spring 容器，
				WebApplicationContext wac = ContextLoader.getCurrentWebApplicationContext();  
				ISysDictionaryContentService sysDictionaryContentService =  (ISysDictionaryContentService) wac.getBean("sysDictionaryContentService"); 
				
				Map<String,String> map = sysDictionaryContentService.selectByDisctCodeMap("EMAY_KEY");
				// 查询数据字典，获取序列号，key值
				client=new Client(map.get("SOFTWARESERIAL_NO"),map.get("KEY"),map.get("URI"),map.get("CHAR_SET"));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return client;
	}
	
	
}
