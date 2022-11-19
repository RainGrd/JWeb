var  bidRecord  = {
		pid:"",	
		init:function(){
			var pid = $("#pid").val();
			
		},
		//初始化
		initDataGrid:function(){
			var pid = $("#pid").val();
			$('#showBidRecordGrids').datagrid({     
				url:basePath + 'bizBorrowDetailController/selectBidRecordDetailsById.shtml',
				width:'100%',
				method:'post',
				queryParams:{'pid':pid},
				fit:true,
				toolbar:'#pointToolbar1',
				pagination: true,
			    rownumbers:true,
			    sortOrder:'asc',
			    remoteSort:false,
			    columns:view_cus_bidRecord,
			    onClickRow:function(rowIndex, rowData){
					$('#showBidRecordGrids').datagrid('clearChecked');
					$('#showBidRecordGrids').datagrid('checkRow',rowIndex);
			    },
			    onLoadSuccess:function(data){
					clearSelectRows('#showBidRecordGrids');
					if($(".showDataListWrap .datagrid-cell-rownumber").length > 1){
						// 设计总计表头
						$(".showDataListWrap .datagrid-cell-rownumber:last").html("总计");
					}else{
						$('#showBidRecordGrids').datagrid("deleteRow",0);
					}
				}
			});
		},
};