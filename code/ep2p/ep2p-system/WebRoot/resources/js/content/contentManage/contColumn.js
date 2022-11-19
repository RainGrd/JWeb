$(function() {
	getContentData();
})
/**
 * 加载页面数据
 */
function getContentData() {
	$('#grid').datagrid({
		url : basePath + 'contColumnController/getContentData.shtml',
		width : '100%',
		fit : true,
		title : '栏目管理',
		toolbar : '#toolbar',
		pagination : true,
		rownumbers : true,
		sortOrder : 'desc',
		remoteSort : false,
		columns : content_model,
		onClickRow : function(rowIndex, rowData) {
			$('#grid').datagrid('clearChecked');
			$('#grid').datagrid('checkRow', rowIndex);
		}
	});

}
// 列格式
var content_model = [ [ {
	field : 'pid',
	checkbox : true
}, {
	field : 'coluName',
	title : '栏目名称 ',
	width : '10%',
	align : 'center',
	sortable : true
}, {
	field : 'webTag',
	title : '前台标示 ',
	width : '10%',
	align : 'center',
	sortable : true
}, {
	field : 'status',
	title : '状态',
	width : '10%',
	align : 'center',
	sortable : true,
	formatter:formatStatusTow
}, {
	field : 'createUser',
	title : '创建人',
	width : '10%',
	align : 'center',
	sortable : true
}, {
	field : 'createTime',
	title : '创建时间',
	width : '15%',
	align : 'center',
	sortable : true,
	formatter : convertDateDetail
}, {
	field : 'lastUpdateUser',
	title : '最后更新人',
	width : '10%',
	align : 'center',
	sortable : true
}, {
	field : 'lastUpdateTime',
	title : '最后更新时间',
	width : '15%',
	align : 'center',
	sortable : true,
	formatter : convertDateDetail
}, {
	field : 'pids',
	title : '操作',
	width : '20%',
	align : 'center',
	formatter : formatOperation
} ] ];

// 操作 格式化
function formatOperation(value, row, index) {
	return '<a onclick="editCont(\''
			+ row.pid
			+ '\')" href="javascript:void(0)">编辑</a>&nbsp;<a onclick=manageCont("'
			+ row.pid +'","'+row.coluName + '") href="javascript:void(0)">内容管理</a>';
}
/**
 * 修改
 * 
 * @param pid
 */
function editCont(pid) {
	if (pid == null || pid == "") {
		$.messager.alert('操作提示',"数据错误 pid为空!",'info');  
		return;
	}
	var tag =$("#tag").val()
	//window.location.href = BASE_PATH + "contColumnController/toAddPage.shtml?pid=" + pid+"&tag="+tag+"&pid="+pid;
	
	$("<div id='addDialog' /> ").dialog({
		href:BASE_PATH + "contColumnController/toAddPage.shtml?pid=" + pid+"&tag="+tag+"&pid="+pid,
		title:"栏目编辑",
		method:'post',
		width:'350px',
		height:'200px',
		modal: true,
		buttons:[{
			text:'保存',
			iconCls:'icon-save',
			handler:function(){
				addContent();
			}
		},{
			text:'取消',
			iconCls:'icon-cancel',
			handler:function(){
				$("#addDialog").dialog('close');
			}
		}],
		onClose : function() {
				$(this).dialog('destroy');
			}
	});

}
/**
 * 内容管理
 * 
 * @param pid
 */
function manageCont(pid, coluName) {
	var path = BASE_PATH + "columnContentController/toColContentList.shtml?colId="+pid+"&tag="+0+"&coluName="+coluName;
	childLayout_addTab(path,coluName);
	//window.location.href = BASE_PATH + "columnContentController/toColContentList.shtml?colId="+pid+"&tag="+0;
}
/**
 * 加载 查询数据方法 inputParam 输入查询条件 刚进入画面 加载所有
 */
function loadSysParam() {
	var obj = jqueryUtil.serializeObject($("#selectCont"));
	var objStr = JSON.stringify(obj);
	$('#grid').datagrid('load', {
		data : objStr
	});
}
/**
 * 重置方法
 */
