/**
 * vip生日特权js
 * 
 */
var birPriList = {
	//初始化
	initDataGrid:function(){
		$('#birPriGrid').datagrid({    
			url:basePath + 'actActivityController/toBirPriListData.shtml',
			width:'100%',
			fit:true,
			method: 'POST',
			title:'vip生日特权设置',
			toolbar:'#toolbar',
			pagination: true,
		    rownumbers:true,
		    remoteSort:false,
		    fitColumns:true,
			selectOnCheck: true,
			singleSelect: false,
			checkOnSelect: true,
		    columns:birPri_Model, 
			onClickRow : function(rowIndex, rowData) {
				$('#birPriGrid').datagrid('clearChecked');
				$('#birPriGrid').datagrid('checkRow', rowIndex);
			}
		});
	},
	// 打开新增
	openAdd:function(){
		// 跳转到新增页面
		var path = BASE_PATH + "actActivityController/toBirPriPage.shtml?pid=-1";
		childLayout_addTab(path,'新增/编辑VIP生日特权');
	},
	// 删除 
	remove:function(){
		// 判断是否选中数据
		var rows = $('#birPriGrid').datagrid('getSelections');
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
		var myDate= new Date().getTime(); 
		//判断活动是否有人参与
		for(var i=0;i<rows.length;i++){
			var endTime =  new Date(rows[i].endDate).getTime(); 
			var startTime = new Date(rows[i].startDate).getTime();
			 if(myDate > startTime){  
				$.messager.alert('操作提示',"只能删除未开始的活动!");
				return;
			}
	  	}
		$.messager.confirm("操作提示","确定删除选中的数据吗?",
			function(r) {
	 			if(r){
	 				$.ajax( {
	 					type : "post",
	 					url : BASE_PATH+"actActivityController/deleteBatch.shtml?pids="+pids,
	 					async : false,
	 					dataType : "json",
	 					success : function(data) {
	 						if(data.message == 200){
		 						$.messager.alert('操作提示',"删除成功!");
								$("#birPriGrid").datagrid('reload');
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
		jqueryUtil.ajaxSearchPage('#birPriGrid','#searcm');
//		var obj = jqueryUtil.serializeObject($("#searcm"));
//		var objStr = JSON.stringify(obj);
//		$('#birPriGrid').datagrid('load', {
//			data : objStr
//		}); 
	}
	
};

/**
 * 页面数据初始化
 */
$(function(){
	birPriList.initDataGrid();	
}); 

window.top["reload_Abnormal_Monitor"]=function(){
	$("#birPriGrid").datagrid("load");
};