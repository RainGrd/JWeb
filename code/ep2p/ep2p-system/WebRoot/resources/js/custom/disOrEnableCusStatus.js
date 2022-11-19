var cusStatus={
		pid:"",	
		init:function(){
			var pid = $("#pid").val();
			cusStatus.loadRadio();
		},
		//初始化
		initDataGrid:function(){
			var pid = $("#pid").val();
			$('#showCusGrids').datagrid({     
				url:basePath + 'customerController/cusStatusChangeHistoryById.shtml',
				width:'100%',
				method:'post',
				queryParams:{'pid':pid},
				fit:true,
				toolbar:'#batchToolbar',
				pagination: true,
			    rownumbers:true,
			    sortOrder:'asc',
			    remoteSort:false,
			    columns:disOrEnable_Cus_Status,
			    onClickRow:function(rowIndex, rowData){
					$('#showCusGrids').datagrid('clearChecked');
					$('#showCusGrids').datagrid('checkRow',rowIndex);
			    }
			});
		},
		//加载客户详细信息
		loadData:function(){
			var pid =$("#pid").val();
			if("" != pid){
				$.ajax({
					type: "POST",
			    	url : basePath + 'customerController/selectCusHistoryDetailedById.shtml',
			    	data:{data:"{pid:"+pid+"}"},
					dataType: "json",
				    success: function(data){
				    	if(data.message.status ==200){
				    		$("#baseInfo").form('load',data.result);
				    	}else{
				    		$.messager.alert("操作提示","数据加载失败,请联系管理员!","error");
				    	}
					}
				});
			}
		},
		//保存
		save:function(){
			var pid = $("#pid").val();
			//序列化表单 
			var obj = jqueryUtil.serializeObject($("#baseInfo1"));
			var objStr = JSON.stringify(obj);
			$.ajax({
				type: "POST",
		        async:false, 
		    	url : BASE_PATH+"customerController/saveCustomerStatus.shtml",
		    	data:{"data":objStr},
				dataType: "json",
			    success: function(data){
			    	if(data.message.status == 200 ){
			    		$.messager.alert('操作提示',"保存成功！",'success');
			    		window.location.href=BASE_PATH+"customerController/toDisableOrEnableCusStatusList.shtml?pid="+pid;
			    	}else{
			    		$.messager.alert('操作提示',"保存失败！",'error');
			    	}
				}
			}); 
		},
		loadRadio:function(){
			$.post(BASE_PATH+"sysDistionaryContentController/selectByDisctCodeNoPlease.shtml",{"dictCode":"CUS_WIT_STATUS"}, 
				function(ret) {
					if(ret.message.status == 200){
						common.createRadioButtons("cusStatus_td","cusStatus",ret.data);
					}else{
						$.messager.alert('操作提示',"删除失败！",'error');
					}
				},'json'
			);
		}
		
}