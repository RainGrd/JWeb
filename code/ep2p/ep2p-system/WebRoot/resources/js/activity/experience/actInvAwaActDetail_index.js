/**
 * 投资奖励活动查询js类
 */
var actInvAwaActDetail = {
	//初始化
	initDataGrid:function(){
		$('#actInvAwaActDetailGrid').datagrid({    
			url:basePath + 'actInvAwaActDetailController/selectAllPage.shtml',
			width:'100%',
			fit:true,
			method: 'POST',
			title:'获得投资奖励活动查询',
			toolbar:'#toolbar',
			pagination: true,
		    rownumbers:true,
		    remoteSort:false,
		    fitColumns:true,
			selectOnCheck: true,
			singleSelect: false,
			checkOnSelect: true,
		    columns:actInvAwaActDetail_model,
		    onLoadSuccess:function(data){
				clearSelectRows('#actInvAwaActDetailGrid');
				if($("#actInvAwaActDetailGrid_dlg .datagrid-cell-rownumber").length > 1){
					// 设计总计表头
					$("#actInvAwaActDetailGrid_dlg .datagrid-cell-rownumber:last").html("总计");
				}else{
					$('#actInvAwaActDetailGrid').datagrid("deleteRow",0);
				}
			}
		});
	},
	// 查询
	searchData:function(){
		jqueryUtil.ajaxSearchPage('#actInvAwaActDetailGrid','#searcm');
	}
	
};

/**
 * 页面数据初始化
 */
$(function(){
	actInvAwaActDetail.initDataGrid();
});
