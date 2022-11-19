var batchReplaceCus = {
	pid:"",	
	init:function(){
		batchReplaceCus.pid = $("#pid").val();
		 
	},
	//初始化
	initDataGrid:function(){
		var pid = $("#pid").val();
		$('#showCusGrid').datagrid({    
			url:basePath + 'customerController/getCusByPid.shtml?pid='+pid,
			width:'100%',
			fit:true,
			toolbar:'#batchToolbar',
		    sortOrder:'asc',
		    remoteSort:false,
		    rownumbers:true,
		    columns:batchReplaceCustomer_Model,
		    onClickRow:function(rowIndex, rowData){
				$('#showCusGrid').datagrid('clearChecked');
				$('#showCusGrid').datagrid('checkRow',rowIndex);
		    }
		});
	},
	//批量更换客服
	batchReplaceCustomer:function(){
		var rows = $('#grid').datagrid('getSelections');
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
		// 获取选中的旧客服ID
	 	var oldcusServiceId= "";
		for(var j=0;j<rows.length;j++){
	  		if(j==0){
	  			oldcusServiceId += rows[j].oldcusServiceId;
	  		}else{
	  			oldcusServiceId +=","+rows[j].oldcusServiceId;
	  		}
	  	}
		$("<div id='addDialog' /> ").dialog({
			href:basePath + "customerController/toBatchReplaceCus.shtml?pid="+pId+'&oldcusServiceId='+oldcusServiceId,
			title:"批量更换客服",
			method:'post',
			width:'700',
			height:'400',
			modal: true,
			buttons:[{
				text:'确定更换',
				iconCls:'icon-save',
				handler:batchReplaceCus.save
			},{
				text:'取消',
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
		//批量更换客服弹窗保存事件
		save:function(){
			var ss =  $("#cusServicePidDlg").combobox("getValue");
			if(ss==""){
				$.messager.alert('操作提示',"请选择要更换的客服！",'error');
			}else{
			 if(!$("#baseInfo").form('validate')){
					return ;
				}
				//序列化表单 
				var obj = jqueryUtil.serializeObject($("#baseInfo"));
				var objStr = JSON.stringify(obj);
				$.ajax({
					type: "POST",
			        async:false, 
			    	url : BASE_PATH+"customerController/batchUpdateCus.shtml",
			    	data:{"data":objStr},
					dataType: "json",
				    success: function(data){
				    	if(data.message.status == 200 ){
				    		$.messager.alert('操作提示',"保存成功！",'success');
				    		window.location.href=BASE_PATH+"customerController/toList.shtml";
				    	}else{
				    		$.messager.alert('操作提示',"保存失败！",'error');
				    	}
					}
				}); 
			}
				},
}
