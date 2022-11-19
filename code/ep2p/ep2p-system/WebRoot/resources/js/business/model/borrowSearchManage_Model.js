/**
 * 借款查询管理列表字段
 */
var borrowSearchManage_Model = [[    
       	        {field:'pid',checkbox:true},
    	        {field:'borrowCode',title:'借款编码',width:'10%',align:'center',sortable:true,formatter:formatBorrowCode},    
    	        {field:'borrowName',title:'借款名称',width:'10%',align:'center',sortable:true},    
    	        {field:'customerId',hidden:true,sortable:true},
    	        {field:'customerName',title:'借款人',width:'10%',align:'center',sortable:true,formatter:formatCustomerName}, 
    	        {field:'sname',title:'姓名',width:'10%',align:'center',sortable:true}, 
    	        {field:'phoneNo',title:'手机号码',width:'10%',align:'center',sortable:true},
    	        {field:'borrowTypeName',title:'借款类型',width:'10%',align:'center',sortable:true},
    	        {field:'homeTotal',title:'房产总价',width:'10%',align:'center',sortable:true,formatter:common.formatCurrency},
    	        {field:'borrowMoney',title:'借款金额',width:'10%',align:'right',sortable:true,formatter:common.formatCurrency},
    	        {field:'borDeadline',title:'期限（月）',width:'6%',align:'center',sortable:true},
    	        {field:'publishTime',title:'发布时间',width:'12%',align:'center',sortable:true},
    	        {field:'borrowRate',title:'年化率（%）',width:'8%',align:'center',sortable:true,formatter:formatBorrowRate},
    	        {field:'repaymentTypeName',title:'还款方式',width:'8%',align:'center',sortable:true},
    	        {field:'accrualTypeName',title:'计息方式',width:'8%',align:'center',sortable:true},
    	        {field:'investRewardScale',title:'投资奖励（%）',width:'10%',align:'center',sortable:true},
    	        {field:'borStatusName',title:'状态',width:'6%',align:'center',sortable:true,formatter:formatBorrowStatus},
    	        {field:'alreadyMoney',title:'已投标金额',width:'10%',align:'center',sortable:true,formatter:common.formatCurrency},
    	        {field:'borrowProgress',title:'完成进度（%）',width:'8%',align:'center',sortable:true},
    	        {field:'yy',title:'操作',width:'10%',align:'center',formatter:formatOperation}
    	    ]];

var ser = 0 ;


//编码格式化
function formatBorrowCode(value,row,index){
	if(row.isTotal == 'yes'){
		return "";
	}else if(row.borrowType == '1' || row.borrowType == '2' ){
		return '<a href="#" onclick="viewBorrowInfo(\''+row.pid+'\')">'+row.borrowCode+'</a>';
	}else if(row.borrowType == '3'){
		return '<a href="#" onclick="toEdit(\''+row.pid+'\')">'+row.borrowCode+'</a>';
	}else if(row.borrowType == '4' || row.borrowType == '5'){
		return '<a href="#" onclick="edit(\''+row.pid+'\',\'yes\')">'+row.borrowCode+'</a>' ;
	}
}

//借款人格式化
function formatCustomerName(value,row,index){
	if(row.isTotal == 'yes'){
		return "";
	}
	return '<a onclick="viewCustomerInfo(\''+row.customerId+'\')" href="#">'+row.customerName+'</a>';
}

//操作 格式化
function formatOperation(value,row,index){
	if(row.isTotal == 'yes'){
		return "";
	}
	return '<a onclick="borrowSearch.toDetail(\''+row.pid+'\')" href="#">查看投标详细</a>';
}

//完成进度 格式化
function formatProgress(value,row,index){
	if(row.isTotal == 'yes'){
		return "";
	}
	return row.borrowProgress+'%';
}

//年利率格式化
function formatBorrowRate(value,row,index){
	return row.borrowRate;
}

function formatBorrowStatus(value,row,index){
	if(row.borStatusName == null){
		return row.approveStatusName;
	}
	return row.borStatusName;
}

//借款贷前审核查看资料
function viewBorrowInfo(pid){
	var path = BASE_PATH+'bizBorrowController/toBorrowReviewView.shtml?pid='+pid+'&view=yes';
	childLayout_addTab(path,"借款信息查看");
}

//借款发布查看借款人资料
function viewCustomerInfo(pid){
	var path = BASE_PATH + 'customerController/viewCustomerDataList.shtml?pid='+pid;
	childLayout_addTab(path,"查看借款人资料");
}

//新手体验标资料查看
function edit(pid,view){
	var path = BASE_PATH + "bizBorrowController/newStandardAdd.shtml?pid="+pid+"&view="+view;
	childLayout_addTab(path,'查看新手标体验标');
}

//查看理财产品
function toEdit(pid,customerId){
	var view="readView";
	var path = BASE_PATH + "bizBorrowController/openbizBorrowManageAddView.shtml?pageTag=1&customerId="+customerId+"&pid="+pid+"&view="+view;
	childLayout_addTab(path,'查看理财产品');
}

