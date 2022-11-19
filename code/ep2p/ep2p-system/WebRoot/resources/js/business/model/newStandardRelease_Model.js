/**
 * 发布新手标体验标管理列表字段
 */
var newStandardRelease_model = [[    
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
    	        {field:'alreadyMoney',title:'完成金额',width:'6%',align:'right',sortable:true,formatter:common.formatCurrency},
    	        {field:'borrowProgress',title:'完成进度',width:'10%',align:'right',sortable:true,formatter:formatProgress},
    	        {field:'accrualTypeName',title:'计息类型',width:'10%',align:'center',sortable:true},
    	        {field:'borStatusName',title:'状态',width:'6%',align:'center',sortable:true},
    	        {field:'publishTime',title:'发布时间',width:'10%',align:'center',sortable:true},
    	        {field:'yy',title:'操作',width:'10%',align:'center',formatter:formatOperation}
    	    ]];

var ser = 0 ;


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
	if(row.borrowCode==null || row.borrowCode==""){
		return "总计";
	}
	return '<a href="#" onclick="newStandard.edit(\''+row.pid+'\',\'yes\')">'+row.borrowCode+'</a>' ;
}

//借款人格式化
function formatCustomerName(value,row,index){
	if(row.customerName == null  || row.customerName == ""){
		return "";
	}
	return '<a onclick="viewCustomerInfo(\''+row.customerId+'\')" href="#">'+row.customerName+'</a>';
}

function formatProgress(value,row,index){
	if(row.borrowProgress!=null && row.borrowProgress!=''){
		return (row.borrowProgress*100).toFixed(2)+"%";
	}else{
		return "";
	}
}

//操作 格式化
function formatOperation(value,row,index){
	if(row.pid == null || row.pid==""){
		return "";
	}
	var html =  '<a onclick="newStandard.toDetail(\''+row.pid+'\')" href="#">查看投资详细</a>';
	if(row.borStatusName != '已撤销'){
		html += ' <a href="javascript:void(0)" onclick="newStandard.toRevoke(\''+row.pid+'\') ">撤销</a>';
	}
	return html;
}

