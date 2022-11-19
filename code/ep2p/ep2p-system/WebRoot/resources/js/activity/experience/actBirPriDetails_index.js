/**
 * 编号生成规则内容js类
 */
var actBirPriDetails = {
	//初始化
	initDataGrid:function(){
		$('#actBirPriDetailsGrid').datagrid({    
			url:basePath + 'actBirPriItemActivityRelController/selectBirPriItem.shtml',
			width:'100%',
			fit:true,
			method: 'POST',
			title:'获得vip生日特权活动查询',
			toolbar:'#toolbar',
			pagination: true,
		    rownumbers:true,
		    remoteSort:false,
		    fitColumns:true,
			selectOnCheck: true,
			singleSelect: false,
			checkOnSelect: true,
		    columns:actBirPriDetails_model,
		    onLoadSuccess:function(data){
				clearSelectRows('#actBirPriDetailsGrid'); 
				if($("#actBirPriDetailsGrid_dlg .datagrid-cell-rownumber").length > 1){
					// 设计总计表头
					$("#actBirPriDetailsGrid_dlg .datagrid-cell-rownumber:last").html("总计");
				}else{
					//$('#actInvWealthCoopDetailGrid').datagrid("deleteRow",0);
				} 
			}
		});
	},
	// 查询
	searchData:function(){
		jqueryUtil.ajaxSearchPage('#actBirPriDetailsGrid','#searcm');
	},
	// 查询
	searchDataDetail:function(){
		jqueryUtil.ajaxSearchPage('#actBirPriDetailsGrid_det','#searchFromactBirPriDetails');
	},
	// 初始化赠送明细
	initDataGrid_det:function(url){
		// 初始化datagrid
		$('#actBirPriDetailsGrid_det').datagrid({    
			url:url,
			width:'100%',
			fit:true,
			method: 'POST',
			toolbar:'#toolbar_actInvAwaActDetail',
			pagination: true,
		    rownumbers:true,
		    remoteSort:false,
		    fitColumns:true,
			selectOnCheck: true,
			singleSelect: false,
			checkOnSelect: true,
		    columns:actBirPriDetailsGrid_det_model,
		    onLoadSuccess:function(data){
				 
			}
		});
	}
	
};

/**
 * 页面数据初始化
 */
$(function(){
	actBirPriDetails.initDataGrid();
	if($("#activityId").val() != ""){
		actBirPriDetails.searchData();
	}
});
