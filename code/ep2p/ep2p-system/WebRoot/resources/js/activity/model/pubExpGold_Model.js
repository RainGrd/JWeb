/**
 * 体验金信息列表字段
 */
var pubExpGold_model = [[    
       	        {field:'pid',checkbox:true},
    	        {field:'actCode',title:'体验金编号',width:'10%',align:'center',sortable:true},    
    	        {field:'expName',title:'体验金名称',width:'10%',align:'center',sortable:true},    
    	        {field:'expAmount',title:'体验金金额',width:'8%',align:'right',sortable:true,formatter:common.formatCurrency},
    	        {field:'actTag',title:'体验金标签',width:'10%',align:'center',sortable:true},    
    	        {field:'expGolDesc',title:'体验金描述',width:'15%',align:'center',sortable:true},    
    	        {field:'smsContent',title:'短信提醒内容',width:'20%',align:'center',sortable:true},
    	        {field:'effTime',title:'适用开始日期',width:'15%',align:'center',sortable:true,formatter:convertDateDetail}, 
    	        {field:'expTime',title:'适用结束日期',width:'15%',align:'center',sortable:true,formatter:convertDateDetail},  
    	        {field:'status',title:'状态',width:'5%',align:'center',sortable:true,formatter:formatStatus},  
    	        {field:'createUserName',title:'创建人',width:'15%',align:'center',sortable:true},    
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
	var path = BASE_PATH + "pubExpGoldController/openPubExpGoldAdd.shtml?pid="+row.pid;
	var str = "编辑体验金活动";
	return '<a onclick="childLayout_addTab(\''+path+'\',\''+str+'\')" href="#">编辑</a>';
}