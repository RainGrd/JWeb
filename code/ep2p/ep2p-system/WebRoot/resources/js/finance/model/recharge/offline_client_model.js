/**
 * 线下充值客户列表Model
 */
var offline_index_model = [[    
    {field:'roleCode',title:'客户名称',width:'20%',align:'center',formatter:formatOperation},    
    {field:'roleName',title:'真实姓名',width:'20%',align:'center',sortable:true}, 
    {field:'parentRoleName',title:'手机号码',width:'20%',align:'center',sortable:true}, 
    {field:'operation',title:'操作',width:'20%',align:'center',formatter:formatOperation}
]];

/**
 * 操作 格式化
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function formatOperation(value,row,index){
	var strBut = ""
	var pid = row.pid;
	var roleName= row.roleName;
	var roleCode = row.roleCode;
	strBut += '<a onclick="openUserAssign(\''+roleName+'\',\''+roleCode+'\',\''+pid+'\')" href="#">充值</a> | ';
	return strBut;
}

/**
 * 客户详情
 * @param value
 * @param row
 * @param index
 */
function clientDetails(value,row,index){
	var strBut = ""
		var pid = row.pid;
		var roleName= row.roleName;
		var roleCode = row.roleCode;
		strBut += '<a onclick="openUserAssign(\''+roleName+'\',\''+roleCode+'\',\''+pid+'\')" href="#">'+value+'</a> | ';
		return strBut;
}