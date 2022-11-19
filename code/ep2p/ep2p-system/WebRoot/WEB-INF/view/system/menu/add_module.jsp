<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">
<div data-options="region:'center',border:false" class="p10">
		<form id="menu_add_module" name="menu_add_module" method="POST">
			<table class="menu_add_module_table form_table_one " >
				<tr>
					<td class="align_right" style="width: 120px;">菜单名称：</td>
					<td><input type="text" class="text_style easyui-validatebox"
						id="menuName" name="menuName" data-options="required:true" /></td>
				</tr>
				<tr>
					<td class="align_right">模块顺序：</td>
					<td><input type="text" class="text_style easyui-validatebox"
						id="menuOrder" name="menuOrder" data-options="required:true" /></td>
				</tr>
				<tr>
					<td class="align_right">图标名称：</td>
					<td><input type="text" class="text_style easyui-validatebox"
						id="menuIco" name="menuIco" data-options="required:true" /></td>
				</tr>
				<tr>
					<td class="align_right">菜单可见性：</td>
					<td>
						<select id="menuVisible" class="easyui-combobox" name="menuVisible" style="width: 60%">
							<option value="1">显示</option>
							<option value="2">隐藏</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="align_right">菜单描述：</td>
					<td>
						<textarea id="menuDesc" name="menuDesc"  rows="5" style="width: 70%"></textarea>
						<input type="hidden" name="menuLevel" id="menuLevel" value="1">
					</td>
				</tr>
			</table>
		</form>
</div>
</body>