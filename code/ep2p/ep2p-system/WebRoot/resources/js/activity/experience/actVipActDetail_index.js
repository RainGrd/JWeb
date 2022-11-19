/**
 * 编号生成规则内容js类
 */
var actVipActDetail = {
	//初始化
	initDataGrid:function(){
		$('#actVipActDetailGrid').datagrid({    
			url:basePath + 'actVipActDetailController/selectAllPage.shtml',
			width:'100%',
			fit:true,
			method: 'POST',
			title:'赠送VIP查询',
			toolbar:'#toolbar',
			pagination: true,
		    rownumbers:true,
		    remoteSort:false,
		    fitColumns:true,
			selectOnCheck: true,
			singleSelect: true,
			checkOnSelect: true,
		    columns:actVipActDetail_model,
		    onLoadSuccess:function(data){
				clearSelectRows('#actVipActDetailGrid');
				if($("#actVipActDetailGrid_dlg .datagrid-cell-rownumber").length > 1){
					// 设计总计表头
					$("#actVipActDetailGrid_dlg .datagrid-cell-rownumber:last").html("总计");
				}else{
					$('#actVipActDetailGrid').datagrid("deleteRow",0);
				}
			}
		});
	},
	// 查询
	searchData:function(){
		jqueryUtil.ajaxSearchPage('#actVipActDetailGrid','#searcm');
	}
	
};

/**
 * 页面数据初始化
 */
$(function(){
	actVipActDetail.initDataGrid();
});
