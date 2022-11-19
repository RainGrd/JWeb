/**
 *系统日志管理列表字段
 */
var logManager_model = [[    
       	        {field:'pid',checkbox:true},
    	        {field:'name',title:'真实姓名',width:'10%',align:'center',sortable:true},    
    	        {field:'accountNo',title:'用户账号',width:'10%',align:'center',sortable:true},    
    	        {field:'operType',title:'操作类型',width:'10%',align:'center',sortable:true}, 
    	        {field:'loginIp',title:'登录IP',width:'10%',align:'center',sortable:true},    
    	        {field:'createDate',title:'创建日期',width:'20%',align:'center',sortable:true,formatter:convertDateDetail}, 
    	        {field:'operContent',title:'操作内容',width:'10%',align:'center',sortable:true},
    	        {field:'status',title:'结果类型',width:'10%',align:'center',sortable:true}
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

