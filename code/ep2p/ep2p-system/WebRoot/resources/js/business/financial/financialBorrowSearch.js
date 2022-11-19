/**
 * 借款项目发布js类
 */
var financialBorrowSearch = {
		//初始化
		initDataGrid:function(){
			$('#financialBorrowSearchGrid').datagrid({    
				url:basePath + 'bizBorrowController/searchBorrowList.shtml?borrowType=3&borStatus=2',
				width:'100%',
				fit:true,
				title:'理财产品查询列表',
				toolbar:'#toolbar',
				pagination: true,
			    rownumbers:true,
			    sortOrder:'asc',
			    fitColumns: true,
			    remoteSort:false,
			    columns:financialBorrowSearchManage_Model,
			    onLoadSuccess:function(data){
			    	if($("#financialBorrow_context .datagrid-cell-rownumber").length > 1){
						// 设计总计表头
						$("#financialBorrow_context .datagrid-cell-rownumber:last").html("总计");
					}else{
						$('#financialBorrow_context').datagrid("deleteRow",0);
					}
			    },
			    onClickRow:function(rowIndex, rowData){
					$('#financialBorrowSearchGrid').datagrid('clearChecked');
					$('#financialBorrowSearchGrid').datagrid('checkRow',rowIndex);
			    }
			});
		},
		resetForm:function(){
			$("#searcm").form("reset");
		},
		searchData:function(){
			jqueryUtil.ajaxSearchPage("#financialBorrowSearchGrid","#searcm");
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
					handler:financialBorrowSearch.revoke
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
			    		$('#financialBorrowSearchGrid').datagrid('reload'); 
			    		$("#revokeDialog").dialog('close');
			    	}else{
			    		$.messager.alert('操作提示',"撤销失败,请联系管理员！",'error');
			    	}
			    	
				}
			}); 
		}
}

$(document).ready(function(){
	financialBorrowSearch.initDataGrid();	
});