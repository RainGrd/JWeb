/**
 * 编号生成规则内容js类
 */
var actExpActDetail = {
	//初始化
	initDataGrid:function(){
		$('#actExpActDetailGrid').datagrid({    
			url:basePath + 'actExpActDetailController/selectAllPage.shtml',
			width:'100%',
			fit:true,
			method: 'POST',
			title:'赠送体验金查询',
			toolbar:'#toolbar',
			pagination: true,
		    rownumbers:true,
		    remoteSort:false,
		    fitColumns:true,
			selectOnCheck: true,
			singleSelect: true,
			checkOnSelect: true,
		    columns:actExpActDetail_model,
		    onLoadSuccess:function(data){
				clearSelectRows('#actExpActDetailGrid');
				if($("#actExpActDetailGrid_dlg .datagrid-cell-rownumber").length > 1){
					// 设计总计表头
					$("#actExpActDetailGrid_dlg .datagrid-cell-rownumber:last").html("总计");
				}else{
					$('#actExpActDetailGrid').datagrid("deleteRow",0);
				}
			}
		});
	},
	// 查询
	searchData:function(){
		jqueryUtil.ajaxSearchPage('#actExpActDetailGrid','#searcm');
	}
	
};

/**
 * 页面数据初始化
 */
$(function(){
	actExpActDetail.initDataGrid();
});
