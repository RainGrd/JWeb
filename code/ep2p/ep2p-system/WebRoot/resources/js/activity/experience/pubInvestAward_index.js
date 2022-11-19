/**
 * 编号生成规则内容js类
 */
var pubInvestAward = {
	//初始化
	initDataGrid:function(){
		$('#pubInvestAwardGrid').datagrid({    
			url:basePath + 'pubInvestAwardController/queryPubInvestAwardList.shtml',
			width:'100%',
			fit:true,
			method: 'POST',
			title:'投资奖励信息列表',
			toolbar:'#toolbar',
			pagination: true,
		    rownumbers:true,
		    remoteSort:false,
		    fitColumns:true,
			selectOnCheck: true,
			singleSelect: false,
			checkOnSelect: true,
		    columns:pubInvestAward_model,
		    onLoadSuccess:function(data){
				clearSelectRows('#pubInvestAwardGrid');
			}
		});
	},
	// 打开新增
	openAdd:function(){
		// 跳转到新增页面
		var path = BASE_PATH + "pubInvestAwardController/openPubInvestAwardAdd.shtml?pid=-1";
		childLayout_addTab(path,'新增投资奖励活动');
	},
	// 删除 
	remove:function(){
		// 判断是否选中数据
		var rows = $('#pubInvestAwardGrid').datagrid('getSelections');
		if ( rows.length == 0 ) {
	 		$.messager.alert("操作提示","请选择数据");
			return;
		}
		// 获取选中pid 
	 	var pids = "";
		for(var i=0;i<rows.length;i++){
			// 判断状态
			if(rows[i].actStatus != 1){
				$.messager.alert("操作提示","只能删除未进行的活动");
				return;
			}
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
	 					url : BASE_PATH+"pubInvestAwardController/deleteBatch.shtml?pids="+pids,
	 					async : false,
	 					dataType : "json",
	 					success : function(data) {
	 						if(data.message == 200){
		 						$.messager.alert('操作提示',"删除成功!");
								$("#pubInvestAwardGrid").datagrid('reload');
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
		jqueryUtil.ajaxSearchPage('#pubInvestAwardGrid','#searcm');
	}
	
};

/**
 * 页面数据初始化
 */
$(function(){
	pubInvestAward.initDataGrid();	
});

window.top["reload_Abnormal_Monitor"]=function(){
	$("#pubInvestAwardGrid").datagrid( "load");
};