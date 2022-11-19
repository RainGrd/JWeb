/**
 * 实名认证列表字段
 */
var custAuthenticationHis_Model = [[    
    	        {field:'customerName',title:'用户名',width:'10%',align:'center',sortable:true},    
    	        {field:'sname',title:'姓名',width:'10%',align:'center',sortable:true},    
    	        {field:'phoneNo',title:'手机号码',width:'10%',align:'center',sortable:true}, 
    	        {field:'registrationTime',title:'注册时间',width:'15%',align:'center',sortable:true,formatter:convertDateDetail},
    	        {field:'attestationStatus',title:'认证状态',width:'10%',align:'center',sortable:true,formatter:formatAttestationStatus},
    	        {field:'custDesc',title:'备注',width:'20%',align:'center',sortable:true},
    	        {field:'cz',title:'操作',width:'15%',align:'center',sortable:true,formatter:formatCaoZuo},
    	    ]];

/**
 * 格式化认证状态
 * @param value 值
 * @param row 行对象
 * @param index 行ID
 */
function formatAttestationStatus(value,row,index){
	if(row.attestationStatus == 1 ){
		return "审核中";
	}else if(row.attestationStatus == 2 ){
		return "已同意";
	}else if(row.attestationStatus == 3 ){
		return "已拒绝";
	}
	return "-";
}

/**
 * 格式化操作
 * @param value 值
 * @param row 行对象
 * @param index 行ID
 */
function formatCaoZuo(value,row,index){
	var str = '<a onclick="openChaKaiKeHu(\''+row.pid+'\')" href="#">查看客户资料</a> ';
	if(row.attestationStatus != 2){
		str += ' | ';
		var path = BASE_PATH + "authenVipCustomerController/openCustAuthenticationHisEdit.shtml?pid="+row.pid;
		var title = "实名认证审核";
		str += '<a onclick="childLayout_addTab(\''+path+'\',\''+title+'\')" href="#">审核</a>';
	}
	return str;
}

function openAuthenti(pid){
	var path = BASE_PATH+"customerController/customerHistoryView.shtml?pid="+pid;
	childLayout_addTab(path,'客服历史查看');
}

/**
 * 查看客户资料
 * @param pid 客户ID
 */
function openChaKaiKeHu(pid){
	var path = BASE_PATH+"customerController/viewCustomerDataList.shtml?pid="+pid;
	childLayout_addTab(path,'查看客户资料');
}
