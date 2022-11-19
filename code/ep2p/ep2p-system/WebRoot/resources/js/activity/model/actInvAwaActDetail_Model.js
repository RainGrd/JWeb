/**
 * 投资奖励活动查询datagrid字段
 */
var actInvAwaActDetail_model = [[    
       	        {field:'pid',checkbox:true},
    	        {field:'actCode',title:'奖励编号',width:'12%',align:'center',sortable:true},    
    	        {field:'actName',title:'奖励名称',width:'15%',align:'center',sortable:true},    
    	        {field:'actTag',title:'活动标签',width:'10%',align:'center',sortable:true},   
    	        {field:'investAwardType',title:'奖励类型',width:'10%',align:'center',sortable:true,formatter:formatAwardType},   
    	        {field:'investAwardValue',title:'奖励值',width:'10%',align:'center',sortable:true,formatter:formatAwardValue},   
    	        {field:'actStatus',title:'进行状态',width:'10%',align:'center',sortable:true,formatter:formatStatus},   
    	        {field:'participantsNumber',title:'参与人数',width:'8%',align:'center',sortable:true,formatter:formatNumber},    
    	        {field:'cz',title:'操作',width:'5%',align:'center',sortable:true,formatter:formatCaoZuo}
    	    ]];

var actInvAwaActDetailGrid_det_model= [[
				{field:'customerName',title:'用户名',width:'120px',align:'center',sortable:true,formatter:formatUser},    
				{field:'custName',title:'用户名称',width:'100px',align:'center',sortable:true},    
				{field:'phoneNo',title:'手机号码',width:'100px',align:'center',sortable:true},   
				{field:'investAwardValue',title:'奖励',width:'100px',align:'center',sortable:true,formatter:formatAwardValue},   
				{field:'useStatus',title:'使用情况',width:'100px',align:'center',sortable:true,formatter:formatUseStatus},    
				{field:'getTime',title:'获得时间',width:'100px',align:'center',sortable:true},   
				{field:'investAwardType',title:'备注',width:'150px',align:'center',sortable:true,formatter:formatAwardType},     
            ]];

function formatUser(value,row,index){
	return '<a onclick="openUserPage(\''+row.customerId+ '\')" href="#">'+row.customerName+'</a>'
	
}
function openUserPage(customerId){
	var path = BASE_PATH+"customerController/viewCustomerDataList.shtml?pid="+customerId;
	childLayout_addTab(path,'查看客户资料');
}
/**
 * 奖励类型
 * @param value 值
 * @param row 行对象
 * @param index 行ID
 */
function formatAwardType(value,row,index){
	if(row.pid != null && row.pid != "" ){
		if(row.investAwardType == 1){
			return "积分";
		}else if(row.investAwardType == 2){
			return "经验";
		}else if(row.investAwardType == 3){
			return "加息";
		}
		return "新状态";
	}
}

/**
 * 奖励值
 * @param value 值
 * @param row 行对象
 * @param index 行ID
 */
function formatAwardValue(value,row,index){
	if(row.pid != null && row.pid != "" ){
		if(row.investAwardType == 3){
			return row.investAwardValue + "%" ;
		}else{
			return row.investAwardValue;
		}
	}
}

/**
 * 格式化状态
 * @param value 值
 * @param row 行对象
 * @param index 行ID
 */
function formatStatus(value,row,index){
	if(row.pid != null && row.pid != "" ){
		var myDate= new Date().getTime();
		var endTime =  new Date(row.endTime).getTime();
		var startTime = new Date(row.startTime).getTime();
		if(myDate > endTime){
			return "已过期";
		}else if(myDate >= startTime && myDate <= endTime){
			return "进行中";
		}else if(myDate < startTime){
			return "未开始";
		} 
	}
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
	if(row.investAwardType == 3){
		return row.participantsNumber+"/"+row.interest;
	} else {
		return row.participantsNumber;
	}
}

/**
 * 格式化操作
 * @param value 值
 * @param row 行对象
 * @param index 行ID
 */
function formatCaoZuo(value,row,index){ 
	if(row.pid != null && row.pid != "" ){
		// 跳转到编辑页面
		var path = BASE_PATH + "actInvAwaActDetailController/openActInvAwaActDetailList.shtml?activityId="+row.pid+"&actCode="+row.actCode+"&actName="+row.actName;
		//因为Tomcat服务器会自动帮你做一次URLDecode，所以再加上你自己在代码里面写的URLDecode 所以要做两边encode
		path = encodeURI(encodeURI(path));
		var str = "查看赠送明细";
		return '<a onclick="childLayout_addTab(\''+path+'\',\''+str+'\')" href="#">查看</a>';
	}
}

function formatUseStatus(value,row,index){
	if(row.useStatus == 1){
		return "已使用";
	}else if(row.useStatus == 2){
		return "未使用";
	}else{
		return "-";
	}
}
 