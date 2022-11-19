/**
 * 借后管理列表字段
 */
var borrowAfterManage_Model = [[    
       	        {field:'pid',checkbox:true},
       	        {field:'rplanid',hidden:true},
       	        {field:'borrowCode',title:'借款编码',width:'10%',align:'center',sortable:true,hidden:true},
    	        {field:'borrowName',title:'借款名称',width:'10%',align:'center',sortable:true,formatter:formatBorrowCode}, 
    	        {field:'cpid',hidden:true,sortable:true},
    	        {field:'customerName',title:'借款人',width:'10%',align:'center',sortable:true,formatter:formatCustomerName}, 
    	        {field:'sname',title:'姓名',width:'10%',align:'center',sortable:true}, 
    	        {field:'availableBalance',title:'可用余额',width:'10%',align:'center',sortable:true,formatter:common.formatCurrency}, 
    	        {field:'phoneNo',title:'手机号码',width:'10%',align:'center',sortable:true},
    	        {field:'borrowMoney',title:'借款金额',width:'10%',align:'right',sortable:true,formatter:common.formatCurrency},
    	        {field:'borrowType',title:'借款类型',width:'10%',align:'center',sortable:true},
    	        {field:'homeTotal',title:'房产总价',width:'10%',align:'center',sortable:true,formatter:common.formatCurrency},
    	        {field:'borrowApr',title:'年利率（%）',width:'10%',align:'center',sortable:true},
    	        {field:'periods',title:'期限（月）',width:'10%',align:'center',sortable:true},
    	        {field:'repaidTime',title:'还款时间',width:'10%',align:'center',sortable:true},
    	        {field:'repaymentTypeVal',title:'还款方式',width:'10%',align:'center',sortable:true},
    	        {field:'capital',title:'本期还款金额',width:'10%',align:'center',sortable:true,formatter:common.formatCurrency},
    	        {field:'receiptPalnStatusVal',title:'状态',width:'10%',align:'center',sortable:true},
    	        {field:'isBlackList',title:'是否黑名单',width:'10%',align:'center',sortable:true,formatter:formatBlackList},
    	        {field:'repPlaDesc',title:'备注',width:'10%',align:'center',sortable:true},
    	        {field:'op',title:'操作',width:'10%',align:'center',formatter:opformatter}
    	    ]];


//编码格式化
function formatBorrowCode(value,row,index){
	var result ="";
	if(row.pid != '1'){
		result = '<a href="javascript:void(0);" onclick="borrowAfterManage.viewBorrowInfo(\''+row.pid+'\')">'+row.borrowName+'</a>';
	}else{
		result = row.borrowName;
	}
	return result;
}

//格式化黑名单
function formatBlackList(value,row,index){
	var result ="";
	if(value == '1'){
		result = '否';
	}
	if(value == '0'){
		result = '是';
	}
	return result;
}


//借款人格式化
function formatCustomerName(value,row,index){
	var result = "";
	if(row.pid != '1'){
		result = '<a onclick="borrowAfterManage.viewCustomerInfo(\''+row.cpid+'\')" href="javascript:void(0);">'+row.customerName+'</a>';
	}
	return result;
}

//备注格式化
function opformatter(value, row, index){
	var desc = row.repPlaDesc == null ? "" : row.repPlaDesc;
	var result = '';
	if(row.pid != '1'){
		result = '<a onclick="borrowAfterManage.toUpdateDescPage(\''+row.pid+'\',\''+ row.rplanid +'\',\''+ desc +'\')" href="javascript:void(0);">填写备注</a>';
	}
		return result;
}