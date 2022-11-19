/**
 * 数据字典js类
 */
var distionary = {
		//初始化
		initDataGrid:function(){
			$('#distionaryGrid').datagrid({    
				url:basePath + 'sysDistionaryController/distionaryList.shtml',
				width:'100%',
				fit:true,
				title:'数据字典列表',
				toolbar:'#toolbar',
				pagination: true,
			    rownumbers:true,
			    sortOrder:'asc',
			    remoteSort:false,
			    columns:dictionaryManager_model,
			    onClickRow:function(rowIndex, rowData){
					$('#distionaryGrid').datagrid('clearChecked');
					$('#distionaryGrid').datagrid('checkRow',rowIndex);
			    }
			});
		},
		// 新增
		toAdd:function(){
			$("<div id='addDialog' /> ").dialog({
				href:basePath + "sysDistionaryController/toAdd.shtml",
				title:"数据字典-新增",
				method:'post',
				width:'500',
				height:'350',
				modal: true,
				buttons:[{
					text:'保存',
					iconCls:'icon-save',
					handler:distionary.save
				},{
					text:'关闭',
					iconCls:'icon-cancel',
					handler:function(){
						$("#addDialog").dialog('close');
					}
				}],
				onClose : function() {
						$(this).dialog('destroy');
					}
			});
		},
		// 修改
		toUpdate:function(pid){
			$("<div id='addDialog' /> ").dialog({
				href:basePath + "sysDistionaryController/toAdd.shtml?pid="+pid,
				title:"数据字典-修改",
				method:'post',
				width:'500',
				height:'350',
				modal: true,
				buttons:[{
					text:'保存',
					iconCls:'icon-save',
					handler:distionary.save
				},{
					text:'关闭',
					iconCls:'icon-cancel',
					handler:function(){
						$("#addDialog").dialog('close');
					}
				}],
				onClose : function() {
						$(this).dialog('destroy');
					}
			});
		},
		// 删除
		batchRemove:function(){
			var rows = $('#distionaryGrid').datagrid('getSelections');
		 	if ( rows.length == 0 ) {
		 		$.messager.alert("操作提示","请选择数据","error");
				return;
			}
		 	// 获取选中pid
		 	var pId = "";
			for(var i=0;i<rows.length;i++){
		  		if(i==0){
		  			pId+=rows[i].pid;
		  		}else{
		  			pId+=","+rows[i].pid;
		  		}
		  	}
			$.messager.confirm("操作提示","确定删除选择项?",
				function(r) {
		 			if(r){
						$.post(BASE_PATH+"sysDistionaryController/deleteBtach.shtml",{pid:pId}, 
							function(ret) {
								if(ret.message.status == 200){
									$.messager.alert('操作提示',"删除成功！",'success');
									$("#distionaryGrid").datagrid('reload');
								}else{
									$.messager.alert('操作提示',"删除失败！",'error');
								}
							},'json');
		 			}
				}
		 	);
		},
		// 保存
		save:function(){
			// 验证表单必填项
			if(!$("#distionaryAdd").form('validate')){
				return ;
			}
			
			var obj = jqueryUtil.serializeObject($("#distionaryAdd"));
			//验证编码是否存在 
			if(!distionary.validateCode(obj.dictCode,obj.pid)){
				return;
			}
			var objStr = JSON.stringify(obj);
			$.ajax({
				type: "POST",
		        async:false, 
		    	url : BASE_PATH+"sysDistionaryController/save.shtml",
		    	data:{"data":objStr},
				dataType: "json",
			    success: function(data){
			    	if(data.message.status == 200 ){
			    		$.messager.alert('操作提示',"保存成功！",'success');
			    		$('#distionaryGrid').datagrid('reload'); 
			    		$("#addDialog").dialog('close');
			    	}else{
			    		$.messager.alert('操作提示',"保存失败,请联系管理员！",'error');
			    	}
			    	
				}
			}); 
		},
		validateCode:function(code,pid){
			var result = false;
			var parm ="{dictCode:'"+code+"',pid:'"+pid+"'}";
			$.ajax({
				type: "POST",
		        async:false, 
		    	url : BASE_PATH+"sysDistionaryController/validateCode.shtml",
		    	data:{"data":parm},
				dataType: "json",
			    success: function(data){
			    	if(data.message.status == 200 ){
			    		if(data.result == 'suc'){
			    			result = true;
			    		}else{
			    			$.messager.alert('操作提示',"您输入的数据字典编码已存在,请检查！",'error');
			    		}
			    	}else{
			    		$.messager.alert('操作提示',"验证数据字典编码失败,请联系管理员！",'error');
			    	}
				}
			}); 
			return result;
		},
		// 查询
		searchData:function(){
			var obj = jqueryUtil.serializeObject($("#searcm"));
			var objStr = JSON.stringify(obj);
			$('#distionaryGrid').datagrid('load',{data:objStr});
		},
		// 数据加载
		loadData:function(){
			var pid = $("#pid").val();
			if(null!=pid && ""!=pid){
				$.ajax({
					type: "POST",
			    	url : BASE_PATH+"sysDistionaryController/getByPid.shtml",
			    	data:{data:"{pid:"+pid+"}"},
					dataType: "json",
				    success: function(data){
				    	if(data.message.status ==200){
				    		$("#distionaryAdd").form('load',data.result);
				    	}else{
				    		$.messager.alert("操作提示","数据加载失败,请联系管理员!","error");
				    	}
					}
				});
			}
		}
}
