var cusHistoryView={
		pid:"",	
		init:function(){
			cusHistoryView.pid = $("#pid").val();
			 
		},
		//初始化
		initDataGrid:function(){
			var pid = $("#pid").val();
			$('#showCusGrids').datagrid({    
				url:basePath + 'customerController/cusHistoryDetailedById.shtml',
				width:'100%',
				method:'post',
				queryParams:{'pid':pid},
				fit:true,
				toolbar:'#batchToolbar',
				pagination: true,
			    rownumbers:true,
			    sortOrder:'asc',
			    remoteSort:false,
			    columns:customerInfo_Model,
			    onClickRow:function(rowIndex, rowData){
					$('#showCusGrids').datagrid('clearChecked');
					$('#showCusGrids').datagrid('checkRow',rowIndex);
			    }
			});
		},
		//加载客服历史详情
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
		}
}