/**
 * 借款项目类型金额占比js类
 */
var bizBorrowTypeStat = {
		// 初始化客户列表数据table
		initBizBorrowTypeStatGrid:function(){
			$('#selectStatByBorrowTypeData').datagrid({    
				url:basePath + 'report/bizBorrowReportController/selectStatByBorrowTypeData.shtml',
				width:'100%',
				fit:true,
				title:'借款项目类型数据占比',
				toolbar:'#toolbar',
				pagination: false,
			    rownumbers:false,
			    sortOrder:'asc',
			    remoteSort:false,
			    columns:bizBorrowTypeStat_model,
			    onLoadSuccess:function(){
			    	
			    }
			});
		}
		
}

$(function(){
	bizBorrowTypeStat.initBizBorrowTypeStatGrid();
});