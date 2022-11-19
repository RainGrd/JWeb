/**
 * 系统日志js类
 */
var log = {
		//初始化
		initDataGrid:function(){
			$('#logGrid').datagrid({    
				url:basePath + 'sysLogController/logList.shtml',
				width:'100%',
				fit:true,
				title:'系统日志列表',
				toolbar:'#toolbar',
				pagination: true,
			    rownumbers:true,
			    sortOrder:'asc',
			    remoteSort:false,
			    columns:logManager_model,
			    onClickRow:function(rowIndex, rowData){
					$('#logGrid').datagrid('clearChecked');
					$('#logGrid').datagrid('checkRow',rowIndex);
			    }
			});
		},
		// 新增
		add:function(){
			$("<div id='addDialog' /> ").dialog({
				href:basePath + "sysLogController/toAdd.shtml",
				title:"系统日志-新增",
				method:'post',
				width:'500',
				height:'350',
				modal: true,
				buttons:[{
					text:'保存',
					iconCls:'icon-save',
					handler:log.save
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
			var rows = $('#logGrid').datagrid('getSelections');
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
						$.post(BASE_PATH+"sysLogController/deleteBtach.shtml",{pid:pId}, 
							function(ret) {
								if(ret.message.status == 200){
									$.messager.alert('操作提示',"删除成功！",'success');
									$("#logGrid").datagrid('load');
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
			if(!$("#logAdd").form('validate')){
				return ;
			}
			
			var obj = jqueryUtil.serializeObject($("#logAdd"));
			/*//验证编码是否存在 
			if(!distionary.validateCode(obj.dictCode)){
				return;
			}*/
			var objStr = JSON.stringify(obj);
			$.ajax({
				type: "POST",
		        async:false, 
		    	url : BASE_PATH+"sysLogController/save.shtml",
		    	data:{"data":objStr},
				dataType: "json",
			    success: function(data){
			    	if(data.message.status == 200 ){
			    		$.messager.alert('操作提示',"保存成功！",'success');
			    		$('#logGrid').datagrid('reload'); 
			    		$("#addDialog").dialog('close');
			    	}else{
			    		$.messager.alert('操作提示',"保存失败,请联系管理员！",'error');
			    	}
			    	
				}
			}); 
		},
		searchData:function(){
			//var obj = obj.name;
			var saccountNo = $("input[name='accountNo']").val();
			var sname = $("input[name='name']").val();
			var screateTime = $("input[name='createTime']").val();
			var sstatus = $("input[name='status']").val();
			var soperType = $("input[name='operType']").val();
			$('#logGrid').datagrid('load',{accountNo:saccountNo,name:sname,createTime:screateTime,status:sstatus,operType:soperType});
		}
}


$(document).ready(function(){
	log.initDataGrid();	
});
