<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">
	<script type="text/javascript" src="${basePath}resources/js/system/model/button_model.js" charset="utf-8"></script>
	<script type="text/javascript" src="${basePath}resources/js/system/button/button.js" charset="utf-8"></script>
	<div class="formWrap" data-options="region:'center',border:false">
		<form id="button_add_or_update_form" name="button_add_or_update_form" method="POST">
			<input type="hidden" id="pid" name="pid" value="${pid}">
			<table class="button_form_table_one">
				<tr>
					<td class="align_right" >按钮名称：</td>
					<td><input type="text" class="text_style easyui-validatebox"
						id="buttonName" name="buttonName" data-options="required:true" /></td>
				</tr>
				<!-- <tr>
					<td class="align_right">按钮编码：</td>
					<td>
						<input id="buttonCode" class="text_style easyui-validatebox" name="buttonCode"/>
					</td>
				</tr> -->
				<tr>
					<td class="align_right">按钮图标：</td>
					<td>
						<input id="buttonIco" class="text_style easyui-validatebox" name="buttonIco"/>
					</td>
				</tr>
			</table>
		</form>
	</div>
</body>