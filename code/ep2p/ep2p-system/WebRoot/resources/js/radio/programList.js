var  radio  = {
		//初始化
		initDataGrid:function(){
			$('#programListGrid').datagrid({    
				url:basePath + 'radioController/getProgramSearchList.shtml',
				width:'100%',
				fit:true,
				title:'节目列表查询',
				toolbar:'#toolbar',
				pagination: true,
			    rownumbers:true,
			    sortOrder:'asc',
			    remoteSort:false,
			    columns:programList_model,
			    onClickRow:function(rowIndex, rowData){
					$('#programListGrid').datagrid('clearChecked');
			    }
			});
		},
		
		add:function(){
			window.location.href=BASE_PATH+"customerController/toAdd.shtml";
		},
		//批量删除
		beatchDel:function(){
			var rows = $('#programListGrid').datagrid('getSelections');
			if (rows.length == 0) {
				$.messager.alert("操作提示", "请选择数据", "error");
				return;
			}// 获取选中的系统用户名 
			var pids = "";
			for (var i = 0; i < rows.length; i++) {
				if (i == 0) {
					pids += rows[i].pid;
				} else {
					pids += "," + rows[i].pid;
				}
			}
			$.messager.confirm("操作提示","确定删除节目信息?",
			function(r) {
				if (r) {
					$.post(BASE_PATH+"radioController/beatchDeleteProgramList.shtml",{pids:pids}, 
							function(ret) {
								if(ret.message.status == 200){
									$.messager.alert('操作提示',"删除成功！",'success');
									$("#programListGrid").datagrid('reload');
								}else{
									$.messager.alert('操作提示',"删除失败！",'error');
								}
							},'json');
				}
			});
		},
		//修改
		update:function(pid){
			//序列化表单 
			var obj = jqueryUtil.serializeObject($("#baseInfo"));
			var objStr = JSON.stringify(obj);
			$.ajax({
				type: "POST",
		        async:false, 
		    	url : BASE_PATH+"radioController/updateProgramListById.shtml?pid="+pid,
		    	data:{"data":objStr},
				dataType: "json",
			    success: function(data){
			    	if(data.message.status == 200 ){
			    		$.messager.alert('操作提示',"保存成功！",'success');
			    		window.location.href=BASE_PATH+"radioController/toProgramSearchList.shtml";
			    	}else{
			    		$.messager.alert('操作提示',"保存失败！",'error');
			    	}
				}
			});
		},
		//上传节目
		uploadProgram:function(){
			var path = BASE_PATH+"radioController/uploadProgramList.shtml";
			$("<div id='uploadDialog' /> ").dialog({
				href:path,
				title:"上传节目",
				method:'post',
				width:'550',
				height:'300',
				modal: true,
				buttons:[{
					text:'上传',
					iconCls:'icon-save',
					handler:function(){
						radio.upload();
					}
				},{
					text:'返回',
					iconCls:'icon-cancel',
					handler:function(){
						$("#uploadDialog").dialog('close');
					}
				}],
				onClose : function() {
						$(this).dialog('destroy');
					}
			});
		},
		//节目上传
		upload:function(){
			debugger;
			var obj = jqueryUtil.serializeObject($("#uploadFile"));
			var objStr = JSON.stringify(obj);
			$("#uploadFile").form('submit', {
				url:BASE_PATH+'radioController/upload.shtml',
				onSubmit : function() {return $(this).form('validate');},
				success : function(data) {
					var result = eval("("+data+")");
					if(result.message == 200 ){
						$.messager.alert('操作提示',"上传节目成功！",'success');
						window.location.href=BASE_PATH+"radioController/toProgramSearchList.shtml";
			    	}else{
			    		$.messager.alert('操作提示',"上传节目失败！",'error');
			    	}
				}
			});
			/**$.ajax({
				type: "POST",
		        async:false, 
		    	url : BASE_PATH+"radioController/upload.shtml",
		    	data:{"data":objStr},
				dataType: "json",
			    success: function(data){
			    	if(data.message.status == 200 ){
			    		$.messager.alert('操作提示',"上传节目成功！",'success');
			    		window.location.href=BASE_PATH+"radioController/toProgramSearchList.shtml";
			    	}else{
			    		$.messager.alert('操作提示',"上传节目失败！",'error');
			    	}
				}
			});*/
		},
		//预发布保存
		savePredict:function(){
			var obj = jqueryUtil.serializeObject($("#baseInfo"));
			var objStr = JSON.stringify(obj);
			$.ajax({
				type: "POST",
		        async:false, 
		    	url : BASE_PATH+"radioController/savePredict.shtml",
		    	data:{"data":objStr},
				dataType: "json",
			    success: function(data){
			    	if(data.message.status == 200 ){
			    		$.messager.alert('操作提示',"保存成功！",'success');
			    		window.location.href=BASE_PATH+"radioController/toProgramSearchList.shtml";
			    	}else{
			    		$.messager.alert('操作提示',"保存失败！",'error');
			    	}
				}
			});
		},
};