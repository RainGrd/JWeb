/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 自动投标处理方法类--方法处理
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年12月18日     Lin Xu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.autobidding.impl;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import com.yscf.core.dao.ptp.autobidding.AutoBiddingQueueMapper;
import com.yscf.core.dao.ptp.autobidding.AutoBiddingQueueRemarksMapper;
import com.yscf.core.service.autobidding.IAutoBiddingProcessService;
import com.yscf.core.service.business.impl.BizBorrowDetailServiceImpl;
import com.yscf.core.service.business.impl.BizBorrowServiceImpl;
import com.yscf.core.service.system.impl.SysLogServiceImpl;
import com.yscf.core.thread.AutoBiddingBackThread;


/**
 * Description：<br> 
 * 自动投标处理方法类--方法处理
 * @author  Lin Xu
 * @date    2015年12月18日
 * @version v1.0.0
 */
@Service("abiddingProcessService")
public class AutoBiddingProcessService implements IAutoBiddingProcessService {
	//处理日志对象
	private Logger logger = Logger.getLogger(AutoBiddingProcessService.class);

	//投标服务服务层
	@Resource(name="bizBorrowService")
	private BizBorrowServiceImpl bizBorrowService; 
	
	//查询自动投标队列信息
	@Resource(name="autoBiddingQueueMapper")
	private AutoBiddingQueueMapper autoBiddingQueueMapper;
	
	//自动投标的信息投资结果处理
	@Resource(name="autoBiddingQueueRemarksMapper")
	private AutoBiddingQueueRemarksMapper abqremarkMapper;
	
	//每个人进行具体投资服务类  --目前需要添加
	@Resource(name="bizBorrowDeatailService")
	private BizBorrowDetailServiceImpl  bizBDetailServiceImpl;
	
	
	//日志处理服务类
	@Resource(name="sysLogService")
	private SysLogServiceImpl sysLogServiceImpl;
	
	
	/**
	 * 通过投标的id进行自动投标
	 */
	@Override
	public void updateProcessAutoBidding(String bspid) {
		try {
			AutoBiddingBackThread abdbthread = new AutoBiddingBackThread(bspid,
					bizBorrowService, autoBiddingQueueMapper, bizBDetailServiceImpl, abqremarkMapper,
					sysLogServiceImpl);
			abdbthread.start();
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.debug("失效自动投标中已启动的投标：" + e.getMessage());
				e.printStackTrace();
			}
		}
	}
	
}


