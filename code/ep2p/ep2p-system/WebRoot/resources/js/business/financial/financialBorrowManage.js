/**
 * 理财产品管理列表
 */
var financialBorrowManage = {
		// 初始化客户列表数据table
		initCusTomerDataGrid:function(){
			$('#cusTomerGrid').datagrid({    
				url:basePath + 'customerController/selectPage.shtml?status=1&isBlacklist=1&isFreeze=1', 
				width:'100%',
				fit:true,
				title:'客户列表',
				toolbar:'#toolbar',
				pagination: true,
			    rownumbers:true,
			    sortOrder:'asc',
			    remoteSort:false,
			    columns:financialCusTomeManager_Model,
			    onClickRow:function(rowIndex, rowData){
					$('#cusTomerGrid').datagrid('clearChecked');
					$('#cusTomerGrid').datagrid('checkRow',rowIndex);
			    }
			});
		},
		//初始化
		initDataGrid:function(){
			$('#financialBorrowManageGrid').datagrid({    
				url:basePath + 'bizBorrowController/searchBorrowList.shtml?borrowType=3&status=1&borStatus=1',
				width:'100%',
				fit:true,
				title:'理财产品管理',
				toolbar:'#toolbar',
				pagination: true,
			    rownumbers:true,
			    sortOrder:'asc',
			    fitColumns: true,
			    remoteSort:false,
			    columns:financialBorrowManage_Model,
			    onLoadSuccess:function(data){
			    	if($("#borrowSearch_context .datagrid-cell-rownumber").length > 1){
						// 设计总计表头
						$("#borrowSearch_context .datagrid-cell-rownumber:last").html("总计");
					}else{
						$('#borrowSearch_context').datagrid("deleteRow",0);
					}
			    },
			    onClickRow:function(rowIndex, rowData){
					$('#financialBorrowManageGrid').datagrid('clearChecked');
					$('#financialBorrowManageGrid').datagrid('checkRow',rowIndex);
			    }
			});
		},
		openAdd:function(){
			var customerId =$("#customerId").val();
			// 跳转到新增页面
			var path = BASE_PATH + "bizBorrowController/toFinancialBorrowCusList.shtml";
			childLayout_addTab(path,'选择客户');
		},
		resetForm:function(){
			$("#searcm").form("reset");
		},
		searchCusTomer:function(){
			jqueryUtil.ajaxSearchPage("#cusTomerGrid","#searcm");
		},
		searchData:function(){
			jqueryUtil.ajaxSearchPage("#financialBorrowManageGrid","#searcm");
		},
		toDelete:function(pid){
			$.messager.confirm("操作提示","确认删除当前数据？", function (data) {
				if(data){
					$.ajax({
						type : "POST",
						url : BASE_PATH
								+ "bizBorrowController/deleteBorrowManageByPid.shtml?pid="+ pid, 
						dataType : "json",
						success : function(data) {  
							if (data.message == "200") { 
								$.messager.alert('操作提示',"删除成功!",'success');    
								// 刷新页面
			 					reloadTabGrid("理财产品管理"); 
							}
						},
						error : function() { 
								$.messager.alert('操作提示',"删除失败!",'error');      
						}
					}); 
				}
			})
		},
		//跳转到 发布页面
		toPublishPage:function(pid){
//			var path = BASE_PATH + "bizBorrowController/toPublishPage.shtml?pid="+pid;
//			childLayout_addTab(path,'理财产品发布');
			$("<div id='detailDialog' /> ").dialog({
				href: basePath + "bizBorrowController/toPublishPage.shtml?pid="+pid,
				title:"理财产品发布",
				method:'post',
				width:'600',
				height:'350',
				modal: true,
				buttons:[{
					text:'发布',
					iconCls:'icon-save',
					handler:financialBorrowManage.publish
				},{
					text:'关闭',
					iconCls:'icon-cancel',
					handler:function(){
						$("#detailDialog").dialog('close');
					}
				}], 
				onClose : function() {
						$(this).dialog('destroy');
					}
			});
		},
		//预发布
		publish:function(){ 
			if(!$("#baseInfo").form('validate')){
				return ;
			} 
			$("#publish").show();
			 $('#publish').dialog({
	                title: '确认发布', 
	                collapsible: false,
	                minimizable: false,
	                maximizable: false,
	                resizable: false,
	                width: 400,
	                height: 330,
	                modal: true
	            });
			
			
//			var url = BASE_PATH + "bizBorrowController/publish.shtml?status=1";  
//			// 验证表单必填项
//			if(!$("#baseInfo").form('validate')){
//				return ;
//			} 
//			$("#baseInfo").form('submit', {
//				url : url,
//				success : function(data) { 
//					var result = eval("("+data+")");
//	 				if(result.message == 200){   
//		 					$.messager.alert('操作提示','操作成功','info',function(){   
//		 						window.location.href = BASE_PATH
//								+ "bizBorrowController/financialBorrowManagePublishPreview.shtml?pid="+result.borrow.pid; 
//	 						}); 
//	 				}else{
//	 					$.messager.alert('操作提示',"操作失败,请重新操作!",'error');
//	 				}  
//				}
//			})
		},
		//确认发布确认
		publishConfirm:function(){
			var pid =  $("#pid").val();
			var url = BASE_PATH + "bizBorrowController/publish.shtml?pid="+pid; 
			$("#baseInfo").form('submit', {
				url : url,
				success : function(data) { 
					var result = eval("("+data+")");
	 				if(result.message == 200){  
		 					$.messager.alert('操作提示','发布成功','info'); 
		 					// 刷新页面
		 					reloadTabGrid("理财产品管理");;
		 					// 关闭当前页面
		 					$("#publish").dialog('close');
		 					$("#detailDialog").dialog('close');
		 					
	 				}else{
	 					$.messager.alert('操作提示',"操作失败,请重新操作!",'error');
	 				}  
				}
			});
		},
		//确认发布取消
		publishCancel:function(){ 
			// 关闭当前页面
			$("#publish").dialog('close'); 
		},
		//修改
		toEdit:function(pid){
			var customerId =$("#customerId").val();
			var path = BASE_PATH + "bizBorrowController/openbizBorrowManageAddPage.shtml?&customerId="+customerId+"&pid="+pid;

			childLayout_addTab(path,'修改理财产品');
		},
		// 跳转到投标记录详情
		toDetail:function(pid){
			$("<div id='detailDialog' /> ").dialog({
				href: basePath + "bizBorrowController/getBorrowById.shtml?pid="+pid+"&toUrl="+"financialBorrow/financialDetailByFinancialId",
				title:"理财产品-投标记录",
				method:'post',
				width:'700',
				height:'500',
				modal: true,
				buttons:[{
					text:'关闭',
					iconCls:'icon-cancel',
					handler:function(){
						$("#detailDialog").dialog('close');
					}
				}],
				onClose : function() {
						$(this).dialog('destroy');
					}
			});
		},
		//跳转到撤销页面
		toRevoke:function(pid){
			$("<div id='revokeDialog' /> ").dialog({
				href: basePath + "bizBorrowController/toFinancialRevoke.shtml?pid="+pid,
				title:"理财产品-撤销",
				method:'post',
				width:'700',
				height:'350',
				modal: true,
				buttons:[{
					text:'撤销',
					iconCls:'icon-save',
					handler:financialBorrowManage.revoke
				}],
				onClose : function() {
						$(this).dialog('destroy');
					}
			});
		},
		//确认撤销
		revoke:function(){
			// 验证表单必填项
			if(!$("#financialRevoke").form('validate')){
				return ;
			}
			var obj = jqueryUtil.serializeObject($("#financialRevoke"));
			$.ajax({
				type: "POST",
		        async:false, 
		    	url : BASE_PATH+"bizBorrowController/revoke.shtml",
		    	data:obj,
				dataType: "json",
			    success: function(data){
			    	if(data.message.status == 200 ){
			    		$.messager.alert('操作提示',"撤销成功！",'success');
			    		$('#financialBorrowManageGrid').datagrid('reload'); 
			    		$("#revokeDialog").dialog('close');
			    	}else{
			    		$.messager.alert('操作提示',"撤销失败,请联系管理员！",'error');
			    	}
			    	
				}
			}); 
		},
		
} 
/**
 * 加载 查询数据方法 inputParam 输入查询条件 刚进入画面 加载所有
 */
function loadSysParam() {
	var obj = jqueryUtil.serializeObject($("#toolbar"));
	var objStr = JSON.stringify(obj);
	$('#financialBorrowManageGrid').datagrid('load', {
		data : objStr
	});
} 
function cheageDeadline(obj){
	alert(obj.value)
}
window.top["reload_Abnormal_Monitor"]=function(){
	$("#financialBorrowManageGrid").datagrid("load");
};