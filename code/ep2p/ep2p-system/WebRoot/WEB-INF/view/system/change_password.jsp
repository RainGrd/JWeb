<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<body class="easyui-layout">
<script type="text/javascript">
</script>
<form id="change_password_form" name="change_password_form" method="post" >
	<div>
		<table>
			<tr>
				<td class="label_right"><font color="red">*</font>旧密码：</td>
				<td> <input type="password" class="easyui-textbox" id="oldPassword" name="oldPassword" data-options="required:'true'" style="width:150px"/> </td>
			</tr>
			<tr>
				<td class="label_right"><font color="red">*</font>新密码：</td>
				<td> <input type="password" class="easyui-textbox" id="password" name="password"data-options="required:'true'"  style="width:150px"/> </td>
			</tr>
			<tr>
				<td class="label_right"><font color="red">*</font>确认密码：</td>
				<td> <input type="password" class="easyui-textbox" id="affirmPassword" name="affirmPassword"data-options="required:'true',validType:'affirmPassword'"  style="width:150px"/> </td>
			</tr>
		</table>
	</div>
</form>
</body>