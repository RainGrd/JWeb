/**
 * 条件设定字段
 */
var pubCondition_model = [[    
       	        {field:'pid',checkbox:true},
    	        {field:'condCode',title:'条件编号',width:'8%',align:'center',sortable:true},    
    	        {field:'condName',title:'条件名称',width:'10%',align:'center',sortable:true},    
    	        {field:'condTag',title:'条件标签',width:'10%',align:'center',sortable:true},    
    	        {field:'condDesc',title:'条件描述',width:'15%',align:'center',sortable:true},    
    	        {field:'condValue',title:'条件值(SQL)',width:'15%',align:'center',sortable:true},    
    	        {field:'status',title:'状态',width:'5%',align:'center',sortable:true,formatter:formatStatus},  
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
 * 格式化操作
 * @param value 值
 * @param row 行对象
 * @param index 行ID
 */
function formatCaoZuo(value,row,index){
	// 跳转到编辑页面
	var path = BASE_PATH + "pubConditionController/openPubConditionAdd.shtml?pid="+row.pid;
	var str = "编辑条件设定";
	return '<a onclick="childLayout_addTab(\''+path+'\',\''+str+'\')" href="#">编辑</a>';
}