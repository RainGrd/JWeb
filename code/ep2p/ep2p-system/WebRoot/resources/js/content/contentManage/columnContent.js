$(function() {
	
	$("input[name='isLowerLevel']").click(function() { 
		if ($(this).is(":checked") && $("#tag").val()!=1) {
			$(".level").hide("slow");
		} else {
			$(".level").show("slow");
		}
		if ($("#isCustomFileShow").is(":checked")) {
			$("#file").show();
		} else {
			$("#file").hide();
		} 
	});
	$("#isCustomFileShow").click(function() {
		$("#file").show("slow");
	});
	$("#notIsCustomFile").click(function() {
		$("#file").hide("slow");
	}); 
})
var coluName ="";
/**
 * 加载页面数据
 */
function getContentData() {
	var parentId = $("#parentId").val();
	var tag = $("#tag").val();
	coluName = $("#coluName").val();
	$('#grid')
			.datagrid(
					{
						url : basePath
								+ 'columnContentController/getColContentByColId.shtml?parentId='
								+ parentId + "&tag=" + tag,
						width : '100%',
						fit : true,
						title : coluName,
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
/**
 * 加载子页面数据
 */
function getContentLowData() {
	var parentId = $("#parentId").val();
	var tag = $("#tag").val();// 页面标示
	coluName = $("#coluName").val();
	$('#grid')
			.datagrid(
					{
						url : basePath
								+ 'columnContentController/getColContentByColId.shtml?parentId='
								+ parentId + "&tag=" + tag,
						width : '100%',
						fit : true,
						title : coluName,
						toolbar : '#toolbar',
						pagination : true,
						rownumbers : true,
						sortOrder : 'desc',
						remoteSort : false,
						columns : content_low_model,
						onClickRow : function(rowIndex, rowData) {
							$('#grid').datagrid('clearChecked');
							$('#grid').datagrid('checkRow', rowIndex);
						}
					});

}
// 二级列格式
var content_model = [ [ {
	field : 'pid',
	checkbox : true
}, {
	field : 'titleName',
	title : '标题名称 ',
	width : '10%',
	align : 'center',
	sortable : true,
	formatter : formatPreview
}, {
	field : 'dictContName',
	title : '所属标签',
	width : '10%',
	align : 'center',
	sortable : true
}, {
	field : 'status',
	title : '状态',
	width : '5%',
	align : 'center',
	sortable : true,
	formatter : formatStatusTow
}, {
	field : 'isLowerLevel',
	title : '是否存在下级',
	width : '8%',
	align : 'center',
	sortable : true,
	formatter : formatLowerLevel
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

// 三级列格式
var content_low_model = [ [ {
	field : 'pid',
	checkbox : true
}, {
	field : 'titleName',
	title : '标题名称 ',
	width : '10%',
	align : 'center',
	sortable : true, 
	formatter : formatPreview
}, {
	field : 'dictContName',
	title : '所属标签',
	width : '10%',
	align : 'center',
	sortable : true
}, {
	field : 'beginTime',
	title : '上架时间 ',
	width : '10%',
	align : 'center',
	sortable : true,
	formatter:convertDateDetail
}, {
	field : 'endTime',
	title : '下架时间',
	width : '10%',
	align : 'center',
	sortable : true,
	formatter:convertDateDetail
}, {
	field : 'status',
	title : '状态',
	width : '5%',
	align : 'center',
	sortable : true,
	formatter : formatStatusTow
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
	formatter : convertDate
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
	formatter : convertDate
}, {
	field : 'pids',
	title : '操作',
	width : '20%',
	align : 'center',
	formatter : formatLowOperation
} ] ];
// 操作 格式化
function formatOperation(value, row, index) {
	//var name = coluName \"-->\" row.titleName;
	var str = '<a onclick="editCont(\'' + row.pid
			+ '\')" href="javascript:void(0)">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	str += '<a  href="javascript:void(0)" onClick="MoveUp('+index+')">上移</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onClick="MoveDown('+index+')">下移</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	if (row.isLowerLevel != 0) {
		str += '<a onclick=manageCont("'
			+ row.pid +'","'+ coluName +"--"+ row.titleName +'") href="javascript:void(0)">内容管理</a>';
	}  
	return str;
}

//操作 格式化
function formatLowOperation(value, row, index) {
	var str = '<a onclick="editCont(\'' + row.pid
			+ '\')" href="javascript:void(0)">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;'; 
		str += '<a  href="javascript:void(0)" onClick="MoveUp('+index+')">上移</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onClick="MoveDown('+index+')">下移</a>';
	 
	return str;
}

function formatPreview(value, row, index){
	if (row.isLowerLevel == 0) { 
		var content = row.fileUrl == null ? row.content : row.fileUrl ;
		return '<a onclick="preview(\'' + row.pid + '\',\''+row.fileUrl+ '\',\''+fomtHtml(row.content) +'\',\'' +row.url +'\')" href="javascript:void(0)">'+row.titleName+'</a>';
	}else{
		return row.titleName;
	}
}
function preview(fileId,fileUrl,content,url){
	if(fileUrl != "null"){
		var path = BASE_PATH + "contAdvContentController/preview.shtml?fileId="+fileId+"&fileUrl="+fileUrl+"&url="+url;
		//因为Tomcat服务器会自动帮你做一次URLDecode，所以再加上你自己在代码里面写的URLDecode 所以要做两边encode
		path = encodeURI(encodeURI(path));
		var str = "预览";
		childLayout_addTab(path,str); 
	}else{
		$.messager.alert('预览',content);  
	}
}
// 是否有下级
function formatLowerLevel(value, row, index) {
	if (row.isLowerLevel == 0) {
		return "否";
	}
	return "是";
}
/**
 * 点击修改 根据id 查询到对应的实体
 * 
 * @param pid
 *            栏目内容的id
 */
function editCont(pid) {
	var parentId = $("#parentId").val();// 栏目id
	var tag=$("#tag").val();
	window.location.href = BASE_PATH
			+ "columnContentController/toAddPage.shtml?parentId=" + parentId
			+ "&tag=" + tag+"&pid="+pid;
}
/**
 * 状态格式化
 * 
 * @param value
 * @param row
 * @param index
 * @returns {String}
 */
function formatStatusTow(value, row, index) {
	if (row.status == 2) {
		return "禁用";
	}
	return "启用";
}
/**
 * 重置方法
 */
function resetInput() {
	$("#selectCont").form("reset"); 
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
 * 返回到栏目内容管理页面 需要保存 栏目id
 */
function retPage() {
	var parentId = $("#parentId").val();
	var tag = $("#tag").val();
	window.location.href = BASE_PATH
			+ "columnContentController/toColContentList.shtml?colId="
			+ parentId + "&tag=" + tag;
}
/**
 * 跳转到 栏目内容管理 新增or修改页面
 */
function toAddPage() {
	var parentId = $("#parentId").val();
	var tag = $("#tag").val();
	window.location.href = BASE_PATH
			+ "columnContentController/toAddPage.shtml?parentId=" + parentId
			+ "&tag=" + tag;
}
/**
 * 查询 所属标签
 */
function selectTag() {
	$
			.ajax({
				type : "POST",
				url : BASE_PATH
						+ "sysDistionaryContentController/selectByDisctCode.shtml?dictCode=CONT_COLUMN_TAG",
				dataType : "json",
				success : function(data) {
					var str = "";
					for (var i = 0; i < data.data.length; i++) {
						if (data.data[i].dictId != null) {
							str += " <input type='checkbox' value='"
									+ data.data[i].pid
									+ "' name ='tagIds' id='"
									+ data.data[i].pid + "'/>"
									+ data.data[i].dictContName;
						}
					}
					$("#colTag").html(str); 
					selectedTag();
				},
				error : function() {
					$.messager.alert('操作提示',"查询失败!",'error');  
				}
			});
}
/**
 * 已选择的标签
 */
function selectedTag() {
	var pid = $("#pid").val();
	$.ajax({
		type : "POST",
		url : BASE_PATH + "contTagController/selectedTag.shtml?contentId="
				+ pid,
		dataType : "json",
		success : function(data) {
			var str = "";
			for (var i = 0; i < data.rows.length; i++) {
				var tagIds = $("input[name='tagIds']");// 页面标签的集合
				for (var j = 0; j < tagIds.length; j++) {
					if (tagIds[j].id == data.rows[i].dictContId) {
						tagIds[j].checked = "true";
					}
				}
			}
		},
		error : function() {
			$.messager.alert('操作提示',"查询失败!",'error'); 
		}
	});
}

/**
 * 保存修改 方法
 */
function addContent() { 
	if ($("#titleName").val() == null || $("#titleName").val() == "") {
		$.messager.alert('操作提示',"标题名称不能为空!",'info');  
		return;
	}
	if (!$("input[name='isLowerLevel']").is(":checked")) { 
		if ($("#isCustomFileShow").is(":checked")) {
			if ($("#txt2").val() == "" && $("#txt2").attr("value") == "") {
				$.messager.alert('操作提示',"文件路径不能为空!",'info');  
				return;
			}
			//去最后一个.的位置  取得文件的类型
			var fileName = "";
			if($("#txt2").val() == ""){
				fileName = $("#txt2").attr("value").substring($("#txt2").attr("value").lastIndexOf(".")); 
				$("#txt2").val($("#txt2").attr("value"));
			}else{
				fileName = $("#txt2").val().substring($("#txt2").val().lastIndexOf(".")); 
			}
			
			if(fileName == ".jpg" || fileName == ".bmp" || fileName== ".gif" || fileName ==".jpeg" || fileName==".png" || fileName ==".html" || fileName=='.htm'){
				
			}else{
				$.messager.alert('操作提示',"上传的文件只能是：jpg、bmp、gif、jpeg、png格式 或者上传的文件是html、htm",'info'); 
				return ;
			} 
		}else{
			$("#borrowFileImg").attr("disabled","disabled");
		}
		if (($("#txt2").val() == "" && $("#txt2").attr("value") == "") && !UM.getEditor('myEditor').hasContents()) {
			$.messager.alert('操作提示',"必须上传文件或者填写内容!",'info');  
			return;
		}
		if (($("#txt2").val() != "" && $("#txt2").attr("value") != "") && UM.getEditor('myEditor').hasContents()) {
			$.messager.alert('操作提示',"只能上传文件或者填写内容!",'info');  
			return;
		}
	}else{
		if($("#isCustomFileShow").is(":checked")){ 
			if ($("#url").val() == "") {
				$.messager.alert('操作提示',"文件路径不能为空!",'info');  
				return;
			}
		} 
	} 
	
	//封面
	if($("#txt3").val() != "" && $("#txt3").val() != undefined){
		var fileNameImg = $("#txt3").val().substring($("#txt3").val().lastIndexOf(".")); 
		if(fileNameImg == ".jpg" || fileName == ".bmp" || fileName== ".gif" || fileName ==".jpeg" || fileName==".png"){
			
		}else{
			$.messager.alert('操作提示',"上传的封面图片只能是：jpg、bmp、gif、jpeg、png格式",'info'); 
			return ;
		} 
	}
	var tag = $("#tag").val(); 
	var pid = $("#pid").val();
	var parentId = $("#parentId").val(); 
	//把编辑器的值赋值给"内容"隐藏域
	$("#content").val(UM.getEditor('myEditor').getContent());
	var obj = jqueryUtil.serializeObject($("#selectCont"));
	var objStr = JSON.stringify(obj);
	$("#selectCont").form('submit', {
		url:  BASE_PATH	+ "columnContentController/saveOrUpdateContent.shtml?pid="
		+ pid,
		onSubmit : function() {return $(this).form('validate');},
		success : function(data) { 
				$.messager.alert('操作提示',"操作成功！",'success');  
				window.location.href = BASE_PATH
				+ "columnContentController/toColContentList.shtml?colId="
				+ parentId + "&tag=" + tag;
		},
		error:function(){
			$.messager.alert('操作提示',"操作失败！",'success');  
		}
	});
	
}

function addTag(data) {
	$.ajax({
		type : "POST",
		url : BASE_PATH + "contTagController/saveOrUpdateTag.shtml?contentId="
				+ data.pid + "&tagIds=" + data.tagIds + "&pid=" + data.tagPids,
		dataType : "json",
		success : function(data) {
			if (data.message == '200') {
				$.messager.alert('操作提示',"新增成功！",'success');   
				return;
			}

		},
		error : function() {
			$.messager.alert('操作提示',"新增失败！",'error'); 
		}
	});
}
/**
 * 跳转到第三级内容管理页面
 * 
 * @param pid
 */
function manageCont(pid,coluName) {
	var path = BASE_PATH
			+ "columnContentController/toColContentList.shtml?colId=" + pid
			+ "&tag=" + 1+"&coluName="+coluName; 
	childLayout_addTab(path,coluName);
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
			$
					.ajax({
						type : "POST",
						url : BASE_PATH
								+ "columnContentController/batchUpdateByPids.shtml?pids="
								+ pids,
						data : {
							"data" : '{"status":"' + status + '","pid":"' + pids
									+ '"}'
						},
						dataType : "json",
						success : function(data) {
							if (data.message.status == "200") {
								if (status == "1") {
									$.messager.alert('操作提示',"启用成功",'success');  
								} else {
									$.messager.alert('操作提示',"禁用成功",'success');  
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
								$.messager.alert('操作提示',"启用失败",'error');  
							} else {
								$.messager.alert('操作提示',"禁用失败",'error');  
							}
						}
					});
		}
	});
}

function MoveUp(index) {
   /* var row = $("#grid").datagrid('getSelected');
    var index = $("#grid").datagrid('getRowIndex', row);*/
    mysort(index, 'up', 'grid');
    $("#grid").datagrid('clearChecked'); 
     
}
//下移
function MoveDown(index) {
    /*var row = $("#grid").datagrid('getSelected');
    var index = $("#grid").datagrid('getRowIndex', row);*/
    mysort(index, 'down', 'grid');
    $("#grid").datagrid('clearChecked'); 
     
}
 
 
function mysort(index, type, gridname) {
	var newObject = null;
	var oldObject = null;
    if ("up" == type) {
        if (index != 0) {
            var toup = $('#' + gridname).datagrid('getData').rows[index];
            var todown = $('#' + gridname).datagrid('getData').rows[index - 1];
            $('#' + gridname).datagrid('getData').rows[index] = todown;
            $('#' + gridname).datagrid('getData').rows[index - 1] = toup;
            $('#' + gridname).datagrid('refreshRow', index);
            $('#' + gridname).datagrid('refreshRow', index - 1);
            $('#' + gridname).datagrid('selectRow', index - 1); 
            newObject = $('#' + gridname).datagrid('getData').rows[index];
            oldObject = $('#' + gridname).datagrid('getData').rows[index- 1]; 
        }
    } else if ("down" == type) {
        var rows = $('#' + gridname).datagrid('getRows').length;
        if (index != rows - 1) {
            var todown = $('#' + gridname).datagrid('getData').rows[index];
            var toup = $('#' + gridname).datagrid('getData').rows[index + 1];
            $('#' + gridname).datagrid('getData').rows[index + 1] = todown;
            $('#' + gridname).datagrid('getData').rows[index] = toup;
            $('#' + gridname).datagrid('refreshRow', index);
            $('#' + gridname).datagrid('refreshRow', index + 1);
            $('#' + gridname).datagrid('selectRow', index + 1);
            newObject = $('#' + gridname).datagrid('getData').rows[index];
            oldObject = $('#' + gridname).datagrid('getData').rows[index + 1];
        }
    }
    updateOrder(newObject,oldObject);
}
/**
 * 上移  下移  更新数据库
 * @param newObject
 * @param OldObject
 */
function updateOrder(newObject,oldObject){
	var newObjectId = newObject.pid;
	var newObjectOrder = convertDateDetail(newObject.coluOrder,"");
	var oldObjectId = oldObject.pid;
	var oldObjectOrder = convertDateDetail(oldObject.coluOrder,"");
	$.ajax({
		type : "POST",
		url : BASE_PATH + "columnContentController/updateOrder.shtml?newObjectId="
				+ newObjectId +"&newObjectOrder=" + newObjectOrder+ "&oldObjectId=" + oldObjectId +"&oldObjectOrder=" + oldObjectOrder, 
		dataType : "json",
		success : function(data) {
			$('#grid').datagrid('loadData', {
				total : 0,
				rows : []
			});
			loadSysParam("");
		},
		error : function() {
			$.messager.alert('操作提示',"操作失败!",'error');  
		}
	});
}
/**
 * 得到 所有标签信息
 */
function getTag(){
	
	$
	.ajax({
		type : "POST",
		url : BASE_PATH
				+ "sysDistionaryContentController/selectByDisctCode.shtml?dictCode=CONT_COLUMN_TAG",
		dataType : "json",
		success : function(data) {
			var str = "";
			for (var i = 0; i < data.data.length; i++) {
				if (data.data[i].dictId != null) {
					str +="<option value='"+data.data[i].dictId+"'>"+data.data[i].dictContName+"</option>";
				}
			}
			$('#dictContName').append(str);
			//$('#dictContName').combobox('reload',str);
		},
		error : function() {
			$.messager.alert('操作提示',"查询失败!",'error');  
		}
	});
}
//普通字符转换成转意符
function html2Escape(sHtml) {
	 return sHtml.replace(/[<>&"]/g,function(c){return {'<':'&lt;','>':'&gt;','&':'&amp;','"':'&quot;'}[c];});
}
//回车转为br标签
function return2Br(str) {
	 return str.replace(/\r?\n/g,"<br />");
}
//去除开头结尾换行,并将连续3次以上换行转换成2次换行
function trimBr(str) {
	 str=str.replace(/((\s|&nbsp;)*\r?\n){3,}/g,"\r\n\r\n");//限制最多2次换行
	 str=str.replace(/^((\s|&nbsp;)*\r?\n)+/g,'');//清除开头换行
	 str=str.replace(/((\s|&nbsp;)*\r?\n)+$/g,'');//清除结尾换行
	 return str;
}
//将多个连续空格合并成一个空格
function mergeSpace(str) {
	 str=str.replace(/(\s|&nbsp;)+/g,' ');
	 return str;
}

function fomtHtml(str){
	var temp ;
	temp = html2Escape(str);
	temp = return2Br(temp);
	temp = trimBr(temp);
	temp = mergeSpace(temp);
	return temp;
}