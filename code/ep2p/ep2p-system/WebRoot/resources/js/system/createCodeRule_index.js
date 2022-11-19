/**
 * 编号生成规则内容js类
 */
var createCodeRule = {
	//初始化
	initDataGrid:function(){
		$('#createCodeRuleGrid').datagrid({    
			url:basePath + 'sysCreateCodeRuleController/queryCreateCodeList.shtml',
			width:'100%',
			fit:true,
			method: 'POST',
			title:'编号生成规则列表',
			toolbar:'#toolbar',
			pagination: true,
		    rownumbers:true,
		    remoteSort:false,
		    fitColumns:true,
			selectOnCheck: true,
			singleSelect: false,
			checkOnSelect: true,
		    columns:createCodeRule_model,
		    onLoadSuccess:function(data){
				clearSelectRows('#createCodeRuleGrid');
			}
		});
	},
	// 查询
	searchData:function(){
		var obj = jqueryUtil.serializeObject($("#searcm"));
		var objStr = JSON.stringify(obj);
		$('#createCodeRuleGrid').datagrid('load',{data:objStr});
	}
};

/**
 * 页面数据初始化
 */
$(function(){
	createCodeRule.initDataGrid();
});