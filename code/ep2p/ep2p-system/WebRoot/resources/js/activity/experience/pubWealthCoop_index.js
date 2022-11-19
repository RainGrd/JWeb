/**
 * 编号生成规则内容js类
 */
var pubWealthCoop = {
	//初始化
	initDataGrid:function(){
		$('#pubWealthCoopGrid').datagrid({    
			url:basePath + 'pubWealthCoopController/queryPubWealthCoopList.shtml',
			width:'100%',
			fit:true,
			method: 'POST',
			title:'财富合伙人信息列表',
			toolbar:'#toolbar',
			pagination: true,
		    rownumbers:true,
		    remoteSort:false,
		    fitColumns:true,
			selectOnCheck: true,
			singleSelect: false,
			checkOnSelect: true,
		    columns:pubWealthCoop_model,
		    onLoadSuccess:function(data){
				clearSelectRows('#pubWealthCoopGrid');
				if($("#actInvWealthCoopDetail_dlg .datagrid-cell-rownumber").length > 1){
					// 设计总计表头
					$("#actInvWealthCoopDetail_dlg .datagrid-cell-rownumber:last").html("总计");
				}else{
					//$('#actInvWealthCoopDetailGrid').datagrid("deleteRow",0);
				} 
			}
		});
	},
	// 打开新增
	openAdd:function(){
		// 跳转到新增页面
		var path = BASE_PATH + "pubWealthCoopController/openPubWealthCoopAdd.shtml?pid=-1";
		childLayout_addTab(path,'新增财富合伙人活动');
	},
	// 删除 
	remove:function(){
		// 判断是否选中数据
		var rows = $('#pubWealthCoopGrid').datagrid('getSelections');
		if ( rows.length == 0 ) {
	 		$.messager.alert("操作提示","请选择数据");
			return;
		}
		var myDate= new Date().getTime(); 
		//判断活动是否有人参与
		for(var i=0;i<rows.length;i++){
			var endTime =  new Date(rows[i].endTime).getTime(); 
			var startTime = new Date(rows[i].startTime).getTime();
			 if(myDate > startTime){  
				$.messager.alert('操作提示',"只能删除未开始的活动!");
				return;
			}
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
	 					url : BASE_PATH+"pubWealthCoopController/deleteBatch.shtml?pids="+pids,
	 					async : false,
	 					dataType : "json",
	 					success : function(data) {
	 						if(data.message == 200){
		 						$.messager.alert('操作提示',"删除成功!");
								$("#pubWealthCoopGrid").datagrid('reload');
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
		jqueryUtil.ajaxSearchPage('#pubWealthCoopGrid','#searcm');
	}
	
};

/**
 * 页面数据初始化
 */
$(function(){
	pubWealthCoop.initDataGrid();	
});

window.top["reload_Abnormal_Monitor"]=function(){
	$("#pubWealthCoopGrid").datagrid("load");
};