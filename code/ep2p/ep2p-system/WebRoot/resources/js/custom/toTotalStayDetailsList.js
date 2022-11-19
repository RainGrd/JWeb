var  totalStay  = {
		pid:"",	
		init:function(){
			var pid = $("#pid").val();
			
		},
		//初始化
		initDataGrid:function(){
			var pid = $("#pid").val();
			var flag = $("#flag").val();
			if(flag==1){
			$('#showTotalStayGrids').datagrid({     
				url:basePath + 'customerController/selectTotalStayDetailsById.shtml',
				width:'100%',
				method:'post',
				queryParams:{'pid':pid,'flag':flag},
				fit:true,
				toolbar:'#pointToolbar1',
				pagination: true,
			    rownumbers:true,
			    sortOrder:'asc',
			    remoteSort:false,
			    columns:view_cus_totalStay,
			    onClickRow:function(rowIndex, rowData){
					$('#showTotalStayGrids').datagrid('clearChecked');
					$('#showTotalStayGrids').datagrid('checkRow',rowIndex);
			    },
			    onLoadSuccess:function(data){
					clearSelectRows('#showTotalStayGrids');
					if($(".showDataListWrap .datagrid-cell-rownumber").length > 1){
						// 设计总计表头
						$(".showDataListWrap .datagrid-cell-rownumber:last").html("总计");
					}else{
						$('#showTotalStayGrids').datagrid("deleteRow",0);
					}
				}
			});
			}
			else{
				$('#showCollectInterestGrids').datagrid({     
					url:basePath + 'customerController/selectTotalStayDetailsById.shtml',
					width:'100%',
					method:'post',
					queryParams:{'pid':pid,'flag':flag},
					fit:true,
					toolbar:'#pointToolbar1',
					pagination: true,
				    rownumbers:true,
				    sortOrder:'asc',
				    remoteSort:false,
				    columns:view_cus_collectInterest,
				    onClickRow:function(rowIndex, rowData){
						$('#showCollectInterestGrids').datagrid('clearChecked');
						$('#showCollectInterestGrids').datagrid('checkRow',rowIndex);
				    },
				    onLoadSuccess:function(data){
						clearSelectRows('#showCollectInterestGrids');
						if($(".showDataListWrap .datagrid-cell-rownumber").length > 1){
							// 设计总计表头
							$(".showDataListWrap .datagrid-cell-rownumber:last").html("总计");
						}else{
							$('#showCollectInterestGrids').datagrid("deleteRow",0);
						}
					}
				});
			}
		},
		//查询
		searchData:function(){
			debugger;
			//序列化表单 
			var obj = jqueryUtil.serializeObject($("#searcher"));
			if(obj.flag=='1'){
				$('#showTotalStayGrids').datagrid({url:BASE_PATH+'customerController/selectTotalStayDetailList.shtml',queryParams:obj});
			}else{
				$('#showCollectInterestGrids').datagrid({url:BASE_PATH+'customerController/selectTotalStayDetailList.shtml',queryParams:obj});
			}
		}
};