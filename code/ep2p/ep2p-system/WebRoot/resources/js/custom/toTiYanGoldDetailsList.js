var  tiyanjinGold  = {
		pid:"",	
		init:function(){
			var pid = $("#pid").val();
			
		},
		//初始化
		initDataGrid:function(){
			var pid = $("#pid").val();
			$('#showCusTiYanJinGrids').datagrid({     
				url:basePath + 'customerController/selectTiYanJinDetailsById.shtml',
				width:'100%',
				method:'post',
				queryParams:{'pid':pid},
				fit:true,
				toolbar:'#pointToolbar1',
				pagination: true,
			    rownumbers:true,
			    sortOrder:'asc',
			    remoteSort:false,
			    columns:view_cus_tiyanGold,
			    onClickRow:function(rowIndex, rowData){
					$('#showCusTiYanJinGrids').datagrid('clearChecked');
					$('#showCusTiYanJinGrids').datagrid('checkRow',rowIndex);
			    },
			    onLoadSuccess:function(data){
					clearSelectRows('#showCusTiYanJinGrids');
					if($(".showDataListWrap .datagrid-cell-rownumber").length > 1){
						// 设计总计表头
						$(".showDataListWrap .datagrid-cell-rownumber:last").html("总计");
					}else{
						$('#showCusTiYanJinGrids').datagrid("deleteRow",0);
					}
				}
			});
		},
		//查询
		searchData:function(){
			//序列化表单 
			var obj = jqueryUtil.serializeObject($("#searcher"));
			$('#showCusTiYanJinGrids').datagrid({url:BASE_PATH+'customerController/selectTiYanJinDetailList.shtml',queryParams:obj});
		}
};