/**
 * 客户资金流水js类
 */
var custFundWater = {
		//初始化
		initDataGrid:function(){
			$('#custFundWaterGrid').datagrid({    
				url:basePath+'custFundWaterController/custFundWaterListData.shtml',
				width:'100%',
				fit:true,
				title:'客户资金流水列表',
				toolbar:'#toolbar',
				pagination: true,
			    rownumbers:true,
			    sortOrder:'asc',
			    remoteSort:false,
			    columns:custFundWaterManager_model,
			    onClickRow:function(rowIndex, rowData){
					$('#custFundWaterGrid').datagrid('clearChecked');
					$('#custFundWaterGrid').datagrid('checkRow',rowIndex);
			    }
			});
		},
		
		// 新增
		add:function(){
			$("<div id='addDialog' /> ").dialog({
				href:basePath + "custFundWaterController/toAdd.shtml",
				title:"客户资金流水-新增",
				method:'post',
				width:'500',
				data:{pid:pid},
				height:'350',
				modal: true,
				buttons:[{
					text:'保存',
					iconCls:'icon-save',
					handler:custFundWater.save
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
			var rows = $('#custFundWaterGrid').datagrid('getSelections');
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
						$.post(BASE_PATH+"custFundWaterController/deleteBtach.shtml",{pid:pId}, 
							function(ret) {
								if(ret.message.status == 200){
									$.messager.alert('操作提示',"删除成功！",'success');
									$("#custFundWaterGrid").datagrid('load');
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
			if(!$("#custFundWaterAdd").form('validate')){
				return ;
			}
			var obj = jqueryUtil.serializeObject($("#custFundWaterAdd"));
			var objStr = JSON.stringify(obj);
			$.ajax({
				type: "POST",
		        async:false, 
		    	url : BASE_PATH+"custFundWaterController/batchAudit.shtml?pid="+obj.pid+"&auditStatus="+obj.auditStatus+"&witDesc="+obj.witDesc,
		    	//data:{pid:obj.pid,auditStatus:obj.auditStatus,witDesc:obj.witDesc},
				dataType: "json",
			    success: function(data){
			    	if(data.message.status == 200 ){
			    		$.messager.alert('操作提示',"审核成功！",'success');
			    		$('#custFundWaterGrid').datagrid('reload'); 
			    		$("#toBatchAudit").dialog('close');
			    	}else{
			    		$.messager.alert('操作提示',"审核失败,请联系管理员！",'error');
			    	}
			    	
				}
			}); 
		},
		searchData:function(){
			var obj = jqueryUtil.serializeObject($("#searcm"));
			$('#custFundWaterGrid').datagrid('load',obj);
		},
		cleanData:function(){
			$('#searcm').form('reset');
		},
		//跳转至数据下载页面
		toDownloadPage:function(){
			$.comdownload("#custFundWaterGrid","#searcm",
					basePath + "custFundWaterController/exportDownLoadFile.shtml");
		}
}


$(document).ready(function(){
	custFundWater.initDataGrid();			
});
