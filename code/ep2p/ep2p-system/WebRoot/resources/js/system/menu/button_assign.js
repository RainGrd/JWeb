/**
 * 配置按钮类
 */
var ButtonAssign = {
	//初始化可选按钮列表
	initChoosableButtonGrid:function(){
		$('#choosable_button_grid').datagrid({    
			url:basePath + 'sysButtonController/choosableButton.shtml',
			height:'380',
			width:'98%',
			toolbar:'#choosable_button_toolbar',
			title:'可选按钮',
			pagination: true,
		    rownumbers:false,
		    sortOrder:'asc',
		    queryParams:{"menuId":$("#menuId").val()},
		    remoteSort:false,
		    columns: button_index_model
		});
	},
	//初始化已选按钮列表
	initSelectedButtonGrid:function(){
		$('#selected_button_grid').datagrid({    
			url:basePath + 'sysButtonController/selectedButton.shtml',
			height:'380',
			width:'98%',
			toolbar:'#selected_button_toolbar',
			title:'已选按钮',
			pagination: true,
		    rownumbers:false,
		    sortOrder:'asc',
		    queryParams:{"menuId":$("#menuId").val()},
		    remoteSort:false,
		    columns: button_index_model
		});
	},
	//分配按钮
	buttonAssign:function(){
		var rows = $('#choosable_button_grid').datagrid('getSelections');
	 	if ( rows.length == 0 ) {
	 		$.messager.alert("操作提示","请选择数据","error");
			return;
		}
	 	// 获取选中pid
	 	var buttonIds = "";
		for(var i=0;i<rows.length;i++){
	  		if(i==0){
	  			buttonIds+=rows[i].pid;
	  		}else{
	  			buttonIds+=","+rows[i].pid;
	  		}
	  	}
		$.messager.confirm("操作提示","你确定要为当前菜单分配所选按钮吗?",
				function(r) {
		 			if(r){
		 				var data = {"menuId":$("#menuId").val(),"buttonIds":buttonIds}
						$.post(BASE_PATH+"sysMenuController/buttonAssign.shtml",data, 
							function(ret) {
								if(ret.message.status == 200){
									$.messager.alert('操作提示',"用户分配成功！",'info');
									var queryData = {"menuId":$("#menuId").val(),};
									$('#choosable_button_grid').datagrid('load',queryData);
									$('#selected_button_grid').datagrid('load',queryData);
								}else{
									$.messager.alert('操作提示',"按钮分配失败！",'error');
								}
							},'json');
		 			}
				}
		 	);
	},
	//取消按钮分配
	cancelButtonAssign:function(){
		var rows = $('#selected_button_grid').datagrid('getSelections');
	 	if ( rows.length == 0 ) {
	 		$.messager.alert("操作提示","请选择数据","error");
			return;
		}
	 	// 获取选中pid
	 	var buttonIds = "";
		for(var i=0;i<rows.length;i++){
	  		if(i==0){
	  			buttonIds+=rows[i].pid;
	  		}else{
	  			buttonIds+=","+rows[i].pid;
	  		}
	  	}
		$.messager.confirm("操作提示","你确定要取消所选菜单的按钮吗?",
				function(r) {
		 			if(r){
		 				var data = {"menuId":$("#menuId").val(),"buttonIds":buttonIds}
						$.post(BASE_PATH+"sysMenuController/cancelButtonAssign.shtml",data, 
							function(ret) {
								if(ret.message.message == 200){
									$.messager.alert('操作提示',ret.data,'info');
									var queryData = {"menuId":$("#menuId").val(),};
									$('#choosable_button_grid').datagrid('load',queryData);
									$('#selected_button_grid').datagrid('load',queryData);
								}else{
									$.messager.alert('操作提示',"按钮分配失败！",'error');
								}
							},'json');
		 			}
				}
		 	);
	}
}
