/**
 *角色管理列表字段
 */
var role_index_model = [[    
    {field:'pid',checkbox:true},
    {field:'roleCode',title:'角色编码',width:'10%',align:'center',sortable:true},    
    {field:'roleName',title:'角色名称',width:'20%',align:'center',sortable:true}, 
    {field:'parentRoleName',title:'父角色',width:'20%',align:'center',sortable:true}, 
    /*{field:'status',title:'状态',width:'10%',align:'center',sortable:true,formatter:formatIsUpdate},   */ 
    {field:'operation',title:'操作',width:'25%',align:'center',formatter:formatOperation}
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
	strBut += '<a onclick="openUserAssign(\''+roleName+'\',\''+roleCode+'\',\''+pid+'\')" href="#">用户分配</a> | ';
	strBut += '<a onclick="openPermissionAssign(\''+roleName+'\',\''+roleCode+'\',\''+pid+'\')" href="#">权限分配</a> | ';
	strBut += '<a onclick="update(\''+pid+'\')" href="#">修改</a>';
	return strBut;
}
/**
 * 格式化状态
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function formatIsUpdate(value,row,index){
	if(row.isUpdate == 0){
		return "禁用";
	}
	return "启用";
}

/**
 * 用户分配
 * @param row 
 */
function openUserAssign(roleName,roleCode,roleId){
	var url = BASE_PATH + "sysRoleController/openUserAssign.shtml?roleName="+roleName+"&roleCode="+roleCode+"&roleId="+roleId;
	//因为Tomcat服务器会自动帮你做一次URLDecode，所以再加上你自己在代码里面写的URLDecode 所以要做两边encode
	url = encodeURI(encodeURI(url));
	$("<div id='role_user_assign_dialog' /> ").dialog({
		href:url,
		title:"角色-用户分配",
		method:'post',
		width:'800px',
		height:'470px',
		modal: true,
		onClose : function() {
				$(this).dialog('destroy');
			}
	});
}

/**
 * 权限分配
 * @param row
 */
function openPermissionAssign(roleName,roleCode,roleId){
	var url = BASE_PATH + "sysRoleController/openPermissionAssign.shtml?roleName="+roleName+"&roleCode="+roleCode+"&roleId="+roleId;
	//因为Tomcat服务器会自动帮你做一次URLDecode，所以再加上你自己在代码里面写的URLDecode 所以要做两边encode
	url = encodeURI(encodeURI(url));
	$("<div id='role_permission_assign_dialog' /> ").dialog({
		href:url,
		title:"角色-权限分配",
		method:'post',
		width:'800px',
		height:'470px',
		modal: true,
		onClose : function() {
				$(this).dialog('destroy');
			}
	});
}

/**
 * 修改
 * @param row
 */
function update(pid){
	role.openAddOrUpdate(pid);
}

