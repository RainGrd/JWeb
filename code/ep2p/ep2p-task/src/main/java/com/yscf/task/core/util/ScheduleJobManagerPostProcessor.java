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
 * 2015年12月21日     Yu.Zhang		Initial Version.
 *************************************************************************
 */
package com.yscf.task.core.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

import com.yscf.task.core.service.QuartzInit;


/**
 * Description：<br> 
 * 服务启动时加载类型
 * @author  Yu.Zhang
 * @date    2015年12月21日
 * @version v1.0.0
 */
public class ScheduleJobManagerPostProcessor implements BeanPostProcessor {

	@Override
	public Object postProcessAfterInitialization(Object obj, String arg1)
			throws BeansException {
		if(obj instanceof QuartzInit) {  
            // 初始化方法，将所有定时任务加载到quarz中
			((QuartzInit)obj).init();
        }  
        return obj;  
	}

	@Override
	public Object postProcessBeforeInitialization(Object obj, String arg1)
			throws BeansException {
		return obj;
	}

}


