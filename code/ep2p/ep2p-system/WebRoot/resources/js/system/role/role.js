/**
 * 角色js类
 */
var role = {
		//初始化加载角色首页列表
		initDataGrid:function(){
			$('#role_index_grid').datagrid({    
				url:basePath + 'sysRoleController/roleIndexList.shtml',
				width:'100%',
				fit:true,
				title:'角色管理',
				toolbar:'#role_index_toolbar',
				pagination: true,
				rownumbers: true,
			    sortOrder:'asc',
			    remoteSort:false,
			    columns: role_index_model,
			    onClickRow:function(rowIndex, rowData){
					$('#role_index_grid').datagrid('clearChecked');
					$('#role_index_grid').datagrid('checkRow',rowIndex);
			    }
			});
		},
		searchData:function(){
			var obj = jqueryUtil.serializeObject($("#role_index_form"));
			var objStr = JSON.stringify(obj,true);
			$('#role_index_grid').datagrid('load',{data:objStr});
		},
		openAddOrUpdate:function(pid){
			var url = BASE_PATH + "sysRoleController/openAddOrUpdate.shtml";
			var title="角色-新增";
			if(pid){
				url = BASE_PATH + "sysRoleController/openAddOrUpdate.shtml?pid="+pid
				title = "角色-修改";
			}
			$("<div id='role_add_or_update_dialog' /> ").dialog({
				href:url,
				title:title,
				method:'post',
				width:'500px',
				height:'300px',
				modal: true,
				buttons:[{
					text:'保存',
					iconCls:'icon-save',
					handler:function(){
						role.addOrUpdate();
					}
				},{
					text:'取消',
					iconCls:'icon-cancel',
					handler:function(){
						$("#role_add_or_update_dialog").dialog('close');
					}
				}],
				onClose : function() {
						$(this).dialog('destroy');
					}
			});
		},
		// 批量删除
		batchRemove:function(){
			var rows = $('#role_index_grid').datagrid('getSelections');
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
			$.messager.confirm("操作提示","确定删除选择项?",
				function(r) {
		 			if(r){
						$.post(BASE_PATH+"sysRoleController/deleteBtach.shtml",{pIds:pIds}, 
							function(ret) {
								if(ret.message.status == 200){
									$.messager.alert('操作提示',ret.data,'success');
									$("#role_index_grid").datagrid('reload');
								}else{
									$.messager.alert('操作提示',"删除失败！",'error');
								}
							},'json'
						);
		 			}
				}
		 	);
		},
		// 保存或者修改
		addOrUpdate:function(){
			// 验证表单必填项
			if(!$("#role_add_or_update_form").form('validate')){
				return ;
			}
			
			var obj = jqueryUtil.serializeObject($("#role_add_or_update_form"),true);
			if(!obj.pid && ""==obj.pid){
				//验证编码是否存在 
				if(!role.validateName(obj.roleName)){
					return;
				}
			}
			var objStr = JSON.stringify(obj);
			$.ajax({
				type: "POST",
		        async:false, 
		    	url : BASE_PATH+"sysRoleController/addOrUpdate.shtml",
		    	data:{"data":objStr},
				dataType: "json",
			    success: function(data){
			    	if(data.message.message == 200 ){
			    		$.messager.alert('操作提示',data.data,'success');
			    		$('#role_index_grid').datagrid('reload'); 
			    		$("#role_add_or_update_dialog").dialog('close');
			    	}else{
			    		$.messager.alert('操作提示',"保存失败,请联系管理员！",'error');
			    	}
			    	
				}
			}); 
		},
		//验证角色名称是否存在
		validateName:function(roleName){
			var result = false;
			var parm ="{roleName:'"+roleName+"'}";
			$.ajax({
				type: "POST",
		        async:false, 
		    	url : BASE_PATH+"sysRoleController/validateName.shtml",
		    	data:{"data":parm},
				dataType: "json",
			    success: function(data){
			    	if(data.message.status == 200 ){
			    		if(data.isExist == 'true'){
			    			$.messager.alert('操作提示',"您输入的角色名称已存在,请检查！",'error');
			    		}else{
			    			result = true;
			    		}
			    	}else{
			    		$.messager.alert('操作提示',"验证数据角色名称失败,请联系管理员！",'error');
			    	}
				}
			}); 
			return result;
		}
}
