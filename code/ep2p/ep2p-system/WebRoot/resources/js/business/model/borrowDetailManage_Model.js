
// 期限单位格式化
var value = $("#borrowType").val();
var title = "期限（月）";
if(value == 4 || value == 5){
	title = "期限（天）";
}

/**
 * 投标记录表字段
 */
var borrowDetailManage_Model = [[    
       	        {field:'pid',checkbox:true},
    	        {field:'investmentTime',title:'投标时间',align:'center',sortable:true},    
    	        {field:'interestTime',title:'计息时间',align:'center',sortable:true},    
    	        {field:'customerName',title:'投标人',align:'center',sortable:true,formatter:formatCustName}, 
    	        {field:'sname',title:'姓名',align:'center',sortable:true,formatter:formatSName}, 
    	        {field:'phoneNo',title:'手机号码',align:'center',sortable:true,formatter:formatPhoneNo},
    	        {field:'investmentAmount',title:'投标金额',align:'right',sortable:true,formatter:common.formatCurrency},
    	        {field:'borDeadline',title:title,align:'center',sortable:true,formatter:formatBorDeadline},
    	        {field:'awardAmount',title:'奖励',align:'center',sortable:true},
    	        {field:'interest',title:'利息',align:'center',sortable:true},
    	        {field:'remark',title:'备注',align:'center',sortable:true}
    	    ]];

var ser = 0 ;

//投标人格式化
function formatCustName(value,row,index){
	if(row.customer){
		return row.customer.customerName;
	}
	return '';
}

//姓名格式化
function formatSName(value,row,index){
	if(row.customer){
		return row.customer.sname;
	}
	return '';
}

//手机号码格式化
function formatPhoneNo(value,row,index){
	if(row.customer){
		return row.customer.phoneNo;
	}
	return '';
}

//借款期限格式化
function formatBorDeadline(value,row,index){
	if(row.bizBorrow){
		return row.bizBorrow.borDeadline;
	}
	return '';
}

//编码格式化
function formatBorrowCode(value,row,index){
	return '<a onclick="viewBorrowInfo(\''+row.pid+'\')" href="#">'+row.borrowCode+'</a>';
}


//期限单位格式化
function formatOperation(value,row,index){
	if(row.borStatus==1){
		return '<a onclick="releaseBorrowManage.toRelease(\''+row.pid+'\')" href="#">发布</a> <a href="#" onclick="viewBorrowInfo(\''+row.pid+'\')">修改</a>';
	}else{
		return ' <a href="#" onclick="viewBorrowInfo(\''+row.pid+'\')">修改</a>  <a href="#" onclick="releaseBorrowManage.toRevoke(\''+row.pid+'\')">撤销</a>';
	}
}

//借款贷前审核查看资料
function viewBorrowInfo(pid){
	var path = BASE_PATH+'bizBorrowController/toBorrowReviewView.shtml?pid='+pid+'&approveNode=4';
	childLayout_addTab(path,"借款信息");
}



