/**
 * 按钮类
 */
var sysButton = {
	//初始化加载按钮首页列表
	initDataGrid:function(){
		$('#button_index_grid').datagrid({    
			url:basePath+'sysButtonController/buttonIndexList.shtml',
			width:'100%',
			fit:true,
			title:'按钮管理',
			toolbar:'#button_index_toolbar',
			pagination: true,
		    rownumbers:false,
		    sortOrder:'asc',
		    remoteSort:false,
		    columns: button_index_model
		});
	},
	openAddOrUpdate:function(pid){
		var url = BASE_PATH + "sysButtonController/openAddOrUpdate.shtml";
		var title="按钮-新增";
		if(pid){
			url = BASE_PATH + "sysButtonController/openAddOrUpdate.shtml?pid="+pid
			title = "按钮-修改";
		}
		$("<div id='button_add_or_update_dialog' /> ").dialog({
			href:url,
			title:title,
			method:'post',
			width:'400px',
			height:'160px',
			modal: true,
			buttons:[{
				text:'保存',
				iconCls:'icon-save',
				handler:function(){
					sysButton.addOrUpdate();
				}
			},{
				text:'取消',
				iconCls:'icon-cancel',
				handler:function(){
					$("#button_add_or_update_dialog").dialog('close');
				}
			}],
			onClose : function() {
					$(this).dialog('destroy');
				}
		});
	},
	// 保存或者修改
	addOrUpdate:function(){
		// 验证表单必填项
		if(!$("#button_add_or_update_form").form('validate')){
			return ;
		}
		
		var obj = jqueryUtil.serializeObject($("#button_add_or_update_form"),true);
		var objStr = JSON.stringify(obj);
		$.ajax({
			type: "POST",
	        async:false, 
	    	url : BASE_PATH+"sysButtonController/addOrUpdate.shtml",
	    	data:{"data":objStr},
			dataType: "json",
		    success: function(data){
		    	if(data.message.message == 200 ){
		    		$.messager.alert('操作提示',data.data,'success');
		    		$('#button_index_grid').datagrid('reload'); 
		    		$("#button_add_or_update_dialog").dialog('close');
		    	}else{
		    		$.messager.alert('操作提示',"保存失败,请联系管理员！",'error');
		    	}
		    	
			}
		}); 
	}
}
