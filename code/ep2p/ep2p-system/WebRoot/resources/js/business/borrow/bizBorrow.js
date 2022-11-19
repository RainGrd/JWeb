/**
 * 借款项目发布js类
 */
var bizBorrow = {
		//初始化
		initDataGrid:function(){
			$('#bizBorrowGrid').datagrid({    
				url:basePath + 'bizBorrowController/bizBorrowList.shtml?approveStatus=6&isApproveList=true&borrowReleaseStatu=borrowRelease',
				width:'100%',
				fit:true,
				title:'借款项目发布列表',
				toolbar:'#toolbar',
				pagination: true,
			    rownumbers:true,
			    sortOrder:'asc',
			    fitColumns: true,
			    remoteSort:false,
			    columns:bizBorrow_model,
			    onLoadSuccess:function(data){
			    	if($("#borrowRelease_context .datagrid-cell-rownumber").length > 1){
						// 设计总计表头
						$("#borrowRelease_context .datagrid-cell-rownumber:last").html("总计");
					}else{
						$('#borrowRelease_context').datagrid("deleteRow",0);
					}
			    },
			    onClickRow:function(rowIndex, rowData){
					$('#bizBorrowGrid').datagrid('clearChecked');
					$('#bizBorrowGrid').datagrid('checkRow',rowIndex);
			    }
			});
		},
		// 弹出借款项目发布
		toRelease:function(pid){
			$("<div id='releaseDialog' /> ").dialog({
				href: basePath + "bizBorrowController/toRelease.shtml?pid="+pid,
				title:"借款项目-发布",
				method:'post',
				width:'700',
				height:'450',
				modal: true,
				buttons:[{
					text:'发布',
					iconCls:'icon-save',
					handler:bizBorrow.toReleaseConfirm
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
			var obj = jqueryUtil.serializeObject($("#bizBorrowReleaseConfirm"));
			$.ajax({
				type: "POST",
		        async:false, 
		    	url : BASE_PATH+"bizBorrowController/release.shtml",
		    	data:obj,
				dataType: "json",
			    success: function(data){
			    	if(data.message.status == 200 ){
			    		$.messager.alert('操作提示',"发布成功！",'success');
			    		$('#bizBorrowGrid').datagrid('reload'); 
			    		$("#releaseConfirmDialog").dialog('close');
			    		$("#releaseDialog").dialog('close');
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
				href: basePath + "bizBorrowController/toReleaseConfirm.shtml?data="+objStr,
				title:"借款项目-发布确认",
				method:'post',
				width:'700',
				height:'350',
				modal: true,
				buttons:[{
					text:'确认',
					iconCls:'icon-save',
					handler:bizBorrow.release
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
			
			bizBorrow.initDataGrid();	
		},
		searchData:function(){
			jqueryUtil.ajaxSearchPage("#bizBorrowGrid","#searcm");
		},
		/**
		 * 查询框清空
		 */
		resetInput:function(){
			$("#searcm input").val("");
		}
}

$(document).ready(function(){
	bizBorrow.initDataGrid();	
});