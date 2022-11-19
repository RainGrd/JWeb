var giveVip={
		pid:"",	
		init:function(){
			cusHistoryView.pid = $("#pid").val();
			 
		},
		//初始化
		initDataGrid:function(){
			var pid = $("#pid").val();
			$('#showCusGrids').datagrid({     
				url:basePath + 'customerController/getVipHistoryDetailedById.shtml',
				width:'100%',
				method:'post',
				queryParams:{'pid':pid},
				fit:true,
				toolbar:'#batchToolbar',
				pagination: true,
			    rownumbers:true,
			    sortOrder:'asc',
			    remoteSort:false,
			    columns:giveVIP_Model,
			    onClickRow:function(rowIndex, rowData){
					$('#showCusGrids').datagrid('clearChecked');
					$('#showCusGrids').datagrid('checkRow',rowIndex);
			    }
			});
		},
		//加载赠送vip详情
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
				    		$("#toGiveVipFrom").form('load',data.result);
				    	}else{
				    		$.messager.alert("操作提示","数据加载失败,请联系管理员!","error");
				    	}
					}
				});
			}
		},
		//保存
		save:function(){
			//获取第一行数据
			var rows = $('#showCusGrids').datagrid('getRows');
			debugger;
//			var vipInfoId = rows[0].tpvId;
			var vipInfoId = "";
			var pid = $("#pid").val();
			var largessValue = $("#largessValue").val();
			var larVipWatDesc = $("#larVipWatDesc").val();
			//序列化表单 
			var obj = jqueryUtil.serializeObject($("#toGiveVipFrom"));
			var objStr = JSON.stringify(obj);
			$.ajax({
				type: "POST",
		        async:false, 
		    	url : BASE_PATH+"customerController/saveGiveVip.shtml",
		    	data:{"data":objStr,"largessValue":largessValue,"vipInfoId":vipInfoId},
				dataType: "json",
			    success: function(data){
			    	if(data.message.status == 200 ){
			    		$.messager.alert('操作提示',"保存成功！",'success');
			    		window.location.href=BASE_PATH+"customerController/toGiveVipList.shtml?pid="+pid;
			    	}else{
			    		$.messager.alert('操作提示',"保存失败！",'error');
			    	}
				}
			}); 
		}
}