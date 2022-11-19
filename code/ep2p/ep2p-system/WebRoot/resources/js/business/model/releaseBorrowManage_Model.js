/**
 * 发布借款管理列表字段
 */
var releaseBorrowManage_Model = [[    
       	        {field:'pid',checkbox:true},
       	        //{field:'tt',title:'序号',width:'3%',align:'center',sortable:true,formatter:formatSer}, 
    	        {field:'borrowCode',title:'借款编码',width:'10%',align:'center',sortable:true,formatter:formatBorrowCode},    
    	        {field:'borrowName',title:'借款名称',width:'10%',align:'center',sortable:true},    
    	        {field:'customerId',hidden:true,sortable:true},
    	        {field:'cpid',hidden:true,sortable:true},
    	        {field:'customerName',title:'借款人',width:'10%',align:'center',sortable:true,formatter:formatCustomerName}, 
    	        {field:'sname',title:'姓名',width:'10%',align:'center',sortable:true}, 
    	        {field:'phoneNo',title:'手机号码',width:'10%',align:'center',sortable:true},
    	        {field:'borrowTypeName',title:'借款类型',width:'10%',align:'center',sortable:true},
    	        {field:'homeTotal',title:'房产总价',width:'10%',align:'center',sortable:true,formatter:common.formatCurrency},
    	        {field:'borrowMoney',title:'借款金额',width:'10%',align:'right',sortable:true,formatter:common.formatCurrency},
    	        {field:'borDeadline',title:'期限（月）',width:'6%',align:'center',sortable:true},
    	        {field:'publishTime',title:'发布时间',width:'12%',align:'center',sortable:true},
    	        {field:'borrowRate',title:'年化率（%）',width:'10%',align:'center',sortable:true,formatter:formatBorrowRate},
    	        {field:'repaymentTypeName',title:'还款方式',width:'8%',align:'center',sortable:true},
    	        {field:'accrualTypeName',title:'计息方式',width:'8%',align:'center',sortable:true},
    	        {field:'investRewardScale',title:'投资奖励（%）',width:'10%',align:'center',sortable:true},
    	        {field:'borStatusName',title:'状态',width:'6%',align:'center',sortable:true},
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

//编码格式化
function formatBorrowCode(value,row,index){
	if(row.isTotal == 'yes'){
		return "";
	}
	return '<a href="#" onclick="viewBorrowInfo(\''+row.pid+'\')">'+row.borrowCode+'</a>';
}

//年利率格式化
function formatBorrowRate(value,row,index){
	if(row.isTotal == 'yes'){
		return "";
	}
	return row.borrowRate;
}

//借款人格式化
function formatCustomerName(value,row,index){
	if(row.isTotal == 'yes'){
		return "";
	}
	return '<a onclick="viewCustomerInfo(\''+row.cpid+'\')" href="#">'+row.customerName+'</a>';
}

//操作 格式化
function formatOperation(value,row,index){
	if(row.isTotal == 'yes'){
		return "";
	}
	if(row.borStatus==1){
		return '<a onclick="releaseBorrowManage.toRelease(\''+row.pid+'\')" href="#">发布</a> <a href="#" onclick="updateBorrowInfo(\''+row.pid+'\')">修改</a>  <a href="#" onclick="releaseBorrowManage.toRevoke(\''+row.pid+'\')">撤销</a>';
	}else{
		return ' <a href="#" onclick="releaseBorrowManage.toRevoke(\''+row.pid+'\')">撤销</a>';
	}
}

//查看资料
function viewBorrowInfo(pid){
	var path = BASE_PATH+'bizBorrowController/toBorrowReviewView.shtml?pid='+pid+'&view=yes';
	childLayout_addTab(path,"借款信息查看");
}

//修改借款资料
function updateBorrowInfo(pid){
	var path = BASE_PATH+'bizBorrowController/toBorrowReviewView.shtml?pid='+pid;
	childLayout_addTab(path,"借款信息修改");
}

//借款发布查看借款人资料
function viewCustomerInfo(pid){
	var path = BASE_PATH + 'customerController/viewCustomerDataList.shtml?pid='+pid;
	childLayout_addTab(path,"查看借款人资料");
}


