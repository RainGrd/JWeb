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
				url:basePath + 'bizInvestController/getList.shtml',
				width:'100%',
				fit:true,
				title:'投资项目明细查询',
				toolbar:'#toolbar',
				pagination: true,
			    rownumbers:true,
			    sortOrder:'asc',
			    fitColumns: true,
			    remoteSort:false,
			    columns:invest_info_model,
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
		searchData:function(){
			jqueryUtil.ajaxSearchPage("#invest_manage_grid","#invest_manage_form");
		},
		//跳转至数据下载页面
		toDownloadPage:function(){
			$.comdownload("#invest_manage_grid","#invest_manage_form",
					basePath + "bizInvestController/exportDownLoadFile.shtml");
		}
}

$(document).ready(function(){
	InvestManage.initDataGrid();	
});