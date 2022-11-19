<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">
<form id="menu_add_child_node_form" name="menu_add_child_node_form" method="POST">
	<table class="menu_add_child_table form_table_one">
		<tr>
			<td class="align_right" style="width: 120px;">父级菜单：</td>
			<td>
				<input type="hidden" name="menuLevel" id="menuLevel">
				<input type="hidden" id="parentAuthId" value="${parentAuthId}" name="parentAuthId">
				<input type="text" disabled="disabled" class="text_style easyui-validatebox"
				id="parentMenuName" name="parentMenuName" value="${parentNenuName}" data-options="required:true" />
			</td>
		</tr>
		<tr>
			<td class="align_right" style="width: 120px;">菜单名称：</td>
			<td><input type="text" class="text_style easyui-validatebox"
				id="menuName" name="menuName" data-options="required:true" /></td>
		</tr>
		<tr>
			<td class="align_right">显示顺序：</td>
			<td><input type="text" class="text_style easyui-validatebox"
				id="menuOrder" name="menuOrder" data-options="required:true" /></td>
		</tr>
		<tr>
			<td class="align_right">图标名称：</td>
			<td><input type="text" class="text_style easyui-validatebox"
				id="menuIco" name="menuIco" data-options="required:true" /></td>
		</tr>
		<tr>
			<td class="align_right">菜单URL：</td>
			<td><input type="text" class="text_style easyui-validatebox"
				id="menuUrl" name="menuUrl" /></td>
		</tr>
		<tr>
			<td class="align_right">菜单可见性：</td>
			<td>
				<!-- <input type="text" style="width: 70%" class="text_style easyui-validatebox"
				id="menuVisible" name="menuVisible" /> -->
				<select id="menuVisible" class="easyui-combobox" name="menuVisible" style="width: 60%" >
					<option></option>
				    <option value="1">显示</option>   
				    <option value="2">隐藏</option>   
				</select> 
			</td>
		</tr>
		<tr>
			<td class="align_right">菜单描述：</td>
			<td>
				<textarea id="menuDesc" name="menuDesc"  rows="5" style="width: 70%"></textarea>
			</td>
		</tr>
	</table>
</form>
</body>