/**
 * 理财产品查询管理列表字段
 */
var financialBorrowSearchManage_Model = [[    
       	        {field:'pid',checkbox:true},
       	        {field:'publishTime',title:'发布时间',width:'10%',align:'center',sortable:true}, 
    	        {field:'borrowCode',title:'编号',width:'10%',align:'center',sortable:true,formatter:formatBorrowCode},    
    	        {field:'borrowName',title:'名称',width:'10%',align:'center',sortable:true},    
    	        {field:'customerName',title:'借款人',width:'10%',align:'center',sortable:true,formatter:formatCus}, 
    	        {field:'sname',title:'借款人名称',width:'10%',align:'center',sortable:true}, 
    	        {field:'borrowMoney',title:'理财金额',width:'10%',align:'right',sortable:true,formatter:common.formatCurrency}, 
    	        {field:'repaymentTypeName',title:'还款方式',width:'10%',align:'center',sortable:true}, 
    	        {field:'borrowRate',title:'年利率（%）',width:'10%',align:'center',sortable:true}, 
    	        {field:'borDeadline',title:'期限（月）',width:'10%',align:'center',sortable:true},
    	        {field:'accrualTypeName',title:'计息类型',width:'10%',align:'center',sortable:true},
    	        {field:'alreadyMoney',title:'完成金额',width:'10%',align:'center',sortable:true},
    	        {field:'borrowProgress',title:'完成进度',width:'10%',align:'center',sortable:true},
    	        {field:'borStatusName',title:'状态',width:'6%',align:'center',sortable:true},
    	        {field:'yy',title:'操作',width:'10%',align:'center',formatter:formatOperation}
    	    ]];

var ser = 0 ;
function formatCus(value,row,index){ 
	 if(row.customerName == null){
		 return "";
	 }
	 	return '<a onclick="openUserPage(\''+row.customerId+ '\')" href="#">'+row.customerName+'</a>'
		
}

//编码格式化
function formatBorrowCode(value,row,index){
	if(row.isTotal == 'yes'){
		return "";
	}
	return '<a onclick="toEdit(\''+row.pid+'\',\''+row.customerId+'\')" href="#">'+row.borrowCode+'</a>';
}

//操作 格式化
function formatOperation(value,row,index){
	if(row.isTotal == 'yes'){
		return "";
	}
	if(row.borStatus==0){
		return '<a onclick="financialBorrowSearch.toDetail(\''+row.pid+'\')" href="#">查看投标详细</a>';
	}else{
		return '<a onclick="financialBorrowSearch.toDetail(\''+row.pid+'\')" href="#">查看投标详细</a> <a onclick="financialBorrowSearch.toRevoke(\''+row.pid+'\')" href="#">撤销</a>';
	}
}

//借款贷前审核查看资料
function viewBorrowInfo(pid){
	var path = BASE_PATH+'bizBorrowController/toBorrowReviewView.shtml?pid='+pid+'&approveNode=4';
	childLayout_addTab(path,"借款信息");
}

//查看理财产品
function toEdit(pid,customerId){
	var view="readView";
	var path = BASE_PATH + "bizBorrowController/openbizBorrowManageAddView.shtml?pageTag=1&customerId="+customerId+"&pid="+pid+"&view="+view;
	childLayout_addTab(path,'查看理财产品');
}


