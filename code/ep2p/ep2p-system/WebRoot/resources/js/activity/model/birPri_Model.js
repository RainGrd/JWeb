/**
 * 体验金信息列表字段
 */
var birPri_Model = [[    
       	        {field:'pid',checkbox:true},
    	        {field:'actCode',title:'特权编号',width:'10%',align:'center',sortable:true},    
    	        {field:'actName',title:'特权名称',width:'10%',align:'center',sortable:true},    
    	        {field:'actTag',title:'特权',width:'8%',align:'center',sortable:true},
    	        {field:'actDesc',title:'特权描述',width:'10%',align:'center',nowrap:'false',sortable:true},         
    	        {field:'smsContent',title:'短信提醒内容',width:'20%',align:'center',sortable:true},
    	        {field:'startDate',title:'适用开始日期',width:'15%',align:'center',sortable:true,formatter:convertDateDetail}, 
    	        {field:'endDate',title:'适用结束日期',width:'15%',align:'center',sortable:true,formatter:convertDateDetail},  
    	        {field:'status',title:'状态',width:'5%',align:'center',sortable:true,formatter:formatStatus},  
    	        {field:'createUser',title:'创建人',width:'15%',align:'center',sortable:true},    
    	        {field:'createTime',title:'创建时间',width:'15%',align:'center',sortable:true,formatter:convertDateDetail},    
    	        {field:'cz',title:'操作',width:'8%',align:'center',sortable:true,formatter:formatOperation}
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
function formatOperation(value,row,index){
	// 跳转到编辑页面
	var path = BASE_PATH + "actActivityController/openVIPBirPriEditPage.shtml?pid="+row.pid;
	var str = "新增/编辑VIP生日特权";
	return '<a onclick="childLayout_addTab(\''+path+'\',\''+str+'\')" href="#">编辑</a>';
}