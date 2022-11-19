/**
 * 赠送VIP活动明细datagrid字段
 */
var actInvWealthCoopDetail_model = [[    
       	        {field:'pid',checkbox:true},
    	        {field:'actCode',title:'编号',width:'12%',align:'center',sortable:true},    
    	        {field:'actName',title:'名称',width:'15%',align:'center',sortable:true},    
    	        {field:'actTag',title:'活动标签',width:'10%',align:'center',sortable:true},   
    	        {field:'investAwardType',title:'奖励方式',width:'10%',align:'center',sortable:true,formatter:formatType},   
    	        {field:'wealthCoopValue',title:'奖励值',width:'10%',align:'center',sortable:true,formatter:formatValue},   
    	        {field:'actStatus',title:'进行状态',width:'10%',align:'center',sortable:true,formatter:formatStatus},   
    	        {field:'participantsNumber',title:'获得人数',width:'8%',align:'center',sortable:true,formatter:formatNumber},    
    	        {field:'cz',title:'操作',width:'5%',align:'center',sortable:true,formatter:formatCaoZuo}
    	    ]];

var actInvWealthCoopDetailGrid_det_model= [[
				{field:'customerName',title:'用户名',width:'120px',align:'center',sortable:true,formatter:formatUser},    
				{field:'custName',title:'用户名称',width:'100px',align:'center',sortable:true},    
				{field:'phoneNo',title:'手机号码',width:'100px',align:'center',sortable:true},   
				{field:'wealthCoopValue',title:'奖励值',width:'100px',align:'center',sortable:true,formatter:formatValue},    
				{field:'getTime',title:'获得时间',width:'150px',align:'center',sortable:true},   
				{field:'investAwardType',title:'备注',width:'150px',align:'center',sortable:true,formatter:formatType},     
            ]];

function formatUser(value,row,index){
	return '<a onclick="openUserPage(\''+row.customerId+ '\')" href="#">'+row.customerName+'</a>'
	
}
function openUserPage(customerId){
	var path = BASE_PATH+"customerController/viewCustomerDataList.shtml?pid="+customerId;
	childLayout_addTab(path,'查看客户资料');
}
/**
 * 格式化状态
 * @param value 值
 * @param row 行对象
 * @param index 行ID
 */
function formatStatus(value,row,index){
	var myDate= new Date().getTime();
	var endTime =  new Date(row.endTime).getTime();
	var startTime = new Date(row.startTime).getTime();
	if(endTime == 0 || startTime== 0){
		return "";
	}
	if(myDate > endTime){
		return "已过期";
	}else if(myDate >= startTime && myDate <= endTime){
		return "进行中";
	}else if(myDate < startTime){
		return "未开始";
	} 
}

/**
 * 格式化参与人数
 * @param value 值
 * @param row 行对象
 * @param index 行ID
 */
function formatNumber(value,row,index){
	if(row.paychecksAmount != 0 && row.paychecksAmount){
		return row.paychecksAmount;
	}
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
	if(row.pid != null && row.pid != 0){ 
		// 跳转到编辑页面
		var path = BASE_PATH + "actInvWealthCoopDetailController/openWealthCoopDetailList.shtml?activityId="+row.pid+"&actCode="+row.actCode+"&actName="+row.actName;
		//因为Tomcat服务器会自动帮你做一次URLDecode，所以再加上你自己在代码里面写的URLDecode 所以要做两边encode
		path = encodeURI(encodeURI(path));
		var str = "查看赠送明细";
		return '<a onclick="childLayout_addTab(\''+path+'\',\''+str+'\')" href="#">查看</a>';
	}
}
/**
 * 格式化奖励值
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function formatValue(value,row,index){
	if(row.investAwardType == 1){
		return row.wealthCoopValue ;
	}else if(row.investAwardType == 2){
		return row.wealthCoopValue+"%";
	} 
}
/**
 * 格式化奖励方式
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function formatType(value,row,index){
	if(row.investAwardType == 1){
		return "固定金额奖励";
	}else if(row.investAwardType == 2){
		return "百分比奖励";
	} 
}
 