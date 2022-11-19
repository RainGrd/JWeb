/**
 * 借款项目发布js类
 */
var borrowDetail = {
		//初始化
		initDataGrid:function(){
			var borrowId = $('#borrowId').val();
			$('#bizBorrowDetailGrid').datagrid({    
				url:basePath + 'bizBorrowDetailController/borrowDetailListByBId.shtml?borrowId='+borrowId,
				width:'100%',
				fit:true,
				title:'借款投标详情',
				pagination: true,
			    rownumbers:true,
			    sortOrder:'asc',
			    fitColumns: true,
			    toolbar:'#bizBorrowDetailToolbar',
			    remoteSort:false,
			    columns:borrowDetailManage_Model,
			    onClickRow:function(rowIndex, rowData){
					$('#bizBorrowDetailGrid').datagrid('clearChecked');
					$('#bizBorrowDetailGrid').datagrid('checkRow',rowIndex);
			    }
			});
		}		
}

$(document).ready(function(){
	borrowDetail.initDataGrid();	
});