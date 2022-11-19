/*
 **************************************************************************
 * 版权声明：
 * 本软件为深圳市力泰金融信息服务有限公司开发研制。未经本公司正式书面同意，
 * 其他任何个人、团体不得使用、复制、修改或发布本软件. 
 **************************************************************************
 * 程序描述:
 * 
 **************************************************************************
 * 修改历史:  
 * Date:       	    by:    		    Reason:  
 *           
 * 2015年10月8日     JingYu.Dai		Initial Version.
 *************************************************************************
 */
package com.yscf.system.controller.task;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.achievo.framework.exception.FrameworkException;
import com.yscf.common.Constant.Constant;
import com.yscf.core.model.system.SysDictionaryContent;
import com.yscf.core.service.activity.impl.ActActivityServiceImpl;
import com.yscf.core.service.business.impl.BizBorrowInfoVOServiceImpl;
import com.yscf.core.service.business.impl.BizReceiptTransferServiceImpl;
import com.yscf.core.service.business.impl.BorrowAfterManageServiceImpl;
import com.yscf.core.service.financial.impl.BizRechargeOfflineServiceImpl;
import com.yscf.core.service.financial.impl.BizWithdrawServiceImpl;
import com.yscf.core.service.system.impl.SysDictionaryContentServiceImpl;
import com.yscf.core.service.user.impl.CusBirWarnHisServiceImpl;
import com.yscf.core.service.user.impl.CusTomerServiceImpl;
import com.yscf.system.controller.base.EscfBaseController;

/**
 * Description：任务管理
 * @author  JingYu.Dai
 * @date    2015年10月8日
 * @version v1.0.0
 */
@Controller
@RequestMapping("/taskController")
public class TaskController extends EscfBaseController{

	@Autowired
	public TaskController(SysDictionaryContentServiceImpl service) {
		super(service);
	}
	@Override
	public Class<?> getModel() {
		return null;
	}

	private Logger logger = LoggerFactory.getLogger(TaskController.class);
	
	//数据字典内容业务交互实现类
	@Resource(name="sysDictionaryContentService")
	private SysDictionaryContentServiceImpl contentServiceImpl;
	
	//线下充值业务处理实现类
	@Resource(name="bizRechargeOfflineServiceImpl")
	private BizRechargeOfflineServiceImpl offlineServiceImpl;
	
	//系统提现业务处理实现类
	@Resource(name="bizWithdrawService")
	private BizWithdrawServiceImpl withdrawServiceImpl;
	
	//借款
	@Resource(name="bizBorrowInfoVOService")
	private BizBorrowInfoVOServiceImpl bizBorrowInfoVOService;
	
	//债券
	@Resource(name="bizReceiptTransferService")
	private BizReceiptTransferServiceImpl bizReceiptTransferService;
	
	//活动管理业务商用接口
	@Resource(name="actActivityServiceImpl")
	private ActActivityServiceImpl actActivityServiceImpl;
	
	@Resource(name="cusTomerService")
	private CusTomerServiceImpl cusTomerServiceImpl;
	
	@Resource(name="cusBirWarnHisServiceImpl")
	private CusBirWarnHisServiceImpl cusBirWarnHisServiceImpl;
	
	@Resource(name="borrowAfterManageServiceImpl")
	private BorrowAfterManageServiceImpl borrowAfterManageServiceImpl;
	/**
	 * Description：财务审核任务
	 * @author  JingYu.Dai
	 * @date    2015年10月9日
	 * @version v1.0.0
	 * @return  Map<String,Object>
	 */
	@RequestMapping("/financialTask")
	@ResponseBody
	public Map<String,Object> financialTask(HttpServletRequest request , HttpServletResponse response) {
		Map<String,Object> map = new HashMap<String, Object>();
		List<TaskVo> vos = new ArrayList<TaskVo>();
		Integer totalCount = 0;
		// 查询数据字典列表 根据key值
		List<SysDictionaryContent> dcs = contentServiceImpl.selectByDisctCode(TaskConstant.FINANCE_CHECK_TASK);
		for (SysDictionaryContent dc : dcs) {
			//创建任务对象
			TaskVo vo = new TaskVo();
			vo.setTaskModule(TaskConstant.FINANCE_CHECK_TASK);
			//定义总记录条数初始值
			try {
				if (TaskConstant.FINANCE_RECHARGE.equals(dc.getDictContCode())&&isPermission(10045)) {
					//查询充值资金待审核状态总记录条数
					int count = offlineServiceImpl.getTotalCountByRecStatus(Constant.REC_STATUS_REFERENDUM);
					vo.setTaskCount(count);
					vo.setTaskType(TaskConstant.FINANCE_RECHARGE);
					totalCount += count;
				} else if (TaskConstant.FINANCE_TRANSFER_CONFIRMATION.equals(dc.getDictContCode())&&isPermission(10060)) {
					//查询转账确认 总记录条数
					int count = withdrawServiceImpl.getTotalCountByAuditStatus(Constant.WITHDRAWAL_TRANSFER_CONFIRMATION);
					vo.setTaskCount(count);
					vo.setTaskType(TaskConstant.FINANCE_TRANSFER_CONFIRMATION);
					totalCount += count;
				} else if (TaskConstant.FINANCE_WITHDRAW.equals(dc.getDictContCode())&&isPermission(10059)) {
					//查询提现申请审核 总记录条数
					int count = withdrawServiceImpl.getTotalCountByAuditStatus(Constant.WITHDRAWAL_AUDIT);
					vo.setTaskCount(count);
					vo.setTaskType(TaskConstant.FINANCE_WITHDRAW);
					totalCount += count;
				}
			} catch (FrameworkException e) {
				if (logger.isInfoEnabled()) {
					e.printStackTrace();
				}
			}
			vo.setTaskName(dc.getDictContName());
			vos.add(vo);
		}
		map.put("data", vos);
		map.put("status", "200");
		map.put("totalCount", totalCount);
		return map;
	}
	
