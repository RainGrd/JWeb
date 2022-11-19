/**
 * 任务管理js类
 */
var Task = {
	taskLoadCount:0,
	/**
	 * 初始化
	 */
	init:function(){
		Task.taskTotalCount = 0;
		Task.initFinancialTask();
		Task.initBusinessTask();
		Task.initClientTask();
		Task.initActivityTask();
	},
	/**
	 * 初始化财务审核任务
	 */
	initFinancialTask:function(){
		$.post(basePath+"taskController/financialTask.shtml",null,function(ret) {
			var htmlStr = Task.formatPageElement("财务管理",ret.data,ret.totalCount);
			Task.taskTotalCount+=ret.totalCount;
			$("#taskTotalCountI").html(Task.taskTotalCount);
			//添加页面元素到 此 finance_check_task_div  div
		 	$("#finance_check_task_div").html(htmlStr);
		});
	},
	/**
	 * 初始化业务审核任务
	 */
	initBusinessTask:function(){
		$.post(basePath+"taskController/businessTask.shtml",null,function(ret){
			var htmlStr = Task.formatPageElement("业务管理",ret.data,ret.totalCount);
			Task.taskTotalCount+=ret.totalCount;
			$("#taskTotalCountI").html(Task.taskTotalCount);
			//添加页面元素到 此 finance_check_task_div  div
		 	$("#business_check_task_div").html(htmlStr);
		});
	},
	/**
	 * 初始化客户审核任务
	 */
	initClientTask:function(){
		$.post(basePath+"taskController/clientTask.shtml",null,function(ret){
			var htmlStr = Task.formatPageElement("客户管理",ret.data,ret.totalCount);
			Task.taskTotalCount+=ret.totalCount;
			$("#taskTotalCountI").html(Task.taskTotalCount);
			//添加页面元素到 此 finance_check_task_div  div
		 	$("#client_check_task_div").html(htmlStr);
		});
	},
	/**
	 * 初始化活动审核任务
	 */
	initActivityTask:function(){
		$.post(basePath+"taskController/activityTask.shtml",null,function(ret){
			var htmlStr = Task.formatPageElement("活动-论坛管理",ret.data,ret.totalCount);
			Task.taskTotalCount+=ret.totalCount;
			$("#taskTotalCountI").html(Task.taskTotalCount);
			//添加页面元素到 此 finance_check_task_div  div
		 	$("#activity_check_task_div").html(htmlStr);
		});
	},
	/**
	 * 格式化页面元素
	 * @param data 数据元素列表
	 */
	formatPageElement:function(taskModuleName,data,totalCount){
		var htmlStr = "<div class='taskTitle disableTitle'>"+taskModuleName+'<span class="taskCount">'+totalCount+'</span></div>';
		if(totalCount){
			htmlStr = "<div class='taskTitle'>"+taskModuleName+'<span class="taskCount">'+totalCount+'</span></div>';
		}
		//下面使用each进 行遍历  FINANCE_CHECK_TASK
		htmlStr += '<div class="taskList"><ul>';
	    $.each(data,function(i,e) {
	    	if(e.taskCount){
	    		 htmlStr += '<li class="enableItem"><a href="#" onclick="Task.taskOnclick(\''+e.taskModule+'\',\''+e.taskType+'\')"><span class="badge badge-grey">';  
	    		 htmlStr += e.taskName;
	    		 htmlStr += '</span><span class="taskiCount">'+e.taskCount+'</span></a> </li>';
	    	}else{
	    		 htmlStr += '<li class="disableItem"><span class="badge badge-grey left">';  
	    		 htmlStr += e.taskName;
	    		 htmlStr += '</span><span class="taskiCount">'+e.taskCount+'</span></li>';
	    	}
    		 
	    });
	    htmlStr += '</ul></div>';
	    htmlStr += '<div class="clear"></div>';
	    return htmlStr;
	},
	/**
	 * 任务点击事件
	 * @param taskModule 任务模块    对应数据字典表t_sys_dictionary.dict_code
	 * @param taskType	 任务类型	对应数据字典表t_sys_dictionary_content.dict_cont_code
	 */
	taskOnclick:function(taskModule,taskType){
		/*
		 * ACTIVITY_CHECK_TASK(活动审核任务模块)
		 * FINANCE_CHECK_TASK(财务审核任务模块)
		 * BUSINESS_CHECK_TASK(业务审核任务模块)
		 * CLIENT_CHECK_TASK(客户审核任务模块)
		 */
		if("ACTIVITY_CHECK_TASK" == taskModule){
			Task.activityModuleTaskDispose(taskType);
		}else if("FINANCE_CHECK_TASK" == taskModule){
			Task.financeModuleTaskDispose(taskType);
		}else if("BUSINESS_CHECK_TASK" == taskModule){
			Task.businessModuleTaskDispose(taskType);
		}else if("CLIENT_CHECK_TASK" == taskModule){
			Task.clientModuleTaskDispose(taskType);
		}
	},
	/**
	 * 活动模块 子任务 处理方法
	 * @param taskType
	 */
	activityModuleTaskDispose:function(taskType){
		/*
		 * （1、2）对应数据字典表数据
		 *  1：活动审核任务->待发布活动
		 *  2：活动审核任务->进行中活动
		 */
		if("1" == taskType){
			// 1：活动审核任务->待发布活动	 页面
			var path = BASE_PATH+"actActivityController/openActActivity.shtml?status=1";
			layout_addTab(path,'待发布活动');
		}else if("2" == taskType){
			// 2：活动审核任务->进行中活动	 页面
			var path = BASE_PATH+"actActivityController/openActActivity.shtml?status=2";
			layout_addTab(path,'进行中活动');
		}
	},
	/**
	 * 财务模块 子任务 处理方法
	 * @param taskType
	 */
	financeModuleTaskDispose:function(taskType){
		/*
		 * （1、2）对应数据字典表数据
		 * 1：财务审核任务->提现申请审核
		 * 2：财务审核任务->充值审核
		 */
		if("1" == taskType){
			//1： 财务审核任务->提现申请审核 页面
			var path = BASE_PATH+"bizWithdrawController/openWithdrawCheckList.shtml?isTaskListOpen=true";
			layout_addTab(path,'提现审核');
		}else if("2" == taskType){
			// 2：财务审核任务->充值审核 页面
			var path = BASE_PATH+"bizRechargeOfflineController/openOfflineRechargeList.shtml?isTaskListOpen=true";
			layout_addTab(path,'线下充值客户列表');
		}else if("3" == taskType){
			// 2：财务审核任务->充值审核 页面
			var path = BASE_PATH+"bizWithdrawController/openTransferAffirmList.shtml?isTaskListOpen=true";
			layout_addTab(path,'转账确认');
		}
	},
	/**
	 * 业务模块 子任务 处理方法
	 * @param taskType
	 */
	businessModuleTaskDispose:function(taskType){
		/*
		 * （1、2）对应数据字典表数据
		 *  1：业务审核任务->借款担保初审
		 *  2：业务审核任务->借款借前审核
		 */
		if("1" == taskType){
			// 1：业务审核任务->借款担保初审 页面
			var path = BASE_PATH+"bizBorrowApproveController/toBorrowPrelimList.shtml?isTaskListOpen=true";
			layout_addTab(path,'借款担保审核');
		}else if("2" == taskType){
			// 2：业务审核任务->借款借前审核 页面
			var path = BASE_PATH+"bizBorrowApproveController/toBorrowReviewList.shtml?isTaskListOpen=true";
			layout_addTab(path,'借款借前审核');
		}else if("3" == taskType){
			// 3：业务审核任务->项目发布
			var path = BASE_PATH+"bizBorrowController/toList.shtml";
			layout_addTab(path,'借款项目发布');
		}else if("4" == taskType){
			//4：业务审核任务->下个月待还款
			var path = BASE_PATH+"borrowAfterManageController/toBorrowAfterMPage.shtml?isTaskListOpen=true";
			layout_addTab(path,'下个月待还款');
		}else if("5" == taskType){
			//5：业务审核任务->近15天转让债权
			var path = BASE_PATH+"bizReceiptTransferController/toBizReceiptTransferList.shtml?isTaskListOpen=true";
			layout_addTab(path,'债权管理');
		}
	},
	/**
	 * 客户模块 子任务 处理方法
	 * @param taskType
	 */
	clientModuleTaskDispose:function(taskType){
		/*
		 * （1、2）对应数据字典表数据
		 *  1：客户审核任务->实名认证	
		 *  2：客户审核任务->VIP生日	
		 */
		if("1" == taskType){
			// 1：客户审核任务->实名认证	 页面
			var path = BASE_PATH+"authenVipCustomerController/openCustAuthenticationHis.shtml?isTaskListOpen=true";
			layout_addTab(path,'实名认证');
		}else if("2" == taskType){
			// 2：客户审核任务->VIP生日	 页面
			var path = BASE_PATH+"authenVipCustomerController/openCusBirWarnHis.shtml?isTaskListOpen=true";
			layout_addTab(path,'VIP生日');
		}
	}
		
}


$(function(){
	debugger;
	if(Task.taskLoadCount==0){
		Task.init();
	}
	Task.taskLoadCount++;
}); 