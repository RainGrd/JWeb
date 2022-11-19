/**
 *业务提现审核类js_model
 */
var biz_fundtally_model = [[    
       	        {field:'pid',checkbox:true},
       	        {field:'actorTime',title:'日期',width:'12%',align:'center',sortable:true}, 
    	        {field:'customerName',title:'客户名称',width:'10%',align:'center',formatter:common.getCustomerName},    
    	        {field:'sname',title:'真实姓名',width:'10%',align:'center',formatter:common.getSName}, 
    	        {field:'phoneNo',title:'手机号码',width:'10%',align:'center',formatter:common.getPhoneNo},
    	        
    	        {field:'incomeAmount',title:'收入',width:'8%',align:'center',sortable:true,formatter:common.formatCurrency}, 
    	        {field:'outlayAmount',title:'支出',width:'8%',align:'center',sortable:true,formatter:common.formatCurrency},
    	        {field:'detailType',title:'类型',width:'10%',align:'center',sortable:true,formatter:formatAuditStatus},
    	        {field:'funDesc',title:'备注',width:'32%',align:'center',sortable:true}
    	    ]];

/**
 * 获取流水类型
 *	1001	VIP付费
	1003	利息管理费
	1004	推荐奖励分成
	1006	推荐奖励
	1007	提现手续费
	1009	债权转让服务费
	1011	管理费率
	1012	加息奖励
	1014	投资奖励
	1016	红包
	1018	积分兑现
	1020	系统奖励
	1022	体验标
	1024	赠送体验金
	1025	回收体验金
	1026	收取罚息
	1028	续投奖励
	1030	抢到红包
	1032	派发红包
	1034	系统奖励
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function formatAuditStatus(value,row,index){
	if(value == '1001'){
		return "VIP付费";
	}else if(value == '1003'){
		return "利息管理费";
	}else if(value == '1004'){
		return "推荐奖励分成";
	}else if(value == '1006'){
		return "推荐奖励";
	}else if(value == '1007'){
		return "提现手续费";
	}else if(value == '1009'){
		return "债权转让服务费";
	}else if(value == '1011'){
		return "管理费率";
	}else if(value == '1012'){
		return "加息奖励";
	}else if(value == '1014'){
		return "投资奖励";
	}else if(value == '1016'){
		return "红包";
	}else if(value == '1018'){
		return "积分兑现";
	}else if(value == '1020'){
		return "系统奖励";
	}else if(value == '1022'){
		return "体验标";
	}else if(value == '1024'){
		return "赠送体验金";
	}else if(value == '1025'){
		return "回收体验金";
	}else if(value == '1026'){
		return "收取罚息";
	}else if(value == '1028'){
		return "续投奖励";
	}else if(value == '1030'){
		return "抢到红包";
	}else if(value == '1032'){
		return "派发红包";
	}else if(value == '1034'){
		return "系统奖励";
	}
	return "";
}

