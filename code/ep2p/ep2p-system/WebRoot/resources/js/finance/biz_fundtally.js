/**
 * 业务提现审核类js
 */
var BizFundtally = {
	/**
	 * 初始化数据
	 */
	initDataGrid:function(){
		$('#biz_fundtally_grid').datagrid({    
			url:basePath + 'bizFundtallyController/selectBizFundtallyPage.shtml',
			width:'100%',
			fit:true,
			title:'系统收支明细',
			toolbar:'#biz_fundtally_toolbar',
			pagination: true,
		    rownumbers:true,
		    sortOrder:'asc',
		    remoteSort:false,
		    columns:biz_fundtally_model,
		    onClickRow:function(rowIndex, rowData){
				$('#biz_fundtally_grid').datagrid('clearChecked');
				$('#biz_fundtally_grid').datagrid('checkRow',rowIndex);
		    }
		});
	},
	//查询按钮click事件
	searchButClick: function(){
		var obj = jqueryUtil.serializeObject($("#biz_fundtally_form"),true);
		$('#biz_fundtally_grid').datagrid('load',obj);
	},
	/**
	 * 重置按钮
	 * @param formId form表单ID
	 */
	resetButClick:function(formId){
		$(formId).form('reset');
	},
	//跳转至数据下载页面
	toDownloadPage:function(){
		$.comdownload("#biz_fundtally_grid","#biz_fundtally_form",
				basePath + "bizFundtallyController/exportDownLoadFile.shtml");
	}
}
