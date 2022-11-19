/**
 * 文件上传js 文件
 * 
 * @author heng.wang
 * 
 * @date 2015-10-20 
 */
var uploadProgram={
		
		// 初始化客户列表数据table
		initCusTomerDataGrid:function(){
			$('#cusTomerGrid').datagrid({    
				url:basePath + 'customerController/getCustomerSearchList.shtml',
				width:'100%',
				fit:true,
				title:'客户列表',
				toolbar:'#toolbar',
				pagination: true,
			    rownumbers:true,
			    sortOrder:'asc',
			    remoteSort:false,
			    columns:borrowCusTomeManager_Model,
			    onClickRow:function(rowIndex, rowData){
					$('#cusTomerGrid').datagrid('clearChecked');
					$('#cusTomerGrid').datagrid('checkRow',rowIndex);
			    }
			});
		},
		
}