/**
 * 配置权限类
 */
var loadTreeC=0;//判断Tree是第一次加载or 不是第一次加载
var permissionAssign = {
	//构造树
	buildTreeNode:function(data){
		var nodes = [];
		var m_menus = {};
		for (var i = 0; i < data.length; i++) {
			var item = data[i];
			var buttons = item.buttons;
			var checked = true;
			var node = {
				id : item.pid,
				text : item.menuName,
				attributes : {
					url : item.menuUrl,
					pid : item.parentAuthId
				}
			};
			if (!item.parentAuthId) {
				nodes.push(node);
			}
			var parentNode = m_menus[item.parentAuthId];
			m_menus[item.pid] = node;
			if (item.parentAuthId && parentNode) {
				parentNode.state = 'closed';
				// 只展开一级菜单
				if (parentNode.attributes.parentAuthId) {
					//parentNode.state = 'closed';
				}
				var children = parentNode['children'];
				if (!children) {
					children = parentNode['children'] = new Array();
				}
				children.push(node);
				if(buttons.length>0){
					for (var j = 0; j < buttons.length; j++) {
						var button = buttons[j];
						var children1 = node['children'];
						if (!children1) {
							children1 = node['children'] = new Array();
						}
						debugger;
						var node1 = {
								id : button.menuButtonId,
								text : button.buttonName,
								iconCls:"icon-save",
								attributes : {
									menuButtonId : button.menuButtonId
								}
							};
						children1.push(node1);
					}
				}
			}
		}
		delete m_menus;
		return nodes;
	},
	//初始化权限树
	initPermissionTree:function(){
			$.ajax({
				url: BASE_PATH+'sysMenuController/queryPermissionSelect.shtml',
				success: function(result) {
					if (result.message.message!="200") {
						$.messager.alert('操作提示',result.data,'error');
						return;
					}
					var	nodes = permissionAssign.buildTreeNode(result.data);
					$('#role_permission_tree').tree({
						animate: true,
						checkbox: true,
						cascadeCheck:false,
						lines:true,
						data: nodes,
						onCheck:function (node, checked){							
							if(node.children&&loadTreeC){ //非第一次加载+有子节点
							if(node.children.length){
								if(checked){
									$(node.target).parent().find('.tree-checkbox').removeClass('tree-checkbox0').removeClass('tree-checkbox2');
									$(node.target).parent().find('.tree-checkbox').addClass('tree-checkbox1');
								}else{
									$(node.target).parent().find('.tree-checkbox').removeClass('tree-checkbox1').removeClass('tree-checkbox2');
									$(node.target).parent().find('.tree-checkbox').addClass('tree-checkbox0');
								}
							}
							}
						},
						onLoadSuccess:function(node, data){
							$.post(BASE_PATH+"sysRoleMenuButtonRelController/queryPermission.shtml",{"roleId":$("#roleId").val()}, 
									function(ret) {
										if(ret.message.status == 200){
											//第一次加载 loadTreeC=0
											$(ret.data).each(function(index,element){
												var n = null;
												if(element.menuId){
													//menuButtonIds += element.attributes.menuButtonId+",";
													n = $("#role_permission_tree").tree('find',element.menuId);
												}else if(element.menuButtonRelId){
													debugger;
													n = $("#role_permission_tree").tree('find',element.menuButtonRelId);
												}
				                                if(n){
				                                    $("#role_permission_tree").tree('check',n.target);
				                                }
											});
											loadTreeC=1;//加载完之后变成1
											
										}else{
											$.messager.alert('操作提示',"权限初始化失败！",'error');
										}
									},'json'
								);
						}
					});
				}
			});
		
	},
	//保存
	save:function(){
		var nodes = $('#role_permission_tree').tree('getChecked');
		var roleId = $("#roleId").val();
		var menuIds = "";
		var menuButtonIds ="";
		$(nodes).each(function(index,element){
			if(element.attributes.menuButtonId){
				menuButtonIds += element.attributes.menuButtonId+",";
			}else{
				menuIds += element.id+",";
			}
		});
		$.post(BASE_PATH+"sysRoleMenuButtonRelController/permissionAssign.shtml",{"menuIds":menuIds,"menuButtonIds":menuButtonIds,"roleId":roleId}, 
			function(ret) {
				if(ret.message.status == 200){
					$.messager.alert('操作提示',ret.data,'success');
					$("#role_index_grid").datagrid('reload');
				}else{
					$.messager.alert('操作提示',"删除失败！",'error');
				}
			},'json'
		);
	},
	//重置
	reset:function(){
		permissionAssign.initPermissionTree();
	},
	//关闭
	close:function(){
		$("#role_permission_assign_dialog").dialog('close');
	},
	//还原
	restore:function(){
		
	}
}
