var  availabPoint  = {
		pid:"",	
		init:function(){
			var pid = $("#pid").val();
			
		},
		//初始化
		initDataGrid:function(){
			var pid = $("#pid").val();
			$('#showCusGrids').datagrid({     
				url:basePath + 'customerController/selectAvailablePointDetailsById.shtml',
				width:'100%',
				method:'post',
				queryParams:{'pid':pid},
				fit:true,
				toolbar:'#pointToolbar',
				pagination: true,
			    rownumbers:true,
			    sortOrder:'asc',
			    remoteSort:false,
			    columns:view_cus_availabPoint,
			    onClickRow:function(rowIndex, rowData){
					$('#showCusGrids').datagrid('clearChecked');
					$('#showCusGrids').datagrid('checkRow',rowIndex);
			    }
			});
		},
		//查询
		searchData:function(){
			//序列化表单 
			var obj = jqueryUtil.serializeObject($("#searcher"));
			$('#showCusGrids').datagrid({url:BASE_PATH+'customerController/selectAvailablePointDetailList.shtml',queryParams:obj});
		}
};