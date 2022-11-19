/**
 * 条件设定js类
 */
var pubCondition = {
	//初始化
	initDataGrid:function(){
		$('#pubConditionGrid').datagrid({    
			url:basePath + 'pubConditionController/queryPubConditionList.shtml',
			width:'100%',
			fit:true,
			method: 'POST',
			title:'条件设定列表',
			toolbar:'#toolbar',
			pagination: true,
		    rownumbers:true,
		    remoteSort:false,
		    fitColumns:true,
			selectOnCheck: true,
			singleSelect: false,
			checkOnSelect: true,
		    columns:pubCondition_model,
		    onLoadSuccess:function(data){
				clearSelectRows('#pubConditionGrid');
			}
		});
	},
	// 打开新增
	openAdd:function(){
		// 跳转到新增页面
		var path = BASE_PATH + "pubConditionController/openPubConditionAdd.shtml?pid=-1";
		childLayout_addTab(path,'新增条件设定');
	},
	// 删除 
	remove:function(){
		// 判断是否选中数据
		var rows = $('#pubConditionGrid').datagrid('getSelections');
		if ( rows.length == 0 ) {
	 		$.messager.alert("操作提示","请选择数据");
			return;
		}
		// 获取选中pid 
	 	var pids = "";
		for(var i=0;i<rows.length;i++){
	  		if(i==0){
	  			pids+=rows[i].pid;
	  		}else{
	  			pids+=","+rows[i].pid;
	  		}
	  	}
		$.messager.confirm("操作提示","确定删除选中的数据吗?",
			function(r) {
	 			if(r){
	 				$.ajax( {
	 					type : "post",
	 					url : BASE_PATH+"pubConditionController/deleteBatch.shtml?pids="+pids,
	 					async : false,
	 					dataType : "json",
	 					success : function(data) {
	 						if(data.message == 200){
		 						$.messager.alert('操作提示',"删除成功!");
								$("#pubConditionGrid").datagrid('reload');
	 						}else{
	 							$.messager.alert('操作提示',"删除失败,请重新操作!","error");
	 						}
	 					}
	 				}); 
	 			}
		});
		
	},
	// 查询
	searchData:function(){
		jqueryUtil.ajaxSearchPage('#pubConditionGrid','#searcm');
	}
	
};

/**
 * 页面数据初始化
 */
$(function(){
	pubCondition.initDataGrid();
});

window.top["reload_Abnormal_Monitor"]=function(){
	$("#pubConditionGrid").datagrid( "load");
};