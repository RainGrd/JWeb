/**
 * 借款项目发布js类
 */
var borrowSearch = {
		//初始化
		initDataGrid:function(){
			$('#borrowSearchGrid').datagrid({    
				url:basePath + 'bizBorrowController/searchBorrowList.shtml?borrowReleaseStatu=borrowSearch',
				width:'100%',
				fit:true,
				title:'借款项目查询',
				toolbar:'#toolbar',
				pagination: true,
			    rownumbers:true,
			    sortOrder:'asc',
			    fitColumns: true,
			    remoteSort:false,
			    columns:borrowSearchManage_Model,
			    onLoadSuccess:function(data){
			    	if($("#borrowSearch_context .datagrid-cell-rownumber").length > 1){
						// 设计总计表头
						$("#borrowSearch_context .datagrid-cell-rownumber:last").html("总计");
					}else{
						$('#borrowSearch_context').datagrid("deleteRow",0);
					}
			    },
			    onClickRow:function(rowIndex, rowData){
					$('#borrowSearchGrid').datagrid('clearChecked');
					$('#borrowSearchGrid').datagrid('checkRow',rowIndex);
			    }
			});
		},
		resetForm:function(){
			$("#searcm").form("reset");
		},
		searchCd:function(){
			$('table .moreTr').each(function(){
				if($(this).hasClass('none')){
					$(this).removeClass('none');
				}else{
					$(this).addClass('none');
				}
			});
			
			borrowSearch.initDataGrid();	
		},
		searchData:function(){
			jqueryUtil.ajaxSearchPage("#borrowSearchGrid","#searcm");
		},
		/**
		 * 查询框清空
		 */
		resetInput:function(){
			$("#searcm input").val("");
		},
		// 跳转到投标记录详情
		toDetail:function(pid){
			$("<div id='detailDialog' /> ").dialog({
				href: basePath + "bizBorrowController/getBorrowById.shtml?pid="+pid+"&toUrl="+"bizBorrowDetail/bizBorrowDetailByBorrowId",
				title:"借款项目-投标记录",
				method:'post',
				width:'70%',
				height:'500',
				modal: true,
				resizable:true,
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
		}
}

$(document).ready(function(){
	borrowSearch.initDataGrid();	
});