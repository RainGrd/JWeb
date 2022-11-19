/**
 * VIP信息列表字段
 */
var sysVipinfo_model = [[    
       	        {field:'pid',checkbox:true},
    	        {field:'vipCode',title:'VIP编号',width:'8%',align:'center',sortable:true},    
    	        {field:'vipName',title:'VIP名称',width:'8%',align:'center',sortable:true},    
    	        {field:'vipLevel',title:'VIP级别',width:'5%',align:'center',sortable:true},    
    	        {field:'vipIco',title:'图标样式',width:'10%',align:'center',sortable:true},    
    	        {field:'startTime',title:'适用开始日期',width:'12%',align:'center',sortable:true,formatter:convertDateDetail}, 
    	        {field:'endTime',title:'适用结束日期',width:'12%',align:'center',sortable:true,formatter:convertDateDetail},  
    	        {field:'status',title:'状态',width:'8%',align:'center',sortable:true,formatter:formatStatus},  
    	        {field:'remark',title:'备注',width:'15%',align:'center',sortable:true},    
    	        {field:'createUserName',title:'创建人',width:'12%',align:'center',sortable:true},    
    	        {field:'createTime',title:'创建时间',width:'12%',align:'center',sortable:true,formatter:convertDateDetail},    
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
	var path = BASE_PATH + "sysVipinfoController/openSysVipinfoAdd.shtml?pid="+row.pid;
	var str = "编辑VIP信息";
	return '<a onclick="childLayout_addTab(\''+path+'\',\''+str+'\')" href="#">编辑</a>';
}