/**
 * 发布借款管理列表字段
 */
var newStandard_model = [[    
       	        //{field:'tt',title:'序号',width:'3%',align:'center',sortable:true,formatter:formatSer}, 
    	        {field:'borrowCode',title:'编号',width:'10%',align:'center',sortable:true,formatter:formatBorrowCode},    
    	        {field:'borrowName',title:'名称',width:'10%',align:'center',sortable:true}, 
    	        {field:'customerId',hidden:true,sortable:true},
    	        {field:'customerName',title:'借款人',width:'10%',align:'center',sortable:true,formatter:formatCustomerName}, 
    	        {field:'sname',title:'借款人<br>名称',width:'6%',align:'center',sortable:true}, 
    	        {field:'borrowTypeName',title:'类型',width:'6%',align:'center',sortable:true},
    	        {field:'borrowMoney',title:'标的金额',width:'6%',align:'center',sortable:true,formatter:common.formatCurrency},
    	        {field:'repaymentTypeName',title:'还款方式',width:'6%',align:'center',sortable:true},
    	        {field:'borrowRate',title:'年化率',width:'6%',align:'center',sortable:true,formatter:yearRate},
    	        {field:'borDeadline',title:'期限',width:'6%',align:'center',sortable:true,formatter:deadline},
    	        {field:'accrualTypeName',title:'计息类型',width:'10%',align:'center',sortable:true},
    	        {field:'borStatusName',title:'状态',width:'6%',align:'center',sortable:true},
    	        {field:'publishTime',title:'预发布时间',width:'10%',align:'center',sortable:true},
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

function yearRate(value,row,index){
	if(row.borrowRate > 0){
		
		return (row.borrowRate * 100).toFixed(2) + "%";
		
	}else{
		
		return "";
	}
	
}
function deadline(value,row,index){
	if(row.borDeadline != null){
		return row.borDeadline + "天";
	}else{
		return "";
	}
	
}

//编码格式化
function formatBorrowCode(value,row,index){
	if(row.borrowCode==null || row.borrowCode==""){
		return "总计";
	}
	return row.borrowCode;
}


//借款人格式化
function formatCustomerName(value,row,index){
	if(row.customerName==null || row.customerName == ""){
		return "";
	}
	return '<a onclick="viewCustomerInfo(\''+row.customerId+'\')" href="#">'+row.customerName+'</a>';
}


//操作 格式化
function formatOperation(value,row,index){
	if(row.pid == null || row.pid==""){
		return "";
	}
	return '<a onclick="newStandard.toRelease(\''+row.pid+'\')" href="#">发布</a> <a href="#" onclick="newStandard.edit(\''+row.pid+'\',\'no\')">编辑</a> <a onclick="newStandard.remove(\''+row.pid+'\')" href="javascript:void(0);">删除</a>';
}