	/**
	 * Description：业务审核任务 
	 * @author  JingYu.Dai
	 * @date    2015年10月9日
	 * @version v1.0.0
	 * @param request
	 * @param response 
	 * @return Map<String,Object>
	 */
	@RequestMapping("/businessTask")
	@ResponseBody
	public Map<String,Object> businessTask(HttpServletRequest request , HttpServletResponse response) {
		Map<String,Object> map = new HashMap<String, Object>();
		List<TaskVo> vos = new ArrayList<TaskVo>();
		Integer totalCount = 0;
		// 查询数据字典列表 根据key值
		List<SysDictionaryContent> dcs = contentServiceImpl.selectByDisctCode(TaskConstant.BUSINESS_CHECK_TASK);
		for (SysDictionaryContent dc : dcs) {
			//创建任务对象
			TaskVo vo = new TaskVo();
			vo.setTaskModule(TaskConstant.BUSINESS_CHECK_TASK);
			//定义总记录条数初始值
			try {
				if (TaskConstant.BUSINESS_LOAN_GUARANTEE.equals(dc.getDictContCode())&&isPermission(10040)) {
					//业务审核任务->借款担保初审任务  总记录条数
					int count = bizBorrowInfoVOService.getApproverCount("1");
					vo.setTaskCount(count);
					vo.setTaskType(TaskConstant.BUSINESS_LOAN_GUARANTEE);
					totalCount += count;
				} else if (TaskConstant.BUSINESS_BEFORE_BORROWING.equals(dc.getDictContCode())&&isPermission(10044)) {
					//业务审核任务->借款借前审核任务	 总记录条数
					int count = bizBorrowInfoVOService.getApproverCount("2");
					vo.setTaskCount(count);
					vo.setTaskType(TaskConstant.BUSINESS_BEFORE_BORROWING);
					totalCount += count;
				} else if (TaskConstant.BUSINESS_PROJECT_RELEASE.equals(dc.getDictContCode())&&isPermission(10048)) {
					int count = bizBorrowInfoVOService.getReleaseCount("6");
					vo.setTaskCount(count);
					vo.setTaskType(TaskConstant.BUSINESS_PROJECT_RELEASE);
					totalCount += count;
				} else if (TaskConstant.BUSINESS_NEXT_MONTH_REPAYMENT.equals(dc.getDictContCode())&&isPermission(10086)) {
					//业务审核任务->下个月待还款	 总记录条数
					int count = borrowAfterManageServiceImpl.selectTotalReplayNumbers();
					vo.setTaskCount(count);
					vo.setTaskType(TaskConstant.BUSINESS_NEXT_MONTH_REPAYMENT);
					totalCount += count;
				} else if (TaskConstant.BUSINESS_ASSIGNED_CLAIM.equals(dc.getDictContCode())&&isPermission(10075)) {
					int count = bizReceiptTransferService.selectCountByCreateTime(new Date(), 15);
					vo.setTaskCount(count);
					vo.setTaskType(TaskConstant.BUSINESS_ASSIGNED_CLAIM);
					totalCount += count;
				} 
			} catch (Exception e) {
				if (logger.isInfoEnabled()) {
					e.printStackTrace();
				}
			}
			vo.setTaskName(dc.getDictContName());
			vos.add(vo);
		}
		map.put("data", vos);
		map.put("status", "200");
		map.put("totalCount", totalCount);
		return map;
	}
	
