/**
 * 编号生成规则内容js类
 */
var pubVipinfo = {
	//初始化
	initDataGrid:function(){
		$('#pubVipinfoGrid').datagrid({    
			url:basePath + 'pubVipinfoController/queryPubVipinfoList.shtml',
			width:'100%',
			fit:true,
			method: 'POST',
			title:'赠送VIP活动列表',
			toolbar:'#toolbar',
			pagination: true,
		    rownumbers:true,
		    remoteSort:false,
		    fitColumns:true,
			selectOnCheck: true,
			singleSelect: false,
			checkOnSelect: true,
		    columns:pubVipinfo_model,
		    onLoadSuccess:function(data){
				clearSelectRows('#pubVipinfoGrid');
			}
		});
	},
	// 打开新增
	openAdd:function(){
		// 跳转到新增页面
		var path = BASE_PATH + "pubVipinfoController/openPubVipinfoAdd.shtml?pid=-1";
		childLayout_addTab(path,'新增VIP赠送活动');
	},
	// 删除 
	remove:function(){
		// 判断是否选中数据
		var rows = $('#pubVipinfoGrid').datagrid('getSelections');
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
	 					url : BASE_PATH+"pubVipinfoController/deleteBatch.shtml?pids="+pids,
	 					async : false,
	 					dataType : "json",
	 					success : function(data) {
	 						if(data.message == 200){
		 						$.messager.alert('操作提示',"删除成功!");
								$("#pubVipinfoGrid").datagrid('reload');
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
		jqueryUtil.ajaxSearchPage('#pubVipinfoGrid','#searcm');
	}
	
};

/**
 * 页面数据初始化
 */
$(function(){
	pubVipinfo.initDataGrid();
});

window.top["reload_Abnormal_Monitor"]=function(){
	$("#pubVipinfoGrid").datagrid( "load");
};