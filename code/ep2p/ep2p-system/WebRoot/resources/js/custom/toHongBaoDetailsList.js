var  hongbao  = {
		pid:"",	
		init:function(){
			var pid = $("#pid").val();
			
		},
		//初始化
		initDataGrid:function(){
			var pid = $("#pid").val();
			$('#showCusHongbaoGrids').datagrid({     
				url:basePath + 'customerController/selectHongbaoDetailsById.shtml',
				width:'100%',
				method:'post',
				queryParams:{'pid':pid},
				fit:true,
				toolbar:'#pointToolbar',
				pagination: true,
			    rownumbers:true,
			    sortOrder:'asc',
			    remoteSort:false,
			    columns:view_cus_hongbao,
			    onClickRow:function(rowIndex, rowData){
					$('#showCusHongbaoGrids').datagrid('clearChecked');
					$('#showCusHongbaoGrids').datagrid('checkRow',rowIndex);
			    },
			    onLoadSuccess:function(data){
					clearSelectRows('#showCusHongbaoGrids');
					if($(".showDataListWrap .datagrid-cell-rownumber").length > 1){
						// 设计总计表头
						$(".showDataListWrap .datagrid-cell-rownumber:last").html("总计");
					}else{
						$('#showCusHongbaoGrids').datagrid("deleteRow",0);
					}
				}
			});
		}
};