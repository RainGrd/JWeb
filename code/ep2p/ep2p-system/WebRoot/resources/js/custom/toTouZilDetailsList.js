var  touzi  = {
		pid:"",	
		init:function(){
			var pid = $("#pid").val();
			
		},
		//初始化
		initDataGrid:function(){
			var pid = $("#pid").val();
			$('#showTouziGrids').datagrid({     
				url:basePath + 'custFundWaterController/selectTouziDetailsById.shtml',
				width:'100%',
				method:'post',
				queryParams:{'pid':pid},
				fit:true,
				toolbar:'#pointToolbar1',
				pagination: true,
			    rownumbers:true,
			    sortOrder:'asc',
			    remoteSort:false,
			    columns:view_cus_accountTotal,
			    onClickRow:function(rowIndex, rowData){
					$('#showTouziGrids').datagrid('clearChecked');
					$('#showTouziGrids').datagrid('checkRow',rowIndex);
			    }
			});
		},
		//查询
		searchData:function(){
			//序列化表单 
			var obj = jqueryUtil.serializeObject($("#searcher"));
			$('#showAccountTotalGrids').datagrid({url:BASE_PATH+'custFundWaterController/selectAccountTotalDetailList.shtml',queryParams:obj});
		}
};