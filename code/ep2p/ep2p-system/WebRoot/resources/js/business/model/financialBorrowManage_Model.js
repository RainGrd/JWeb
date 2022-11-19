/**
 * 理财产品管理列表字段
 */
var financialBorrowManage_Model = [[    
       	        {field:'pid',checkbox:true},
       	        {field:'publishTime',title:'预发布时间',width:'10%',align:'center',sortable:true},    
    	        {field:'borrowCode',title:'编号',width:'10%',align:'center',sortable:true},    
    	        {field:'borrowName',title:'名称',width:'10%',align:'center',sortable:true},    
    	        {field:'customerName',title:'借款人',width:'10%',align:'center',sortable:true,formatter:formatCus}, 
    	        {field:'sname',title:'借款人名称',width:'10%',align:'center',sortable:true}, 
    	        {field:'borrowMoney',title:'理财金额',width:'10%',align:'center',sortable:true,formatter:common.formatCurrency}, 
    	        {field:'borrowRate',title:'年化率（%）',width:'10%',align:'center',sortable:true}, 
    	        {field:'borDeadline',title:'期限（月）',width:'10%',align:'center',sortable:true},
    	        {field:'startMoney',title:'起投金额',width:'10%',align:'right',sortable:true,formatter:common.formatCurrency},
    	        {field:'endMoney',title:'投资上限',width:'10%',align:'right',sortable:true,formatter:common.formatCurrency},
    	        {field:'accrualTypeName',title:'计息类型',width:'10%',align:'center',sortable:true}, 
    	        {field:'repaymentTypeName',title:'还款方式',width:'10%',align:'center',sortable:true}, 
    	        {field:'investRewardScale',title:'投资奖励（%）',width:'10%',align:'center',sortable:true}, 
    	        {field:'borStatusName',title:'状态',width:'6%',align:'center',sortable:true},
    	        {field:'yy',title:'操作',width:'10%',align:'center',formatter:formatOperation}
    	    ]];



 var financialCusTomeManager_Model = [[    
                          	        {field:'customerName',title:'客户名',width:'10%',align:'center',sortable:true,formatter:formatCus},    
                        	        {field:'sname',title:'姓名',width:'20%',align:'center',sortable:true},    
                        	        {field:'phoneNo',title:'手机号码',width:'10%',align:'center',sortable:true}, 
                        	        {field:'yy',title:'操作',width:'30%',align:'center',formatter:formatOperationCus}
                        	    ]];
 
 function formatOperationCus(value,row,index){
	 	if(row.pid == null){
	 		return "";
	 	}
	 	if("newStandard" == $("#type").val()){
	 		var result = '<a onclick="selectNewStandard(\''+row.pid+'\')" href="#">新增新手标</a>';
	 	}else{
	 		var result = '<a onclick="selectBorrowType(\''+row.pid+'\')" href="#">新增理财产品</a>';
	 	}
		return result;
	 	
 } 
 
 function formatterStatusName(value,row,index){
	 if(row.pid == null){
		 return "";
	 }
	 return "待招标";
 }

 function formatCus(value,row,index){ 
	 if(row.pid == null){
		 return "";
	 }
	 if(row.customerName == null){
		 return '<a onclick="openUserPage(\''+row.customerId+ '\')" href="#">'+row.phoneNo+'</a>' ;
	 }
	 	return '<a onclick="openUserPage(\''+row.customerId+ '\')" href="#">'+row.customerName+'</a>';
		
}  
function openUserPage(customerId){
	var path = BASE_PATH+"customerController/viewCustomerDataList.shtml?pid="+customerId;
	childLayout_addTab(path,'查看客户资料');
}
 function selectBorrowType(curId){
	// 跳转到新增页面
		var path = BASE_PATH + "bizBorrowController/openbizBorrowManageAddPage.shtml?pageTag=1&customerId="+curId;
		childLayout_addTab(path,'理财产品管理新增\修改');
	 
 }
 
 function selectNewStandard(curId){
	// 跳转到新增页面
	 var path = BASE_PATH + "bizBorrowController/newStandardAdd.shtml?pid=-1&paramsKey=SYSTEM_USER&type=1&customerId="+curId;
	 
		childLayout_addTab(path,'新增新手标');
		
		
	 
 }

//编码格式化
function formatBorrowCode(value,row,index){
	if(row.pid == null){
		return "";
	}
	return '<a onclick="viewBorrowInfo(\''+row.pid+'\')" href="#">'+row.borrowCode+'</a>';
}

//操作 格式化
function formatOperation(value,row,index){
	if(row.pid == null ){
		return "";
	}
	var str =  '<a onclick="financialBorrowManage.toPublishPage(\''+row.pid+'\')" href="#">发布</a>&nbsp;&nbsp;&nbsp;&nbsp;'+
	'<a onclick="financialBorrowManage.toEdit(\''+row.pid+'\')" href="#">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;'+ 
	'<a onclick="financialBorrowManage.toDelete(\''+row.pid+'\')" href="#">删除</a>';
	return str;
}  


//借款贷前审核查看资料
function viewBorrowInfo(pid){
	var path = BASE_PATH+'bizBorrowController/toBorrowReviewView.shtml?pid='+pid+'&approveNode=4';
	childLayout_addTab(path,"借款信息");
}

