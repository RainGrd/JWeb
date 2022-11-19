/**
 * 短信模板js类
 */
var releaseBorrowManage = {
		//初始化
		initDataGrid:function(){
			$('#releaseBorrowManageGrid').datagrid({    
				url:basePath + 'bizBorrowController/releaseBorrowManageList.shtml?borrowReleaseStatu=borrowReleaseManage',
				width:'100%',
				fit:true,
				title:'已发布借款项目管理列表',
				toolbar:'#toolbar',
				pagination: true,
			    rownumbers:true,
			    sortOrder:'asc',
			    fitColumns: true,
			    remoteSort:false,
			    columns:releaseBorrowManage_Model,
			    onLoadSuccess:function(data){
			    	if($("#releaseBorrow_context .datagrid-cell-rownumber").length > 1){
						// 设计总计表头
						$("#releaseBorrow_context .datagrid-cell-rownumber:last").html("总计");
					}else{
						$('#releaseBorrow_context').datagrid("deleteRow",0);
					}
			    },
			    onClickRow:function(rowIndex, rowData){
					$('#releaseBorrowManageGrid').datagrid('clearChecked');
					$('#releaseBorrowManageGrid').datagrid('checkRow',rowIndex);
			    }
			});
		},
		// 弹出借款项目发布
		toRelease:function(pid){
			$("<div id='releaseDialog' /> ").dialog({
				href: basePath + "bizBorrowController/toManageRelease.shtml?pid="+pid,
				title:"借款项目-发布",
				method:'post',
				width:'700',
				height:'350',
				modal: true,
				buttons:[{
					text:'发布',
					iconCls:'icon-save',
					handler:releaseBorrowManage.toReleaseConfirm
				},{
					text:'关闭',
					iconCls:'icon-cancel',
					handler:function(){
						$("#releaseDialog").dialog('close');
					}
				}],
				onClose : function() {
						$(this).dialog('destroy');
					}
			});
		},
		// 发布
		release:function(){
			// 验证表单必填项
			if(!$("#bizBorrowRelease").form('validate')){
				return ;
			}
			var obj = jqueryUtil.serializeObject($("#bizBorrowRelease"));
			$.ajax({
				type: "POST",
		        async:false, 
		    	url : BASE_PATH+"bizBorrowController/manageRelease.shtml",
		    	data:obj,
				dataType: "json",
			    success: function(data){
			    	if(data.message.status == 200 ){
			    		$.messager.alert('操作提示',"发布成功！",'success');
			    		$('#releaseBorrowManageGrid').datagrid('reload'); 
			    		$("#releaseDialog").dialog('close');
			    		$("#releaseConfirmDialog").dialog('close');
			    	}else{
			    		$.messager.alert('操作提示',"发布失败,请联系管理员！",'error');
			    	}
			    	
				}
			}); 
		},
		resetForm:function(){
			$("#searcm").form("reset");
		},
		// 弹出借款项目发布确认
		toReleaseConfirm:function(){
			// 验证表单必填项
			if(!$("#bizBorrowRelease").form('validate')){
				return ;
			}
			var obj = jqueryUtil.serializeObject($("#bizBorrowRelease"));
			var objStr = JSON.stringify(obj);
			$("<div id='releaseConfirmDialog' /> ").dialog({
				href: basePath + "bizBorrowController/toManageReleaseConfirm.shtml?pid="+pid+"&data="+objStr,
				title:"借款项目-发布确认",
				method:'post',
				width:'700',
				height:'350',
				modal: true,
				buttons:[{
					text:'确认',
					iconCls:'icon-save',
					handler:releaseBorrowManage.release
				},{
					text:'关闭',
					iconCls:'icon-cancel',
					handler:function(){
						$("#releaseConfirmDialog").dialog('close');
					}
				}],
				onClose : function() {
						$(this).dialog('destroy');
					}
			});
		},
		searchCd:function(){
			$('table .moreTr').each(function(){
				if($(this).hasClass('none')){
					$(this).removeClass('none');
				}else{
					$(this).addClass('none');
				}
			});
			releaseBorrowManage.initDataGrid();	
		},
		searchData:function(){
			jqueryUtil.ajaxSearchPage("#releaseBorrowManageGrid","#searcm");
		},
		//跳转到撤销页面
		toRevoke:function(pid){
			$("<div id='revokeDialog' /> ").dialog({
				href: basePath + "bizBorrowController/toRevoke.shtml?pid="+pid,
				title:"已发布借款项目-撤销",
				method:'post',
				width:'700',
				height:'350',
				modal: true,
				buttons:[{
					text:'撤销',
					iconCls:'icon-save',
					handler:releaseBorrowManage.revoke
				}],
				onClose : function() {
						$(this).dialog('destroy');
					}
			});
		},
		//确认撤销
		revoke:function(){
			// 验证表单必填项
			if(!$("#bizBorrowRevoke").form('validate')){
				return ;
			}
			var obj = jqueryUtil.serializeObject($("#bizBorrowRevoke"));
			$.ajax({
				type: "POST",
		        async:false, 
		    	url : BASE_PATH+"bizBorrowController/revoke.shtml",
		    	data:obj,
				dataType: "json",
			    success: function(data){
			    	if(data.message.status == 200 ){
			    		$.messager.alert('操作提示',"撤销成功！",'success');
			    		$('#releaseBorrowManageGrid').datagrid('reload'); 
			    		$("#revokeDialog").dialog('close');
			    	}else{
			    		$.messager.alert('操作提示',"撤销失败,请联系管理员！",'error');
			    	}
			    	
				}
			}); 
		}
		
}

$(document).ready(function(){
	releaseBorrowManage.initDataGrid();	
});