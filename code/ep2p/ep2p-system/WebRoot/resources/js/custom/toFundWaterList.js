var  waters  = {
		pid:"",	
		init:function(){
			var pid = $("#pid").val();
			
		},
		//初始化
		initDataGrid:function(){
			var pid = $("#pid").val();
			$('#fundWaterGrid').datagrid({     
				url:basePath + 'custFundWaterController/selectWaterType.shtml',
				width:'100%',
				method:'post',
				queryParams:{'pid':pid},
				fit:true,
				toolbar:'#toolbar',
				pagination: true,
			    rownumbers:true,
			    sortOrder:'asc',
			    remoteSort:false,
			    columns:zijinWater,
			    onClickRow:function(rowIndex, rowData){
					$('#fundWaterGrid').datagrid('clearChecked');
					$('#fundWaterGrid').datagrid('checkRow',rowIndex);
			    }
			});
		},
		//查询
		searchData:function(){
			//序列化表单 
			var obj = jqueryUtil.serializeObject($("#searcher1"));
			$('#fundWaterGrid').datagrid({url:BASE_PATH+'custFundWaterController/selectWaterType.shtml',queryParams:obj});
		}
		
};