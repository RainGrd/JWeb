/**
 * 编号生成规则内容js类
 */
var actRedpActDetail = {
	//初始化
	initDataGrid:function(){
		$('#actRedpActDetailGrid').datagrid({    
			url:basePath + 'actRedpActDetailController/selectAllPage.shtml',
			width:'100%',
			fit:true,
			method: 'POST',
			title:'红包活动查询',
			toolbar:'#toolbar',
			pagination: true,
		    rownumbers:true,
		    remoteSort:false,
		    fitColumns:true,
			selectOnCheck: true,
			singleSelect: false,
			checkOnSelect: true,
		    columns:actRedpActDetail_model,
		    onLoadSuccess:function(data){
				clearSelectRows('#actRedpActDetailGrid');
				if($("#actRedpActDetailGrid_dlg .datagrid-cell-rownumber").length > 1){
					// 设计总计表头
					$("#actRedpActDetailGrid_dlg .datagrid-cell-rownumber:last").html("总计");
				}else{
					$('#actRedpActDetailGrid').datagrid("deleteRow",0);
				}
			}
		});
	},
	// 查询
	searchData:function(){
		jqueryUtil.ajaxSearchPage('#actRedpActDetailGrid','#searcm');
	}
};

/**
 * 页面数据初始化
 */
$(function(){
	actRedpActDetail.initDataGrid();
});
