<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/common/common.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="<%=basePath %>resources/js/system/menu/menu.js"></script>
<script type="text/javascript">
	$(document).ready(function() {
		//菜单加载
		sysMenu.initTree('menu-index-tree');
		//表单只读
		common.readonlyOrEnabledForm("menu_index_form",true);
		//隐藏修改时的按钮
		$("#menu_edit_button_div").hide();
		$('#menu_button_div .easyui-linkbutton').linkbutton('disable');
		$('#menu_button_div a[id=add_module_a]').linkbutton('enable');
	});
</script>
<title>菜单首页</title>
</head>
<body class="easyui-layout">
<div data-options="region:'center',border:false">
	
	<div class="easyui-layout" style="width:100%;height:98%;">
		<div data-options="region:'west',split:true" title="菜单管理" style="width:260px;">
			<ul class="easyui-tree" id="menu-index-tree"></ul>
		</div>
		<div data-options="region:'center',title:'详细内容'">
			<form id="menu_index_form" name="menu_index_form">
				<%@ include file="add_or_edit.jsp" %>
				<div style="text-align: center;" id="menu_button_div">
					<a id="add_module_a" class="easyui-linkbutton" data-options="iconCls:'icon-add'"  onclick="sysMenu.openAddModule();">新增模块</a>
					&nbsp;<a class="easyui-linkbutton" data-options="iconCls:'icon-edit'" onclick="sysMenu.menuEditClick();">修改</a>
					&nbsp;<a class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="sysMenu.meunDelete();">删除</a>
					&nbsp;<a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="sysMenu.openAddChildNode();">新增子节点</a>
					&nbsp;<a class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="sysMenu.openButtonAssign();">配置按钮</a>
					<!-- 
					<input type="button" class="easyui-linkbutton" value="新增模块" onclick="sysMenu.openAddModule();"> 
					<input type="button" value="修改" disabled="disabled" onclick="sysMenu.menuEditClick();">
					<input type="button" value="删除" disabled="disabled" onclick="sysMenu.meunDelete();">
					<input type="button" disabled="disabled" value="新增子节点" onclick="sysMenu.openAddChildNode();">
					<input type="button" disabled="disabled" value="配置按钮" onclick="sysMenu.add();">  -->
				</div>
				<div class="menuEditBtnDiv" id="menu_edit_button_div">
					<a class="easyui-linkbutton" data-options="iconCls:'icon-save'" onclick="sysMenu.menuEditSaveClick();">保存</a>
					<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="sysMenu.menuEditCancelClick();">取消</a>					
				</div>
			</form>
		</div>
	</div>
</div>
</body>
</html>