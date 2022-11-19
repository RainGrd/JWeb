<%@page language="java" pageEncoding="UTF-8"
	contentType="text/html; charset=UTF-8"%>
<%@ include file="/common/common.jsp"%>
<script type="text/javascript" src="${basePath}resources/js/system/dictionary/dictionaryContent.js" charset="utf-8"></script>
<body class="easyui-layout">

<script type="text/javascript">
$(function(){
	distionaryContent.loadData();
});
</script>

<form action="<%=basePath%>sysDistionaryContentController/save.shtml" id="distionaryContentAdd" name="distionaryContentAdd" method="post" >
	<div class="formWrap">
		<input type="hidden" id="pid" name="pid" value="${pid}">
		<input type="hidden" id="dictId" name="dictId" value="${dictId}">
		<input type="hidden" id="isUpdate" name="isUpdate" value="${isUpdate}">
		<table>
			<tr>
				<td class="label_right"><font color="red">*</font>字典编码：</td>
				<td> <input class="easyui-validatebox" id="dictContCode" name="dictContCode" required="true" data-options="validType:'length[0,20]'"  missingMessage="请输入数据字典编码!"  /> </td>
			</tr>
			<tr>
				<td class="label_right"><font color="red">*</font>顺序号：</td>
				<td> <input class="easyui-validatebox easyui-numberbox" id="dictContOrder" name="dictContOrder" required="true" missingMessage="请输入数据顺序号!" /> </td>
			</tr>
			<tr>
				<td class="label_right"><font color="red">*</font>字典名称：</td>
				<td> <input class="easyui-validatebox" id="dictContName" name="dictContName" required="true" data-options="validType:'length[0,100]'"  missingMessage="请输入数据类型名称!"    /> </td>
			</tr>
			<tr>
				<td class="label_right">状态：</td>
				<td> <input type="radio" name="status" value="1" checked="checked"/>启用&nbsp;&nbsp;&nbsp;&nbsp;<input type="radio" name="status" value="0" />禁用</td>
			</tr>
			<tr>
				<td class="label_right"><font color="red">*</font>描述：</td>
				<td > 
					<input name="dictContDesc" id="dictContDesc" required="true"   missingMessage="请输入数据描述信息!" class="easyui-textbox" style="width:300px;height:60px" data-options="multiline:true,validType:'length[0,100]'" >
				</td>
			</tr>
		</table>
	</div>
</form>
</body>