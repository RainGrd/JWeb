/**
 * 
 */
var borrowChoose= {
	//初始化数据信息
	initBorrow:function(){
		var vagrConTemId = $("#agrConTemId").val();
		$('#agreementMContext_datagrid_list').datagrid({    
			url:basePath + "contractManageController/getContractMList.shtml",
			width:'100%',
			fit:true,
			pagination: true,
			rownumbers:true,
			sortOrder:'asc',
		    remoteSort:false,
		    singleSelect:true,
		    toolbar:'#agreementMContext_list_toolbar',
		    columns:agreementMContextListModel
	       
		});
	},
	
	//搜索协议内容
	serachAgreementContext:function(){
		var borAgrAnme = $("#borAgrAnme").val(); 
		$('#agreementMContext_datagrid_list').datagrid('load',{
			borAgrAnme:borAgrAnme 
		}); 
	}
};

