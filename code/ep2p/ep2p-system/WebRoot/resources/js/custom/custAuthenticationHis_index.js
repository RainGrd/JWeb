/**
 * 编号生成规则内容js类
 */
var custAuthenticationHis = {
	//初始化
	initDataGrid:function(){
		$('#custAuthenticationHisGrid').datagrid({    
			url:basePath + 'authenVipCustomerController/selectAuthenticationByCondition.shtml',
			width:'100%',
			fit:true,
			method: 'POST',
			title:'实名认证列表',
			toolbar:'#toolbar',
			pagination: true,
		    rownumbers:true,
		    remoteSort:false,
		    fitColumns:true,
			selectOnCheck: true,
			singleSelect: true,
			checkOnSelect: true,
		    columns:custAuthenticationHis_Model,
		    onLoadSuccess:function(data){
				clearSelectRows('#custAuthenticationHisGrid');
			}
		});
	},
	// 查询
	searchData:function(){
		jqueryUtil.ajaxSearchPage('#custAuthenticationHisGrid','#searcm');
	}
	
};

/**
 * 页面数据初始化
 */
$(function(){
	custAuthenticationHis.initDataGrid();	
});

window.top["reload_Abnormal_Monitor"]=function(){
	$("#custAuthenticationHisGrid").datagrid("load");
};