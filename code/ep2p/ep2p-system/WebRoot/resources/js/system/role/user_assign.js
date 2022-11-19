/**
 * 配置用户类
 */
var userAssign = {
	//初始化可选用户列表
	initChoosableUserGrid:function(){
		$('#choosable_user_grid').datagrid({    
			url:basePath + 'sysUserController/choosableUser.shtml',
			height:'390',
			width:'96%',
			toolbar:'#choosable_user_toolbar',
			title:'可选用户',
			pagination: true,
		    rownumbers:false,
		    sortOrder:'asc',
		    queryParams:{"roleId":$("#roleId").val()},
		    remoteSort:false,
		    columns: user_assign_model,
		    onClickRow:function(rowIndex, rowData){
		    	//$('#distionaryGrid').datagrid('load',{data:objStr});
				//$('#choosable_user_grid').datagrid('clearChecked');
				//$('#choosable_user_grid').datagrid('checkRow',rowIndex);
		    }
		});
	},
	//初始化已选用户列表
	initSelectedUserGrid:function(){
		$('#selected_user_grid').datagrid({    
			url:basePath + 'sysUserController/selectedUser.shtml',
			height:'390',
			width:'99%',
			toolbar:'#selected_user_toolbar',
			title:'已选用户',
			pagination: true,
		    rownumbers:false,
		    sortOrder:'asc',
		    queryParams:{"roleId":$("#roleId").val()},
		    remoteSort:false,
		    columns: user_assign_model,
		    onClickRow:function(rowIndex, rowData){
				//$('#selected_user_grid').datagrid('clearChecked');
				//$('#selected_user_grid').datagrid('checkRow',rowIndex);
		    }
		});
	},
	searchChoosableData:function(){
		var data = {"roleId":$("#roleId").val(),"queryStr":$("#choosableQueryStr").val()}
		$('#choosable_user_grid').datagrid('load',data);
	},
	searchSelectedData:function(){
		var data = {"roleId":$("#roleId").val(),"queryStr":$("#selectedQueryStr").val()}
		$('#selected_user_grid').datagrid('load',data);
	},
	//分配用户
	userAssign:function(){
		var rows = $('#choosable_user_grid').datagrid('getSelections');
	 	if ( rows.length == 0 ) {
	 		$.messager.alert("操作提示","请选择数据","error");
			return;
		}
	 	// 获取选中pid
	 	var pIds = "";
		for(var i=0;i<rows.length;i++){
	  		if(i==0){
	  			pIds+=rows[i].pid;
	  		}else{
	  			pIds+=","+rows[i].pid;
	  		}
	  	}
		$.messager.confirm("操作提示","你确定要为当前角色分配所选用户吗?",
				function(r) {
		 			if(r){
		 				var data = {"roleId":$("#roleId").val(),"userIds":pIds}
						$.post(BASE_PATH+"sysRoleController/userAssign.shtml",data, 
							function(ret) {
								if(ret.message.status == 200){
									$.messager.alert('操作提示',"用户分配成功！",'info');
									var choosableData = {"roleId":$("#roleId").val(),"queryStr":$("#choosableQueryStr").val()};
									var selectedData = {"roleId":$("#roleId").val(),"queryStr":$("#selectedQueryStr").val()};
									$('#choosable_user_grid').datagrid('load',choosableData);
									$('#selected_user_grid').datagrid('load',selectedData);
								}else{
									$.messager.alert('操作提示',"用户分配失败！",'error');
								}
							},'json');
		 			}
				}
		 	);
	},
	//取消用户分配
	cancelUserAssign:function(){
		
		var rows = $('#selected_user_grid').datagrid('getSelections');
	 	if ( rows.length == 0 ) {
	 		$.messager.alert("操作提示","请选择数据","error");
			return;
		}
	 	// 获取选中pid
	 	var pIds = "";
		for(var i=0;i<rows.length;i++){
	  		if(i==0){
	  			pIds+=rows[i].pid;
	  		}else{
	  			pIds+=","+rows[i].pid;
	  		}
	  	}
		$.messager.confirm("操作提示","你确定要取消所选用户的角色吗?",
				function(r) {
		 			if(r){
		 				var data = {"roleId":$("#roleId").val(),"userIds":pIds}
						$.post(BASE_PATH+"sysRoleController/cancelUserAssign.shtml",data, 
							function(ret) {
								if(ret.message.message == 200){
									$.messager.alert('操作提示',ret.data,'info');
									var choosableData = {"roleId":$("#roleId").val(),"queryStr":$("#choosableQueryStr").val()};
									var selectedData = {"roleId":$("#roleId").val(),"queryStr":$("#selectedQueryStr").val()};
									$('#choosable_user_grid').datagrid('load',choosableData);
									$('#selected_user_grid').datagrid('load',selectedData);
								}else{
									$.messager.alert('操作提示',"用户分配失败！",'error');
								}
							},'json');
		 			}
				}
		 	);
	}
}
