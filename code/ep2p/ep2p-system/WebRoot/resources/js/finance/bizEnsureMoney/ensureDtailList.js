/**
 * 备付金明细 js 类
 */
var ensureDetailList = {
		//加载备付金明细列表
		initGrid:function(){
			$('#ensureDetailListTable').datagrid({    
				url:basePath +"bizEnsureMoneyDetailController/selectForTypeToList.shtml?isDetail=yes",
				width:'100%',
				fit:true,
				toolbar:'#biz_ensure_index_toolbar',
				pagination: true,
				rownumbers: true,
			    sortOrder:'asc',
			    remoteSort:false,
			    columns:ensure_detail_model,
			    onClickRow:function(rowIndex, rowData){
					$('#bizEnsureIndexGrid').datagrid('clearChecked');
					$('#bizEnsureIndexGrid').datagrid('checkRow',rowIndex);
					
			    },
			    onLoadSuccess:function(data){
			    	if($("#ensureDetailListTableDiv .datagrid-cell-rownumber").length > 1){
						// 设计总计表头
						$("#ensureDetailListTableDiv .datagrid-cell-rownumber:last").html("总计");
					}else{
						$('#bizEnsureIndexGrid').datagrid("deleteRow",0);
					}
			    }

			});
		},
		// 查询
		searchData:function(){
			var obj = jqueryUtil.serializeObject($("#ensure_detail_list_form"));
			$('#ensureDetailListTable').datagrid('load',obj);
		},
		//跳转至数据下载页面
		toDownloadPage:function(){
			$.comdownload("#ensureDetailListTable","#ensure_detail_list_form",basePath + "bizEnsureMoneyDetailController/exportDownLoadFile.shtml");
		},
		cleanData:function(){
			$('#ensure_detail_list_form').form('reset');
		}
}