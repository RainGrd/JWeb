/**
 * 短信模板js类
 */
var smsTemplates = {
		//初始化
		initDataGrid:function(){
			$('#smsTemplatesGrid').datagrid({    
				url:basePath + 'sysSmsTemplatesController/smsTemplatesList.shtml',
				width:'100%',
				fit:true,
				title:'短信模板列表',
				toolbar:'#toolbar',
				pagination: true,
			    rownumbers:false,
			    sortOrder:'asc',
			    remoteSort:false,
			    columns:smsTemplatesManager_model,
			    onClickRow:function(rowIndex, rowData){
					$('#smsTemplatesGrid').datagrid('clearChecked');
					$('#smsTemplatesGrid').datagrid('checkRow',rowIndex);
			    }
			});
		},
		// 新增
		add:function(){
			$("<div id='addDialog' /> ").dialog({
				href:basePath + "sysSmsTemplatesController/toAdd.shtml",
				title:"短信模板-新增",
				method:'post',
				width:'500',
				height:'350',
				modal: true,
				buttons:[{
					text:'保存',
					iconCls:'icon-save',
					handler:smsTemplates.save
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
		update:function(pid){
			$("<div id='addDialog' /> ").dialog({
				href: basePath + "sysSmsTemplatesController/toAdd.shtml?pid="+pid,
				title:"短信模板-修改",
				method:'post',
				width:'500',
				height:'350',
				modal: true,
				buttons:[{
					text:'保存',
					iconCls:'icon-save',
					handler:smsTemplates.save
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
			var rows = $('#smsTemplatesGrid').datagrid('getSelections');
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
						$.post(BASE_PATH+"sysSmsTemplatesController/deleteBtach.shtml",{pid:pId}, 
							function(ret) {
								if(ret.message.status == 200){
									$.messager.alert('操作提示',"删除成功！",'success');
									$("#smsTemplatesGrid").datagrid('reload');
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
			if(!$("#smsTemplatesAdd").form('validate')){
				return ;
			}
			var obj = jqueryUtil.serializeObject($("#smsTemplatesAdd"));
			//验证编码是否存在 
			if(!smsTemplates.validateCode(obj.tempCode,obj.pid)){
				return;
			}
			var objStr = JSON.stringify(obj);
			$.ajax({
				type: "POST",
		        async:false, 
		    	url : BASE_PATH+"sysSmsTemplatesController/save.shtml",
		    	data:{"data":objStr},
				dataType: "json",
			    success: function(data){
			    	if(data.message.status == 200 ){
			    		$.messager.alert('操作提示',"保存成功！",'success');
			    		$('#smsTemplatesGrid').datagrid('reload'); 
			    		$("#addDialog").dialog('close');
			    	}else{
			    		$.messager.alert('操作提示',"保存失败,请联系管理员！",'error');
			    	}
			    	
				}
			}); 
		},
		validateCode:function(code,pid){
			var result = false;
			if(pid == ""){
				var parm ="{tempCode:"+code+"}";
				$.ajax({
					type: "POST",
			        async:false, 
			    	url : BASE_PATH+"sysSmsTemplatesController/validateCode.shtml",
			    	data:{"data":parm},
					dataType: "json",
				    success: function(data){
				    	if(data.message.status == 200 ){
				    		if(data.result == 'suc'){
				    			result = true;
				    		}else{
				    			$.messager.alert('操作提示',"您输入的短信模板编码已存在,请检查！",'error');
				    		}
				    	}else{
				    		$.messager.alert('操作提示',"验证短信模板编码失败,请联系管理员！",'error');
				    	}
					}
				}); 
			}else{
				result = true;
			}
			return result;
			
		},
		searchData:function(){
			var obj = jqueryUtil.serializeObject($("#searcm"));
			var objStr = JSON.stringify(obj);
			$('#smsTemplatesGrid').datagrid('load',{data:objStr});
		},
		/**
		 * 查询框清空
		 */
		resetInput:function(){
			$("#searcm input").val("");
		}
}

$(document).ready(function(){
	smsTemplates.initDataGrid();	
});