var  jingyan  = {
		pid:"",	
		init:function(){
			var pid = $("#pid").val();
			
		},
		//初始化
		initDataGrid:function(){
			var pid = $("#pid").val();
			$('#showJingYanCusGrids').datagrid({     
				url:basePath + 'customerController/selectJingyanDetailsById.shtml',
				width:'100%',
				method:'post',
				queryParams:{'pid':pid},
				fit:true,
				toolbar:'#pointToolbar',
				pagination: true,
			    rownumbers:true,
			    sortOrder:'asc',
			    remoteSort:false,
			    columns:view_cus_jingyan,
			    onClickRow:function(rowIndex, rowData){
					$('#showJingYanCusGrids').datagrid('clearChecked');
					$('#showJingYanCusGrids').datagrid('checkRow',rowIndex);
			    },
			    onLoadSuccess:function(data){
					clearSelectRows('#showJingYanCusGrids');
					if($("#showDataListWrap-dlg .datagrid-cell-rownumber").length > 1){
						// 设计总计表头
						$("#showDataListWrap-dlg .datagrid-cell-rownumber:last").html("总计");
					}else{
						$('#showJingYanCusGrids').datagrid("deleteRow",0);
					}
				}
			});
		}
};