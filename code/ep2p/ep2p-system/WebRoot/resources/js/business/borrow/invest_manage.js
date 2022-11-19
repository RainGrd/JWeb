/**
 * 投资管理 js 类
 */
var InvestManage = {
		/**
		 * 初始化页面元素
		 */
		initDataGrid:function(){
			//加载投资数据列表
			$('#invest_manage_grid').datagrid({    
				url:basePath + 'bizBorrowDetailController/selectAllRelSelectivePage.shtml',
				width:'100%',
				fit:true,
				title:'借款项目发布列表',
				toolbar:'#toolbar',
				pagination: true,
			    rownumbers:true,
			    sortOrder:'asc',
			    fitColumns: true,
			    remoteSort:false,
			    columns:invest_manage_model,
			    onClickRow:function(rowIndex, rowData){
					$('#invest_manage_grid').datagrid('clearChecked');
					$('#invest_manage_grid').datagrid('checkRow',rowIndex);
			    }
			});
		},
		//重置查询表单
		resetForm:function(){
			$("#invest_manage_form").form("reset");
		},
		/**
		 * 显示与隐藏查询条件
		 */
		searchCd:function(){
			$('table .moreTr').each(function(){
				if($(this).hasClass('none')){
					$(this).removeClass('none');
				}else{
					$(this).addClass('none');
				}
			});
			InvestManage.initDataGrid();	
		},
		searchData:function(){
			jqueryUtil.ajaxSearchPage("#invest_manage_grid","#invest_manage_form");
		}
}

$(document).ready(function(){
	InvestManage.initDataGrid();	
});