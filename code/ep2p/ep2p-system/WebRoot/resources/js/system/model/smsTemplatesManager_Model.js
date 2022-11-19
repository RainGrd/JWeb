/**
 * 数据字典管理列表字段
 */
var smsTemplatesManager_model = [[    
       	        {field:'pid',checkbox:true},
       	        //{field:'tt',title:'序号',width:'3%',align:'center',sortable:true,formatter:formatSer}, 
    	        {field:'tempCode',title:'短信编码',width:'15%',align:'center',sortable:true},    
    	        {field:'smsTempName',title:'短信名称',width:'15%',align:'center',sortable:true},    
    	        {field:'smsContent',title:'短信内容',width:'20%',align:'center',sortable:true}, 
    	        {field:'createUser',title:'创建人',width:'15%',align:'center',sortable:true}, 
    	        {field:'createTime',title:'创建时间',width:'10%',align:'center',sortable:true,formatter:convertDate}, 
    	        {field:'yy',title:'操作',width:'10%',align:'center',formatter:formatOperation}
    	    ]];

var ser = 0 ;

/**
 * 序号
 * @param value
 * @param row
 * @param index
 * @returns {Number}
 */
function formatSer(value,row,index){
	return ser++;
}

//操作 格式化
function formatOperation(value,row,index){
	return '<a onclick="smsTemplates.update(\''+row.pid+'\')" href="#">修改</a> <a href="#" onclick="testSendSms(\''+row.pid+'\')">测试</a>';
}

function updateDetail(pid){
	var path = BASE_PATH+"sysSmsTemplatesController/toAdd.shtml?pid="+pid;
	childLayout_addTab(path,'数据字典内容查看');
}

function testSendSms(pid){
	$.ajax({
		type: "POST",
        async:false, 
    	url : BASE_PATH+"sysSmsTemplatesController/testSendSms.shtml",
    	data:{pid:pid},
		dataType: "json",
	    success: function(data){
	    	if(data.message.status == 200 ){
	    		$.messager.alert('操作提示',"发送成功！",'success');
	    	}else{
	    		$.messager.alert('操作提示',"保存失败,请联系管理员！",'error');
	    	}
	    	
		}
	}); 
}

// 是否允许修改 格式化
function formatStatus(value,row,index){
	if(row.status == 0){
		return "关闭";
	}
	return "启用";
}

