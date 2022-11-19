/**
 * 债权转让model
 * 
 */
var invest_manage_model = [[   
    {field:'pid',checkbox:true},
    {field:'borrowId',hidden:true},
    {field:'transferCode',title:'债权编号',width:'10%',align:'center',sortable:true},    
    {field:'borrowName',title:'借款名称',width:'10%',align:'center',sortable:true},    
    {field:'timeRemaining',title:'剩余/总期次',width:'8%',align:'center',sortable:true,formatter:formatPlanIndex},    
    {field:'createTime',title:'发布时间',width:'10%',align:'center',sortable:true},    
    {field:'profitRate',title:'年化率',width:'5%',align:'center',sortable:true,formatter:formatProfitRate}, 
    {field:'projectValue',title:'项目价值',width:'6%',align:'center',sortable:true,formatter:common.formatCurrency},
    {field:'successAmount',title:'转让价格',width:'6%',align:'center',sortable:true,formatter:common.formatCurrency},
    {field:'createUserName',title:'转让人',width:'6%',align:'center',sortable:true,formatter:formatCreateUserName},
    {field:'customerName',title:'购买人',width:'6%',align:'center',sortable:true,formatter:formatCustomerName},
    {field:'successTime',title:'购买时间',width:'6%',align:'center',sortable:true},
    {field:'statusName',title:'状态',width:'5%',align:'center',sortable:true},
    {field:'recTraDesc',title:'备注',width:'22%',align:'center',sortable:true,formatter:des}
]];


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
// 期次格式化
function formatPlanIndex(value,row,index){
	if(row.borrowCode == null || row.borrowCode == ''){
		return '';
	}
	return row.timeRemaining + "/" + row.deadline;
}
//编码格式化
function formatBorrowCode(value,row,index){
	if(row.borrowCode == null || row.borrowCode == ''){
		return "总计";
	}
	return '<a href="#" onclick="viewBorrowInfo(\''+row.borrowId+'\')">'+row.borrowCode+'</a>';
}

//转让人格式化
function formatCreateUserName(value,row,index){
	if(row.createUser == null || row.createUser==""){
		return "";
	}
	return '<a onclick="viewCustomerInfo(\''+row.createUser+'\')" href="#">'+row.createUserName+'</a>';
}
//购买人格式化
function formatCustomerName(value,row,index){
	if(row.customerName == null || row.customerName==""){
		return "";
	}
	return '<a onclick="viewCustomerInfo(\''+row.customerId+'\')" href="#">'+row.customerName+'</a>';
}

function des(value,row,index){
	if(row.borrowCode == null || row.borrowCode == ''){
		return '';
	}
	
	var pv = common.formatCurrency(row.projectValue);
	var rp = common.formatCurrency(row.residualPrincipal);
	var fee = common.formatCurrency(row.fee);
	var it = common.formatCurrency(row.interestToday);
	return "债权总价"+pv+"元(剩余本金"+rp+"元，<br>当期至今利息"+it+"元)转让手续费"+fee+"元";
	
}


//操作 格式化
function formatOperation(value,row,index){
	if(row.statusName == '转让中'){
		return '<a href="#" onclick="InvestManage.toRevoke(\''+row.pid+'\')">撤销</a>';
	}
}
//操作 格式化
function formatProfitRate(value,row,index){
	if(row.profitRate == null){
		return '';
	}
	return (parseFloat(row.profitRate)*100).toFixed(2) + "%";
}


