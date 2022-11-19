var ensure_detail_model = [[    
                {field:'pid',checkbox:true},
    	        {field:'catorTime',title:'日期',width:'15%',align:'center',sortable:true},    
    	        {field:'customerName',title:'客户名',width:'10%',align:'center',sortable:true},    
    	        {field:'sname',title:'姓名',width:'10%',align:'center',sortable:true},    
    	        {field:'phoneNo',title:'手机号码',width:'10%',align:'center',sortable:true},    
    	        {field:'income',title:'收入',width:'10%',align:'center',sortable:true,formatter:formatIncome},    
    	        {field:'expenditures',title:'支出',width:'10%',align:'center',sortable:true,formatter:formatExpenditures},    
    	        {field:'balance',title:'备付金余额',width:'10%',align:'right',sortable:true,formatter:formatBalanceValue},    
    	        {field:'ensMonDetTypeName',title:'类型',width:'10%',align:'center',sortable:true},    
    	        {field:'actorUserName',title:'处理人',width:'10%',align:'center',sortable:true},    
    	        {field:'ensMonDetDesc',title:'备注',width:'20%',align:'center',sortable:true},  
    	    ]];


/**
 * 收入列格式化
 * @param value
 * @param row
 * @param index
 * @returns
 */
function formatIncome(value,row,index){
	if(row.feeType == 1){
		return "<font color='#A7C333'>"+common.formatCurrency(row.happenValue)+"</font>";
	}else if (row.feeType == 3){
		return common.formatCurrency(row.sumIncome);
	}
	return "";
}

/**
 * 支出列格式化
 * @param value
 * @param row
 * @param index
 * @returns
 */
function formatExpenditures(value,row,index){
	if(row.feeType == 2){
		return "<font color='#F5A828'>-"+common.formatCurrency(row.happenValue)+"</font>";
	}else if (row.feeType == 3){
		return common.formatCurrency(row.sumExpenditures);
	}
	return "";
}

/**
 * 备付金余额格式化
 * @param value
 * @param row
 * @param index
 * @returns
 */
function formatBalanceValue(value,row,index){
	if (row.feeType == 3){
		return "";
	}
	if(value >= 0 ){
		return common.formatCurrency(value) == ""?"0.00":common.formatCurrency(value);
	}else{
		return "<font color='#FF2717'>"+common.formatCurrency(value)+"</font>";
	}
}
