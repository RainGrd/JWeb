/**
 * 借款客户列表
 */
var borrowCusTomeManager_Model = [[    
    	        {field:'customerName',title:'客户名',width:'10%',align:'center',sortable:true},    
    	        {field:'sname',title:'姓名',width:'20%',align:'center',sortable:true},    
    	        {field:'phoneNo',title:'手机号码',width:'10%',align:'center',sortable:true}, 
    	        {field:'yy',title:'操作',width:'30%',align:'center',formatter:formatOperation}
    	    ]];

//操作 格式化
function formatOperation(value,row,index){
	var result = '<a onclick="selectBorrowType(\''+row.pid+'\')" href="#">新增借款</a>';
	return result;
}

/**
 * 打开选择借款类型窗口
 * @param curId
 */
function selectBorrowType(curId){
	$("#addBorrow").dialog("open");
	$("#customerId").val(curId);
}
