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
 * 2015年12月24日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.task.impl;

import java.util.Map;

import com.yscf.common.util.EscfApplicationContext;
import com.yscf.core.model.task.ScheduleJob;
import com.yscf.core.service.system.ISysDictionaryContentService;
import com.yscf.core.service.task.IScheduleJobTask;

/**
 * Description：<br> 
 * TODO
 * @author  Yu.Zhang
 * @date    2015年12月24日
 * @version v1.0.0
 */
public class ScheduleJobTaskTest implements IScheduleJobTask {
	
	@Override
	public void execute(ScheduleJob scheduleJob) {
		System.out.println("-------------------------"+scheduleJob.getJobName());
		
		ISysDictionaryContentService sysDictionaryContentService =  (ISysDictionaryContentService) EscfApplicationContext.getBean("sysDictionaryContentService"); 
		
		Map<String,String> map = sysDictionaryContentService.selectByDisctCodeMap("EMAY_KEY");
		
		System.out.println("-------------------------"+map.get("SOFTWARESERIAL_NO"));
	}

}


