/**
 * 实名认证列表字段
 */
var custAuthenticationHisAuditing_Model = [[    
    	        {field:'status',title:'操作类别',width:'10%',align:'center',sortable:true,formatter:formatStatus}, 
    	        {field:'autHisDesc',title:'操作说明',width:'20%',align:'center',sortable:true},   
    	        {field:'operTime',title:'操作时间',width:'10%',align:'center',sortable:true,formatter:convertDateDetail},    
    	        {field:'createUserName',title:'操作人',width:'10%',align:'center',sortable:true}
    	    ]];

/**
 * 格式化认证状态
 * @param value 值
 * @param row 行对象
 * @param index 行ID
 */
function formatStatus(value,row,index){
	if(row.status == 1 ){
		return "同意";
	}else if(row.status == 2 ){
		return "拒绝";
	}
	return "-";
}

