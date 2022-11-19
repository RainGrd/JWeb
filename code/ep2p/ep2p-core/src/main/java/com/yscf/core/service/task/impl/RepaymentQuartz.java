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
 * 2015年12月24日     JunJie.Liu		Initial Version.
 *************************************************************************
 */
package com.yscf.core.service.task.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yscf.common.Constant.Constant;
import com.yscf.common.util.EscfApplicationContext;
import com.yscf.core.model.business.BizRepaymentPlan;
import com.yscf.core.model.task.ScheduleJob;
import com.yscf.core.service.business.IBizReceiptPlanService;
import com.yscf.core.service.business.IBizRepaymentPlanService;
import com.yscf.core.service.task.IScheduleJobTask;

/**
 * Description：<br> 
 * 还款定时器
 * @author  JunJie.Liu
 * @date    2015年12月24日
 * @version v1.0.0
 */
public class RepaymentQuartz implements IScheduleJobTask {
	
	private Logger logger = LoggerFactory.getLogger(RepaymentQuartz.class);
	
	@Override
	public void execute(ScheduleJob scheduleJob) {
		
		logger.info("开始执行还款定时器");
		//总共还款计划个数
		int borrowPlanCount = 0;
		// 成功个数
		int sucCount = 0;
		// 失败个数
		int failCount = 0;
		
		//逾期垫付计划个数
		int borrowPlanCount3 = 0;
		// 逾期垫付成功个数
		int sucCount3 = 0;
		// 逾期垫付失败个数
		int failCount3 = 0;
		
		//逾期还款计划个数
		int borrowPlanCount2 = 0;
		// 逾期还款成功个数
		int sucCount2 = 0;
		// 逾期还款失败个数
		int failCount2 = 0;
		
		//还款中计划个数
		int borrowPlanCount1 = 0;
		// 还款中计划个数
		int sucCount1 = 0;
		// 还款中计划个数
		int failCount1 = 0;
	
		
		IBizRepaymentPlanService service = (IBizRepaymentPlanService)EscfApplicationContext.getBean("bizRepaymentPlanService");
		IBizReceiptPlanService bizReceiptPlanService = (IBizReceiptPlanService)EscfApplicationContext.getBean("bizReceiptPlanService");
		
		String time = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		
		// 1.获取当天（如2016/03/01 10:10:10  取   2016-03-01） 前，并包含2016-03-01的还款中的还款计划集合
		// 2.当还款中的借款人，在逾期还款列表中未还款时，则无需进行还款了。
		// 3.当还款中的借款人，在逾期垫付列表中未还款时，则无需进行还款了。
		// 存入逾期还款，以及逾期垫付的未还款人的Id，在还款中列表，直接过滤
		List<String> notReapymentUsers = new ArrayList<String>();
		logger.info("1.开始获取【逾期垫付】的还款记录");
		// 逾期垫付
		List<BizRepaymentPlan> list3 = service.findRepaymentPlanByStatus(Constant.BIZ_REPLAN_STATUS_3, time);
		logger.info("2.获取【逾期垫付】的还款记录成功，有"+list3.size()+"条");
	

		logger.info("===========【逾期垫付开始还款】===========");
		// 逾期垫付列表，进行还款
		if(list3 != null && list3.size() > 0){
			borrowPlanCount += list3.size();
			borrowPlanCount3 = list3.size();
			for(int i=0;i<list3.size();i++){
				BizRepaymentPlan brp = list3.get(i);
				try{
					// 2.执行还款
					service.executeRepaymentToSystem(brp,"还款定时器垫付成功");
					sucCount++;
					sucCount3++;
					logger.info("【逾期垫付】用户ID【"+brp.getCustomerId()+"】，当前第"+(i+1)+"次还款，还款【成功】");
				}catch(Exception e){
					notReapymentUsers.add(brp.getCustomerId());
					failCount++;
					failCount3++;
					logger.info("【逾期垫付】用户ID【"+brp.getCustomerId()+"】，当前第"+(i+1)+"次还款，还款【失败】，原因："+e.getMessage());
					logger.error(e.getMessage());
				}
			}
		}
		logger.info("3.【逾期垫付】总还款："+borrowPlanCount3+"个，【成功】还款："+sucCount3+"个，【失败】还款："+failCount3+"个");
		
		logger.info("===========【逾期垫付还款完毕】===========");
		
		
		
		// 逾期还款
		logger.info("4.开始获取【逾期还款】的还款记录");
		List<BizRepaymentPlan> list2 = service.findRepaymentPlanByStatus(Constant.BIZ_REPLAN_STATUS_2, time);
		logger.info("5.获取【逾期还款】的还款记录成功，有"+list2.size()+"条");
		//逾期还款列表，进行还款
		logger.info("===========【逾期还款开始还款】===========");
		if(list2 != null && list2.size() > 0){
			borrowPlanCount += list2.size();
			borrowPlanCount2 = list2.size();
			for(int i=0;i<list2.size();i++){
				BizRepaymentPlan brp = list2.get(i);
				try{
					// 2.执行还款
					service.executeRepayment(brp,"还款定时器还款成功");
					sucCount++;
					sucCount2++;
					logger.info("【逾期还款】用户ID【"+brp.getCustomerId()+"】，当前第"+(i+1)+"次还款，还款【成功】");
				}catch(Exception e){
					failCount++;
					failCount2++;
					notReapymentUsers.add(brp.getCustomerId());
					logger.info("【逾期还款】用户ID【"+brp.getCustomerId()+"】，当前第"+(i+1)+"次还款，还款【失败】，原因："+e.getMessage());
					logger.error(e.getMessage());
				}
			}
		}
		logger.info("6.【逾期还款】总还款："+borrowPlanCount2+"个，【成功】还款："+sucCount2+"个，【失败】还款："+failCount2+"个");
		
		logger.info("===========【逾期还款还款完毕】===========");
		
		
		
	
		logger.info("7.开始获取【还款中】的还款记录");
		// 还款中
		List<BizRepaymentPlan> list = service.findRepaymentPlanByStatus(Constant.BIZ_REPLAN_STATUS_1, time);
		logger.info("8.获取【还款中】的还款记录成功，有"+list.size()+"条");
		// 还款中列表，进行还款
		logger.info("===========【还款中开始还款】===========");
		if(list != null && list.size() > 0){
			borrowPlanCount += list.size();
			borrowPlanCount1 = list.size();
			for(int i=0;i<list.size();i++){
				BizRepaymentPlan brp = list.get(i);
				try{
					if(notReapymentUsers.contains(brp.getCustomerId())){
						//如果用户存在逾期/垫付未能还款的记录，则不能进行还款中列表的还款
						failCount++;
						failCount1++;
						logger.info("【还款中】用户ID【"+brp.getCustomerId()+"】，当前第"+(i+1)+"次还款，还款【失败】，原因：逾期垫付/逾期还款中，有未完成还款");
						continue;
					}
					// 2.执行还款
					service.executeRepayment(brp,"还款定时器还款成功");
					sucCount++;
					sucCount1++;
					logger.info("【还款中】用户ID【"+brp.getCustomerId()+"】，当前第"+(i+1)+"次还款，还款【成功】");
				}catch(Exception e){
					failCount++;
					failCount1++;
					logger.info("【还款中】用户ID【"+brp.getCustomerId()+"】，当前第"+(i+1)+"次还款，还款【失败】，原因："+e.getMessage());
					logger.error(e.getMessage());
				}
			}
		}
		
		logger.info("9.【还款中】总还款："+borrowPlanCount1+"个，【成功】还款："+sucCount1+"个，【失败】还款："+failCount1+"个");
		
		logger.info("===========【还款中还款完毕】===========");
		logger.info("执行还款定时器结束");
		logger.info("结果：");
		logger.info("【总共还款】计划："+borrowPlanCount+"个，【成功】还款："+sucCount+"个，【失败】还款："+failCount+"个");
		logger.info("1.【逾期垫付】总还款："+borrowPlanCount3+"个，【成功】还款："+sucCount3+"个，【失败】还款："+failCount3+"个");
		logger.info("2.【逾期还款】总还款："+borrowPlanCount2+"个，【成功】还款："+sucCount2+"个，【失败】还款："+failCount2+"个");
		logger.info("3.【还款中】总还款："+borrowPlanCount1+"个，【成功】还款："+sucCount1+"个，【失败】还款："+failCount1+"个");
		
		//还加息
		try {
			
			bizReceiptPlanService.repayNetHike(time);
		} catch (Exception e) {
			if(logger.isDebugEnabled()){
				logger.error(e.getMessage());
			}
		}
	}
}

