/**
 * 借款审核js 文件
 * 
 * @author Yu.zhang
 * 
 * @date 2015-09-24 
 */

var houseManager = {
		
		// 查询 借款担保审核项目
		searchList:function(){
			jqueryUtil.ajaxSearchPage("#houseManagerGrid","#searcm");
		},
		// 初始化 楼盘管理table
		initHouseManagerDataGrid:function(){
			$('#houseManagerGrid').datagrid({    
				url:basePath + 'bizHousesController/selectAllPage.shtml',
				width:'100%',
				fit:true,
				title:'楼盘管理列表',
				toolbar:'#toolbar',
				pagination: true,
			    rownumbers:true,
			    checkbox:false,
			    sortOrder:'asc',
			    remoteSort:false,
			    columns:houseManager_model,
			    onClickRow:function(rowIndex, rowData){
					$('#houseManagerGrid').datagrid('clearChecked');
					$('#houseManagerGrid').datagrid('checkRow',rowIndex);
			    }
			});
		},
		// 新增
		toAdd:function(){
			$("<div id='addDialog' /> ").dialog({
				href:basePath + "bizHousesController/toAdd.shtml",
				title:"楼盘管理-新增",
				method:'post',
				width:'500',
				height:'350',
				modal: true,
				buttons:[{
					text:'保存',
					iconCls:'icon-save',
					handler:houseManager.save
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
				href:basePath + "bizHousesController/toAdd.shtml?pid="+pid,
				title:"楼盘管理-修改",
				method:'post',
				width:'500',
				height:'350',
				modal: true,
				buttons:[{
					text:'保存',
					iconCls:'icon-save',
					handler:houseManager.save
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
		// 保存
		save:function(){
			// 验证表单必填项
			if(!$("#houseAdd").form('validate')){
				return ;
			}
			
			var obj = jqueryUtil.serializeObject($("#houseAdd"));
			var objStr = JSON.stringify(obj);
			$.ajax({
				type: "POST",
		        async:false, 
		    	url : BASE_PATH+"bizHousesController/save.shtml",
		    	data:{"data":objStr},
				dataType: "json",
			    success: function(data){
			    	if(data.message.status == 200 ){
			    		$.messager.alert('操作提示',"保存成功！",'success');
			    		$('#houseManagerGrid').datagrid('reload'); 
			    		$("#addDialog").dialog('close');
			    	}else{
			    		$.messager.alert('操作提示',"保存失败,请联系管理员！",'error');
			    	}
			    	
				}
			}); 
		},
		// 删除
		deleteHouse:function(pid){
			$.messager.confirm("操作提示","确定删除选择项?",
					function(r) {
			 			if(r){
							$.post(BASE_PATH+"bizHousesController/delete.shtml",{pid:pid}, 
								function(ret) {
									if(ret.message.status == 200){
										$.messager.alert('操作提示',"删除成功！",'success');
										$("#houseManagerGrid").datagrid('reload');
									}else{
										$.messager.alert('操作提示',"删除失败！",'error');
									}
								},'json');
			 			}
					}
			 	);
		},
		// 数据加载
		loadData:function(){
			var pid = $("#pid").val();
			if(null!=pid && ""!=pid){
				$.ajax({
					type: "POST",
			    	url : BASE_PATH+"bizHousesController/getByPid.shtml",
			    	data:{pid:pid},
					dataType: "json",
				    success: function(data){
				    	if(data.message.status ==200){
				    		$("#houseAdd").form('load',data.result);
				    	}else{
				    		$.messager.alert("操作提示","数据加载失败,请联系管理员!","error");
				    	}
					}
				});
			}
		}
}

