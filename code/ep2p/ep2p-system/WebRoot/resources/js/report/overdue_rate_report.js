/**
 * 逾期率报表js类
 */
var overdueRate = {
		// 初始化客户列表数据table
		initOverdueRateGrid:function(){
			$('#overdueRateGrid').datagrid({    
				url:basePath + 'report/bizBorrowReportController/getOverdueRate.shtml',
				width:'100%',
				fit:true,
				title:'逾期率统计',
				toolbar:'#toolbar',
				pagination: false,
			    rownumbers:false,
			    sortOrder:'asc',
			    remoteSort:false,
			    columns:overdueRate_model,
			    onLoadSuccess:function(){
			    	
			    }
			});
		}
		
}
