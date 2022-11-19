/**
 * 实名认证列表字段
 */
var cusBirWarnHis_Model = [[    
       	        {field:'pid',checkbox:true},
    	        {field:'customerName',title:'用户名',width:'10%',align:'center',sortable:true},    
    	        {field:'sname',title:'姓名',width:'10%',align:'center',sortable:true,formatter:formatCusName},    
    	        {field:'phoneNo',title:'手机号码',width:'10%',align:'center',sortable:true}, 
    	        {field:'birthday',title:'生日',width:'10%',align:'center',sortable:true},
    	        {field:'vipLevel',title:'VIP级别',width:'8%',align:'center',sortable:true},
    	        {field:'vipMessage',title:'VIP生日信息',width:'20%',align:'center',sortable:true},
    	        {field:'isWran',title:'是否已提醒',width:'10%',align:'center',sortable:true,formatter:formatIsWran},
    	        {field:'operTime',title:'提醒时间',width:'15%',align:'center',sortable:true,formatter:convertDateDetail},
    	    ]];

/**
 * 格式化认证状态
 * @param value 值
 * @param row 行对象
 * @param index 行ID
 */
function formatIsWran(value,row,index){
	if(row.isWran == 1 ){
		return "是";
	}
	return "否";
}

/**
 * 格式化姓名
 * @param value 值
 * @param row 行对象
 * @param index 行ID
 */
function formatCusName(value,row,index){
	if(null != row.sname && row.sname == "" ){
		return str = '<a onclick="openChaKaiKeHu(\''+row.pid+'\')" href="#">'+row.sname+'</a> ';
	}else{
		return "";
	}
}

/**
 * 查看客户资料
 * @param pid 客户ID
 */
function openChaKaiKeHu(pid){
	var path = BASE_PATH+"customerController/viewCustomerDataList.shtml?pid="+pid;
	childLayout_addTab(path,'查看客户资料');
}
