/**
 * 投资奖励信息列表字段
 */
var pubInvestAward_model = [[    
       	        {field:'pid',checkbox:true},
    	        {field:'actCode',title:'奖励编号',width:'10%',align:'center',sortable:true},    
    	        {field:'investAwardName',title:'奖励名称',width:'10%',align:'center',sortable:true},    
    	        {field:'investAwardType',title:'奖励方式',width:'10%',align:'center',sortable:true,formatter:formatInvestAwardType},    
    	        {field:'investAwardValue',title:'奖励值',width:'8%',align:'center',sortable:true,formatter:formatAwardValue},
    	        {field:'actTag',title:'奖励标签',width:'10%',align:'center',sortable:true},  
    	        {field:'invAwaDesc',title:'奖励描述',width:'15%',align:'center',sortable:true},    
    	        {field:'smsContent',title:'短信提醒内容',width:'20%',align:'center',sortable:true},
    	        {field:'status',title:'状态',width:'5%',align:'center',sortable:true,formatter:formatStatus},  
    	        {field:'startTime',title:'适用开始日期',width:'15%',align:'center',sortable:true,formatter:convertDateDetail}, 
    	        {field:'endTime',title:'适用结束日期',width:'15%',align:'center',sortable:true,formatter:convertDateDetail},  
    	        {field:'createUserName',title:'创建人',width:'15%',align:'center',sortable:true},    
    	        {field:'createTime',title:'创建时间',width:'15%',align:'center',sortable:true,formatter:convertDateDetail},    
    	        {field:'cz',title:'操作',width:'8%',align:'center',sortable:true,formatter:formatCaoZuo}
    	    ]];

/**
 * 格式化状态
 * @param value 值
 * @param row 行对象
 * @param index 行ID
 */
function formatStatus(value,row,index){
	if(row.status == 1){
		return "启用";
	}else if(row.status == 2){
		return "禁用";
	}
	return "已删除";
}

/**
 * 奖励值
 * @param value 值
 * @param row 行对象
 * @param index 行ID
 */
function formatAwardValue(value,row,index){
	if(row.investAwardType == 3){
		return row.investAwardValue + "%" ;
	}else{
		return row.investAwardValue;
	}
}

/**
 * 格式化奖励类型
 * @param value 值
 * @param row 行对象
 * @param index 行ID
 */
function formatInvestAwardType(value,row,index){
	if(row.investAwardType == 1){
		return "积分";
	}else if(row.investAwardType == 2){
		return "经验";
	}else if(row.investAwardType == 3){
		return "加息劵";
	}
	return "其他";
}

/**
 * 格式化操作
 * @param value 值
 * @param row 行对象
 * @param index 行ID
 */
function formatCaoZuo(value,row,index){
	// 跳转到编辑页面
	var path = BASE_PATH + "pubInvestAwardController/openPubInvestAwardAdd.shtml?pid="+row.pid;
	var str = "编辑投资奖励活动";
	return '<a onclick="childLayout_addTab(\''+path+'\',\''+str+'\')" href="#">编辑</a>';
}