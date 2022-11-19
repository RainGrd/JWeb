/**
 * 编号生成规则内容js类
 */
var actInvWealthCoopDetail = {
	//初始化
	initDataGrid:function(){
		$('#actInvWealthCoopDetailGrid').datagrid({    
			url:basePath + 'actInvWealthCoopDetailController/selectAllPage.shtml',
			width:'100%',
			fit:true,
			method: 'POST',
			title:'获得财富合伙人活动查询',
			toolbar:'#toolbar',
			pagination: true,
		    rownumbers:true,
		    remoteSort:false,
		    fitColumns:true,
			selectOnCheck: true,
			singleSelect: false,
			checkOnSelect: true,
		    columns:actInvWealthCoopDetail_model,
		    onLoadSuccess:function(data){
				clearSelectRows('#actInvWealthCoopDetailGrid');  
				if($("#actInvWealthCoopDetail_dlg .datagrid-cell-rownumber").length > 1){
					// 设计总计表头
					$("#actInvWealthCoopDetail_dlg .datagrid-cell-rownumber:last").html("总计");
				}else{
					//$('#actInvWealthCoopDetailGrid').datagrid("deleteRow",0);
				} 
			}
		});
	},
	// 查询
	searchData:function(){
		jqueryUtil.ajaxSearchPage('#actInvWealthCoopDetailGrid','#searcm');
	} 
};

/**
 * 页面数据初始化
 */
$(function(){
	actInvWealthCoopDetail.initDataGrid();
});
