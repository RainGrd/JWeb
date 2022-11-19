/**
 * 系统活动列表字段
 */
var actActivity_model = [[    
       	        {field:'activityType',title:'活动类型',width:'10%',align:'center',sortable:true},    
    	        {field:'actCode',title:'活动编号',width:'10%',align:'center',sortable:true},    
    	        {field:'actName',title:'活动名称',width:'10%',align:'center',sortable:true},    
    	        {field:'actTag',title:'活动标签',width:'10%',align:'center',sortable:true},    
    	        {field:'actDesc',title:'活动描述',width:'15%',align:'center',sortable:true},    
    	        {field:'startDate',title:'适用开始日期',width:'15%',align:'center',sortable:true,formatter:convertDateDetail}, 
    	        {field:'endDate',title:'适用结束日期',width:'15%',align:'center',sortable:true,formatter:convertDateDetail},  
    	        {field:'status',title:'进行状态',width:'5%',align:'center',sortable:true,formatter:formatStatus},
    	        {field:'participantsNumber',title:'参与人数',width:'8%',align:'center',sortable:true,formatter:formatNumber}, 
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
		return "未开始";
	}else if(row.status == 2){
		return "进行中";
	}else if(row.status == 3){
		return "过期";
	}
	return "已删除";
}

/**
 * 格式化参与人数
 * @param value 值
 * @param row 行对象
 * @param index 行ID
 */
function formatNumber(value,row,index){
	if(null == row.participantsNumber || row.participantsNumber == 0){
		return "-";
	}
	return row.participantsNumber;
}

/**
 * 格式化操作
 * @param value 值
 * @param row 行对象
 * @param index 行ID
 */
function formatCaoZuo(value,row,index){
	// 跳转到查看
	var path = BASE_PATH + "pubVipinfoController/openPubVipinfoAdd.shtml?pid="+row.pid;
	var str = "";
	if(row.activityType == "抢红包" || row.activityType == "送红包"){
		path = BASE_PATH + "actRedpActDetailController/openActRedpActDetailList.shtml?activityId="+row.pid+"&actCode="+row.actCode+"&actName="+row.actName;
		str = "红包获得明细";
	}else if(row.activityType == "赠送VIP"){
		path = BASE_PATH + "actVipActDetailController/openActVipActDetailList.shtml?activityId="+row.pid+"&actCode="+row.actCode+"&actName="+row.actName;
		str = "查看赠送明细";
	}else if(row.activityType == "体验金"){
		path = BASE_PATH + "actExpActDetailController/openActExpActDetailList.shtml?activityId="+row.pid+"&actCode="+row.actCode+"&actName="+row.actName+"&expNumber="+row.expNumber;
		str = "查看赠送明细";
	}else if(row.activityType == "投资奖励"){
		var path = BASE_PATH + "actInvAwaActDetailController/openActInvAwaActDetailList.shtml?activityId="+row.pid+"&actCode="+row.actCode+"&actName="+row.actName;
		str = "投资奖励获得详细";
	}else if(row.activityType == "VIP生日特权"){
		var path = BASE_PATH + "actBirPriItemActivityRelController/toBirPriDetails.shtml?activityId="+row.pid;
		str = "VIP生日特权活动查询";
	}else if(row.activityType == "财富合伙人奖励"){
		var path = BASE_PATH + "actInvWealthCoopDetailController/openWealthCoopDetailList.shtml?activityId="+row.pid+"&actCode="+row.actCode+"&actName="+row.actName;
		str = "财富合伙人奖励获得详细";
	}
	//因为Tomcat服务器会自动帮你做一次URLDecode，所以再加上你自己在代码里面写的URLDecode 所以要做两边encode
	path = encodeURI(encodeURI(path));
	return '<a onclick="childLayout_addTab(\''+path+'\',\''+str+'\')" href="#">查看</a>';
}