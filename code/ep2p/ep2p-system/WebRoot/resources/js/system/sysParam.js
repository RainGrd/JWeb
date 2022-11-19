$(function() {
	initDataGrid();
});

/**
 * 初始化 datagrid
 */
function initDataGrid() {
	$('#grid').datagrid({
		url : basePath + 'sysParamsController/loadSysParams.shtml',
		width : '100%',
		fit : true,
		title : '系统参数列表',
		toolbar : '#toolbar',
		pagination : true,
		rownumbers : true,
		sortOrder : 'asc',
		remoteSort : false,
		columns : Param_model,
		onClickRow : function(rowIndex, rowData) {
			$('#grid').datagrid('clearChecked');
			$('#grid').datagrid('checkRow', rowIndex);
		}
	});
}
/**
 * 加载 查询数据方法 inputParam 输入查询条件 刚进入画面 加载所有
 */
function loadSysParam(inputParam) {
	var obj = jqueryUtil.serializeObject($("#searchFrom"));
	var objStr = JSON.stringify(obj);
	$('#grid').datagrid('load', {
		data : objStr
	});
}
/**
 * 加载 查询数据方法 inputParam 输入查询条件 刚进入画面 加载所有
 */
function addParam(inputParam) {
	var pid = $("#pid").val();
	var paramKey = $("#paramKey").val();
	var paramValue = $("#paramValue").val();
	var paramDesc = $("#paramDesc").val();
	if (paramKey == "") {
		$.messager.alert("操作提示","参数主键不能为空");
		return;
	}
	if (paramValue == "") {
		$.messager.alert("操作提示","参数值不能为空"); 
		return;
	}
	if (paramDesc == "") {
		$.messager.alert("操作提示","描述不能为空");  
		return;
	}
	$.ajax({
		type : "POST",
		url : BASE_PATH
				+ "sysParamsController/saveOrUpdateSysParams.shtml?pid=" + pid,
		data : {
			"data" : '{"paramKey":' + paramKey + ',"paramValue":"' + paramValue
					+ '","paramDesc":"' + paramDesc + '","pid":"' + pid + '"}'
		},
		dataType : "json",
		success : function(data) {
			$.messager.alert("操作提示","保存成功");   
			window.location.href = BASE_PATH
					+ "sysParamsController/toPage.shtml";
		},
		error : function() {
			$.messager.alert("操作提示","保存失败");    
		}
	});
}
function retPage() {
	window.location.href = BASE_PATH + "sysParamsController/toPage.shtml";
}
var Param_model = [ [ {
	field : 'pid',
	checkbox : true
}, {
	field : 'paramKey',
	title : '参数主键 ',
	width : '20%',
	align : 'center',
	sortable : true
}, {
	field : 'paramValue',
	title : '参数值',
	width : '20%',
	align : 'center',
	sortable : true
}, {
	field : 'paramDesc',
	title : '描述',
	width : '20%',
	align : 'center',
	sortable : true
}, {
	field : 'pids',
	title : '修改',
	width : '25%',
	align : 'center',
	formatter : formatOperation
} ] ];
// 操作 格式化
function formatOperation(value, row, index) {
	return '<a onclick="editPara(\'' + row.pid
			+ '\')" href="javascript:void(0)">修改</a>';
}
// 跳转到修改页面
function editPara(pid) {
	window.location.href = BASE_PATH
			+ "sysParamsController/editParamByPid.shtml?pid=" + pid;
}
function batchDelete() {
	if ($('#grid').datagrid("getSelections").length <= 0) { 
		$.messager.alert("操作提示","至少选择一项");  
		return;
	}
	$.messager.confirm("操作提示","是否确认删除?", function (data) {  
		 if (data) {   
				var length = $('#grid').datagrid("getSelections").length
				var pids = ""
				for (var i = 0; i < length; i++) {
					pids += $('#grid').datagrid("getSelections")[i].pid + ",";
				}
				pids = pids.substr(0, pids.lastIndexOf(","))
				$.ajax({
					type : "POST",
					url : BASE_PATH + "sysParamsController/batchDeletePara.shtml?pids="
							+ pids,
					dataType : "json",
					success : function(data) {
						if (data.message.status == "200") {
							$.messager.alert("操作提示","删除成功");   
							$('#grid').datagrid('loadData', {
								total : 0,
								rows : []
							});
							loadSysParam("");
						}
					},
					error : function() {
						$.messager.alert("删除失败","删除成功"); 
					}
				});
			}

	 });
}
// 跳转到新增页面
function addPara() {
	window.location.href = BASE_PATH + "sysParamsController/toAdd.shtml";
}