	/**
	 * Description：客户审核任务
	 * @author  JingYu.Dai
	 * @date    2015年10月19日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 */
	@RequestMapping("/clientTask")
	@ResponseBody
	public Map<String,Object> clientTask(HttpServletRequest request , HttpServletResponse response) {
		Map<String,Object> map = new HashMap<String, Object>();
		List<TaskVo> vos = new ArrayList<TaskVo>();
		Integer totalCount = 0;
		// 查询数据字典列表 根据key值
		List<SysDictionaryContent> dcs = contentServiceImpl.selectByDisctCode(TaskConstant.CLIENT_CHECK_TASK);
		for (SysDictionaryContent dc : dcs) {
			//创建任务对象
			TaskVo vo = new TaskVo();
			vo.setTaskModule(TaskConstant.CLIENT_CHECK_TASK);
			//定义总记录条数初始值
			try {
				if (TaskConstant.CLIENT_CHECK_CERTIFICATION.equals(dc.getDictContCode())&&isPermission(10070)) {
					//查询提交实名认证人数	总数
					int count = cusTomerServiceImpl.selectAuthenticationCount();
					vo.setTaskCount(count);
					vo.setTaskType(TaskConstant.CLIENT_CHECK_CERTIFICATION);
					totalCount += count;
				} else if (TaskConstant.CLIENT_CHECK_VIP_BIRTHDAY.equals(dc.getDictContCode())&&isPermission(10076)) {
					//查询今天VIP生日提醒数 	总数
					int count = cusBirWarnHisServiceImpl.selectVipBirCount();
					vo.setTaskCount(count);
					vo.setTaskType(TaskConstant.CLIENT_CHECK_VIP_BIRTHDAY);
					totalCount += count;
				} 
			} catch (Exception e) {
				if (logger.isInfoEnabled()) {
					e.printStackTrace();
				}
			}
			vo.setTaskName(dc.getDictContName());
			vos.add(vo);
		}
		map.put("data", vos);
		map.put("status", "200");
		map.put("totalCount", totalCount);
		return map;
	}
	
	/**
	 * Description：活动任务
	 * @author  JingYu.Dai
	 * @date    2015年10月19日
	 * @version v1.0.0
	 * @param request
	 * @param response
	 * @return Map<String,Object>
	 */
	@RequestMapping("/activityTask")
	@ResponseBody
	public Map<String,Object> activityTask(HttpServletRequest request , HttpServletResponse response) {
		Map<String,Object> map = new HashMap<String, Object>();
		List<TaskVo> vos = new ArrayList<TaskVo>();
		Integer totalCount = 0;
		// 查询数据字典列表 根据key值
		List<SysDictionaryContent> dcs = contentServiceImpl.selectByDisctCode(TaskConstant.ACTIVITY_CHECK_TASK);
		for (SysDictionaryContent dc : dcs) {
			//创建任务对象
			TaskVo vo = new TaskVo();
			vo.setTaskModule(TaskConstant.ACTIVITY_CHECK_TASK);
			//定义总记录条数初始值
			try {
				if(isPermission(10084)){
					if (TaskConstant.ACTIVITY_TO_LAUNCH.equals(dc.getDictContCode())) {
						//活动审核任务->待发布活动	总数
						int count = actActivityServiceImpl.selectCountByStatus("1");
						vo.setTaskCount(count);
						vo.setTaskType(TaskConstant.ACTIVITY_TO_LAUNCH);
						totalCount += count;
					} else if (TaskConstant.ACTIVITY_IN_THE_ACTIVITY.equals(dc.getDictContCode())) {
						//活动审核任务->进行中活动	 总记录条数
						int count = actActivityServiceImpl.selectCountByStatus("2");
						vo.setTaskCount(count);
						vo.setTaskType(TaskConstant.ACTIVITY_IN_THE_ACTIVITY);
						totalCount += count;
					}  
				}
			} catch (Exception e) {
				if (logger.isInfoEnabled()) {
					e.printStackTrace();
				}
			}
			vo.setTaskName(dc.getDictContName());
			vos.add(vo);
		}
		map.put("data", vos);
		map.put("status", "200");
		map.put("totalCount", totalCount);
		return map;
	}
	
	private boolean isPermission(Integer menuCode) {
		boolean falg = false;
		List<Integer> permissions = getContextUserPermissionList();
		// 实体类Role中包含有角色权限的实体类信息
		// 获取权限
		for (Integer p : permissions) {
			System.out.println(p);
			if (menuCode.equals(p)) {
				falg = true;
				return falg;
			}
		}
		return falg;
	}

}