function resetInput() {
	$("#selectCont").form("reset"); 
}
/**
 * 跳转到新增页面
 */
function toAddPage() {
	// window.location.href = BASE_PATH + "contColumnController/toAddPage.shtml";
	$("<div id='addDialog' /> ").dialog({
		href:BASE_PATH + "contColumnController/toAddPage.shtml",
		title:"栏目新增",
		method:'post',
		width:'350px',
		height:'200px',
		modal: true,
		buttons:[{
			text:'保存',
			iconCls:'icon-save',
			handler:function(){
				addContent();
			}
		},{
			text:'取消',
			iconCls:'icon-cancel',
			handler:function(){
				$("#addDialog").dialog('close');
			}
		}],
		onClose : function() {
				$(this).dialog('destroy');
			}
	});
}
function retPage() {
	var tag = $("#tag").val();
	window.location.href = BASE_PATH + "contColumnController/toList.shtml?tag="+tag;
}
/**
 * 新增 栏目
 */
function addContent() {
	var coluName = $("#coluNameForm").val();
	var pid = $("#pid").val();
	var webTag = $("#webTag").val();
	var cloDesc =$("#cloDesc").val();
	var status = $('input:radio[name="status"]:checked').val();
	if (coluName == null || coluName == "") {
		$.messager.alert('操作提示',"栏目名称不能为空!",'info');  
		return;
	}
	if (webTag == null || webTag == "") {
		$.messager.alert('操作提示',"前台标示不能为空!",'info');  
		return;
	}
	if (status == null || status == "") {
		$.messager.alert('操作提示',"状态不能为空!",'info');  
		return;
	}
	$.ajax({
		type : "POST",
		url : BASE_PATH + "contColumnController/saveOrUpdateContent.shtml?pid="
				+ pid,
		data : {
			"data" : '{"coluName":"' + coluName + '","status":"' + status+ '","webTag":"' + webTag + '","cloDesc":"' + cloDesc+'","pid":"' + pid + '"}'
		},
		dataType : "json",
		success : function(data) {
			$.messager.alert('操作提示',"新增成功!",'success');  
			window.location.href = BASE_PATH
					+ "contColumnController/toList.shtml";
		},
		error : function() {
			$.messager.alert('操作提示',"新增失败!",'error');  
		}
	});
}
/**
 * 启用 禁用 方法 statuts =1 启用 status= 2 禁用
 */
function activationOrDisable(status) {
	if ($('#grid').datagrid("getSelections").length <= 0) {
		$.messager.alert('操作提示',"至少选择一项!",'info');  
		return;
	}
	var str = "";
	if (status == "1") {
		str = "确认启用？"
	} else {
		str = "确认禁用？"
	}
	$.messager.confirm("操作提示",str, function (data) { 
		if (data) {
			var length = $('#grid').datagrid("getSelections").length
			var pids = ""
			for (var i = 0; i < length; i++) {
				pids += $('#grid').datagrid("getSelections")[i].pid + ",";
			}
			pids = pids.substr(0, pids.lastIndexOf(","));
			$.ajax({
				type : "POST",
				url : BASE_PATH
						+ "contColumnController/batchUpdateByPids.shtml?pids="
						+ pids,
				data : {
					"data" : '{"status":"' + status + '","pid":"' + pids + '"}'
				},
				dataType : "json",
				success : function(data) {
					if (data.message.status == "200") {
						if (status == "1") {
							$.messager.alert('操作提示',"启用成功!",'success');  
						}else{
							$.messager.alert('操作提示',"禁用成功!",'success');  
						}
						$('#grid').datagrid('loadData', {
							total : 0,
							rows : []
						});
						loadSysParam("");
					}
				},
				error : function() {
					if (status == "1") {
						$.messager.alert('操作提示',"启用失败!",'error'); 
					}else{
						$.messager.alert('操作提示',"禁用失败!",'error');   
					}
				}
			});
		} 
	 });
	return
}
/**
 * 状态格式化
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function formatStatusTow (value,row,index){
	if(row.status == 2){
		return "禁用";
	}
	return "启用";
}

 