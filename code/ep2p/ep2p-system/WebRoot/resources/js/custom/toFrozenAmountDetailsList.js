var  frozenAmount  = {
		pid:"",	
		init:function(){
			var pid = $("#pid").val();
			
		},
		//初始化
		initDataGrid:function(){
			var pid = $("#pid").val();
			$('#showCusGrids').datagrid({     
				url:basePath + 'customerController/selectFrozenAmountDetailsById.shtml',
				width:'100%',
				method:'post',
				queryParams:{'pid':pid},
				fit:true,
				toolbar:'#pointToolbar1',
				pagination: true,
			    rownumbers:true,
			    sortOrder:'asc',
			    remoteSort:false,
			    columns:view_cus_froZen,
			    onClickRow:function(rowIndex, rowData){
					$('#showCusGrids').datagrid('clearChecked');
					$('#showCusGrids').datagrid('checkRow',rowIndex);
			    },
			    onLoadSuccess:function(data){
					clearSelectRows('#showCusGrids');
					if($(".showDataListWrap .datagrid-cell-rownumber").length > 1){
						// 设计总计表头
						$(".showDataListWrap .datagrid-cell-rownumber:last").html("总计");
					}else{
						$('#showCusGrids').datagrid("deleteRow",0);
					}
				}
			});
		},
		//查询
		searchData:function(){
			//序列化表单 
			var obj = jqueryUtil.serializeObject($("#searcher"));
			$('#showCusGrids').datagrid({url:BASE_PATH+'customerController/selectFrozenAmountDetailList.shtml',queryParams:obj});
		}
};