/**
 * 赠送VIP活动明细datagrid字段
 */
var actExpActDetail_model = [[    
    	        {field:'actCode',title:'体验金编号',width:'12%',align:'center',sortable:true},    
    	        {field:'actName',title:'体验金名称',width:'15%',align:'center',sortable:true},    
    	        {field:'actTag',title:'活动标签',width:'10%',align:'center',sortable:true},   
    	        {field:'status',title:'进行状态',width:'10%',align:'center',sortable:true,formatter:formatStatus},   
    	        {field:'expAmount',title:'体验金额',width:'10%',align:'right',sortable:true,formatter:common.formatCurrency},   
    	        {field:'getAmount',title:'已获得总额',width:'10%',align:'right',sortable:true,formatter:common.formatCurrency},   
    	        {field:'useAmount',title:'已使用金额',width:'10%',align:'right',sortable:true,formatter:common.formatCurrency},   
    	        {field:'participantsNumber',title:'参与人数',width:'8%',align:'center',sortable:true,formatter:formatNumber},    
    	        {field:'cz',title:'操作',width:'5%',align:'center',sortable:true,formatter:formatCaoZuo}
    	    ]];

var actExpActDetailGrid_det_model= [[
                {field:'pid',checkbox:true},
				{field:'customerName',title:'用户名',width:'120px',align:'center',sortable:true,formatter:formatterCusName},    
				{field:'custName',title:'用户名称',width:'100px',align:'center',sortable:true},    
				{field:'phoneNo',title:'手机号码',width:'100px',align:'center',sortable:true},   
				{field:'getAmount',title:'获得金额',width:'100px',align:'right',sortable:true,formatter:common.formatCurrency},   
				{field:'useStatus',title:'使用状况',width:'100px',align:'center',sortable:true,formatter:formatUseStatus},   
				{field:'getTime',title:'获取时间',width:'150px',align:'center',sortable:true,formatter:convertDateDetail},    
				{field:'expireTime',title:'过期时间',width:'150px',align:'center',sortable:true,formatter:convertDateDetail},
				{field:'useAmount',title:'使用金额',width:'100px',align:'right',sortable:true,formatter:common.formatCurrency},
				{field:'useTime',title:'使用时间',width:'150px',align:'center',sortable:true,formatter:convertDateDetail}
				    
            ]];

/**
 * 格式化状态
 * @param value 值
 * @param row 行对象
 * @param index 行ID
 */
function formatStatus(value,row,index){
	if(row.pid != null && row.pid != "" ){
		if(row.actStatus == 1){
			return "未开始";
		}else if(row.actStatus == 2){
			return "进行中";
		}else if(row.actStatus == 3){
			return "过期";
		}
		return "已删除";
	}
}

/**
 * 格式化使用状态
 * @param value 值
 * @param row 行对象
 * @param index 行ID
 */
function formatUseStatus(value,row,index){
	if(row.useStatus == 1){
		return "已使用";
	}else if(row.useStatus == 2){
		return "未使用";
	}
	return "";
}

/**
 * 格式化用户名
 * @param value 值
 * @param row 行对象
 * @param index 行ID
 */
function formatterCusName(value,row,index){
	if(null != row.customerName && row.customerName != ""){
		var path = BASE_PATH+"customerController/viewCustomerDataList.shtml?pid="+row.customerId;
		var str = "查看客户资料";
		return '<a onclick="childLayout_addTab(\''+path+'\',\''+str+'\')" href="#">'+row.customerName+'</a>';
	}
	return "";
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
	if(null != row.pid && row.pid != ""){
		// 跳转到编辑页面
		var path = BASE_PATH + "actExpActDetailController/openActExpActDetailList.shtml?activityId="+row.pid+"&actCode="+row.actCode+"&actName="+row.actName+"&expNumber="+row.expNumber;
		//因为Tomcat服务器会自动帮你做一次URLDecode，所以再加上你自己在代码里面写的URLDecode 所以要做两边encode
		path = encodeURI(encodeURI(path));
		var str = "查看赠送明细";
		return '<a onclick="childLayout_addTab(\''+path+'\',\''+str+'\')" href="#">查看</a>';
	}
	return "";
}
