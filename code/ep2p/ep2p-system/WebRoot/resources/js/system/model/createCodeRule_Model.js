/**
 * 编号管理列表字段
 */
var createCodeRule_model = [[    
       	        {field:'pid',checkbox:true},
    	        {field:'rulePrefix',title:'前缀',width:'10%',align:'center',sortable:true},    
    	        {field:'ruleType',title:'编号类型',width:'10%',align:'center',sortable:true,formatter:formatRuleType},    
    	        {field:'ruleYear',title:'年',width:'5%',align:'center',sortable:true},
    	        {field:'ruleMonth',title:'月',width:'5%',align:'center',sortable:true},    
    	        {field:'ruleDay',title:'日',width:'5%',align:'center',sortable:true},    
    	        {field:'ruleOrder',title:'序号',width:'8%',align:'center',sortable:true},
    	        {field:'status',title:'状态',width:'8%',align:'center',sortable:true,formatter:formatStatus}, 
    	        {field:'ruleDesc',title:'描述',width:'20%',align:'center',sortable:true},    
    	        {field:'createUser',title:'创建人',width:'10%',align:'center',sortable:true},    
    	        {field:'createTime',title:'创建时间',width:'15%',align:'center',sortable:true,formatter:convertDate},    
    	    ]];


/**
 * 格式化状态
 * @param value 值
 * @param row 行对象
 * @param index 行ID
 */
function formatStatus(value,row,index){
	if(row.status == 1){
		return "正常";
	}
	return "已删除";
}

/**
 * 格式化编号类型
 * @param value 值
 * @param row 行对象
 * @param index 行ID
 */
function formatRuleType(value,row,index){
	if(row.ruleType == 1){
		return "系统管理";
	}else if(row.ruleType == 2){
		return "财务管理";
	}else if(row.ruleType == 3){
		return "统计管理";
	}else if(row.ruleType == 4){
		return "论坛管理";
	}else if(row.ruleType == 5){
		return "内容管理";
	}else if(row.ruleType == 6){
		return "电台管理";
	}else if(row.ruleType == 7){
		return "活动管理";
	}else if(row.ruleType == 8){
		return "业务管理";
	}else if(row.ruleType == 9){
		return "客户管理";
	}else {
		return "其他";
	}
}