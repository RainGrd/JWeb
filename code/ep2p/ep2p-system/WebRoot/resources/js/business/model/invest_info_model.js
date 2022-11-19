/**
 * 投资管理
 */
var invest_info_model = [[    
    {field:'pid',checkbox:true},
    {field:'borrowName',title:'借款名称',width:'10%',align:'center',sortable:true,formatter:getBorrowName},    
    {field:'borrowCode',title:'借款编号',width:'10%',align:'center',sortable:true,formatter:getBorrowCode},    
    {field:'customerName',title:'投资人',width:'10%',align:'center',sortable:true,formatter:getCustomerName},    
    {field:'issue',title:'期次',width:'10%',align:'center',sortable:true}, 
    {field:'expireTime',title:'待收时间',width:'12%',align:'center',sortable:true},
    {field:'repaymentTypeName',title:'还款方式',width:'12%',align:'center',sortable:true},
    {field:'benXi',title:'待收本息',width:'10%',align:'right',sortable:true,formatter:common.formatCurrency},
    {field:'receiptStatusName',title:'状态',width:'5%',align:'center',sortable:true},
    {field:'des',title:'备注',width:'30%',align:'center',sortable:true,formatter:getDes}
]];

/**
 * 获取借款编码
 * @param value 当前列值
 * @param row 行对象
 * @param i 行数
 */
function getBorrowCode(value,row,i){
	if(row.borrowCode == null || row.borrowCode == ''){
		return '';
	}
	//编码格式化
	return '<a href="#" onclick="viewBorrowInfo(\''+row.borrowId+'\')">'+row.borrowCode+'</a>';
}

function getPid(value,row,i){
	return row.brpId;
}
function getDes(value,row,i){
	if(row.des == null || row.des == ''){
		return '';
	}
	//编码格式化
	var des = row.des;
	if(row.transferId != null && row.transferId != ''){
		var url = ep2pUrl +'/mybids/transferController/index/toTransferInfo.shtml?pid='+row.transferId;
		if(row.receiptStatus == '3' || row.receiptStatus == '4'){
			des = '债权编号：<a href="'+url+'" target="_blank" >'+row.transferCode+'</a><br>' + des;
		}
	}
	return des
}

function viewBorrowInfo(pid){
	var path = BASE_PATH+'bizBorrowController/toBorrowReviewView.shtml?pid='+pid+'&view=yes';
	childLayout_addTab(path,"借款信息查看");
}
function getCustomerName(value,row,i){
	if(row.customerName == null || row.customerName == ''){
		if(row.phoneNo == null || row.phoneNo == ''){
			return '';
		}
		return '<a onclick="viewCustomerInfo(\''+row.customerId+'\')" href="javascript:void(0);">'+row.phoneNo+'</a>';
	}
	return '<a onclick="viewCustomerInfo(\''+row.customerId+'\')" href="javascript:void(0);">'+row.customerName+'</a>';
}
//借款发布查看借款人资料
function viewCustomerInfo(pid){
	var path = BASE_PATH + 'customerController/viewCustomerDataList.shtml?pid='+pid;
	childLayout_addTab(path,"查看投资人资料");
}

/**
 * 获取借款名称
 * @param v
 * @param row
 * @param i
 */
function getBorrowName(v,row,i){
	return '<a href="#" onclick="viewBorrowInfo(\''+row.borrowId+'\')">'+row.borrowName+'</a>';
}

/**
 * 获取 期限（月）
 * @param v
 * @param row
 * @param i
 */
function getBorDeadline(v,row,i){
	if(row.bizBorrow){
		return row.bizBorrow.borDeadline
	}
	return "";
}



