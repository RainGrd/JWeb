var  customerList  = {
		//初始化
		initDataGrid:function(){
			$('#grid').datagrid({    
				url:basePath + 'customerController/getCustomerSearchList.shtml',
				width:'100%',
				fit:true,
				title:'客户列表查询',
				toolbar:'#toolbar',
				pagination: true,
			    rownumbers:true,
			    sortOrder:'asc',
			    remoteSort:false,
			    columns:customerManager_model,
			    onClickRow:function(rowIndex, rowData){
					$('#grid').datagrid('clearChecked');
					$('#grid').datagrid('checkRow',rowIndex);
			    },
			    onLoadSuccess:function(data){
					clearSelectRows('#grid');
					if($(".userListDiv .datagrid-cell-rownumber").length > 1){
						// 设计总计表头
						$(".userListDiv .datagrid-cell-rownumber:last").html("总计");
					}else{
						$('#grid').datagrid("deleteRow",0);
					}
				}
			});
		},
		
		add:function(){
			window.location.href=BASE_PATH+"customerController/toAdd.shtml";
		},
		//excel导出
		excelPort:function(){
			$.comdownload("#grid","#searcher",
					basePath + "customerController/exportDownLoadFile.shtml");
		},
		
		//查询
		searchData:function(){
			//序列化表单 
			var obj = jqueryUtil.serializeObject($("#searcher"));
			$('#grid').datagrid('load',obj);
		}
};