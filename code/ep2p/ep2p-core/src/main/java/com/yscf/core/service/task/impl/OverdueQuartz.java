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
import com.yscf.core.service.business.IBizRepaymentPlanService;
import com.yscf.core.service.task.IScheduleJobTask;

/**
 * Description：<br> 
 * 逾期/垫付定时器
 * @author  JunJie.Liu
 * @date    2015年12月24日
 * @version v1.0.0
 */
public class OverdueQuartz implements IScheduleJobTask {
	
	private Logger logger = LoggerFactory.getLogger(OverdueQuartz.class);
	
	@Override
	public void execute(ScheduleJob scheduleJob) {
		
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
		// 逾期垫付更新罚息成功个数
		int faCount = 0;
		// 逾期垫付更新罚息失败个数
		int fasCount = 0;
		
		//逾期还款计划个数
		int borrowPlanCount2 = 0;
		// 逾期还款成功个数
		int sucCount2 = 0;
		// 逾期还款失败个数
		int failCount2 = 0;
		// 逾期垫付垫付成功个数
		int faCount2 = 0;
		// 逾期垫付垫付失败个数
		int fasCount2 = 0;
		
		//还款中计划个数
		int borrowPlanCount1 = 0;
		// 还款中计划个数
		int sucCount1 = 0;
		// 还款中计划个数
		int failCount1 = 0;
		// 还款中垫付成功个数
		int faCount3 = 0;
		// 还款中垫付失败个数
		int fasCount3 = 0;
		
		logger.info("开始执行逾期/垫付定时器");
	
		
		IBizRepaymentPlanService service = (IBizRepaymentPlanService)EscfApplicationContext.getBean("bizRepaymentPlanService");
		
		String time = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		
		// 2.当还款中的借款人，在逾期还款列表中未还款时，则无需进行还款了。
		// 3.当还款中的借款人，在逾期垫付列表中未还款时，则无需进行还款了。
		// 存入逾期还款，以及逾期垫付的未还款人的Id，在还款中列表，直接过滤
		List<String> notReapymentUsers = new ArrayList<String>();
		
		logger.info("开始获取【逾期垫付】的记录");
		// 获取逾期垫付的记录，将其金额还给系统，如果没有钱，更新其罚息
		List<BizRepaymentPlan> overdueList = service.findRepaymentPlanByStatus(Constant.BIZ_REPLAN_STATUS_3, time);
		logger.info("获取【逾期垫付】的记录【成功】，共有"+overdueList.size()+"条");
		logger.info("============【逾期垫付】开始还款】============");
		if(overdueList != null && overdueList.size() > 0){
			borrowPlanCount += overdueList.size();
			borrowPlanCount3 = overdueList.size();
			
			for(int i=0;i<overdueList.size();i++){
				
				BizRepaymentPlan brp = overdueList.get(i);
				try{
					
					service.executeRepaymentToSystem(brp,"定时器逾期/垫付成功");
					sucCount++;
					sucCount3++;
					logger.info("【逾期垫付】用户ID【"+brp.getCustomerId()+"】，当前第"+(i+1)+"次还款【成功】");
				}catch(Exception ae){
					notReapymentUsers.add(brp.getCustomerId());
					failCount++;
					failCount3++;
					logger.info("【逾期垫付】用户ID【"+brp.getCustomerId()+"】，当前第"+(i+1)+"次还款【失败】，原因："+ae.getMessage());
					logger.error(ae.getMessage());
					// 如果没有钱，更新其罚息
					try {
						logger.info("【逾期垫付】用户ID【"+brp.getCustomerId()+"】，开始更新罚息");
						service.updateOverdue(brp,false);
						logger.info("【逾期垫付】用户ID【"+brp.getCustomerId()+"】，更新罚息【成功】");
						faCount++;
					} catch (Exception e) {
						logger.info("【逾期垫付】用户ID【"+brp.getCustomerId()+"】，更新罚息【失败】，原因："+e.getMessage());
						logger.error(e.getMessage());
						fasCount++;
					}
					
				}
			}
		}
		logger.info("【逾期垫付】总还款："+borrowPlanCount3+"个，【成功】还款："+sucCount3+"个，【失败】还款："+failCount3+"个，【更新罚息成功】："+faCount+"个，【更新罚息成功】："+fasCount+"个");
		logger.info("============【逾期垫付】还款结束】============");
		
		
		
		// 获取逾期还款的记录，进行垫付，如果系统备付金没有钱，更新其罚息
		logger.info("开始获取【逾期还款】的记录");
		List<BizRepaymentPlan> overdueRepaymentList = service.findRepaymentPlanByStatus(Constant.BIZ_REPLAN_STATUS_2, time);
		logger.info("获取【逾期还款】的记录【成功】，共有"+overdueRepaymentList.size()+"条");
		
		logger.info("============【逾期还款】开始还款/垫付】============");
		if(overdueRepaymentList != null && overdueRepaymentList.size() > 0){
			borrowPlanCount += overdueRepaymentList.size();
			borrowPlanCount2 = overdueRepaymentList.size();
			for(int i=0;i<overdueRepaymentList.size();i++){
				BizRepaymentPlan brp = overdueRepaymentList.get(i);
				try{
					
					service.executeRepayment(brp,"逾期垫付还款器还款成功");
					sucCount++;
					sucCount2++;
					logger.info("【逾期还款】用户ID【"+brp.getCustomerId()+"】，当前第"+(i+1)+"次还款【成功】");
				}catch(Exception ae){
					notReapymentUsers.add(brp.getCustomerId());
					failCount++;
					failCount2++;
					logger.info("【逾期还款】用户ID【"+brp.getCustomerId()+"】，当前第"+(i+1)+"次还款【失败】，原因："+ae.getMessage());
					logger.error(ae.getMessage());
					try {
						logger.info("【逾期还款】用户ID【"+brp.getCustomerId()+"】，系统开始进行垫付并且更新罚息");
						service.executePayment(brp);
						logger.info("【逾期还款】用户ID【"+brp.getCustomerId()+"】，垫付并且更新罚息【成功】");
						faCount2++;
					} catch (Exception e) {
						// 垫付出现异常
						logger.info("【逾期还款】用户ID【"+brp.getCustomerId()+"】，垫付/更新罚息【失败】，原因："+e.getMessage());
						logger.error(e.getMessage());
						fasCount2++;
					}
				}
			}
		}
		logger.info("【逾期还款】总还款："+borrowPlanCount2+"个，【成功】还款："+sucCount2+"个，【失败】还款："+failCount2+"个，【垫付成功】："+faCount2+"个，【垫付失败】："+fasCount2+"个");
		logger.info("============【逾期还款】还款/垫付结束】============");
		
		
		
		
		// 获取到还款中的记录
		logger.info("开始获取【还款中】的记录");
		List<BizRepaymentPlan> list = service.findRepaymentPlanByStatus(Constant.BIZ_REPLAN_STATUS_1, time);
		logger.info("获取【还款中】的记录【成功】，共有"+list.size()+"条");
		
		logger.info("============【还款中】开始还款】============");
		if(list != null && list.size() > 0){
			borrowPlanCount += list.size();
			borrowPlanCount1 = list.size();
			for(int i=0;i<list.size();i++){
				BizRepaymentPlan brp = list.get(i);
				try{
					if(notReapymentUsers.contains(brp.getCustomerId())){
						//如果用户存在逾期/垫付未能还款的记录，则不能进行还款中列表的还款，但是得更新罚息
						failCount++;
						failCount1++;
						logger.info("【还款中】用户ID【"+brp.getCustomerId()+"】，当前第"+(i+1)+"次还款，还款【失败】，原因：逾期垫付/逾期还款中，有未完成还款");
						service.updateOverdue(brp,true);
						continue;
					}
					service.executeRepayment(brp,"逾期垫付还款器还款成功");
					sucCount++;
					sucCount1++;
					logger.info("【还款中】用户ID【"+brp.getCustomerId()+"】，当前第"+(i+1)+"次还款【成功】");
				}catch(Exception ae){
					// 还款人余额不足，系统对其进行垫付，如果备付金不足，改为逾期，更新罚息
					failCount++;
					failCount1++;
					logger.info("【还款中】用户ID【"+brp.getCustomerId()+"】，当前第"+(i+1)+"次还款【失败】，原因："+ae.getMessage());
					try {
						logger.info("【还款中】用户ID【"+brp.getCustomerId()+"】，系统开始进行垫付并且更新罚息");
						service.executePayment(brp);
						logger.info("【还款中】用户ID【"+brp.getCustomerId()+"】，垫付并且更新罚息 【成功】");
						faCount3++;
					} catch (Exception e) {
						// 垫付失败
						logger.info("【还款中】用户ID【"+brp.getCustomerId()+"】，垫付/更新罚息【失败】，原因："+e.getMessage());
						fasCount3++;
					}
					
				}
			}
		}
		logger.info("【还款中】总还款："+borrowPlanCount1+"个，【成功】还款："+sucCount1+"个，【失败】还款："+failCount1+"个，【垫付成功】："+faCount3+"个，【垫付失败】："+fasCount3+"个");
		logger.info("============【还款中】还款结束】============");
		
		logger.info("执行逾期/垫付定时器结束");
		logger.info("结果");
		logger.info("总共还款计划："+borrowPlanCount+"个，成功还款："+sucCount+"个，失败还款："+failCount+"个");
		logger.info("1.【逾期垫付】总还款："+borrowPlanCount3+"个，【成功】还款："+sucCount3+"个，【失败】还款："+failCount3+"个，【更新罚息成功】："+faCount+"个，【更新罚息成功】："+fasCount+"个");
		logger.info("2.【逾期还款】总还款："+borrowPlanCount2+"个，【成功】还款："+sucCount2+"个，【失败】还款："+failCount2+"个，【垫付成功】："+faCount2+"个，【垫付失败】："+fasCount2+"个");
		logger.info("3.【还款中】总还款："+borrowPlanCount1+"个，【成功】还款："+sucCount1+"个，【失败】还款："+failCount1+"个，【垫付成功】："+faCount3+"个，【垫付失败】："+fasCount3+"个");
		
	}
}

