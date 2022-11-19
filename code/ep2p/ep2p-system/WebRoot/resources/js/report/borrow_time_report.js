/**
 * 借款项目占比报表js类
 */
var borrowTime = {
		// 初始化数据table
		initborrowTimeGrid:function(){
			$('#borrowTimeGrid').datagrid({    
				url:basePath + 'report/bizBorrowReportController/getBorrowTimeP.shtml',
				width:'100%',
				fit:true,
				title:'借款项目占比统计',
				toolbar:'#toolbar',
				pagination: false,
			    rownumbers:false,
			    sortOrder:'asc',
			    remoteSort:false,
			    columns:borrowTime_model,
			    onLoadSuccess:function(){
			    	
			    }
			});
		}
		
}
