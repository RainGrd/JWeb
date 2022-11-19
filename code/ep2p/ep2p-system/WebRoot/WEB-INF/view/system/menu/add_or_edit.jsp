<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="pt10"></div>
<table class="form_table_one formTable">
	<tr>
		<td class="align_right">父级菜单：</td>
		<td>
			<input type="hidden" name="menuLevel" id="menuLevel">
			<input type="text" class="text_style easyui-validatebox"
			id="parentMenuName" name="parentMenuName" />
		</td>
	</tr>
	<tr>
		<td class="align_right" >菜单名称：</td>
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
			<select id="menuVisible" class="easyui-combobox"  name="menuVisible" style="width:60%">   
				<option></option>   
			    <option value="1">显示</option>   
			    <option value="2">隐藏</option>   
			</select> 
		</td>
	</tr>
	<tr>
		<td class="align_right">菜单描述：</td>
		<td>
			<textarea id="menuDesc" name="menuDesc"  rows="5"></textarea>
			<input type="hidden" id="pid" name="pid">
			<input type="hidden" id="parentAuthId" name="parentAuthId">
		</td>
	</tr>
</table>