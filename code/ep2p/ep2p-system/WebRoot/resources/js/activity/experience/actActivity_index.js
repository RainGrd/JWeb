/**
 * 系统活动js类
 */
var actActivity = {
	//初始化
	initDataGrid:function(){
		$('#actActivityGrid').datagrid({    
			url:basePath + 'actActivityController/selectAllPage.shtml',
			width:'100%',
			fit:true,
			method: 'POST',
			title:'系统活动列表',
			toolbar:'#toolbar',
			pagination: true,
		    rownumbers:true,
		    remoteSort:false,
		    fitColumns:true,
			selectOnCheck: true,
			singleSelect: false,
			checkOnSelect: true,
		    columns:actActivity_model,
		    onLoadSuccess:function(data){
				clearSelectRows('#actActivityGrid');
			}
		});
	},
	// 查询
	searchData:function(){
		jqueryUtil.ajaxSearchPage('#actActivityGrid','#searcm');
	}
};

/**
 * 页面数据初始化
 */
$(function(){
	var isTaskListOpen = $("#isTaskListOpen").val();
	if(isTaskListOpen && "" != isTaskListOpen){
		$("#status").find("option[value='1']").attr("selected",'selected');
		$('#actActivityGrid').datagrid({    
			url:basePath + 'actActivityController/selectAllPage.shtml',
			width:'100%',
			fit:true,
			method: 'POST',
			title:'系统活动列表',
			toolbar:'#toolbar',
			pagination: true,
		    rownumbers:true,
		    remoteSort:false,
		    fitColumns:true,
			selectOnCheck: true,
			singleSelect: false,
			checkOnSelect: true,
			queryParams:{status:isTaskListOpen},
		    columns:actActivity_model,
		    onLoadSuccess:function(data){
				clearSelectRows('#actActivityGrid');
			}
		});
	}
	actActivity.initDataGrid();
});

window.top["reload_Abnormal_Monitor"]=function(){
	$("#actActivityGrid").datagrid( "load");
};