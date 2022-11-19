/**
 * 财富合伙人信息列表字段
 */
var pubWealthCoop_model = [[    
       	        {field:'pid',checkbox:true},
    	        {field:'actCode',title:'奖励编号',width:'10%',align:'center',sortable:true},    
    	        {field:'wealthCoopName',title:'奖励名称',width:'10%',align:'center',sortable:true},   
    	        {field:'wealthCoopTyep',title:'奖励方式',width:'8%',align:'center',sortable:true,formatter:formatType},  
    	        {field:'wealthCoopValue',title:'奖励值',width:'8%',align:'center',sortable:true,formatter:common.formatCurrency},
    	        {field:'actTag',title:'奖励标签',width:'10%',align:'center',sortable:true},    
    	        {field:'invAwaDesc',title:'奖励描述',width:'15%',align:'center',sortable:true},    
    	        {field:'smsContent',title:'短信提醒内容',width:'20%',align:'center',sortable:true},
    	        {field:'startTime',title:'适用开始日期',width:'15%',align:'center',sortable:true,formatter:convertDateDetail}, 
    	        {field:'endTime',title:'适用结束日期',width:'15%',align:'center',sortable:true,formatter:convertDateDetail},  
    	        {field:'status',title:'状态',width:'5%',align:'center',sortable:true,formatter:formatStatus},  
    	        {field:'createUser',title:'创建人',width:'15%',align:'center',sortable:true},    
    	        {field:'createTime',title:'创建时间',width:'15%',align:'center',sortable:true,formatter:convertDateDetail},    
    	        {field:'cz',title:'操作',width:'8%',align:'center',sortable:true,formatter:formatCaoZuo}
    	    ]];

// 短信模版需要加载的列表字段
var smsTemplates_model = [[
                {field:'pid',checkbox:true},
       	        {field:'tempCode',title:'短信编号',width:'25%',align:'center',sortable:true},    
       	        {field:'smsContent',title:'短信内容',width:'60%',align:'center',sortable:true}
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
		return "已过期";
	}
	return "已删除";
}

/**
 * 格式化操作
 * @param value 值
 * @param row 行对象
 * @param index 行ID
 */
function formatCaoZuo(value,row,index){
	// 跳转到编辑页面
	var path = BASE_PATH + "pubWealthCoopController/openPubWealthCoopAdd.shtml?pid="+row.pid;
	var str = "编辑财富合伙人活动";
	return '<a onclick="childLayout_addTab(\''+path+'\',\''+str+'\')" href="#">编辑</a>';
}
function formatType(value,row,index){
	if(row.investAwardType == 1){
		return "固定金额奖励";
	}else if(row.investAwardType == 2){
		return "百分比奖励";
	}
	return "请选择";
}