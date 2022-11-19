var  points  = {
		pid:"",	
		init:function(){
			var pid = $("#pid").val();
			
		},
		//初始化
		initDataGrid:function(){
			var pid = $("#pid").val();
			$('#showCusGrids').datagrid({     
				url:basePath + 'customerController/selectCusPointDetailsById.shtml',
				width:'100%',
				method:'post',
				queryParams:{'pid':pid},
				fit:true,
				toolbar:'#pointToolbar',
				pagination: true,
			    rownumbers:true,
			    sortOrder:'asc',
			    remoteSort:false,
			    columns:view_cus_point,
			    onClickRow:function(rowIndex, rowData){
					$('#showCusGrids').datagrid('clearChecked');
			    },
			    onLoadSuccess:function(data){
					clearSelectRows('#showCusGrids');
					if($("#showDataListWrap-dlg .datagrid-cell-rownumber").length > 1){
						// 设计总计表头
						$("#showDataListWrap-dlg .datagrid-cell-rownumber:last").html("总计");
					}else{
						$('#showCusGrids').datagrid("deleteRow",0);
					}
				}
			});
		}
};