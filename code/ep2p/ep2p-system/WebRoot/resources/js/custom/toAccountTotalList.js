var  accountTotal  = {
		pid:"",	
		init:function(){
			var pid = $("#pid").val();
			
		},
		//初始化
		initDataGrid:function(){
			var pid = $("#pid").val();
			var flag = $("#flag").val();
			$('#showAccountTotalGrids').datagrid({     
				url:basePath + 'custFundWaterController/selectAccountTotalDetailsById.shtml',
				width:'100%',
				method:'post',
				queryParams:{'pid':pid,'flag':flag},
				fit:true,
				toolbar:'#pointToolbar1',
				pagination: true,
			    rownumbers:true,
			    sortOrder:'asc',
			    remoteSort:false,
			    columns:view_cus_accountTotal,
			    onClickRow:function(rowIndex, rowData){
					$('#showAccountTotalGrids').datagrid('clearChecked');
					$('#showAccountTotalGrids').datagrid('checkRow',rowIndex);
			    },
			    onLoadSuccess:function(data){
					clearSelectRows('#showAccountTotalGrids');
					if($(".showDataListWrap .datagrid-cell-rownumber").length > 1){
						// 设计总计表头
						$(".showDataListWrap .datagrid-cell-rownumber:last").html("总计");
					}else{
						$('#showAccountTotalGrids').datagrid("deleteRow",0);
					}
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