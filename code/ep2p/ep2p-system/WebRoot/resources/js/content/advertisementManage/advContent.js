$(function() {
	getContentData(); 
})
/**
 * 加载页面数据
 */
function getContentData() {
	var advId = $("#advId").val(); 
	$('#grid')
			.datagrid(
					{
						url : basePath
								+ 'contAdvContentController/getAdvContentByAdvId.shtml?advId='
								+ advId ,
						width : '100%',
						fit : true,
						title : '',
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
 
// 二级列格式
var content_model = [ [ {
	field : 'pid',
	checkbox : true
}, {
	field : 'titleName',
	title : '广告名称 ',
	width : '10%',
	align : 'center',
	sortable : true,
	formatter : formatPreview
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

//格式化预览
 function formatPreview(value, row, index){
	 return '<a onclick="preview(\'' + row.fileId + '\',\''+row.fileUrl +'\',\'' +row.url +'\')" href="javascript:void(0)">'+row.titleName+'</a>';
 }
/**
 * 预览
 * @param fileId 文件id
 * @param fileUrl 文件上传的路径
 * @param url 点击图片跳转的地址
 */
 function preview(fileId,fileUrl,url){
	 var path = BASE_PATH + "contAdvContentController/preview.shtml?fileId="+fileId+"&fileUrl="+fileUrl+"&url="+url;
	//因为Tomcat服务器会自动帮你做一次URLDecode，所以再加上你自己在代码里面写的URLDecode 所以要做两边encode
	path = encodeURI(encodeURI(path));
	var str = "预览";
	childLayout_addTab(path,str);
 }
// 操作 格式化
function formatOperation(value, row, index) {
	var str = '<a onclick="editCont(\'' + row.pid 
			+ '\')" href="javascript:void(0)">编辑</a>&nbsp;&nbsp;&nbsp;&nbsp;'; 
		str += '<a  href="javascript:void(0)" onClick="MoveUp('+index+')">上移</a>&nbsp;&nbsp;&nbsp;&nbsp;<a href="javascript:void(0)" onClick="MoveDown('+index+')">下移</a>&nbsp;&nbsp;&nbsp;&nbsp;';
	return str;
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
	var advId = $("#advId").val(); 
//	window.location.href = BASE_PATH
//			+ "contAdvContentController/toAddPage.shtml?pid="+pid+"&advId="+advId;

	$("<div id='addDialog' /> ").dialog({
		href: BASE_PATH	+ "contAdvContentController/toAddPage.shtml?pid="+pid+"&advId="+advId, 
		title:"广告内容编辑",
		method:'post',
		width:'460px',
		height:'260px',
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
	var advId = $("#advId").val(); 
	window.location.href = BASE_PATH
				+ "contAdvContentController/toAdvContentList.shtml?advId="
			+ advId;
}
/**
 * 跳转到 栏目内容管理 新增or修改页面
 */
function toAddPage() {
	var advId = $("#advId").val(); 
	//window.location.href = BASE_PATH
	//		+ "contAdvContentController/toAddPage.shtml?advId=" + advId;
	$("<div id='addDialog' /> ").dialog({
		href: BASE_PATH	+ "contAdvContentController/toAddPage.shtml?advId=" + advId,
		title:"广告内容新增",
		method:'post',
		width:'460px',
		height:'260px',
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
 * 保存修改 方法
 */
function addContent() {
	if ($("#titleNameForm").val() == null || $("#titleNameForm").val() == "") {
		$.messager.alert('操作提示',"广告名称不能为空！",'info');  
		return;
	} 
	 
	if ($("#txt2").val() == "") {
		$.messager.alert('操作提示',"文件路径不能为空！",'info'); 
		return;
	}  
	var obj = jqueryUtil.serializeObject($("#selectContForm"));
	var objStr = JSON.stringify(obj);
	var pid = $("#pidForm").val();
	var advId = $("#advIdForm").val();
	//去最后一个.的位置  取得文件的类型
	var fileName = $("#txt2").val().substring($("#txt2").val().lastIndexOf(".")); 
	if(fileName == ".jpg" || fileName == ".bmp" || fileName== ".gif" || fileName ==".jpeg" || fileName==".png"){
		
	}else{
		$.messager.alert('操作提示',"上传的图片只能是：jpg、bmp、gif、jpeg、png格式",'info'); 
		return ;
	} 
	$("#selectContForm").form('submit', {
		url: BASE_PATH
		+ "contAdvContentController/saveOrUpdateContent.shtml?pid="+ pid,
		onSubmit : function() {return $(this).form('validate');},
		success : function(data) { 
				$.messager.alert('操作提示',"操作成功！",'success');  
				window.location.href = BASE_PATH
						+ "contAdvContentController/toAdvContentList.shtml?advId="+ advId ; 
		},
		error:function(){
			$.messager.alert('操作提示',"操作失败！",'success');  
		}
	});
	
//	$
//			.ajax({
//				type : "POST",
//				url : BASE_PATH
//						+ "contAdvContentController/saveOrUpdateContent.shtml?pid="
//						+ pid,
//				data : {
//					data : objStr
//				},
//				dataType : "json",
//				success : function(data) {
//					if (data.message.status == "200") {
//						if ($("input[name='tagIds']:checked").length > 0) {
//							addTag(data.content);
//						}
//						$.messager.alert('操作提示',"添加成功！",'success');  
//						window.location.href = BASE_PATH
//								+ "contAdvContentController/toAdvContentList.shtml?advId="
//								+ advId ;
//					} else { 
//						$.messager.alert('操作提示',"添加失败！",'error');   
//					}
//				},
//				error : function() {
//					$.messager.alert('操作提示',"添加失败！",'error');   
//				}
//			});
}
 
 
/**
 * 启用 禁用 方法 statuts =1 启用 status= 2 禁用
 */
function activationOrDisable(status) {
	if ($('#grid').datagrid("getSelections").length <= 0) {
		$.messager.alert('操作提示',"至少选择一项！",'success'); 
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
    							+ "contAdvContentController/batchUpdateByPids.shtml?pids="
    							+ pids,
    					data : {
    						"data" : '{"status":"' + status + '","pid":"' + pids
    								+ '"}'
    					},
    					dataType : "json",
    					success : function(data) {
    						if (data.message.status == "200") {
    							if (status == "1") {
    								$.messager.alert('操作提示',"启用成功!",'success');  
    							} else {
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
    						} else {
    							$.messager.alert('操作提示',"禁用失败!",'error'); 
    						}
    					}
    				});
        }  
        
    });
	return
  
}

function MoveUp(index) {
   /*
	 * var row = $("#grid").datagrid('getSelected'); var index =
	 * $("#grid").datagrid('getRowIndex', row);
	 */
    mysort(index, 'up', 'grid');
    $("#grid").datagrid('clearChecked'); 
     
}
// 下移
function MoveDown(index) {
    /*
	 * var row = $("#grid").datagrid('getSelected'); var index =
	 * $("#grid").datagrid('getRowIndex', row);
	 */
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
 * 上移 下移 更新数据库
 * 
 * @param newObject
 * @param OldObject
 */
function updateOrder(newObject,oldObject){
	var newObjectId = newObject.pid;
	var newObjectOrder = convertDateDetail(newObject.advOrder,"");
	var oldObjectId = oldObject.pid;
	var oldObjectOrder = convertDateDetail(oldObject.advOrder,"");
	$.ajax({
		type : "POST",
		url : BASE_PATH + "contAdvContentController/updateOrder.shtml?newObjectId="
				+ newObjectId +"&newObjectOrder=" + newObjectOrder+ "&oldObjectId=" + oldObjectId +"&oldObjectOrder=" + oldObjectOrder, 
		dataType : "json",
		success : function(data) {
		},
		error : function() {
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
			// $('#dictContName').combobox('reload',str);
 
		},
		error : function() { 
			$.messager.alert('操作提示',"查询失败！",'error');    
		}
	});
}
